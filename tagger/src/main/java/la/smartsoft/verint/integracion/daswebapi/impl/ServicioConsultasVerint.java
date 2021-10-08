package la.smartsoft.verint.integracion.daswebapi.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import la.smartsoft.verint.integracion.daswebapi.IConsultaVerint;
import la.smartsoft.verint.integracion.daswebapi.TokenInvalidoException;
import la.smartsoft.verint.integracion.daswebapi.dto.Period;
import la.smartsoft.verint.integracion.daswebapi.dto.RequestDynamicQuery;
import la.smartsoft.verint.integracion.daswebapi.dto.ResponseDynamicQuery;
import la.smartsoft.verint.integracion.daswebapi.dto.SessionVerint;
import la.smartsoft.verint.integracion.db.rdw.dto.LlamadaDTO;
import la.smartsoft.verint.integracion.token.dto.Token;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

/**
 * @author pedro
 *	Servicio de Consulta de Llamadas en Verint
 */
public class ServicioConsultasVerint extends ConfiguracionApi implements IConsultaVerint {
	
	private static final Logger LOG = Logger.getLogger(ServicioConsultasVerint.class);
	
	private static final String BEARER = "Bearer ";
	private static final String IMPACT360AUTHTOKEN = "Impact360AuthToken";
	private static final String QUERY_ANI = "ANI = ";
	private static final String AUDIO_CH_NUM = "AUDIO_CH_NUM";
	private static final String PBX_LOGIN_ID = "PBX_LOGIN_ID";
	private static final String COMMAND_TIMEOUT_SECONDS = "30";
	private static final String PERIOD_TYPE = "Relative";
	private static final Integer CANTIDAD_SEGUNDOS_QUERY_INICIO = -3600;
	private static final Integer CANTIDAD_SEGUNDOS_QUERY_DESPUES = 3600;

	//private static final Integer CANTIDAD_SEGUNDOS_QUERY_INICIO_DEFAULT = 86400;
	private static final Integer CANTIDAD_SEGUNDOS_QUERY_DESPUES_DEFAULT = 86400;
	

