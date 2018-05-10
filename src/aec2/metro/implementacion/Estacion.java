package aec2.metro.implementacion;

public class Estacion {


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
