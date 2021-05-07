/**
 * Genera individuos para analisis
 */
public class Individuo {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private State state; //estado del individuo (susceptible,infectado,recuperado)
    private double rec_time; //tiempo de recuperación
    private Comuna comuna;
    private boolean mask;

    /**
     * Constructor de la clase individuo
     * @param comuna corresponde a la comuna en donde se va a ubicar
     * @param speed corresponde a la velocidad de la persona en metros segundo cuadrado
     * @param deltaAngle corresponde a la diferencia máx. del ángulo en radianes
     */
    public Individuo (Comuna comuna, double speed, double deltaAngle){
	//??
        angle = Math.random()*2*Math.PI; //en radianes
        x = Math.random()*comuna.getWidth();
        y = Math.random()*comuna.getHeight();
        this.speed = speed;
        this.deltaAngle = deltaAngle;
        this.state =  State.S; //se inicializa como susceptible
        rec_time=0;
        this.comuna = comuna;
        this.mask = false;
    }

    /**
     * metodo para agregar una mascara al individuo
     */
    public void putmask(){this.mask = true;}

    /**
     * @return true si el individuo tiene máscara y false en caso contrario
     */
    public boolean getmask(){
        if(mask){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @return el valor de x
     */
    public double getX(){return x;}

    /**
     * @return el valor de y
     */
    public double getY(){return y;}

    /**
     * metodo para calcular el nuevo estado del individuo
     * @param delta_t  corresponde a la diferncia de tiempo entre estados
     */
    public void computeNextState(double delta_t) {
        double r=Math.random();
        angle+= Math.floor(r*(2*deltaAngle+1)-deltaAngle); //se suma un valor aleatorio entre -deltaAngle y +deltaAngle
        x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
	//??
        if(x_tPlusDelta < 0){   // rebound logic
            angle = Math.PI - angle;
            x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        }
        else if( x_tPlusDelta > comuna.getWidth()){
            angle = Math.PI - angle;
            x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        }
        if(y_tPlusDelta < 0){   // rebound logic
            angle = 2*Math.PI - angle;
            y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
         }
        else if( y_tPlusDelta > comuna.getHeight()){
            angle = 2*Math.PI - angle;
            y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
        }
        if (state==State.I){   //revisa si el indiviuo cumple las condiciones de recuperacion
            rec_time-=delta_t;
            if(0>=rec_time) {
                state = State.R;  //recuperado
                rec_time = 0;
            }
        }


    }

    /**
     * metodo para actualizar posición
     */
    public void updateState(){
	x = x_tPlusDelta;
    y = y_tPlusDelta;
    }

    /**
     * metodo para cambiar el estado del inviduo a infectado
     * @param rec_time corresponde al tiempo de recuperación dado
     */
    public void infect(double rec_time){ //infecta individuo
        if(state==State.S){
            state=State.I;
        }
        this.rec_time=rec_time;
    }

    /**
     * metodo para cambiar el estado del inviduo a vacunado
     */
    public void vaccine(){
        if(state == State.S){
            state = State.V;
        }
    }

    /**
     * @return el estado de la persona (S,I,R)
     */
    public State getState(){ //retorna estado de persona
        return state;
    }
}

/**
 * Corresponde a los estados del individuo (S para susceptibles, I para infectados, R para recuperados)
 */
enum State{
    S,I,R,V             //S para susceptibles, I para infectados, R para recuperados
}
