package listas;

import java.io.Serializable;

public interface Lista extends Serializable {
	
	/**Inserta el elemento pasado por parametro despues de la posicion actual**/
	public void insertar(Object elemento);
	
	/**Elimina el elemento pasado por parametro si existe**/
	public void eliminar(Object elemento);
	
	/**Determina si existe el elemento pasado por parametro en la lista y colaca la posicion actual en la situacion
	 * de dicho elemento en caso de existir.
	 * @param elemento
	 * @return true si existe el elemento. False de lo contrario.
	 */
	public boolean buscar(Object elemento);
	
	/**Coloca la posicion actual antes del primer elemento de la lista**/
    public void cero();
	
	/**Situa la posicion actual en el primer elemento de la lista*/
    public void primero();	
    
	/**Avanza la posicion actual**/
	public void avanzar();
	/**
	 * Determina si la posicion actual es una posicion valida.
	 * De esta manera, se puede comprobar si se ha llegado al
	 * final de la lista.
	 * @return true o false 
	 */
	public boolean estaDentro();
	/**
	 * Devuelve el elemento de la posicion actual
	 * @return Object elemento de la posicion actual
	 */
	public Object recuperar();

}
