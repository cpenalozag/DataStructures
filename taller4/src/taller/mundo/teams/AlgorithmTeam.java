package taller.mundo.teams;

/*
 * AlgorithmTeam.java
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

import static taller.mundo.AlgorithmTournament.TipoOrdenamiento;

public abstract class AlgorithmTeam implements Comparable<AlgorithmTeam>
{
     public String name;
     public double time;
     public String timeFormat;
     public double accuracy;
     protected boolean userDefined = true;

     public abstract Comparable[] sort(Comparable[] list, TipoOrdenamiento orden);

     public AlgorithmTeam(String name)
     {
         this.name = name;
     }

     @Override
     public String toString()
     {
        return String.format("%25s | %10s | %12g", name, timeFormat, accuracy); //"Equipo: "+name;//String.format("Equipo: %s | Posición %d | Tiempo total: %.2fs", name, overallPosition, time);
     }

    @Override
    public int hashCode() 
    {
        return name.hashCode();
    }

    @Override
    public int compareTo(AlgorithmTeam t)
    {
         if(time > t.time)
         {
             return 1;
         }
         else if(time == t.time)
         {
             return 0;
         }
         else
         {
             return -1;
         }
    }

}
