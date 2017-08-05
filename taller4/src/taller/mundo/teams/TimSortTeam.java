package taller.mundo.teams;

/*
 * TimSortTeam.java
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

public class TimSortTeam extends AlgorithmTeam
{
     public TimSortTeam()
     {
          super("Timsort :)");
          userDefined = false;
     }

     @Override
     public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
     {
           Arrays.sort(list);
           if(orden == TipoOrdenamiento.DESCENDENTE)
           {
                return reverseArray(list);
           }
           return list;
     }

     public Comparable[] reverseArray(Comparable[] list)
     {
          for(int i = 0; i < list.length/2; i++)
          {
              Comparable temp = list[i];
              list[i] = list[list.length - 1 - i];
              list[list.length - 1 - i] = temp; 
          }
          return list;
    }
}
