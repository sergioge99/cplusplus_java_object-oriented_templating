
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

class Elemento{
	String nombre;
    int tamanyo;

    public Elemento(String n){
		nombre=n;
		tamanyo=0;
    }
    
	public String getName(){return nombre;}
    public int getSize(){return tamanyo;}
    public void cambiar_tamanyo(int t){
        tamanyo = t;
    }
}