/*
 * JSimil. 2007-2010 Luis Quesada.
 */

package com.jsimil.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.jsimil.core.CodeElement;
import com.jsimil.core.Matching;
import com.jsimil.core.CodeFile;
import com.jsimil.core.Location;
import com.jsimil.core.ProgramComparison;

/**
 * Exportador de diff.
 * @author elezeta
 */
abstract public class DiffExport {

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
     * Exporta los resultados.
     * @param ruta Ruta a donde exportar los resultados.
     * @param overwrite Sobreescribir los resultados?
     * @param res Resultados a utilizar.
     * @param limite Límite de resultados a exportar, si -1 no hay límite.
     * @param diffsimil Similitud mínima para considerar coincidencia.
     * @.post Escrito fichero.
     * @exception IOException No se puede crear el fichero.
     */
    static public void export(String ruta,boolean overwrite,
            List<ProgramComparison> res,int limite,double diffsimil)
    throws IOException {
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
        f.mkdir();
        String ruta2 = ""+ruta;
        if (!ruta2.endsWith(File.separator))
            ruta2 += File.separator;
        writeOVW(ruta2+"jsimil.ovw");

        int i;
        ProgramComparison r;
        for (i = 0;i < res.size() && (limite == -1 || limite>0);i++) {
            if (limite != -1)
                limite--;
            r = res.get(i);
            int j;
            Matching coin;
            boolean ok = true;
            for (j = 0;j < r.getCoincidencias().size();j++) {
                coin = r.getCoincidencias().get(j);
                if (coin.getElementoA() != null) {
                    if (coin.getElementoA().getLugarCodigoOriginal() == null)
                    ok = false;
                }
                if (coin.getElementoB() != null) {
                    if (coin.getElementoB().getLugarCodigoOriginal() == null)
                    ok = false;
                }
            }
            if (ok)
                escribeDiff(ruta2,r,diffsimil);
        }
    }
 
