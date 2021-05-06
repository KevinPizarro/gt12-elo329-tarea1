import javax.swing.plaf.synth.Region;
import java.io.PrintStream;
import java.io.File;
import java.io.FileWriter;


/*
 Segunda Etapa: Individuos se mueven aleatoriamente y pueden contagiarse. Esta etapa es similar a la previa (sin vacuna,
 sin mascarilla), excepto que se crean N individuos los cuales son almacenados en un ArrayList <individuo>.
 La clase simulador crea y ubica I individuosinfectados y (N-I) individuos susceptibles de infectarse.
 La clase Individuo se debe completar para reflejar la interacción entre individuos.Como salida se espera un archivo de
 salida similar al indicado en la descripción general de la tarea,excepto que no hay columna V.
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
    private double p0;
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
     * Constructor con 4 parámetros. Se le entrega un setup a seguir para individuos iniciales.
     * @param output Salida por consola.
     * @param comuna Setup del espacio inicial de la comuna.
     * @param initialInfected Cantidad de infectados iniciales.
     * @param totalPerson Cantidad de individuos totales.
     * @param rec_time Tiempo de recuperacion para los individuos.
     */
    public Simulador (PrintStream output, Comuna comuna, int totalPerson, int initialInfected, double rec_time, double speed, double delta_angle, double distance, double p0){
        out=output;
        this.comuna = comuna;
        this.totalPerson = totalPerson;
        this.initialInfected = initialInfected;
        this.rec_time = rec_time;
        this.speed = speed;
        this.delta_angle = delta_angle;
        this.distance = distance;
        this.p0 = p0;
    }

    /**
     * Imprime por consola el estado de un individuo en t,x,y.
     * @param t Tiempo determinado.
     */
    private void printState(double t){
        //TODO: Cambiar de getState() a nuevo método de comuna.
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
        //TODO: Llamar a comuna para que infecte
        comuna.setPerson(totalPerson, rec_time, initialInfected, comuna, speed, delta_angle);
        /**
         * Mientras el tiempo actual sea menor al tiempo de termino de simulación, seguiremos computando estados siguientes.
         */
        System.out.println("Time, Inf, Rec, Sus");
        printState(t);
        while (t<endTime) {
            for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
                comuna.computeNextState(delta_t, distance, p0, rec_time); // compute its next state based on current global state
                comuna.updateState();            // update its state
            }
            printState(t);
        }
    }
}