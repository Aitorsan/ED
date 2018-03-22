package listas_exp;
import nodos_exp.Nodo_exp;
public class ListaSinCabecera<T> {

	private Nodo_exp<T> cabecera;
	private Nodo_exp<T> actual;
	
	public ListaSinCabecera() {
		
		cabecera = null;
		actual = null;
	}
	
	
	public boolean  esVacia() {
		
		return (cabecera == null);
	}
	
	
	
	public void insertar(T elemento) {
		Nodo_exp<T>nuevo = new Nodo_exp<T>(elemento);
		
		if( cabecera == null) {
			cabecera = nuevo;
             actual = nuevo;
		}else {
			
			 actual.setEnlace(nuevo);
			 actual = nuevo;
			
			
		}
      
	}
	
	public Nodo_exp<T> buscar(T element) {
		if( esVacia()) {
			return null;
		}
		else {
			
		}	return buscar( cabecera,element);
	
		
	}
	
	private Nodo_exp<T> buscar(Nodo_exp<T> nodo, T elemento) {
		
		if( nodo != null) {
			
			if( nodo.getElemento().equals(elemento)) {
				return nodo;
			}else {
				return (buscar(nodo.getEnlace(),elemento));
			}
					
		}
		return nodo;
		

	}
		
	public void imprimir() {
			Nodo_exp<T> nodoIterador = cabecera;
			System.out.println("Lista:");
			
			while(nodoIterador != null) {
				System.out.print("["+nodoIterador.getElemento()+"]->");
				nodoIterador = nodoIterador.getEnlace();
		
			}
			System.out.println();
		
	}
	
}
