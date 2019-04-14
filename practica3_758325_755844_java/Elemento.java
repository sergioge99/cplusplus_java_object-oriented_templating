//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

interface Elemento{
	public String nombre;
	public int tamanyo;
	String getName(){return nombre;}
	int getSize(){return tamanyo;}
}