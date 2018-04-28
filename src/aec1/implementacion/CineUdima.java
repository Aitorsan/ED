package aec1.implementacion;

import java.util.StringTokenizer;
import aec1.especificacion.ICine;
import listas.*;
import colas.*;
import excepciones.DesbordamientoInferior;
import pilas.*;

public class CineUdima implements ICine {

	//flags
	public static final char ENTRADA       = 0b00000001;
	public static final char TAQUILLA_UNO  = 0b00000010;	
	public static final char TAQUILLA_DOS = 0b00000011;	
	public static final char COMERCIO      = 0b00000100;	
	public static final char CONTROL       = 0b00000101;
	public static final char CONTROL_P     = 0b00000110 ;	
	public static final char ASEOSM        = 0b00000111;	
	public static final char ASEOSH        = 0b00001000;
	public static final char SALA          = 0b00001001;	
	public static final char SALIDA        = 0b00001010;	
	public static final char LISTA_ZONA_PROYECCION = 0b00001011;
	public static final char ALL           = 0b00001111;
    // Lists
	Lista zonaEntrada;
	Lista zonaProyeccion;
	
    //If the client is within the entry area can acces those queues
	// In this area the client can always go out from the cinema without using the exit door.
	Cola taquillas_ventanilla_uno;
	Cola taquillas_ventanilla_dos;
	Cola comercio;
	Cola control;
	Cola control_prioritario;
	
	 //If the client is within the entry area can acces the areas listed below
	 //The only way to go out from the cinema once the client is inside this are is using
	// the exit List "salida".
	Cola aseo_m;
	Cola aseo_h;
	Pila sala_proyeccion;
    Lista salida;
	
/**
 * CindeUdima Constructor
 */
	public CineUdima() {
		//register
		zonaEntrada = new ListaEnlazada();
		
		//Entry area
		taquillas_ventanilla_uno = new ColaEnlazada();
		taquillas_ventanilla_dos = new ColaEnlazada();
		comercio = new ColaEnlazada();
		control = new ColaEnlazada();
		control_prioritario = new ColaEnlazada();
		
		//Projection area
		zonaProyeccion = new ListaEnlazada();
	    aseo_m = new ColaEnlazada();
		aseo_h = new ColaEnlazada();
		sala_proyeccion = new PilaVector(10);
	    salida = new ListaEnlazada();
	
	}
	/**
	 * Register method
	 * @param cliente
	 */
	@Override
	public void registarCliente(Cliente cliente) {
	
           zonaEntrada.insertar(cliente);
	}
	
	/**
	 * Search a a given client within the cinema
	 * @param cliente
	 * @return true if the client is found
	 */
	public boolean buscarClienteEnCine(Cliente cliente) {
		
		 if( isTheSame(zonaEntrada,cliente)
		 || isTheSame(zonaProyeccion,cliente)
		 || isTheSame(salida,cliente)
		 || isTheSame(taquillas_ventanilla_uno,cliente)
		 || isTheSame(taquillas_ventanilla_dos,cliente)
         || isTheSame(comercio,cliente)
         || isTheSame(control,cliente)
         || isTheSame(control_prioritario,cliente)
         || isTheSame(aseo_h,cliente)
         || isTheSame(aseo_h,cliente)
         || buscarEnSalaCine(cliente))
		 {
			 return true;
		 }
		
		 return false;
             
	}
	
	/**
	 * Search for a client within the cinema 
	 * @param nameSurname
	 * @return If a client is found return that client object
	 */
	public Cliente buscarClienteEnCine(String nameSurname) {

		    
		Cliente client = search( zonaEntrada,nameSurname);

		if( client == null) 
			client =search( zonaProyeccion,nameSurname);

		if( client == null) 
			client = search( taquillas_ventanilla_uno,nameSurname);

		if( client == null) 
			client = search( taquillas_ventanilla_dos,nameSurname);

		if( client == null) 
			client =  search( comercio,nameSurname);

		if( client == null) 
			client =  search( control,nameSurname);

		if( client == null) 
			client =  search( control_prioritario,nameSurname);

		if( client == null) 
			client = search( salida, nameSurname);
       
		if(client == null) {
            client = search(aseo_h,nameSurname);	
        }
		if( client == null) {
			client = search(aseo_m,nameSurname);
		}
			  	   
		 return client;
	}
	
