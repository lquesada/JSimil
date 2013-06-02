/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.languages;

/**
 * Excepción de idioma inexistente.
 * @author elezeta
 */
public class NoSuchLanguageException extends Exception {

    /**
     * Serializable
     */
    private static final long serialVersionUID = 0;

    /**
     * Constructor con cadena.
     * Lanza una excepción con el mensaje indicado.
     * @param mensaje Cadena de excepción.
     */
    NoSuchLanguageException(String mensaje) {
        super(mensaje);
    }
    
}
