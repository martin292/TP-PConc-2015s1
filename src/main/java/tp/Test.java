package tp;

public class Test {

	//Test del metodo quicksort
	
	public static void main(String[] args) {
		Lista lista = new Lista();
		
		for(int i= 0; i < 100; i++){
			int num = (int) (Math.random()*(0 - 100) + 100);
			if(!lista.contains(num))
				lista.add(num);
		}
		
		System.out.println("size= " + lista.size());
		lista.imprimir();
		
		lista.quickSort(25);
		
		try {Thread.sleep(10000); lista.imprimir();}
		catch (InterruptedException e) {e.printStackTrace();}
	}

}
