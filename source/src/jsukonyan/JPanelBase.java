package jsukonyan;


import java.awt.CardLayout;
import javax.swing.JPanel;


/**
 *Esta clase maneja el panel base del juego, que esta contenido en el JFrame.
 * 
 */
public class JPanelBase extends JPanel {
    
    private JMenuPrincipal menuPanel;
    public JMenuAcerca acercaPanel;
    private JVentana ventanaPadre;
    private CardLayout cardLayout;
    private JMenuOpciones menuOpciones;
    private JPanelJuego panelNuevoJuego;
    private JMenuPuntaje menuPuntaje;
    
    /**
     * Creamos el panel base
     * @param ventanaPadre panel que contiene a este panel
     */
    public JPanelBase(JVentana ventanaPadre){
        //Utilizamos un CardLayout que nos permite manejar distintos paneles
        //e ir cambiando entre ellos.
        super(new CardLayout());
        this.cardLayout = (CardLayout) this.getLayout();
                
        //Guardamos la ventana padre que contiene a este panel
        this.ventanaPadre = ventanaPadre;              
      
        //Creamos el menu principal y lo agregamos
        this.menuPanel = new JMenuPrincipal(this);
        this.add(this.menuPanel, "mainMenu");
        this.menuPanel.setFocusable(true);
        //Creamos el menu acerca de y lo agregamos
        this.acercaPanel = new JMenuAcerca(this);
        this.add(this.acercaPanel, "aboutMenu");        
        this.acercaPanel.setFocusable(true);
        
        //Creamos el menu opciones
        this.menuOpciones = new JMenuOpciones (this);//creo el menu 
        this.add(this.menuOpciones,"menuOpciones");//como se encuentra dentro del panel 
        this.acercaPanel.setFocusable(true);// le da el focus para que pueda trabajar
        
        //Creamos el menu nuevo juego
        this.panelNuevoJuego = new JPanelJuego (this);
        this.add(this.panelNuevoJuego,"nuevoJuego");
        this.acercaPanel.setFocusable(true);
        
        //Creamos el menu de puntaje
        this.menuPuntaje = new JMenuPuntaje(this);
        this.add(this.menuPuntaje, "menuPuntaje");
        this.menuPuntaje.setFocusable(true);

        //Hacemos que el menu sea focuseable por defecto y que sea el primero
        //en aparecer al iniciar la ventana.
        this.menuPanel.requestFocusInWindow();
        
        //Mostramos el panel correspondiente al tag
        this.cardLayout.show(this, "mainMenu");
             
    }
    
    /**
     * Cambia el panel actual al panel acerca de.
     */
    public void showAbout(){
        this.cardLayout.show(this, "aboutMenu");
        this.acercaPanel.requestFocusInWindow();
    }   
    
    /**
     * Cambia el panel actual al panel de menu principal.
     */
    public void showMenu(){
        this.cardLayout.show(this, "mainMenu");        
        this.menuPanel.requestFocusInWindow();        
    }
    
    /**
     * Cambia el panel actual al menu de opciones.
     */
    public void showMenuOpciones()
    {
        this.cardLayout.show(this,"menuOpciones");// lo dejo de los primeros y me lo muestro 
        this.menuOpciones.requestFocusInWindow();// ek imput de teclas le llega al panel 
    }
    
    /**
     * Cambia el menu actual a la ventana de juego.
     */
    public void showNuevoJuego()
    {
        this.cardLayout.show(this,"nuevoJuego");
        this.panelNuevoJuego.requestFocusInWindow();
        //Resetea el panel de juego
        this.panelNuevoJuego.reset();
    }

    /**
     * Cambia el menu actual al menu de ingresar puntajes.
     * @param movimientos numero de movimientos en la partida reciente
     */
    public void showPuntajeMenu(int movimientos) {
        this.cardLayout.show(this, "menuPuntaje");
        this.menuPuntaje.setPuntaje(movimientos);
        this.menuPuntaje.requestFocusInWindow();        
    }
}
