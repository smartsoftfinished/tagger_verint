package la.smartsoft.verint.integracion.db.rdw.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "INCIDENTES_VERINT")
public class IncidentesVerint {
	
	@Column
	private Date FechaCreacion;
	
	@Column
	private int PrioridadInicial;
	
	@Column
	private String Agencia;
	
	@Column
	private Date FechaCierre;
	
	@Id
	@Column
	private String IncidentNumber;
	
	@Column
	private String TipoIncidente;
	
	@Column
	private Long Duracion;
	
	@Column
	private String RecursoAsignado;
	
	@Column
	private String Localidad;
	
	@Column
	private String NumeroTelefonoIncidente;
	
	@Column
	private int X_COORD;
	
	@Column
	private int Y_COORD;
	
	@Column
	private String Jurisdiccion;
	
	@Column
	private String Barrio;

	public Date getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public int getPrioridadInicial() {
		return PrioridadInicial;
	}

	public void setPrioridadInicial(int prioridadInicial) {
		PrioridadInicial = prioridadInicial;
	}

	public String getAgencia() {
		return Agencia;
	}

	public void setAgencia(String agencia) {
		Agencia = agencia;
	}

	public Date getFechaCierre() {
		return FechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		FechaCierre = fechaCierre;
	}

	public String getIncidentNumber() {
		return IncidentNumber;
	}

	public void setIncidentNumber(String incidentNumber) {
		IncidentNumber = incidentNumber;
	}

	public String getTipoIncidente() {
		return TipoIncidente;
	}

	public void setTipoIncidente(String tipoIncidente) {
		TipoIncidente = tipoIncidente;
	}

	public Long getDuracion() {
		return Duracion;
	}

	public void setDuracion(Long duracion) {
		Duracion = duracion;
	}

	public String getRecursoAsignado() {
		return RecursoAsignado;
	}

	public void setRecursoAsignado(String recursoAsignado) {
		RecursoAsignado = recursoAsignado;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getNumeroTelefonoIncidente() {
		return NumeroTelefonoIncidente;
	}

	public void setNumeroTelefonoIncidente(String numeroTelefonoIncidente) {
		NumeroTelefonoIncidente = numeroTelefonoIncidente;
	}

	public int getX_COORD() {
		return X_COORD;
	}

	public void setX_COORD(int x_COORD) {
		X_COORD = x_COORD;
	}

	public int getY_COORD() {
		return Y_COORD;
	}

	public void setY_COORD(int y_COORD) {
		Y_COORD = y_COORD;
	}

	public String getJurisdiccion() {
		return Jurisdiccion;
	}

	public void setJurisdiccion(String jurisdiccion) {
		Jurisdiccion = jurisdiccion;
	}

	public String getBarrio() {
		return Barrio;
	}

	public void setBarrio(String barrio) {
		Barrio = barrio;
	}
	
	
}
