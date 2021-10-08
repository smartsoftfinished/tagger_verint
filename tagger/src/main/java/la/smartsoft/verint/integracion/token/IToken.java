package la.smartsoft.verint.integracion.token;

import la.smartsoft.verint.integracion.token.dto.Token;
import la.smartsoft.verint.integracion.token.dto.UsuarioToken;

/**
 * @author pedro
 *	Permite obtener un Token en el API de Verint
 */
public interface IToken {

	/**
	 * Permite obtener un token de Verint a partir de la información de un usuario
	 * @param usuarioToken
	 * @return
	 */
	public Token validarUsuario(UsuarioToken usuarioToken);
	
}
