package la.smartsoft.verint.integracion.daswebapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 * Clase con los atributos QDI
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Qdi {
	
	private @JsonProperty("TIME_FROM") String TIME_FROM;
	private @JsonProperty("TIME_TO") String TIME_TO;
	private @JsonProperty("REL_TIME_TO") String REL_TIME_TO;
	private @JsonProperty("REL_TIME_FROM") String REL_TIME_FROM;
	
	/**
	 * @return the tIME_FROM
	 */
	public String getTIME_FROM() {
		return TIME_FROM;
	}
	/**
	 * @param tIME_FROM the tIME_FROM to set
	 */
	public void setTIME_FROM(String tIME_FROM) {
		TIME_FROM = tIME_FROM;
	}
	/**
	 * @return the tIME_TO
	 */
	public String getTIME_TO() {
		return TIME_TO;
	}
	/**
	 * @param tIME_TO the tIME_TO to set
	 */
	public void setTIME_TO(String tIME_TO) {
		TIME_TO = tIME_TO;
	}
	/**
	 * @return the rEL_TIME_TO
	 */
	public String getREL_TIME_TO() {
		return REL_TIME_TO;
	}
	/**
	 * @param rEL_TIME_TO the rEL_TIME_TO to set
	 */
	public void setREL_TIME_TO(String rEL_TIME_TO) {
		REL_TIME_TO = rEL_TIME_TO;
	}
	/**
	 * @return the rEL_TIME_FROM
	 */
	public String getREL_TIME_FROM() {
		return REL_TIME_FROM;
	}
	/**
	 * @param rEL_TIME_FROM the rEL_TIME_FROM to set
	 */
	public void setREL_TIME_FROM(String rEL_TIME_FROM) {
		REL_TIME_FROM = rEL_TIME_FROM;
	}

	@Override
	public String toString() {
		return "[TIME_FROM: " + TIME_FROM +
			", TIME_TO: " + TIME_TO +
			", REL_TIME_TO: " + REL_TIME_TO +
			", REL_TIME_FROM: " + REL_TIME_FROM + "]";
	}	
}
