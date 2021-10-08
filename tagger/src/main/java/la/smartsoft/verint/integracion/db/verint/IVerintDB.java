package la.smartsoft.verint.integracion.db.verint;

import la.smartsoft.verint.integracion.db.verint.dto.AuditoriaTaggingDTO;

public interface IVerintDB {
	
	public void registrarAuditoria(AuditoriaTaggingDTO auditoriaTagging);

}
