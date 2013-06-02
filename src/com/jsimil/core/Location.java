/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;

/**
 * Localización de un elemento en ficheros.
 * @author elezeta
 */
final public class Location implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Linea donde comienza el elemento descrito.
     */
    private int lineaIni;
    
    /**
     * Linea donde termina el elemento descrito.
     */
    private int lineaFin;
    
    /**
     * Fichero donde se encuentra el elemento descrito.
     */
    private CodeFile fichero;
    
    /**
     * Constructor.
     * @param fichero Fichero donde se encuentra el elemento descrito.
     * @.post Objeto inicializado.
     */
    protected Location(CodeFile fichero) {
        this.fichero = fichero;
        lineaIni = -1;
        lineaFin = -1;
    }
    
    /**
     * Cambiar la linea final.
     * @param lineaFin Nuevo valor de linea final.
     * @.post El valor de la linea final ha cambiado.
     */
    void setLineaFin(int lineaFin) {
        this.lineaFin = lineaFin;
    }
    
    /**
     * Cambiar la linea inicial.
     * @param lineaIni Nuevo valor de linea inicial.
     * @.post El valor de la linea inicial ha cambiado.
     */
    void setLineaIni(int lineaIni) {
        this.lineaIni = lineaIni;
    }
    
    /**
     * Devuelve el valor de la linea inicial.
     * @return El valor de la linea inicial.
     */
    public int getLineaIni() {
        return lineaIni;
    }
    
    /**
     * Devuelve el valor de la linea final.
     * @return El valor de la linea final.
     */
    public int getLineaFin() {
        return lineaFin;
    }
    
    /**
     * Devuelve el fichero donde se encuentra el elemento.
     * @return El fichero donde se encuentra el elemento.
     */
    public CodeFile getFichero() {
        return fichero;
    }
    
}
