package api;

public interface ConjuntoTDA {

	void inicializarConjunto();
	boolean conjuntoVacio(); // siempre que el conjunto est� inicializado
	void agregar(int x); 	// siempre que el conjunto est� inicializado
	int elegir();	// siempre que el conjunto est� inicializado y no est� vac�o
	void sacar(int x);	// siempre que el conjunto est� inicializado
	boolean pertenece(int x);	// siempre que el conjunto est� inicializado
}
