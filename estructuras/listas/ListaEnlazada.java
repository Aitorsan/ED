package listas;
import nodos.Nodo;


/**Clase que implementa una lista enlazada**/
public class ListaEnlazada {
	
	/**Enlace al nodo cabecera*/
	protected Nodo cabecera;
	
	/**Enlace al nodo de la posicion actual**/
	protected Nodo actual;
	
	/**Constructor de la clase.Construye una lista vacia que empieza con el nodo cabecera*/
	public ListaEnlazada(){
		//Se crea el nodo cabecera;
		cabecera = new Nodo(null);
		cabecera.setEnlace(null);
		//Se establece la posicion actual en la cabecera
		actual = cabecera;
	}
	
	
	/**Inserta el elemento pasado por parametro despues de la posicion actua**/
	public void insertar(Object elemento) {
		//Se crea un nodo y el enlace con el siguiente nodo actual
		Nodo nuevo = new Nodo(elemento);
		
		nuevo.setEnlace(actual.getEnlace());
		
		//Se actualiza el enlace del nodo actual
		actual.setEnlace(nuevo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**Muestra por pantalla la lista de objectos**/
	public void imprimir() {
		
		Nodo nodoIterador = cabecera;
		System.out.println("Lista:");
		
		while(nodoIterador != null) {
			System.out.print("["+nodoIterador.getElemento()+"]->");
			nodoIterador = nodoIterador.getEnlace();
	
		}
		System.out.println();
}

}
