package la.smartsoft.verint.ws.rest.api;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.token.dto.Token;

/**
 * Clase con la configuración de las API y configuraciones del proyecto.
 * 
 * @author idiaz
 *
 */
public abstract class ConfiguracionApi {

	private static final Logger LOG = Logger.getLogger(ConfiguracionApi.class);

	/* Constantes y variables internas de configuración */

	private static final String CFG_PATH_CONFIGURACION = "/config.properties";

	private static final String CFG_ENDPOINT_VERINT_REST_TOKEN = "config.endpoint.verint.rest.token";
	private static final String CFG_ENDPOINT_VERINT_REST_QUERY = "config.endpoint.verint.rest.query";
	private static final String CFG_ENDPOINT_VERINT_SOAP_TAGGING = "config.endpoint.verint.soap.tagging";
	private static final String CFG_DATASOURCE_VERINT_RDW = "datasource.verint.rdw";
	private static final String CFG_DATASOURCE_VERINT_TAGGER = "datasource.verint.tagger";
	private static final String CFG_AMBIENTE_PRODUCCION = "ambiente.produccion";
	private static final String CFG_TOKEN_USUARIO = "token.usuario";
	private static final String CFG_TOKEN_CONTRASENIA = "token.contrasenia";
	private static final String CFG_USER_VERINT_TAGGER = "user.verint.tagger";
	private static final String CFG_CONTRASENIA_VERINT_TAGGER = "password.verint.tagger";

	private Properties configuracion;

	/* Constantes de acceso a las API */

	protected String ENDPOINT_VERINT_REST_TOKEN;
	protected String ENDPOINT_VERINT_REST_QUERY;
	protected String ENDPOINT_VERINT_SOAP_TAGGING;
	protected String DATASOURCE_VERINT_RDW;
	protected String DATASOURCE_VERINT_TAGGER;
	protected Boolean AMBIENTE_PRODUCCION;
	protected String TOKEN_USUARIO;
	protected String TOKEN_CONTRASENIA;
	protected String USER_VERINT_TAGGER;
	protected String CONTRASENIA_VERINT_TAGGER;
	protected Token TOKEN = new Token();

	// protected RequestAplicacion aplicacion;

	/**
	 * Constructor: Inicializa la configuración de las API.
	 * 
	 * @throws IOException
	 */
	public ConfiguracionApi() {

		// Traemos archivo de configuración
		getConfiguracion();

		// Endpoint para obtener token JWT
		ENDPOINT_VERINT_REST_TOKEN = configuracion.getProperty(CFG_ENDPOINT_VERINT_REST_TOKEN);

		// Endpoint para realizar consultas en Verint
		ENDPOINT_VERINT_REST_QUERY = configuracion.getProperty(CFG_ENDPOINT_VERINT_REST_QUERY);

		// Endpoint para realizar tagging en Verint (Asociarle un incidente a una
		// llamada)
		ENDPOINT_VERINT_SOAP_TAGGING = configuracion.getProperty(CFG_ENDPOINT_VERINT_SOAP_TAGGING);

		// Datasource para acceso a RDW
		DATASOURCE_VERINT_RDW = configuracion.getProperty(CFG_DATASOURCE_VERINT_RDW);

		// Datasource para acceso a BD propia Verint Tagger
		DATASOURCE_VERINT_TAGGER = configuracion.getProperty(CFG_DATASOURCE_VERINT_TAGGER);

		// Datasource para acceso a BD propia Verint Tagger
		DATASOURCE_VERINT_TAGGER = configuracion.getProperty(CFG_DATASOURCE_VERINT_TAGGER);

		// true, si ambiente es producción, false si es pruebas
		AMBIENTE_PRODUCCION = Boolean.parseBoolean(configuracion.getProperty(CFG_AMBIENTE_PRODUCCION));

		// Nombre de usuario para servicio de Tokens
		TOKEN_USUARIO = configuracion.getProperty(CFG_TOKEN_USUARIO);

		// Contrasenia de usuario para servicio de Tokens
		TOKEN_CONTRASENIA = configuracion.getProperty(CFG_TOKEN_CONTRASENIA);

		// Usuario base de datos verint tagger
		USER_VERINT_TAGGER = configuracion.getProperty(CFG_USER_VERINT_TAGGER);

		// Password base de datos verint tagger
		CONTRASENIA_VERINT_TAGGER = configuracion.getProperty(CFG_CONTRASENIA_VERINT_TAGGER);

		// Data de la aplicación
		// aplicacion = new
		// RequestAplicacion(configuracion.getProperty(CFG_SERVICIO_OPCION),
		// configuracion.getProperty(CFG_SERVICIO_APLICACION));

	}

	/**
	 * Método semi singleton para obtener la configuración del sistema.
	 * 
	 * @return properties file.
	 * @throws IOException
	 */
	public Properties getConfiguracion() {

		try {
			// Verificamos que esté cargada la configuración
			if (configuracion == null) {

				// Inicializamos configuración de la aplicación
				configuracion = new Properties();
				configuracion.load(ConfiguracionApi.class.getResourceAsStream(CFG_PATH_CONFIGURACION));
			}

			// retornamos la configuración
			return configuracion;
		} catch (Exception e) {
			LOG.error(e);
		}

		return configuracion;

	}

}
