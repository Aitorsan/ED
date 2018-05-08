package listas;
import nodos.NodoDoble;
/**
 * Clase Lista enlazada doble
 * @author Aitor sanmartin ferreira
 */
public class ListaDobleEnlazada implements Lista {

	/**cabecera**/
     NodoDoble cabecera;
     /**terminacion**/
     NodoDoble terminacion;
     /**actual marca una posicion en la lista**/
     NodoDoble actual;
     /**size para saber cuantos elementos hay en la lista**/
     int size;
     /**Constructor**/
     public ListaDobleEnlazada() {
    	 cabecera = new NodoDoble(null);
    	 terminacion = new NodoDoble(null);
    	 actual = null;
    	 cabecera.setSiguiente(terminacion);
    	 terminacion.setAnterior(cabecera);
         size = 0;
     }
     
     public static void main(String [] args) {
    	 ListaDobleEnlazada l = new ListaDobleEnlazada();
    	 l.insertar("Crash");
    	 System.out.println("no woman no crash!!");
     }
     
	@Override
	public void insertar(Object elemento) {
	      
		//se crea un nodo y se enlaza con el nodo actual y ocn el nodo siguiente al nodo acutal
		NodoDoble nuevo = new NodoDoble(elemento);
		//arreglo para evitar java.null.pointer exception cuando la lista esta vacia
		if(actual == null) {
			actual = cabecera;
		}
	    if( actual == terminacion) {
	    	actual = actual.getAnterior();
	    }
			nuevo.setAnterior(actual);
			nuevo.setSiguiente(actual.getSiguiente());
			//se actualizan los enlaces del nodo actual
			actual.setSiguiente(nuevo);			
			//si existe un nodo siguiente al nodo nuevo, se establece su enlace anterior
			if( nuevo.getSiguiente() != null) {
				nuevo.getSiguiente().setAnterior(nuevo);
			}
			actual = nuevo;
			//aumenta el numero de elementos
			++size;
	}

	@Override
	public void eliminar(Object elemento) {

	     NodoDoble iterador = cabecera.getSiguiente();
	     
	     while( iterador != terminacion && !iterador.getElemento().equals(elemento)) {
	    	 iterador = iterador.getSiguiente();
	     }
	     
	     if( iterador != terminacion) {
	    	 actual = iterador.getAnterior();	 
	    	 iterador.getAnterior().setSiguiente(iterador.getSiguiente());
	    	 iterador.getSiguiente().setAnterior(iterador.getAnterior());
	    	 
	    	 iterador = null;
	    	 //disminuye el numero de elementos  
	    	 --size;
	     }
	     
	}

	@Override
	public boolean buscar(Object elemento) {
		
		NodoDoble iterador = cabecera.getSiguiente();
          
          while( iterador != terminacion && !iterador.getElemento().equals(elemento)) {
        	  iterador = iterador.getSiguiente();
          }
		
          if( iterador == terminacion) {
        	  return false;
          }
		return true;
	}

	@Override
	public void cero() {
		actual =cabecera;	
	}

	@Override
	public void primero() {
		actual = cabecera.getSiguiente();
	}

	@Override
	public void avanzar() {
		//se avanza la posicion actual, comprobando que no se ha llengado al final 
		if( actual!= terminacion && actual != null) {
			actual = actual.getSiguiente();
		}else {
			System.out.println("No se puede avanzar la posicion actual por que no se encuentra dentro de la lista");
		}
		
	}

	public void retroceder() {
		//se avanza la posicion actual, comprobando que no se esta en el nodo cabecera
		if(actual!=cabecera && actual!= null) {
			actual = actual.getAnterior();
		}else {
			System.out.println("No se puede retroceder la posicion actual por que no se encuentra dentro de la lista");
		}
	}
	
	@Override
	public boolean estaDentro() {	
		return ( actual != cabecera && actual != terminacion && actual != null);
	}

	@Override
	public Object recuperar() {
		
		if( estaDentro()) {
			return actual.getElemento();
		}
		System.out.println("No se puede recuperar el elemento de la posicion actual por que no esta dentro de la lista");
		return null;
	}
    /**
     * Retorna el numero de elementos en la lista.Si esta vacia el numero es cero 
     * @return
     */
   public int getSize() {
	   return size;
   }
}
