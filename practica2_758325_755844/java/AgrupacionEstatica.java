//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

//En Java, no existe el concepto de pre-declaracion ni de funcion. Java es un 
//lenguaje puramente orientado a objetos, por lo que todo son clases.
//
//El uso de programación paramétrica no requiere ninguna palabra clave. Directamente ponemos
//el tipo parámetro entre corchetes.
//Para hacer uso de iteradores, implementamos el interfaz Iterable<T>. Esto es un mecanismo
//básico de la herencia en Java que es inevitable en este caso y sobre el que hablaremos
//más adelante en clase de teoría.
public class AgrupacionEstatica<T> implements Iterable<T>
{
	//Esta es la única forma que tiene Java de declarar valores inmutables.
	private static final int MAX = 40; 

	//Habrás notado que en Java no hay bloques "private" y "public", sino que es una palabra clave
	//que se pone en cada elemento (atributo o método).
	private T[] datos;
	private int total;

	//El constructor de Java llama a los constructores de los parámetros dentro del bloque de código.
	@SuppressWarnings("unchecked")
	public AgrupacionEstatica()
	{
		datos = (T[])(new Object[MAX]);
		total = 0;
	}

	public boolean anyadir(T t) 
	{
		boolean sePuede = total < MAX;
		if(sePuede) {
			datos[total] = t;
			total++;
		}
		return sePuede;
	}

	public boolean borrarUltimo()
	{
		boolean sePuede = total > 0;
		if(sePuede) {
			total--;
		}
		return sePuede;
	}

	//Esta clase representa a un iterador sobre la agrupación. De nuevo, por el comportamiento estándar de los
	//iteradores en Java, deberemos utilizar la herencia.
	private class IteradorAgrupacion<T> implements Iterator<T> 
	{
		//Aquí declaramos los atributos
		AgrupacionEstatica<T> ag;
		int i;

		//Este es el constructor del iterador.
		private IteradorAgrupacion(AgrupacionEstatica<T> ag) 
		{
			this.ag = ag;
			i       = ag.total - 1;
		}

		//Todos los iteradores deben de implementar un método que devuelva
		//si hay siguiente elemento (o no).
		public boolean hasNext()	
		{
			return i>=0;
		}

		//Todos los iteradores deben de implementar un método que devuelva el elemento
		//en la posción actual y avance el iterador. Por construcción, este método debe
		//lanzar una excepción si no existe el siguiente elemento.
		//Recuerda que se recorren desde el último al primero.
		public T next() throws NoSuchElementException
		{
			//Aquí lanzamos la excepción
			if (!hasNext()) throw new NoSuchElementException();
			else {
				T t = ag.datos[i];
				i--;
				return t;
			}
		}

		//Los iteradores en Java tienen que tener este método, también. Para no implementarlo (no lo vamos
		//a usar y nuestra definición de agrupación no lo incluye) simplemente lanzamos una excepción.
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}
	}

	//Este método de la estructura de datos simplemente devuelve un nuevo iterador con el que recorrerse la estructura de datos.
	public Iterator<T> iterator()
	{
		return new IteradorAgrupacion<T>(this);
	}
}
