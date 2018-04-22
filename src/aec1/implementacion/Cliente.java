package aec1.implementacion;



public class Cliente {

	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private int edad;
	private Prioridad prioridad;
    private char sexo;
    private int entrada;
 
    /**Constructor sin parametros**/
    public Cliente() {
    	  
	     this.nombre = null;
	     this.primerApellido = null;
	     this.segundoApellido = null;
	     this.edad = 0;
	     this.prioridad = null;
	     this.sexo = 0;
	     this.entrada = 0;
    
    }
    /**Constructor con parametros**/
    public Cliente( String nombre,String primerApellido,String segundoApellido, int edad, Prioridad prioridad, char sexo) {
    	   
    	     this.nombre = nombre;
    	     this.primerApellido= primerApellido;
    	     this.segundoApellido = segundoApellido;
    	     this.edad = edad;
    	     this.prioridad = prioridad;
    	     this.sexo = sexo;
    	     this.entrada = 0;
    }
    
    /**El cliente aumenta la variable entrada si decide comprar una**/
    public void comprarEntrada() {
    	   ++entrada;
    }
    /**Devuelve los datos sobre nombre del cliente**/
    public String getNombre() {
   
    	  return nombre;
    }
    
    
    /**Devuelve los datos sobre el primer apellido del cliente**/
    public String getPrimerApellido() {
   
    	  return primerApellido;
    }
    
    /**Devuelve los datos sobre el segundo apellido del cliente**/
    public String getSegundoApellido() {
   
    	  return segundoApellido;
    }
    
    
    /**Devuelve la edad del cliente*/
    public int getEdad() {
      	return edad;
    }
    
    /**Devuelve verdadero si el cliente tiene una entrada, falso si no la tiene**/
   public boolean clienteTieneEntrada() {
	   return (entrada > 0);
   }
    
   /**Disminuye el numero de entradas que tiene el cliente al ser usada en el acceso a la sala de proyeccion*/
   public void gastarEntrada() {
	   if( clienteTieneEntrada()) {
		   --entrada;
	   }
   }
   
   /**Devuelve verdadero si el cliente tiene alguna situacion especial que le da prioridad*/
   public boolean prioritario() {
	     return (prioridad == Prioridad.EMBARAZADA || prioridad == Prioridad.TERCERA_EDAD|| prioridad == Prioridad.DISCAPACIDAD);
   }
    
   
   /**Devuelve el sexo del cliente, para saber a que aseo debe acudir**/
   public char getSexo() {
	   return sexo;
   }
   
   /**Inicia el campo de nombre del cliente*/
  public void setNombre(String nombre) {
	  this.nombre = nombre;
  }
  /**Inicia el campo de apellidos del cliente*/
  public void setPrimerApellido(String primerApellido) {
	  this.primerApellido = primerApellido;
  }
   
  /**Inicia el campo de apellidos del cliente*/
  public void setSegundoApellido(String segundoApellido) {
	  this.segundoApellido = segundoApellido;
  }
   /**Set sexo**/
   public void setSexo(String sexo) {
	   
	   if( sexo.equals("Hombre")) {
		   this.sexo = 'H';
	   }else {
		   this.sexo ='M';
	   }
   }
   
   /**Setter para la edad del cliente**/
   public void setEdad( int edad) {
	   this.edad = edad;
   }
   
   
   /**Setter para la prioridad del cliente **/
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
   
}
