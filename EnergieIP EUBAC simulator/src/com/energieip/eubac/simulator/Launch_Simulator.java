package com.energieip.eubac.simulator;

import com.energieip.eubac.simulator.applications.CeilingSystemHeatingCooling;
import com.energieip.eubac.simulator.room.Simulated_room;

/**
 * this class is the main entry point for launching simulator
 * @author stef
 *
 */
public class Launch_Simulator {

	Simulated_room simulated_room;
	
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
		
		int HVAC_SA = 24; // HVAC regulator short address
		CeilingSystemHeatingCooling ceilingSystemHeatingCooling = new CeilingSystemHeatingCooling(HVAC_SA);	
		
		
		//simulated_room = new Simulated_room(ceilingSystemHeatingCooling);
		
		// Debug purpose
		simulated_room = new Simulated_room();
		
		
		
	}

}
