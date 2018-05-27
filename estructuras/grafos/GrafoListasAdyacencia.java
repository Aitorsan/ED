package grafos;

import excepciones.DesbordamientoInferior;
import excepciones.OperacionIncorrecta;
import listas.ListaEnlazada;
import monticulos.ColaPrioridad;
import monticulos.Monticulo;
import pilas.Pila;
import pilas.PilaEnlazada;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Iterator;


/**Implementacion de un grafo con listas de adyacencia**/
public class GrafoListasAdyacencia implements Grafo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2968583975208005382L;
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
		if( tablaNodos.get(origen) == null || tablaNodos.get(destino)==null){
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
			System.out.print("Nodo: "+ entrada.getKey()+". ");

			ListaEnlazada listaAdyacencia = entrada.getValue();
            System.out.print("Lista adyacencia: ");
			listaAdyacencia.primero();

			while(listaAdyacencia.estaDentro()) {

				Arista arista =(Arista) listaAdyacencia.recuperar();

				System.out.print("("+arista.getCoste()+","+arista.getDestino()+") ");
				listaAdyacencia.avanzar();

			}
			System.out.println(".");
			listaAdyacencia.primero();
		}
	}


	/**Busca los caminos minimos de un nodo origen a todos los nodos del grafo con el algoritmo
	 * de Dijkstra. Para ello , devuelve  una tabla que asocia cada nodo a la distancia del camino
	 * minimo y al nodo anterior en dicho camino.
	 */
	public HashMap<Object, CasillaDijkstra> dijkstra(Object origen){
		
		CasillaDijkstra casilla;
		
		Object nodo, nodoAdyacente;
		
		Camino camino, caminoAdyacente;
		
		boolean explorado;
		
		ListaEnlazada adyacentes;
		
		int distancia,distanciaAdyacente,costeArista;
		
		//Se crea e inicializa la tabla de distancias
		HashMap<Object,CasillaDijkstra>tablaDistancias = new HashMap<Object,CasillaDijkstra>();
		
		Iterator<Object> iterador = tablaNodos.keySet().iterator();
		
		while(iterador.hasNext()) {
			casilla = new CasillaDijkstra();
			tablaDistancias.put(iterador.next(), casilla);
		}
		
		/* La distancia del nodo origen se establece a cero y se inserta en una cola
		 * de prioridad vacia. La dimension maxima de esta cola es el cuadrado del
		 * numero de nodos
		 */
		tablaDistancias.get(origen).setDistancia(0);
					
	    camino = new Camino(origen,0);
		
		ColaPrioridad cola = new Monticulo(tablaNodos.keySet().size()*tablaNodos.keySet().size());
		cola.insertar(camino);
		
		//Mientras la cola no se quede vacia, se analizan los nodos
		
		while(!cola.esVacia()) {
			try {
				//Se extrae el camino minimo ignorando los duplicados
				explorado = true;
				while(explorado && !cola.esVacia()) {
					
					camino = (Camino)cola.obtenerMinimo();
					cola.eliminarMinimo();
					nodo = camino.getDestino();
					explorado = tablaDistancias.get(nodo).estaExplorado();
				}
				
				//Se tratan los nodos adyacentes y se añaden a la cola
				if(!explorado) {
					nodo = camino.getDestino();
					tablaDistancias.get(nodo).setExplorado(true);
					distancia = camino.getLongitud();
					adyacentes = this.obtenerAdyacentes(nodo);
					adyacentes.primero();
					
					while(adyacentes.estaDentro()) {
						nodoAdyacente = ((Arista)adyacentes.recuperar()).getDestino();
						costeArista = ((Arista)adyacentes.recuperar()).getCoste();
						distanciaAdyacente = tablaDistancias.get(nodoAdyacente).getDistancia();
						
						if(distancia + costeArista < distanciaAdyacente) {
							tablaDistancias.get(nodoAdyacente).setDistancia(distancia+costeArista);
							tablaDistancias.get(nodoAdyacente).setAnterior(nodo);
							caminoAdyacente = new Camino(nodoAdyacente,distancia+costeArista);
							cola.insertar(caminoAdyacente);
						}
						adyacentes.avanzar();
					}
					adyacentes.primero();//TOODO:AQUI
				}
				
			}catch(DesbordamientoInferior e) {
				e.printStackTrace();
			}
	
		}
		//se devuelve la tabla de distancias
		return tablaDistancias;	
	}//end method



    /**Busca el camino minimo entre un nodo origen y el nodo destino, y devuelve una lista con
     * los nodos del camino.Para ello,usa el metodo de buscar todos los caminos. En caso de no
     * existir un camino, se duevuelve nulo. */

	public ListaEnlazada dijkstra(Object origen,Object destino) {
		
		
		Object nodo;
		
		//se obtiene la tabla de distancias llamando al metodo correspondiente
		HashMap <Object, CasillaDijkstra> distancias = dijkstra(origen);
		
		//Se comprueba que se ha obtenido un camino. De lo contrario, se devuelve nulo
		if(distancias.get(destino).getDistancia() == Integer.MAX_VALUE) {
			return null;
		}
		
		//Se recorre el camino minimo desde el destino y se almacena en una pila
		Pila pila = new PilaEnlazada();
		pila.apilar(destino);
		nodo = destino;
		
		while(!nodo.equals(origen)) {
			
			//Se retrocede el nodo anterior, y se apila
			nodo = distancias.get(nodo).getAnterior();
			pila.apilar(nodo);
		}
		
		
		//Sealmacena en una lista el orden inverso y se devuelve
		
		ListaEnlazada lista = new ListaEnlazada();
		
		while(!pila.esVacia()) {
			
			try{
				lista.insertar(pila.cima());
				pila.desapilar();
				lista.avanzar();
			}catch(DesbordamientoInferior e) {
				e.printStackTrace();
			}
			
		}
		
		lista.primero();
		return lista;
	}//end method





}//fin class
