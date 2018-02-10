package com.energieip.eubac.simulator.applications;

import com.energieip.api.EnergieAPI;
import com.energieip.eubac.simulator.room.Simulated_room;

public class CeilingSystemHeatingCooling {
	
	
	// Ceiling def
	public int total_system_energy = 1500; // (W)
	
	
	public EnergieAPI energieAPI;
	
	/**
	 * Default constructor
	 * @param simulated_room 
	 */
	public CeilingSystemHeatingCooling(Simulated_room simulated_room) {
		
		// use EnergieIP API
		energieAPI = new EnergieAPI();
		
		System.out.println("Watchdog=" + energieAPI.getWatchdog());
		
		energieAPI.setData1(210);
		
	}
	
	

}
