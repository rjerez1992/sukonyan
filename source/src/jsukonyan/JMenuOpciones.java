package jsukonyan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import static jsukonyan.JSOURCE.*;//imagenes
import static jsukonyan.KSOURCE.*;//botones


/**
 * Esta clase maneja la ventana de opciones y la configuracion de teclas.
 * La configuracion de teclas no persiste luego de cerrar el programa, a diferencia de los puntajes 
 */
public class JMenuOpciones extends JPanel implements KeyListener
{
     JPanelBase panelPadre;
     JMenuItem arriba;
     JMenuItem abajo;
     JMenuItem izquierda;
     JMenuItem derecha;
     JMenuItem volver;
     JSonido sonidoBoton;
     boolean arribaConf; //estos sirven para ver cual se configura
     boolean izquierdaConf;
     boolean derechaConf;
     boolean abajoConf;
     JTexto arribaAsig;//estos para mostrar la tecla actual configurada
     JTexto izquierdaAsig;
     JTexto derechaAsig;
     JTexto abajoAsig;
     JTexto ingresar;
     
     int focusBoton;
     
     //CONTRULLE EL PANEL Y AGREGA EL KEYSLISTENER
     /**
      * Construimos el panel de Opciones
      * @param panelPadre panel que contiene a este panel
      */
    JMenuOpciones(JPanelBase panelPadre)
    {
        super();
        this.panelPadre = panelPadre;        
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        //Creamos el sonido para los botones
        this.sonidoBoton = new JSonido(BUTTONSOUND.toString());
        
        this.arriba = new JMenuItem(ARRIBA.toString());
        this.arriba.moveTo(135, 165);
        this.add(this.arriba);
        this.arriba.onFocus();
        this.focusBoton = 0;
       
        this.izquierda = new JMenuItem(IZQUIERDA.toString());
        this.izquierda.moveTo(135, 165 + 40);
        this.add(this.izquierda);
        
        this.derecha = new JMenuItem (DERECHA.toString());
        this.derecha.moveTo(138, 165 + 82);
        this.add(this.derecha);
        
        this.abajo = new JMenuItem (ABAJO.toString());
        this.abajo.moveTo(138, 165 + 122);
        this.add(this.abajo);
        
        this.volver = new JMenuItem (VOLVER.toString());
        this.volver.moveTo(284, 165 + 182);
        this.add(this.volver);
        
        this.arribaConf = false;//estos sirven para ver cual configuramos
        this.izquierdaConf = false;
        this.derechaConf = false;
        this.abajoConf = false;
        
        //Los labels de abajo los utilizamos para mostrar la tecla actualmente asignada
        this.arribaAsig = new JTexto(KeyEvent.getKeyText(KEYUP.getCode()), 20f);//crea un texto con el nombre de la tecla
        this.arribaAsig.moveTo(556, 211);
        this.add(arribaAsig);
    
        this.izquierdaAsig = new JTexto(KeyEvent.getKeyText(KEYLEFT.getCode()), 20f);//crea un texto con el nombre de la tecla
        this.izquierdaAsig.moveTo(556, 211+42);
        this.add(izquierdaAsig);
   
        this.derechaAsig = new JTexto(KeyEvent.getKeyText(KEYRIGHT.getCode()), 20f);//crea un texto con el nombre de la tecla
        this.derechaAsig.moveTo(556, 211+82);
        this.add(derechaAsig);
    
        this.abajoAsig = new JTexto(KeyEvent.getKeyText(KEYDOWN.getCode()), 20f);//crea un texto con el nombre de la tecla
        this.abajoAsig.moveTo(556, 211+122);
        this.add(abajoAsig);
        
        this.ingresar = new JTexto("<<    >>", 20f);    
        this.ingresar.moveTo(1000, 1000);
        this.add(this.ingresar);
    }
    
    
     public void keyReleased(KeyEvent e) {
        
        if(!arribaConf && !izquierdaConf && !derechaConf && !abajoConf)
        {
            //Verificamos la tecla que fue presionada, la comparamos con las teclas que tenemos configuradas en el ENUM
            //Luego de esto movemos la posicion del menu segun la tecla presionada.

            if (e.getKeyCode() == KEYDOWN.getCode()){ //cuando apretamos abajo 
                if (this.focusBoton < 4){
                    this.focusBoton++;
                    this.sonidoBoton.play();
                }
                if (this.focusBoton <= 0)
                    this.focusBoton = 0;
                else if (this.focusBoton >= 4)
                    this.focusBoton = 4;
            }

            else if (e.getKeyCode() == KEYUP.getCode())//cuando apretamos arriba
            {
                if (this.focusBoton > 0){
                    this.focusBoton--;
                    this.sonidoBoton.play();
                }
                if (this.focusBoton <= 0)
                    this.focusBoton = 0;
                else if (this.focusBoton >= 4)
                    this.focusBoton = 4;
            }
            
             else if (e.getKeyCode() == KEYACCEPT.getCode() && this.focusBoton ==0)//cuando apretamos enter en volver
            {
                this.sonidoBoton.play();
                this.arribaConf = true;  //556 / 211         
                this.ingresar.moveTo(this.arribaAsig.getX(), this.arribaAsig.getY());              
                this.remove(this.arribaAsig);                
            } 

            else if (e.getKeyCode() == KEYACCEPT.getCode() && this.focusBoton ==1)
            {
                this.sonidoBoton.play();
                this.izquierdaConf = true;  //556 / 211         
                this.ingresar.moveTo(this.izquierdaAsig.getX(), this.izquierdaAsig.getY());              
                this.remove(this.izquierdaAsig);                
            }  

            else if (e.getKeyCode() == KEYACCEPT.getCode() && this.focusBoton ==2)
            {
                this.sonidoBoton.play();
                this.derechaConf = true;  //556 / 211         
                this.ingresar.moveTo(this.derechaAsig.getX(), this.derechaAsig.getY());              
                this.remove(this.derechaAsig);                
            } 

            else if (e.getKeyCode() == KEYACCEPT.getCode() && this.focusBoton ==3)
            {
                this.sonidoBoton.play();
                this.abajoConf = true;  //556 / 211         
                this.ingresar.moveTo(this.abajoAsig.getX(), this.abajoAsig.getY());              
                this.remove(this.abajoAsig);                
            } 

            
            else if (e.getKeyCode() == KEYACCEPT.getCode() && this.focusBoton ==4)
            {
                this.sonidoBoton.play();
                this.panelPadre.showMenu();
            }       
            
            //Actualizamos el cambio
            if (focusBoton == 0)//cambiamos los focus de los botones
            {
                this.arriba.onFocus();
                this.izquierda.deFocus();
                this.derecha.deFocus();
                this.abajo.deFocus();
                this.volver.deFocus();
            }

            else if (focusBoton == 1)
            {
                this.arriba.deFocus();
                this.izquierda.onFocus();
                this.derecha.deFocus();
                this.abajo.deFocus();
                this.volver.deFocus();
            }

            else if (focusBoton == 2)
            {
                this.arriba.deFocus();
                this.izquierda.deFocus();
                this.derecha.onFocus();
                this.abajo.deFocus();
                this.volver.deFocus();
            }

            else if (focusBoton == 2)
            {
                this.arriba.deFocus();
                this.izquierda.deFocus();
                this.derecha.onFocus();
                this.abajo.deFocus();
                this.volver.deFocus();
            }

            else if (focusBoton == 3)
            {
                this.arriba.deFocus();
                this.izquierda.deFocus();
                this.derecha.deFocus();
                this.abajo.onFocus();
                this.volver.deFocus();
            }

         else if (focusBoton == 4)
            {
                this.arriba.deFocus();
                this.izquierda.deFocus();
                this.derecha.deFocus();
                this.abajo.deFocus();
                this.volver.onFocus();
            }


            this.repaint();//pintamos o sino no se ve
        }//cierra el if
        
        else if (arribaConf || izquierdaConf || derechaConf || abajoConf) 
        {
            int ei = e.getKeyCode();
            if (arribaConf)
            {
                //Realizamos la configuracion si la tecla es distinta a las demas
                if (ei!=KEYDOWN.getCode() && ei!=KEYRIGHT.getCode() && ei!=KEYLEFT.getCode() && ei!=KEYHELP.getCode() && ei!=KEYACCEPT.getCode() && ei!=KEYEXIT.keyCode){
                    //Configuramos la nueva tecla
                    KEYUP.setCode(e.getKeyCode());  
                    //Cambiamos el valor a falso para que no entre aqui de nuevo
                    arribaConf = false;
                    this.sonidoBoton.play();//reproduce el sonido              
                    //Solucion parche para actualizar el texto
                    this.arribaAsig = new JTexto(KeyEvent.getKeyText(KEYUP.getCode()), 20f);//crea un texto con el nombre de la tecla
                    this.arribaAsig.moveTo(556, 211);//movemos para que se vea
                    this.add(this.arribaAsig);//agregamos al panel
                    //Solucion parche*
                    //Aun no entiendo por que no repinta luego de hacerlo, aunque hay un repaint abajo.                
                    this.ingresar.moveTo(1000, 10000);//Solucion parche*
                    this.panelPadre.showAbout();
                    this.panelPadre.showMenuOpciones();
                }
                //Si es alguna de esas, seguiremos esperando por una tecla distinta.
            }
            else if (izquierdaConf)
            {
                //Realizamos la configuracion si la tecla es distinta a las demas
                if (ei!=KEYDOWN.getCode() && ei!=KEYRIGHT.getCode() && ei!=KEYUP.getCode() && ei!=KEYHELP.getCode() && ei!=KEYACCEPT.getCode() && ei!=KEYEXIT.keyCode){
                    //Configuramos la nueva tecla
                    KEYLEFT.setCode(e.getKeyCode());  
                    //Cambiamos el valor a falso para que no entre aqui de nuevo
                    izquierdaConf = false;
                    this.sonidoBoton.play();//reproduce el sonido              
                    //Solucion parche para actualizar el texto
                    this.izquierdaAsig = new JTexto(KeyEvent.getKeyText(KEYLEFT.getCode()), 20f);//crea un texto con el nombre de la tecla
                    this.izquierdaAsig.moveTo(556, 211+42);//movemos para que se vea
                    this.add(this.izquierdaAsig);//agregamos al panel
                    //Solucion parche*
                    //Aun no entiendo por que no repinta luego de hacerlo, aunque hay un repaint abajo.                
                    this.ingresar.moveTo(1000, 10000);//Solucion parche*
                    this.panelPadre.showAbout();
                    this.panelPadre.showMenuOpciones();
                }
                //Si es alguna de esas, seguiremos esperando por una tecla distinta.
            }
            else if (derechaConf)
            {
                //Realizamos la configuracion si la tecla es distinta a las demas
                if (ei!=KEYDOWN.getCode() && ei!=KEYLEFT.getCode() && ei!=KEYUP.getCode() && ei!=KEYHELP.getCode() && ei!=KEYACCEPT.getCode() && ei!=KEYEXIT.keyCode){
                    //Configuramos la nueva tecla
                    KEYRIGHT.setCode(e.getKeyCode());  
                    //Cambiamos el valor a falso para que no entre aqui de nuevo
                    derechaConf = false;
                    this.sonidoBoton.play();//reproduce el sonido              
                    //Solucion parche para actualizar el texto
                    this.derechaAsig = new JTexto(KeyEvent.getKeyText(KEYRIGHT.getCode()), 20f);//crea un texto con el nombre de la tecla
                    this.derechaAsig.moveTo(556, 211+82);//movemos para que se vea
                    this.add(this.derechaAsig);//agregamos al panel
                    //Solucion parche*
                    //Aun no entiendo por que no repinta luego de hacerlo, aunque hay un repaint abajo.                
                    this.ingresar.moveTo(1000, 10000);//Solucion parche*
                    this.panelPadre.showAbout();
                    this.panelPadre.showMenuOpciones();
                }
                //Si es alguna de esas, seguiremos esperando por una tecla distinta.
            }
            else if (abajoConf)
            {
                if (ei!=KEYRIGHT.getCode() && ei!=KEYLEFT.getCode() && ei!=KEYUP.getCode() && ei!=KEYHELP.getCode() && ei!=KEYACCEPT.getCode() && ei!=KEYEXIT.keyCode){
                    //Configuramos la nueva tecla
                    KEYDOWN.setCode(e.getKeyCode());  
                    //Cambiamos el valor a falso para que no entre aqui de nuevo
                    abajoConf = false;
                    this.sonidoBoton.play();//reproduce el sonido              
                    //Solucion parche para actualizar el texto
                    this.abajoAsig = new JTexto(KeyEvent.getKeyText(KEYDOWN.getCode()), 20f);//crea un texto con el nombre de la tecla
                    this.abajoAsig.moveTo(556, 211+122);//movemos para que se vea
                    this.add(this.abajoAsig);//agregamos al panel
                    //Solucion parche*
                    //Aun no entiendo por que no repinta luego de hacerlo, aunque hay un repaint abajo.                
                    this.ingresar.moveTo(1000, 10000);//Solucion parche*
                    this.panelPadre.showAbout();
                    this.panelPadre.showMenuOpciones();
                }
                //Si es alguna de esas, seguiremos esperando por una tecla distinta.
            }
            this.repaint();
        }
            
        
     }//cierra el metodo
     
    /**
     * Pintamos le componente agregandole un fondo, antes de pintar los demas componentes.
     * @param g Graficos del componente
     */     
    @Override
    public void paintComponent(Graphics g) {        
        super.paintComponent(g);        
        
        //Pinta el fondo del componente
        g.drawImage(new JImagen(MENUOPCIONES.toString()).getImage(),0, 0, null); 
        
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
}
