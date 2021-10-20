package la.smartsoft.verint.integracion.db.verint.dto;

import java.time.LocalTime;

public class HorarioDTO {
    private Long idHorario;
    private LocalTime hora;
    private LocalTime inicio;
    private LocalTime fin;

    public HorarioDTO() {
    }

    public HorarioDTO(Long idHorario, LocalTime hora, LocalTime inicio, LocalTime fin) {
        this.idHorario = idHorario;
        this.hora = hora;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Long getIdHorario() {
        return this.idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFin() {
        return this.fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "{" + " idHorario='" + getIdHorario() + "'" + ", hora='" + getHora() + "'" + ", inicio='" + getInicio()
                + "'" + ", fin='" + getFin() + "'" + "}";
    }

}
