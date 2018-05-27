package aec2.metro.implementacion;

import java.io.Serializable;

import listas.ListaEnlazada;

public class TuplaCaminoValor implements Serializable {


	/***/
	private static final long serialVersionUID = -1952637944523324421L;
	
	private ListaEnlazada camino;
	private int valor;

	public TuplaCaminoValor(ListaEnlazada camino, int valor) {
		this.camino = camino;
		this.valor = valor;
	}

	public ListaEnlazada getCamino() {
		return camino;
	}

	public void setCamino(ListaEnlazada camino) {
		this.camino = camino;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		String caminos= "";
		camino.primero();
		if( !camino.estaDentro()) {
			caminos +="no existe camino";
		}else {
			
			while(camino.estaDentro()) {
				caminos+= camino.recuperar();
				camino.avanzar();
			}
		}
		camino.primero();
		
		return "TuplaCaminoValor [camino=" + caminos + ", valor=" + valor + "]";
	}

}
