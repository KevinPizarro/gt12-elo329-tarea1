import java.awt.geom.Rectangle2D;

public class Comuna {
    private Individuo person;
    private Rectangle2D territory; // Alternatively: double width, length;

    public Comuna(){
        territory = new Rectangle2D.Double(0, 0, 1000, 1000); // 1000x1000 m²;
    }
    public Comuna(double width, double length){
        territory = new Rectangle2D.Double(0,0, width, length);
        person=null;
    }
    public double getWidth() {
        return territory.getWidth(); //??
    }
    public double getHeight() {
        return territory.getHeight(); //??
    }
    public void setPerson(Individuo person){
        this.person = person;
    }
    public void computeNextState (double delta_t) {
        person.computeNextState(delta_t);
    }
    public void updateState () {
        person.updateState();
    }
    public String getState() {
        return person.getState();
    }
    public static String getStateDescription() {
        return Individuo.getStateDescription();
    }
    // include others methods as necessary
 }
