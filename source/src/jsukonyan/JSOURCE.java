package jsukonyan;

/**
 * Este ENUM devuelve los valores para los recursos usados en la aplicacion.
 * En esta clase se guardan las rutas "literales" de cada recurso utilizado en la aplicacion,
 * entiendase como recurso, imagenes, audios y otros medios.
 */
public enum JSOURCE {
    //Creamos cada ENUM para cada recurso.
    //Cualquier nuevo recurso, debe agregarse aqui.
    //Todo debe estar incluido en la carpeta "resources".
    //Los botones no deben llevar extension, "png" se agregará automaticamente.  
    FONDOPUNTAJES("record_menu.png"),
    WINSOUND("win_sound.mp3"),
    CUADROVACIO("background_block.png"),
    ESPACIOMATERIA("unfilled_hole.png"),
    MUROSOLIDO("solid_wall.png"),
    NYANCAT("nyan_cat.png"),
    MATERIALISTA("filled_hole.png"),
    MATERIANEGRA("black_block.png"),
    PANELJUEGO("playing_background.png"),
    PUNTAJES("scores.dat"),
    FUENTE("nyanfont.ttf"), //fuente del juego
    ARRIBA("up"), //imagen arriba
    ABAJO("down"), //imagen abajo
    IZQUIERDA("left"), //imagen izquierda
    DERECHA("right"), //imagen boton derecha
    VOLVER("back"), //imagen boton volver
    MENUOPCIONES ("options_menu.png"),
    HELPBGIMAGE("help_menu.png"), //imagen menu ayuda
    ABOUTBGIMAGE("about_menu.png"), //imagen menu acerca de
    BUTTONSOUND("button.mp3"), //sonido de boton
    EXITBUT("exit"), //imagen boton salir
    OPTIONSBUT("options"), //imagen boton opciones
    ABOUTBUT("about"), //imagen boton acerca de
    NEWGAMEBUT("new_game"), //imagen boton nuevo juego
    MAINICON("main_icon.png"),  //imagen icono 
    MAINBGIMAGE("main_menu.png"),
    MAINBGMUSIC("nyan.mp3");
    
  
    //Las variables contenidas en cada ENUM
    String strText;
    
    //Constructor del ENUM
    //El constructor automaticamente agrega "resources" que es la carpeta
    //donde se encuentran guardados todos los recursos.
    /**
     * Construye el enum
     * @param strText valor textual del enum, en general son rutas.
     */
    private JSOURCE(String strText){
        this.strText = "resources/"+strText;
    }
    
    /**
     * Devuelve el valor String de cada ENUM.
     * @return String correspondiente al ENUM
     */
    @Override
    public String toString(){
        return this.strText;
    }
}
