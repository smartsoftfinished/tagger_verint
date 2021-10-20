package la.smartsoft.verint.integracion.db.rdw.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.hibernate.Session;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;

import la.smartsoft.verint.integracion.db.rdw.IRDW;
import la.smartsoft.verint.integracion.db.rdw.dto.LlamadaDTO;
import la.smartsoft.verint.integracion.db.rdw.utils.HibernateUtil;
import la.smartsoft.verint.integracion.db.verint.dto.AuditoriaTaggingDTO;
import la.smartsoft.verint.integracion.db.verint.dto.ParametroDTO;
import la.smartsoft.verint.integracion.db.verint.impl.ServicioAuditoria;
import la.smartsoft.verint.integracion.db.verint.impl.ServicioParametro;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

/**
 * @author pedro Servicio que obtiene la lista de llamadas
 */
public class ServicioRDW extends ConfiguracionApi implements IRDW {

	private static final Logger LOG = Logger.getLogger(ServicioRDW.class);
	// private SessionFactory sessionFactory = Hibernate

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<LlamadaDTO> obtenerLlamadas(Date inicio, Date fin) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			LOG.info("Inicia obtenerLlamadas");
			LOG.info(sdf.format(inicio));
			LOG.info(sdf.format(fin));

			// Consulta para Pruebas

			// if (!AMBIENTE_PRODUCCION) {
			// return obtenerLlamadasDummy(inicio, fin);
			// }
			// Calendar calendar = Calendar.getInstance();
			// calendar.add(Calendar.HOUR_OF_DAY, -23);
			// inicio = calendar.getTime();
			// Se consulta numero maximo de registros a buscar
			ServicioParametro servicioParametro = new ServicioParametro();

			LOG.info("Se intenta consumir el parametro registros maximos");
			ParametroDTO numRegistros = servicioParametro.consultarParametro(ParametroDTO.REGISTROS_MAXIMOS);
			LOG.info("Se consume el parametro registros maximos");
			LOG.info("Se intenta consumir el parametro nombre de tabla");
			ParametroDTO tabla = servicioParametro.consultarParametro(ParametroDTO.TABLA_RDW);
			LOG.info("Se consume el parametro nombre de tabla");
			LOG.info("Se intenta consumir el parametro nombre columna numero incidente");
			ParametroDTO numIncidente = servicioParametro.consultarParametro(ParametroDTO.COL_NUM_INCIDENTE);
			LOG.info("Se consume el parametro nombre columna numero incidente");
			LOG.info("Se intenta consumir el parametro nombre columna telefono");
			ParametroDTO telefono = servicioParametro.consultarParametro(ParametroDTO.COL_TELEFONO);
			LOG.info("Se consume el parametro nombre columna telefono");
			LOG.info("Se intenta consumir el parametro nombre columna fecha incidente");
			ParametroDTO fechaInicio = servicioParametro.consultarParametro(ParametroDTO.COL_FECHA_INICIO);
			LOG.info("Se consume el parametro nombre columna fecha incidente");
			LOG.info("Se intenta consumir el parametro nombre columna fecha fin");
			ParametroDTO fechaFin = servicioParametro.consultarParametro(ParametroDTO.COL_FECHA_FIN);
			LOG.info("Se consume el parametro nombre columna fecha fin");
			// Implementar consulta SQLServer
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String query = "select top " + numRegistros.getValor() + " " + numIncidente.getValor() + ", "
					+ telefono.getValor() + ", DATEDIFF(second, " + fechaInicio.getValor() + ", " + fechaFin.getValor()
					+ " ) as Duration, " + fechaInicio.getValor() + " from " + tabla.getValor() + " " + "where "
					+ fechaInicio.getValor() + " >= '" + sdf.format(inicio) + "' and " + fechaInicio.getValor() + " < '"
					+ sdf.format(fin) + "'";
			LOG.info(query);
			List<Object[]> queryRta = session.createSQLQuery(query).addScalar(numIncidente.getValor(), new StringType())
					.addScalar(telefono.getValor(), new StringType()).addScalar("Duration", new LongType())
					.addScalar(fechaInicio.getValor(), new TimestampType()).list();
			session.close();
			LOG.info("Se han encontrado: " + queryRta.size() + " registros en RDW");

