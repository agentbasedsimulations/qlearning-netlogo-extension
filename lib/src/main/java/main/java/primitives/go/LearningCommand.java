package main.java.primitives.go;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.burlap.ActorCriticAlgorithm;
import main.java.burlap.QLearningAlgorithm;
import main.java.burlap.SarsaAlgorithm;
import main.java.model.AgentLearning;
import main.java.model.Session;

public class LearningCommand implements org.nlogo.api.Command {

    @Override
    public Syntax getSyntax() {
    	return SyntaxJ.commandSyntax();
    	// return SyntaxJ.commandSyntax(new int[] {Syntax.BooleanType()}); TODO allow the 'debug' argument 
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        AgentLearning agent = Session.getInstance().getAgent(context.getAgent());

        if(agent.algorithm.equals("qlearning")) {
            QLearningAlgorithm learning = QLearningAlgorithm.getInstance(args, context);
            learning.go(args, context);
        } else if(agent.algorithm.equals("sarsa-lambda")) {
            SarsaAlgorithm learning = SarsaAlgorithm.getInstance(args, context);
            learning.go(args, context);
        } else if(agent.algorithm.equals("actor-critic")) {
            ActorCriticAlgorithm learning = ActorCriticAlgorithm.getInstance(args, context);
            learning.go(args, context);
        }
        
//        TODO allow the 'debug' argument
//        if(args.length > 0 && args[0].getBooleanValue()) {
//        	throw new ExtensionException("Debug=true is not supported yet"); // TODO
//        }
    }
}