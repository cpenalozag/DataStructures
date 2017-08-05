package taller.mundo.teams;

/*
 * QuickSortTeam.java
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

import java.util.Random;
import static taller.mundo.AlgorithmTournament.TipoOrdenamiento;

public class QuickSortTeam extends AlgorithmTeam
{

	private static Random random = new Random();

	public QuickSortTeam()
	{
		super("Quicksort (*)");
		userDefined = true;
	}

	@Override
	public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
	{
		quicksort(list, 0, list.length-1, orden);
		return list;
	}
	// Trabajo en Clase

	private static void quicksort(Comparable[] lista, int inicio, int fin, TipoOrdenamiento orden)
	{
		int index = particion(lista, inicio, fin, orden);
		if (inicio < index - 1)
			quicksort(lista, inicio, index - 1, orden);
		if (index < fin)
			quicksort(lista, index, fin, orden);
	}

	private static int particion(Comparable[] lista, int inicio, int fin, TipoOrdenamiento orden)
	{
		int i = inicio, j = fin;
		Comparable temp;
		Comparable pivot = lista[eleccionPivote(inicio, fin)];

		switch(orden){
		case ASCENDENTE:
			while (i <= j) {
				while (lista[i].compareTo(pivot)<0)
					i++;
				while (lista[j].compareTo(pivot)>0)
					j--;
				if (i <= j) {
					temp = lista[i];
					lista[i] = lista[j];
					lista[j] = temp;
					i++;
					j--;
				}
			}
			break;
		case DESCENDENTE:
			while (i <= j) {
				while (lista[i].compareTo(pivot)>0)
					i++;
				while (lista[j].compareTo(pivot)<0)
					j--;
				if (i <= j) {
					temp = lista[i];
					lista[i] = lista[j];
					lista[j] = temp;
					i++;
					j--;
				}
			}
			break;
		}

		return i;
	}

	private static int eleccionPivote(int inicio, int fin)
	{
		/**
           Este procedimiento realiza la elecci�n de un �ndice que corresponde al pivote res-
           pecto al cual se realizar�  la partici�n de la lista. Se recomienda escoger el ele-
           mento que se encuentra en la mitad, o de forma aleatoria entre los �ndices [inicio, fin).
		 **/
		int pivot = (inicio + fin)/2; 
//		int pivot = randInt(inicio, fin);
		return pivot;
	}

	/**
      Retorna un número aleatorio que se encuentra en el intervalo [min, max]; inclusivo.
      @param min, índice inicial del intervalo.
      @param max, índice final del intervalo.
      @return Un número aleatorio en el intervalo [min, max].
	 **/
	public static int randInt(int min, int max) 
	{
		int randomNum = random.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	// Trabajo en Clase

}
