/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.jsimilcli;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import com.jsimil.core.CodeElement;
import com.jsimil.core.DefaultJSimilProfileFactory;
import com.jsimil.core.JSimil;
import com.jsimil.core.ProgramBattery;
import com.jsimil.core.Matching;
import com.jsimil.core.Configuration;
import com.jsimil.core.JSimilException;
import com.jsimil.core.MatchingProfile;
import com.jsimil.core.ProgramComparison;
import com.jsimil.core.ProgramComparisonList;
import com.jsimil.core.ExceptionType;
import com.jsimil.export.DiffExport;
import com.jsimil.export.HTMLExport;
import com.jsimil.export.TXTExport;
import com.jsimil.export.XMLExport;
import com.jsimil.languages.Language;
import com.jsimil.languages.NoSuchLanguageException;


/**
 * Interfaz de linea de comandos de JSimil.
 * @author elezeta
 */
abstract public class JSimilCLI {

    /**
     * Mostrar perfil.
     * @param perfil Perfil a mostrar.
     */
    static void showProfile(MatchingProfile perfil) {
        try {
        System.out.println("ProgMatchMIN "+perfil.getValor("ProgMatchMIN"));
        System.out.println("ProgMatchMAX "+perfil.getValor("ProgMatchMAX"));
        System.out.println("ProgMatchERROR "+perfil.getValor("ProgMatchERROR"));
        System.out.println("ProgMatchLIMIT "+perfil.getValor("ProgMatchLIMIT"));
        System.out.println("ReflexiveMATCH "+perfil.getValor("ReflexiveMATCH"));
        System.out.println("DifferenceSEARCH "+
                perfil.getValor("DifferenceSEARCH"));
        System.out.println("ProgMatchOPTIMISM "+
                perfil.getValor("DifferenceSEARCH"));
        System.out.println("ClassMatchOPTIMISM "+
                perfil.getValor("ClassMatchOPTIMISM"));
        System.out.println("MethodMatchOPTIMISM "+
                perfil.getValor("MethodMatchOPTIMISM"));
        System.out.println("BlockAllowMULTIMATCH "+
                perfil.getValor("BlockAllowMULTIMATCH"));
        System.out.println("MethodAllowMULTIMATCH "+
                perfil.getValor("MethodAllowMULTIMATCH"));
        System.out.println("ClassAllowMULTIMATCH "+
                perfil.getValor("ClassAllowMULTIMATCH"));
        System.out.println("ClassSAMENAMEMATCH "+
                perfil.getValor("ClassSAMENAMEMATCH"));
        System.out.println("MethodSAMENAMEMATCH "+
                perfil.getValor("MethodSAMENAMEMATCH"));
        System.out.println("MethodOfClassSAMENAMEMATCH "+
                perfil.getValor("MethodOfClassSAMENAMEMATCH"));
        System.out.println("ProgMatchDIFFERENCE "+
                perfil.getValor("ProgMatchDIFFERENCE"));
        } catch (JSimilException e) {
            //Ok.
        }
    } 
    
    /**
     * Mostrar ayuda.
     * @param lang Idioma de la ayuda.
     */
    static void showHelp(Language lang) {
        System.err.println(lang.getFrase(4,"JSimilCLI"));
        System.err.println("");
        System.err.println(lang.getFrase(5));
        //help
        System.err.println(lang.getFrase(6));
        System.err.println("");
        //lang
        System.err.println(lang.getFrase(7));
        System.err.println(lang.getFrase(8));
        System.err.println("");
        //verbose
        System.err.println(lang.getFrase(9));
        System.err.println(lang.getFrase(264));
        System.err.println(lang.getFrase(430));
        System.err.println("");
        //process
        System.err.println(lang.getFrase(33));
        System.err.println(lang.getFrase(10));
        System.err.println(lang.getFrase(105));
        System.err.println(lang.getFrase(36));
        System.err.println(lang.getFrase(245));
        System.err.println(lang.getFrase(249));
        System.err.println("");
        //config
        System.err.println(lang.getFrase(12));
        System.err.println(lang.getFrase(19));
        System.err.println(lang.getFrase(23));
        System.err.println(lang.getFrase(24));
        System.err.println(lang.getFrase(25));
        System.err.println("");
        //profile
        System.err.println(lang.getFrase(11));
        System.err.println(lang.getFrase(14));
        System.err.println(lang.getFrase(45));
        System.err.println(lang.getFrase(20));
        System.err.println(lang.getFrase(22));
        System.err.println(lang.getFrase(46));
        System.err.println(lang.getFrase(21));
        System.err.println("");
        //battery
        System.err.println(lang.getFrase(13));
        System.err.println(lang.getFrase(15));
        System.err.println(lang.getFrase(18));
        System.err.println(lang.getFrase(16));
        System.err.println(lang.getFrase(17));
        System.err.println("");
        //enables
        System.err.println(lang.getFrase(31));
        System.err.println(lang.getFrase(29));
        System.err.println(lang.getFrase(27));
        System.err.println("");        
        //output file
        System.err.println(lang.getFrase(26));
        System.err.println(lang.getFrase(185));
        System.err.println(lang.getFrase(186));
        System.err.println(lang.getFrase(28));
        System.err.println(lang.getFrase(35));
        System.err.println(lang.getFrase(145));
        System.err.println(lang.getFrase(146));
        System.err.println(lang.getFrase(279));
        System.err.println(lang.getFrase(280));
        System.err.println("");
        //output console
        System.err.println(lang.getFrase(30));
        System.err.println(lang.getFrase(32));
        System.err.println("");
        //Aviso
        System.err.println(lang.getFrase(34));
        System.err.println("");
        System.err.println(lang.getFrase(61));
        System.err.println("");
        System.err.println("    JSimilCLI -d -a -t dir");
        System.err.println(lang.getFrase(62));
        System.err.println("");
    }

