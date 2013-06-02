/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Método de un programa a comparar.
 * @author elezeta
 */
final class CodeMethod extends CodeElement implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Bloques que contiene el método.
     */
    private List<CodeBlock> bloques;
    
    /**
     * Clase que contiene al método.
     */
    private CodeClass clase;
    
    /**
     * Constructor.
     * @param programa Programa que contiene al método.
     * @param codigoOriginal Lugar del código original (null si no disponible).
     * @param codigoDesensamblado Lugar del código desensamblado.
     * @param nombre Nombre del método.
     * @param id Id del método.
     * @param clase Clase que contiene al método.
     * @.post Objeto método inicializado.
     */
    protected CodeMethod(Program programa,Location codigoOriginal,
                      Location codigoDesensamblado,String nombre,int id,
                      CodeClass clase) {
        super(programa,codigoOriginal,codigoDesensamblado,nombre,id);
        this.clase = clase;
        setNombres();
        bloques = new ArrayList<CodeBlock>();
    }
       
    /**
     * Devuelve la lista de bloques contenidos en el método.
     * @return Lista de bloques contenidos en el método.
     */
    List<CodeBlock> getBloques() {
        return bloques;
    }
    
    /**
     * Devuelve la clase que contiene al método.
     * @return Clase que contiene al método.
     */
    CodeClass getClase() {
        return clase;
    }
    
    /**
     * Cargar subelementos.
     * @.post Crea los objetos SBloque correspondientes a partir del método.
     * @.post Añade los SBloques al SPrograma.
     */
    void carga() {
        /*z
        System.out.println(getId());
         getPrograma().getBateria().Event(TipoEvent.DEBUG,getNombreCompleto());
        
        if (getLugarCodigoOriginal() != null) {
            getPrograma().getBateria().Event(TipoEvent.DEBUG,"CODIGO:");
            for (int i = getLugarCodigoOriginal().getLineaIni();
                 i <= getLugarCodigoOriginal().getLineaFin();i++)
                getPrograma().getBateria().Event(TipoEvent.DEBUG,
                 "   "+getLugarCodigoOriginal().getFichero().getDatos().get(i));
        }
        if (getLugarCodigoDesensamblado() != null) {
            getPrograma().getBateria().Event(TipoEvent.DEBUG,"DESEN:");
            for (int i = getLugarCodigoDesensamblado().getLineaIni();
                 i <= getLugarCodigoDesensamblado().getLineaFin();i++)
                getPrograma().getBateria().Event(TipoEvent.DEBUG,
            "   "+getLugarCodigoDesensamblado().getFichero().getDatos().get(i));
        }
         
         */
        
        List<String> de = getLugarCodigoDesensamblado().getFichero().getDatos();
        int ini = getLugarCodigoDesensamblado().getLineaIni();
        int fin = getLugarCodigoDesensamblado().getLineaFin();
        int numinst = fin-ini+2;
        Location des;
        Location cod;
        CodeFile fich_cod;
        List<Integer> o;
        CodeBlock b;        
        //Creo un vector para indicar las proposiciones lider.
        boolean plider[] = new boolean[numinst];
        int i;
        for (i = 0;i < numinst;++i)
            plider[i] = false;
        //Primera instrucción del código es proposición lider.
        boolean ok = false;
        for (i = ini;i < fin && !ok;++i) {
            if (de.get(i).endsWith("Code:")) {
                ok = true;
                plider[i-ini+1] = true;
                //System.out.println("LIDER "+de.get(i+1));        
            }
        }
        boolean swit = false;
        ok = false;
        int comienzo = i;
        for (i = comienzo;i < fin && !ok;++i) {
            String linea = de.get(i);
            //System.out.println("INST: "+linea);
            int nlinea = Tools.numeroLinea(linea);
            String nombreinst;
            int obj;
            int objloc;
            if (nlinea == -1)
                ok = true;
            else {
                //Recorro las instrucciones: {
                //  Si es switch, marca flag.
                //  Si deja de ser switch y hay instrucción, marca plider.
                //  Marcar objetivos como plider.
                //  Marcar siguiente a instrucción con objetivo como plider.
                //  Marcar siguiente a instrucción invoke como plider.
                //  Marcar siguiente a instrucción throw como plider.
                //}

                //System.out.println("Linea: "+nlinea);
                nombreinst = Tools.nombreInstruccion(linea);
                if (nombreinst.contains("invoke") ||
                        nombreinst.contains("throw")) {
                    plider[i+1-ini] = true;
                    //System.out.println("LIDER: "+de.get(i+1));
                }                
                if (nombreinst.contains("switch")) {
                    //System.out.println("INI SWITCH.");
                    swit = true; 
                }
                else if (swit && !nombreinst.equals("-")) {
                    //System.out.println("FIN SWITCH.");
                    swit = false;
                    plider[i-ini] = true;
                    //System.out.println("LIDER: "+de.get(i));                    
                }
                //System.out.println("Nombre inst: "+nombreinst);
                obj=Tools.objetivoInstruccion(linea);
                if (obj != -1) {
                    //System.out.println("Objetivo: "+obj);
                    objloc = Tools.buscaInstruccion(de,ini,fin,obj);
                  
                    if (objloc != -1) {
                        plider[objloc-ini] = true;
                        //System.out.println("LIDER(obj): "+de.get(objloc));
                    }
                    if (!nombreinst.equals("-")
                            && Tools.numeroLinea(de.get(i+1)) != -1) {
                        plider[i+1-ini] = true;
                        //System.out.println("LIDER: "+de.get(i+1));
                    }    
                    //System.out.println("Objetivo en: "+objloc);
                    //System.out.println("Objetivo en: "+de.get(objloc));
                }                
            }
        }
        plider[i-1-ini] = true;
        int fine = i;
        int first = -1;
        int last = -1;
        int bid = 0;
        for (i = comienzo;i <= fine;++i) {
            //System.out.println("INST: "+de.get(i));
            if (plider[i-ini]) {
                if (first == -1) {
                    first = i;
                }
                else {
                    last = i-1;

                    //System.out.println("ULTIMA"+de.get(last));
                    des = new Location(getLugarCodigoDesensamblado()
                                            .getFichero());
                    des.setLineaIni(first);
                    des.setLineaFin(last);
                    cod = null;
                    //System.out.println("baaa");
                    o= Tools.buscaOriginal(de,ini,fin,first,last);
                    //System.out.println("baaab "+o.get(0)+" "+o.get(1));
                    if ((o.get(0) != -1) && (o.get(1) != -1)) {
                        if (getLugarCodigoOriginal() != null) {
                            cod = new Location(getLugarCodigoOriginal()
                                            .getFichero());
                                    int ini_cod = o.get(0);
                                    int fin_cod = o.get(1);
                                   
                                    fich_cod = getLugarCodigoOriginal()
                                                    .getFichero();
                                    if (ini_cod < 0)
                                        ini_cod = 0;
                                    if (ini_cod > fich_cod.getDatos().size()-1)
                                        ini_cod = fich_cod.getDatos().size()-1;
                                    if (fin_cod < 0)
                                        fin_cod = 0;
                                    if (fin_cod > fich_cod.getDatos().size()-1)
                                        fin_cod = fich_cod.getDatos().size()-1; 
                            cod.setLineaIni(ini_cod);
                            cod.setLineaFin(fin_cod);
                        }
                    }
                    b = new CodeBlock(getPrograma(),cod,des,
                            (new Integer(bid)).toString(),bid,this);
                    ++bid;
                    getPrograma().getBloques().add(b);
                    getBloques().add(b);
                    b.carga();
                    first = i;
                }
            }
        }
    }
    
    /**
     * Elemento padre.
     * @return Elemento padre.
     */
    MatchableElement getPadre() {
        return clase;
    }
    
    /**
     * Procesar coincidencia de dos elementos.
     * @param elemento Elemento del mismo tipo con el que emparejar.
     * @param perfil Perfil con el que emparejar.
     * @return Coincidencia.
     */
    List<Matching> procesa(CodeMethod elemento,MatchingProfile perfil) {
        CodeMethod simple;
        CodeMethod complejo;
        List<Matching> res = new ArrayList<Matching>();
        List<Matching> reso = new ArrayList<Matching>();
        List<Matching> resp = new ArrayList<Matching>();
        List<Matching> resa;
        double sim = 0;
        double simo = 0;
        double simp = 0;
        double sima = 0;
        //double simmaxa = 0;
        double tama;
        double inst1;
        double inst2;
        double mejor;
        double aux;
        boolean search;
        double m;
        CodeBlock b1;
        CodeBlock b2;
        CodeBlock mejorb1 = null;
        CodeBlock mejorb2 = null;
        int i,j;
        double aceptable;
        double limite;
        double emparejar;
        double optim;
        ArrayList<CodeBlock> bloq;
        int k;
        Matching con,n;
        CodeMethod emparejante;
        CodeMethod emparejado;
        int mejorn;
        double mejorreal;
        double minblock;
        boolean asociados[];
        boolean multiblock = false;
        boolean soyemparejado;        
        boolean multiok;        
        if (perfil.getValorS("BlockAllowMULTIMATCH") > 0.5) {
            multiblock = true;
        }         
        boolean soysimple;
        if (getBloques().size() > elemento.getBloques().size()) {
            complejo = this;
            simple = elemento;
            soysimple = false;
        }
        else {
            complejo = elemento;
            simple = this;
            soysimple = true;
        }

        aceptable = perfil.getValorS("MethodMatchACCEPTABLE");
        limite = perfil.getValorS("MethodMatchLIMIT");
        emparejar = perfil.getValorS("MethodMatchDIFFERENCE");
        optim = perfil.getValorS("MethodMatchOPTIMISM");
        minblock = perfil.getValorS("MethodMatchBlockMatchMINIMUM");        
        int blockmin = (int)Math.round(
                perfil.getValorS("BlockConsiderOnlyMINIMUMINSTRUCTION")-0.5);
        boolean nulas = false;
        if (perfil.getValorS("ReturnNullMatchesIfNULL") > 0.5) {
            nulas = true;
        } 
        if (perfil.getValorS("DifferenceSEARCH") > 0.5) {
            search = true;
        }
        else
            search = false;
        if (optim >= 0.99)
            optim = 1;
        else if (optim <= 0.01)
            optim = 0;
 
        for (k = 0;k < 2;++k) {
            resa = new ArrayList<Matching>();
            sima = 0;
            //simmaxa = 0;

            if (k == 0) {
                emparejante = complejo;
                emparejado = simple;
                soyemparejado = soysimple;
            }
            else {
                emparejante = simple;
                emparejado = complejo;
                soyemparejado = !soysimple;
            }
            if ((k == 0 && optim < 0.99) || (k == 1 && optim > 0.01)) {
                bloq = new ArrayList<CodeBlock>(emparejante.getBloques());
                Collections.sort(bloq,Tools.compporcent);
                tama = 0;
                for (j = 0;j < bloq.size();++j) {
                    if (tama < limite)
                        tama += bloq.get(j).getPorcentajePadre();
                    else {
                        bloq.remove(j);
                        --j;
                    }
                }
                asociados = new boolean[emparejado.getBloques().size()];
                for (i = 0;i < emparejado.getBloques().size();++i)
                    asociados[i] = false;
                for (i = 0;i < bloq.size();++i) {
                    mejor = -1;
                    mejorn = -1;
                    mejorreal = -1;
                    for (j = 0;j < emparejado.getBloques().size();++j) {
                        inst1 = bloq.get(i).getNInstrucciones();
                        inst2 = emparejado.getBloques().get(j)
                                .getNInstrucciones();
                        if (inst1 < inst2) {
                            aux = inst1;
                            inst1 = inst2;
                            inst2 = aux;
                        }
                        multiok = multiblock || !asociados[j];
                        if ((inst1 >= blockmin && inst2 >= blockmin) &&
                           (inst2/inst1>=emparejar) && multiok &&
                           (!bloq.get(i).getNombreCompleto().equals(
                            emparejado.getBloques().get(j)
                            .getNombreCompleto()))) {
                            if (!soyemparejado) {
                                m = bloq.get(i).match(
                                         emparejado.getBloques().get(j),perfil);
                                b1 = bloq.get(i);
                                b2 = emparejado.getBloques().get(j);
                            }
                            else {
                                m = emparejado.getBloques().get(j).match(
                                        bloq.get(i),perfil);
                                b1 = emparejado.getBloques().get(j);
                                b2 = bloq.get(i);
                            }
                            if (search) {
                                if (1-m >= mejor) {
                                    mejorreal = m;
                                    mejor = 1-m;
                                    mejorb1 = b1;
                                    mejorb2 = b2;
                                    mejorn = j;
                                }
                            }
                            else {
                                if (m >= mejor) {
                                    mejorreal = m;
                                    mejor = m;
                                    mejorb1 = b1;
                                    mejorb2 = b2;
                                    mejorn = j;
                                }
                            }
                            if ((!search && mejor >= aceptable) ||
                                (search && mejor <= aceptable))
                                j = emparejado.getBloques().size();
                        }                    
                    }
                    if (mejor > -1 && ((!search && mejor >= minblock) ||
                       (search && mejor <= minblock))) {
                        sima += mejorreal*bloq.get(i).getPorcentajePadre();
                        //simmaxa += bloq.get(i).getPorcentajePadre();
                        resa.add(new Matching(mejorb1,mejorb2,mejor,0));
                        asociados[mejorn] = true;                        
                    }                
                    else {
                        if (nulas) {
                            if (!soyemparejado)
                            resa.add(
                               new Matching(bloq.get(i),null,0,0));
                                else
                            resa.add(
                               new Matching(null,bloq.get(i),0,0));
                        }
                    }

                }
                //if (sima != 0.0 && simmaxa != 0.0)
                //    sima = sima/simmaxa;  
                if (k == 0) {
                    resp = resa;
                    simp = sima;
                }
                else {
                    reso = resa;
                    simo = sima;
                }
            }
        }
        
        sim = optim*simo+(1-optim)*simp;
        if (sim > 0.9999)
            sim = 1;
        con = new Matching(this,elemento,sim,0);
        res.add(con);

        if (optim < 0.99)
            for (i = 0;i < resp.size();++i) {
                n = resp.get(i);
                n.setProfundidad(n.getProfundidad()+1);
                res.add(n);
            }
        if (optim > 0.01)
            for (i = 0;i < reso.size();++i) {
                n = reso.get(i);
                n.setProfundidad(n.getProfundidad()+1);
                res.add(n);
            }

        return res;
    }
    
    /**
     * Tipo del elemento.
     * @return "SBloque", "SMetodo", "SClase", "SPrograma".
     */
    public String getTipo() {
        return "SMetodo";
    }    
    
}
