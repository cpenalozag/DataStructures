package taller.interfaz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import taller.interfaz.ArbolCLI.Node;


public class ArbolCLI implements IReconstructorArbol
{
	public class Node 
	{
		String dato;
		Node left, right;

		public String darDato(){
			return dato;
		}

		Node(String val) 
		{
			dato = val;
			left = right = null;
		}
	}

	private Scanner in;
	private String[] preorden;
	private String[] inorden;
	private Node root;
	private List<Node> nodos;
	private int indicePre;

	/**
	 * Constructor arbol binario simple
	 */
	public ArbolCLI()
	{
		in = new Scanner(System.in);
		nodos = new ArrayList<Node>();
		indicePre = 0;
	}

	/**
	 * Retorna la raiz del arbol binario
	 * @return root Raiz del arbol binario
	 */
	public Node darRaiz()
	{
		return root;
	}

	/**
	 * Retorna el arreglo de cadenas con el recorrido en preorden del arbol.
	 * @return preorden Arreglo con el recorrido preorden del arbol
	 */
	public String[] darPreorden(){
		return preorden;
	}

	/**
	 * Retorna el arreglo de cadenas con el recorrido en inorden del arbol.
	 * @return inorden Arreglo con el recorrido inorden del arbol
	 */
	public String[] darInorden()
	{
		return inorden;
	}

	/**
	 * Imprime el menu principal de la aplicación.
	 */
	public void mainMenu()
	{
		boolean finish = false;
		while(!finish)
		{	
			Screen.clear();
			System.out.println("------------------------------------------");
			System.out.println("-                                        -");
			System.out.println("-           Siembra de árboles           -");
			System.out.println("-                                        -");
			System.out.println("------------------------------------------");
			System.out.println("EL sistema para la plantación de árboles binarios\n");

			System.out.println("Menú principal:");
			System.out.println("-----------------");
			System.out.println("1. Cargar archivo con semillas");
			System.out.println("2. Probar metodo es subarbol");
			System.out.println("3. Salir");
			System.out.print("\nSeleccione una opción: ");
			try{
				int opt1 = Integer.parseInt(in.next());
				switch(opt1)
				{
				case 1:
					recibirArchivo();
					break;
				case 2:
					probarEsSubarbol();
					break;
				case 3:
					finish = true;
					break;
				default:
					break;
				}
			}
			catch(Exception e){
				System.out.println("Por favor elija una opcion valida.");

			}

		}
	}

	/**
	 * Recibe el archivo con los recorridos y comienza la ejecución del programa.
	 */
	public void recibirArchivo()
	{
		boolean finish = false;
		while(!finish)
		{
			Screen.clear();
			System.out.println("Recuerde que el archivo a cargar");
			System.out.println("debe ser un archivo properties");
			System.out.println("que tenga la propiedad in-orden,");
			System.out.println("la propiedad pre-orden (donde los ");
			System.out.println("elementos estén separados por comas) y");
			System.out.println("que esté guardado en la carpeta data.");
			System.out.println("");
			System.out.println("Introduzca el nombre del archivo:");
			System.out.println("----------------------------------------------------");

			// TODO Leer el archivo .properties 
			String ruta = in.next();
			try {
				cargarArchivo("./data/"+ruta);
				// TODO Reconstruir árbol 
				reconstruir();
				crearArchivo("arbolPlantado.json");

				System.out.println("Ha plantado el árbol con éxito!\nPara verlo, dirijase a /data/arbolPlantado.json");
				imprimir(this.root);
				System.out.println("Nota: ejecute Refresh (Clic derecho - Refresh) sobre la carpeta /data/ para actualizar y visualizar el archivo JSON");
				System.out.println("Presione 1 para salir");
				in.next();
				finish=true;

			} catch (Exception e) {
				System.out.println("Hubo un problema cargando el archivo:");
			}
		}
	}

