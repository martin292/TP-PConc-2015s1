package tp;

public class Test {

	//Test del metodo quicksort
	
	public static void main(String[] args) throws InterruptedException {
		Lista lista = new Lista();
		
		for(int i= 0; i < 100; i++){
			int num = (int) (Math.random()*(0 - 100) + 100);
			if(!lista.contains(num))
				lista.add(num);
		}
		
		lista.imprimir();
		
		lista.quickSort(15);
		
		lista.imprimir();
	}

}
