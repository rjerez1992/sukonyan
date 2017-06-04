package jsukonyan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JPanel;
import static jsukonyan.JSOURCE.*;
import static jsukonyan.KSOURCE.*;

/**
 *Esta clase maneja el panel de juego, donde se desarrolla toda la partida. * 
 */
public class JPanelJuego extends JPanel implements KeyListener{
    
    JPanelBase panelPadre;
    JSonido buttonSound;
    int iniX;
    int iniY;
    int nivelActual[][];
    int movimientos;
    int nroNivel;
    int jJugador;
    int kJugador;
    JSonido winSound;
    
    /**
     * Creamos el panel de juego
     * @param panelPadre panel que contiene a este panel
     */
    JPanelJuego (JPanelBase panelPadre)
    {
        super();
        this.panelPadre = panelPadre;        
        this.setBackground(Color.BLACK);
        this.buttonSound = new JSonido(BUTTONSOUND.toString());
        this.addKeyListener(this);         
        //Cordenadas iniciales para dibujar
        this.iniX = 15;
        this.iniY = 6;
        //Empezamos con el nivel 1
        this.nroNivel = 1;
        this.movimientos = 0;
        this.nivelActual = this.loadLevel(1);    
        this.jJugador = 0;
        this.kJugador = 0;
        this.winSound = new JSonido(WINSOUND.toString());       
        JSonido winSound;
    }
    
    /**
     * Resetea los valores basicos de la partida, para comenzar una nueva.
     */
    public void reset(){
        System.out.printf("Reseteado");        
        this.nroNivel = 1;
        this.movimientos = 0;
        this.nivelActual = this.loadLevel(this.nroNivel);
        this.repaint();       
    }
    
    /**
     * Revisa el array para ver cuantas cajas sueltas quedan
     * @return El estado del tablero, terminao o no.
     */
    public boolean levelOver(){
        for (int j=0; j<10; j+=1){
            for (int k=0; k<18; k+=1){
                if (this.nivelActual[j][k]==1)
                    return false;
                }
            }         
        return true;
        }         
    
    @Override
    public void paintComponent(Graphics g) {        
        //super.paintComponent(g);        
        
        //Pinta el fondo del componente
        g.drawImage(new JImagen(PANELJUEGO.toString()).getImage(),0, 0, null); //FONDO        
        
        //Pintamos los bloques vacios <Cambiado por una imagen>
        //for (int j=0; j<42*10; j+=42){
        //    for (int k=0; k<42*18; k+=42){
        //        g.drawImage(new JImagen(CUADROVACIO.toString()).getImage(), iniX+k, iniY+j, null);
        //    }
        
        Image imgTemp = null;
        //Pintamos los bloques segun el nivel
        for (int j=0; j<10; j+=1){
            for (int k=0; k<18; k+=1){
                //Valores -> 0=Vacio, 1=Materia, 2=Hoyo, 3=MateriaPuesta, 9=Personaje, 8=Muro
                if (nivelActual[j][k]==8) //Muro
                    imgTemp = new JImagen(MUROSOLIDO.toString()).getImage();
                else if (nivelActual[j][k]==1) //Muro
                    imgTemp = new JImagen(MATERIANEGRA.toString()).getImage();
                else if (nivelActual[j][k]==2) //Muro
                    imgTemp = new JImagen(ESPACIOMATERIA.toString()).getImage();
                else if (nivelActual[j][k]==3) //Muro
                    imgTemp = new JImagen(MATERIALISTA.toString()).getImage();
                
                if (nivelActual[j][k]!=0 && nivelActual[j][k]!=9 && nivelActual[j][k]!=7){
                    //Pintamos lo que resulte
                    g.drawImage(imgTemp, iniX+k*42, iniY+j*42, null);
                }
                
                //Dibuja el personaje al final para que aparezca
                else if (nivelActual[j][k]==9 || nivelActual[j][k]==7){
                    g.drawImage(new JImagen(NYANCAT.toString()).getImage(), iniX+k*42, iniY+j*42, null);            
                    this.kJugador = k;
                    this.jJugador = j;
                }
            }
        }
        
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
        g.drawString(""+this.nroNivel, 225-(fm.stringWidth(""+this.nroNivel)/2), 465); //Dibuja el texto nroNivel;
        g.drawString(""+this.movimientos, 575-(fm.stringWidth(""+this.movimientos)/2), 465); //Dibuja el texto nroMovimientos;
        
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
        //Esto pinta el mensaje de ayuda cuando se apreta la KEYHELP
        //Automaticamente desaparece con el repaint en el metodo keyReleased
        if (e.getKeyCode() == KEYHELP.getCode()){            
            this.getGraphics().drawImage(new JImagen(HELPBGIMAGE.toString()).getImage(),0, 0, null); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int mCol=0;
        int mFil = 0;
        
        if (e.getKeyCode() == KEYEXIT.getCode())//Cuando apretamos ESC vuelve al menu anterior
        {  
            this.buttonSound.play();
            this.panelPadre.showMenu(); 
        }
        
        //Verificamos la direccion del movimiento
        else if (e.getKeyCode() == KEYRIGHT.getCode())
        {            
            mCol = 1;
        }
        else if (e.getKeyCode() == KEYLEFT.getCode()){            
            mCol = -1;
        }
        else if (e.getKeyCode() == KEYDOWN.getCode()){            
            mFil = 1;
        }
        else if (e.getKeyCode() == KEYUP.getCode()){            
            mFil = -1;
        }
        
   
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////MOVIMIENTO
        //Valores -> 0=Vacio, 1=Materia, 2=Hoyo, 3=MateriaPuesta, 9=Personaje, 8=Muro, 7=Personaje sobre hoyo
        if (this.jJugador+mFil<10 && this.kJugador+mCol<18 && (mCol!=0 || mFil!=0) && this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]!=8){
            
            //Si el siguente bloque es vacio, movemos sin preguntar nada.
            if (this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]==0){  
                if (this.nivelActual[this.jJugador][this.kJugador]==7)
                    {this.nivelActual[this.jJugador][this.kJugador] = 2;}
                else
                    {this.nivelActual[this.jJugador][this.kJugador] = 0;}
                this.nivelActual[this.jJugador+mFil][this.kJugador+mCol] = 9;                                       
                //Las nuevas coordenadas del personaje se calculan automaticamente 
                this.movimientos++; //Aumentamos en uno los movimientos
            }           
            
            //Si el siguente bloque es hoyo, movemos tambien
            else if (this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]==2){       
                if (this.nivelActual[this.jJugador][this.kJugador]==7)
                    {this.nivelActual[this.jJugador][this.kJugador] = 2;}
                else
                    {this.nivelActual[this.jJugador][this.kJugador] = 0;}
                this.nivelActual[this.jJugador+mFil][this.kJugador+mCol] = 7;                                       
                //Las nuevas coordenadas del personaje se calculan automaticamente          
                this.movimientos++; //Aumentamos en uno los movimientos
            }        

