import java.io.PrintStream;
import java.io.File;
import java.io.FileWriter;

public class Simulador {
    private Comuna comuna;
    private PrintStream out;

    //private Simulador(){ }
    public Simulador (PrintStream output, Comuna comuna){ //Constructor del simulador
        out=output;
        this.comuna = comuna;
    }
    private void printStateDescription(){; //Método para imprimir el encabezado de la salida
        String s="time,\t"+Comuna.getStateDescription();
        out.println(s);
    }
    private void printState(double t){ //Método para imprimir la posición del individuo
        String s = t + ",\t";
        s+= comuna.getState();
        out.println(s);
    }
    /**
     * @param delta_t time step
     * @param endTime simulation time
     * @param samplingTime  time between printing states to not use delta_t that would generate too many lines.
     */
    public void simulate (double delta_t, double endTime, double samplingTime) {  // simulate time passing
        double t=0;
        printStateDescription();
        while (t<endTime) {
            for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
                comuna.computeNextState(delta_t); // compute its next state based on current global state
                comuna.updateState();            // update its state
            }
            printState(t);
        }
        
    }
}