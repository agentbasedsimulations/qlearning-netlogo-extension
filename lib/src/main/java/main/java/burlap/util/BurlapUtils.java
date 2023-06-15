package main.java.burlap.util;

import burlap.behavior.learningrate.ConstantLR;
import burlap.behavior.learningrate.LearningRate;

public class BurlapUtils {

	public static String toString(LearningRate lr) {
		if (lr instanceof ConstantLR) {
			return String.valueOf(((ConstantLR)lr).learningRate);
		}
		return "unknown learning rate type";
	}
}
