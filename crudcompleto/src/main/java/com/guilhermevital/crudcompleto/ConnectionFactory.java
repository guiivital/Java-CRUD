package com.guilhermevital.crudcompleto;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/formulario";
	
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return con;
	}
	
	public static void main(String[] args) throws Exception {
		Connection connection = createConnectionToMySQL();
		if(connection!= null) {
			System.out.println("Conexão obtida com sucesso!");
			connection.close();
		}
	}
}
