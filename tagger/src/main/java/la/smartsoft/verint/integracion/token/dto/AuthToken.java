package la.smartsoft.verint.integracion.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que retorna un objeto con el Token
 * @author pedro
 *
 */
public class AuthToken {
	
	private @JsonProperty("AuthToken") Token AuthToken;

	/**
	 * @return the authToken
	 */
	public Token getAuthToken() {
		return AuthToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(Token authToken) {
		this.AuthToken = authToken;
	}

	@Override
	public String toString() {
		return "[AuthToken: " + (AuthToken !=null ? AuthToken.toString(): "NULL")  +"]";	
	}

}
