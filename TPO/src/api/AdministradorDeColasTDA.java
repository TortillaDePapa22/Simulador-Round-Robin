package api;


import apis.ColaPrioridadTDA;
import apis.DiccionarioSimpleTDA;

public interface AdministradorDeColasTDA {
	
	void inicializar(int cantidad);
	void acolar(int idElemento, int TiempoEstimado); //Siempre que este inicializado
	void desacolar(); 				//Siempre que este inicializado
	ColaPrioridadTDA programacion();//Siempre que este inicializado
	int cantColas(); 				//Siempre que este inicializado
	int primero();					//Siempre que este inicializado
	int estimado();					//Siempre que este inicializado y cola no vacia
	int puestoProximoElem(int idElemento);	//Siempre que este inicializado y cola no vacia
	int puestoDelElem(int idElemento); 		//Siempre que este inicializado y cola no vacia
	DiccionarioSimpleTDA elementos(); 		//Siempre que este inicializado y cola no vacia

	
}
