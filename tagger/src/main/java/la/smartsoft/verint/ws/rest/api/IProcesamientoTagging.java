package la.smartsoft.verint.ws.rest.api;

import java.sql.SQLException;

/**
 * @author pedro
 *
 */
public interface IProcesamientoTagging {
	
	/**
	 * Permite hacer el Tagging de las llamadas a los incidentes
	 */
	public void procesar() throws SQLException;

}
