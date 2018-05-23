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
		metro.anadirConexion(T, C, 8);
		metro.anadirConexion(T, G, 40);
		metro.anadirConexion(T, F, 6);
		metro.anadirConexion(C, E, 9);
		metro.anadirConexion(E, F, 7);
		metro.anadirConexion(E, T, 88);
		metro.anadirConexion(F, S, 3);
		metro.anadirConexion(S, G, 7);
		metro.anadirConexion(G, R, 8);

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

		// El resultado mostrado por este test se encuentra en el enunciado de nuestra
		// actividad
		
		/**TEST eliminar estacion F, al elminar la estacion F tambien eliminamos sus correspondientes aristas**/
		metro.eliminarEstacion(F);
		 
		origen = R;
		destino = G;

		tuplaCaminoMenosEstaciones = metro.caminoMenosEstaciones(origen, destino); 
		tuplaCaminoMasRapido = metro.caminoMasRapido(origen, destino);
		System.out.println("\n----------------- TEST ELIMINACION  ( estacion F y todas sus conexiones) -----------------------\n");
				
		System.out.println(String.format(
				"Estimado usuario,\n" + "El camino mas rapido para ir de la estacion %s a la estacion %s "
						+ "tiene una duracion de %s minutos y el camino a seguir es %s.\nSi "
						+ "por el contrario desea conocer el camino con menos estaciones "
						+ "debe saber que el camino a seguir es %s y usted pasara solamente por %s estaciones.\n"
						+ "Reciba un cordial saludo.\n" + "Metro de Madrid.\n",origen, destino, tuplaCaminoMasRapido.getValor(), 
						tuplaCaminoMasRapido.getCamino(),tuplaCaminoMenosEstaciones.getCamino(), tuplaCaminoMenosEstaciones.getValor()));

		System.out.println("\n----------------- DESPUES DE LA ELIMINACION-----------------------\n");
	
		((MetroMadrid)metro).imprimirRed();
		
		System.out.println("\n\n----------------- TEST ELIMINACION UNA CONEXION ( de la estacion T) -----------------------\nn");
		
		System.out.println("\nGrafo antes de la eliminacion de la conexion de T con G, (40,G):\n");
		((MetroMadrid)metro).imprimirRed();
		System.out.println("\nGrafo despues de la eliminacion de la conexion de T con G, (40,G):\n");
	     
		((MetroMadrid)metro).eliminarConexion(T, G);
		((MetroMadrid)metro).imprimirRed();
		
		
		
		
	}//end main

}//end class
