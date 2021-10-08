package la.smartsoft.verint.integracion.datamodelws;

import java.util.List;

import la.smartsoft.verint.integracion.daswebapi.dto.SessionVerint;

/**
 * @author pedro
 * Clase que permite 
 */
public interface IActualizacionVerint {

	/**
	 * Permite actualizar los atributos de un listado de sesiones
	 * @param sesiones
	 * @return
	 */
	public boolean actualizarVerint(List<SessionVerint> sesiones);
	
}
