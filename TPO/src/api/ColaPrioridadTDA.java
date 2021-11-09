package api;

public interface ColaPrioridadTDA {
	
	public void inicializarCola();
	void acolarPrioridad(int x, int prioridad); //siempre que la cola este inicializada
	void desacolar(); //siempre que la cola este inicializada y no vacia
	int primero (); //siempre que la cola este inicializada y no vacia
	boolean colaVacia(); //siempre que la cola este inicializada
	int prioridad(); //siempre que la cola este inicializada y no vacia
}
