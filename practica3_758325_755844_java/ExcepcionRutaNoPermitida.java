import java.util.*;
import java.lang.*;

public class ExcepcionRutaNoPermitida extends ExcepcionNoPermitido
{
	public String toString(){
        return "Error: ruta completa no permitida como argumento";
    }
}