/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */
package com.jsimil.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Clase de un programa a comparar.
 * @author elezeta
 */
final class CodeClass extends CodeElement implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Métodos contenidos en la clase.
     */
    private List<CodeMethod> metodos;
    
    /**
     * Constructor.
     * @param programa Programa que contiene a la clase.
     * @param codigoOriginal Lugar del código original (null si no disponible).
     * @param codigoDesensamblado Lugar del código desensamblado.
     * @param nombre Nombre de la clase.
     * @param id Id de la clase.
     * @.post Objeto clase inicializado.
     */
    protected CodeClass(Program programa,Location codigoOriginal,
                      Location codigoDesensamblado,String nombre,int id) {
        super(programa,codigoOriginal,codigoDesensamblado,nombre,id);
        setNombres();
        metodos = new ArrayList<CodeMethod>();
    }

    /**
     * Devuelve los métodos contenidos.
     * @return Vector de métodos contenidos.
     */
    List<CodeMethod> getMetodos() {
        return metodos;
    }
    
    /**
     * Cargar subelementos.
     * @.post Crea los objetos SMetodo correspondientes a partir de la clase.
     * @.post Llama a los métodos carga de estos subelementos.
     * @.post Añade los métodos encontrados al programa.
     */
    void carga() {
        
        /*
        getPrograma().getBateria().Event(TipoEvent.DEBUG,getNombre());
        
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
        int i;
        Location des = getLugarCodigoDesensamblado();
        List<String> datos = des.getFichero().getDatos();
        String linea;
        boolean comments;
        int nivel = 0;
        String metodo;
        int mid = 0;
        int ini;
        int fin;
        boolean ok;
        boolean wait;
        StringTokenizer st;
        String ss;
        StringTokenizer st2;
        String ss2;
        CodeFile fich_des;
        Location sdes;
        List minmax;
        int ini_cod;
        int fin_cod;
        Location scod;
        CodeMethod a;
        for (i = des.getLineaIni();i <= des.getLineaFin();++i) {
            linea = datos.get(i);
            comments = false;
            linea = linea.replace("/*"," /* ");
            linea = linea.replace("*/"," */ ");
            linea = linea.replace("//"," // ");
            linea = linea.replace("\\\""," potato ");
            linea = linea.replace("\""," \" ");            
            linea = linea.replace("{"," { ");
            linea = linea.replace("}"," } ");
            st = new StringTokenizer(linea," \n\t\r;");
            while (st.hasMoreTokens()) {
                ss = st.nextToken();

                //Si lee / *, se para hasta * / (aunque sea en otra linea).
                if (ss.equals("/*")) 
                    comments = true;
                if (ss.equals("*/"))
                    comments = false;
                if (!comments) {
                    
                    //Si lee //, se para hasta fin de linea.
                    if (ss.equals("//")) {
                        while (st.hasMoreTokens())
                           ss = st.nextToken();
                        ss = new String("");
                    } 

                    //Si lee comilla, se para hasta leer otra comilla o fin lin.
                    if (ss.equals("\"")) {                    
                        if (st.hasMoreTokens()) {
                            do {
                                ss = st.nextToken();
                            } while (!ss.equals("\"") && st.hasMoreTokens());
                        }
                        if (!st.hasMoreTokens())
                            ss = new String("");
                    }
                    if (ss.equals("{"))
                        ++nivel;
                    if (ss.equals("}"))
                        --nivel;
                    if ((nivel == 1) && (!st.hasMoreTokens())) {
                        if (!ss.equals("") && datos.get(i).endsWith(";")) {
                            metodo = Tools.nombreMetodo(datos.get(i));
                            if (!metodo.equals("")) {                                
                                ini = i;
                                fin = -1;
                                ok = false;
                                wait = false;
                                for (fin = i+1;fin < datos.size() && !ok;++fin){
                                    st2 =
                                        new StringTokenizer(datos.get(fin),
                                                            " \n\t\r;");
                                    ss2 = "";
                                    if (st2.hasMoreTokens())
                                        ss2 = st2.nextToken();
                                    if (!wait) {
                                        if (ss2.equals("line"))
                                            wait = true;
                                    }
                                    else {
                                        if (!ss2.equals("line")) {
                                            ok = true;
                                            fin -= 1;
                                        }
                                    }
                                }
                                
                                if (ok) {
                                    CodeFile fich_cod;
                                    fich_des = des.getFichero();
                                    sdes = new Location(fich_des);
                                    sdes.setLineaIni(ini);
                                    sdes.setLineaFin(fin);
                                    minmax = 
                                        Tools.getLines(fich_des.getDatos(),ini,
                                        fin);
                                    ini_cod = ((Integer)minmax.get(0))
                                                  .intValue();
                                    fin_cod = ((Integer)minmax.get(1))
                                                  .intValue();
                                    scod = null;
                                    if (ini_cod != -1 && fin_cod != -1) {
                                        if (getLugarCodigoOriginal() != null) {
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
                                            scod = new Location(fich_cod);
                                            scod.setLineaIni(ini_cod);
                                            scod.setLineaFin(fin_cod);
                                        }
                                    }
                                    a = new CodeMethod(getPrograma(),scod,
                                            sdes, metodo, mid, this);
                                    getPrograma().getMetodos().add(a);
                                    getMetodos().add(a);
                                    a.carga();
                                    ++mid;
                                }
                            }
                        }
                    }
                }
            }
        }

        //Encontrar los métodos que contiene.
        //Para cada método: {
        //  Obtener el nombre con parámetros del método.
        //  Crear los lugares en cuestión.
        //  Generar la ID.
        //  Delimitar el código desensamblado incluyendo excepciones y lineas.
        //  Obtener del código desensamblado las lineas del código original.
        //  Crear un SMetodo.
        //  Lanzar carga del SMetodo.
        //}
    }
    
    /**
     * Elemento padre.
     * @return Elemento padre.
     */
    MatchableElement getPadre() {
        return getPrograma();
    }
    
    /**
     * Procesar coincidencia de dos elementos.
     * @param elemento Elemento del mismo tipo con el que emparejar.
     * @param perfil Perfil con el que emparejar.
     * @return Coincidencia.
     */
    List<Matching> procesa(CodeClass elemento,MatchingProfile perfil) {
        CodeClass simple;
        CodeClass complejo;
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
        int mejorn;
        double aux;
        List<Matching> m;
        List<Matching> mejorm;
        int i,j;
        double aceptable;
        double limite;
        double emparejar;
        double optim;
        ArrayList<CodeMethod> bloq;
        int k;
        Matching con,n;
        CodeClass emparejante;
        CodeClass emparejado;
        double minmet;
        boolean nombreok;
        boolean multiok;
        
        boolean search;
        boolean soysimple;
        if (getMetodos().size() > elemento.getMetodos().size()) {
            complejo = this;
            simple = elemento;
            soysimple = false;
        }
        else {
            complejo = elemento;
            simple = this;
            soysimple = true;
        }
        boolean soyemparejado;

        boolean asociados[];
        aceptable = perfil.getValorS("ClassMatchACCEPTABLE");
        limite = perfil.getValorS("ClassMatchLIMIT");
        emparejar = perfil.getValorS("ClassMatchDIFFERENCE");
        optim = perfil.getValorS("ClassMatchOPTIMISM");
        minmet = perfil.getValorS("ClassMatchMethodMatchMINIMUM");
        boolean multimethod = false;
        if (perfil.getValorS("MethodAllowMULTIMATCH") > 0.5) {
            multimethod = true;
        }         
        boolean methodsame = false;
        if (perfil.getValorS("MethodSAMENAMEMATCH") > 0.5) {
            methodsame = true;
        } 
        boolean nulas = false;
        if (perfil.getValorS("ReturnNullMatchesIfNULL") > 0.5) {
            nulas = true;
        } 
        int methodmin = (int)Math.round(
                perfil.getValorS("MethodConsiderOnlyMINIMUMINSTRUCTION")-0.5);
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
                bloq = new ArrayList<CodeMethod>(emparejante.getMetodos());
                Collections.sort(bloq,Tools.compporcent);
                tama = 0;
                mejorm = new ArrayList<Matching>();
                for (j = 0;j < bloq.size();++j) {
                    if (tama < limite)
                        tama += bloq.get(j).getPorcentajePadre();
                    else {
                        bloq.remove(j);
                        --j;
                    }
                }
                asociados = new boolean[emparejado.getMetodos().size()];
                for (i = 0;i < emparejado.getMetodos().size();++i)
                    asociados[i] = false;
                for (i = 0;i < bloq.size();++i) {
                    mejor = -1;
                    mejorn = -1;
                    for (j = 0;j < emparejado.getMetodos().size();++j) {
                        inst1 = bloq.get(i).getNInstrucciones();
                        inst2 = emparejado.getMetodos().get(j)
                                .getNInstrucciones();
                        if (inst1 < inst2) {
                            aux = inst1;
                            inst1 = inst2;
                            inst2 = aux;
                        }
                        nombreok = !methodsame ||
                                bloq.get(i).getNombre()
                                .equals(
                                emparejado.getMetodos().get(j)
                                .getNombre());
                        multiok = multimethod || !asociados[j];
                        if ((inst1 >= methodmin && inst2 >= methodmin) &&
                           (inst2/inst1>=emparejar) && nombreok &&
                         !bloq.get(i).getNombreCompleto().equals(
                            emparejado.getMetodos().get(j)
                            .getNombreCompleto()) && multiok) {

                            if (!soyemparejado)
                                m = bloq.get(i).procesa(
                                             emparejado.getMetodos().get(j),
                                             perfil);
                            else
                                m = emparejado.getMetodos().get(j).procesa(
                                        bloq.get(i),perfil);
                            if (search) {
                                if (1-m.get(0).getSimilitud() >= mejor) {
                                    mejor = 1-m.get(0).getSimilitud();
                                    mejorm = m;
                                    mejorn = j;
                                }
                            }
                            else {
                                if (m.get(0).getSimilitud() >= mejor) {
                                    mejor = m.get(0).getSimilitud();
                                    mejorm = m;
                                    mejorn = j;
                                }
                            }
                            if ((search && mejor <= aceptable) ||
                                (!search && mejor >= aceptable))
                                j = emparejado.getMetodos().size();
                        }                    
                    }
                    if (mejor > -1 && ((!search && mejor >= minmet) ||
                            (search && mejor <= minmet))) {
                        sima += mejor*bloq.get(i).getPorcentajePadre();
                        //simmaxa += bloq.get(i).getPorcentajePadre();
                        resa.addAll(mejorm);
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
        return "SClase";
    }
    
}
