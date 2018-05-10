package grafos;
import excepciones.OperacionIncorrecta;
/**Implementacion de grafos no dirigidos con listas de adyacencia**/
public class GrafoListasAdyacenciaNoDirigido extends GrafoListasAdyacencia{

/**Inserta una arista simetrica entre dos nodos con el coste indicado por parametro**/
	public void insertarArista(Object nodo1, Object nodo2,int coste) throws OperacionIncorrecta{
		super.insertarArista(nodo1, nodo2, coste);
		super.insertarArista(nodo2, nodo1, coste);
	}
	
	
}
