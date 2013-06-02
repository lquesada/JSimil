/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Programa a comparar.
 * @author elezeta
 */
final class Program extends MatchableElement implements Serializable,Cloneable
    {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;

    /**
     * Batería de programas a la que pertenece el programa.
     */
    private ProgramBattery bateria;
    
    /**
     * Bloques que contiene el programa.
     */
    private List<CodeBlock> bloques;
    
    /**
     * Métodos que contiene el programa.
     */
    private List<CodeMethod> metodos;
    
    /**
     * Clases que contiene el programa.
     */
    private List<CodeClass> clases;
    
    /**
     * Ficheros que contienen al programa.
     */
    private List<CodeFile> ficheros;
    
    /**
     * Paquetes del programa.
     */
    private List<String> paquetes;
    
    /**
     * Estado frente al procesamiento.
     */
    private boolean estado;
    
    /**
     * Constructor.
     * @param nombre Nombre del programa.
     * @param id Id del programa.
     * @param bateria Batería a la que pertenece el programa.
     * @.post Ha sido inicializado un objeto programa.
     */
    protected Program(String nombre,int id,ProgramBattery bateria) {
        super(nombre,id);
        setNombres();
        bloques = new ArrayList<CodeBlock>();
        metodos = new ArrayList<CodeMethod>();
        clases = new ArrayList<CodeClass>();
        ficheros = new ArrayList<CodeFile>();
        paquetes = new ArrayList<String>();
        this.bateria = bateria;
        estado = true;
    }
     
    /**
     * Encontrar clases en ficheros class y java.
     * @param clasesClass relación clase -> fichero class.
     * @param clasesJava relación clase -> fichero java.
     * @param ruta Ruta a explorar.
     * @param config Configuración.
     * @.post Map clasesClass relleno.
     * @.post Map clasesJava relleno.
     * @return Si había clases o no.
     */
    private boolean cargaListaRecursiva(HashMap<String,String> clasesClass,
                                     HashMap<String,String> clasesJava,
                                     String ruta,Configuration config) {
        boolean encontrado = false;        
        File check = new File(ruta);
        if (!check.exists()) {
            return false;
        }
        if (!check.isDirectory()) {
            return false;
        }
        String contenido[] = check.list();
        if (contenido == null)
            return false;
        int i;
        String filename;
        File f2;
        String linea;
        List<String> cont;
        BufferedReader stdInput;
        List<String> clas;
        String nombreclas;
        File c;
        boolean seguir;
        String nombre;
        String[] envp = {};
        Process p;
        boolean aceptacomp = config.getAceptaComp();      
        for (i = 0;i < contenido.length;++i) {
            filename = ruta+File.separator+contenido[i];
            f2 = new File(filename);
            if (f2.isDirectory()) {
                if (cargaListaRecursiva(clasesClass,clasesJava,filename,config))
                    encontrado = true;
            }
            else if (f2.canRead()) {
                if (contenido[i].endsWith(".java")) {
                    try {
                        stdInput = new BufferedReader(new 
                                     FileReader(f2));
                        cont = new ArrayList<String>();
                        while ((linea = stdInput.readLine()) != null)
                            cont.add(linea);
                        stdInput.close();
                        clas = Tools.listaClases(cont,null,null);
                        int j;
  //DEBUG System.out.println("JAVA! " + filename);
                        if (clas.size() > 0)
                            encontrado = true;
                        for (j = 0;j < clas.size();++j) {
                            nombreclas = clas.get(j);
                            if (!clasesJava.containsKey(nombreclas)) {
                                clasesJava.put(nombreclas,filename);
                                bateria.event(EventType.ENCONTRADA_CLASE_FUENTE,
                                    getNombre()+"."+nombreclas);
                            }
//DEBUG   System.out.println("Contiene " + clas.get(j));
                        }
                        stdInput.close();
                    } catch (IOException e) {
                        bateria.event(
                                EventType.ERROR_LEYENDO_FICHERO_JAVA,getNombre()
                                +"."+filename+" "+e.getMessage());
                    }
                }
                else if (contenido[i].endsWith(".class") && aceptacomp) {
                    c = new File(filename);
                    nombre = c.getName();
                    nombre = nombre.substring(0,nombre.length()-6);
                    seguir = true;
                    try {
                        p = Runtime.getRuntime().exec(
                            config.getRutaDesensamblador() + " " + nombre,
                            envp,
                            new File(ruta));
                        stdInput = new BufferedReader(new 
                                         InputStreamReader(p.getInputStream()));
                        cont = new ArrayList<String>();
                        while ((linea = stdInput.readLine()) != null)
                            cont.add(linea);
                        stdInput.close();
                        clas = Tools.listaClases(cont,null,null);
  //DEBUG System.out.println("CLASS! " + filename);
                        int j;
                        if (clas.size() > 0)
                            encontrado = true;
                        for (j = 0;j < clas.size();++j) {
                            seguir = false;
//DEBUG   System.out.println("Contiene " + clas.get(j));
  //DEBUG System.out.println(clas.get(j)+" class-> "+filename);
                            nombreclas = clas.get(j);
                            if (!clasesClass.containsKey(nombreclas)) {
                                bateria.event(
                                    EventType.ENCONTRADA_CLASE_COMPILADA,
                                    getNombre()+"."+nombreclas);
                                clasesClass.put(nombreclas,filename);
                            }
                        }
                        try {
                            p.waitFor();
                            p.getInputStream().close();
                            p.getOutputStream().close();
                            p.getErrorStream().close();

                        } catch (InterruptedException e) {
                            bateria.event(EventType.INTERRUPCION_DESENSAMBLANDO,
                                    nombre+".class");
                        }
                    } catch (IOException e) {
                        bateria.event(
                       EventType.ERROR_ENTRADA_SALIDA_DESENSAMBLADOR,getNombre()
                                +"."+filename+" "+e.getMessage());
                    }
                    if (seguir) {
                        bateria.event(
                       EventType.ERROR_CLASS_VACIO_O_NO_VALIDO,getNombre()+"."+
                                filename);                        
                    }
                }                
            }
        }
        
        return encontrado;
    }
    
    /**
     * Desensambla recursivamente las clases.
     * @param direc Dirección raiz.
     * @param config Configuración.
     * @.post Clases desensambladas.
     * @exception JSimilException Error desensamblando.
     * @exception JSimilException Abortando por error.
     */
    private void desensambla(String direc,Configuration config)
      throws JSimilException {
        String listado = Tools.getClases(direc,"");
        String listilla = "";
        InputStream stdInput = null;
        FileOutputStream fos = null;
        StringTokenizer st;
        String[] envp = {};
        Process p;
        byte[] buf = new byte[4096];
        try {
            fos = new FileOutputStream(direc+"out.javap");
            st = new StringTokenizer(listado);
            
            while (st.hasMoreTokens()) {
                listilla = st.nextToken()+" ";
                while (st.hasMoreTokens() && listilla.length()<100)
                    listilla += st.nextToken()+" ";
                p = Runtime.getRuntime().exec(
                    config.getRutaDesensamblador() + " -c -l " + listilla,
                    envp,
                    new File(direc));
                stdInput = p.getInputStream();
                int x = 0;
                while ((x = stdInput.read(buf)) != -1)
                    fos.write(buf, 0, x);
                stdInput.close();
                try {
                    p.waitFor();
                    p.getInputStream().close();
                    p.getOutputStream().close();
                    p.getErrorStream().close();
                    if (p.exitValue() != 0) {
                        bateria.event(EventType.ERROR_DESENSAMBLANDO,
                            getNombre());
                        if (config.getDescartaErrores())
                            throw new JSimilException(
                          ExceptionType.ABORTANDO_POR_ERROR,
                          "Abortando por error.");
                    }
                } catch (InterruptedException e) {
                    bateria.event(EventType.INTERRUPCION_DESENSAMBLANDO,
                            getNombre());

                }
            }
            bateria.event(EventType.DESENSAMBLADO,
                getNombre());
            fos.close();
        } catch (IOException e) {
            bateria.event(
           EventType.ERROR_ENTRADA_SALIDA_DESENSAMBLADOR,getNombre()+
                    " "+e.getMessage());
        }
    }
    
    /**
     * Carga ficheros.
     * @param direc Dirección raiz.
     * @param ficheros Lista de ficheros a rellenar.
     * @.post Ficheros cargados.
     */
    private void cargaFicheros(String direc,String ruta,
                               List<CodeFile> ficheros,
                               HashMap<String,String> fichs)  {
        File check = new File(direc+ruta);
        String contenido[] = check.list();
        if (contenido == null)
            return;
        int i;
        String filename;
        String cad;
        CodeFile a;
        for (i = 0;i < contenido.length;++i) {
            filename = direc+ruta+contenido[i];
            File f2 = new File(filename);
            if (f2.isDirectory()) {
                cargaFicheros(direc,ruta+contenido[i]+File.separator,ficheros,
                        fichs);
            }
            else if (f2.canRead()) {
                 if (contenido[i].endsWith(".java") ||
                     contenido[i].endsWith(".javap")) {
                    try {
                        a = new CodeFile();
                        a.setRuta(filename);
                        a.carga();
                        cad = ruta+contenido[i];
                        a.setRuta(cad);
                        if (fichs.containsKey(cad)) {
                            a.setRutaOrig(fichs.get(cad));
                        }
                        ficheros.add(a);
                        bateria.event(EventType.CARGADO,getNombre()+"."+
                                ruta+contenido[i]);
                    } catch (JSimilException e) {
                        if (contenido[i].endsWith(".java"))
                            bateria.event(
                                EventType.ERROR_LEYENDO_FICHERO_JAVA,getNombre()
                                +"."+filename+" "+e.getMessage());
                        if (contenido[i].endsWith(".javap"))
                            bateria.event(
                               EventType.ERROR_LEYENDO_FICHERO_JAVAP,getNombre()
                               +filename+" "+e.getMessage());
                    }
                }                
            }
        }        
    }
     
    /**
     * Procesar coincidencia de dos elementos.
     * @param elemento Elemento del mismo tipo con el que emparejar.
     * @param perfil Perfil con el que emparejar.
     * @return Coincidencia.
     */
    @SuppressWarnings("unchecked")
    ProgramComparison procesa(Program elemento,MatchingProfile perfil) {
        int i,j;
        double sim = 0;
        Program simple;
        Program complejo;
        ProgramComparison res = new ProgramComparison();
        res.setPaquetes1(paquetes);
        res.setPaquetes2(elemento.getPaquetes());
        double simo;
        double simp;
        double sima;
        double simmaxa;
        double tama;
        ArrayList<Matching> resio = new ArrayList<Matching>();
        ArrayList<Matching> resip = new ArrayList<Matching>();
        ArrayList<Matching> resia;
        ArrayList<CodeClass> clasespendientess = new ArrayList<CodeClass>();
        ArrayList<CodeClass> clasesnos = new ArrayList<CodeClass>();
        ArrayList<CodeMethod> metodosnos = new ArrayList<CodeMethod>();
        ArrayList<CodeClass> clasesnoc = new ArrayList<CodeClass>();
        ArrayList<CodeMethod> metodosnoc = new ArrayList<CodeMethod>();
        ArrayList<CodeClass> emparejantec;
        ArrayList<CodeClass> emparejadoc;
        ArrayList<CodeMethod> emparejantem;
        ArrayList<CodeMethod> emparejadom;
        ArrayList<CodeClass> clasespendientesc = new ArrayList<CodeClass>();
        ArrayList<CodeMethod> metodospendientess = new ArrayList<CodeMethod>();
        ArrayList<CodeMethod> metodospendientesc = new ArrayList<CodeMethod>();
        int mejor;
        double mejors;
        int k;
        CodeClass se;
        double optim = perfil.getValorS("ProgMatchOPTIMISM");
        double min,max,err,minadd,limite,aceptablec,aceptablem,emparejarc;
        double emparejarm;
        int ki;
        List<Matching> mejorc;
        boolean asociadas[];
        double inst1,inst2,aux;
        boolean search;
        CodeMethod se2;
        double minmet;
        boolean nombreok;
        boolean multiok;
        boolean[] asociados;
        boolean nombrecok;        
        int classmin = (int)Math.round(
                perfil.getValorS("ClassConsiderOnlyMINIMUMINSTRUCTION")-0.5);
        int methodmin = (int)Math.round(
                perfil.getValorS("MethodConsiderOnlyMINIMUMINSTRUCTION")-0.5);
        double progdif = perfil.getValorS("ProgMatchDIFFERENCE");
        inst1 = this.getNInstrucciones();
        inst2 = elemento.getNInstrucciones();
        if (inst1 < inst2) {
            aux = inst1;
            inst1 = inst2;
            inst2 = aux;
        }
      if (inst2/inst1>=progdif) {        
            
        if (optim >= 0.99)
            optim = 1;
        else if (optim <= 0.01)
            optim = 0;

        boolean soysimple;
        if (getClases().size() > elemento.getClases().size()) {
            complejo = this;
            simple = elemento;
            soysimple = false;
        }
        else {
            complejo = elemento;
            simple = this;
            soysimple = true;
        }
        for (i = 0;i < simple.getClases().size();++i) {
            se = simple.getClases().get(i);
            if (se.getNInstrucciones() >= classmin &&
                    (se.getPorcentajePrograma() >=
                    perfil.getValorS("ProgMatchClassThresholdMINIMUM")) &&
                (se.getPorcentajePrograma() <=
                    perfil.getValorS("ProgMatchClassThresholdMAXIMUM"))) {
                clasespendientess.add(se);
            }
            else {
                for (j = 0;j < se.getMetodos().size();++j) {
                    se2 = se.getMetodos().get(j);
                    if (se2.getNInstrucciones() >= methodmin)
                        metodospendientess.add(se2);
                }
            }
        }
        for (i = 0;i < complejo.getClases().size();++i) {
            se = complejo.getClases().get(i);
            if (se.getNInstrucciones() >= classmin &&
                    (se.getPorcentajePrograma() >=
                    perfil.getValorS("ProgMatchClassThresholdMINIMUM")) &&
                (se.getPorcentajePrograma() <=
                    perfil.getValorS("ProgMatchClassThresholdMAXIMUM")))
                clasespendientesc.add(se);
            else {
                for (j = 0;j < se.getMetodos().size();++j) {
                    se2 = se.getMetodos().get(j);
                    if (se2.getNInstrucciones() >= methodmin)
                        metodospendientesc.add(se2);
                }
            }
        }


        min = perfil.getValorS("ProgMatchMIN");
        max = perfil.getValorS("ProgMatchMAX");
        err = perfil.getValorS("ProgMatchERROR");
        minadd = perfil.getValorS("ProgMatchClassMatchMINIMUM");
        minmet = perfil.getValorS("ProgMatchMethodMatchMINIMUM");
        limite = perfil.getValorS("ProgMatchLIMIT");
        aceptablec = perfil.getValorS("ProgMatchClassACCEPTABLE");
        aceptablem = perfil.getValorS("ProgMatchMethodACCEPTABLE");
        emparejarc = perfil.getValorS("ProgMatchClassDIFFERENCE");
        emparejarm = perfil.getValorS("ProgMatchMethodDIFFERENCE");
        Program emparejante;
        //SPrograma emparejado;
        boolean soyemparejado;        
        boolean classsame = false;
        if (perfil.getValorS("ClassSAMENAMEMATCH") > 0.5) {
            classsame = true;
        } 
        boolean nulas = false;
        if (perfil.getValorS("ReturnNullMatchesIfNULL") > 0.5) {
            nulas = true;
        } 
        boolean methodsame = false;
        if (perfil.getValorS("MethodSAMENAMEMATCH") > 0.5) {
            methodsame = true;
        } 
        boolean methodcsame = false;
        if (perfil.getValorS("MethodOfClassSAMENAMEMATCH") > 0.5) {
            methodcsame = true;
        } 
        boolean multiclass = false;
        if (perfil.getValorS("ClassAllowMULTIMATCH") > 0.5) {
            multiclass = true;
        } 
        boolean multimethod = false;
        if (perfil.getValorS("MethodAllowMULTIMATCH") > 0.5) {
            multimethod = true;
        }         
        if (perfil.getValorS("DifferenceSEARCH") > 0.5) {
            search = true;
        }
        else
            search = false;
        simo = 0;
        simp = 0;
        for (k = 0;k < 2;++k) {
            resia = new ArrayList<Matching>();
            sima = 0;
            simmaxa = 0;

            if (k == 0) {
                emparejante = complejo;
                //emparejado = simple;
                emparejantec = new ArrayList<CodeClass>(clasespendientesc);
                emparejadoc = new ArrayList<CodeClass>(clasespendientess);
                emparejantem = new ArrayList<CodeMethod>(metodospendientesc);
                emparejadom = new ArrayList<CodeMethod>(metodospendientess);
                soyemparejado = soysimple;
            }
            else {
                emparejante = simple;
                //emparejado = complejo;
                emparejantec = new ArrayList<CodeClass>(clasespendientess);
                emparejadoc = new ArrayList<CodeClass>(clasespendientesc);
                emparejantem = new ArrayList<CodeMethod>(metodospendientess);
                emparejadom = new ArrayList<CodeMethod>(metodospendientesc);
                soyemparejado = !soysimple;
            }
            if ((k == 0 && optim < 0.99) || (k == 1 && optim > 0.01)) {
                asociadas = new boolean[emparejadoc.size()];
                for (i = 0;i < emparejadoc.size();++i)
                    asociadas[i] = false;
                if (emparejantec.size()>0 && emparejadoc.size()>0) {
                    Collections.sort(emparejantec,Tools.compporcent);
                    tama = 0;
                    for (j = 0;j < emparejantec.size();++j) {
                        if (tama < limite)
                            tama += emparejantec.get(j).getPorcentajePrograma();
                        else {
                            for (ki = 0;
                                 ki < emparejantec.get(j).getMetodos().size();
                                 ++ki) {
                                se2 = emparejantec.get(j).getMetodos()
                                        .get(ki);
                                if (se2.getNInstrucciones() >= methodmin)
                                    emparejantem.add(se2);
                            }
                            emparejantec.remove(j);
                            --j;
                        }
                    }
                    i = 0;
                    while (
                            //Debe seguir si:
                            //es más pequeño que min Y puede ser más grande que
                            //min.
                            ((sima<=min && sima+(1-simmaxa)>=min)
                            //no es más grande que max Y puede ser más grande
                            //que max
                            || (sima<max && sima+(1-simmaxa)>=max)
                            //el error no es adecuado: 1-simmax<error
                            || (sima>=min && sima<=max && 1-simmaxa>err))
                          && (i<emparejantec.size())) {
                        mejor = -1;
                        mejorc = null;
                        mejors = 0;
                        for (j = 0;j < emparejadoc.size();++j) {
                            inst1 = emparejantec.get(i).getNInstrucciones();
                            inst2 = emparejadoc.get(j).getNInstrucciones();
                            if (inst1 < inst2) {
                                aux = inst1;
                                inst1 = inst2;
                                inst2 = aux;
                            }
                            nombreok = !classsame ||
                                    emparejantec.get(i).getNombre()
                                    .equals(
                                    emparejadoc.get(j).getNombre()
                                    );
                            multiok = multiclass || !asociadas[j];
                            if (inst2/inst1>=emparejarc && nombreok &&
                                !emparejadoc.get(j).getNombreCompleto()
                                .equals(
                                emparejantec.get(i).getNombreCompleto()) &&
                                multiok) {
                                List<Matching> c;
                                if (!soyemparejado)
                                    c =
                                        emparejantec.get(i).procesa(
                                        emparejadoc.get(j), perfil);
                                else
                                    c =
                                        emparejadoc.get(j).procesa(
                                        emparejantec.get(i), perfil);
                                if (search) {
                                    if (1-c.get(0).getSimilitud()>mejors ||
                                           mejor == -1) {
                                        mejorc = c;
                                        mejor = j;
                                        mejors = 1-c.get(0).getSimilitud();
                                    }
                                }
                                else {
                                    if (c.get(0).getSimilitud()>mejors ||
                                           mejor == -1) {
                                        mejorc = c;
                                        mejor = j;
                                        mejors = c.get(0).getSimilitud();
                                    }
                                }
                                if ((search && mejors <= aceptablec) ||
                                    (!search && mejors >= aceptablec))
                                    j = emparejadoc.size();                            
                            }
                        }
                        //if (mejor != -1)
                        //System.out.println("DEBUG"+mejors+" "+minadd+" "+
                        //emparejadoc.get(mejor).getNombreCompleto()+" "+
                        //emparejantec.get(i).getNombreCompleto());
                            /*System.out.println("TDO DEBUG "+emparejantec.get(i).getNombreCompleto());
                            System.out.println("TDO DEBUG "+emparejadoc.get(mejor).getNombreCompleto());
                            System.out.println("TDO DEBUG "+mejor+" "+mejorc+" "+mejors+" "+minadd);
                            System.exit(0);*/
                        if (mejorc != null &&
                            ((!search && mejors>=minadd) ||
                             (search && mejors<=minadd)) && mejor != -1) {
                            simmaxa += 
                                emparejantec.get(i).getPorcentajePrograma();
                            sima += mejors*
                                emparejantec.get(i).getPorcentajePrograma();
                            resia.addAll(mejorc);
                            bateria.event(EventType.EMPAREJADAS_CLASES,
                              emparejantec.get(i).getNombreCompleto()+" "+
                              emparejadoc.get(mejor).getNombreCompleto()+" "+
                              mejors);
                            asociadas[mejor] = true;
                        }
                        else {
                            for (ki = 0;
                                 ki < emparejantec.get(i).getMetodos().size();
                                 ++ki) {
                                se2 = emparejantec.get(i).getMetodos()
                                        .get(ki);
                                if (se2.getNInstrucciones() >= methodmin)
                                    emparejantem.add(se2);
                            }                            
                        }
                        ++i;
                    }
                }
                else {
                    for (i = 0;i < emparejantec.size();++i) {
                        for (ki = 0;
                             ki < emparejantec.get(i).getMetodos().size();
                             ++ki) {
                            se2 = emparejantec.get(i).getMetodos()
                                    .get(ki);
                            if (se2.getNInstrucciones() >= methodmin)
                                emparejantem.add(se2);
                        }
                    }
                }
                for (i = 0;i < emparejadoc.size();++i)
                    if (!asociadas[i]) {
                        for (ki = 0;
                             ki < emparejadoc.get(i).getMetodos().size();
                             ++ki) {
                            se2 = emparejadoc.get(i).getMetodos()
                                    .get(ki);
                            if (se2.getNInstrucciones() >= methodmin)
                                emparejadom.add(se2);
                        }
                    }
                tama = simmaxa;
                for (j = 0;j < emparejantem.size();++j) {
                    if (tama < limite)
                        tama += emparejantem.get(j).getPorcentajePrograma();
                    else {
                        emparejantem.remove(j);
                        --j;
                    }
                }
                asociados = new boolean[emparejadom.size()];
                for (i = 0;i < emparejadom.size();++i)
                    asociados[i] = false;

                i = 0;
                while (
                        //Debe seguir si:
                        //es más pequeño que min Y puede ser más grande que min.
                        ((sima<=min && sima+(1-simmaxa)>=min)
                        //no es más grande que max Y puede ser más grande que
                        //max
                        || (sima<max && sima+(1-simmaxa)>=max)
                        //el error no es adecuado: 1-simmax<error
                        || (sima>=min && sima<=max && 1-simmaxa>err))
                      && (i<emparejantem.size())) {
                    mejor = -1;
                    mejorc = null;
                    mejors = 0;
                    for (j = 0;j < emparejadom.size();++j) {
                            inst1 = emparejantem.get(i).getNInstrucciones();
                            inst2 = emparejadom.get(j).getNInstrucciones();
                            if (inst1 < inst2) {
                                aux = inst1;
                                inst1 = inst2;
                                inst2 = aux;
                            }
                            nombreok = !methodsame ||
                                    emparejantem.get(i).getNombre()
                                    .equals(
                                    emparejadom.get(j).getNombre()
                                    );
                            nombrecok = !methodcsame ||
                                    emparejantem.get(i).getClase()
                                    .getNombre().equals(
                                    emparejadom.get(j).getClase()
                                    .getNombre()
                                    );
                            multiok = multimethod || !asociados[j];
                            if (inst2/inst1>=emparejarm && nombreok &&
                                nombrecok && multiok) {
                                List<Matching> c;
                                if (!soyemparejado)
                                    c =
                                        emparejantem.get(i).procesa(
                                        emparejadom.get(j), perfil);
                                else
                                    c =
                                        emparejadom.get(j).procesa(
                                        emparejantem.get(i), perfil);
                                if (search) {
                                    if (1-c.get(0).getSimilitud()>mejors || mejor == -1) {
                                        mejorc = c;
                                        mejor = j;
                                        mejors = 1-c.get(0).getSimilitud();
                                    }
                                }
                                else {
                                    if (c.get(0).getSimilitud()>mejors || mejor == -1) {
                                        mejorc = c;
                                        mejor = j;
                                        mejors = c.get(0).getSimilitud();
                                    }
                                }
                                if ((search && mejors <= aceptablem) ||
                                    (!search && mejors >= aceptablem))
                                    j = emparejadom.size(); 
                            }
                        }
                    if (mejorc != null && mejor != -1 && 
                            ((!search && mejors >= minmet) ||
                              search && mejors <= minmet)) {
                        if (!emparejadom.get(mejor).getNombreCompleto().equals(
                            emparejantem.get(i).getNombreCompleto())) {
                            simmaxa +=emparejantem.get(i).getPorcentajePrograma();
                            sima += mejors*
                                 emparejantem.get(i).getPorcentajePrograma();
                            resia.addAll(mejorc);
                            bateria.event(EventType.EMPAREJADOS_METODOS,
                              emparejantem.get(i).getNombreCompleto()+" "+
                              emparejadom.get(mejor).getNombreCompleto()+" "+
                              mejors);
                            asociados[mejor] = true;
                        }
                    }
                    else {
                        if (nulas) {
                            if (emparejante == this)
                            resia.add(
                               new Matching(emparejantem.get(i),null,0,0));
                                else
                            resia.add(
                               new Matching(null,emparejantem.get(i),0,0));
                        }
                    }
                    ++i;                
                }
                if (k == 0) {
                    resip = resia;
                    simp = sima;
                }
                else {
                    resio = resia;
                    simo = sima;
                }
            }

        }
        sim = optim*simo+(1-optim)*simp;
        if (optim < 0.99)
            res.coincidencias.addAll(resip);
        if (optim > 0.01)
            res.coincidencias.addAll(resio);
        
      }
        
        bateria.event(EventType.EMPAREJADOS_PROGRAMAS,
            getNombre()+" "+elemento.getNombre()+" "+sim);
        res.setNombre1(getNombre());
        res.setTama1(getNInstrucciones());
        res.setNombre2(elemento.getNombre());
        res.setTama2(elemento.getNInstrucciones());
//        sim = sim*1000000;
//        sim = Math.round(sim);
//        sim = sim/1000000;
//        SCoincidencia c;
/*        if (search) {
            sim = 1-sim;
            for (i = 0;i < res.coincidencias.size();i++) {
                c = res.coincidencias.get(i);
                c.setSimilitud(1-c.getSimilitud());
            }
        }*/
        if (sim > 0.9999)
            sim = 1;        
        res.setSimilitud(sim);
//        double csim;
//        for (i = 0;i < res.coincidencias.size();i++) {
//            c = res.coincidencias.get(i);
//            csim = c.getSimilitud();
//            csim = csim*1000000;
//            csim = Math.round(csim);
//            csim = csim/1000000;
//            c.setSimilitud(csim);         
//        }
        return res;              
    }
    
    /**
     * Cargar programa y subelementos.
     * Hace saltar eventos en errores ignorables.
     * @param config Configuración.
     * @.post Crea los ficheros correspondientes.
     * @.post Crea los objetos fichero correspondientes a partir del programa.
     * @.post Carga esos ficheros a memoria.
     * @.post Crea el resto de objetos correspondientes a partir del programa.
     * @.post Llama al método carga de esos objetos.
     * @exception JSimilException Error copiando contenidos.
     * @exception JSimilException Abortando por error.
     */
    void carga(Configuration config) throws JSimilException {
  
        String tempdir1;
        tempdir1 = System.getProperty("java.io.tmpdir");
        if (!tempdir1.endsWith(File.separator))
            tempdir1 = tempdir1 + File.separator;
        String randdir1;
        Math.random();
        do {
            randdir1 = "JSimil_" + (int)(Math.random()*100000000);
        } while ((new File(tempdir1+randdir1)).exists());
        String direc1;
        direc1 = tempdir1+randdir1;
        try {
            Tools.copyDirectoryJar(
                    new File(bateria.getRuta()+getNombre()),new File(direc1));
            
        } catch (IOException e) {
            Tools.deleteDirectory(new File(direc1));
            throw new JSimilException(ExceptionType.ERROR_COPIANDO_CONTENIDOS,
                    "No se pueden copiar los contenidos de este programa.");
        }
        //Crea una lista con los ficheros class (ojo con los $)
        HashMap<String,String> clasesClass = new HashMap<String,String>();
        HashMap<String,String> clasesClassExtra = new HashMap<String,String>();
        //Crea una lista con los ficheros java y las clases que contienen
        HashMap<String,String> clasesJava = new HashMap<String,String>();
        
        if (!cargaListaRecursiva(clasesClass,clasesJava,
                            direc1,config)) {
            bateria.event(EventType.ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA,
                    getNombre());
            throw new JSimilException(ExceptionType.ABORTANDO_POR_ERROR,
                    "Abortando por error.");
        }

        boolean compilar = config.getCompilar();
        int i,j;
        

//DEBUG       Object camposa[];
//DEBUG                Object camposa2[];
        
        
        //Saca de la lista los class de los que disponemos de los java
        //y los class internos y ficheros repetidos.

//DEBUG                System.out.println("PROG\n\n\n\n\n\n\n\n\n\nPROG");
/*DEBUG
 camposa = clasesClass.keySet().toArray();
        for (i = 0;i < camposa.length;i++) {
 System.out.println("En clase: "+camposa[i]);
 System.out.println("("+clasesClass.get(camposa[i])+")");
 }                camposa2 = clasesJava.keySet().toArray();
 for (i = 0;i < camposa2.length;i++) {
 System.out.println("En java: "+camposa2[i]);
 System.out.println("("+clasesJava.get(camposa2[i])+")");
 }
*/
        Object campos[] = clasesClass.keySet().toArray();
        Object camposx[] = clasesJava.keySet().toArray();

      
        for (i = 0;i < campos.length;++i)
            if (((String)campos[i]).contains("$"))
                clasesClass.remove(campos[i]);
        for (i = 0;i < camposx.length;++i)
            if (((String)camposx[i]).contains("$"))
                clasesJava.remove(camposx[i]);
        
        campos = clasesClass.keySet().toArray();
        camposx = clasesJava.keySet().toArray();
        for (i = 0;i < campos.length;++i)
            for (j = i+1;j < campos.length;++j) {
                if (!campos[i].equals("") && !campos[j].equals("")) {
                  if (clasesClass.get(campos[i]).equals(
                                      clasesClass.get(campos[j]))) {
                      clasesClass.remove(campos[j]);
                      campos[j] = new String("");
                  }
                }
            }
        for (i = 0;i < camposx.length;++i)
            for (j = i+1;j < camposx.length;++j) {
                if (!camposx[i].equals("") && !camposx[j].equals("")) {
                  if (clasesJava.get(camposx[i]).equals(
                                      clasesJava.get(camposx[j]))) {
                      clasesJava.remove(camposx[j]);
                      camposx[j] = new String("");
                  }
                }
            }
        
        campos = clasesClass.keySet().toArray();
        camposx = clasesJava.keySet().toArray();
        for (i = 0;i < camposx.length;++i) {
            for (j = 0;j < campos.length;++j) {
                String nomclass = (String)camposx[i];
                if (nomclass.contains("$")) {
                    nomclass = nomclass.substring(0,nomclass.indexOf("$"));
                }
                if (!camposx[i].equals("")) {
                  if (nomclass.equals((String)campos[j])) {
                      clasesClassExtra.put((String)camposx[i],
                              clasesClass.get(camposx[i]));
                      clasesClass.remove(camposx[i]);                      
                      camposx[i] = new String("");
                  }
                }
            }
        }
        


        //Copia lo que queda (class y javas) a un directorio temporal
        String tempdir;
        tempdir = System.getProperty("java.io.tmpdir");
        if (!tempdir.endsWith(File.separator))
            tempdir = tempdir + File.separator;
        String randdir;
        Math.random();
        do {
            randdir = "JSimil_" + (int)(Math.random()*100000000);
        } while ((new File(tempdir+randdir)).exists());
        String direc;
        direc = tempdir+randdir;
        File f = new File(direc);
        if (!f.mkdir()) {
            bateria.event(EventType.NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL,
                    getNombre()+" "+direc);
            return;
        }
        direc = direc+File.separator;

        if (!compilar) {
            campos = clasesClassExtra.keySet().toArray();
            for (i = 0;i < campos.length;++i) {
                clasesClass.put((String)campos[i],
                        clasesClassExtra.get((String)campos[i]));
            }
        }
        campos = clasesClass.keySet().toArray();
        for (i = 0;i < campos.length;++i) {
            String objetivo = (String)campos[i];
            if (objetivo.contains("/")) {
                File fn = new File(direc+objetivo.substring(0,
                        objetivo.lastIndexOf('/')).replace("/",File.separator));
                if (!fn.exists()) {
                    if (!fn.mkdirs()) {
                        bateria.event(
                            EventType.NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL,
                            getNombre()+" "+direc);
                        Tools.deleteDirectory(new File(direc));
                    }
                    
                }
            }
            try {
                String newname = direc+
                        ((String)campos[i]).replace("/",File.separator);
                newname = newname+".class";
                File tar = new File(newname);
                if (!tar.exists())
                    Tools.copyFile(new File(clasesClass.get(campos[i])),tar);
            } catch (IOException e) {
                bateria.event(EventType.ERROR_COPIANDO_FICHERO_CLASS,getNombre()
                        +"."+clasesClass.get((campos[i]))+" "+e.getMessage());
            }
        }        
        HashMap<String,String> fichs = new HashMap<String,String>();
        String objetivo;
        String objdir;
        File fn;
        File g;
        String newname;
        File tar;
        if (compilar) {
            camposx = clasesJava.keySet().toArray();
            for (i = 0;i < camposx.length;++i) {
                objetivo = (String)camposx[i];
                objdir = new String("");
                if (objetivo.contains("/")) {
                    objdir = objetivo.substring(0,
                            objetivo.lastIndexOf("/"))
                            .replace("/",File.separator)+
                            File.separator;
                    fn = new File(direc+objdir);
                    if (!fn.exists()) {
                        if (!fn.mkdirs()) {
                            bateria.event(
                                EventType.NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL,
                                getNombre()+" "+direc);
                            Tools.deleteDirectory(new File(direc));
                        }
                    }
                }
                try {
                    g = new File(clasesJava.get(camposx[i]));
                    newname = direc+objdir+
                            g.getName();
                    tar = new File(newname);
                    if (!tar.exists()) {
                        Tools.copyFile(g,tar);
                        fichs.put(objdir+
                            g.getName(),clasesJava.get(camposx[i]).substring(
                            (direc1+File.separator).length()));
                    }
                } catch (IOException e) {
                    bateria.event(EventType.ERROR_COPIANDO_FICHERO_JAVA,
                            getNombre()+"."+clasesJava.get(camposx[i])+" "+
                            e.getMessage());
                }

            }        
        }
        //Compila todo el código de ese directorio temporal
        String listado = "";
        if (compilar) {
            for (i = 0;i < camposx.length;++i) {
                objetivo = (String)camposx[i];
                objdir = new String("");
                if (objetivo.contains("/")) {
                    objdir = objetivo.substring(0,
                            objetivo.lastIndexOf("/"))
                            .replace("/",File.separator)+File.separator;
                }
                g = new File(clasesJava.get(camposx[i]));
                newname = direc+objdir+
                        g.getName();
                tar = new File(newname);
                if (tar.exists()) {
                    listado += objdir+g.getName()+" ";
                }
            }   
        }

        BufferedWriter out;
        FileWriter fw;
        try {
            fw = new FileWriter(direc+"listado.jl");
            out = new BufferedWriter(fw);
            out.write(listado);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new JSimilException(ExceptionType.ERROR_ESCRIBIENDO_FICHERO,
                                direc+"listado.jl");
        }        
        
        String[] envp = {"CLASSPATH="+config.getClassPath()};
        StringTokenizer encodings;
        List<String> cont;
        String encoding;
        boolean ok;
        Process p;
        BufferedReader stdInput;
        String linea;
        if (compilar && !listado.equals("")) {
            encodings =
                    new StringTokenizer(config.getEncoding(),",");
            ok = false;
            cont = new ArrayList<String>();
            while (encodings.hasMoreTokens() && !ok) {
                cont = new ArrayList<String>();
                encoding = encodings.nextToken().trim();
                try {
                  try {
                    p = Runtime.getRuntime().exec(
                        config.getRutaCompilador() + " -nowarn -encoding " +
                      encoding+" @listado.jl",envp,new File(direc));
                    stdInput = new BufferedReader(new 
                                     InputStreamReader(p.getErrorStream()));
                    while ((linea = stdInput.readLine()) != null)
                        cont.add(linea);
                    stdInput.close();
                    p.waitFor();
                    p.getInputStream().close();
                    p.getOutputStream().close();
                    p.getErrorStream().close();
                    if (p.exitValue() == 0) {
                        ok = true;
                    }
                    else
                        bateria.event(EventType.ERROR_COMPILANDO,
                            getNombre());
                  } catch (InterruptedException ee) {
                      bateria.event(EventType.INTERRUPCION_COMPILANDO,
                          getNombre()+" "+ee.getMessage());
                  }
                } catch (IOException ee) {
                    bateria.event(EventType.INTERRUPCION_COMPILANDO,
                        getNombre()+" "+ee.getMessage());
                }
            }
            if (!ok) {
                for (j = 0;j < cont.size();j++)
                    bateria.event(EventType.ERROR_COMPILANDO_MSG,
                            cont.get(j));
                if (config.getDescartaErrores()) {
                    throw new JSimilException(ExceptionType.ABORTANDO_POR_ERROR,
                      "");
                }
            }
            else
                bateria.event(EventType.COMPILADO,getNombre());
        }

        bateria.event(EventType.EXPLORADO_APARTADO_Y_COMPILADO,
                getNombre());

        //Lanza el desensamblador para cada class existente.
        desensambla(direc,config);
        
        //Crea los objetos SFichero correspondientes y los carga
        cargaFicheros(direc,"",ficheros,fichs);
    
/*DEBUG
        for (int b = 0;b < ficheros.size();b++) {
            SFichero eje = ficheros.get(b);
            System.out.println("NUEVO NUEVO NUEVO" + eje.getRuta());
            for (int v = 0;v < eje.getDatos().size();v++) 
                System.out.println(eje.getDatos().get(v));
        }
 */
        
        //Elimina los archivos temporales.
        Tools.deleteDirectory(new File(direc));
        Tools.deleteDirectory(new File(direc1));
       
        //Busca en los javap los nombres de las clases.
        CodeFile fich;
        List<String> clasesvalidas = new ArrayList<String>();
        List<String> clas = new ArrayList<String>();
        List<String> packs = new ArrayList<String>();
        String pack;
        int ind;
        for (i = 0;i < ficheros.size();++i) {
            if (ficheros.get(i).getRuta().endsWith(".javap")) {
                fich = ficheros.get(i);
                clas = Tools.listaClases(fich.getDatos(),null,null);
                for (j = 0;j < clas.size();j++) {
                    pack = "";
                    if (clas.get(j).contains("/")) {
                        ind = clas.get(j).lastIndexOf("/");
                        pack = clas.get(j).substring(0,ind);
                    }
                    if (!packs.contains(pack))
                        packs.add(pack);
                }
                clasesvalidas.addAll(clas);
            }
        }    
        
/*DEBUG
        System.out.println("LISTADO DE CLASES DEL PROGRAMA "+getNombre());
        for (i = 0;i < clasesvalidas.size();i++) {
            System.out.println(clasesvalidas.get(i));
        }
 */
        int x;
        
        List<Integer> salida = new ArrayList<Integer>();
        List<String> tiene = new ArrayList<String>();
        CodeFile fich_cod;
        Location cod;
        CodeFile fich_des;
        int ini_cod;
        int ini_des;
        int fin_cod;
        int fin_des;
        int y;
        String clasecon;
        List minmax;
        Location des;
        CodeClass cc;
        //Genera las clases y les asigna los ficheros y lanza la carga.
        for (i = 0;i < clasesvalidas.size();++i) {
            fich_cod = null;
            fich_des = null;
            ini_cod = -1;
            ini_des = -1;
            fin_cod = -1;
            fin_des = -1;
            for (x = 0;x < ficheros.size() && fich_des == null;++x) {
                if (ficheros.get(x).getRuta().endsWith(".javap")) {
                    salida.clear();
                    Tools.listaClases(ficheros.get(x).getDatos(),
                            clasesvalidas.get(i),salida);
                    if (salida.get(0) != -1 && salida.get(1) != -1) {
                        fich_des = ficheros.get(x);
                        ini_des = salida.get(0);
                        fin_des = salida.get(1);
                        /*DEBUG
                        System.out.println("BUSCANDO");
                        System.out.println(ficheros.get(x).getRuta());
                        System.out.println(clasesvalidas.get(i));
                        System.out.println(salida.get(0));
                        System.out.println(salida.get(1));
                        System.out.println("FIN");
                        System.out.println("");
                        System.out.println("");
                        */
                    }
                }
            }
            /*DEBUG
             if (fich_des == null)
                System.out.println("NO ENCONTRADA " +clasesvalidas.get(i));
            */
            if (fich_des != null) {
                for (x = 0;x < ficheros.size() && fich_cod == null;++x) {
//                        System.out.println(ficheros.get(x).getRuta());                    
                    if (ficheros.get(x).getRuta().endsWith(".java")) {
                        salida.clear();
                        clasecon = clasesvalidas.get(i);
                        if (clasecon.contains("$"))
                            clasecon = 
                                 clasecon.substring(0,clasecon.indexOf("$"));
                        tiene = Tools.listaClases(ficheros.get(x).getDatos(),
                                                  null,null);
                        ok = false;
                        for (y = 0;y < tiene.size() && !ok;++y) {     
                            if (tiene.get(y).equals(clasecon)) {
                                fich_cod = ficheros.get(x);  
                                minmax =
                                    Tools.getLines(fich_des.getDatos(),0,
                                                   fich_des.getDatos().size());
                                ini_cod = ((Integer)minmax.get(0)).intValue();
                                fin_cod = ((Integer)minmax.get(1)).intValue();
                            }
                        }
                    }
                }
                if (fich_cod == null) {
                    bateria.event(EventType.FALTA_CODIGO_FUENTE,getNombre()+"."+
                            clasesvalidas.get(i));
                    cod = null;
                }
                else {
                    if (ini_cod != -1 && fin_cod != -1) {
                        cod = new Location(fich_cod);
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
                    else {
                        cod = null;
                    }                        
                }
/*DEBUG
            System.out.println("BUSCANDO");
            System.out.println(clasesvalidas.get(i));
            System.out.println("ENCONTRADA EN");
            if (fich_cod != null && ini_cod != -1 && fin_cod >= 0) {
                System.out.println("JAVA : "+fich_cod.getRuta());
                System.out.println(" ("+ini_cod+"-"+fin_cod+")");
            }
            if (fich_des != null && ini_des != -1 && fin_des >= 0) {
                System.out.println("JAVAP: "+fich_des.getRuta());
                System.out.println(" ("+ini_des+"-"+fin_des+")");
            }
 */
                des = new Location(fich_des);
                if (ini_des < 0)
                    ini_des = 0;
                if (ini_des > fich_des.getDatos().size()-1)
                    ini_des = fich_des.getDatos().size()-1;
                if (fin_des < 0)
                    fin_des = 0;
                if (fin_des > fich_des.getDatos().size()-1)
                    fin_des = fich_des.getDatos().size()-1;
                des.setLineaIni(ini_des);
                des.setLineaFin(fin_des);
                cc = new CodeClass(this,cod,des,clasesvalidas.get(i),i);
                clases.add(cc);  
                cc.carga();
            }
        }
        
        /*DEBUG
        int summ = 0;
        for (x = 0;x < metodos.size();x++) {
          summ += metodos.get(x).getNInstrucciones();
        }
        int sumb = 0;
        for (x = 0;x < bloques.size();x++) {
          sumb += bloques.get(x).getNInstrucciones();
        }
        int sumc = 0;
        for (x = 0;x < metodos.size();x++) {
          for (int y = 0;y < metodos.get(x).getBloques().size();y++) {
              sumc += metodos.get(x).getBloques().get(y).getNInstrucciones();
          }
        }
        System.out.println("INST PROG: "+getNInstrucciones());
        System.out.println("INST METO: "+summ);
        System.out.println("INST BLOQ: "+sumb);
        System.out.println("INST MEBL: "+sumc);
        */
        double porcentaje;
        int x1;
        int x2;
        int x3;

        //double ctotal = 0;
        //double mtotal = 0;
        //double btotal = 0;
        CodeClass cact;
        CodeMethod mact;
        CodeBlock bact;
        for (x1 = 0;x1 < clases.size();++x1) {
            cact = clases.get(x1);
            porcentaje = ((double)cact.getNInstrucciones())/
                          ((double)getNInstrucciones());
            //System.out.println("c TIENE "+cact.getNInstrucciones() +
            //        "/"+getNInstrucciones()+" = "+porcentaje);
            cact.setPorcentajePadre(porcentaje);
            cact.setPorcentajePrograma(porcentaje);
  //          ctotal += porcentaje;
            for (x2 = 0;x2 < cact.getMetodos().size();++x2) {
                mact = cact.getMetodos().get(x2);
                porcentaje = ((double)mact.getNInstrucciones())/
                              ((double)cact.getNInstrucciones());
                //System.out.println("  m TIENE "+mact.getNInstrucciones() +
                //        "/"+cact.getNInstrucciones()+" = "+porcentaje);
                mact.setPorcentajePadre(porcentaje);
                porcentaje = ((double)mact.getNInstrucciones())/
                              ((double)getNInstrucciones());
                //System.out.println("  m(p) TIENE "+mact.getNInstrucciones() +
                //        "/"+getNInstrucciones()+" = "+porcentaje);
                mact.setPorcentajePrograma(porcentaje);
     //           mtotal += porcentaje;
                for (x3 = 0;x3 < mact.getBloques().size();++x3) {
                    bact = mact.getBloques().get(x3);
                    porcentaje = ((double)bact.getNInstrucciones())/
                                  ((double)mact.getNInstrucciones());
                  //System.out.println("    b TIENE "+bact.getNInstrucciones() +
                  //          "/"+mact.getNInstrucciones()+" = "+porcentaje);
                    bact.setPorcentajePadre(porcentaje);
                    porcentaje = ((double)bact.getNInstrucciones())/
                                  ((double)getNInstrucciones());
                //System.out.println("    b(p) TIENE "+bact.getNInstrucciones()+
                //            "/"+getNInstrucciones()+" = "+porcentaje);
                    bact.setPorcentajePrograma(porcentaje);
  //                  btotal += porcentaje;
                }
            }
        }
//System.out.println("BTOTAL " +btotal);
//System.out.println("CTOTAL " +ctotal);
//System.out.println("MTOTAL " +mtotal);
        if (bloques.size() == 0)
            throw new JSimilException(
                  ExceptionType.ABORTANDO_POR_ERROR,
                  "Abortando por error.");
        Collections.sort(packs);
        paquetes = packs;
        bateria.event(EventType.DESENSAMBLADO_Y_CARGADO,getNombre());

    }

    /**
     * Devuelve la lista de bloques del programa.
     * @return Lista de bloques.
     */
    List<CodeBlock> getBloques() {
        return bloques;
    }
    
    /**
     * Devuelve la lista de clases del programa.
     * @return Lista de clases.
     */
    List<CodeClass> getClases() {
        return clases;
    }
    
    /**
     * Devuelve la lista de métodos del programa.
     * @return Lista de métodos.
     */
    List<CodeMethod> getMetodos() {
        return metodos;
    }
    
    /**
     * Devuelve la batería a la que pertenece el programa.
     * @return Batería a la que pertenece el programa.
     */
    ProgramBattery getBateria() {
        return bateria;
    }
    
    /**
     * Cambia la batería a la que pertenece el programa.
     * @param bateria Batería a la que pertenece el programa.
     * @.post Batería cambiada.
     */
    void setBateria(ProgramBattery bateria) {
        this.bateria = bateria;
    }
    
    /**
     * Elemento padre.
     * @return Elemento padre.
     */
    MatchableElement getPadre() {
        return null;
    }
    
    /**
     * Devuelve el estado actual.
     * @return Estado actual.
     */
    boolean getEstado() {
        return estado;
    }

    /**
     * Cambia el estado del programa.
     * @param val Nuevo estado.
     * @.post Estado cambiado.
     */
    void setEstado(boolean val) {
        estado = val;
    }
        
    /**
     * Tipo del elemento.
     * @return "SBloque", "SMetodo", "SClase", "SPrograma".
     */
    public String getTipo() {
        return "SPrograma";
    }
    
    /**
     * Devuelve la lista de paquetes del programa.
     * @return Lista de paquetes.
     */
    List<String> getPaquetes() {
        return paquetes;
    }

}

