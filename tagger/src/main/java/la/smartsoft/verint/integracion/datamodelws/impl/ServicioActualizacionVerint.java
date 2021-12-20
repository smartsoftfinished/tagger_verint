package la.smartsoft.verint.integracion.datamodelws.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import co.com.verint.ws.client.ArrayOfstring;
import co.com.verint.ws.client.DataModelWCF;
import co.com.verint.ws.client.IDataModelWS;
import co.com.verint.ws.client.ObjectFactory;
import co.com.verint.ws.client.TransactionMode;
import la.smartsoft.verint.integracion.daswebapi.dto.SessionVerint;
import la.smartsoft.verint.integracion.datamodelws.IActualizacionVerint;
import la.smartsoft.verint.integracion.db.verint.dto.AuditoriaTaggingDTO;
import la.smartsoft.verint.integracion.db.verint.impl.ServicioAuditoria;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

/**
 * @author pedro Implementación del servicio de tagging el cual consiste en
 *         asignarle un número de incidente a las llamadas
 */
public class ServicioActualizacionVerint extends ConfiguracionApi implements IActualizacionVerint {

	public static final String WEB_SERVICE_DATA_MODEL = "WEB_SERVICE_DATA_MODEL";

	// CONSTANTES PARA EL SERVICIO
	public static final String WS_DATOS_EXTERNAL_SOURCE = "ExternalSource";
	public static final String WS_DATOS_OPERATION = "Session Insert";
	public static final String APPEND_TIME = "0000-05:00";

	private static final Logger LOG = Logger.getLogger(ServicioActualizacionVerint.class);

