package la.smartsoft.verint.ws.rest.obj;

/**
 * Request del API de validación para cruzar un JWT con unos parámetros del
 * servicio. Usar este request para APIs que funcionen publicamente, o que 
 * no tienen conexión a base de datos.
 * 
 * @author idiaz
 *
 */
public class RequestAplicacion {

	private String codigoOpcion;
	private String codigoApp;
	
	public RequestAplicacion(String codigoOpcion, String codigoApp) {
		this.codigoOpcion = codigoOpcion;
		this.codigoApp = codigoApp;
	}

	public String getCodigoOpcion() {
		return codigoOpcion;
	}

	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}

	public String getCodigoApp() {
		return codigoApp;
	}

	public void setCodigoApp(String codigoApp) {
		this.codigoApp = codigoApp;
	}

}
