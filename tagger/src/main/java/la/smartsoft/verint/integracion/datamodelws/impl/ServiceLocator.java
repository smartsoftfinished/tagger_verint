package la.smartsoft.verint.integracion.datamodelws.impl;

/**
 * Clase que permite obtener una referencia a un servicio
 * @author ppabon
 */
public class ServiceLocator {
	
	//objeto que maneja el cache de los servicios
	private static CacheServicios cache;

	static {
		cache = new CacheServicios();
	}

	/**
	 * M�todo que retorna un servicio dado su identificador
	 * @param serviceId
	 * @return
	 */
	public static Object getService(String serviceId) {
		
		//Se intenta obtener el servicio a partir del cache
		return cache.getService(serviceId);

	}
	
	/**
	 * M�todo que retorna un servicio dado su identificador
	 * @param jndiName
	 * @return
	 */
	public static void addService(String serviceId, Object service) {
		cache.addService(serviceId, service);
	}
}
