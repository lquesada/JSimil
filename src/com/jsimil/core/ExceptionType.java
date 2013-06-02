/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

/**
 * Tipos de excepciones.
 * @author elezeta
 */
public enum ExceptionType {
    
  /**
   * Excepción NO_PERFIL_ASIGNADO
   */
  NO_PERFIL_ASIGNADO,
      
  /**
   * Excepción NO_CONFIG_ASIGNADA
   */
  NO_CONFIG_ASIGNADA,
      
  /**
   * Excepción NO_BATERIA_ASIGNADA
   */
  NO_BATERIA_ASIGNADA,
      
  /**
   * Excepción ERROR_LEYENDO_ARCHIVOS
   */
  ERROR_LEYENDO_ARCHIVOS,
  
    /**
   * Excepción RUTA_NO_VALIDA_O_INACCESIBLE
   */
  RUTA_NO_VALIDA_O_INACCESIBLE,

  /**
   * Excepción NO_PROGRAMAS_A_COMPARAR
   */
  NO_PROGRAMAS_A_COMPARAR,
      
  /**
   * Excepción SOLO_UN_PROGRAMA_A_COMPARAR
   */
  SOLO_UN_PROGRAMA_A_COMPARAR,
      
  /**
   * Excepción FORMATO_INCORRECTO
   */
  FORMATO_INCORRECTO,
      
  /**
   * Excepción ERROR_ESCRIBIENDO_FICHERO
   */
  ERROR_ESCRIBIENDO_FICHERO,
      
  /**
   * Excepción RUTA_DEL_COMPILADOR_NO_VALIDA_O_INACCESIBLE
   */
  RUTA_DEL_COMPILADOR_NO_VALIDA_O_INACCESIBLE,
      
  /**
   * Excepción RUTA_DEL_DESENSAMBLADOR_NO_VALIDA_O_INACCESIBLE
   */
  RUTA_DEL_DESENSAMBLADOR_NO_VALIDA_O_INACCESIBLE,
      
  /**
   * Excepción COMPILADOR_NO_EJECUTABLE
   */
  COMPILADOR_NO_EJECUTABLE,
      
  /**
   * Excepción DESENSAMBLADOR_NO_EJECUTABLE
   */
  DESENSAMBLADOR_NO_EJECUTABLE,
      
  /**
   * Excepción ERROR_LANZANDO_COMPILADOR_PARA_VERSION
   */
  ERROR_LANZANDO_COMPILADOR_PARA_VERSION,
      
  /**
   * Excepción ATRIBUTO_INEXISTENTE
   */
  ATRIBUTO_INEXISTENTE,

  /**
   * Excepción NO_EXISTE_COMPARACION_INDICADA
   */
  NO_EXISTE_COMPARACION_INDICADA,
      
  /**
   * Excepción FICHEROS_DE_PROGRAMA_ILEGIBLES
   */
  FICHEROS_DE_PROGRAMA_ILEGIBLES,
      
  /**
   * Excepción NO_BATERIA_CARGADA
   */
  NO_BATERIA_CARGADA,
      
  /**
   * Excepción PROGRAMA_NO_ENCONTRADO_EN_BATERIA
   */
  PROGRAMA_NO_ENCONTRADO_EN_BATERIA,

  /**
   * Excepción ERROR_EN_PERFIL
   */
  ERROR_EN_PERFIL,

  /**
   * Excepción IMPOSIBLE_AUTOCONFIGURAR
   */
  IMPOSIBLE_AUTOCONFIGURAR,
          
  /**
   * Excepción ERROR_COPIANDO_CONTENIDOS
   */
  ERROR_COPIANDO_CONTENIDOS,
          
  /**
   * Excepción ABORTANDO_POR_ERROR
   */
  ABORTANDO_POR_ERROR

}
