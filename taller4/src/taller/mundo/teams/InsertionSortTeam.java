package taller.mundo.teams;

/*
 * InsertionSortTeam.java
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

public class InsertionSortTeam extends AlgorithmTeam
{
	public InsertionSortTeam()
	{
		super("Insertion sort (-)");
		userDefined = false;
	}

	@Override
	public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
	{
		return insertionSort(list, orden);
	}

	/*
     Ordena un arreglo de enteros, usando Ordenamiento por inserci�n.
     @param arr Arreglo de enteros.
	 **/
	private Comparable[] insertionSort(Comparable[] arr, TipoOrdenamiento orden)
	{
		for (int i=1; i<arr.length; i++){
			for (int j=i; j>0; j--){
				switch (orden){
				case ASCENDENTE:
					if (arr[j].compareTo(arr[j-1])<0 && getState(arr[j-1], arr[j], orden)){
						Comparable temp = arr[j-1];
						arr[j-1]=arr[j];
						arr[j]=temp;
					}
					break;
				case DESCENDENTE:
					if (arr[j].compareTo(arr[j-1])>0 && getState(arr[j-1], arr[j], orden)){
						Comparable temp = arr[j-1];
						arr[j-1]=arr[j];
						arr[j]=temp;
					}
					break;
				}
			}
		}

		return arr;
	}

	private boolean getState(Comparable a, Comparable b, TipoOrdenamiento orden)
	{
		int stat = a.compareTo(b);
		boolean val = stat > 0 && orden == TipoOrdenamiento.ASCENDENTE;
		val = val || stat < 0 && orden == TipoOrdenamiento.DESCENDENTE;
		return val;
	}
}
