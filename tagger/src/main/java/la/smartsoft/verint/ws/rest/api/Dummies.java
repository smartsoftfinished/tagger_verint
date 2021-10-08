package la.smartsoft.verint.ws.rest.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.daswebapi.dto.RequestDynamicQuery;
import la.smartsoft.verint.integracion.token.dto.UsuarioToken;
import la.smartsoft.verint.ws.rest.api.dto.ErrorWS;
import la.smartsoft.verint.ws.rest.api.dto.ParametrosEntrada;
import la.smartsoft.verint.ws.rest.api.dto.ParametrosSalida;
import la.smartsoft.verint.ws.rest.api.impl.ServicioProcesamiento;

@Path("/dummy")
public class Dummies extends ConfiguracionApi{

	private static final Logger LOG = Logger.getLogger(Dummies.class);
	
	public Dummies() throws IOException {
		super();
	}
	
	@Path("/token")
	@POST
	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public String procesarToken(UsuarioToken token) {
		
		try {
			LOG.info("Inicio procesarToken ");
			LOG.info("Termino procesarToken ");
			return "{\"AuthToken\":{\"id\":\"1251\",\"token\":\"odeHquobgOXQ2pUG\",\"extendSession\":null}}";
		} catch (Exception e) {
		}
		
		return "{\"error\":\"error\"}";
	}
	
