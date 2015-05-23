package tp;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ListaTest {

	protected Lista lista;
	
	@Before
	public void setUp() throws Exception {
		this.lista = new Lista();
	}
	
	@Test
	public void testSize(){
		assertTrue(lista.size() == 0);
	}
	
	@Test
	public void testIsEmpty(){
		assertTrue(lista.isEmpty());
	}
	
	@Test
	public void testContains(){
		assertFalse(lista.contains(1));
	}
	
	@Test
	public void testAdd(){
		lista.add(1);
		assertTrue(lista.contains(1));
		//
		lista.add(2);
		assertTrue(lista.contains(1));
		assertTrue(lista.contains(2));
	}
	
	@Test
	public void testGet(){
		lista.add(1);
		assertTrue(lista.get(1) == 1);
	}
	
	@Test
	public void testSet(){
		lista.add(1);
		assertTrue(lista.set(2, 1).contains(2));
	}
	
}
