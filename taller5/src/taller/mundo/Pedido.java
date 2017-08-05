package taller.mundo;

public class Pedido
{

	// ----------------------------------
	// Atributos
	// ----------------------------------

	/**
	 * Precio del pedido
	 */
	private double precio;
	
	/**
	 * Autor del pedido
	 */
	private String autorPedido;
	
	/**
	 * Cercania del pedido
	 */
	private int cercania;
	
	// ----------------------------------
	// Constructor
	// ----------------------------------
	
	/**
	 * Constructor del pedido
	 * TODO Defina el constructor de la clase
	 */
	public Pedido( double pPrecio, String pAutorPedido, int pCercania ){
		precio = pPrecio;
		autorPedido = pAutorPedido;
		cercania = pCercania;
	}
	
	// ----------------------------------
	// Métodos
	// ----------------------------------
	
	/**
	 * Getter del precio del pedido
	 */
	public double getPrecio()
	{
		return precio;
	}
	
	/**
	 * Getter del autor del pedido
	 */
	public String getAutorPedido()
	{
		return autorPedido;
	}
	
	/**
	 * Getter de la cercania del pedido
	 */
	public int getCercania() {
		return cercania;
	}
	
}
