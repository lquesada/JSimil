/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Perfil de comparación, contiene atributos que definen la comparación.
 * @author elezeta
 */
final public class MatchingProfile implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Valores de configuración del perfil.
     */
    HashMap<String,Double> valores;

    /**
     * Valores de bloques del perfil.
     */
    transient double atributos[];
    
    /**
     * Escuchador de eventos.
     */
    transient private JSimilEventListener listener;
    
    /**
     * Constructor del perfil.
     * Inicializa los valores de configuración a los válidos.
     * @.post Perfil inicializado.
     */
    public MatchingProfile() {
        atributos = new double[43];
        valores = new HashMap<String,Double>();
        valores.put("ReflexiveMATCH",0.0);
        valores.put("DifferenceSEARCH",0.0);
        valores.put("ReturnNullMatchesIfNULL",0.0);

        valores.put("ProgMatchMIN",0.0);
        valores.put("ProgMatchMAX",0.0);
        valores.put("ProgMatchERROR",0.0);
        valores.put("ProgMatchClassThresholdMINIMUM",0.0);
        valores.put("ProgMatchClassThresholdMAXIMUM",0.0);
        valores.put("ProgMatchClassMatchMINIMUM",0.0);
        valores.put("ProgMatchLIMIT",0.0);
        valores.put("ProgMatchClassACCEPTABLE",0.0);
        valores.put("ProgMatchClassDIFFERENCE",0.0);
        valores.put("ProgMatchMethodACCEPTABLE",0.0);
        valores.put("ProgMatchMethodDIFFERENCE",0.0);
        valores.put("ProgMatchDIFFERENCE",0.0);
        valores.put("ProgMatchMethodMatchMINIMUM",0.0);

        valores.put("ProgMatchOPTIMISM",0.0);
        valores.put("ClassMatchACCEPTABLE",0.0);
        valores.put("ClassMatchLIMIT",0.0);
        valores.put("ClassMatchDIFFERENCE",0.0);
        valores.put("ClassMatchOPTIMISM",0.0);
        valores.put("ClassMatchMethodMatchMINIMUM",0.0);
        valores.put("ClassAllowMULTIMATCH",0.0);
        valores.put("ClassConsiderOnlyMINIMUMINSTRUCTION",0.0);
        valores.put("ClassSAMENAMEMATCH",0.0);
        valores.put("MethodMatchACCEPTABLE",0.0);
        valores.put("MethodMatchLIMIT",0.0);
        valores.put("MethodMatchDIFFERENCE",0.0);
        valores.put("MethodMatchOPTIMISM",0.0);
        valores.put("MethodMatchBlockMatchMINIMUM",0.0);
        valores.put("MethodAllowMULTIMATCH",0.0);
        valores.put("MethodConsiderOnlyMINIMUMINSTRUCTION",0.0);
        valores.put("MethodSAMENAMEMATCH",0.0);
        valores.put("MethodOfClassSAMENAMEMATCH",0.0);
        
        valores.put("BlockAllowMULTIMATCH",0.0);
        valores.put("BlockConsiderOnlyMINIMUMINSTRUCTION",0.0);
        valores.put("BlockWeightINSTRUCTIONCOUNT",0.0);
        valores.put("BlockWeightCONSTCOUNT",0.0);
        valores.put("BlockWeightLDCCOUNT",0.0);
        valores.put("BlockWeightLOADCOUNT",0.0);
        valores.put("BlockWeightSTORECOUNT",0.0);
        valores.put("BlockWeightNEWCOUNT",0.0);
        valores.put("BlockWeightNEWARRAYCOUNT",0.0);
        valores.put("BlockWeightDUPCOUNT",0.0);
        valores.put("BlockWeightGETFIELDCOUNT",0.0);
        valores.put("BlockWeightPUTFIELDCOUNT",0.0);
        valores.put("BlockWeightGETSTATICCOUNT",0.0);
        valores.put("BlockWeightRETURNCOUNT",0.0);
        valores.put("BlockWeightIFCOUNT",0.0);
        valores.put("BlockWeightJSRCOUNT",0.0);
        valores.put("BlockWeightRETCOUNT",0.0);
        valores.put("BlockWeightGOTOCOUNT",0.0);
        valores.put("BlockWeightLOOKUPSWITCHCOUNT",0.0);
        valores.put("BlockWeightTABLESWITCHCOUNT",0.0);
        valores.put("BlockWeightPUSHCOUNT",0.0);
        valores.put("BlockWeightPOPCOUNT",0.0);
        valores.put("BlockWeightSWAPCOUNT",0.0);
        valores.put("BlockWeightINVOKESPECIALCOUNT",0.0);
        valores.put("BlockWeightINVOKEVIRTUALCOUNT",0.0);
        valores.put("BlockWeightINVOKEINTERFACECOUNT",0.0);
        valores.put("BlockWeightINVOKESTATICCOUNT",0.0);
        valores.put("BlockWeightCHECKCASTCOUNT",0.0);
        valores.put("BlockWeightINCCOUNT",0.0);
        valores.put("BlockWeightADDCOUNT",0.0);
        valores.put("BlockWeightSUBCOUNT",0.0);
        valores.put("BlockWeightMULCOUNT",0.0);
        valores.put("BlockWeightDIVCOUNT",0.0);
        valores.put("BlockWeightREMCOUNT",0.0);
        valores.put("BlockWeightORCOUNT",0.0);
        valores.put("BlockWeightANDCOUNT",0.0);
        valores.put("BlockWeightXORCOUNT",0.0);
        valores.put("BlockWeightSHIFTLEFTCOUNT",0.0);
        valores.put("BlockWeightSHIFTRIGHTCOUNT",0.0);
        valores.put("BlockWeightNEGCOUNT",0.0);
        valores.put("BlockWeightTHROWCOUNT",0.0);
        valores.put("BlockWeightMONITORENTERCOUNT",0.0);
        valores.put("BlockWeightMONITOREXITCOUNT",0.0);
        valores.put("BlockWeightMETHODSTART",0.0);
        valores.put("BlockWeightMETHODEND",0.0);
        listener = null;
    }
    
    /**
     * Devuelve el valor de un atributo de configuración (sin excepción).
     * @param key Clave del atributo a devolver.
     * @return Valor del atributo.
     */    
    double getValorS(String key) {
        return (valores.get(key)).doubleValue();
    }

    /**
     * Cargar atributos.
     * @.post Carga los atributos del bloque.
     */
    void cargaat() {
        atributos[0] = valores.get("BlockWeightINSTRUCTIONCOUNT").doubleValue();
        atributos[1] = valores.get("BlockWeightCONSTCOUNT").doubleValue();
        atributos[2] = valores.get("BlockWeightLDCCOUNT").doubleValue();
        atributos[3] = valores.get("BlockWeightLOADCOUNT").doubleValue();
        atributos[4] = valores.get("BlockWeightSTORECOUNT").doubleValue();
        atributos[5] = valores.get("BlockWeightNEWCOUNT").doubleValue();
        atributos[6] = valores.get("BlockWeightNEWARRAYCOUNT").doubleValue();
        atributos[7] = valores.get("BlockWeightDUPCOUNT").doubleValue();
        atributos[8] = valores.get("BlockWeightGETFIELDCOUNT").doubleValue();
        atributos[9] = valores.get("BlockWeightPUTFIELDCOUNT").doubleValue();
        atributos[10] = valores.get("BlockWeightGETSTATICCOUNT").doubleValue();
        atributos[11] = valores.get("BlockWeightRETURNCOUNT").doubleValue();
        atributos[12] = valores.get("BlockWeightIFCOUNT").doubleValue();
        atributos[13] = valores.get("BlockWeightJSRCOUNT").doubleValue();
        atributos[14] = valores.get("BlockWeightRETCOUNT").doubleValue();
        atributos[15] = valores.get("BlockWeightGOTOCOUNT").doubleValue();
        atributos[16] = valores.get("BlockWeightLOOKUPSWITCHCOUNT")
                .doubleValue();
        atributos[17] = valores.get("BlockWeightTABLESWITCHCOUNT")
                .doubleValue();
        atributos[18] = valores.get("BlockWeightPUSHCOUNT").doubleValue();
        atributos[19] = valores.get("BlockWeightPOPCOUNT").doubleValue();
        atributos[20] = valores.get("BlockWeightSWAPCOUNT").doubleValue();
        atributos[21] = valores.get("BlockWeightINVOKESPECIALCOUNT")
                .doubleValue();
        atributos[22] = valores.get("BlockWeightINVOKEVIRTUALCOUNT")
                .doubleValue();
        atributos[23] = valores.get("BlockWeightINVOKEINTERFACECOUNT")
                .doubleValue();
        atributos[24] = valores.get("BlockWeightINVOKESTATICCOUNT")
                .doubleValue();
        atributos[25] = valores.get("BlockWeightCHECKCASTCOUNT").doubleValue();
        atributos[26] = valores.get("BlockWeightINCCOUNT").doubleValue();
        atributos[27] = valores.get("BlockWeightADDCOUNT").doubleValue();
        atributos[28] = valores.get("BlockWeightSUBCOUNT").doubleValue();
        atributos[29] = valores.get("BlockWeightMULCOUNT").doubleValue();
        atributos[30] = valores.get("BlockWeightDIVCOUNT").doubleValue();
        atributos[31] = valores.get("BlockWeightREMCOUNT").doubleValue();
        atributos[32] = valores.get("BlockWeightORCOUNT").doubleValue();
        atributos[33] = valores.get("BlockWeightANDCOUNT").doubleValue();
        atributos[34] = valores.get("BlockWeightXORCOUNT").doubleValue();
        atributos[35] = valores.get("BlockWeightSHIFTLEFTCOUNT").doubleValue();
        atributos[36] = valores.get("BlockWeightSHIFTRIGHTCOUNT").doubleValue();
        atributos[37] = valores.get("BlockWeightNEGCOUNT").doubleValue();
        atributos[38] = valores.get("BlockWeightTHROWCOUNT").doubleValue();
        atributos[39] = valores.get("BlockWeightMONITORENTERCOUNT")
                .doubleValue();
        atributos[40] = valores.get("BlockWeightMONITOREXITCOUNT")
                .doubleValue();
        atributos[41] = valores.get("BlockWeightMETHODSTART").doubleValue();
        atributos[42] = valores.get("BlockWeightMETHODEND").doubleValue();
   }   
    
    /**
     * Devuelve el valor de un atributo de configuración.
     * @param key Clave del atributo a devolver.
     * @return Valor del atributo.
     * @exception JSimilException El atributo indicado no existe.
     */    
    public double getValor(String key) throws JSimilException {
        if (valores.containsKey(key))
            return (valores.get(key)).doubleValue();
        else
            throw new JSimilException(ExceptionType.ATRIBUTO_INEXISTENTE,key);
    }
    
    /**
     * Cambia el valor de un parámetro del perfil.
     * El atributo ya debía existir y debe ser reemplazado.
     * @param key Clave del atributo a reemplazar.
     * @param val Nuevo valor para el atributo.
     * @.post El valor ha sido reemplazado.
     * @exception JSimilException El atributo indicado no existe.
     */
    public void setValor(String key,double val) throws JSimilException {
        if (valores.containsKey(key))
            valores.put(key,new Double(val));
        else
            throw new JSimilException(ExceptionType.ATRIBUTO_INEXISTENTE,key);
    }
    
    /**
     * Validar la batería de programas.
     * @exception JSimilException Alguno de los parámetros esperaba 0 o 1.
     * @exception JSimilException Alguno de los parámetros esperaba entre 0 y 1.
     * @exception JSimilException Alguno de los parámetros esperaba > 0.
     * @exception JSimilException Alguno de los parámetros esperaba >= 0.
     * @exception JSimilException Alguno de los parámetros esperaba < 0.
     * @exception JSimilException Alguno de los parámetros esperaba <= 0.
     * @exception JSimilException Alguno de los parámetros tiene un valor no valido.
     */
    public void validate() throws JSimilException {
        double a,b;
        boolean err = false;
        a = valores.get("DifferenceSEARCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "DifferenceSEARCH");
            err = true;
        }
        a = valores.get("BlockAllowMULTIMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "BlockAllowMULTIMATCH");
            err = true;
        }
        a = valores.get("MethodAllowMULTIMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "MethodAllowMULTIMATCH");
            err = true;
        }
        a = valores.get("ClassAllowMULTIMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "ClassAllowMULTIMATCH");
            err = true;
        }
        a = valores.get("ReturnNullMatchesIfNULL");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "ReturnNullMatchesIfNULL");
            err = true;
        }        
        a = valores.get("MethodSAMENAMEMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "MethodSAMENAMEMATCH");
            err = true;
        }     
        a = valores.get("MethodOfClassSAMENAMEMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "MethodOfClassSAMENAMEMATCH");
            err = true;
        }              
        a = valores.get("ClassSAMENAMEMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "ClassSAMENAMEMATCH");
            err = true;
        }        
        a = valores.get("ReflexiveMATCH");
        if (a != 0 && a != 1) {
            event(EventType.PARAMETRO_ESPERABA_CERO_O_UNO,
                  "ReflexiveMATCH");
            err = true;
        }
        a = valores.get("ProgMatchMIN");
        if (a < 0 || a > 1) {
            event(EventType.PARAMETRO_ESPERABA_ENTRE_CERO_Y_UNO,
                  "ProgMatchMIN");
            err = true;
        }
        b = a;
        a = valores.get("ProgMatchMAX");
        if (a < 0 || a > 1) {
            event(EventType.PARAMETRO_ESPERABA_ENTRE_CERO_Y_UNO,
                  "ProgMatchMAX");
            err = true;
        }
        if (b > a) {
            event(EventType.PARAMETRO_TIENE_VALOR_NO_VALIDO,
                  "ProgMatchMIN");
            err = true;
        }
        String[] ai = {
        "ProgMatchClassThresholdMINIMUM",
        "ProgMatchClassThresholdMAXIMUM",
        "ProgMatchClassMatchMINIMUM",
        "ProgMatchERROR",
        "ProgMatchOPTIMISM",
        "ProgMatchLIMIT",
        "ProgMatchClassACCEPTABLE",
        "ProgMatchClassDIFFERENCE",
        "ProgMatchMethodACCEPTABLE",
        "ProgMatchMethodDIFFERENCE",
        "ClassMatchACCEPTABLE",
        "ClassMatchLIMIT",
        "ClassMatchDIFFERENCE",
        "ClassMatchOPTIMISM",
        "MethodMatchACCEPTABLE",
        "MethodMatchLIMIT",
        "MethodMatchDIFFERENCE",
        "MethodMatchOPTIMISM",
        "ProgMatchDIFFERENCE",
        "ProgMatchMethodMatchMINIMUM",
        "ClassMatchMethodMatchMINIMUM",
        "MethodMatchBlockMatchMINIMUM",
        };
        int x;
        for (x = 0;x < ai.length;++x) {
            a = valores.get(ai[x]);
            if (a < 0 || a > 1) {
                event(EventType.PARAMETRO_ESPERABA_ENTRE_CERO_Y_UNO,
                      ai[x]);
                err = true;
            }
        }
       
        Object campos[] = valores.keySet().toArray();
        int i;
        for (i = 0;i < campos.length;++i) {
            if (((String)campos[i]).startsWith("BlockWeight") ||
                ((String)campos[i]).endsWith("MINIMUMINSTRUCTION")) {
                a = valores.get((String)campos[i]);
                if (a < 0) {
                   event(EventType.PARAMETRO_ESPERABA_MAYOR_CERO,
                      (String)campos[i]);
                   err = true;
                }
            }
        }
        if (err)
            throw new JSimilException(ExceptionType.ERROR_EN_PERFIL,
                    "Error en perfil");

    }
    
    /**
     * Cargar la configuración del perfil desde un fichero.
     * @param ruta Ruta desde la que leer el perfil.
     * @.post Configuración cargada desde un fichero.
     * @exception JSimilException La ruta no es válida o accesible.
     * @exception JSimilException Formato incorrecto.
     */
    public void load(String ruta) throws JSimilException {
        BufferedReader in;
        FileReader fr;
        String linea;
        int estado;
        
        try {
            fr = new FileReader(ruta);
        }
        catch (FileNotFoundException e) {
            fr = null;
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                               "Ruta no válida o inaccesible.");    
        }
        in = new BufferedReader(fr);
        
        estado = 0;
        
        try {
            while ((linea = in.readLine()) != null) {
                linea.trim();
                if (estado == 0) {
                    if (linea.equals("JSimil Profile File"))
                        estado = 1;
                    else
                        throw new JSimilException(ExceptionType.FORMATO_INCORRECTO,
                                             "Formato incorrecto.");
                }
                else {
                    Object campos[] = valores.keySet().toArray();
                    int i;
                    for (i = 0;i < campos.length;++i) {
                        if (linea.startsWith((String)campos[i])) {
                            valores.put((String)campos[i], 0.0);
                            if (linea.startsWith(((String)campos[i])+" "))
                                valores.put((String)campos[i],
                                  new Double(
                                    linea.substring(
                                       (((String)campos[i])+" ").length()
                                    )
                                  )
                                );
                        }
                    }                    
                }
            }
            if (estado == 0) {
                throw new JSimilException(ExceptionType.FORMATO_INCORRECTO,
                                     "Formato incorrecto.");
            }
            in.close();
        }
        catch (IOException e) {
            Object campos[] = valores.keySet().toArray();
            int i;
            for (i = 0;i < campos.length;++i) {
                valores.put((String)campos[i],0.0);
            }
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                                 "Ruta no válida o inaccesible.");
        } 
    }
    
    /**
     * Guardar la configuración del perfil a un fichero.
     * @param ruta Ruta a la que escribir el perfil.
     * @.post Configuración escrita a un fichero.
     * @exception JSimilException La ruta no es válida o accesible.
     * @exception JSimilException Error escribiendo el fichero.
     */
    public void save(String ruta) throws JSimilException {
        BufferedWriter out;
        FileWriter fw;

        if (ruta == null) {
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                                 "Ruta no válida o inaccesible.");    
        }
        else if (ruta.equals("")) {
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                                 "Ruta no válida o inaccesible.");                  
        }
        
        try {
            fw = new FileWriter(ruta);
        }
        catch (IOException e) {
            throw new JSimilException(ExceptionType.ERROR_ESCRIBIENDO_FICHERO,
                               "Error abriendo fichero para escribir.");    
        }
        out = new BufferedWriter(fw);
                
        try {
            out.write("JSimil Profile File");
            out.newLine();
            out.newLine();
            
            Object campos[] = valores.keySet().toArray();
            ArrayList<String> a = new ArrayList<String>();
            ArrayList<String> b = new ArrayList<String>();
            ArrayList<String> c = new ArrayList<String>();
            ArrayList<String> d = new ArrayList<String>();
            int i;
            for (i = 0;i < campos.length;++i) {
                  if (((String)campos[i]).startsWith("Prog") &&
                          !((String)campos[i]).endsWith("MIN") &&
                          !((String)campos[i]).endsWith("MAX") &&
                          !((String)campos[i]).endsWith("ERROR") &&
                          !((String)campos[i]).endsWith("OPTIMISM") &&
                          !((String)campos[i]).endsWith("LIMIT")
                          ) 
                      a.add((String)campos[i]);
                  else if (((String)campos[i]).startsWith("Class"))
                      b.add((String)campos[i]);
                  else if (((String)campos[i]).startsWith("Method"))
                      c.add((String)campos[i]);
                  else if (((String)campos[i]).startsWith("Block"))
                      d.add((String)campos[i]);
            }
            Collections.sort(a);
            Collections.sort(b);
            Collections.sort(c);
            Collections.sort(d);
            

            String aa;
            aa = "ReflexiveMATCH";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            aa = "DifferenceSEARCH";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            aa = "ReturnNullMatchesIfNULL";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            out.newLine();
            aa = "ProgMatchMIN";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            aa = "ProgMatchMAX";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            aa = "ProgMatchERROR";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            aa = "ProgMatchLIMIT";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            aa = "ProgMatchOPTIMISM";
            out.write(aa + " " + valores.get(aa));
            out.newLine();
            out.newLine();
            for (i = 0;i < a.size();++i) {
                out.write(a.get(i) + " " + valores.get(a.get(i)));
                out.newLine();
            }
            out.newLine();
            for (i = 0;i < b.size();++i) {
                out.write(b.get(i) + " " + valores.get(b.get(i)));
                out.newLine();
            }
            out.newLine();
            for (i = 0;i < c.size();++i) {
                out.write(c.get(i) + " " + valores.get(c.get(i)));
                out.newLine();
            }
            out.newLine();
            for (i = 0;i < d.size();++i) {
                out.write(d.get(i) + " " + valores.get(d.get(i)));
                out.newLine();
            }
            out.flush();
            out.close();
        }
        catch (IOException e) {
            throw new JSimilException(ExceptionType.ERROR_ESCRIBIENDO_FICHERO,
                                 "Error escribiendo fichero.");
        }
    }

    /**
     * Devuelve el escuchador de eventos asignado.
     * @return Escuchador de eventos asignado.
     */
    public JSimilEventListener getListener() {
        return listener;
    }
    
    /**
     * Asigna un escuchador de eventos.
     * @param listener Escuchador de eventos a asignar.
     * @.post Escuchador de eventos asignado.
     */
    public void setListener(JSimilEventListener listener) {
        this.listener = listener;
    }

    /**
     * Lanza un evento.
     * @param tipo Tipo del evento lanzado.
     * @param mensaje Mensaje del evento lanzado.
     * @.post Se ha lanzado el evento en cuestión.
     */
    void event(EventType tipo,String mensaje) {
        if (listener != null)
            listener.sEventOcurred(new JSimilEvent(this,tipo,mensaje));
    }
    
    /**
     * Comparar el contenido de dos perfiles.
     * @param obj Otro perfil a comparar.
     * @return true si iguales, false si distintos.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MatchingProfile other = (MatchingProfile) obj;
        if (this.valores != other.valores &&
                (this.valores == null || !this.valores.equals(other.valores))) {
            return false;
        }
        return true;
    }

    /**
     * Obtener el hashcode de un perfil.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.valores != null ? this.valores.hashCode() : 0);
        return hash;
    }
    
}
