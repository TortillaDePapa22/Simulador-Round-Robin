package test;

import java.util.Scanner;
import java.util.Timer;

import api.AdministradorDeColasTDA;
import impl.AdministradorDeColas;
import impl.ColaPrioridadDA;
import impl.RoundRobin;

public class TestTPO {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdministradorDeColasTDA admin = new AdministradorDeColas();
		RoundRobin adminRR = new RoundRobin(admin); //ACA SE VA A REALIZAR EL ROUND ROBIN
		
		int cantidadPuestos = 0;
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Ingrese la cantidad de servidores a utilizar: ");
		cantidadPuestos = sc.nextInt();
		
		admin.inicializar(cantidadPuestos); //Inicializo servers
		

	
		System.out.println("-------------------------------------------------");
		
		admin.acolar(97,4);//proceso 1
		admin.acolar(98,2);//proceso 2
		admin.acolar(99,3);//proceso 3
		admin.acolar(55,2);
		admin.acolar(84,7);
		admin.acolar(15,9);
		admin.acolar(95,1);
		admin.acolar(53,3);
		admin.acolar(39,4);
		admin.acolar(48,8);
		
		admin.desacolar();
		admin.acolar(100,1);
		admin.acolar(48,8);
		admin.acolar(42,8);
		admin.acolar(12,8);
		admin.acolar(7,8);
		admin.acolar(91,8);
		admin.acolar(106,5);
		System.out.println("\n \nQuantum: " + admin.estimado()); //quantum
	
		
		Timer temporizador = new Timer();

		adminRR.setTemporizador(temporizador);
		temporizador.scheduleAtFixedRate(adminRR, 0, 1000); //Comienza el proceso
		System.out.println("ROUND ROBIN COMENZADO... \nHora comienzo simulador: " + adminRR.horaEjecucion() + "\n");
		
		
		
	}
	
	
	
	
	
	
	
//---------------------------------------------------------------------------------
	//Imprimir Cola Prioridad
	public static void imprimir (apis.ColaPrioridadTDA c) {
		apis.ColaPrioridadTDA aux = new ColaPrioridadDA();
		aux.inicializarCola();
		while(!c.colaVacia()) {
			System.out.println(" " + c.primero() + " " + c.prioridad());
			aux.acolarPrioridad(c.primero(),c.prioridad());
			c.desacolar();
		}
		pasar(aux,c);
	}
	
	public static void pasar (apis.ColaPrioridadTDA origen, apis.ColaPrioridadTDA destino) {
		while(!origen.colaVacia()) {
			destino.acolarPrioridad(origen.primero(), origen.prioridad());
			origen.desacolar();
		}
	}
	
	
	

}
