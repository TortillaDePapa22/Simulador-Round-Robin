package impl;

import java.util.Timer;
import java.util.TimerTask;

import api.AdministradorDeColasTDA;
import apis.ColaPrioridadTDA;
import apis.ColaTDA;
import apis.DiccionarioSimpleTDA;

public class AdministradorDeColas implements AdministradorDeColasTDA{
	

	Server[] vectSer;
	
	
	ColaPrioridadTDA Robin = new ColaPrioridadDA(); // aca es donde va la cola de procesos
	DiccionarioSimpleTDA RobinDic = new DicSimpleL(); 
	Proceso process;
	static int copCantidad;
	static int quantum;
	int contadoridProceso = 0, sumTiempos = 0, copIndice = 0;



	
	
	
	
	
	@Override
	public void inicializar(int cantidad) { //Inicializa sum de tiempo de espera de los puestos
		// TODO Auto-generated method stub

		Robin.inicializarCola();
		RobinDic.inicializarDiccionario();
		copCantidad = cantidad;
		
		vectSer = new Server[10]; //Maximo 10 servers.
		
		
		for(int i = 0; i<cantidad; i++) {
			vectSer[i] = new Server();
			vectSer[i].nombreServer = "Server " + i;
			vectSer[i].idServer = i;
			vectSer[i].tiempoLiberacion = 0;
			vectSer[i].idProcesoEnServer = -1;
			System.out.println("Servidor " + i + " creado. " + (i+1) + " de " + cantidad + " servidores creados.");
		}
		
	}
	

	@Override
	public void acolar(int idElemento, int TiempoEstimado) {
		// TODO Auto-generated method stub
		boolean existeEnRobin = false;
		ColaPrioridadTDA aux = new ColaPrioridadDA(); 
		aux.inicializarCola();
		copiar(Robin,aux);
		
//CHEQUEAR CHEQUEO, COMPARA NOMBRE CON PID!!!!!!!!!!!!!!!!!!!!!!!!!
		//chequeo si elemento existe en cola Robin
		while(!aux.colaVacia() && existeEnRobin == false) {
			if(idElemento != aux.primero())
				aux.desacolar();
			else {
				existeEnRobin = true;
				System.out.println("El elemento " + idElemento + " no se ha agregado, ya existe en la cola.");
			}
		}
		if(existeEnRobin == false) {
			process = new Proceso();
			process.setNombre(idElemento);
			process.setTiempoEst(TiempoEstimado);
			process.setPID(contadoridProceso+1);

			RobinDic.agregar(process.getNombre(), process.getPID());
			Robin.acolarPrioridad(process.getPID(), process.getTiempoEst()); //Acolo en ROBIN
			contadoridProceso++; //actualizo numeración ID
			String strID = String.format("%04d", contadoridProceso); //agrego 000
			copIndice++; //actualizo copia indice
			sumTiempos = sumTiempos + TiempoEstimado; //actualizo tiempo
			System.out.println("Proceso numero: " + strID + " acolado");
		
		}
	}

	@Override
	public void desacolar() {
		int estimadoProcesoDesacolar;
		estimadoProcesoDesacolar = Robin.prioridad();
		sumTiempos = sumTiempos - estimadoProcesoDesacolar;
		copIndice--;
		this.Robin.desacolar();
		
		String strID = String.format("%04d", contadoridProceso); //agrego 000
		System.out.println("Proceso numero: " + strID + " desacolado");
		
	}

	/*
	Programación: devuelve una cola prioridad, donde la prioridad es el tiempo estimado de atención de los elementos a partir de la apertura de los puestos de atención. 
	Se supone que todos los puestos abren al mismo tiempo. El TDA debe estar inicializado.
	NOTA: En esta cola prioridad, la prioridad mayor es la del elemento de menor tiempo estimado de atención.
	Es decir, el elemento con tiempo estimado de atención 0’ va primero que el de tiempo de atención 30’.
	 */
	@Override
	public ColaPrioridadTDA programacion() {
		// TODO Auto-generated method stub
		
		return Robin;
	}

