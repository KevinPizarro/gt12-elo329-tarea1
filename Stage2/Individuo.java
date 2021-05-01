public class Individuo {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private Comuna comuna;
    private State state; //estado del individuo (susceptible,infectado,recuperado)
    private double rec_time; //tiempo de recuperación

    public Individuo (Comuna comuna, double speed, double deltaAngle,State state){
	//??
        angle = Math.random()*2*Math.PI;
        x = Math.random()*comuna.getWidth();
        y = Math.random()*comuna.getHeight();
        this.speed = speed;
        this.deltaAngle = deltaAngle; 
        this.comuna = comuna;
        this.state =  state;
        rec_time=0;
    }
    public static String getStateDescription(){
        return "x,\ty";
    }
    public String getState() {
        return x + ",\t" + y;
    }
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
            if(delta_t>rec_time){
                State.R;  //recuperado
                rec_time=0;
            }
            else{rec_time-=delta_t}
        }

	//??
    }
    public void updateState(){
	x = x_tPlusDelta;
    y = y_tPlusDelta;
    }

    public void infect(double rec_time){ //infecta individuo
        if(state==State.S){
            state=State.I
        }
        this.rec_time=rec_time;
    }

}

public enum State{
    S,I, R             //S para susceptibles, I para infectados, R para recuperados
}
