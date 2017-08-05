package mundo;

import java.util.Date;

public class Ocio extends Evento
{
	
	public enum  TipoEventoOcio {
		CUMPLEANOS,
		FECHA_IMPORTANTE_DEL_ANO,
		CENA,
		ALMUERZO,
		OTRA_ACTIVIDAD_DE_OCIO,
		FIESTA,
		GRADO,
		MATRIMONIO,
	}
	
	protected TipoEventoOcio tipo;
	
	public Ocio(Date pFecha, String pLugar, TipoEventoOcio pTipo, boolean pObligatorio, boolean pFormal) 
	{
		super(pFecha, pLugar, pObligatorio, pFormal);
		tipo = pTipo;  
	}


}
