package la.smartsoft.verint.integracion.daswebapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionVerint {

	private @JsonProperty("exception_reason") String exception_reason;
	private @JsonProperty("call_id") String call_id;
	private @JsonProperty("personal_id") String personal_id;
	private @JsonProperty("string_extension") String string_extension;
	private @JsonProperty("pbx_login_id") String pbx_login_id;
	private @JsonProperty("dnis") String dnis;
	private @JsonProperty("total_hold_time_in_seconds") String total_hold_time_in_seconds;
	private @JsonProperty("switch_id") String switch_id;
	private @JsonProperty("ani") String ani;
	private @JsonProperty("number_of_holds") String number_of_holds;
	private @JsonProperty("direction") String direction;
	private @JsonProperty("switch_call_id") String switch_call_id;
	private @JsonProperty("cd17") String cd17;
	private @JsonProperty("cd2") String cd2;
	private @JsonProperty("audio_end_time") String audio_end_time;
	private @JsonProperty("media_type_bit_mask") String media_type_bit_mask;
	private @JsonProperty("AUDIO_START_TIME") String AUDIO_START_TIME;
	private @JsonProperty("wrapup_time_in_seconds") String wrapup_time_in_seconds;
	private @JsonProperty("audio_module_num") String audio_module_num;
	private @JsonProperty("interaction_type") String interaction_type;
	private @JsonProperty("audio_ch_num") String audio_ch_num;
	private @JsonProperty("site_id") Long site_id;
	// TODO: verificas si el sid es el session_id
	private @JsonProperty("sid") Long sid;

	/**
	 * @return the exception_reason
	 */
	public String getException_reason() {
		return exception_reason;
	}

	/**
	 * @param exception_reason the exception_reason to set
	 */
	public void setException_reason(String exception_reason) {
		this.exception_reason = exception_reason;
	}

	/**
	 * @return the call_id
	 */
	public String getCall_id() {
		return call_id;
	}

	/**
	 * @param call_id the call_id to set
	 */
	public void setCall_id(String call_id) {
		this.call_id = call_id;
	}

	/**
	 * @return the personal_id
	 */
	public String getPersonal_id() {
		return personal_id;
	}

	/**
	 * @param personal_id the personal_id to set
	 */
	public void setPersonal_id(String personal_id) {
		this.personal_id = personal_id;
	}

	/**
	 * @return the string_extension
	 */
	public String getString_extension() {
		return string_extension;
	}

	/**
	 * @param string_extension the string_extension to set
	 */
	public void setString_extension(String string_extension) {
		this.string_extension = string_extension;
	}

	/**
	 * @return the pbx_login_id
	 */
	public String getPbx_login_id() {
		return pbx_login_id;
	}

	/**
	 * @param pbx_login_id the pbx_login_id to set
	 */
	public void setPbx_login_id(String pbx_login_id) {
		this.pbx_login_id = pbx_login_id;
	}

	/**
	 * @return the dnis
	 */
	public String getDnis() {
		return dnis;
	}

	/**
	 * @param dnis the dnis to set
	 */
	public void setDnis(String dnis) {
		this.dnis = dnis;
	}

	/**
	 * @return the total_hold_time_in_seconds
	 */
	public String getTotal_hold_time_in_seconds() {
		return total_hold_time_in_seconds;
	}

	/**
	 * @param total_hold_time_in_seconds the total_hold_time_in_seconds to set
	 */
	public void setTotal_hold_time_in_seconds(String total_hold_time_in_seconds) {
		this.total_hold_time_in_seconds = total_hold_time_in_seconds;
	}

	/**
	 * @return the switch_id
	 */
	public String getSwitch_id() {
		return switch_id;
	}

	/**
	 * @param switch_id the switch_id to set
	 */
	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}

	/**
	 * @return the ani
	 */
	public String getAni() {
		return ani;
	}

	/**
	 * @param ani the ani to set
	 */
	public void setAni(String ani) {
		this.ani = ani;
	}

	/**
	 * @return the number_of_holds
	 */
	public String getNumber_of_holds() {
		return number_of_holds;
	}

	/**
	 * @param number_of_holds the number_of_holds to set
	 */
	public void setNumber_of_holds(String number_of_holds) {
		this.number_of_holds = number_of_holds;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the switch_call_id
	 */
	public String getSwitch_call_id() {
		return switch_call_id;
	}

	/**
	 * @param switch_call_id the switch_call_id to set
	 */
	public void setSwitch_call_id(String switch_call_id) {
		this.switch_call_id = switch_call_id;
	}

	/**
	 * @return the cd17
	 */
	public String getCd17() {
		return cd17;
	}

	/**
	 * @param cd17 the cd17 to set
	 */
	public void setCd17(String cd17) {
		this.cd17 = cd17;
	}

	/**
	 * @return the cd2
	 */
	public String getCd2() {
		return cd2;
	}

	/**
	 * @param cd2 the cd2 to set
	 */
	public void setCd2(String cd2) {
		this.cd2 = cd2;
	}

	/**
	 * @return the audio_end_time
	 */
	public String getAudio_end_time() {
		return audio_end_time;
	}

	/**
	 * @param audio_end_time the audio_end_time to set
	 */
	public void setAudio_end_time(String audio_end_time) {
		this.audio_end_time = audio_end_time;
	}

	/**
	 * @return the media_type_bit_mask
	 */
	public String getMedia_type_bit_mask() {
		return media_type_bit_mask;
	}

	/**
	 * @param media_type_bit_mask the media_type_bit_mask to set
	 */
	public void setMedia_type_bit_mask(String media_type_bit_mask) {
		this.media_type_bit_mask = media_type_bit_mask;
	}

	/**
	 * @return the aUDIO_START_TIME
	 */
	public String getAUDIO_START_TIME() {
		return AUDIO_START_TIME;
	}

	/**
	 * @param aUDIO_START_TIME the aUDIO_START_TIME to set
	 */
	public void setAUDIO_START_TIME(String aUDIO_START_TIME) {
		AUDIO_START_TIME = aUDIO_START_TIME;
	}

	/**
	 * @return the wrapup_time_in_seconds
	 */
	public String getWrapup_time_in_seconds() {
		return wrapup_time_in_seconds;
	}

	/**
	 * @param wrapup_time_in_seconds the wrapup_time_in_seconds to set
	 */
	public void setWrapup_time_in_seconds(String wrapup_time_in_seconds) {
		this.wrapup_time_in_seconds = wrapup_time_in_seconds;
	}

	/**
	 * @return the audio_module_num
	 */
	public String getAudio_module_num() {
		return audio_module_num;
	}

	/**
	 * @param audio_module_num the audio_module_num to set
	 */
	public void setAudio_module_num(String audio_module_num) {
		this.audio_module_num = audio_module_num;
	}

	/**
	 * @return the interaction_type
	 */
	public String getInteraction_type() {
		return interaction_type;
	}

	/**
	 * @param interaction_type the interaction_type to set
	 */
	public void setInteraction_type(String interaction_type) {
		this.interaction_type = interaction_type;
	}

	/**
	 * @return the audio_ch_num
	 */
	public String getAudio_ch_num() {
		return audio_ch_num;
	}

	/**
	 * @param audio_ch_num the audio_ch_num to set
	 */
	public void setAudio_ch_num(String audio_ch_num) {
		this.audio_ch_num = audio_ch_num;
	}

	public Long getSite_id() {
		return this.site_id;
	}

	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}

	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "[exception_reason: " + exception_reason + ", call_id: " + call_id + ", personal_id: " + personal_id
				+ ", string_extension: " + string_extension + ", pbx_login_id: " + pbx_login_id + ", dnis: " + dnis
				+ ", total_hold_time_in_seconds: " + total_hold_time_in_seconds + ", switch_id: " + switch_id
				+ ", ani: " + ani + ", direction: " + direction + ", switch_call_id: " + switch_call_id + ", cd17: "
				+ cd17 + ", cd2: " + cd2 + ", audio_end_time: " + audio_end_time + ", media_type_bit_mask: "
				+ media_type_bit_mask + ", AUDIO_START_TIME: " + AUDIO_START_TIME + ", wrapup_time_in_seconds: "
				+ wrapup_time_in_seconds + ", audio_module_num: " + audio_module_num + ", interaction_type: "
				+ interaction_type + ", audio_ch_num: " + audio_ch_num + "]";
	}
}
