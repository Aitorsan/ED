package aec2.test;

import aec2.metro.implementacion.Estacion;
import aec2.metro.implementacion.MetroMadrid;
import aec2.metro.implementacion.TuplaCaminoValor;
import aec2.metro.interfaz.IMetro;
public class TestEnunciado {

	public static void main(String[] args) {
		
		// Se crea el objeto MetroMadrid
		IMetro metro = new MetroMadrid();

		// Se crean las estaciones
		Estacion R = new Estacion("R");
		Estacion G = new Estacion("G");
		Estacion B = new Estacion("B");
		Estacion T = new Estacion("T");
		Estacion S = new Estacion("S");
		Estacion C = new Estacion("C");
		Estacion E = new Estacion("E");
		Estacion F = new Estacion("F");

		// Se anaden las estaciones a la red
		metro.anadirEstacion(R);//
		metro.anadirEstacion(G);//
		metro.anadirEstacion(B);//
		metro.anadirEstacion(T);//
		metro.anadirEstacion(S);//
		metro.anadirEstacion(C);//
		metro.anadirEstacion(E);//
		metro.anadirEstacion(F);//

		// Se anaden las conexiones entre las diferentes estaciones
		metro.anadirConexion(R, B, 3);
		metro.anadirConexion(R, T, 10);
		metro.anadirConexion(B, T, 10);
		metro.anadirConexion(T, C, 8);//
		metro.anadirConexion(T, G, 40);
		metro.anadirConexion(T, F, 6);
		metro.anadirConexion(C, E, 9);
		metro.anadirConexion(E, F, 7);
		metro.anadirConexion(E, T, 88);
		metro.anadirConexion(F, S, 3);
		metro.anadirConexion(S, G, 7);
		metro.anadirConexion(G, R, 8);

		String[] s = ((MetroMadrid)metro).getStations();
		for(String n : s ){
			System.out.println(n);
		}
		//comprobar que esta todo introducido correctamente
		System.out.println("\n----------------- ANTES DE LA ELIMINACION-----------------------\n");
	     ((MetroMadrid)metro).imprimirRed();
         //ponemos un poquito de espacio 
	     System.out.println();
		// Se definen las estaciones de origen y destino y se calculan los caminos
		Estacion origen = R;
		Estacion destino = G;

		TuplaCaminoValor tuplaCaminoMenosEstaciones = metro.caminoMenosEstaciones(origen, destino);
		TuplaCaminoValor tuplaCaminoMasRapido = metro.caminoMasRapido(origen, destino);

		// Se muestra el resultadodefinen las estaciones de origen y destino y se
		// calculan los caminos
		System.out.println(String.format(
				"Estimado usuario,\n" + "El camino mas rapido para ir de la estacion %s a la estacion %s "
						+ "tiene una duracion de %s minutos y el camino a seguir es %s.\nSi "
						+ "por el contrario desea conocer el camino con menos estaciones "
						+ "debe saber que el camino a seguir es %s y usted pasara solamente por %s estaciones.\n"
						+ "Reciba un cordial saludo.\n" + "Metro de Madrid.\n",origen, destino, tuplaCaminoMasRapido.getValor(), 
						tuplaCaminoMasRapido.getCamino(),tuplaCaminoMenosEstaciones.getCamino(), tuplaCaminoMenosEstaciones.getValor()));

		/*******************************************************************************************************
		 * TEST EXTRAS
		 *******************************************************************************************************/
		
		/*****************************************************************************************************************
		 *  1.  TEST de eliminar estacion F, al eliminar la estacion F tambien eliminamos sus correspondientes aristas
		 *****************************************************************************************************************/
		metro.eliminarEstacion(F);

		System.out.println("\n----------------- DESPUES DE LA ELIMINACION ( estacion F y todas sus conexiones)-----------------------\n");
	
		((MetroMadrid)metro).imprimirRed();
		origen = R;
		destino = G;

		tuplaCaminoMenosEstaciones = metro.caminoMenosEstaciones(origen, destino); 
		tuplaCaminoMasRapido = metro.caminoMasRapido(origen, destino);
	
				
		System.out.println(String.format(
				"Estimado usuario,\n" + "El camino mas rapido para ir de la estacion %s a la estacion %s "
						+ "tiene una duracion de %s minutos y el camino a seguir es %s.\nSi "
						+ "por el contrario desea conocer el camino con menos estaciones "
						+ "debe saber que el camino a seguir es %s y usted pasara solamente por %s estaciones.\n"
						+ "Reciba un cordial saludo.\n" + "Metro de Madrid.\n",origen, destino, tuplaCaminoMasRapido.getValor(), 
						tuplaCaminoMasRapido.getCamino(),tuplaCaminoMenosEstaciones.getCamino(), tuplaCaminoMenosEstaciones.getValor()));

		/****************************************************************************************************************
		 * 2.TEST
		 *  
         * Eliminar una sola conexion entre una estacion origen y una destino
         * 
         * 
		 *****************************************************************************************************************/
		System.out.println("\n\n----------------- TEST ELIMINACION UNA CONEXION ( de la estacion T con G) -----------------------\n\n");
		
		System.out.println("\nGrafo antes de la eliminacion de la conexion de T con G, (40,G):\n");
		((MetroMadrid)metro).imprimirRed();
		System.out.println("\nGrafo despues de la eliminacion de la conexion de T con G, (40,G):\n");
	     
		((MetroMadrid)metro).eliminarConexion(T, G);//metodo de eliminacion de conexiones entre dos estaciones
		((MetroMadrid)metro).imprimirRed();
		
		/*****************************************************************************************************************
		 * 3.TEST
		 *  
         * Eliminar todas las conexiones hacia una estacion:
         * Podria darse el caso de canbio de rutas hacia una estacion en concreto por lo tanto esa ruta ya no estaria
         * presente en las otras estaciones. Sin embargo la estacion seguiria conservando sus rutas
         * 
		 *****************************************************************************************************************/
		System.out.println("\n----------------- ANTES DE LA ELIMINACION DE LAS RUTAS HACIA LA ESTACION T (las estaciones B y R apuntan a la estacion T)-----------------------\n");
		((MetroMadrid)metro).imprimir();
		
		((MetroMadrid)metro).eliminarConexionesAEstacion(T);
		System.out.println("\n----------------- DESPUES DE LA ELIMINACION DE LAS RUTAS HACIA LA ESTACION T ( las estaciones B y R no apuntan a la estacion T)-----------------------\n");
		((MetroMadrid)metro).imprimir();
		
		/*****************************************************************************************************************
		 * 4.TEST
		 *  
         * Aislar una estacion:
         * Podria darse el caso de que hubiera una averia en esa estación temporalmente podriamos desabilitar todas
         * las conexiones hacia esta.
         * Se eliminan todas las conexiones hacia esta estacion y todas las conexiones de esta estacion hacia otras estaciones.
         * Pero no se elimina la estacion.
		 *****************************************************************************************************************/
		
		//Recuperamos las conexiones eliminadas ya que el grafo ha sido disminuido signifcantemente
		metro.anadirConexion(B, T, 10);
		metro.anadirConexion(B, S, 15);
		System.out.println("\n----------------- ANTES DE LA DE AISLAR LA ESTACION B (la estacion R apunta a la estacion B, y B apunta a la estacion T y S)-----------------------\n");
		System.out.println("Debido a la disminucion del grafo añadimos dos conexiones a B antes de aislarla:\nmetro.anadirConexion(B, T, 10);\n" + 
		                   "metro.anadirConexion(B, S, 15);\n");
		((MetroMadrid)metro).imprimir();
		((MetroMadrid)metro).aislarEstacion(B);
		System.out.println("\n----------------- DESPUES DE LA DE AISLAR LA ESTACION B (la estacion R ya NO apunta a la estacion B, y B Ya NO apunta a la estacion T y S, o cualquier otra)-----------------------\n");
		((MetroMadrid)metro).imprimir();
		
		
		
	}//end main

}//end class
