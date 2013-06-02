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
import com.jsimil.core.Location;
import com.jsimil.core.MatchingProfile;
import com.jsimil.core.ProgramComparison;

/**
 * Exportador de XML.
 * @author elezeta
 */
abstract public class XMLExport {

    /**
     * Escribe los elementos recursivamente al fichero de salida.
     * @param lista Lista de elementos a escribir.
     * @param ini Posición a partir de la cual escribir.
     * @param prof Profundidad a escribir.
     * @param out Fichero a escribir.
     * @param resume Resumir?
     * @exception IOException Error escribiendo.
     * @.post Elementos escritos.
     */
    static private void escribeElementos(List<Matching> lista,int ini,
            int prof,BufferedWriter out,boolean resume) throws IOException {
        int i,j,k;
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        ds.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("0.00000",ds);
        Matching cc;
        CodeElement ele1;
        CodeElement ele2;
        Location cod;
        int codlin;
        String codigo;
        int codini;
        i = ini;
        List<String> dat;
       
        while (i < lista.size()) {
            cc = lista.get(i);
            if (cc.getProfundidad() == prof) {
                ele1 = cc.getElementoA();
                ele2 = cc.getElementoB();
                if (ele1 != null && ele2 != null) {
                    if (ele1.getTipo().equals("SBloque")) {
                        out.write("<blockmatch>");
                        out.newLine();
                    }
                    else if (ele1.getTipo().equals("SMetodo")) {
                        out.write("<methodmatch>");
                        out.newLine();
                    }
                    else if (ele1.getTipo().equals("SClase")) {
                        out.write("<classmatch>");
                        out.newLine();
                    }

                    out.write("<element1>");
                    out.newLine();
                      out.write("<name>"+ele1.getNombre()+
                                "</name>");
                      out.newLine();
                      out.write("<parentpercentage>"+
                                df.format(ele1.getPorcentajePadre())+
                                "</parentpercentage>");
                      out.newLine();
                      out.write("<programpercentage>"+
                                df.format(ele1.getPorcentajePrograma())+
                                "</programpercentage>");
                      out.newLine();
                      if (!resume) {
                          if (ele1.getTipo().equals("SBloque")) {

                              cod = ele1.getLugarCodigoOriginal();
                              if (cod != null) {
                                  codlin = cod.getLineaFin()-cod.getLineaIni()+1;
                                  codini = cod.getLineaIni();
                                  out.write("<sourcecode>");
                                  out.write("<lines>"+codlin+"</lines>");
                                  dat = cod.getFichero().getDatos();
                                  for (j = 0;j < codlin;j++) {
                                      codigo = dat.get(codini+j);
                                      out.write("<line"+j+">"+
                                            Tools.codifica2(codigo)+"</line"+j+">");
                                  }
                                  out.write("</sourcecode>");
                              }
                              cod = ele1.getLugarCodigoDesensamblado();
                              if (cod != null) {
                                  codlin = cod.getLineaFin()-cod.getLineaIni()+1;
                                  codini = cod.getLineaIni();
                                  out.write("<disassembledcode>");
                                  out.write("<lines>"+codlin+"</lines>");
                                  dat = cod.getFichero().getDatos();
                                  for (j = 0;j < codlin;j++) {
                                      codigo = dat.get(codini+j);
                                      out.write("<line"+j+">"+
                                             Tools.codifica2(codigo)+"</line"+j+">");
                                  }
                                  out.write("</disassembledcode>");
                              }
                          }
                      }
                    out.write("</element1>");
                    out.newLine();
                    out.write("<element2>");
                    out.newLine();
                      out.write("<name>"+ele2.getNombre()+
                                "</name>");
                      out.newLine();
                      out.write("<parentpercentage>"+
                                df.format(ele2.getPorcentajePadre())+
                                "</parentpercentage>");
                      out.newLine();
                      out.write("<programpercentage>"+
                                df.format(ele2.getPorcentajePrograma())+
                                "</programpercentage>");
                      out.newLine();
                      if (!resume) {
                          if (ele2.getTipo().equals("SBloque")) {

                              cod = ele2.getLugarCodigoOriginal();
                              if (cod != null) {
                                  codlin = cod.getLineaFin()-cod.getLineaIni()+1;
                                  codini = cod.getLineaIni();
                                  out.write("<sourcecode>");
                                  out.write("<lines>"+codlin+"</lines>");
                                  dat = cod.getFichero().getDatos();
                                  for (j = 0;j < codlin;j++) {
                                      codigo = dat.get(codini+j);
                                      out.write("<line"+j+">"+
                                             Tools.codifica2(codigo)+"</line"+j+">");
                                  }
                                  out.write("</sourcecode>");
                              }
                              cod = ele2.getLugarCodigoDesensamblado();
                              if (cod != null) {
                                  codlin = cod.getLineaFin()-cod.getLineaIni()+1;
                                  codini = cod.getLineaIni();
                                  out.write("<disassembledcode>");
                                  out.write("<lines>"+codlin+"</lines>");
                                  dat = cod.getFichero().getDatos();
                                  for (j = 0;j < codlin;j++) {
                                      codigo = dat.get(codini+j);
                                      out.write("<line"+j+">"+
                                             Tools.codifica2(codigo)+"</line"+j+">");
                                  }
                                  out.write("</disassembledcode>");
                              }
                          }
                      }
                    out.write("</element2>");
                    out.newLine();
                    out.write("<similarity>"+df.format(cc.getSimilitud())+
                              "</similarity>");
                    out.newLine();
                    out.write("<elementsmatches>");
                    out.newLine();
                    escribeElementos(lista,i+1,prof+1,out,resume);
                    out.write("</elementsmatches>");
                    out.newLine();
                    if (ele1.getTipo().equals("SBloque")) {
                        out.write("</blockmatch>");
                        out.newLine();
                    }
                    else if (ele1.getTipo().equals("SMetodo")) {
                        out.write("</methodmatch>");
                        out.newLine();
                    }
                    else if (ele1.getTipo().equals("SClase")) {
                        out.write("</classmatch>");
                        out.newLine();
                    }                
                }
            }
            else if (cc.getProfundidad() < prof)
                return;
            i++;
        }
    }
    
