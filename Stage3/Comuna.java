import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.math.*;

public class Comuna {
    private Individuo person;
    private Rectangle2D territory; // Alternatively: double width, length;
    private ArrayList<Individuo> personas;

    public Comuna(){
        territory = new Rectangle2D.Double(0, 0, 1000, 1000); // 1000x1000 m²;
    }
    public Comuna(double width, double length){
        territory = new Rectangle2D.Double(0,0, width, length);

    }
    public double getWidth() {
        return territory.getWidth(); //??
    }
    public double getHeight() {
        return territory.getHeight(); //??
    }
    public void setPerson(int n_ind, double time_recovery, int n_inf, Comuna comuna, double speed, double deltaAngle){
        personas = new ArrayList<Individuo>(n_ind);
        for(int i=0;i<n_ind;i++)
        {
            if(i<n_inf)
            {
                Individuo p=new Individuo(comuna, speed, deltaAngle);
                personas.add(p);
                personas.get(i).infect(time_recovery);
            }
            else
            {
                Individuo p=new Individuo(comuna, speed, deltaAngle);
                personas.add(p);
            }
        }
    }
    public void computeNextState (double delta_t, double distancia, double p0, double rec_time) {
        for (int i=0;i < personas.size();i++){
            for(int j=0; j< personas.size();j++){
                if(personas.get(i).getState()==State.S && personas.get(j).getState()==State.I){
                    double e = Math.sqrt(Math.pow(personas.get(i).getX()-personas.get(j).getX(),2)+Math.pow(personas.get(i).getY()-personas.get(j).getY(),2));
                    if(e < distancia){
                        if(Math.random()<=p0)
                        {
                            personas.get(i).infect(rec_time);
                        }

                    }
                }
            }
        }
        for(int i=0; i<personas.size();i++) {
            personas.get(i).computeNextState(delta_t);
        }
        
    }
    public void updateState () {

        for(int i=0; i<personas.size();i++) {
            personas.get(i).updateState();
        }
    }

    public String getIndState()
    {
        int sus=0,inf=0,rec=0;
        State status;
        for(int i=0; i<personas.size();i++)
        {
            status=personas.get(i).getState();
            switch (status) {
                case R:
                    rec++;
                    break;
                case I:
                    inf++;
                    break;
                case S:
                    sus++;
                    break;
            }
            
        }
        String s = inf + ",\t" + rec + ",\t" + sus;
        return s;
    }
    // include others methods as necessary
 }
