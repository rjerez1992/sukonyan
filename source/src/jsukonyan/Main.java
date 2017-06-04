package jsukonyan;

/**
 * Clase principal de JSukonyan.
 * Esta clase crea la ventana, la hace visible y la comienza.
 * 
 *@author  Reinaldo Jerez
 *@author Cristobal Henriquez
 * 
 * El proyecto funciona en un 99%
 * Errores descubiertos: Los puntajes luego de ser guardados no son actualizados,
 * Sin embargo los cambios se ven reflejados al abrir la aplicacion
 * El sonido a veces se detiene, depende de la capacidad de cada computador (muy pesado)
 * 
 */
public class Main {    
    /**
     * Clase principal de JSukonyan
     * @param args Parametros entregados por terminal. (Sin utilidad en este caso)
     */    
    public static void main(String[] args){
        //Creamos la ventana
        JVentana ventana = new JVentana();  
        //Hacemos la ventana visible
        ventana.setVisible(true);
        //Iniciamos la ventana (timer)
        ventana.start() ;
    }    
}