    /**
     * Exporta los resultados.
     * @param ruta Ruta a donde exportar los resultados.
     * @param overwrite Sobreescribir los resultados?
     * @param writeprofile Escribir el perfil en los resultados?
     * @param res Resultados a utilizar.
     * @param per Perfil a utilizar.
     * @param resume Resumir?
     * @param limite Límite de resultados a exportar, si -1 no hay límite.
     * @.post Escrito fichero.
     * @exception IOException No se puede crear el fichero.
     */
    static public void export(String ruta,boolean overwrite,
            boolean writeprofile,List<ProgramComparison> res,MatchingProfile per,
            boolean resume,int limite)
    throws IOException {
        int i;
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
        out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
        out.newLine();
        out.write("<results>");
        out.newLine();
        ProgramComparison r;
        List<Matching> lista;
        Matching cc;
        out.write("<matches>");
        out.newLine();
        int prof;
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
            out.write("<match>");
            out.newLine();
            r = res.get(i);
            lista = r.getCoincidencias();
            out.write("<program1>"+r.getNombre1()+"</program1>");
            out.newLine();
            if (!r.getNombre1().equals(r.getNombre2())) {
                out.write("<program2>"+r.getNombre2()+"</program2>");
                out.newLine();
            }
            out.write("<similarity>"+df.format(r.getSimilitud())
                    +"</similarity>");
            out.newLine();
            if ((r.getSimilitud() >= pmin || pmin==0)
                    && (r.getSimilitud() <= pmax||pmax==1.0)) {
                out.write("<elementsmatches>");
                out.newLine();
                escribeElementos(lista,0,0,out,resume);
                out.write("</elementsmatches>");
            }
            out.newLine();
            out.write("</match>");
            out.newLine();
        }
        out.write("</matches>");
        out.newLine();
      
        if (writeprofile) {
            try {
                out.write("<profile>");
                out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ProgMatchMIN</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ProgMatchMIN")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ProgMatchMAX</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ProgMatchMAX")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ProgMatchERROR</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ProgMatchERROR")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ProgMatchLIMIT</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ProgMatchLIMIT")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ReflexiveMATCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ReflexiveMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>DifferenceSEARCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("DifferenceSEARCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ProgMatchOPTIMISM</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ProgMatchOPTIMISM")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ClassMatchOPTIMISM</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ClassMatchOPTIMISM")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>MethodMatchOPTIMISM</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("MethodMatchOPTIMISM")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>BlockAllowMULTIMATCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("BlockAllowMULTIMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>MethodAllowMULTIMATCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("MethodAllowMULTIMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ClassAllowMULTIMATCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ClassAllowMULTIMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ClassSAMENAMEMATCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ClassSAMENAMEMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();           
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>MethodSAMENAMEMATCH</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("MethodSAMENAMEMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>MethodOfClassSAMENAMEMATCH</name>");
                    out.newLine();
                    out.write("<value>"+
                            per.getValor("MethodOfClassSAMENAMEMATCH")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                  out.write("<attribute>");
                  out.newLine();
                    out.write("<name>ProgMatchDIFFERENCE</name>");
                    out.newLine();
                    out.write("<value>"+per.getValor("ProgMatchDIFFERENCE")+
                            "</value>");
                    out.newLine();
                  out.write("</attribute>");
                  out.newLine();                  
                out.write("</profile>");
                out.newLine();
            }
            catch (JSimilException e) {
                //Ok.
            }
        }
        out.write("</results>");
        out.newLine();
        out.flush();
        out.close();
    }
    
}
