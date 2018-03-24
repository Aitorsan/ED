package aa1;
import colas.*;
import excepciones.DesbordamientoInferior;
import listas.*;
public class AA_1 {
	
	
	public static void main( String [] args) {
		
	/*
	 * Apartado 2: TEST  Ejercicio 1:
	 */
		/*Prueba con cola vector*/
		Cola c = new ColaVector(6);
		c.insertar(10);
		c.insertar(5);
		c.insertar(12);
		c.insertar(2);
		c.insertar(3);
		c.insertar(1);
        try {
        	    System.out.println("primer elemento de cola \"c\" ANTES de usar la funcion maxCola:"+ (int)c.primero());
        	    System.out.println("max value: "+ maxCola(c));
 		    System.out.println("primer elemento de cola \"c\"DESPUES de usar la funcion maxCola:"+ (int)c.primero());
        } catch (DesbordamientoInferior e) {
        	    System.out.println(e.getMessage());
		}
        
        /*Prueba con cola Enlazada*/   
        Cola d = new ColaEnlazada();
        d.insertar(10);
		d.insertar(5);
		d.insertar(12);
		d.insertar(2);
		d.insertar(33);
		d.insertar(1);
        try {	
        	    System.out.println("primer elemento de cola \"d\" ANTES de usar la funcion maxCola:"+ (int)d.primero());
			System.out.println("max value: "+ maxCola(d));
			System.out.println("primer elemento de cola \"d\"DESPUES de usar la funcion maxCola:"+ (int)d.primero());
		
        } catch (DesbordamientoInferior e) {
        	    System.out.println(e.getMessage());
	
		}
		
	
	
		
		
		
	}
	/**Apartado 2: Ejercicio 1: 
	 * - función en Java que recibe una cola y retorna el valor más alto de los almacenados en la cola.
	 * - Los elementos que conforman la cola son números enteros.
	 * - La cola, tras la llamada a la función, queda en el mismo estado, mismo número de elementos 
	 *   y en el mismo orden que antes de llamar a la función.
	 * - La función debe trabajar exclusivamente con los métodos de la interfaz Cola. */
	public static int maxCola(Cola cola) {
		
		int max_value = -1;
	if( !cola.esVacia()) {
		Cola aux = new ColaEnlazada();
		try {
			
			max_value = (int) cola.primero();
			
			while( !cola.esVacia()) {
				
				if((int)cola.primero() >  max_value ) {
					max_value = (int)cola.primero();
				}else {
			       aux.insertar(cola.primero());
					cola.quitarPrimero();
				}
				
			}
		
			while( !aux.esVacia()) {
				cola.insertar(aux.primero());
				aux.quitarPrimero();
			}
		}catch(DesbordamientoInferior d) {
			System.out.println(d.getMessage());
		}
	
	}else {
		System.out.println("La cola esta vacia");
	}
		
		return max_value;
	}
	
	
	/**
	 *  APARTADO 2: EJERCICIO 2
	 *  Metodo que convierte un numero a un string en binario.
	 */
	public static String to_binary_string(int numero) {
		
		//String con la representacion binaria de nuestro numero decimal
		String s="";
		/* Esta variable es la que nos va a permitir comprobar que bits en el numero
		 * estan activos y cuales no. Lo inicializamos a 1 que en binario seria tambien
		 * 1
		 */
		int manipulador= 1;
		/* Antes de poder realizar la manipulacion necesitamos saber cuantos bits
		 * Significativos tiene el numero. Por lo tanto el modo de proceder sera
		 * mienietras la diferencia del numero menos el manipulador sea mayor o igual 
		 * que cero significa que el manipulador todavia no ha encontrado el limite
		 * superior de los bits significativos del numero. Por lo tanto mientras esto no 
		 * se cumpla vamos moviendo los bits de la variable manipulador a la izquierda
		 * en una unidad cada vez, de modo que cuando la diferencia sea menor o igual a 0
		 * paramos de trasladar bits hacia la izquierda. En este momento manipulador contiene
		 * un bit mas del que tiene el numero. Por lo tanto una vez acabado debemos deslizar
		 * un bit a la izquierda y de este modo ya tedremos el tamaño de bits del numero introducido.
		 * **********************************************
		 * EJEMPLO 1:
		 * **********************************************
		 *        numero = 5 -> binario 1010
		 *        manipulador = 1 -> binario 1
		 *        
		 *       *Empezamos el loop examinando la condicion:
		 *        5-1 = 4 -> el numero es positivo >=0 por lo tanto hay que realizar el loop
		 *        1 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 2 -> binario 10
		 *        
		 *        2 vuelta:
		 *        ---------
		 *        5-2 = 3 -> el numero es positivo >= 0 por lo tanto seguimos
		 *        2 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 4 -> binario 100
		 *        
		 *        3 vuelta:
		 *        ----------
		 *        5-4 = 1 -> el numero es positivo >= 0 por lo tanto seguimos
		 *        4 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 8 -> binario 1000
		 *        
		 *         4 vuelta:
		 *         ---------
		 *        5-8 = -3 -> el numero es Negativo <= 0 por lo tanto paramos
		 *        resultado: 8 -> binario 1000
		 *        
		 *        *Al finalizar tenemos:
		 *         numero      : 5->  101
		 *         manipulador : 8-> 1000    
		 *         Como observamos manipulador se pasa por un bit a la izquierda.
		 *         Por lo tanto movemos uno a la derecha
		 *         
		 *         8>>1 -> resultado: 4 ->100
		 *        
		 *         Finalmente tenemos situado manipulador donde queriamos
		 *         numero      : 5->  101
		 *         manipulador : 4->  100
		 * *****************************************************        
		 *      EJEMPLO 2: En caso de que el numero sea par.
		 * *****************************************************  
		 *        numero = 4 -> binario 100
		 *        manipulador = 1 -> binario 1
		 *        
		 *        empezamos el loop examinando la condicion
		 *        
		 *        4-1 = 3 -> el numero es positivo >=0 por lo tanto hay que realizar el loop
		 *        1 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 2 -> binario 10
		 *        
		 *        2 vuelta:
		 *        4-2 = 2 -> el numero es positivo >= 0 por lo tanto seguimos
		 *        2 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 4 -> binario 100
		 *        
		 *        3 vuelta:
		 *        4-4 = 0 -> el numero es = 0 por lo tanto seguimos
		 *        4 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 8 -> binario 1000
		 *        
		 *         4 vuelta:
		 *        4-8 = -4 -> el numero es Negativo <= 0 por lo tanto paramos
		 *       
		 *        resultado: 8 -> binario 1000
		 *        
		 *        Al finalizar tenemos:
		 *         numero      : 4->  101
		 *         manipulador : 8-> 1000
		 *         
		 *         como observamos manipulador se pasa por un bit a la izquierda.
		 *         Por lo tanto movemos uno a la derecha
		 *         
		 *         8>>1 -> resultado: 4 ->100
		 *        
		 *         Finalmente tenemos situado manipulador donde queriamos
		 *         numero      : 4->  100
		 *         manipulador : 4->  100
		 */
		while( numero - manipulador >= 0) {
			
			manipulador<<=1;
		}
		 manipulador>>=1;
		/* Ahora que ya sabemos el numero de bits de nuestro numero, ya podemos comprobar que bits estan activos.
		 * Procedemos a comprobar si el manipulador es igual a 0, esto significa que habremos acabado.
		 */
		 while(manipulador != 0) {
			 //Si manipulador AND numero es distinto de cero significa que ese bit esta activo
			 if( (manipulador & numero) != 0) {
				 s+="1";// por lo tanto introducimos 1 en el el string
			 }else {// si es cero 
				 s+="0";// significa que el bit esta inactivo asi que introducimos 0 en el string
			 }
			 manipulador>>=1;// a continuacion movemos el bit que comprueba, que los bits de numero esten activos o no
		 }
		// Al final retornamos el string con la representacion binaria
		return s;
	}

	
	
	
	/**
	 * APARTADO 2: EJERCICIO 3
	 * Se desea implementan una función en Java que reciba una lista y dos objetos o1 y o2.
	 *  La función debe reemplazar todas las ocurrencias del objeto o1 por el objeto o2.
	 *  1.Si el objeto o1 no se encuentra en la lista no se realizará ningún reemplazo.
	 *  2.La lista, tras la llamada a la función, debe tener el mismo número de elementos 
	 *  y en el mismo orden que originalmente: será exactamente igual salvo por los elementos reemplazados.
	 *  3.Se trabajará con objetos (clase Object de Java). Para poder comparar los objetos de la lista y determinar si son iguales.
	 *  4.La función debe trabajar exclusivamente con los métodos de la interfaz Lista.
	 */
	
	public static void reemplazar(Lista lista, Object o1, Object o2) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
