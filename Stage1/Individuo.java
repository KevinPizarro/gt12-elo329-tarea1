public class Individuo {
    private double x, y, speed, angle, deltaAngle;
    private double x_tPlusDelta, y_tPlusDelta;
    private Comuna comuna;

    public Individuo (Comuna comuna, double speed, double deltaAngle){
	//Constructor del Individuo
        angle = Math.random()*2*Math.PI; //Calculamos el angulo multiplicando un numero n (0<n<1) y lo multiplicamos por 2*pi para obtener un angulo en 0<angle<360
        x = Math.random()*comuna.getWidth(); //Calculamos la posicion inicial en x como un valor entre 0 y el ancho de la comuna
        y = Math.random()*comuna.getHeight(); //Calculamos la posicion inicial en y como un valor entre 0 y el ancho de la comuna
        this.speed = speed; //Asignamos la velocidad
        this.deltaAngle = deltaAngle;  //Asignamos el DeltaAngle
        this.comuna = comuna; // Asignamos la comuna
    }
    public static String getStateDescription(){ //Método para generar el encabezado de salida
        return "x,\ty"; 
    }
    public String getState() { //Método para imprimir las actualizaciones de "x" e "y" 
        return x + ",\t" + y; 
    }
    public void computeNextState(double delta_t) { //Método para calcular los nuevos estados del angulo, y la posición
        double r=Math.random();
        angle+= Math.floor(r*(2*deltaAngle+1)-deltaAngle); //se suma un valor aleatorio entre -deltaAngle y +deltaAngle
        x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
        if(x_tPlusDelta < 0){   // rebound logic in x, left side 
            angle = Math.PI - angle;
            x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        }
        else if( x_tPlusDelta > comuna.getWidth()){ //rebound logic in x, right side
            angle = Math.PI - angle;
            x_tPlusDelta=x+speed*Math.cos(angle)*delta_t;
        }
        if(y_tPlusDelta < 0){   // rebound logic in y, down side
            angle = 2*Math.PI - angle;
            y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
         }
        else if( y_tPlusDelta > comuna.getHeight()){ //rebound logic in y, up side
            angle = 2*Math.PI - angle;
            y_tPlusDelta=y+speed*Math.sin(angle)*delta_t;
        }
    }
    public void updateState(){ //Método para actualizar la posición
	x = x_tPlusDelta;
    y = y_tPlusDelta;
    }
}
