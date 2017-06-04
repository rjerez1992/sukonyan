package jsukonyan;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Esta clase crea los botones del juego a base de imagenes.
 * Las imagenes deben estar contenidas en la carpeta resources y debidamente ancladas a algun enum  
 */
public class JMenuItem extends JComponent{
    private boolean onFocus;
    private String onFocusFile;
    private String deFocusFile;
    private int posX;
    private int posY;
    
    //La carpeta resources debe contener 2 archivos
    //ArchivoOnFocus.png y ArchivoDeFocus.png
    //El enum solo devolverá el nombre del archivo, por lo tanto
    //se le debe agregar el tipo de focus y la extension.
    /**
     * Crea un archivo JMenuItem.
     * @param filePath ruta de la imagen para crear el item de menu
     */
    public JMenuItem(String filePath){
        super();
        this.onFocus = false;       
        this.onFocusFile = filePath+"OnFocus.png";
        this.deFocusFile = filePath +"DeFocus.png"; 
        this.posX = 0;
        this.posY = 0;
    }
    
    //Este constructor es para usar los JMenuItem como imagenes simples
    /**
     * Crea un archivo JMenuImagen en cierta posicion
     * @param filePath ruta del archivo
     * @param posX posicion en X
     * @param posY posicion en Y
     */
    public JMenuItem(String filePath, int posX, int posY){
        super();
        this.onFocus = false;       
        this.onFocusFile = filePath;
        this.deFocusFile = filePath; 
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Cambia el valor de focus a verdadero.
     * El focus se vuelve este boton.
     */
    public void onFocus(){
        this.onFocus = true;       
        this.repaint();

    }
    
    /**
     * Cambia el valor de focus a negativo.
     * Quita el focus del boton
     */
    public void deFocus(){
        this.onFocus = false;  
    }   
    
    /**
     * Mueve el boton a la posicion X,Y y lo repinta.
     * @param posX posicion X para mover.
     * @param posY posicion Y para mover.
     */
    public void moveTo(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Pinta el componente de acuerdo a sus propios estandares.
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (onFocus){
            //Pintamos cuando el boton es el focus
            g.drawImage(new JImagen(onFocusFile).getImage(), this.posX, this.posY, null);            
        }
        else{
            //Pintamos cuando el boton no es el focus
            g.drawImage(new JImagen(deFocusFile).getImage(), this.posX, this.posY, null);     
        }                  
    }
}
