package tp;

public class Test {

	//Test del metodo quicksort
	
	public static void main(String[] args) {
		Lista lista = new Lista();
		
		for(int i= 0; i < 100; i++){
			int num = (int) (Math.random()*(0 - 100) + 100);
			lista.add(num);
		}
//		lista.add(10);
//		lista.add(9);
//		lista.add(8);
//		lista.add(7);
//		lista.add(6);
//		lista.add(5);
//		lista.add(4);
//		lista.add(4);
//		lista.add(2);
//		lista.add(1);
		System.out.println("size= " + lista.size());
		lista.imprimir();
		
		lista.quickSort(1); //Solo anda con uno
		
		try {
			Thread.sleep(7000); //Cuanto mas numeros en lista mas tiempo en el sleep
			lista.imprimir();
			System.out.println("size= " + lista.size()); //Menos por que no guarda repetidos
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}

}
