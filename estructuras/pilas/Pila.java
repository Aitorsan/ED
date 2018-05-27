package pilas;

import java.io.Serializable;

import excepciones.DesbordamientoInferior;

public interface Pila extends Serializable {
	/**Inserta un elemento al final de la pila**/
	public void apilar(Object elemento);
	
	/**Elimina el ultimo elemento de la pila que se inserto
	 * @throws DesbordamientoInferior */
	public void desapilar() throws DesbordamientoInferior;
	
	/**Devuelve el ultimo elemento que se inserto en la pila
	 * @throws DesbordamientoInferior **/
	public Object cima() throws DesbordamientoInferior;
	
	/**Devuelve cierto si la pila esta vacia y falso en cualquier otro caso*/
	public boolean esVacia();

}
