package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import listas.ListaDobleEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DobleListaEnlazada_Test {

	ListaDobleEnlazada l;
	
	@BeforeEach
	void setUp() {
		l = new ListaDobleEnlazada();
		
	}
	@Test
	void test_Insertion() {
		for( int i = 0;i < 5; ++i) {
		  l.insertar("Aitor"+i);
		  assertNotNull(l.recuperar());
		  assertEquals("Aitor"+i,l.recuperar());
	    }
		assertEquals(5,l.getSize());
	
	}
	
	@Test
	void elimination_test() {
		for( int i = 0;i < 5; ++i) {
			  l.insertar("Aitor"+i);
			  assertNotNull(l.recuperar());
			  assertEquals("Aitor"+i,l.recuperar());
		    }
	
		for( int i = 0;i < 5; ++i) {
			  l.eliminar("Aitor"+i);
			
		    }
	
		assertEquals(0,l.getSize());
	    assertNull(l.recuperar());
	}

}
