package la.smartsoft.verint.integracion.daswebapi;

import java.util.List;

import la.smartsoft.verint.integracion.daswebapi.dto.SessionVerint;
import la.smartsoft.verint.integracion.db.rdw.dto.LlamadaDTO;
import la.smartsoft.verint.integracion.token.dto.Token;

/**
 * @author pedro
 *	Servicios de Consulta de Verint de la API DASWEBAPI
 */
public interface IConsultaVerint {
	
	/**
	 * Consultar Sesiones Verint (Llamadas)
	 * @param llamada
	 * @param token
	 * @return List<SessionVerint>
	 */
	public List<SessionVerint> consultarVerint(LlamadaDTO llamada, Token token)throws TokenInvalidoException;

}
