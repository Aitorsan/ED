package grafos;



/**Almacena la informacion necesaria de cada nodo para realizar el algoritmo de Dijkstra**/
public class CasillaDijkstra extends Casilla {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -383921627007579926L;
	/**Determina si un nodo ha sido explorado**/
	private boolean explorado;
	
	/**Constructor de la clase**/
	public CasillaDijkstra() {
		super();
		explorado = false;
	}
	
	//Metodos accesores mutadores
	
	public boolean estaExplorado() {
		return explorado;
	}
	
	public void setExplorado(boolean explorado) {
		this.explorado = explorado;
	}
	
	
}//fin class
