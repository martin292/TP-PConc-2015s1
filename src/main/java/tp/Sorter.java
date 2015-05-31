package tp;

public class Sorter extends Thread{

	private Lista lista;
	public boolean ordenado;
	private ThreadsActivos threadsActivos;
	
	public Sorter(Lista lista, ThreadsActivos threadsActivos){
		this.lista = lista;
		this.ordenado = false;
		this.threadsActivos= threadsActivos;
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	@Override
    public void run() {
		try {activarThread(); sort();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public synchronized void sort() throws InterruptedException{
		if(hayMasDeUnNumero()){
			int pivot   = pivot();
			Lista left  = menores(pivot);
			Lista right = mayores(pivot);

			Sorter l = nuevoThread(left, threadsActivos);
			Sorter r = nuevoThread(right, threadsActivos);
			
			iniciarThreads(l, r);
			
			actualizarLista(pivot, left, right);
		}
		terminar();
	}	
	
	//----------------------------------------------------------------------------------------------------------
	
	private void activarThread() throws InterruptedException {
		threadsActivos.incrementarThreadActivos();
	}
			
	private void iniciarThreads(Sorter l, Sorter r) throws InterruptedException {
		l.start(); threadsActivos.esperarSiEsNecesario(l);
		r.start(); threadsActivos.esperarSiEsNecesario(r);
	}
	
	private void terminar() {
		threadsActivos.termino(this);
	}
	
	private void actualizarLista(int pivot, Lista left, Lista right) {
		lista.actualizar(left, pivot, right);
	}	
	
	private boolean hayMasDeUnNumero() {
		return lista.size() > 1;
	}
	
	private Sorter nuevoThread(Lista left, ThreadsActivos threadsActivos) {
		return new Sorter(left , threadsActivos);
	}
	
	public synchronized void ordenado() {
		this.ordenado = true;
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
	
	//
	
}
