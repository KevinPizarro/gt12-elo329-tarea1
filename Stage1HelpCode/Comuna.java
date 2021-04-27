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
        return //??
    }
    public double getHeight() {
        return //??
    }
    public void setPerson(Individuo person){
        //??
    }
    public void computeNextState (double delta_t) {
        person.computeNextState(delta_t);
    }
    public void updateState () {
        person.updateState();
    }
    // include others methods as necessary
 }
