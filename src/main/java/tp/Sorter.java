package tp;

public class Sorter extends Thread{

	private Lista lista; //Lista a ordenar
	//private int threads; //Cantidad de threds que pueden estar activos
	//private int activos; //Cantidad de threds activos (solo el original lo usa)
	private Sorter original; //Thread original
	public boolean ordenado; //Es true solo si el tamaños de la lista es <= 1
	private ThreadsActivos threadsActivos;
	
	public Sorter(Lista lista, ThreadsActivos threadsActivos){ //int cantThreads
		this.lista = lista;
		//this.threads = cantThreads;
		//this.activos = 0;
		this.original = this;
		this.ordenado = false;
		this.threadsActivos= threadsActivos;
	}
	
	public Sorter(Lista lista, Sorter o, ThreadsActivos threadsActivos){
		this.lista = lista;
		//this.threads = o.threads;
		this.original = o;
		this.ordenado = false;
		this.threadsActivos= threadsActivos;
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	public synchronized void sort() throws InterruptedException{
		if(hayMasDeUnNumero()){
			int pivot   = pivot();
			Lista left  = menores(pivot);
			Lista right = mayores(pivot);

			Sorter l = nuevoThread(left, threadsActivos);
			Sorter r = nuevoThread(right, threadsActivos);
			
			//iniciarThreads(l, r);
			l.start();
			threadsActivos.esperarSiEsNecesario(l);
			r.start();
			threadsActivos.esperarSiEsNecesario(r);
//			while(listasDesordenadas(l, r))
//				wait();
			actualizarLista(pivot, left, right);
		}
		//notificar();
		threadsActivos.termino(this);
	}				
	
	@Override
    public void run() {
		try {/*activar();*/ threadsActivos.incrementarThreadActivos(); sort();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}

	//----------------------------------------------------------------------------------------------------------
	
//	private synchronized void notificar() {
//		ordenar();
//		decrementarThreadsActivos();
//		notifyAll();
//	}
	
//	private synchronized void activar() throws InterruptedException {
//		incrementarThreadsActivos();
//		while(seAlcanzoMaxCantDeThreads())
//			wait();
//	}
	
	private void iniciarThreads(Sorter l, Sorter r) {
		l.start(); r.start();
	}
	
	private void actualizarLista(int pivot, Lista left, Lista right) {
		lista.actualizar(left, pivot, right);
	}
	
	private boolean desordenada(Sorter s) {
		return !s.ordenado;
	}
	
//	private synchronized boolean seAlcanzoMaxCantDeThreads() {
//		return original().activos >= threads;
//	}
//
//	private synchronized void incrementarThreadsActivos() {
//		original().activos += 1;
//	}
	
	public synchronized void ordenado() {
		this.ordenado = true;
	}

//	private synchronized void decrementarThreadsActivos() {
//		original().activos -= 1;
//	}

	private boolean listasDesordenadas(Sorter l, Sorter r) {
		return desordenada(l) || desordenada(r);
	}
	
	private Sorter original() {
		return this.original;
	}
	
	private Lista mayores(int pivot) {
		return lista.mayoresQue(pivot);
	}

	private Lista menores(int pivot) {
		return lista.menoresQue(pivot);
	}

	private int pivot() {
		return lista.getPivot();
	}
	
	private boolean hayMasDeUnNumero() {
		return lista.size() > 1;
	}
	
	private Sorter nuevoThread(Lista left, ThreadsActivos threadsActivos) {
		return new Sorter(left , original(), threadsActivos);
	}
	
	//
	
}
