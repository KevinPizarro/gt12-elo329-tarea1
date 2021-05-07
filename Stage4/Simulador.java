import java.io.PrintStream;


/*
 Cuarta Etapa: En esta última etapa se espera alcanzar la funcionalidad descrita en la sección 1. Utilice un archivo
de entrada con el formato señalado allí y genere un archivo de salida como el indicado en sección 1.
Usando una planilla de cálculo, manipule los datos para generar la gráfica pedida la cual usted
incluirá en su documentación de la tarea.

*/

/**
 * Clase simulador, es aquél que da inicio a la ejecución de la simulación, controla los tiempos, salidas y el setup.
 */
public class Simulador {
    private Comuna comuna;
    private PrintStream out;
    private int totalPerson;
    private int initialInfected;
    private double rec_time;
    private double speed;
    private double delta_angle;
    private double distance;
    private double m;
    private double p0;
    private double p1;
    private double p2;
    private int numVac;
    private double vacsize;
    private double vactime;
    /**
     * Constructor con 2 parámetros. Asume una lista vacía y infectados iniciales 0.
     * @param output Salida por consola.
     * @param comuna Setup del espacio inicial de la comuna.
     */
    public Simulador (PrintStream output, Comuna comuna){
        out=output;
        this.comuna = comuna;
        this.initialInfected = 0;
        this.totalPerson = 0;
        this.distance = 0;
        this.rec_time = 0;
    }

    /**
     * Constructor con 12 parámetros. Se le entrega un setup a seguir para individuos iniciales.
     * @param output Salida por consola.
     * @param comuna Setup del espacio inicial de la comuna.
     * @param initialInfected Cantidad de infectados iniciales.
     * @param totalPerson Cantidad de individuos totales.
     * @param rec_time Tiempo de recuperacion para los individuos.
     * @param speed Velocidad de los individuos
     * @param deltaAngle diferencial del angulo
     * @param distancia Distancia entre individuos para infectarse
     * @param m Fraccion de individuos con mascarilla
     * @param p0 Probabilidad de contagio dos individuos sin mascarilla
     * @param p1 Probabilidad de contagio un individuo sin mascarilla
     * @param p2 Probabilidad de contagio dos individuos con mascarilla
     * @param numVac Cantidad de vacunatorios
     * @param vacsize Tamaño del lado de los vacunatorios
     * @param vactime Tiempo que pasa despues del inicio para iniciar los vacunatorios
     */
    public Simulador (PrintStream output, Comuna comuna, int totalPerson, int initialInfected, double rec_time, double speed, double delta_angle, double distance, double m, double p0, double p1, double p2, int numVac, double vacsize, double vactime){
        out=output;
        this.comuna = comuna;
        this.totalPerson = totalPerson;
        this.initialInfected = initialInfected;
        this.rec_time = rec_time;
        this.speed = speed;
        this.delta_angle = delta_angle;
        this.distance = distance;
        this.m = m;
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.numVac = numVac;
        this.vacsize = vacsize;
        this.vactime = vactime;
    }

    /**
     * Imprime por consola el estado de un individuo en t,x,y.
     * @param t Tiempo determinado.
     */
    private void printState(double t){
        String s = t + ",\t";
        s += comuna.getIndState();
        out.println(s);
    }

    /**
     * Método principal para realizar la simulación y coordinar los tiempos en pasos delta_t.
     * @param delta_t time step
     * @param endTime simulation time
     * @param samplingTime  time between printing states to not use delta_t that would generate too many lines.
     */
    public void simulate (double delta_t, double endTime, double samplingTime) {  // simulate time passing
        double t = 0;
        comuna.setvac(numVac, vacsize);
        comuna.setPerson(totalPerson, rec_time, initialInfected, comuna, speed, delta_angle, m);
        /**
         * Mientras el tiempo actual sea menor al tiempo de termino de simulación, seguiremos computando estados siguientes.
         */
        System.out.println("Time, Vac, Inf, Rec, Sus");
        printState(t);
        while (t<endTime) {
            for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
                comuna.computeNextState(delta_t, distance, p0, p1, p2, rec_time, vactime, t); // compute its next state based on current global state
                comuna.updateState();            // update its state
            }
            printState(t);
        }
        comuna.MostrarCuadro();
    }
}