
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.*;
import java.lang.*;

abstract class Elemento{
	String nombre;
    int tamanyo;
    public Elemento(){
        tamanyo = 0;
    }
    public Elemento(String n){
		nombre=n;
		tamanyo=0;
    }
    
	public String getName(){return nombre;}
    public abstract int getSize();
    public void cambiar_tamanyo(int t){
        tamanyo = t;
    }
}