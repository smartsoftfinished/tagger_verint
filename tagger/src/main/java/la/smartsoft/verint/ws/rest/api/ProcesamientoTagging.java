package la.smartsoft.verint.ws.rest.api;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import la.smartsoft.verint.ws.rest.api.dto.ErrorWS;
import la.smartsoft.verint.ws.rest.api.dto.ParametrosEntrada;
import la.smartsoft.verint.ws.rest.api.dto.ParametrosSalida;
import la.smartsoft.verint.ws.rest.api.impl.ServicioProcesamiento;

@Path("/servicios")
public class ProcesamientoTagging extends ConfiguracionApi{

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
	//@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Consumes("text/plain")
	public ParametrosSalida procesar(
			//@Context HttpServletRequest headers, ParametrosEntrada entrada
			) {
		ParametrosSalida salida = new ParametrosSalida();
		try {
			LOG.info("Inicio procesamiento ");
			
			IProcesamientoTagging procesamiento = new ServicioProcesamiento();
			procesamiento.procesar();
			
			salida.setExitoso(Boolean.TRUE);
			LOG.info("Termino procesamiento ");
		} /*catch (SQLException e) {
			ErrorWS errorWS = new ErrorWS();
			errorWS.setCodigoError(ERR_SISTEMA);
			errorWS.setDescripcionError(ERROR_BD);
			salida.addError(errorWS);
			salida.setExitoso(Boolean.FALSE);
			return salida;
		} */catch (Exception e) {
			ErrorWS errorWS = new ErrorWS();
			errorWS.setCodigoError(ERR_SISTEMA);
			errorWS.setDescripcionError(ERROR_PROCEMIENTO);
			salida.addError(errorWS);
			salida.setExitoso(Boolean.FALSE);
			return salida;
		}
		
		return salida;
	}
	
 

//	@Path("/crearSolicitudCredito")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	public RespuestaWS crearSolicitudCredito(@Context HttpServletRequest headers,
//			SolicitudRedescuentoDigital solicitud) {
//
//		RedescuentoClient redescuento = new RedescuentoClient();
//		RespuestaWS objRespuesta = new RespuestaWS();
//
//		try {
//			if (solicitud == null) {
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(ERROR_PARAMETRO);
//				objRespuesta.getErrores().add(err);
//				objRespuesta.setExitoso(false);
//				return objRespuesta;
//			}
//
//			// Parametros para autenticación JWT.
//			ConsumoWebservice apiseguridadService = new ConsumoWebservice();
//			RespuestaPeticion respuestaValudJwt = apiseguridadService.validarJWT(urlApiSeguridadValidacion, headers,
//					aplicacion);
//
//			// Si se autentica, entonces realiza el consumo al servicio SOAP.
//			if (respuestaValudJwt.getError() == null || respuestaValudJwt.getError().isEmpty()) {
//
//				// Parámetros de consulta SOAP
//				solicitud.getDatosOperacion().setIdIntermediarioFinanciero(apiseguridadService.getNitFromJwt(headers));
//				objRespuesta = redescuento.crearSolicitudCredito(urlSoapRedescuentoDigital, solicitud);
//
//			} else {
//				objRespuesta.setCodigoError(ERR_NEGOCIO);
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(respuestaValudJwt.getError());
//				objRespuesta.getErrores().add(err);
//				objRespuesta.setExitoso(false);
//			}
//
//		} catch (Exception e) {
//			objRespuesta.setCodigoError(ERR_NEGOCIO);
//			ErrorWS err = new ErrorWS();
//			err.setCodigo(ERR2000);
//			err.setMensaje(ERROR_GENERAL);
//			objRespuesta.getErrores().add(err);
//			objRespuesta.setExitoso(false);
//		}
//
//		return objRespuesta;
//	}
//	
//	
//	@Path("/modificarSolicitudCredito")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	public RespuestaWS modificarSolicitudCredito(@Context HttpServletRequest headers,
//			ModificarSolicitudCredito arg0) {
//
//		RedescuentoClient redescuento = new RedescuentoClient();
//		RespuestaWS objRespuesta = new RespuestaWS();
//
//		try {
//			if (arg0 == null || arg0.getSolicitudCredito() == null || arg0.getIdSolicitud() == null) {
//
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(ERROR_PARAMETRO);
//				objRespuesta.getErrores().add(err);
//				objRespuesta.setExitoso(false);
//				return objRespuesta;
//			}
//
//			// Parametros para autenticación JWT.
//			ConsumoWebservice apiseguridadService = new ConsumoWebservice();
//			RespuestaPeticion respuestaValudJwt = apiseguridadService.validarJWT(urlApiSeguridadValidacion, headers,
//					aplicacion);
//
//			// Si se autentica, entonces realiza el consumo al servicio SOAP.
//			if (respuestaValudJwt.getError() == null || respuestaValudJwt.getError().isEmpty()) {
//
//				// Parámetros de consulta SOAP
//				objRespuesta = redescuento.modificarSolicitudCredito(urlSoapRedescuentoDigital, arg0.getSolicitudCredito(),
//						arg0.getIdSolicitud());
//
//			} else {
//				objRespuesta.setCodigoError(ERR_NEGOCIO);
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(respuestaValudJwt.getError());
//				objRespuesta.getErrores().add(err);
//				objRespuesta.setExitoso(false);
//			}
//
//		} catch (Exception e) {
//			objRespuesta.setCodigoError(ERR_SISTEMA);
//			ErrorWS err = new ErrorWS();
//			err.setCodigo(ERR2000);
//			err.setMensaje(ERROR_GENERAL);
//			objRespuesta.getErrores().add(err);
//			objRespuesta.setExitoso(false);
//			return objRespuesta;
//		}
//		
//		return objRespuesta;
//	}
//	
//	@Path("/aprobarSolicitudCredito")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	public RespuestaWS aprobarSolicitudCredito(@Context HttpServletRequest headers, AprobarSolicitudCredito arg0) {
//
//		RedescuentoClient redescuento = new RedescuentoClient();
//		RespuestaWS objRespuesta = new RespuestaWS();
//
//		try {
//			if (arg0 == null || arg0.getIdSolicitud() == null) {
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(ERROR_PARAMETRO);
//				objRespuesta.getErrores().add(err);
//
//				objRespuesta.setExitoso(false);
//				return objRespuesta;
//			}
//
//			// Parametros para autenticación JWT.
//			ConsumoWebservice apiseguridadService = new ConsumoWebservice();
//			RespuestaPeticion respuestaValudJwt = apiseguridadService.validarJWT(urlApiSeguridadValidacion, headers,
//					aplicacion);
//
//			// Si se autentica, entonces realiza el consumo al servicio SOAP.
//			if (respuestaValudJwt.getError() == null || respuestaValudJwt.getError().isEmpty()) {
//				objRespuesta = redescuento.aprobarSolicitudCredito(urlSoapRedescuentoDigital, arg0.getIdSolicitud());
//
//			} else {
//				objRespuesta.setCodigoError(ERR_NEGOCIO);
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(respuestaValudJwt.getError());
//				objRespuesta.getErrores().add(err);
//
//				objRespuesta.setExitoso(false);
//			}
//
//			return objRespuesta;
//
//		} catch (Exception e) {
//			objRespuesta.setCodigoError(ERR_SISTEMA);
//
//			ErrorWS err = new ErrorWS();
//			err.setCodigo(ERR2000);
//			err.setMensaje(ERROR_GENERAL);
//			objRespuesta.getErrores().add(err);
//			objRespuesta.setExitoso(false);
//			return objRespuesta;
//		}
//
//	}
//	
//	
//	@Path("/asignarPagareDeceval")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	public RespuestaWS asignarPagareDeceval(@Context HttpServletRequest headers, SolicitudAsignarPagDeceval arg0) {
//
//		RedescuentoClient redescuento = new RedescuentoClient();
//		RespuestaWS objRespuesta = new RespuestaWS();
//
//		try {
//			if (arg0 == null) {
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(ERROR_PARAMETRO);
//				objRespuesta.getErrores().add(err);
//
//				objRespuesta.setExitoso(false);
//				return objRespuesta;
//			}
//
//			// Parametros para autenticación JWT.
//			ConsumoWebservice apiseguridadService = new ConsumoWebservice();
//			RespuestaPeticion respuestaValudJwt = apiseguridadService.validarJWT(urlApiSeguridadValidacion, headers,
//					aplicacion);
//
//			// Si se autentica, entonces realiza el consumo al servicio SOAP.
//			if (respuestaValudJwt.getError() == null || respuestaValudJwt.getError().isEmpty()) {
//
//				objRespuesta = redescuento.asignarPagareDeceval(urlSoapRedescuentoDigital, arg0);
//
//			} else {
//				objRespuesta.setCodigoError(ERR_NEGOCIO);
//				ErrorWS err = new ErrorWS();
//				err.setCodigo(ERR2000);
//				err.setMensaje(respuestaValudJwt.getError());
//				objRespuesta.getErrores().add(err);
//				objRespuesta.setExitoso(false);
//			}
//
//			return objRespuesta;
//
//		} catch (Exception e) {
//			objRespuesta.setCodigoError(ERR_SISTEMA);
//			ErrorWS err = new ErrorWS();
//			err.setCodigo(ERR2000);
//			err.setMensaje(ERROR_GENERAL);
//			objRespuesta.getErrores().add(err);
//
//			objRespuesta.setExitoso(false);
//			return objRespuesta;
//		}
//	}
}
