package test;




import taller.estructuras.TablaHash;
import taller.interfaz.Ciudadano;

public class MainTest {

	private TablaHash<String, Ciudadano> tabla = new TablaHash<String, Ciudadano>();
	public MainTest()
	{
	}
	
	public void datos500000()
	{
		long tiempoDeCarga = System.nanoTime();
		int i = cargarDatos500000();
		tiempoDeCarga = System.nanoTime() - tiempoDeCarga;
		System.out.println(i+" datos cargados en " + tiempoDeCarga + " nanosegundos");
		System.out.println();
		System.out.println("Buscar habitante con nombre: Nomb1111.");
		long tiempoDeCarga1 = System.nanoTime();
		Ciudadano c = buscarC500000();
		tiempoDeCarga = System.nanoTime() - tiempoDeCarga1;
		System.out.printf("Habitante con nombre: %s, Apellido: %s, Numero: %s. \n", c.darNombre(), c.darApellido(), c.darNumero() );
		System.out.printf("Información encontrada en %d nanosegundos. \n", tiempoDeCarga);
		System.out.println();
	}
	
	public int cargarDatos500000()
	{
		int i =0;
		for(i = 0; i<500000; i++)
		{
			Ciudadano c = new Ciudadano("Nombre" +i, "Apellido" +i, i+"11,11"+i, "1234567"+i);
			tabla.put(c.darNombre(), c);
		}
		return i;
	}
	
	public Ciudadano buscarC500000()
	{
		Ciudadano c = tabla.get("Nomb1111");
		return c;
	}
	
}
