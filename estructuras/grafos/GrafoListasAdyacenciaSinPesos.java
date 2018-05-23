package grafos;


import excepciones.*;
import listas.ListaEnlazada;

import java.util.HashMap;
import java.util.Iterator;

import colas.Cola;
import colas.ColaEnlazada;
public class GrafoListasAdyacenciaSinPesos extends GrafoListasAdyacencia{

	
	/**Inserta un arista entre el nodo origen y el nodo destino**/
	public void insertarArista(Object origen,Object destino)throws OperacionIncorrecta{
		super.insertarArista(origen, destino, 1);
	}
	
	
	/**Operacion no soportada en un grafo sin pesos**/

   public void insertarArista(Object origen,Object destino, int coste)throws OperacionIncorrecta{
	throw new OperacionIncorrecta("Esta operacion no se puede aplicar con grafos sin pesos");
	}
	
	
	/**Busca los caminos minimos de un nodo origen a todos los nodos del grafo.
	 * Para ello, devuelve una tabla que asocia cada nodo a la distancia del camino
	 * minimo y al nodo anterior en dicho camino
	 */
	public HashMap<Object,Casilla> buscarCaminosMinimos(Object origen){
		
		Casilla casilla;
		Object nodo, nodoAdyacente;
		ListaEnlazada adyacentes;
		int distancia, distanciaAdyacente;
		
		//Se crea e inicializa la tabla de distancias
		HashMap<Object,Casilla> tablaDistancias = new HashMap<Object,Casilla>();
		
		Iterator<Object>iterador = tablaNodos.keySet().iterator();
		
		while(iterador.hasNext()) {
			casilla = new Casilla();
			tablaDistancias.put(iterador.next(), casilla);
		}
		
		
		/*La distancia del nodo origen se establece a cero y se inserta en una cola vacia*/
		tablaDistancias.get(origen).setDistancia(0);
		Cola cola = new ColaEnlazada();
		cola.insertar(origen);
		
		//Mientras la cola no se queda vacia, se analizan los nodos
		while( !cola.esVacia()) {
			
			try{
				
				//se extrae el nodo y se consulta su distancia
				nodo = cola.primero();
				cola.quitarPrimero();
				distancia = tablaDistancias.get(nodo).getDistancia();
				
				//Se tratan todos los nodos adyacentes no marcados y se insertan a la cola
				adyacentes = this.obtenerAdyacentes(nodo);
				
				adyacentes.primero();
				
				while(adyacentes.estaDentro()) {
					
					nodoAdyacente = ((Arista)adyacentes.recuperar()).getDestino();
					distanciaAdyacente = tablaDistancias.get(nodoAdyacente).getDistancia();
					
					if(distanciaAdyacente == Integer.MAX_VALUE) {
						tablaDistancias.get(nodoAdyacente).setDistancia(distancia+1);
						tablaDistancias.get(nodoAdyacente).setAnterior(nodo);
						cola.insertar(nodoAdyacente);
					}
					adyacentes.avanzar();
				}
				
			}catch(DesbordamientoInferior e) {
				e.printStackTrace();
			}
		}
		
		//se devuelve la tabla de distancias
		return tablaDistancias;
	}
	
	
	
	
	
}//fin class
