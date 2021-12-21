#include "practica4.h"
#include <iostream>

int main(int argc, char** argv)
{
	Camion camion(20);
	Producto             apuntes_tepro(1.5, "Apuntes de Tecnologia de Programacion");

	if (camion.guardar(apuntes_tepro))       std::cout<<"Camion lleno con apuntes de tepro"<<std::endl;
	
	Producto trenzas_almudevar(10.5, "Trenzas de Almudevar");
	if (camion.guardar(trenzas_almudevar))   std::cout<<"Camion lleno con trenzas de Almudevar"<<std::endl;

}

