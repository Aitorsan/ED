package grafos;

import excepciones.OperacionIncorrecta;



public class GrafoListasAdyacenciaNoDirigidoSinPesos extends GrafoListasAdyacenciaSinPesos {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2137186801472246331L;

	/**Inserta una arista entre dos nodos**/
	public void insertarArista(Object nodo1, Object nodo2)throws OperacionIncorrecta{
		super.insertarArista(nodo1, nodo2);
		super.insertarArista(nodo2, nodo1);
	}
	
	
}
