package la.smartsoft.verint.integracion.db.rdw;

import java.util.Date;
import java.util.List;

import la.smartsoft.verint.integracion.db.rdw.dto.LlamadaDTO;

/**
 * @author pedro
 *	Defición de funcionalidades para la integración con RDW
 */
public interface IRDW {
	
	/**
	 * Permite obtener un listado de llamadas en RDW a partir de una fecha de inicio y de fin
	 * @param inicio
	 * @param fin
	 * @return
	 */
	public List<LlamadaDTO> obtenerLlamadas(Date inicio, Date fin);

}
