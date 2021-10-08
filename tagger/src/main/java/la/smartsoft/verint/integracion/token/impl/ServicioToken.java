package la.smartsoft.verint.integracion.token.impl;

import java.util.LinkedHashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.token.IToken;
import la.smartsoft.verint.integracion.token.dto.AuthToken;
import la.smartsoft.verint.integracion.token.dto.Token;
import la.smartsoft.verint.integracion.token.dto.UsuarioToken;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;

/**
 * @author pedro
 *	Clase para el manejo de generación del Token Verint
 */
public class ServicioToken extends ConfiguracionApi implements IToken {
	
	private static final Logger LOG = Logger.getLogger(ServicioToken.class);
	private static final String ID = "id";
	private static final String TOKEN = "token";
	private static final String EXTENDED_SESSION = "extendSession";
	private static final String AUTH_TOKEN = "AuthToken";	

	@Override
	public Token validarUsuario(UsuarioToken usuarioToken) {
		
		//Consulta Real
		try {
			//Construimos request a servicio REST.
			LOG.info("Inicia Servicio Consulta Token");
			LOG.info("Endpoint Token:" + ENDPOINT_VERINT_REST_TOKEN);
			LOG.info("Endpoint Token: user: " + usuarioToken.getUser() + " , password: " + usuarioToken.getPassword());
			Client clienteWeb = ClientBuilder.newClient();
			WebTarget clientTarget = clienteWeb.target(ENDPOINT_VERINT_REST_TOKEN);
			
			//Consumimos servicio REST
			Invocation.Builder invocationBuilder = clientTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(usuarioToken, MediaType.APPLICATION_JSON));
			
			//Status del Consumo
			int status = response.getStatus();
			LOG.info("Estatus: " + status);
			
			//Obtenemos respuesta de la API.
//			if (Status.OK.getStatusCode() == status) {
//
//				//La respuesta es un Mapa
//				LinkedHashMap<String, LinkedHashMap<String, String>> map =  response.readEntity(LinkedHashMap.class);
//				if(map !=null && !map.isEmpty()) {
//					LinkedHashMap<String, String> datosToken =  map.get(AUTH_TOKEN);
//					
//					if(datosToken !=null) {
//						Token token = new Token();
//						token.setId(datosToken.get(ID));
//						token.setToken(datosToken.get(TOKEN));
//						token.setExtendSession(datosToken.get(EXTENDED_SESSION));
//						LOG.info("Datos Token: UserId: " + token.getId() + " , TOKEN: " + token.getToken());
//						LOG.info("Fin Servicio Consulta Token");
//						return token;						
//					}else {
//						LOG.info("NO SE OBTUBO TOKEN 1");
//					}
//					
//				}
//				LOG.info("NO SE OBTUBO TOKEN 2");
//			}
			
			if (Status.OK.getStatusCode() == status) {
				LOG.info("Estatus OK");
				AuthToken authToken = response.readEntity(AuthToken.class);
				LOG.info("RTA TOKEN: " + (authToken!=null? authToken.toString(): "NULA"));
				if(authToken !=null && authToken.getAuthToken() !=null) {
					return authToken.getAuthToken();
				}
			}
			
			LOG.info("NO SE OBTUBO TOKEN 3");
			
		} catch (Exception e) {
			LOG.error("Error Servicio Consulta Token");
			LOG.error(e);
		}
		
		LOG.info("NO SE OBTUBO TOKEN 4");
		LOG.info("Fin Servicio Consulta Token");
		return null;
	}
	
	/**
	 * @param usuarioToken
	 * @return
	 */
	public Token validarUsuarioDummy(UsuarioToken usuarioToken) {
		
		//Consulta para Pruebas
		Token token = new Token();
		token.setId("1251");
		token.setToken("VU95aR42X7w3IsYa");
		
		return token;
	}

}
