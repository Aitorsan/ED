package asf.data_structures.queues;
import asf.data_structures.nodes.Node;
import excepciones.DesbordamientoInferior;

public class ColaEnlazada_exp<T> implements Cola_exp<T> {

	private Node<T> primero;
	private Node<T> ultimo;
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
		
		Node <T> nuevo = new Node<T>(elemento);
		
		nuevo.set_next(null);
       
		if( primero == null) {
        	    
        	     primero = nuevo;
        	     ultimo = nuevo;
         }else {
        	 
        	 ultimo.set_next(nuevo);
        	 ultimo = nuevo;
        	 
         }
         ++numElementos;
	}

	@Override
	public T primero() throws DesbordamientoInferior {
          if( esVacia()) {
        	  
        	    throw new DesbordamientoInferior("La cola esta vacia, imposible extraer datos");
          }

		return primero.getElement();
	}

	@Override
	public void quitarPrimero() throws DesbordamientoInferior {
		if(esVacia()) {
			throw new DesbordamientoInferior("la cola no contiene mas elementos, imposible extraer datos");
		}
		
	     Node<T> temp = primero;
	     primero = primero.get_next();
	     temp.set_next(null);
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
	
    /**
     * Find an element in the list
     * @param element
     * @return true if the element is inside the list false if it's empty or not found.
     */
	public  boolean buscar  ( T element) {
		
		if( esVacia()) {
			return false;
		}else {
			return buscar(primero,element);
		}
		
	}
	
	/**
	 * Find the element recursively
	 * @param node
	 * @param element
	 * @return
	 */
	private boolean buscar( Node<T> node,T element) {
		
		
		if( node != null) {
			
			if( node.getElement().equals(element)) {
		
				 return true;
			}else {
				return buscar( node.get_next(),element);
			}
		}else {
			return false;
		}
	}

}
