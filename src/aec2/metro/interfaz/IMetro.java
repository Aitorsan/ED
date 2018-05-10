package aec2.metro.interfaz;

import aec2.metro.implementacion.Estacion;
import aec2.metro.implementacion.TuplaCaminoValor;

public interface IMetro {


	/**
	 * Anade la estacion a la red de metro
	 * 
	 * @param estacion
	 */
	public void anadirEstacion(Estacion estacion);

	/**
	 * Anade una conexion entre dos estaciones existentes en la red de metro
	 * 
	 * @param estacionOrigen
	 *            estacion de origen
	 * @param estacionDestino
	 *            estacion de destino
	 * @param tiempo
	 *            entero que representa el tiempo empleado en ir desde la
	 *            estacionOrigen a la estacionDestino
	 */
	public void anadirConexion(Estacion estacionOrigen, Estacion estacionDestino, int tiempo);

	/**
	 * Metodo que devuelve el camino mas rapido (menor tiempo) para ir desde la
	 * estacionOrigen a la estacionDestino
	 * 
	 * @param estacionOrigen
	 *            estacion de origen
	 * @param estacionDestino
	 *            estacion de destino
	 * @return TuplaCaminoValor: objeto que contiene el camino a seguir y el tiempo
	 *         total a emplear
	 */
	public TuplaCaminoValor caminoMasRapido(Estacion estacionOrigen, Estacion estacionDestino);

	/**
	 * Metodo que devuelve el camino con menos estaciones para ir desde la
	 * estacionOrigen a la estacionDestino
	 * 
	 * @param estacionOrigen
	 *            estacion de origen
	 * @param estacionDestino
	 *            estacion de destino
	 * @return TuplaCaminoValor: objeto que contiene el camino a seguir y el numero
	 *         total de estaciones
	 * 
	 */
	public TuplaCaminoValor caminoMenosEstaciones(Estacion estacionOrigen, Estacion estacionDestino);

	/**
	 * METODO EXTRA 1<br>
	 * Elimina de la red de metro una conexion previamente existente entre las
	 * estaciones estacionOrigen y estacionDestino
	 * 
	 * @param estacionOrigen
	 *            estacion de origen de la conexion
	 * @param estacionDestino
	 *            estacion de destino de la conexión
	 */
	public void eliminarConexion(Estacion estacionOrigen, Estacion estacionDestino);

	/**
	 * METODO EXTRA 2<br>
	 * Elimina una estacion de la red de metro y tambien todas las conexiones
	 * desde/hasta esta estacion en la red completa
	 * 
	 * @param estacion
	 *            a eliminar de la red
	 */
	public void eliminarEstacion(Estacion estacion);

}
