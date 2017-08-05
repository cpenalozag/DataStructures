package mundo;

import java.util.Date;



public class Amigos extends Ocio{
	
	public enum De
	{
		UNIVERSIDAD,
		COLEGIO,
		TRABAJO,
		OTROS
	}
    
	protected De tipoAmigos;
	
	public Amigos(Date pFecha, String pLugar, TipoEventoOcio pTipo, boolean pObligatorio, boolean pFormal, De pTipoA) 
	{
		super(pFecha, pLugar, pTipo, pObligatorio, pFormal);
		tipoAmigos = pTipoA;
	}
	public String conv (Boolean b)
	{
		if (!b) return "No";
		else
			return "Si";
	}

		public String toString()
	{
			return "Evento con AMIGOS: \n"
			  		+"FECHA: " + this.fecha
			  		+"\nLUGAR: " + this.lugar
			  		+"\nTIPO EVENTO: " + this.tipo
			  		+"\nOBLIGATORIO: " + conv(this.obligatorio)
			  		+"\nFORMAL: "+ conv(this.formal);
	}

}
