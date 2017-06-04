package jsukonyan;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import static jsukonyan.JSOURCE.*;

/**
 *Esta clase maneja la ventana principal de la aplicacion (JFrame). 
 *Este marco se encarga de cambiar entre los distintos paneles. * 
 */
class JVentana extends JFrame implements WindowListener
{    
    //Musica de fondo
    private JSonido soundFile;
    //Panel base para los demas
    private JPanelBase panelBase;
    
    /**
     * Creamos la ventana.
     */
    public JVentana()
    {
        //Agregamos el titulo de la ventana
        this.setTitle("JSukonyan");        
        //Agregamos el icono de la ventana
        this.setIconImage(new JImagen(MAINICON.toString()).getImage());
        
        //Opciones basicas de la ventana
        super.setSize(800, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.addWindowListener(this);
        super.setResizable(false);
        //Se crea al medio de la ventana
        super.setLocationRelativeTo(null);
        //Agregamos el panel base
        this.panelBase = new JPanelBase(this);        
        this.add(panelBase);
                
        //Agregamos la musica de fondo
        this.soundFile = new JSonido(MAINBGMUSIC.toString());          
    }
    
    /**
     * Estas acciones se realizan al momento de iniciar la ventana.
     */
    public void start()
    {
        //Reproducimos el audio 
        this.soundFile.play();        
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
    }

    /**
     * Estas acciones se realizan al momento de cerrar la ventana.
     * @param e null
     */
    @Override
    public void windowClosed(WindowEvent e)
    {
        //Terminamos la reproduccion del audio
        //Nos aseguramos de terminal cualquier otro proceso
        this.soundFile.close();
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
    }
    
}