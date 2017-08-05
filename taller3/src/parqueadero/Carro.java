package parqueadero;


import java.util.Date;

public class Carro 
{
   /**
    * Color del vehiculo
    */
   private String color;
   /**
    * Matricula del vehiculo
    */
   private String matricula;
   /**
    * nombre del dueño del vehiculo
    */
   private String nombreConductor;
   /**
    * Registro ingreso del vehiculos
    */
   private Date diaHora = new Date ();
   /**
    * Registro ingreso en milisegundos
    */
   private long diaHoraMillis;
   /**
    * Crea el registro de un nuevo vehiculo al llegar al parqueadero
    * @param pColor color del vehiculo
    * @param pMatricula matricula del vehiculo
    * @param pNombreConductor nombre de quien conduce el vehiculos
    */
   
   public Carro (String pColor, String pMatricula, String pNombreConductor) 
  {
	 color = pColor;
	 matricula = pMatricula;
	 nombreConductor = pNombreConductor;
   }
   
   /**
    * Da el color del vehiculo
    * @return el color del vehiculo
    */
   public String darColor()
   {
	   return color;
   }
   /**
    * Da la matricula del vehiculo
    * @return la matricula del vehiculo
    */
   public String darMatricula()
   {
	   return matricula;
   }
   /**
    * Da el nombre de quien conduce el vehiculo
    * @return el nombre de quien conduce el vehiculo
    */
   public String darNombreConductor ()
   {
	   return nombreConductor;
   }
   /**
    * Devuelve en milisegundos la hora de llegada del vehiculo en el momento de su registro
    * @return la hora de registro del vehiculo en milisegundos
    */
   public long darLlegada()
   {
	   diaHoraMillis = diaHora.getTime();
	   return diaHoraMillis;
   }	
}
