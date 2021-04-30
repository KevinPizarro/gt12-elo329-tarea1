import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;


public class Stage2 {
    public static void main(String [] args) throws IOException {
        Locale.setDefault(new Locale("en", "US")); //Para asumir el punto como decimal
        if (args.length != 1) {
            System.out.println("Usage: java Stage1Main <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner s=new Scanner(new File(args[0]));
        System.out.println("File: "+args[0]);
        double simulationDuration = s.nextDouble();
        System.out.println("Simulation time: "+simulationDuration);
        s.nextLine();
        double comunaWidth = s.nextDouble();
        double comunaLength = s.nextDouble();
        s.nextLine();
        double speed = s.nextDouble();
        double delta_t = s.nextDouble();
        double deltaAngle = s.nextDouble();
        double samplingTime = 1.0;  // 1 [s]
        Comuna comuna = new Comuna(comunaWidth, comunaLength);
        Individuo person = new Individuo(comuna, speed, deltaAngle);
        comuna.setPerson(person);
        Simulador sim = new Simulador(System.out, comuna);
        sim.simulate(delta_t, simulationDuration,samplingTime);
    }
}
