import java.util.*;
import java.lang.*;

public class ExcepcionNoEsRaiz extends ExcepcionArbolFicheros
{
	public String toString(){
        return "Error: la ruta debe construirse con la raiz";
    }
}