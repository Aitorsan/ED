package asf.data_structures.lists;
import java.util.Iterator;

import asf.data_structures.nodes.Node;
import asf.exceptions.OutOfBoundsException;
public class LinkedList <T> {

	private Node<T> m_head;
	private Node<T> m_iterator;
	private Node<T> m_tail;
	private int m_size;
	
	public LinkedList() {
		m_size = 0;
		m_head = null;
		m_iterator = null;
		m_tail = null;
	}
	
	/**
	 * Checks whether the list is empty or not
	 * @return boolean value, true if the list is empty
	 */
	public boolean  isEmpty() {
		
		return (m_head == null);
	}
	
	
	/**
	 * Insert an element at the end of the list
	 * @param elemento
	 */
	public void insert(T elemento) {
		Node<T>nuevo = new Node<T>(elemento);
		
		if( m_head == null) {
			 m_head = nuevo;
             m_iterator = nuevo;
             m_tail = nuevo;
		}else {
			
			 m_tail.set_next(nuevo);
			 m_tail = nuevo;
			
			
		}
		++m_size;
      
	}
	
	/**
	 * Insert an element at a given position. If the list is empty will be inserted at the first position
	 * @param element
	 * @param index
	 * @throws OutOfBoundsException
	 */
	public void insert_at_index(T element, int index)throws OutOfBoundsException {
		
		if( index  < 0 || index >= m_size) throw new OutOfBoundsException("index out of bounds cannot be acces");
			
		if( isEmpty()) {
		
			insert(element);
			System.out.println("The list is empty the element was added at index: 0");		
		}else {
			m_iterator = m_head;
			for( int i = 0; i < (index % m_size);++i) {
				m_iterator = m_iterator.get_next();
			}
			
			m_iterator.insert_element(element);
		}
		
		
		
	}
	
	/**
	 * Get the size of the list
	 * @return size number of elements into the list
	 */
	public int get_size() {
		return m_size;
	}
	
	
	/**
	 * search for a given element within the list
	 * @param nodo
	 * @param element
	 * @return true or false
	 */
	public boolean search(T element) {
		if( isEmpty()) {
			return false;
		}
		else {
			
			boolean value =  search( m_head,element);
	
			return value;
		}	
		
	}
	
	/**
	 * Helper function to search for a given element within the list and makes actual point to the element
	 * @param nodo
	 * @param elemento
	 * @return boolean value
	 */
	private boolean search(Node<T> nodo, T elemento) {
		
		if( nodo != null) {
			
			if( nodo.getElement().equals(elemento)) {
				
				m_iterator = nodo;
				return true;
			}else {
				return (search(nodo.get_next(),elemento));
			}
					
		}else {
			return false;
			
		}
		

	}
	
	/**
	 * Get an element in a given index
	 * @param index
	 * @return
	 * @throws OutOfBoundsException
	 */
	public T getElementAtIndex( int index) throws OutOfBoundsException {
		if(isEmpty())throw new OutOfBoundsException("the list is empty");
		if( index< 0 ) throw new OutOfBoundsException("the index can't be negative");
		if( index > m_size ) throw new OutOfBoundsException("the index out of bounds");
		if( index == 0) return m_head.getElement();
		if( index == (m_size - 1)) return m_tail.getElement();
		int counter = 0;
		m_iterator = m_head;
		while(counter != index ) {
			
			m_iterator = m_iterator.get_next();
			++counter;
			
		}
		
		
		return m_iterator.getElement();
				
	}
	
    /**
     * This method returns the last element at the end of the list if
     * <br> the list is not empty. If it's empty will throw exception.
     * @throws Exception
     * @return the last element in the list if its not empty
     */
	public T get_last()throws Exception {
		if( isEmpty()) throw new Exception("The list is empty");
		
		return m_tail.getElement();
	}
 
	/**
	 * Returns the first element of the list. If its empty will throw an exception
	 * @return First element in the list
	 * @throws OutOfBoundsException
	 */
    public T get_first() throws OutOfBoundsException{
    	if(isEmpty()) throw new OutOfBoundsException("the list is empty");
    	return m_head.getElement();
    }
	
    /**
     * Delete an element if the list is not empty, and its found
     * @param element
     */
    public void delete_element(T element) {
    	
    	if(isEmpty()) {
    		
    		System.out.println("list is empty");
    	
        }else {
        	
        	Node<T> trail = m_head;
        	m_iterator = m_head;
        	while(m_iterator != null && !m_iterator.getElement().equals(element) ) {
        		trail = m_iterator;
        		m_iterator = m_iterator.get_next();
    
        	}
        	if( m_iterator != null) {
        		
        		//1. The element we want to delete is at the beginning of the list
        		if( m_iterator == m_head) {
        			m_head =m_head.get_next();
        			
        			//2. The element is somewhere in the list
        		}else {
        			
        			trail.set_next(m_iterator.get_next());
        			m_iterator.set_next(null);
        			
        			//3. The element is the last in the list we have to take care of the tail node
        			if( m_iterator == m_tail) {
        				m_tail = trail;
        			}
        			
        		}
        		trail = null;
        		m_iterator = m_head;
        	}else {
        		
        		
        		System.out.println("the element is not in the list");
        	}
        	
        	
    	}
    		
      	
    	
    }
	/**
	 * Print all the elements contained in the list	
	 */
	public void print_list() {
			Node<T> it = m_head;
			System.out.println("List:");
			
			while(it != null) {
				System.out.print("["+it.getElement()+"]->");
				it = it.get_next();
		
			}
			System.out.println();
	}
	
}
