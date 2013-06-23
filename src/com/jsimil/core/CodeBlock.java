/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.core;

import java.io.Serializable;
import java.util.List;


/**
 * Bloque básico de código de un programa a comparar.
 * @author elezeta
 */
final class CodeBlock extends CodeElement implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Método que contiene al bloque.
     */
    private CodeMethod metodo;
    
    /**
     * Atributos del elemento.
     */
    double atributos[];
    /*
0 "INSTRUCTIONCOUNT"
1 "CONSTCOUNT"
2 "LDCCOUNT"
3 "LOADCOUNT"
4 "STORECOUNT"
5 "NEWCOUNT"
6 "NEWARRAYCOUNT"
7 "DUPCOUNT"
8 "GETFIELDCOUNT"
9 "PUTFIELDCOUNT"
10 "GETSTATICCOUNT"
11 "RETURNCOUNT"
12 "IFCOUNT"
13 "JSRCOUNT"
14 "RETCOUNT"
15 "GOTOCOUNT"
16 "LOOKUPSWITCHCOUNT"
17 "TABLESWITCHCOUNT"
18 "PUSHCOUNT"
19 "POPCOUNT"
20 "SWAPCOUNT"
21 "INVOKESPECIALCOUNT"
22 "INVOKEVIRTUALCOUNT"
23 "INVOKEINTERFACECOUNT"
24 "INVOKESTATICCOUNT"
25 "CHECKCASTCOUNT"
26 "INCCOUNT"
27 "ADDCOUNT"
28 "SUBCOUNT"
29 "MULCOUNT"
30 "DIVCOUNT"
31 "REMCOUNT"
32 "ORCOUNT"
33 "ANDCOUNT"
34 "XORCOUNT"
35 "SHIFTLEFTCOUNT"
36 "SHIFTRIGHTCOUNT"
37 "NEGCOUNT"
38 "THROWCOUNT"
39 "MONITORENTERCOUNT"
40 "MONITOREXITCOUNT"
41 "METHODSTART"
42 "METHODEND"
     */
    
    /**
     * Si el bloque está o no cargado en memoria
     */
    boolean cargado;
    
    /**
     * Constructor.
     * @param programa Programa que contiene al bloque.
     * @param codigoOriginal Lugar del código original (null si no disponible).
     * @param codigoDesensamblado Lugar del código desensamblado.
     * @param nombre Nombre del bloque.
     * @param id Id del bloque.
     * @param metodo Método que contiene al bloque.
     * @.post Objeto bloque inicializado.
     */
    protected CodeBlock(Program programa,Location codigoOriginal,
                      Location codigoDesensamblado,String nombre,int id,
                      CodeMethod metodo) {
        super(programa,codigoOriginal,codigoDesensamblado,nombre,id);
        this.metodo = metodo;
        setNombres();
        atributos = new double[43];
        cargado = false;
        int i;
        for (i = 0;i < 43;++i)
            atributos[i] = 0.0;        
    }
    
    /**
     * Devuelve el método que contiene al bloque.
     * @return Método que contiene al bloque.
     */
    CodeMethod getMetodo() {
        return metodo;
    }
    
    /**
     * Cargar subelementos.
     * @.post Cuenta el número de instrucciones del bloque.
     */
    void carga() {
        
        /*
         getPrograma().getBateria().event(TipoEvent.DEBUG,getNombreCompleto());
        
        if (getLugarCodigoOriginal() != null) {
            getPrograma().getBateria().event(TipoEvent.DEBUG,"CODIGO:");
            for (int i = getLugarCodigoOriginal().getLineaIni();
                 i <= getLugarCodigoOriginal().getLineaFin();i++)
                getPrograma().getBateria().event(TipoEvent.DEBUG,
                 i+" "+getLugarCodigoOriginal().getFichero().getDatos().get(i));
        }
        if (getLugarCodigoDesensamblado() != null) {
            getPrograma().getBateria().event(TipoEvent.DEBUG,"DESEN:");
            for (int i = getLugarCodigoDesensamblado().getLineaIni();
                 i <= getLugarCodigoDesensamblado().getLineaFin();i++)
                getPrograma().getBateria().event(TipoEvent.DEBUG,i+
            "   "+getLugarCodigoDesensamblado().getFichero().getDatos().get(i));
        }
         */
        
        int ini = getLugarCodigoDesensamblado().getLineaIni();
        int fin = getLugarCodigoDesensamblado().getLineaFin();
        int nin = fin-ini+1;
        setNInstrucciones(nin);
        atributos[0] = (double)nin;
        getMetodo().setNInstrucciones(getMetodo().getNInstrucciones()+nin);
        getMetodo().getClase().setNInstrucciones(
                    getMetodo().getClase().getNInstrucciones()+nin);
        getPrograma().setNInstrucciones(getPrograma().getNInstrucciones()+nin);
        int i;
        List<String> de = getLugarCodigoDesensamblado().getFichero().getDatos();
        String instold = "";
        String inst = "";
        nin = 0;
        for (i = ini;i <= fin;++i) {
            if (!inst.equals("-"))
                instold = inst;            
            inst = Tools.nombreInstruccion(de.get(i));
//DEBUG System.out.println("A " + inst + " old "+instold);

            ++nin;

            if (inst == null) { }
            else if (inst.contains("load"))
                atributos[3] += 1.0;
            else if (inst.contains("const"))
                atributos[1] += 1.0;
            else if (inst.contains("push"))
                atributos[18] += 1.0;
            else if (inst.equals("invokevirtual"))
                atributos[22] += 1.0;
            else if (inst.equals("getfield"))
                atributos[8] += 1.0;
            else if (inst.startsWith("if"))
                atributos[12] += 1.0;
            else if (inst.equals("putfield"))
                atributos[9] += 1.0;
            else if (inst.contains("add"))
                atributos[27] += 1.0;
            else if (inst.startsWith("dup"))
                atributos[7] += 1.0;
            else if (inst.contains("store"))
                atributos[4] += 1.0;
            else if (inst.equals("invokespecial"))
                atributos[21] += 1.0;
            else if (inst.equals("new"))
                atributos[5] += 1.0;
            //else if (inst.contains("tableswitch")) {
            //}
            else if (inst.equals("-")) {
                if (instold.contains("lookupswitch"))
                    atributos[16] += 1.0;
                else if (instold.contains("tableswitch"))
                    atributos[17] += 1.0;
            }
            else if (inst.startsWith("goto"))
                atributos[15] += 1.0;
            else if (inst.contains("return"))
                atributos[11] += 1.0;
            else if (inst.startsWith("ldc"))
                atributos[3] += 1.0;
            else if (inst.contains("sub"))
                atributos[28] += 1.0;
            //else if (inst.contains("lookupswitch")) {
            //}
            else if (inst.contains("mul"))
                atributos[29] += 1.0;
            else if (inst.contains("div"))
                atributos[30] += 1.0;
            else if (inst.equals("invokestatic"))
                atributos[24] += 1.0;
            else if (inst.contains("inc"))
                atributos[26] += 1.0;
            else if (inst.contains("pop"))
                atributos[19] += 1.0;
            else if (inst.contains("rem"))
                atributos[31] += 1.0;
            else if (inst.contains("newarray"))
                atributos[6] += 1.0;
            else if (inst.equals("getstatic"))
                atributos[10] += 1.0;
            else if (inst.startsWith("jsr"))
                atributos[13] += 1.0;
            else if (inst.equals("ret"))
                atributos[14] += 1.0;
            else if (inst.equals("swap"))
                atributos[20] += 1.0;
            else if (inst.equals("invokeinterface"))
                atributos[23] += 1.0;
            else if (inst.equals("checkcast"))
                atributos[25] += 1.0;
            else if (inst.contains("or"))
                atributos[32] += 1.0;
            else if (inst.contains("and"))
                atributos[33] += 1.0;
            else if (inst.contains("xor"))
                atributos[34] += 1.0;
            else if (inst.contains("shl"))
                atributos[35] += 1.0;
            else if (inst.contains("shr"))
                atributos[36] += 1.0;                
            else if (inst.contains("neg"))
                atributos[37] += 1.0;
            else if (inst.contains("throw"))
                atributos[38] += 1.0;
            else if (inst.contains("monitorenter"))
                atributos[39] += 1.0;
            else if (inst.contains("monitorexit"))
                atributos[40] += 1.0;
        }
                
        if (getId() == 0)
            atributos[41] = 1.0;
        
        if (atributos[11] > 0)
            atributos[42] += 1.0;

        /*DEBUG
        Object campos[] = atributos.keySet().toArray();
        for (i = 0;i < campos.length;i++) {
            System.out.println(campos[i] + " " + atributos.get(campos[i]));
            
        }
         */

    }
    
    /**
     * Elemento padre.
     * @return Elemento padre.
     */
    MatchableElement getPadre() {
        return metodo;
    }
    
    /**
     * Procesar coincidencia de dos elementos.
     * @param elemento Elemento del mismo tipo con el que emparejar.
     * @param perfil Perfil con el que emparejar.
     * @return Coincidencia.
     */
    double match(CodeBlock elemento,MatchingProfile perfil) {
        double max = 0,diff = 0,uno,dos,val;
        int i;
        for (i = 43;--i >= 0;) {
        //for (i = 0;i < 43;++i) {
            val = perfil.atributos[i];
            uno = atributos[i]*val;
            dos = elemento.atributos[i]*val;
            if (uno>dos) {
                max += uno;
                diff += (uno-dos);
            }
            else {
                max += dos;
                diff += (dos-uno);
            }
            //System.out.println(campos[i]+" "+(maxact-diffact)+"/"+maxact);
            
        }
        if (max != 0)
            max = (max-diff)/max;
        //System.out.println("EMPAREJADO CON VALOR "+match);
        //ArrayList<SCoincidencia> a = new ArrayList<SCoincidencia>();
        //a.add(new SCoincidencia(this,elemento,max,0));
        //return a;
        return max;
        //System.out.println("BLOQUE!");
    }
    
    /**
     * Tipo del elemento.
     * @return "SBloque", "SMetodo", "SClase", "SPrograma".
     */
    public String getTipo() {
        return "SBloque";
    }
    
}
