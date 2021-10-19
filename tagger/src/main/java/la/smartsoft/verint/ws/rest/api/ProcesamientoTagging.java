package la.smartsoft.verint.ws.rest.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.audiomedia.IConsultarAudio;
import la.smartsoft.verint.integracion.audiomedia.impl.ServicioConsultaAudioMedia;
import la.smartsoft.verint.integracion.db.verint.IVerintDB;
import la.smartsoft.verint.integracion.db.verint.impl.ServicioAuditoria;
import la.smartsoft.verint.ws.rest.api.dto.ErrorWS;
import la.smartsoft.verint.ws.rest.api.dto.ParametrosEntrada;
import la.smartsoft.verint.ws.rest.api.dto.ParametrosSalida;
import la.smartsoft.verint.ws.rest.api.dto.RespuestaAudio;
import la.smartsoft.verint.ws.rest.api.impl.ServicioProcesamiento;

@Path("/servicios")
public class ProcesamientoTagging extends ConfiguracionApi {

	private static final Logger LOG = Logger.getLogger(ProcesamientoTagging.class);

	/* Errores que se devuelven en caso de que existan de acuerdo al contexto */
	private static final String ERROR_PROCEMIENTO = "Ha ocurrido un error general durante el proceso";
	private static final String ERROR_BD = "Ha ocurrido un error con la base de datos, por favor contacte con el administrador";
	private static final String ERROR_GENERAL = "Ha ocurrido un error general, por favor contacte con el administrador";

	private static final String ERR_NEGOCIO = "01";
	private static final String ERR_SISTEMA = "02";

	public ProcesamientoTagging() throws IOException {
		super();
	}

	@Path("/procesar")
	@GET
	// @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Consumes("text/plain")
	public ParametrosSalida procesar(
	// @Context HttpServletRequest headers, ParametrosEntrada entrada
	) {
		ParametrosSalida salida = new ParametrosSalida();
		try {
			LOG.info("Inicio procesamiento ");

			IProcesamientoTagging procesamiento = new ServicioProcesamiento();
			procesamiento.procesar();

			salida.setExitoso(Boolean.TRUE);
			LOG.info("Termino procesamiento ");
		} /*
			 * catch (SQLException e) { ErrorWS errorWS = new ErrorWS();
			 * errorWS.setCodigoError(ERR_SISTEMA); errorWS.setDescripcionError(ERROR_BD);
			 * salida.addError(errorWS); salida.setExitoso(Boolean.FALSE); return salida; }
			 */catch (Exception e) {
			ErrorWS errorWS = new ErrorWS();
			errorWS.setCodigoError(ERR_SISTEMA);
			errorWS.setDescripcionError(ERROR_PROCEMIENTO);
			salida.addError(errorWS);
			salida.setExitoso(Boolean.FALSE);
			return salida;
		}

		return salida;
	}

	@Path("/audio")
	@GET
	// @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Consumes("text/plain")
	public RespuestaAudio consultarAudio(@Context HttpServletRequest headers, String incidentNumber) {

		RespuestaAudio salida = new RespuestaAudio();

		try {
			LOG.info("Inicio consultarAudio ");

			IVerintDB consultaBD = new ServicioAuditoria();
			Map<String, Object> rta = consultaBD.consultarInformacionAudio(incidentNumber);

			if (rta.get("siteId") != null && rta.get("sessionId") != null) {
				IConsultarAudio audioService = new ServicioConsultaAudioMedia();
				List<String> audios = audioService.consultarAudio(Integer.valueOf((String) rta.get("siteId")),
						Integer.valueOf((String) rta.get("sessionId")));
				salida.setAudios(audios);
				salida.setExitoso(Boolean.TRUE);
			} else {
				salida.setExitoso(Boolean.FALSE);
				ErrorWS error = new ErrorWS();
				error.setCodigoError("ERR_TAGGER_002");
				error.setDescripcionError("No se encontraron sesiones para el incidente");
				salida.setError(error);
			}

			LOG.info("Termino consultarAudio ");
			return salida;

		} catch (Exception e) {
			ErrorWS errorWS = new ErrorWS();
			errorWS.setCodigoError("ERR_TAGGER_001");
			errorWS.setDescripcionError("Error general : " + e.getMessage());
			salida.setError(errorWS);
			salida.setExitoso(Boolean.FALSE);
			return salida;
		}

	}

}