			ServicioAuditoria servicioAuditoria = new ServicioAuditoria();
			List<LlamadaDTO> lista = new ArrayList<LlamadaDTO>();
			for (Object[] object : queryRta) {
				if (object != null) {
					LlamadaDTO llamadaDTO = new LlamadaDTO();
					llamadaDTO.setIncidentNumber((String) object[0]);
					String numero = (String) object[1];
					Pattern p = Pattern.compile("[\\s]");
					Matcher m = p.matcher(numero);
					llamadaDTO.setNumeroTelefonoIncidente(m.replaceAll(""));
					llamadaDTO.setDuracion((object[2] != null ? (Long) object[2] : null));
					llamadaDTO.setFechaRegistro((object[3] != null ? (Date) object[3] : null));
					lista.add(llamadaDTO);
					servicioAuditoria
							.registrarAuditoria(new AuditoriaTaggingDTO(new Date(), llamadaDTO.getIncidentNumber(),
									llamadaDTO.getNumeroTelefonoIncidente(), "SIN PROCESAR", null, null, null));
				}
			}

			LOG.info("Fin obtenerLlamadas");
			LOG.info("Cantidad Registros: " + (lista != null ? lista.size() : "VACIO"));

			return lista;
		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();
		}

		LOG.info("Fin obtenerLlamadas Null");
		return null;

	}

	/**
	 * Servicio que retorna un listado de llamadas Dummy
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 */
	private List<LlamadaDTO> obtenerLlamadasDummy(Date inicio, Date fin) {

		List<LlamadaDTO> llamadas = new ArrayList<LlamadaDTO>();

		LlamadaDTO llamada1 = new LlamadaDTO();
		ServicioAuditoria servicioAuditoria = new ServicioAuditoria();

		// llamada1.setNumeroTelefonoIncidente("3228857088");
		// llamada1.setIncidentNumber("SUR-01462008-21");
		// llamada1.setDuracion(8l);

		// llamada1.setNumeroTelefonoIncidente("3112182793");
		// llamada1.setIncidentNumber("SUR-01848609-21");
		// llamada1.setDuracion(9l);

		// llamada1.setNumeroTelefonoIncidente("3112182793");
		// llamada1.setIncidentNumber("SUR-02003828-21");
		// llamada1.setDuracion(30l);

		llamada1.setNumeroTelefonoIncidente("3222271737");
		llamada1.setIncidentNumber("SUR-02202069-21");
		llamada1.setDuracion(30l);

		// servicioAuditoria.registrarAuditoria(new AuditoriaTaggingDTO(new Date(),
		// llamada1.getIncidentNumber(),
		// llamada1.getNumeroTelefonoIncidente(), "SIN PROCESAR", null, null, null));

		// 2021-10-13T10:40:57.843Z

		// Calendar calendar = GregorianCalendar.getInstance();
		// calendar.set(Calendar.YEAR, 2021);
		// calendar.set(Calendar.MONTH, 7);
		// calendar.set(Calendar.DAY_OF_MONTH, 25);
		// calendar.set(Calendar.HOUR_OF_DAY, 8);
		// calendar.set(Calendar.MINUTE, 20);
		// calendar.set(Calendar.SECOND, 14);
		// calendar.set(Calendar.MILLISECOND, 700);

		// 2021-07-02T20:20:14.700Z

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR, 2021);
		calendar.set(Calendar.MONTH, 9);
		calendar.set(Calendar.DAY_OF_MONTH, 13);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 40);
		calendar.set(Calendar.SECOND, 50);
		calendar.set(Calendar.MILLISECOND, 00);
		// 2021-08-24T04:42:04.767Z

		llamada1.setFechaRegistro(calendar.getTime());
		llamadas.add(llamada1);

		LlamadaDTO llamada2 = new LlamadaDTO();
		llamada2.setDuracion(300l);
		llamada2.setNumeroTelefonoIncidente("3203341568");
		llamada2.setIncidentNumber("SUR-02216497-21");

		Calendar calendar2 = GregorianCalendar.getInstance();
		calendar2.set(Calendar.YEAR, 2021);
		calendar2.set(Calendar.MONTH, 9);
		calendar2.set(Calendar.DAY_OF_MONTH, 15);
		calendar2.set(Calendar.HOUR_OF_DAY, 16);
		calendar2.set(Calendar.MINUTE, 20);
		calendar2.set(Calendar.SECOND, 41);
		calendar2.set(Calendar.MILLISECOND, 00);

		llamada2.setFechaRegistro(calendar2.getTime());
		llamadas.add(llamada2);
		//
		// LlamadaDTO llamada3 = new LlamadaDTO();
		// llamada3.setDuracion(200l);
		// llamada3.setNumeroTelefonoIncidente("3004105180");
		// llamada3.setIncidentNumber("I3");
		// llamadas.add(llamada3);

		return llamadas;
	}

}
