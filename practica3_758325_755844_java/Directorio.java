
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
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

    public void anyadir_elemento(Elemento nuevo){
        contenido.addLast(nuevo);
    }
}