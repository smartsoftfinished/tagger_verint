package la.smartsoft.verint.integracion.daswebapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pedro
 *	Clase con la respuesta de la consulta de Dynamic Query
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDynamicQuery {
	
	private @JsonProperty("Sessions")List<SessionVerint> Sessions;
	private @JsonProperty("QDI")List<Qdi> QDI;
	
	/**
	 * @return the sessions
	 */
	public List<SessionVerint> getSessions() {
		return Sessions;
	}
	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(List<SessionVerint> sessions) {
		Sessions = sessions;
	}
	/**
	 * @return the qDI
	 */
	public List<Qdi> getQDI() {
		return QDI;
	}
	/**
	 * @param qDI the qDI to set
	 */
	public void setQDI(List<Qdi> qDI) {
		QDI = qDI;
	}
	
	@Override
	public String toString() {
		
		StringBuilder ses = new StringBuilder("");
		if(Sessions !=null) {
			for(SessionVerint sesion : Sessions) {
				ses.append(sesion.toString()).append("; ");;
			}
		}
		ses.toString();
		
		StringBuilder sbQdi = new StringBuilder("");
		if(QDI !=null) {
			for(Qdi qdi : QDI) {
				sbQdi.append(qdi.toString()).append("; ");
			}
		}
		
		return "[Sessions" + ses.toString() +
				", QDI"+sbQdi.toString() + "]";
		
		
	}
	
 

}
