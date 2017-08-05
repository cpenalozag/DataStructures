package interfaz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mundo.Agenda;
import mundo.Amigos;
import mundo.Amigos.De;
import mundo.Evento;
import mundo.Familia;
import mundo.Familia.Miembros;
import mundo.Ocio.TipoEventoOcio;
import mundo.Socios;
import mundo.Trabajo.TipoEvento;
public class Main {

	private BufferedReader br;
	private Agenda <Evento> eventos;
	private String pattern = "yyyy/MM/dd 'a las' hh:mm";

	/**
	 * Clase principal de la aplicación, incializa el mundo.
	 * @throws Exception
	 */
	public Main() throws Exception
	{

		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("** AGENDA PERSONAL **");
		System.out.println("Bienvenido a su agenda personal");
		eventos = new Agenda<Evento> ();
		menuInicial();
	}

	/**
	 * Menú principal de la aplicación
	 * @throws Exception
	 */
	public void menuInicial() throws Exception
	{
		String mensaje = "Menú principal: \n"
				+ "[1] Ver mis eventos \n"
				+ "[2] Agendar un evento \n"
				+ "[3] Editar un evento \n"
				+ "[4] Eliminar un evento "; 
		System.out.print(mensaje);

		try
		{
			int op1 = Integer.parseInt(br.readLine());
			if (op1>4 || op1<1)
			{
				System.out.println("\nERROR: Ingrese una opción valida\n");
				menuInicial();
			}

			switch(op1)
			{
			case 1: estadoEventos(); break;
			case 2: agregarEvento(); break;
			case 3: editarEvento(); break;
			case 4: eliminarEvento(); break;
			default:
				menuInicial();
			}
		}
		catch(NumberFormatException e)
		{
			menuInicial();
		}
		catch(Exception e)
		{
			System.out.println("Hubo un error en la aplicación");
			menuInicial();
		}
	}

	/**
	 * Menú que permite ver el estado de la agenda
	 * @throws Exception
	 */
	public void estadoEventos() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("\n<< Estado de la agenda:>>\n");

