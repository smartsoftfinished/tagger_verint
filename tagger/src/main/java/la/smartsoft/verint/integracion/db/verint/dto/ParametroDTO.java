package la.smartsoft.verint.integracion.db.verint.dto;

import java.util.Date;

//clase dto parametro ok
public class ParametroDTO {

	public static int SEGUNDOS_ATRAS = 1;
	public static int RANGO_CONSULTA = 2;
	public static int SEGUNDOS_CONSULTA = 3;
	public static int SEGUNDOS_DESP_CONSULTA = 4;
	public static int REGISTROS_MAXIMOS = 5;
	public static int TABLA_RDW = 6;
	public static int COL_NUM_INCIDENTE = 7;
	public static int COL_TELEFONO = 8;
	public static int COL_FECHA_INICIO = 9;
	public static int COL_FECHA_FIN = 10;
	public static int MAX_INTENTOS_TAGGING = 11;
	public static int DIAS_VERINT = 12;

	private static int count = 0;
	private int idParametro;
	private String nombre;
	private String descripcion;
	private String valor;
	private String Estado;
	private Date fechaCreacion;
	private String tipoParametro;
	private String claseParametro;

	public ParametroDTO(String nombre, String descripcion, String valor, String estado, Date fechaCreacion,
			String tipoParametro, String claseParametro) {
		super();

		setIdParametro(++count);
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		Estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.tipoParametro = tipoParametro;
		this.claseParametro = claseParametro;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		ParametroDTO.count = count;
	}

	public int getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ParametroDTO() {
		super();
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public String getClaseParametro() {
		return claseParametro;
	}

	public void setClaseParametro(String claseParametro) {
		this.claseParametro = claseParametro;
	}

	@Override
	public String toString() {
		return "ParametroDTO [idParametro=" + idParametro + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", valor=" + valor + ", Estado=" + Estado + ", fechaCreacion=" + fechaCreacion + ", tipoParametro="
				+ tipoParametro + ", claseParametro=" + claseParametro + "]";
	}

}
