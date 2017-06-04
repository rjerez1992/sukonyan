package jsukonyan;

import java.awt.event.KeyEvent;



/**
 *Este ENUM guarda los valores para las teclas que utiliza la aplicacion.
 * Por default vienen algunas teclas definidas, una vez que una tecla es
 * configurada, el valor en este enum es modificado. * 
 */
public enum KSOURCE {
    KEYUP(KeyEvent.VK_W),
    KEYDOWN(KeyEvent.VK_S),
    KEYLEFT(KeyEvent.VK_A),
    KEYRIGHT(KeyEvent.VK_D),
    KEYACCEPT(KeyEvent.VK_ENTER),
    KEYHELP(KeyEvent.VK_H),
    KEYEXIT(KeyEvent.VK_ESCAPE);
    
    int keyCode;
    
    /**
     * Creamos el enum
     * @param keyCode codigo de la tecla guardado
     */
    KSOURCE(int keyCode){
        this.keyCode = keyCode;
    }
    
    /**
     * Retorna el dodigo int correspondiente a la tecla que corresponde al ENUM.
     * @return int correspondiente a la tecla
     */
    public int getCode(){
        return this.keyCode;
    }
    
    /**
     *Modifica el valor del ENUM para asignar una nueva tecla.
     * @param keyCode valor int de la nueva tecla
     */
    public void setCode(int keyCode){
        this.keyCode = keyCode;
    }    
}
