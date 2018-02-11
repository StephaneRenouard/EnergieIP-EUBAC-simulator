package com.energieip.eubac.simulator.test;

public class TestValvePosition {

	public static void main(String[] args) {
		
		new TestValvePosition();

	}
	
	/**
	 * default constructor
	 */
	public TestValvePosition() {
		
		int _valve_position = 512;
		double valve_position = _valve_position/10 ;
		double valve_position2 = _valve_position%10;
		valve_position2 = valve_position2/10;
		valve_position = valve_position + valve_position2;
		
		System.out.println(valve_position);
		
	}

}
