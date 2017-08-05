package taller.mundo;

import java.util.ArrayList;
import java.util.Random;

public class LineaCuatro 
{
	/**
	 * Matriz que representa el estado actual del juego.
	 */
	private String[][] tablero;

	/**
	 * Lista que contiene a los jugadores
	 */
	private ArrayList<Jugador> jugadores;

	/**
	 * Nombre del jugador en turno
	 */
	private String atacante;

	/**
	 * Simbolo del jugador en turno
	 */
	private String simbolo;

	/**
	 * Determina si el juego se terminó
	 */
	private boolean finJuego;

	/**
	 * El número del jugador en turno
	 */
	private int turno;

	/**
	 * Número de fichas necesarias para ganar
	 */
	private int fichas;
	
	/**
	 * Nombre del ganador del juego
	 */
	private String nombreGanador = "";
	
	/**
	 * Crea una nueva instancia del juego
	 */
	public LineaCuatro(ArrayList <Jugador> pJugadores,int pFil, int pCol, int pFichas)
	{
		tablero = new String[pFil][pCol];
		for(int i=0;i<tablero.length;i++)
		{
			for(int j=0;j<tablero[0].length;j++)
			{
				tablero[i][j]="___";
			}

		}
		jugadores = pJugadores;
		finJuego = false;
		turno = 0;
		fichas = pFichas;
		atacante = jugadores.get(turno).darNombre();
		simbolo = jugadores.get(turno).darSimbolo();
		if (simbolo.length()==1){
			simbolo = " " + simbolo + " ";
		}
	}
	/**
	 * Retorna el tablero del juego
	 * @return tablero
	 */
	public String[][] darTablero()
	{
		return tablero;
	}
	
	/**
	 * Retorna el nombre del ganador de la partida
	 * @return nombre del ganador
	 */
	public String darNombreGanador()
	{
		return nombreGanador;
	}

	/**
	 * Retorna el jugador en turno
	 * @return atacante jugador en turno
	 */
	public String darAtacante()
	{
		return atacante;
	}

	/**
	 * Cambia el atacante y actualiza el turno actual
	 */
	public void cambiarAtacante()
	{
		if (turno<jugadores.size()-1)
		{
			turno++;
			atacante = jugadores.get(turno).darNombre();
			simbolo = jugadores.get(turno).darSimbolo();
			if (simbolo.length()==1){
				simbolo = " " + simbolo + " ";
			}
		}
		else
		{
			turno=0;
			atacante = jugadores.get(turno).darNombre();
			simbolo = jugadores.get(turno).darSimbolo();
			if (simbolo.length()==1){
				simbolo = " " + simbolo + " ";
			}
		}
	}

	/**
	 * Determina si el juego termina
	 * @return true si el juego se termino, false de lo contrario
	 */
	public boolean fin()
	{
		return finJuego;
	}

	/**
	 * Registra una jugada aleatoria
	 */
	public boolean registrarJugadaAleatoria()
	{
		boolean res = false;
		Random r = new Random();
		int fil = r.nextInt(tablero.length);
		int col = r.nextInt(tablero[0].length);

		if (tablero[fil][col].equals("___")){
			res=true;
			tablero[fil][col]=simbolo;
			if (terminar(fil, col)==true){
				res=false;
				nombreGanador=atacante;
			}
			cambiarAtacante();
		}
		else{
			registrarJugadaAleatoria();
		}
		return res;
	}

	/**
	 * Registra una jugada en el tablero
	 * @return true si la jugada se pudo realizar, false de lo contrario
	 */
	public boolean registrarJugada(int col, int fil)
	{
		boolean res = false;
		if (tablero[fil-1][col-1].equals("___")){
			res=true;
			tablero[fil-1][col-1]=simbolo;
			if (terminar(fil, col)==true){
				res=false;
				nombreGanador=atacante;
			}
			cambiarAtacante();
		}
		else{
			res=false;
		}
		return res;
	}

	/**
	 * Determina si una jugada causa el fin del juego
	 * @return true si la jugada termina el juego false de lo contrario
	 */
	public boolean terminar(int fil,int col)
	{
		boolean res = false;
		if (verificarHorizantal(fil)==true||verificarVertical(col)==true||verificarDiag1(fil, col)==true||verificarDiag2(fil, col)==true){
			res = true;
			finJuego = true;
		}
		return res;
	}

	/**
	 * Determina si es jugador completo cuatro en linea horizontalmente.
	 * @param fil Fila de la jugada actual
	 * @return True si ganó. False de lo contrario
	 */
	public boolean verificarHorizantal(int fil){
		int suma = 0;
		boolean gano = false;
		for (int i=0; i<tablero[0].length; i++)
			if (tablero[fil][i].equals(simbolo)){
				suma++;
				if (suma>=fichas){
					gano = true;
				}
			}
			else{
				suma=0;
			}
		return gano;
	}

	/**
	 * Determina si es jugador completo cuatro en linea verticalmente.
	 * @param fil Fila de la jugada actual
	 * @return True si ganó. False de lo contrario
	 */
	public boolean verificarVertical(int col){
		int suma = 0;
		boolean gano = false;
		for (int i=0; i<tablero.length; i++)
			if (tablero[i][col].equals(simbolo)){
				suma++;
				if (suma>=fichas){
					gano = true;
				}
			}
			else{
				suma=0;
			}
		return gano;
	}

	/**
	 * Determina si es jugador completo cuatro en linea en la primera diagonal.
	 * @param fil Fila de la jugada actual
	 * @param col Columna de la jugada actual
	 * @return True si ganó. False de lo contrario
	 */
	public boolean verificarDiag1 (int fil, int col){
		int suma = 1;
		boolean gano = false;
		for(int i=fil+1;i<tablero.length;i++){
			for (int j=col+1;j<tablero[0].length;j++){
				if (tablero[i][j].equals(simbolo)){
					suma++;
				}
				else{
					break;
				}
			}
		}
		for(int i=fil-1;i>0;i--){
			for (int j=col-1;j>0;j--){
				if (tablero[i][j].equals(simbolo)){
					suma++;
				}
				else{
					break;
				}
			}
		}
		if (suma>=fichas){
			gano=true;
		}
		return gano;
	}
	
	/**
	 * Determina si es jugador completo cuatro en linea en la segunda diagonal.
	 * @param fil Fila de la jugada actual
	 * @param col Columna de la jugada actual
	 * @return True si ganó. False de lo contrario
	 */
	public boolean verificarDiag2 (int fil, int col){
		int suma = 1;
		boolean gano = false;
		for(int i=fil-1;i>0;i--){
			for (int j=col+1;j<tablero[0].length;j++){
				if (tablero[i][j].equals(simbolo)){
					suma++;
				}
				else{
					break;
				}
			}
		}
		for(int i=fil+1;i<tablero.length;i++){
			for (int j=col-1;j>0;j--){
				if (tablero[i][j].equals(simbolo)){
					suma++;
				}
				else{
					break;
				}
			}
		}
		if (suma>=fichas){
			gano=true;
		}
		return gano;
	}

}
