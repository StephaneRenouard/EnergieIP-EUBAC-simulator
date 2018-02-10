package com.energieip.eubac.simulator.room;

import fr.handco.lib.time.Time;

public class Simulated_room implements Runnable{

	// Debug
	public boolean DEBUG = true;
	
	
	// Initial params
	public double temp_room_inside_initial = 15.0; // �C
	public double temp_room_outside_initial = 5.0; // �C
	
	public double room_L = 5; // Longueur (m)
	public double room_l = 3; // largeur (m)
	public double room_L_ext = room_L; // Longueur expos�e sur l'exterieur (m)
	public double room_l_ext = room_l; // largeur expos�e sur l'exterieur (m)
	public double room_surface = room_L * room_l; // superficie (m�)
	public double room_height = 2.5; // hauteur du plafond (m)
	public double room_volume = room_surface*room_height; // (m3)
	public double room_surface_ext = (room_L_ext*room_height) + (room_l_ext*room_height); // (m�) superficie expos�e � l'exterieur
	
	public double coef_U = 0.33; // (W/m�K) coefficient de transmission thermique en W par m� expos� et par � de difference
	public double room_energy = 0;
	
	// external energy
	public double human_body_energy = 60; // (W) 60W par occupant humain en apport de chaleur
	public double human_number = 0; // nombre d'humain dans la piece
	public double room_external_energy = 0; // (W) energie externe apport�e dans la piece, i.e. ordinateur 
	
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
	
	
	
	/**
	 * Thread
	 */
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
	
	} // end of run() 
		
	

} // end of class
