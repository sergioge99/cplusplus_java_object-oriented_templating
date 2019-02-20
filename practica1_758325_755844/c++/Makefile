.SUFFIXES: .cc .o

PROG=main-struct main-class
SRCS=main-struct.cc main-class.cc
OBJS=$(SRCS:.cc=.o)

all: $(PROG)

main-struct: main-struct.o 
	g++ -std=c++11 -Wall -o main-struct $^

main-class: main-class.o 
	g++ -std=c++11 -Wall -o main-class $^

%.o: %.cc
	g++ -std=c++11 -Wall -c $<

.PHONY: edit go

edit:
	@geany -s -i $(SRCS) &

go:
	@$(PROG)

clean:
	@rm -f $(PROG) *.o core tags

main-struct.o: agrupacion-struct.h
main-class.o:  agrupacion-class.h
