package parqueadero;

import java.util.Date;
import java.util.Iterator;

import estructuras.Cola;
import estructuras.Pila;


public class Central 
{
	/**
	 * Cola de carros en espera para ser estacionados
	 */
	Cola<Carro> cola;

	/**
	 * Pilas de carros parqueaderos 1, 2, 3 .... 8
	 */
	Pila<Carro> pila1;
	Pila<Carro> pila2;
	Pila<Carro> pila3;
	Pila<Carro> pila4;
	Pila<Carro> pila5;
	Pila<Carro> pila6;
	Pila<Carro> pila7;
	Pila<Carro> pila8;

	Pila<Carro>[] pilas = new Pila[8];

	/**
	 * Pila de carros parqueadero temporal:
	 * Aca se estacionan los carros temporalmente cuando se quiere
	 * sacar un carro de un parqueadero y no es posible sacarlo con un solo movimiento
	 */
	Pila<Carro> pilaTemporal;

	/**
	 * Inicializa el parqueadero: Los parqueaderos (1,2... 8) el estacionamiento temporal y la cola de carros que esperan para ser estacionados.
	 */
	public Central ()
	{
		cola = new Cola<Carro>();
		pila1 = new Pila<Carro>();
		pila2 = new Pila<Carro>();
		pila3 = new Pila<Carro>();
		pila4 = new Pila<Carro>();
		pila5 = new Pila<Carro>();
		pila6 = new Pila<Carro>();
		pila7 = new Pila<Carro>();
		pila8 = new Pila<Carro>();
		pilas[0]=pila1;
		pilas[1]=pila2;
		pilas[2]=pila3;
		pilas[3]=pila4;
		pilas[4]=pila5;
		pilas[5]=pila6;
		pilas[6]=pila7;
		pilas[7]=pila8;
		pilaTemporal = new Pila<Carro>();
	}

	/**
	 * Imprime una representación gráfica de la cola de carros esperando ser parqueados.
	 * @return cola de carros
	 */
	public void imprimirCola(){
		Iterator<Carro> i = cola.iterator();
		String representacion = "Cola carros en espera: ";
		while (i.hasNext()){
			representacion += i.next().darMatricula() + "  ";
		}
		System.out.println(representacion);
	}

	/**
	 * Retorna el arreglo de pilas de parqueaderos
	 * @return arreglo de pilas de parqueaderos
	 */
	public void imprimirPilas(){
		
		for (int i=0; i<7; i++)
		  {
			  Iterator<Carro> it = pilas[i].iterator();
			  String representacion = "Parqueadero " + (i+1) + ": ";
			  while (it.hasNext()){
				  representacion += it.next().darMatricula() + "  ";
			  }
			  System.out.println(representacion);
		  }
	}

	/**
	 * Registra un cliente que quiere ingresar al parqueadero y el vehiculo ingresa a la cola de carros pendientes por parquear
	 * @param pColor color del vehiculo
	 * @param pMatricula matricula del vehiculo
	 * @param pNombreConductor nombre de quien conduce el vehiculo
	 */
	public void registrarCliente (String pColor, String pMatricula, String pNombreConductor)
	{
		Carro nuevo = new Carro(pColor, pMatricula, pNombreConductor);
		cola.enqueue(nuevo);
	}    

	/**
	 * Parquea el siguiente carro en la cola de carros por parquear
	 * @return matricula del vehiculo parqueado y ubicaci�n
	 * @throws Exception
	 */
	public String parquearCarroEnCola() throws Exception
	{
		Carro enCola = cola.dequeue();
		String nParqueadero = parquearCarro(enCola);
		return "Se ha parqueado el carro con matricula: " + enCola.darMatricula()+" en el parqueadero " + nParqueadero + ".";
	} 
	/**
	 * Saca del parqueadero el vehiculo de un cliente
	 * @param matricula del carro que se quiere sacar
	 * @return El monto de dinero que el cliente debe pagar
	 * @throws Exception si no encuentra el carro
	 */
	public double atenderClienteSale (String matricula) throws Exception
	{
		Carro sale = sacarCarro(matricula);
		Double tarifa = cobrarTarifa(sale);
		return tarifa;
	}

	/**
	 * Busca un parqueadero con cupo dentro de los 3 existentes y parquea el carro
	 * @param aParquear es el carro que se saca de la cola de carros que estan esperando para ser parqueados
	 * @return El parqueadero en el que qued� el carro
	 * @throws Exception
	 */
	public String parquearCarro(Carro aParquear) throws Exception
	{
		String resp = ""+-1;
		boolean parqueo = false;
		for (int i=0; i<7 && !parqueo; i++){
			if (pilas[i].size()<4){
				pilas[i].push(aParquear);
				parqueo = true;
				resp = ""+(i+1);
			}
		}
		return resp;
	}

