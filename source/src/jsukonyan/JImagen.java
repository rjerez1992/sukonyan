package jsukonyan;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * Esta clase crea imagenes desde un archivo*.
 * Para obtener la imagen creada por esta clase, se debe utilizar
 * el metodo getImage() sobre la instancia de la clase.
 */
public class JImagen {
    
    //Creamos la variable donde almacenaremos la imagen
    private Image img;
    
    /**
     * Crea una archivo JImage
     * @param filePath ruta de la imagen
     */
    public JImagen(String filePath){
        //Obtenemos el archivo, desde la ruta del archivo
        File imgFile = new File(filePath);
        //Generamos un ImageIcon a partir del archivo
        ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
        //Obtenemos la imagen del ImageIcon
        img = imgIcon.getImage();        
    }
    
    /**
     *Esta funcion devuelve la imagen del JImagen.
     * @return Imagen creada por el JImagen
     */
    public Image getImage(){
        return this.img;
    }
}
