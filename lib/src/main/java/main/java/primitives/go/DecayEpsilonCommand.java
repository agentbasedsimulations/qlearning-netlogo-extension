package main.java.primitives.go;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.AgentLearning;
import main.java.model.Session;

public class DecayEpsilonCommand implements org.nlogo.api.Command {

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
	    	
	    	//DOCUMENTAR ISSO
	    	if(agent.getActionSelection().getTypeOf().equals("rate")) {
	    		Double roulette = agent.getActionSelection().getRoulette() * agent.getActionSelection().getDecreaseRateNumber();
	    		agent.getActionSelection().setRoulette(roulette);
	        
	    	} 
	    	else if (agent.getActionSelection().getTypeOf().equals("value")) {
	        	if(agent.getActionSelection().getDecreaseIsNumber()) {
	              agent.getActionSelection().setRoulette(agent.getActionSelection().getDecreaseRateNumber());
	            } 
	        	else {
	        		Double roulette;
	        		try {
	        			  roulette =  (Double) agent.getActionSelection().getDecreaseRateReporter().report(context, args);
	        		} catch (NullPointerException e){
	        			throw new ExtensionException("No Epsilon Decay function for agent " + context.getAgent().id() + " was defined");
	                }

	              agent.getActionSelection().setRoulette(roulette);
	            }
	          } else {
	        	  Double roulette = agent.getActionSelection().getRoulette() * agent.getActionSelection().getDecreaseRateNumber();
		          agent.getActionSelection().setRoulette(roulette);
	          }
	    
	    
	    }
	}

}
