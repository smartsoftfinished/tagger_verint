package la.smartsoft.verint.integracion.audiomedia.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import co.com.verint.audiomedia.ws.client.ArrayOfMediaDescription;
import co.com.verint.audiomedia.ws.client.ArrayOfSessionDetails;
import co.com.verint.audiomedia.ws.client.ArrayOfSessionMedia;
import co.com.verint.audiomedia.ws.client.ArrayOfString;
import co.com.verint.audiomedia.ws.client.MediaDescription;
import co.com.verint.audiomedia.ws.client.MediaItem;
import co.com.verint.audiomedia.ws.client.MediaModifiers;
import co.com.verint.audiomedia.ws.client.MediaUtilizationAPIWebService;
import co.com.verint.audiomedia.ws.client.MediaUtilizationAPIWebServiceSoap;
import co.com.verint.audiomedia.ws.client.ObjectFactory;
import co.com.verint.audiomedia.ws.client.SessionDetails;
import co.com.verint.audiomedia.ws.client.SessionMedia;
import la.smartsoft.verint.integracion.audiomedia.IConsultarAudio;
import la.smartsoft.verint.integracion.datamodelws.impl.ServiceLocator;
import la.smartsoft.verint.integracion.token.IToken;
import la.smartsoft.verint.integracion.token.dto.UsuarioToken;
import la.smartsoft.verint.integracion.token.impl.ServicioToken;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

/**
 * @author pedro Implementacion del servicio de tagging el cual consiste en
 *         asignarle un numero de incidente a las llamadas
 */
public class ServicioConsultaAudioMedia extends ConfiguracionApi implements IConsultarAudio {

	public static final String WEB_SERVICE_AUDIO_MEDIA = "WEB_SERVICE_AUDIO_MEDIA";

	// CONSTANTES PARA EL SERVICIO
	private static final Logger LOG = Logger.getLogger(ServicioConsultaAudioMedia.class);

	@Override
	public List<String> consultarAudio(Integer siteId, Integer sessionId) {

		LOG.info("Inicio consultarAudio");
		List<String> audios = new ArrayList<String>();

		try {
			IToken tokenService = new ServicioToken();
			UsuarioToken usuarioToken = new UsuarioToken();
			usuarioToken.setUser(TOKEN_USUARIO);
			usuarioToken.setPassword(TOKEN_CONTRASENIA);
			TOKEN = tokenService.validarUsuario(usuarioToken);

			MediaUtilizationAPIWebServiceSoap serviceVerint = getAudioMediaPort(ENDPOINT_VERINT_SOAP_AUDIO, 60000,
					TOKEN.getToken());
			LOG.info("siteId: " + siteId);
			LOG.info("sessionId: " + sessionId);

			if (siteId == null || sessionId == null) {
				return audios;
			}

			ObjectFactory objectFactory = new ObjectFactory();

			// Se crea el detail
			ArrayOfSessionDetails details = objectFactory.createArrayOfSessionDetails();
			SessionDetails sesionDetail = objectFactory.createSessionDetails();
			sesionDetail.setSiteId(siteId);
			sesionDetail.setSessionId(sessionId);
			details.getSessionDetails().add(sesionDetail);

			// Se crea el mediadescriptor
			ArrayOfMediaDescription mediaDescriptions = objectFactory.createArrayOfMediaDescription();
			MediaDescription media = objectFactory.createAudioMediaDescription();
			media.setFormat("portable");
			media.setDecrypt(true);
			mediaDescriptions.getMediaDescription().add(media);

			// Se crea los modifiers
			MediaModifiers modifiers = objectFactory.createMediaModifiers();

			ArrayOfString arrayPlayBack = objectFactory.createArrayOfString();
			arrayPlayBack.getString().add(sessionId.toString());
			modifiers.setPlaybackSiteIds(arrayPlayBack);

			ArrayOfSessionMedia sessionMedia = serviceVerint.getMediaFilesWithSessionDetails(details, mediaDescriptions,
					modifiers);
			LOG.info("SESION MEDIA : " + (sessionMedia != null && sessionMedia.getSessionMedia() != null
					? sessionMedia.getSessionMedia().size() + ""
					: "Vacia"));
			for (SessionMedia sesionMedia : sessionMedia.getSessionMedia()) {
				if (sesionMedia.getMediaItems() != null && sesionMedia.getMediaItems().getMediaItem() != null) {
					for (MediaItem item : sesionMedia.getMediaItems().getMediaItem()) {
						audios.add(item.getHttpPath());
					}
				}
			}

			LOG.info("Termina Correctamente consultarAudio: siteId: " + siteId + ", sessionId : " + sessionId);

		} catch (Exception e) {
			LOG.error("Termina con error consultarAudio: siteId: " + siteId + ", sessionId : " + sessionId);
			LOG.error(e.getMessage());
			LOG.error(e);
			e.printStackTrace();
			throw new RuntimeException("Error consultando audios : " + e.getMessage());
		}

		LOG.info("Fin consultarAudio");
		return audios;
	}

	/**
	 * Retorna una instancia del Servicio de AudioMedia
	 * MediaUtilizationAPIWebServiceSoap
	 * 
	 * @param endPoint
	 * @param timeoutRequest
	 * @return
	 * @throws Exception
	 */
	public MediaUtilizationAPIWebServiceSoap getAudioMediaPort(String endPoint, Integer timeoutRequest, String token)
			throws Exception {

		try {
			MediaUtilizationAPIWebServiceSoap gatewayPort = (MediaUtilizationAPIWebServiceSoap) ServiceLocator
					.getService(WEB_SERVICE_AUDIO_MEDIA);

			// Si no se tiene el servicio en el cache, se instancia y se agrega
			if (gatewayPort == null) {
				MediaUtilizationAPIWebService tx = new MediaUtilizationAPIWebService();
				gatewayPort = tx.getMediaUtilizationAPIWebServiceSoap();

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

				ServiceLocator.addService(WEB_SERVICE_AUDIO_MEDIA, gatewayPort);
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

				// Map<String, Object> headers = new HashMap<String, Object>();
				// headers.put("Impact360AuthToken", token);

				// Se crea un header con el token en Impact360AuthToken
				Map<String, List<String>> headers = new HashMap<String, List<String>>();
				headers.put("Impact360AuthToken", Collections.singletonList(token));

				LOG.info("Antes de poner Token");
				// Se setea el token
				bindingProvider.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);
				LOG.info("Despues de poner Token");

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
