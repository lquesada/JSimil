/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;


/**
 * Coincidencia entre dos elementos comparados.
 * @author elezeta
 */
final public class Matching implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Similitud entre los dos elementos considerados en la coincidencia.
     */
    private double similitud;
    
    /**
     * Primer elemento de los considerados.
     */
    private CodeElement elementoA;
    
    /**
     * Segundo elemento de los considerados.
     */
    private CodeElement elementoB;
    
    /**
     * Profundidad de la coincidencia.
     */
    private int profundidad;
    
    /**
     * Constructor.
     * @param elementoA Primer elemento de los considerados.
     * @param elementoB Segundo elemento de los considerados.
     * @param similitud Similitud entre dichos elementos (puede modificarse).
     * @param profundidad Profundidad de la coincidencia.
     * @.post Objeto coincidencia inicializado.
     */
    protected Matching(CodeElement elementoA,
                            CodeElement elementoB,double similitud,
                            int profundidad) {
        this.similitud = similitud;
        this.elementoA = elementoA;
        this.elementoB = elementoB;
        this.profundidad = profundidad;
    }

    /**
     * Cambiar la profundidad de la coincidencia.
     * @param val Nueva profundidad.
     * @.post Profundidad cambiada.
     */
    void setProfundidad(int val) {
        profundidad = val;
    }
    
    /**
     * Asignar una similitud a la coincidencia.
     * @param similitud Similitud a asignar.
     * @.post Similitud asignada a la instancia.
     */
    void setSimilitud(double similitud) {
        this.similitud = similitud;
    }
    
    /**
     * Obtener la similitud asignada a la coincidencia.
     * @return Similitud asignada.
     */
    public double getSimilitud() {
        return similitud;
    }
    
    /**
     * Obtener el primer elemento asignado a la coincidencia.
     * @return Primer elemento asignado.
     */
    public CodeElement getElementoA() {
        return elementoA;
    }
    
    /**
     * Obtener el segundo elemento asignado a la coincidencia.
     * @return Segundo elemento asignado.
     */
    public CodeElement getElementoB() {
        return elementoB;
    }
    
    /**
     * Obtener la profundidad de la coincidencia.
     * @return Profundidad de la coincidencia.
     */
    public int getProfundidad() {
        return profundidad;
    }

}
