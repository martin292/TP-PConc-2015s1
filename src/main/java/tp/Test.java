package tp;

public class Test {

	//Test del metodo quicksort // Solo ordena con un thread
	
	public static void main(String[] args) {
		Lista lista = new Lista();
		for(int i= 0; i < 20; i++){
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
//		lista.add(3);
//		lista.add(2);
//		lista.add(1);
		
		lista.imprimir();
		
		lista.quickSort(1);
		
		try {Thread.sleep(5000); lista.imprimir();}//Cuanto mas numeros en lista mas tiempo en el slepp
		catch (InterruptedException e) {e.printStackTrace();}
	}

}
