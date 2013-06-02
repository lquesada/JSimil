/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.export;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import com.jsimil.core.CodeElement;
import com.jsimil.core.JSimil;
import com.jsimil.core.Matching;
import com.jsimil.core.JSimilException;
import com.jsimil.core.Location;
import com.jsimil.core.MatchingProfile;
import com.jsimil.core.ProgramComparison;
import com.jsimil.languages.Language;


/**
 * Exportador de HTML.
 * @author elezeta
 */
abstract public class HTMLExport {

    /**
     * Eliminar directorio recursivamente.
     * @param path Directorio a eliminar.
     * @.post Directorio eliminado.
     * @return Si se ha eliminado correctamente o no.
     */
    static private boolean deleteDirectory(File path) {
        if(path.exists()) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; ++i) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }
    
    /**
     * Escribe el icono a un fichero.
     * @param ruta Ruta a escribir el icono.
     * @exception IOException Error escribiendo.
     */
    static private void writeIcono(String ruta) throws IOException {
        FileOutputStream fos = new FileOutputStream(ruta);
        DataOutputStream dos = new DataOutputStream(fos);
        byte data[] = new byte[Resources.icono.length];
        int i;
        for (i = 0;i < Resources.icono.length;++i)
            data[i] = (byte)Resources.icono[i];
        dos.write(data);
        dos.flush();
        dos.close();
    }
    
    /**
     * Escribe el logo a un fichero.
     * @param ruta Ruta a escribir el logo.
     * @exception IOException Error escribiendo.
     */
    static private void writeLogo(String ruta) throws IOException {
        FileOutputStream fos = new FileOutputStream(ruta);
        DataOutputStream dos = new DataOutputStream(fos);
        byte data[] = new byte[Resources.logo.length];
        int i;
        for (i = 0;i < Resources.logo.length;++i)
            data[i] = (byte)Resources.logo[i];
        dos.write(data);
        dos.flush();
        dos.close();
    }

    /**
     * Escribe el css a un fichero.
     * @param ruta Ruta a escribir el css.
     * @exception IOException Error escribiendo.
     */
    static private void writeCSS(String ruta) throws IOException {
        FileWriter fw = new FileWriter(ruta);
        BufferedWriter out = new BufferedWriter(fw);
        int i;
        for (i = 0;i < Resources.css.length;++i) {
            out.write(Resources.css[i]);
            out.newLine();
        }
        out.flush();
        out.close();
    }
    
    /**
     * Escribe la cabecera de una página HTML.
     * @param out BufferedWriter de salida.
     * @param back Ruta a la página anterior.
     * @param index Ruta al índice.
     * @param css Ruta al css.
     * @param logo Ruta al logo.
     * @param icon Ruta al icono.
     * @param lang Idioma.
     * @param js Instancia de JSimil.
     * @exception IOException Error escribiendo.
     */
    static private void headerHTML(BufferedWriter out,String back,String index,
                                   String css,String logo,String icon,
                                   Language lang,JSimil js) throws IOException {
        out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//" +
                 "EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.newLine();
        out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\"" + 
                  " xml:lang=\"en\" lang=\"en\" >");
        out.newLine();
        out.write("<head>");
        out.newLine();
        out.write("<meta http-equiv=\"content-type\" content=\"text/html;" +
                  "charset=utf-8\"/>");
        out.newLine();
        out.write("<style type=\"text/css\" title=\"currentStyle\"" +
                  " media=\"screen\">@import \""+
                  css.replace(File.separator,"/")+"\";</style>");
        out.newLine();
        out.write("<link rel=\"shortcut icon\" href=\""+
                icon.replace(File.separator,"/")+"\""+
                  " type=\"image/x-icon\"/>");
        out.newLine();
        out.write("<title>"+lang.getFrase(198)+"</title>");
        out.newLine();
        out.write("</head>");
        out.newLine();
        out.write("<body>");
        out.newLine();
        out.write("<table id=\"top\"><tr>");
        out.newLine();
          out.write("<td id=\"topl\">");
          out.newLine();
            out.write(lang.getFrase(199,js.getVersion())+"<br/><br/>");
            out.newLine();
            out.write(lang.getFrase(202));
            out.newLine();
          out.write("</td>");
          out.newLine();
            out.write("<td id=\"topr\">");
            out.newLine();
            out.write("<a href=\""+js.getWeb()+"\"><img src=\""+
                    logo.replace(File.separator,"/")+"\" " +
                    "alt=\"JSimil\"/></a>");
            out.newLine();
          out.write("</td>");
          out.newLine();
        out.write("</tr></table>");
        out.newLine();
        if ((back != null) || (index != null)) {
            out.write("<div class=\"back\">");
            out.newLine();
            if (back != null) {
                out.write("<a href=\"javascript:history.go(-1)\">"+
                        lang.getFrase(200)+"</a>");
//                out.write("<a href=\""+back+"\">"+lang.getFrase(200)+"</a>");
                out.newLine();
            }
            if (index != null) {
                out.write("<a href=\""+index.replace(File.separator,"/")+"\">"+
                          lang.getFrase(201)+"</a>");
                out.newLine();
            }
            out.write("</div>");
            out.newLine();
        }
        out.write("<div id=\"main\">");
        out.newLine();
    }

    /**
     * Escribe el pie de una página HTML.
     * @param out BufferedWriter de salida.
     * @param back Ruta a la página anterior.
     * @param index Ruta al índice.
     * @param lang Idioma.
     * @param js Instancia de JSimil.
     * @.post Pie de página escrito.
     * @exception IOException Error escribiendo.
     */
    static private void footerHTML(BufferedWriter out,String back,String index,
                                   Language lang,JSimil js) throws IOException {
        out.write("</div>");
        out.newLine();
        if ((back != null) || (index != null)) {
            out.write("<div class=\"back\">");
            out.newLine();
            if (back != null) {
                out.write("<a href=\"javascript:history.go(-1)\">"+
                        lang.getFrase(200)+"</a>");
//                out.write("<a href=\""+back+"\">"+lang.getFrase(200)+"</a>");
                out.newLine();
            }
            if (index != null) {
                out.write("<a href=\""+index.replace(File.separator,"/")+"\">"+
                          lang.getFrase(201)+"</a>");
                out.newLine();
            }
            out.write("</div>");
            out.newLine();
        }
        out.write("<div id=\"footer\">");
        out.newLine();
        out.write(lang.getFrase(203,js.getVersion(),js.getTDes(),
                "<a href=\""+js.getWeb()+"\">"+
                js.getWeb().replace("http://","")+"</a>"));
        out.newLine();
        out.write("</div>");
        out.newLine();
        out.write("</body>");
        out.newLine();
        out.write("</html>");
        out.newLine();
    }

    /**
     * Escribe el fichero de sobreescritura.
     * @param ruta Ruta a escribir el fichero de sobreescritura.
     * @exception IOException Error escribiendo.
     */
    static private void writeOVW(String ruta) throws IOException {
        FileWriter fw = new FileWriter(ruta);
        BufferedWriter out = new BufferedWriter(fw);
        out.write("JSimil output directory.");
        out.newLine();
        out.write("Ok to remove.");
        out.newLine();
        out.flush();
        out.close();
    }

    /**
     * Escribe un fichero de emparejamiento.
     * @param rutaglobal Ruta global principal.
     * @param dir Directorio para este emparejamiento.
     * @param miruta Mi ruta.
     * @param back Ruta hacia atrás.
     * @param index Ruta al índice.
     * @param css Ruta al css.
     * @param logo Ruta al logo.
     * @param icono Ruta al icono.
     * @param lang Idioma a utilizar.
     * @param js Instancia JSimil a utilizar.
     * @param df DecimalFormat a utilizar.
     * @param a Elemento 1.
     * @param b Elemento 2.
     * @param s Coincidencias a escribir.
     * @param ini Posición inicial a escribir.
     * @param prof Profundidad a escribir. 
     * @param resume Cantidad de resumen a realizar.
     * @param simil Similitud del emparejamiento.
     * @exception IOException Error escribiendo.
     * @.post Fichero escrito.
     */
    static private void escribeSubMatch(String rutaglobal,String dir,
            String miruta,String back,String index,String css,String logo,
            String icono,Language lang,JSimil js,DecimalFormat df,
            CodeElement a,CodeElement b,List<Matching> s,int ini,
            int prof,int resume,double simil) throws IOException {
        File f = new File(rutaglobal+dir+miruta);
        BufferedWriter out = new BufferedWriter(new FileWriter(f));
        headerHTML(out, back, index, css, logo, icono, lang, js);
        out.write("<div class=\"results\">"+
                lang.getFrase(230)+"</div><br/>");
        out.newLine();
        out.write("<table class=\"comparados2\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(221)+"</td>");
            out.newLine();
            out.write("<td class=\"tabcts\">"+lang.getFrase(231)+"</td>");
            out.newLine();
            out.write("<td class=\"tabcts\">"+lang.getFrase(232)+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obj\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(a.getNombreCompleto())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+df.format(a.getPorcentajePadre())+
                    "</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+
                    df.format(a.getPorcentajePrograma())+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obji\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(b.getNombreCompleto())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+df.format(b.getPorcentajePadre())+
                    "</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+
                    df.format(b.getPorcentajePrograma())+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
        out.write("</table>");
        out.newLine();        
        out.write("<br/><div class=\"results\">"+
                lang.getFrase(224)+" "+df.format(simil)+".</div>");
        out.newLine();
        out.write("<br/><br/><br/><div class=\"results\">"+
                lang.getFrase(223)+"</div><br/>");
        out.newLine();

        escribeSubElementos(out,rutaglobal,dir,miruta,rutaglobal+dir+miruta,
                index,css,logo,icono,s,ini,prof,resume,lang,df,
                js,false);
          
        footerHTML(out, back, index, lang, js);
        out.flush();
        out.close();
    }
    
    /**
     * Escribe un fichero de emparejamiento.
     * @param rutaglobal Ruta global principal.
     * @param dir Directorio para este emparejamiento.
     * @param miruta Mi ruta.
     * @param miruta2 Mi ruta (paquetes).
     * @param back Ruta hacia atrás.
     * @param index Ruta al índice.
     * @param css Ruta al css.
     * @param logo Ruta al logo.
     * @param icono Ruta al icono.
     * @param lang Idioma a utilizar.
     * @param js Instancia JSimil a utilizar.
     * @param df DecimalFormat a utilizar.
     * @param r Resultado a escribir.
     * @param resume Cantidad de resumen a realizar.
     * @.post Fichero escrito.
     * @exception IOException Error escribiendo.
     */
    static private void escribeMatch(String rutaglobal,String dir,String miruta,
            String miruta2,String back,String index,String css,String logo,
            String icono,Language lang,JSimil js,DecimalFormat df,ProgramComparison r,
            int resume)
            throws IOException {
        
        File fp = new File(rutaglobal+dir+miruta2);
        BufferedWriter out = new BufferedWriter(new FileWriter(fp));        
        headerHTML(out, back, index, css, logo, icono, lang, js);
        out.write("<div class=\"results\">"+
                lang.getFrase(220)+"</div><br/>");
        out.newLine();
        out.write("<table class=\"comparados\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(221)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(222)+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obj\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(r.getNombre1())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+r.getTama1()+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obji\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(r.getNombre2())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+r.getTama2()+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
        out.write("</table>");
        out.newLine();        
        out.write("<br/><div class=\"results\">"+
                lang.getFrase(224)+" "+df.format(r.getSimilitud())+
                ".</div>");
        out.newLine();
    
        escribeSubElementosPac(out,rutaglobal,dir,miruta,rutaglobal+dir+miruta,
                index,css,logo,icono,0,0,resume,lang,df,js,true,r); 
        //Mostrar lista de paquetes de ambos y el [sin nombre].
            //Mostrar opción para ver el fichero normal.
            //Mostrar recursivamente los emparejamientos por paquetes.
            //Mostrar la suma de similitud por paquetes.
        footerHTML(out, back, index, lang, js);
        out.flush();
        out.close();
        File f = new File(rutaglobal+dir+miruta);
        out = new BufferedWriter(new FileWriter(f));
        headerHTML(out, back, index, css, logo, icono, lang, js);
        out.write("<div class=\"results\">"+
                lang.getFrase(220)+"</div><br/>");
        out.newLine();
        out.write("<table class=\"comparados\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(221)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(222)+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obj\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(r.getNombre1())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+r.getTama1()+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obji\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(r.getNombre2())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+r.getTama2()+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
        out.write("</table>");
        out.newLine();        
        out.write("<br/><div class=\"results\">"+
                lang.getFrase(224)+" "+df.format(r.getSimilitud())+
                ".</div>");
        out.newLine();
        out.write("<br/><br/><br/><div class=\"results\">"+
                lang.getFrase(223)+"</div><br/>");
        out.newLine();

        escribeSubElementos(out,rutaglobal,dir,miruta,rutaglobal+dir+miruta,
                index,css,logo,icono,r.getCoincidencias(),0,0,resume,lang,df,
                js,true);

        footerHTML(out, back, index, lang, js);
        out.flush();
        out.close();
    }

    /**
     * Escribe un fichero de código.
     * @param ruta Ruta donde escribir el fichero.
     * @param a Elemento 1.
     * @param b Elemento 2.
     * @param back Enlace para regresar atrás.
     * @param index Enlace para regresar al índice.
     * @param css Enlace al css.
     * @param logo Enlace al logo.
     * @param icono Enlace al icono.
     * @param si Coincidencias.
     * @param num Número de coincidencia a mostrar.
     * @param lang Idioma a utilizar.
     * @param df DecimalFormat a utilizar.
     * @param js Instancia JSimil a utilizar.
     * @exception IOException Error escribiendo.
     * @.post Fichero escrito.
     */
    static private void escribeCodigo(String ruta,CodeElement a,
            CodeElement b,String back,String index,String css,String logo,
            String icono,List <Matching> si,int num,
            Language lang,DecimalFormat df,
            JSimil js) throws IOException {
        Matching s = si.get(num);
        File f = new File(ruta);
        int i;
        String codigo;
        int lineasaorig = 0;
        int lineasborig = 0;
        int lineasadese = 0;
        int lineasbdese = 0;
        int lineaaorigi = 0;
        int lineaborigi = 0;
        int lineaadesei = 0;
        int lineabdesei = 0;
        double aorig[] = null;
        double borig[] = null;
        double adese[] = null;
        double bdese[] = null;
        int aorigi[] = null;
        int borigi[] = null;
        int adesei[] = null;
        int bdesei[] = null;        

        if (a.getLugarCodigoOriginal() != null) {
            lineasaorig = a.getLugarCodigoOriginal().getLineaFin()-
                    a.getLugarCodigoOriginal().getLineaIni()+1;
            lineaaorigi = a.getLugarCodigoOriginal().getLineaIni();
            if (lineasaorig > 0) {
                aorig = new double[lineasaorig];
                aorigi = new int[lineasaorig];
            }
            for (i = 0;i < lineasaorig;++i)
                aorig[i] = 0;
        }
        if (a.getLugarCodigoDesensamblado() != null) {
            lineasadese = a.getLugarCodigoDesensamblado().getLineaFin()-
                    a.getLugarCodigoDesensamblado().getLineaIni()+1;
            lineaadesei = a.getLugarCodigoDesensamblado().getLineaIni();
            if (lineasadese > 0) {
                adese = new double[lineasadese];
                adesei = new int[lineasadese];
            }
            for (i = 0;i < lineasadese;++i)
                adese[i] = 0;
        }
        if (b.getLugarCodigoOriginal() != null) {
            lineasborig = b.getLugarCodigoOriginal().getLineaFin()-
                    b.getLugarCodigoOriginal().getLineaIni()+1;
            lineaborigi = b.getLugarCodigoOriginal().getLineaIni();
            if (lineasborig > 0) {
                borig = new double[lineasborig];
                borigi = new int[lineasborig];
            }
            for (i = 0;i < lineasborig;++i)
                borig[i] = 0;
        }
        if (b.getLugarCodigoDesensamblado() != null) {
            lineasbdese = b.getLugarCodigoDesensamblado().getLineaFin()-
                    b.getLugarCodigoDesensamblado().getLineaIni()+1;
            lineabdesei = b.getLugarCodigoDesensamblado().getLineaIni();
            if (lineasbdese > 0) {
                bdese = new double[lineasbdese];
                bdesei = new int[lineasbdese];
            }
            for (i = 0;i < lineasbdese;++i)
                bdese[i] = 0;
        }
        
        //Para cada una de las coincidencias...
        boolean seguir = true;
        int prof = s.getProfundidad();
        int itera = num+1;
        if (itera>=si.size())
            seguir = false;
        else if (si.get(itera).getProfundidad()<=prof)
            seguir = false;
        Matching act;

        int inicon;
        int fincon;
        int ki,j;
        while (seguir) {
            act = si.get(itera);
            
            //Calculo su linea inicial y final.
            for (ki = 0;ki < 2;ki++) {
                CodeElement ele;
                if (ki == 0)
                    ele = act.getElementoA();
                else
                    ele = act.getElementoB();
                if (ele != null) {
                    if (ele.getLugarCodigoOriginal() != null) {
                        inicon = ele.getLugarCodigoOriginal().getLineaIni();
                        fincon = ele.getLugarCodigoOriginal().getLineaFin();
                        if (ki == 0) {
                            inicon -= lineaaorigi;
                            fincon -= lineaaorigi;
                            if ((inicon < 0) ||
                               (fincon < 0) ||
                               (inicon >= aorig.length) ||
                               (fincon >= aorig.length)) {
                                inicon = 1;
                                fincon = 0;
                            }
                            for (j = inicon;j <= fincon;j++)
                                if (aorig[j] < act.getSimilitud())
                                    aorig[j] = act.getSimilitud();
                        }
                        else {
                            inicon -= lineaborigi;
                            fincon -= lineaborigi;
                            if ((inicon < 0) ||
                               (fincon < 0) ||
                               (inicon >= borig.length) ||
                               (fincon >= borig.length)) {
                                inicon = 1;
                                fincon = 0;
                            }
                            for (j = inicon;j <= fincon;j++)
                                if (borig[j] < act.getSimilitud())
                                    borig[j] = act.getSimilitud();
                        }
                    }
                    if (ele.getLugarCodigoDesensamblado() != null) {
                       inicon = ele.getLugarCodigoDesensamblado().getLineaIni();
                       fincon = ele.getLugarCodigoDesensamblado().getLineaFin();

                        if (ki == 0) {
                            inicon -= lineaadesei;
                            fincon -= lineaadesei;
                            if ((inicon < 0) ||
                               (fincon < 0) ||
                               (inicon >= adese.length) ||
                               (fincon >= adese.length)) {
                                inicon = 1;
                                fincon = 0;
                            }
                            for (j = inicon;j <= fincon;j++)
                                if (adese[j] < act.getSimilitud())
                                    adese[j] = act.getSimilitud();
                        }
                        else {
                            inicon -= lineabdesei;
                            fincon -= lineabdesei;
                            if ((inicon < 0) ||
                               (fincon < 0) ||
                               (inicon >= bdese.length) ||
                               (fincon >= bdese.length)) {
                                inicon = 1;
                                fincon = 0;
                            }
                            for (j = inicon;j <= fincon;j++)
                                if (bdese[j] < act.getSimilitud())
                                    bdese[j] = act.getSimilitud();
                        }
                    }
                }
            }
            itera++;
            if (itera>=si.size())
                seguir = false;
            else if (si.get(itera).getProfundidad()<=prof)
                seguir = false;
        }
        for (j = 0;j < lineasaorig;j++)
            aorigi[j] = (int)Math.round((aorig[j]*10));
        for (j = 0;j < lineasadese;j++)
            adesei[j] = (int)Math.round((adese[j]*10));
        for (j = 0;j < lineasborig;j++)
            borigi[j] = (int)Math.round((borig[j]*10));
        for (j = 0;j < lineasbdese;j++)
            bdesei[j] = (int)Math.round((bdese[j]*10));
        
        BufferedWriter out = new BufferedWriter(new FileWriter(f));
        headerHTML(out, back, index, css, logo, icono, lang, js);
        out.write("<div class=\"results\">"+
                lang.getFrase(235)+"</div><br/>");
        out.newLine();
        out.write("<table class=\"comparados2\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(221)+"</td>");
            out.newLine();
            out.write("<td class=\"tabcts\">"+lang.getFrase(231)+"</td>");
            out.newLine();
            out.write("<td class=\"tabcts\">"+lang.getFrase(232)+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obj\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(a.getNombreCompleto())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+df.format(a.getPorcentajePadre())+
                    "</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+
                    df.format(a.getPorcentajePrograma())+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"obji\">");
          out.newLine();
            out.write("<td class=\"tabc\">"+
                    Tools.recorta(b.getNombreCompleto())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+df.format(b.getPorcentajePadre())+
                    "</td>");
            out.newLine();
            out.write("<td class=\"tabcc\">"+
                    df.format(b.getPorcentajePrograma())+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
        out.write("</table>");
        out.newLine();        
        out.write("<br/><div class=\"results\">"+
                lang.getFrase(224)+" "+df.format(s.getSimilitud())+".</div>");
        out.newLine();

        out.write("<br/><br/>");
        out.newLine();
        escribeLeyenda(out,lang);
        
        out.write("<br/><br/><div class=\"results\">"+
                lang.getFrase(236)+"</div><br/>");
        out.newLine();

        out.write("<table class=\"code\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabcth\">"+
                    Tools.recorta(a.getNombreCompleto())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcth\">"+
                    Tools.recorta(b.getNombreCompleto())+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"showcode\">");
          out.newLine();
            out.write("<td class=\"tabch\">");
            out.newLine();
            out.write("<div class=\"tabover\">");
            out.newLine();
            if (a.getLugarCodigoOriginal() == null) {
                out.write(lang.getFrase(238));
                out.newLine();
            }
            else {
                Location cod = a.getLugarCodigoOriginal();
                List<String> ss = cod.getFichero().getDatos();
                for (i = cod.getLineaIni();i <= cod.getLineaFin();i++) {
                    codigo = ss.get(i);
                    out.write("<div class=\"codem"+aorigi[i-lineaaorigi]+"\">"+
                            Tools.codificacod(codigo)+"</div>");
                    out.newLine();
                }
            }
            out.write("</div>");
            out.newLine();
            out.write("</td>");
            out.newLine();
            out.write("<td class=\"tabch\">");
            out.newLine();
            out.write("<div class=\"tabover\">");
            out.newLine();
            if (b.getLugarCodigoOriginal() == null) {
                out.write(lang.getFrase(238));
                out.newLine();
            }
            else {
                Location cod = b.getLugarCodigoOriginal();
                List<String> ss = cod.getFichero().getDatos();
                for (i = cod.getLineaIni();i <= cod.getLineaFin();i++) {
                    codigo = ss.get(i);
                    out.write("<div class=\"codem"+borigi[i-lineaborigi]+"\">"+
                            Tools.codificacod(codigo)+"</div>");
                }
            }
            out.write("</div>");
            out.newLine();
            out.write("</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();      
        out.write("</table>");
        out.newLine();
        out.write("<br/><br/>");
        out.newLine();
        escribeLeyenda(out,lang);
        
        out.write("<br/><br/><div class=\"results\">"+
                lang.getFrase(237)+"</div><br/>");
        out.newLine();

        out.write("<table class=\"code\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabcth\">"+
                    Tools.recorta(a.getNombreCompleto())+"</td>");
            out.newLine();
            out.write("<td class=\"tabcth\">"+
                    Tools.recorta(b.getNombreCompleto())+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();        
          out.write("<tr class=\"showcode\">");
          out.newLine();
            out.write("<td class=\"tabch\">");
            out.newLine();
            out.write("<div class=\"tabover\">");
            out.newLine();
            if (a.getLugarCodigoDesensamblado() == null) {
                out.write(lang.getFrase(238));
                out.newLine();
            }
            else {
                Location cod = a.getLugarCodigoDesensamblado();
                List<String> ss = cod.getFichero().getDatos();
                for (i = cod.getLineaIni();i <= cod.getLineaFin();i++) {
                    codigo = ss.get(i);
                    out.write("<div class=\"codem"+adesei[i-lineaadesei]+"\">"+
                            Tools.codificacod(codigo)+"</div>");
                }
            }
            out.write("</div>");
            out.newLine();
            out.write("</td>");
            out.newLine();
            out.write("<td class=\"tabch\">");
            out.newLine();
            out.write("<div class=\"tabover\">");
            out.newLine();
            if (b.getLugarCodigoDesensamblado() == null) {
                out.write(lang.getFrase(238));
                out.newLine();
            }
            else {
                Location cod = b.getLugarCodigoDesensamblado();
                List<String> ss = cod.getFichero().getDatos();
                for (i = cod.getLineaIni();i <= cod.getLineaFin();i++) {
                    codigo = ss.get(i);
                    out.write("<div class=\"codem"+bdesei[i-lineabdesei]+"\">"+
                            Tools.codificacod(codigo)+"</div>");
                }
            }
            out.write("</div>");
            out.newLine();
            out.write("</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();      
        out.write("</table>");
        out.newLine();
        
        footerHTML(out, back, index, lang, js);
        out.flush();
        out.close();
    }

    /**
     * Escribe la leyenda de color en HTML.
     * @param out Flujo en el que se debe escribir.
     * @.post Leyenda de color escrita.
     */
    static private void escribeLeyenda(BufferedWriter out,Language lang)
    throws IOException {
        out.write("<table class=\"comparados2\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabcth\" colspan=\"11\">");
            out.newLine();
            out.write(lang.getFrase(278));
            out.newLine();
            out.write("</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();
          out.write("<tr>");
          out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb0\">" +
                    "&nbsp;<br/>0-5%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb1\">" +
                    "&nbsp;<br/>5-15%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb2\">" +
                    "&nbsp;<br/>15-25%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb3\">" +
                    "&nbsp;<br/>25-35%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb4\">" +
                    "&nbsp;<br/>35-45%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb5\">" +
                    "&nbsp;<br/>45-55%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb6\">" +
                    "&nbsp;<br/>55-65%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb7\">" +
                    "&nbsp;<br/>65-75%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb8\">" +
                    "&nbsp;<br/>75-85%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb9\">" +
                    "&nbsp;<br/>85-95%<br/>&nbsp;</div></td>");
            out.newLine();
            out.write("<td class=\"tabccc\"><div class=\"codeb10\">" +
                    "&nbsp;<br/>95-100%<br/>&nbsp;</div></td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();
        out.write("</table>");
        out.newLine();
    }

    /**
     * Escribe los subelementos de un emparejamiento.
     * @param out Writer a escribir los elementos.
     * @param rutaglobal Ruta global principal.
     * @param dir Directorio para este emparejamiento.
     * @param miruta Mi ruta.
     * @param back Ruta hacia atrás.
     * @param index Ruta al índice.
     * @param css Ruta al css.
     * @param logo Ruta al logo.
     * @param icono Ruta al icono.
     * @param s Lista de coincidencias a escribir.
     * @param ini Índice inicial a escribir.
     * @param prof Profundidad a escribir.
     * @param resume Cantidad de resumen a realizar.
     * @param lang Idioma a utilizar.
     * @param df DecimalFormat a utilizar.
     * @param js Instancia JSimil a utilizar.
     * @param showclass Mostrar clase en métodos.
     * @exception IOException Error escribiendo.
     * @.post Resultados escritos.
     */
    static private void escribeSubElementos(BufferedWriter out,
            String rutaglobal,String dir,String miruta,
            String back,String index,String css,String logo,String icono,
            List<Matching> s,int ini,int prof,int resume,
            Language lang,DecimalFormat df,JSimil js,boolean showclass)
            throws IOException {
        
        out.write("<table class=\"resultados\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(226)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(221)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(277)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(275)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(276)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(216)+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();
          int i;
          i = ini;
          String divc;
          boolean seguir = true;
          Matching a;
          CodeElement ele1,ele2;
          String filename;
          String filecode;
          if (i >= s.size())
              seguir = false;
          int show = 0;
          while (seguir) {
              a = s.get(i);
              if (a.getProfundidad()<prof)
                  seguir = false;
              if (seguir == true && a.getProfundidad()==prof) {
                  ele1 = a.getElementoA();
                  ele2 = a.getElementoB();
                  if (ele1 != null && ele2 != null) {
                      divc = "obj";
                      if (show%2==0)
                          divc+="i";
                      show++;
                      out.write("<tr class=\""+divc+"\">");
                      out.newLine();
                      out.newLine();
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      out.write("<a name=\"match"+i+"\"></a>");
                      out.newLine();
                      filename = "";
                      if (a.getElementoA().getTipo().endsWith("SMetodo") &&
                              resume<1) {
                          filename = "code"+i+".html";
                          escribeCodigo(rutaglobal+dir+filename,ele1,ele2,
                                  "index.html#match"+i,
                                  index,css,logo,icono,s,i,lang,df,js);
                      }
                      else if (a.getElementoA().getTipo().endsWith("SClase")) {
                          File ndir =
                            new File(rutaglobal+dir+"match"+i+File.separator);
                          ndir.mkdir();
                          filename = "match"+i+File.separator+"index.html";
                          escribeSubMatch(rutaglobal+dir,"match"+i+File.separator,
                            "index.html",
                            ".."+File.separator+"index.html#match"+i,
                            ".."+File.separator+index,
                            ".."+File.separator+css,
                            ".."+File.separator+logo,
                            ".."+File.separator+icono,
                            lang,
                            js,
                            df,
                            ele1,
                            ele2,
                            s,
                            i+1,
                            prof+1,
                            resume,
                            a.getSimilitud());
                      }
                      boolean muestra = false;
                      if (!filename.equals("")) {
                          out.write("<a href=\""+
                                    filename.replace(File.separator,"/")+"\">");
                          out.newLine();
                      }
                      if (a.getElementoA().getTipo().endsWith("SBloque"))
                          out.write(lang.getFrase(227)+"<br/>"+
                                  lang.getFrase(227));
                      else if (a.getElementoA().getTipo().endsWith("SMetodo")) {
                          if (muestra)
                              muestra = true;
                          out.write(lang.getFrase(228)+"<br/>"+
                                  lang.getFrase(228));
                      }
                      else
                          out.write(lang.getFrase(229)+"<br/>"+
                                  lang.getFrase(229));
                      if (!filename.equals("")) {
                          out.write("</a>");
                          out.newLine();
                      }
                      out.newLine();
                      out.write("</td>");
                      out.newLine();
                      out.write("<td class=\"tabc\">");
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("<a href=\""+
                                  filename.replace(File.separator,"/")+"\">");
                          out.newLine();
                      }
                      String nombre1;
                      String nombre2;
                      if (showclass) {
                        nombre1 = ele1.getNombreInterno();
                        nombre2 = ele2.getNombreInterno();
                      }
                      else {
                        nombre1 = ele1.getNombre();
                        nombre2 = ele2.getNombre();
                      }
                      out.write(Tools.recorta(nombre1)+"<br/>"+
                            Tools.recorta(nombre2));
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("</a>");
                          out.newLine();
                      }
                      out.write("</td>");
                      out.newLine();
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("<a href=\""+
                                  filename.replace(File.separator,"/")+"\">");
                          out.newLine();
                      }
                      out.write(""+ele1.getNInstrucciones()+"<br/>"+
                                ele2.getNInstrucciones());
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("</a>");
                          out.newLine();
                      }
                      out.write("</td>");
                      out.newLine();
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("<a href=\""+
                                  filename.replace(File.separator,"/")+"\">");
                          out.newLine();
                      }
                      out.write(""+df.format(ele1.getPorcentajePadre())+
                              "<br/>"+df.format(ele2.getPorcentajePadre()));
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("</a>");
                          out.newLine();
                      }
                      out.write("</td>");
                      out.newLine();
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("<a href=\""+
                                  filename.replace(File.separator,"/")+"\">");
                          out.newLine();
                      }
                      out.write(""+df.format(ele1.getPorcentajePrograma())+
                              "<br/>"+df.format(ele2.getPorcentajePrograma()));
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("</a>");
                          out.newLine();
                      }
                      out.write("</td>");
                      out.newLine();
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("<a href=\""+
                                  filename.replace(File.separator,"/")+"\">");
                          out.newLine();
                      }
                      out.write(df.format(a.getSimilitud()));
                      out.newLine();
                      if (!filename.equals("")) {
                          out.write("</a>");
                          out.newLine();
                      }
                      out.write("</td>");
                      out.newLine();
                      out.write("</tr>");
                      out.newLine();
                  }
                  else if (ele1 != null || ele2 != null) {
                      divc = "obj";
                      if (show%2==0)
                          divc+="i";
                      show++;
                      out.write("<tr class=\""+divc+"\">");
                      out.newLine();
                      out.newLine();
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      out.write("<a name=\"match"+i+"\"></a>");
                      out.newLine();
                      filename = "";
                      String tipo;
                      if (ele1 != null)
                          tipo = ele1.getTipo();
                      else
                          tipo = ele2.getTipo();
                      if (tipo.endsWith("SBloque"))
                          out.write(lang.getFrase(227));
                      else if (tipo.endsWith("SMetodo"))
                          out.write(lang.getFrase(228));
                      else
                          out.write(lang.getFrase(229));
                      out.newLine();
                      out.write("</td>");
                      out.newLine();
                      out.write("<td class=\"tabc\">");
                      out.newLine();
                      String nombre1 = "";
                      String nombre2 = "";
                      if (showclass) {
                          if (ele1 != null)
                              nombre1 = ele1.getNombreInterno();
                          if (ele2 != null)
                              nombre2 = ele2.getNombreInterno();
                      }
                      else {
                          if (ele1 != null)
                              nombre1 = ele1.getNombre();
                          if (ele2 != null)
                              nombre2 = ele2.getNombre();
                      }
                      if (ele1 != null) {
                          out.write(Tools.recorta(nombre1));
                      }
                      else
                          out.write(lang.getFrase(262));
                      out.write("<br/>");
                      if (ele2 != null) {
                          out.write(Tools.recorta(nombre2));
                      }
                      else
                          out.write(lang.getFrase(262));                      
                      out.newLine();
                      out.write("</td>");
                      out.newLine();

                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (ele1 != null) {
                          out.write(""+ele1.getNInstrucciones());
                      }
                      else
                          out.write("-");
                      out.write("<br/>");
                      if (ele2 != null) {
                          out.write(""+ele2.getNInstrucciones());
                      }
                      else
                          out.write("-");
                      out.newLine();
                      out.write("</td>");
                      out.newLine();

                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (ele1 != null) {
                          out.write(""+df.format(ele1.getPorcentajePadre()));
                      }
                      else
                          out.write("-");
                      out.write("<br/>");
                      if (ele2 != null) {
                          out.write(""+df.format(ele2.getPorcentajePadre()));
                      }
                      else
                          out.write("-");
                      out.newLine();
                      out.write("</td>");
                      out.newLine();
                      
                      out.write("<td class=\"tabcc\">");
                      out.newLine();
                      if (ele1 != null) {
                          out.write(""+df.format(ele1.getPorcentajePrograma()));
                      }
                      else
                          out.write("-");
                      out.write("<br/>");
                      if (ele2 != null) {
                          out.write(""+df.format(ele2.getPorcentajePrograma()));
                      }
                      else
                          out.write("-");
                      out.newLine();
                      out.write("</td>");
                      out.newLine();
                      
                      out.write("<td class=\"tabcc\">-</td>");
                      out.newLine();
                      out.write("</tr>");
                      out.newLine();
                  }
              }
              ++i;
              if (i >= s.size())
                  seguir = false;
          }
        out.write("</table>");
        out.newLine();
    }

    /**
     * Escribe los subelementos de un emparejamiento.
     * @param out Writer a escribir los elementos.
     * @param rutaglobal Ruta global principal.
     * @param dir Directorio para este emparejamiento.
     * @param miruta Mi ruta.
     * @param back Ruta hacia atrás.
     * @param index Ruta al índice.
     * @param css Ruta al css.
     * @param logo Ruta al logo.
     * @param icono Ruta al icono.
     * @param ini Índice inicial a escribir.
     * @param prof Profundidad a escribir.
     * @param resume Cantidad de resumen a realizar.
     * @param lang Idioma a utilizar.
     * @param df DecimalFormat a utilizar.
     * @param js Instancia JSimil a utilizar.
     * @param showclass Mostrar clase en métodos.
     * @param r Resultado a escribir.
     * @exception IOException Error escribiendo.
     * @.post Resultados escritos.
     */
    static private void escribeSubElementosPac(BufferedWriter out,
            String rutaglobal,String dir,String miruta,
            String back,String index,String css,String logo,String icono,
            int ini,int prof,int resume,
            Language lang,DecimalFormat df,JSimil js,boolean showclass,
            ProgramComparison r)
            throws IOException {
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        ds.setGroupingSeparator(' ');
        DecimalFormat df2 = new DecimalFormat("0.000",ds);
        List<Matching> s = r.getCoincidencias();
        int k,i,j,n;
        String nom,divc;
        List<String> paq;
        int actual = 0;
            out.write("<br/><div class=\"results\">"+
                    "<a href=\"index.html\">"+
                    lang.getFrase(274)+"</a></div>");
        for (k = 0;k < 2;++k) {
            if (k == 0) {
                nom = r.getNombre1();
                paq = r.getPaquetes1();
            }
            else {
                nom = r.getNombre2();
                paq = r.getPaquetes2();
            }
            out.write("<br/><br/><div class=\"results\">"+
                    lang.getFrase(266,nom)+"</div>"+"<br/>");
            out.newLine();
            out.write("<table class=\"resultadosp\">");
            out.newLine();
              out.write("<tr class=\"tittab\">");
              out.newLine();
                out.write("<td class=\"tabct\">"+lang.getFrase(267)+"</td>");
                out.newLine();
                out.write("<td class=\"tabct\">"+lang.getFrase(273)+"</td>");
                out.newLine();
              out.write("</tr>");
              out.newLine();
              for (i = 0;i < paq.size();++i) {
                String pacc = paq.get(i);
                String pace = pacc;
                CodeElement el;
                List <Matching> a = new ArrayList<Matching>();
                for (j = 0;j < r.getCoincidencias().size();++j) {
                    if (k == 0)
                        el = r.getCoincidencias().get(j).getElementoA();
                    else
                        el = r.getCoincidencias().get(j).getElementoB();
                    if (el != null) {
                        String aa = el.getNombreInterno();
                        int ind = aa.indexOf(':');
                        if (ind > -1)
                            aa = aa.substring(0,ind);
                        ind = aa.lastIndexOf('/');
                        if (ind > -1)
                            aa = aa.substring(0,ind);
                        else
                            aa = "";
                        if (pacc.equals(aa)) {
                            a.add(r.getCoincidencias().get(j));
                        }
                    }                     
                }
                String filename = "";
                if (a.size() > 0) {
                    filename = "pack"+actual+".html";
                    actual++;
                    File f = new File(rutaglobal+dir+filename);
                    BufferedWriter out2 = new BufferedWriter(new FileWriter(f));
                    headerHTML(out2, back, index, css, logo, icono, lang, js);
                    out2.write("<div class=\"results\">"+
                            lang.getFrase(220)+"</div><br/>");
                    out2.newLine();
                    out2.write("<table class=\"comparados\">");
                    out2.newLine();
                      out2.write("<tr class=\"tittab\">");
                      out2.newLine();
                        out2.write("<td class=\"tabct\">"+lang.getFrase(221)+
                                "</td>");
                        out2.newLine();
                        out2.write("<td class=\"tabct\">"+lang.getFrase(222)+
                                "</td>");
                        out2.newLine();
                      out2.write("</tr>");
                      out2.newLine();        
                      out2.write("<tr class=\"obj\">");
                      out2.newLine();
                        out2.write("<td class=\"tabc\">"+
                                Tools.recorta(r.getNombre1())+"</td>");
                        out2.newLine();
                        out2.write("<td class=\"tabcc\">"+r.getTama1()+"</td>");
                        out2.newLine();
                      out2.write("</tr>");
                      out2.newLine();        
                      out2.write("<tr class=\"obji\">");
                      out2.newLine();
                        out2.write("<td class=\"tabc\">"+
                                Tools.recorta(r.getNombre2())+"</td>");
                        out2.newLine();
                        out2.write("<td class=\"tabcc\">"+r.getTama2()+"</td>");
                        out2.newLine();
                      out2.write("</tr>");
                      out2.newLine();        
                    out2.write("</table>");
                    out2.newLine();        
                    out2.write("<br/><div class=\"results\">"+
                            lang.getFrase(224)+" "+df.format(r.getSimilitud())+
                            ".</div>");
                    if (k == 0)
                        out2.write("<br/><div class=\"results\">"+
                            lang.getFrase(272,"<b>"+pacc+"</b>",
                            "<b>"+r.getNombre1())+"</b></div>");
                    else
                        out2.write("<br/><div class=\"results\">"+
                            lang.getFrase(272,"<b>"+pacc+"</b>",
                            "<b>"+r.getNombre2())+"</b></div>");
                    out2.newLine();
                    out2.write("<br/><br/><div class=\"results\">"+
                            lang.getFrase(223)+"</div><br/>");
                    out2.newLine();

                    escribeSubElementos(out2,rutaglobal,dir,miruta,rutaglobal+dir+miruta,
                            index,css,logo,icono,a,0,0,resume,lang,df,js,true);

                    footerHTML(out2, back, index, lang, js);
                    out2.flush();
                    out2.close();
                }
                if (pace.equals(""))
                    pace = "<i>["+lang.getFrase(268)+"]</i>";
                divc = "obj";
                if (i%2==0)
                    divc+="i";
                out.write("<tr class=\""+divc+"\">");
                out.newLine();
                out.write("<td class=\"tabcc\">");
                out.newLine();
                if (!filename.equals("")) {
                    out.write("<a href=\""+
                            filename.replace(File.separator,"/")+"\">");
                    out.newLine();
                }
                out.write(pace);
                out.newLine();
                if (!filename.equals("")) {
                    out.write("</a>");
                    out.newLine();
                }
                out.write("</td>");
                out.newLine();                
                out.write("<td class=\"tabcc\">");
                out.newLine();
                if (!filename.equals("")) {
                    out.write("<a href=\""+
                            filename.replace(File.separator,"/")+"\">");
                    out.newLine();
                }
                double sim = 0;
                for (j = 0;j < a.size();++j) {
                    if (k == 0)
                        sim += a.get(j).getSimilitud()*
                                a.get(j).getElementoA().getPorcentajePrograma();
                    else
                        sim += a.get(j).getSimilitud()*
                                a.get(j).getElementoB().getPorcentajePrograma();
                }
                if (r.getSimilitud()==0)
                    sim = 0;
                else {
                    sim /= r.getSimilitud();
                    sim *= 100;
                }
                out.write(df2.format(sim));
                out.newLine();
                if (!filename.equals("")) {
                    out.write("</a>");
                    out.newLine();
                }
                out.write("</td>");
                out.newLine();   
                out.write("</tr>");
                out.newLine();
                
              }
            out.write("</table><br/>");
            out.newLine();
        }
    }
    
    /**
     * Exporta los resultados.
     * @param ruta Ruta a donde exportar los resultados.
     * @param overwrite Sobreescribir los resultados?
     * @param writeprofile Escribir el perfil en los resultados?
     * @param res Resultados a utilizar.
     * @param per Perfil a utilizar.
     * @param lang Idioma a utilizar.
     * @param resume 0 = completo, 1 = resumen, 2 = resumen corto.
     * @param limite Límite de resultados a exportar, si -1 no hay límite.
     * @.post Escrito fichero.
     * @exception IOException No se puede crear el fichero.
     */
    static public void export(String ruta,boolean overwrite,
            boolean writeprofile,List<ProgramComparison> res,MatchingProfile per,Language lang,
            int resume,int limite)
    throws IOException {
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        ds.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("0.0000%",ds);
        File f = new File(ruta);
        if (f.exists()) {
            if (!overwrite)
                throw new IOException("exists");
            if (overwrite) {
                String ruta2 = ""+ruta;
                if (!ruta2.endsWith(File.separator))
                    ruta2 += File.separator;
                ruta2 += "jsimil.ovw";
                File f1 = new File(ruta2);
                if (f1.exists()) {
                    if (!deleteDirectory(f))
                        throw new IOException("delete");                    
                    f = new File(ruta);
                }
                else {
                    throw new IOException("exists2");
                }
            }
                
        }
        JSimil js = new JSimil();
        f.mkdir();
        String ruta2 = ""+ruta;
        if (!ruta2.endsWith(File.separator))
            ruta2 += File.separator;
        String rutalogo = "jsimil.gif";
        String rutacss = "jsimil.css";
        String rutaicono = "jsimil.ico";
        writeOVW(ruta2+"jsimil.ovw");
        writeLogo(ruta2+rutalogo);
        writeCSS(ruta2+rutacss);
        writeIcono(ruta2+rutaicono);
        
        String actual=ruta2+"index.html";
        
        BufferedWriter out = new BufferedWriter(new FileWriter(actual));
        headerHTML(out,null,null,rutacss,rutalogo,rutaicono,lang,js);

        double pmin = 0;
        double pmax = 0;
        double perr = 0;
        try {
            pmin = per.getValor("ProgMatchMIN");
            pmax = per.getValor("ProgMatchMAX");
            perr = per.getValor("ProgMatchERROR");
        } catch (JSimilException e) {
            //Ok.
        }
        
        if (writeprofile) {
            out.write("<div id=\"profile\">");
            out.newLine();
            try {
                out.write("<b>"+lang.getFrase(213)+"</b><br/><br/>");
                out.newLine();
                if (per.getValor("ReflexiveMATCH")>0.5)
                    out.write(lang.getFrase(206)+"<br/>");
                else
                    out.write(lang.getFrase(207)+"<br/>");
                out.newLine();
                if (per.getValor("DifferenceSEARCH")>0.5)
                    out.write(lang.getFrase(208)+"<br/>");
                else
                    out.write(lang.getFrase(209)+"<br/>");
                out.newLine();
                if (per.getValor("BlockAllowMULTIMATCH")>0.5)
                    out.write(lang.getFrase(287)+"<br/>");
                else
                    out.write(lang.getFrase(288)+"<br/>");
                out.newLine();
                if (per.getValor("MethodAllowMULTIMATCH")>0.5)
                    out.write(lang.getFrase(251)+"<br/>");
                else
                    out.write(lang.getFrase(252)+"<br/>");
                out.newLine();
                if (per.getValor("ClassAllowMULTIMATCH")>0.5)
                    out.write(lang.getFrase(253)+"<br/>");
                else
                    out.write(lang.getFrase(254)+"<br/>");
                out.newLine();
                if (per.getValor("ClassSAMENAMEMATCH")>0.5)
                    out.write(lang.getFrase(255)+"<br/>");
                else
                    out.write(lang.getFrase(256)+"<br/>");
                out.newLine();
                if (per.getValor("MethodSAMENAMEMATCH")>0.5)
                    out.write(lang.getFrase(257)+"<br/>");
                else
                    out.write(lang.getFrase(258)+"<br/>");
                out.newLine();
                if (per.getValor("MethodOfClassSAMENAMEMATCH")>0.5)
                    out.write(lang.getFrase(259)+"<br/>");
                else
                    out.write(lang.getFrase(260)+"<br/>");
                out.newLine();
                out.write(lang.getFrase(204,""+pmin,""+pmax)+
                        " <div class=\"blue\">"+lang.getFrase(217)+
                        "</div><br/>");
                out.newLine();
                out.write(lang.getFrase(205,""+perr)
                          +"<br/>");
                out.newLine();
                out.write(lang.getFrase(214,""+per.getValor("ProgMatchLIMIT"))
                          +"<br/>");
                out.newLine();
                out.write(lang.getFrase(261,""+
                        per.getValor("ProgMatchDIFFERENCE"))+"<br/>");
                out.newLine();
                out.write(lang.getFrase(210,""+
                          per.getValor("ProgMatchOPTIMISM"))+"<br/>");
                out.newLine();
                out.write(lang.getFrase(211,""+
                          per.getValor("ClassMatchOPTIMISM"))+"<br/>");
                out.newLine();
                out.write(lang.getFrase(212,""+
                          per.getValor("MethodMatchOPTIMISM"))+"<br/>");
                out.newLine();
                
            } catch (JSimilException e) {
                //Ok.
            }
            out.write("</div><br/><br/><br/>");
            out.newLine();
        }
        out.write("<div class=\"results\">"+
                lang.getFrase(218)+"</div><br/>");
        out.newLine();
        out.write("<table class=\"resultados\">");
        out.newLine();
          out.write("<tr class=\"tittab\">");
          out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(270)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(271)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(216)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(219)+"</td>");
            out.newLine();
            out.write("<td class=\"tabct\">"+lang.getFrase(265)+"</td>");
            out.newLine();
          out.write("</tr>");
          out.newLine();
        int i;
        ProgramComparison r;
        String divc;
        double simil;
        double simil1;
        double simil2;
        String filename;
        String filename2;
        for (i = 0;i < res.size() && (limite == -1 || limite>0);i++) {
            if (limite != -1)
                limite--;
            
            r = res.get(i);
            
            if ((r.getSimilitud() >= pmin || pmin==0)
                    && (r.getSimilitud() <= pmax||pmax==1.0))
                divc = "obj";
            else
                divc = "notobj";
            if (i%2 == 0)
                divc += "i";
            filename = "";
            filename2 = "";
            if (divc.startsWith("obj") && resume<2) {
                out.flush();
                File ndir = new File(ruta2+"match"+i+File.separator);
                ndir.mkdir();
                filename = "match"+i+File.separator+"index.html";
                filename2 = "match"+i+File.separator+"index2.html";
                escribeMatch(ruta2,"match"+i+File.separator,"index.html",
                        "index2.html",
                        ".."+File.separator+"index.html#match"+i,
                        ".."+File.separator+"index.html",
                        ".."+File.separator+rutacss,
                        ".."+File.separator+rutalogo,
                        ".."+File.separator+rutaicono,
                        lang,
                        js,
                        df,
                        r,
                        resume);
            }
            out.write("<tr class=\""+divc+"\">");
            out.newLine();
              out.write("<td class=\"tabc\">");
              out.newLine();
              out.write("<a name=\"match"+i+"\"></a>");
              out.newLine();
              if (!filename.equals("")) {
                  out.write("<a href=\""+
                          filename.replace(File.separator,"/")+"\">");
                  out.newLine();
              }
              out.write(Tools.recorta(r.getNombre1())+"<br/>"+
                    Tools.recorta(r.getNombre2()));
              out.newLine();
              if (!filename.equals("")) {
                  out.write("</a>");
                  out.newLine();
              }
              out.write("</td>");
              out.newLine();
              out.write("<td class=\"tabcc\">");
              out.newLine();
              out.write("<a name=\"match"+i+"\"></a>");
              out.newLine();
              if (!filename.equals("")) {
                  out.write("<a href=\""+
                          filename.replace(File.separator,"/")+"\">");
                  out.newLine();
              }
              out.write(r.getTama1()+"<br/>"+r.getTama2());
              out.newLine();
              if (!filename.equals("")) {
                  out.write("</a>");
                  out.newLine();
              }
              out.write("</td>");
              out.newLine();
              simil = r.getSimilitud();
              if ((r.getSimilitud() >= pmin || pmin==0)
                      && (r.getSimilitud() <= pmax||pmax==1.0)) {
                  out.write("<td class=\"tabcc\">");
                  out.newLine();
                  if (!filename.equals("")) {
                      out.write("<a href=\""+
                              filename.replace(File.separator,"/")+"\">");
                      out.newLine();
                  }
                  out.write(df.format(r.getSimilitud()));
                  out.newLine();
                  if (!filename.equals("")) {
                      out.write("</a>");
                      out.newLine();
                  }
                  out.write("</td>");
                  out.newLine();
              }
              else
                out.write("<td class=\"tabcc\">&nbsp;</td>");
              out.newLine();
              if (simil < pmin) {
                  simil1 = 0;
                  simil2 = pmin;
              }
              else if (simil > pmax) {
                  simil1 = pmax;
                  simil2 = 1;
              }
              else {
                  simil1 = r.getSimilitud();
                  simil2 = r.getSimilitud()+perr;
                  if (simil2 > pmax)
                      simil2 = pmax;
              }
              if (simil1 < simil)
                  simil1 = simil;
              if (simil2 < simil1)
                  simil2 = simil1;
              if (simil1 < 0)
                  simil1 = 0;
              if (simil2 > 1)
                  simil2 = 1;
              simil2 += 0.0000001;
              double calcula = simil1-simil2;
              if (calcula<0) calcula *= -1;
              if (calcula < 0.000001) {
                  out.write("<td class=\"tabcc\">");
                  out.newLine();
                  if (!filename.equals("")) {
                      out.write("<a href=\""+
                              filename.replace(File.separator,"/")+"\">");
                      out.newLine();
                  }
                  out.write(df.format(simil1));
                  out.newLine();
                  if (!filename.equals("")) {
                      out.write("</a>");
                      out.newLine();
                  }
                  out.write("</td>");
                  out.newLine();
              }
              else {
                  out.write("<td class=\"tabcc\">");
                  out.newLine();
                  if (!filename.equals("")) {
                      out.write("<a href=\""+
                              filename.replace(File.separator,"/")+"\">");
                      out.newLine();
                  }
                  out.write(df.format(simil1)+"-"+df.format(simil2));
                  out.newLine();
                  if (!filename.equals("")) {
                      out.write("</a>");
                      out.newLine();
                  }
                  out.write("</td>");
                  out.newLine();
              }
              out.write("<td class=\"tabcc\">");
              out.newLine();

              if (!filename2.equals("")) {
                  out.write("<a href=\""+
                          filename2.replace(File.separator,"/")+
                          "\">"+lang.getFrase(269,""+
                          (r.getPaquetes1().size()))+
                          "</a><br/>");
                  out.write("<a href=\""+
                          filename2.replace(File.separator,"/")+
                          "\">"+lang.getFrase(269,""+
                          (r.getPaquetes2().size()))+
                          "</a>");
                  out.newLine();
              }
              else {
                  out.write("&nbsp;");
                  out.newLine();
              }
              out.write("</td>");
              out.newLine();
            out.write("</tr>");
            out.newLine();
        }
        out.write("</table>");
        out.newLine();
        footerHTML(out,null,null,lang,js);
        out.flush();
        out.close();
    }
    
}