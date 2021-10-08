package la.smartsoft.verint.integracion.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 * Clase que recibe la información necesario para realizar la autenticación en el Servicio JWT de Verint
 */
public class UsuarioToken {

	private @JsonProperty("user") String user;
	private @JsonProperty("password") String password;
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param usuario the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param contrasenia the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
