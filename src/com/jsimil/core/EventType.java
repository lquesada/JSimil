/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;

/**
 * Tipos de eventos.
 * @author elezeta
 */
public enum EventType {
  
  /**
   * Evento DEBUG
   */
  DEBUG,
  
  /**
   * Evento ERROR_ENTRADA_SALIDA_DESENSAMBLADOR
   */
  ERROR_ENTRADA_SALIDA_DESENSAMBLADOR,
  
  /**
   * Evento ERROR_CLASS_VACIO_O_NO_VALIDO
   */
  ERROR_CLASS_VACIO_O_NO_VALIDO,
  
  /**
   * Evento ERROR_LEYENDO_FICHERO_JAVA
   */
  ERROR_LEYENDO_FICHERO_JAVA,
  
  /**
   * Evento ERROR_LEYENDO_FICHERO_JAVAP
   */
  ERROR_LEYENDO_FICHERO_JAVAP,
  
  /**
   * Evento ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA
   */
  ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA,
  
  /**
   * Evento ERROR_COPIANDO_FICHERO_CLASS
   */
  ERROR_COPIANDO_FICHERO_CLASS,
  
  /**
   * Evento ERROR_COPIANDO_FICHERO_JAVA
   */
  ERROR_COPIANDO_FICHERO_JAVA,
  
  /**
   * Evento INTERRUPCION_COMPILANDO
   */  
  INTERRUPCION_COMPILANDO,
  
  /**
   * Evento EXPLORADO_APARTADO_Y_COMPILADO
   */
  EXPLORADO_APARTADO_Y_COMPILADO,  
  
  /**
   * Evento ERROR_COMPILANDO
   */
  ERROR_COMPILANDO,

  /**
   * Evento ERROR_COMPILANDO_MSG
   */
  ERROR_COMPILANDO_MSG,
  
  /**
   * Evento ERROR_DESENSAMBLANDO
   */
  ERROR_DESENSAMBLANDO,
    
  /**
   * Evento BATERIA_CARGADA
   */
  BATERIA_CARGADA,
  
  /**
   * Evento INTERRUPCION_DESENSAMBLANDO
   */
  INTERRUPCION_DESENSAMBLANDO,  
  
  /**
   * Evento FALTA_CODIGO_FUENTE
   */
  FALTA_CODIGO_FUENTE,
  
  /**
   * Evento NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL
   */  
  NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL,
  
  /**
   * Evento DESENSAMBLADO_Y_CARGADO
   */
  DESENSAMBLADO_Y_CARGADO,
  
  /**
   * Evento ENCONTRADA_CLASE_FUENTE
   */
  ENCONTRADA_CLASE_FUENTE,
  
  /**
   * Evento ENCONTRADA_CLASE_COMPILADA
   */
  ENCONTRADA_CLASE_COMPILADA,
  
  /**
   * Evento DESENSAMBLADO
   */
  DESENSAMBLADO,
  
  /**
   * Evento CARGADO
   */
  CARGADO,
  
  /**
   * Evento COMPILADO
   */
  COMPILADO,
          
  /**
   * Evento EMPAREJADAS_CLASES
   */
  EMPAREJADAS_CLASES,
                    
  /**
   * Evento EMPAREJADOS_METODOS
   */
  EMPAREJADOS_METODOS,
                    
  /**
   * Evento EMPAREJADOS_PROGRAMAS
   */
  EMPAREJADOS_PROGRAMAS,

  /**
   * Evento PARAMETRO_ESPERABA_ENTRE_CERO_Y_UNO
   */
  PARAMETRO_ESPERABA_ENTRE_CERO_Y_UNO,
      
  /**
   * Evento PARAMETRO_ESPERABA_MAYOR_CERO
   */
  PARAMETRO_ESPERABA_MAYOR_CERO,
      
  /**
   * Evento PARAMETRO_TIENE_VALOR_NO_VALIDO
   */
  PARAMETRO_TIENE_VALOR_NO_VALIDO,
      
  /**
   * Evento PARAMETRO_ESPERABA_CERO_O_UNO
   */
  PARAMETRO_ESPERABA_CERO_O_UNO,
          
  /** 
   * Evento ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES
   */
  ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES,

  /** 
   * Evento ERROR_COPIANDO_CONTENIDOS
   */
  ERROR_COPIANDO_CONTENIDOS,

  /** 
   * Evento ERROR_ABORTANDO_POR_ERROR
   */
  ERROR_ABORTANDO_POR_ERROR

}
