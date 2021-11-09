package api;

/*
 * La estructura de datos diccionario se caracteriza porque cada valor ingresa 
 * a la estructura asociado a una clave, y est�s claves existen siempre que tengan 
 * valor asociado y son �nicas.
 * */

public interface DiccionarioSimpleTDA {
	void inicializarDiccionario();
	void agregar(int clave, int valor);// siempre que el diccionario est� inicializado
	void eliminar(int clave);// siempre que el diccionario est� inicializado
	int recuperar(int clave);// siempre que el diccionario est� inicializado y la clave exista en el mismo
	ConjuntoTDA claves();// siempre que el diccionario est� inicializado
}
