package aec1.especificacion;

import aec1.implementacion.*;

/**
 * Interface for the cinema class those are the basic methods that
 * a class should have when implements this interface
 * @author Aitor sanmartin ferreira
 *
 */
public interface ICine {
	
	/**
	 * Register a customer
	 * @param cliente
	 */
   public void registarCliente(Cliente cliente);
   /**
    * Search a client within the cinema
    * @param cliente
    * @return
    */
   public boolean buscarClienteEnCine(Cliente cliente);
   /**
    * overloaded method for searching clients
    * @param nameSurname
    * @return
    */
   public Cliente buscarClienteEnCine(String nameSurname);
   /**
    * Move clients around
    * @param c
    * @param where
    * @throws Exception
    */
   public void move( Cliente c , String where)throws Exception;
   /**
    * Method to find a client inside the projection room
    * @param nameAndSurname
    * @return
    */
   public boolean buscarCLienteEnSalaCine(String nameAndSurname);
   /**
    * Delete a client from the Cinema
    * @param nameSurname
    * @return
    */
   public boolean deleteClient(String nameSurname);

}
