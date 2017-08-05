package taller.interfaz;

import java.util.ArrayList;
import java.util.Scanner;

import taller.mundo.Jugador;
import taller.mundo.LineaCuatro;

public class Interfaz 
{
	/**
	 * Juego actual
	 */
	private LineaCuatro juego;

	/**
	 * Escáner de texto ingresado por el usuario en la consola
	 */
	private Scanner sc;

	/**
	 * Crea una nueva instancia de la clase de interacción del usuario con la consola
	 */
	public Interfaz()
	{
		sc= new Scanner (System.in);
		imprimirMenu();
		while (true)
		{
			try
			{
				int opt=Integer.parseInt(sc.next());
				if(opt==1)
				{
					empezarJuego();
				}
				else if(opt==2)
				{
					empezarJuegoMaquina();
				}
				else if(opt==3)
				{
					System.out.println("¡Vuelva pronto!");
					break;
				}
				else
				{
					System.out.println("Comando inválido");
					continue;
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Comando inválido");
				continue;
			}


		}
	}
	/**
	 * Imprime el menú principal
	 */
	public void imprimirMenu()
	{
		System.out.println("------------------------LÍNEA CUATRO------------------------");
		System.out.println("-Menú principal-");
		System.out.println("Ingrese un comando:");
		System.out.println("1. Empezar juego con amigos");
		System.out.println("2. Empezar juego vs máquina");
		System.out.println("3. Salir");		
	}

	/**
	 * Crea un nuevo juego
	 */
	public void empezarJuego()
	{
		try{
			ArrayList<Jugador> jugadores = new ArrayList<>();
			System.out.println("Ingrese el número de jugadores: ");  
			int cant = sc.nextInt();
			for (int i=0; i<cant; i++){
				System.out.format("Jugador %d ingrese su nombre: ", i+1);
				String nombre = sc.next();
				System.out.format("Jugador %d ingrese su caracter representativo: ", i+1);
				String car = sc.next();
				Jugador j = new Jugador(nombre, car);
				jugadores.add(j);

			}
			System.out.println("Ingrese el número de filas del tablero: ");
			int filas = sc.nextInt();
			System.out.println("Ingrese el número de columnas del tablero: ");
			int columnas = sc.nextInt();
			System.out.println("Ingrese el número de fichas necesarias para ganar: ");
			int fichas = sc.nextInt();
			juego = new LineaCuatro(jugadores, filas, columnas, fichas);
			juego();
		}
		catch(Exception e){
			System.out.println("");
		}
	}

	public void finDelJuego(){
		System.out.format("Felicidades %s, ha ganado el juego!", juego.darNombreGanador());
		System.out.println("Desea volver a jugar?");
		System.out.println("1. Si!");
		System.out.println("2. No :(");
		int res = sc.nextInt();
		if (res==1){
			imprimirMenu();
		}
		else{
			System.out.println("¡Vuelva pronto!");
		}
	}

	/**
	 * Modera el juego entre jugadores
	 */
	public void juego()
	{
		boolean test = true;
		System.out.format("%s en qué fila desea jugar? ", juego.darAtacante());
		int filaJuego = sc.nextInt();
		System.out.format("%s en qué columna desea jugar? ", juego.darAtacante());
		int columnaJuego = sc.nextInt();
		test = juego.registrarJugada(columnaJuego, filaJuego);
		if (test==false&&juego.fin()){
			finDelJuego();
		}
		imprimirTablero();
		juego();
	}
	/**
	 * Empieza el juego contra la máquina
	 */
	public void empezarJuegoMaquina()
	{
		System.out.println("Ingrese el número de filas del tablero: ");
		int filas = sc.nextInt();
		System.out.println("Ingrese el número de columnas del tablero: ");
		int columnas = sc.nextInt();
		System.out.println("Ingrese el número de fichas necesarias para ganar: ");
		int fichas = sc.nextInt();
		System.out.print("Ingrese su nombre: ");
		String nombre = sc.next();
		System.out.format("Ingrese su caracter representativo: ");
		String car = sc.next();
		Jugador j = new Jugador(nombre, car);
		Jugador m = new Jugador("Maquina", "M");
		ArrayList<Jugador> jugadores = new ArrayList<>();
		jugadores.add(j);
		jugadores.add(m);
		juego = new LineaCuatro(jugadores, filas, columnas, fichas);
		imprimirTablero();
		juegoMaquina();
	}
	/**
	 * Modera el juego contra la máquina
	 */
	public void juegoMaquina()
	{
		boolean test = true;
		System.out.format("%s en qué fila desea jugar?", juego.darAtacante());
		int filaJuego = sc.nextInt();
		System.out.format("%s en qué columna desea jugar?", juego.darAtacante());
		int columnaJuego = sc.nextInt();
		test = juego.registrarJugada(columnaJuego, filaJuego);
		if (test==false&&juego.fin()){
			finDelJuego();
		}
		imprimirTablero();
		juego.registrarJugadaAleatoria();
		imprimirTablero();
		juegoMaquina();
	}

	/**
	 * Imprime el estado actual del juego
	 */
	public void imprimirTablero()
	{
		String[][] tab = juego.darTablero();
		for(int i=0;i<juego.darTablero().length;i++){
			System.out.println();
			System.out.print(i+1);
			for (int j=0;j<juego.darTablero()[0].length;j++){
				System.out.print(tab[i][j].toString() + " ");
			}
		}
		System.out.println();
	}
}
