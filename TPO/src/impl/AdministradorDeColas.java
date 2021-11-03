package impl;

import api.AdministradorDeColasTDA;
import apis.ColaPrioridadTDA;
import apis.ColaTDA;
import apis.DiccionarioSimpleTDA;

public class AdministradorDeColas implements AdministradorDeColasTDA {

	class Server{
		String nombreServer;
		int tiempoLiberacion;
	}
	
	
	ColaPrioridadTDA Robin = new ColaPrioridadDA(); // aca es donde va la cola de procesos
	Proceso process;
	int contadoridProceso = 0;
	int sumTiempos = 0, copIndice = 0;
	
	@Override
	public void inicializar(int cantidad) { //Inicializa sum de tiempo de espera de los puestos
		// TODO Auto-generated method stub

		Server ser;
		Robin.inicializarCola();
		
		for(int i=0; i<cantidad; i++) {
			ser = new Server();
			ser.nombreServer = "Server " + i;
			ser.tiempoLiberacion = 0;
			System.out.println("Servidor " + i + " creado. " + (i+1) + " de " + cantidad + " servidores creados.");
		}
		
	}
	

	@Override
	public void acolar(int idElemento, int TiempoEstimado) {
		// TODO Auto-generated method stub
			process = new Proceso();
			process.setNombre("Proceso " + idElemento);
			process.setTiempoEst(TiempoEstimado);
			process.setIdPro(contadoridProceso+1);
			Robin.acolarPrioridad(process.getIdPro(), process.getTiempoEst()); //Acolo en ROBIN
			contadoridProceso++; //actualizo numeración ID
			copIndice++; //actualizo copia indice
			sumTiempos = sumTiempos + TiempoEstimado; //actualizo tiempo
			
			
			

	}

	@Override
	public void desacolar() {
		// TODO Auto-generated method stub
		this.Robin.desacolar();
		copIndice--;
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
		ColaPrioridadTDA c = new ColaPrioridadDA();
		
		
		
		return c;
	}

	@Override
	public int cantColas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int primero() {
		// TODO Auto-generated method stub
		
		return this.Robin.primero();
	}

	@Override
	public int estimado() {
		// TODO Auto-generated method stub
		return (sumTiempos / copIndice);
	}


	//Devuelve id del puesto del proximo elemento a ser atendido
	@Override
	public int puestoProximoElem(int idElemento) {
		// TODO Auto-generated method stub
		if(Robin.colaVacia()) {
			return -1;
		}
		
		

		return 0;
	}
	
	//Dado un elemento devuelve el puesto de atención en el que se encuentra.

	@Override
	public int puestoDelElem(int idElemento) {
		// TODO Auto-generated method stub
		
		
		return 0;
	}

	@Override
	public DiccionarioSimpleTDA elementos() {
		// TODO Auto-generated method stub
		return null;
	}

}
