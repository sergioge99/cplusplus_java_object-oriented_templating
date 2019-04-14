//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Directorio extends Elemento
{
	LinkedList<Elemento> contenido = new LinkedList<Elemento>();
	public Directorio(String n){
		nombre=n;
		tamanyo=0;
	}
	
}
