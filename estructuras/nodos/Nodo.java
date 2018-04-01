package nodos;

public class Nodo {

	/**Elemento que contiene el nodo**/
	protected Object elemento;
	
	/**Enlace al siguiente nodo**/
	protected Nodo enlace;
	
	/**Constructor de la clase**/
	public Nodo(Object elemento) {
		this.elemento = elemento;
	}
	/**Devuelve el elemento del nodo**/
	public Object getElemento() {
		return elemento;
	}
	/**Devuelve el enlace del nodo**/
	public Nodo getEnlace() {
		return enlace;
	}
	/**Establece el enlace pasado por parametro**/
	public void setEnlace(Nodo enlace) {
		this.enlace = enlace;
	}
}
