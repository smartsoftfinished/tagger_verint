package la.smartsoft.verint.integracion.db.verint.dto;

import java.util.Date;

//clase dto auditoria  tagging

public class AuditoriaTaggingDTO {

	private static int count = 0;
	private int idAuditoria;
	private Date fechaRegistro;
	private String incidentNumber;
	private String numeroTelefono;
	private String estado;
	private String mensajeError;
	private Long sessionId;
	private Long siteId;
	private Date fechaIncidente;
	private Long intentosTagging;

	public AuditoriaTaggingDTO() {
		super();
	}

	public AuditoriaTaggingDTO(Date fechaRegistro, String incidentNumber, String numeroTelefono, String estado,
			String mensajeError, Long sessionId, Long siteId, Date fechaIncidente, Long intentosTagging) {
		this.fechaRegistro = fechaRegistro;
		this.incidentNumber = incidentNumber;
		this.numeroTelefono = numeroTelefono;
		this.estado = estado;
		this.mensajeError = mensajeError;
		this.sessionId = sessionId;
		this.siteId = siteId;
		this.fechaIncidente = fechaIncidente;
		this.intentosTagging = intentosTagging;
	}

	public Date getFechaIncidente() {
		return this.fechaIncidente;
	}

	public void setFechaIncidente(Date fechaIncidente) {
		this.fechaIncidente = fechaIncidente;
	}

	public Long getIntentosTagging() {
		return this.intentosTagging;
	}

	public void setIntentosTagging(Long intentosTagging) {
		this.intentosTagging = intentosTagging;
	}

	public Long getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(Long idSesion) {
		this.sessionId = idSesion;
	}

	public Long getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Long idSite) {
		this.siteId = idSite;
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
