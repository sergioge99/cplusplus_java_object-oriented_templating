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
		String[] v=s.split("/");
		int vn = v.length;
		if(s.substring(0,1).equals("/")){
			Directorio r = rutaActiva.getFirst();
			boolean existe=true;
			int i=0;
			while(i<vn && existe){
				Directorio r = r.existe_name(v[i]);
				if(r==null){
					existe=false;
				}
				else{
					i++;
				}
			}
			if(existe){
				System.out.print(r.tamanyo);
			}
			
		else{
			Directorio r = rutaActiva.getLast();
			boolean existe=true;
			int i=0;
			while(i<vn && existe){
				Directorio r = r.existe_name(v[i]);
				if(r==null){
					existe=false;
				}
				else{
					i++;
				}
			}
			if(existe){
				System.out.print(r.tamanyo);
			}
		}
		
	}
}
