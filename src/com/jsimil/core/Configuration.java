/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Configuración de rutas a programas utilizados.
 * @author elezeta
 */
final public class Configuration implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Ruta al ejecutable del compilador.
     */
    private String rutaCompilador;
        
    /**
     * Ruta al ejecutable del desensamblador.
     */
    private String rutaDesensamblador;
    
    /**
     * Si se debe compilar o no.
     */
    private boolean compilar;
    
    /**
     * Si se deben aceptar los códigos ya compilados.
     */
    private boolean aceptaComp;

    /**
     * Si se descartan los programas que dan errores.
     */
    private boolean descartaErr;

    /**
     * Codificación de los ficheros fuente.
     */
    private String encoding;

    /**
     * Class path
     */
    private String classPath;
    
    /**
     * Constructor.
     * @.post Objeto config inicializado.
     */
    public Configuration() {
      rutaCompilador = "";
      rutaDesensamblador = "";
      compilar = true;
      aceptaComp = false;
      descartaErr = true;
      encoding = "UTF-8,ISO-8859-1";
      classPath = ".";
    }

    /**
     * Obtener la ruta al compilador.
     * @return Ruta al compilador.
     */
    public String getRutaCompilador() {
        return rutaCompilador;
    }

    /**
     * Asignar la ruta del compilador.
     * @param rutaCompilador Ruta a asignar.
     * @.post Ruta asignada.
     */
    public void setRutaCompilador(String rutaCompilador) {
        this.rutaCompilador = rutaCompilador;
    }

    /**
     * Obtener la ruta al desensamblador.
     * @return Ruta al desensamblador.
     */
    public String getRutaDesensamblador() {
        return rutaDesensamblador;
    }

    /**
     * Asignar la ruta del desensamblador.
     * @param rutaDesensamblador Ruta a asignar.
     * @.post Ruta asignada.
     */
    public void setRutaDesensamblador(String rutaDesensamblador) {
        this.rutaDesensamblador = rutaDesensamblador;
    }
    
    /**
     * Validar la configuración.
     * @exception JSimilException La ruta del compilador no es válida o accesible.
     * @exception JSimilException Ruta del desensamblador no es válida o accesible.
     * @exception JSimilException Compilador no ejecutable.
     * @exception JSimilException Desensamblador no ejecutable.
     */
    public void validate() throws JSimilException {
        File f = new File(rutaCompilador);
        if (!f.exists()) {
            throw new JSimilException(
                      ExceptionType.RUTA_DEL_COMPILADOR_NO_VALIDA_O_INACCESIBLE,
                           "Ruta del compilador no válida o inaccesible.");
        }

        try {
          try {
            Process p = Runtime.getRuntime().exec(rutaCompilador);
            p.waitFor();
            p.getInputStream().close();
            p.getOutputStream().close();
            p.getErrorStream().close();
          } catch (InterruptedException e) {
              throw new JSimilException(ExceptionType.COMPILADOR_NO_EJECUTABLE,
                             "Compilador no ejecutable.");
          }
        } catch (IOException e) {
            throw new JSimilException(ExceptionType.COMPILADOR_NO_EJECUTABLE,
                           "Compilador no ejecutable.");
        }

        File f2 = new File(rutaDesensamblador);
        if (!f2.exists()) {
            throw new JSimilException(
                  ExceptionType.RUTA_DEL_DESENSAMBLADOR_NO_VALIDA_O_INACCESIBLE,
                            "Ruta del desensamblador no válida o inaccesible.");
        }
        try {
          try {
            Process p = Runtime.getRuntime().exec(rutaDesensamblador);
            p.waitFor();
            p.getInputStream().close();
            p.getOutputStream().close();
            p.getErrorStream().close();
          } catch (IOException e) {
              throw new JSimilException(ExceptionType.DESENSAMBLADOR_NO_EJECUTABLE,
                             "Desensamblador no ejecutable.");
          }
        } catch (InterruptedException e) {
            throw new JSimilException(ExceptionType.DESENSAMBLADOR_NO_EJECUTABLE,
                           "Desensamblador no ejecutable.");
        }
    }

    /**
     * Obtener la versión de los programas utilizados.
     * @return Versiones de los programas utilizados.
     * @exception JSimilException Error lanzando compilador para obtener la versión.
     */
    public String getVersion() throws JSimilException {
        String vers;
        try {
            Process p = Runtime.getRuntime().exec(rutaCompilador + " -version");
            BufferedReader stdInput = new BufferedReader(new 
                                         InputStreamReader(p.getErrorStream()));
            String linea = stdInput.readLine();
            stdInput.close();
            if (linea == null) {
                throw new JSimilException(
                           ExceptionType.ERROR_LANZANDO_COMPILADOR_PARA_VERSION,
                           "Error lanzando compilador para versión.");
            }
            if (!linea.startsWith("javac")) {
                throw new JSimilException(
                           ExceptionType.ERROR_LANZANDO_COMPILADOR_PARA_VERSION,
                           "Error lanzando compilador para versión.");
            }
            vers = linea.substring("javac ".length());
            try {
              p.waitFor();
              p.getInputStream().close();
              p.getOutputStream().close();
              p.getErrorStream().close();
            } catch (InterruptedException e) {
              throw new JSimilException(
                           ExceptionType.ERROR_LANZANDO_COMPILADOR_PARA_VERSION,
                             "Error lanzando compilador para versión.");
            }
          } catch (IOException e) {
              throw new JSimilException(
                           ExceptionType.ERROR_LANZANDO_COMPILADOR_PARA_VERSION,
                             "Error lanzando compilador para versión.");
          }
                
        return vers;
    }

    /**
     * Cargar la configuración desde un fichero.
     * @param ruta Ruta desde la que leer la configuración.
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
            encoding = "UTF-8,ISO-8859-1";            
            while ((linea = in.readLine()) != null) {
                linea.trim();
                if (estado == 0) {
                    if (linea.equals("JSimil Configuration File"))
                        estado = 1;
                    else
                        throw new JSimilException(ExceptionType.FORMATO_INCORRECTO,
                                             "Formato incorrecto.");
                }
                else {
                    if (linea.startsWith("ClassPath")) {
                        classPath = ".";
                        if (linea.startsWith("ClassPath ")) {
                            classPath = linea.substring(
                                                  "ClassPath ".length()).trim();                                                            
                        }
                    }
                    if (linea.startsWith("Encoding")) {
                        encoding = "UTF-8,ISO-8859-1";
                        if (linea.startsWith("Encoding ")) {
                            encoding = linea.substring(
                                                   "Encoding ".length()).trim();                                                            
                        }
                    }
                    if (linea.startsWith("CompilerPath")) {
                        rutaCompilador = "";
                        if (linea.startsWith("CompilerPath ")) {
                            rutaCompilador = linea.substring(
                                                   "CompilerPath ".length())
                                                   .trim();                                                            
                            if (!(new File(rutaCompilador)).isAbsolute()) {
                                rutaCompilador = ((new File(ruta))
                                        .getAbsoluteFile()).getParent()+
                                                File.separator+rutaCompilador;
                            }
                        }
                    }
                    else if (linea.startsWith("DisassemblerPath")) {
                        rutaDesensamblador = "";
                        if (linea.startsWith("DisassemblerPath ")) {
                            rutaDesensamblador = linea.substring(
                                               "DisassemblerPath ".length())
                                               .trim();
                            if (!(new File(rutaDesensamblador)).isAbsolute()) {
                                rutaDesensamblador = (new File(ruta))
                                                     .getParent()+
                                              File.separator+rutaDesensamblador;
                            }
                        }
                    }
                    else if (linea.startsWith("AcceptCompiled")) {
                        aceptaComp = true;
                        if (linea.startsWith("AcceptCompiled ")) {
                            String a = linea.substring(
                                                   "AcceptCompiled ".length())
                                                   .trim();
                            if (a.equals("false"))
                                aceptaComp = false;
                            else
                                aceptaComp = true;
                        }
                    }
                    else if (linea.startsWith("DiscardErrors")) {
                        descartaErr = true;
                        if (linea.startsWith("DiscardErrors ")) {
                            String a = linea.substring(
                                                   "DiscardErrors ".length())
                                                   .trim();
                            if (a.equals("false"))
                                descartaErr = false;
                            else
                                descartaErr = true;
                        }
                    }
                    else if (linea.startsWith("Compile")) {
                        compilar = true;
                        if (linea.startsWith("Compile ")) {
                            String a = linea.substring(
                                                   "Compile ".length())
                                                   .trim();
                            if (a.equals("false"))
                                compilar = false;
                            else
                                compilar = true;
                        }
                    }
                    else if (linea.equals("Autoconfigure")) {
                        try {
                            autoConfig();
                        } catch (JSimilException e) {
                            //Ok.
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
            rutaCompilador = "";
            rutaDesensamblador = ""; 
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                                 "Ruta no válida o inaccesible.");
        } 
    }

    /**
     * Guardar la configuración a un fichero.
     * @param ruta Ruta a la que escribir la configuración.
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
            out.write("JSimil Configuration File");
            out.newLine();
            out.newLine();
            out.write("Encoding "+getEncoding());
            out.newLine();
            if (aceptaComp)
                out.write("AcceptCompiled true");
            else
                out.write("AcceptCompiled false");
            out.newLine();
            if (descartaErr)
                out.write("DiscardErrors true");
            else
                out.write("DiscardErrors false");
            out.newLine();
            if (compilar)
                out.write("Compile true");
            else
                out.write("Compile false");
            out.newLine();
            String padre = (new File(ruta).getAbsoluteFile()).getParent();
            String eruta = rutaCompilador;
            if (padre != null) {
                if (eruta.startsWith(padre))
                    eruta = eruta.substring((padre+File.separator).length());
            }
            out.write("CompilerPath "+eruta);
            out.newLine();
            eruta = rutaDesensamblador;
            if (padre != null) {
                if (eruta.startsWith(padre))
                    eruta = eruta.substring((padre+File.separator).length());
            }
            out.write("DisassemblerPath "+eruta);
            out.newLine();
            out.flush();
            out.close();
        }
        catch (IOException e) {
            throw new JSimilException(ExceptionType.ERROR_ESCRIBIENDO_FICHERO,
                                 "Error escribiendo fichero.");
        } 
    }    
    
    /**
     * Realiza una configuración automática.
     * @.post Configuración automática realizada.
     * @exception JSimilException Imposible autoconfigurar.
     */
    public void autoConfig() throws JSimilException {
        String rutasecc = rutaCompilador;
        String rutasecd = rutaDesensamblador;
        rutaCompilador = System.getProperty("java.home") + File.separator +
                ".." + File.separator + "bin" + File.separator + "javac";
        rutaDesensamblador = System.getProperty("java.home") + File.separator +
                ".." + File.separator + "bin" + File.separator + "javap";
        if (!(new File(rutaCompilador)).exists()) {
            rutaCompilador += ".exe";
        }
        if (!(new File(rutaDesensamblador)).exists()) {
            rutaDesensamblador += ".exe";
        }
        if (!(new File(rutaCompilador)).exists()) {
            rutaCompilador = System.getProperty("java.home") + File.separator +
                "bin" + File.separator + "javac";
        }
        if (!(new File(rutaDesensamblador)).exists()) {
            rutaDesensamblador = System.getProperty("java.home") + File.separator +
                "bin" + File.separator + "javap";
        }
        if (!(new File(rutaCompilador)).exists()) {
            rutaCompilador += ".exe";
        }
        if (!(new File(rutaDesensamblador)).exists()) {
            rutaDesensamblador += ".exe";
        }
        if (!(new File(rutaCompilador)).exists()) {
            rutaCompilador = System.getProperty("java.home") + 
                "bin" + File.separator + "javac";
        }
        if (!(new File(rutaDesensamblador)).exists()) {
            rutaDesensamblador = System.getProperty("java.home") +
                "bin" + File.separator + "javap";
        }
        if (!(new File(rutaCompilador)).exists()) {
            rutaCompilador += ".exe";
        }
        if (!(new File(rutaDesensamblador)).exists()) {
            rutaDesensamblador += ".exe";
        }
        try {
            validate();
        }
        catch (JSimilException e) {
            rutaCompilador = rutasecc;
            rutaDesensamblador = rutasecd;
            throw new JSimilException(
               ExceptionType.IMPOSIBLE_AUTOCONFIGURAR,
                "Imposible autoconfigurar.");
        }
    }

    /**
     * Obtiene si se deben aceptar los códigos compilados o no.
     * @return true si sí, false si no.
     */
    public boolean getAceptaComp() {
        return aceptaComp;
    }

    /**
     * Obtiene si se deben descartar los programas con errores.
     * @return true si sí, false si no.
     */
    public boolean getDescartaErrores() {
        return descartaErr;
    }
    
    /**
     * Obtiene si se deben compilar o no los programas.
     * @return true si sí, false si no.
     */
    public boolean getCompilar() {
        return compilar;
    }

    /**
     * Cambia si se deben aceptar los códigos compilados o no.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    public void setAceptaComp(boolean val) {
        this.aceptaComp = val;
    }
   
    /**
     * Cambia si se deben descartar los programas con errores.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    public void setDescartaErrores(boolean val) {
        this.descartaErr = val;
    }
    
    /**
     * Cambia la opción de compilación de los programas.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    public void setCompilar(boolean val) {
        this.compilar = val;
    }

    /**
     * Cambia la codificación de los ficheros fuente.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    public void setEncoding(String val) {
        this.encoding = val;
    }

    /**
     * Devuelve la codificación de los ficheros fuente.
     * @return codificación de los ficheros fuente.
     */
    public String getEncoding() {
        return encoding;
    }
    
    /**
     * Cambia el classpath.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    public void setClassPath(String val) {
        this.classPath = val;
    }

    /**
     * Devuelve el classpath.
     * @return classpath.
     */
    public String getClassPath() {
        return classPath;
    }    
}
