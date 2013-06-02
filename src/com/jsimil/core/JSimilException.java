/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;

/**
 * Excepción lanzada por JSimil.
 * @author elezeta
 */
final public class JSimilException extends Exception implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Tipo de excepción.
     */
    private ExceptionType tipo;
    
    /**
     * Constructor con cadena y tipo.
     * Lanza una excepción con el mensaje indicado y con el tipo indicado.
     * @param tipo Tipo de excepción.
     * @param mensaje Cadena de excepción.
     * @.post Objeto inicializado.
     */
    protected JSimilException(ExceptionType tipo,String mensaje) {
        super(mensaje);
        this.tipo = tipo;
    }
    
    /**
     * Devuelve el tipo de excepción.
     * @return Tipo de excepción.
     */
    public ExceptionType getTipo() {
        return tipo;
    }
    
}
