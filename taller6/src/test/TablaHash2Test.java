package test;

import junit.framework.TestCase;
import taller.estructuras.TablaHash;
import taller.interfaz.Ciudadano;

public class TablaHash2Test <T, V> extends TestCase {

	private TablaHash<String, Ciudadano> tabla =  new TablaHash<String, Ciudadano>();

	public void setUp() throws Exception {
		for(int i = 0; i<10; i++)
		{
			Ciudadano c = new Ciudadano("Nombre" + i, "Apellido" + i, i+"1,1"+i,"1234567"+i);
			tabla.put(c.darNombre(),c);
			
		}
	}

	public final void testSize() {
		assertEquals(10, tabla.size());
		tabla.delete("Nombre2");
		assertEquals(9, tabla.size());
	}

	public final void testIsEmpty() {
		for(int i = 0; i<10; i++)
		{
			tabla.delete("Nombre"+i);
		}
		assertEquals(0, tabla.size());
	}

	public final void testContains() {
		
		
		assertTrue(tabla.contains("Nombre5"));
		assertFalse(tabla.contains("Nombre11"));
		
	}

	public final void testPut() {
		Ciudadano c = new Ciudadano("Nombre", "Apellido", "111,111","1132311");
		tabla.put(c.darNombre(), c);
		assertTrue(tabla.contains("Nombre"));
	}

	public final void testGet() {
		
		assertEquals("Nombre7", tabla.get("Nombre7").darNombre());
	}

	public final void testDelete() {
		Ciudadano c = tabla.delete("Nombre5");
		assertEquals("Nombre5", c.darNombre());
	}

}
