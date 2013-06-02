/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Conjunto de las comparaciones entre programas obtenidas.
 * @author elezeta
 */
final public class ProgramComparisonList implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
        
    /**
     * Lista de resultados de comparar programas entre sí.
     */
    List<ProgramComparison> resultados;
    
    /**
     * Lista con los nombres de los programas comparados.
     */
    private List<String> nombres;
    
    /**
     * Constructor de resultados.
     * @.post Objeto resultados inicializado.
     */
    protected ProgramComparisonList() {
        resultados = new ArrayList<ProgramComparison>();
        nombres = new ArrayList<String>();
    }
    
    /**
     * Devuelve la comparación de dos programas.
     * @param nombre1 Nombre del primer programa.
     * @param nombre2 Nombre del segundo programa.
     * @return Comparación entre ambos programas.
     * @exception JSimilException No se puede comparar un programa consigo mismo.
     * @exception JSimilException No existe la comparación indicada.
     */
    public ProgramComparison getResultado(String nombre1,String nombre2)
            throws JSimilException {
        int i;
        ProgramComparison res;
        for (i = 0;i < resultados.size();++i) {
            res = resultados.get(i);
            if (((res.getNombre1().equals(nombre1) &&
                res.getNombre2().equals(nombre2))) ||
                ((res.getNombre1().equals(nombre2) &&
                res.getNombre2().equals(nombre1)))) {
                return res;
            }
        }
        throw new JSimilException(ExceptionType.NO_EXISTE_COMPARACION_INDICADA,
                    "No existe la comparación indicada");
    }
    
    /**
     * Devuelve la lista completa de comparaciones.
     * @return Comparación entre todos los programas.
     */
    public List<ProgramComparison> getResultados() {
        return Collections.unmodifiableList(resultados);
    }
    
    /**
     * Devuelve la lista de nombres de programas comparados.
     * @return Nombres de todos los programas.
     */
    public List<String> getNombres() {
        return nombres;
    }
    
}
