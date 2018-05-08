package nodos;

/**
 * Clase que representa un nodo de una lista doblemente enlazada
 *
 */
public class NodoDoble {

	/**Elemento que contiene el nodo**/
	private Object elemento;
	
	/**Enlace al siguiente nodo**/
	private NodoDoble siguiente;
	
	/**Enlace al nodo anterior**/
	private NodoDoble anterior;
	
	/**Constructor de la clase nodo doble**/
	public NodoDoble(Object elemento) {
		this.elemento = elemento;
		anterior = null;
		anterior = null;
	}
	
	/**Devuelve el elemento del nodo**/
	public Object getElemento() {
		return elemento;
	}
	
	/**Establece el elemento pasado por parámetro**/
	public void setElemento(Object elemento) {
		this.elemento = elemento;
	}
	

	/**Devuelve el enlace nodo**/
	public NodoDoble getSiguiente() {
		return this.siguiente;
	}
	
	/**Establece el enlace al siguiente nodo **/
	public void setSiguiente(NodoDoble siguiente) {
		this.siguiente = siguiente;
	}
	
	/**Devuelve al nodo anterior**/
	public NodoDoble getAnterior() {
		return anterior;
	}
	
	/**Establece el nodo anterior **/
	public void setAnterior(NodoDoble anterior) {
		this.anterior = anterior;
	}
	
	
}
