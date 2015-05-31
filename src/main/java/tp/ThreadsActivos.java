package tp;

public class ThreadsActivos {
	private int cantidadMax;
	private int activos;
	
	public ThreadsActivos(int max){
		this.cantidadMax= max;
		this.activos= 0;
	}

	public synchronized void esperarSiEsNecesario(Sorter hijo) {
		while(!hijo.ordenado && activos >= cantidadMax){
			decrementarThreadActivos();
			try { wait(); } catch (InterruptedException e) { }
			incrementarThreadActivos();
		}
	}
	
	public synchronized void esperarQueTerminen(Sorter l, Sorter r) {
		while(!l.ordenado && !r.ordenado){
			decrementarThreadActivos();
			try { wait(); } catch (InterruptedException e) { }
			incrementarThreadActivos();
		}
	}
	
	public synchronized void incrementarThreadActivos(){
		while(activos >= cantidadMax){
			try { wait(); } catch (InterruptedException e) {}
		}
		activos++;
		System.out.println("Threads activos= " + activos);
		notifyAll();
	}
	
	public synchronized void decrementarThreadActivos(){
//		s.ordenado= true;
		activos--;
		notifyAll();
	}
	
	public synchronized void termino(Sorter s){
		s.ordenado();
		decrementarThreadActivos();
	}
}
