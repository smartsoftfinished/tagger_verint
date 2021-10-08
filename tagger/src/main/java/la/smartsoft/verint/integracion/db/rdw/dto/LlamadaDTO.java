package la.smartsoft.verint.integracion.db.rdw.dto;

import java.util.Date;

/**
 * @author pedro
 * DTO con la información de las Llamadas a Tagguear
 */
public class LlamadaDTO {
	
	String incidentNumber;
	String numeroTelefonoIncidente;
	Long duracion;
	Date fechaRegistro;
	
	/**
	 * @return the incidentNumber
	 */
	public String getIncidentNumber() {
		return incidentNumber;
	}
	/**
	 * @param incidentNumber the incidentNumber to set
	 */
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}
	/**
	 * @return the numeroTelefonoIncidente
	 */
	public String getNumeroTelefonoIncidente() {
		return numeroTelefonoIncidente;
	}
	/**
	 * @param numeroTelefonoIncidente the numeroTelefonoIncidente to set
	 */
	public void setNumeroTelefonoIncidente(String numeroTelefonoIncidente) {
		this.numeroTelefonoIncidente = numeroTelefonoIncidente;
	}
	/**
	 * @return the duracion
	 */
	public Long getDuracion() {
		return duracion;
	}
	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}
	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Override
	public String toString() {
		return "[incidentNumber:" + incidentNumber +
				", numeroTelefonoIncidente:" + numeroTelefonoIncidente +
				", duracion:" + duracion +
				", fechaRegistro:"+(fechaRegistro !=null ? fechaRegistro.toString() : "NULL") +"]";
	}

}
