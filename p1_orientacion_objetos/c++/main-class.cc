#include "agrupacion-class.h"
#include <iostream>

int main(int argc,char* argv[])
{
	agrupacion<int> ag;
	ag.anyadir(42);
	ag.anyadir(15);
	ag.borrarUltimo();
	for (int i = 0; i < 100; i+=5) ag.anyadir(i);
	
	agrupacion<int>::const_iterator iter = ag.begin();
	while(iter != ag.end()) {
		std::cout<<*iter<<" ";
		++iter;		
	}
	std::cout<<std::endl;

//Opcionalmente, con esta nomenclatura, puede hacerse asi
	for (int i : ag) std::cout<<i<<" ";
	std::cout<<std::endl;
}
