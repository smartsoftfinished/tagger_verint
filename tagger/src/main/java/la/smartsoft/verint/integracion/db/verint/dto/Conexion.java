package la.smartsoft.verint.integracion.db.verint.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

//clase para conexion a postgresql

public class Conexion extends ConfiguracionApi {
	Connection connection = null;

	public Connection crearConexion() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection conexion = DriverManager.getConnection(DATASOURCE_VERINT_TAGGER, USER_VERINT_TAGGER,
				CONTRASENIA_VERINT_TAGGER);
		if (conexion != null) {
			System.out.print("Conexion establecida..");
			return conexion;
		}
		return null;
	}

	public Connection getConnection() {
		return connection;
	}

	public void desconectar() {
		connection = null;
	}

}
