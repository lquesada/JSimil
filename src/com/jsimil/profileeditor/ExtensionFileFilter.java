/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.profileeditor;

import java.io.File;

/**
 * Filtrador de ficheros por extensión.
 * @author elezeta
 */
public final class ExtensionFileFilter
        extends javax.swing.filechooser.FileFilter {

    /**
     * Descripción del filtro.
     */
    private String desc;
    
    /**
     * Extensión del filtro.
     */
    private String ext;
    
    /**
     * Constructor.
     * @param desc Descripción.
     * @param ext Extensión.
     */
    public ExtensionFileFilter(String desc,String ext) {
        this.desc = desc;
        this.ext = ext.toLowerCase();
    }
            
    /**
     * Se acepta si la extensión coincide.
     * @param pathname Ruta del fichero a comparar.
     * @return true si coincide, false si no.
     */
    public boolean accept(File pathname) {
        return pathname.getAbsolutePath().toLowerCase().endsWith(ext) ||
                pathname.isDirectory();
    }

    /**
     * Devuelve la descripción del filtro.
     * @return descripción del filtro.
     */
    @Override
    public String getDescription() {
        return desc+" (*"+ext+")";
    }
    

}
