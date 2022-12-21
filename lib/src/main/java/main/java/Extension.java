package main.java;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

import main.java.primitives.go.ActCommand;
import main.java.primitives.go.DecayEpsilonCommand;
import main.java.primitives.go.GetEpisodeCommand;
import main.java.primitives.go.GetQTableCommand;
import main.java.primitives.go.LearnCommand;
import main.java.primitives.go.LearningCommand;
import main.java.primitives.setup.ActionSelectionCommand;
import main.java.primitives.setup.ActionSelectionEGreedyCommand;
import main.java.primitives.setup.ActionSelectionRandomCommand;
import main.java.primitives.setup.ActionsCommand;
import main.java.primitives.setup.DiscountFactorCommand;
import main.java.primitives.setup.EndEpisodeCommand;
import main.java.primitives.setup.LearningRateCommand;
import main.java.primitives.setup.PrintCommand;
import main.java.primitives.setup.RewardCommand;
import main.java.primitives.setup.StateDefinitionCommand;
import main.java.primitives.setup.StateDefinitionExtraCommand;

public class Extension extends DefaultClassManager {
	public void load(PrimitiveManager primitiveManager) {
		primitiveManager.addPrimitive("learning-rate", new LearningRateCommand());
		primitiveManager.addPrimitive("discount-factor", new DiscountFactorCommand());
		primitiveManager.addPrimitive("action-selection", new ActionSelectionCommand());
		primitiveManager.addPrimitive("action-selection-egreedy", new ActionSelectionEGreedyCommand());
		primitiveManager.addPrimitive("action-selection-random", new ActionSelectionRandomCommand());
		primitiveManager.addPrimitive("reward", new RewardCommand());
		primitiveManager.addPrimitive("end-episode", new EndEpisodeCommand());
		primitiveManager.addPrimitive("actions", new ActionsCommand());
//		primitiveManager.addPrimitive("random-seed", new RandomSeedCommand());
		primitiveManager.addPrimitive("state-def", new StateDefinitionCommand());
		primitiveManager.addPrimitive("state-def-extra", new StateDefinitionExtraCommand());
		primitiveManager.addPrimitive("print", new PrintCommand());
		
		primitiveManager.addPrimitive("act", new ActCommand());
		primitiveManager.addPrimitive("learn", new LearnCommand());
		primitiveManager.addPrimitive("learning", new LearningCommand());
		primitiveManager.addPrimitive("decay-epsilon", new DecayEpsilonCommand());
		primitiveManager.addPrimitive("get-episode", new GetEpisodeCommand());
		primitiveManager.addPrimitive("get-qtable", new GetQTableCommand());
		
	}
}
