package main.java.primitives.setup;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import main.java.model.Session;

public class RandomSeedCommand implements org.nlogo.api.Command {

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.commandSyntax(
				new int[] {Syntax.NumberType()});
	}

	@Override
	public void perform(Argument[] args, Context context) throws ExtensionException {
		Session.getInstance().getAgent(context.getAgent()).getActionSelection().getRandomGen().setSeed(args[0].getIntValue());
	}

}
