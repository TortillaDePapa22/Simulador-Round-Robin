package implTDA;

import api.ConjuntoTDA;
import api.DiccionarioSimpleTDA;
	
//DICCIONARIO SIMPLE LISTAS

public class DicSimpleL implements DiccionarioSimpleTDA {

	class NodoClave{
		int clave;
		int valor;
		NodoClave sigClave;
	}
	
	NodoClave origen;
	
	@Override
	//COSTO: Constante
	public void inicializarDiccionario() {
		// TODO Auto-generated method stub
		origen = null;
	}

	@Override
	//COSTO: Lineal
	public void agregar(int clave, int valor) {
		// TODO Auto-generated method stub
		NodoClave nc = Clave2NodoClave (clave);
		if (nc == null) {
			nc = new NodoClave();
			nc.clave = clave;
			nc.sigClave = origen;
			origen = nc;
		}
		nc.valor = valor;
	}
	
	private NodoClave Clave2NodoClave (int clave) {
		NodoClave aux = origen;
		while (aux!=null && aux.clave!=clave){
			aux = aux.sigClave;
		}
		return aux;
	}

	
	@Override
	//COSTO: Lineal
	public void eliminar(int clave) {
		// TODO Auto-generated method stub
		if(origen!=null) {
			if(origen.clave == clave) {
				origen = origen.sigClave;
			}else {
				NodoClave aux = origen;
				while(aux.sigClave != null && aux.sigClave.clave != clave) {
					aux = aux.sigClave;
				}
				if(aux.sigClave!=null) {
					aux.sigClave = aux.sigClave.sigClave;
				}
			}
		}
	}

	
	@Override
	//COSTO: Lineal
	public int recuperar(int clave) {
		// TODO Auto-generated method stub
		NodoClave n = Clave2NodoClave(clave);
		return n.valor;
	}

	
	@Override
	//COSTO: Lineal
	public ConjuntoTDA claves() {
		// TODO Auto-generated method stub
		ConjuntoTDA c = new ConjuntoLD();
		c.inicializarConjunto();
		
		NodoClave aux = origen;
		while(aux != null) {
			c.agregar(aux.clave);
			aux = aux.sigClave;
		}
		return c;
	}
}
