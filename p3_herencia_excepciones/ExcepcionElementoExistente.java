import java.util.*;
import java.lang.*;

public class ExcepcionElementoExistente extends ExcepcionArbolFicheros
{
	public String toString(){
        return "Error: ya existe un elemento con ese nombre";
    }
}