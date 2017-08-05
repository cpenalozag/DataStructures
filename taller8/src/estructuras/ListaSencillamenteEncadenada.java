package estructuras;

import estructuras.ListaSencillamenteEncadenada.NodoSencillo;

/**
 * Clase que representa una lista sencillamente encadenada
 * @param <T>
 */
public class ListaSencillamenteEncadenada<T>
{

	/**
	 * Primer nodo de la lista.
	 */
	protected NodoSencillo<T> primero;

	/**
	 * Ultimo nodo de la lista
	 */
	protected NodoSencillo<T> ultimo;

	/**
	 * Tamaño de la lista.
	 */
	protected int size;	
	/**
	 * Comparador elementos
	 */
	protected IComparator<T> comparador;

	/**
	 * Constructor nodo lista sencillamente encadenado.
	 * @param <T> Elemento del nodo
	 */
	public static class NodoSencillo <T>
	{
		/**
		 * Crea un nuevo nodo
		 * @param nElemento el elemento a almacenar en el nodo. nElemento != null
		 */
		public NodoSencillo(T nElemento)
		{

			elemento = nElemento;
			siguiente=null;
		}
		/**
		 * Elemento alamacenado en el nodo
		 */
		protected T elemento;

		/**
		 * Siguiente nodo
		 */
		protected NodoSencillo<T> siguiente;

		/**
		 * Cambia el nodo siguiente
		 * post: Se ha cambiado el nodo siguiente
		 * @param nSiguiente el nuevo elemento siguiente
		 */
		public void cambiarSiguiente(NodoSencillo<T> nSiguiente)
		{
			siguiente = nSiguiente;
		}

		/**
		 * Devuelve el elemento almacenado en el nodo
		 * @return elemento
		 */
		public T darElemento()
		{
			return elemento;
		}

		/**
		 * Cambia el elemento almacenado en el nodo
		 * @param nElemento el nuevo elemento a almacenar en el nodo
		 */
		public void cambiarElemento(T nElemento)
		{
			elemento = nElemento;
		}

		/**
		 * Devuelve el identificador del nodo
		 * Corresponde al identificador del elemento almacenado
		 * @return identificador
		 */
		public String darIdentificador()
		{
			return elemento.toString();
		}

		/**
		 * Devuelve el siguiente nodo
		 * @return siguiente
		 */
		public NodoSencillo<T> darSiguiente()
		{
			return siguiente;
		}
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�mentro
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * 
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaSencillamenteEncadenada(T nPrimero, IComparator<T> pComparador)
	{
		if(nPrimero == null)
		{
			primero = null;
		}
		else{
			primero = new NodoSencillo<T>( nPrimero );
		}

		this.comparador = pComparador;
	}

	public ListaSencillamenteEncadenada(IComparator<T> pComparador)
	{
		primero = null;
		this.comparador = pComparador;
	}

	public ListaSencillamenteEncadenada()
	{
		primero = null;
		this.comparador = null;
	}

	public ListaSencillamenteEncadenada(T nPrimero)
	{
		if(nPrimero == null)
		{
			primero = null;
		}
		else{
			primero = new NodoSencillo<T>( nPrimero );
		}
	}

	/**
	 * Retorna el primer elemento de la lista
	 */
	public NodoSencillo darPrimero(){
		return primero;
	}
	
	/**
	 * Retorna el ultimo elemento de la lista
	 */
	public NodoSencillo darUltimo(){
		return ultimo;
	}


	/**
	 * Retorna el tamaño de la lista
	 * @return Tamaño de la lista sencillamente encadenada.
	 */
	public int size ()
	{
		return size;
	}

	/**
	 * Indica si la lista est� vacia
	 * @return true si la lista est� vacia o false en caso contrario
	 */
	public boolean isEmpty( )
	{
		return primero == null;
	}

	public boolean contains(T elem) {
		NodoSencillo<T> actual = primero;
		while (actual.siguiente!=null){
			if (actual.elemento.equals(elem)){
				return true;
			}
			actual = actual.siguiente;
		}
		return false;
	}

