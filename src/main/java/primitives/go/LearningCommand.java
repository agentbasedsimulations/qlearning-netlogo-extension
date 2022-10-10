package main.java.primitives.go;

import java.util.LinkedHashMap;
import java.util.Map;

import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.AgentLearning;
import main.java.model.Session;

public class LearningCommand implements org.nlogo.api.Command {

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax(new int[] {Syntax.BooleanType()});
	}

	@Override
	public void perform(Argument[] args, Context context) throws ExtensionException {
		AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
		
		if(agent == null) {
		      throw new ExtensionException("Agent " + context.getAgent().id() + " isn't a learner agent");
		      
		} else {
			
			  //VERIFICANDO INFORMAÇÕES
		      if(agent.getActions() == null) {
		    	  throw new ExtensionException("No action has been defined for agent " + context.getAgent().id());  
		      }
		        
		      if(agent.getLearningRate() == -1) {
		    	  throw new ExtensionException("No learning rate has been defined for agent " + context.getAgent().id());
		      }
		      
		      if(agent.getDiscountFactor() == -1) {
		    	  throw new ExtensionException("No discount factor has been defined for agent " + context.getAgent().id());
		      }
		      
		      if(agent.getActionSelection().getMethod().equals("")) {
		    	  throw new ExtensionException("No action selection method has been defined for agent " + context.getAgent().id());
		      }
		        

		      //CRIA QTABLE
		      String actualState = null;
		      try {
				actualState = agent.getState(context);
		      } catch (AgentException e1) {
				e1.printStackTrace();
		      }
			
		      LinkedHashMap<String,Double> actualQlist = new LinkedHashMap<String, Double>();
		      LinkedHashMap<String,Double> qlist = agent.getQTable().get(actualState);
		      String previousState = agent.getPreviousState();

		      if(qlist == null) {
		    	  for(int i = 0; i < agent.getActions().size(); i++) {
		    		  actualQlist.put(agent.getActions().get(i).toString(), 0.00);
		    	  }
		          agent.getQTable().put(actualState, actualQlist);
		      } else {
		          actualQlist = qlist;
		      }
		      
		      //ESCOLHE PROXIMA AÇÃO
		      int actionActualState = agent.getActionSelection().getAction(actualQlist);
		      
		      //EXECUTA AÇÃO
		      agent.getActions().get(actionActualState).perform(context, args);
		      
		      //PEGA VALOR ESTADO ATUAL
		      Double qValueActualState = null;
		      int contAct = 0;
		      for (Map.Entry<String, Double> entry : actualQlist.entrySet()) {
			        if (contAct == actionActualState ) {
			        	qValueActualState = entry.getValue() == null ? 0.00 : entry.getValue();
			        }
			        contAct++;
			    }
		      
		      //RECEBE RECOMPENSA
		      Double reward = (Double) agent.getReward().report(context, args);
		      
		      //ATUALIZA ESTADO
		      String newState = null;
		      try {
				newState = agent.getState(context);
		      } catch (AgentException e) {
				e.printStackTrace();
		      }
		      Double newStateBestAction = agent.getBestActionExpectedReward(newState);
		      
		      //ALGORITMO QLEARNING
		      Double newQvalue =
		        qValueActualState + (agent.getLearningRate() * (reward + (agent.getDiscountFactor() * newStateBestAction) - qValueActualState));

		      //ATUALIZA QTABLE		    
		      int cont = 0;
				for (Map.Entry<String, Double> entry : actualQlist.entrySet()) {
			       if (cont == actionActualState) {
			            entry.setValue(newQvalue);
			       }
			      cont++;
			  }
				
			  LinkedHashMap<String, Double> newQlist = actualQlist;
			  if(previousState != null) {
			      agent.getQTable().put(previousState, newQlist);
			    }

		      //IMPRIME QTABLE
			  if(args.length > 0 && args[0].getBooleanValue()) {
			        String print =
			        		"S: estado, R: recompensa, A: ação, t: tempo \n" +
			        		          "S(t-1): " + previousState + "\n" +
			        		          "A(t-1): " + actionActualState + "\n" +
			        		          "Q(t-1): " + actualQlist.toString() + "\n" +
			        		          "R(t): " + reward + "\n" +
			        		          "S(t): " + newState + "\n" +
			        		          "A(t): " + newStateBestAction + "\n" +
			        		          "Q(t): " + newQlist + "\n" +
			        		          "epsilon: " + agent.getActionSelection().getRoulette() +
			        		          "\n-----------------------------";

		        context.workspace().outputObject(
		          print , null, true, false, null); //Normal
		      }

		      //VERIFICA EPISODIO
		      Boolean isEndEpisode = (Boolean) agent.getEndEpisode().report(context, args);
		      if(isEndEpisode) {
		        agent.setEpisode();
		        if(agent.getActionSelection().getMethod().equals("e-greedy")) {
		        	new DecayEpsilonCommand().perform(args, context);	
		        }
		        agent.getResetEpisode().perform(context, args);
		      } 
		      
		}
		
	}
	
}
	
