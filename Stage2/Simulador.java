import java.io.PrintStream;

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
     * Constructor con 2 parámetros. Asume todos los parámetros en 0.
     * @param output Salida por consola.
     * @param comuna Setup del espacio inicial de la comuna.
     */
    public Simulador (PrintStream output, Comuna comuna){
        out=output;
        this.comuna = comuna;
        this.totalPerson = 0;
        this.initialInfected = 0;
        this.rec_time = 0;
        this.speed = 0;
        this.delta_angle = 0;
        this.distance = 0;
        this.p0 = 0;
    }

    /**
     * Constructor con 9 parámetros. Se le entrega un setup a seguir para individuos iniciales.
     * @param output Salida por consola.
     * @param comuna Setup del espacio inicial de la comuna.
     * @param totalPerson Cantidad de individuos totales.
     * @param initialInfected Cantidad de infectados iniciales.
     * @param rec_time Tiempo de recuperacion para los individuos.
     * @param speed Velocidad de los individuos.
     * @param delta_angle Diferencial del angulo.
     * @param distance Distancia entre individuos para infectarse.
     * @param p0 Probabilidad de contagio dos individuos sin mascarilla.
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
     * Método para imprimir el encabezado del archivo de salida.
     */
    private void printStateDescription(){
        String s="Time, Inf, Rec, Sus";
        out.println(s);
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
    public void simulate (double delta_t, double endTime, double samplingTime) {
        double t = 0;
        comuna.setPerson(totalPerson, rec_time, initialInfected, comuna, speed, delta_angle);
        printStateDescription();
        printState(t);
        while (t<endTime) {
            for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
                comuna.computeNextState(delta_t, distance, p0, rec_time);
                comuna.updateState();
            }
            printState(t);
        }
    }
}