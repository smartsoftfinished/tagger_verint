package la.smartsoft.verint.integracion.db.verint;

import java.util.Date;

import la.smartsoft.verint.integracion.db.verint.dto.HorarioDTO;

public interface IVerintHorario {
    public HorarioDTO consultarHorario(Date hora);
}
