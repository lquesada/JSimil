/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

import java.util.ArrayList;
import java.util.List;


/**
 * Procesador paralelizado de batería.
 * @author elezeta
 */
final class ParallelProgramBatteryProcessor {

    /**
     * Clase de hebra paralelizada para el procesamiento.
     */
    class ProcessThread extends Thread {

        /**
         * Procesador asociado.
         */
        ParallelProgramBatteryProcessor process;
        
        /**
         * Resultados obtenidos.
         */
        ArrayList<ProgramComparison> resultados;
        
        /**
         * Constructor.
         * @param process Procesador asociado.
         * @.post Objeto inicializado.
         */
        ProcessThread(ParallelProgramBatteryProcessor process) {
            this.process = process;
            resultados  = new ArrayList<ProgramComparison>();
        }
        
        /**
         * Lanzar hebra.
         * @.post Programas procesados.
         */
        @Override
        public void run() {
            int i,j;
            boolean reflexcond = perfil.getValorS("ReflexiveMATCH") <= 0.5;
            ProgramComparison res;
            while ((i = process.getCuenta()) < process.programas.size()) {
                if (reflexcond) {
                        if (programas.get(i).getEstado()) {
                            for (j = i+1;j < programas.size();++j) {
                                if (programas.get(j).getEstado()) {
                                    res = programas.get(i).procesa(
                                            programas.get(j),perfil);
                                    resultados.add(res);
                                }
                            }
                        }
                }
                else {
                        if (programas.get(i).getEstado()) {
                            res = programas.get(i).procesa(programas.get(i),
                                    perfil);
                            resultados.add(res);
                        }
                }
            }
        }
        
        /**
         * Obtener resultados.
         * @return Resultados.
         */
        ArrayList<ProgramComparison> getResultados() {
            return resultados;
        }
        
    }
    
    /**
     * Lista de programas a procesar.
     */
    protected List<Program> programas;
    
    /**
     * Batería a procesar.
     */
    protected ProgramBattery bateria;
    
    /**
     * Perfil a utilizar.
     */
    protected MatchingProfile perfil;
    
    /**
     * Cuenta con exclusión mutua.
     */
    protected Integer cuenta;
    
    /**
     * Obtener e incrementar cuenta con exclusión mutua.
     * @return Cuenta actual.
     * @.post Cuenta incrementada.
     */
    synchronized int getCuenta() {
        return cuenta++;
    }
    
    /**
     * Constructor.
     * @param programas Programas a cargar.
     * @param bateria Batería a cargar.
     * @param perfil Perfil a utilizar.
     * @.post Objeto inicializado.
     */
    ParallelProgramBatteryProcessor(List<Program> programas,ProgramBattery bateria,
                    MatchingProfile perfil) {              
        this.programas = programas;
        this.bateria = bateria;
        this.perfil = perfil;
        cuenta = 0;
    }

    /**
     * Procesar y devolver resultados.
     * @param nhebras Número de hebras a utilizar.
     * @return Resultados obtenidos.
     */
    ProgramComparisonList getResultados(int nhebras) {
      List<ProgramComparison> resultados = new ArrayList<ProgramComparison>();      

      //Lanzo hebras.
      int hi;
      ProcessThread hebras[] = new ProcessThread[nhebras];
      cuenta = 0;
      for (hi = 0;hi < nhebras;hi++) {
          hebras[hi] = new ProcessThread(this);
          hebras[hi].start();
      }
      ProgramComparisonList res = new ProgramComparisonList();
      int i;
      for (i = 0;i < programas.size();++i) {
          res.getNombres().add(programas.get(i).getNombre());
      }
      
      //Cosecho hebras.
      for (hi = 0;hi < nhebras;hi++) {
          try {
              hebras[hi].join();
              resultados.addAll(hebras[hi].getResultados());
          } catch (Exception e) {
              //Ok.
          }
      }
      res.resultados.addAll(resultados);
      return res;
    }
    
}