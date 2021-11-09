package api;

/*
 * La estructura de datos diccionario se caracteriza porque cada valor ingresa 
 * a la estructura asociado a una clave, y estás claves existen siempre que tengan 
 * valor asociado y son únicas.
 * */

public interface DiccionarioSimpleTDA {
	void inicializarDiccionario();
	void agregar(int clave, int valor);// siempre que el diccionario esté inicializado
	void eliminar(int clave);// siempre que el diccionario esté inicializado
	int recuperar(int clave);// siempre que el diccionario esté inicializado y la clave exista en el mismo
	ConjuntoTDA claves();// siempre que el diccionario esté inicializado
}
