package com.energieip.eubac.simulator.room;

import fr.handco.lib.time.Time;

public class Simulated_room implements Runnable{

	// Debug
	public boolean DEBUG = true;
	
	// Initial params
	public int temp_room_inside_initial = 15;
	public int temp_room_outside_initial = 5;
	
	public double room_size = 15; // superficie (m²)
	public double room_height = 2.5; // hauteur du plafond (m)$
	public double room_volume = room_size*room_height; // (m3)
	
	
	
	// Thread
	public Thread roomThread; // loop thread
	public int SLEEPING_TIME = 5000; // allow a break for thread (every 5 sec)

	
	/**
	 * Default constructor
	 */
	public Simulated_room() {
		
		// launch thread
		roomThread = new Thread(this);
		roomThread.start();
		
	}
	
	
	
	
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (!Thread.interrupted()) {

			try {	
				
				
				
				
				// sleep
				roomThread.sleep(SLEEPING_TIME);
				
			} catch (InterruptedException e) {

				// Nothing to do
				System.out.println(Time.timeStamp("regulation thread interruped"));

			}

		} // end while
	}
		
	

} // end of class
