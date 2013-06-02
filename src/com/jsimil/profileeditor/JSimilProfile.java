/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.profileeditor;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import com.jsimil.core.JSimil;
import com.jsimil.languages.Language;
import com.jsimil.languages.NoSuchLanguageException;

/**
 * Clase principal de la aplicación.
 * @author elezeta
 */
public class JSimilProfile extends SingleFrameApplication {
    
    /**
     * Argumentos...
     */
    static String[] argsi;
    
    /**
     * Mostrar ayuda.
     * @param lang Idioma de la ayuda.
     * @.post Ayuda mostrada.
     */
    public static void showHelp(Language lang) {
        System.err.println(lang.getFrase(289,"JSimilProfile"));
        System.err.println("");
        System.err.println(lang.getFrase(5));
        //help
        System.err.println(lang.getFrase(6));
        System.err.println("");
        //lang
        System.err.println(lang.getFrase(7));
        System.err.println(lang.getFrase(8));
        System.err.println("");
        System.err.println(lang.getFrase(20));
        System.err.println("");
    }
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        int i,j;
        String langg = System.getProperty("user.language");
        String lngdef = null;
        for (i = 0;i < Language.getLangsNum();++i) {
            if (Language.getLangsCod(i).equals(langg)) {
                lngdef = langg;
                if (lngdef.equals("es"))
                    lngdef = "est";
            }
        }
        if (lngdef == null)
            lngdef = "en";
        String lng = lngdef;
        Language lang = null;
        try {
          lang = new Language(lng);
        } catch (NoSuchLanguageException e) {
            System.err.println("Abortando: No existe idioma por defecto.");
            System.exit(1);
        }
        boolean ok = true;
        boolean showhelp = false;
        boolean showlangs = false;
        boolean quit = false;
        boolean hecho = false;
        String profileload = null;
        for (i = 0;i < argsi.length;++i) {
            ok = false;
            if (argsi[i].equals("-h") || argsi[i].equals("--help")) {
                showhelp = true;
                ok = true;
            }
            else if (argsi[i].equals("--langlist")) {
                showlangs = true;
                ok = true;
            }
            else if (i+1 < argsi.length) {
                if (argsi[i].startsWith("-") && argsi[i+1].startsWith("-")) {
                    System.err.println(lang.getFrase(40,argsi[i+1]));
                    quit = true;
                }
                if (argsi[i].equals("-l") || argsi[i].equals("--lang")) {
                    lng = argsi[i+1];
                    try {
                      lang = new Language(lng);
                    } catch (NoSuchLanguageException e) {
                        System.err.println(lang.getFrase(39,lng));
                    }
                    i++;
                    ok = true;
                }
                else if (argsi[i].equals("-p") ||
                        argsi[i].equals("--profile-load")) {
                    if (profileload != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-p/--profile-load"));
                        quit = true;
                    }
                    profileload = argsi[i+1];
                    i++;
                    ok = true;
                }
            }
            if (!ok) {
                if (i+1 < argsi.length)
                    System.err.println(lang.getFrase(3,argsi[i]));
                else
                    System.err.println(lang.getFrase(41,argsi[i]));
                quit = true;
            }
        }            
        JSimil js = new JSimil();        

        if (quit)
            System.exit(1);   

        if (quit)
            System.exit(1);

        System.err.println(""); 
        System.err.println(lang.getFrase(0,js.getVersion(),
                                          js.getTDes(),
                                          js.getWeb()));
        System.err.println(lang.getFrase(1));
        System.err.println(""); 
        String[] autores = js.getAutores();
        for (i = 0;i < autores.length;++i) {
            System.err.println(lang.getFrase(2,autores[i])); 
        }
        System.err.println(""); 
        System.err.println(lang.getFrase(290));
        System.err.println(""); 


        if (showlangs) {
            System.err.println(lang.getFrase(65));
            System.err.println("");
            for (j = 0;j < Language.getLangsNum();++j) {
                if (Language.getLangsCod(j).equals(lngdef))
                    System.err.println(lang.getFrase(64,
                                          Language.getLangsCod(j),
                                          Language.getLangsName(j)));
                else
                    System.err.println(lang.getFrase(63,
                                          Language.getLangsCod(j),
                                          Language.getLangsName(j)));
            }
            hecho = true;
            System.err.println("");
        }
        
        if (showhelp) {
            hecho = true;
            showHelp(lang);            
        }
        if (!hecho)
            show(new JSimilProfileView(this,lang,profileload));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of JSimilProfile
     */
    public static JSimilProfile getApplication() {
        return Application.getInstance(JSimilProfile.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        argsi = args;
        launch(JSimilProfile.class, args);
    }
}
