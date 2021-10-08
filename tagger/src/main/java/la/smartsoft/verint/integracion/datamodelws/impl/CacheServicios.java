package la.smartsoft.verint.integracion.datamodelws.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author ppabon
 * Clase que permite manejar un cache para los servicios
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class CacheServicios {
	
	private transient static Logger logger = Logger.getLogger( CacheServicios.class );

	//Mapa de Servicios Cacheados
	private Map services;

	/**
	 * Constructor
	 */
	public CacheServicios() {
		services = new HashMap<String, Object>();
	}

	/**
	 * Servicio que retorna un servicio Cacheado
	 * @param serviceName
	 * @return
	 */
	public Object getService(String serviceName) {

		Object service = services.get(serviceName);
		if (service != null) {
			logger.info("Returning cached  " + serviceName + " object");
		}

		return service;
	}

	/**
	 * Servicio que adiciona un servicio al cache
	 * @param serviceName
	 * @param newService
	 */
	public void addService(String serviceName, Object newService) {

		if (services.get(serviceName) == null) {
			services.put(serviceName, newService);
		}

	}

}
