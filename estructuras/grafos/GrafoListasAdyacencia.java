package grafos;

import excepciones.OperacionIncorrecta;
import listas.ListaEnlazada;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Iterator;
/**Implementacion de un grafo con listas de adyacencia**/
public class GrafoListasAdyacencia implements Grafo{

	/**Tabla de dispersion que contiene los nodos del grafo y los asocia con sus listas de adyacencia**/
	protected HashMap<Object,ListaEnlazada> tablaNodos;
	
	/**Constructor **/
	public GrafoListasAdyacencia() {
		tablaNodos = new HashMap<Object,ListaEnlazada>();
	}
	
	/**Insertar un nodo con el elemento pasado por parametro **/
	@Override
	public void insertarNodo(Object elemento) {
		//Inserta el nuevo nodo y lo incluye con un alista de adyacencia vacia
		ListaEnlazada listaAdyacencia = new ListaEnlazada();
		tablaNodos.put(elemento,listaAdyacencia);
		
	}

	/**Inserta una arista entre el nodo origen y el nodo detsino con el coste pasado por parametro**/
	@Override
	public void insertarArista(Object origen, Object destino, int coste) throws OperacionIncorrecta {
		//se comprueba que existan los nodos
		if( tablaNodos.get(origen) == null || tablaNodos.get(destino)== null){
			throw new OperacionIncorrecta("No se puede insertar una arista entre nodos inexistentes");
		}
		
		//Se inserta la arista
		ListaEnlazada listaAdyacencia = tablaNodos.get(origen);
		Arista arista = new Arista(coste,destino);
		listaAdyacencia.insertar(arista);

	}

	/**
	 * Devuelve una lista enlazada con las aristas a los nodos adyacentes con
	 * sus respectivos costes. En caso de no existir el nodo , devuelve nulo.
	 * En caso de existir el nodo y no tener nodos adyacentes, devuelve una lista
	 * vacia
	 */
	@Override
	public ListaEnlazada obtenerAdyacentes(Object elemento) {
		
		ListaEnlazada listaAdyacencia = tablaNodos.get(elemento);	
		return listaAdyacencia;
	}
	
	/**Muestra por pantalla el grafo**/
	public void imprimir() {
		Set<Entry<Object,ListaEnlazada>> conjunto = tablaNodos.entrySet();

		Iterator <Entry<Object,ListaEnlazada>>iterador = conjunto.iterator();

		System.out.println("--Grafo--");

		while(iterador.hasNext()) {

			Entry<Object,ListaEnlazada>entrada= iterador.next();
			System.out.println("Nodo: "+ entrada.getKey());

			ListaEnlazada listaAdyacencia = entrada.getValue();

			listaAdyacencia.primero();

			while(listaAdyacencia.estaDentro()) {

				Arista arista =(Arista) listaAdyacencia.recuperar();

				System.out.print("("+arista.getCoste()+","+arista.getDestino()+")");
				listaAdyacencia.avanzar();

			}
			System.out.println(".");
		}
	}


	
}
