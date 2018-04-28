package asf.data_structures.stacks;

import excepciones.DesbordamientoInferior;

public interface Pila_exp<T> {

	/** Insert an element at the end of the stack.*/
	public void apilar(T elemento);
	
	/**Delete the last element that was inserted on the stack
	 * @throws DesbordamientoInferior */
	public void desapilar() throws DesbordamientoInferior;
	
	/** Return the element at the top of the stack
	 * @throws DesbordamientoInferior */
	public T cima() throws DesbordamientoInferior;
	
	/**Return true if the stack is empty . False otherwise*/
	public boolean esVacia();
	
	
}
