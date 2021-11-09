package impl;

import java.util.Timer;
import java.util.TimerTask;

import api.AdministradorDeColasTDA;
import apis.ColaPrioridadTDA;

import test.TestTPO;

public class RoundRobin extends TimerTask{

	private AdministradorDeColasTDA adminRR;
	private Timer temporizador;
	private ColaPrioridadTDA Robin;
	private impl.Server[] servidor;

	
	public RoundRobin(AdministradorDeColasTDA admin) {
		// TODO Auto-generated constructor stub
		this.adminRR = admin;
	}

	public void setTemporizador(Timer temporizador) {
		this.temporizador = temporizador;
	}

	public void Robin(ColaPrioridadTDA Robin) {
		this.Robin = Robin;
	}
	
	public void Server(Server ser[]) {
		this.servidor = ser;
	}
	
	public Proceso llamarSiguiente() { 
		Proceso proceso 
		
		return null;
		
	}

	@Override
	public void run() {
		int segundosEnServer =  AdministradorDeColas.quantum;
		int cantidadServers = AdministradorDeColas.copCantidad;
		int i=0, nroServer=0;
		while (servidor[i].idProcesoEnServer == 0 && i < cantidadServers) {
			servidor[i].idProcesoEnServer = Robin.primero();
			servidor[i].tiempoLiberacion = Robin.prioridad();
			Robin.desacolar();
		}
		while(!Robin.colaVacia()) {
			if(servidor[nroServer].tiempoLiberacion == 0) {
				
			}
		

			Thread.sleep(segundosEnServer);
	
		}
			
			
		if(Robin.colaVacia()){
			System.out.println("Finalizado el Round Robin");
			this.temporizador.cancel();
			return;
		}
			
		
	
	}
		
}
