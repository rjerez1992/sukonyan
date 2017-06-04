package jsukonyan;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *Esta clase almacena las funcionalidades para reproducir sonidos utilizando Javazoom.
 *Los sonidos se reproducen en un hilo separado, por lo cual es necesario detenerlos o detener la aplicacion para acabarlos. 
 *(*)Este codigo es un extracto tomado desde una pregunta de Stackoverflow.
 * (**)Cuando se ejecutan muchos sonidos en un tiempo muy corto (0.5sec), genera una excepcion de decodificacion,
 * (**)sin embargo, esta no es causal del cierre de la aplicacion.
 */
public class JSonido {
    private String filename;
    private Player player; 

    /**
     * Crear un archivo de sonido y un player
     * @param filename ruta del archivo
     */
    public JSonido(String filename) {
        this.filename = filename;
    } 

    /**
     * Termina la reproduccion del sonido.
     */
    public void close() { if (player != null) player.close(); }

    /**
     * Reproduce un sonido.
     */
    public void play() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problema reproduciendo el archivo:" + filename);
            System.out.println(e);
        }

        //Se ejecuta en un hilo separado para reproducirlo como fondo*
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();    
    }    
}
