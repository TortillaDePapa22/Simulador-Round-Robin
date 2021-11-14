package test;


import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import api.AdministradorDeColasTDA;
import impl.AdministradorDeColas;
import impl.RoundRobin;


public class TestTPO {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 0, valor, tiempo, cantidadPuestos = 0;
		Random rand = new Random();
		
		AdministradorDeColasTDA admin = new AdministradorDeColas();
		RoundRobin adminRR = new RoundRobin(admin); //ACA SE VA A REALIZAR EL ROUND ROBIN

		Scanner sc= new Scanner(System.in);
		System.out.print("Ingrese la cantidad de servidores a utilizar (Máximo 10): ");
		cantidadPuestos = sc.nextInt();
		admin.inicializar(cantidadPuestos); //Inicializo servers
		System.out.println("\n-------------------------------------------------\n");

		/*
		admin.acolar(99, 1);
		admin.acolar(98, 2);
		admin.acolar(97, 3);
		admin.acolar(96, 4);
		admin.acolar(95, 5);
		admin.acolar(94, 6);
		admin.acolar(93, 7);
		admin.acolar(92, 8);
		admin.acolar(91, 9);
		admin.acolar(90, 9);
		admin.acolar(50, 9);
		admin.acolar(70, 9);
		*/
		
		while(i < 40) { //acolo 40 numeros al azar
			valor = i;
			tiempo = rand.nextInt(10);
			tiempo += 1; //sumo 1 para que tiempo no sea 0;
			admin.acolar(valor, tiempo);
			i++;
		}
		
		
		admin.desacolar();
		admin.desacolar();
		admin.acolar(80, 4);
		admin.acolar(108, 16);
		
//		AdministradorDeColas.imprimir(admin.elementos());
		
		admin.programacion(); // Imprimo la programación
		
		System.out.println("\n \nQuantum: " + admin.estimado()); //quantum
		Timer temporizador = new Timer();
		RoundRobin.horaIni = adminRR.horaEjecucion();
		System.out.println("\nROUND ROBIN COMENZADO... \nHora comienzo simulador:" + RoundRobin.horaIni + "\n");
		adminRR.setTemporizador(temporizador);
		temporizador.scheduleAtFixedRate(adminRR, 0, 1000); //Comienza el proceso
	
		
		
		
	}
}
