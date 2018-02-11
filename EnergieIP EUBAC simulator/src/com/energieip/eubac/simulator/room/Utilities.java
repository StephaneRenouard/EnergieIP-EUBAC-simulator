package com.energieip.eubac.simulator.room;

public class Utilities {

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
						
			System.out.println("[computeDeperdition] iteration=" + i + " T(init)=" + temp_room_inside + " joules_init=" + joules_init + " joules_final="+joules_final + "T(final)="  + temp_final);
			
			temp_room_inside = temp_final;
		}				
		
		
		
		return joules_final;
	}

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
	public static double compute_add_humans(double room_energy, double human_body_energy, double human_number, int sLEEPING_TIME) {
		
		int iteration = sLEEPING_TIME/1000;
	
		double energy_from_human = human_body_energy*iteration*human_number;
		
		room_energy = room_energy + energy_from_human;
		
		return room_energy;
	}

	public static double compute_add_external_factors(double room_energy, double room_external_energy,
			int sLEEPING_TIME) {
		
		int iteration = sLEEPING_TIME/1000;
		double energy_from_external_factors = room_external_energy*iteration;
		
		room_energy = room_energy + energy_from_external_factors;
		
		return room_energy;
	}
	

}
