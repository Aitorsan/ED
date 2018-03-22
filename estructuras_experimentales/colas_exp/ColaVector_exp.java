package colas_exp;

import excepciones.DesbordamientoInferior;
/**
 * Basic Queue implemented with vectors
 * @author aitorSf
 *
 * @param <T>
 */
public class ColaVector_exp<T> implements Cola_exp<T> {

	/**Vector containing the elements of the queue*/
	private Object []datos;
	/**first position in the queue, last position in the queue and number of elements in the queue*/
	private int primero, fin, numElementos;
	
	/**
	 * Constructor takes the size of the Queue
	 * @param tam
	 */
	public ColaVector_exp(int tam) {
		
		datos = new Object[tam];
		primero =0;
		fin = -1;
		numElementos = 0;
	}
	/**
	 * Insert an element at the end of the Queue
	 */
	@Override
	public void insertar(T elemento) {
	 
		if(numElementos >= datos.length) {
			System.out.println("la cola esta llena");
		}else {
			++fin;
			fin %= datos.length;
			datos[fin] = elemento;
			++numElementos;
		}
		
	}

	/**
	 * Return the first element in the queue
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T primero() throws DesbordamientoInferior {
           if( esVacia()) {
        	     throw new DesbordamientoInferior("La cola ya no contiene mas elementos, imposible extraer datos");
           }

		return (T)datos[primero];
	}

	/**Delete the first element in the queue*/
	@Override
	public void quitarPrimero() throws DesbordamientoInferior {
		
		  if( esVacia()) {
     	     throw new DesbordamientoInferior("La cola ya no contiene mas elementos, imposible extraer datos");
        }else {
        	
        	    ++primero;
        	    primero %=datos.length;
        	    --numElementos;
        }
	}
	
	/**Check whether the Queue is empty or not*/
	@Override
	public boolean esVacia() {
		return (numElementos == 0);
	}

}
