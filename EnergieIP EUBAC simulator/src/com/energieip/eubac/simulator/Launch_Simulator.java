package com.energieip.eubac.simulator;

import com.energieip.eubac.simulator.applications.CeilingSystemHeatingCooling;

/**
 * this class is the main entry point for launching simulator
 * @author stef
 *
 */
public class Launch_Simulator {

	/**
	 * default main entry point
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Launch_Simulator();

	}
	
	
	/**
	 * Default constructor
	 */
	public Launch_Simulator() {
		
		new CeilingSystemHeatingCooling();		
		
	}

}
