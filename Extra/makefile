JFLAGS = -g
JC = javac
JVM= java 
FILE= Configuracion.txt
FILE2= Salida.csv

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Comuna.java \
	Individuo.java \
	Extra.java \
	Simulador.java

MAIN = Extra

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) $(FILE) > $(FILE2)

clean:
	$(RM) *.class
