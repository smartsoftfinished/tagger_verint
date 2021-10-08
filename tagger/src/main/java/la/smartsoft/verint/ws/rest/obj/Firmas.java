package la.smartsoft.verint.ws.rest.obj;

import java.util.List;

/**
 * @author Freddy Sabogal
 *
 */
public class Firmas {
	
	private List<String> firmas;
	

	public Firmas() {
	}

	public Firmas(List<String> firmas) {
		this.firmas = firmas;
	}

	public List<String> getFirmas() {
		return firmas;
	}

	public void setFirmas(List<String> firmas) {
		this.firmas = firmas;
	}
	
}