	@Override
	public void cargarArchivo(String nombre) throws IOException {
		try{
			File f = new File(nombre);
			FileInputStream fileInput = new FileInputStream(f);

			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration<?> enumKeys = properties.keys();
			String key = (String) enumKeys.nextElement();
			String value = properties.getProperty(key);
			String[] partes = value.split(",");
			int tam = partes.length;
			inorden = new String[tam];
			int i=0;
			while (i<tam){
				inorden[i]=partes[i];
				i++;
			}

			key = (String) enumKeys.nextElement();
			value = properties.getProperty(key);
			partes = value.split(",");
			tam = partes.length;
			preorden = new String[tam];
			i=0;
			while (i<tam){
				preorden[i]=partes[i];
				i++;
			}
		}
		catch(Exception e){
		System.out.println("No existe un archivo con el nombre dado.");
		recibirArchivo();
		}
	}

	@Override
	public void crearArchivo(String info) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		File f = new File(("./data/"+info));
		if (!f.exists()){
			try {
				f.createNewFile();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		JSONObject obj = new JSONObject();
		obj = crearJSON(root, nodos);

		try (FileWriter file = new FileWriter(("./data/"+info))) {
			file.write(obj.toString());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Crea el archivo JSON con el arbol armado
	 * @param node Nodo que se va a agregar al archivo
	 * @param otros Lista de nodos del arbol
	 * @return json Objeto JSON con la información de cada nodo y finalmente del arbol completo.
	 */
	public JSONObject crearJSON(Node node, List<Node> otros) {
		JSONObject json = new JSONObject();
		json.put("Dato", node.dato);
		List<JSONObject> hijos = new ArrayList<JSONObject>();
		ArrayList<Node> aRemover = new ArrayList<Node>();
		otros.remove(aRemover);
		for (Node n: otros){
			if (esHijoDe(n, node) && n != null){
				aRemover.add(n);
				hijos.add(crearJSON(n, otros));
			}
		}
		json.put("Hijos", hijos);
		return json;
	}
	
	public void probarEsSubarbol(){
		boolean finish = false;
		while (!finish){
			ArbolCLI sub = new ArbolCLI();
			ArbolCLI arb = new ArbolCLI();
			ArbolCLI noSub = new ArbolCLI();
			try {
				noSub.cargarArchivo("./data/caso1.properties");
				noSub.reconstruir();
				sub.cargarArchivo("./data/subArbolNombre.properties");
				sub.reconstruir();
				arb.cargarArchivo("./data/ejemploNombre.properties");
				arb.reconstruir();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imprimir(sub.root);
			imprimir(arb.root);
			if (esSubarbol(arb.darRaiz(), sub.darRaiz())){
				System.out.println("Si es subarbol.");
			}
			else{
				System.out.println("No es subarbol.");
			}
			System.out.println("Presione 1 para salir");
			in.next();
			finish=true;
		}
	}
	
	/**
	 * Metodo que verifica si un subnodo es hijo de un nodo dado.
	 * @param sub Subnodo siendo revisado
	 * @param nodo Nodo que podría ser padre del subnodo
	 * @return true si es hijo, false de lo contrario
	 */
	public boolean esHijoDe (Node sub, Node nodo)
	{
		return nodo.left==sub||nodo.right==sub;
	}

	@Override
	public void reconstruir() {
		root = this.construirArbol(inorden, preorden, 0, preorden.length - 1);
		nodos.add(root);
	}

	/**
	 * Construye un arbol binario dados los recorridos en inorden y preorden utilizando un llamado recursivo.
	 * @param in Arreglo inorden
	 * @param pre Arreglo preorden
	 * @param ini Posicion inicial del posible indice en el arreglo inorden
	 * @param fin Posicion final del posible indice en el arreglo inorden
	 * @return nodo siendo creado, el ultimo nodo en ser retornado es la raiz
	 */
	private Node construirArbol(String in[], String pre[], int ini, int fin) 
	{

		if (ini > fin) 
			return null;

		//Elige el nodo actual del arreglo en preorder y aumenta indicePre

		if (indicePre>preorden.length-1)
			return null;
		Node nodo = new Node(pre[indicePre++]);

		//Si este nodo no tiene más hijos retorna
		if (ini == fin)
			return nodo;

		//De lo contrario, busca el indice del nodo en el arreglo inorden
		int inIndex = buscar(inorden, ini, fin, nodo.dato);
		//Usando el indice del arrego inorden, construye los subarboles izquierdo y derecho
		nodo.left = construirArbol(in, pre, ini, inIndex - 1);
		nodos.add(nodo.left);
		nodo.right = construirArbol(in, pre, inIndex + 1, fin);
		nodos.add(nodo.right);

		return nodo;
	}

	/**
	 * Busca el indice de un elemento en el arreglo.
	 * @param arr Arreglo donde se buscará
	 * @param ini Posicion inicial del arreglo en la cual se buscará
	 * @param fin Posicion final del arreglo en la cual se buscará
	 * @param val Valor buscado en el arreglo
	 * @return Indice del elemento en el arreglo o -1 en caso de que no se encuentre
	 */
	private int buscar(String arr[], int ini, int fin, String val) 
	{
		int r = -1;
		for (int i = ini; i <= fin; i++) 
		{
			if (arr[i].equals(val)){
				r = i;
			}
		}
		return r;
	}

	/**
	 * Método que verifica si un arbol contiene un subarbol dado.
	 * @param T arbol en el cual se buscará al subarbol
	 * @param S arbol que se buscara dentro de T
	 * @return true si es subarbol, false de lo contrario
	 */
	public boolean esSubarbol(Node T, Node S) 
	{
		if (S == null) 
			return true;

		if (T == null)
			return false;

		if (sonIguales(T, S)) 
			return true;

		/* If the tree with root as current node doesn't match then
           try left and right subtrees one by one */
		return esSubarbol(T.left, S)
				|| esSubarbol(T.right, S);
	}

	private boolean sonIguales(Node raiz1, Node raiz2) 
	{
		if (raiz1 == null && raiz2 == null)
			return true;

		if (raiz1 == null || raiz2 == null)
			return false;

		return (raiz1.dato.equals(raiz2.dato)
				&& sonIguales(raiz1.left, raiz2.left)
				&& sonIguales(raiz1.right, raiz2.right));
	}
	
	/**
	 * Imprime el arbol binario.
	 * @param root Raiz del arbol a imprimir
	 */
	public static void imprimir(Node root) {
		int maxLevel = nivelMaximo(root);

		imprimirNodosInternos(Collections.singletonList(root), 1, maxLevel);
	}

	private static void imprimirNodosInternos(List<Node> nodes, int nivel, int nivelMaximo) {
		if (nodes.isEmpty() || sonNulosTodosLosElementos(nodes))
			return;

		int floor = nivelMaximo - nivel;
		int arcos = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int primerosEspacios = (int) Math.pow(2, (floor)) - 1;
		int entreEspacios = (int) Math.pow(2, (floor + 1)) - 1;

		imprimirEspacios(primerosEspacios);

		List<Node> nodosNuevos = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node != null) {
				System.out.print(node.dato);
				nodosNuevos.add(node.left);
				nodosNuevos.add(node.right);
			} else {
				nodosNuevos.add(null);
				nodosNuevos.add(null);
				System.out.print(" ");
			}

			imprimirEspacios(entreEspacios);
		}
		System.out.println("");

		for (int i = 1; i <= arcos; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				imprimirEspacios(primerosEspacios - i);
				if (nodes.get(j) == null) {
					imprimirEspacios(arcos + arcos + i + 1);
					continue;
				}

				if (nodes.get(j).left != null)
					System.out.print("/");
				else
					imprimirEspacios(1);

				imprimirEspacios(i + i - 1);

				if (nodes.get(j).right != null)
					System.out.print("\\");
				else
					imprimirEspacios(1);

				imprimirEspacios(arcos + arcos - i);
			}

			System.out.println("");
		}

		imprimirNodosInternos(nodosNuevos, nivel + 1, nivelMaximo);
	}

	private static void imprimirEspacios(int cuenta) {
		for (int i = 0; i < cuenta; i++)
			System.out.print(" ");
	}

	private static int nivelMaximo(Node nodo) {
		if (nodo == null)
			return 0;

		return Math.max(nivelMaximo(nodo.left), nivelMaximo(nodo.right)) + 1;
	}

	private static boolean sonNulosTodosLosElementos(List<Node> lista) {
		for (Object object : lista) {
			if (object != null)
				return false;
		}
		return true;
	} 
}