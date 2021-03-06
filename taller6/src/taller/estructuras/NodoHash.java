package taller.estructuras;

public class NodoHash<K,V> {

	private K llave;
	private V valor;
	
	public NodoHash(K llave, V valor) {
		super();
		this.llave = llave;
		this.valor = valor;
	}
	
	public K getLlave() {
		return llave;
	}
	
	public void setLlave(K llave) {
		this.llave = llave;
	}
	
	public V getValor() {
		return valor;
	}
	
	public void setValor(V valor) {
		this.valor = valor;
	}
	
}
