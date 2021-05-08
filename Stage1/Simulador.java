import java.io.PrintStream;

/**
 * Clase simulador, es aquél que da inicio a la ejecución de la simulación, controla los tiempos, salidas y el setup.
 */
public class Simulador {
    private Comuna comuna;
    private PrintStream out;

    /**
     * Es el constructor de la clase.
     * @param output Corresponde a la salida por consola.
     * @param comuna Corresponde a la comuna creada.
     */
    public Simulador (PrintStream output, Comuna comuna){
        out=output;
        this.comuna = comuna;
    }

    /**
     * Método para imprimir el encabezado del archivo de salida.
     */
    private void printStateDescription(){
        String s="time,\t"+Comuna.getStateDescription();
        out.println(s);
    }

    /**
     * Imprime por consola el estado de un individuo en t,x,y.
     * @param t Tiempo determinado.
     */
    private void printState(double t){
        String s = t + ",\t";
        s+= comuna.getState();
        out.println(s);
    }
    /**
     * Método principal para comenzar la simulación y llamar al cómputo de los estados siguientes.
     * @param delta_t Corresponde al paso de tiempo
     * @param endTime Tiempo de simulación
     * @param samplingTime  Tiempo entre delta_t.
     */
    public void simulate (double delta_t, double endTime, double samplingTime) {
        double t=0;
        printStateDescription();
        while (t<endTime) {
            for (double nextStop = t + samplingTime; t < nextStop; t += delta_t) {
                comuna.computeNextState(delta_t);
                comuna.updateState();
            }
            printState(t);
        }
    }
}