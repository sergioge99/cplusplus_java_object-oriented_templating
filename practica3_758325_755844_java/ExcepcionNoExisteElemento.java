import java.util.*;
import java.lang.*;

public class ExcepcionNoExisteElemento extends ExcepcionNoExiste
{
	public String toString(){
        return "No existe directorio,fichero o enlace";
    }
}