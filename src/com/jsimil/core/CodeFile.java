/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fichero de un programa a comparar.
 * @author elezeta
 */
final public class CodeFile implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Ruta al fichero.
     */
    private String ruta;
    
    /**
     * Ruta al fichero original.
     */
    private String rutaorig;
    
    /**
     * Contenidos del fichero.
     */
    private List<String> datos;
    
    /**
     * Constructor.
     * @.post Objeto inicializado.
     */
    protected CodeFile() {
        ruta = "";
        rutaorig = "";
        datos = new ArrayList<String>();
    }
    
    /**
     * Carga el contenido del fichero en cuestión a memoria.
     * @.post Cargado el fichero a memoria.
     * @exception JSimilException La ruta no es válida o accesible.
     */
    void carga() throws JSimilException {
        String linea;
        try {
            BufferedReader in = new BufferedReader(new 
            FileReader(new File(ruta)));
            while ((linea = in.readLine()) != null) {
                datos.add(linea);
            }
            in.close();
        } catch(IOException e) {
            throw new JSimilException(ExceptionType.ERROR_LEYENDO_ARCHIVOS,
                    "Error leyendo fichero "+ruta);
        }
    }
    
    /**
     * Asigna la ruta del fichero.
     * @.post Ruta asignada al fichero.
     * @param ruta Ruta del fichero.
     */
    void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Asigna la ruta original del fichero.
     * @.post Ruta asignada al fichero.
     * @param ruta Ruta del fichero.
     */
    void setRutaOrig(String ruta) {
        this.rutaorig = ruta;
    }
    
    /**
     * Devuelve la ruta asignada al fichero.
     * @return Ruta asignada al fichero.
     */
    public String getRuta() {
        return ruta;
    }
    
    /**
     * Devuelve la ruta original asignada al fichero.
     * @return Ruta asignada al fichero.
     */
    public String getRutaOrig() {
        return rutaorig;
    }
    
    /**
     * Devuelve la array con los datos del fichero.     
     * @return Array con los datos del fichero.
     */
    public List<String> getDatos() {
        return Collections.unmodifiableList(datos);
    }
    
}
