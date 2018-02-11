package com.energieip.eubac.simulator.room;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utilities {

	/**
	 * compute energy transfer from inside to outside
	 * @param room_energy
	 * @param temp_room_outside
	 * @param coef_U
	 * @param room_surface_ext
	 * @param sLEEPING_TIME
	 * @param joule_factor
	 * @return double
	 */
	public static double compute_Inside_Outside_Energy_Transfert(double room_energy, double temp_room_outside, double coef_U,
			double room_surface_ext, int sLEEPING_TIME, double joule_factor) {
		
		int iteration = sLEEPING_TIME/1000;
		double temp_final = 0;
		double joules_final=0;
		
		for (int i = 0; i < iteration; i++) {
			
			double joules_init = room_energy;
			double temp_room_inside = Utilities.compute_temp_from_energy(room_energy, joule_factor);
			double transfert = coef_U * room_surface_ext * (temp_room_inside-temp_room_outside); // should be negative or positive, in W or J/s
			
			joules_final = joules_init - transfert;
			
			temp_final = joules_final/(joule_factor*60);
			
			// for debug purpose
			//System.out.println("[computeDeperdition] iteration=" + i + " T(init)=" + temp_room_inside + " joules_init=" + joules_init + " joules_final="+joules_final + "T(final)="  + temp_final);
			
			temp_room_inside = temp_final;
		}				
		
		
		
		return joules_final;
	}

	/**
	 * compute temperature in °C from energy in Joules
	 * @param room_energy
	 * @param joule_factor
	 * @return double
	 */
	public static double compute_temp_from_energy(double room_energy, double joule_factor) {
		
		double temp = room_energy/( joule_factor *60);
		
		return temp;
	}

	
	/**
	 * T(°C) => joules
	 * @param temp_room_inside
	 * @param joule_factor
	 * @return
	 */
	public static double compute_Energy(double temp_room_inside, double joule_factor) {
		
		double joules_init = temp_room_inside * joule_factor * 60;
		
		return joules_init;
	}
	
	/**
	 * Add human energy
	 * @param room_energy
	 * @param human_body_energy
	 * @param human_number
	 * @param sLEEPING_TIME 
	 * @return
	 */
	public static double compute_humans_energy(double human_body_energy, double human_number, int sLEEPING_TIME) {
		
		int iteration = sLEEPING_TIME/1000;
	
		double energy_from_human = human_body_energy*iteration*human_number;
			
		return energy_from_human;
	}
	
	/**
	 * compute external factor
	 * @param room_energy
	 * @param room_external_energy
	 * @param sLEEPING_TIME
	 * @return double
	 */
	public static double compute_external_factors_energy(double room_external_energy, int sLEEPING_TIME) {
		
		int iteration = sLEEPING_TIME/1000;
		
		double energy_from_external_factors = room_external_energy*iteration;
		
		return energy_from_external_factors;
	}
	
	
	/**
	 * transform temperature in EiP format (1/10°C)
	 * @param temperature
	 * @return int
	 */
	public static int compute_temp_in_EiP_format(double temperature){
		
		// temperature in EiP format are in 1/10°C
		temperature = temperature*10;
		DecimalFormat df = new DecimalFormat("#"); // #.# for 1 decimal
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		String round_temp = df.format(temperature);
				
		return Integer.parseInt(round_temp);
	}

	/**
	 * compute energy from HVAC application
	 * @param room_energy
	 * @param valve_position
	 * @param application_power
	 * @param sLEEPING_TIME
	 * @return double
	 */
	public static double compute_application_energy( int _valve_position, int application_power, int sLEEPING_TIME) {
		
		int iteration = sLEEPING_TIME/1000;
		
		// 512 => 51.2
		double valve_position = _valve_position/10 ;
		double valve_position2 = _valve_position%10;
		valve_position2 = valve_position2/10;
		valve_position = valve_position + valve_position2;
		
		double factor = valve_position/100; //(%)
		
		double power_from_system = factor * application_power; // in W
		double energy_from_application = power_from_system * iteration; // in J
			
		return energy_from_application;
	}
	

}
