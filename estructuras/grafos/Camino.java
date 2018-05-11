package grafos;


/**Representa un camino con un destino y una longitud,
 * y se usa en el algoritmo de Dijkstra.*/
public class Camino implements Comparable<Object> {

	/**Destino del camino*/
	private Object destino;
	
	/**Longitud del camino**/
	private int longitud;
	
	/**Constructor**/
	public Camino(Object destino, int longitud) {
		this.destino = destino;
		this.longitud = longitud;
	}
	
	public Object getDestino() {
		return destino;
	}

	public void setDestino(Object destino) {
		this.destino = destino;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	/**Metodo que permite comprar un camino con otro y determinar
	 * si es menor, igual o mayor. Devuelve un numero positivo si
	 * el camino actual es mayor que el cmaino pasado por parametro,
	 * cero si es igual, y un numero negativo si es menor**/
	@Override
	public int compareTo(Object o) {

		return longitud - ((Camino)o).getLongitud();
	}

}
