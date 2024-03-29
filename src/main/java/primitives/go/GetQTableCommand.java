package main.java.primitives.go;

import java.util.LinkedHashMap;
import java.util.Map;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

import main.java.model.Session;

public class GetQTableCommand implements org.nlogo.api.Reporter {

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.reporterSyntax(Syntax.StringType());
	}

	@Override
	public Object report(Argument[] args, Context context) throws ExtensionException {
		LinkedHashMap<String, LinkedHashMap<String, Double>> qTable = Session.getInstance().getAgent(context.getAgent()).getQTable();
		
		    String ret = "--------- Q-Table --------- \n";
		    
		    for (Map.Entry<String, LinkedHashMap<String, Double>> entry : qTable.entrySet()) {
		    	LinkedHashMap<String, Double> qList = entry.getValue();
		    	ret += entry.getKey() + " -> ";
		    	
		    	for (Map.Entry<String, Double> entryList : qList.entrySet()) {
		    		ret += entryList.getKey().toString() + ": " + entryList.getValue() + " | "; 
		    	}
		    	ret += "\n";
		    }
		    
		    ret += "---------------------------------\n";
		    
		    return ret;
	 }

}
