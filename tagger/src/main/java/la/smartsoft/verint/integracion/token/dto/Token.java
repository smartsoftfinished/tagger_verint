package la.smartsoft.verint.integracion.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 * Clase que permite transportar la información del Token
 */
public class Token {
	
	private @JsonProperty("id") String id;
	private @JsonProperty("token") String token;
	private @JsonProperty("extendSession") String extendSession;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the extendSession
	 */
	public String getExtendSession() {
		return extendSession;
	}
	/**
	 * @param extendSession the extendSession to set
	 */
	public void setExtendSession(String extendSession) {
		this.extendSession = extendSession;
	}
	
	@Override
	public String toString() {
		return "[id: " + id +
				", token: " + token +
				", extendSession: " + extendSession + "]";	
	}
	
}
