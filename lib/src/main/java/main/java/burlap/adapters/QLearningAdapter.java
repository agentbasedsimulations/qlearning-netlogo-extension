package main.java.burlap.adapters;

import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.behavior.valuefunction.QValue;
import burlap.mdp.singleagent.SADomain;
import burlap.statehashing.HashableStateFactory;
import main.java.burlap.AgentState;
import main.java.model.AgentLearning;

import java.util.List;

import org.nlogo.api.AgentException;
import org.nlogo.api.AnonymousCommand;
import org.nlogo.api.Context;

public class QLearningAdapter extends QLearning {
private AgentLearning al = new AgentLearning();
private Context context;
private QLearning  learning;


	public QLearningAdapter(SADomain domain, double gamma, HashableStateFactory hashingFactory, double qInit,
			double learningRate) {
		super(domain, gamma, hashingFactory, qInit, learningRate);
		// TODO Auto-generated constructor stub
	}
	public String getQtable(List<AnonymousCommand> actions ) throws AgentException{
		//usar o objeto q function(hash map)
		for(int i = 0 ; i < qFunction.size(); i++) {
			 //for (QValue qvalue : learning.qValues(as.s))
			//System.out.println(actions.toString());
			System.out.println(qFunction.get(actions));
		}
		
		return super.toString();	
	}

}
