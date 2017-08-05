package taller.mundo;

import java.util.Comparator;

public class ComparatorCercania implements Comparator<Pedido> 
{

	public int compare(Pedido arg0, Pedido arg1) {
		return (arg0.getCercania()-arg1.getCercania());
	}

}
