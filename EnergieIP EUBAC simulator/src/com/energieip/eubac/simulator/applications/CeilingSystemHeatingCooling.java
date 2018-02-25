package com.energieip.eubac.simulator.applications;

import com.energieip.api.EnergieAPI;
import com.energieip.eubac.simulator.valves.Valve_6;

import fr.handco.lib.time.Time;

public class CeilingSystemHeatingCooling implements Runnable{
	
	
	// Ceiling def
	public int total_system_power = 600; // (W)
	
	// EIP HVAC driver def
	public int HVAC_SA = 0;
			
	// EnergieIP
	public EnergieAPI energieAPI;
	
	// Thread
	public Thread ceilingThread; // loop thread
	public int SLEEPING_TIME = 5000; // allow a break for thread (every 5 sec)

	
	/**
	 * Default constructor
	 */
	public CeilingSystemHeatingCooling(int _HVAC_SA) {
		
		// get SA as local
		HVAC_SA = _HVAC_SA;
		
		// use EnergieIP API
		energieAPI = new EnergieAPI();
		
		//System.out.println("Watchdog=" + energieAPI.getWatchdog());
				
	}
	
	/**
	 * set Room temperature (in 1/10 C)
	 * @param room_temp
	 */
	public void setRoomTemperature(int room_temp){
		
		// room temp must be in 1/10�C 
		//energieAPI.setData1(room_temp);
		energieAPI.set_data1(room_temp);
		
	}
	
	/**
	 * get Valve positon
	 * @return int
	 */
	public int getValvePosition(boolean heating) {
		
		double units=0.0;
		double d_percentage=0.0;
		int i_percentage=0;
		int max = 0;;
		int min = 0;
		
		// get valve position
		int position_real = energieAPI.get_HVAC_input_0_10V(HVAC_SA);
		
		
		if(heating){
			max = Valve_6.VALVE_HOT_100;
			min = Valve_6.VALVE_HOT_0;
		}else {
			max = Valve_6.VALVE_COLD_100;
			min = Valve_6.VALVE_COLD_0;
		}
		
		//percentage =((100/(max-min))*((position_real/10)-min))/100;
		
		System.out.println("min="+min + " max=" + max);
		
		units = (100.0/(max-min));
		System.out.println("units="+units);
			 
		d_percentage=((position_real/10)-min)*units;
		System.out.println("d_percentage="+d_percentage);
		
		
		i_percentage = (int) Math.round(d_percentage);
		
		if(i_percentage>100){
			i_percentage = 100;
		}else if(i_percentage<0){
			i_percentage=0;
		}
		
		
		
		return i_percentage;
		
	}
	
	/**
	 * return total system power in W
	 * @return int
	 */
	public int getSystemPower(){
		
		return total_system_power;
		
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
