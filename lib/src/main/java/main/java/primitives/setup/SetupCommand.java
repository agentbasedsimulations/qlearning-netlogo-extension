package main.java.primitives.setup;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.nlogo.api.AgentException;
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

public class SetupCommand implements org.nlogo.api.Command {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[] {});
    }
    
    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        AgentLearning agent = Session.getInstance().getAgent(context.getAgent());

        if(agent.algorithm.equals("qlearning")) {
            QLearningAlgorithm.setInstanceNull();
            QLearningAlgorithm learning = QLearningAlgorithm.getInstance(args, context);
            try {       
                learning.setup();
            } catch (AgentException ex) {
                Logger.getLogger(SetupCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (agent.algorithm.equals("sarsa-lambda")) {
            SarsaAlgorithm.setInstanceNull();
            SarsaAlgorithm learning = SarsaAlgorithm.getInstance(args, context);
            try {       
                learning.setup();
            } catch (AgentException ex) {
                Logger.getLogger(SetupCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (agent.algorithm.equals("actor-critic")) {
            ActorCriticAlgorithm.setInstanceNull();
            ActorCriticAlgorithm learning = ActorCriticAlgorithm.getInstance(args, context);
            try {       
                learning.setup();
            } catch (AgentException ex) {
                Logger.getLogger(SetupCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
        
    }    
}
