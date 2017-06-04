package jsukonyan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JPanel;
import static jsukonyan.JSOURCE.*;
import static jsukonyan.KSOURCE.*;

/**
 *Esta clase muestra el menu acerca de, el cual contiene informacion de los desarrolladores y puntajes maximos*.
 * En este panel se muestra la informacion de ambos desarrolladores junto con sus fotos y los 10 mejores puntajes del juego
 */
public class JMenuAcerca extends JPanel implements KeyListener{

    JPanelBase panelPadre;
    JSonido buttonSound;
    JTexto puntaje0;
    JTexto puntaje1;
    JTexto puntaje2;
    JTexto puntaje3;
    JTexto puntaje4;
    JTexto puntaje5;
    JTexto puntaje6;
    JTexto puntaje7;
    JTexto puntaje8;
    JTexto puntaje9;
    
    /**
     * Crea un menu Acerca de
     * @param panelPadre Panel que contiene a este panel
     */
    JMenuAcerca(JPanelBase panelPadre){
        super();
        this.panelPadre = panelPadre;        
        this.setBackground(Color.BLACK);
        this.buttonSound = new JSonido(BUTTONSOUND.toString());
        this.addKeyListener(this);
        
        //Leer puntajes
        //Y agregarlos
        String score0 = "";
        String score1 = "";
        String score2 = "";
        String score3 = "";
        String score4 = "";
        String score5 = "";
        String score6 = "";
        String score7 = "";
        String score8 = "";
        String score9 = "";
        
        //Leemos los puntajes
        File file = new File(PUNTAJES.toString());
        try 
        {
            Scanner read = new Scanner(file);
            if (read !=null)
                while (read.hasNextLine())
                {
                   score0 = read.nextLine();
                   score1 = read.nextLine();
                   score2 = read.nextLine();
                   score3 = read.nextLine();
                   score4 = read.nextLine();
                   score5 = read.nextLine();
                   score6 = read.nextLine();
                   score7 = read.nextLine();
                   score8 = read.nextLine();
                   score9 = read.nextLine();
                }
        } 
        
        catch (FileNotFoundException ex) 
        {
            
        }
        
        //Dibujamos cada puntaje
        this.puntaje0 = new JTexto (score0,18f);
        this.puntaje0.moveTo(630,127);
        this.add(this.puntaje0);
        
        this.puntaje1 = new JTexto (score1,18f);
        this.puntaje1.moveTo(630,127+28);
        this.add(this.puntaje1);
        
        this.puntaje2 = new JTexto (score2,18f);
        this.puntaje2.moveTo(630,127+56);
        this.add(this.puntaje2);
        
        this.puntaje3 = new JTexto (score3,18f);
        this.puntaje3.moveTo(630,127+84);
        this.add(this.puntaje3);
        
        this.puntaje4 = new JTexto (score4,18f);
        this.puntaje4.moveTo(630,127+112);
        this.add(this.puntaje4);
        
        this.puntaje5 = new JTexto (score5,18f);
        this.puntaje5.moveTo(630,127+140);
        this.add(this.puntaje5);
    
        this.puntaje6 = new JTexto (score6,18f);
        this.puntaje6.moveTo(630,127+168);
        this.add(this.puntaje6);
        
        this.puntaje7 = new JTexto (score7,18f);
        this.puntaje7.moveTo(630,127+196);
        this.add(this.puntaje7);
        
        this.puntaje8 = new JTexto (score8,18f);
        this.puntaje8.moveTo(630,127+224);
        this.add(this.puntaje8);
        
        this.puntaje9 = new JTexto (score9,18f);
        this.puntaje9.moveTo(630,127+252);
        this.add(this.puntaje9);        
    }
    
    /**
     * Pintamos le componente agregandole un fondo, antes de pintar los demas componentes.
     * @param g Graphics Graficos del componente
     */
    @Override
    public void paintComponent(Graphics g) {        
        super.paintComponent(g);        
        
        //Pinta el fondo del componente
        g.drawImage(new JImagen(ABOUTBGIMAGE.toString()).getImage(),0, 0, null); //FONDO
        
        //Pinta todos los demas componentes de este panel
        for (Component comp : this.getComponents()){
            comp.paint(g);
        }        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Al apretar la tecla aceptar, se volvera al menu principal.
     * @param e Tecla presionada
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KEYACCEPT.getCode()){
            this.buttonSound.play();
            this.panelPadre.showMenu();
        }
    }
}