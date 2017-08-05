package taller.mundo;

public class Jugador 
{
	
	/**
	 * Nombre del jugador
	 */
	private String nombre;
	
	/**
	 * Símbolo escogido por el jugador
	 */
	private String simbolo;
	
	/**
	 * Crea un nuevo jugador
	 */
	public Jugador(String pNombre, String pSimbolo)
	{
		nombre = pNombre;
		simbolo = pSimbolo;
	}
	
	/**
	 * Retorna el nombre del jugador
	 * @return nombre del jugador
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * Retorna el símbolo escogido por el jugador
	 * @return simbolo símbolo del jugador
	 */
	public String darSimbolo()
	{
		return simbolo;
	}

}
