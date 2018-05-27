package listas;
import nodos.Nodo;
public class ListaEnlazadaOrdenada extends ListaEnlazada {

	
	/*
	 * Inserta el elemento en la posicion marcada por el orden.Como requisito , el elemento<br>
	 * insertado debe ser comparable con otros elementos, de tal forma que se puedan seguir<br>
	 * un orden en la insercion.
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890305092317965259L;
	public void insertar(Object elemento)
	{
		//Se comprueba que el elemento es comprable
		if( elemento instanceof Comparable) {
			insertar((Comparable<?>)elemento);
		}else {
			throw new UnsupportedOperationException("En la lista ordenada, no se puede insertar el elemento por no ser comparable");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private <E>void insertar(Comparable<E> elemento) {
		//Se empieza el recorrido al principio de la lista
		Nodo iterador = cabecera;

		 /*Se reccoren todos los elementos mientras el elemento a insertar es mayor que
		 * el elemento del siguiente nodo al iterador.Cuando acaba el bucle, la posición
		 * adecuada del elemento a insertar es justo despues del nodo iterador
		 * */
		 
		while(iterador.getEnlace() != null && elemento.compareTo(((E)iterador.getEnlace().getElemento()))>0) {
			iterador = iterador.getEnlace();
		}
		
		//Se crea un nodo y se enlaza con el siguiente nodo al iterador
		Nodo nuevo = new Nodo(elemento);
		nuevo.setEnlace(iterador.getEnlace());
		
		//Se actualiza el enlace del nodo actual
		iterador.setEnlace(nuevo);
		// restablecer actual
		actual = nuevo;
		//para ser mas correctos
		iterador = null;
		
		
	}
	public static void main(String[] args) {
		
		ListaEnlazadaOrdenada l = new ListaEnlazadaOrdenada();
		//comprobar que cuando actual se sale de la lista y es nulo no hay problemas
		l.primero();
		
      // insertar los elementos en ordenes aleatorios
	  // al introducir numeros de tipo int se hace una conversion implicita a la clase Wrapper Integer por eso podemos usar la lista ordenada	
		l.insertar(5);
		l.insertar(8);
		l.insertar(3);
		l.insertar(1);
		l.insertar(7);
		l.insertar(4);
		l.insertar(6);
		l.insertar(2);
		//comprobar que estan ordenados de menor a mayor
		l.imprimir();
		
		//avanzar la posicion
		l.avanzar();
		//insertar mas numeros
		l.insertar(11);
		l.insertar(9);
		l.insertar(10);
		
		//comprobar que la lista esta ordenada
		l.imprimir();
		
		// eliminar numeros y ver si sigue ordenada
		
	    l.eliminar(1);
	    l.eliminar(11);
	    
	    //comprobar que sigue ordenada
	    l.imprimir();
	    
	    
	    // eliminar del medio de la lista
	    l.eliminar(5);
	    
	    //comprobar que sigue ordenada
	    l.imprimir();
	    
	    
	    
	    if(l.buscar(9)) {
	   
	    	System.out.println("el numero:" + 9 + " esta en la lista");	
	    }
		
		System.out.println("el  numero recuperado tiene que ser el 9 =  "+l.recuperar());
		
		if(!l.buscar(5)) {
			   
	    	System.out.println("el numero:" + 5+ " no esta en la lista");	
	    }
		
		for ( int i = 12; i < 100; ++i) {
			l.insertar(i);
		}
		l.imprimir();
	    l.cero();
		while( l.estaDentro()) {
			l.eliminar(l.recuperar());
			l.avanzar();
		}
		l.imprimir();
		System.out.println("succed!!");
		
	}
	
}
