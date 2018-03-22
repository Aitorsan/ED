package colas;
import nodos.Nodo;
import excepciones.DesbordamientoInferior;

/**Clase que implementa una cola con una lista enlzada*/
public class ColaEnlazada implements Cola {

	/**Referencia al primer nodo de la cola**/
	private Nodo inicio;
	
	/**Referencia al ultimo nodo de la cola**/
	private Nodo fin;
	
	/**Constructor de la clase.Crea una cola vacia**/
	public ColaEnlazada() {
		inicio = null;
		fin = null;
	}
	
	
	/**Inserta un elemento al final de la cola**/
	@Override
	public void insertar(Object elemento) {
	//Se crea un nuevo nodo con el elemento y que el siguiente enlace apunte a nulo
		Nodo nuevo  = new Nodo(elemento);
		nuevo.setEnlace(null);
		
		//si esta vacia se apunta por inicio y fin
		if( esVacia()) {
			inicio = nuevo;
			fin = nuevo;
			
		//Si no esta vacia, el ultimo nodo apunta al nuevo y el nuevo pasa a ser el ultimo
		}else {
			fin.setEnlace(nuevo);
			fin = nuevo;	
		}

	}

	/**Devuelve el elemento qu elleva mas tiempo en la cola**/
	@Override
	public Object primero() throws DesbordamientoInferior {
	
		//Comprueba si la cola esta vacia
		if(esVacia()) {
			throw new DesbordamientoInferior("La cola está vacia y no hay un primer elemento");
		}
		//Devuelve el primero elemento
		return inicio.getElemento();
	}

	/**Elimina el elemento que lleva mas tiempo en la cola**/
	@Override
	public void quitarPrimero() throws DesbordamientoInferior {
	

		//Comprueba si la cola esta vacia
		if(esVacia()) {
			throw new DesbordamientoInferior("La cola esta vacia y no hay un primer elemento");
		}
		/* Inicio se establece segundo elemento y el recolector de basura borra el 
		 * primero automaticamente al dejar de ser referenciado
		 */
		inicio = inicio.getEnlace();
		
		/*Si solo habia un elemento, la referencia fin debera pasar a nulo dado que la cola esta vacia*/
		if( inicio == null) {
			fin =null;
		}
	}

	/**Determina si la cola esta vacia*/
	@Override
	public boolean esVacia() {

		return(inicio == null);
	}

}
