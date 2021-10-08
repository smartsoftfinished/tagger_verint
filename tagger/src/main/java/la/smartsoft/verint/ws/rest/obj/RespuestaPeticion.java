package la.smartsoft.verint.ws.rest.obj;

import java.util.List;

/**
 * @author Freddy Sabogal
 *
 */
public class RespuestaPeticion {
	
	private List<PermisosRol> permisosRol;
	private String error;
	
	public List<PermisosRol> getPermisosRol() {
		return permisosRol;
	}
	public void setPermisosRol(List<PermisosRol> permisosRol) {
		this.permisosRol = permisosRol;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
