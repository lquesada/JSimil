/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarEntry;


/**
 * Clase auxiliar de métodos estáticos.
 * @author elezeta
 */
abstract class Tools {
        
    /**
     * Obtiene recursivamente las clases.
     * @param direc Dirección raiz.
     * @param ruta Ruta.
     * @.post Clases desensambladas.
     */
    static String getClases(String direc,String ruta) {
        String listado = "";
        try {
            File check = new File(direc+ruta);
            String contenido[] = check.list();
            if (contenido == null)
                return "";
            int i;
            String filename;

            for (i = 0;i < contenido.length;++i) {
                filename = direc+ruta+contenido[i];
                File f2 = new File(filename);
                if (f2.isDirectory()) {
                    listado+=getClases(direc,ruta+contenido[i]+File.separator)+
                            " ";
                }
                else if (f2.canRead()) {
                    if (contenido[i].endsWith(".class")) {
                        String nombre = ruta+contenido[i];
                        nombre = nombre.substring(0,nombre.length()-6);
                        listado += nombre+" ";
                    }                
                }
            }
        } catch (Exception e) {
            //Ok.
        }
        return listado;
    }

    /**
     * Eliminar directorio recursivamente.
     * @param path Directorio a eliminar.
     * @.post Directorio eliminado.
     * @return Si se ha eliminado correctamente o no.
     */
    static boolean deleteDirectory(File path) {
        if(path.exists()) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; ++i) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }
    
    /**
     * Copiar fichero.
     * @param in Fichero origen.
     * @param out Fichero destino.
     * @.post Fichero copiado.
     * @exception IOException Excepción copiando fichero.     
     */
    static void copyFile(File in, File out) throws IOException {
        FileInputStream fis  = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } 
        catch (IOException e) {
            throw e;
        }
        finally {
            if (fis != null) fis.close();
            if (fos != null) fos.close();
        }
    }     

   /**
    * Obtiene el número de lineas en código original de un código desensamblado.
    * @param d Código desensamblado.
    * @param ini Linea inicial.
    * @param fin Linea final.
    * @return Linea mínima y máxima del código original.
    */
    static List<Integer> getLines(List<String> d,int ini,int fin) {
        int z;
        int ini_cod = -1;
        int fin_cod = -1;
        if (ini < 0)
            ini = 0;
        if (fin > d.size())
            fin = d.size();
        for (z = ini;z<fin;++z) {
            String line = d.get(z);
            if (line.startsWith("   line ")) {
                line = line.replace("   line ","");
                int m = 0;
                while ((line.charAt(m) >= '0') && (line.charAt(m) <= '9') &&
                       line.length() > m)
                     ++m;
                line = line.substring(0,m);
                if (!line.equals("")) {
                    try {
                        int a = Integer.parseInt(line)-1;
                        if (ini_cod == -1 || ini_cod > a)
                            if (a >= 0)
                                ini_cod = a;
                        if (fin_cod == -1 || fin_cod < a)
                            if (a >= d.size())
                                fin_cod = d.size()-1;
                            else
                                fin_cod = a;
                    }
                    catch (Exception e) {
                        //Ok.
                    }
                }
            }
        }
        List<Integer> a = new ArrayList<Integer>();
        a.add(new Integer(ini_cod));
        a.add(new Integer(fin_cod));
        return a;
    }
    
   /**
    * Obtiene el nombre de un método a partir del código.
    * @param linea Codigo.
    * @return Nombre del método.
    */
    static String nombreMetodo(String linea) {
        boolean comments = false;
        String metodo = new String("");
        linea = linea.replace("/*"," /* ");
        linea = linea.replace("*/"," */ ");
        linea = linea.replace("//"," // ");
        linea = linea.replace("\\\""," potato ");
        linea = linea.replace("\""," \" ");            
        linea = linea.replace("{"," { ");
        linea = linea.replace("}"," } ");
        linea = linea.replace("("," ( ");
        linea = linea.replace(")"," ) ");
        linea = linea.replace(","," , ");
        linea = linea.replace(";"," ; ");
        StringTokenizer st = new StringTokenizer(linea," \n\t\r;");
        String ss;
        String tipo = new String("");
        List<String> tokens = new ArrayList<String>();
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
                tokens.add(ss);
            }
        }

        //Localizo el paréntesis.
        int i;
        int pari = -1;
        int pard = -1;
        for (i = 0;i < tokens.size();++i) {
            if (tokens.get(i).equals("("))
                pari = i;
            if (tokens.get(i).equals(")") && pard==-1)
                pard = i;
        }
        if (pari < 1)
            return "";
        if (pard < 2)
            return "";
        //Justamente antes está el nombre.
        String nombre = tokens.get(pari-1);
        //Justamente antes, si no es public, private, protected,
        //synchronized o static (en cuyo caso no devuelve), está el tipo.
        //Si justamente antes, el tipo no es void, int, long, char, double
        //o float, es de tipo compuesto. Cambiar por barras los puntos.
        if (pari > 1) {
            String tip = tokens.get(pari-2);
            if (tip.equals("public") ||
                tip.equals("private") ||
                tip.equals("protected") ||
                tip.equals("synchronized") ||
                tip.equals("static")) {
                
            }
            else if (tip.equals("void"))
                tipo = "V";
            else if (tip.equals("int"))
                tipo = "I";
            else if (tip.equals("long"))
                tipo = "J";
            else if (tip.equals("char"))
                tipo = "C";
            else if (tip.equals("double"))
                tipo = "D";
            else if (tip.equals("float"))
                tipo = "F";
            else
                tipo = "L"+tip.replace(".","/")+";";
        }
        if (tipo.equals(""))
            tipo = "V";
        //Seguidamente leer los parámetros.

        String pars = "";
        
        i = pari+1;
        while (i < tokens.size() && !tokens.get(i).equals(")")) {
            String tip = tokens.get(i);
            if (tip.equals("void"))
                pars += "V";
            else if (tip.equals("int"))
                pars += "I";
            else if (tip.equals("long"))
                pars += "J";
            else if (tip.equals("char"))
                pars += "C";
            else if (tip.equals("double"))
                pars += "D";
            else if (tip.equals("float"))
                pars += "F";
            else
                pars += "L"+tip.replace(".","/")+";";            
            i+=2;
        }
        
        
        //Monto el nombre como: nombre:(parametros)tipo
        metodo = nombre+":("+pars+")"+tipo;

