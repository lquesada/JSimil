/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.core;

import java.io.Serializable;

/**
 * Clase principal del detector de similitudes JSimil.
 * @author elezeta
 */
final public class JSimil implements Serializable {
    
    /**
     * Serializable
     */
    static final long serialVersionUID = 10L;
    
    /**
     * Versión
     */
    private static String version = "1.0.7";

    /**
     * Autores
     */
    private static String[] autores = {"Luis Quesada"};

    /**
     * Web
     */
    private static String web = "http://www.jsimil.com";

    /**
     * Tiempo de desarrollo
     */
    private static String tDes = "2007-2009";
    
    /**
     * Perfil a utilizar en los procesamientos.
     */
    private MatchingProfile perfil;
    
    /**
     * Configuración a utilizar en los procesamientos.
     */
    private Configuration config;
    
    /**
     * Batería de programas a utilizar en los procesamientos.
     */
    private ProgramBattery bateria;

    /**
     * Resultados obtenidos en los procesamientos.
     */
    private ProgramComparisonList resultados;
    
    /**
     * Número de hebras a utilizar.
     */
    private int nhebras;
    
    /**
     * Constructor por defecto, inicializa a null.
     * @.post Instancia inicializada.
     */
    public JSimil() {
        perfil = null;
        config = null;
        bateria = null;
        resultados = null;
        setNHebras(Runtime.getRuntime().availableProcessors()+2);
    }
    
    /**
     * Obtener el perfil asignado a los procesamientos.
     * @return Perfil asignado.
     */
    public MatchingProfile getPerfil() {
        return perfil;
    }
        
    /**
     * Asignar un perfil a los procesamientos.
     * @param perfil Perfil a asignar.
     * @.post Perfil asignado a la instancia.
     * @.post Resultados (si los había) eliminados.
     */
    public void setPerfil(MatchingProfile perfil) {
        this.perfil = perfil;
        resultados = null;
    }

    /**
     * Obtener la configuración asignada a los procesamientos.
     * @return Configuración asignada.
     */
    public Configuration getConfig() {
        return config;
    }
        
    /**
     * Asignar una configuración a los procesamientos.
     * @param config Configuración a asignar.
     * @.post Configuración asignada a la instancia.
     * @.post Resultados (si los había) eliminados.
     */
    public void setConfig(Configuration config) {
        this.config = config;
        resultados = null;
    }

    /**
     * Obtener la batería asignada a los procesamientos.
     * @return Batería de programas asignada.
     */
    public ProgramBattery getBateria() {
        return bateria;
    }
        
    /**
     * Asignar una batería de programas a los procesamientos.
     * @param bateria Batería de programas a asignar.
     * @.post Batería de programas asignada a la instancia.
     * @.post Resultados (si los había) eliminados.
     */
    public void setBateria(ProgramBattery bateria) {
        this.bateria = bateria;
        resultados = null;
    }
    
    /**
     * Obtener los resultados obtenidos tras los procesamientos.
     * @return Resultados obtenidos en los procesamientos.
     */
    public ProgramComparisonList getResultados() {
        return resultados;
    }

    /**
     * Llevar a cabo la carga.
     * @.post Bateria cargada.
     * @exception JSimilException No hay ninguna configuración asignada.
     * @exception JSimilException No hay ninguna batería de programas asignada.
     * @exception JSimilException La configuración no valida.
     * @exception JSimilException La batería de programas no valida.
     * @exception JSimilException Resto de excepciones de SBateria.carga()
     */
    public void carga() throws JSimilException {
        if (config == null)
            throw new JSimilException(ExceptionType.NO_CONFIG_ASIGNADA,
                                 "No hay configuración asignada.");
        if (bateria == null)
            throw new JSimilException(ExceptionType.NO_BATERIA_ASIGNADA,
                                 "No hay batería asignada.");
        bateria.validate();
        config.validate();
        bateria.carga(config,nhebras);
    }
    
    /**
     * Llevar a cabo el procesamiento.
     * @.post Resultados obtenidos.
     * @exception JSimilException No hay ningún perfil asignado.
     * @exception JSimilException No hay ninguna batería de programas asignada.
     * @exception JSimilException El perfil no valida.
     * @exception JSimilException La batería de programas no esta cargada.
     */
    public void procesa() throws JSimilException {
        if (perfil == null)
            throw new JSimilException(ExceptionType.NO_PERFIL_ASIGNADO,
                                 "No hay perfil asignado.");
        if (config == null)
            throw new JSimilException(ExceptionType.NO_CONFIG_ASIGNADA,
                                 "No hay configuración asignada.");
        if (bateria == null)
            throw new JSimilException(ExceptionType.NO_BATERIA_ASIGNADA,
                                 "No hay batería asignada.");
        if (bateria.getProgramas().isEmpty())
            throw new JSimilException(ExceptionType.NO_BATERIA_CARGADA,
                                 "No hay batería cargada.");
        perfil.validate();
        bateria.validate();
        //config.validate();
        resultados = bateria.procesa(perfil,nhebras);
    }
    
    /**
     * Devuelve la versión
     * @return Versión del programa.
     */
    public String getVersion() {
        return version+"_("+serialVersionUID+")";
    }
    
    /**
     * Devuelve los autores.
     * @return Autores del programa.
     */
    public String[] getAutores() {
        return autores;
    }

    /**
     * Devuelve la web.
     * @return Web del programa.
     */
    public String getWeb() {
        return web;
    }
    
    /**
     * Devuelve el tiempo de desarrollo.
     * @return Tiempo de desarrollo.
     */
    public String getTDes() {
        return tDes;
    }
       
    /**
     * Devuelve el número de hebras.
     * @return Número de hebras.
     */
    public int getNHebras() {
        return nhebras;
    }    
    
    /**
     * Cambia el número de hebras.
     * @param nhebras Nuevo valor.
     * @.post Número de hebras cambiado, 1 como mínimo.
     */
    public void setNHebras(int nhebras) {
        this.nhebras = nhebras;
        if (this.nhebras < 1)
            this.nhebras = 1;
    }
    
}
