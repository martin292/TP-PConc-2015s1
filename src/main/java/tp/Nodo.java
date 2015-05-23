package tp;

public class Nodo {

	private Nodo nodoAnterior;
	private Nodo nodoSiguiente;
	private int numero;
	private int posicion;
	
	
	public Nodo(){
		super();
	}
	
	public boolean contiene(int num) {
		boolean ret = false;
		return ret = (nodoSiguiente != null) ? (num == numero) || nodoSiguiente.contiene(num) : num == numero;
	}
	
	/**
	 * PreCond: La posicion debe existir.
	 * @param pos
	 * @return
	 */
	public int get(int pos) {
		return (posicion == pos) ? numero : ((nodoSiguiente != null) ? nodoSiguiente.get(pos): null);
	}
	
	/**
	 * PreCond: La posicion debe existir.
	 * @param num
	 * @param pos
	 */
	public void set(int num, int pos) {
		if(this.posicion == pos){
			this.numero = num;
		}else if(nodoSiguiente != null){
			this.nodoSiguiente.set(num, pos);
		}
	}
	
	

	public Nodo getNodoAnterior() {return nodoAnterior;}
	public void setNodoAnterior(Nodo nodoAnterior) {this.nodoAnterior = nodoAnterior;}
	public Nodo getNodoSiguiente() {return nodoSiguiente;}
	public void setNodoSiguiente(Nodo nodoSiguiente) {this.nodoSiguiente = nodoSiguiente;}
	public int getNumero() {return numero;}
	public void setNumero(int numero) {this.numero = numero;}
	public int getPosicion() {return posicion;}
	public void setPosicion(int posicion) {	this.posicion = posicion;}
	
}
