#! /usr/bin/env bash

#run es una función de bash que simplifica el proceso de ejecución de un programa descrito en código fuente de Java.
#Copyright (C) 2015 - Equipo ISIS1206

#This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
#This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

#You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.


#Realiza la compilación del archivo Jar
ant all

#Consulta el resultado del proceso
OUT=$?

#Si hubo errores, se imprime un mensaje de alerta
if [ $OUT -ne 0 ];then
   echo "An error ocurred while building Java Classes."
   exit 2
fi

#La ventana de la consola es vaciada.
clear

#Ejecución de la aplicación:
java -jar dist/taller.jar
