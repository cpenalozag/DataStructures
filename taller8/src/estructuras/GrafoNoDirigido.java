package estructuras;

import java.util.ArrayList;

/**
 * Clase que representa un grafo no dirigido con pesos en los arcos.
 * @param K tipo del identificador de los vertices (Comparable)
 * @param E tipo de la informacion asociada a los arcos

 */
public class GrafoNoDirigido<K extends Comparable<K>, E> implements Grafo<K,E> {

	/**
	 * Nodos del grafo
	 */
	//TODO Declare la estructura que va a contener los nodos

	private HashLinear<K, Nodo<K>> nodos;

	/**
	 * Lista de adyacencia 
	 */
	private HashLinear<String, ListaSencillamenteEncadenada<Arco<K,E>>> adj;
	//TODO Utilice sus propias estructuras (defina una representacion para el grafo)
	// Es libre de implementarlo con la representacion de su agrado. 

	private int cantRutas;
	/**
	 * Construye un grafo no dirigido vacio.
	 */
	public GrafoNoDirigido() {
		//TODO implementar
		nodos = new HashLinear<>();

		adj = new HashLinear<>();
	}

	@Override
	public boolean agregarNodo(Nodo<K> nodo) {
		//TODO implementar
		nodos.put(nodo.darId(), nodo);
		return nodos.contains(nodo.darId());
	}

	@Override
	public boolean eliminarNodo(K id) {
		//TODO implementar

		nodos.delete(id);
		return !nodos.contains(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Arco<K,E>[] darArcos() {
		Arco[] arcos = new Arco[cantRutas];
		try{

			int i = 0;
			for(String key: adj.keys())
			{
				ListaSencillamenteEncadenada<Arco<K,E>> lista = adj.get(key);
				for(int j = 0; j<lista.size; j++)
				{
					Arco<K,E> actual = lista.get(j);
					if(actual!=null)
					{
						arcos[i] = actual;
						i++;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arcos;
		//TODO implementar
	}

	private Arco<K,E> crearArco( K inicio, K fin, double costo, E e )
	{
		Nodo<K> nodoI = buscarNodo(inicio);
		Nodo<K> nodoF = buscarNodo(fin);
		if ( nodoI != null && nodoF != null)
		{
			return new Arco<K,E>( nodoI, nodoF, costo, e);
		}
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Nodo<K>[] darNodos() {
		//TODO implementar
		Nodo<K>[] nod = new Nodo[nodos.size()];

		int i = 0;
		for(K key: nodos.keys())
		{
			nod[i++] = nodos.get(key);
		}
		return nod;
	}

	@Override
	public boolean agregarArco(K inicio, K fin, double costo, E obj) {
		//TODO implementar
		Arco<K, E> arc = crearArco(inicio, fin, costo, obj);
		boolean ret  =false;
		ListaSencillamenteEncadenada<Arco<K,E>> lista = adj.get(inicio.toString());
		if(lista!=null)
			lista.agregar(arc);
		else
		{
			ListaSencillamenteEncadenada<Arco<K,E>> listas = new ListaSencillamenteEncadenada<Arco<K, E>>();
			listas.agregar(arc);
			adj.put(inicio.toString(), listas);
		}
		return ret;
	}

	@Override
	public boolean agregarArco(K inicio, K fin, double costo) {
		return agregarArco(inicio, fin, costo, null);
	}

	@Override
	public Arco<K,E> eliminarArco(K inicio, K fin) {
		//TODO implementar
		ListaSencillamenteEncadenada<Arco<K,E>> lista = adj.get(inicio.toString());
		if(lista!=null)
		{
			for (int i = 0; i<lista.size; i++){
				Arco<K,E> actual = lista.get(i);
				if(actual.darNodoFin().equals(fin)) 
					return actual;
			}
		}
		return null;
	}

	@Override
	public Nodo<K> buscarNodo(K id) {
		//TODO implementar
		return nodos.get(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Arco<K,E>[] darArcosOrigen(K id) {
		//TODO implementar

		ListaSencillamenteEncadenada<Arco<K,E>> lista = adj.get(id.toString());
		System.out.println(id.toString());
		if(lista!=null){
			Arco[] arcos =  new Arco[lista.size()];

			try{
				if(lista!=null)
				{
					int i = 0;
					for (int j = 0; j< lista.size; j++){
						Arco<K,E> actual = lista.get(j);
						arcos[i] = actual;
						i++;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			return arcos;
		}
		else 
			return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Arco<K,E>[] darArcosDestino(K id) {
		//TODO implementar
		Arco[] j = darArcos();
		ArrayList<Arco> y = new ArrayList<Arco>();
		Arco[] retu =null;
		try{
			int i = 0;
			while(i<adj.size())
			{
				if(j[i].darNodoFin().equals(id))
				{
					y.add(j[i]);

				}
				i++;
			}
			retu = new Arco[y.size()];
			int k =0;
			for(Arco u: y)
			{
				retu[k++]=u;

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retu;
	}


	public void cambiarTamhRutas(int cantRutas) {
		// TODO Auto-generated method stub
		this.cantRutas = cantRutas;
	}

}
