import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class Comuna {
    private Rectangle2D territory; // Alternatively: double width, length;
    private ArrayList<Individuo> personas;
    private ArrayList<Rectangle2D.Double> vacunatorios;

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
    public void setvac(int numVac, double vacsize){
        vacunatorios = new ArrayList<Rectangle2D.Double>(numVac);
        double x;
        double y;
        for(int i = 0; i < numVac; i++){
            x = Math.random()*territory.getWidth();
            y = Math.random()*territory.getHeight();
            if(x >= territory.getWidth() - vacsize){
                x -= vacsize;
            }
            if(y >= territory.getHeight() - vacsize){
                y -= vacsize;
            }
            if(i == 0){
                vacunatorios.add(new Rectangle2D.Double(x, y, vacsize, vacsize));
            }
            else{
                for(int j = 0; j < i; j++){
                    Rectangle2D vac = new Rectangle2D.Double(x,y,vacsize,vacsize);
                    if(existvac(x, y) || intervac(vac)){
                        while(existvac(x, y) || intervac(vac)){
                            x = Math.random()*territory.getWidth();
                            y = Math.random()*territory.getHeight();
                            if(x >= territory.getWidth() - vacsize){
                                x -= vacsize;
                            }
                            if(y >= territory.getHeight() - vacsize){
                                y -= vacsize;
                            }
                            vac.setRect(x, y, vacsize, vacsize);
                        }
                    }
                }
                vacunatorios.add(new Rectangle2D.Double(x, y, vacsize, vacsize));
            }
        }

    }
    public void MostrarCuadro(){
        LinesRectsOvals application = new LinesRectsOvals();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE); 
    }
    public class LinesRectsOvals extends JFrame {

        // set window's title bar String and dimensions
        public LinesRectsOvals()
        {
        super( "Drawing lines, rectangles and ovals" );
        setSize( 1000, 1000 );
        setVisible( true );
        }
        // display various lines, rectangles and ovals
        public void paint( Graphics g ){
            super.paint( g ); // call superclass's paint method
            g.setColor( Color.RED );
            g.drawRect( 100, 100, 500, 500 );
            for(int i = 0; i < vacunatorios.size(); i++ ){
                g.setColor( Color.BLUE );
                g.drawRect( 100+(int)vacunatorios.get(i).getX(), 100+(int)vacunatorios.get(i).getY(), 100, 100 );        
            }
        } // end method paint
    }
    public boolean intervac(Rectangle2D nuevo){
        for(int i = 0; i < vacunatorios.size(); i++){
            if(nuevo.intersects(vacunatorios.get(i))){
                return true;
            }
        }
        return false;
    }
    public boolean existvac(double x, double y){
        for(int i = 0; i < vacunatorios.size(); i++){
            if(vacunatorios.get(i).contains(x,y)){
                return true;
            }
        }
        return false;
    }
    public void setPerson(int n_ind, double time_recovery, int n_inf, Comuna comuna, double speed, double deltaAngle, double m){
        personas = new ArrayList<Individuo>(n_ind);
        for(int i=0;i<n_ind;i++){
            if(i<n_inf){
                Individuo p=new Individuo(comuna, speed, deltaAngle);
                personas.add(p);
                personas.get(i).infect(time_recovery);
                if(i<n_inf*m){
                    personas.get(i).putmask();
                }
            }
            else{
                Individuo p=new Individuo(comuna, speed, deltaAngle);
                personas.add(p);
                if(i-n_inf<(n_ind-n_inf)*m){
                    personas.get(i).putmask();
                }
            }
        }
    }
    public void computeNextState (double delta_t, double distancia, double p0, double p1, double p2, double rec_time, double vactime, double t) {
        for (int i=0;i < personas.size();i++){
            for(int j=0; j< personas.size();j++){
                if(personas.get(i).getState() == State.S && existvac(personas.get(i).getX(), personas.get(i).getY()) && t >= vactime){
                    personas.get(i).vaccine();
                }
                if(personas.get(i).getState()==State.S && personas.get(j).getState()==State.I){
                    double e = Math.sqrt(Math.pow(personas.get(i).getX()-personas.get(j).getX(),2)+Math.pow(personas.get(i).getY()-personas.get(j).getY(),2));
                    if(e < distancia){
                        if((personas.get(i).getmask() == true) && (personas.get(j).getmask() == true)){
                            if(Math.random()<=p2){
                                personas.get(i).infect(rec_time);
                            }
                        }
                        else if(((personas.get(i).getmask() == true) && (personas.get(j).getmask() == false)) || ((personas.get(i).getmask() == false) && (personas.get(j).getmask() == true))){
                            if(Math.random()<=p1){
                                personas.get(i).infect(rec_time);
                            }
                        } 
                        else{
                            if(Math.random()<=p0){
                                personas.get(i).infect(rec_time);
                            }
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
        int sus=0,inf=0,rec=0,vac=0;
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
                case V:
                    vac++;
                    break;
            }
            
        }
        String s = vac + ",\t" + inf + ",\t" + rec + ",\t" + sus;
        return s;
    }
    // include others methods as necessary
 }