	/**
	 * Search for a client within the cinema 
	 * @param nameSurname
	 * @return If a client is found return that client object
	 */
	public Cliente buscarClienteEnCineDesdeEntrada(String nameSurname) {
		   
		Cliente client = search( zonaEntrada,nameSurname);
			
		if( client == null) 
			client = search( taquillas_ventanilla_uno,nameSurname);

		if( client == null) 
			client = search( taquillas_ventanilla_dos,nameSurname);

		if( client == null) 
			client =  search( comercio,nameSurname);

		if( client == null) 
			client =  search( control,nameSurname);

		if( client == null) 
			client =  search( control_prioritario,nameSurname);

			  	   
		 return client;
	}
	
	/**
	 * Moves a client from one place to another if that client exist.
	 * @param c
	 * @param where
	 * @throws Exception
	 */
	  public void move( Cliente c , String where)throws Exception {
		
		  if( c != null) {
			 if( where.equals("Lista Entrada")&& !isTheSame(zonaEntrada,c)) {
				
				  zonaEntrada.insertar(c);
			  }
			  else if( where.equals("Taquillas") && !isTheSame(taquillas_ventanilla_uno,c)&& !isTheSame(taquillas_ventanilla_dos,c)) {
				  int lessPopulatedQueue = 0, taquillaUno = 0,taquillaDos = 0;
				 taquillaUno =  countClientsTicketOffice(taquillas_ventanilla_uno);
				 taquillaDos =  countClientsTicketOffice(taquillas_ventanilla_dos);
				  
				 if(taquillaDos < taquillaUno)
					 lessPopulatedQueue = 2;
				 
				  switch(lessPopulatedQueue) {
				  
				  case 0:
				  case 1:
					  taquillas_ventanilla_uno.insertar(c);
					  lessPopulatedQueue = 1;
				  break;
				  case 2:
					  taquillas_ventanilla_dos.insertar(c);
		    	  break;
				  
				  }
				 throw new Exception("El cliente ha elegido la taquilla: "+lessPopulatedQueue);
			  }
			  else if( where.equals("Comercio")&&  !isTheSame(comercio,c)) {
				  comercio.insertar(c);
			  }
			  else if( where.equals("Control prioritario")&& !isTheSame(control_prioritario,c)) {
				  if( c.prioritario()) {
	                 
					  control_prioritario.insertar(c);
				  }else {
					  control.insertar(c);
					  throw new Exception("El cliente no es prioritario");
					  
				  }
				 
			  }
			  else if( where.equals( "Control") && !isTheSame(control,c)){
				  if(c.prioritario()) {
					  control_prioritario.insertar(c);
					  throw new Exception("El cliente debe ir a la cola prioritaria");
				  }else {
					  control.insertar(c);
				  }
				 
			  }
			  else if(where.equals("Proyeccion")) {
				  zonaProyeccion.insertar(c);
			  }else if(where.equals("Aseo hombres")) {
				  if( c.getSexo() == 'H') {
                      aseo_h.insertar(c);
				  }else {
					  aseo_m.insertar(c);
                     throw new Exception("El cliente es una mujer no puede entrar en el aseo de hombres el cliente ha sido movido al aseo correspondiente");
				  }

			  }else if(where.equals("Aseo mujeres")) {
				  if( c.getSexo() == 'M') { 
					  aseo_m.insertar(c);
				  }else {
					  aseo_h.insertar(c);
					     throw new Exception("El cliente es un hombre no puede entrar en el aseo de mujeres, el cliente ha sido movido al aseo correspondiente");
				  }  
			  }else if(where.equals("Sala cine")){
				  int num = contar_clientes_sala();
				  if(num <10) {
					  sala_proyeccion.apilar(c);
				  }else {
					  zonaProyeccion.insertar(c);
					  throw new Exception ("La sala esta llena");
				  }
			  }else if(where.equals("Salida")) {
				  salida.insertar(c);
			  }
		}  
		  
	  }
	  
