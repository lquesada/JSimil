/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.core;

import java.io.Serializable;
import java.util.EventObject;

/**
 * Evento lanzado por JSimil.
 * @author elezeta
 */
final public class JSimilEvent extends EventObject implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Tipo del evento.
     */
    private EventType tipo;
    
    /**
     * Mensaje del evento.
     */
    private String mensaje;
    
    /**
     * Constructor.
     * @param source Objeto que lanzó el evento.
     * @param tipo Tipo de evento lanzado.
     * @param mensaje Mensaje del evento lanzado.
     * @.post Objeto inicializado.
     */
    protected JSimilEvent(Object source,EventType tipo,String mensaje) {
        super(source); 
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    
    /**
     * Devuelve el tipo del evento.
     * @return Tipo del evento.
     */
    public EventType getTipo() {
        return tipo;
    }
    
    /**
     * Devuelve el mensaje del evento.
     * @return Mensaje del evento.
     */
    public String getMensaje() {
        return mensaje;
    }
    
}
