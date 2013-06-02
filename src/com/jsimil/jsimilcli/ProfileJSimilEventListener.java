/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.jsimilcli;

import com.jsimil.core.JSimilEvent;
import com.jsimil.core.JSimilEventListener;
import com.jsimil.core.EventType;
import com.jsimil.languages.Language;


/**
 * Escuchador de eventos de perfil.
 * @author elezeta
 */
public class ProfileJSimilEventListener implements JSimilEventListener {

    /**
     * Serializable
     */
    private static final long serialVersionUID = 0;

    /**
     * Primera ejecución?
     */
    private boolean first = false;

    /**
     * Idioma
     */
    private Language lang;

    /**
     * Constructor por defecto.
     * @param lang Idioma asociado.
     * @.post Objeto inicializado.
     */
    public ProfileJSimilEventListener(Language lang) {
        this.lang = lang;
    }

    /**
     * Ha ocurrido un evento.
     * @param e Evento de JSimil ocurrido.
     */
    public void sEventOcurred(JSimilEvent e) {
        if (!first) {
            System.err.println(lang.getFrase(86));
            System.err.println("- - - - - - - - - - - - - - ");
           first = true;
        }
        if (e.getTipo() ==
            EventType.PARAMETRO_ESPERABA_CERO_O_UNO)
                System.err.println(lang.getFrase(91,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.PARAMETRO_ESPERABA_ENTRE_CERO_Y_UNO)
                System.err.println(lang.getFrase(92,e.getMensaje()));
        else if (e.getTipo() ==
            EventType.PARAMETRO_TIENE_VALOR_NO_VALIDO)
                System.err.println(lang.getFrase(93,
                        e.getMensaje().replace("MIN","MAX"),
                        e.getMensaje()));
        else if (e.getTipo() ==
            EventType.PARAMETRO_ESPERABA_MAYOR_CERO)
                System.err.println(lang.getFrase(94,e.getMensaje()));
    }
    
}
