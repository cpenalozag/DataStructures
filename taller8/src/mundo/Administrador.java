package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

import estructuras.Arco;
import estructuras.GrafoNoDirigido;
import modelos.Estacion;

/**
 * Clase que representa el administrador del sistema de metro de New York
 *
 */
public class Administrador {

	/**
	 * Ruta del archivo de estaciones
	 */
	public static final String RUTA_PARADEROS ="./data/stations.txt";

	/**
	 * Ruta del archivo de rutas
	 */
	public static final String RUTA_RUTAS ="./data/routes.txt";


	/**
	 * Grafo que modela el sistema de metro de New York
	 */
	//TODO Declare el atributo grafo No dirigido que va a modelar el sistema. 
	// Los identificadores de las estaciones son String
	private GrafoNoDirigido<String, Object> grafo;


	private int cantRutas;
	/**
	 * Construye un nuevo administrador del Sistema
	 */
	public Administrador() 
	{
		//TODO inicialice el grafo como un GrafoNoDirigido
		grafo  = new GrafoNoDirigido<>();
	}

	/**
	 * Devuelve todas las rutas que pasan por la estacion con nombre dado
	 * @param identificador 
	 * @return Arreglo con los identificadores de las rutas que pasan por la estacion requerida
	 */
	public String[] darRutasEstacion(String identificador)
	{
		//TODO Implementar
		Arco<String, Object>[] origen = grafo.darArcosOrigen(identificador);
		Arco<String, Object>[] destino = grafo.darArcosDestino(identificador);
		String [] resp = null;
		if(origen!=null&&destino!=null)
			resp = new String[origen.length+destino.length];
		else if(origen!=null&&destino==null)
			resp = new String[origen.length];
		else if(origen==null&&destino!=null)
			resp = new String[destino.length];
		int i = 0;
		if(origen!=null)
			for(Arco l: origen)
			{
				if(l!=null)
					resp[i++] = l.darNodoFin().darId().toString();
			}
		if(destino!=null)
			for(Arco l : destino)
			{
				if(l!=null)
					resp[i++] = l.darNodoFin().darId().toString();
			}
		return resp;
	}

	/**
	 * Devuelve la distancia que hay entre las estaciones mas cercanas del sistema.
	 * @return distancia minima entre 2 estaciones
	 */
	public double distanciaMinimaEstaciones()
	{
		//TODO Implementar
		Arco<String, Object>[] arcos = grafo.darArcos();
		double min = Double.MAX_VALUE;
		for(Arco r: arcos)
		{
			if(r.darCosto()<min)
				min=r.darCosto();
		}
		return min;
	}

	/**
	 * Devuelve la distancia que hay entre las estaciones más lejanas del sistema.
	 * @return distancia maxima entre 2 paraderos
	 */
	public double distanciaMaximaEstaciones()
	{
		//TODO Implementar
		Arco<String, Object>[] arcos = grafo.darArcos();
		double max = 0;
		for(Arco r: arcos)
		{
			if(r.darCosto()>max)
				max=r.darCosto();
		}
		return max;
	}


	/**
	 * Metodo encargado de extraer la informacion de los archivos y llenar el grafo 
	 * @throws Exception si ocurren problemas con la lectura de los archivos o si los archivos no cumplen con el formato especificado
	 */
	public void cargarInformacion() throws Exception
	{
		//TODO Implementar
		File archivo = new File(RUTA_PARADEROS);
		if(archivo.exists())
		{
			Scanner canal = new Scanner (new FileInputStream( archivo ));
			int cant = canal.nextInt();
			int i= 0;

			while(i<cant)
			{
				String[] partes = canal.next().split(";");
				String nombre = partes[0];

				Double latitud = Double.parseDouble(partes[1]);
				Double longitud = Double.parseDouble(partes[2]);
				Estacion<String> estacion = new Estacion<String>(nombre, latitud, longitud);
				grafo.agregarNodo(estacion);
				i++;
			}
			canal.close();
		}

		System.out.println("Se han cargado correctamente "+grafo.darNodos().length+" estaciones.");

		//TODO Implementar
		File archivo2 = new File(RUTA_RUTAS);

		try{
			if(archivo2.exists())
			{
				BufferedReader canal = new BufferedReader(new FileReader( archivo2 ));
				int tam = Integer.parseInt(canal.readLine());
				int  i = 0;
				while(i<tam)
				{
					String nombrerRta ="";
					String linea = canal.readLine();

					if(linea.startsWith("--")){
						nombrerRta = canal.readLine();
						int rutas = Integer.parseInt(canal.readLine());
						String inicio = "";
						for(int p = 0; p<rutas;p++)
						{

							String[] partes = canal.readLine().split(" ");
							String nombre = partes[0];
							int distancia = Integer.parseInt(partes[1]);
							if(distancia==0)
							{
								inicio = nombre;
								continue;
							}
							grafo.agregarArco(inicio, nombre, distancia, nombrerRta);
							cantRutas++;
							grafo.agregarArco(nombre, inicio, distancia, nombrerRta);
							cantRutas++;
						}
					}

					i++;
				}
				canal.close();
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		//TODO Implementar
		grafo.cambiarTamhRutas(cantRutas);

		System.out.println("Se han cargado correctamente "+ grafo.darArcos().length/2+" arcos");
	}

	/**
	 * Busca la estacion con identificador dado<br>
	 * @param identificador de la estacion buscada
	 * @return estacion cuyo identificador coincide con el parametro, null de lo contrario
	 */
	public Estacion<String> buscarEstacion(String identificador)
	{
		//TODO Implementar
		Estacion <String> nodo = (Estacion<String>) grafo.buscarNodo(identificador);
		return nodo;
	}
}
