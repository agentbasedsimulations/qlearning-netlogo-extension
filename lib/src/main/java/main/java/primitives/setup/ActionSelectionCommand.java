package primitives.setup;

import java.util.List;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import model.AgentLearning;
import model.Session;
import model.ActionSelection;

public class ActionSelectionCommand implements org.nlogo.api.Command {

    @Override
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.StringType(), Syntax.ListType()});
    }

    @Override
    public void perform(Argument[] args, Context context) throws ExtensionException {
        AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
        if(agent == null) {
            throw new ExtensionException(
                    "You should first define a state definition to the agent. Agent id: " + context.getAgent().id());
        }
        String method = args[0].getString().toLowerCase();
      
         Double decreaseRate = 0.00;
        if(method.equalsIgnoreCase("e-greedy")) {
           decreaseRate = (Double) args[1].getList().get(1);
        }
      
        ActionSelection actionSelection = agent.actionSelection;
        actionSelection.setMethod(method);
        actionSelection.setRoulette((Double) args[1].getList().get(0));
        actionSelection.setDecreaseRateNumber(decreaseRate);
    }
}