//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.LinkedList;
import java.util.Elemento;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Ruta
{
	LinkedList<Directorio> rutaActiva = new LinkedList<Directorio>();
	public Ruta(Directorio raiz){
		rutaActiva.add(raiz);
	}
	void ls(){
		Directorio d = rutaActiva.getLast();
		int k=d.contenido.size();
		for(i=0;i<k;i++){
			System.out.print(d.contenido.get(i).getName());
			System.out.println();
		}
	}
	void stat(String s){
		Directorio d = rutaActiva.getLast();
		int k=d.contenido.size();
		if(s.substring(0,1).equals("/")){
			//tratar ruta completa, split, acceder directorio a directorio hasta el final , ver si existe el elemento y devolver el tamaño
		}
		else{
			int i=0;
			boolean encontrado=false;
			while(i<k & !encontrado){
				if(d.contenido.get(i).getName().equals(s)){
					System.out.print(d.contenido.get(i).getSize());
					System.out.println();
					encontrado=true;
				}
				else{
					i++;
				}
			}
		}
		
	}
}
