package taller.interfaz;

/*
 * Screen.java
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

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Screen
{
    public static void clear()
    {
         String OS = System.getProperty("os.name");
         String ins = null;

         if(OS.contains("Windows"))
         {
              ins = "cls";
              for(int c = 0; c < 1000; c++)
              {
                     System.out.println("\b") ;
              }
         }
         else
         {
              ins = "clear";
         }

         try
         {
              Runtime r = Runtime.getRuntime();
              Process pr = r.exec(new String[]{ins});
              pr.waitFor();
              String line = "";
 
              try(BufferedReader reader = 
              new BufferedReader(new InputStreamReader(pr.getInputStream())))
              {
                     while ((line = reader.readLine())!= null) 
                     {
                            System.out.println(line);
                     }
              }
              
         }
         catch(Exception e)
         {

         }
    }
}