    /**
     * Comparador por similitud descendente.
     */
    static final Comparator<ProgramComparison> compsimil =
      new Comparator<ProgramComparison>() {
        public int compare(ProgramComparison o1, ProgramComparison o2) {
            double val = o2.getSimilitud()-o1.getSimilitud();
            if (val<0)
                return -1;
            else if (val>0)
                return 1;
            else
                return 0;
        }
    };

    /**
     * Comparador por similitud descendente y tamaño.
     */
    static final Comparator<ProgramComparison> compsimilsize =
      new Comparator<ProgramComparison>() {
        public int compare(ProgramComparison o1, ProgramComparison o2) {
            int diff1 = o2.getTama1()-o2.getTama2();
            if (diff1 < 0)
                diff1 = -diff1;
            int diff2 = o1.getTama1()-o1.getTama2();
            if (diff2 < 0)
                diff2 = -diff2;
            double val = ((o2.getSimilitud()*(o2.getTama1()+o2.getTama2()
                           -diff1))-
                         (o1.getSimilitud()*(o1.getTama1()+o1.getTama2()
                         -diff2)));
            if (val<0)
                return -1;
            else if (val>0)
                return 1;
            else
                return 0;
        }
    };
    
    /**
     * Comparador por similitud ascendente.
     */
    static final Comparator<ProgramComparison> compdiff =
      new Comparator<ProgramComparison>() {
        public int compare(ProgramComparison o1, ProgramComparison o2) {
            double val = o2.getSimilitud()-o1.getSimilitud();
            if (val<0)
                return 1;
            else if (val>0)
                return -1;
            else
                return 0;
        }
    };

    /**
     * Comparador por similitud ascendente y tamaño.
     */
    static final Comparator<ProgramComparison> compdiffsize =
      new Comparator<ProgramComparison>() {
        public int compare(ProgramComparison o1, ProgramComparison o2) {
            int diff1 = o2.getTama1()-o2.getTama2();
            if (diff1 < 0)
                diff1 = -diff1;
            int diff2 = o1.getTama1()-o1.getTama2();
            if (diff2 < 0)
                diff2 = -diff2;
            double val = ((o2.getSimilitud()*(o2.getTama1()+o2.getTama2()
                           -diff1))-
                         (o1.getSimilitud()*(o1.getTama1()+o1.getTama2()
                         -diff2)));
            if (val<0)
                return 1;
            else if (val>0)
                return -1;
            else
                return 0;
        }
    };
    
