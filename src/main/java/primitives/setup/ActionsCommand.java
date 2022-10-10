package main.java.primitives.setup;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.AgentLearning;
import main.java.model.Session;

public class ActionsCommand implements org.nlogo.api.Command {

	@Override
	public Syntax getSyntax() {
		//List(CommandType | RepeatableType)
		return SyntaxJ.commandSyntax(
				new int[] {Syntax.CommandType() | Syntax.RepeatableType()});
	}

	@Override
	public void perform(Argument[] args, Context context) throws ExtensionException {
		AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
		
		if(agent == null) {
			throw new ExtensionException(
			        "You should first define an state definition to the agent. Agent id: " + context.getAgent().id());
		}
		
		for(Argument action : args) {
			agent.addAction(action.getCommand());
		}
	}

}
