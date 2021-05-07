import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
/**
 * En la clase Stage4, se abre el archivo de configuración, y se leen los parametros a utilizar en la simulación, 
 * los cuales se entregan a la clase comuna y la clase simulador
 */

public class Stage4 {
    public static void main(String [] args) throws IOException {
        Locale.setDefault(new Locale("en", "US")); //Para asumir el punto como decimal
        if (args.length != 1) {
            System.out.println("Usage: java Stage4 Main <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner s=new Scanner(new File(args[0]));
        double simulationDuration = s.nextDouble();
        int Npeople = s.nextInt(); 
        int Iinfected = s.nextInt();
        double rec_Time = s.nextDouble();
        s.nextLine();
        double comunaWidth = s.nextDouble();
        double comunaLength = s.nextDouble();
        s.nextLine();
        double speed = s.nextDouble();
        double delta_t = s.nextDouble();
        double deltaAngle = s.nextDouble();
        s.nextLine();
        double d = s.nextDouble();
        double m = s.nextDouble();
        double p0 = s.nextDouble();
        double p1 = s.nextDouble();
        double p2 = s.nextDouble();
        s.nextLine();
        int numVac = s.nextInt();
        double vacsize = s.nextDouble();
        double vactime = s.nextDouble();
        double samplingTime = 1.0;  // 1 [s]
        Comuna comuna = new Comuna(comunaWidth, comunaLength);
        Simulador sim = new Simulador(System.out, comuna, Npeople, Iinfected, rec_Time, speed, deltaAngle, d, m, p0, p1, p2, numVac, vacsize, vactime);
        sim.simulate(delta_t, simulationDuration,samplingTime);
    }
}