	@Override
	public List<SessionVerint> consultarVerint(LlamadaDTO llamada, Token token) throws TokenInvalidoException{
		
		try {
			LOG.info("Inicia consultarVerint");
			LOG.info(llamada.toString());
			
			if(llamada.getNumeroTelefonoIncidente() !=null && llamada.getIncidentNumber() !=null
					 && llamada.getFechaRegistro() !=null) {
				
				RequestDynamicQuery request = obtenerRequest(llamada, token);
				LOG.info("DATOS CONSULTA DYNAMIC QUERY : "+ request.toString());
				
				if(request !=null) {
					
					//Construimos request a servicio REST.
					Client clienteWeb = ClientBuilder.newClient();
					WebTarget clientTarget = clienteWeb.target(ENDPOINT_VERINT_REST_QUERY);
					LOG.info("Se va a consultar en:" +ENDPOINT_VERINT_REST_QUERY);
					
					Invocation.Builder invocationBuilder = clientTarget.request(MediaType.APPLICATION_JSON)
							.header(HttpHeaders.AUTHORIZATION, BEARER + token.getToken())
							.header(IMPACT360AUTHTOKEN, token.getToken());
					
					String prueba = "{\r\n" + 
							"\"UserID\"\r\n" + 
							":\r\n" + 
							"1251\r\n" + 
							",\r\n" + 
							"\"ConditionsString\"\r\n" + 
							":\r\n" + 
							"\"ANI = 3105539188\"\r\n" + 
							",\r\n" + 
							"\"period\"\r\n" + 
							": {\r\n" + 
							"\"BeginPeriod\"\r\n" + 
							":\r\n" + 
							"\"2021-06-17T00:00:00\"\r\n" + 
							",\r\n" + 
							"\"EndPeriod\"\r\n" + 
							":\r\n" + 
							"\"2021-06-18T23:59:59\"\r\n" + 
							",\r\n" + 
							"\"TimeOfDateBegin\"\r\n" + 
							":\r\n" + 
							"\"00:00:00\"\r\n" + 
							",\r\n" + 
							"\"TimeOfDateEnd\"\r\n" + 
							":\r\n" + 
							"\"23:59:59\"\r\n" + 
							",\r\n" + 
							"\"Type\"\r\n" + 
							":\r\n" + 
							"\"Relative\"\r\n" + 
							",\r\n" + 
							"\"Days\"\r\n" + 
							":\r\n" + 
							"}\r\n" + 
							",\r\n" + 
							"\"RequestedGroups\"\r\n" + 
							": [\r\n" + 
							"\"AUDIO_CH_NUM\"\r\n" + 
							",\r\n" + 
							"\"PBX_LOGIN_ID\"\r\n" + 
							"]\r\n" + 
							",\r\n" + 
							"\"RequestedColumns\"\r\n" + 
							":\r\n" + 
							"null\r\n" + 
							",\r\n" + 
							"\"CommandTimoutSeconds\"\r\n" + 
							":\r\n" + 
							"30\r\n" + 
							"}";
					
					//Consumimos servicio REST
					Response response = invocationBuilder.post(Entity.entity(request, MediaType.APPLICATION_JSON));
					
					int status = response.getStatus();
					LOG.info("Estatus: " + status);
					
					//Obtenemos respuesta de la API.
					if (Status.OK.getStatusCode() == status) {
						
						//String rta = response.getEntity();
						Map map = response.readEntity(Map.class);
						return obtenerRespuestaSesiones(map);
						//ResponseDynamicQuery responseDQ = response.readEntity(ResponseDynamicQuery.class);
						
						//LOG.info("RTA DASWEBAPI STRING: " + rta);
						/*if(response !=null && responseDQ !=null) {
							LOG.info("RTA DASWEBAPI: " + responseDQ.toString());
							return responseDQ.getSessions();
						}*/
						//System.out.println("Pedro");
					}
					LOG.info("NO SE CONSULTO DASWEBAPI");
					
				}else {
					LOG.info("No se pudo construir Request de Consulta");
				}			

			
			}else {
				LOG.info("No vienen datos completos, numero de telefono, incidente y fecha de registro son requeridos");
			}
			
			
			LOG.info("Fin consultarVerint");
		} catch (Exception e) {
			LOG.error("Error Consulta Verint");
			LOG.error(e);
		}
		
		LOG.info("Inicia consultarVerint Null");
		return new ArrayList<SessionVerint>();
		
	}
	
