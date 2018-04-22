package aec1.implementacion;


/**
 * Client class content all the information relevant to register a single client on the system
 * @author Aitor Sanmartin Ferreira
 *
 */
public class Cliente {

	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private int edad;
	private Prioridad prioridad;
    private char sexo;
    private int entrada;
 
    /**Constructor without parameters**/
    public Cliente() {
    	  
	     this.nombre = null;
	     this.primerApellido = null;
	     this.segundoApellido = null;
	     this.edad = 0;
	     this.prioridad = null;
	     this.sexo = 0;
	     this.entrada = 0;
    
    }
    /**Constructor with parameters**/
    public Cliente( String nombre,String primerApellido,String segundoApellido, int edad, Prioridad prioridad, char sexo) {
    	   
    	     this.nombre = nombre;
    	     this.primerApellido= primerApellido;
    	     this.segundoApellido = segundoApellido;
    	     this.edad = edad;
    	     this.prioridad = prioridad;
    	     this.sexo = sexo;
    	     this.entrada = 0;
    }
    
    /**
     * The number of tickets increments if the client decide to buy a ticket
     */
    public void comprarEntrada() {
    	   ++entrada;
    }
   /**
    * Getter that return the name of a client
    * @return nombre
    */
    public String getNombre() {
   
    	  return nombre;
    }
    
    
    /**
     * Getter that returns the first surname of a client
     * @return primerApellido
     */
    public String getPrimerApellido() {
   
    	  return primerApellido;
    }
    
    /**
     * Getter that returns the second surname of a client
     * @return segundoApellido
     */
    public String getSegundoApellido() {
   
    	  return segundoApellido;
    }
    
    
 /**
  * Getter that returns the age of the client
  * @return edad
  */
    public int getEdad() {
      	return edad;
    }
    
    /**
     * Check if the client has a ticket or not
     * @return true if the client has at least one ticket
     */
   public boolean clienteTieneEntrada() {
	   return (entrada > 0);
   }
    
   /**
    * Diminish the number of tickets of the client
    */
   public void gastarEntrada() {
	   if( clienteTieneEntrada()) {
		   --entrada;
	   }
   }
   
   /**
    * Method that returns true if the client has priority or not
    * @return boolean
    */
   public boolean prioritario() {
	     return (prioridad == Prioridad.EMBARAZADA || prioridad == Prioridad.TERCERA_EDAD|| prioridad == Prioridad.DISCAPACIDAD);
   }
    
   
  /**
   * Getter 
   * @return the gender of a client
   */
   public char getSexo() {
	   return sexo;
   }
   
   /**
    * Set the name of a client
    * @param nombre
    */
  public void setNombre(String nombre) {
	  this.nombre = nombre;
  }
  /**
   * Set the first surname
   * @param primerApellido
   */
  public void setPrimerApellido(String primerApellido) {
	  this.primerApellido = primerApellido;
  }
   
  /**
   * Set the second surname
   * @param segundoApellido
   */
  public void setSegundoApellido(String segundoApellido) {
	  this.segundoApellido = segundoApellido;
  }
   /**
    * Set the gender of the client
    * @param sexo
    */
   public void setSexo(String sexo) {
	   
	   if( sexo.equals("Hombre")) {
		   this.sexo = 'H';
	   }else {
		   this.sexo ='M';
	   }
   }
   
   /**
    * Set the age of the client
    * @param edad
    */
   public void setEdad( int edad) {
	   this.edad = edad;
   }
   
   
   /**
    * Set the level of priority of a client
    * @param prioridad
    */
   public void setPrioridad(String prioridad) {
	   if(prioridad.equals("Ninguna")) {
		   this.prioridad = Prioridad.NORMAL;
	   }else if( prioridad.equals("Tercera Edad")) {
		   this.prioridad = Prioridad.TERCERA_EDAD;
	   }else if( prioridad.equals("Embarazada")) {
		   this.prioridad = Prioridad.EMBARAZADA;
	   }else if( prioridad.equals("Discapacitado/a")){
		   this.prioridad = Prioridad.DISCAPACIDAD;
	   }
   }
   
}//End of class
