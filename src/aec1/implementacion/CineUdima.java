package aec1.implementacion;

import aec1.especificacion.ICine;
import listas.*;
import colas.*;
import excepciones.DesbordamientoInferior;
import pilas.*;

public class CineUdima implements ICine {

    // listas de las zonas
	Lista zonaEntrada;
	Lista zonaProyeccion;
	
    //Si esta en la lista zona de entrada puede acceder a las siguientes colas
	// aqui se puede salir del establecimiento siempre y cuando no se este en ninguna cola
	Cola taquillas_ventanilla_uno;
	Cola taquillas_ventanilla_dos;
	Cola comercio;
	Cola control;
	Cola control_prioritario;
	
	// Si esta en la lista zona de proyeccion puede acceder a las siguientes areas
	// la unica forma de salir aqui es que no este en ninguna de las colas y usando 
	// la cola salida
	Cola aseo_m;
	Cola aseo_h;
	Pila sala_proyeccion;
    Lista salida;
	
	

	public CineUdima() {
		//registro
		zonaEntrada = new ListaEnlazada();
		
		//Zona Entrada
		taquillas_ventanilla_uno = new ColaEnlazada();
		taquillas_ventanilla_dos = new ColaEnlazada();
		comercio = new ColaEnlazada();
		control = new ColaEnlazada();
		control_prioritario = new ColaEnlazada();
		
		//Zona Proyeccion
		zonaProyeccion = new ListaEnlazada();
	    aseo_m = new ColaEnlazada();
		aseo_h = new ColaEnlazada();
		sala_proyeccion = new PilaVector(10);
	    salida = new ListaEnlazada();
	
	}
	/**Se registra el cliente en la zona de la entrada ya que es la primera zona</b>
	 * a la que todos los clietes deben acceder primero
	 * @param cliente
	 */
	@Override
	public void registarCliente(Cliente cliente) {
		if(!zonaEntrada.estaDentro()) {
			zonaEntrada.cero();
		}
           zonaEntrada.insertar(cliente);
	}


	/**
	 * Si el cliente esta en la zona de Entrada, y decide irse 
	 * puede hacerlo libremente, aunque haya comprado entrada
	 * @param cliente
	 */
	@Override
	public boolean borrarCliente(Cliente cliente) {
	      boolean borrado = false;
		
	      if(salida.buscar(cliente) ) {
	    	      sacarClienteDeLista(salida,cliente.getNombre());
	    	      borrado = true;
		}else if( zonaEntrada.buscar(cliente)) {
			  sacarClienteDeLista(zonaEntrada,cliente.getNombre());
			  borrado = true;
		}else if (buscarClienteEnCola( taquillas_ventanilla_uno, cliente.getNombre()) ) {
			sacarClienteDeCola(taquillas_ventanilla_uno,cliente.getNombre());
			borrado = true;
		}else if(buscarClienteEnCola( taquillas_ventanilla_dos, cliente.getNombre()) ) {
			sacarClienteDeCola(taquillas_ventanilla_dos,cliente.getNombre());
			borrado = true;
		}else if (buscarClienteEnCola( comercio, cliente.getNombre()) ) {
			sacarClienteDeCola(comercio,cliente.getNombre());
			borrado = true;
		}else if(buscarClienteEnCola( control, cliente.getNombre()) ) {
			sacarClienteDeCola(control,cliente.getNombre());
			borrado = true;
		}else if(buscarClienteEnCola( control_prioritario, cliente.getNombre()))  {
			sacarClienteDeCola(control_prioritario,cliente.getNombre());
			borrado = true;
		}
		
		return borrado;
	}


	@Override
	public void moverClienteSalaPoyeccion(Cliente cliente) {
		
   
	}
	
	
	public String getListInfoEntrada() {
		String info="";
		
		
		//1 lista entrada
		zonaEntrada.primero();
		while(zonaEntrada.estaDentro()) {
			info+= ((Cliente)zonaEntrada.recuperar()).getNombre();
			info+="\n";
		    zonaEntrada.avanzar();
		}
		
		//3 cola Taquilla_uno
		  info+=getInfoCola(taquillas_ventanilla_uno);
		
		
		return info;
	}
	
	
	/**Recoger la informacion de la cola de Proyeccion**/
	
	public String getListInfoProyeccion() {
		String info="";
		
		//1.Lista sala proyeccion
		zonaProyeccion.primero();
		while(zonaProyeccion.estaDentro()) {
			info+= zonaProyeccion.recuperar();
		    zonaProyeccion.avanzar();
		}
		//2.Aseos señora
		//3.Aseos caballero
		//4.Pila sala proyeccion
		//5.Lista salida
	    
		
		
		return info;
	}
	
	
	public boolean buscarClienteEnCine(Cliente c) {
		
		 if( buscarClienteEnLista(zonaEntrada,c)
		 || buscarClienteEnLista(zonaProyeccion,c)
		 || buscarClienteEnLista(salida,c)
		 || buscarClienteEnCola(taquillas_ventanilla_uno,c.getNombre())
		 || buscarClienteEnCola(taquillas_ventanilla_dos,c.getNombre())
         || buscarClienteEnCola(comercio,c.getNombre())
         || buscarClienteEnCola(control,c.getNombre())
         || buscarClienteEnCola(control_prioritario,c.getNombre())
         || buscarClienteEnCola(aseo_h,c.getNombre())
         || buscarClienteEnCola(aseo_h,c.getNombre())
         || buscarEnSalaCine(c))
		 {
			 return true;
		 }
		

		 return false;

		  
             
	}
	/*-*********************************************************
	 * Funciones auxiliares para ayudar en ciertas tareas 
	 ***********************************************************/
	
