#ifndef AGRUPACION_H
#define AGRUPACION_H

// Interfaz del TAD agrupación genérico. Pre-declaraciones:
const int MAX = 40; //Límite tamaño de la agrupación, en esta implementación.

template<typename T> struct agrupacion;

//definir operaciones de agrupacion
template<typename T> void iniciar (agrupacion<T>& c);
template<typename T> bool anyadir (agrupacion<T>& c, const T& p);
template<typename T> bool borrarUltimo (agrupacion<T>& c);
//  -> Iterador
template<typename T> void iniciarIterador (agrupacion<T>& c);
template<typename T> bool existeSiguiente (const agrupacion<T>& c);
template<typename T> T siguiente (agrupacion<T>& c, bool& err);

// Declaración:
template<typename T>
struct agrupacion{
	friend void iniciar<T> (agrupacion<T>& c);
	friend bool anyadir<T> (agrupacion<T>& c, const T& p);
	friend bool borrarUltimo<T> (agrupacion<T>& c);

	friend void iniciarIterador<T> (agrupacion<T>& c);
	friend bool existeSiguiente<T> (const agrupacion<T>& c);
	friend T siguiente<T> (agrupacion<T>& c, bool& err);
	
private://declaración de la representación interna del tipo:
	T datos[MAX];
	int total;
	int iterador;
};

//ATENCIÓN: para tipos de datos genéricos
//la implementación de las operaciones también estará en agrupacion.h
template<typename T>
void iniciar(agrupacion<T>& c) {
	c.total = 0;
}

template<typename T>
bool anyadir (agrupacion<T>& c, const T& p) {
	bool sePuede = c.total < MAX;
	if (sePuede) {
		c.datos[c.total] = p;
		c.total++;
	}
	return sePuede;
}
template<typename T>
bool borrarUltimo (agrupacion<T>& c) {
	bool sePuede = c.total > 0;
	if (sePuede) c.total--;
	return sePuede;
}

template<typename T>
void iniciarIterador(agrupacion<T>& c) {
	c.iterador = (c.total - 1);
}

template<typename T>
bool existeSiguiente(const agrupacion<T>& c) {
	return c.iterador >= 0;
}

template<typename T>
T siguiente(agrupacion<T>& c, bool& err) {
	err = (c.iterador < 0);
	return c.datos[c.iterador--];
}

#endif //fin de agrupacion.h
