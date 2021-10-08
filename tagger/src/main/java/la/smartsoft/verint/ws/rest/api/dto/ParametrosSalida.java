package la.smartsoft.verint.ws.rest.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ParametrosSalida {
	
	Boolean exitoso;
	List<ErrorWS> errores;

	/**
	 * @return
	 */
	public List<ErrorWS> getErrores() {
		return errores;
	}

	/**
	 * @param errores
	 */
	public void setErrores(List<ErrorWS> errores) {
		this.errores = errores;
	}

	/**
	 * @return
	 */
	public Boolean getExitoso() {
		return exitoso;
	}

	/**
	 * @param exitoso
	 */
	public void setExitoso(Boolean exitoso) {
		this.exitoso = exitoso;
	}
	
	/**
	 * Permite agregar un error a la lista de errores
	 * @param error
	 */
	public void addError(ErrorWS error) {
		if(errores == null) {
			errores = new ArrayList<ErrorWS>();
		}
		errores.add(error);
	}

}
