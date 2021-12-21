import java.util.*;
import java.lang.*;

public class ExcepcionNoExisteElemento extends ExcepcionNoExiste
{
	public String toString(){
        return "Error: no existe directorio,fichero o enlace";
    }
}