
rem run es una función de bash que simplifica el proceso de ejecución de un programa descrito en código fuente de Java.
rem Copyright (C) 2015 - Equipo ISIS1206

rem This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
rem This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

rem You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.


rem Realiza la compilación del archivo Jar
call ant all

rem Consulta el resultado del proceso; en caso de que el proceso no haya sido
rem exitoso, enviar un mensaje de alerta.

 if %ERRORLEVEL% NEQ 0 (
     echo An error ocurred while building Java classes.
     exit /B 2
 )

rem La ventana de la consola es vaciada.
call cls

rem Ejecución de la aplicación:

rem Reemplazar default-master.jar por ${project.name}-${version}.jar, 
rem los valores de las variables project.name y version declaradas en build.xml
call java -jar dist/taller.jar