	@Override
	public int cantColas() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int primero() {
		// TODO Auto-generated method stub

		return this.Robin.primero();
	}

	
	@Override
	public int estimado() {
		// TODO Auto-generated method stub
		quantum = sumTiempos / copIndice;
		return quantum;
	}


	//Devuelve id del puesto del proximo elemento a ser atendido
	@Override
	public int puestoProximoElem(int idElemento) {
		
		int proximo, indice = 0; //Indice me indica la posicion en la que se encuentra
		int id;
		ColaPrioridadTDA auxRobin = new ColaPrioridadDA();
		auxRobin.inicializarCola();
		
		copiar(Robin, auxRobin);
		
		
		if(auxRobin.colaVacia()) {
			System.out.println("No hay proximo elemento. La cola está vacía.");
			return -1;
		}
		else {	
			id = RobinDic.recuperar(idElemento); //Recupero el processID
			proximo = auxRobin.primero();		
			while(id != proximo && !auxRobin.colaVacia()) {
				auxRobin.desacolar();
				proximo = auxRobin.primero();
				indice++;
			}
		}	
		
		
		if(auxRobin.colaVacia()) {
			System.out.println("El proceso con nombre " + idElemento + " no se encuentra en la cola.");
			return 0;
		}
		else {
			System.out.println("El proceso con nombre " + idElemento + " se encuentra en el puesto " + indice);
			return indice;
		}
	}
	
	
	//Dado un elemento devuelve el puesto de atención en el que se encuentra.

	@Override
	public int puestoDelElem(int idElemento) {
		// TODO Auto-generated method stub
		int id, cont=0; 
		
		id = RobinDic.recuperar(idElemento); //Recupero el processID
		
		while(cont < copCantidad) {
			if (id != vectSer[cont].idProcesoEnServer) 
				cont++;
			else 
				System.out.println("El proceso " + idElemento + " se encuentra en el  " + vectSer[cont].nombreServer);
		}
		
		if(cont==copCantidad) {
			System.out.println("El proceso " + idElemento + " no se encuentra en ningún servidor actualmente.");
			return -1;
		}
		return cont;
	}

	//Devuelve un diccionario con los elementos encolados y la demora estimada de atención.
	public DiccionarioSimpleTDA elementos() {
		// TODO Auto-generated method stub
		int clave, valor;
		ColaPrioridadTDA auxRobin = new ColaPrioridadDA();
		auxRobin.inicializarCola();
		DiccionarioSimpleTDA encolados = new DicSimpleL();
		encolados.inicializarDiccionario();
		
		copiar(Robin, auxRobin);
		
		if(auxRobin.colaVacia()) 
			System.out.println("No hay elementos encolados.");
		else {
			while(!auxRobin.colaVacia()) {
				clave = auxRobin.primero();
				valor = auxRobin.prioridad();
				encolados.agregar(clave, valor);
				auxRobin.desacolar();
			}
		}
		
		return encolados;
	}
	
	

		
		
		
	
	
	
//----------------------------------------------------------------------------------------------------
	
	//Copiar Cola Prioridad a Cola Prioridad
	public static void copiar(ColaPrioridadTDA c1, ColaPrioridadTDA c2) {
		ColaPrioridadTDA aux = new ColaPrioridadDA();
		aux.inicializarCola();
		pasar(c1, aux);
		while(!aux.colaVacia()) {
			c1.acolarPrioridad(aux.primero(), aux.prioridad());
			c2.acolarPrioridad(aux.primero(), aux.prioridad());
			aux.desacolar();
		}
	}
		
		//Pasar de ColaPrioridad a ColaPrioridad
	public static void pasar (ColaPrioridadTDA origen, ColaPrioridadTDA destino) {
		while(!origen.colaVacia()) {
			destino.acolarPrioridad(origen.primero(), origen.prioridad());
			origen.desacolar();
		}
	}

}
