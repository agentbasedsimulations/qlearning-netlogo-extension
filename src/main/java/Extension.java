package main.java;

import org.nlogo.api.*;

public class Extension extends DefaultClassManager {
	public void load(PrimitiveManager primitiveManager) {
		primitiveManager.addPrimitive("first-n-integers", new IntegerList());
	}
}
