package listas;
import nodos.Nodo;


/**Clase que implementa una lista enlazada**/
public class ListaEnlazada implements Lista{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8021340129708416557L;

	/**Enlace al nodo cabecera*/
	protected Nodo cabecera;
	
	/**Enlace al nodo de la posicion actual**/
	protected Nodo actual;
	
	/**Constructor de la clase.Construye una lista vacia que empieza con el nodo cabecera*/
	public ListaEnlazada(){
		//Se crea el nodo cabecera;
		cabecera = new Nodo(null);
		cabecera.setEnlace(null);
		//Se establece la posicion actual en la cabecera
		actual = cabecera;
	}
	
	
	/**Inserta el elemento pasado por parametro despues de la posicion actua**/
	@Override
	public void insertar(Object elemento) {
		//Se crea un nodo y el enlace con el siguiente nodo actual
		Nodo nuevo = new Nodo(elemento);       
		// 1. actual apunta a la cabecera
	  
         if( !estaDentro()){
        	  Nodo nodoIterador = cabecera;
              while(nodoIterador.getEnlace() != actual) {
             	   nodoIterador = nodoIterador.getEnlace();
              }
              actual = nodoIterador;
         }
         
	  
	                         
		//if(estaDentro) actual no es gual a null                                   
		nuevo.setEnlace(actual.getEnlace());    
		
		//Se actualiza el enlace del nodo actual
		actual.setEnlace(nuevo);
	}	

    /** Elimina el elemento pasado por parametro si existe**/
	@Override
	public void eliminar(Object elemento) {
	   /*Se recorren todos los nodos hasta situarse en el nodo anterior al que se quiere
	    * eliminar si existe. Para esto, se empieza en el nodo cabecera y las condiciones
	    * se realizan sobre el nodo siguiente al nodo anterior.
	    */
		Nodo nodoIterador = cabecera;
		while( nodoIterador.getEnlace() != null && !nodoIterador.getEnlace().getElemento().equals(elemento)) {
			nodoIterador = nodoIterador.getEnlace();
		}
		/*Si existe, se elmina el elemento. Para ello, el nodo anterior al nodo elminado
		 *se enlaza con el nodo siguiente al nodo elminado. De esta manera excluye el nodo 
		 *que se quiere eliminar
		 */
		if(nodoIterador.getEnlace() != null) {
			actual = nodoIterador;
			nodoIterador = nodoIterador.getEnlace();
	
			actual.setEnlace(actual.getEnlace().getEnlace());
		     nodoIterador.setEnlace(null);
		}
		
	}

/**Determina si existe el elemento pasado pro parametro en la lista y coloca
 * la posicion actual en la situacion de dicho elemento en caso de existir
 */
	@Override
	public boolean buscar(Object elemento) {
		
		//Se recorren todos los nodos desde la cabecera para encontrar el elemento
		Nodo nodoIterador = cabecera.getEnlace();
		
		while( nodoIterador!= null && !nodoIterador.getElemento().equals(elemento)) {
			nodoIterador = nodoIterador.getEnlace();
		}
		//Si se encuentra el nodo se altera la posicion actual
		if( nodoIterador != null) {
			actual = nodoIterador;
			return true;
		}else {
			return false;
		}
	}

   /**
    * Coloca la posicion actual antes del primer elemento de la lista
    */
	@Override
	public void cero() {
        //La posicion actual se situa en el nodo cabecera
		actual = cabecera;
	}

    /**Situa la posicion actual en el primer elemento de la lista**/
	@Override
	public void primero() {
        /*La posicion actual se situa en el nodo siguiente a la cabecera, el primero de la lista*/
		actual = cabecera.getEnlace();
	}
	/**Avanza la posicion actual**/
	@Override
	public void avanzar() {

		//Se avanza la posicion actual, comprbando que no se ha llegado al final
		if( estaDentro()) {
			actual = actual.getEnlace();
		}else {
			System.out.println("No se puede avanzar la poscion atual por que no esta dentro de la lista");	
		}
	}

/**Determina si la posicion actual es una poscion valida. De esta manera , se puede comprobar si se ha llegado 
 * al final de la lsita
 */
	@Override
	public boolean estaDentro() {
		return (actual != null);
	}

/**Devuelve el elemento de la poscion actual*/
	@Override
	public Object recuperar() {
		
		/*Se devuelve el elemento de la posicona actual, comprobando que no se ha llegado al final*/
		if(estaDentro()) {
			return actual.getElemento();
		}else {
			System.out.println("No se puede recuperar el elemento de la posicion actual por que no esta dentro");
            return null;
		}
		
	}
	
	/**Muestra por pantalla la lista de objectos**/
	public void imprimir() {
		
		Nodo nodoIterador = cabecera.getEnlace();
		System.out.println("Lista:");
		
		while(nodoIterador != null) {
			System.out.print("["+nodoIterador.getElemento()+"]->");
			nodoIterador = nodoIterador.getEnlace();
		}
		System.out.println();

	}


	/*Sobreescribimos el metodo toString*/
	@Override
	public String toString() {
		
		this.primero();
		Nodo nodoIterador = actual;
		String elementos ="";
		
		while(nodoIterador != null) {
			elementos+= nodoIterador.getElemento();
			if(nodoIterador.getEnlace() != null) {
				elementos+=", ";
			}
			nodoIterador = nodoIterador.getEnlace();
		}
		return String.format("ListaEnlazada [ %s ]",elementos);
	}


}
