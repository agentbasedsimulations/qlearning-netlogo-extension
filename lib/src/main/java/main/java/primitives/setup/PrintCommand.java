package main.java.primitives.setup;

import org.nlogo.api.AgentException;
import org.nlogo.api.AnonymousCommand;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.AgentLearning;
import main.java.model.Session;


public class PrintCommand implements org.nlogo.api.Command {

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax();
	}

	@Override
	public void perform(Argument[] args, Context context) throws ExtensionException {
		AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
		
		System.out.println("Learning Rate: " + agent.getLearningRate());
		System.out.println("Discount Factor: " + agent.getDiscountFactor());
		System.out.println("Episode: " + agent.getEpisode());
		
		try {
			System.out.println("State: " + agent.getState(context));
		} catch (AgentException e) {
			e.printStackTrace();
		}
		
		for(AnonymousCommand a : agent.getActions()) {
			System.out.println("Action: " + a.toString());
		}
		
		System.out.println("Reward: " + agent.getReward().toString());
		
		
	}

}
