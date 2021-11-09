package api;

public interface ConjuntoTDA {

	void inicializarConjunto();
	boolean conjuntoVacio(); // siempre que el conjunto esté inicializado
	void agregar(int x); 	// siempre que el conjunto esté inicializado
	int elegir();	// siempre que el conjunto esté inicializado y no esté vacío
	void sacar(int x);	// siempre que el conjunto esté inicializado
	boolean pertenece(int x);	// siempre que el conjunto esté inicializado
}
