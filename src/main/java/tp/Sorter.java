package tp;

public class Sorter extends Thread{

	private Lista lista;
	private int threads;
	private boolean ordenado;
	
	public Sorter(Lista lista, int cantThreads){
		this.lista = lista;
		this.threads = cantThreads;		
		this.ordenado = (lista.size() <= 1);
	}
	
	public synchronized void sort() throws InterruptedException{
		System.out.println("Tamaño: " + lista.size());
		if(!ordenado){
			int pivot = lista.getPivot();
			System.out.println("Pivot: " + pivot);
			Lista left = lista.menoresQue(pivot);
			System.out.println("Left: ");
			left.imprimir();
			Lista right = lista.mayoresQue(pivot);
			System.out.println("Right: ");
			right.imprimir();
			
			threads -= 2;
			Sorter l = new Sorter(left, threads/2);
			Sorter r = new Sorter(right, threads/2);
			
			l.start(); r.start();
			
			while(!l.ordenado || !r.ordenado)
				this.wait();
			
			left.add(pivot);
			left.concat(right);
			lista.actualizar(left);
			//lista.imprimir();
		}
		
		this.ordenado = (lista.size() <= 1);
		this.notifyAll();		
		lista.imprimir();
	}
	
	@Override
    public void run() {
		try {sort();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
}