            //Si el siguente bloque es materia, necesitamos un hoyo de materia o un espacio vacio luego de el
            else if (this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]==1 && this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)]==0){
                //Movemos el bloque al espacio vacio y luego el personaje al espacio del bloque
                this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)] = 1;
                if (this.nivelActual[this.jJugador][this.kJugador]==7) //Deja el bloque anterior libre
                    {this.nivelActual[this.jJugador][this.kJugador] = 2;}
                else
                    {this.nivelActual[this.jJugador][this.kJugador] = 0;}
                this.nivelActual[this.jJugador+mFil][this.kJugador+mCol] = 9; ///Mueve al personaje al bloque solicitado         
                this.movimientos++; //Aumentamos en uno los movimientos
            }
            else if (this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]==1 && this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)]==2){
                //Movemos la caja al hoyo y se transforma en caja puesta y luego el personaje al nuevo espacio, dejamos el espacio vacio por ultimo
                this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)] = 3;
                if (this.nivelActual[this.jJugador][this.kJugador]==7) //Deja el bloque anterior libre
                    {this.nivelActual[this.jJugador][this.kJugador] = 2;}
                else
                    {this.nivelActual[this.jJugador][this.kJugador] = 0;}
                this.nivelActual[this.jJugador+mFil][this.kJugador+mCol] = 9; ///Mueve al personaje al bloque solicitado  
                this.movimientos++; //Aumentamos en uno los movimientos
            }

            //Si el siguente bloque es Materia puesta, nesecitamos que el siguente a ese seria vacio o hoyo para materia
            else if (this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]==3 && this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)]==0){
                //Movemos la materia al bloque vacio y el nuevo bloque es personaje sobre hoyo
                this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)] = 1;
                if (this.nivelActual[this.jJugador][this.kJugador]==7) //Deja el bloque anterior libre
                    {this.nivelActual[this.jJugador][this.kJugador] = 2;}
                else
                    {this.nivelActual[this.jJugador][this.kJugador] = 0;}
                this.nivelActual[this.jJugador+mFil][this.kJugador+mCol] = 7; ///Mueve al personaje al bloque solicitado   
                this.movimientos++; //Aumentamos en uno los movimientos
            }
            else if (this.nivelActual[this.jJugador+mFil][this.kJugador+mCol]==3 && this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)]== 2){
                //Movemos la materia el otro hoyo y el nuevo bloque es personaje sobre hoyo          
                this.nivelActual[this.jJugador+(mFil*2)][this.kJugador+(mCol*2)] = 3;
                if (this.nivelActual[this.jJugador][this.kJugador]==7) //Deja el bloque anterior libre
                    {this.nivelActual[this.jJugador][this.kJugador] = 2;}
                else
                    {this.nivelActual[this.jJugador][this.kJugador] = 0;}
                this.nivelActual[this.jJugador+mFil][this.kJugador+mCol] = 7; ///Mueve al personaje al bloque solicitado   
                this.movimientos++; //Aumentamos en uno los movimientos
            }        
            //En ningun otro caso realizamos el movimiento 
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////MOVIMIENTO
        //Repintamos para reflejar los cambios         
        this.repaint();
        
        //Cuando hemos ganado el ultimo nivel, pasamos a guardar el puntaje
        if (this.nroNivel == 10 && levelOver()){
            this.winSound.play();
            //Cuando estamos en el ultimo nivel 
            this.panelPadre.showPuntajeMenu(this.movimientos);
        }
        //Cambiamos de nivel al terminar un nivel.
        else if(levelOver()){        
            this.winSound.play();
                    
            this.nroNivel+=1;
            this.nivelActual = this.loadLevel(this.nroNivel);            
            //Pequeño delay de ejecucion
            try {
                Thread.sleep(500);               
            } catch (InterruptedException ex) {                
            }    
        }           
    }
        
    /**
     *Esta funcion carga los niveles
     * @param lvl Nivel a cargar
     * @return int[][] del nivel cargado
     */
    public int[][] loadLevel(int lvl){
        //La matriz tiene 10 filas y 18 columnas
        //Valores -> 0=Muro, 1=Caja, 2=Hoyo, 3=CajaPuesta, 9=Personaje, 8=Vacio
        ///////////////////////////////////NIVEL1/////////////////////////////////////////////////
        int nivel1[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //Listo -> Muy facil
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,2,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,1,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,9,0,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL2///////////////////////////////////////////////
        int nivel2[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,2,8,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,8,8,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,8,8,1,0,1,2,8,0,0,0,0,0,0},
                                {0,0,0,0,8,2,0,1,9,8,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,8,8,8,1,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,2,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        ///////////////////////////////////NIVEL3////////////////////////////////////////////////
        int nivel3[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,9,1,2,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,2,1,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,0,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,0,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL4///////////////////////////////////////////////
        int nivel4[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,8,8,0,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,8,0,0,0,8,9,0,8,0,0,0,0,0},
                                {0,0,0,0,0,8,0,0,0,0,1,0,8,0,0,0,0,0},
                                {0,0,0,0,0,8,0,0,0,8,0,0,8,0,0,0,0,0},
                                {0,0,0,0,0,0,8,8,8,8,1,2,8,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,8,0,2,8,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL5///////////////////////////////////////////////
        int nivel5[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,8,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,8,8,8,8,0,0,0,8,0,0,0,0,0},
                                {0,0,0,0,8,0,0,0,9,0,0,0,0,8,0,0,0,0},
                                {0,0,0,0,8,0,1,0,8,8,0,8,0,8,0,0,0,0},
                                {0,0,0,0,8,0,0,1,8,2,0,0,0,8,0,0,0,0},
                                {0,0,0,0,8,0,0,0,8,2,0,0,8,0,0,0,0,0},
                                {0,0,0,0,0,8,8,8,0,8,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL6///////////////////////////////////////////////
        int nivel6[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,8,8,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,2,0,0,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,2,8,0,8,0,0,0,0,0,0},
                                {0,0,8,8,8,8,8,8,2,8,0,8,0,0,0,0,0,0},
                                {0,8,9,0,0,1,0,1,0,1,0,8,0,0,0,0,0,0},
                                {0,8,0,8,0,8,0,8,0,8,8,0,0,0,0,0,0,0},
                                {0,8,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0},
                                {0,0,8,8,8,8,8,8,8,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL7///////////////////////////////////////////////
        int nivel7[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,0,9,8,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,3,1,0,8,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,0,0,0,8,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,0,2,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,1,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,0,2,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL8///////////////////////////////////////////////
        int nivel8[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,8,8,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,8,0,2,2,8,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,8,0,1,0,0,0,0,8,0,0,0,0,0},
                                {0,0,0,0,0,8,0,0,8,1,8,0,8,0,0,0,0,0},
                                {0,0,0,0,0,8,0,9,0,2,1,0,8,0,0,0,0,0},
                                {0,0,0,0,0,0,8,8,8,8,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL9///////////////////////////////////////////////
        int nivel9[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,0,2,0,8,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,1,0,1,9,8,0,0,0,0,0},
                                {0,0,0,0,0,0,8,2,1,2,8,8,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        /////////////////////////////////////NIVEL10///////////////////////////////////////////////
        int nivel10[][]={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//arreglar
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,8,0,0,2,0,8,0,0,0,0,0,0},
                                {0,0,0,0,0,8,0,3,0,8,0,8,0,0,0,0,0,0},
                                {0,0,0,0,0,8,0,2,1,0,0,8,0,0,0,0,0,0},
                                {0,0,0,0,0,8,0,0,8,1,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,8,8,0,9,0,8,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,8,8,8,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        //////////////////////////////////////////////////////////////////////////////////////////////
        if (lvl==1)
            return nivel1;
        else if(lvl==2)
            return nivel2;
        else if(lvl==3)
            return nivel3;
        else if(lvl==4)
            return nivel4;
        else if(lvl==5)
            return nivel5;
        else if(lvl==6)
            return nivel6;
        else if(lvl==7)
            return nivel7;
        else if(lvl==8)
            return nivel8;
        else if(lvl==9)
            return nivel9;
        else if(lvl==10)
            return nivel10;              
        return nivel1;
    }
}

    

