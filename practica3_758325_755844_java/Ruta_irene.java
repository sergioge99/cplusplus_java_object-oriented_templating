//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Elemento;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Ruta
{
	LinkedList<Directorio> rutaActiva = new LinkedList<Directorio>();
	public Ruta(Directorio raiz){
		rutaActiva.add(raiz);
	}

	public String pwd() {
		int n = rutaActiva.size();
		String ruta= "/";
		if(n>1) {
			for(int i=1;i<=n;i++) {
				Directorio dir=rutaActiva.get(i);
				ruta=ruta + dir.getname() + "/";
			 }
		}
		return ruta;
	}


	public void cd(String path) {
		if(path != ".") {
			if(path == "..") {
				Directorio eliminar=rutaActiva.removeLast();
			}
			else if(path=="/") {
				int n = rutaActiva.size();
				rutaActiva.clear();
			}else if(path.atChar(0) == '/'){	//Ruta completa
				//Descomponemos path en un array de strings
				String[] nueva_ruta = path.split("/");
				int nDirectorios=nueva_ruta.length;
				Directorio ultimo = rutaActiva.getFirst();
				//Borramos la ruta activa para sustituirla por la nueva
				rutaActiva.clear();
				//Anyadimos raiz
				rutaActiva.addFirst(ultimo); 
				Elemento nuevo = ultimo.existe_name(nueva_ruta[1]);
				int i=1;
				//Compruebo que nuevo es un elemento
				while( (nuevo != null) && (nuevo instanceof Directorio) && (i<nDirectorios)){
					//Creo directorio nuevo
					rutaActiva.add(i,nuevo);
					i++;
					ultimo=nuevo;
					//Creo otro directorio
					nuevo = ultimo.existe_name(nueva_ruta[i]);
				}
			}else{		//Directorio nuevo
				Directorio ultimo = rutaActiva.getLast();
				Elemento nuevo = ultimo.existe_name(path);
				if ((nuevo != null) && (nuevo instanceof Directorio)){
					rutaActiva.addLast(nuevo);
				}
			}
		}
	}


	public void vim (String file, int size){
		//Extraigo directorio actual
		Directorio actual = rutaActiva.getLast();
		Elemento archivo = actual.existe_name(file);
		if ( archivo == null ){
			//No existe y por tanto lo creo
			archivo = new Archivo(file, size);
			//Lo situo en el directorio actual
			actual.anyadir_elemento(archivo);
		}
		else if( archivo instanceof Archivo ){
			//Cambiamos el tamanyo
			archivo.cambiar_tamanyo(size);
		}
		else if( archivo instanceof Enlace ){
			archivo = archivo.apunto_a_archivo();
			//El elemento apuntado por un enlace es un archivo
			if( archivo instanceof Archivo ){
				archivo.cambiar_tamanyo(size);
			}
		}
	}

	public void ln (String orig, String dest){
		//Ruta completa
		if(dest.atChar(0) == '/'){
			String[] la_ruta = orig.split("/");
			int n=la_ruta.length;
			Directorio ultimo = rutaActiva.getFirst();
			Elemento nuevo = ultimo.existe_name(la_ruta[1]);
			int i=1;
			//Compruebo que nuevo es un elemento
			while( (nuevo != null) && (nuevo instanceof Directorio) && (i<(n-1))){
				i++;
				ultimo=nuevo;
				//Creo otro directorio
				nuevo = ultimo.existe_name(nueva_ruta[i]);
			}
			if(nuevo != null){
				Enlace es_new = new Enlace(dest, nuevo);
				Directorio actual = rutaActiva.getLast();
				actual.anyadir_elemento(es_new);
			}
		}
	}
}

