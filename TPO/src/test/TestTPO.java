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
		System.out.print("Ingrese la cantidad de servidores a utilizar (M�ximo 10): ");
		cantidadPuestos = sc.nextInt();
		admin.inicializar(cantidadPuestos); //Inicializo servers
		System.out.println("\n-------------------------------------------------\n");

		
		while(i < 40) { //acolo 40 numeros al azar
			valor = i;
			tiempo = rand.nextInt(10);
			tiempo += 1; //sumo 1 para que tiempo no sea 0;
			admin.acolar(valor, tiempo);
			i++;
		}
		
		
		admin.programacion(); // Imprimo la programaci�n
		
		System.out.println("\n \nQuantum: " + admin.estimado()); //quantum
		Timer temporizador = new Timer();
		RoundRobin.horaIni = adminRR.horaEjecucion();
		System.out.println("\nROUND ROBIN COMENZADO... \nHora comienzo simulador:" + RoundRobin.horaIni + "\n");
		adminRR.setTemporizador(temporizador);
		temporizador.scheduleAtFixedRate(adminRR, 0, 1000); //Comienza el proceso
	
		
		
		
	}
}
