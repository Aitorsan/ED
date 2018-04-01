package test;

import listas.Lista;
import listas.ListaEnlazada;

public class Main {

	public static void main(String[] args) {
		  Lista l = new ListaEnlazada();
	        // Mucho codigo por medio ..
	        l.primero();
	        //1.posible solucion
	        if(!l.estaDentro()){   
	             l.cero();
	        }
	         //ya no tenddria el problema
	        l.insertar("Algo");

	}



	
	
	 /**Si quiero saber si que objeto tiene esa clave**/
		public static boolean buscarObjeto(Lista l,String clave) {
			 
			  boolean encontrado= false;
			  //como quiero acceder a un nombre es logico recorrer la lista
			  // ya que el metodo buscar no me valdria por que quiero acceder
			  // a un elemento dentro del objeto que esta en la lista
			  //Como no se donde esta lo normal seria situar el puntero actual
			  // al inicio.
		    if( l.estaDentro()) {
		    	      l.primero();
		    }else {
		    	      l.cero();    
		    }
		     while(l.estaDentro()) {
		    	     if(((ObjetoConClave)l.recuperar()).getClave().equals(clave)){
		    	    	    encontrado = true;
		    	     }
		    	     l.avanzar();
		     }
		     l.cero();
			return encontrado;
		}

}



