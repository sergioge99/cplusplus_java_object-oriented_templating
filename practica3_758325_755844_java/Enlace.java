  
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.*;
import java.lang.*;

public class Enlace extends Elemento
{
    public Elemento el_enlace;
	public Enlace(String _nombre, Elemento nuevo){
        super(_nombre);
        el_enlace = nuevo;
    }
    
    public int getSize(){
        return el_enlace.getSize();
    }

    public Elemento apunto_a_archivo(){
        if(el_enlace instanceof Enlace){
            Enlace enlace = (Enlace)el_enlace;
            Elemento ayuda = enlace.apunto_a_archivo();
        }
        return el_enlace;
    }
};