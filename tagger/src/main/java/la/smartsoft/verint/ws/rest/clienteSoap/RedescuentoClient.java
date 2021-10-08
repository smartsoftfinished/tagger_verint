package la.smartsoft.verint.ws.rest.clienteSoap;

public class RedescuentoClient {

//	private static final Logger LOG = Logger.getLogger(RedescuentoClient.class);
//
//	/**
//	 * Prepara la interfaz de conexión sobrescribiendo el endpoint generado por el contexto.
//	 * 
//	 * @param endPoint enlace en lo que se encuentra el servicio SOAP.
//	 * @return
//	 */
//	public RedescuentoDigitalSoapBean getB2ResdescuentoDigital(String endPoint) {
//
//		try {
//			URL url = new URL(endPoint + "?wsdl");
//			
//			RedescuentoDigitalService service = new RedescuentoDigitalService();
//			RedescuentoDigitalSoapBean gateway = service.getRedescuentoDigitalSoapBeanPort();
//			BindingProvider bindingProvider = (BindingProvider) gateway;
//			
//			String timeout = "600000";
//			
//			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url.toString());
//			bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", timeout);
//			bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", timeout);
//			
//			bindingProvider.getRequestContext().put("javax.xml.ws.client.connectionTimeout", timeout);
//			bindingProvider.getRequestContext().put("javax.xml.ws.client.receiveTimeout", timeout);
//			
//			LOG.info("Conectado a: "
//					+ bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
//
//			return gateway;
//		} catch (MalformedURLException e) {
//			LOG.error("Error:getB2ResdescuentoDigital: " + e);
//		}
//		return null;
//	}
//
//	/**
//	 * Método encargado de realizar el consumo SOAP a Banca2 (back) para grabar una
//	 * solicitud redescuento digital.
//	 * 
//	 * @param endPoint
//	 * @param solicitud
//	 * @return
//	 */
//	public RespuestaWS crearSolicitudCredito(String endPoint, SolicitudRedescuentoDigital solicitud) {
//
//		try {
//
//			RedescuentoDigitalSoapBean puerto = getB2ResdescuentoDigital(endPoint);
//			return puerto.crearSolicitudCredito(solicitud);
//
//		} catch (Exception e) {
//			LOG.error("Error:creando solicitud: " + e);
//			throw e;
//		}
//	}
//	
//	/**
//	 * Método encargado de realizar el consumo SOAP a Banca2 (back) para modificar una
//	 * solicitud redescuento digital.
//	 * 
//	 * @param endPoint
//	 * @param solicitud
//	 * @param idSolicitud
//	 * @return
//	 */
//	
//	public RespuestaWS modificarSolicitudCredito(String endPoint, SolicitudRedescuentoDigital solicitud, String idSolicitud) {
//		try {
//		
//			RedescuentoDigitalSoapBean puerto = getB2ResdescuentoDigital(endPoint);
//			return puerto.modificarSolicitudCredito(solicitud, idSolicitud);
//			
//		} catch (Exception e) {
//			LOG.error("Error:modificarSolicitudCredito: " + e);
//			throw e;
//		}
//	}
//	
//	/**
//	 * Método encargado de realizar el consumo SOAP a Banca2 (back) para aprobar una
//	 * solicitud redescuento digital.
//	 * 
//	 * @param endPoint
//	 * @param arg0
//	 * @return
//	 */
//	public RespuestaWS aprobarSolicitudCredito(String endPoint, String solicitud) {
//		try {
//		
//			RedescuentoDigitalSoapBean puerto = getB2ResdescuentoDigital(endPoint);
//			return puerto.aprobarSolicitudCredito(solicitud);
//			
//		} catch (Exception e) {
//			LOG.error("Error:aprobarSolicitudCredito: " + e);
//			throw e;
//		}
//	}
//
//	/**
//	 * Método encargado de realizar el consumo SOAP a Banca2 (back) para asignar una
//	 * solicitud redescuento digital.
//	 * 
//	 * @param endPoint
//	 * @param solicitud
//	 * @return
//	 */
//	public RespuestaWS asignarPagareDeceval(String endPoint, SolicitudAsignarPagDeceval solicitud) {
//		try {
//		
//			RedescuentoDigitalSoapBean puerto = getB2ResdescuentoDigital(endPoint);
//			return puerto.asignarPagareDeceval(solicitud);
//			
//		} catch (Exception e) {
//			LOG.error("Error:asignarPagareDeceval: " + e);
//			throw e;
//		}
//	}
	
}
