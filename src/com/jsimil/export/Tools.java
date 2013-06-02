/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.export;

import java.util.StringTokenizer;

/**
 * Clase auxiliar de métodos estáticos.
 * @author elezeta
 */
abstract class Tools {

    /**
     * Codifica caracteres extraños a xml.
     * @param re Cadena a codificar.
     * @return Cadena codificada.
     */
    static String codifica2(String re) {
        re = re.replace("&","&amp;");
        re = re.replace("<","&lt;");
        re = re.replace(">","&gt;");
        re = re.replace("\"","&quot;");
        re = re.replace("'","&#039;");
        re = re.replace("(","&#040;");
        re = re.replace(")","&#041;");
        re = re.replace("/","&#047;");
        re = re.replace("\\","&#092;");   
        return re;
    }
    
    /**
     * Codifica caracteres extraños a html.
     * @param re Cadena a codificar.
     * @return Cadena codificada.
     */
    static String codifica(String re) {
        re = re.replace("&","&amp;");
        re = re.replace(" ","&nbsp;");
        re = re.replace("á","&aacute;");
        re = re.replace("é","&eacute;");
        re = re.replace("í","&iacute;");
        re = re.replace("ó","&oacute;");
        re = re.replace("ú","&uacute;");
        re = re.replace("Á","&Aacute;");
        re = re.replace("É","&Eacute;");
        re = re.replace("Í","&Iacute;");
        re = re.replace("Ó","&Oacute;");
        re = re.replace("Ú","&Uacute;");
        re = re.replace("ñ","&ntilde;");
        re = re.replace("Ñ","&Ntilde;");
        re = re.replace("<","&lt;");
        re = re.replace(">","&gt;");
        re = re.replace("\"","&quot;");
        re = re.replace("'","&#039;");
        re = re.replace("(","&#040;");
        re = re.replace(")","&#041;");
        re = re.replace("/","&#047;");
        re = re.replace("\\","&#092;");   
        return re;
    }

    /**
     * Codifica caracteres extraños a html y mete wordwrap.
     * @param re Cadena a codificar.
     * @return Cadena codificada.
     */
    static String codificacod(String re) {
        int codl = 62;
        String rea = "";
        rea = rea.replace("\t","    ");
        while (!re.equals("")) {
            if (re.length()>codl) {
                rea+=re.substring(0,codl)+" _\n_ ";
                re = re.substring(codl);
                codl = 60;
            }
            else {
                rea+=re;
                re = "";
            }
        } 
        rea = rea.replace("&","&amp;");
        rea = rea.replace(" ","&nbsp;");
        rea = rea.replace("á","&aacute;");
        rea = rea.replace("é","&eacute;");
        rea = rea.replace("í","&iacute;");
        rea = rea.replace("ó","&oacute;");
        rea = rea.replace("ú","&uacute;");
        rea = rea.replace("Á","&Aacute;");
        rea = rea.replace("É","&Eacute;");
        rea = rea.replace("Í","&Iacute;");
        rea = rea.replace("Ó","&Oacute;");
        rea = rea.replace("Ú","&Uacute;");
        rea = rea.replace("ñ","&ntilde;");
        rea = rea.replace("Ñ","&Ntilde;");
        rea = rea.replace("<","&lt;");
        rea = rea.replace(">","&gt;");
        rea = rea.replace("\"","&quot;");
        rea = rea.replace("'","&#039;");
        rea = rea.replace("(","&#040;");
        rea = rea.replace(")","&#041;");
        rea = rea.replace("/","&#047;");
        rea = rea.replace("\\","&#092;");   
        rea = rea.replace("\n","<br/>");   
        return rea;
    }

    /**
     * Devuelve una cadena recortada con espacios cada 40 caracteres.
     * @param re Cadena a recortar.
     * @return Cadena recortada con espacios.
     */
    static String recorta(String re) {
        re = re.replace(";","; ");
        re = re.replace(".",". ");
        re = re.replace(":",": ");
        re = re.replace("/","/ ");
        re = codifica(re).replace("&nbsp;"," ");
        StringTokenizer st = new StringTokenizer(re);
        String output = "";
        String t;
        while (st.hasMoreTokens()) {
           t = st.nextToken();  
           while (!t.equals("")) {
               if (t.length()>40) {
                   output+=" "+t.substring(0,40);
                   t = t.substring(40);
               }
               else {
                   output+=" "+t;
                   t = "";
               }
           }
        }           
        return output;
    }
    
}
