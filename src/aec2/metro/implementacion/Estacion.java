package aec2.metro.implementacion;

import java.io.Serializable;

public class Estacion implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5023547579023716448L;
	
	
	private String nombre;

	public Estacion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	
	
	

}