	public String toString()
	{
		String cadena= "";
		NodoSencillo<T> ms = primero;
		while(ms!= null)
		{
			cadena+= ms.darIdentificador()+";";
			ms = ms.siguiente;
		}
		return cadena;
	}
	/**
	 * Agrega un elemento a la lista
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
	 * @param elem el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public void agregar (T elem){
		if (insert(elem)){
			size++;
		}
	}
	
	private boolean insert(T elem) {
		boolean resp = false;
		if(elem == null)
		{
			throw new NullPointerException( );
		}
		else{
			NodoSencillo<T> nuevo = crearNuevoNodo(elem);
			if (primero == null){
				agregarAlComienzo(nuevo);
				resp=true;
			}
			else{
				if (comparador==null && !contains(elem)){
					agregarAlFinal(nuevo);
					return true;
				}
				else{
					if (comparador.compare(elem, ultimo.darElemento())>0.0){
						agregarAlFinal(nuevo);
						return true;
					}
					else if (comparador.compare(elem, primero.darElemento())<0.0){
						agregarAlComienzo(nuevo);
						return true;
					}
					else{
						return agregar(nuevo, elem);
					}
				}
			}
		}

		return resp;
	}

	private boolean agregar(NodoSencillo<T> nodo, T elem) 
	{
		NodoSencillo<T> actual = primero;
		while (actual.siguiente!=null && comparador.compare(elem, actual.siguiente.darElemento())>0.0){
			actual = actual.siguiente;
		}
		if (comparador.compare(elem, actual.darElemento())==0.0){
			return false;
		}
		else{
			NodoSencillo<T> siguiente = actual.siguiente;
			actual.siguiente = nodo;
			nodo.siguiente = siguiente;
			return true;
		}
	}
	
	public void agregarAlFinal (T elem){
		NodoSencillo<T> nuevo = crearNuevoNodo(elem);
		agregarAlFinal(nuevo);
	}

	private void agregarAlFinal(NodoSencillo<T> nuevo) {
		if (nuevo == null)
			return;
		else {
			nuevo.siguiente = null;
			if (primero == null) {
				primero = nuevo;
				ultimo = nuevo;
			} else {
				ultimo.siguiente = nuevo;
				ultimo = nuevo;
			}
		}
	}

	private void agregarAlComienzo(NodoSencillo<T> nuevo) {
		if (nuevo == null)
			return;
		else {
			if (primero == null) {
				nuevo.siguiente = null;
				primero = nuevo;
				ultimo = nuevo;
			} else {
				nuevo.siguiente = primero;
				primero = nuevo;
			}
		}
	}

	private NodoSencillo<T> crearNuevoNodo(T item) {
		return new NodoSencillo<T>(item);
	}

	public boolean remover(T elem){
		if (primero.darElemento().equals(elem)){
			if (removerPrimero()!=null){
				size--;
				return true;
			}
		}
		else if (ultimo.darElemento().equals(elem)){
			if (removerUltimo()!=null)
			{
				size--;
			return true;
			}
		}
		else{
			if (remove(elem)){
				size--;
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Elimina el primer nodo y retorna su elemento
	 */
	public T removerPrimero( )
	{
		T resp = null;
		if(primero != null)
		{
			if (primero.darSiguiente()==null)
			{
				resp = primero.darElemento();
				primero = null;
			}
			else{
				resp = primero.darElemento();
				primero = primero.darSiguiente();
			}
		}
		return resp;
	}

	private T removerUltimo() {
		T resp = null;
		if (ultimo == null)
			return resp;
		else {
			if (primero == ultimo) {
				resp = primero.darElemento();
				primero = null;
				ultimo = null;
			} 
			else {
				NodoSencillo<T> penultimo = primero;
				while (penultimo.siguiente != ultimo)
					penultimo = penultimo.siguiente;
				ultimo = penultimo;
				resp = penultimo.siguiente.darElemento();
				penultimo.siguiente = null;
			}
		}
		return resp;
	}

	private boolean remove(T elem) {
		NodoSencillo<T> actual = primero;
		while (actual.siguiente!=null && comparador.compare(elem, actual.siguiente.darElemento())>0){
			actual = actual.siguiente;
		}
		if (actual.siguiente!=null){
			actual.siguiente = actual.siguiente.siguiente;
			return true;
		}
		return false;
	}

	/**
	 * Borra todos los elementos de la lista
	 * post: el primer elemento es nulo
	 */
	public void clear( )
	{
		primero = null;
		size = 0;
	}

	/**
	 * Devuelve el elemento de la posici�n dada
	 * @param pos la posici�n  buscada
	 * @return el elemento en la posici�n dada 
	 * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
	 */
	public T get( int pos ) throws IndexOutOfBoundsException
	{
		T resp = null; 
		NodoSencillo<T> x = null;
		if ( pos < 0 || pos >= size() )
		{
			throw new IndexOutOfBoundsException( );
		}
		if (pos == size()){
			resp = primero.darElemento( );
		}
		if (pos<=size()){
			int i = 0;
			x = primero;
			while (i<pos){
				x = x.darSiguiente();
				i++;
			}
		}
		return x.darElemento();
	}

	/**
	 * Agrega el elemento que entra por parametro al nodo en la posicion indicada
	 * @param pos Posicion del nodo
	 * @param elem Elemento que se agrega al nodo
	 */
	public void set (int pos, T elem){
		int i = 0;
		NodoSencillo<T> nodo = primero;
		while (nodo!=null){
			if (i == pos){
				nodo.cambiarElemento(elem);
				break;
			}
			i++;
			nodo = nodo.darSiguiente();
		}
	}

	/**
	 * Indica la posici�n del objeto que llega por par�metro en la lista
	 * @param objeto el objeto que se desea buscar en la lista. objeto != null
	 * @return la posici�n del objeto o -1 en caso que no se encuentre en la lista
	 */
	public int indexOf( Object objeto )
	{
		int respuesta = -1;
		boolean termino = false;
		if ( objeto!=null )
		{
			for ( int i  = 0; i < size && !termino; i++ )
			{
				T actual = get( i );
				if ( actual == objeto )
				{
					termino = true;
					respuesta = i;

				}
			}
		}

		return respuesta;
	}

	/**
	 * Crea un nuevo iterador
	 * @return iterador sencillo
	 */
	public IteradorSencillo<T> darIteradorSencillo()
	{
		return new IteradorSencillo<>(this);
	}
	public class IteradorSencillo <T>
	{
		/**
		 * El nodo donde se encuentra el iterado
		 */
		private NodoSencillo<T> actual;
		
		public IteradorSencillo(ListaSencillamenteEncadenada pLista){
			actual = pLista.darPrimero();
		}

		/**
		 * Indica si a˙n hay elementos por recorrer
		 * @return true en caso de que a˙n haya elemetos o false en caso contrario
		 */
		public boolean hasNext( )
		{
			return actual != null;
		}

		/**
		 * Devuelve el siguiente elemento a recorrer
		 * post: se actualizado actual al siguiente del actual
		 * @return objeto en actual
		 */
		public T next( )
		{
			T valor = (T)actual.darElemento( );
			actual = actual.darSiguiente( );
			return valor;
		}
	}
}
