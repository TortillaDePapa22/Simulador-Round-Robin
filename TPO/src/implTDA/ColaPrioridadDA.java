package implTDA;

import api.ColaPrioridadTDA;

//COLA PRIORIDAD DOS ARREGLOS ?????

public class ColaPrioridadDA implements ColaPrioridadTDA {

	int [] elementos;
	int [] prioridades;
	int indice;
	int primero;
	
	//COSTO: Constante
	public void inicializarCola() {
		elementos = new int[100];
		prioridades = new int[100];
		indice = 0;
		primero = 0;
	}
	
	
	@Override
	//COSTO: Lineal (2 n)
	public void acolarPrioridad(int x, int prioridad) {
		// TODO Auto-generated method stub
		int j = indice;
		// Encuentra lugar donde acolar al nuevo elemento desplazando a derecha
		for(;j>0 && prioridades[j-1]>=prioridad; j--) {
			elementos[j] = elementos[j-1];		// Desplazo a derecha
			prioridades[j] = prioridades[j-1];	// Desplazo a derecha
		}
		elementos[j] = x;
		prioridades[j] = prioridad;
		indice++;
		}

	@Override
	//COSTO: Constante
	public void desacolar() {
		// TODO Auto-generated method stub
		int cont = 0;
		while(cont<indice) {
			elementos[cont] = elementos [cont+1];
			prioridades[cont] = prioridades[cont+1];
			cont++;
		}
		indice--;
	}

	@Override
	//COSTO: Constante
	public int primero() {
		// TODO Auto-generated method stub
		return elementos[primero];
	}

	@Override
	//COSTO: Constante
	public boolean colaVacia() {
		// TODO Auto-generated method stub
		return(indice==0);
	}

	@Override
	//COSTO: Constante
	public int prioridad() {
		// TODO Auto-generated method stub
		return prioridades[primero];
	}



}
