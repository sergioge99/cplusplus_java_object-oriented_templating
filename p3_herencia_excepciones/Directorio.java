import java.util.LinkedList;
import java.util.*;
import java.lang.*;

public class Directorio extends Elemento
{
    LinkedList<Elemento> contenido = new LinkedList<Elemento>();
    public Directorio(){
        nombre = "";
        tamanyo = 0;
    }
	public Directorio(String n){
		nombre=n;
		tamanyo=0;
    }
    
    public boolean existe(Directorio uno){
        return contenido.contains(uno);
    }
    
    public Elemento existe_name(String name){
        int n = contenido.size();
        Elemento nuevo = null;
        int i=0;
        boolean encontrado = false;
        while(!encontrado && i < n){
            nuevo = contenido.get(i);
            if(name.equals(nuevo.getName())){
                encontrado=true;
            }else{
                i++;
            }
        }
        if(!encontrado){ return null;}
        else{return nuevo;}
    }

    public final int getSize(int lvl) throws ExcepcionBucle {
        if(lvl<20){
            int n = contenido.size();
            int total = 0;
            for (int i=0;i<n;i++){
                Elemento nuevo = contenido.get(i);
                total = total + nuevo.getSize(lvl+1);
            }
            return total;
        }
        else{
            throw new ExcepcionBucle();
        }
    }

    public void anyadir_elemento(Elemento nuevo){
        contenido.addLast(nuevo);
	}
	
	public Boolean borrar_elemento(String name){
        int n = contenido.size();
        Elemento nuevo = null;
        int i=0;
        boolean encontrado = false;
        while(!encontrado && i < n){
            nuevo = contenido.get(i);
            if(name.equals(nuevo.getName())){
				encontrado=true;
				contenido.remove(i);
            }else{
                i++;
            }
        }
        return encontrado;
    }
}
