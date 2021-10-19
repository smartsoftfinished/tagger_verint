package la.smartsoft.verint.integracion.db.verint.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.db.verint.IVerintDB;
import la.smartsoft.verint.integracion.db.verint.dto.AuditoriaTaggingDTO;
import la.smartsoft.verint.integracion.db.verint.dto.Conexion;

//clase servicio registrar una auditoria
public class ServicioAuditoria implements IVerintDB {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ServicioAuditoria.class);

	ArrayList<AuditoriaTaggingDTO> auditoria = new ArrayList<AuditoriaTaggingDTO>();

	/**
	 * Auditoria Service
	 */
	@SuppressWarnings("static-access")
	@Override
	public void registrarAuditoria(AuditoriaTaggingDTO audit) {
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = new Conexion().crearConexion();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			stmt.executeUpdate(
					"INSERT INTO  AUDITORIA_TAGGING(FECHA_REGISTRO,INCIDENT_NUMBER,NUMERO_TELEFONO,ESTADO,MENSAJE_ERROR, SESSION_ID, SITE_ID) VALUES ('"
							+ audit.getFechaRegistro() + "', '" + audit.getIncidentNumber() + "', '"
							+ audit.getNumeroTelefono() + "', '" + audit.getEstado() + "', '" + audit.getMensajeError()
							+ "', " + audit.getSessionId() + ", " + audit.getSiteId() + ")");

			System.out.print("Se ha registrado Exitosamente: ");

			ResultSet rs = stmt.executeQuery("select * from AUDITORIA_TAGGING  where ID_AUDITORIA > 0");

			if (rs.next()) {

				AuditoriaTaggingDTO auditor = new AuditoriaTaggingDTO();
				auditor.setIdAuditoria(rs.getInt("ID_AUDITORIA"));
				auditor.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
				auditor.setIncidentNumber(rs.getString("INCIDENT_NUMBER"));
				auditor.setNumeroTelefono(rs.getString("NUMERO_TELEFONO"));
				auditor.setEstado(rs.getString("ESTADO"));
				auditor.setMensajeError(rs.getString("MENSAJE_ERROR"));
				auditor.setSessionId(rs.getLong("SESSION_ID"));
				auditor.setSiteId(rs.getLong("SITE_ID"));
				auditoria.add(auditor);
				System.out.println(auditor);
				System.out.println("guardado ok: ");
			}
			conn.commit();
		} catch (Exception e) {
			LOG.error("Termina con ERROR actualizarAuditoria : " + audit.getIncidentNumber());
			LOG.error(e.getMessage());
			LOG.error(e);
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando Statement : " + audit.getIncidentNumber());
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando Statement : " + audit.getIncidentNumber());
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
		}

	}

	@Override
	public void actualizarAuditoria(AuditoriaTaggingDTO audit) {
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = new Conexion().crearConexion();
			conn.setAutoCommit(false);
			String query = "UPDATE AUDITORIA_TAGGING SET";
			int fields = 0;
			if (audit.getEstado() != null) {
				query += " ESTADO = '" + audit.getEstado() + "'";
				fields++;
			}
			if (audit.getMensajeError() != null) {
				query += (fields > 0 ? ", " : " ") + "MENSAJE_ERROR = '" + audit.getMensajeError() + "'";
				fields++;
			}
			if (audit.getSessionId() != null) {
				query += (fields > 0 ? ", " : " ") + "SESSION_ID = " + audit.getSessionId();
				fields++;
			}
			if (audit.getSiteId() != null) {
				query += (fields > 0 ? ", " : " ") + "SITE_ID = " + audit.getSiteId();
				fields++;
			}
			query += " WHERE INCIDENT_NUMBER = '" + audit.getIncidentNumber() + "'";

			stmt = conn.createStatement();
			stmt.executeUpdate(query);

			System.out.print("Se ha registrado Exitosamente: ");

			ResultSet rs = stmt.executeQuery("select * from AUDITORIA_TAGGING  where ID_AUDITORIA > 0");

			if (rs.next()) {

				AuditoriaTaggingDTO auditor = new AuditoriaTaggingDTO();
				auditor.setIdAuditoria(rs.getInt("ID_AUDITORIA"));
				auditor.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
				auditor.setIncidentNumber(rs.getString("INCIDENT_NUMBER"));
				auditor.setNumeroTelefono(rs.getString("NUMERO_TELEFONO"));
				auditor.setEstado(rs.getString("ESTADO"));
				auditor.setMensajeError(rs.getString("MENSAJE_ERROR"));
				auditor.setSessionId(rs.getLong("SESSION_ID"));
				auditor.setSiteId(rs.getLong("SITE_ID"));
				auditoria.add(auditor);
				System.out.println(auditor);
				System.out.println("actualizacion ok: ");
			}
			conn.commit();

		} catch (Exception e) {
			LOG.error("Termina con ERROR actualizarAuditoria : " + audit.getIncidentNumber());
			LOG.error(e.getMessage());
			LOG.error(e);
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando Statement : " + audit.getIncidentNumber());
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando Conexion : " + audit.getIncidentNumber());
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
		}
	}

	@Override
	public Map<String, Object> consultarInformacionAudio(String incidentNumber) {

		// Respuesta
		Map<String, Object> respuesta = new HashMap<String, Object>();

		// Coxiones, Statemente y ResultSet
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			LOG.info("Inicia consultarInformacionAudio INCIDENT_NUMBER : " + incidentNumber);

			// Se abre conexión
			conn = new Conexion().crearConexion();
			conn.setAutoCommit(false);

			// Query
			String query = " SELECT SITE_ID, SESSION_ID FROM AUDITORIA_TAGGING WHERE INCIDENT_NUMBER = ? ";

			// Se crea Prepared Statement
			stmt = conn.prepareStatement(query);
			stmt.setString(1, incidentNumber);

			// Se ejecuta Query
			rs = stmt.executeQuery();

			if (rs.next()) {
				LOG.info("Se encuentra información para consulta de INCIDENT_NUMBER : " + incidentNumber);
				respuesta.put("siteId", rs.getString("SITE_ID"));
				respuesta.put("sessionId", rs.getString("SESSION_ID"));
				LOG.info("siteId" + respuesta.get("siteId"));
				LOG.info("sessionId" + respuesta.get("sessionId"));
			} else {
				LOG.info("NO se encuentra información para consulta de INCIDENT_NUMBER : " + incidentNumber);
			}

			LOG.info("Termina consultarInformacionAudio INCIDENT_NUMBER : " + incidentNumber);

		} catch (Exception e) {
			LOG.error("Termina con ERROR consultarInformacionAudio: INCIDENT_NUMBER : " + incidentNumber);
			LOG.error(e.getMessage());
			LOG.error(e);
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando ResulSet : " + incidentNumber);
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando Statement : " + incidentNumber);
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					LOG.error("Error cerrando Conexion : " + incidentNumber);
					LOG.error(e2.getMessage());
					LOG.error(e2);
				}
			}
		}
		return respuesta;
	}
}