	@Override
	public boolean actualizarVerint(List<SessionVerint> sesiones) {

		LOG.info("Inicio actualizarVerint");
		ServicioAuditoria servicioAuditoria = new ServicioAuditoria();
		try {
			LOG.info("Cantidad Sesiones : " + (sesiones != null ? sesiones.size() : " VACIAS"));
			if (sesiones == null || sesiones.size() == 0)
				return true;
			IDataModelWS serviceVerint = getDataModelWCFPort(ENDPOINT_VERINT_SOAP_TAGGING, 60000);

			SessionVerint sesion = sesiones.get(0);
			// SimpleDateFormat origen = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			// SimpleDateFormat objetivo = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS0000XXX");

			LOG.info("Inicia Tagueo: ANI: " + sesion.getAni() + ", Incidente : " + sesion.getCd2());
			LOG.info("Datos: " + sesion.toString());

			try {
				ObjectFactory objectFactory = new ObjectFactory();
				/*
				 * //Se crea un ArrayOfEnvelope Envelope envelope =
				 * objectFactory.createEnvelope();
				 * 
				 * //Se instancia el Object Factory para los objetos del Servicio Header header
				 * = objectFactory.createHeader();
				 * 
				 * //HEADER //SOURCE JAXBElement<String> source =
				 * objectFactory.createHeaderSource(WS_DATOS_EXTERNAL_SOURCE);
				 * header.setSource(source);
				 * 
				 * //OPERATION JAXBElement<String> operation =
				 * objectFactory.createHeaderOperation(WS_DATOS_OPERATION);
				 * header.setOperation(operation);
				 * 
				 * //TIME GregorianCalendar cal = new GregorianCalendar(); cal.setTime(new
				 * Date()); XMLGregorianCalendar calendar =
				 * DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
				 * header.setTime(calendar);
				 * 
				 * //JAXBElement<T> JAXBElement<Header> headerXML=
				 * objectFactory.createHeader(header); envelope.setHeader(headerXML);
				 * 
				 * //EXTENSION ArrayOfExtension arrayExtension
				 * =objectFactory.createArrayOfExtension();
				 * 
				 * //TelephonyContactExtension TelephonyContactExtension telephonyContact =
				 * objectFactory.createTelephonyContactExtension();
				 * telephonyContact.setID(Long.valueOf(sesion.getCall_id()));
				 * //telephonyContact.setExceptionReason(sesion.getException_reason());
				 * arrayExtension.getExtension().add(telephonyContact);
				 * 
				 * //UserExtension UserExtension userExtension =
				 * objectFactory.createUserExtension();
				 * userExtension.setID(Long.valueOf(sesion.getPersonal_id()));
				 * JAXBElement<String> stringExtension =
				 * objectFactory.createString(sesion.getString_extension());
				 * userExtension.setStringExtension(stringExtension); JAXBElement<String>
				 * personalId = objectFactory.createString(sesion.getPersonal_id());
				 * userExtension.setPbxLoginName(personalId);
				 * arrayExtension.getExtension().add(userExtension);
				 * 
				 * //TelephonySessionExtension TelephonySessionExtension telSesExtension =
				 * objectFactory.createTelephonySessionExtension(); JAXBElement<String> dnis =
				 * objectFactory.createString(sesion.getDnis()); telSesExtension.setDNIS(dnis);
				 * //telSesExtension.setHoldDuration(dnis); //No hay TotalHoldTime
				 * JAXBElement<Long> longSwitchId = objectFactory.createLong(Long.valueOf(
				 * sesion.getSwitch_id()) ); telSesExtension.setSwitchID(longSwitchId);
				 * JAXBElement<String> ani = objectFactory.createString(sesion.getAni());
				 * telSesExtension.setANI(ani); //telSesExtension.setHolds(ani);
				 * //telSesExtension.setDirection(ani); JAXBElement<String> switchCallID =
				 * objectFactory.createString(sesion.getSwitch_call_id());
				 * telSesExtension.setSwitchCallID(switchCallID);
				 * arrayExtension.getExtension().add(telSesExtension);
				 * 
				 * //PrivateDataExtension PrivateDataExtension priDatExtension =
				 * objectFactory.createPrivateDataExtension(); JAXBElement<String> p17 =
				 * objectFactory.createString(sesion.getCd17()); priDatExtension.setP17(p17);
				 * JAXBElement<String> p2 = objectFactory.createString(sesion.getCd2());
				 * priDatExtension.setP2(p2);
				 * arrayExtension.getExtension().add(priDatExtension);
				 * 
				 * //PrivateDataExtension AudioAcquisitionExtension audAcqExtension =
				 * objectFactory.createAudioAcquisitionExtension(); //JAXBElement<String> p17 =
				 * objectFactory.createString(sesion.getCd17()); audAcqExtension.setEnd(null);
				 * JAXBElement<String> mediaTypeBitMask =
				 * objectFactory.createString(sesion.getMedia_type_bit_mask());
				 * audAcqExtension.setMediaTypeBitMask(mediaTypeBitMask);
				 * audAcqExtension.setStart(null); //audAcqExtension.setWrapUpTIme(null);
				 * audAcqExtension.setModule(Long.valueOf(sesion.getAudio_module_num()));
				 * JAXBElement<String> interactionType =
				 * objectFactory.createString(sesion.getInteraction_type());
				 * audAcqExtension.setInteractionType(interactionType);
				 * //audAcqExtension.setTimeOffset();
				 * audAcqExtension.setChannel(Long.valueOf(sesion.getAudio_ch_num()));
				 * arrayExtension.getExtension().add(audAcqExtension);
				 * 
				 * Data data = objectFactory.createData(); JAXBElement<ArrayOfExtension>
				 * arrayExtensionJAX =objectFactory.createArrayOfExtension(arrayExtension);
				 * 
				 * data.setExtensions(arrayExtensionJAX);
				 * //data.getExtensions().setValue(arrayExtension);
				 * 
				 * JAXBElement<Data> dataXML = objectFactory.createData(data);
				 * envelope.setData(dataXML);
				 * 
				 * ArrayOfEnvelope arrayOfEnvolve = objectFactory.createArrayOfEnvelope();
				 * arrayOfEnvolve.getEnvelope().add(envelope);
				 * 
				 * //serviceVerint.updateTyped(arrayOfEnvolve);
				 * //serviceVerint.processTyped(arrayOfEnvolve);
				 */

				// XML Request a actualizar
				StringBuilder sb = new StringBuilder();

				sb.append(
						"<Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
				sb.append("<Header>");
				sb.append("		<Source>ExternalSource</Source>"); // <!--VAM-->
				sb.append("		<Operation>Session Insert</Operation>"); // <!--Session Update-->
				sb.append("		<Time>").append(getTimeString(new Date())).append("</Time>"); // <!--Time the
																								// request is
																								// made--> //TODO
				sb.append("		<IsDirty>true</IsDirty>");
				sb.append("</Header>");
				sb.append("<Data>");
				sb.append("<Extensions>");

				// TelephonyContact
				sb.append("<Extension name=\"TelephonyContact\">");
				if (sesion.getException_reason() != null && !"".equals(sesion.getException_reason())
						&& !"null".equals(sesion.getException_reason())) {
					sb.append("		<ExceptionReason>").append(sesion.getException_reason())
							.append("</ExceptionReason>"); // <!--exception_reason-->
				}
				if (sesion.getCall_id() != null && !"".equals(sesion.getCall_id())
						&& !"null".equals(sesion.getCall_id())) {
					sb.append("		<ID>").append(sesion.getCall_id()).append("</ID>"); // <!--call_ID-->
				}
				sb.append("</Extension>");

				// User
				sb.append("<Extension name=\"User\">");
				if (sesion.getPersonal_id() != null && !"".equals(sesion.getPersonal_id())
						&& !"null".equals(sesion.getPersonal_id())) {
					sb.append("		<ID>").append(sesion.getPersonal_id()).append("</ID>"); // <!--personal_id-->
				}
				if (sesion.getString_extension() != null && !"".equals(sesion.getString_extension())
						&& !"null".equals(sesion.getString_extension())) {
					sb.append("		<StringExtension>").append(sesion.getString_extension())
							.append("</StringExtension>"); // <!--string_extension-->
				}
				if (sesion.getPbx_login_id() != null && !"".equals(sesion.getPbx_login_id())
						&& !"null".equals(sesion.getPbx_login_id())) {
					sb.append("		<PbxLoginName>").append(sesion.getPbx_login_id()).append("</PbxLoginName>"); // <!--pbx_login_id-->
				}
				sb.append("</Extension>");

				// TelephonySession
				sb.append("<Extension name=\"TelephonySession\">");
				if (sesion.getDnis() != null && !"".equals(sesion.getDnis()) && !"null".equals(sesion.getDnis())) {
					sb.append("		<DNIS>").append(sesion.getDnis()).append("</DNIS>"); // <!--DNIS-->
				}
				if (sesion.getTotal_hold_time_in_seconds() != null && !"".equals(sesion.getTotal_hold_time_in_seconds())
						&& !"null".equals(sesion.getTotal_hold_time_in_seconds())) {
					sb.append("		<HoldDuration>").append(sesion.getTotal_hold_time_in_seconds())
							.append("</HoldDuration>"); // <!--total_hold_time_in_seconds-->
				}
				if (sesion.getSwitch_id() != null && !"".equals(sesion.getSwitch_id())
						&& !"null".equals(sesion.getSwitch_id())) {
					sb.append("		<SwitchID>").append(sesion.getSwitch_id()).append("</SwitchID>"); // <!--Switch_id-->
				}
				if (sesion.getAni() != null && !"".equals(sesion.getAni()) && !"null".equals(sesion.getAni())) {
					sb.append("		<ANI>").append(sesion.getAni()).append("</ANI>"); // <!--ANI-->
				}
				if (sesion.getNumber_of_holds() != null && !"".equals(sesion.getNumber_of_holds())
						&& !"null".equals(sesion.getNumber_of_holds())) {
					sb.append("		<Holds>").append(sesion.getNumber_of_holds()).append("</Holds>"); // <!--number_of_holds-->
				}
				if (sesion.getDirection() != null && !"".equals(sesion.getDirection())
						&& !"null".equals(sesion.getDirection())) {
					sb.append("		<Direction>").append(sesion.getDirection()).append("</Direction>"); // <!--direction-->
				}
				if (sesion.getSwitch_call_id() != null && !"".equals(sesion.getSwitch_call_id())
						&& !"null".equals(sesion.getSwitch_call_id())) {
					sb.append("		<SwitchCallID>").append(sesion.getSwitch_call_id()).append("</SwitchCallID>"); // <!--switch_call_id-->
				}
				sb.append("</Extension>");

				// PrivateData
				sb.append("<Extension name=\"PrivateData\">");
				if (sesion.getCd17() != null && !"".equals(sesion.getCd17()) && !"null".equals(sesion.getCd17())) {
					sb.append("		<P17>").append(sesion.getCd17()).append("</P17>"); // <!--CD17-->
				}
				if (sesion.getCd2() != null && !"".equals(sesion.getCd2()) && !"null".equals(sesion.getCd2())) {
					sb.append("		<P2>").append(sesion.getCd2()).append("</P2>"); // <!--CD17-->
				}
				sb.append("</Extension>");

				// AudioAcquisition
				sb.append("<Extension name=\"AudioAcquisition\">");
				if (sesion.getAudio_end_time() != null && !"".equals(sesion.getAudio_end_time())
						&& !"null".equals(sesion.getAudio_end_time())) {
					String horaFin = sesion.getAudio_end_time() + (sesion.getAudio_end_time().length() == 21 ? "00"
							: (sesion.getAudio_end_time().length() == 22 ? "0" : "")) + APPEND_TIME;
					sb.append("		<End>").append(horaFin).append("</End>"); // <!--audio_end_time-->
				}
				// sb.append("
				// <End>").append("2021-09-01T12:41:01.56").append(APPEND_TIME).append("</End>");
				if (sesion.getMedia_type_bit_mask() != null && !"".equals(sesion.getMedia_type_bit_mask())
						&& !"null".equals(sesion.getMedia_type_bit_mask())) {
					sb.append("		<MediaTypeBitMask>").append(sesion.getMedia_type_bit_mask())
							.append("</MediaTypeBitMask>"); // <!--media_type_bit_mask-->
				}
				if (sesion.getAUDIO_START_TIME() != null && !"".equals(sesion.getAUDIO_START_TIME())
						&& !"null".equals(sesion.getAUDIO_START_TIME())) {
					String hora = sesion.getAUDIO_START_TIME() + (sesion.getAUDIO_START_TIME().length() == 21 ? "00"
							: (sesion.getAUDIO_START_TIME().length() == 22 ? "0" : "")) + APPEND_TIME;
					sb.append("		<Start>").append(hora).append("</Start>"); // <!--audio_start_time-->
				}
				if (sesion.getWrapup_time_in_seconds() != null && !"".equals(sesion.getWrapup_time_in_seconds())
						&& !"null".equals(sesion.getWrapup_time_in_seconds())) {
					sb.append("		<WrapUpTIme>").append(sesion.getWrapup_time_in_seconds()).append("</WrapUpTIme>"); // <!--wrapup_time_in_seconds-->
				}
				if (sesion.getAudio_module_num() != null && !"".equals(sesion.getAudio_module_num())
						&& !"null".equals(sesion.getAudio_module_num())) {
					sb.append("		<Module>").append(sesion.getAudio_module_num()).append("</Module>"); // <!--audio_module_num-->
				}
				if (sesion.getInteraction_type() != null && !"".equals(sesion.getInteraction_type())
						&& !"null".equals(sesion.getInteraction_type())) {
					sb.append("		<InteractionType>").append(sesion.getInteraction_type())
							.append("</InteractionType>"); // <!--interaction_type-->
				}
				if (sesion.getTotal_hold_time_in_seconds() != null && !"".equals(sesion.getTotal_hold_time_in_seconds())
						&& !"null".equals(sesion.getTotal_hold_time_in_seconds())) {
					sb.append("		<TimeOffset>").append(sesion.getTotal_hold_time_in_seconds())
							.append("</TimeOffset>"); // <!-- difference between local_start and audio_start -->
														// TODO
				} else {
					LOG.info("Entro ELSE TimeOffset");
					sb.append("		<TimeOffset>").append("0").append("</TimeOffset>"); // <!--audio_end_time-->
				}
				if (sesion.getAudio_ch_num() != null && !"".equals(sesion.getAudio_ch_num())
						&& !"null".equals(sesion.getAudio_ch_num())) {
					sb.append("		<Channel>").append(sesion.getAudio_ch_num()).append("</Channel>"); // <!--audio_ch_num-->
				}
				sb.append("</Extension>");
				sb.append("</Extensions>");
				sb.append("</Data>");
				sb.append("</Envelope>");

				// LOG.info(sb.toString());

				ArrayOfstring array = objectFactory.createArrayOfstring();
				array.getString().add(sb.toString());
				serviceVerint.processEx(array, TransactionMode.NONE);
				servicioAuditoria.actualizarAuditoria(new AuditoriaTaggingDTO(null, sesion.getCd2(), null, "PROCESADO",
						null, null, null, null, null));
				LOG.info("Termina Correctamente Tagueo: ANI: " + sesion.getAni() + ", Incidente : " + sesion.getCd2());
			} catch (Exception e) {
				LOG.error("Error En Servicio Tagging " + sesion);
				servicioAuditoria.actualizarAuditoria(new AuditoriaTaggingDTO(null, sesion.getCd2(), null,
						"ERROR TAGGER", e.getMessage(), null, null, null, null));
				LOG.error(e.getMessage());
				LOG.error(e);
				e.printStackTrace();
			}

			// }//Fin for
		} catch (Exception e) {
			LOG.error("Error En Servicio Tagging 2");
			SessionVerint sesion = sesiones.get(0);
			servicioAuditoria.actualizarAuditoria(new AuditoriaTaggingDTO(null, sesion.getCd2(), null,
					"ERROR TAGGER", e.getMessage(), null, null, null, null));
			LOG.error(e);
		}

		LOG.info("Fin actualizarVerint");

		return true;

	}

	private String getTimeString(Date time) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", new Locale("es_CO"));
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		return format.format(time);

	}

