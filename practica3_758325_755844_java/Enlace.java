  
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Enlace extends Elemento
{
    public Elemento el_enlace;
	public Enlace(String _nombre, Elemento nuevo){
        el_enlace = nuevo;
        this.nombre = _nombre;
        this.tamanyo = nuevo.tamanyo;
    }
    
    public Elemento apunto_a_archivo(){
        if(el_enlace instanceof Enlace){
            Enlace enlace = (Enlace)el_enlace;
            Elemento ayuda = enlace.apunto_a_archivo();
        }else{
            return Elemento;
        }
    }
}