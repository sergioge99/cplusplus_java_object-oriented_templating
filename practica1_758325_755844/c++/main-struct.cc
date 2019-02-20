#include "agrupacion-struct.h"
#include <iostream>

int main(int argc,char* argv[])
{
	agrupacion<int> ag;
	iniciar(ag);
	anyadir(ag, 42);
	anyadir(ag, 15);
	borrarUltimo(ag);
	for (int i = 0; i < 100; i+=5) anyadir(ag, i);
	
	iniciarIterador(ag);
	bool error = false;
	while(existeSiguiente(ag) && (!error)) {
		std::cout<<siguiente(ag, error)<<" ";		
	}
	std::cout<<std::endl;
}
