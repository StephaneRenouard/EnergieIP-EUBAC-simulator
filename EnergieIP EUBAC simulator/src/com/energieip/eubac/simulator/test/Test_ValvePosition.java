package com.energieip.eubac.simulator.test;

public class Test_ValvePosition {

	public static void main(String[] args) {
		
		new Test_ValvePosition();

	}
	
	/**
	 * default constructor
	 */
	public Test_ValvePosition() {
		
		int _valve_position = 512;
		double valve_position = _valve_position/10 ;
		double valve_position2 = _valve_position%10;
		valve_position2 = valve_position2/10;
		valve_position = valve_position + valve_position2;
		
		System.out.println("valve_position=" + valve_position);
		
		// should be divided by 100 (%)
		double factor = valve_position/100;
		
		System.out.println("factor=" + factor);
		
	}

}
