import java.io.PrintStream;
import java.io.File;
import java.io.FileWriter;

public class Simulador {
    private Comuna comuna;
    private PrintStream out;

    //private Simulador(){ }
    public Simulador (PrintStream output, Comuna comuna){
        out=output;
        this.comuna = comuna;
    }
    private void printStateDescription(){;
        String s="time,\t"+Comuna.getStateDescription();
        out.println(s);
    }
    /* private void printState(double t){
        String s = t + ",\t";
        s+= comuna.getState();
        out.println(s);
    } */
    public void printxt(String s){
        File file = new File("salida.csv");
        try {
            file.createNewFile();
        }
        catch (Exception e){
            e.getStackTrace();
        }
        try {
            FileWriter output = new FileWriter("salida.csv");
        output.write(s);
        output.close();
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    /**
     * @param delta_t time step
     * @param endTime simulation time
     * @param samplingTime  time between printing states to not use delta_t that would generate too many lines.
     */
    public void simulate (double delta_t, double endTime, double samplingTime) {  // simulate time passing
        double t=0;
        String s = "";
        printStateDescription();
        s = t + ",\t" + comuna.getState() + '\n';
        while (t<endTime) {
            for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
                comuna.computeNextState(delta_t); // compute its next state based on current global state
                comuna.updateState();            // update its state
            }
            s += t + ",\t" + comuna.getState() + '\n';
        }
        printxt(s);
    }
}