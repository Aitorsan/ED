package aa1;
import colas.*;
import excepciones.DesbordamientoInferior;
import listas.*;
import pilas.Pila;
import pilas.PilaEnlazada;

/**
 * APARTADO 2 de la actividad AA1 ESTRUCTURAS DE DATOS-UDIMA
 * @author aitorSf
 */
public class AA_1 {


	/**APARTADO 2: EJERCICIO 1: 
	 * - función en Java que recibe una cola y retorna el valor más alto de los almacenados en la cola.
	 * - Los elementos que conforman la cola son números enteros.
	 * - La cola, tras la llamada a la función, queda en el mismo estado, mismo número de elementos 
	 *   y en el mismo orden que antes de llamar a la función.
	 * - La función debe trabajar exclusivamente con los métodos de la interfaz Cola. */
	public static int maxCola(Cola cola) {
		
		Integer max_value = null;
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
		//string usado para la representacion binaria
		String s = "";
		//estructura de datos utilizada
        Pila c = new PilaEnlazada();
        try {
        	
			while( numero > 0) {
				c.apilar(numero % 2);// si el numero es par nos da de resto 0 de lo contrario nos da 1, este resultado lo apilamos 
				numero/=2;// dividimos el numero por factor de dos
			}
		
			//mientras la pila no esta vacia insertamos los elementos en el string uno a uno en orden inverso
			while( !c.esVacia()) {
				s+=c.cima();
				c.desapilar();
			}
        }catch(DesbordamientoInferior e) {
        	   System.out.println(e.getMessage());
        }
			return s;
	}
	/**
	 * APARTADO 2: EJERCICIO 3
	 * Se desea implementan una función en Java que reciba una lista y dos objetos o1 y o2.
	 *  La función debe reemplazar todas las ocurrencias del objeto o1 por el objeto o2.[X]
	 *  1.Si el objeto o1 no se encuentra en la lista no se realizará ningún reemplazo.[X]
	 *  2.La lista, tras la llamada a la función, debe tener el mismo número de elementos 
	 *  y en el mismo orden que originalmente: será exactamente igual salvo por los elementos reemplazados.[X]
	 *  3.Se trabajará con objetos (clase Object de Java).[X]
	 *  4.La función debe trabajar exclusivamente con los métodos de la interfaz Lista.[X]
	 */
	public static void reemplazar(Lista lista, Object o1, Object o2) {
		//situamos el nodo actual en el primer elemento  para recorrer la lista desde el principio 
		lista.primero();
		    //mientras se encuentre el elemento buscado, situamos el nodo actual en odnde esta el elemento que queremos sustituir
		    while( lista.buscar(o1) ) {
			lista.insertar(o2);// Insertamos el nuevo elemento justo despues del que queremos sustituir
			lista.eliminar(o1);//eliminamos el nodo y de este modo queda en la misma posicion que estaba
		}
	}
	
	
	
/*-***********************************************************************************************************************************
 * ALTERNATIVAS PARA EL APARTADO 2: EJERCICIO 2
 *************************************************************************************************************************************/
	
	/**
	 *  APARTADO 2: EJERCICIO 2.1(Alternativa 1) 
	 *  Metodo que convierte un numero a un string en binario.
	 */
	public static String to_binary_string_bit(int numero) {
		
		String s = "";

			while( numero > 0) {
				/* El equivalente a modulo operator usando bit operators es ("numero" AND 2^n-1), esto nos da la misma operacion.
				 * 
				 * Logica para convertir a binary string:
				 * 
				 * Al dividir cualquier numero entre 2, el resto solo nos puede dar o 0 o 1, debido a que queremos averiguar
				 * nuestro numero en base 2, tendremos que operar cualquier numero que queramos contra el modulo de 2.
				 * Esto significa que los numeros impares por ejemplo el 3 van a tener un resto de 1, ya que 3 si intentamos 
				 * dividirlo entre dos solo cabrian 2 y 1 quedaria fuera. Sin embargo cualquier numero par
				 * seria posible dividirlo en 2 partes iguales dandonos un resto de 0.
				 *
				 * 
				 * 1. hacemos el numero modulos 2 y el resultado lo almacenamos en el string
				 * 2. dividimos el numero entre dos
				 * 
				 * Esto se repite hasta que nuestro numero quede a cero. Las operaciones quedarian asi:
				 *   s+= numero%2;
				 *   numero/=2;
				 *   
				 * Podemos usar la misma logica usando manipulación bit. 
				 */
				s += numero & 1;  
				numero>>=1;// dividimos el numero por dos, o movemos los bits una posicion a la derecha
			}
			return s;
	}

	/**
	 *  APARTADO 2: EJERCICIO 2.2(Alternativa 2) quizas demasiado complicada a parte de que no es muy eficiente
	 *  Metodo que convierte un numero a un string en binario.
	 */
	public static String to_binary_string_bit_2(int numero) {
		
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
	

	
	

	
}
