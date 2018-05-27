package pilas;

import excepciones.DesbordamientoInferior;

public class PilaVector implements Pila{

	/**
	 * 
	 */
	private static final long serialVersionUID = -614349278171404573L;


	/**Vector que contiene los datos de la pila**/
	private Object[] datos;
	
	
    /**Numero de elementos que contiene la pila.Tambien indica la primera psocion del vector*/
	private int numElementos;
	
	/**Constructor de la clase.Se debe indicar el tamaño que quiere que tenga el vector**/
	public PilaVector( int tam) {
		/*Se crea el vector del tamaño indicado y se inciializa el numero de elementos a cero*/
		datos = new Object[tam];
		numElementos = 0;
	}
	
	/**Inseta un elemento al final de la pila**/
	@Override
	public void apilar(Object elemento) {
		//Si el vector esta lleno, se le indica al usuario
		if( numElementos >= datos.length) {
			System.out.println("La pila esta llena y no se pueden apilar mas elementos");
			
		}else {
			/*Se inserta el elemento en la primera posicion libre del vector y se incrementa el número de elementos*/
		    datos[numElementos] = elemento;
		    numElementos++;
		
		}

		
		
	}

	/**Elimina el ultimo elemento de la pila que se insertó*/
	@Override
	public void desapilar()throws DesbordamientoInferior {
	
		//Se lanza un excepcion en caso de que no haya elementos
		if(esVacia()) {
			throw new DesbordamientoInferior("No se puede desapilar un elemento de una pila vacia");
		}
		//Se decrementa el número de elementos
		numElementos--;
	}

	/**Devuelve el ultimo elemento qu ese inserto en la pila**/
	@Override
	public Object cima()throws DesbordamientoInferior {
		//Se lanza un excepcion en caso de que no haya elementos			
		if(esVacia()){			
			throw new DesbordamientoInferior("No se puede desapilar un elemento de una pila vacia");	
		}
        //Se devuelve el elemento del vector que est aen la cima
		return datos[numElementos-1];
	}

	/**Devuelve cierto si la pila esta vacia y falso en cualquier otro caso*/
	@Override
	public boolean esVacia() {


		return (numElementos == 0);
	}
	

}
