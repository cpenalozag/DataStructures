package mundo;

import java.util.Date;

public class Familia extends Ocio
{
	public enum Miembros
	{
		ESPOSA,
		HIJOS,
		ESPOSA_E_HIJOS,
		OTROS
	}
    
	protected Miembros miembros;
	
	public Familia(Date pFecha, String pLugar, TipoEventoOcio pTipo, boolean pObligatorio, boolean pFormal, Miembros pMiembros) 
	{
		super(pFecha, pLugar, pTipo, pObligatorio, pFormal);
		miembros = pMiembros;
	}

	public String conv (Boolean b)
	{
		if (!b) return "No";
		else
			return "Si";
	}
	
	public String toString()
	{
	  return "Evento con FAMILIA: \n"
	  		+"FECHA: " + this.fecha
	  		+"\nLUGAR: " + this.lugar
	  		+"\nTIPO EVENTO: " + this.tipo
	  		+"\nOBLIGATORIO: " + conv(this.obligatorio)
	  		+"\nFORMAL: "+ conv(this.formal);
	}
}
