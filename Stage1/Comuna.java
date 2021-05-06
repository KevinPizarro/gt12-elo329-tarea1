import java.awt.geom.Rectangle2D;

public class Comuna {
    private Individuo person;
    private Rectangle2D territory; // Alternatively: double width, length;

    public Comuna(){
        territory = new Rectangle2D.Double(0, 0, 1000, 1000); // 1000x1000 m²;
    }
    public Comuna(double width, double length){
        //Constructor de comuna, con parametros para agregar el alto y ancho
        territory = new Rectangle2D.Double(0,0, width, length);
        person=null;
    }
    public double getWidth() { //Método para obtener el ancho de la comuna
        return territory.getWidth();
    }
    public double getHeight() { //Método para obtener el alto de la comuna
        return territory.getHeight(); 
    }
    public void setPerson(Individuo person){ //Método para incluir al individuo en la comuna
        this.person = person;
    }
    public void computeNextState (double delta_t) { //Método para calcular el siguiente estado
        person.computeNextState(delta_t);
    }
    public void updateState () { //Método para actualizar la posición del individuo
        person.updateState();
    }
    public String getState() { //Método para obtener la posición de la persona
        return person.getState();
    }
    public static String getStateDescription() { //Método para obtener la descripción del estado
        return Individuo.getStateDescription();
    }
 }
