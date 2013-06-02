/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Batería de programas a comparar.
 * @author elezeta
 */
final public class ProgramBattery implements Serializable {
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = JSimil.serialVersionUID;
    
    /**
     * Ruta a la batería de programas.
     */
    private String ruta;
    
    /**
     * Programas contenidos en la batería.
     */
    private List<Program> programas;
    
    /**
     * Escuchador de eventos.
     */
    transient private JSimilEventListener listener;
    
    /**
     * Constructor.
     * @.post Objeto batería inicializado.
     */
    public ProgramBattery() {
        ruta = "";
        listener = null;
        programas = new ArrayList<Program>();
    }
    
    /**
     * Comprobar si un programa o directorio tiene contenido.
     * @param ruta Ruta del programa o directorio a comprobar.
     * @return Si tiene o no contenido.
     */
    boolean contienePrograma(String ruta) {
        File check = new File(ruta);
        String contenido[] = check.list();
        int i;
        for (i = 0;i < contenido.length;++i) {
            File f2 = new File(ruta+File.separator+contenido[i]);
            if (f2.isDirectory()) {
                if (contienePrograma(ruta+File.separator+contenido[i]))
                    return true;
            }
            else if (f2.canRead() &&
                    (contenido[i].endsWith(".java") ||
                    contenido[i].endsWith(".class") ||
                    contenido[i].endsWith(".jar")))
                return true;
        }
        return false;
    }
    
    /**
     * Cargar subelementos.
     * @param config Configuración.
     * @param nhebras Número de hebras a utilizar.
     * @.post Crea los objetos SFichero y los carga.
     * @.post Crea los objetos SPrograma correspondientes a partir de la batería.
     * @.post Además llama al método carga de cada uno de esos SPrograma.
     * @exception JSimilException Error leyendo archivos.
     * @exception JSimilException No existen programas en esa ruta.
     */
   void carga(Configuration config,int nhebras) throws JSimilException {
        programas = new ArrayList<Program>();
        File f = new File(ruta);
        String contenido[] = f.list();
        if (contenido == null) {
            throw new JSimilException(
                      ExceptionType.ERROR_LEYENDO_ARCHIVOS,
                           "Error leyendo archivos.");
        }
        ParallelProgramBatteryLoader l = new ParallelProgramBatteryLoader(contenido,ruta,this,config);
        programas = l.getProgramas(nhebras);
        if (programas.size() == 0)
            throw new JSimilException(
                      ExceptionType.NO_PROGRAMAS_A_COMPARAR,
                           "No existen programas legibles en esa ruta.");
        event(EventType.BATERIA_CARGADA,"Batería cargada.");
    }
    
    /**
     * Devolver lista de programas.
     * @return Lista de programas incluidos en la batería.
     */
    List<Program> getProgramas() {
      return programas;    
    }
    
    /**
     * Llevar a cabo el procesamiento.
     * @param perfil Perfil a utilizar.
     * @param nhebras Número de hebras a utilizar.
     * @.post Resultados obtenidos y devueltos.
     * @exception JSimilException Sólo existe un programa a comparar.
     */
    ProgramComparisonList procesa(MatchingProfile perfil,int nhebras) throws JSimilException {
        if (programas.size() == 1 && perfil.getValorS("ReflexiveMATCH") <= 0.5)
            throw new JSimilException(
            ExceptionType.SOLO_UN_PROGRAMA_A_COMPARAR,
                   "Sólo existe un programa a comparar.");
        perfil.cargaat();
        ParallelProgramBatteryProcessor p = new ParallelProgramBatteryProcessor(programas,this,perfil);
        ProgramComparisonList ret = p.getResultados(nhebras);
        return ret;
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
     * Asignar una ruta a la batería.
     * @param ruta Ruta a asignar.
     * @.post Ruta asignada a la instancia.
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
        if (!this.ruta.endsWith(File.separator))
            this.ruta = this.ruta + File.separator;
    }
    
    /**
     * Obtener la ruta asignada a la batería.
     * @return Ruta asignada.
     */
    public String getRuta() {
        return ruta;
    }
    
    /**
     * Validar la batería de programas.
     * @exception JSimilException La ruta no es válida o accesible.
     * @exception JSimilException No existen programas en esa ruta.
     */
    public void validate() throws JSimilException {
        if (!cargada()) {
            File f = new File(ruta);
            if (!f.exists()) {
                throw new JSimilException(
                          ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                               "Ruta no válida o inaccesible.");
            }
            if (!f.isDirectory()) {
                throw new JSimilException(
                          ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                               "Ruta no válida o inaccesible.");
            }
            String contenido[] = f.list();
            if (contenido == null) {
                throw new JSimilException(
                          ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                               "Ruta no válida o inaccesible.");
            }

            int i;
            int progs = 0;
            for (i = 0;i < contenido.length;++i) {
                File f2 = new File(ruta+File.separator+contenido[i]);
                if (f2.isDirectory())
                    if (contienePrograma(ruta+File.separator+contenido[i]))
                        ++progs;                
            }
            if (progs == 0)
                throw new JSimilException(
                          ExceptionType.NO_PROGRAMAS_A_COMPARAR,
                               "No existen programas legibles en esa ruta.");
        }
    }
    
