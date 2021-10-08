package la.smartsoft.verint.integracion.db.verint.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//clase para conexion a postgresql

public class Conexion {
	static final String URL = "jdbc:postgresql://localhost:5432/verintt";
	static final String USER = "sa";
	static final String PASS = "Smartsoft2021%";
	Connection connection = null;
	
	public static Connection crearConexion() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conexion = DriverManager.getConnection(URL, USER, PASS);
		if (conexion != null){
		System.out.print("Conexion establecida..");
		return conexion;
		}
		return null;
		}
	
	 public Connection getConnection(){
	      return connection;
	   }

	   public void desconectar(){
	      connection = null;
	   }
	
	

}
