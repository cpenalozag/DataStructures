package mundo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Agenda<T> {

	public ArrayList <T> eventos;
	private BufferedReader br;
	private String pattern = "yyyy/MM/dd 'a las' hh:mm";

	public Agenda()
	{
		eventos = new ArrayList <T> ();
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public int agendar(T event)
	{
		eventos.add(event);
		return (eventos.size()-1);
	}

	public void eliminarEvento(int num)
	{
		if(eventos.size()>=num)
		{
			eventos.remove(num);
			System.out.println("El evento fue eliminado con éxito");
		}
		else
			System.out.println("Operación invalida: aún no hay planificado un evento " + num);
	}

	public Date procesarFecha (String fecha) throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date date = format.parse(fecha);
			return date;
		} catch (ParseException e) 
		{
			System.out.println("Fecha invalida. Intentelo de nuevo");
		}
		return null;
	}

}


