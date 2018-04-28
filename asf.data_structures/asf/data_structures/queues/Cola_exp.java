package asf.data_structures.queues;
import excepciones.DesbordamientoInferior;
public interface Cola_exp<T>{
	
	/**Insert an element at the end of the Queue
	 * @param <T>*/
	public void insertar (T elemento);
	
	/**Return the oldest element in the Queue*/
	public T primero()throws DesbordamientoInferior;
	
	/**Delete the oldest element in the Queue*/
	public void quitarPrimero()throws DesbordamientoInferior;
	
	/**Return whether the Queue is empty or not*/
	public boolean esVacia();
	

}