	/**
	 * Busca un cliente en una lista dada y lo retorna
	 * @param l
	 * @param nombre
	 * @return Cliente
	 */
	private Cliente sacarClienteDeLista(Lista l , String nombre) {
		
		   l.primero();
		   boolean encontrado = false;
		   Cliente c  = null;
		   while(l.estaDentro() && !encontrado) {
			   c = (Cliente)l.recuperar();
			   if( c.getNombre().equals(nombre)) {   
				   encontrado = true;
				   l.eliminar(c);
			   }else {
				   c = null;
				   l.avanzar();
			   }
		   }
		
		   return c;
	}
	
	/**
	 * Busca a un cliente y lo saca de la cola dejando la cola<\b>
	 * en el mismo orden que estaba sin el cliente
	 * @param c
	 * @param nombre
	 * @return Cliente
	 */
	private Cliente sacarClienteDeCola(Cola c, String nombre) {

		Cola aux = new ColaEnlazada();
		Cliente cliente = null;

		try {
			while(!c.esVacia()) {

				Cliente helper = (Cliente)c.primero();

				if( helper.getNombre().equals(nombre)) {
					cliente = helper;
					c.quitarPrimero();
				}else {
					aux.insertar(c.primero());
					c.quitarPrimero();
				}
			}	
			while( !aux.esVacia()) {
				c.insertar(aux.primero());
				aux.quitarPrimero();
			}

		} catch (DesbordamientoInferior e) {
			//TODO:handle this exception
			System.out.println(e.getMessage());
		}

		return cliente;

	}

	
/**
 * Busca un cliente en una Cola dada
 * @param c
 * @param nombre
 * @return
 */
	public boolean buscarClienteEnCola( Cola c , String nombre) {

		boolean estaEnLaCola  = false;
		Cola aux = new ColaEnlazada();

		try {
           //TODO:Modificar la busqueda en una cola
			while( !c.esVacia()) {
				
				if( ((Cliente)c.primero()).getNombre().equals(nombre)) {
					estaEnLaCola = true;
				}
				aux.insertar(c.primero());
				c.quitarPrimero();
			}
			
			while( !aux.esVacia()) {
				c.insertar(aux.primero());
				aux.quitarPrimero();
			}
		}catch(DesbordamientoInferior e) {

		}


		return estaEnLaCola;

	}

	
	/**Retorna la informacion que se encuentra en una cola**/
	private String getInfoCola(Cola c) {
		Cola aux = new ColaEnlazada();
		String data ="";
		try {
		
			while( !c.esVacia()) {
			    data+=c.primero();
			    data+="\n";
				aux.insertar(c.primero());
				c.quitarPrimero();
			}
				
			while( !aux.esVacia()) {
					
				c.insertar(aux.primero());	
				aux.quitarPrimero();
			}
			} catch (DesbordamientoInferior e) {
				// TODO Auto-generated catch block handle the exception properly
				System.out.println(e.getMessage());
			}
			
		
		return data;
	}
	
	/**Busca un cliente en una lista dada**/
	private boolean buscarClienteEnLista(Lista l,Cliente cliente) {
		
		boolean encontrado = false;
		
		l.primero();
		
	    	 while(l.estaDentro()) {
	    		   
	    		 Cliente aux =(Cliente) l.recuperar();   		 
	    	     
	    		 if(aux.getNombre().equals(cliente.getNombre()) && aux.getPrimerApellido().equals(cliente.getPrimerApellido())
	    			 && aux.getSegundoApellido().equals(cliente.getSegundoApellido())){	     
	    	    	 
	    			 encontrado = true;
	    	     }
	    	 	 l.avanzar();
	    	 }
	    	 l.cero();
	 
	    	 return encontrado;
	}
	
	/**
	 * Busca si un cliente esta en la sala de proyeccion
	 * debido a que es un stack tenemos que buscar una 
	 * forma de buscarlos elementos y volver a ponerlos 
	 * en su sitio
	 * @param c
	 * @throws DesbordamientoInferior 
	 */
	private boolean buscarEnSalaCine(Cliente c)  {
		Pila aux = new PilaVector(10); 
		boolean encontrado = false;
		try {
		
			while(!sala_proyeccion.esVacia()) {
			
				if( c.getNombre().equals(sala_proyeccion.cima())){
					encontrado=true;
				}
				aux.apilar(sala_proyeccion.cima());
				sala_proyeccion.desapilar();
		
			}
			
			while( !aux.esVacia()) {
				sala_proyeccion.apilar(aux.cima());
				aux.desapilar();
			}
			
		} catch (DesbordamientoInferior e) {
		
			//TODO:
		}
			
		
		
		return encontrado;
		
	}

	
}
