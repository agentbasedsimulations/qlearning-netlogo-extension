package main.java.burlap;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;

import burlap.mdp.core.TerminalFunction;
import burlap.mdp.core.state.State;
import main.java.model.AgentLearning;
import main.java.model.Session;

/**
 * Is End Episode(?) Class
 * @author Eloisa Bazzanella
 * @since  april, 2022
 */
public class IsEndEpisode implements TerminalFunction {
    
    private Argument[] args;
    private Context context;

    public IsEndEpisode(Argument[] args, Context context) {
        this.args = args;
        this.context = context;
    }

    public boolean isTerminal(State s) {
        AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
        Boolean isEndEpisode = (Boolean) agent.endEpisode.report(context, args);

        return isEndEpisode;
    }
}