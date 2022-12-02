package burlap;

import burlap.behavior.singleagent.learning.actorcritic.ActorCritic;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.behavior.singleagent.learning.tdmethods.SarsaLam;
import burlap.behavior.valuefunction.QValue;
import burlap.mdp.core.StateTransitionProb;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.model.statemodel.FullStateModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AgentLearning;
import model.Session;
import org.nlogo.api.AgentException;
import org.nlogo.api.AnonymousCommand;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;

/**
 * Agent State Model Class
 * @author Eloisa Bazzanella
 * @since  april, 2022
 */
public class AgentStateModel implements FullStateModel {

    private Argument[] args;
    private Context    context;
    private QLearning  learning;
    private SarsaLam   sarsa;
    private CriticImplementation critic;
    
    public AgentStateModel(Argument[] args, Context context) {
        this.args = args;
        this.context = context;
    }
    
    @Override
    public List<StateTransitionProb> stateTransitions(State s, Action a) {
        return new ArrayList<StateTransitionProb>();
    }

    @Override
    public State sample(State s, Action a) {        
        AgentState state = null;
        try {
            state = new AgentState(context);
        } catch (AgentException ex) {
            Logger.getLogger(AgentStateModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
        String actionExecute = a.actionName();
        for(AnonymousCommand action : agent.actions) {
            if(actionExecute.equals(action.toString())){
                action.perform(context, args);
            }
        }
        
        try {
            state = new AgentState(context);
        } catch (AgentException ex) {
            Logger.getLogger(AgentStateModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return state;
    }
    
    public void setQLearning(QLearning learning) {
        this.learning = learning;
    }
    
    public void setSarsa(SarsaLam sarsa) {
        this.sarsa = sarsa;
    }

    public void setCritic(CriticImplementation critic) {
        this.critic = critic;
    }
}