package tp;

public class Lista {

	private Nodo primerNumero;
	private Nodo ultimoNumero;
	private int cantidad;
	
	public Lista(){
		primerNumero = null;
		ultimoNumero = null;
		cantidad = 0;
	}
	
	/**
	 * Retorna el tamaño de la lista
	 * @return
	 */
	public synchronized int size(){
		return cantidad;
	}
	
	/**
	 * Indica si la lista esta o no vacia
	 * @return
	 */
	public synchronized boolean isEmpty(){
		return cantidad == 0;
	}
	
	/**
	 * Indica si la lista contiene o no un elemento dado
	 * @return
	 */
	public synchronized boolean contains(int num){
		return (primerNumero != null) ? primerNumero.contiene(num) : false;
	}
	
	/**
	 * Agrega un numero al final de la lista
	 * @return
	 */
	public synchronized Lista add(int num){
		Nodo nuevo = new Nodo();
		this.cantidad += 1;
		nuevo.setNumero(num);
		nuevo.setPosicion(this.cantidad);
		
		if(this.cantidad == 1){			
			this.primerNumero = nuevo;
			this.ultimoNumero = nuevo;
		}else{
			nuevo.setNodoAnterior(this.ultimoNumero);
			this.ultimoNumero.setNodoSiguiente(nuevo);
			this.ultimoNumero = nuevo;
		}
				
		return this;
	}
	
	/**
	 * Retorna el elemento almacenado en una posicion dada
	 * Retorna null si la lista esta vacia.
	 * @return
	 */
	public synchronized int get(int posicion){
		return (primerNumero != null) ? this.primerNumero.get(posicion): null;
	}
	
	/**
	 * Permite alterar el valor del elemento almacenado en una posicion dada
	 * @return
	 */
	public synchronized Lista set(int num, int posicion){
		if(primerNumero != null){
			this.primerNumero.set(num, posicion);
		}
		return this;
	}
	
	public synchronized boolean listaVacia(){
		return cantidad == 0;
	}
	
	//------------------------------------------------------------------------------
	
	public synchronized void quicksort(){
		// TODO
		if(!this.listaVacia()){
			//Nodo pivot = this.getPivot();
			//Lista left = this.menoresQue(pivot);
			//Lista right = this.mayoresQue(pivot);
			//left.quicksort();
			//right.quicksort();
			//Lista l = left ++ pivot ++ right;
		}
	}
	
}
