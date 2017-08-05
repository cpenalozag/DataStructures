package interfaz;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import modelos.Estacion;
import mundo.Administrador;


public class Main 
{	
	/**
	 * Administrador del Sistema
	 */
	private Administrador admin;

	/**
	 * Lector de la consola
	 */
	private BufferedReader in;

	/**
	 * Inicializa la clase principal de la aplicación
	 */
	public Main() throws Exception {
		admin = new Administrador();
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("<< Bienvenido al sistema de metro de New York >>");
	}

	/**
	 * Menu principal de la aplicacion
	 * @throws Exception
	 */
	public void menuInicial() throws Exception
	{
		String mensaje = "Por favor seleccione  "
				+ "una opcion: \n - - - - - - - - - - - - - - - - - - - - -\n"
				+ "[1] Cargar informacion sistema \n"
				+ "[2] Buscar estación por nombre \n"
				+ "[3] Rutas que pasan por una estación \n"
				+ "[4] Distancia entre estaciones más cercanos \n"
				+ "[5] Distancia entre estaciones más lejanos \n"
				+ "[6] Salir \n";
		System.out.print(mensaje);
		String op1Str = in.readLine();
		
		System.out.println(">> "+ op1Str);
		int op1 = Integer.parseInt(op1Str);
		switch(op1)
		{
		case 1:
			try
			{
				admin.cargarInformacion();
			}
			catch(Exception e)
			{
				System.out.println(e instanceof IOException ? 
						"Ocurrio un error al leer los archivos. Por favor intenta de nuevo"
						:"No fue posible extraer la información. Verificar el formato de los archivos");
			}
			break;
		case 2:
			System.out.println(">Ingrese el identificador de la estacion");
			Estacion p = admin.buscarEstacion(in.readLine().trim()); 
			System.out.println(p!=null ? p: "Estacion no encontrada!");
			break;
		case 3: 
			System.out.println(">Ingrese el identificador de la estacion");
			String idEst = in.readLine();
			String[] rutas =  admin.darRutasEstacion(idEst.trim());
			System.out.println("Por la estacion "+ idEst+" pasan "+ rutas.length+" ruta(s): ");
			for (int i = 0; i < rutas.length; i++) {
				System.out.println((i+1)+". "+rutas[i]);

			}
			break;
		case 4: 
			System.out.println("Las estaciones más cercanas están a "+admin.distanciaMinimaEstaciones()+" metros de distancia");
			break;
		case 5: 
			System.out.println("Las estaciones más lejanas están a "+admin.distanciaMaximaEstaciones()+" metros de distancia");
			break;
		case 6: System.exit(1);
		}
		System.out.println("<<");
	}
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		for(;;)
		{
			main.menuInicial();
			System.out.println("Intro para volver al menú inicial");
			main.in.readLine();
		}
	}
}
