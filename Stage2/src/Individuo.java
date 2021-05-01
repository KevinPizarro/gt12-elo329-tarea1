public class Individuo {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private Comuna comuna;

    public Individuo (Comuna comuna, double speed, double deltaAngle){
	//??
        angle = Math.random()*2*Math.PI;
        x = Math.random()*comuna.getWidth();
        y = Math.random()*comuna.getHeight();
        this.speed = speed;
        this.deltaAngle = deltaAngle; 
        this.comuna = comuna;
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
	//??
    }
    public void updateState(){
	x = x_tPlusDelta;
    y = y_tPlusDelta;
    }

    public void InfectarIndividuo(){
    }//funcion hardcodeada.
}
