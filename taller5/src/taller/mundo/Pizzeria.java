package taller.mundo;

import taller.estructuras.Heap;
import taller.estructuras.Heap.HeapIterator;

public class Pizzeria 
{	
	// ----------------------------------
	// Constantes
	// ----------------------------------

	/**
	 * Constante que define la accion de recibir un pedido
	 */
	public final static String RECIBIR_PEDIDO = "RECIBIR";
	/**
	 * Constante que define la accion de atender un pedido
	 */
	public final static String ATENDER_PEDIDO = "ATENDER";
	/**
	 * Constante que define la accion de despachar un pedido
	 */
	public final static String DESPACHAR_PEDIDO = "DESPACHAR";
	/**
	 * Constante que define la accion de finalizar la secuencia de acciones
	 */
	public final static String FIN = "FIN";

	// ----------------------------------
	// Atributos
	// ----------------------------------

	/**
	 * Heap que almacena los pedidos recibidos
	 */
	private Heap<Pedido> pedidos = new Heap<>(11, new ComparatorPrecio(), Heap.MAX_HEAP);

	/**
	 * Getter de pedidos recibidos
	 */
	private Pedido[] gPedidos;

	/** 
	 * Heap de elementos por despachar
	 */
	private Heap<Pedido> despachar = new Heap<>(11, new ComparatorCercania(), Heap.MIN_HEAP);

	/**
	 * Getter de elementos por despachar
	 */
	private Pedido[] gDespachar;

	// ----------------------------------
	// Constructor
	// ----------------------------------

	/**
	 * Constructor de la case Pizzeria
	 */
	public Pizzeria()
	{
		
	}

	// ----------------------------------
	// Métodos
	// ----------------------------------

	/**
	 * Agrega un pedido a la cola de prioridad de pedidos recibidos
	 * @param nombreAutor nombre del autor del pedido
	 * @param precio precio del pedido 
	 * @param cercania cercania del autor del pedido 
	 */
	public void agregarPedido(String nombreAutor, double precio, int cercania)
	{
		Pedido nuevo = new Pedido(precio, nombreAutor, cercania);
		pedidos.add(nuevo);
	}

	// Atender al pedido más importante de la cola

	/**
	 * Retorna el proximo pedido en la cola de prioridad o null si no existe.
	 * @return p El pedido proximo en la cola de prioridad
	 */
	public Pedido atenderPedido()
	{
		try{
			Pedido primero = pedidos.poll();
			despachar.add(primero);
			return primero;
		}
		catch (Exception e){
			System.out.println("No hay pedidos por atender.");
		}
		return null;
	}

	/**
	 * Despacha al pedido proximo a ser despachado. 
	 * @return Pedido proximo pedido a despachar
	 */
	public Pedido despacharPedido()
	{
		try{
			Pedido resp = despachar.poll();
			return resp;
		}
		catch (Exception e){
			System.out.println("No hay pedidos por despachar.");
		}
		return null;
	}

	/**
	 * Retorna la cola de prioridad de pedidos recibidos como un arreglo. 
	 * @return arreglo de pedidos recibidos manteniendo el orden de la cola de prioridad.
	 */
	public Pedido [] pedidosRecibidosList()
	{
		gPedidos = new Pedido[pedidos.size()];
		HeapIterator it = pedidos.darIterador();
		int i = 0;
		while (it.hasNext()){
			gPedidos[i] = (Pedido) it.next();
			i++;
		}
		return  gPedidos;
	}

	/**
	 * Retorna la cola de prioridad de despachos como un arreglo. 
	 * @return arreglo de despachos manteniendo el orden de la cola de prioridad.
	 */
	public Pedido [] colaDespachosList()
	{
		gDespachar = new Pedido[despachar.size()];
		HeapIterator it = despachar.darIterador();
		int i = 0;
		while (it.hasNext()){
			gDespachar[i] = (Pedido) it.next();
			i++;
		}
		return  gDespachar;
	}
}
