/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.primitives.setup;

import org.nlogo.api.Argument;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.AgentLearning;
import main.java.model.Session;

/**
 *
 * @author eloba
 */
public class DefineAlgorithmCommand implements org.nlogo.api.Command {

    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[] {Syntax.StringType()});
    }

    @Override
    public void perform(Argument[] args, org.nlogo.api.Context context) throws ExtensionException {
        AgentLearning agent = Session.getInstance().getAgent(context.getAgent());

        if(agent == null) {
          throw new ExtensionException(
            "You should first define a state definition to this agent. Agent id: " + context.getAgent().id());
        }

        agent.setAlgorithm(args[0].getString());
    }
}
