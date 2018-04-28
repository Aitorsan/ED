package tests;




import  static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import asf.data_structures.lists.LinkedList;


class List_test {
	
	LinkedList<String> l = new LinkedList<String>();

	@BeforeEach
    public  void setUp() {
		l.insert("Aitor");
		l.insert("Carolin");
		l.insert("rono");
		l.insert("John Deere");
		
	}
	
	@Test
	public void assert_size() {
	
		assertEquals(4,l.get_size());
	}
	
	@Test
	public void assert_last_Element() {
		try {
		
			assertNotNull(l.get_last());
			assertEquals("John Deere",l.get_last());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	@Test
	public void assert_first_Element() {
	
		
		assertEquals("Aitor",l.get_first());
		
	}
	
	@Test
	public void assert_indices() {
		
	
		assertEquals("Aitor",l.getElementAtIndex(0));
		assertEquals("Carolin",l.getElementAtIndex(1));
	    assertEquals("rono",l.getElementAtIndex(2));
		assertEquals("John Deere",l.getElementAtIndex(3));
	
	}
	
	@Test
	public void assert_set_Element_at_index() {
	
		l.insert_at_index("Substitute", 3);
		assertEquals("Substitute",l.getElementAtIndex(3));
		
	}
	@Test
	public void search_true_assertion() {
	
		assertTrue(l.search("Aitor"),"this has to be true");
		
	}
	@Test
	public void search_false_assertion() {
		l.insert_at_index("Substitute", 3);
		assertFalse(l.search("John Deere"));
		
	}
	@Test
	public void isEmpty_assertion() {
		
		assertFalse(l.isEmpty());
	}
	
	
	@Test
	public void delete_assertion() {
		
		l.delete_element("Aitor");
		
		l.print_list();
		assertNotEquals("Aitor",l.getElementAtIndex(0));
		
	}
	
}
