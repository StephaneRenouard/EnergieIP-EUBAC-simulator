package com.energieip.eubac.simulator.applications;

import com.energieip.api.EnergieAPI;
import com.energieip.eubac.simulator.room.Simulated_room;

import fr.handco.lib.time.Time;

public class CeilingSystemHeatingCooling implements Runnable{
	
	
	// Ceiling def
	public int total_system_energy = 1500; // (W)
	
	// EIP HVAC driver def
	public int HVAC_SA = 0;
			
	// EnergieIP
	public EnergieAPI energieAPI;
	
	// Thread
	public Thread ceilingThread; // loop thread
	public int SLEEPING_TIME = 5000; // allow a break for thread (every 5 sec)

	
	/**
	 * Default constructor
	 * @param simulated_room 
	 */
	public CeilingSystemHeatingCooling(Simulated_room simulated_room, int HVAC_SA) {
		
		// use EnergieIP API
		energieAPI = new EnergieAPI();
		
		System.out.println("Watchdog=" + energieAPI.getWatchdog());
		
		//energieAPI.setData1(210);
		
		
		
	}

	
	/**
	 * Thread
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		
		while (!Thread.interrupted()) {

			try {	
				
				
				
				
				// sleep
				ceilingThread.sleep(SLEEPING_TIME);
				
			} catch (InterruptedException e) {

				// Nothing to do
				System.out.println(Time.timeStamp("ceiling thread interruped"));

			}

		} // end while
		
	} // end of run()
	
	

} // end of class