		int i = 0;
		try
		{
			while(eventos.eventos.get(i)!=null)
			{
				System.out.println("EVENTO # " + i);
				System.out.println(eventos.eventos.get(i).toString());
				System.out.println("");
				i++;
			}
		}
		catch (Exception e)
		{
			if(eventos.eventos.isEmpty())
				System.out.println("Aún no tiene eventos programados:");
			System.out.println("Presione intro para regresar");
			br.readLine();
			menuInicial();
		}


	}

	/**
	 * Menú que permite agregar un evento a la agenda
	 * @throws Exception
	 */
	public void agregarEvento() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Agregar evento >>\n");
		String mensaje =
				"[1] Con socios\n"
						+ "[2] Con clientes\n"
						+ "[3] Con familiares\n"
						+ "[4] Con amigos";

		System.out.print(mensaje);
		int op2 = Integer.parseInt(br.readLine());
		if (op2>4 || op2<1)
		{
			System.out.println("\nERROR: Ingrese una opción valida\n");
			agregarEvento();
		}
		switch(op2)
		{
		case 1: agregarEventoSocios(); break;
		case 2: agregarEventoClientes(); break;
		case 3: agregarEventoFamiliares(); break;
		case 4: agregarEventoAmigos(); break;
		}

	}

	/**
	 * Menú para agregar un evento con socios a la agenda
	 * @throws Exception
	 */
	public void agregarEventoSocios() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Agregar evento con socios >>\n");

		System.out.println("Ingrese la fecha de su evento. Use el siguiente formato: aaaa/mm/dd a las hh:mm");
		Date fecha = procesarFecha(br.readLine());

		System.out.println("Ingrese el lugar de su evento");		
		String lugar = br.readLine();

		System.out.println("¿Cómo se llama la empresa del socio? ") ;		
		String empresa = br.readLine();

		System.out.println("¿Tipo de evento? ") ;
		TipoEvento [] Teventos = TipoEvento.values();
		for(int i=0; i<Teventos.length; i++)
		{
			System.out.println("["+(i+1)+"] "+Teventos[i]);
		}
		int indice2 = Integer.parseInt(br.readLine());
		TipoEvento tipo = Teventos[indice2-1];

		System.out.println("¿Tipo de vestimenta?");
		System.out.println("[1] Formal");
		System.out.println("[2] Informal");
		boolean formal = false;		
		String op = br.readLine();
		switch(op)
		{
		case "1": formal = true; break;
		case "2": formal = false; break;
		}

		System.out.println("¿Asistencia obligatoria?");
		System.out.println("[1] SI");
		System.out.println("[2] NO");
		boolean obligatorio = false;
		String op1 = br.readLine();
		switch(op1)
		{
		case "1": obligatorio = true; break;
		case "2": obligatorio = false; break;
		}

		Evento socios = new Socios(fecha, tipo, lugar, obligatorio, formal, empresa);
		int respuesta = eventos.agendar(socios);
		System.out.println("El evento ha sido agendado con éxito. En su agenda es el evento # " + respuesta);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		menuInicial();
	}

	/**
	 * Menú para agregar un evento con clientes a laagenda
	 * @throws Exception
	 */
	public void agregarEventoClientes() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Agregar evento con clientes >>\n");

		System.out.println("Ingrese la fecha de su evento. Use el siguiente formato: aaaa/mm/dd a las hh:mm");
		Date fecha = procesarFecha(br.readLine());

		System.out.println("Ingrese el lugar de su evento");		
		String lugar = br.readLine();

		System.out.println("¿Cómo se llama el negocio del cliente? ") ;		
		String negocio = br.readLine();

		System.out.println("¿Tipo de evento? ") ;
		TipoEvento [] Teventos = TipoEvento.values();
		for(int i=0; i<Teventos.length; i++)
		{
			System.out.println("["+(i+1)+"] "+Teventos[i]);
		}
		int indice2 = Integer.parseInt(br.readLine());
		TipoEvento tipo = Teventos[indice2-1];

		System.out.println("¿Tipo de vestimenta?");
		System.out.println("[1] Formal");
		System.out.println("[2] Informal");
		boolean formal = false;		
		String op = br.readLine();
		switch(op)
		{
		case "1": formal = true; break;
		case "2": formal = false; break;
		}

		System.out.println("¿Asistencia obligatoria?");
		System.out.println("[1] SI");
		System.out.println("[2] NO");
		boolean obligatorio = false;
		String op1 = br.readLine();
		switch(op1)
		{
		case "1": obligatorio = true; break;
		case "2": obligatorio = false; break;
		}

		Evento clientes = new Socios(fecha, tipo, lugar, obligatorio, formal, negocio);
		int respuesta = eventos.agendar(clientes);
		System.out.println("El evento ha sido agendado con éxito. En su agenda es el evento # " + respuesta);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		menuInicial();
	}


	/**
	 * Menú para agregar un evento con familiares a la agenda 
	 * @throws Exception
	 */
	public void agregarEventoFamiliares() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Agregar evento con familiares >>\n");

		System.out.println("Ingrese la fecha de su evento. Use el siguiente formato: aaaa/mm/dd a las hh:mm");
		Date fecha = procesarFecha(br.readLine());

		System.out.println("Ingrese el lugar de su evento");		
		String lugar = br.readLine();

		System.out.println("¿Con quienes es el evento? ") ;		
		Miembros [] miembros = Miembros.values();
		for(int i=0; i<miembros.length; i++)
		{
			System.out.println("["+(i+1)+"] "+miembros[i]);
		}
		int indice = Integer.parseInt(br.readLine());
		Miembros flia = miembros[indice-1];

		System.out.println("¿Tipo de evento? ") ;
		TipoEventoOcio [] Teventos = TipoEventoOcio.values();
		for(int i=0; i<Teventos.length; i++)
		{
			System.out.println("["+(i+1)+"] "+Teventos[i]);
		}
		int indice2 = Integer.parseInt(br.readLine());
		TipoEventoOcio tipo = Teventos[indice2-1];

		System.out.println("¿Tipo de vestimenta?");
		System.out.println("[1] Formal");
		System.out.println("[2] Informal");
		boolean formal = false;		
		String op = br.readLine();
		switch(op)
		{
		case "1": formal = true; break;
		case "2": formal = false; break;
		}

		System.out.println("¿Asistencia obligatoria?");
		System.out.println("[1] SI");
		System.out.println("[2] NO");
		boolean obligatorio = false;
		String op1 = br.readLine();
		switch(op1)
		{
		case "1": obligatorio = true; break;
		case "2": obligatorio = false; break;
		}

		Evento familia = new Familia(fecha, lugar, tipo, obligatorio, formal, flia);
		int respuesta = eventos.agendar(familia);
		System.out.println("El evento ha sido agendado con éxito. En su agenda es el evento # " + respuesta);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		menuInicial();
	}

	/**
	 * Menú para agregar un evento con amigos a la agenda 
	 * @throws Exception
	 */
	public void agregarEventoAmigos() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Agregar evento con amigos >>\n");

		System.out.println("Ingrese la fecha de su evento. Use el siguiente formato: aaaa/mm/dd a las hh:mm");
		Date fecha = procesarFecha(br.readLine());

		System.out.println("Ingrese el lugar de su evento");		
		String lugar = br.readLine();

		System.out.println("Amigos de:") ;		
		De [] amigos = De.values();
		for(int i=0; i<amigos.length; i++)
		{
			System.out.println("["+(i+1)+"] "+amigos[i]);
		}
		int indice = Integer.parseInt(br.readLine());
		De de = amigos[indice-1];

		System.out.println("¿Tipo de evento? ") ;
		TipoEventoOcio [] Teventos = TipoEventoOcio.values();
		for(int i=0; i<Teventos.length; i++)
		{
			System.out.println("["+(i+1)+"] "+Teventos[i]);
		}
		int indice2 = Integer.parseInt(br.readLine());
		TipoEventoOcio tipo = Teventos[indice2-1];

		System.out.println("¿Tipo de vestimenta?");
		System.out.println("[1] Formal");
		System.out.println("[2] Informal");
		boolean formal = false;		
		String op = br.readLine();
		switch(op)
		{
		case "1": formal = true; break;
		case "2": formal = false; break;
		}

		System.out.println("¿Asistencia obligatoria?");
		System.out.println("[1] SI");
		System.out.println("[2] NO");
		boolean obligatorio = false;
		String op1 = br.readLine();
		switch(op1)
		{
		case "1": obligatorio = true; break;
		case "2": obligatorio = false; break;
		}

		Evento eamigos = new Amigos(fecha, lugar, tipo, obligatorio, formal, de);
		int respuesta = eventos.agendar(eamigos);
		System.out.println("El evento ha sido agendado con éxito. En su agenda es el evento # " + respuesta);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		menuInicial();
	}

	/**
	 * Menú para editar la fecha o lugar de un evento
	 * @throws Exception
	 */
	public void editarEvento() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Editar un evento >>\n");
		if (eventos.eventos.isEmpty())
		{
			System.out.println("Aún no tiene eventos programados");
			menuInicial();	    
		}

		System.out.println("¿qué evento desea editar?");
		int event = Integer.parseInt(br.readLine());
		try
		{
			if(event<=eventos.eventos.size())
			{
				System.out.println("¿Qué desea editar de su evento?:");
				System.out.println("[1] Fecha y/o hora");
				System.out.println("[2] lugar");
				String op = br.readLine();
				switch(op)
				{
				case "1": 
				{
					System.out.println("Ingrese la nueva fecha y hora de su evento. Use el siguiente formato: aaaa/mm/dd a las hh:mm");; 
					eventos.eventos.get(event).fecha = procesarFecha(br.readLine());
					estadoEventos();
					break;
				}
				case "2":
				{
					System.out.println("ingrese el nuevo lugar de su evento");
					eventos.eventos.get(event).lugar = br.readLine();
					estadoEventos();
					break;
				}				
				}
			}
			else
			{
				System.out.println("Operación invalida: aún no hay planificado un evento " + event);
				editarEvento();
			}
		}
		catch (Exception e)
		{
			System.out.println("Operación invalida: aún no hay planificado un evento " + event);
			editarEvento();
		}
	}
	/**
	 * Menú para eliminar un evento de la agenda
	 * @throws Exception
	 */
	public void eliminarEvento() throws Exception
	{
		System.out.println("* * * * * * * * * * * * * * * * ");
		System.out.println("<< Eliminar un evento >>\n");
		if (eventos.eventos.isEmpty())
		{
			System.out.println("Aún no tiene eventos programados");
		}
		else{
			System.out.println("Ingrese el número de evento que desea eliminar");
			int numEvent = Integer.parseInt(br.readLine());
			try
			{
				eventos.eliminarEvento(numEvent);
			}
			catch (Exception e)
			{
				System.out.println("No se ha definido un evento " + numEvent +". Intentelo de nuevo.");
				eliminarEvento();
			}
		}

		menuInicial();
	}

	/**
	 * Procesa los stringsy los vuelve Date
	 * @throws Exception
	 */

	public Date procesarFecha (String fecha) throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date date = format.parse(fecha);
			return date;
		} catch (ParseException e) 
		{
			System.out.println("Fecha invalida. Intentelo de nuevo");
			menuInicial();
		}
		return null;
	}
	/**
	 * Main...
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Main();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
