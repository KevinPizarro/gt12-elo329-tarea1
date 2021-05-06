import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;


public class Stage1 {
    public static void main(String [] args) throws IOException {
        Locale.setDefault(new Locale("en", "US")); //Para asumir el punto como decimal
        if (args.length != 1) {
            System.out.println("Usage: java Stage1Main <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner s=new Scanner(new File(args[0]));
        //Leemos los parametros del archivo de configuración
        double simulationDuration = s.nextDouble();
        s.nextLine();
        double comunaWidth = s.nextDouble();
        double comunaLength = s.nextDouble();
        s.nextLine();
        double speed = s.nextDouble();
        double delta_t = s.nextDouble();
        double deltaAngle = s.nextDouble();
        double samplingTime = 1.0;  // 1 [s]
        Comuna comuna = new Comuna(comunaWidth, comunaLength); //Creamos la comuna
        Individuo person = new Individuo(comuna, speed, deltaAngle); //Creamos al individuo
        comuna.setPerson(person); //Ubicamos al individuo en la comuna
        Simulador sim = new Simulador(System.out, comuna); //Creamos el simulador
        sim.simulate(delta_t, simulationDuration,samplingTime); //Inicializamos la simulación
    }
}
