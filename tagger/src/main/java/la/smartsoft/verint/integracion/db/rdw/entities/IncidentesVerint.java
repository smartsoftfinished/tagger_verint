package la.smartsoft.verint.integracion.db.rdw.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "DATOS_BASICOS")
public class IncidentesVerint {

	@Column
	private Date FechaIncidente;

	@Column
	private String TipoIncidente;

	@Column
	private String Agencia;

	@Column
	private String Localidad;

	@Column
	private String Jurisdiccion;

	@Column
	private int X_COORD;

	@Column
	private int Y_COORD;

	@Column
	private Date Timestamp;

	@Id
	@Column(name = "Númeroincidente")
	private String IncidentNumber;

	@Column
	private int PrioridadInicial;

	@Column
	private Date FechaCierre;

	@Column
	private Long Duracion;

	@Column
	private String RecursoAsignado;

	@Column
	private String NumeroTelefonoIncidente;

	@Column
	private String Barrio;
}
