/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;
import java.util.EventListener;

/**
 * Interfaz del escuchador de eventos de JSimil.
 * @author elezeta
 */
public interface JSimilEventListener extends EventListener,Serializable {
    
    /**
     * Ha ocurrido un evento.
     * @param e Evento de JSimil ocurrido.
     * @.post Evento tratado.
     */
    public void sEventOcurred(JSimilEvent e);
    
}
