package taller.test;

import java.util.Arrays;

import junit.framework.TestCase;
import taller.mundo.AlgorithmTournament.TipoOrdenamiento;
import taller.mundo.teams.BubbleSortTeam;

public class BubbleSortTest extends TestCase{

	public void testOrdenarAscendente() {
		Comparable[] arr = {12, 6, 2, 11, 5, 10, 1, 8, 4, 9, 3, 7};
		BubbleSortTeam b = new BubbleSortTeam();
		TipoOrdenamiento ascendente = TipoOrdenamiento.ASCENDENTE;
		b.sort(arr, ascendente);
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]", Arrays.toString(arr));
	}

	public void testOrdenarDescendente() {
		Comparable[] arr = {12, 6, 2, 11, 5, 10, 1, 8, 4, 9, 3, 7};
		BubbleSortTeam b = new BubbleSortTeam();
		TipoOrdenamiento descendente = TipoOrdenamiento.DESCENDENTE;
		b.sort(arr, descendente);
		assertEquals("[12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]", Arrays.toString(arr));
	}
	
	public void testOrdenarAscendenteConDuplicados() {
		Comparable[] arr = {12, 5, 2, 11, 5, 9, 1, 8, 4, 9, 2, 5};
		BubbleSortTeam b = new BubbleSortTeam();
		TipoOrdenamiento ascendente = TipoOrdenamiento.ASCENDENTE;
		b.sort(arr, ascendente);
	    assertEquals("[1, 2, 2, 4, 5, 5, 5, 8, 9, 9, 11, 12]", Arrays.toString(arr));
	}
	
	public void testOrdenarDescendenteConDuplicados() {
		Comparable[] arr = {12, 5, 2, 11, 5, 9, 1, 8, 4, 9, 2, 5};
		BubbleSortTeam b = new BubbleSortTeam();
		TipoOrdenamiento ascendente = TipoOrdenamiento.DESCENDENTE;
		b.sort(arr, ascendente);
	    assertEquals("[12, 11, 9, 9, 8, 5, 5, 5, 4, 2, 2, 1]", Arrays.toString(arr));
	}
}