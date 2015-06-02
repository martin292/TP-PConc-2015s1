package tp;

import java.util.ArrayList;
import java.util.List;

public class Lista {

	private List<Integer> lista = new ArrayList<Integer>();	
	
	//
	
	public synchronized int size(){return lista.size();}

	public synchronized boolean isEmpty(){return lista.isEmpty();}

	public synchronized boolean contains(int num){return lista.contains(num);}

	public synchronized void add(int num){lista.add(num);}

	public synchronized int get(int pos){return lista.get(pos);}

	public synchronized void set(int pos, int num){lista.set(pos, num);}
	
	public synchronized void quickSort(int t) throws InterruptedException{
		Sorter s = new Sorter(this, new ThreadsActivos(t));
		s.start();
		while(listaDesordenada(s))
			wait();
	}
	
	//
	
	private boolean listaDesordenada(Sorter s) {
		return !s.ordenado;
	}

	public synchronized int getPivot(){
		return this.get(random());
	}

	private int random() {
		return (int) (Math.random()*this.size());
	}
	
	public synchronized Lista menoresQue(int pivot){
		Lista menores = new Lista();
		for (Integer num : lista){
			if(num < pivot)
				menores.add(num);
		}
		return menores;
	}
	
	public synchronized Lista mayoresQue(int pivot){
		Lista mayores = new Lista();
		for (Integer num : lista){
			if(num > pivot)
				mayores.add(num);
		}
		return mayores;
	}
	
	public synchronized void concat(Lista l){
		this.lista.addAll(l.lista);
	}
	
	public synchronized void actualizar(Lista left, int pivot, Lista right){
		left.add(pivot);
		left.concat(right);
		this.lista = left.lista;
	}
	
	public synchronized void imprimir(){
		System.out.println(lista);
	}

	public synchronized void notificar() {notify();}
	
	//
}
