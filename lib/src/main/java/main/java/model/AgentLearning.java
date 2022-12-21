package main.java.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nlogo.agent.Turtle;
import org.nlogo.agent.World;
import org.nlogo.api.AgentException;
import org.nlogo.api.AnonymousCommand;
import org.nlogo.api.AnonymousReporter;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;

public class AgentLearning {

	
	LinkedHashMap<String, LinkedHashMap<String, Double>> qTable = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
	
	StateDefinition stateDef = null;
	String previousState = null;
	
	List<AnonymousCommand> actions = new ArrayList<>();
	AnonymousReporter rewardFunc = null;
	AnonymousReporter endEpisode = null;
	AnonymousCommand resetEpisode = null;
	
	ActionSelection actionSelection = new ActionSelection();
	
	private Double learningRate = -1.00;
	private Double discountFactor = -1.00;
	
    int episode = 0; 
    int actionActualState = 0;
    
    org.nlogo.api.Agent agent = null;
    
    
    public Double getBestActionExpectedReward(String state) {
    	LinkedHashMap<String, Double> qList = qTable.get(state);
    	
    	//Estado não visitado anteriormente
    	if(qList == null) { 
    	    return 0.00;
    	} else {
    		Map.Entry<String, Double> maxEntry = null;
    		for (Map.Entry<String, Double> entry : qList.entrySet()) {
    	        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
    	            maxEntry = entry;
    	        }
    	    }
    		
    		return maxEntry.getValue();
    	}
    }
    
    public String getState(Context context) throws AgentException {
	    String state = "";
	    
	    for(String v : stateDef.getVars()) {	    	
	    	Turtle turtle = ((World) agent.world()).getTurtle(agent.id());
	    	state += turtle.getVariable(v);
	    }
	    
	    if(stateDef.getReporterAux() == null) {
	    	return state;
	    } else {
	    	Object[] args = null;
		    String reporterAuxResult = stateDef.getReporterAux().report(context, args).toString();
		    return state + reporterAuxResult;
	    }
    }
    
    public void setDiscountFactor(Double f) throws ExtensionException {
	    if(f > 1 || f < 0) {
	      throw new ExtensionException("Discount factor must be a value between 0 and 1");
	    }
	    discountFactor = f;
	}
    
    public void setLearningRate(Double r) throws ExtensionException {
	    if(r > 1 || r < 0) {
	      throw new ExtensionException("Learning rate must be a value between 0 and 1");
	    }
	    learningRate = r;
	}
    
    public void setActionActualState(int actionActualState) {
    	this.actionActualState = actionActualState;
    }
    
    public void setEndEpisode (AnonymousReporter endEpisode) {
    	this.endEpisode = endEpisode;
    }
    
    public void setResetEpisode (AnonymousCommand resetEpisode) {
    	this.resetEpisode = resetEpisode;
    }
    
    public void setRewardFunction(AnonymousReporter reward) {
    	this.rewardFunc = reward;
    }
    
    public void addAction(AnonymousCommand a) {
    	actions.add(a);
    }
    
    public void setStateDefinition (StateDefinition stateDef) {
    	this.stateDef = stateDef;
    }
    
    public void setAgent(org.nlogo.api.Agent agent) {
    	this.agent = agent;
    }
    
    public void setEpisode() {
    	this.episode += 1;
    }
    
    public void setPreviousState(String previousState) {
    	this.previousState = previousState;
    }
    
    public StateDefinition getStateDef() {
    	return this.stateDef;
    }
    
    public String getPreviousState() {
    	return this.previousState;
    }
    
    public List<AnonymousCommand> getActions() {
    	return this.actions;
    }
    
    public AnonymousReporter getReward() {
    	return this.rewardFunc;
    }
    
    public AnonymousReporter getEndEpisode() {
    	return this.endEpisode;
    }
    
    public AnonymousCommand getResetEpisode() {
    	return this.resetEpisode;
    }
    
    public ActionSelection getActionSelection() {
    	return this.actionSelection;
    }
    
    public Double getLearningRate() {
    	return this.learningRate;
    }
    
    public Double getDiscountFactor() {
    	return this.discountFactor;
    }
    
    public org.nlogo.api.Agent getAgent() {
    	return this.agent;
    }
    
    public int getEpisode() {
    	return this.episode;
    }
    
    public int getActionActualState() {
    	return this.actionActualState;
    }
    
    public LinkedHashMap<String, LinkedHashMap<String, Double>> getQTable() {
    	return this.qTable;
    }
}
