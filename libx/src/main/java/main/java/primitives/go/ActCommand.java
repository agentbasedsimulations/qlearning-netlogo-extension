package main.java.primitives.go;

import java.util.LinkedHashMap;

import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.AgentLearning;
import main.java.model.Session;

public class ActCommand implements 	org.nlogo.api.Command {

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax();
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
				agent.setPreviousState(actualState);
		     } catch (AgentException e) {
				e.printStackTrace();
		     }
			
			LinkedHashMap<String,Double> actualQlist = new LinkedHashMap<String,Double>();
		    LinkedHashMap<String,Double> qlist = agent.getQTable().get(actualState);

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
		      agent.setActionActualState(actionActualState);
		      
		      // EXECUTA AÇÃO
		      agent.getActions().get(actionActualState).perform(context, args);
		}
	}

}
