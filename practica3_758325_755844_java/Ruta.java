import java.util.*;
import java.lang.*;

public class Ruta
{
	LinkedList<Directorio> rutaActiva = new LinkedList<Directorio>();
	public Ruta(Directorio raiz){
		rutaActiva.addFirst(raiz);
	}

	public String pwd() {
		int n = rutaActiva.size();
		String ruta= "";
		if(n>0) {
			for(int i=0;i<n;i++) {
				Directorio dir=rutaActiva.get(i);
				ruta=ruta + dir.getName() + "/";
			 }
		}
		return ruta;
	}

	public void ls(){
		Directorio d = rutaActiva.getLast();
		int k=d.contenido.size();
		for(int i=0;i<k;i++){
			System.out.print(d.contenido.get(i).getName());
			System.out.println();
		}
	}

	public void cd(String path) {
		if(!path.equals(".")) {
			if(path.equals("..") ) {
				Directorio eliminar=rutaActiva.removeLast();
			}
			else if(path.equals("/")) {
				Directorio raiz = rutaActiva.getFirst();
				rutaActiva.clear();
				rutaActiva.addFirst(raiz);
			}else if(path.charAt(0) == '/'){	//Ruta completa
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
				while( (nuevo != null) && (nuevo instanceof Directorio) && (i<nDirectorios) ){
					//Creo directorio nuevo
					
					nuevo = ultimo.existe_name(nueva_ruta[i]);
					Directorio nuevo2 = (Directorio)nuevo;
					rutaActiva.add(i,nuevo2);
					
					ultimo=nuevo2;
					//Creo otro directorio
					i++;
					
				}
			}else{		//Directorio nuevo
				Directorio ultimo = rutaActiva.getLast();
				Elemento nuevo = ultimo.existe_name(path);
				if ((nuevo != null) && (nuevo instanceof Directorio)){
					Directorio nuevo2 = (Directorio)nuevo;
					rutaActiva.addLast(nuevo2);
				}
			}
		}
	}

	public void stat(String s){
		if(s.substring(0,1).equals("/")){
			//si es ruta parcial buscamos a partir de la carpeta raiz
			String[] v=s.split("/");
			int vn = v.length;
			Directorio r = rutaActiva.getFirst();
			Elemento d = r.existe_name(v[0]);
			boolean existe=true;
			int i=0;
			while(i<vn && existe){
				if(d instanceof Directorio && d!=null){
					//si es directorio accedemos a el y buscamos la siguiente direccion de v
					i++;
					Directorio r2 = (Directorio)d;
					r=r2;
					d = r.existe_name(v[i]);
				}
				else{
					existe=false;
				}
			}
			if(existe){
				System.out.print(r.tamanyo);
				System.out.println();
			}
		}
		else{
			//si es ruta parcial buscamos a partir de la carpeta actual
			Directorio r = rutaActiva.getLast();
			Elemento d = r.existe_name(s);
			System.out.print(d.tamanyo);
			System.out.println();
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
			Enlace archivo1 = (Enlace)archivo;
			Elemento archivo2 = archivo1.apunto_a_archivo();
			//El elemento apuntado por un enlace es un archivo
			if( archivo2 instanceof Archivo ){
				archivo2.cambiar_tamanyo(size);
				archivo1.cambiar_tamanyo(size);
			}
		}
	}

	public void mkdir(String e){
		Directorio actual = rutaActiva.getLast();
		if(actual.existe_name(e)==null){
			Directorio nuevo = new Directorio(e);
			actual.anyadir_elemento(nuevo);
		}
	}

	public void ln (String orig, String dest){
		//Ruta completa
		if(orig.charAt(0) == '/'){
			String[] la_ruta = orig.split("/");
			int n=la_ruta.length;
			//System.out.print(n);
			Directorio ultimo = rutaActiva.getFirst();
			Elemento nuevo = ultimo.existe_name(la_ruta[1]);
			int i=1;
			//Compruebo que nuevo es un elemento
			while( (nuevo != null) && (nuevo instanceof Directorio) && (i<(n-1))){
				i++;
				Directorio nuevo2 = (Directorio)nuevo;
				ultimo=nuevo2;
				//Creo otro directorio
				nuevo = ultimo.existe_name(la_ruta[i]);
			}
			if(nuevo != null){
				Enlace es_new = new Enlace(dest, nuevo);
				Directorio actual = rutaActiva.getLast();
				actual.anyadir_elemento(es_new);
			}
		}
	}
	public void rm(String e){
		//Ruta completa
		if(e.charAt(0) == '/'){
			String[] la_ruta = e.split("/");
			int n=la_ruta.length;
			Directorio ultimo = rutaActiva.getFirst();
			Elemento nuevo = ultimo.existe_name(la_ruta[1]);
			int i=0;
			//Compruebo que nuevo es un elemento
			while( (nuevo != null) && (nuevo instanceof Directorio) && (i<(n-2))){
				i++;
				Directorio nuevo2 = (Directorio)nuevo;
				ultimo=nuevo2;
				//Creo otro directorio
				nuevo = ultimo.existe_name(la_ruta[i]);
			}
			if((nuevo != null) && (nuevo instanceof Directorio)){
				ultimo.borrar_elemento(la_ruta[i+1]);
			}
		}
		else{
			Directorio a = rutaActiva.getLast();
			Elemento d = a.existe_name(e);
			if(d instanceof Directorio){

			}
			else if(d instanceof Archivo){
	
			}
			else if(d instanceof Enlace){
				
			}
		}
		//akengew
		
	}
}