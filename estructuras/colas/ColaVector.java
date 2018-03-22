package colas;

import excepciones.DesbordamientoInferior;

public class ColaVector implements Cola {


		/**Vector que contiene los elementos de la cola*/
		private Object []datos;
		
		/**Posicion del primer, ultimo elemento. Y el numero de elementos en la cola*/
		private int primero, fin, numElementos;
		
		/**
		 * Constructor de la clase crea una cola vacia y el vector tendra el tamaño
		 * indicado por el parametro tam.
		 * @param tam
		 */
		public ColaVector(int tam) {
			
			datos = new Object[tam];
			primero =0;
			fin = -1;
			numElementos = 0;
		}
		
		/**Inserta un elemento al final de la cola*/
		@Override
		public void insertar(Object elemento) {
		 
			if(numElementos >= datos.length) {
				System.out.println("la cola esta llena");
			}else {
				++fin;
				fin %= datos.length;
				datos[fin] = elemento;
				++numElementos;
			}
			
		}

		/**Devuelve el elemento que lleva mas tiempo en la cola*/
		@Override
		public Object primero() throws DesbordamientoInferior {
	           if( esVacia()) {
	        	     throw new DesbordamientoInferior("La cola ya no contiene mas elementos, imposible extraer datos");
	           }

			return datos[primero];
		}

		/**Elimina el elemnto que lleva mas tiempo en la cola*/
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
		
		/**Determina si la cola esta vacia*/
		@Override
		public boolean esVacia() {
			return (numElementos == 0);
		}
	

	}


