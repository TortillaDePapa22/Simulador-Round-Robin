package implTDA;

import api.ConjuntoTDA;

//CONJUNTO LISTA DINAMICA

public class ConjuntoLD implements ConjuntoTDA {

	public class Nodo {
		int info;
		Nodo sig;
	}

	Nodo c;
	
	@Override
	//COSTO: Constante
	public void inicializarConjunto() {
		// TODO Auto-generated method stub
		c=null;
	}

	@Override
	public boolean conjuntoVacio() {
		// TODO Auto-generated method stub
		return (c==null);
	}

	@Override
	//COSTO: Lineal
	public void agregar(int x) {
		// TODO Auto-generated method stub
		if(!this.pertenece(x)) {//SI x NO PERTENECE AL CONJUNTO, LO AGREGO
			Nodo aux = new Nodo();
			aux.info = x;
			aux.sig = c;
			c = aux;
		}
	}

	@Override
	//COSTO: Constante
	public int elegir() {
		// TODO Auto-generated method stub
		return c.info;
	}

	@Override
	//COSTO: Lineal
	public void sacar(int x) {
		// TODO Auto-generated method stub
		if(c!=null) {
			//si es el primer elemento de la lista
			if(c.info == x) {
				c=c.sig;
			}else {
				Nodo aux = c;
				while (aux.sig != null && aux.sig.info!=x) {
					aux = aux.sig;
				}
				if(aux.sig != null)
					aux.sig = aux.sig.sig;
			}
		}
		
	}

	@Override
	//COSTO: Lineal
	public boolean pertenece(int x) {
		// TODO Auto-generated method stub
		Nodo aux = c;
		while ((aux != null) && (aux.info != x)) {
			aux = aux.sig;
		}
		return (aux != null);
	}

}
