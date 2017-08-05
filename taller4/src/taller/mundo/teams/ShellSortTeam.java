package taller.mundo.teams;

/*
 * ShellSortTeam.java
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

public class ShellSortTeam extends AlgorithmTeam
{
     public ShellSortTeam()
     {
          super("Shellsort :)");
          userDefined = false;
     }

     @Override
     public Comparable[] sort(Comparable[] list, TipoOrdenamiento orden)
     {
          return shellSort(list, orden);
     }

     /**
     Ordena un arreglo de enteros, usando Shellsort con cinco intervalos posibles.
     @param arr Arreglo de enteros.
     **/
     private Comparable[] shellSort(Comparable[] arr, TipoOrdenamiento orden)
     {
        int[] K = {281, 77, 23, 8, 1};          //Conjunto de intervalos posibles. Ver Sedgewick, 1983.
        for(Integer k : K)                      //Por cada intervalo
        {
            for(int i = 0; i < arr.length; i++)     //Recorrer los índices del arreglo
            {
                for(int j = k+i; j >= (i % k)+k; j -= k)    //Se recorre cada sublista, iniciando en el elemento del extremo.
                {
                     if(j >= arr.length)                    //Sí el índice extremo desborda el tamaño del arreglo, finalizar.
                     {
                         break;
                     }

                     int stat = arr[j].compareTo(arr[j-k]);
                     boolean val = stat < 0 && orden == TipoOrdenamiento.ASCENDENTE;
                     val = val || stat > 0 && orden == TipoOrdenamiento.DESCENDENTE;
                     if(val)         //Se realiza ordenamiento por inserción por cada elemento que se encuentra
                     {                                      //k elementos a la izquierda.
                          Comparable temp = arr[j];
                          arr[j] = arr[j-k];
                          arr[j-k] = temp;
                     }
                }
            }
        }
        return arr;
     }
}
