package taller.interfaz;

public class Ciudadano {
	private String nombre;
	private String apellido;
	private String ubicacion;
	private String numero;
	
	public Ciudadano(String pNombre, String pApellido, String pUbicacion, String pNumero){
		nombre=pNombre;
		apellido=pApellido;
		ubicacion=pUbicacion;
		numero=pNumero;
	}

	public String darNombre() {
		return nombre;
	}

	public String darApellido() {
		return apellido;
	}
	
	public String darUbicacion() {
		return ubicacion;
	}

	public String darNumero() {
		return numero;
	}

}