		/**
		 * Search a client inside the projection room by a given name and surname
		 * @param nameAndSurname
		 * @return
		 */
		public boolean buscarCLienteEnSalaCine(String nameAndSurname) {
			  StringTokenizer str = new StringTokenizer(nameAndSurname);
			   String name = str.nextToken();
			   String surname = str.nextToken();
			   String secondSurname = str.nextToken(); 
			   Pila stack = new PilaVector(10);
				boolean found = false;
				try {
				
					while(!sala_proyeccion.esVacia()) {
					
						if( name.equals(((Cliente)sala_proyeccion.cima()).getNombre())&&
						    surname.equals(((Cliente)sala_proyeccion.cima()).getPrimerApellido())&&
						    secondSurname.equals(((Cliente)sala_proyeccion.cima()).getSegundoApellido())){
							found=true;
						}
						
						stack.apilar(sala_proyeccion.cima());
						sala_proyeccion.desapilar();
				
					}
					
					while( !stack.esVacia()) {
						sala_proyeccion.apilar(stack.cima());
						stack.desapilar();
					}
				
					
				} catch (DesbordamientoInferior e) {
				
					System.out.println("Error en pila Line: 519 CineUdima.java\nError message: "+e.getMessage());
				}
					
				return found;
		}
	  
	/*-*********************************************************
	 * Auxiliar methods to help the class to make certain task
	 ***********************************************************/
		/**
	    * Search for a client on the list, with a given name and surname
	    * @param l
	    * @param name
	    * @return
	    */
	   private Cliente search(Lista l , String nameAndSurname) {
		   StringTokenizer str = new StringTokenizer(nameAndSurname);
		   String name = str.nextToken();
		   String surname = str.nextToken();
		   String secondSurname = str.nextToken();
		   l.primero();
		    Cliente moved_client = null;
		  boolean found = false;
		   while(l.estaDentro()&&!found) {
			   
			   if( name.equals(((Cliente)l.recuperar()).getNombre())
					   &&surname.equals(((Cliente)l.recuperar()).getPrimerApellido())
					   &&secondSurname.equals(((Cliente)l.recuperar()).getSegundoApellido())) {
				   moved_client = (Cliente)l.recuperar();
				   l.eliminar(l.recuperar());
				 found = true;
			   }
			   l.avanzar();
			   
		   }
		   l.primero();
		   return moved_client;
	   }
	
		  /**
		   * Count the number of clients in a queue, the client will choose<br>
		   * the queue with less people waiting.
		   * @param taquillas_ventanilla_uno2
		   * @return
		   */
		 private int countClientsTicketOffice(Cola c) {
			 int counter = 0;
			 Cola aux =null; 
			try{
				 aux = new ColaEnlazada();
				 while(!c.esVacia()) {
					 ++counter;
					 aux.insertar(c.primero());
					 c.quitarPrimero();
				 }
				 while(!aux.esVacia()) {
					 c.insertar(aux.primero());
					 aux.quitarPrimero();
				 }
			}catch(DesbordamientoInferior e) {
				System.out.println(e.getMessage());
			}
			
			return counter;
		}
		 
		 /**
		    * Find and delete a client from the queue
		    * @param c
		    * @param name
		    * @return true if the client was found 
		    */
		   private Cliente search(Cola c , String nameAndSurname) {
			   StringTokenizer str = new StringTokenizer(nameAndSurname);
			   String name = str.nextToken();
			   String surname = str.nextToken();
			   String secondSurname = str.nextToken();
			  Cliente client = null;
			   Cola auxiliar = new ColaEnlazada();
			
			   try {
				   while(!c.esVacia() ) {
					   
					   if( name.equals(((Cliente)c.primero()).getNombre())
							   &&surname.equals(((Cliente)c.primero()).getPrimerApellido())
							   &&secondSurname.equals(((Cliente)c.primero()).getSegundoApellido())) {
						   client = (Cliente)c.primero();
						   c.quitarPrimero();
						
					   }else {
						   auxiliar.insertar(c.primero());
		                   c.quitarPrimero();	 
					   }
							   
				   }
				   while(!auxiliar.esVacia()) {
					   c.insertar(auxiliar.primero());
					   auxiliar.quitarPrimero();
				   }
				   
			   }catch(DesbordamientoInferior e) {
				   System.out.println("Error Cola line: 365 CineUdima.java class\nError message: "+ e.getMessage());
			   }
			
			   return client;
			   
		   }
	   
