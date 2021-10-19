package la.smartsoft.verint.integracion.db.verint.dto;

import java.text.ParseException;
import java.util.Date;

import la.smartsoft.verint.integracion.db.verint.impl.ServicioAuditoria;
//TEST PARA REGISTRAR UNA AUDITORIA
import la.smartsoft.verint.integracion.db.verint.impl.ServicioParametro;

public class Test {

	public static void main(String[] args) throws ParseException {

		// prueba testing registar una auditoria object
		ServicioAuditoria s = new ServicioAuditoria();
		try {

			Date date = new Date(2021 - 03 - 12);

			AuditoriaTaggingDTO r = new AuditoriaTaggingDTO(date, "4", "765432", "activo", "error", 0l, 0l);

			s.registrarAuditoria(r);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// prueba testing consultar un parametro por id (int)

		ServicioParametro servicioParametro = new ServicioParametro();
		try {

			System.out.print("Parametro " + servicioParametro.consultarParametro(4).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
