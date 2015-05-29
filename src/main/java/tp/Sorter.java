package tp;

public class Sorter extends Thread{

	private Lista lista; //Lista a ordenar
	private int threads; //Cantidad de threds que pueden estar activos
	private int activos; //Cantidad de threds activos (solo el original lo usa)
	private Sorter original; //Thread original
	public boolean ordenado; //Es true solo si el tamaños de la lista es <= 1
	
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

			Sorter l = new Sorter(left , original());
			Sorter r = new Sorter(right, original());
			
			l.start(); r.start();
			
			while(listasDesordenadas(l, r))
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
	
	private boolean desordenada(Sorter s) {
		return !s.ordenado;
	}
	
	private synchronized boolean seAlcanzoMaxCantDeThreads() {
		return original().activos >= threads;
	}

	private synchronized void incrementarThreadsActivos() {
		original().activos += 1;
	}
	
	private synchronized void ordenar() {
		this.ordenado = true;
	}

	private synchronized void decrementarThreadsActivos() {
		original().activos -= 1;
	}

	private boolean listasDesordenadas(Sorter l, Sorter r) {
		return desordenada(l) || desordenada(r);
	}
	
	private Sorter original() {
		return this.original;
	}
	
	//
	
}
