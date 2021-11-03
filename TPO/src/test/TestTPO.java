package test;

import java.util.Scanner;

import api.AdministradorDeColasTDA;
import api.ColaPrioridadTDA;
import impl.AdministradorDeColas;
import impl.ColaPrioridadDA;
import impl.Proceso;

public class TestTPO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdministradorDeColasTDA admin = new AdministradorDeColas();
		Proceso pro1 = new Proceso();
		
		int cantidadPuestos = 0;
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Ingrese la cantidad de servidores a utilizar: ");
		cantidadPuestos = sc.nextInt();
		
		admin.inicializar(cantidadPuestos);
	
		System.out.println("Impreso.");
		
		admin.acolar(1,4);//proceso 1
		admin.acolar(4,2);//proceso 2
		admin.acolar(6,3);//proceso 3
		
		
		
		
		
	}
	
	
	
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
