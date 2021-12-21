#pragma once
#include <string>
#include <iostream>

class Transportable {
protected:
	double volumen;
public:
    Transportable () {}
	Transportable (double volumen_ ) : volumen(volumen_) {}
};

class Generico : public Transportable{
public:
    Generico () {}
    virtual double vol() const{};
};

class Elemento : public Transportable{
protected:
    std::string nombre;
public:
    Elemento (double volumen_, const std::string& nombre_ ) : Transportable(volumen_), nombre(nombre_){}
    double vol() const {
        return this->volumen;
    }
};

class Producto : public Elemento, public Generico{
public:
    Producto (double volumen_, const std::string& nombre_) : Elemento(volumen_,nombre_){}
};

class Toxico : public Elemento{
public:
    Toxico (double volumen_, const std::string& nombre_) : Elemento(volumen_,nombre_){}
};

class SerVivo : public Elemento{
public:
    SerVivo (double volumen_, const std::string& nombre_) : Elemento(volumen_,nombre_){}
};

template <typename T>
class Transportador {
protected:
	double capacidad;
    double libre;
public:
	Transportador (double capacidad_ ) : capacidad(capacidad_), libre(capacidad_) { std::cout<< capacidad << "libre" <<libre<<std::endl;}
	
	virtual double vol() const{
        return capacidad;
    }

    bool guardar(const T &elemento) {
        if(elemento.vol() <= libre){
            std::cout<< "bish" << elemento.vol()<<std::endl;
            libre=libre-elemento.vol();
            return true;
        }else{
            return false;
        }
    }
};

template <typename T>
class Contenedor : public Generico, public Transportador<T>{
public:
    Contenedor (double capacidad_) : Transportador<T>(capacidad_){std::cout<< "cont cap" << this->capacidad<<std::endl;}
    double vol(){
        return this->capacidadcapacidad;
    }
};


class Camion : public Transportador<Generico>{
public:
    Camion (double capacidad_) : Transportador<Generico>(capacidad_){}
};