package la.smartsoft.verint.integracion.db.verint.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import org.apache.log4j.Logger;

import la.smartsoft.verint.integracion.db.rdw.impl.ServicioRDW;
import la.smartsoft.verint.integracion.db.verint.IVerintHorario;
import la.smartsoft.verint.integracion.db.verint.dto.Conexion;
import la.smartsoft.verint.integracion.db.verint.dto.HorarioDTO;

public class ServicioHorario implements IVerintHorario {

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(ServicioRDW.class);

    @SuppressWarnings("static-access")
    @Override
    public HorarioDTO consultarHorario(Date hora) {

        Connection conn = null;
        HorarioDTO horarioDTO = new HorarioDTO();

        try {
            LOG.info("Se intenta obtener conexion a base PostgreSQL");
            conn = new Conexion().crearConexion();
            LOG.info("Conexion Establecida");
            conn.setAutoCommit(false);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

            PreparedStatement smt = conn
                    .prepareStatement("SELECT ID_HORARIO,HORA,INICIO,FIN FROM HORARIO_TAGGER WHERE HORA = '"
                            + format.format(hora) + "' ");

            System.out.print("Se consulto Exitosamente:  ");

            ResultSet rs = smt.executeQuery();

            if (!rs.next()) {

                System.out.print("no hay datos!: ");
            } else {
                horarioDTO.setIdHorario(rs.getLong("ID_HORARIO"));
                horarioDTO.setHora(rs.getObject("HORA", LocalTime.class));
                horarioDTO.setInicio(rs.getObject("INICIO", LocalTime.class));
                horarioDTO.setFin(rs.getObject("FIN", LocalTime.class));
                LOG.info(horarioDTO);
                LOG.info(horarioDTO.getInicio());
                LOG.info(horarioDTO.getFin());
            }

            rs.close();
            smt.close();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.print("No se obtuvo el parametro compos Vacios: ");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.print("No se obtuvo el parametro compos Vacios: ");
        }

        return horarioDTO;
    }
}
