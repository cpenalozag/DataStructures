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

public class NewSortTeam extends AlgorithmTeam
{

	public NewSortTeam()
	{
		super("Hybrid (*)");
		userDefined = true;
	}

	public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
	{
		hybridSort(list, 0, list.length-1, orden);
		return list;
	}
	// Trabajo en Clase

	private static void hybridSort(Comparable[] lista, int inicio, int fin, TipoOrdenamiento orden)
	{
		int size = (fin+1) - inicio;
		if (inicio < fin){
			if (size <= 10){
				InsertionSortTeam i = new InsertionSortTeam();
				i.sort(lista, orden);
			}
			else{
				QuickSortTeam q = new QuickSortTeam();
				q.sort(lista, orden);
			}
		}
	}

	
}
