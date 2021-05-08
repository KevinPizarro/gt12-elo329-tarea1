import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * La clase comuna se encarga de crear el arreglo de las personas y posicionarlas en la comuna, calcula el siguiente
 * estados de los individuos, con esto se calcula cuando una persona se infecta. Tambien se encarga de crear el
 * vacunatorio.
 */
public class Comuna {
    private Rectangle2D territory; // Alternatively: double width, length;
    private ArrayList<Individuo> personas;
    private ArrayList<Rectangle2D.Double> vacunatorios;

    /**
     * Si no se ingresan parametros se crea un rectangulo con medidas fijas que corresponde a la comuna.
     */
    public Comuna(){
        territory = new Rectangle2D.Double(0, 0, 1000, 1000); // 1000x1000 m²;
    }

    /**
     * Se crea la comuna como un rectangulo con los parametros ingresados.
     * @param width Ancho de la comuna
     * @param length Largo de la comuna
     */
    public Comuna(double width, double length){
        territory = new Rectangle2D.Double(0,0, width, length);

    }

    /**
     *
     * @return Retorna el ancho de la comuna.
     */
    public double getWidth() {
        return territory.getWidth(); //??
    }

    /**
     *
     * @return Retorna el alto de la comuna.
     */
    public double getHeight() {
        return territory.getHeight(); //??
    }

    /**
     * Metodo que crea los vacunatorios en el territorio.
     * @param numVac Cantidad de vacunatorios en la comuna.
     * @param vacsize Tamaño de los vacunatorios.
     */
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
                    if(intervac(vac)){
                        while(intervac(vac)){
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

    /**
     * Se identifica si un nuevo vacunatorio se intersecta con uno ya existente.
     * @param nuevo El nuevo vacunatorio
     * @return
     */
    public boolean intervac(Rectangle2D nuevo){
        for(int i = 0; i < vacunatorios.size(); i++){
            if(nuevo.intersects(vacunatorios.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * Se identifica si exite un vacunatorio en las coordenadas dada.
     * @param x
     * @param y
     * @return Se retorna true enn caso de existir vacunatorio.
     */
    public boolean existvac(double x, double y){
        for(int i = 0; i < vacunatorios.size(); i++){
            if(vacunatorios.get(i).contains(x,y)){
                return true;
            }
        }
        return false;
    }

    /**
     * Se crea el array de las personas existentes en las comunas. Se inicializa la cantidad correspondientes de
     * personas infectadas y personas con máscarillas.
     * @param n_ind El número de individuos
     * @param time_recovery El tiempo de recuperación de los indivicuos.
     * @param n_inf El número máximo de infectados permitidos.
     * @param comuna La comuna en la que vive el individuo.
     * @param speed La velocidad de desplzamiento del individuo.
     * @param deltaAngle La variación del angulo sobre el que se va a desplazar el individuo aleatoriamente.
     * @param m El número máximo de personas con máscarilla.
     */
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

    /**
     * Se cácula el siguiente estado de todas las personas en la comuna identificando las personas que se infectan.
     * @param delta_t La variación de tiempo existente entre cada calculo de estado.
     * @param distancia La distancia a la cual una persona se puede infectar con otra.
     * @param p0 Probabilidad de infección cuando ninguna de las dos personas usa mascarillas.
     * @param p1 Probabilidad de infección cuando solo una persona usa mascarillas.
     * @param p2 Probabilidad de infección cuando las dos personas usan mascarillas.
     * @param rec_time Tiempo de recuperación.
     * @param vactime Tiempo desde empezada la simulación en la que se activan los vacunatorios.
     * @param t Tiempo de ejecución de la simulación.
     */
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

    /**
     * Se actualiza el estados de todas las personas.
     */
    public void updateState () {

        for(int i=0; i<personas.size();i++) {
            personas.get(i).updateState();
        }
    }

    /**
     *
     * @return Se retorna la cantidad de recuperados, infectados, susceptibles y vacunados.
     */
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
