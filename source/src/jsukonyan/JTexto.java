package jsukonyan;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.swing.JLabel;
import static jsukonyan.JSOURCE.*;


/**
 *Esta clase maneja los textos del juego. * 
 */
public class JTexto extends JLabel{
    Font font;
    String text;
    int posX;
    int posY;
    float at;
    
    /**
     * Creamos un texto
     * @param text texto
     * @param at formato
     */
    public JTexto(String text, float at){
        super();
        this.text = text; 
        this.at = at;
        //Esta parte intenta cargar la font custom del juego
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(FUENTE.toString())); 
            this.font = this.font.deriveFont(Font.BOLD, this.at);                
        }
        catch (FontFormatException | IOException ex){
            //No hacemos nada en el caso que no se pueda cargar.           
        }
        this.setText(text);
        this.setFont(font);
        this.setForeground(Color.WHITE);               
        this.posX = 0;
        this.posY = 0;
    }
    
    /**
     * Mueve el texto a la posicion x, y
     * @param x
     * @param y 
     */
    public void moveTo(int x, int y){
        this.posX = x;
        this.posY = y;
    }
    
    /**
     * Devuelve el valor x del texto
     * @return posicion x
     */
    public int getX(){
        return this.posX;
    }
    
    /**
     * Devuelve el valor y del texto
     * @return posicion y
     */
    public int getY(){
        return this.posY;
    }
    
    
   
    @Override
    public void paintComponent(Graphics g) {    
        this.setFont(font);
        g.setFont(font);
        FontMetrics fm = this.getFontMetrics(font);   
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(text, posX-(fm.stringWidth(text)/2), posY);        
    }
    
}
