/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Cargador paralelizado de batería.
 * @author elezeta
 */
final class ParallelProgramBatteryLoader {

    /**
     * Clase de hebra de carga.
     */
    class LoaderThread extends Thread {

        /**
         * Clase cargadora asociada.
         */
        ParallelProgramBatteryLoader loader;
        
        /**
         * Lista de programas cargados.
         */
        ArrayList<Program> programas;
        
        /**
         * Constructor.
         * @param loader Clase cargadora asociada.
         * @.post Objeto inicializado.
         */
        LoaderThread(ParallelProgramBatteryLoader loader) {
            this.loader = loader;
            programas = new ArrayList<Program>();
        }
        
        /**
         * Lanzar hebra.
         * @.post Quedan cargados en la lista programas los programas.
         */
        @Override
        public void run() {
            int i,id;
            id = 0;
            Program a;
            while ((i = loader.getCuenta()) < loader.contenido.length) {
                File f2 = new File(ruta+File.separator+contenido[i]);
                if (f2.isDirectory())
                    if (bateria.contienePrograma(
                                   ruta+File.separator+contenido[i]))
                    {
                        a = new Program(contenido[i],id,bateria);
                        try {
                            a.carga(config);
                            programas.add(a);
                            ++id;
                        } catch (JSimilException e) {
                            if (e.getTipo()==
                                 ExceptionType.FICHEROS_DE_PROGRAMA_ILEGIBLES)
                                 bateria.event(
                                 EventType.ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES,
                                    contenido[i]);
                            if (e.getTipo()==
                                    ExceptionType.ERROR_COPIANDO_CONTENIDOS)
                              bateria.event(EventType.ERROR_COPIANDO_CONTENIDOS,
                                    contenido[i]);
                            if (e.getTipo()==
                                    ExceptionType.ABORTANDO_POR_ERROR)
                              bateria.event(EventType.ERROR_ABORTANDO_POR_ERROR,
                                    contenido[i]);
                        }
                    }
            }
        }
        
        /**
         * Obtener la lista de programas cargados.
         * @return Lista de programas cargados.
         */
        ArrayList<Program> getProgramas() {
            return programas;
        }
        
    }
    
    /**
     * Lista de rutas a cargar.
     */
    protected String[] contenido;
    
    /**
     * Ruta donde cargar.
     */
    protected String ruta;
    
    /**
     * Batería de programas a cargar.
     */
    protected ProgramBattery bateria;
    
    /**
     * Configuración a utilizar.
     */
    protected Configuration config;
    
    /**
     * Cuenta con acceso sincronizado.
     */
    protected Integer cuenta;
    
    /**
     * Obtener e incrementar cuenta.
     * @return Cuenta.
     * @.post Cuenta incrementada con exclusión mutua.
     */
    synchronized int getCuenta() {
        return cuenta++;
    }
    
    /**
     * Inicializar.
     * @param contenido Lista de programas a cargar.
     * @param ruta Ruta a cargar.
     * @param bateria Batería a cargar.
     * @param config Configuración a utilizar.
     * @.post Objeto inicializado.
     */
    ParallelProgramBatteryLoader(String[] contenido,String ruta,ProgramBattery bateria,
            Configuration config) {              
        this.contenido = contenido;
        this.ruta = ruta;
        this.bateria = bateria;
        this.config = config;
        cuenta = 0;
    }

    /**
     * Realizar la carga y obtener el listado de programas.
     * @param nhebras Número de hebras a utilizar.
     * @return Programas cargados.
     */
    ArrayList<Program> getProgramas(int nhebras) {
        int i;
      ArrayList<Program> programas = new ArrayList<Program>();      
      ArrayList<String> contelist = new ArrayList<String>();
      Object []contenido2;
      for (i = 0;i < contenido.length;i++)
          contelist.add(contenido[i]);
      Collections.sort(contelist);
      contenido2 = contelist.toArray();
      for (i = 0;i < contenido.length;i++)
          contenido[i] = (String) contenido2[i];
      //Lanzo hebras.
      int hi;
      LoaderThread hebras[] = new LoaderThread[nhebras];
      cuenta = 0;
      for (hi = 0;hi < nhebras;hi++) {
          hebras[hi] = new LoaderThread(this);
          hebras[hi].start();
      }
      
      //Cosecho hebras.
      for (hi = 0;hi < nhebras;hi++) {
          try {
              hebras[hi].join();
              programas.addAll(hebras[hi].getProgramas());
          } catch (Exception e) {
              //Ok.
          }
      }
      Collections.sort(programas,
          new Comparator<Program>() {
            public int compare(Program o1, Program o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        }
      );
      return programas;
    }
    
}
