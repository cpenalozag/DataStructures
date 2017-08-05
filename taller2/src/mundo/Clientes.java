package mundo;

import java.util.Date;

public class Clientes extends Trabajo {

	protected String negocioCliente;

	public Clientes(Date pFecha, TipoEvento pTipo, String pLugar, boolean pObligatorio, boolean pFormal, String pNegocioCliente) {
		super(pFecha, pTipo, pLugar, pObligatorio, pFormal);
		negocioCliente = pNegocioCliente;
	}

	
	public String conv (Boolean b)
	{
		if (!b) return "No";
		else
			return "Si";
	}
	
	public String toString()
	{
	  return "Evento con CLIENTES: \n"
	  		+"FECHA: " + this.fecha
	  		+"\nNEGOCIO CLIENTE: " + this.negocioCliente
	  		+"\nTIPO EVENTO: " + this.tipoEvento
	  		+"\nLUGAR: " + this.lugar
	  		+"\nOBLIGATORIO: " + conv(this.obligatorio)
	  		+"\nFORMAL: "+ conv(this.formal);
	}
	
}
