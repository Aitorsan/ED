package nodos_exp;

public class Nodo_exp<T>  {

	private T elemento;
	private Nodo_exp<T> enlace;
	
	
	public Nodo_exp( T elemento) {
		this.elemento = elemento;
	}
	
	
	public T getElemento() {
		return elemento;
	}
	
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	public Nodo_exp<T> getEnlace(){
		return enlace;
	}
	
	public void setEnlace(Nodo_exp<T> ultimo) {
		
		this.enlace = ultimo;
		
	}
}
