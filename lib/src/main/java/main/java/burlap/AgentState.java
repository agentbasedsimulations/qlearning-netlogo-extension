package main.java.burlap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.nlogo.api.AgentException;
import org.nlogo.api.Context;

import burlap.mdp.core.state.MutableState;
import burlap.mdp.core.state.State;
import main.java.model.AgentLearning;
import main.java.model.Session;

/**
 * Agent State Class
 * @author Eloisa Bazzanella
 * @since  april, 2022
 */
public class AgentState implements MutableState {
    
    private Context context;
    private Map<String, Double> state;

    public AgentState(Context context) throws AgentException {
        this.context = context;
        this.state = new HashMap<>();
        
        AgentLearning agent =  Session.getInstance().getAgent(context.getAgent());
        
        this.state = agent.getState(context);
    }
    
    @Override
    public MutableState set(Object variableKey, Object value) {
        for(String s: state.keySet()) {
            if(variableKey.equals(s)) {
                state.replace(s, (Double) value);
            }
        }
        
        return this;
    }

    @Override
    public Object get(Object variableKey) {
        return state.get(variableKey);
    }

    @Override
    public State copy() {
        try {
            return new AgentState(context);
        } catch (AgentException ex) {
            Logger.getLogger(AgentState.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Object> variableKeys() {
        List<String> vars = new ArrayList<>();
        
        for (String s : state.keySet()) {
            vars.add(s);
        }
        
        return new ArrayList<Object>(vars);
    }

	@Override
	public String toString() {
	return "Estado:" + state ;
	}
    
   
}