/*DEBUG
        System.out.println("Linea: "+linea);
        System.out.println("Nombre: "+nombre);
        System.out.println("Tipo: "+tipo);
        System.out.println("Parámetros: "+pars);
        System.out.println("MÉtODO: "+metodo);
*/
        return metodo;
    }
    
   /**
    * Obtiene el número de linea de una linea desensamblada.
    * @param linea Codigo.
    * @return Número de linea, -1 si no es ninguna, -2 si es default.
    */
    static int numeroLinea(String linea) {
        StringTokenizer st = new StringTokenizer(linea,"\n\r\t ;");
        String ss;
        if (st.hasMoreTokens()) {
            ss = st.nextToken();
            if (ss.endsWith(":")) {
                if (ss.equals("default:"))
                    return -2;
                try {
                    return Integer.parseInt(ss.substring(0,ss.length()-1));
                }
                catch (Exception e) {
                    return -1;
                }
            }
            else
                return -1;
        }
        else
            return -1;
        
    }
    
    /**
    * Obtiene el nombre de instrucción de una linea determinada.
    * @param linea Codigo.
    * @return Nombre de instrucción, null si es ninguna, "-" si es switch.
    */
    static String nombreInstruccion(String linea) {
        StringTokenizer st = new StringTokenizer(linea,"\n\r\t ;{}()/");
        String ss;
        if (st.hasMoreTokens()) {
            ss = st.nextToken();
            if (ss.endsWith(":")) {
                if (st.hasMoreTokens()) {
                    ss = st.nextToken();
                    try {
                        Integer.parseInt(ss);
                        return "-";
                    }
                    catch (Exception e) {
                        return ss;
                    }
                }
                else
                    return null;
            }
            else
                return null;
        }
        else
            return null;
    }

   /**
    * Obtiene el objetivo de una linea determinada.
    * @param linea Codigo.
    * @return Objetivo, -1 si es ninguna.
    */
    static int objetivoInstruccion(String linea) {
        StringTokenizer st = new StringTokenizer(linea,"\n\r\t ;");
        String ss;
        if (st.hasMoreTokens()) {
            ss = st.nextToken();
            if (ss.endsWith(":")) {
                if (st.hasMoreTokens()) {
                    ss = st.nextToken();
                    if (ss.startsWith("if") || ss.startsWith("goto") ||
                            ss.startsWith("jsr")) {
                        if (st.hasMoreTokens()) {
                            ss = st.nextToken();
                            try {
                                return Integer.parseInt(ss);
                            } catch (Exception e) {
                                return -1;
                            }
                        }
                        else
                            return -1;
                    }
                    else {
                        try {
                            return Integer.parseInt(ss);
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                }
                else
                    return -1;
            }
            else
                return -1;
        }
        else
            return -1;
    }
   
   /**
    * Busca el número de linea donde está una instrucción.
    * @param cod Código.
    * @param ini Inicio del código.
    * @param fin Final del código.
    * @param nlinea Número de linea a buscar.
    * @return Número de linea donde está, -1 si no está.
    */
    static int buscaInstruccion(List<String> cod,int ini, int fin, int nlinea) {
        int i;
        for (i = ini;i < fin;++i) {
            if (numeroLinea(cod.get(i)) == nlinea) {
                return i;
            }
        }
        return -1;
    }

   /**
    * Busca el número de lineas del código original de una porción de código.
    * @param cod Código.
    * @param ini Primera linea donde buscar.
    * @param fin Última linea donde buscar.
    * @param inip Primera instrucción porción a buscar.
    * @param finp Última instrucción porción a buscar.
    * @return Inicio y fin en una lista, null si error.
    */
    static List<Integer> buscaOriginal(List<String> cod,int ini, int fin,
                                       int inip,int finp) {
        int min = numeroLinea(cod.get(inip));
        int max = numeroLinea(cod.get(finp));
        int ini_cod = -1;
        int fin_cod = -1;
        int a = -1;
        int line2 = -1;
        List<Integer> ret = new ArrayList<Integer>();
        int i;
        for (i = ini;i < fin;++i) {
            String line = cod.get(i);
            if (line.startsWith("   line ")) {
                //System.out.println("OK1!");
                line = line.replace("   line ","");
                int m = 0;
                while ((line.charAt(m) >= '0') && (line.charAt(m) <= '9') &&
                       line.length() > m)
                     ++m;
                String line1 = line.substring(0,m);
                if (!line1.equals("")) {
                    try {
                        a = Integer.parseInt(line1)-1;
                        if (line.length() >= m+2) {
                            line2 = new Integer(line.substring(m+2,
                                                line.length()));
                            if (line2 <= min) {
                                ini_cod = a;
                                if (fin_cod < a)
                                    fin_cod = a;
                            }
                            /*DEBUG
                            System.out.println("");
                            System.out.println("LINEOLD: "+lineold);
                            System.out.println("LINE: "+a);
                            System.out.println("LINE2: "+line2);
                            System.out.println("MAX: "+max);
                            System.out.println("INI_COD: "+ini_cod);
                            System.out.println("FIN_COD: "+fin_cod);
                             */
                            if (a != -1 && line2 <= max) {
                               // System.out.println("CAMBIO!!!!!!!!!!"+a);
                                if (fin_cod < a)
                                    fin_cod = a;
                            }
                        }
                    } catch (Exception e) {
                        //Ok.
                    }
                }
            }
        }
        ret.add(new Integer(ini_cod));        
        ret.add(new Integer(fin_cod));
        return ret;
    }

    
    /**
     * Obtiene los nombres de clases contenidos en una lista de Strings.
     * @param cont Contenido de entrada.
     * @param clas Clase a buscar comienzo y fin.
     * @param out Lista en la que se escribirá comienzo y final.
     * @.post Si se ha indicado clase a buscar, se rellenará la lista.
     * @return Listado de clases contenidas.
     */
    static List<String> listaClases(List<String> cont,String clas,
            List<Integer> out) {
        String linea;
        List<String> s = new ArrayList<String>();
        int estado = 0;
        String pack = new String("");
        int i;
        int ini = -1;
        int fin = -1;

        String actual = new String("");
        int nivel = 0;
        int nivelc = 0;
        boolean comments = false;
            
        for (i = 0;i < cont.size();++i) {
            linea = cont.get(i);
            linea = linea.replace("/*"," /* ");
            linea = linea.replace("*/"," */ ");
            linea = linea.replace("//"," // ");
            linea = linea.replace("\\\""," potato ");
            linea = linea.replace("\""," \" ");            
            linea = linea.replace("{"," { ");
            linea = linea.replace("}"," } ");
            StringTokenizer st = new StringTokenizer(linea," \n\t\r;");
            String ss;
            while (st.hasMoreTokens()) {
                ss = st.nextToken();

                //Si lee / *, se para hasta * / (aunque sea en otra linea).
                if (ss.equals("/*")) {
                    comments = true;
                }
                if (ss.equals("*/")) {
                    comments = false;
                }
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
                    if (ss.equals("{")) {
                        ++nivel;
                    }
                    if (ss.equals("}")) {
                        --nivel;
                        if (nivel == nivelc-1) {
                            nivelc = nivel;
                            if (!actual.equals("")) {
                                actual = actual.substring(0,
                                         actual.lastIndexOf(")"));
                                if (clas != null)
                                    if (actual.equals(clas))
                                        fin = i+1;
                                actual = actual.substring(0,
                                           actual.lastIndexOf(")")+1);
                            }
                        }
                    }

                    if (estado == 0 && ss.equals("package")) {
                        if (st.hasMoreTokens())
                            pack = st.nextToken().replace(".","/")+"/";
                        estado = 1;
                    }

                    if (ss.equals("class") || ss.equals("interface") || (ss.equals("enum"))) {
                        if (st.hasMoreTokens()) {
                            ss = st.nextToken();
                            actual=actual+ss.replace(".","/");
                            s.add((pack+actual).replace(")","$"));
                            if (clas != null)
                                if (clas.equals(pack+actual))
                                    ini = i;
                            actual=actual+")";
                        }
                        ++nivelc;
                        estado = 1;
                    }
                }
            }
        }
        if (clas != null && out != null) {
            out.add(ini);
            out.add(fin);
        }
        return s;
    }

    /**
     * Comparador de porcentaje de programa.
     */
    static final Comparator<CodeElement> compporcent =
      new Comparator<CodeElement>() {
        public int compare(CodeElement o1, CodeElement o2) {
            double val = o2.porcentajePrograma-o1.porcentajePrograma;
            if (val<0)
                return -1;
            else if (val>0)
                return 1;
            else
                return 0;
        }
    };
    
    /**
     * Comparador de tablas.
     */
    static final Comparator<ArrayList> comptab= new Comparator<ArrayList>() {
        public int compare(ArrayList o1, ArrayList o2) {
            double val = ((Double)o2.get(2))-((Double)o1.get(2));
            if (val<0)
                return -1;
            else if (val>0)
                return 1;
            else
                return 0;
        }
    };

    /**
     * Comparador de resultados Descendente sin importancia de porcentaje.
     */
    static final Comparator<ArrayList> compdesnimp =
            new Comparator<ArrayList>() {
        public int compare(ArrayList o1, ArrayList o2) {
            double val = ((Matching)o2.get(0)).getSimilitud() -
                         ((Matching)o1.get(0)).getSimilitud();
            if (val<0)
                return -1;
            else if (val>0)
                return 1;
            else
                return 0;
        }
    };
    
    /**
     * Comparador de resultados Descendente con importancia de porcentaje.
     */
    static final Comparator<ArrayList> compdesimp =
            new Comparator<ArrayList>() {
        public int compare(ArrayList o1, ArrayList o2) {
            Matching uno = (Matching)o1.get(0);
            Matching dos = (Matching)o2.get(0);
            if ((dos.getElementoA() == null) | (dos.getElementoB() == null))
                return -1;
            if ((uno.getElementoA() == null) | (uno.getElementoB() == null))
                return 1;
            double val = dos.getElementoA().porcentajePrograma*
                     dos.getElementoB().porcentajePrograma*
                     dos.getSimilitud()  -
                     uno.getElementoA().porcentajePrograma*
                     uno.getElementoB().porcentajePrograma*
                     uno.getSimilitud();
            if (val<0)
                return -1;
            else if (val>0)
                return 1;
            else
                return 0;
        }
    };

    /**
     * Comparador de resultados Ascendente sin importancia de porcentaje.
     */
    static final Comparator<ArrayList> compascnimp =
            new Comparator<ArrayList>() {
        public int compare(ArrayList o1, ArrayList o2) {
            double val = ((Matching)o2.get(0)).getSimilitud() -
                         ((Matching)o1.get(0)).getSimilitud();
            if (val<0)
                return 1;
            else if (val>0)
                return -1;
            else
                return 0;
        }
    };
    
    /**
     * Comparador de resultados Ascendente con importancia de porcentaje.
     */
    static final Comparator<ArrayList> compascimp =
            new Comparator<ArrayList>() {
        public int compare(ArrayList o1, ArrayList o2) {
            Matching uno = (Matching)o1.get(0);
            Matching dos = (Matching)o2.get(0);
            if ((dos.getElementoA() == null) | (dos.getElementoB() == null))
                return -1;
            if ((uno.getElementoA() == null) | (uno.getElementoB() == null))
                return 1;
            double val = dos.getElementoA().porcentajePrograma*
                     dos.getElementoB().porcentajePrograma*
                     dos.getSimilitud()  -
                     uno.getElementoA().porcentajePrograma*
                     uno.getElementoB().porcentajePrograma*
                     uno.getSimilitud();
            if (val<0)
                return 1;
            else if (val>0)
                return -1;
            else
                return 0;
        }
    };

    /**
     * Copia un directorio y descomprime los ficheros jar.
     * @.post Directorio copiado.
     * @exception IOException.
     */
    static public void copyDirectoryJar(File sourceLocation ,
                                        File targetLocation)
    throws IOException {
        
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            
            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectoryJar(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            if (sourceLocation.getName().endsWith(".jar")) {
                String destDir = targetLocation.getAbsolutePath();
                if (!destDir.endsWith(File.separator))
                    destDir += File.separator;
                new File(destDir).mkdir();
                JarFile jar = new JarFile(sourceLocation);
                Enumeration enume = jar.entries();
                while (enume.hasMoreElements()) {
                    JarEntry file = (JarEntry) enume.nextElement();
                    File f = new File(destDir
                            + java.io.File.separator + file.getName());
                    if (file.isDirectory()) { // if its a directory, create it
                        f.mkdir();
                        continue;
                    }
                    else if (file.getName().endsWith(".class")) {
                        // get the input stream
                        InputStream is = jar.getInputStream(file);
                        FileOutputStream fos = new FileOutputStream(f);
                        // write contents of 'is' to 'fos'
                        while (is.available() > 0) {
                            fos.write(is.read());
                        }
                        fos.close();
                        is.close();
                    }
                }               
            }
            else {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        }
    }
    
}
