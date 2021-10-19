package la.smartsoft.verint.ws.rest.api.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.daswebapi.IConsultaVerint;
import la.smartsoft.verint.integracion.daswebapi.TokenInvalidoException;
import la.smartsoft.verint.integracion.daswebapi.dto.SessionVerint;
import la.smartsoft.verint.integracion.daswebapi.impl.ServicioConsultasVerint;
import la.smartsoft.verint.integracion.datamodelws.IActualizacionVerint;
import la.smartsoft.verint.integracion.datamodelws.impl.ServicioActualizacionVerint;
import la.smartsoft.verint.integracion.db.rdw.IRDW;
import la.smartsoft.verint.integracion.db.rdw.dto.LlamadaDTO;
import la.smartsoft.verint.integracion.db.rdw.impl.ServicioRDW;
import la.smartsoft.verint.integracion.db.verint.dto.ParametroDTO;
import la.smartsoft.verint.integracion.db.verint.impl.ServicioParametro;
import la.smartsoft.verint.integracion.token.IToken;
import la.smartsoft.verint.integracion.token.dto.UsuarioToken;
import la.smartsoft.verint.integracion.token.impl.ServicioToken;
import la.smartsoft.verint.ws.rest.api.ConfiguracionApi;
import la.smartsoft.verint.ws.rest.api.IProcesamientoTagging;

/**
 * @author pedro Clase que realiza el Tagueo, lo cual consiste en buscar los
 *         incidentes en RWD, Con los resultados obtenidos, buscar las llamadas
 *         en un rango de tiempo, a los resultados encontrados se les debe poner
 *         el número de incidente y se debe actualizar la sesion
 */
public class ServicioProcesamiento extends ConfiguracionApi implements IProcesamientoTagging {

	private static final String CAMPO_INCIDENTE = "P1";

	private static final Logger LOG = Logger.getLogger(ServicioProcesamiento.class);

	@Override
	public void procesar() throws SQLException {

		LOG.info("Inicia Procesamiento");

		// 1. Se consultan las llamadas en RDW
		IRDW rdwService = new ServicioRDW();
		IToken tokenService = new ServicioToken();
		IConsultaVerint consultaVerintService = new ServicioConsultasVerint();
		IActualizacionVerint actualizacionVerintService = new ServicioActualizacionVerint();
		boolean resultado = false;
		List<SessionVerint> sesiones = null;

		ServicioParametro servicioParametro = new ServicioParametro();

		LOG.info("Se intenta consumir el parametro segundos antes");
		ParametroDTO segundosAntes = servicioParametro.consultarParametro(ParametroDTO.SEGUNDOS_ATRAS);
		LOG.info("Se consume el parametro segundos antes " + segundosAntes.getValor());

		LOG.info("Se intenta consumir el parametro rango busqueda");
		ParametroDTO rangoBusqueda = servicioParametro.consultarParametro(ParametroDTO.RANGO_CONSULTA);
		LOG.info("Se consume el parametro rango busqueda");

		Calendar calendar = Calendar.getInstance();
		LOG.info("Fecha inicio antes de resta" + calendar.getTime());
		calendar.add(Calendar.HOUR, -(Integer.parseInt(segundosAntes.getValor())));
		LOG.info("Fecha inicio es " + calendar.getTime());

		Date inicio = calendar.getTime();

		calendar.add(Calendar.SECOND, Integer.parseInt(rangoBusqueda.getValor()));

		List<LlamadaDTO> llamadas = rdwService.obtenerLlamadas(inicio, calendar.getTime());
		// List<LlamadaDTO> llamadas = rdwService.obtenerLlamadas(new Date(), new
		// Date());
		LOG.info("Se consultan incidentes: " + (llamadas != null ? llamadas.size() + "" : "0") + " encontrados");

		for (LlamadaDTO llamada : llamadas) {
			LOG.info("Inicia Procesamiento: " + llamada.toString());

			// 2. Se consultan los números teléfonicos en un rango de Fechas
			// 2.1 Se consulta el token
			if (TOKEN == null || TOKEN.getId() == null || TOKEN.getToken() == null) {
				LOG.info("Inicio Consulta Token Inicial: " + llamada.getNumeroTelefonoIncidente());
				UsuarioToken usuarioToken = new UsuarioToken();
				usuarioToken.setUser(TOKEN_USUARIO);
				usuarioToken.setPassword(TOKEN_CONTRASENIA);
				TOKEN = tokenService.validarUsuario(usuarioToken);
				LOG.info("Fin Consulta Token Inicial: " + llamada.getNumeroTelefonoIncidente());
			}

			// 2.2 Se consulta Sesiones de Verint
			try {
				LOG.info("Antes Consulta Verint: " + llamada.getNumeroTelefonoIncidente());
				sesiones = consultaVerintService.consultarVerint(llamada, TOKEN);
				LOG.info("Despues Consulta Verint: " + (sesiones != null ? sesiones.size() + "" : "0") + " sesiones ");
			} catch (TokenInvalidoException e) {
				LOG.info("Inicio Consulta Token Exception: " + llamada.getNumeroTelefonoIncidente());
				UsuarioToken usuarioToken = new UsuarioToken();
				usuarioToken.setUser(TOKEN_USUARIO);
				usuarioToken.setPassword(TOKEN_CONTRASENIA);
				TOKEN = tokenService.validarUsuario(usuarioToken);
				LOG.info("Fin Consulta Token Exception: " + llamada.getNumeroTelefonoIncidente());
				LOG.info("Antes Consulta Verint Exception: " + llamada.getNumeroTelefonoIncidente());
				sesiones = consultaVerintService.consultarVerint(llamada, TOKEN);
				LOG.info("Despues Consulta Verint Exception: " + (sesiones != null ? sesiones.size() + "" : "0")
						+ " sesiones ");
			}

			// 3. Se hace el Tagging
			// 3.1 Asignar Incidente en Datos Sesion
			// taguearSession(llamada, sesiones);

			// 3.2 Actualizar Sesion con el nuevo Dato
			// LOG.info("Antes Actualizacion Verint: " +
			// llamada.getNumeroTelefonoIncidente());
			// resultado = actualizacionVerintService.actualizarVerint(sesiones);
			// LOG.info("Fin Actualizacion Verint: " + llamada.getNumeroTelefonoIncidente()
			// + " " + resultado);

			LOG.info("Termina Procesamiento: " + llamada.toString());
		}

		LOG.info("Termina Procesamiento");

	}

	/**
	 * @param llamada
	 * @param sesiones
	 */
	private void taguearSession(LlamadaDTO llamada, List<SessionVerint> sesiones) {
		// Si se encontró información para la llamada
		if (sesiones != null && !sesiones.isEmpty()) {
			LOG.info("Sesiones no nula Inicio: " + llamada.getNumeroTelefonoIncidente());
			for (SessionVerint sesion : sesiones) {
				sesion.setCd2(llamada.getIncidentNumber());
				LOG.info("Se asigna campo: " + CAMPO_INCIDENTE + llamada.getNumeroTelefonoIncidente());
			}
			LOG.info("Sesiones no nula Fin: " + llamada.getNumeroTelefonoIncidente());
		} else {
			LOG.info("Sesiones Nulas: " + llamada.getNumeroTelefonoIncidente());
		}
	}

}
