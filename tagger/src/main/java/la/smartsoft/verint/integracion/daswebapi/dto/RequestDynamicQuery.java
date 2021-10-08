package la.smartsoft.verint.integracion.daswebapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 *	Clase con los atributos que se requieren para 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDynamicQuery {
	
	private @JsonProperty("UserID") String UserID;
	private @JsonProperty("ConditionsString") String ConditionsString;
	private @JsonProperty("period") Period period;
	private @JsonProperty("RequestedGroups") List<String> RequestedGroups;
	private @JsonProperty("RequestedColumns") String RequestedColumns;
	private @JsonProperty("CommandTimoutSeconds") String CommandTimoutSeconds;
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return UserID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.UserID = userID;
	}
	/**
	 * @return the conditionsString
	 */
	public String getConditionsString() {
		return ConditionsString;
	}
	/**
	 * @param conditionsString the conditionsString to set
	 */
	public void setConditionsString(String conditionsString) {
		ConditionsString = conditionsString;
	}
	/**
	 * @return the period
	 */
	public Period getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(Period period) {
		this.period = period;
	}
	/**
	 * @return the requestedGroups
	 */
	public List<String> getRequestedGroups() {
		return RequestedGroups;
	}
	/**
	 * @param requestedGroups the requestedGroups to set
	 */
	public void setRequestedGroups(List<String> requestedGroups) {
		RequestedGroups = requestedGroups;
	}
	/**
	 * @return the requestedColumns
	 */
	public String getRequestedColumns() {
		return RequestedColumns;
	}
	/**
	 * @param requestedColumns the requestedColumns to set
	 */
	public void setRequestedColumns(String requestedColumns) {
		RequestedColumns = requestedColumns;
	}
	/**
	 * @return the commandTimoutSeconds
	 */
	public String getCommandTimoutSeconds() {
		return CommandTimoutSeconds;
	}
	/**
	 * @param commandTimoutSeconds the commandTimoutSeconds to set
	 */
	public void setCommandTimoutSeconds(String commandTimoutSeconds) {
		CommandTimoutSeconds = commandTimoutSeconds;
	}
	
	@Override
	public String toString() {
		return "[UserID: " + UserID +
				", ConditionsString: " + ConditionsString +
				", period: " + (period !=null ?period.toString() : "NULL" ) +
				", RequestedGroups: " + (RequestedGroups!=null ? RequestedGroups.toString() : "NULL" ) +
				", RequestedColumns: " + RequestedColumns +
				", CommandTimoutSeconds: "+CommandTimoutSeconds + "]";
	}

}
