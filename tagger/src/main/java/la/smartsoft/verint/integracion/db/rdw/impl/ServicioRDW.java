package la.smartsoft.verint.integracion.db.rdw.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import la.smartsoft.verint.integracion.db.rdw.IRDW;
import la.smartsoft.verint.integracion.db.rdw.dto.LlamadaDTO;
import la.smartsoft.verint.integracion.db.rdw.utils.HibernateUtil;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

/**
 * @author pedro
 *	Servicio que obtiene la lista de llamadas
 */
public class ServicioRDW extends ConfiguracionApi implements IRDW  {
	
	private static final Logger LOG = Logger.getLogger(ServicioRDW.class);
//	private SessionFactory sessionFactory = Hibernate
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<LlamadaDTO> obtenerLlamadas(Date inicio, Date fin) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSSSSS");
			
			LOG.info("Inicia obtenerLlamadas");
			LOG.info(sdf.format(inicio));
			LOG.info(sdf.format(fin));
			
			//Consulta para Pruebas
			if(!AMBIENTE_PRODUCCION) {
				return obtenerLlamadasDummy(inicio, fin);
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -3);
			inicio = calendar.getTime();
					
			//Implementar consulta SQLServer 	
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List<Object[]> queryRta = session.createQuery("select IncidentNumber,NumeroTelefonoIncidente,Duracion,FechaCreacion from INCIDENTES_VERINT "
					+ "where FechaCreacion >= :fechaInicio and FechaCreacion <= :fechaFinal")
					.setParameter("fechaInicio", inicio).setParameter("fechaFinal", fin).list();
			session.close();
			
			List<LlamadaDTO> lista = new ArrayList<LlamadaDTO>();
			for(Object[] object : queryRta) {
				if(object != null) {
					LlamadaDTO llamadaDTO = new LlamadaDTO();
					llamadaDTO.setIncidentNumber((String) object[0]);
					llamadaDTO.setNumeroTelefonoIncidente((String) object[1]);
					llamadaDTO.setDuracion((object[2] != null ? (Long) object[2]  : null ));
					llamadaDTO.setFechaRegistro(( object[3] !=null ? (Date) object[3] : null ));
					lista.add(llamadaDTO);
				}
			}
			
			LOG.info("Fin obtenerLlamadas");
			LOG.info("Cantidad Registros: " + (lista !=null ? lista.size() : "VACIO"));
			
			return lista;
		} catch (Exception e) {
			LOG.error(e);
		}
		
		LOG.info("Fin obtenerLlamadas Null");
		return null;
		

	}
	
	/**
	 * Servicio que retorna un listado de llamadas Dummy
	 * @param inicio
	 * @param fin
	 * @return
	 */
	private List<LlamadaDTO> obtenerLlamadasDummy(Date inicio, Date fin) {
		
		List<LlamadaDTO> llamadas = new ArrayList<LlamadaDTO>();
		
		LlamadaDTO llamada1 = new LlamadaDTO();
		
		//llamada1.setNumeroTelefonoIncidente("3228857088");
		//llamada1.setIncidentNumber("SUR-01462008-21");
		//llamada1.setDuracion(8l);

//		llamada1.setNumeroTelefonoIncidente("3112182793");
//		llamada1.setIncidentNumber("SUR-01848609-21");
//		llamada1.setDuracion(9l);
		
		llamada1.setNumeroTelefonoIncidente("3112182793");
		llamada1.setIncidentNumber("SUR-02003828-21");
		llamada1.setDuracion(30l);
		
		/*Calendar calendar =  GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR, 2021);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.DAY_OF_MONTH, 25);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 20);
		calendar.set(Calendar.SECOND, 14);
		calendar.set(Calendar.MILLISECOND, 700);*/
		//2021-07-02T20:20:14.700Z
		
		Calendar calendar =  GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR, 2021);
		calendar.set(Calendar.MONTH, 8);
		calendar.set(Calendar.DAY_OF_MONTH, 16);
		calendar.set(Calendar.HOUR_OF_DAY, 14);
		calendar.set(Calendar.MINUTE, 55);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 00);
		//2021-08-24T04:42:04.767Z
		
		llamada1.setFechaRegistro(calendar.getTime());
		llamadas.add(llamada1);
		
		LlamadaDTO llamada2 = new LlamadaDTO();
		llamada2.setDuracion(300l);
		llamada2.setNumeroTelefonoIncidente("3112182793");
		llamada2.setIncidentNumber("SUR-02003877-21");
		
		Calendar calendar2 =  GregorianCalendar.getInstance();
		calendar2.set(Calendar.YEAR, 2021);
		calendar2.set(Calendar.MONTH, 8);
		calendar2.set(Calendar.DAY_OF_MONTH, 16);
		calendar2.set(Calendar.HOUR_OF_DAY, 15);
		calendar2.set(Calendar.MINUTE, 8);
		calendar2.set(Calendar.SECOND, 00);
		calendar2.set(Calendar.MILLISECOND, 00);
		
		llamada2.setFechaRegistro(calendar2.getTime());
		
		llamadas.add(llamada2);
//
//		LlamadaDTO llamada3 = new LlamadaDTO();
//		llamada3.setDuracion(200l);
//		llamada3.setNumeroTelefonoIncidente("3004105180");
//		llamada3.setIncidentNumber("I3");
//		llamadas.add(llamada3);

		return llamadas;
	}

}