    /**
     * Main.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Interpreto los parámetros.
        int i,j,k,n;
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        ds.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("0.00000",ds);

        String langg = System.getProperty("user.language");
        String lngdef = null;
        for (i = 0;i < Language.getLangsNum();++i) {
            if (Language.getLangsCod(i).equals(langg)) {
                lngdef = langg;
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
        boolean ok;
        boolean showhelp = false;
        boolean listener = false;
        boolean process = true;
        boolean dontautoconfig = false;
        boolean autoconfig = false;
        boolean outputstandard = false;
        boolean resumestandard = false;
        boolean forceload = false;
        boolean forceoverwrite = false;
        boolean outputprofile = false;
        String newconfig = null;
        String newbattery = null;
        String newprofile = null;
        String defrprofile = null;
        String defprofile = null;
        String batteryload = null;
        String batterypath = null;
        String batterysave = null;
        String batterydump = null;
        String configload = null;
        String configsave = null;
        String exportdiff = null;
        double diffsimil = -1.0;
        String exporthtml = null;
        String exportrhtml = null;
        String exportrrhtml = null;
        String exporttxt = null;
        String exportresume = null;
        String exportxml = null;
        String exportrxml = null;
        boolean profiledefault = false;
        boolean rprofiledefault = false;
        String profileload = null;
        String profilesave = null;
        boolean quit = false;
        boolean hecho = false;
        boolean hacer = false;
        boolean showlangs = false;
        boolean enableall = false;
        boolean profileerr = false;
        String enableonly = null;
        String disableonly = null;
        boolean usesize = false;
        int limite = -1;
        boolean setlimite = false;
        boolean hideerrors = false;
        int nhebras = -1;
        
        for (i = 0;i < args.length;++i) {
            ok = false;
            if (args[i].equals("-h") || args[i].equals("--help")) {
                showhelp = true;
                ok = true;
            }
            else if (args[i].equals("-o")
                    || args[i].equals("--output-standard")) {
                if (outputstandard == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-o/--output-standard"));
                    quit = true;
                }
                outputstandard = true;
                ok = true;
            }
            else if (args[i].equals("-q")
                    || args[i].equals("--enable-all")) {
                if (enableall == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-q/--enable-all"));
                    quit = true;
                }
                enableall = true;
                ok = true;
            }            else if (args[i].equals("-r")
                    || args[i].equals("--resume-standard")) {
                if (resumestandard == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-r/--resume-standard"));
                    quit = true;
                }
                resumestandard = true;
                ok = true;
            }
            else if (args[i].equals("-y")
                    || args[i].equals("--mind-size")) {
                if (usesize == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-y/--mind-size"));
                    quit = true;
                }
                usesize = true;
                ok = true;
            }
            else if (args[i].equals("-w")
                    || args[i].equals("--force-battery-load")) {
                if (forceload == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-w/--force-battery-load"));
                    quit = true;
                }
                forceload = true;
                ok = true;
            }
            else if (args[i].equals("-f")
                    || args[i].equals("--force-overwrite")) {
                if (forceoverwrite == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-f/--force-overwrite"));
                    quit = true;
                }
                forceoverwrite = true;
                ok = true;
            }
            else if (args[i].equals("-n") || args[i].equals("--dont-process")) {
                if (process == false) {
                    System.err.println(lang.getFrase(38,
                                                   "-n/--dont-process"));
                    quit = true;
                }
                process = false;
                ok = true;
            }
            else if (args[i].equals("--dont-autoconfig")) {
                if (dontautoconfig == true) {
                    System.err.println(lang.getFrase(38,
                                                   "--dont-autoconfig"));
                    quit = true;
                }
                dontautoconfig = true;
                ok = true;
            }
            else if (args[i].equals("-i") ||
                    args[i].equals("--output-profile")) {
                if (outputprofile == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-i/--output-profile"));
                    quit = true;
                }
                outputprofile = true;
                ok = true;
            }
            else if (args[i].equals("-a") || (args[i].equals("--autoconfig"))) {
                if (autoconfig == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-a/--autoconfig"));
                    quit = true;
                }
                autoconfig = true;
                ok = true;
            }
            else if (args[i].equals("--hide-errors")) {
                if (hideerrors == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-hide-errors"));
                    quit = true;
                }
                hideerrors = true;
                ok = true;
            }
            else if (args[i].equals("-v") || args[i].equals("--verbose")) {
                if (listener == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-v/--verbose"));
                    quit = true;
                }
                listener = true;
                ok = true;
            }
            else if (args[i].equals("--rprofile-def")) {
                if (rprofiledefault == true) {
                    System.err.println(lang.getFrase(38,
                                                   "--rprofile-def"));
                    quit = true;
                }
                rprofiledefault = true;
                ok = true;
            }       
            else if (args[i].equals("-d") ||
                    args[i].equals("--profile-def")) {
                if (profiledefault == true) {
                    System.err.println(lang.getFrase(38,
                                                   "-d/--profile-def"));
                    quit = true;
                }
                profiledefault = true;
                ok = true;
            }                               
            else if (args[i].equals("--langlist")) {
                showlangs = true;
                ok = true;
            }
            else if (i+1 < args.length) {
                if (args[i].startsWith("-") && args[i+1].startsWith("-")) {
                    System.err.println(lang.getFrase(40,args[i+1]));
                    quit = true;
                }
                if (args[i].equals("-l") || args[i].equals("--lang")) {
                    lng = args[i+1];
                    try {
                      lang = new Language(lng);
                    } catch (NoSuchLanguageException e) {
                        System.err.println(lang.getFrase(39,lng));
                    }
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--enable-only")) {
                    if (enableonly != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--enable-only"));
                        quit = true;
                    }
                    enableonly = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--disable-only")) {
                    if (disableonly != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--disable-only"));
                        quit = true;
                    }
                    disableonly = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--new-config-to")) {
                    if (newconfig != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--new-config-to"));
                        quit = true;
                    }
                    newconfig = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--new-battery-to")) {
                    if (newbattery != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--new-battery-to"));
                        quit = true;
                    }
                    newbattery = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--new-profile-to")) {
                    if (newprofile != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--new-profile-to"));
                        quit = true;
                    }
                    newprofile = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--def-rprofile-to")) {
                    if (defrprofile != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--def-rprofile-to"));
                        quit = true;
                    }
                    defrprofile = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--def-profile-to")) {
                    if (defprofile != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--def-profile-to"));
                        quit = true;
                    }
                    defprofile = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-p") ||
                        args[i].equals("--profile-load")) {
                    if (profileload != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-p/--profile-load"));
                        quit = true;
                    }
                    profileload = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-b") ||
                        args[i].equals("--battery-load")) {
                    if (batteryload != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-b/--battery-load"));
                        quit = true;
                    }
                    batteryload = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--diff-simil")) {
                    if (diffsimil != -1.0) {
                        System.err.println(lang.getFrase(38,
                                                       "--diff-simil"));
                        quit = true;
                    }
                    try {
                        diffsimil = Double.parseDouble(args[i+1])/100.0;
                        if (diffsimil<0.0)
                            diffsimil = 0.0;
                        else if (diffsimil>1.0)
                            diffsimil = 1.0;
                    } catch (Exception e) {
                        System.err.println(lang.getFrase(286,args[i+1]));
                        diffsimil = -1;
                        quit = true;
                    }
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-z") || args[i].equals("--threads")) {
                    if (nhebras != -1) {
                        System.err.println(lang.getFrase(38,
                                                       "-z/--threads"));
                        quit = true;
                    }
                    try {
                        nhebras = Integer.parseInt(args[i+1]);
                    } catch (Exception e) {
                        System.err.println(lang.getFrase(286,args[i+1]));
                        nhebras = -1;
                        quit = true;
                    }
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-u") ||
                        args[i].equals("--results-limit")) {
                    if (setlimite != false) {
                        System.err.println(lang.getFrase(38,
                                                       "-u/--results-limit"));
                        quit = true;
                    }
                    setlimite = true;
                    try {
                        limite = Integer.parseInt(args[i+1]);
                        if (limite<0)
                            limite = -1;
                    } catch (Exception e) {
                        System.err.println(lang.getFrase(250,args[i+1]));
                        limite = -1;
                        quit = true;
                    }
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-t") ||
                        args[i].equals("--battery-path")) {
                    if (batterypath != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-t/--battery-path"));
                        quit = true;
                    }
                    batterypath = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--battery-save")) {
                    if (batterysave != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--battery-save"));
                        quit = true;
                    }
                    batterysave = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--profile-save")) {
                    if (profilesave != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--profile-save"));
                        quit = true;
                    }
                    profilesave = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-g") ||
                        args[i].equals("--battery-dump")) {
                    if (batterydump != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-g/--battery-dump"));
                        quit = true;
                    }
                    batterydump = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-c") ||
                        args[i].equals("--config-load")) {
                    if (configload != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-c/--config-load"));
                        quit = true;
                    }
                    configload = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--config-save")) {
                    if (configsave != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--config-save"));
                        quit = true;
                    }
                    configsave = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-k") ||
                        args[i].equals("--export-diff")) {
                    if (exportdiff != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-k/--export-diff"));
                        quit = true;
                    }
                    exportdiff = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-e") ||
                        args[i].equals("--export-html")) {
                    if (exporthtml != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-e/--export-html"));
                        quit = true;
                    }
                    exporthtml = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--export-rhtml")) {
                    if (exportrhtml != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--export-rhtml"));
                        quit = true;
                    }
                    exportrhtml = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("--export-rrhtml")) {
                    if (exportrrhtml != null) {
                        System.err.println(lang.getFrase(38,
                                                       "--export-rrhtml"));
                        quit = true;
                    }
                    exportrrhtml = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-s") ||
                        args[i].equals("--export-txt")) {
                    if (exporttxt != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-s/--export-txt"));
                        quit = true;
                    }
                    exporttxt = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-j") ||
                        args[i].equals("--export-resume")) {
                    if (exportresume != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-j/--export-resume"));
                        quit = true;
                    }
                    exportresume = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-x") ||
                        args[i].equals("--export-xml")) {
                    if (exportxml != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-x/--export-xml"));
                        quit = true;
                    }
                    exportxml = args[i+1];
                    i++;
                    ok = true;
                }
                else if (args[i].equals("-m") ||
                        args[i].equals("--export-rxml")) {
                    if (exportrxml != null) {
                        System.err.println(lang.getFrase(38,
                                                       "-m/--export-rxml"));
                        quit = true;
                    }
                    exportrxml = args[i+1];
                    i++;
                    ok = true;
                }
            }
            if (!ok) {
                if (i+1 < args.length)
                    System.err.println(lang.getFrase(3,args[i]));
                else
                    System.err.println(lang.getFrase(41,args[i]));
                quit = true;
            }
        }
        if (diffsimil == -1.0)
            diffsimil = 1.0;
        if (nhebras == -1)
            nhebras = Runtime.getRuntime().availableProcessors()+2;
        JSimil js = new JSimil();
        js.setNHebras(nhebras);
        
        if (rprofiledefault && profileload != null && profiledefault) {
            System.err.println(lang.getFrase(47));
            quit = true;            
        }
        else if (profiledefault && profileload != null) {
            System.err.println(lang.getFrase(43));
            quit = true;            
        }
        else if (rprofiledefault && profileload != null) {
            System.err.println(lang.getFrase(48));
            quit = true;            
        }
        else if (profiledefault && rprofiledefault) {
            System.err.println(lang.getFrase(49));
            quit = true;            
        }
        
        if (enableall && enableonly != null && disableonly != null) {
            System.err.println(lang.getFrase(147));
            quit = true;
        }
        else if (enableall && enableonly != null) {
            System.err.println(lang.getFrase(148));
            quit = true;
        }
        else if (enableall && disableonly != null) {
            System.err.println(lang.getFrase(149));
            quit = true;
        }
        else if (enableonly != null && disableonly != null) {
            System.err.println(lang.getFrase(150));
            quit = true;
        }
        
        if (batteryload != null && batterypath != null) {
            System.err.println(lang.getFrase(44));
            quit = true;
        }
        if (autoconfig && configload != null) {
            System.err.println(lang.getFrase(42));
            quit = true;
        }

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
        System.err.println(lang.getFrase(107));
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
        
        if (newconfig == null && newbattery == null && newprofile == null &&
                process && !resumestandard && defrprofile == null)
            resumestandard = true;
        if (outputstandard && resumestandard)
            resumestandard = false;
        String a[] = {newbattery,newprofile,newconfig,defprofile,batterysave,
        batterydump,configsave,exporthtml,exporttxt,profilesave,defrprofile,
        exportxml,exportresume,exportrxml,exportrhtml,exportrrhtml,exportdiff};
        for (i = 0;i < a.length;++i) {
            for (j = i+1;j < a.length;++j) {
                if (a[i] != null && a[j] != null) {
                    if (a[i].equals(a[j])) {
                        System.err.println(lang.getFrase(37,a[i]));
                        System.exit(1);
                    }
                }
            }
        }
/*
        System.err.println("showhelp "+showhelp);
        System.err.println("listener "+listener);
        System.err.println("process "+process);
        System.err.println("autoconfig "+autoconfig);
        System.err.println("dontautoconfig "+dontautoconfig);
        System.err.println("outputstandard "+outputstandard);
        System.err.println("resumestandard "+resumestandard);
        System.err.println("profiledefault "+profiledefault);
        System.err.println("rprofiledefault "+rprofiledefault);
        System.err.println("newconfig "+newconfig);
        System.err.println("newbattery "+newbattery);
        System.err.println("newprofile "+newprofile);
        System.err.println("defrprofile "+defrprofile);
        System.err.println("defprofile "+defprofile);
        System.err.println("batteryload "+batteryload);
        System.err.println("batterypath "+batterypath);
        System.err.println("batterysave "+batterysave);
        System.err.println("batterydump "+batterydump);
        System.err.println("configload "+configload);
        System.err.println("configsave "+configsave);
        System.err.println("exporthtml "+exporthtml);
        System.err.println("exportrhtml "+exportrhtml);
        System.err.println("exportrrhtml "+exportrrhtml);
        System.err.println("exportdiff "+exportdiff);
        System.err.println("exporttxt "+exporttxt);
        System.err.println("exportresume "+exportresume);
        System.err.println("exportxml "+exportxml);
        System.err.println("exportrxml "+exportrxml);
        System.err.println("profileload "+profileload);
        System.err.println("profilesave "+profilesave);
*/
        if ((//(configload != null || autoconfig) && 
            //(profileload != null || rprofiledefault || profiledefault) &&
            (batteryload != null || batterypath != null))) {
//            (newconfig != null || newbattery != null || newprofile != null ||
//            defprofile != null || defrprofile != null || batterysave != null ||
//            profilesave != null || configsave != null)) {
            hacer = true;
        }
        if (((configload != null || autoconfig) && configsave != null) || 
            ((profileload != null || rprofiledefault || profiledefault) &&
            profilesave != null)) {
            hacer = true;
        }
        Configuration con = new Configuration();
        MatchingProfile per = new MatchingProfile();
        ProgramBattery bat = new ProgramBattery();
        DefaultJSimilEventListener list;
        if (listener)
            list = new DefaultJSimilEventListener(lang,hideerrors);
        else
            list = null;
        if (newconfig != null || newprofile != null || newbattery != null ||
            defprofile != null || defrprofile != null) {
            System.err.println(lang.getFrase(55)); 
            hecho = true;
            if (newconfig != null) {
                try {
                    con.save(newconfig);
                    System.err.println(lang.getFrase(56,newconfig)); 
                } catch (JSimilException e) {
                    System.err.println(lang.getFrase(50,newconfig)); 
                }
                System.err.println(""); 
            }
            if (newprofile != null) {
                try {
                    per.save(newprofile);
                    System.err.println(lang.getFrase(57,newprofile)); 
                } catch (JSimilException e) {
                    System.err.println(lang.getFrase(51,newprofile)); 
                }
                System.err.println(""); 
            }   
            if (defprofile != null) {
                try {
                    DefaultJSimilProfileFactory.defecto().save(defprofile);
                    System.err.println(lang.getFrase(58,defprofile)); 
                } catch (JSimilException e) {
                    System.err.println(lang.getFrase(52,defprofile)); 
                }
                System.err.println(""); 
            }  
            if (defrprofile != null) {
                try {
                    DefaultJSimilProfileFactory.defectoReflexivo()
                            .save(defrprofile);
                    System.err.println(lang.getFrase(59,defrprofile)); 
                } catch (JSimilException e) {
                    System.err.println(lang.getFrase(53,defrprofile)); 
                }
                System.err.println(""); 
            }                 
            if (newbattery != null) {
                try {
                    bat.save(newbattery);
                    System.err.println(lang.getFrase(60,newbattery)); 
                } catch (JSimilException e) {
                    System.err.println(lang.getFrase(54,newbattery)); 
                }
                System.err.println(""); 
            }
        }

