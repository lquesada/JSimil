/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import com.jsimil.core.CodeElement;
import com.jsimil.core.Matching;
import com.jsimil.core.JSimilException;
import com.jsimil.core.MatchingProfile;
import com.jsimil.core.ProgramComparison;

/**
 * Exportador de TXT.
 * @author elezeta
 */
abstract public class TXTExport {

    /**
     * Exporta los resultados.
     * @param ruta Ruta a donde exportar los resultados.
     * @param overwrite Sobreescribir los resultados?
     * @param writeprofile Escribir el perfil en los resultados?
     * @param res Resultados a utilizar.
     * @param per Perfil a utilizar.
     * @param resume Resumir?
     * @param limite Limite de resultados a exportar, si -1 no hay límite.
     * @.post Escrito fichero.
     * @exception IOException No se puede crear el fichero.
     */
    static public void export(String ruta,boolean overwrite,
            boolean writeprofile,List<ProgramComparison> res,MatchingProfile per,
            boolean resume,int limite)
    throws IOException {
        int i,j,k,n;
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        ds.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("0.00000",ds);
        File f = new File(ruta);
        if (f.exists() && !overwrite) {
            throw new IOException("exists");
        }
        FileWriter fw;
        fw = new FileWriter(f);
        BufferedWriter out;
        out = new BufferedWriter(fw);
        out.write("-----------------------------------------");
        out.newLine();
        ProgramComparison r;
        List<Matching> lista;
        Matching cc;
        String cad;
        double pmin = 0;
        double pmax = 0;
        try {
            pmin = per.getValor("ProgMatchMIN");
            pmax = per.getValor("ProgMatchMAX");
        } catch (JSimilException e) {
            //Ok.
        } 
        for (i = 0;i < res.size() && (limite == -1 || limite>0);i++) {
            if (limite != -1)
                limite--;
            r = res.get(i);
            lista = r.getCoincidencias();
            cad = "0P"+lista.size();
            while (cad.length() < 6)
                cad = cad+" ";
            out.write(cad+r.getNombre1()+" - "+
                               r.getNombre2()+": "+
                               df.format(r.getSimilitud()));
            out.newLine();
            if (!resume && (r.getSimilitud() >= pmin || pmin==0)
                    && (r.getSimilitud() <= pmax||pmax==1.0)) {
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
                        if (ele1.getTipo().equals("SClase")) {
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
                        out.write(cad+
                                ele1.getNombre()+" ("+
                                df.format(ele1.getPorcentajePadre())+","+
                                df.format(ele1.getPorcentajePrograma())+") - "+
                                ele2.getNombre()+" ("+
                                df.format(ele2.getPorcentajePadre())+","+
                                df.format(ele2.getPorcentajePrograma())+"):"+
                                df.format(cc.getSimilitud()));                            
                        out.newLine();
                    }
                }
            }
        }
        out.write("-----------------------------------------");
        out.newLine();
        if (writeprofile) {
            try {
                out.write("#########################################");
                out.newLine();
                out.write("ProgMatchMIN "+pmin);
                out.newLine();
                out.write("ProgMatchMAX "+pmax);
                out.newLine();
                out.write("ProgMatchERROR "+per.getValor("ProgMatchERROR"));
                out.newLine();
                out.write("ProgMatchLIMIT "+per.getValor("ProgMatchLIMIT"));
                out.newLine();
                out.write("ReflexiveMATCH "+per.getValor("ReflexiveMATCH"));
                out.newLine();
                out.write("DifferenceSEARCH "+per.getValor("DifferenceSEARCH"));
                out.newLine();
                out.write("ProgMatchOPTIMISM "+
                    per.getValor("DifferenceSEARCH"));
                out.newLine();
                out.write("ClassMatchOPTIMISM "+
                    per.getValor("ClassMatchOPTIMISM"));
                out.newLine();
                out.write("MethodMatchOPTIMISM "+
                    per.getValor("MethodMatchOPTIMISM"));
                out.newLine();
                out.write("BlockAllowMULTIMATCH "+
                    per.getValor("BlockAllowMULTIMATCH"));
                out.newLine();
                out.write("MethodAllowMULTIMATCH "+
                    per.getValor("MethodAllowMULTIMATCH"));
                out.newLine();
                out.write("ClassAllowMULTIMATCH "+
                    per.getValor("ClassAllowMULTIMATCH"));
                out.newLine();
                out.write("ClassSAMENAMEMATCH "+
                    per.getValor("ClassSAMENAMEMATCH"));
                out.newLine();
                out.write("MethodSAMENAMEMATCH "+
                    per.getValor("MethodSAMENAMEMATCH"));
                out.newLine();
                out.write("MethodOfClassSAMENAMEMATCH "+
                    per.getValor("MethodOfClassSAMENAMEMATCH"));
                out.newLine();
                out.write("ProgMatchDIFFERENCE "+
                    per.getValor("ProgMatchDIFFERENCE"));
                out.newLine();
                out.write("#########################################");
                out.newLine();
            }
            catch (JSimilException e) {
                //Ok.
            }
        }
        out.flush();
        out.close();
    }
    
}
