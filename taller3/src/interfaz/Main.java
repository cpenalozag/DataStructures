package interfaz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

import estructuras.Pila;
import parqueadero.Carro;
import parqueadero.Central;

public class Main 
{
	private BufferedReader br;
	private Central central;

	/**
	 * Clase principal de la aplicaci�nn, incializa el mundo.
	 * @throws Exception
	 */
	public Main() throws Exception
	{

		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("** PLATAFORMA CITY PARKING S.A.S **");
		System.out.println();
		central = new Central ();
		menuInicial();
	}
	
	/**
	 * Menú principal de la aplicación
	 * @throws Exception
	 */
	public void menuInicial() throws Exception
	{
		String mensaje = "Men� principal: \n"
			+ "1. Registrar Cliente \n"
			+ "2. Parquear Siguente Carro En Cola \n"
			+ "3. Atender Cliente Que Sale \n"
	        + "4. Ver Estado Parqueaderos \n"
	        + "5. Ver Carros En Cola \n"
			+ "6. Salir \n\n"
			+ "Opcion: ";

		boolean terminar = false;
		while ( !terminar )
		{
			System.out.print(mensaje);
			int op1 = Integer.parseInt(br.readLine());
			while (op1>6 || op1<1)
			{
				System.out.println("\nERROR: Ingrese una opci�n valida\n");
				System.out.print(mensaje);
				op1 = Integer.parseInt(br.readLine());
			}

			switch(op1)
			{
				case 1: registrarCliente(); break;
				case 2: parquearCarro(); break;
				case 3: salidaCliente(); break;
				case 4: verEstadoParquederos(); break;
				case 5: verCarrosEnCola(); break;
				case 6: terminar = true; System.out.println("\n Terminacion de Servicios. Hasta pronto."); break;
			}
		}

	}

  /**
   * M�todo para registrar a un nuevo cliente
   * @throws Exception
   */
	public void registrarCliente () throws Exception
  {
	  System.out.println("** REGISTRAR CLIENTE **");
	  System.out.println("Ingrese el nombre del conductor:");
	  String nombre = br.readLine();
	  System.out.println("Ingrese la matricula del carro:");
	  String matricula = br.readLine();
	  System.out.println("Ingrese el color del carro:");
	  String color = br.readLine();
	  central.registrarCliente(color, matricula, nombre);
  }
  /**
   * M�todo para parquear un carro que se encuentra en la cola
   * @throws Exception
   */
  public void parquearCarro() throws Exception
  {
	  System.out.println("** PARQUEAR CARRO EN COLA **");
	  try{
		  String txt = central.parquearCarroEnCola();
		  System.out.println(txt);
	  }
	  catch (Exception e){
		  System.out.println("No hay más carros en la cola.");
	  }
  }
 /**
  * M�todo para sacar un carro del parqueadero
  * @throws Exception
  */
  public void salidaCliente () throws Exception
  {
	  System.out.println("** REGISTRAR SALIDA CLIENTE **");
	  System.out.println("Ingrese la matricula del carro: ");
	  String matricula = br.readLine();
	  try{
		  Double tarifa = central.atenderClienteSale(matricula);
		  System.out.println("Debe pagar $" + tarifa + " pesos.");
	  }
	  catch (Exception e){
		  System.out.println("No se encontró el carro con la placa indicada.");
	  }
	  
  }
  /**
   * M�todo que permite visualizar graficaente el estado de los parqueaderos
   * @throws Exception
   */
  public void verEstadoParquederos() throws Exception
  {
	  System.out.println("** ESTADO PARQUEADEROS **");
	  central.imprimirPilas();
  }
  
  /**
   * M�todo que permite visualizar graficaente el estado de la cola de carros pendientes por parquear
   * @throws Exception
   */
  public void verCarrosEnCola () throws Exception
  {
	  System.out.println("** ESTADO COLA **");
	  central.imprimirCola();
  }
  
  /**
	 * Main...
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
