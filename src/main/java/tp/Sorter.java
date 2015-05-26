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
		this.activos = 1;
		this.original = this;
		this.ordenado = (lista.size() <= 1);
	}
	
	public Sorter(Lista lista, int cantThreads, Sorter o){
		this.lista = lista;
		this.threads = cantThreads;
		this.original = o;
		this.ordenado = (lista.size() <= 1);
	}
	
	public synchronized void sort() throws InterruptedException{
		if(ordenado){
			notificar();
			return;
		}
		int pivot = lista.getPivot();
		Lista left = lista.menoresQue(pivot);
		Lista right = lista.mayoresQue(pivot);

		Sorter l = new Sorter(left, threads, this.original);
		Sorter r = new Sorter(right, threads, this.original);			
		l.start(); r.start();

		while(threadsDesordenados(l, r))
			wait();

		lista.actualizar(left, pivot, right);
		notificar();		
	}

	private void notificar() {
		this.ordenado = true;
		this.original.activos -= 1;
		notifyAll();
	}

	private boolean threadsDesordenados(Sorter l, Sorter r) {
		return !l.ordenado || !r.ordenado;
	}
	
	@Override
    public void run() {
		try {
			this.original.activos += 1;
			while(threads == this.original.activos)
				wait();
			
			sort();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
}
