package la.smartsoft.verint.ws.rest.obj;

/**
 * @author Freddy Sabogal
 *
 */
public class PermisosRol {
	private String rol;
	private String jwt;
	private String fechaExpiracion;
	
	public PermisosRol() {
		
	}
	
	public PermisosRol(String rol, String jwt, String fechaExpiracion) {
		this.rol = rol;
		this.jwt = jwt;
		this.fechaExpiracion = fechaExpiracion;
	}
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	
}
