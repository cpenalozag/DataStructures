package mundo;

import java.util.Date;

public class Trabajo extends Evento {

	protected TipoEvento tipoEvento;

	public enum TipoEvento
	{
		JUNTAS,
		ALMUERZOS,
		CENAS,
		COCTELES,
		PRESENTACIONES;
	}

	public Trabajo(Date pFecha, TipoEvento pTipo, String pLugar, boolean pObligatorio, boolean pFormal) {
		super(pFecha, pLugar, pObligatorio, pFormal);
		tipoEvento = pTipo;
	}

}