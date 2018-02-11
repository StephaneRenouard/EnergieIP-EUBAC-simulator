package com.energieip.eubac.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
	
	String url = "jdbc:mysql://51.254.141.145:3306/eubac";
	String utilisateur = "java";
	String motDePasse = "java";
	java.sql.Connection connexion = null;
	
	/**
	 * Default constructor
	 */
	public MysqlConnector() {
		
		try {

		    Class.forName( "com.mysql.jdbc.Driver" );

		} catch ( ClassNotFoundException e ) {
			
			System.out.println("MySQL: Connector/Class not found");

		}
		
		
		
		try {
		    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

		    /* Ici, nous placerons nos requêtes vers la BDD */
		    /* ... */

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
		} finally {
		    if ( connexion != null )
		        try {
		            /* Fermeture de la connexion */
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
		
		
		
	}

}
