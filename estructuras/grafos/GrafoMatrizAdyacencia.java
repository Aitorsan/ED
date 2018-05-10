package grafos;

import excepciones.OperacionIncorrecta;
import listas.ListaEnlazada;


/**Grafo implementado con una matriz de adyacencia**/
public class GrafoMatrizAdyacencia implements Grafo{

	/**Vector con los nodos**/
	private Object nodos[];
	
	/**Matriz de adyacencia que contiene los costes**/
	private int costes[][];
	
	/**Contador del numero de nodos**/
	private int numNodos;
	
	/**Constructor de la clase que determina la capacidad maxima del numero
	 * de nodos en el grafo, y comienza con un grafo vacio.
	 */
	public GrafoMatrizAdyacencia(int capacidad) {
		numNodos = 0;
		nodos = new Object[capacidad];
		costes = new int[capacidad][capacidad];
		
		/*Se inicializa la matriz de los costes al maximo valor entero,
		 *que representa el infinito*/
		for(int i = 0;i <capacidad;++i) {
		
			for(int j = 0; j < capacidad;++j) {
				costes[i][j] = Integer.MAX_VALUE;
			}
		}
		
	}
	
	/**Inserta un nodo con el elemento pasado por parametro***/
	@Override
	public void insertarNodo(Object elemento) {
		if(numNodos >= nodos.length) {
			System.out.println("El grafo esta lleno y no se pueden insertar mas nodos");
		}else {
			nodos[numNodos] = elemento;
			++numNodos;
		}
	}

	@Override
	public void insertarArista(Object origen, Object destino, int coste) throws OperacionIncorrecta {
		
		 int indiceOrigen = buscarIndice(origen);
		int indiceDestino = buscarIndice(destino);
		
		if(indiceOrigen < 0 || indiceDestino <0) {
			
		}else {
			costes[indiceOrigen][indiceDestino] = coste;
		}
	
	}

	@Override
	public ListaEnlazada obtenerAdyacentes(Object elemento) {
	     
		
		int indiceElemento = buscarIndice(elemento);
	     
	    if( indiceElemento == -1) {
	    	return null;
	    }
	     
	    /*Se insertan a la lista todos los elementos que no tengan un coste infinito, representado con el maximo 
	     * valor entero
	     */
	    ListaEnlazada lista = new ListaEnlazada();
	    
	    for( int i = 0; i < numNodos;++i) {
	    	
	    	if(costes[indiceElemento][i] != Integer.MAX_VALUE) {
	    		Arista arista = new Arista(costes[indiceElemento][i],nodos[i]);
	    		lista.insertar(arista);
	    	}
	    }
	    
		return lista;
	}
	
	
	/**Buscar el indice de un elemento de un nodo.Devuelve -1 en caso de no encontrarlo**/
	private int buscarIndice(Object elemento) {
		int cont = 0;

		while(cont < numNodos ) {
			
			if(elemento.equals(nodos[cont])) {
                 return cont;				
			}				
				++cont;
		}
		return -1;
	}
	
	
	

}//fin class
