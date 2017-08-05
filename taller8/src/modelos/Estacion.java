package modelos;

import estructuras.Nodo;

/**
 * Clase que representa una estacion del metro de New York
 * @param K tipo del identificador unico de los vertices del grafo 
 */
//TODO La clase debe implementar la interface Nodo
public class Estacion<K extends Comparable<K>> implements Nodo<K>{

	/**
	 * Latitud del estación
	 */
	//TODO declare el atributo latitud
	private double latitud;
	
	/**
	 * Longitud del estación
	 */
	//TODO Declare el atributo longitud	
	private double longitud;
	
	/**
	 * Identificador único del paradero (de tipo K)
	 */
	//TODO Declare el identificador del paradero (de tipo K)
	private K id;
	
	
	/**
	 * Construye un nuevo paradero con un identificador y una ubicacion latitud y la longitud dados
	 * @param id Indentificador unico
	 * @param latitud 
	 * @param longitud 
	 */
	public Estacion(K id, double latitud, double longitud) {
		//TODO implementar
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public K darId(){
		//TODO implementar
		return id;
	}
	
	/**
	 * Devuelve la latitud del paradero
	 * @return latitud
	 */
	public double darLatitud() {
		//TODO implementar
		return latitud;
	}
	
	/**
	 * Devuelve la longitud del paradero
	 * @return longitud
	 */
	public double darLongitud() {
		//TODO implementar
		return longitud;
	}
	
	/**
	 * nombre (latitud, longitud)
	 */
	@Override
	public String toString() {
		return darId() +" ("+darLatitud()+" , "+darLongitud()+")";
	}

	@Override
	public int compareTo(Nodo<K> o) 
	{
		return this.darId().compareTo(o.darId());
	}
	
	
}
