package main.java.model;

import java.util.List;

import org.nlogo.api.AnonymousReporter;

public class StateDefinition {

	private List<String> vars = null;
	private AnonymousReporter reporterAux = null;
	
	public void setVars(List<String> vars) {
		this.vars = vars;
	}
	
	public void setReporterAux(AnonymousReporter reporterAux) {
		this.reporterAux = reporterAux;
	}
	
	public List<String> getVars() {
		return vars;
	}
	
	public AnonymousReporter getReporterAux() {
		return reporterAux;
	}
}