	@Path("/query")
	@POST
	@Consumes(MediaType.APPLICATION_JSON  + "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	//public String procesarQuery(LinkedHashMap<String, Object> query) {
	public String procesarQuery(RequestDynamicQuery query) {
		
		try {
			LOG.info("Inicio procesarQuery Dummy");
			LOG.info("Termino procesarQuery  Dummy");
	//		return "{\"Sessions\":[{\"AUDIO_START_TIME\":\"2021-07-02T03:31:52.337\",\"sid\":23778393,\"site_id\":427,\"ani\":\"3205602488\",\"audio_ch_num\":52230291,\"audio_end_time\":\"2021-07-02T03:35:16.337\",\"audio_module_num\":427001,\"audio_start_time1\":\"2021-07-02T03:31:52.337\",\"audio_start_time_gmt\":\"2021-07-02T08:31:52.337\",\"call_id\":9142606911230000041,\"cd1\":null,\"cd2\":null,\"cd3\":null,\"cd4\":null,\"cd5\":null,\"cd6\":null,\"cd7\":null,\"cd8\":null,\"cd9\":null,\"cd10\":null,\"cd11\":null,\"cd12\":null,\"cd13\":null,\"cd14\":null,\"cd15\":null,\"cd16\":null,\"cd17\":\"3205602488\",\"cd18\":null,\"cd19\":null,\"cd20\":null,\"cd21\":null,\"cd22\":null,\"cd23\":null,\"cd24\":null,\"cd25\":null,\"cd26\":null,\"cd27\":null,\"cd28\":null,\"cd29\":null,\"cd30\":null,\"cd31\":null,\"cd32\":null,\"cd33\":null,\"cd34\":null,\"cd35\":null,\"cd36\":null,\"cd37\":null,\"cd38\":null,\"cd39\":null,\"cd40\":null,\"cd41\":null,\"cd42\":null,\"cd43\":null,\"cd44\":null,\"cd45\":null,\"cd46\":null,\"cd47\":null,\"cd48\":null,\"cd49\":null,\"cd50\":null,\"cd51\":null,\"cd52\":null,\"cd53\":null,\"cd54\":null,\"cd55\":null,\"cd56\":null,\"cd57\":null,\"cd58\":null,\"cd59\":null,\"cd60\":null,\"cd61\":null,\"cd62\":null,\"cd63\":null,\"cd64\":null,\"cd65\":null,\"cd66\":null,\"cd67\":null,\"cd68\":null,\"cd69\":null,\"cd70\":null,\"cd71\":null,\"cd72\":null,\"cd73\":null,\"cd74\":null,\"cd75\":null,\"contact_duration\":\"00:03:24\",\"contact_duration_seconds\":204,\"direction\":1,\"dnis\":\"2913500\",\"duration\":\"00:03:24\",\"duration_seconds\":204,\"evaluated_by_me\":0,\"exception_reason\":1,\"extension\":71921,\"string_extension\":\"71921\",\"is_exception\":0,\"local_audio_end_time\":\"2021-07-02T03:35:16.337\",\"local_audio_start_time\":\"2021-07-02T03:31:52.337\",\"number_of_conferences\":0,\"number_of_holds\":1,\"number_of_transfers\":0,\"num_of_cxm_evals\":0,\"num_of_ccq_evals\":0,\"session_number_of_holds\":1,\"session_total_hold_time\":\"00:00:00\",\"session_total_hold_time_in_seconds\":0,\"pbx_login_id\":\"42009\",\"pcd1\":null,\"pcd1_value\":null,\"pcd10\":null,\"pcd10_value\":null,\"pcd11\":null,\"pcd11_value\":null,\"pcd12\":null,\"pcd12_value\":null,\"pcd13\":null,\"pcd13_value\":null,\"pcd14\":null,\"pcd14_value\":null,\"pcd15\":null,\"pcd15_value\":null,\"pcd2\":null,\"pcd2_value\":null,\"pcd3\":null,\"pcd3_value\":null,\"pcd4\":null,\"pcd4_value\":null,\"pcd5\":null,\"pcd5_value\":null,\"pcd6\":null,\"pcd6_value\":null,\"pcd7\":null,\"pcd7_value\":null,\"pcd8\":null,\"pcd8_value\":null,\"pcd9\":null,\"pcd9_value\":null,\"personal_id\":427003413,\"personal_name\":\"ALARCON CERRO, DAVID MARCEL\",\"personal_tag\":0,\"prod_time\":null,\"producer_name\":\"\",\"remark\":\"\",\"screens_exists\":0,\"screens_module\":0,\"switch_call_id\":\"1487\",\"switch_id\":427000006,\"switch_name\":\"CS1000\",\"total_hold_time\":\"00:00:00\",\"total_hold_time_in_seconds\":0,\"type\":1,\"wrapup_time_in_seconds\":0,\"wrapup_time\":\"00:00:00\",\"interaction_type_id\":1,\"interaction_type\":\"Phone\",\"media_type_bit_mask\":1,\"transaction_id\":\"2021070203315233105223029142700401\",\"eval_count\":0,\"has_emotion\":\"0\",\"ifind_categories\":\"\",\"ifind_rank\":0,\"source_db\":\"CentralContact\"}],\"QDI\":[{\"TIME_FROM\":\"2021-07-01T23:59:00\",\"TIME_TO\":\"2021-07-02T23:59:00\",\"REL_TIME_TO\":\"2021-07-02T03:31:52.337\",\"REL_TIME_FROM\":\"2021-07-02T03:31:52.337\"}]}";
			
			String respuesta = "{\"Sessions\":[{\"AUDIO_START_TIME\":\"2021-09-01T10:44:11.853\",\r\n" + 
					"\"sid\":24929610,\r\n" + 
					"\"site_id\":427,\r\n" + 
					"\"ani\":\"3135341509\",\r\n" + 
					"\"audio_ch_num\":53568922,\r\n" + 
					"\"audio_end_time\":\"2021-09-01T10:45:17.853\",\r\n" + 
					"\"audio_module_num\":427001,\r\n" + 
					"\"audio_start_time1\":\r\n" + 
					"\"2021-09-01T10:44:11.853\",\r\n" + 
					"\"audio_start_time_gmt\":\r\n" + 
					"\"2021-09-01T15:44:11.853\",\r\n" + 
					"\"call_id\":9143136545180000041,\"cd1\":null,\"cd2\":null,\"cd3\":null,\"cd4\":null,\"cd5\":null,\"cd6\":null,\"cd7\":null,\"cd8\":null,\"cd9\":null,\"cd10\":null,\"cd11\":null,\"cd12\":null,\r\n" + 
					"\"cd13\":null,\"cd14\":null,\"cd15\":null,\"cd16\":null,\"cd17\":\"3135341509\",\"cd18\":null,\"cd19\":null,\"cd20\":null,\"cd21\":null,\"cd22\":null,\"cd23\":null,\"cd24\":null,\"cd25\":null,\r\n" + 
					"\"cd26\":null,\"cd27\":null,\"cd28\":null,\"cd29\":null,\"cd30\":null,\"cd31\":null,\"cd32\":null,\"cd33\":null,\"cd34\":null,\"cd35\":null,\"cd36\":null,\"cd37\":null,\"cd38\":null,\r\n" + 
					"\"cd39\":null,\"cd40\":null,\"cd41\":null,\"cd42\":null,\"cd43\":null,\"cd44\":null,\"cd45\":null,\"cd46\":null,\"cd47\":null,\"cd48\":null,\"cd49\":null,\"cd50\":null,\"cd51\":null,\r\n" + 
					"\"cd52\":null,\"cd53\":null,\"cd54\":null,\"cd55\":null,\"cd56\":null,\"cd57\":null,\"cd58\":null,\"cd59\":null,\"cd60\":null,\"cd61\":null,\"cd62\":null,\"cd63\":null,\"cd64\":null,\r\n" + 
					"\"cd65\":null,\"cd66\":null,\"cd67\":null,\"cd68\":null,\"cd69\":null,\"cd70\":null,\"cd71\":null,\"cd72\":null,\"cd73\":null,\"cd74\":null,\"cd75\":null,\r\n" + 
					"\"contact_duration\":\"00:01:06\",\"contact_duration_seconds\":66,\"direction\":1,\"dnis\":\"2207900\",\"duration\":\"00:01:06\",\"duration_seconds\":66,\"evaluated_by_me\":0,\r\n" + 
					"\"exception_reason\":1,\"extension\":71912,\"string_extension\":\"71912\",\"is_exception\":0,\"local_audio_end_time\":\"2021-09-01T10:45:17.853\",\r\n" + 
					"\"local_audio_start_time\":\"2021-09-01T10:44:11.853\",\"number_of_conferences\":0,\"number_of_holds\":1,\"number_of_transfers\":0,\"num_of_cxm_evals\":0,\r\n" + 
					"\"num_of_ccq_evals\":0,\"session_number_of_holds\":1,\"session_total_hold_time\":\"00:00:00\",\"session_total_hold_time_in_seconds\":0,\"pbx_login_id\":\"41990\",\r\n" + 
					"\"pcd1\":null,\"pcd1_value\":null,\"pcd10\":null,\"pcd10_value\":null,\"pcd11\":null,\"pcd11_value\":null,\"pcd12\":null,\"pcd12_value\":null,\"pcd13\":null,\"pcd13_value\":null,\r\n" + 
					"\"pcd14\":null,\"pcd14_value\":null,\"pcd15\":null,\"pcd15_value\":null,\"pcd2\":null,\"pcd2_value\":null,\"pcd3\":null,\"pcd3_value\":null,\"pcd4\":null,\"pcd4_value\":null,\r\n" + 
					"\"pcd5\":null,\"pcd5_value\":null,\"pcd6\":null,\"pcd6_value\":null,\"pcd7\":null,\"pcd7_value\":null,\"pcd8\":null,\"pcd8_value\":null,\"pcd9\":null,\"pcd9_value\":null,\r\n" + 
					"\"personal_id\":427003375,\"personal_name\":\"HURTADO GUERRERO, ANDRES FABIAN\",\"personal_tag\":0,\"prod_time\":null,\"producer_name\":\"\",\"remark\":\"\",\r\n" + 
					"\"screens_exists\":0,\"screens_module\":0,\"switch_call_id\":\"54543\",\"switch_id\":427000006,\"switch_name\":\"CS1000\",\"total_hold_time\":\"00:00:00\",\r\n" + 
					"\"total_hold_time_in_seconds\":0,\"type\":1,\"wrapup_time_in_seconds\":0,\"wrapup_time\":\"00:00:00\",\"interaction_type_id\":1,\"interaction_type\":\"Phone\",\r\n" + 
					"\"media_type_bit_mask\":1,\"transaction_id\":\"2021090110441185705356892242700401\",\"eval_count\":0,\"has_emotion\":\"0\",\"ifind_categories\":\"\",\"ifind_rank\":0,\r\n" + 
					"\"source_db\":\"CentralContact\"},{\"AUDIO_START_TIME\":\"2021-09-01T10:41:33.853\",\"sid\":24929572,\"site_id\":427,\"ani\":\"3135341509\",\"audio_ch_num\":53568879,\r\n" + 
					"\"audio_end_time\":\"2021-09-01T10:43:00.853\",\"audio_module_num\":427001,\"audio_start_time1\":\"2021-09-01T10:41:33.853\",\r\n" + 
					"\"audio_start_time_gmt\":\"2021-09-01T15:41:33.853\",\"call_id\":9143136529380000041,\"cd1\":null,\"cd2\":null,\"cd3\":null,\"cd4\":null,\"cd5\":null,\"cd6\":null,\"cd7\":null,\r\n" + 
					"\"cd8\":null,\"cd9\":null,\"cd10\":null,\"cd11\":null,\"cd12\":null,\"cd13\":null,\"cd14\":null,\"cd15\":null,\"cd16\":null,\"cd17\":\"3135341509\",\"cd18\":null,\"cd19\":null,\"cd20\":null,\r\n" + 
					"\"cd21\":null,\"cd22\":null,\"cd23\":null,\"cd24\":null,\"cd25\":null,\"cd26\":null,\"cd27\":null,\"cd28\":null,\"cd29\":null,\"cd30\":null,\"cd31\":null,\"cd32\":null,\"cd33\":null,\r\n" + 
					"\"cd34\":null,\"cd35\":null,\"cd36\":null,\"cd37\":null,\"cd38\":null,\"cd39\":null,\"cd40\":null,\"cd41\":null,\"cd42\":null,\"cd43\":null,\"cd44\":null,\"cd45\":null,\"cd46\":null,\r\n" + 
					"\"cd47\":null,\"cd48\":null,\"cd49\":null,\"cd50\":null,\"cd51\":null,\"cd52\":null,\"cd53\":null,\"cd54\":null,\"cd55\":null,\"cd56\":null,\"cd57\":null,\"cd58\":null,\"cd59\":null,\r\n" + 
					"\"cd60\":null,\"cd61\":null,\"cd62\":null,\"cd63\":null,\"cd64\":null,\"cd65\":null,\"cd66\":null,\"cd67\":null,\"cd68\":null,\"cd69\":null,\"cd70\":null,\"cd71\":null,\"cd72\":null,\r\n" + 
					"\"cd73\":null,\"cd74\":null,\"cd75\":null,\"contact_duration\":\"00:01:27\",\"contact_duration_seconds\":87,\"direction\":1,\"dnis\":\"2207900\",\"duration\":\"00:01:27\",\r\n" + 
					"\"duration_seconds\":87,\"evaluated_by_me\":0,\"exception_reason\":1,\"extension\":71062,\"string_extension\":\"71062\",\"is_exception\":0,\r\n" + 
					"\"local_audio_end_time\":\"2021-09-01T10:43:00.853\",\"local_audio_start_time\":\"2021-09-01T10:41:33.853\",\"number_of_conferences\":0,\"number_of_holds\":0,\r\n" + 
					"\"number_of_transfers\":0,\"num_of_cxm_evals\":0,\"num_of_ccq_evals\":0,\"session_number_of_holds\":0,\"session_total_hold_time\":\"00:00:00\",\r\n" + 
					"\"session_total_hold_time_in_seconds\":0,\"pbx_login_id\":\"41923\",\"pcd1\":null,\"pcd1_value\":null,\"pcd10\":null,\"pcd10_value\":null,\"pcd11\":null,\"pcd11_value\":null,\r\n" + 
					"\"pcd12\":null,\"pcd12_value\":null,\"pcd13\":null,\"pcd13_value\":null,\"pcd14\":null,\"pcd14_value\":null,\"pcd15\":null,\"pcd15_value\":null,\"pcd2\":null,\"pcd2_value\":null,\r\n" + 
					"\"pcd3\":null,\"pcd3_value\":null,\"pcd4\":null,\"pcd4_value\":null,\"pcd5\":null,\"pcd5_value\":null,\"pcd6\":null,\"pcd6_value\":null,\"pcd7\":null,\"pcd7_value\":null,\"pcd8\":null,\r\n" + 
					"\"pcd8_value\":null,\"pcd9\":null,\"pcd9_value\":null,\"personal_id\":427003051,\"personal_name\":\"ESCOBAR, CARLOS ANDRES\",\"personal_tag\":0,\"prod_time\":null,\r\n" + 
					"\"producer_name\":\"\",\"remark\":\"\",\"screens_exists\":0,\"screens_module\":0,\"switch_call_id\":\"54679\",\"switch_id\":427000006,\"switch_name\":\"CS1000\",\r\n" + 
					"\"total_hold_time\":\"00:00:00\",\"total_hold_time_in_seconds\":0,\"type\":1,\"wrapup_time_in_seconds\":0,\"wrapup_time\":\"00:00:00\",\"interaction_type_id\":1,\r\n" + 
					"\"interaction_type\":\"Phone\",\"media_type_bit_mask\":1,\"transaction_id\":\"2021090110413384405356887942700401\",\"eval_count\":0,\"has_emotion\":\"0\",\"ifind_categories\":\"\",\r\n" + 
					"\"ifind_rank\":0,\"source_db\":\"CentralContact\"}],\"QDI\":[{\"TIME_FROM\":\"2021-08-30T23:59:00\",\"TIME_TO\":\"2021-09-01T23:59:00\",\"REL_TIME_TO\":\"2021-09-01T10:44:11.853\",\r\n" + 
					"\"REL_TIME_FROM\":\"2021-09-01T10:41:33.853\"}]}";
			
			return respuesta;
			
		} catch (Exception e) {
		}
		
		return "{\"error\":\"error\"}";
	}
}
