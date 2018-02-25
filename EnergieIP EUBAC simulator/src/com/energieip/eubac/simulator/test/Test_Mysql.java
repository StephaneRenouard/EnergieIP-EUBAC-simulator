package com.energieip.eubac.simulator.test;

import com.energieip.eubac.mysql.MysqlConnector;

public class TestMysql {

	public static void main(String[] args) {
		
		new TestMysql();

	}
	
	/**
	 * Default constructor
	 */
	public TestMysql() {
		
		new MysqlConnector();
		
		
	}

}
