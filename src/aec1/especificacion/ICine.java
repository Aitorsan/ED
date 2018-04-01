package aec1.especificacion;

import aec1.implementacion.*;
public interface ICine {

	
	
    public void registarCliente(Cliente cliente);
    
    public boolean borrarCliente(Cliente cliente);
   
    /**Mover el cliente a la zona de proyeccion, si tiene entrada**/
    public void moverClienteSalaPoyeccion(Cliente cliente);
    
    /**Recoge la informacion de la Zona de enrada**/
    public String getListInfoEntrada();
     
    /**Recoge la informacion de la Zona de Proyeccion*/
    public String getListInfoProyeccion();
}
