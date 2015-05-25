package tp;

import java.util.ArrayList;
import java.util.List;

public class Lista {

	private List<Integer> lista = new ArrayList<Integer>();	
	
	
	public synchronized int size(){return lista.size();}

	public synchronized boolean isEmpty(){return lista.isEmpty();}

	public synchronized boolean contains(int num){return lista.contains(num);}

	public synchronized void add(int num){lista.add(num);}

	public synchronized int get(int pos){return lista.get(pos);}

	public synchronized void set(int pos, int num){lista.set(pos, num);}
	
	
	
	public synchronized void quickSort(int t){
		new Sorter(this, t).start();
	}
	
	
	public synchronized int getPivot(){
		int pos = (int) (Math.random()*(0 - this.size()) + this.size());
		return this.get(pos);
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
	
	public synchronized void actualizar(Lista l){
		this.lista = l.lista;
	}
	
	public synchronized void imprimir(){
		System.out.println(lista);
	}
	//
}
