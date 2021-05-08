import java.awt.geom.Rectangle2D;

/**
 * La clase comuna se encarga de crear al objeto correspondiente a la persona, inicializa el tamaño de la comuna y
 * se calcula el siguiente estado del individuo.
 */
public class Comuna {
    private Individuo person;
    private Rectangle2D territory; // Alternatively: double width, length;

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
        //Constructor de comuna, con parametros para agregar el alto y ancho
        territory = new Rectangle2D.Double(0,0, width, length);
        person=null;
    }

    /**
     *
     * @return Retorna el ancho de la comuna.
     */
    public double getWidth() { //Método para obtener el ancho de la comuna
        return territory.getWidth();
    }

    /**
     *
     * @return Retorna el alto de la comuna.
     */
    public double getHeight() { //Método para obtener el alto de la comuna
        return territory.getHeight(); 
    }

    /**
     * Se crea a la única persona que reside en la comuna.
     * @param person
     */
    public void setPerson(Individuo person){ //Método para incluir al individuo en la comuna
        this.person = person;
    }

    /**
     * Se calcula el siguiente estado.
     * @param delta_t
     */
    public void computeNextState (double delta_t) { //Método para calcular el siguiente estado
        person.computeNextState(delta_t);
    }

    /**
     * Se actualiza la posición del individuo.
     */
    public void updateState () { //Método para actualizar la posición del individuo
        person.updateState();
    }

    /**
     * Se retorna la posición del individuo.
     * @return
     */
    public String getState() { //Método para obtener la posición de la persona
        return person.getState();
    }

    /**
     * Se obtiene una descripción del estado de la persona.
     * @return
     */
    public static String getStateDescription() { //Método para obtener la descripción del estado
        return Individuo.getStateDescription();
    }
 }
