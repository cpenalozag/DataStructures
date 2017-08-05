package taller.mundo.teams;

/*
 * MergeSortTeam.java
 * This file is part of AlgorithmRace
 *
 * Copyright (C) 2015 - ISIS1206 Team 
 *
 * AlgorithmRace is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * AlgorithmRace is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AlgorithmRace. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Arrays;
import static taller.mundo.AlgorithmTournament.TipoOrdenamiento;

public class MergeSortTeam extends AlgorithmTeam
{
	public MergeSortTeam()
	{
		super("Merge sort (*)");
		userDefined = true;
	}

	@Override
	public Comparable[] sort(Comparable[] lista, TipoOrdenamiento orden)
	{
		return merge_sort(lista, orden);
	}


	private static Comparable[] merge_sort(Comparable[] lista, TipoOrdenamiento orden)
	{
		int size = lista.length;
		if (size < 2){
			return lista;
		}
		int mid = size / 2;
		int tamIzquierda = mid;
		int tamDerecha = size - mid;
		Comparable[] izquierda = new Comparable[tamIzquierda];
		Comparable[] derecha = new Comparable[tamDerecha];
		for (int i = 0; i < mid; i++) {
			izquierda[i] = lista[i];

		}
		for (int i = mid; i < size; i++) {
			derecha[i - mid] = lista[i];
		}
		merge_sort(izquierda, orden);
		merge_sort(derecha, orden);
		lista = merge(izquierda, derecha, lista, orden);

		return lista;
	}

	private static Comparable[] merge(Comparable[] izquierda, Comparable[] derecha, Comparable[] lista, TipoOrdenamiento orden)
	{
		int tamIzquierda = izquierda.length;
		int tamDerecha = derecha.length;
		int i = 0, j = 0, k = 0;
		while (i < tamIzquierda && j < tamDerecha) {
			switch (orden){
			case ASCENDENTE:
				if (izquierda[i].compareTo(derecha[j])<=0 ) {
					lista[k] = izquierda[i];
					i++;
					k++;
				} else {
					lista[k] = derecha[j];
					k++;
					j++;
				}
				break;
			case DESCENDENTE:
				if (izquierda[i].compareTo(derecha[j])>=0 ) {
					lista[k] = izquierda[i];
					i++;
					k++;
				} else {
					lista[k] = derecha[j];
					k++;
					j++;
				}
			}
		}
		while (i < tamIzquierda) {
			lista[k] = izquierda[i];
			k++;
			i++;
		}
		while (j < tamDerecha) {
			lista[k] = derecha[j];
			k++;
			j++;
		}

		return lista;
	}


}