	/**
	 * Retorna una instancia del Servicio de Actualización de Verint IDataModelWS
	 * 
	 * @param endPoint
	 * @param timeoutRequest
	 * @return
	 * @throws Exception
	 */
	public IDataModelWS getDataModelWCFPort(String endPoint, Integer timeoutRequest) throws Exception {

		try {
			IDataModelWS gatewayPort = (IDataModelWS) ServiceLocator.getService(WEB_SERVICE_DATA_MODEL);

			// Si no se tiene el servicio en el cache, se instancia y se agrega
			if (gatewayPort == null) {
				DataModelWCF tx = new DataModelWCF();
				gatewayPort = tx.getBasicHttpBindingIDataModelWS();

				BindingProvider bindingProvider = (BindingProvider) gatewayPort;
				// bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				// "http://172.31.0.29:8780/BE1-WS/services");
				bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
				if (bindingProvider != null) {
					LOG.info("Conectado a: "
							+ bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
				}

				// Se define el timeout
				definirTimeout(bindingProvider, timeoutRequest, timeoutRequest);

				ServiceLocator.addService(WEB_SERVICE_DATA_MODEL, gatewayPort);
			}

			// Definición de propiedades del endpoint y timeout
			if (gatewayPort != null) {
				BindingProvider bindingProvider = (BindingProvider) gatewayPort;
				bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
				if (bindingProvider != null) {
					LOG.info("Conectado a: "
							+ bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
				}
				// Se define el timeout
				definirTimeout(bindingProvider, timeoutRequest, timeoutRequest);
			}

			return gatewayPort;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Método que permite definir el timeout para desconectar la invocación al
	 * servicio
	 * 
	 * @param bindingProvider
	 * @param timeout
	 */
	private void definirTimeout(BindingProvider bindingProvider, int connectTimeout, int timeout) {
		if (bindingProvider != null) {
			bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", connectTimeout);
			bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", timeout);
			bindingProvider.getRequestContext().put("com.sun.xml.ws.developer.JAXWSProperties.CONNECT_TIMEOUT",
					connectTimeout);
			bindingProvider.getRequestContext().put("com.sun.xml.ws.connect.timeout", connectTimeout);
			bindingProvider.getRequestContext().put("com.sun.xml.ws.internal.connect.timeout", connectTimeout);
			bindingProvider.getRequestContext().put("com.sun.xml.ws.request.timeout", timeout);
			bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", timeout);
		}
	}

	public static void main(String[] args) {
		try {
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", new Locale("es_CO"));
			format.setTimeZone(TimeZone.getTimeZone("UTC"));

			System.out.println(format.format(d));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
