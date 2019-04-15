  
//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.*;
import java.lang.*;

public class Archivo extends Elemento
{
	public Archivo(String n,int t){
		nombre=n;
		tamanyo=t;
	}

	public int getSize(){
        return tamanyo;
    }
}