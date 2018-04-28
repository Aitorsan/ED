package asf.data_structures.stacks;

import excepciones.DesbordamientoInferior;

public class PilaVector_exp<T> implements Pila_exp<T> {
  /**Vector containing the elements in the stack*/
	private Object [] datos;
	/**Number of elements contained in the stack. Also
	 * indicates the first position in the vector
	 */
	private int numElementos;
	/**Constructor receives the size of the stack
	 * @param tam the size of the stack
	 */
	public PilaVector_exp(int tam) {
		datos = new Object[tam];
		numElementos = 0;
	}
	
	/**Insert a new element at the top of the stack*/
	@Override
	public void apilar(T elemento) {

		//if the vector is full
		if( numElementos >= datos.length) {
			System.out.println("La pila esta llena y no se pueden apilar mas elementos");
		}else {
			/*Insert the element at the first free position into the vector
			 * and increment the number of elements
			 */
			datos[numElementos] = elemento;
			++numElementos;
	
		}

		
	}
    /**Delete the last element inserted into the stack
     * @throws DesbordamientoInferior */
	@Override
	public void desapilar() throws DesbordamientoInferior {
    
		//we throw an exception in case there aren't, any more elements in the list
		if( esVacia()) {
			throw new DesbordamientoInferior("No se puede desapilar un elemento de una pila vacia");
			
		}
		
		numElementos--;
	}

	/**Return the last element inserted in the stack
	 * @throws DesbordamientoInferior */
	@SuppressWarnings("unchecked")
	@Override
	public T cima() throws DesbordamientoInferior {
	     //Before return an element we need to check if the list is empty.
		 //In that case we throw an exception.
		if( esVacia()) {
			throw new DesbordamientoInferior("No se puede consultar la cima de una pila vacia");
		}
		return (T)datos[numElementos-1];
	}

	/**Return true if the list is empty*/
	@Override
	public boolean esVacia() {
		return (numElementos == 0);
	}

}
