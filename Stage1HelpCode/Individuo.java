public class Individuo {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private Comuna comuna;

    public Individuo (Comuna comuna, double speed, double deltaAngle){
	//??
        angle = Math.random()*2*Math.PI;
    }
    public static String getStateDescription(){
        return "x,\ty";
    }
    public String getState() {
        return x + ",\t" + y;
    }
    public void computeNextState(double delta_t) {
        double r=Math.random();
        angle+= //??
        x_tPlusDelta=x+speed*Math.cos(angle);
	//??
        if(x_tPlusDelta < 0){   // rebound logic
           //??
        }
        else if( x_tPlusDelta > comuna.getWidth()){
           //??
        }
	//??
    }
    public void updateState(){
	//??
    }
}
