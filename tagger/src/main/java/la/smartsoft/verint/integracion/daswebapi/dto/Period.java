package la.smartsoft.verint.integracion.daswebapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 * Clase con la información del Período a Consultar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Period {
	
	private @JsonProperty("BeginPeriod") String BeginPeriod;
	private @JsonProperty("EndPeriod") String EndPeriod;
	private @JsonProperty("TimeOfDateBegin") String TimeOfDateBegin;
	private @JsonProperty("TimeOfDateEnd") String TimeOfDateEnd;
	private @JsonProperty("Type") String Type;
	private @JsonProperty("Days") String Days;
	
	/**
	 * @return the beginPeriod
	 */
	public String getBeginPeriod() {
		return BeginPeriod;
	}
	/**
	 * @param beginPeriod the beginPeriod to set
	 */
	public void setBeginPeriod(String beginPeriod) {
		BeginPeriod = beginPeriod;
	}
	/**
	 * @return the endPeriod
	 */
	public String getEndPeriod() {
		return EndPeriod;
	}
	/**
	 * @param endPeriod the endPeriod to set
	 */
	public void setEndPeriod(String endPeriod) {
		EndPeriod = endPeriod;
	}
	/**
	 * @return the timeOfDateBegin
	 */
	public String getTimeOfDateBegin() {
		return TimeOfDateBegin;
	}
	/**
	 * @param timeOfDateBegin the timeOfDateBegin to set
	 */
	public void setTimeOfDateBegin(String timeOfDateBegin) {
		TimeOfDateBegin = timeOfDateBegin;
	}
	/**
	 * @return the timeOfDateEnd
	 */
	public String getTimeOfDateEnd() {
		return TimeOfDateEnd;
	}
	/**
	 * @param timeOfDateEnd the timeOfDateEnd to set
	 */
	public void setTimeOfDateEnd(String timeOfDateEnd) {
		TimeOfDateEnd = timeOfDateEnd;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * @return the days
	 */
	public String getDays() {
		return Days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(String days) {
		Days = days;
	}
	
	@Override
	public String toString() {
		return "[BeginPeriod: " + BeginPeriod +
				", EndPeriod: " + EndPeriod +
				", TimeOfDateBegin: " + TimeOfDateBegin +
				", TimeOfDateEnd: " + TimeOfDateEnd +
				", Type: " + Type +
				", Days: "+Days + "]";	
	}
	

}
