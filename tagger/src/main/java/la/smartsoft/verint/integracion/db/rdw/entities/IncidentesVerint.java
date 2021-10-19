package la.smartsoft.verint.integracion.db.rdw.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "DATOS_BASICOS")
public class IncidentesVerint {
	@Id
	@Column(name = "Númeroincidente")
	private String IncidentNumber;

	@Column(name = "LLAMANTE_Teléfono")
	private String llamanteTelefono;

	@Column(name = "FechaIncidente")
	private Date FechaIncidente;

	@Column(name = "Duration")
	private String duracion;

	// @Column
	// private String Localidad;

	// @Column
	// private String Jurisdiccion;

	// @Column
	// private int X_COORD;

	// @Column
	// private int Y_COORD;

	// @Column
	// private Date Timestamp;

	// @Column
	// private int PrioridadInicial;

	// @Column
	// private Date FechaCierre;

	// @Column
	// private Long Duracion;

	// @Column
	// private String RecursoAsignado;

	// @Column
	// private String NumeroTelefonoIncidente;

	// @Column
	// private String Barrio;

	public String getIncidentNumber() {
		return this.IncidentNumber;
	}

	public void setIncidentNumber(String IncidentNumber) {
		this.IncidentNumber = IncidentNumber;
	}

	public String getLlamanteTelefono() {
		return this.llamanteTelefono;
	}

	public void setLlamanteTelefono(String llamanteTelefono) {
		this.llamanteTelefono = llamanteTelefono;
	}

	public Date getFechaIncidente() {
		return this.FechaIncidente;
	}

	public void setFechaIncidente(Date FechaIncidente) {
		this.FechaIncidente = FechaIncidente;
	}

	public String getDuracion() {
		return this.duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

}
