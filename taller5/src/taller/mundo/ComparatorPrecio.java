package taller.mundo;

import java.util.Comparator;

public class ComparatorPrecio implements Comparator<Pedido> 
{

	public int compare(Pedido arg0, Pedido arg1) {
		return (int) (arg0.getPrecio()-arg1.getPrecio());
	}

}