	/**
	 * @return
	 */
	public List<SessionVerint> obtenerRespuestaSesiones(Map map) {
		List<SessionVerint>respuesta = new ArrayList<SessionVerint>();
		
		try {
			
			if(map !=null && !map.isEmpty()) {
				if(map.containsKey(("Sessions"))){
					
					ArrayList<Map<String, Object>> listaSesiones = (ArrayList<Map<String, Object>>)map.get("Sessions");
					if(listaSesiones!=null && !listaSesiones.isEmpty()) {
						LOG.info("HAY : " + listaSesiones.size() + " SESIONES");
						
						for(Map<String , Object> session : listaSesiones) {
							SessionVerint sesionRta = new SessionVerint();
							
							//Mostramos los valores que trae el servicio
							imprimirAtributos(session);
							
							LOG.info("audio_end_time : "+ session.get("audio_end_time"));
							
							if(session.get("ani") !=null) {
								sesionRta.setAni((String)session.get("ani"));
							}
							if(session.get("audio_ch_num") !=null) {
								sesionRta.setAudio_ch_num(((Integer)session.get("audio_ch_num")).toString());
							}
							if(session.get("audio_end_time") !=null) {
								sesionRta.setAudio_end_time((String)session.get("audio_end_time"));
							}else {
								if(session.get("AUDIO_START_TIME") !=null && session.get("duration_seconds") !=null) {
									sesionRta.setAudio_end_time(
										obtenerFechaFin(((String)session.get("AUDIO_START_TIME")), (((Integer)session.get("duration_seconds"))))
									);
								}
							}		
							
							if(session.get("audio_module_num") !=null) {
								sesionRta.setAudio_module_num(((Integer)session.get("audio_module_num")).toString());
							}
							if(session.get("AUDIO_START_TIME") !=null) {
								sesionRta.setAUDIO_START_TIME((String)session.get("AUDIO_START_TIME"));
							}
							if(session.get("call_id") !=null) {
								sesionRta.setCall_id(((Long)session.get("call_id")).toString());
							}
							if(session.get("cd17") !=null) {
								sesionRta.setCd17((String)session.get("cd17"));
							}
							if(session.get("cd2") !=null) {
								sesionRta.setCd2((String)session.get("cd2"));
							}
							if(session.get("direction") !=null) {
								sesionRta.setDirection(((Integer)session.get("direction")).toString());
							}
							if(session.get("dnis") !=null) {
								sesionRta.setDnis((String)session.get("dnis"));
							}
							if(session.get("exception_reason") !=null) {
								sesionRta.setException_reason(((Integer)session.get("exception_reason")).toString());
							}
							if(session.get("interaction_type") !=null) {
								sesionRta.setInteraction_type((String)session.get("interaction_type"));
							}
							if(session.get("media_type_bit_mask") !=null) {
								sesionRta.setMedia_type_bit_mask(((Integer)session.get("media_type_bit_mask")).toString());
							}
							if(session.get("number_of_holds") !=null) {
								sesionRta.setNumber_of_holds(((Integer)session.get("number_of_holds")).toString());
							}
							if(session.get("pbx_login_id") !=null) {
								sesionRta.setPbx_login_id((String)session.get("pbx_login_id"));
							}
							if(session.get("personal_id") !=null) {
								sesionRta.setPersonal_id(((Integer)session.get("personal_id")).toString());
							}
							if(session.get("string_extension") !=null) {
								sesionRta.setString_extension((String)session.get("string_extension"));
							}
							if(session.get("switch_call_id") !=null) {
								sesionRta.setSwitch_call_id(((String)session.get("switch_call_id")).toString());
							}
							if(session.get("switch_id") !=null) {
								sesionRta.setSwitch_id(((Integer)session.get("switch_id")).toString());
							}
							if(session.get("total_hold_time_in_seconds") !=null) {
								sesionRta.setTotal_hold_time_in_seconds(((Integer)session.get("total_hold_time_in_seconds")).toString());
							}
							if(session.get("wrapup_time_in_seconds") !=null) {
								sesionRta.setWrapup_time_in_seconds(((Integer)session.get("wrapup_time_in_seconds")).toString());
							}
							LOG.info(sesionRta.toString());
							respuesta.add(sesionRta);
						}
					}else {
						LOG.info("Lista no tenia SESIONES");	
					}				
					
				}else {
					LOG.info("Respuesta no tenia SESIONES");	
				}
			}else {
				LOG.info("No se devolvieron SESIONES");
			}
			
		} catch (Exception e) {
			LOG.error("Error obteniendo Sesiones" , e);
		}

		
		return respuesta;
	}
	
	/**
	 * Permite obtener la fecha de fin dado una fecha de inicio y unos segundos de duración.
	 * @return
	 */
	public String obtenerFechaFin(String fechaInicio, int segundos) {
		
		try {
			LOG.info("Fecha Inicio : " + fechaInicio);
			LOG.info("Segundos : " + segundos);
			
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", new Locale("es_CO"));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", new Locale("es_CO"));
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date date = format.parse(fechaInicio);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.SECOND, segundos);
			
