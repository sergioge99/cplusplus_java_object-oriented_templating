import java.util.*;
import java.lang.*;

public class Archivo extends Elemento
{
	public Archivo(String n,int t){
		nombre=n;
		tamanyo=t;
	}

	public final int getSize(int lvl)throws ExcepcionBucle {
		if(lvl<20){
			return tamanyo;
		}
        else{
			throw new ExcepcionBucle();
		}
    }
}
