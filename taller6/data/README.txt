¿Qué pasa si llega nueva información para una llave que ya existe en la tabla de hash?
Si llega nueva información para una llave existente, la tabla buscará el siguiente espacio vació al que debería ocupar ya que esta sigue el modelo de lineal probing.

¿Cuál es el criterio de crecimiento de la tabla?
Si al agregar un elemento, se alcanza el factor de carga máximo, que en este caso es de 50%, se duplica el tamaño de la tabla. (Creando una nueva con el doble del tamaño y haciendo rehash de los elementos existentes)

¿Cuál función de hashing escogió y por qué?
Utilice la función de hash del método .hashCode() ya que esta está diseñada para no aumentar la complejidad temporal significativamente y permite un funcionamiento correcto del programa.

Mida el tiempo de ejecución de los requerimientos R1, R2 para tablas de hash con 500000, 1000000, 1500000 y 2000000 de datos. Haga una tabla de comparación de tiempos de R1 y R2 para cada tabla de hash definida ¿Qué opina de estos resultados? ¿Si agrega más datos los tiempos de ejecución cambian significativamente? Responda y justifique estas preguntas en el archivo README.txt

R1
Tiempo (en nanosegundos)     Número de datos
6100                         500000
6482                         1000000
6383                         1500000
6521                         2000000

R2
Tiempo (en nanosegundos)     Número de datos
68927910                     500000
120957310                    1000000
239810273                    1500000
298736482                    2000000

Estos resultados muestran que el tiempo requerido para agregar datos no cambia significativamente, mientras que el crecimiento en el tiempo de búsqueda es casi proporcional al aumento en la cantidad de datos de la tabla.

¿Tiene sentido hacer una tabla de listas?
Crear una tabla de listas tiene sentido en caso de querer analizar distintos datos que tienen algo en común, en este caso se pueden buscar los distintos ciudadanos con el mismo nombre o apellido, resulta menos eficiente pero podría ser útil para búsquedas como las mencionadas previamente.

Describa en qué situaciones las tablas de Hashing no son una buena opción.
Cuando la cantidad de datos cambia mucho, ya que existe necesidad de ampliar el espacio de la tabla. Se trata de una operación costosa.
Si hay datos complejos ya que existe dificultad para recorrer todos los elementos. Se suelen emplear listas para procesar la totalidad de los elementos.
Cuando se desea hacer un buen uso de la mejoría el cual se desaprovecha un poco al usar una tabla de hash. Si se reserva espacio para todos los posibles elementos, se consume más memoria de la necesaria.