  //Getters returns all the data structures use to store all the clients it is use by the handler
	public Lista getZonaEntrada() {
		return zonaEntrada;
	}
	public Lista getZonaProyeccion() {
		return zonaProyeccion;
	}
	public Cola getTaquillas_ventanilla_uno() {
		return taquillas_ventanilla_uno;
	}
	public Cola getTaquillas_ventanilla_dos() {
		return taquillas_ventanilla_dos;
	}
	public Cola getControl_prioritario() {
		return control_prioritario;
	}
	public Pila getSala_proyeccion() {
		return sala_proyeccion;
	}
	public Lista getSalida() {
		return salida;
	}
	
	public Cola getComercio() {
		return comercio;
	}
	public Cola getControl() {
		return control;
	}
	public Cola getAseo_m() {
		return aseo_m;
	}
	public Cola getAseo_h() {
		return aseo_h;
	}
	
/**
 * Busca un cliente en una Cola dada
 * @param c
 * @param nombre
 * @return
 */
	private boolean isTheSame( Cola cola , Cliente cliente) {

		boolean estaEnLaCola  = false;
		Cola aux = new ColaEnlazada();

		try {

			while( !cola.esVacia()) {
				
				if( ((Cliente)cola.primero()).getNombre().equals(cliente.getNombre())
						&& ((Cliente)cola.primero()).getPrimerApellido().equals(cliente.getPrimerApellido())
						&& ((Cliente)cola.primero()).getSegundoApellido().equals(cliente.getSegundoApellido())) {
					estaEnLaCola = true;
				}
				aux.insertar(cola.primero());
				cola.quitarPrimero();
			}
			
			while( !aux.esVacia()) {
				cola.insertar(aux.primero());
				aux.quitarPrimero();
			}
		}catch(DesbordamientoInferior e) {

			System.out.println("Error en cola Line: 445 CineUdima.java\nError message: "+e.getMessage());
		}

		return estaEnLaCola;
	}

/**
 * Search a client within a given list
 * @param l
 * @param cliente
 * @return
 */
	private boolean isTheSame(Lista l,Cliente cliente) {
		
		boolean encontrado = false;
		
		l.primero();
		
	    	 while(l.estaDentro()) {
	    		    		 
	    	     Cliente aux = (Cliente)l.recuperar();
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
	 * Search who is the last client that enters in the projection room
	 * @param nameAndSurname
	 * @return
	 */
	public boolean isLastInProjectionRoom(String nameAndSurname) {
		  StringTokenizer str = new StringTokenizer(nameAndSurname);
		   String name = str.nextToken();
		   String surname = str.nextToken();
		   String secondSurname = str.nextToken(); 
			boolean isFirst = false;
			try {
			
				if(!sala_proyeccion.esVacia()) {
				
					if( name.equals(((Cliente)sala_proyeccion.cima()).getNombre())&&
					    surname.equals(((Cliente)sala_proyeccion.cima()).getPrimerApellido())&&
					    secondSurname.equals(((Cliente)sala_proyeccion.cima()).getSegundoApellido())){
						isFirst=true;
					}
			
				}
				
			} catch (DesbordamientoInferior e) {
			
				System.out.println("Error en lista Line: 550 CineUdima.java\nError message: "+e.getMessage());
			}
					
			return isFirst;
	}
	
	/** 
	 * Search a client within the projection room stack
	 * @param c
	 * @throws DesbordamientoInferior 
	 */
	private boolean buscarEnSalaCine(Cliente c)  {
		Pila aux = new PilaVector(10); 
		boolean encontrado = false;
		try {
		
			while(!sala_proyeccion.esVacia()) {
			
				if( c.getNombre().equals(((Cliente)sala_proyeccion.cima()).getNombre())&&
						c.getPrimerApellido().equals(((Cliente)sala_proyeccion.cima()).getPrimerApellido())
						&& c.getSegundoApellido().equals(((Cliente)sala_proyeccion.cima()).getSegundoApellido())){
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
		    System.out.println("Line:582 CineUdima.java class\nError message: "+e.getMessage());
		}
			
		return encontrado;
	}

	/**
	 * Search and delete the client if its found in on of the following areas
	 * @param nameSurname
	 * @return boolean value true if the client was successfully deleted
	 */
	   public boolean deleteClient(String nameSurname) {
		   
		  boolean borrado = false;
		  if(searchAndDelete(zonaEntrada,nameSurname)
		  || searchAndDelete(taquillas_ventanilla_uno,nameSurname)
		  || searchAndDelete( taquillas_ventanilla_dos,nameSurname)
		  || searchAndDelete(comercio,nameSurname)
		  || searchAndDelete( control,nameSurname)
		  || searchAndDelete(control_prioritario,nameSurname))
	       {
			  borrado = true;
		  }
		   
		   return borrado;
	   }
	   
	   /**
	    * Find and delete a client from the queue
	    * @param c
	    * @param name
	    * @return true if the client was found 
	    */
	   private boolean searchAndDelete(Cola c , String nameAndSurname) {
		   StringTokenizer str = new StringTokenizer(nameAndSurname);
		   String name="";
		   String surname="" ;
		   String secondSurname="";
		   if(str.hasMoreTokens())
		      name = str.nextToken();
		   if(str.hasMoreTokens())
		       surname = str.nextToken();
		   if(str.hasMoreTokens())
		       secondSurname = str.nextToken();
		  
		   Cola auxiliar = new ColaEnlazada();
		   boolean borrado = false;
		   try {
			   while(!c.esVacia() ) {
				   
				   if( name.equals(((Cliente)c.primero()).getNombre())
						   &&surname.equals(((Cliente)c.primero()).getPrimerApellido())
						   &&secondSurname.equals(((Cliente)c.primero()).getSegundoApellido())) {
					   c.quitarPrimero();
					   borrado = true;
				   }else {
					   auxiliar.insertar(c.primero());
	                   auxiliar.quitarPrimero();
				   }
								   
			   }
			   while(!auxiliar.esVacia()) {
				   c.insertar(auxiliar.primero());
				   auxiliar.quitarPrimero();
			   }
			   
		   }catch(DesbordamientoInferior e) {
			   System.out.println("Error en cola Line: 651 CineUdima.java\nError message: "+e.getMessage());
		   }
		
		   return borrado;
	   }
	   
	   /**
	    * Delete a client from a list
	    * @param l
	    * @param name
	    * @return
	    */
	   private boolean searchAndDelete(Lista l , String nameAndSurname) {
		   StringTokenizer str = new StringTokenizer(nameAndSurname);
		   String name = str.nextToken();
		   String surname = str.nextToken();
		   String secondSurname = str.nextToken();
		   l.primero();
		   boolean borrado = false;
		   while(l.estaDentro()) {
			   
			   if( name.equals(((Cliente)l.recuperar()).getNombre())
					   &&surname.equals(((Cliente)l.recuperar()).getPrimerApellido())
					   &&secondSurname.equals(((Cliente)l.recuperar()).getSegundoApellido())) {
				   l.eliminar(l.recuperar());
				   borrado = true;
			   }
			   l.avanzar();
			   
		   }
		   l.primero();
		   return borrado;
	   }
	   
	   
	   /**
	    * Because our data structure do not have a counter to know how many elements has inside.
	    * We have to make a helper function to compute that.
	    * @return
	    */
	  private int contar_clientes_sala() {
		  
		  Pila aux = new PilaVector(10); 
		int contador = 0;
			try {
			
				while(!sala_proyeccion.esVacia()) {
				
					++contador;
					aux.apilar(sala_proyeccion.cima());
					sala_proyeccion.desapilar();
				}
				
				while( !aux.esVacia()) {
					sala_proyeccion.apilar(aux.cima());
					aux.desapilar();
				}
				
			} catch (DesbordamientoInferior e) {
			    System.out.println("Line:714 CineUdima.java class\nError message: "+e.getMessage());
			}
				
		  return contador;
	  }
}//End of class
