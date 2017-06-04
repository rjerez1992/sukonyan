package jsukonyan;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import static jsukonyan.JSOURCE.*;
import static jsukonyan.KSOURCE.*;

/**
 *Esta clase muestra el menu principal y genera todo su comportamiento. 
 */
public class JMenuPrincipal extends JPanel implements KeyListener{    

    JPanelBase panelPadre;
    JMenuItem nuevoJuego;
    JMenuItem acercaDe;
    JMenuItem opciones;
    JMenuItem salir;

    private int focusMenu;
    private JSonido buttonSound;
    
    /**
     * Crea el panel de menu principal
     * @param panelPadre panel que contiene a este panel
     */
    JMenuPrincipal(JPanelBase panelPadre){
        super();
        //Guardamos la ventana padre para hacer operaciones de cambio
        this.panelPadre = panelPadre;
        //Creamos un fondo negro por si acaso sale algo feo
        this.setBackground(Color.BLACK);
        
        //Creamos el sonido para los botones
        this.buttonSound = new JSonido(BUTTONSOUND.toString());
        
        //Agregamos el boton Nuevo Juego
        this.nuevoJuego = new JMenuItem(NEWGAMEBUT.toString());        
        this.nuevoJuego.moveTo(400-(105), 285);
        this.add(nuevoJuego);  
        //Este boton siempre comienza siento el foco
        this.nuevoJuego.onFocus();     
        this.focusMenu = 0;
        
        //Agregamos el boton Acerca de
        this.acercaDe = new JMenuItem(ABOUTBUT.toString());
        this.acercaDe.moveTo(400-105, 315);
        this.add(acercaDe);
        
        //Agregamos el boton Opciones
        this.opciones = new JMenuItem(OPTIONSBUT.toString());
        this.opciones.moveTo(400-105, 345);
        this.add(opciones);
        
        //Agregamos el boton Salir
        this.salir = new JMenuItem(EXITBUT.toString());
        this.salir.moveTo(400-105, 375);
        this.add(salir);
     
        //Agregamos el KeyListener al panel
        this.addKeyListener(this);  
    }
    
    /**
     * Pintamos le componente agregandole un fondo, antes de pintar los demas componentes.
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {        
        super.paintComponent(g);        
        
        //Pinta el fondo del componente
        g.drawImage(new JImagen(MAINBGIMAGE.toString()).getImage(),0, 0, null); 
        
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
        if (e.getKeyCode() == KEYHELP.getCode()){
            this.getGraphics().drawImage(new JImagen(HELPBGIMAGE.toString()).getImage(),0, 0, null); 
        }
    }

     /**
      * Accion realizada cuando se suelta la tecla especifica.
      * Se comprueba que tecla se presionó y luego se realiza la accion correspondiente.
      * Al final se repinta todo para reflejar el nuevo estado de la ventana.
      * @param e KeyEvent
      */
    public void keyReleased(KeyEvent e) {
        
        //Verificamos la tecla que fue presionada, la comparamos con las teclas que tenemos configuradas en el ENUM
        //Luego de esto movemos la posicion del menu segun la tecla presionada.
        
        if (e.getKeyCode() == KEYDOWN.getCode()){
            if (this.focusMenu < 3){
                this.focusMenu++;
                this.buttonSound.play();
            }
            if (this.focusMenu <= 0)
                this.focusMenu = 0;
            else if (this.focusMenu >= 3)
                this.focusMenu = 3;
        }
        
        if (e.getKeyCode() == KEYUP.getCode()){
            if (this.focusMenu > 0){
                this.focusMenu--;
                this.buttonSound.play();
            }
            if (this.focusMenu <= 0)
                this.focusMenu = 0;
            else if (this.focusMenu >= 3)
                this.focusMenu = 3;
        }
        
        if (e.getKeyCode()== KEYACCEPT.getCode() && this.focusMenu == 2)
        {
            this.buttonSound.play();
            this.panelPadre.showMenuOpciones();        
        }
        
        if (e.getKeyCode() == KEYACCEPT.getCode() && this.focusMenu == 0)
        {
            this.buttonSound.play();
            this.panelPadre.showNuevoJuego();
        }
        
        //Si estamos sobre el menu salir y apretamos enter, cerramos la aplicacion.
        if (e.getKeyCode() == KEYACCEPT.getCode() && focusMenu == 3){
            this.buttonSound.play();
            System.exit(0);
        }
        
        else if (e.getKeyCode() == KEYACCEPT.getCode() && focusMenu == 1){
            this.buttonSound.play();
            this.panelPadre.showAbout();           
        }
        
        //Luego de mover la posicion del menu, debemos cambiar el nuevo focus segun el numero de menu.
        //El orden de los menus va desde 0 a 3, Nuevo Juego a Salir.
        //Cada vez que ponemos uno en focus, debemos dejar los demas sin focus.
        if (this.focusMenu == 0){
            this.nuevoJuego.onFocus();
            this.acercaDe.deFocus();
            this.opciones.deFocus();
            this.salir.deFocus();
        }
        else if (this.focusMenu == 1){
            this.nuevoJuego.deFocus();
            this.acercaDe.onFocus();
            this.opciones.deFocus();
            this.salir.deFocus();
        }
        else if (this.focusMenu == 2){
            this.nuevoJuego.deFocus();
            this.acercaDe.deFocus();
            this.opciones.onFocus();
            this.salir.deFocus();
        }
        else if (this.focusMenu == 3){
            this.nuevoJuego.deFocus();
            this.acercaDe.deFocus();
            this.opciones.deFocus();
            this.salir.onFocus();
        }        
        //Repintamos para ver los cambios
        this.repaint();
    }
  
}
