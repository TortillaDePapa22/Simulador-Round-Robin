package impl;

import java.util.Timer;
import java.util.TimerTask;

import api.AdministradorDeColasTDA;
import apis.ColaPrioridadTDA;
import impl.AdministradorDeColas.Server;

public class RoundRobin extends TimerTask{

	private AdministradorDeColasTDA adminRR;
	private Timer temporizador;
	private ColaPrioridadTDA Robin;
	private Server[] servidor;
	
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int segundosEnServer =  AdministradorDeColas.quantum;
		servidor = new Server[10];
/*		try {
			
			while(!Robin.colaVacia()) {
				
				
			}
			
			
			if(Robin.colaVacia()){
				System.out.println("Finalizado el Round Robin");
				this.temporizador.cancel();
				return;
			}
			
			
			
			} catch (InterruptedException ex) {
			//System.out.println("Error de interrupción");
		}
	*/
	}
		
}
