package pilas_exp;
import excepciones.DesbordamientoInferior;
import nodos_exp.Nodo_exp;


public class PilaEnlazada_exp<T> implements Pila_exp<T> {
	
	private Nodo_exp< T> cima;
	private Integer numElementos;
	public PilaEnlazada_exp() {
		cima = null;
		numElementos = 0;
	}

	@Override
	public void apilar(T elemento) {
		
		Nodo_exp<T> nodo= new Nodo_exp<T>(elemento);
		nodo.setEnlace(cima);
		cima = nodo;
		++numElementos;
	}

	@Override
	public void desapilar() throws DesbordamientoInferior {
		  if( esVacia()) {
			  throw new DesbordamientoInferior("la pila ya no contiene mas elementos, esta vacia");
		  }
           cima = cima.getEnlace();
           --numElementos;
		
	}

	@Override
	public T cima() throws DesbordamientoInferior {
		if(esVacia()) {
			throw new DesbordamientoInferior("La pila esta vacia");
		}
		return cima.getElemento();
	}

	@Override
	public boolean esVacia() {
		
		return (cima == null);
	}
	
	
	
	
	
	

}
