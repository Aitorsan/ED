package colas;
import excepciones.DesbordamientoInferior;
/**Interfaz que representa una cola.El primero en entrar es el primero en salir fifo.**/
public interface Cola {

	/**Inserta un elemento al final de la cola**/
	public void insertar(Object elemento);
	
	/**Devuelve el elemento que lleva mas tiempo en la cola**/
	public Object primero() throws DesbordamientoInferior;
	
	/**Elimina el elemento que lleva mas tiempo en la cola**/
	public void quitarPrimero() throws DesbordamientoInferior;
	
	/**Determina si la cola esta vacia**/
	boolean esVacia();
	
}