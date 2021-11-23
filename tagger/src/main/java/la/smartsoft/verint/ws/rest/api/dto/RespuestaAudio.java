package la.smartsoft.verint.ws.rest.api.dto;

import java.util.List;
import java.util.Map;

public class RespuestaAudio {

	Boolean exitoso;
	ErrorWS error;
	List<Map<String, String>> audios;

	/**
	 * @return
	 */
	public List<Map<String, String>> getAudios() {
		return audios;
	}

	/**
	 * @param audios
	 */
	public void setAudios(List<Map<String, String>> audios) {
		this.audios = audios;
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
	 * @return
	 */
	public ErrorWS getError() {
		return error;
	}

	/**
	 * @param error
	 */
	public void setError(ErrorWS error) {
		this.error = error;
	}

	public Boolean isExitoso() {
		return this.exitoso;
	}

}
