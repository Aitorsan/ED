package grafos;

import listas.ListaEnlazada;
import excepciones.OperacionIncorrecta;

/**Definicion de un grafo dirigido con pesos.Los otros tipos de grafos se pueden considerar<br> 
 * casos particulares de este.
 */
public interface Grafo {

	/**Inserta un nodo con el elemento pasado por parametro**/
	public void insertarNodo(Object elemento);
	
	/**Inserta una arista entre el nodo origen y el nodo destino con el coste
	 * pasado por parametro, si existen ambos nodos */
	public void insertarArista(Object origen, Object destino, int coste )throws OperacionIncorrecta;
	
	/**Devuelve una lista enlazada con las aristas a los nodos adyacentes<br>
	 * con sus respectivos costes. En caso de no existirel nodo , devuelve<br>
	 * nulo. En caso de existir el nodo y no tener nodos adyacentes, devuelve<br>
	 * una lista vacia.
	 */
	public ListaEnlazada obtenerAdyacentes(Object elemento);

}//end interface
