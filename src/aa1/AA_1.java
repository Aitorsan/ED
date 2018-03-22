package aa1;

public class AA_1 {
	
	
	public static void main( String [] args) {
		
		
		
	}
	
	
	/**Ejercicio2 : Metodo que convierte un numero a un string en binario**/
	public static String to_binary_string(int numero) {
		
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
		 *        
		 *        5-1 = 4 -> el numero es positivo >=0 por lo tanto hay que realizar el loop
		 *        1 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 2 -> binario 10
		 *        
		 *        2 vuelta:
		 *        ---------
		 *        
		 *        5-2 = 3 -> el numero es positivo >= 0 por lo tanto seguimos
		 *        2 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 4 -> binario 100
		 *        
		 *        3 vuelta:
		 *        ----------
		 *        
		 *        5-4 = 1 -> el numero es positivo >= 0 por lo tanto seguimos
		 *        4 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 8 -> binario 1000
		 *        
		 *         4 vuelta:
		 *         ---------
		 *        
		 *        5-8 = -3 -> el numero es Negativo <= 0 por lo tanto paramos
		 *        resultado: 8 -> binario 1000
		 *        
		 *        *Al finalizar tenemos:
		 *        
		 *         numero      : 5->  101
		 *         manipulador : 8-> 1000
		 *         
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
		 *        
		 *        4-2 = 2 -> el numero es positivo >= 0 por lo tanto seguimos
		 *        2 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 4 -> binario 100
		 *        
		 *        3 vuelta:
		 *        
		 *        4-4 = 0 -> el numero es = 0 por lo tanto seguimos
		 *        4 << 1  -> lo siguiente sera mover los bits del manipulador a la izquierda
		 *        resultado: 8 -> binario 1000
		 *        
		 *         4 vuelta:
		 *        
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
		 *        
		 */
		while( numero - manipulador >= 0) {
			
			manipulador<<=1;
		}
		 manipulador>>=1;

		/* 
		 * Procedemos a comprobar si el manipulador es igual a 0 significa que habremos acabado
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
