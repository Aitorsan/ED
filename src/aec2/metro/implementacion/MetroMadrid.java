package aec2.metro.implementacion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import aec2.metro.interfaz.IMetro;
import colas.Cola;
import colas.ColaEnlazada;
import excepciones.DesbordamientoInferior;
import excepciones.OperacionIncorrecta;
import grafos.*;
import listas.ListaEnlazada;
import pilas.Pila;
import pilas.PilaEnlazada;


public class MetroMadrid  extends GrafoListasAdyacencia implements IMetro {

	/**Constructor de la red MetroMadrid**/
	public MetroMadrid() {
		//Inciamos un grafo vacio
		super();
	}
	
	@Override
	/**Metodo que inserta una nueva estacion en la red del metro*/
	public void anadirEstacion(Estacion estacion) {
		insertarNodo(estacion);		
	}

	/**Metodo que añade una conexion entre dos estacioens existentes en la red de metro
	 * indicando el tiempo empeleado en ir de la estacion origen al destino
	 */
	@Override
	public void anadirConexion(Estacion estacionOrigen, Estacion estacionDestino, int tiempo) {
		
		try {
			insertarArista(estacionOrigen, estacionDestino, tiempo);	
		} catch (OperacionIncorrecta e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**Metodo que devuelve el camino mas rapido (menor tiempo) para ir desde la estacion origen
	 * a la estacion destino y el tiempo empleado en el trayecto.
	 */
	@Override
	public TuplaCaminoValor caminoMasRapido(Estacion estacionOrigen, Estacion estacionDestino) {

		//para poder conseguir el tiempo total del camino mas rapido
		HashMap<Object, CasillaDijkstra> caminos = dijkstra(estacionOrigen);

		//para saber cual es el camino o estaciones que debemos pasar hasta llegar a nuestro destino
		ListaEnlazada estaciones =  dijkstra(estacionOrigen, estacionDestino);

		//tiempo total en recorrer el camino
		int tiempoTotal = 0;
		
		//Si existe la estacion en la lista cogemos su distancia y la sumamos
		if(caminos.containsKey(estacionOrigen)) {				
			
			tiempoTotal = caminos.get(estacionDestino).getDistancia();
		}


		//dejamos la posicion del nodo de la lista en la cabeza 
        estaciones.primero();
        
        TuplaCaminoValor caminoMasRapido = new TuplaCaminoValor(estaciones,tiempoTotal);

		return caminoMasRapido;
	}

	/**Metodo que determina y devuelve le camino con menso estaciones entre dos estaciones 
	 * dadas, ademas del numero de estaciones del camino
	 */

	@Override
	public TuplaCaminoValor caminoMenosEstaciones(Estacion estacionOrigen, Estacion estacionDestino) {
		
		Object nodo;
		int numEstaciones=0;
		//Se obtiene la tabla distancias llamando al metodo correspondiente
		HashMap<Object,CasillaDijkstra>distancia= buscarCaminosMinimos(estacionOrigen);
		
		//Se comprueba qaue se ha obtenido un camino.Si no es asi se devuelve nulo
		if(distancia.get(estacionDestino).getDistancia() == Integer.MAX_VALUE) {
			return null;
		}
		
		//Se recorre el camino minimo desde el destino y se almacena en una pila
		Pila pila = new PilaEnlazada();
		pila.apilar(estacionDestino);

		nodo = estacionDestino;
		
		while(!nodo.equals(estacionOrigen)) {
			//Se retrocede al nodo anterior y se apila
			nodo = distancia.get(nodo).getAnterior();
			pila.apilar(nodo);
			//incrementamos el numero de estaciones que pasamos. La razon de contar aqui el numero de estaciones
			// es que si lo contamos por ejemplo en el loop de desapilar estaremos contando la estacion origen tambien y el resultado
			// sera que pasamos por una estacion mas de la que pasamos en realidad
			++numEstaciones;
		}
		//Se almacena en una lista de orden inverso que sera la lista que añadira a la tuplaCamino
		ListaEnlazada lista = new ListaEnlazada();
		while(!pila.esVacia()) {
			try {
				lista.insertar(pila.cima());
				pila.desapilar();
				lista.avanzar();
			}catch(DesbordamientoInferior e) {
				System.out.println("[ERROR message]:"+e.getMessage()+"\nline: 128\nclass: MetroMadrid\nMethod: public TuplaCaminoValor CaminoMenosEstaciones(Estacion estacionOrigen, Estacion estacionDestino)\n\n");
			}
		}
		
		lista.primero();
		
		TuplaCaminoValor camino = new TuplaCaminoValor(lista,numEstaciones);
				
		return camino;
	}

	/**Metodo que elimina la conexion existente entre dos estacioens de la red de Metro*/
	@Override
	public void eliminarConexion(Estacion estacionOrigen, Estacion estacionDestino) {
		
		ListaEnlazada lista = obtenerAdyacentes(estacionOrigen);
		lista.eliminar(estacionDestino);
		tablaNodos.put(estacionOrigen, lista);
		
	}

	/**Metodo que elimina una estacion de la red de Metro y, por consiguiente, todas
	 * las posibles conexiones que existan con otras estaciones de la red, tanto entrantes
	 * como salientes
	 */
	@Override
	public void eliminarEstacion(Estacion estacion) {
		
		tablaNodos.remove(estacion);
	
		Iterator<Entry<Object,ListaEnlazada>> it = tablaNodos.entrySet().iterator();
		
		while(it.hasNext()) {
			Entry<Object,ListaEnlazada> actualEntry= it.next();
			Estacion key = (Estacion)actualEntry.getKey();
			ListaEnlazada listaActual = actualEntry.getValue();
			listaActual.primero();
			while(listaActual.estaDentro()) {
				
				Arista aris = (Arista)listaActual.recuperar();
				if(aris.getDestino().equals(estacion)) {
					listaActual.eliminar(aris);
					listaActual.primero();
					tablaNodos.put(key, listaActual);
				}
				listaActual.avanzar();
			}
		}
	}
	/**
	 * Imprime la red del metro de 
	 */
	public void imprimirRed() {
		imprimir();
	}
	
	/************************************************************************************
	 * 
	 *                        METODOS PRIVADOS
	 * 
	 * ***********************************************************************************/
	
	/**
	 * BSF o recorrido en anchura modificado para poder usarlo con un grafo 
	 * con pesos. Solo se explica la parte del codigo añadida o modificada,
	 * ya que el resto ha sido copiada del manual de referencia.
	 * @param estacionOrigen
	 * @return HashMap<Object,CasillaDijstra> tabla de caminos.
	 */
	private HashMap<Object,CasillaDijkstra>buscarCaminosMinimos(Estacion estacionOrigen){
		
		Object nodo, nodoAdyacente;
		ListaEnlazada adyacentes;
		int distancia,distanciaAdyacente;
		
		/*
		 * Se obtiene la tabla de distancias del algoritmo de dijstkstra.
		 * Esto es debido a que usamos un grafo con pesos para almacenar
		 * nuestra informacion. Por lo tanto, para obtener una tabla de distancias
		 * llamamos a este metodo para que nos de una tabla con las distancias
		 * desde la estacion origen a la destino.
		 * El problema es que esta tabla contiene unos pesos que no nos interesan, ya
		 * que queremos contar el numero de aristas sin tener en cuenta los pesos. Para 
		 * esto iteramos sobre las casillas y las reiniciamos a infinito para las distancias
		 * y a null para sus nodos anteriores. De este modo ya podremos obtener la tabla 
		 * necesaria para poder utilizar el algoritmo BSF o recorrido en anchura, y devolver
		 * una tabla de distancias apropiada.
		 */
		HashMap<Object,CasillaDijkstra>tablaDistancias = dijkstra(estacionOrigen);
		Iterator<Object> it = tablaDistancias.keySet().iterator();
		//iteramos y reiniciamos las casillas
		while(it.hasNext()) {
			Object next = it.next();
			CasillaDijkstra c = tablaDistancias.get(next);
			c.setDistancia(Integer.MAX_VALUE);
			c.setAnterior(null);
			tablaDistancias.put(next,c);
		}
		
		/*La distancia del nodo origen se establece a cero y se inserta en una cola vacìa*/
		tablaDistancias.get(estacionOrigen).setDistancia(0);
		
		Cola cola = new ColaEnlazada();
		cola.insertar(estacionOrigen);
		
		while(!cola.esVacia()) {
			
			//Se extrae el nodo y se consulta su distancia
			try {
				
				nodo = cola.primero();
				cola.quitarPrimero();
				distancia = tablaDistancias.get(nodo).getDistancia();
				
				//Se tratan toods los nodos adyacentes no marcados y se insertan en la cola
				adyacentes = obtenerAdyacentes(nodo);
				adyacentes.primero();
				
				while(adyacentes.estaDentro()) {
					nodoAdyacente = ((Arista)adyacentes.recuperar()).getDestino();
					distanciaAdyacente = tablaDistancias.get(nodoAdyacente).getDistancia();
					
					if( distanciaAdyacente == Integer.MAX_VALUE) {
						tablaDistancias.get(nodoAdyacente).setDistancia(distancia+1);
						tablaDistancias.get(nodoAdyacente).setAnterior(nodo);
						cola.insertar(nodoAdyacente);
					}
					adyacentes.avanzar();			
				}
	
			} catch (DesbordamientoInferior e) {
				System.out.println("[ERROR message]:"+e.getMessage()+"\nline: 189-209\nclass: MetroMadrid\nMethod:private method can't acces information\n\n");
			}

		}
		
		return tablaDistancias;
	}
	

}//end class