    /**
     * Escribir dos ficheros de resultados a la ruta indicada.
     * @param ruta Ruta a escribir.
     * @param r Fichero de resultado del que extraer los dos resultados.
     * @param diffsimil Similitud mínima para considerar coincidencia.
     * @.post Datos escritos.
     */
    static private void escribeDiff(String ruta,ProgramComparison r,double diffsimil) 
      throws IOException {
        String ab = ruta+r.getNombre1()+"-"+r.getNombre2()+".diff";
        String ba = ruta+r.getNombre2()+"-"+r.getNombre1()+".diff";
        BufferedWriter outab = new BufferedWriter(new FileWriter(ab));
        BufferedWriter outba = new BufferedWriter(new FileWriter(ba));
        BufferedWriter out;
        BufferedWriter outotro;
        //Vamos a hacerlo fichero a fichero.
        //Obtener listado de ficheros
        HashMap<String,CodeFile> listaficherosa =
                new HashMap<String,CodeFile>();
        HashMap<String,CodeFile> listaficherosb =
                new HashMap<String,CodeFile>();
        int i;
        int nlineas;
        Matching coin;
        Location cod;
        String rutaf;
        for (i = 0;i < r.getCoincidencias().size();i++) {
            coin = r.getCoincidencias().get(i);
            if (coin.getElementoA() != null) {
                cod = coin.getElementoA().getLugarCodigoOriginal();
                if (cod != null) {
                    rutaf = cod.getFichero().getRutaOrig();
                    if (!listaficherosa.containsKey(rutaf))
                        listaficherosa.put(rutaf,cod.getFichero());
                }
            }
            if (coin.getElementoB() != null) {
                cod = coin.getElementoB().getLugarCodigoOriginal();
                if (cod != null) {
                    rutaf = cod.getFichero().getRutaOrig();
                    nlineas = cod.getFichero().getDatos().size();
                    if (!listaficherosb.containsKey(rutaf))
                        listaficherosb.put(rutaf,cod.getFichero());
                }
            }
        }
        //Para los dos elementos...
        int ki;
        HashMap<String,CodeFile> listaficheros;
        String actual;
        CodeElement ele;
        int j;
        double igual[];
        CodeFile fich;
        CodeFile fichotro;
        CodeFile lastfichotroa = null,lastfichotrob = null;
        int lineaini;
        int lineafin;
        int k;
        double sim;
        for (ki = 0;ki < 2;ki++) {
            if (ki == 0)
                listaficheros = listaficherosa;
            else
                listaficheros = listaficherosb;
            
            //Para cada fichero...
            Object[] nombresficheros =
                    listaficheros.keySet().toArray();

            for (i = 0;i < nombresficheros.length;i++) {
                boolean info = false;
                boolean first = true;
                int deriva = 0;
                actual = (String)nombresficheros[i];
                //Genero la lista de lineas.
                fich = listaficheros.get(actual);
                nlineas = fich.getDatos().size();
                igual = new double[nlineas];
                for (j = 0;j < nlineas;j++)
                    igual[j] = 1.0;
                
                fichotro = null;
                boolean tienesubelementos = true;
                //Busco los elementos sin subelementos que estén en ese fichero.
                for (j = 0;j < r.getCoincidencias().size();j++) {
                    coin = r.getCoincidencias().get(j);
                    sim = coin.getSimilitud();
                    if (ki == 0) {
                        ele = coin.getElementoA();
                        if (coin.getElementoB()!=null) {
                            fichotro = coin.getElementoB()
                                    .getLugarCodigoOriginal().getFichero();
                            lastfichotrob = fichotro;
                        }
                    }
                    else {
                        ele = coin.getElementoB();
                        if (coin.getElementoA()!=null) {
                            fichotro = coin.getElementoA()
                                    .getLugarCodigoOriginal().getFichero();
                            lastfichotroa = fichotro;
                        }
                    }

                    if (ele != null) {
                       
                        if (j==r.getCoincidencias().size()-1)
                            tienesubelementos = false;
                        else if (r.getCoincidencias().get(j+1)
                                .getProfundidad() <= coin.getProfundidad())
                            tienesubelementos = false;
                        if (!tienesubelementos &&
                            ele.getLugarCodigoOriginal().getFichero() ==
                            fich) {
                           //Si la similitud de este bloque es menor a
                           //diff simil, marco como distinto.
                            lineaini = ele.getLugarCodigoOriginal()
                                    .getLineaIni();
                            lineafin = ele.getLugarCodigoOriginal()
                                    .getLineaFin(); 
                            info = true;
                            for (k = lineaini;k <= lineafin;k++) {
                                if (igual[k]-0.01>sim) {
                                    igual[k] = sim;
                                }
                            }
                        }                            
                    }
                }
            
                if (ki == 0) {
                    out = outba;
                    outotro = outab;
                }
                else {
                    out = outab;
                    outotro = outba;
                }
                if (fichotro == null) {
                    if (ki == 0)
                        fichotro = lastfichotroa;
                    else
                        fichotro = lastfichotrob;
                    if (fichotro == null)
                        fichotro = fich;
                }
                if (info) {
                    //Buscar un false. ini.
                    //Buscar un true o final. fin.
                    //Escribir como diferencias.
                    int ini = -1;
                    int fin = -1;
                    for (j = 0;j < nlineas;j++) {
                        if (ini == -1) {
                            if (igual[j]+0.01 < diffsimil)
                                ini = j;
                        }
                        if (ini != -1 && j == nlineas-1)
                            fin = j;
                        else if (ini != -1 && igual[j+1]+0.01 >= diffsimil)
                            fin = j;
                        if (fin != -1) {
                            if (first) {
                                out.write("diff "+fichotro.getRutaOrig()+" "+
                                                  fich.getRutaOrig());
                                out.newLine();
                                outotro.write("diff "+fich.getRutaOrig()+" "+
                                                  fichotro.getRutaOrig());
                                outotro.newLine();
                                first = false;
                            }
                            ini++;
                            fin++;
                            int anterior = ini-1-deriva;
                            deriva += fin-ini+1;
                            if (ini >= fich.getDatos().size())
                                ini = fich.getDatos().size()-1;
                            if (fin >= fich.getDatos().size())
                                fin = fich.getDatos().size()-1;
                            
                            if (ini != fin)
                                out.write(anterior+"a"+ini+","+fin);
                            else
                                out.write(anterior+"a"+ini);
                            out.newLine();
                            if (ini != fin)
                                outotro.write(ini+","+fin+"d"+anterior);
                            else
                                outotro.write(ini+"d"+anterior);
                            outotro.newLine();
                            for (k = ini;k <= fin;k++) {
                                out.write("> "+fich.getDatos().get(k));
                                out.newLine();
                                outotro.write("< "+fich.getDatos().get(k));
                                outotro.newLine();
                            }
                            ini = -1;
                            fin = -1;                            
                        }
                    }
                }

            }
            
        }
        outab.flush();
        outab.close();        
        outba.flush();
        outba.close();
    }
}
