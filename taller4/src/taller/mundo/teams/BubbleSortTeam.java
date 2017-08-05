package taller.mundo.teams;

/*
 * BubbleSortTeam.java
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

import taller.mundo.AlgorithmTournament.TipoOrdenamiento;


public class BubbleSortTeam extends AlgorithmTeam
{
	public BubbleSortTeam()
	{
		super("Bubble sort (-)");
		userDefined = false;
	}

	@Override
	public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
	{
		return bubbleSort(list, orden);
	}

	/**
     Ordena un arreglo de enteros, usando Bubble Sort.
     @param arr Arreglo de enteros.
	 **/
	private Comparable[] bubbleSort(Comparable[] arr, TipoOrdenamiento orden)
	{
		boolean termino = false;
			for (int i=arr.length-1; i>0 && !termino; i--){
				int cambios = 0;
				for (int j = 0; j < i; j++){
					switch (orden){
					case DESCENDENTE:
						if(arr[j].compareTo(arr[j+1])<0){
							Comparable temp = arr[j];
							arr[j]=arr[j+1];
							arr[j+1]=temp;
							cambios++;
						}
						break;
					case ASCENDENTE:
						if(arr[j].compareTo(arr[j+1])>0){
							Comparable temp = arr[j];
							arr[j]=arr[j+1];
							arr[j+1]=temp;
							cambios++;
						}
						break;
					}
				}
				if (cambios==0){
					termino = true;
				}

			}

			return arr;
		}
	}
