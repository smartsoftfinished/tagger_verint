package la.smartsoft.verint.ws.rest.jwt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import la.smartsoft.verint.ws.rest.obj.RequestAplicacion;
import la.smartsoft.verint.ws.rest.obj.RespuestaPeticion;


public class ConsumoWebservice {
	
	private static final Logger logger = Logger.getLogger(ConsumoWebservice.class);
	
	/* Llaves para clasificar los parámetros con el que se creó el JWT*/
	private static final String KEY_CLAIMS_NIT = "nit";


	/**
	 * Método encargado de consumir el API de seguridad publico
	 * 
	 * @param url
	 * @param path
	 * @param headers
	 * @param aplicacion
	 * @return
	 */
	public RespuestaPeticion validarJWT(String url, HttpServletRequest headers, RequestAplicacion aplicacion) {
		
		try {
			//Construimos request a servicio REST.
			Client clienteWeb = ClientBuilder.newClient();
			WebTarget clientTarget = clienteWeb.target(url);
			
			Invocation.Builder invocationBuilder = clientTarget.request(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.AUTHORIZATION, headers.getHeader("Authorization"));
			
			//Consumimos servicio REST
			Response response = invocationBuilder.post(Entity.entity(aplicacion, MediaType.APPLICATION_JSON));
			
			int status = response.getStatus();
			logger.info("Estatus: " + status);
			
			//Obtenemos respuesta de la API.
			if (Status.OK.getStatusCode() == status) {
				return response.readEntity(RespuestaPeticion.class);
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	
	public Long getNitFromJwt(HttpServletRequest headers) throws Exception  {

		String jwtEncoded = headers.getHeader("Authorization");
		
		if (jwtEncoded == null || jwtEncoded.isEmpty()) {
			throw new Exception("El JWT es nulo o vacío.");
		}
		
		jwtEncoded = jwtEncoded.replace("Bearer", "").trim();

		logger.info("------------ Decode JWT ------------");
		String[] splitJwt = jwtEncoded.split("\\.");

		if (splitJwt.length >= 2) {

			String base64EncodedHeader = splitJwt[0];
			String base64EncodedBody = splitJwt[1];
			Base64 base64Url = new Base64(true);

			logger.info("~~~~~~~~~ JWT Header ~~~~~~~");
			String header = new String(base64Url.decode(base64EncodedHeader));
			logger.info("JWT Header : " + header);

			logger.info("~~~~~~~~~ JWT Body ~~~~~~~");
			String body = new String(base64Url.decode(base64EncodedBody));
			logger.info("JWT Body : " + body);

			ObjectMapper mapper = new ObjectMapper();
			Map mapBody = mapper.readValue(body, HashMap.class);

			if (mapBody != null) {
				String strNit = (String) mapBody.get(KEY_CLAIMS_NIT);
				return Long.parseLong(strNit);
			}
		} else {
			throw new Exception("El JWT no tiene un formato correcto.");
		}
		
		//No se pudo obtener el NIT del JWT.
		return null;
	}

}
