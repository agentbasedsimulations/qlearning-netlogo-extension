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

public class GetQTableCommand implements org.nlogo.api.Reporter {

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.reporterSyntax(Syntax.StringType());
	}

	@Override
	public Object report(Argument[] args, Context context) throws ExtensionException {
		AgentLearning agent = Session.getInstance().getAgent(context.getAgent());
		
		StringBuilder sb = new StringBuilder("Agent: ").append(agent.agent.id()).append("\n");
		
		if(agent.algorithm.equals("qlearning")) {
            QLearningAlgorithm learning = QLearningAlgorithm.getInstance(args, context);
            sb.append(learning.getLearningDetails());
        } else if(agent.algorithm.equals("sarsa-lambda")) {
            SarsaAlgorithm learning = SarsaAlgorithm.getInstance(args, context);
            sb.append(learning.getLearningDetails());
        } else if(agent.algorithm.equals("actor-critic")) {
        	ActorCriticAlgorithm learning = ActorCriticAlgorithm.getInstance(args, context);
            sb.append(learning.getLearningDetails());
        }

        return sb.toString();
	 }
}