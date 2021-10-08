package la.smartsoft.verint.integracion.db.verint.dto;

import java.util.Date;

//clase dto auditoria  tagging

public class AuditoriaTaggingDTO {

	private static int count = 0;
	private int idAuditoria ;
	private Date fechaRegistro;
	private String incidentNumber;
	private String numeroTelefono;
	private String estado;
	private String mensajeError;
	
	
	
	
	public AuditoriaTaggingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuditoriaTaggingDTO(Date fechaRegistro, String incidentNumber, String numeroTelefono,
			String estado, String mensajeError) {
		
		
		
		setIdAuditoria(++count);
		this.fechaRegistro = fechaRegistro;
		this.incidentNumber = incidentNumber;
		this.numeroTelefono = numeroTelefono;
		this.estado = estado;
		this.mensajeError = mensajeError;
	}
	public int getIdAuditoria() {
		return idAuditoria;
	}
	public void setIdAuditoria(int idAuditoria) {
		this.idAuditoria = idAuditoria;
	}	

	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getIncidentNumber() {
		return incidentNumber;
	}
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	@Override
	public String toString() {
		return "AuditoriaTaggingDTO [idAuditoria=" + idAuditoria + ", fechaRegistro=" + fechaRegistro
				+ ", incidentNumber=" + incidentNumber + ", numeroTelefono=" + numeroTelefono + ", estado=" + estado
				+ ", mensajeError=" + mensajeError + "]";
	}
	
	
	
	
	
	
}
