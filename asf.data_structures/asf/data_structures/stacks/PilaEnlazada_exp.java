package asf.data_structures.stacks;
import asf.data_structures.nodes.Node;
import excepciones.DesbordamientoInferior;


public class PilaEnlazada_exp<T> implements Pila_exp<T> {
	
	private Node< T> cima;
	private Integer numElementos;
	public PilaEnlazada_exp() {
		cima = null;
		numElementos = 0;
	}

	@Override
	public void apilar(T elemento) {
		
		Node<T> nodo= new Node<T>(elemento);
		nodo.set_next(cima);
		cima = nodo;
		++numElementos;
	}

	@Override
	public void desapilar() throws DesbordamientoInferior {
		  if( esVacia()) {
			  throw new DesbordamientoInferior("la pila ya no contiene mas elementos, esta vacia");
		  }
           cima = cima.get_next();
           --numElementos;
		
	}

	@Override
	public T cima() throws DesbordamientoInferior {
		if(esVacia()) {
			throw new DesbordamientoInferior("La pila esta vacia");
		}
		return cima.getElement();
	}

	@Override
	public boolean esVacia() {
		
		return (cima == null);
	}
	
	
	
	
	
	

}
