package la.smartsoft.verint.ws.rest.api.dto;

import java.util.List;

public class RespuestaAudio {
	
	Boolean exitoso;
	ErrorWS error;
	List<String> audios;

	/**
	 * @return
	 */
	public List<String> getAudios() {
		return audios;
	}

	/**
	 * @param audios
	 */
	public void setAudios(List<String> audios) {
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

}