        boolean perfilb = true;
        boolean seguirc = true;
        boolean seguirb = true;
        boolean seguirp = true;
        if (!hacer) {
            seguirc = false;
            seguirb = false;
            seguirp = false;
        }
        boolean hechoconfig = false;
        if (autoconfig) {
            try {
                con.autoConfig();
                System.err.println(lang.getFrase(67));
            } catch (JSimilException e) {
                System.err.println(lang.getFrase(66));
                hechoconfig = true;
            }
            System.err.println("");
        }        
        if (hacer) {
            if (!autoconfig && configload != null) {
                try {
                    con.load(configload);
                    System.err.println(lang.getFrase(68,configload));
                } catch (JSimilException e) {
                    if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(69,configload));
                    else if (e.getTipo() == ExceptionType.FORMATO_INCORRECTO)
                        System.err.println(lang.getFrase(70,configload));
                    if (!dontautoconfig) {
                        System.err.println(lang.getFrase(71));
                        try {
                            con.autoConfig();
                            System.err.println(lang.getFrase(67));
                        } catch (JSimilException er) {
                            System.err.println(lang.getFrase(66));
                        }
                    }
                }
                System.err.println("");
            }
     
            if (profiledefault) {
                per = DefaultJSimilProfileFactory.defecto();
                System.err.println(lang.getFrase(72));
                System.err.println("");
            }
            else if (rprofiledefault) {
                per = DefaultJSimilProfileFactory.defectoReflexivo();
                System.err.println(lang.getFrase(73));
                System.err.println("");
            }
            else if (profileload != null) {
                try {
                    per.load(profileload);
                    System.err.println(lang.getFrase(74,profileload));
                } catch (JSimilException e) {
                    profileerr = true;
                    if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(75,profileload));
                    else if (e.getTipo() == ExceptionType.FORMATO_INCORRECTO)
                        System.err.println(lang.getFrase(76,profileload));
                }
                System.err.println("");
            }
            else {
                perfilb = false;
                System.err.println(lang.getFrase(104));
                System.err.println("");
            }
            if (batterypath != null) {
                bat.setRuta(batterypath);
                System.err.println(lang.getFrase(77,batterypath));
                System.err.println("");
            }
            else if (batteryload != null) {
                try {
                    bat.load(batteryload);
                    System.err.println(lang.getFrase(78,batteryload));
                } catch (JSimilException e) {
                    if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(79,batteryload));
                    else if (e.getTipo() == ExceptionType.FORMATO_INCORRECTO)
                        System.err.println(lang.getFrase(80,batteryload));
                }            
                System.err.println("");
            }
            if (autoconfig || configload!=null) {
                System.err.println("----------------------------");
                try {
                    con.validate();
                    System.err.println(lang.getFrase(81));
                } catch (JSimilException e) {
                    seguirc = false;
                    System.err.println(lang.getFrase(84));
                    System.err.println("- - - - - - - - - - - - - - ");
                    if (e.getTipo() ==
                          ExceptionType.RUTA_DEL_COMPILADOR_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(87));
                    else if (e.getTipo() ==
                            ExceptionType.COMPILADOR_NO_EJECUTABLE)
                        System.err.println(lang.getFrase(88));
                    else if (e.getTipo() ==
                      ExceptionType.RUTA_DEL_DESENSAMBLADOR_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(89));
                    else if (e.getTipo() ==
                            ExceptionType.DESENSAMBLADOR_NO_EJECUTABLE)
                        System.err.println(lang.getFrase(90));
                }
                System.err.println("----------------------------");
                System.err.println("");
            }
            if (perfilb) {
                System.err.println("----------------------------");
                if (profileerr) {
                    System.err.println(lang.getFrase(86));
                    seguirp = false;
                }
                else {
                    per.setListener(new ProfileJSimilEventListener(lang));
                    try {
                        per.validate();
                        System.err.println(lang.getFrase(83));
                    } catch (JSimilException e) {
                        seguirp = false;
                    }
                    per.setListener(null);
                }
                System.err.println("----------------------------");
                System.err.println("");
            }
            else
                seguirp = false;
            if (batteryload != null || batterypath != null) {
                System.err.println("----------------------------");
                try {
                    bat.validate();
                    System.err.println(lang.getFrase(82));
                } catch (JSimilException e) {
                    seguirb = false;
                    System.err.println(lang.getFrase(85));
                    System.err.println("- - - - - - - - - - - - - - ");
                    if (e.getTipo() ==
                            ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(95));
                    else if (e.getTipo() ==
                            ExceptionType.NO_PROGRAMAS_A_COMPARAR)
                        System.err.println(lang.getFrase(96));
                }
                System.err.println("----------------------------");
                System.err.println("");
            }
            else
                seguirb = false;
        }
        js.setConfig(con);
        js.setPerfil(per);
        bat.setListener(list);
        js.setBateria(bat);
        
        if (seguirc && seguirb && (!bat.cargada() || forceload)) {
            hecho = true;
            System.err.println(lang.getFrase(97));
            System.err.println("");
            long startTime;
            long estimatedTime;
            double time;
            try {
                startTime = System.nanoTime();
                js.carga();
                estimatedTime = System.nanoTime() - startTime;
                time = ((double)estimatedTime)/1000000000;
                System.err.println(lang.getFrase(99,df.format(time)));
            } catch (JSimilException e) {
                if (e.getTipo()==ExceptionType.ERROR_LEYENDO_ARCHIVOS)
                    System.err.println(lang.getFrase(98));
                else if (e.getTipo()==ExceptionType.NO_PROGRAMAS_A_COMPARAR)
                    System.err.println(lang.getFrase(96));
                seguirb = false;
            }
            System.err.println("");
        }            
        else if (bat.cargada() && !forceload) {
            System.err.println(lang.getFrase(106));
            System.err.println("");
        }
        boolean repe = false;
        boolean procesado = false;
        String token;
        StringTokenizer tokenizer;
        List as;
        if (seguirb && seguirp && process) {
            if (enableall) {
                System.err.println(lang.getFrase(151));
                as = bat.getEstados();
                for (i = 0;i < as.size();++i) {
                    try {
                        bat.setEstado(((String)((List)as.get(i)).get(0)),true);
                    }
                    catch (JSimilException e) {
                    }
                }
            }
            else if (enableonly != null) {
                System.err.println(lang.getFrase(152));
                as = bat.getEstados();
                for (i = 0;i < as.size();++i) {
                    try {
                        bat.setEstado(((String)((List)as.get(i)).get(0)),false);
                    }
                    catch (JSimilException e) {
                        System.out.println("ERR");
                    }
                }
                tokenizer = new StringTokenizer(enableonly,",");
                while (tokenizer.hasMoreTokens()) {
                    repe = false;
                    token = tokenizer.nextToken();
                    as = bat.getEstados();
                    for (i = 0;i < as.size();++i) {
                        if ((((String)((List)as.get(i)).get(0))).equals(token)
                            && ((Boolean)((List)as.get(i)).get(1))==true) {
                            repe = true;
                            System.err.println(lang.getFrase(160,token));
                        }
                    }
                    if (!repe) {
                        try {
                            bat.setEstado(token,true);
                            System.err.println(lang.getFrase(153,token));
                        }
                        catch (JSimilException e) {
                            System.err.println(lang.getFrase(155,token));
                        }
                    }
                }
            }
            else if (disableonly != null) {
                System.err.println(lang.getFrase(151));
                as = bat.getEstados();
                for (i = 0;i < as.size();++i) {
                    try {
                        bat.setEstado(((String)((List)as.get(i)).get(0)),true);
                    }
                    catch (JSimilException e) {
                    }
                }
                tokenizer = new StringTokenizer(disableonly,",");
                while (tokenizer.hasMoreTokens()) {
                    repe = false;
                    token = tokenizer.nextToken();
                    as = bat.getEstados();
                    for (i = 0;i < as.size();++i) {
                        if ((((String)((List)as.get(i)).get(0))).equals(token)
                            && ((Boolean)((List)as.get(i)).get(1))==false) {
                            repe = true;
                            System.err.println(lang.getFrase(160,token));
                        }
                    }
                    if (!repe) {
                        try {
                            bat.setEstado(token,false);
                            System.err.println(lang.getFrase(154,token));
                        }
                        catch (JSimilException e) {
                            System.err.println(lang.getFrase(155,token));
                        }
                    }
                }
            }
            else {
                System.err.println(lang.getFrase(156));
            }
            System.err.println("");
            System.err.println(lang.getFrase(157));
            as = bat.getEstados();
            boolean estado;
            for (i = 0;i < as.size();++i) {
                estado = (Boolean)((List)as.get(i)).get(1);
                if (estado)
                    System.err.println(lang.getFrase(158,((String)((List)as.get(i)).get(0))));
                else
                    System.err.println(lang.getFrase(159,((String)((List)as.get(i)).get(0))));
            }
            System.err.println("");
            hecho = true;
            System.err.println(lang.getFrase(101));
            long startTime;
            long estimatedTime;
            double time;
            try {
                startTime = System.nanoTime();
                js.procesa();
                estimatedTime = System.nanoTime() - startTime;
                procesado = true;
                time = ((double)estimatedTime)/1000000000;
                System.err.println(lang.getFrase(102,df.format(time)));
            } catch (JSimilException e) {
                if (e.getTipo()==ExceptionType.SOLO_UN_PROGRAMA_A_COMPARAR) {
                    System.err.println(lang.getFrase(247));
                    System.exit(1);
                }
                else {
                    System.err.println(lang.getFrase(103)+": "+e.getMessage());
                    System.exit(1);
                }
            }
            System.err.println("");
        }
        else {
            if (!hechoconfig) {
                boolean showesto = true;
                if (args.length == 0)
                    showesto = false;
                else if (args.length == 1) {
                    if (args[0].equals("-h") || args[0].equals("--help"))
                        showesto = false;
                }
                else if (args.length == 2) {
                    if (args[0].equals("-l") || args[0].equals("--lang"))
                        showesto = false;
                }
                if (showesto) {
                    System.err.println(lang.getFrase(100));
                    System.err.println("");
                }
            }
        }

        if (configsave != null) {
            try {
                con.save(configsave);
                System.err.println(lang.getFrase(133,configsave));
            } catch (JSimilException e) {
                if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                    System.err.println(lang.getFrase(134,configsave));
                else if (e.getTipo() == ExceptionType.ERROR_ESCRIBIENDO_FICHERO)
                    System.err.println(lang.getFrase(135,configsave));
            }
            System.err.println("");
        }
        if (profilesave != null) {
            try {
                per.save(profilesave);
                System.err.println(lang.getFrase(136,profilesave));
            } catch (JSimilException e) {
                if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                    System.err.println(lang.getFrase(137,profilesave));
                else if (e.getTipo() == ExceptionType.ERROR_ESCRIBIENDO_FICHERO)
                    System.err.println(lang.getFrase(138,profilesave));
            }
            System.err.println("");
        }
        if (batterysave != null) {
            try {
                bat.save(batterysave);
                System.err.println(lang.getFrase(139,batterysave));
            } catch (JSimilException e) {
                if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                    System.err.println(lang.getFrase(140,batterysave));
                else if (e.getTipo() == ExceptionType.ERROR_ESCRIBIENDO_FICHERO)
                    System.err.println(lang.getFrase(141,batterysave));
            }
            System.err.println("");
        }
        if (batterydump != null) {
            if (bat.cargada()) {
                try {
                    bat.dump(batterydump);
                    System.err.println(lang.getFrase(142,batterydump));
                } catch (JSimilException e) {
                    if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                        System.err.println(lang.getFrase(143,batterydump));
                    else if (e.getTipo() == ExceptionType.ERROR_ESCRIBIENDO_FICHERO)
                        System.err.println(lang.getFrase(144,batterydump));
                }
                System.err.println("");
            }
            else {
                System.err.println(lang.getFrase(246,batterydump));
                System.err.println("");
            }
        }
        
        ProgramComparisonList res = js.getResultados();
        if (res == null) {
            procesado = false;
        }
        else if (res.getResultados().size() == 0) {
            System.err.println(lang.getFrase(161));
            System.err.println("");
            procesado = false;
        }
        boolean asc = false;
        try {
            if (per.getValor("DifferenceSEARCH")>0.5)
                asc = true;
        } catch (JSimilException e) {
            //Ok.
        }
        if (procesado) {
            ProgramComparison r;
            for (i = 0;i < res.getResultados().size();i++) {
                r = res.getResultados().get(i);
                r.ordenar(asc,true);
            }
            ArrayList<ProgramComparison> ressort = new ArrayList<ProgramComparison>();
            ressort.addAll(res.getResultados());
            if (!asc) {
                if (usesize)
                    Collections.sort(ressort,compsimilsize);
                else
                    Collections.sort(ressort,compsimil);
            }
            else {
                if (usesize)
                    Collections.sort(ressort,compdiffsize);
                else
                    Collections.sort(ressort,compdiff);
            }
            if (resumestandard) {
                System.err.println(lang.getFrase(162));
                System.out.println("------------------------------------" +
                        "-----");
                int limitei = limite;
                for (i = 0;i < ressort.size()
                        && (limitei == -1 || limitei>0);i++) {
                    if (limitei != -1)
                    limitei--;
                    r = ressort.get(i);
                    System.out.println(r.getNombre1()+" - "+
                                       r.getNombre2()+": "+
                                       df.format(r.getSimilitud()));
                }
                System.out.println("------------------------------------" +
                        "-----");
                if (outputprofile) {
                    System.out.println("#################################" +
                            "########");
                    showProfile(per);
                    System.out.println("#################################" +
                            "########");
                }
                System.err.println("");
            }
            if (outputstandard) {
                double pmin = 0;
                double pmax = 0;
                try {
                    pmin = per.getValor("ProgMatchMIN");
                    pmax = per.getValor("ProgMatchMAX");
                } catch (JSimilException e) {
                    //Ok.
                }
                System.err.println(lang.getFrase(162));
                System.out.println("------------------------------------" +
                        "-----");
                List<Matching> lista;
                Matching cc;
                String cad;
                int limitei = limite;
                for (i = 0;i < ressort.size() &&
                        (limitei == -1 || limitei>0);i++) {
                    if (limitei != -1)
                    limitei--;
                    r = ressort.get(i);
                    lista = r.getCoincidencias();
                    cad = "0P"+lista.size();
                    while (cad.length() < 6)
                        cad = cad+" ";
                    System.out.println(cad+r.getNombre1()+" - "+
                                       r.getNombre2()+": "+
                                       df.format(r.getSimilitud()));
                    if (r.getSimilitud() >= pmin &&
                           r.getSimilitud() <= pmax) {
                        CodeElement ele1;
                        CodeElement ele2;
                        for (j = 0;j < lista.size();++j) {
                            cc = lista.get(j);
                            ele1 = cc.getElementoA();
                            ele2 = cc.getElementoB();
                            if (ele1 != null && ele2 != null) {
                                cad = ""+(cc.getProfundidad()+1);
                                if (ele1.getTipo().equals("SBloque")) {
                                    cad += "B";
                                }
                                else if (ele1.getTipo().equals("SMetodo")) {
                                    cad += "M";
                                }
                                else if (ele1.getTipo().equals("SClase")) {
                                    cad += "C";
                                }
                                n = 0;
                                for (k = j+1;k < lista.size();++k) {
                                    if (cc.getProfundidad()+1==
                                        lista.get(k).getProfundidad())
                                        n++;
                                    else {
                                        k = lista.size();
                                    }
                                }
                                cad += n;
                                while (cad.length() < 6)
                                    cad = cad+" ";
                                for (k = 0;k < cc.getProfundidad()+1;++k)
                                    cad += "  ";
                                System.out.println(cad+
                                        ele1.getNombre()+" ("+
                                        df.format(ele1.getPorcentajePadre())+","+
                                        df.format(ele1.getPorcentajePrograma())+") - "+
                                        ele2.getNombre()+" ("+
                                        df.format(ele2.getPorcentajePadre())+","+
                                        df.format(ele2.getPorcentajePrograma())+"):"+
                                        df.format(cc.getSimilitud()));                            
                            }
                        }
                    } 
                }
                System.out.println("------------------------------------" +
                        "-----");
                if (outputprofile) {
                    System.out.println("#################################" +
                            "########");
                    showProfile(per);
                    System.out.println("#################################" +
                            "########");
                }
                System.err.println("");
            }
            if (exportdiff != null) {
                System.err.println(lang.getFrase(281,exportdiff));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    DiffExport.export(exportdiff,forceoverwrite,
                                    ressort,limite,diffsimil);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(282,exportdiff,
                        df.format(time)));                    
                } catch (IOException e) {
                    
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(283,exportdiff));                    
                    }
                    else if (e.getMessage().equals("exists2")) {
                        System.err.println(lang.getFrase(284,exportdiff));                    
                    }
                    else if (e.getMessage().equals("delete")) {
                        System.err.println(lang.getFrase(284,exportdiff));                    
                    }
                    else
                        System.err.println(lang.getFrase(285,exportdiff));                    
                }
                System.err.println("");
            }
            if (exporthtml != null) {
                System.err.println(lang.getFrase(180,exporthtml));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    HTMLExport.export(exporthtml,forceoverwrite,
                                    outputprofile,ressort,per,lang,0,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(165,exporthtml,
                        df.format(time)));                    
                } catch (IOException e) {
                    
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(175,exporthtml));                    
                    }
                    else if (e.getMessage().equals("exists2")) {
                        System.err.println(lang.getFrase(195,exporthtml));                    
                    }
                    else if (e.getMessage().equals("delete")) {
                        System.err.println(lang.getFrase(195,exporthtml));                    
                    }
                    else
                        System.err.println(lang.getFrase(166,exporthtml));                    
                }
                System.err.println("");
            }
            if (exportrhtml != null) {
                System.err.println(lang.getFrase(187,exportrhtml));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    HTMLExport.export(exportrhtml,forceoverwrite,
                                    outputprofile,ressort,per,lang,1,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(188,exportrhtml,
                        df.format(time)));                    
                } catch (IOException e) {
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(189,exportrhtml));               
                    }
                    else if (e.getMessage().equals("exists2")) {
                        System.err.println(lang.getFrase(196,exportrhtml));                    
                    }
                    else if (e.getMessage().equals("delete")) {
                        System.err.println(lang.getFrase(196,exportrhtml));                    
                    }
                    else
                        System.err.println(lang.getFrase(190,exportrhtml));
                }
                System.err.println("");
            }
            if (exportrrhtml != null) {
                System.err.println(lang.getFrase(191,exportrrhtml));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    HTMLExport.export(exportrrhtml,forceoverwrite,
                                    outputprofile,ressort,per,lang,2,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(192,exportrrhtml,
                        df.format(time)));            
                } catch (IOException e) {
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(193,exportrrhtml));
                    }
                    else if (e.getMessage().equals("exists2")) {
                        System.err.println(lang.getFrase(197,exportrrhtml));                    
                    }
                    else if (e.getMessage().equals("delete")) {
                        System.err.println(lang.getFrase(197,exportrrhtml));                    
                    }
                    else
                        System.err.println(lang.getFrase(194,exportrrhtml));
                }
                System.err.println("");
            }
            if (exporttxt != null) {
                System.err.println(lang.getFrase(181,exporttxt));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    TXTExport.export(exporttxt,forceoverwrite,
                                            outputprofile,ressort,per,
                                            false,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(167,exporttxt,
                            df.format(time)));                    
                } catch (IOException e) {
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(174,exporttxt));                    
                    }
                    else
                        System.err.println(lang.getFrase(168,exporttxt));                    
                }
                System.err.println("");
            }
            if (exportxml != null) {
                System.err.println(lang.getFrase(182,exportxml));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    XMLExport.export(exportxml,forceoverwrite,
                                    outputprofile,ressort,per,false,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(169,exportxml,
                            df.format(time)));                    
                } catch (IOException e) {
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(176,exportxml));                    
                    }
                    else
                        System.err.println(lang.getFrase(170,exportxml));                    
                }
                System.err.println("");
            }
            if (exportrxml != null) {
                System.err.println(lang.getFrase(184,exportrxml));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    XMLExport.export(exportrxml,forceoverwrite,
                                    outputprofile,ressort,per,true,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(177,exportrxml,
                            df.format(time)));                    
                } catch (IOException e) {
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(179,exportrxml));                    
                    }
                    else
                        System.err.println(lang.getFrase(178,exportrxml));                    
                }
                System.err.println("");
            }
            if (exportresume != null) {
                System.err.println(lang.getFrase(183,exportresume));
                long startTime;
                long estimatedTime;
                double time;
                try {
                    startTime = System.nanoTime();
                    TXTExport.export(exportresume,forceoverwrite,
                                            outputprofile,ressort,per,
                                            true,limite);
                    estimatedTime = System.nanoTime() - startTime;
                    time = ((double)estimatedTime)/1000000000;
                    System.err.println(lang.getFrase(171,exportresume,
                            df.format(time)));                                        
                } catch (IOException e) {
                    if (e.getMessage().equals("exists")) {
                        System.err.println(lang.getFrase(173,exportresume));                    
                    }
                    else
                        System.err.println(lang.getFrase(172,exportresume));                    
                }
                System.err.println("");
            }
        }
        if (args.length == 2) {
            if (args[0].equals("-l") || args[0].equals("--lang"))
                showhelp = true;
        }
        if ((!hacer && !hecho && args.length==0) || showhelp) {
            showHelp(lang);
        }          
    }

}
