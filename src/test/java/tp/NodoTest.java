package tp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodoTest {

	protected Nodo nodo1;
	protected Nodo nodo2;
	protected Nodo nodo3;
	
	@Before
	public void setUp() throws Exception {
		nodo1 = new Nodo();
		nodo2 = new Nodo();
		nodo3 = new Nodo();
		
		nodo1.setNumero(1);
		nodo2.setNumero(2);
		nodo3.setNumero(3);
		
		nodo1.setPosicion(1);
		nodo2.setPosicion(2);
		nodo3.setPosicion(3);
		
		nodo1.setNodoSiguiente(nodo2);
		nodo2.setNodoAnterior(nodo1);
		nodo2.setNodoSiguiente(nodo3);
		nodo3.setNodoAnterior(nodo2);		
	}
	
	@Test
	public void testContiene(){
		assertTrue(nodo1.contiene(1));
		assertTrue(nodo1.contiene(2));
		assertTrue(nodo1.contiene(3));
		assertTrue(nodo2.contiene(2));
		assertTrue(nodo2.contiene(3));
		assertTrue(nodo3.contiene(3));
		
		assertFalse(nodo1.contiene(0));
		assertFalse(nodo2.contiene(0));
		assertFalse(nodo3.contiene(0));
	}
	
	@Test
	public void testGet(){
		assertTrue(nodo1.get(1) == 1);
		assertTrue(nodo2.get(2) == 2);
		assertTrue(nodo3.get(3) == 3);
		
		assertTrue(nodo1.get(2) == 2);
		assertTrue(nodo1.get(3) == 3);
	}
	
	@Test
	public void testSet(){
		nodo1.set(1, 2);
		nodo1.set(1, 3);
		
		assertTrue(nodo1.contiene(1));
		assertTrue(nodo2.contiene(1));
		assertTrue(nodo3.contiene(1));
	}
	
}
