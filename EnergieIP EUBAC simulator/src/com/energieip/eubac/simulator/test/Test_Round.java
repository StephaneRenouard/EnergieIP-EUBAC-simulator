package com.energieip.eubac.simulator.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test_Round {

	public static void main(String[] args) {
		
		new Test_Round();

	}
	
	public Test_Round() {
		
		double temperature = 21.25683;
		
		// temperature in EiP format are in 1/10�C
		temperature = temperature*10;
		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		String round_temp = df.format(temperature);
		
		System.out.println(round_temp);
	}

}
