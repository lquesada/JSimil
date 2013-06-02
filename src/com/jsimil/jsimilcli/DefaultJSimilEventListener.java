/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.jsimilcli;

import com.jsimil.core.JSimilEvent;
import com.jsimil.core.JSimilEventListener;
import com.jsimil.core.EventType;
import com.jsimil.languages.Language;


/**
 * Escuchador de eventos de batería.
 * @author elezeta
 */
public class DefaultJSimilEventListener implements JSimilEventListener {

    boolean hideerrors;
    
    /**
     * Constructor por defecto.
     * @param lang Idioma asociado.
     * @param hideerrors Ocultar errores.
     * @.post Objeto inicializado.
     */
    public DefaultJSimilEventListener(Language lang,boolean hideerrors) {
        this.lang = lang;
        this.hideerrors = hideerrors;
    }

    /**
     * Serializable
     */
    private static final long serialVersionUID = 0;

    /**
     * Idioma
     */
    private Language lang;

    /**
     * Ha ocurrido un evento.
     * @param e Evento de JSimil ocurrido.
     */
    public void sEventOcurred(JSimilEvent e) {
        if (e.getTipo() ==
            EventType.DEBUG)
                System.err.println(lang.getFrase(108,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_ENTRADA_SALIDA_DESENSAMBLADOR)
                System.err.println(lang.getFrase(109,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_CLASS_VACIO_O_NO_VALIDO)
                System.err.println(lang.getFrase(110,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_LEYENDO_FICHERO_JAVA)
                System.err.println(lang.getFrase(111,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_LEYENDO_FICHERO_JAVAP)
                System.err.println(lang.getFrase(112,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA)
                System.err.println(lang.getFrase(113,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_COPIANDO_FICHERO_CLASS)
                System.err.println(lang.getFrase(114,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_COPIANDO_FICHERO_JAVA)
                System.err.println(lang.getFrase(115,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.INTERRUPCION_COMPILANDO)
                System.err.println(lang.getFrase(116,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.EXPLORADO_APARTADO_Y_COMPILADO)
                System.err.println(lang.getFrase(117,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_COMPILANDO)
                System.err.println(lang.getFrase(118,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_DESENSAMBLANDO)
                System.err.println(lang.getFrase(119,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.BATERIA_CARGADA)
                System.err.println(lang.getFrase(120));
        else if (e.getTipo() ==
            EventType.INTERRUPCION_DESENSAMBLANDO)
                System.err.println(lang.getFrase(121,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.FALTA_CODIGO_FUENTE)
                System.err.println(lang.getFrase(122,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL)
                System.err.println(lang.getFrase(123,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.DESENSAMBLADO_Y_CARGADO)
                System.err.println(lang.getFrase(124,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ENCONTRADA_CLASE_FUENTE)
                System.err.println(lang.getFrase(125,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ENCONTRADA_CLASE_COMPILADA)
                System.err.println(lang.getFrase(126,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.DESENSAMBLADO)
                System.err.println(lang.getFrase(127,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.CARGADO)
                System.err.println(lang.getFrase(128,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.COMPILADO)
                System.err.println(lang.getFrase(129,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.EMPAREJADAS_CLASES)
                System.err.println(lang.getFrase(130,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.EMPAREJADOS_METODOS)
                System.err.println(lang.getFrase(131,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.EMPAREJADOS_PROGRAMAS)
                System.err.println(lang.getFrase(132,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES)
                System.err.println(lang.getFrase(163,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_COPIANDO_CONTENIDOS)
                System.err.println(lang.getFrase(164,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_ABORTANDO_POR_ERROR)
                System.err.println(lang.getFrase(248,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.ERROR_COMPILANDO_MSG && !hideerrors)
                System.err.println(lang.getFrase(263,e.getMensaje()));

    }

}
