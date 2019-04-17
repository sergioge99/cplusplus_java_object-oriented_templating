import java.util.*;
import java.lang.*;

public class ExcepcionNombreNoPermitido extends ExcepcionNoPermitido
{
	public String toString(){
        return "Error: solo se admiten nombres formados por cadena de texto";
    }
}