package pilas;
import nodos.Nodo;
import excepciones.DesbordamientoInferior;

public class PilaEnlazada implements Pila {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859312240484125167L;
	/**Referencia al ultimo nodo, que esta en la cima d ela pila**/
	private Nodo cima;
	
	/**Constructor. Crea una pila vacia**/
	public PilaEnlazada() {
		//Establecemos la cima a nulo, dado que la pila empieza vacia
		cima = null;
	}
	
	/**Inserta un elemnto al final de la pila**/
	@Override
	public void apilar(Object elemento) {

		//Se crea un nuevo nodo y se añade al final de la lista
		Nodo nodo = new Nodo(elemento);
		
		//El nodo apunta al último elemento de la lista
		nodo.setEnlace(cima);
		
		//La cima se actualiza, dado que ahor aes el nuevo elemento apilado
		cima = nodo;

	}

	/**Elimina el ultimo elemento de la pila que se inserto**/
	@Override
	public void desapilar() throws DesbordamientoInferior {
		//Se comprueba si esta vacia la pila
        if( esVacia()) {
        	throw new DesbordamientoInferior("No se puede desapilar un elemento de una pila vacia");
        }
        /*Se cambia la cima al penultimo nodo. De esta manera la antigua cima
         * se queda sin ser apuntada y se ignora. El recolector de basura de 
         * Java se encarga de liberar la memoria correspondiente
         */
        cima = cima.getEnlace();
	}

	/**Devuelve el ultimo elemento que se inserto en la pila**/
	@Override
	public Object cima() throws DesbordamientoInferior {
		
		if(esVacia()) {
			throw new DesbordamientoInferior("La pila esta vacia");
		}
		//Devuelve el elemento que esta en el nodo de la cima de la pila
		return cima.getElemento();
	}
	
	
    /**Devuelve cierto si la pila esta vacia y falso en caulquier otro caso**/
	@Override
	public boolean esVacia() {
		return (cima == null);
	}

}
