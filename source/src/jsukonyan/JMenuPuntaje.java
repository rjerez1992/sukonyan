package jsukonyan;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JPanel;
import static jsukonyan.JSOURCE.*;
import static jsukonyan.KSOURCE.*;


/**
 * Esta clase maneja la ventana de introduccion de puntajes del juego.
 * 
 */
public class JMenuPuntaje extends JPanel implements KeyListener {
    
    int puntaje;
    JSonido buttonSound;
    JPanelBase panelPadre;
    String nombre;
    
    /**
     * Crea el panel de puntajes
     * @param panelPadre panel que contiene a este panel
     */
    JMenuPuntaje(JPanelBase panelPadre){
        super();
        this.panelPadre = panelPadre;        
        this.setBackground(Color.BLACK);
        this.buttonSound = new JSonido(BUTTONSOUND.toString());
        this.addKeyListener(this);         
        this.puntaje = 0;
        this.nombre = "";
   }
    
    /**
     * Esta funcion guarda el puntaje del jugador
     * @param movimientos numero de movimiento en la partida reciente.
     */
    public void setPuntaje(int movimientos){
        this.puntaje=movimientos;
        this.nombre = "";
        this.repaint();
    }
    
    /**
     * Esta funcion guarda y ordena los puntajes en un archivo *.dat.     * 
     */
    public void saveScore(){
        //Leemos los puntajes a un array      
        ArrayList<String> scores = new ArrayList<String>();
        ArrayList<String> sortedScores = new ArrayList<String>();
        String line;           
        File file = new File(PUNTAJES.toString());
        try 
        {
            Scanner read = new Scanner(file);            
            while (read.hasNextLine())
            {
                line = read.nextLine();
                scores.add(line);                   
            }
            scores.add(this.nombre+" "+this.puntaje);
        }         
        catch (FileNotFoundException ex){}
        //Ordenamos los puntajes segun se deba
        Collections.sort(scores , new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if (getValue(o1)>=getValue(o2))
                    return 1;     
                return -1;
            }
        });
        
        scores.remove(scores.size()-1);
        
        try{
             FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter wr = new PrintWriter(bw);
            for (String score : scores){
                wr.println(score);
            }            
            wr.close();
            bw.close();
            fw.close();        
        }
        catch(IOException ex){
            System.exit(0);
        }                
        //No esta funcionando esta parte
        this.panelPadre.repaint();
    }
    
    /**
     * Obtiene el valor numero de un puntaje (score).
     * @param str string del score leido desde el archivo.dat
     * @return valor numerico del score
     */
    public int getValue(String str){
        String valor = str.substring(str.indexOf(" ")+1);
        //return 1;
        return Integer.parseInt(valor);        
    }
    
    /**
     * Pinta el componente
     * @param g graficos del componente
     */
    @Override
     public void paintComponent(Graphics g) {          
        //Pinta el fondo del componente
        g.drawImage(new JImagen(FONDOPUNTAJES.toString()).getImage(),0, 0, null); 
        
        //Escribimos el puntaje
        Font font;
        Font newFont = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(FUENTE.toString())); 
            newFont = font.deriveFont(Font.BOLD, 18f);                
        }
        catch (FontFormatException | IOException ex){
            //No hacemos nada en el caso que no se pueda cargar.           
        }
        g.setFont(newFont);
        g.setColor(Color.WHITE);
        FontMetrics fm = this.getFontMetrics(newFont);   
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(this.nombre+"|", 400-(fm.stringWidth(""+this.nombre)/2), 250);
        g.drawString(""+this.puntaje, 400-(fm.stringWidth(""+this.puntaje)/2), 340); //Dibuja el puntaje
        
     }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KEYACCEPT.getCode())
        {  
            this.saveScore();        
            this.buttonSound.play();
            this.panelPadre.showMenu(); 
        }
        
        Character c = e.getKeyChar();
        if(Character.isAlphabetic(c)){            
            this.nombre += c;
        }
        else if (e.getKeyCode() == 8){
            if (this.nombre.length()>0)
                this.nombre = this.nombre.substring(0, this.nombre.length()-1);
        }            
        if (this.nombre.length()>9){
            this.nombre = this.nombre.substring(0, 9);
        }
        this.repaint();
    }
}
