package taller.mundo.teams;

/*
 * SelectionSortTeam.java
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

public class SelectionSortTeam extends AlgorithmTeam
{
	public SelectionSortTeam()
	{
		super("Selection Sort (-)");
		userDefined = false;
	}

	@Override
	public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
	{
		return selectionSort(list, orden);
	}

	/**
      Ordena un arreglo de enteros, usando Ordenamiento por selección.
      @param arr Arreglo de enteros.
	 **/
	private Comparable[] selectionSort(Comparable[] arr, TipoOrdenamiento orden)
	{
		for (int i=0; i<arr.length-1; i++){
			int comp = i;
			for (int j=i+1; j<arr.length; j++){
				switch (orden){
				case DESCENDENTE:
					if (arr[j].compareTo(arr[comp])>0){
						comp=j;
					}
					break;
				case ASCENDENTE:
					if (arr[j].compareTo(arr[comp])<0){
						comp=j;
					}
					break;
				}
			}
			Comparable temp=arr[i];
			arr[i]=arr[comp];
			arr[comp]=temp;
		}

		return arr;
	}
}
