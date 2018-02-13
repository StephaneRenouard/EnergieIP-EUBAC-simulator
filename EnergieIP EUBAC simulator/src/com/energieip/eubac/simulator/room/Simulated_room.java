package com.energieip.eubac.simulator.room;

import com.energieip.eubac.mysql.MysqlConnector;
import com.energieip.eubac.simulator.applications.CeilingSystemHeatingCooling;
import com.mysql.jdbc.Util;
import com.sun.javafx.scene.layout.region.SliceSequenceConverter;

import fr.handco.lib.time.Time;

public class Simulated_room implements Runnable{

	// Debug
	public boolean DEBUG = true;
	
	/*
	 * Initial params
	 */
	// temp
	public double temp_room_inside_initial = 22.0; // °C
	public double temp_room_outside_initial = 30.0; // °C
	
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
	public double coef_U = 1; // (W/m²K) coefficient de transmission thermique en W par m² exposé et par ° de difference (0.33)
	public double joule_factor = 1900;  // 1°C HU = 1900 joules, 1W = 1J/s
	
	// energy and power
	public double room_energy = -1; // initial value 
	
	// application
	boolean heating= false;
	
	// valve
	public int valve_0 = 200;  // 200 (20%) is 0
	
	// external energy
	public double human_body_energy = 60; // (W) 60W par occupant humain en apport de chaleur
	public double human_number = 0; // nombre d'humain dans la piece
	public double room_external_energy = 0; // (W) energie externe apportée dans la piece, i.e. ordinateur 
	
	/*
	 * simulation params
	 */
	// temp
	public double temp_room_inside = temp_room_inside_initial; // °C
	public double temp_room_outside = temp_room_outside_initial; // °C
		
	// time
	public int time_factor = 1;
	
	// application
	public boolean application=false;
	public CeilingSystemHeatingCooling ceilingSystemHeatingCooling;
	public int application_power; // (W) 
	
	// valve position
	public int valve_position;
	
	// Thread
	public Thread roomThread; // loop thread
	public int SLEEPING_TIME = 10000; // allow a break for thread (every 10 sec)

	
	/**
	 * Default constructor
	 * @param ceilingSystemHeatingCooling 
	 */
	public Simulated_room(CeilingSystemHeatingCooling _ceilingSystemHeatingCooling) {
		
		// welcome aboard
		System.out.println(Time.timeStamp("[Simulated room] -----------------------------------------------------------------"));
		System.out.println(Time.timeStamp("[Simulated room] STARTING SIMULATION" ));
		System.out.println(Time.timeStamp("[Simulated room] temp_room_inside_initial=" + temp_room_inside_initial + " °C"));
		System.out.println(Time.timeStamp("[Simulated room] temp_room_outside_initial=" + temp_room_outside_initial + " °C"));
		System.out.println(Time.timeStamp("[Simulated room] coef U=" + coef_U+ " W/m²K"));
		System.out.println(Time.timeStamp("[Simulated room] time factor=" + time_factor));
		System.out.println(Time.timeStamp("[Simulated room] -----------------------------------------------------------------"));
		
		
		// add an external heating/cooling application
		application = true;
		ceilingSystemHeatingCooling = _ceilingSystemHeatingCooling; // set as local
		application_power = ceilingSystemHeatingCooling.getSystemPower();
		
		
		// launch thread
		roomThread = new Thread(this);
		roomThread.start();
		
	}
	
	/**
	 * Debug constructor
	 * 
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
				
				// compute t0 room energy
				room_energy = Utilities.compute_Energy(temp_room_inside, joule_factor);
				if(DEBUG){
					System.out.println(Time.timeStamp("----------------------------------------------------------------------"));
					System.out.println(Time.timeStamp("[Simulated room] initial energy=" + room_energy + " J"));
				}
								
				// add human factors (in number of humans)
				double human_energy = Utilities.compute_humans_energy(human_body_energy, human_number,SLEEPING_TIME);
				room_energy+=human_energy;
				if(DEBUG){
					System.out.println(Time.timeStamp("[Simulated room] human body energy=" + human_energy + " J"));
				}
				
				// add external factors (in W)
				double ext_energy = Utilities.compute_external_factors_energy(room_external_energy, SLEEPING_TIME); 
				room_energy+=ext_energy;
				if(DEBUG){
					System.out.println(Time.timeStamp("[Simulated room] ext energy=" + ext_energy + " J"));
				}
				
				// add heating/cooling factors
				if(application){
					// valve position is in 1/10 (so have to be divided by 10)
					valve_position = ceilingSystemHeatingCooling.getValvePosition();
					double application_energy = Utilities.compute_application_energy(valve_position-valve_0, application_power, SLEEPING_TIME);
					
					if(heating){
						room_energy+=application_energy;
					}else {// cooling
						room_energy-=application_energy;
					}	
						
					if(DEBUG){
						System.out.println(Time.timeStamp("[Simulated room] application energy=" + application_energy + " J"));
					}
				}
				
				// then compute thermal transfers
			    if(temp_room_inside>temp_room_outside){
			    	// remove energy for outside transfer
			    	room_energy = Utilities.compute_Inside_Outside_Energy_Transfert(room_energy, temp_room_outside, coef_U, room_surface_ext, SLEEPING_TIME, joule_factor );	    	
			    	if(DEBUG){
						System.out.println(Time.timeStamp("[Simulated room] computing thermal transfer (outside temp=" + temp_room_outside + "°C" + ")"));
					}
			    	
			    }else if(temp_room_inside>temp_room_outside){
			    	//TODO
			    }else{ // equilibrium
			    	// do nothing
			    }
				
			    temp_room_inside = Utilities.compute_temp_from_energy(room_energy, joule_factor);
			    				
				System.out.println(Time.timeStamp("[Simulated room] TEMPERATURE INTERIEUR=" + temp_room_inside));
				
				// return room temp
				if(application){
					// send temperature in 1/10 °C (EIP FORMAT)
					ceilingSystemHeatingCooling.setRoomTemperature(Utilities.compute_temp_in_EiP_format(temp_room_inside));
				}
				
				// store temp in MySQL DB
				MysqlConnector.Insert_data_in_MySQL(Time.timeStamp("").trim(), Integer.toString(Utilities.compute_temp_in_EiP_format(temp_room_inside)), Integer.toString(valve_position));
				
				
				// sleep
				roomThread.sleep(SLEEPING_TIME);
				
			} catch (InterruptedException e) {

				// Nothing to do
				System.out.println(Time.timeStamp("regulation thread interruped"));

			}

		} // end while
	
	} // end of run() 
		
	

} // end of class
