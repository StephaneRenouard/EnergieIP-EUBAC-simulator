package com.energieip.eubac.simulator.room;

import fr.handco.lib.time.Time;

public class Simulated_room implements Runnable{

	// Debug
	public boolean DEBUG = true;
	
	/*
	 * Initial params
	 */
	// temp
	public double temp_room_inside_initial = 15.0; // °C
	public double temp_room_outside_initial = 5.0; // °C
	
	// room
	public double room_L = 5; // Longueur (m)
	public double room_l = 3; // largeur (m)
	public double room_L_ext = room_L; // Longueur exposée sur l'exterieur (m)
	public double room_l_ext = room_l; // largeur exposée sur l'exterieur (m)
	public double room_surface = room_L * room_l; // superficie (m²)
	public double room_height = 2.5; // hauteur du plafond (m)
	public double room_volume = room_surface*room_height; // (m3)
	public double room_surface_ext = (room_L_ext*room_height) + (room_l_ext*room_height); // (m²) superficie exposée à l'exterieur
	
	// coef
	public double coef_U = 0.33; // (W/m²K) coefficient de transmission thermique en W par m² exposé et par ° de difference
	public double room_energy = 0;
	
	// external energy
	public double human_body_energy = 60; // (W) 60W par occupant humain en apport de chaleur
	public double human_number = 0; // nombre d'humain dans la piece
	public double room_external_energy = 0; // (W) energie externe apportée dans la piece, i.e. ordinateur 
	
	
	/*
	 * simulation param
	 */
	// temp
	public double temp_room_inside = temp_room_inside_initial; // °C
	public double temp_room_outside = temp_room_outside_initial; // °C
	
	//time
	public int time_factor = 1;
	
	// Thread
	public Thread roomThread; // loop thread
	public int SLEEPING_TIME = 10000; // allow a break for thread (every 10 sec)

	
	/**
	 * Default constructor
	 */
	public Simulated_room() {
		
		System.out.println(Time.timeStamp("[Simulated room] -----------------------------------------------------------------"));
		System.out.println(Time.timeStamp("[Simulated room] STARTING SIMULATION" ));
		System.out.println(Time.timeStamp("[Simulated room] temp_room_inside_initial=" + temp_room_inside_initial + " °C"));
		System.out.println(Time.timeStamp("[Simulated room] temp_room_outside_initial=" + temp_room_outside_initial + " °C"));
		System.out.println(Time.timeStamp("[Simulated room] coef U=" + coef_U+ " W/m²K"));
		System.out.println(Time.timeStamp("[Simulated room] time factor=" + time_factor));
		System.out.println(Time.timeStamp("[Simulated room] -----------------------------------------------------------------"));
		
		// launch thread
		roomThread = new Thread(this);
		roomThread.start();
		
	}
	
	
	
	/**
	 * Thread
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (!Thread.interrupted()) {

			try {	
							
				System.out.println("[Simulated room] temp_inside=" + temp_room_inside);
				
				// sleep
				roomThread.sleep(SLEEPING_TIME);
				
			} catch (InterruptedException e) {

				// Nothing to do
				System.out.println(Time.timeStamp("regulation thread interruped"));

			}

		} // end while
	
	} // end of run() 
		
	

} // end of class
