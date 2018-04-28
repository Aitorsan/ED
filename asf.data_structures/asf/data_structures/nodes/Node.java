package asf.data_structures.nodes;

public class Node<T>  {

	private T element;
	private Node<T> next;
	
	
	public Node( T elemento) {
		this.element = elemento;
	}
	
	
	public T getElement() {
		return element;
	}
	
	public void insert_element(T elemento) {
		this.element = elemento;
	}
	
	public Node<T> get_next(){
		return next;
	}
	
	public void set_next(Node<T> next) {
		
		this.next = next;
		
	}
}
