package colas_exp;
import excepciones.DesbordamientoInferior;
import nodos_exp.Nodo_exp;

public class ColaEnlazada_exp<T> implements Cola_exp<T> {

	private Nodo_exp<T> primero;
	private Nodo_exp<T> ultimo;
	private int numElementos;
	/**
	 * Constructor creates an empty Queue
	 */
	public ColaEnlazada_exp() {
		numElementos = 0;
		primero = null;
		ultimo = null;
	}
	
	@Override
	public void insertar(T elemento) {
		
		Nodo_exp <T> nuevo = new Nodo_exp<T>(elemento);
		
		nuevo.setEnlace(null);
       
		if( primero == null) {
        	    
        	     primero = nuevo;
        	     ultimo = nuevo;
         }else {
        	 
        	 ultimo.setEnlace(nuevo);
        	 ultimo = nuevo;
        	 
         }
         ++numElementos;
	}

	@Override
	public T primero() throws DesbordamientoInferior {
          if( esVacia()) {
        	  
        	    throw new DesbordamientoInferior("La cola esta vacia, imposible extraer datos");
          }

		return primero.getElemento();
	}

	@Override
	public void quitarPrimero() throws DesbordamientoInferior {
		if(esVacia()) {
			throw new DesbordamientoInferior("la cola no contiene mas elementos, imposible extraer datos");
		}
		
	     Nodo_exp<T> temp = primero;
	     primero = primero.getEnlace();
	     temp.setEnlace(null);
	     //delete temp;->for c++
	     
	     if( primero ==  null) {
	    	    // delete last;-> for c++
	    	     ultimo = null;
	     }
	     --numElementos;
	    
	}

	@Override
	public boolean esVacia() {
		
		return (primero == null);
	}

}