	/**
	 * Itera sobre los tres parqueaderos buscando uno con la placa ingresada
	 * @param matricula del vehiculo que se quiere sacar
	 * @return el carro buscado
	 */
	public Carro sacarCarro (String matricula)
	{
		int numPila = -1;
		boolean encontro = false;
		for (int i=0; i<7 && !encontro; i++)
		{
			Iterator<Carro> it = pilas[i].iterator();
			while (it.hasNext()){
				Carro actual = it.next();
				if (actual.darMatricula().equals(matricula))
				{
					encontro=true;
					numPila=i+1;
				}
			}
		}
		Carro respuesta = null;
		switch(numPila){
		case 1:
			respuesta = sacarCarroP1(matricula);
			break;
		case 2:
			respuesta = sacarCarroP2(matricula);
			break;
		case 3:
			respuesta = sacarCarroP3(matricula);
			break;
		case 4:
			respuesta = sacarCarroP4(matricula);
			break;
		case 5:
			respuesta = sacarCarroP5(matricula);
			break;
		case 6:
			respuesta = sacarCarroP6(matricula);
			break;
		case 7:
			respuesta = sacarCarroP7(matricula);
			break;
		case 8:
			respuesta = sacarCarroP8(matricula);
			break;

		}
		return respuesta;
	}

	/**
	 * Saca un carro del parqueadero 1 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP1 (String matricula)
	{
		Carro sacar = null;
		boolean saco = false;
		while (!pilas[0].isEmpty() &&! saco){
			Carro movido;
			if (!pilas[0].peek().darMatricula().equals(matricula)){
				movido = pilas[0].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[0].pop();
				saco=true;
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[0].push(movido);
					System.out.println("Trato de meterlos");
				}
			}
		}
		return sacar;
	}

	/**
	 * Saca un carro del parqueadero 2 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP2 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[1].isEmpty()){
			Carro movido;
			if (!pilas[1].peek().darMatricula().equals(matricula)){
				movido = pilas[1].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[1].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[1].push(movido);
				}
			}
		}
		return sacar;
	}

	/**
	 * Saca un carro del parqueadero 3 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP3 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[2].isEmpty()){
			Carro movido;
			if (!pilas[2].peek().darMatricula().equals(matricula)){
				movido = pilas[2].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[2].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[2].push(movido);
				}
			}
		}
		return sacar;
	}
	/**
	 * Saca un carro del parqueadero 4 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP4 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[3].isEmpty()){
			Carro movido;
			if (!pilas[3].peek().darMatricula().equals(matricula)){
				movido = pilas[3].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[3].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[3].push(movido);
				}
			}
		}
		return sacar;
	}
	/**
	 * Saca un carro del parqueadero 5 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP5 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[4].isEmpty()){
			Carro movido;
			if (!pilas[4].peek().darMatricula().equals(matricula)){
				movido = pilas[4].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[4].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[4].push(movido);
				}
			}
		}
		return sacar;
	}
	/**
	 * Saca un carro del parqueadero 6 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP6 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[5].isEmpty()){
			Carro movido;
			if (!pilas[5].peek().darMatricula().equals(matricula)){
				movido = pilas[5].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[5].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[5].push(movido);
				}
			}
		}
		return sacar;
	}
	/**
	 * Saca un carro del parqueadero 7 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP7 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[6].isEmpty()){
			Carro movido;
			if (!pilas[6].peek().darMatricula().equals(matricula)){
				movido = pilas[6].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[6].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[6].push(movido);
				}
			}
		}
		return sacar;
	}
	/**
	 * Saca un carro del parqueadero 8 dada su matricula
	 * @param matricula del vehiculo buscado
	 * @return carro buscado
	 */
	public Carro sacarCarroP8 (String matricula)
	{
		Carro sacar = null;
		while (!pilas[7].isEmpty()){
			Carro movido;
			if (!pilas[7].peek().darMatricula().equals(matricula)){
				movido = pilas[7].pop();
				pilaTemporal.push(movido);
			}
			else{
				sacar = pilas[7].pop();
				while (!pilaTemporal.isEmpty())
				{
					movido = pilaTemporal.pop();
					pilas[7].push(movido);
				}
			}
		}
		return sacar;
	}
	/**
	 * Calcula el valor que debe ser cobrado al cliente en funci�n del tiempo que dur� un carro en el parqueadero
	 * la tarifa es de $25 por minuto
	 * @param car recibe como parametro el carro que sale del parqueadero
	 * @return el valor que debe ser cobrado al cliente 
	 */
	public double cobrarTarifa (Carro car)
	{
		Double pago = 0.0;
		Date fechaActual = new Date();
		long tiempo=(fechaActual.getTime()-car.darLlegada());
		pago = (double) (((tiempo/1000)/60)*25);
		return pago;	
	}
}
