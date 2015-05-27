package tp;

public class Sorter extends Thread{

	private Lista lista;
	private int threads; //Cantidad de threds que pueden estar activos
	private int activos; //Cantidad de threds activos
	private Sorter original;
	public boolean ordenado;
	
	public Sorter(Lista lista, int cantThreads){
		this.lista = lista;
		this.threads = cantThreads;
		this.activos = 0;
		this.original = this;
		this.ordenado = false;
	}
	
	public Sorter(Lista lista, Sorter o){
		this.lista = lista;
		this.threads = o.threads;
		this.original = o;
		this.ordenado = false;
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	public synchronized void sort() throws InterruptedException{
		if(lista.size() > 1){
			int pivot = lista.getPivot();
			Lista left  = lista.menoresQue(pivot);
			Lista right = lista.mayoresQue(pivot);

			Sorter l = new Sorter(left , this.original);
			Sorter r = new Sorter(right, this.original);
			
			l.start(); r.start();
			
			while(listasDesordenados(l, r))
				wait();
			
			lista.actualizar(left, pivot, right);
		}
		notificar();
	}
	
	@Override
    public void run() {
		try {activar(); sort();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}

	//----------------------------------------------------------------------------------------------------------
	
	private synchronized void notificar() {
		ordenar();
		decrementarThreadsActivos();
		notifyAll();
	}
	
	private synchronized void activar() throws InterruptedException {
		incrementarThreadsActivos();
		while(seAlcanzoMaxCantDeThreads())
			wait();
	}
	
	//
	
	private boolean desordenado(Sorter s) {
		return !s.ordenado;
	}
	
	private synchronized boolean seAlcanzoMaxCantDeThreads() {
		return this.original.activos >= threads;
	}

	private synchronized void incrementarThreadsActivos() {
		this.original.activos += 1;
	}
	
	private synchronized void ordenar() {
		this.ordenado = true;
	}

	private synchronized void decrementarThreadsActivos() {
		this.original.activos -= 1;
	}

	private boolean listasDesordenados(Sorter l, Sorter r) {
		return desordenado(l) || desordenado(r);
	}
	
	//
	
}
