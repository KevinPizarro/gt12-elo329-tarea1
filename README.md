# Tarea1

	Tarea 1. Simulador de pandemia para el ramo ELO329.

La tarea cuenta con 5 carpetas, cada una resuelve una etapa de esta
y todas poseen los siguientes archivos:

un archivo con el nombre de la etapa .java
Simulador.java
Individuo.java
Comuna.java
Makefile
Configuracion.txt : Archivo el cual contiene los parametros de la simulación de la forma:
		<T. de Simulación [s]> <N. de Individuos> <N. de Infectados> <T. que dura la infección [s] > 
		<Ancho de la comuna [m]> <Alto de la comuna [m] >
		<Velocidad de los individuos [m/s] > <∆t [s] > <∆θ [rad] >
		<Distancia de contagio [m] > <Razón de uso de mascarilla> <p0> <p1> <p2> //Probabilidades de Contagio
		<Cant. de Vacunatorios> <Tamano de los Vac.> <Tiempo para empezar a Vacunar [s] >

 Para ejecutar cada etapa del programa se descarga la carpeta y se escribe en consola el siguiente comando:
    make
 Luego:
	make run
(asegurarse de estar en la misma carpeta)

 Posteriormente se escribe el comando:
	make clean
este elimina los .class generados al compilar.

Se decide optar por la etapa Extra, se encuentra en la carpeta Extra
