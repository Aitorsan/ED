package monticulos;

import excepciones.DesbordamientoInferior;
/**Estaclase implementa un monticulo, que es una implementacion eficiente de la 
 * cola de prioridad**/
public class Monticulo implements ColaPrioridad {

	/**Este vector tiene los elementos del monticulo.En esta respresentacion,
	 * cada nodo en la posicion x tiene sus hijos en x*2 y x*2+1, y el nodo raiz
	 * esta situado en la posicion uno
	 */
	@SuppressWarnings("rawtypes")
	private Comparable[] datos;
	
	/**Numero de elementos del monticulo**/
	int numElementos;
	
	/**Constructor de la clase. Crea el monticulo con la capacidad indicada por el parametro**/
	public Monticulo(int capacidad) {
		/*Se crea el vector vacio con el tamaño "capacidad+1", dado que la posición cero no se usa*/
		datos = new Comparable[capacidad+1];
		numElementos = 0;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void insertar(Comparable elemento) {

		//Variables para determinar la posicion del nueov elemento insertado y de su padre
		int posElemento, posPadre;
		
		/*Si se ha llegado a la capacidad maxima del monticulo, se avisa que el monticulo esta lleno*/
		if(numElementos + 1 >= datos.length) {
			System.out.println("No se puede insertar el elemento, por que el monticulo esta lleno");
		}else{
			
			//Se inserta el nuevo elemento en la primera psoicion libre
			datos[numElementos+1] = elemento;
			posElemento = numElementos+1;
			numElementos++;
			
			
			/*Mientras el elemento no esta en la raiz y sea menor qu esu padre, se flota 
			 * el elemento.
			 */
			posPadre = posElemento/2;
			
			while(posPadre>= 1 && datos[posElemento].compareTo(datos[posPadre])<0) {
				
				// Se intercambian los elementos
				intercambiar(posElemento,posPadre);
				
				//se actualiza la posicion del elemento y de su nuevo padre
				posElemento = posPadre;
				posPadre = posElemento/2;
			}
			
		}
		
	}//end method

	
	/**Intercambia los dos elementos que se encuentran en las posiciones indicadas por los parametro**/
	
	@SuppressWarnings("rawtypes")
	private void intercambiar(int primeraPos,int segundaPos) {
		
		Comparable tmp = datos[primeraPos];
		datos[primeraPos] = datos[segundaPos];
		datos[segundaPos]  = tmp;
		
	}
	
	/**Devuelve el elemento minimo de la cola de prioridad.
	 * En concreto, este es el primer elemento de la cola.
	 * Esta operacion no altera la cola de prioridad
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Comparable obtenerMinimo()throws DesbordamientoInferior {
		
		//Se comprueba si esta vacia la cola
		if(esVacia()) {
			throw new DesbordamientoInferior("No se puede obtener el minimo elemento porque el monticulo esta vacio");
		}			
			/*Devuelve el elemento que esta en la raiz, que esta en la posicion uno del vector*/
		
		return datos[1];
	}

	/** Elimina el elemento minimo de la cola de prioridad
	 * que es el primer elemento de la cola de prioridad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eliminarMinimo()throws DesbordamientoInferior {
		
		//se comprueba wi esta vacio el monticulo
		if(esVacia()) {
			throw new DesbordamientoInferior("No se puede eleminar el elemento minimo, porque el monticulo esta vacio");
		}else {
			
			//variable con las posiciones del elemento hundido y sus hijos izquierdos y derecho
			int posHundido,posIzq, posDch;
			
            //Se coloca el elemento de la ultima posicion en la raiz
			datos[1] = datos[numElementos];
			--numElementos;
			
			/*Se empieza el hundimiento en la raiz y se actualizan sus hijos izquierdo y derecho*/
			posHundido = 1;
			posIzq = hijoIzquierdo(posHundido);
			posDch = hijoDerecho(posHundido);
			
			/* Se produce el proceso de hundimiento mientras se tenga algun hijo
			 * menor o igual que el elemento hundido*/
			while((posIzq >= 0 && datos[posIzq].compareTo(datos[posHundido])<=0)
			|| (posDch >= 0 && datos[posDch].compareTo(datos[posHundido])<=0)) {
			
				//Si tiene los dos hijos se escoge el menor y se compara con el elemento hundido
				
				if(posIzq >= 0 && posDch >= 0) {
					
					//Si el hijo izquierdo  es menor qu eel hijo derecho y menor o igual que el elemnto hundido, se intercambia
					
					if(datos[posIzq].compareTo(datos[posDch])<0
						&& datos[posIzq].compareTo(datos[posHundido])<= 0){
						
						intercambiar(posHundido,posIzq);
						posHundido = posIzq;
						
						/*Si el hijo derecho es menor o igual que el hijo izquierdo
						 * y menor  o igual que el elemento hundiod, se intercambia*/
					}else if(datos[posDch].compareTo(datos[posIzq])<0
						&& datos[posDch].compareTo(datos[posHundido])<= 0) {
						
						intercambiar(posHundido,posDch);
						posHundido = posDch;
					
						//Si solo tiene un hijo, se comprar con el elemento hundido
					}
					
				} else if(posIzq >= 0 && posDch <0) {
						
						/*Si el hijo izquierdo es menor que el elemento hundido,
						 * se produce el intercambio*/
						if(datos[posIzq].compareTo(datos[posHundido])<= 0) {
							
							intercambiar(posHundido,posIzq);
							posHundido = posIzq;
						}
					
				}
					
				//Se actualiza las posiciones de los hijos izquierdo y derecho 	
				posIzq = hijoIzquierdo(posHundido);	
				posDch = hijoDerecho(posHundido);	
			}	
		}	
	}

	/**Determina la posicion del hijo izuquierdo del elemento en la posicion pasada por
	 * parametro. En caso de no existier, devuelve -1
	 * @param posHundido
	 * @return Integer value
	 */
	private int hijoIzquierdo(int posicion) {
		if(posicion*2 <= numElementos) {
			return posicion*2;
		}
		return -1;
	}


	/**Determina la posicion del hijoderecho del elemento en la poisicion pasada por
	 * parametro. En caso de no exister, devuelve -1.
	 * @param posicion
	 * @return Integer value
	 */
	private int hijoDerecho(int posicion) {
		if(posicion*2+1 <= numElementos) {
			return posicion*2;
		}
		return -1;
	}


	/**Determina si la cola de prioridad esta vacia.
	 * Es decir, devuelve cierto si y solo si la cola 
	 * de prioridad no tiene ningun elemento
	 */
	@Override
	public boolean esVacia() {
		return (numElementos <= 0);
	}

	
	
	
	
	
	
	
	
	
}
