package taller.interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import taller.estructuras.TablaHash;


public class Main {

	private static String rutaEntrada = "./data/data500k.csv";
	private static TablaHash<String, Ciudadano> tablaNumero;
	private static TablaHash<String, Ciudadano> tablaNombre;
	private static TablaHash<String, Ciudadano> tablaLocalizacionActual;
	
	public static void main(String[] args) {
		//Cargar registros
		System.out.println("Bienvenido al directorio de emergencias por colicsiones de la ciudad de Londres");
		System.out.println("Espere un momento mientras cargamos la información...");
		System.out.println("Esto puede tardar unos minutos...");
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(rutaEntrada)));
			String entrada = br.readLine();

			//TODO: Inicialice el directorio t
			tablaNumero = new TablaHash<String, Ciudadano>();
			tablaNombre = new TablaHash<String, Ciudadano>();
			tablaLocalizacionActual = new TablaHash<String, Ciudadano>();
			
			int i = 0;
			entrada = br.readLine();
			long tiempo = System.nanoTime();
			while (entrada != null){
				String[] datos = entrada.split(",");
				Ciudadano nuevo = new Ciudadano(datos[0], datos[1], datos[3]+","+datos[4], datos[2]);
				tablaNumero.put(datos[2], nuevo);
				tablaNombre.put(datos[0], nuevo);
				tablaLocalizacionActual.put(datos[3]+","+datos[4], nuevo);

				++i;
				if (i%500000 == 0)
					System.out.println(i+" entradas...");

				entrada = br.readLine();
			}
			tiempo = System.nanoTime()-tiempo;
			System.out.println(i+" entradas cargadas en total");
			System.out.println("Tiempo de carga en nanosegundos: " + tiempo);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Directorio de emergencias por colisiones de Londres v1.0");

		boolean seguir = true;

		while (seguir)
			try {
				System.out.println("Bienvenido, seleccione alguna opcion del menú a continuación:");
				System.out.println("1: Agregar ciudadano a la lista de emergencia");
				System.out.println("2: Buscar ciudadano por número de contacto del acudiante");
				System.out.println("3: Buscar ciudadano por apellido");
				System.out.println("4: Buscar ciudadano por su localización actual");
				System.out.println("Exit: Salir de la aplicación");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String in = br.readLine();
				switch (in) {
				case "1":
					if (agregarCiudadano()){
						System.out.println("Se ha agregado el ciudadano a la lista de emergencia.");
					}
					break;
				case "2":
					if (!buscarCiudadanoPorNumero()){
						System.out.println("No se encontró un ciudadano con el nñumero dado.");
					}
					break;
				case "3":
					if(!buscarCiudadanoPorNombre())
                        System.out.println("No se encontró el ciudadano.");
					break;
				case "4":
					if(!buscarHabitantePorLocalizacionActual())
                        System.out.println("No se encontró el ciudadano.");
					break;
				case "Exit":
					System.out.println("Cerrando directorio...");
					seguir = false;
					break;

				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static boolean agregarCiudadano()
    {
        System.out.println("Ingrese el nombre");
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String nombre = br.readLine();
 
            System.out.println("Ingrese el apellido: ");
            String apellido = br.readLine();
            System.out.println("Ingrese el número de celular: ");
            String celular = br.readLine();
            System.out.println("Ingrese la longitud y latitud separadas por una coma: ");
            String local = br.readLine();
            long tiempo = System.nanoTime();
            Ciudadano nuevo = new Ciudadano(nombre, apellido, local, celular);
            tablaNumero.put(celular, nuevo);
            System.out.println("Tiempo tomado para agregar al ciudadano (en nanosegundos): " + (System.nanoTime()-tiempo));
            return true;
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
	
	public static boolean buscarCiudadanoPorNumero()
    {
        System.out.println("Ingrese el número de celular a buscar.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String numero = br.readLine();
            long tiempo = System.nanoTime();
            Ciudadano buscado = tablaNumero.get(numero);
            if(buscado!=null)
                System.out.printf(" Nombre: %s.\n Apellido: %s.\n Número de celular: %s. \n Localización actual: %s.\n",buscado.darNombre(),buscado.darApellido(),buscado.darNumero(),buscado.darUbicacion());
            else
                return false;
            System.out.println("Tiempo tomado para buscar un ciudadano (en nanosegundos): " + (System.nanoTime()-tiempo));
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

	public static boolean buscarCiudadanoPorNombre()
    {
        System.out.println("Ingrese el nombre del ciudadano buscado:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String numero = br.readLine();
            long tiempo = System.nanoTime();
            Ciudadano buscado = tablaNombre.get(numero);
            if(buscado!=null)
                System.out.printf(" Nombre: %s.\n Apellido: %s.\n Número de celular: %s. \n Localización: %s.\n",buscado.darNombre(),buscado.darApellido(),buscado.darNumero(),buscado.darUbicacion());
            else
                return false;
            System.out.println("Tiempo tomado para buscar el ciudadano (en nanosegundos): " + (System.nanoTime()-tiempo));
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
 
    public static boolean buscarHabitantePorLocalizacionActual()
    {
        System.out.println("Ingrese la localización del ciudadano buscado:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String numero = br.readLine();
            long tiempo = System.nanoTime();
            Ciudadano buscado = tablaLocalizacionActual.get(numero);
            if(buscado!=null)
                System.out.printf(" Nombre: %s.\n Apellido: %s.\n Número de celular: %s. \n Localización: %s.\n",buscado.darNombre(),buscado.darApellido(),buscado.darNumero(),buscado.darUbicacion());
            else
                return false;
            System.out.println("Tiempo tomado para buscar un ciudadano (en nanosegundos): " + (System.nanoTime()-tiempo));
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
	
}
