/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;


/**
 * Elemento comparable.
 * @author elezeta
 */
public abstract class MatchableElement implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Id del elemento.
     */
    private int id;
    
    /**
     * Nombre del elemento.
     */
    private String nombre;
    
    /**
     * Número de instrucciones que posée el elemento.
     */
    private int nInstrucciones;
        
    /**
     * Nombre completo del elemento.
     */
    private String nombreCompleto;  

    /**
     * Nombre interno del elemento.
     */
    private String nombreInterno;  
        
    /**
     * Constructor.
     * @param nombre Nombre del elemento.
     * @param id Id del elemento.
     * @.post Objeto elemento inicializado.
     */
    protected MatchableElement(String nombre,int id) {
        this.nombre = nombre;
        this.id = id;
        nInstrucciones = 0;
    }
    
    /**
     * Devuelve la id del elemento.
     * @return Id del elemento.
     */
    int getId() {
        return id;
    }
    
    /**
     * Calcula los nombres completo e interno.
     * @.post Nombres calculados.
     */
    void setNombres() {
        if (getPadre() != null )
            this.nombreCompleto = getPadre().getNombreCompleto()+"."+nombre;
        else
            this.nombreCompleto = nombre;
        this.nombreInterno = getNombreCompleto();
        this.nombreInterno = this.nombreInterno.substring(
                this.nombreInterno.indexOf(".")+1);    
    }
    
    /**
     * Elemento padre.
     * @return Elemento padre.
     */
    abstract MatchableElement getPadre();
        
    /**
     * Cambia el número de instrucciones del elemento.
     * @param val Nuevo número de instrucciones.
     * @.post Número de instrucciones del elemento modificado.
     */
    void setNInstrucciones(int val) {
        nInstrucciones = val;
    }
    
    /**
     * Devuelve el nombre del elemento.
     * @return Nombre del elemento.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Devuelve el nombre completo del elemento.
     * @return Nombre completo del elemento.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    /**
     * Devuelve el nombre interno del elemento.
     * @return Nombre interno del elemento.
     */
    public String getNombreInterno() {
      return nombreInterno;
    }
    
    /**
     * Devuelve el número de instrucciones del elemento.
     * @return Número de instrucciones del elemento.
     */
    public int getNInstrucciones() {
        return nInstrucciones;
    }
    
    /**
     * Tipo del elemento.
     * @return "SBloque", "SMetodo", "SClase", "SPrograma".
     */
    public abstract String getTipo();

}