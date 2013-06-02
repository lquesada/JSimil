/*
 * JSimil. 2007-2010 Luis Quesada.
 */ 

package com.jsimil.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparación de dos programas y sus elementos.
 * @author elezeta
 */
final public class ProgramComparison implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
        
    /**
     * Grado de similitud entre los programas comparados.
     */
    private double similitud;
    
    /**
     * Listado de coincidencias entre los miembros de estos programas.
     */
    List<Matching> coincidencias;
    
    /**
     * Nombre del primer programa.
     */
    private String nombre1;
    
    /**
     * Nombre del segundo programa.
     */
    private String nombre2;

    /**
     * Tamaño del primer programa.
     */
    private int tama1;
    
    /**
     * Tamaño del segundo programa.
     */
    private int tama2;

    /**
     * Lista de paquetes del primer programa.
     */
    List<String> paquetes1;

    /**
     * Lista de paquetes del segundo programa.
     */
    List<String> paquetes2;
    
    /**
     * Constructor.
     * @.post Inicializado objeto resultado.
     */
    protected ProgramComparison() {
        coincidencias = new ArrayList<Matching>();
        similitud = -1;
        nombre1 = "";
        nombre2 = "";
    }
       
    /**
     * Cambia el valor de similitud de este resultado.
     * @param similitud
     * @.post Valor de similitud cambiado.
     */
    void setSimilitud(double similitud) {
        this.similitud = similitud;
    }
    
    /**
     * Cambia el nombre del segundo programa de este resultado.
     * @param nombre2 Nuevo nombre.
     * @.post Nombre del segundo programa cambiado.
     */
    void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    /**
     * Cambia el nombre del primer programa de este resultado.
     * @param nombre1 Nuevo nombre.
     * @.post Nombre del primer programa cambiado.
     */
    void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    
    /**
     * Cambia el tamaño del segundo programa de este resultado.
     * @param val Nuevo valor.
     * @.post Tamaño del segundo programa cambiado.
     */
    void setTama2(int val) {
        this.tama2 = val;
    }
    
    /**
     * Cambia el tamaño del primer programa de este resultado.
     * @param val Nuevo valor.
     * @.post Nombre del primer programa cambiado.
     */
    void setTama1(int val) {
        this.tama1 = val;
    }
    
    /**
     * Cambia la lista de paquetes del primer programa de este resultado.
     * @param val Nuevo valor.
     * @.post Lista de paquetes del primer programa cambiada.
     */
    void setPaquetes1(List<String> val) {
        this.paquetes1 = val;
    }
    
    /**
     * Cambia la lista de paquetes del segundo programa de este resultado.
     * @param val Nuevo valor.
     * @.post Lista de paquetes del segundo programa cambiada.
     */
    void setPaquetes2(List<String> val) {
        this.paquetes2 = val;
    }
    
    /**
     * Devuelve la similitud entre los resultados.
     * @return Similitud.
     */
    public double getSimilitud() {
        return similitud;
    }
    
    /**
     * Devuelve la lista de coincidencias entre estos dos programas.
     * @return Lista de coincidencias.
     */
    public List<Matching> getCoincidencias() {
        return Collections.unmodifiableList(coincidencias);
    }
    
    /**
     * Devuelve el nombre del primer programa.
     * @return Nombre del primer programa.
     */
    public String getNombre1() {
        return nombre1;
    }
    
    /**
     * Devuelve el nombre del segundo programa.
     * @return Nombre del segundo programa.
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * Devuelve el tamaño del primer programa.
     * @return Tamaño del primer programa.
     */
    public int getTama1() {
        return tama1;
    }
    
    /**
     * Devuelve el tamaño del segundo programa.
     * @return Tamaño del segundo programa.
     */
    public int getTama2() {
        return tama2;
    }
    
    /**
     * Devuelve la lista de paquetes del primer programa.
     * @return Lista de paquetes del primer programa.
     */
    public List<String> getPaquetes1() {
        return paquetes1;
    }
    
    /**
     * Devuelve la lista de paquetes del segundo programa.
     * @return Lista de paquetes del segundo programa.
     */
    public List<String> getPaquetes2() {
        return paquetes2;
    }
    
    /**
     * Ordena las coincidencias.
     * @param asc Ascendente?
     * @param imp Considerar importancia de los programas?
     * @.post Coincidencias ordenadas.
     */
    @SuppressWarnings("unchecked")
    public void ordenar(boolean asc,boolean imp) {
        ArrayList<ArrayList> a = new ArrayList<ArrayList>();
        ArrayList<ArrayList> a1;
        ArrayList<ArrayList> a2;
        ArrayList<Matching> b = null;
        ArrayList<Matching> b1 = null;
        ArrayList<Matching> b2 = null;
        Matching c1;
        Matching c2;
        Matching d;
        Matching d1;
        Matching c;
        Comparator<ArrayList> comp;
        if (asc) {
            if (imp)
                comp = Tools.compascimp;
            else
                comp = Tools.compascnimp;
        }
        else {
            if (imp)
                comp = Tools.compdesimp;
            else
                comp = Tools.compdesnimp;
        }
        int i,j,k;
        b = new ArrayList<Matching>();
        for (i = 0;i < coincidencias.size();++i) {
            c = coincidencias.get(i);
            if (c.getProfundidad() == 0 || i+1 == coincidencias.size()) {
                if (i+1 == coincidencias.size())
                    b.add(c);
                if (b != null) {
                if (b.size()>0) {
                    d = b.get(0);
                    a1 = new ArrayList<ArrayList>();
                    b.remove(0);
                    b1 = new ArrayList<Matching>();
                    for (j = 0;j < b.size();++j) {
                        c1 = b.get(j);
                        if (c1.getProfundidad() == 1 || j+1 == b.size()) {
                            if (j+1 == b.size())
                                b1.add(c1);
                            if (b1 != null) {
                            if (b1.size()>0) {
                                d1 = b1.get(0);
                                a2 = new ArrayList<ArrayList>();
                                b1.remove(0);
                                b2 = new ArrayList<Matching>();
                                for (k = 0;k < b1.size();++k) {
                                    c2 = b1.get(k);
                                    if (c2.getProfundidad() == 2 ||
                                            k+1 == b1.size()) {
                                        if (k+1 == b1.size())
                                            b2.add(c2);
                                        if (b2 != null) {
                                        if (b2.size()>0) {
                                            a2.add(b2);
                                            b2 = null;
                                        }
                                        }
                                        b2 = new ArrayList<Matching>();
                                    }
                                    b2.add(c2);
                                    if (k+1 == b1.size())
                                        b2 = null;
                                }
                                Collections.sort(a2,comp);
                                b1.clear();
                                b1.add(0,d1);
                                for (k = 0;k < a2.size();k++) {
                                    b1.addAll(a2.get(k));
                                }
                                a1.add(b1);
                                b1 = null;
                            }
                            }
                            b1 = new ArrayList<Matching>();
                        }
                        b1.add(c1);
                        if (j+1 == b.size())
                            b1 = null;
                    }
                    Collections.sort(a1,comp);
                    b.clear();
                    b.add(0,d);
                    for (j = 0;j < a1.size();j++) {
                        b.addAll(a1.get(j));
                    }
                    a.add(b);
                    b = null;
                }
                b = new ArrayList<Matching>();
            }
            }
            b.add(c);
            if (i+1 == coincidencias.size())
                b = null;
        }
        coincidencias.clear();
        Collections.sort(a,comp);
        for (i = 0;i < a.size();i++) {
            coincidencias.addAll(a.get(i));
        }
    }

}
