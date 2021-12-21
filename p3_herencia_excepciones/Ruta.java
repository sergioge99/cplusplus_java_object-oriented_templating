import java.util.*;
import java.lang.*;

public class Ruta
{
	LinkedList<Directorio> rutaActiva = new LinkedList<Directorio>();
	public Ruta(Directorio raiz)throws ExcepcionNoEsRaiz{
		if(raiz.getName().equals("")){
			rutaActiva.addFirst(raiz);
		}
		else{
			throw new ExcepcionNoEsRaiz();
		}
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

	public void cd(String path) throws ExcepcionNoExiste,ExcepcionNoEsDirectorio{
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
				while( (nuevo != null) && (i<nDirectorios) ){
					//Creo directorio nuevo
					if( nuevo instanceof Directorio ){
						nuevo = ultimo.existe_name(nueva_ruta[i]);
						Directorio nuevo2 = (Directorio)nuevo;
						rutaActiva.add(i,nuevo2);
						
						ultimo=nuevo2;
						//Creo otro directorio
						i++;
					}else{
						throw new ExcepcionNoExisteRuta();
					}
				}
			}else{		//Directorio nuevo
				Directorio ultimo = rutaActiva.getLast();
				Elemento nuevo = ultimo.existe_name(path);
				if ((nuevo != null) && (nuevo instanceof Directorio)){
					Directorio nuevo2 = (Directorio)nuevo;
					rutaActiva.addLast(nuevo2);
				}else if( nuevo == null ){
					throw new ExcepcionNoExisteDirectorio();
				}else{
					throw new ExcepcionNoEsDirectorio();
				}
			}
		}
	}

	public void stat(String element) throws ExcepcionBucle, ExcepcionNoExiste{
		if(element.charAt(0) == '/'){
			String[] la_ruta = element.split("/");
			int n=la_ruta.length;
			Directorio ultimo = rutaActiva.getFirst();
			Elemento nuevo = ultimo.existe_name(la_ruta[1]);
			int i=1;
			//Compruebo que nuevo es un elemento
			while( i<n-1 ){
				if((nuevo != null) && (nuevo instanceof Directorio)){
					i++;
					Directorio nuevo2 = (Directorio)nuevo;
					ultimo=nuevo2;
					//Creo otro directorio
					nuevo = ultimo.existe_name(la_ruta[i]);
				}else{
					throw new ExcepcionNoExisteRuta();
				}
				
			}
			if(nuevo != null && n>1){
				System.out.print(nuevo.getSize(0));
				System.out.println();
			}

		}
		else{
			//si es ruta parcial buscamos a partir de la carpeta actual
			Directorio ultimo = rutaActiva.getLast();
			Elemento nuevo = ultimo.existe_name(element);
			if(nuevo!=null){
				System.out.print(nuevo.getSize(0));
				System.out.println();
			}else{
				throw new ExcepcionNoExisteElemento();
			}
		}
	}

	public void vim (String file, int size) throws ExcepcionNoPermitido, ExcepcionElementoExistente{
		//Extraigo directorio actual
		if (file.charAt(0) == '/' ){
			throw new ExcepcionRutaNoPermitida();
		}
		Directorio actual = rutaActiva.getLast();
		Elemento archivo = actual.existe_name(file);
		if ( archivo == null ){
			if(!file.matches("^[A-Za-z]+$")){
				throw new ExcepcionNombreNoPermitido();
			}
			else{
				//No existe y por tanto lo creo
				archivo = new Archivo(file, size);
				//Lo situo en el directorio actual
				actual.anyadir_elemento(archivo);
			}
			
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
				archivo1.cambiar_tamanyo(size);
			}
		}else{
			throw new ExcepcionElementoExistente();
		}
	}

	public void mkdir(String dir) throws ExcepcionNoPermitido, ExcepcionElementoExistente{
		if (dir.charAt(0) == '/' ){
			throw new ExcepcionRutaNoPermitida();
		}
		Directorio actual = rutaActiva.getLast();
		if(actual.existe_name(dir)==null){
			if(!dir.matches("^[A-Za-z]+$")){
				throw new ExcepcionNombreNoPermitido();
			}
			else{
				Directorio nuevo = new Directorio(dir);
				actual.anyadir_elemento(nuevo);
			}
		}else{
			throw new ExcepcionElementoExistente();
		}
	}

	public void ln (String orig, String dest) throws ExcepcionNoPermitido, ExcepcionNoExiste, ExcepcionElementoExistente{
		if (dest.charAt(0) == '/' ){
			throw new ExcepcionRutaNoPermitida();
		}
		//Ruta completa
		if(orig.charAt(0) == '/'){
			String[] la_ruta = orig.split("/");
			int n=la_ruta.length;
			System.out.print(n);
			Directorio ultimo = rutaActiva.getFirst();
			Elemento nuevo = ultimo.existe_name(la_ruta[1]);
			int i=1;
			//Compruebo que nuevo es un elemento
			while( (nuevo != null) && (i<(n-1))){
				if( nuevo instanceof Directorio ) {
					i++;
					Directorio nuevo2 = (Directorio)nuevo;
					ultimo=nuevo2;
					//Creo otro directorio
					nuevo = ultimo.existe_name(la_ruta[i]);
				}else{
					throw new ExcepcionNoExisteRuta();
				}
			}
			if(nuevo != null){
				Enlace es_new = new Enlace(dest, nuevo);
				Directorio actual = rutaActiva.getLast();
				actual.anyadir_elemento(es_new);
			}else{
				throw new ExcepcionElementoExistente();
			}
		}
	}
	public void rm(String e) throws ExcepcionNoExiste{
		//Ruta completa
		if(e.charAt(0) == '/'){
			String[] la_ruta = e.split("/");
			int n=la_ruta.length;
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
			if(nuevo != null ){
				ultimo.borrar_elemento(la_ruta[i]);
			}else{
				throw new ExcepcionNoExisteRuta();
			}
		}
		else{
			Directorio ultimo = rutaActiva.getLast();
			Elemento nuevo = ultimo.existe_name(e);
			if(null != nuevo){
				ultimo.borrar_elemento(e);
			}
			else{
				throw new ExcepcionNoExisteElemento();
			}
		}
	}
}