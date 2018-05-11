package grafos;

/** Casilla de la tabal de distancias necesaria para la busqueda de caminos minimos.
 *  Contiene un numero con la distancia y un enlace al anterior nodo.
 */
public class Casilla {
	
	/**Distancia al nodo origen**/
	private int distancia;
	
	/**Enlace al nodo anterior del camino minimo**/
	private Object anterior;
	
	/**Metodo constructor que establece la distancia al maximo numero y el nodo anterior a nulo*/
	public Casilla() {
		distancia = Integer.MAX_VALUE;
		anterior = null;
	}
	
	//Metodos accesores
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Object getAnterior() {
		return anterior;
	}

	public void setAnterior(Object anterior) {
		this.anterior = anterior;
	}
	
	/**Metodo que convierte la casilla en una cadena de caracteres**/
	public String toString() {
		return "Casilla("+distancia+","+anterior+")";
	}

}
