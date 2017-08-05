package mundo;

import java.util.Date;

public class Evento {

	public Date fecha;
	public String lugar;
	public boolean obligatorio;
	public boolean formal;
	
	
	public Evento(Date pFecha, String pLugar, boolean pObligatorio, boolean pFormal)
	{
		fecha = pFecha;
		lugar = pLugar;
		obligatorio = pObligatorio;
		formal= pFormal;	
	}
	
	public boolean creado ()
	{
		if (fecha != null && lugar != null)
			return true;
		return false;
	}

}
