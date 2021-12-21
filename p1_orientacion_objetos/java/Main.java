import java.util.Iterator;

public class Main
{
	public static void main(String[] args)
	{
		Agrupacion<Integer> ag = new Agrupacion<Integer>();
		ag.anyadir(42);
		ag.anyadir(15);
		ag.borrarUltimo();
		for (int i = 0; i < 100; i+=5) ag.anyadir(i);
		
		Iterator<Integer> iter = ag.iterator();
		while(iter.hasNext()) 
			System.out.print(iter.next()+" ");
		
		System.out.println();

	//Opcionalmente, con esta nomenclatura, puede hacerse asi
		for (Integer i : ag) 
			System.out.print(i+" ");
		
		System.out.println();
	}
}
