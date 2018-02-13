package com.energieip.eubac.mysql;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class MysqlConnector {
	
	static String url = "jdbc:mysql://51.254.141.145:3306/eubac";
	static String utilisateur = "java2";
	static String motDePasse = "java2";
	static Connection connection = null;
	
	static String table_name = "";
	
    static Statement statement;
    
	
	/**
	 * Default constructor
	 */
	public MysqlConnector() {
		
		try {

			System.out.println("loading connector");
		    Class.forName( "com.mysql.jdbc.Driver" );

		} catch ( ClassNotFoundException e ) {
			
			System.out.println("MySQL: Connector/Class not found");

		}
		
		
		
		try {
			
			System.out.println("trying to connect");
		
			connection =  (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );

		    // create table 
		    statement = connection.createStatement();
		    
		    // create table
		    MysqlConnector.table_name = "test15";
		    System.out.println("[MySQL] building table " + table_name);
		    String MAKE_TABLE = "CREATE TABLE " + table_name + " (timestamp VARCHAR(20) PRIMARY KEY, value VARCHAR(20), valve VARCHAR(20))";
		    System.out.println(MAKE_TABLE);
		    statement.executeUpdate(MAKE_TABLE);
		    		    
		    
		    
		    /*
		    // add line
		    System.out.println("adding first line");
		    String id = Time.timeStamp("").trim();
		    String value = "21.2";
		    
		    String statment = "INSERT INTO Test_Table(timestamp, value) VALUES ('"+id+"', '"+ value +"');";
		    
		    System.out.println(statment);
		    
		    statement.executeUpdate(statment);
		    
		    statement.executeUpdate("INSERT INTO Test_Table(timestamp, value) VALUES ('2019', '23');");
		    
		    System.out.println("adding second line");		    
		    id  = Time.timeStamp("");
		    value = "21.2";
		    //statement.executeUpdate("insert into Test_Table(timestamp, value) values("+id+", '"+ value +"');");
		    */
		   
		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
		} finally {
		    if ( connection != null )
		        try {
		        	
		        	System.out.println("closing everything");
		        	
		        	 // close statement 
				    statement.close();
				    		        	
		            /* Fermeture de la connexion */
				    connection.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
			
	} // end of constructor
	
	/**
	 * insert values into DB
	 * @param value1
	 * @param value2
	 */
	public static void Insert_data_in_MySQL(String value1, String value2){
		 try {
			connection =  (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connection.createStatement();
			
			String statment = "INSERT INTO " + table_name + "(timestamp, value) VALUES ('"+value1+"', '"+ value2 +"');";
			System.out.println(statment);
			statement.executeUpdate(statment);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    if ( connection != null )
		        try {
		        	
		        	
		        	 // close statement 
				    statement.close();
				    		        	
		            /* Fermeture de la connexion */
				    connection.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
		 
	}
	
	/**
	 * insert values into DB
	 * @param value1
	 * @param value2
	 * @param value3
	 */
	public static void Insert_data_in_MySQL(String value1, String value2, String value3){
		 try {
			connection =  (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connection.createStatement();
			
			String statment = "INSERT INTO " + table_name + "(timestamp, value, valve) VALUES ('"+value1+"', '"+ value2 +"', '" + value3 +"');";
			System.out.println(statment);
			statement.executeUpdate(statment);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    if ( connection != null )
		        try {
		        	
		        	
		        	 // close statement 
				    statement.close();
				    		        	
		            /* Fermeture de la connexion */
				    connection.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
		 
	}

} // end of class
