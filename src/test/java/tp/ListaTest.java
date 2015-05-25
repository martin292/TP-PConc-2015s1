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
		assertTrue(lista.get(0) == 1);
	}
	
	@Test
	public void testSet(){
		lista.add(1);
		lista.set(0, 2);
		assertTrue(lista.contains(2));
	}
	
	@Test
	public void testMenoresQue(){
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		
		Lista nueva = lista.menoresQue(4);
		
		assertTrue(nueva.contains(1));
		assertTrue(nueva.contains(2));
		assertTrue(nueva.contains(3));
		
		assertFalse(nueva.contains(4));
		assertFalse(nueva.contains(5));
	}
	
	@Test
	public void testMayoresQue(){
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		
		Lista nueva = lista.mayoresQue(2);
		
		assertTrue(nueva.contains(3));
		assertTrue(nueva.contains(4));
		assertTrue(nueva.contains(5));
		
		assertFalse(nueva.contains(1));
		assertFalse(nueva.contains(2));
	}
	
}
