package la.smartsoft.verint.integracion.audiomedia;

import java.util.List;
import java.util.Map;

/**
 * @author pedro Clase que permite
 */
public interface IConsultarAudio {

	/**
	 * Permite consultar los audios de una llamada a partir del siteId y sessionId
	 * 
	 * @param sesiones
	 * @return
	 */
	public List<Map<String, String>> consultarAudio(Integer siteId, Integer sessionId);

}
