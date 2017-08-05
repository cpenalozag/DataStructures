package taller.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import taller.interfaz.ArbolCLI;

public class ArbolCLITest {


	ArbolCLI l = new ArbolCLI();
	public void SetUp()
	{
		try {
			l.cargarArchivo("./data/caso1.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SetUp2()
	{
		try {
			l.cargarArchivo("./data/caso2.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SetUp3()
	{
		try {
			l.cargarArchivo("./data/ejemploNombre.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCargarArchivo() {
		SetUp();
		assertEquals("A", l.darPreorden()[0]);
		int size = l.darInorden().length;
		assertEquals("C".toString(), l.darInorden()[size-2].toString());
		SetUp2();
		assertEquals("J", l.darPreorden()[0]);
		size = l.darInorden().length;
		assertEquals("O".toString(), l.darInorden()[size-2].toString());
		SetUp3();
		assertEquals("E", l.darPreorden()[0]);
		size = l.darInorden().length;
		assertEquals("Z".toString(), l.darInorden()[size-2].toString());
		SetUp2();
		
	}

	@Test
	public void testReconstruir() {
		SetUp();
		l.reconstruir();
		System.out.println("-------------Caso 1-------------");
		assertEquals("A", ((ArbolCLI.Node)l.darRaiz()).darDato());
		ArbolCLI.imprimir(l.darRaiz());
		l = new ArbolCLI();
		SetUp2();
		l.reconstruir();
		System.out.println("-------------Caso 2-------------");
		assertEquals("J", ((ArbolCLI.Node)l.darRaiz()).darDato());
		ArbolCLI.imprimir(l.darRaiz());
		l = new ArbolCLI();
		SetUp3();
		l.reconstruir();
		System.out.println("-----------Caso Nombre----------");
		assertEquals("E", ((ArbolCLI.Node)l.darRaiz()).darDato());
		ArbolCLI.imprimir(l.darRaiz());
		l=new ArbolCLI();
		try {
			l.cargarArchivo("./data/subArbolNombre.properties");
			l.reconstruir();
			ArbolCLI.imprimir(l.darRaiz());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
