#ifndef AGRUPACION_DINAMICA_H
#define AGRUPACION_DINAMICA_H


// Interfaz del TAD agrupación genérico. Pre-declaraciones:

template<typename T>
class agrupacion_dinamica{
private: 
    friend class nodoAgrupacion;
    class nodoAgrupacion{
        public:
		T dato;
		nodoAgrupacion* sig;

		nodoAgrupacion(const T& dato_, nodoAgrupacion* sig_):dato(dato_), sig(sig_){}
		~nodoAgrupacion(){
			//delete(sig);
		}
	};
	
	//Mantenemos aqui los mismos atributos privados, ignorando aquellos que tengan que ver
	//con el iterador
	int total;
	nodoAgrupacion* primero;
    nodoAgrupacion* ultimo;

public:
//	Ahora la funcion iniciar de la estructura es el constructor. 
	agrupacion_dinamica()
	{ 
	    total = 0;
        primero = nullptr;
        ultimo = nullptr;
	}

//	TODO: La funcion anyadir ahora es el metodo anyadir. Rellénalo. Para acceder a atributos
//	y métodos de la propia clase, deberás hacerlo a través del puntero this->
	bool anyadir(const T& p)
	{
		if(ultimo == nullptr){
            nodoAgrupacion* nuevo = new nodoAgrupacion(p,nullptr);
            primero = nuevo;
			ultimo = nuevo;
		}
		else{ //No es el primero que anyadimos
			nodoAgrupacion* nuevo = new nodoAgrupacion(p,ultimo);
            ultimo = nuevo;
		}
		total++;
		return true;
	}

//	TODO: La funcion borrarUltimo se transforma tambien en un metodo. Rellénalo.
	bool borrarUltimo()
	{
		bool sePuede = (ultimo != nullptr);
		if(sePuede){
			nodoAgrupacion* aux = ultimo;
			ultimo=aux->sig;
			if(total==1){primero=nullptr;}
			total--;
			delete(aux);
		}
		return sePuede;
	}

	//Declarando la clase const_iterator (iterador constante) como friend y como
	//clase local, conseguimos que pueda haber varios iteradores, cada uno de ellos
	//llevando su propio "puntero" a que parte de la estructura estamos
	friend class const_iterator;
       
	class const_iterator {
	private:
		//Al separar el iterador en una clase aparte, trasladamos los miembros privados que estaban
		//en el struct a esta clase. Además, mantenemos una referencia a la agrupacion.
		const agrupacion_dinamica<T>& c;
        nodoAgrupacion* iter;
	public:
		//Este constructor sirve como inicializador del iterador, tanto al principio como al final.
		const_iterator(const agrupacion_dinamica& c_, nodoAgrupacion* i) : c(c_), iter(i) {}
		
	//	En la definición por defecto de los iteradores, se separa el avance del iterador
	//	a la obtención del elemento apuntado por el iterador (que en la otra implementación ocurren
	//	en la misma función.
	
		//Este método redefine el operador de pre-incremento (++x).
		//Representa el avance del iterador.
		const_iterator& operator++()   
	       	{ 	
			//TODO: Rellena este hueco para que el iterador sobre la agrupación avance. Recuerda
			//que en nuestra definición de agrupación la estructura se recorre desde el último
			//elemento introducido hasta el primero (como si se tratara de una pila).  
			if(iter != nullptr){
                iter = iter->sig;
            } 
			return (*this);
	       	}

		//Este método redefine el operador de "apuntado" (*x)
		//Representa la obtención de lo apuntado por el iterador.
		const T& operator*()   const 
		{
			//TODO: Rellena este método para que devuelva el elemento T al que está apuntando el iterador.
			return iter->dato;	
		} 
	//	En la definición por defecto de los iteradores, no existe la comprobación de si existe
	//	siguiente elemento. Por defecto las estructuras de datos devuelven iteradores al principio y al final,
	//	y para recorrer la estructura se compara el iterador que avanza con el iterador que apunta al final
	//	de la estructura.
	//
		bool operator!=(const const_iterator& that) const 
		{ 
			//TODO: Rellenar este método para devuelva true si este iterador y el iterador "that" apuntan
			//a sitios diferentes, y false en caso contrario. 
			return iter != that.iter;
		}		

	};

	//Observa como nuestra agrupacion permite generar dos iteradores: uno al principio y otro al final.
	//El iterador que apunta al final de la agrupación se mantiene en este caso únicamente para hacer
	//la comprobación de que "existe siguiente".
	//Date cuenta que los valores que le pasamos como índice del iterador son para que se recorra la
	//estructura desde el último elemento (this->total - 1) hasta el primero (0).
	const_iterator begin() const { return const_iterator(*this, ultimo); }
	const_iterator end()   const { return const_iterator(*this, nullptr); }
};



#endif //fin de agrupacion.h
