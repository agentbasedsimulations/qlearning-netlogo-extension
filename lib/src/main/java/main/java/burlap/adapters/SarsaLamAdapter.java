package main.java.burlap.adapters;

import java.util.List;

import burlap.behavior.policy.Policy;
import burlap.behavior.singleagent.learning.tdmethods.SarsaLam;
import burlap.behavior.valuefunction.QFunction;
import burlap.behavior.valuefunction.QValue;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.SADomain;
import burlap.statehashing.HashableState;
import burlap.statehashing.HashableStateFactory;
import main.java.burlap.util.BurlapUtils;

public class SarsaLamAdapter extends SarsaLam {

	public SarsaLamAdapter(SADomain domain, double gamma, HashableStateFactory hashingFactory, double qInit,
			double learningRate, double lambda) {
		super(domain, gamma, hashingFactory, qInit, learningRate, lambda);
	}

	public SarsaLamAdapter(SADomain domain, double gamma, HashableStateFactory hashingFactory, double qInit,
			double learningRate, int maxEpisodeSize, double lambda) {
		super(domain, gamma, hashingFactory, qInit, learningRate, maxEpisodeSize, lambda);
	}

	public SarsaLamAdapter(SADomain domain, double gamma, HashableStateFactory hashingFactory, double qInit,
			double learningRate, Policy learningPolicy, int maxEpisodeSize, double lambda) {
		super(domain, gamma, hashingFactory, qInit, learningRate, learningPolicy, maxEpisodeSize, lambda);
	}

	public SarsaLamAdapter(SADomain domain, double gamma, HashableStateFactory hashingFactory, QFunction qInit,
			double learningRate, Policy learningPolicy, int maxEpisodeSize, double lambda) {
		super(domain, gamma, hashingFactory, qInit, learningRate, learningPolicy, maxEpisodeSize, lambda);
	}

	public String getLearningDetails() {
		StringBuilder sb = new StringBuilder("Sarsa-Lambda Details").append(System.lineSeparator());
		sb.append("learning rate: ").append(BurlapUtils.toString(super.learningRate)).append(System.lineSeparator());
		sb.append("discount factor: ").append(super.gamma).append(System.lineSeparator());
		sb.append("lambda: ").append(super.lambda).append(System.lineSeparator());
		sb.append("Q table").append(System.lineSeparator());

		for (HashableState hs : super.qFunction.keySet()) {
			State s = hs.s();
			sb.append("state: ").append(s).append("; actions values: ");
			
			List<QValue> values = super.qFunction.get(hs).qEntry; // QValue is a tuple: state s, action a, qValue q
			for (QValue qv : values) {
				sb.append(qv.a).append("=").append(qv.q).append("; ");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
