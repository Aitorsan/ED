package test;

import listas.Lista;
import listas.ListaEnlazada;

public class Main {

	public static void main(String[] args) {

		/** 
		 *  Prueba inicial Aitor<br> 
		 *  Lanzaba una excepcion no controlada al encontrarse actual fuera de la lista.
		 *  El problema se localizaba en el metodo insertar 
		

		Lista l = new ListaEnlazada();
		l.primero();
		l.insertar("Algo");
		System.out.println(l);
		/** 
		 *  Prueba tras la modificacion 
		 
		Lista l2 = new ListaEnlazada();
		l2.insertar("UNO");
		System.out.println(l2);
		l2.avanzar(); // actual pasa a ser UNO
		l2.avanzar(); // se sale de la lista
		System.out.println(l2.estaDentro());
		/** 
		 *  [Pag.59] "Además, el nodo actual debe enlazarse con el nuevo nodo, 
		 *  consiguiendo así una nueva lista enlazada que incluye el nuevo nodoinsertado 
		 *  DESPUÉS DE LA POSICIÓN ACTUAL" 
		 
		l2.insertar("DOS"); // tras la insercion, actual no se modifica
		System.out.println(l2);
		l2.insertar("TRES");
		System.out.println(l2);*/
		
		
		
		
		
		Lista l = new ListaEnlazada();
		
		l.insertar("[uno]");
		l.insertar("[dos]");
		l.insertar("[tres]");
		l.insertar("[cuatro]");
		System.out.println(l.recuperar());
		
		int counter = 0;
		while( l.estaDentro()) {
			++counter;
			l.avanzar();
		}
		System.out.println("count:"+ counter);
		l.insertar("[cinco]");
		

	}


}



