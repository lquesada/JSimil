/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.profileeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel para huella de perfil de la aplicación.
 * @author elezeta
 */
public class FingerprintPanel extends JPanel {

    /**
     * Serializable
     */
    static final long serialVersionUID = 0L;    
    
    /**
     * Fuente.
     */
    Font fuente;

    /**
     * Valor de velocidad.
     */
    double velocidad;
    
    /**
     * Valor de detalle.
     */
    double detalle;
    
    /**
     * Valor de precisión.
     */
    double precision;
    
    /**
     * Valor de sensibilidad.
     */
    double sensibilidad;
    
    /**
     * Valor de asimilación.
     */
    double asimilacion;
    
    /**
     * Valor de especialización.
     */
    double especializacion;
    
    /**
     * Puntos x.
     */
    int xpuntos[];
    
    /**
     * Puntos y;
     */
    int ypuntos[];
    
    /**
     * Colores a usar.
     */
    Color[] colores;
    
    /**
     * Imagen.
     */
    BufferedImage w;
    
    /**
     * Constructor.
     * @param fuente Fuente a utilizar.
     * @.post Panel inicializado.
     */
    public FingerprintPanel(Font fuente) {
        super();
        this.fuente = fuente;
        velocidad = 0;
        detalle = 0;
        precision = 0;
        sensibilidad = 0;
        asimilacion = 0;
        especializacion = 0;
        xpuntos = new int[6];
        ypuntos = new int[6];  
        colores = new Color[4];
        colores[0] = new Color(0,0,0);
        colores[1] = new Color(100,0,0);
        colores[2] = new Color(160,0,0);
        colores[3] = new Color(60,0,0);
        w = new BufferedImage(170,170,BufferedImage.TYPE_INT_RGB);
    }
    
    /**
     * Cambia los valores a mostrar.
     * @param velocidad Velocidad a mostrar.
     * @param detalle Detalle a mostrar.
     * @param precision Precisión a mostrar.
     * @param sensibilidad Sensibilidad a mostrar.
     * @param asimilacion Asimilación a mostrar.
     * @param especializacion Especialización a mostrar.
     * @.post Valores cambiados e imagen redibujada.
     */
    void setValores(double velocidad,double detalle,double precision,
            double sensibilidad,double asimilacion,double especializacion) {
        this.velocidad = velocidad;
        this.detalle = detalle;
        this.precision = precision;
        this.sensibilidad = sensibilidad;
        this.asimilacion = asimilacion;
        this.especializacion = especializacion;
        repaint();        
    }
    
    /**
     * Dibujar linea con un ángulo y tamaño determinado.
     * @param g Contexto gráfico.
     * @param inix Punto x inicial.
     * @param iniy Punto y final.
     * @param angulo Ángulo.
     * @param tama Tamaño de la linea.
     * @.post Linea dibujada.
     */
    private void dibujaLinea(Graphics g,int inix,int iniy,double angulo,
            double tama) {
        g.drawLine(inix,iniy,obtenComponenteX(angulo,tama,inix),
                obtenComponenteY(angulo,tama,iniy));    
    }
    
    /**
     * Obtiene el valor x final a partir de un ángulo, tamaño y valor inicial.
     * @param angulo Angulo.
     * @param tama Tamaño.
     * @param inix X inicial.
     * @return Punto x final.
     */
    private int obtenComponenteX(double angulo,double tama,int inix) {
        angulo = Math.toRadians(angulo-90);
        return (int)(inix+Math.sin(angulo)*tama);
    }

    /**
     * Obtiene el valor y final a partir de un ángulo, tamaño y valor inicial.
     * @param angulo Angulo.
     * @param tama Tamaño.
     * @param iniy Y inicial.
     * @return Punto y final.
     */
    private int obtenComponenteY(double angulo,double tama,int iniy) {
        angulo = Math.toRadians(angulo-90);
        return (int)(iniy+Math.cos(angulo)*tama);
    }
    
    /**
     * Dibujar el componente.
     * @param ga Contexto gráfico.
     * @.post Componente dibujado.
     */
    @Override
    public void paintComponent(Graphics ga) {
        Graphics g = w.getGraphics();
        super.paintComponent(g);
        g.setColor(colores[0]);
        g.setFont(fuente);

        dibujaLinea(g,85,85,0,62);
        g.drawString("SP",7,90);
        xpuntos[0] = obtenComponenteX(0,(int)(61.0*velocidad),85);
        ypuntos[0] = obtenComponenteY(0,(int)(61.0*velocidad),85);

        dibujaLinea(g,85,85,60,62);
        g.drawString("AS",43,151);
        xpuntos[1] = obtenComponenteX(60,(int)(61.0*asimilacion),85);
        ypuntos[1] = obtenComponenteY(60,(int)(61.0*asimilacion),85);
        
        dibujaLinea(g,85,85,120,62);
        g.drawString("SC",113,151);
        xpuntos[2] = obtenComponenteX(120,(int)(61.0*especializacion),85);
        ypuntos[2] = obtenComponenteY(120,(int)(61.0*especializacion),85);
        
        dibujaLinea(g,85,85,180,62);
        g.drawString("PR",150,90);
        xpuntos[3] = obtenComponenteX(180,(int)(61.0*precision),85);
        ypuntos[3] = obtenComponenteY(180,(int)(61.0*precision),85);
        
        dibujaLinea(g,85,85,240,62);
        g.drawString("SE",113,27);
        xpuntos[4] = obtenComponenteX(240,(int)(61.0*sensibilidad),85);
        ypuntos[4] = obtenComponenteY(240,(int)(61.0*sensibilidad),85);
        
        dibujaLinea(g,85,85,300,62);
        g.drawString("DE",43,27);
        xpuntos[5] = obtenComponenteX(300,(int)(61.0*detalle),85);
        ypuntos[5] = obtenComponenteY(300,(int)(61.0*detalle),85);
        
        g.setColor(colores[2]);
        g.fillPolygon(xpuntos,ypuntos, 6);
        g.setColor(colores[3]);
        g.drawPolygon(xpuntos,ypuntos, 6);

        g.setColor(colores[1]);

        int i;
        for (i = 0;i < 6;i++)
            g.drawLine(85,85,xpuntos[i],ypuntos[i]);
        
        ((Graphics2D)ga).drawImage(w,null,null);
    }
    
    /**
     * Exportar imagen.
     * @param ruta Ruta a exportar imagen.
     * @exception IOException Excepción si exportando.
     * @return true si exito, false si error.
     * @.post Imagen exportada.
     */
    public boolean exportarImagen(String ruta) throws IOException {
        return ImageIO.write(w,"png",new File(ruta));
    }
    
}
