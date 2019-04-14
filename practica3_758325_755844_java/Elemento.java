
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

interface Elemento{
	public String nombre;
    public int tamanyo;
    
	public String getName(){return nombre;}
    public int getSize(){return tamanyo;}
    public void cambiar_tamanyo(int t){
        tamanyo = t;
    }
}