    /**
     * Cargar la configuración de la batería desde un fichero.
     * @param ruta Ruta desde la que leer la batería.
     * @.post Configuración cargada desde un fichero.
     * @exception JSimilException La ruta no es válida o accesible.
     * @exception JSimilException Formato incorrecto.
     */
    public void load(String ruta) throws JSimilException {
        FileInputStream fis;
        ObjectInputStream in;
        String linea;
        int estado;
        try {
            fis = new FileInputStream(ruta);
        }
        catch (FileNotFoundException e) {
            fis = null;
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                               "Ruta no válida o inaccesible.");    
        }
        BufferedReader ds = null;
        
        try {
            ds = new BufferedReader(new InputStreamReader(fis));
            linea = ds.readLine();
            linea.trim();
            if (linea.equals("JSimil ProgramSet File"))
                estado = 1;
            else
                estado = 2;
        } catch (IOException e) {
            estado = 2;
        }
        try {
            if (estado == 1) {
                while ((linea = ds.readLine()) != null) {
                    linea.trim();
                    if (linea.startsWith("BatteryPath")) {
                        this.ruta = "";
                        if (linea.startsWith("BatteryPath ")) {
                            this.ruta =
                                       linea.substring("BatteryPath ".length());
                            if (!this.ruta.endsWith(File.separator)) {
                                this.ruta = this.ruta + File.separator;
                            }
                            if (!(new File(this.ruta)).isAbsolute()) {
                                this.ruta = (new File(ruta)).getAbsoluteFile()
                                        .getParent()+File.separator+this.ruta;
                            }
                        }
                    }             
                }
            }
            else if (estado == 2) {
                fis.close();
                fis = new FileInputStream(ruta);
                byte basura[] = new byte[24];
                fis.read(basura,0,24);
                in = new ObjectInputStream(fis);
                ProgramBattery a = (ProgramBattery)in.readObject();
                this.ruta = new String(a.getRuta());
                programas = new ArrayList<Program>(a.getProgramas());
                int i;
                for (i = 0;i < programas.size();++i)
                    programas.get(i).setBateria(this);
            }
            fis.close();
        } catch (Exception e) {
            throw new JSimilException(ExceptionType.FORMATO_INCORRECTO,
                "Formato incorrecto.");
        }
        

    }
    
    /**
     * Guardar la configuración de la batería a un fichero.
     * @param ruta Ruta a la que escribir la batería.
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
            out.write("JSimil ProgramSet File");
            out.newLine();
            out.newLine();
            String eruta = this.ruta;
            String padre = new File(ruta).getParent();
            if (padre != null) {
                if (eruta.startsWith(padre))
                    eruta = eruta.substring((padre+File.separator).length());
            }
            out.write("BatteryPath "+eruta);            
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
     * Exportar la la batería a un fichero.
     * @param ruta Ruta a la que escribir la batería.
     * @.post Batería escrita a un fichero.
     * @exception JSimilException La ruta no es válida o accesible.
     * @exception JSimilException Error escribiendo el fichero.
     */
    public void dump(String ruta) throws JSimilException {
        FileOutputStream fos;
        ObjectOutputStream out;
        
        if (ruta == null) {
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                                 "Ruta no válida o inaccesible.");    
        }
        else if (ruta.equals("")) {
            throw new JSimilException(ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE,
                                 "Ruta no válida o inaccesible.");                  
        }
        
        try {
            fos = new FileOutputStream(ruta);
        }
        catch (IOException e) {
            throw new JSimilException(ExceptionType.ERROR_ESCRIBIENDO_FICHERO,
                               "Error abriendo fichero para escribir.");    
        }
                
        try {
            BufferedWriter out1 = new BufferedWriter(
                    new OutputStreamWriter(fos));
            out1.write("JSimil ProgramSet Dump--");
            out1.flush();

            out = new ObjectOutputStream(fos);
            out.writeObject(this);
               
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
     * Obtiene una lista de programas y estados.
     * @return Lista de listas (programa->estado).
     */
    public List<List<Object>> getEstados() {
        List<List<Object>> ret = new ArrayList<List<Object>>();
        int i;
        List<Object> a;
        for (i = 0;i < getProgramas().size();++i) {
            a = new ArrayList<Object>();            
            a.add(getProgramas().get(i).getNombre());
            a.add(new Boolean(getProgramas().get(i).getEstado()));
            ret.add(a);
        }
        return ret;
    }
    
    /**
     * Cambia el estado de un programa.
     * @param prog Programa a cambiar el estado.
     * @param val Nuevo estado del programa.
     * @.post Estado del programa cambiado.
     * @exception JSimilException No existe ese programa en esta batería.
     */
    public void setEstado(String prog,boolean val) throws JSimilException {
        int i;
        for (i = 0;i < getProgramas().size();++i) {
            if (getProgramas().get(i).getNombre().equals(prog)) {
                getProgramas().get(i).setEstado(val);
                return;
            }
        }
        throw new JSimilException(ExceptionType.PROGRAMA_NO_ENCONTRADO_EN_BATERIA,
                "No existe ese programa en esta batería");
    }
    
    /**
     * Informa sobre si esta o no cargada.
     * @return true si esta cargada, false si no.
     */
    public boolean cargada() {
        return !programas.isEmpty();
    }
    
}
