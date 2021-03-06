package tp;

public class ThreadsActivos {
	
	private int cantidadMax;
	private int activos;
	
	public ThreadsActivos(int max){
		this.cantidadMax= max;
		this.activos= 0;
	}
	
	//--------------------------------------------------------------------------------------------------

	public synchronized void esperarSiEsNecesario(Sorter l, Sorter r) throws InterruptedException {
		while(listaDesordenada(l) || listaDesordenada(r)){
			if(seAlcansoMaxCantDeThreadsActivos()){
				decrementarThreadActivos();
				wait();
				incrementarThreadActivos();
			}else{
				wait();
			}
		}
	}
	
	public synchronized void incrementarThreadActivos() throws InterruptedException{
		while(seAlcansoMaxCantDeThreadsActivos())
			wait();		
		activos++;
	}

	public synchronized void decrementarThreadActivos(){
		activos--;
		notifyAll();
	}
	
	//---------------------------------------------------------------------------------------------------
		
	public synchronized void termino(Sorter s){
		s.ordenado();
		decrementarThreadActivos();
	}
	
	public synchronized boolean seAlcansoMaxCantDeThreadsActivos() {
		return activos >= cantidadMax;
	}
	
	private boolean listaDesordenada(Sorter s) {
		return !s.ordenado;
	}
	
	//
	
}
