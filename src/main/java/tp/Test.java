package tp;

public class Test {

	//Test del metodo quicksort
	
	public static void main(String[] args) {
		Lista lista = new Lista();
		
		lista.add(10);
		lista.add(9);
		lista.add(8);
		lista.add(7);
		lista.add(6);
		lista.add(5);
		lista.add(4);
		lista.add(3);
		lista.add(2);
		lista.add(1);
		
		lista.imprimir();
		
		lista.quickSort(10);
		
		try {Thread.sleep(5000); lista.imprimir();}
		catch (InterruptedException e) {e.printStackTrace();}
	}

}
