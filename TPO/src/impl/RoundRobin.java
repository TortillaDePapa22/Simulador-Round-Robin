package impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import api.AdministradorDeColasTDA;



public class RoundRobin extends TimerTask{

	@SuppressWarnings("unused")
	private AdministradorDeColasTDA adminRR;
	private Timer temporizador;



	
	public RoundRobin(AdministradorDeColasTDA admin) {
		// TODO Auto-generated constructor stub
		this.adminRR = admin;
	}

	public void setTemporizador(Timer temporizador) {
		this.temporizador = temporizador;
	}


/*	public static Proceso llamarSiguiente() { 
		Proceso process = new Proceso();
		process.setPID(AdministradorDeColas.Robin.primero());
		process.setTiempoEst(AdministradorDeColas.Robin.prioridad());
		AdministradorDeColas.Robin.desacolar();
		return process;
	}
	*/
	
	
	public String horaEjecucion() {
		Date horaEje = new Date();
		SimpleDateFormat ft = new SimpleDateFormat (" HH:mm:ss ");
		return ft.format(horaEje);
		
	}
	
	int conta=0;
	
	

	@Override
	public void run() {
		int quantum =  AdministradorDeColas.quantum;
		int cantidadServers = AdministradorDeColas.copCantidad;
		
		int i=0; 
		
		try {
			while (i < cantidadServers && AdministradorDeColas.vectSer[i].idProcesoEnServer == -1) { //Cargo los primeros procesos en los servidores
				AdministradorDeColas.vectSer[i].idProcesoEnServer = AdministradorDeColas.Robin.primero();
				AdministradorDeColas.vectSer[i].tiempoLiberacion = AdministradorDeColas.Robin.prioridad();
				AdministradorDeColas.Robin.desacolar();
				i++;
			}
			if(AdministradorDeColas.Robin.colaVacia()) { // Si la cola esta vacia, termino el proceso.
				Date fechaFin = new Date(); 
				SimpleDateFormat ft = new SimpleDateFormat ("E dd.MM.yyyy 'a las' HH:mm:ss ");
				System.out.println("\n \nRound Robin terminado, no hay mas elementos en la cola.\nFinalizado el: " + ft.format(fechaFin));
				this.temporizador.cancel();
				
				return;
			}
	
	
			if(conta == quantum) {//cuando llega al quantum, vuelo todos los procesos
				i = 0;
				System.out.println("\n------------- QUANTUM ALCANZADO! -------------\n");
				while (i < cantidadServers && !AdministradorDeColas.Robin.colaVacia()) {
					if(AdministradorDeColas.vectSer[i].tiempoLiberacion != 0) {
						AdministradorDeColas.Robin.acolarPrioridad(AdministradorDeColas.vectSer[i].idProcesoEnServer, AdministradorDeColas.vectSer[i].tiempoLiberacion);
					}
					AdministradorDeColas.vectSer[i].idProcesoEnServer = AdministradorDeColas.Robin.primero();
					AdministradorDeColas.vectSer[i].tiempoLiberacion = AdministradorDeColas.Robin.prioridad();
					AdministradorDeColas.Robin.desacolar();
					
					i++;
				}
				conta=0;
			}
			conta++;
			
			

			i = 0;
			while(i < cantidadServers && !AdministradorDeColas.Robin.colaVacia()) { //Aca disminuyo en 1 por cada vuelta que hace.
				AdministradorDeColas.vectSer[i].tiempoLiberacion--;					//Si a algun proceso en algun servidor no le resta tiempo de procesamiento,
				if(AdministradorDeColas.vectSer[i].tiempoLiberacion == 0) {			//Entra el siguiente proceso en la cola.
					AdministradorDeColas.vectSer[i].idProcesoEnServer = AdministradorDeColas.Robin.primero();
					AdministradorDeColas.vectSer[i].tiempoLiberacion = AdministradorDeColas.Robin.prioridad();
					AdministradorDeColas.Robin.desacolar();
				}
				i++;
			}
			

			int x = 0;
			while(x< cantidadServers) {
				String strID = String.format("%04d", AdministradorDeColas.vectSer[x].idProcesoEnServer); //agrego 000
				System.out.println("El servidor " + AdministradorDeColas.vectSer[x].idServer + " tiene el proceso " + strID + " y se libera en: " + AdministradorDeColas.vectSer[x].tiempoLiberacion + " segundos. Hora ejecución: " + horaEjecucion());
				x++;
			}
			System.out.println("----------------------------------------------");
			
			
			Thread.sleep(1000);
			
			
		}catch (InterruptedException ex) {
		System.out.println("Error de interrupcion");
		}
		
	}

	
	
	
}
