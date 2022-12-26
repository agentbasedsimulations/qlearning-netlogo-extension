package main.java.primitives.setup;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.ActionSelection;
import main.java.model.AgentLearning;
import main.java.model.Session;

public class ActionSelectionEGreedyCommand implements org.nlogo.api.Command {

    @Override
    public Syntax getSyntax() {
            return SyntaxJ.commandSyntax(new int[] {Syntax.NumberType(), Syntax.StringType(), Syntax.NumberType() | Syntax.ReporterType()});
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
        if(agent == null) {
            throw new ExtensionException(
                    "You should first define a state definition to the agent. Agent id: " + context.getAgent().id());
        }

        ActionSelection actionSelection = agent.actionSelection;

        try {
            actionSelection.setDecreaseRateReporter(args[2].getReporter());
            actionSelection.decreaseIsNumber = false;
        } catch (ExtensionException e) { 
            actionSelection.setDecreaseRateNumber(args[2].getDoubleValue()); 
            actionSelection.decreaseIsNumber = true;
        }

        actionSelection.setMethod("e-greedy");
        actionSelection.setRoulette(args[0].getDoubleValue());
        actionSelection.setTypeOf(args[1].getString().toLowerCase());
     }
}