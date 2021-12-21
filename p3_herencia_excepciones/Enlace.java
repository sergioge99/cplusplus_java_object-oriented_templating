import java.util.*;
import java.lang.*;

public class Enlace extends Elemento
{
    public Elemento el_enlace;
	public Enlace(String _nombre, Elemento nuevo){
        super(_nombre);
        el_enlace = nuevo;
    }
    
    public final int getSize(int lvl) throws ExcepcionBucle {
        if(lvl<20){
            return el_enlace.getSize(lvl+1);
        }
        else{
            throw new ExcepcionBucle();
        }
        
    }

    public Elemento apunto_a_archivo(){
        if(el_enlace instanceof Enlace){
            Enlace enlace = (Enlace)el_enlace;
            Elemento ayuda = enlace.apunto_a_archivo();
        }
        return el_enlace;
    }
};