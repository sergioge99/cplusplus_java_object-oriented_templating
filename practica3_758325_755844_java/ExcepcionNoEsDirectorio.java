import java.util.*;
import java.lang.*;

public class ExcepcionNoEsDirectorio extends ExcepcionArbolFicheros
{
	public String toString(){
        return "Algo en lo introducido no es un directorio";
    }
}