			LOG.info(format.format(calendar.getTime()));
			return format.format(calendar.getTime());
		} catch (Exception e) {
			LOG.error("Error Obteniendo Fecha Final", e);
		}
		return "";
		
	}
	
	/**
	 * Permite mostrar los valores que retorna eñ servicio de Dynamic Query
	 * @param parametros
	 */
	private void imprimirAtributos(Map<String, Object> parametros) {
		if(parametros !=null  && !parametros.isEmpty()) {
			LOG.info("Existen llaves : " + parametros.size() );
			
			Set<String> llaves = parametros.keySet();
			for (String llave : llaves) {
				LOG.info("LLave : " + llave + " , valor : " + parametros.get(llave));
			}
		}else {
			LOG.info("NO Existen llaves");
		}
	}
	
	/**
	 * Genera el Request del Query REST
	 * @param llamada
	 * @return
	 */
	private RequestDynamicQuery obtenerRequest(LlamadaDTO llamada, Token token) {
		
		RequestDynamicQuery request = new RequestDynamicQuery();
		request.setUserID(token.getId());
		request.setConditionsString(QUERY_ANI + llamada.getNumeroTelefonoIncidente());
		
		request.setPeriod(obtenerPeriod(llamada));
		
		ArrayList<String> reqGroups = new ArrayList<String>();
		reqGroups.add(AUDIO_CH_NUM);
		reqGroups.add(PBX_LOGIN_ID);
		request.setRequestedGroups(reqGroups);
		
		request.setRequestedColumns(null);
		request.setCommandTimoutSeconds(COMMAND_TIMEOUT_SECONDS);
		
		return request;
	}
	
	/**
	 * Genera la información del período de consulta
	 * @param llamada
	 * @return
	 */
	private Period obtenerPeriod(LlamadaDTO llamada) {
		
		
		try {
			Period period = new Period();
			
	        GregorianCalendar calIni = new GregorianCalendar();
	        GregorianCalendar calFin = new GregorianCalendar();
	        
	        calIni.setTime(llamada.getFechaRegistro());
	        calFin.setTime(llamada.getFechaRegistro());
	        
	        calIni.add(Calendar.SECOND, CANTIDAD_SEGUNDOS_QUERY_INICIO);
	        calFin.add(Calendar.SECOND, CANTIDAD_SEGUNDOS_QUERY_DESPUES + (llamada.getDuracion() !=null ? llamada.getDuracion().intValue() : CANTIDAD_SEGUNDOS_QUERY_DESPUES_DEFAULT) );
	        
	        XMLGregorianCalendar calendarXMLIni = DatatypeFactory.newInstance().newXMLGregorianCalendar(calIni);
	        XMLGregorianCalendar calendarXMLFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(calFin);
			
	        period.setBeginPeriod(calendarXMLIni.toString());
	        period.setEndPeriod(calendarXMLFin.toString());

	        period.setTimeOfDateBegin(rellenarCeros(calendarXMLIni.getHour()) + ":" + rellenarCeros(calendarXMLIni.getMinute()) + ":" + rellenarCeros(calendarXMLIni.getSecond()) );
	        period.setTimeOfDateEnd(rellenarCeros(calendarXMLFin.getHour()) + ":" + rellenarCeros(calendarXMLFin.getMinute()) + ":" + rellenarCeros(calendarXMLFin.getSecond()) );
	        
			period.setType(PERIOD_TYPE);
			period.setDays("1");//TODO Validar

	//"BeginPeriod"
	//:
	//"2021-06-17T00:00:00"
	//,
	//"EndPeriod"
	//:
	//"2021-06-18T23:59:59"
	//,
	//"TimeOfDateBegin"
	//:
	//"00:00:00"
	//,
	//"TimeOfDateEnd"
	//:
	//"23:59:59"
			LOG.info("Datos Entrada Period : " + period.toString());
			return period;
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return null;

	}
	
	/**
	 * Si el número es menor a 10 concatena un 0 a la izquierda
	 * @param numero
	 * @return
	 */
	private String rellenarCeros(int numero) {
		String rta = "";
		if(numero < 10) {
			rta = rta + "0" + numero;
		}else {
			rta = rta + numero;
		}
		return rta;
	}

}
