package aec2.metro.implementacion;

import listas.ListaEnlazada;

public class TuplaCaminoValor {


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
		return "TuplaCaminoValor [camino=" + camino + ", valor=" + valor + "]";
	}

}
