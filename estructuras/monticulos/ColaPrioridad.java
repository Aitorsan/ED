package monticulos;

import java.io.Serializable;

import excepciones.DesbordamientoInferior;
/**Esta interfaz define las operaciones de una cola de prioridad**/
public interface ColaPrioridad extends Serializable{

	/**Inserta un elemento en la cola de prioridad. El nivel de
	 * prioridad se puede comprar usando la comparacion propioa
	 * de los elementos.Se asume que el elemento que resulta menor
	 * de la comparacion tiene mayor nivel de prioridad
	 */
	@SuppressWarnings("rawtypes")
	public void insertar( Comparable elemento) ;
	
	/**Devuelve el elemento minimo de la cola de prioridad
	 * En concreto este es el prmer elemento de la cola
	 * Esta operacion no altera la cola de prioridad
	 */
	@SuppressWarnings("rawtypes")
	public Comparable obtenerMinimo()throws DesbordamientoInferior;
	
	/**Elimina el elemento minimo de la cola de prioridad
	 * que es el primer elemento de la cola de prioridad
	 */
	void eliminarMinimo()throws DesbordamientoInferior;
	
	/**Determina si la cola de prioridad esta vacia. Es decir
	 * devuelve cierto si y solo si la cola de prioridad no tiene
	 * ningun elemento.
	 */
	boolean esVacia();
}
