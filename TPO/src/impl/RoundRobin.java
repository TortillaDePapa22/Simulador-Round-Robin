package impl;

import java.text.SimpleDateFormat;
import java.time.Duration;
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
	
	public String horaEjecucion() {
		Date horaEje = new Date();
		SimpleDateFormat ft = new SimpleDateFormat (" HH:mm:ss ");
		return ft.format(horaEje);
		
	}
	
	public static String horaIni;
	public static String horaFin;
	public static String totalOperacion;
	
	int conta=0;
	boolean primeraVez = false;
	int cantServersFinalizados = 0;

	
	
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
			i=0;
				if(AdministradorDeColas.Robin.colaVacia() && primeraVez == false) { // Si la cola esta vacia, termino el proceso.
					Date fechaFin = new Date(); 
					SimpleDateFormat ft = new SimpleDateFormat ("E dd.MM.yyyy 'a las' HH:mm:ss ");
					System.out.println("\n \nNo hay mas elementos en la cola. Finalizado el: " + ft.format(fechaFin));
					System.out.println(" ");
					primeraVez = true;
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
			while(i < cantidadServers ) { //Aca disminuyo en 1 por cada vuelta que hace.
				if(AdministradorDeColas.vectSer[i].idProcesoEnServer >0) 
					AdministradorDeColas.vectSer[i].tiempoLiberacion--;					//Si a algun proceso en algun servidor no le resta tiempo de procesamiento,
				if(AdministradorDeColas.vectSer[i].tiempoLiberacion == 0) {			//Entra el siguiente proceso en la cola.
					if(!AdministradorDeColas.Robin.colaVacia()) {
						AdministradorDeColas.vectSer[i].idProcesoEnServer = AdministradorDeColas.Robin.primero();
						AdministradorDeColas.vectSer[i].tiempoLiberacion = AdministradorDeColas.Robin.prioridad();
						AdministradorDeColas.Robin.desacolar();
					}
					else {
						AdministradorDeColas.vectSer[i].idProcesoEnServer = 0;
						AdministradorDeColas.vectSer[i].tiempoLiberacion = 0;
					}
				}
				i++;
			}
			
			

			int x = 0;
			int conti=0; // este contador confirma si todos los servidores estan en 0.
			System.out.println("----------------------------------------------");
			while(x< cantidadServers) {

				if(AdministradorDeColas.vectSer[x].idProcesoEnServer == 0) {
					System.out.println("El servidor " + AdministradorDeColas.vectSer[x].idServer + " finalizó operaciones. ");
					x++;
					conti++;
					if(conti==cantidadServers) {
						Date fechaFin = new Date(); 
						horaFin = horaEjecucion();
						SimpleDateFormat ft = new SimpleDateFormat ("E dd.MM.yyyy 'a las' HH:mm:ss ");
						System.out.println("\nROUND ROBIN FINALIZADO! NINGUN SERVIDOR TIENE PROCESOS PENDIENTES.\n \nFinalizado el: " + ft.format(fechaFin));
						System.out.println("Hora inicio Simulador: "+ horaIni + "\nHora finalización Simulador: " + horaFin);
						this.temporizador.cancel();
						return;
					}
				}
				else{
					String strID = String.format("%04d", AdministradorDeColas.vectSer[x].idProcesoEnServer); //agrego 000
					System.out.println("El servidor " + AdministradorDeColas.vectSer[x].idServer + " tiene el proceso " + strID + " y se libera en: " + AdministradorDeColas.vectSer[x].tiempoLiberacion + " segundos. Hora ejecución: " + horaEjecucion());
					x++;
				}
				
			}
			System.out.println("----------------------------------------------");
			
			
			Thread.sleep(1000);
			
			
		}catch (InterruptedException ex) {
		System.out.println("Error de interrupcion");
		}

		
	}

	
	
	
}
