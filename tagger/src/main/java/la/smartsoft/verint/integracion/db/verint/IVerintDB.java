package la.smartsoft.verint.integracion.db.verint;

import java.util.List;
import java.util.Map;

import la.smartsoft.verint.integracion.db.verint.dto.AuditoriaTaggingDTO;

public interface IVerintDB {

	public void registrarAuditoria(AuditoriaTaggingDTO auditoriaTagging);

	public void actualizarAuditoria(AuditoriaTaggingDTO auditoriaTagging);

	/**
	 * Permite retornar la información que se requiere para consultar un audio como
	 * el siteId y sessionId (sid), a partir de un número de incidente
	 * 
	 * @param incidentNumber
	 * @return
	 */
	public Map<String, Object> consultarInformacionAudio(String incidentNumber);

	public List<AuditoriaTaggingDTO> consultarPendientes();

}
