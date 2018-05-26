package aec2.metro.implementacion;

import java.io.Serializable;
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

/**
 * Class MetroMadrid, esta clase representa el metro de madrid mediante el uso de un grafo Dirigido con pesos.
 * @author Aitor sanmartin ferreira
 *
 */
public class MetroMadrid  extends GrafoListasAdyacencia implements IMetro, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4777426820851040383L;

	/**Constructor de la red MetroMadrid**/
	public MetroMadrid() {
		//Inciamos un grafo vacio
		super();
	}
	
	@Override
	/***********************************************************************************
	 * Metodo que inserta una nueva estacion en la red del metro
	 ***********************************************************************************/
	public void anadirEstacion(Estacion estacion) {
		insertarNodo(estacion);		
	}

	/***********************************************************************************
	 * Metodo que añade una conexion entre dos estacioens existentes en la red de metro
	 * indicando el tiempo empeleado en ir de la estacion origen al destino
	 ***********************************************************************************/
	@Override
	public void anadirConexion(Estacion estacionOrigen, Estacion estacionDestino, int tiempo) {
		
		try {
			insertarArista(estacionOrigen, estacionDestino, tiempo);	
		} catch (OperacionIncorrecta e) {
			System.out.println(e.getMessage());
		}
		
	}

	/***********************************************************************************
	 * Metodo que devuelve el camino mas rapido (menor tiempo) para ir desde 
	 * la estacion origen a la estacion destino y el tiempo empleado en el trayecto.
	 ***********************************************************************************/
	@Override
	public TuplaCaminoValor caminoMasRapido(Estacion estacionOrigen, Estacion estacionDestino) {

		/*Pasos:
		 * 1. primero llamamos al metodo dikstra que nos devuelve una lista con los caminos que hay que recorrer con el minimo coste en grafos con pesos
		 *    acto seguido se pasa como primer parametro para la creacion de una nueva tublaCaminoValor.
		 * 2.Para saber el tiempo que necesitamos tenemos que llamar al metodo que nos devuelve una tabla con todas las distancias desde
		 *   la estacion origen a todos los puntos del grafo. Despues de esto accedemos a la distancia que nos interesa que es la de la
		 *   estacion destino.
		 * 3. retornamos la nueva tuplaCaminoValor, como no necesitamos hacer nada con el objeto dentro de la funcion lo creamos in- place y lo retornamos
		 */
        return new TuplaCaminoValor(dijkstra(estacionOrigen,estacionDestino),dijkstra(estacionOrigen).get(estacionDestino).getDistancia());

	}

	/**************************************************************************************
	 * Metodo que determina y devuelve le camino con menso estaciones entre dos estaciones 
	 * dadas, ademas del numero de estaciones del camino
	 **************************************************************************************/
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
		
		return  new TuplaCaminoValor(lista,numEstaciones);
				
	}

	/************************************************************************************
	 *  
	 * Metodo que elimina la conexion existente entre dos estaciones de la red de Metro
	 * 
	 * **********************************************************************************/
	@Override
	public void eliminarConexion(Estacion estacionOrigen, Estacion estacionDestino) {
		
        //obtenemos la lista con conexiones de la estacion origen
		ListaEnlazada listaConexionOrigen = tablaNodos.get(estacionOrigen);
	    //situamos el nodo iterador al principio de la lista
		listaConexionOrigen.primero();
	    
		//iteramos hasta el final de la lista
		while(listaConexionOrigen.estaDentro()) {
	    	 
			//accedemos a las estaciones una a una
			Arista actual = (Arista)listaConexionOrigen.recuperar();
	    	//si alguna de las estaciones es la estacion destino eliminamos esta conexion de la lista
			if( actual.getDestino() == estacionDestino) {
	    		 listaConexionOrigen.eliminar(actual);
	    	 }
	    	 //avanzamos al siguiente nodo de la lista o estacion
	    	 listaConexionOrigen.avanzar();
	     }
	   
	     
	}

	/**************************************************************************************
	 * Metodo que elimina una estacion de la red de Metro y, por consiguiente, todas
	 * las posibles conexiones que existan con otras estaciones de la red, tanto entrantes
	 * como salientes
	 **************************************************************************************/
	@Override
	public void eliminarEstacion(Estacion estacion) {
		//eliminamos la estacion del grafo
		tablaNodos.remove(estacion);
	
		// accedemos a las entradas del grafo, las estaciones con sus conexiones
		Iterator<Entry<Object,ListaEnlazada>> it = tablaNodos.entrySet().iterator();
		
		//iteramos sobre las entradas del hashmap donde estan las estaciones asociadas con su lista de conexiones
		while(it.hasNext()) {
			//accedemos una a una a las entradas
			Entry<Object,ListaEnlazada> actualEntry= it.next();
			//obtenemos su lista
			ListaEnlazada listaActual = actualEntry.getValue();
			//situamos el nodo iterador en el principio de la lista
			listaActual.primero();
			//iteramos hasta el final de la lista
			while(listaActual.estaDentro()) {
				//recuperamos las conexiones 
				Arista aris = (Arista)listaActual.recuperar();
				// si alguna de las conexiones contiene la estacion eliminada
				// entonces eliminamos esa conexion ya que la estacion ya no existe
				if(aris.getDestino().equals(estacion)) {
					listaActual.eliminar(aris);
				}
				//avanzamos
				listaActual.avanzar();
			}
		
		}
	}
	/***********************************************************************************
	 * Imprime la red del metro 
	 **********************************************************************************/
	public void imprimirRed() {
		super.imprimir();
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
	            adyacentes.primero();//TODO:AQUI
			} catch (DesbordamientoInferior e) {
				System.out.println("[ERROR message]:"+e.getMessage()+"\nline: 189-209\nclass: MetroMadrid\nMethod:private method can't acces information\n\n");
			}

		}
		
		return tablaDistancias;
	}
	
	/************************************************************************************
	 * 
	 *                     OTROS METODOS EXTRA
	 * 
	 * ***********************************************************************************/

	/**
	 * Este metodo elimina, solo y unicamente, todas las conexiones que puedan existir hacia esta estacion.
	 * Sin embargo la estacion conserva las conexiones con otras estaciones.
	 * @param estacion
	 */
	public void eliminarConexionesAEstacion(Estacion estacion) {
		
		
		      //recorremos las entradas de la tabla hash (estacion, Lista conexiones)
		      Iterator<Entry<Object, ListaEnlazada>>it = tablaNodos.entrySet().iterator();
		
		      //mientras hay elementos en la lista
		      while(it.hasNext()) {
		    	  //accedemos a las entradas una a una
		    	  Entry<Object,ListaEnlazada> entrada = it.next();
		    	  //obtenemos la respectiva lista con las conexiones 
		    	  ListaEnlazada conexiones = entrada.getValue();
		    	  //obtenemos el nombre de la estacion que estamos explorando
		    	  Estacion estacionAexplorar = (Estacion)entrada.getKey();
		    	  //situamos le puntero en la cabecera de la lista de conexiones
		    	  conexiones.primero();
		    	  //buscamos dentro de la lista una a una las conexiones
		    	  while(conexiones.estaDentro()) {
		    		    // recuperamos la arista que contiene el nombre de la estacion con la que hay conexion y su distancia
		    		    Arista conexion = (Arista)conexiones.recuperar();
		    		    //si el destino coincide con la estacion para la cual queremos eliminar todas las rutas que llegan hacia ella
		    		    //eliminamos la conexion
		    		    if( conexion.getDestino().equals(estacion)) {
		    		    	this.eliminarConexion(estacionAexplorar, estacion);
		    		    }
		    		  
		    		    // para evitar que se salga de la lista comprobamos que sigue dentro el puntero y avanzamos
		    		    if(conexiones.estaDentro())
		    		      conexiones.avanzar();
		    	  }
		      }
	}
	
	/**
	 * Podria darse el caso de que hubiera una averia en esa estación temporalmente
	 * podriamos desabilitar todas las conexiones hacia esta.Se eliminan todas las 
	 * conexiones hacia esta estacion y todas las conexiones de esta estacion hacia 
	 * otras estaciones.Pero no se elimina la estacion.
	 * @param estacion
	 */
	public void aislarEstacion(Estacion estacion) {
		
		// reusamos el metodo para eliminar todas las estaciones que apunten a esta
		eliminarConexionesAEstacion(estacion);
		// eliminamos todas las conexiones de la estacion esto es constante solo hay que crear una nueva lista de estaciones vacia
		// y sustituirla el garbage collector se encarga de la antigua lista. Esto no seria posible en otros lenguajes como c++
		tablaNodos.put(estacion, new ListaEnlazada());
		
	}
	
	
	/**
	 * Metodo para la gui para recojer el nombre de las estaciones
	 */
	public String[] getStations() {
		
	
		Estacion[] e=  tablaNodos.keySet().toArray(new Estacion[0]);
		String [] s = new String[e.length];
     
		for(int i = 0; i < e.length;++i) {
		 
			s[i] = e[i].getNombre();
		}
		return s;
		
	}

}//end class
