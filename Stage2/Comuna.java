import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.math.*;


/**
 * La clase comuna se encarga de crear el arreglo de las personas y posicionarlas en la comuna, se inicializa
 * el tamaño de la comuna y se calcula el siguiente estado de los individuos, calculando cuando una persona se infecta.
 */
public class Comuna {
    private Individuo person;
    private Rectangle2D territory; // Alternatively: double width, length;
    private ArrayList<Individuo> personas;

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
     * Se crea el array de las personas existentes en las comunas y se inicializa la cantidad correspondientes de
     * personas infectadas.
     * @param n_ind El número de individuos
     * @param time_recovery El tiempo de recuperación de las personas.
     * @param n_inf El número de infectados permitidos.
     * @param comuna La comuna en la que vive la persona.
     * @param speed La velocidad de las personas.
     * @param deltaAngle La variación del angulo donde la persona se desplazará aleatoriamente.
     */
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

    /**
     * Se cácula el siguiente estado de todas las personas en la comuna identificando las personas que se infectan.
     * @param delta_t La variación del tiempo existente entre caca calculo de estado.
     * @param distancia La distancia a la cual una eprsona se puede infectar con otra.
     * @param p0 Probabilidad de infección cuando ninguna de las dos personas usa mascarillas.
     * @param rec_time Tiempo de recuperación.
     */
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
     * @return Se retorna la cantidad de recuperados, infectados y susceptibles.
     */
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
