package la.smartsoft.verint.integracion.db.verint.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.db.rdw.impl.ServicioRDW;
import la.smartsoft.verint.integracion.db.verint.IVerintParametro;

import la.smartsoft.verint.integracion.db.verint.dto.Conexion;
import la.smartsoft.verint.integracion.db.verint.dto.ParametroDTO;

public class ServicioParametro implements IVerintParametro {
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ServicioRDW.class);

	List<ParametroDTO> parametro = new ArrayList<ParametroDTO>();

	@SuppressWarnings("static-access")
	@Override
	public ParametroDTO consultarParametro(int idParametro) {

		Connection conn = null;
		ParametroDTO parametroDTO = new ParametroDTO();

		try {
			LOG.info("Se intenta obtener conexion a base PostgreSQL");
			conn = new Conexion().crearConexion();
			LOG.info("Conexion Establecida");
			conn.setAutoCommit(false);

			PreparedStatement smt = conn.prepareStatement(
					"SELECT ID_PARAMETRO,NOMBRE,DESCRIPCION,VALOR,ESTADO,FECHA_CREACION,TIPO_PARAMETRO,CLASE_PARAMETRO FROM PARAMETRO_TAGGING WHERE ID_PARAMETRO= '"
							+ idParametro + "'");

			System.out.print("Se consulto Exitosamente:  ");

			ResultSet rs = smt.executeQuery();

			if (!rs.next()) {

				System.out.print("no hay datos!: ");
			} else {
				parametroDTO.setIdParametro(rs.getInt("ID_PARAMETRO"));
				parametroDTO.setNombre(rs.getString("NOMBRE"));
				parametroDTO.setDescripcion(rs.getString("DESCRIPCION"));
				parametroDTO.setValor(rs.getString("VALOR"));
				parametroDTO.setEstado(rs.getString("ESTADO"));
				parametroDTO.setFechaCreacion(rs.getDate("FECHA_CREACION"));
				parametroDTO.setTipoParametro(rs.getString("TIPO_PARAMETRO"));
				parametroDTO.setClaseParametro(rs.getString("CLASE_PARAMETRO"));
				System.out.println("El parametro es: " + parametroDTO.getValor());
				parametro.add(parametroDTO);

			}

			rs.close();
			smt.close();
			conn.commit();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.print("No se obtuvo el parametro compos Vacios: ");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.print("No se obtuvo el parametro compos Vacios: ");
		}

		return parametroDTO;
	}

}
