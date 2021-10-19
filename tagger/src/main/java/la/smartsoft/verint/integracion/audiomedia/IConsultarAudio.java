package la.smartsoft.verint.integracion.audiomedia;

import java.util.List;

/**
 * @author pedro
 * Clase que permite 
 */
public interface IConsultarAudio {

	/**
	 * Permite consultar los audios de una llamada a partir del siteId y sessionId
	 * @param sesiones
	 * @return
	 */
	public List<String> consultarAudio(Integer siteId, Integer sessionId);
	
}
