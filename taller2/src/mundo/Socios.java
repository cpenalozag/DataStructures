package mundo;

import java.util.Date;

public class Socios extends Trabajo {

	protected String empresaCliente;

	public Socios(Date pFecha, TipoEvento pTipo, String pLugar, boolean pObligatorio, boolean pFormal, String pEmpresaCliente) {
		super(pFecha, pTipo, pLugar, pObligatorio, pFormal);
		empresaCliente = pEmpresaCliente;
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
	  		+"\nEMPRESA CLIENTE: " + this.empresaCliente
	  		+"\nTIPO EVENTO: " + this.tipoEvento
	  		+"\nLUGAR: " + this.lugar
	  		+"\nOBLIGATORIO: " + conv(this.obligatorio)
	  		+"\nFORMAL: "+ conv(this.formal);
	}

}
