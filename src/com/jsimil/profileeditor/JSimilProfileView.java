/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.profileeditor;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.Icon;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;

import com.jsimil.core.DefaultJSimilProfileFactory;
import com.jsimil.core.JSimilException;
import com.jsimil.core.MatchingProfile;
import com.jsimil.core.ExceptionType;
import com.jsimil.languages.Language;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Ventana principal de la aplicación.
 * @author elezeta
 */
public class JSimilProfileView extends FrameView {

    /**
     * Idioma
     */
    Language lang;
    
    /**
     * Perfil.
     */
    MatchingProfile perfil;
    
    /**
     * Directorio actual.
     */
    File dir;
    
    /**
     * Está modificado el perfil?
     */
    boolean modificado;
    
    /**
     * Ruta al fichero actual abierto.
     */
    String actual;
    
    /**
     * Fuente normal.
     */
    Font fuente;
    
    /**
     * Fuente negrita.
     */
    Font fuente2;

    /**
     * Fuente itálica.
     */
    Font fuente3;

    /**
     * Icono no modificado.
     */
    Icon icnomodificado;
    
    /**
     * Icono modificado.
     */
    Icon icmodificado;

    /**
     * Formato de números decimales para las propiedades.
     */
    DecimalFormat df;

    /**
     * Formato de números decimales para los atributos.
     */
    DecimalFormat df2;
    
    /**
     * Formato de números decimales para los atributos.
     */
    DecimalFormat df3;
    
    /**
     * Si la ventana se está actualizando.
     */
    boolean quieto;
    
    /**
     * Hashcode del perfil original.
     */
    int hashcode;
    
    /**
     * Atributos de documento.
     */
    SimpleAttributeSet attr;
    
    /**
     * Escuchador de salida.
     */
    WindowListener wl = new WindowAdapter() {
        
        /**
         * Escuchador del evento de salida.
         * @param e Evento.
         * @.post Salida tratada.
         */
        @Override
        public void windowClosing(WindowEvent e) {
            quitPropioInseguro();
        }
        
    };
    
    /**
     * Constructor
     * @param app Aplicación.
     * @param lang Idioma a utilizar.
     * @param profileload Si debe intentar cargarse un perfil desde fichero.
     * @.post Aplicación cargada y funcionando.
     */
    public JSimilProfileView(SingleFrameApplication app,Language lang,
            String profileload) {
        super(app);
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        ds.setGroupingSeparator(' ');
        df = new DecimalFormat("0.0000",ds);
        df2 = new DecimalFormat("0.00",ds);
        df3 = new DecimalFormat("0",ds);
        quieto = false;
        this.lang = lang;
        fuente = new Font("Dialog",Font.PLAIN,11);
        fuente2 = new Font("Dialog",Font.BOLD,11);
        fuente3 = new Font("Dialog",Font.ITALIC,11);
        icnomodificado = new javax.swing.ImageIcon(getClass().getResource(
                "/jsimilprofile/resources/noeditado.png"));
        icmodificado = new javax.swing.ImageIcon(getClass().getResource(
                "/jsimilprofile/resources/editado.png"));
        attr=new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_JUSTIFIED);

        initComponents();
        manager("entra");
        if (System.getProperty("java.version").compareTo("1.6") >= 0)
            ayudaTextArea.setParagraphAttributes(attr,false);

        this.getFrame().setResizable(false);
        this.getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.dir = new File(".");
        this.getFrame().addWindowListener(wl);
        this.actual = null;
        if (profileload != null) {
            try {
                perfil = new MatchingProfile();
                perfil.load(profileload);
                System.err.println(lang.getFrase(74,profileload));
                actual = profileload;
                this.dir = new File(profileload).getParentFile();
            } catch (JSimilException e) {
                if (e.getTipo() == ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                    System.err.println(lang.getFrase(75,profileload));
                else if (e.getTipo() == ExceptionType.FORMATO_INCORRECTO)
                    System.err.println(lang.getFrase(76,profileload));
                perfil = DefaultJSimilProfileFactory.defecto();
            }
            System.err.println("");
        }
        else
            perfil = DefaultJSimilProfileFactory.defecto();
        modificado = false;
        hashcode = perfil.hashCode();
        actualizaActual();
        ajustarPerfil();
        perfilAVentana();
        dibujarPerfil();
    }

    /**
     * Mostrar ventana acerca de.
     * @.post Ventana acerca de mostrada.
     */
    @Action
    public void showAboutBox() {
        managerCambio();
        if (aboutBox == null) {
            JFrame mainFrame = JSimilProfile.getApplication().getMainFrame();
            aboutBox = new JSimilProfileAboutBox(mainFrame,lang);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        JSimilProfile.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        huellaPanel = new FingerprintPanel(fuente);
        huellaLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        butReflexiveNo = new javax.swing.JRadioButton();
        butReflexiveSi = new javax.swing.JRadioButton();
        butDifferenceSearchNo = new javax.swing.JRadioButton();
        butDifferenceSearchSi = new javax.swing.JRadioButton();
        butReturnNull = new javax.swing.JCheckBox();
        butProgMatchMinMaxLabel = new javax.swing.JLabel();
        butProgMatchErrorLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        butProgMatchMinMaxMinLabel = new javax.swing.JLabel();
        butProgMatchMinMaxMinBar = new javax.swing.JSlider();
        butProgMatchMinMaxMinText = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        butProgMatchMinMaxMaxLabel = new javax.swing.JLabel();
        butProgMatchMinMaxMaxBar = new javax.swing.JSlider();
        butProgMatchMinMaxMaxText = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        butProgMatchErrorLabel2 = new javax.swing.JLabel();
        butProgMatchErrorBar = new javax.swing.JSlider();
        butProgMatchErrorText = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        butOptimismClassLabel = new javax.swing.JLabel();
        butOptimismClassBar = new javax.swing.JSlider();
        butOptimismClassText = new javax.swing.JTextField();
        jPanel38 = new javax.swing.JPanel();
        butOptimismMethodLabel = new javax.swing.JLabel();
        butOptimismMethodBar = new javax.swing.JSlider();
        butOptimismMethodText = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        butOptimismProgLabel = new javax.swing.JLabel();
        butOptimismProgBar = new javax.swing.JSlider();
        butOptimismProgText = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        butClassThresholdMaxLabel = new javax.swing.JLabel();
        butClassThresholdMaxBar = new javax.swing.JSlider();
        butClassThresholdMaxText = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        butClassThresholdMinLabel = new javax.swing.JLabel();
        butClassThresholdMinBar = new javax.swing.JSlider();
        butClassThresholdMinText = new javax.swing.JTextField();
        butOptimism = new javax.swing.JLabel();
        butClassThreshold = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        butClassSameNameMatch = new javax.swing.JCheckBox();
        butClassAllowMultiMatch = new javax.swing.JCheckBox();
        butMethodSameNameMatch = new javax.swing.JCheckBox();
        butMethodOfClassSameNameMatch = new javax.swing.JCheckBox();
        butMethodAllowMultiMatch = new javax.swing.JCheckBox();
        butBlockAllowMultiMatch = new javax.swing.JCheckBox();
        butMinimumInstructionLabel = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        butMinimumInstructionLabel1 = new javax.swing.JLabel();
        butMinimumInstructionClass = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        butMinimumInstructionLabel2 = new javax.swing.JLabel();
        butMinimumInstructionMethod = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        butMinimumInstructionLabel3 = new javax.swing.JLabel();
        butMinimumInstructionBlock = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        butDifferenceProgramLabel = new javax.swing.JLabel();
        butDifferenceProgramBar = new javax.swing.JSlider();
        butDifferenceProgramText = new javax.swing.JTextField();
        butDifference = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        butDifferenceClassLabel = new javax.swing.JLabel();
        butDifferenceClassBar = new javax.swing.JSlider();
        butDifferenceClassText = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        butDifferenceProgramMethod = new javax.swing.JLabel();
        butDifferenceProgramMethodBar = new javax.swing.JSlider();
        butDifferenceProgramMethodText = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        butDifferenceClassMethodLabel = new javax.swing.JLabel();
        butDifferenceClassMethodBar = new javax.swing.JSlider();
        butDifferenceClassMethodText = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        butLimitLabel2 = new javax.swing.JLabel();
        butLimitClassBar = new javax.swing.JSlider();
        butLimitClassText = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        butDifferenceBlockLabel = new javax.swing.JLabel();
        butDifferenceBlockBar = new javax.swing.JSlider();
        butDifferenceBlockText = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        butLimitLabel3 = new javax.swing.JLabel();
        butLimitMethodBar = new javax.swing.JSlider();
        butLimitMethodText = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        butLimitLabel1 = new javax.swing.JLabel();
        butLimitProgramBar = new javax.swing.JSlider();
        butLimitProgramText = new javax.swing.JTextField();
        butLimit = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        butAcceptableProgramMethod = new javax.swing.JLabel();
        butAcceptableProgramMethodBar = new javax.swing.JSlider();
        butAcceptableProgramMethodText = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        butAcceptableBlock = new javax.swing.JLabel();
        butAcceptableBlockBar = new javax.swing.JSlider();
        butAcceptableBlockText = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        butAcceptableClassMethod = new javax.swing.JLabel();
        butAcceptableClassMethodBar = new javax.swing.JSlider();
        butAcceptableClassMethodText = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        butAcceptableClass = new javax.swing.JLabel();
        butAcceptableClassBar = new javax.swing.JSlider();
        butAcceptableClassText = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        butMinimumProgramMethod = new javax.swing.JLabel();
        butMinimumProgramMethodBar = new javax.swing.JSlider();
        butMinimumProgramMethodText = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        butMinimumBlock = new javax.swing.JLabel();
        butMinimumBlockBar = new javax.swing.JSlider();
        butMinimumBlockText = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        butMinimumClassMethod = new javax.swing.JLabel();
        butMinimumClassMethodBar = new javax.swing.JSlider();
        butMinimumClassMethodText = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        butMinimumClass = new javax.swing.JLabel();
        butMinimumClassBar = new javax.swing.JSlider();
        butMinimumClassText = new javax.swing.JTextField();
        butAcceptable = new javax.swing.JLabel();
        butMinimum = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        butBlockWeightLabel0 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        butBlockWeightLabel8 = new javax.swing.JLabel();
        butBlockWeightLabel7 = new javax.swing.JLabel();
        butBlockWeightLabel6 = new javax.swing.JLabel();
        butBlockWeightLabel5 = new javax.swing.JLabel();
        butBlockWeightLabel4 = new javax.swing.JLabel();
        butBlockWeightLabel3 = new javax.swing.JLabel();
        butBlockWeightLabel9 = new javax.swing.JLabel();
        butBlockWeightLabel10 = new javax.swing.JLabel();
        butBlockWeightInvokeStatic = new javax.swing.JTextField();
        butBlockWeightInvokeVirtual = new javax.swing.JTextField();
        butBlockWeightInvokeSpecial = new javax.swing.JTextField();
        butBlockWeightMonitorEnter = new javax.swing.JTextField();
        butBlockWeightMonitorExit = new javax.swing.JTextField();
        butBlockWeightTableSwitch = new javax.swing.JTextField();
        butBlockWeightInvokeInterface = new javax.swing.JTextField();
        butBlockWeightLookupSwitch = new javax.swing.JTextField();
        butBlockWeightLabel2 = new javax.swing.JLabel();
        butBlockWeightLabel1 = new javax.swing.JLabel();
        butBlockWeightInstruction = new javax.swing.JTextField();
        butBlockWeightMethodEnd = new javax.swing.JTextField();
        butBlockWeightLabel = new javax.swing.JLabel();
        butBlockWeightMethodStart = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        butBlockWeightLabel19 = new javax.swing.JLabel();
        butBlockWeightLabel18 = new javax.swing.JLabel();
        butBlockWeightLabel17 = new javax.swing.JLabel();
        butBlockWeightLabel16 = new javax.swing.JLabel();
        butBlockWeightLabel15 = new javax.swing.JLabel();
        butBlockWeightLabel20 = new javax.swing.JLabel();
        butBlockWeightLabel21 = new javax.swing.JLabel();
        butBlockWeightLabel14 = new javax.swing.JLabel();
        butBlockWeightNewArray = new javax.swing.JTextField();
        butBlockWeightReturn = new javax.swing.JTextField();
        butBlockWeightNew = new javax.swing.JTextField();
        butBlockWeightConst = new javax.swing.JTextField();
        butBlockWeightThrow = new javax.swing.JTextField();
        butBlockWeightShiftLeft = new javax.swing.JTextField();
        butBlockWeightPutField = new javax.swing.JTextField();
        butBlockWeightShiftRight = new javax.swing.JTextField();
        butBlockWeightLabel12 = new javax.swing.JLabel();
        butBlockWeightGetStatic = new javax.swing.JTextField();
        butBlockWeightLabel13 = new javax.swing.JLabel();
        butBlockWeightGetField = new javax.swing.JTextField();
        butBlockWeightLabel11 = new javax.swing.JLabel();
        butBlockWeightCheckCast = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        butBlockWeightLabel30 = new javax.swing.JLabel();
        butBlockWeightLabel29 = new javax.swing.JLabel();
        butBlockWeightLabel28 = new javax.swing.JLabel();
        butBlockWeightLabel27 = new javax.swing.JLabel();
        butBlockWeightLabel26 = new javax.swing.JLabel();
        butBlockWeightLabel31 = new javax.swing.JLabel();
        butBlockWeightLabel32 = new javax.swing.JLabel();
        butBlockWeightLabel25 = new javax.swing.JLabel();
        butBlockWeightGoto = new javax.swing.JTextField();
        butBlockWeightJSR = new javax.swing.JTextField();
        butBlockWeightIf = new javax.swing.JTextField();
        butBlockWeightPush = new javax.swing.JTextField();
        butBlockWeightPop = new javax.swing.JTextField();
        butBlockWeightSwap = new javax.swing.JTextField();
        butBlockWeightLDC = new javax.swing.JTextField();
        butBlockWeightDup = new javax.swing.JTextField();
        butBlockWeightStore = new javax.swing.JTextField();
        butBlockWeightLoad = new javax.swing.JTextField();
        butBlockWeightInc = new javax.swing.JTextField();
        butBlockWeightLabel23 = new javax.swing.JLabel();
        butBlockWeightLabel24 = new javax.swing.JLabel();
        butBlockWeightLabel22 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        butBlockWeightLabel41 = new javax.swing.JLabel();
        butBlockWeightLabel40 = new javax.swing.JLabel();
        butBlockWeightLabel39 = new javax.swing.JLabel();
        butBlockWeightLabel38 = new javax.swing.JLabel();
        butBlockWeightLabel37 = new javax.swing.JLabel();
        butBlockWeightLabel36 = new javax.swing.JLabel();
        butBlockWeightSub = new javax.swing.JTextField();
        butBlockWeightNeg = new javax.swing.JTextField();
        butBlockWeightAnd = new javax.swing.JTextField();
        butBlockWeightOr = new javax.swing.JTextField();
        butBlockWeightRet = new javax.swing.JTextField();
        butBlockWeightAdd = new javax.swing.JTextField();
        butBlockWeightLabel34 = new javax.swing.JLabel();
        butBlockWeightRem = new javax.swing.JTextField();
        butBlockWeightLabel35 = new javax.swing.JLabel();
        butBlockWeightMul = new javax.swing.JTextField();
        butBlockWeightLabel33 = new javax.swing.JLabel();
        butBlockWeightDiv = new javax.swing.JTextField();
        butBlockWeightLabel42 = new javax.swing.JLabel();
        butBlockWeightXor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        atribVelocidadLabel = new javax.swing.JLabel();
        atribVelocidad = new javax.swing.JTextField();
        atribDetalleLabel = new javax.swing.JLabel();
        atribDetalle = new javax.swing.JTextField();
        atribPrecision = new javax.swing.JTextField();
        atribPrecisionLabel = new javax.swing.JLabel();
        atribSensibilidad = new javax.swing.JTextField();
        atribSensibilidadLabel = new javax.swing.JLabel();
        atribAsimilacionLabel = new javax.swing.JLabel();
        atribAsimilacion = new javax.swing.JTextField();
        atribEspecializacion = new javax.swing.JTextField();
        atribsLabel = new javax.swing.JLabel();
        atribEspecializacionLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();
        perfilActual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        iconoModificado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ayudaTextArea = new javax.swing.JTextPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        loadProfileMenuItem = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        saveProfileMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        exportImageMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        butGReflexive = new javax.swing.ButtonGroup();
        butGDifference = new javax.swing.ButtonGroup();

        FormListener formListener = new FormListener();

        mainPanel.setMaximumSize(new java.awt.Dimension(795, 32768));
        mainPanel.setMinimumSize(new java.awt.Dimension(795, 0));
        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setMinimumSize(new java.awt.Dimension(184, 215));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(184, 215));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.jsimil.profileeditor.JSimilProfile.class).getContext().getResourceMap(JSimilProfileView.class);
        huellaPanel.setBackground(resourceMap.getColor("huellaPanel.background")); // NOI18N
        huellaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        huellaPanel.setMaximumSize(new java.awt.Dimension(170, 170));
        huellaPanel.setMinimumSize(new java.awt.Dimension(170, 170));
        huellaPanel.setName("huellaPanel"); // NOI18N
        huellaPanel.setPreferredSize(new java.awt.Dimension(170, 170));
        huellaPanel.addFocusListener(formListener);
        huellaPanel.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout huellaPanelLayout = new org.jdesktop.layout.GroupLayout(huellaPanel);
        huellaPanel.setLayout(huellaPanelLayout);
        huellaPanelLayout.setHorizontalGroup(
            huellaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 168, Short.MAX_VALUE)
        );
        huellaPanelLayout.setVerticalGroup(
            huellaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 168, Short.MAX_VALUE)
        );

        huellaLabel.setFont(fuente);
        huellaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        huellaLabel.setText(lang.getFrase(332));
        huellaLabel.setName("huellaLabel"); // NOI18N
        huellaLabel.addFocusListener(formListener);
        huellaLabel.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(huellaPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(huellaLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(huellaLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(huellaPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(fuente);
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(568, 345));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(568, 345));
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(568, 345));

        jPanel3.setMaximumSize(new java.awt.Dimension(569, 320));
        jPanel3.setMinimumSize(new java.awt.Dimension(569, 320));
        jPanel3.setName("jPanel3"); // NOI18N

        butGReflexive.add(butReflexiveNo);
        butReflexiveNo.setFont(fuente);
        butReflexiveNo.setText(lang.getFrase(338));
        butReflexiveNo.setName("butReflexiveSi"); // NOI18N
        butReflexiveNo.addFocusListener(formListener);
        butReflexiveNo.addChangeListener(formListener);

        butGReflexive.add(butReflexiveSi);
        butReflexiveSi.setFont(fuente);
        butReflexiveSi.setText(lang.getFrase(337));
        butReflexiveSi.setName("butReflexiveNo"); // NOI18N
        butReflexiveSi.setVerifyInputWhenFocusTarget(false);
        butReflexiveSi.addFocusListener(formListener);
        butReflexiveSi.addChangeListener(formListener);

        butGDifference.add(butDifferenceSearchNo);
        butDifferenceSearchNo.setFont(fuente);
        butDifferenceSearchNo.setText(lang.getFrase(367));
        butDifferenceSearchNo.setName("butDifferenceSearchNo"); // NOI18N
        butDifferenceSearchNo.addFocusListener(formListener);
        butDifferenceSearchNo.addChangeListener(formListener);

        butGDifference.add(butDifferenceSearchSi);
        butDifferenceSearchSi.setFont(fuente);
        butDifferenceSearchSi.setText(lang.getFrase(368));
        butDifferenceSearchSi.setName("butDifferenceSearchSi"); // NOI18N
        butDifferenceSearchSi.setVerifyInputWhenFocusTarget(false);
        butDifferenceSearchSi.addFocusListener(formListener);
        butDifferenceSearchSi.addChangeListener(formListener);

        butReturnNull.setFont(fuente);
        butReturnNull.setText(lang.getFrase(370));
        butReturnNull.setName("butReturnNull"); // NOI18N
        butReturnNull.addFocusListener(formListener);
        butReturnNull.addChangeListener(formListener);

        butProgMatchMinMaxLabel.setFont(fuente);
        butProgMatchMinMaxLabel.setText(lang.getFrase(386));
        butProgMatchMinMaxLabel.setName("butProgMatchMinMaxLabel"); // NOI18N
        butProgMatchMinMaxLabel.addFocusListener(formListener);
        butProgMatchMinMaxLabel.addMouseListener(formListener);

        butProgMatchErrorLabel1.setFont(fuente);
        butProgMatchErrorLabel1.setText(lang.getFrase(389));
        butProgMatchErrorLabel1.setName("butProgMatchErrorLabel1"); // NOI18N
        butProgMatchErrorLabel1.addFocusListener(formListener);
        butProgMatchErrorLabel1.addMouseListener(formListener);

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(410, 28));

        butProgMatchMinMaxMinLabel.setFont(fuente);
        butProgMatchMinMaxMinLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butProgMatchMinMaxMinLabel.setText(lang.getFrase(387));
        butProgMatchMinMaxMinLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butProgMatchMinMaxMinLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butProgMatchMinMaxMinLabel.setName("butProgMatchMinMaxMinLabel"); // NOI18N
        butProgMatchMinMaxMinLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butProgMatchMinMaxMinLabel.addFocusListener(formListener);
        butProgMatchMinMaxMinLabel.addMouseListener(formListener);
        jPanel6.add(butProgMatchMinMaxMinLabel);

        butProgMatchMinMaxMinBar.setFont(fuente);
        butProgMatchMinMaxMinBar.setMaximum(10000);
        butProgMatchMinMaxMinBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butProgMatchMinMaxMinBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butProgMatchMinMaxMinBar.setName("butProgMatchMinMaxMinBar"); // NOI18N
        butProgMatchMinMaxMinBar.addFocusListener(formListener);
        butProgMatchMinMaxMinBar.addChangeListener(formListener);
        jPanel6.add(butProgMatchMinMaxMinBar);

        butProgMatchMinMaxMinText.setFont(fuente);
        butProgMatchMinMaxMinText.setText(resourceMap.getString("butProgMatchMinMaxMinText.text")); // NOI18N
        butProgMatchMinMaxMinText.setMaximumSize(new java.awt.Dimension(50, 18));
        butProgMatchMinMaxMinText.setMinimumSize(new java.awt.Dimension(50, 18));
        butProgMatchMinMaxMinText.setName("butProgMatchMinMaxMinText"); // NOI18N
        butProgMatchMinMaxMinText.setPreferredSize(new java.awt.Dimension(50, 18));
        butProgMatchMinMaxMinText.addFocusListener(formListener);
        butProgMatchMinMaxMinText.addKeyListener(formListener);
        jPanel6.add(butProgMatchMinMaxMinText);

        jPanel7.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel7.setName("jPanel7"); // NOI18N

        butProgMatchMinMaxMaxLabel.setFont(fuente);
        butProgMatchMinMaxMaxLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butProgMatchMinMaxMaxLabel.setText(lang.getFrase(388));
        butProgMatchMinMaxMaxLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butProgMatchMinMaxMaxLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butProgMatchMinMaxMaxLabel.setName("butProgMatchMinMaxMaxLabel"); // NOI18N
        butProgMatchMinMaxMaxLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butProgMatchMinMaxMaxLabel.addFocusListener(formListener);
        butProgMatchMinMaxMaxLabel.addMouseListener(formListener);
        jPanel7.add(butProgMatchMinMaxMaxLabel);

        butProgMatchMinMaxMaxBar.setFont(fuente);
        butProgMatchMinMaxMaxBar.setMaximum(10001);
        butProgMatchMinMaxMaxBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butProgMatchMinMaxMaxBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butProgMatchMinMaxMaxBar.setName("butProgMatchMinMaxMaxBar"); // NOI18N
        butProgMatchMinMaxMaxBar.addFocusListener(formListener);
        butProgMatchMinMaxMaxBar.addChangeListener(formListener);
        jPanel7.add(butProgMatchMinMaxMaxBar);

        butProgMatchMinMaxMaxText.setFont(fuente);
        butProgMatchMinMaxMaxText.setText(resourceMap.getString("butProgMatchMinMaxMaxText.text")); // NOI18N
        butProgMatchMinMaxMaxText.setMaximumSize(new java.awt.Dimension(50, 18));
        butProgMatchMinMaxMaxText.setMinimumSize(new java.awt.Dimension(50, 18));
        butProgMatchMinMaxMaxText.setName("butProgMatchMinMaxMaxText"); // NOI18N
        butProgMatchMinMaxMaxText.setPreferredSize(new java.awt.Dimension(50, 18));
        butProgMatchMinMaxMaxText.addFocusListener(formListener);
        butProgMatchMinMaxMaxText.addKeyListener(formListener);
        jPanel7.add(butProgMatchMinMaxMaxText);

        jPanel8.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel8.setName("jPanel8"); // NOI18N

        butProgMatchErrorLabel2.setFont(fuente);
        butProgMatchErrorLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butProgMatchErrorLabel2.setText(lang.getFrase(390));
        butProgMatchErrorLabel2.setMaximumSize(new java.awt.Dimension(140, 14));
        butProgMatchErrorLabel2.setMinimumSize(new java.awt.Dimension(140, 14));
        butProgMatchErrorLabel2.setName("butProgMatchErrorLabel2"); // NOI18N
        butProgMatchErrorLabel2.setPreferredSize(new java.awt.Dimension(140, 14));
        butProgMatchErrorLabel2.addFocusListener(formListener);
        butProgMatchErrorLabel2.addMouseListener(formListener);
        jPanel8.add(butProgMatchErrorLabel2);

        butProgMatchErrorBar.setFont(fuente);
        butProgMatchErrorBar.setMaximum(10000);
        butProgMatchErrorBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butProgMatchErrorBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butProgMatchErrorBar.setName("butProgMatchErrorBar"); // NOI18N
        butProgMatchErrorBar.addFocusListener(formListener);
        butProgMatchErrorBar.addChangeListener(formListener);
        jPanel8.add(butProgMatchErrorBar);

        butProgMatchErrorText.setFont(fuente);
        butProgMatchErrorText.setText(resourceMap.getString("butProgMatchErrorText.text")); // NOI18N
        butProgMatchErrorText.setMaximumSize(new java.awt.Dimension(50, 18));
        butProgMatchErrorText.setMinimumSize(new java.awt.Dimension(50, 18));
        butProgMatchErrorText.setName("butProgMatchErrorText"); // NOI18N
        butProgMatchErrorText.setPreferredSize(new java.awt.Dimension(50, 18));
        butProgMatchErrorText.addFocusListener(formListener);
        butProgMatchErrorText.addKeyListener(formListener);
        jPanel8.add(butProgMatchErrorText);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butProgMatchMinMaxLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .add(butProgMatchErrorLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                        .addContainerGap())
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(butReflexiveNo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(butReflexiveSi, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(butDifferenceSearchNo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(butDifferenceSearchSi, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(butReturnNull, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .add(12, 12, 12))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(butReflexiveNo)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butReflexiveSi)
                .add(18, 18, 18)
                .add(butDifferenceSearchNo)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butDifferenceSearchSi)
                .add(18, 18, 18)
                .add(butReturnNull)
                .add(19, 19, 19)
                .add(butProgMatchMinMaxLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butProgMatchErrorLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(lang.getFrase(346), jPanel3);

        jPanel35.setMaximumSize(new java.awt.Dimension(569, 320));
        jPanel35.setMinimumSize(new java.awt.Dimension(569, 320));
        jPanel35.setName("jPanel35"); // NOI18N

        jPanel40.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel40.setName("jPanel40"); // NOI18N

        jPanel36.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel36.setName("jPanel36"); // NOI18N

        butOptimismClassLabel.setFont(fuente);
        butOptimismClassLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butOptimismClassLabel.setText(lang.getFrase(406));
        butOptimismClassLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butOptimismClassLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butOptimismClassLabel.setName("butOptimismClassLabel"); // NOI18N
        butOptimismClassLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butOptimismClassLabel.addFocusListener(formListener);
        butOptimismClassLabel.addMouseListener(formListener);
        jPanel36.add(butOptimismClassLabel);

        butOptimismClassBar.setFont(fuente);
        butOptimismClassBar.setMaximum(10000);
        butOptimismClassBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butOptimismClassBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butOptimismClassBar.setName("butOptimismClassBar"); // NOI18N
        butOptimismClassBar.addFocusListener(formListener);
        butOptimismClassBar.addChangeListener(formListener);
        jPanel36.add(butOptimismClassBar);

        butOptimismClassText.setFont(fuente);
        butOptimismClassText.setText(resourceMap.getString("butOptimismClassText.text")); // NOI18N
        butOptimismClassText.setMaximumSize(new java.awt.Dimension(50, 18));
        butOptimismClassText.setMinimumSize(new java.awt.Dimension(50, 18));
        butOptimismClassText.setName("butOptimismClassText"); // NOI18N
        butOptimismClassText.setPreferredSize(new java.awt.Dimension(50, 18));
        butOptimismClassText.addFocusListener(formListener);
        butOptimismClassText.addKeyListener(formListener);
        jPanel36.add(butOptimismClassText);

        jPanel38.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel38.setName("jPanel38"); // NOI18N

        butOptimismMethodLabel.setFont(fuente);
        butOptimismMethodLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butOptimismMethodLabel.setText(lang.getFrase(407));
        butOptimismMethodLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butOptimismMethodLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butOptimismMethodLabel.setName("butOptimismMethodLabel"); // NOI18N
        butOptimismMethodLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butOptimismMethodLabel.addFocusListener(formListener);
        butOptimismMethodLabel.addMouseListener(formListener);
        jPanel38.add(butOptimismMethodLabel);

        butOptimismMethodBar.setFont(fuente);
        butOptimismMethodBar.setMaximum(10000);
        butOptimismMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butOptimismMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butOptimismMethodBar.setName("butOptimismMethodBar"); // NOI18N
        butOptimismMethodBar.addFocusListener(formListener);
        butOptimismMethodBar.addChangeListener(formListener);
        jPanel38.add(butOptimismMethodBar);

        butOptimismMethodText.setFont(fuente);
        butOptimismMethodText.setText(resourceMap.getString("butOptimismMethodText.text")); // NOI18N
        butOptimismMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butOptimismMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butOptimismMethodText.setName("butOptimismMethodText"); // NOI18N
        butOptimismMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butOptimismMethodText.addFocusListener(formListener);
        butOptimismMethodText.addKeyListener(formListener);
        jPanel38.add(butOptimismMethodText);

        jPanel39.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel39.setName("jPanel39"); // NOI18N

        butOptimismProgLabel.setFont(fuente);
        butOptimismProgLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butOptimismProgLabel.setText(lang.getFrase(405));
        butOptimismProgLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butOptimismProgLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butOptimismProgLabel.setName("butOptimismProgLabel"); // NOI18N
        butOptimismProgLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butOptimismProgLabel.addFocusListener(formListener);
        butOptimismProgLabel.addMouseListener(formListener);
        jPanel39.add(butOptimismProgLabel);

        butOptimismProgBar.setFont(fuente);
        butOptimismProgBar.setMaximum(10000);
        butOptimismProgBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butOptimismProgBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butOptimismProgBar.setName("butOptimismProgBar"); // NOI18N
        butOptimismProgBar.addFocusListener(formListener);
        butOptimismProgBar.addChangeListener(formListener);
        jPanel39.add(butOptimismProgBar);

        butOptimismProgText.setFont(fuente);
        butOptimismProgText.setText(resourceMap.getString("butOptimismProgText.text")); // NOI18N
        butOptimismProgText.setMaximumSize(new java.awt.Dimension(50, 18));
        butOptimismProgText.setMinimumSize(new java.awt.Dimension(50, 18));
        butOptimismProgText.setName("butOptimismProgText"); // NOI18N
        butOptimismProgText.setPreferredSize(new java.awt.Dimension(50, 18));
        butOptimismProgText.addFocusListener(formListener);
        butOptimismProgText.addKeyListener(formListener);
        jPanel39.add(butOptimismProgText);

        jPanel41.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel41.setName("jPanel41"); // NOI18N

        butClassThresholdMaxLabel.setFont(fuente);
        butClassThresholdMaxLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butClassThresholdMaxLabel.setText(lang.getFrase(410));
        butClassThresholdMaxLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butClassThresholdMaxLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butClassThresholdMaxLabel.setName("butClassThresholdMaxLabel"); // NOI18N
        butClassThresholdMaxLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butClassThresholdMaxLabel.addFocusListener(formListener);
        butClassThresholdMaxLabel.addMouseListener(formListener);
        jPanel41.add(butClassThresholdMaxLabel);

        butClassThresholdMaxBar.setFont(fuente);
        butClassThresholdMaxBar.setMaximum(10001);
        butClassThresholdMaxBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butClassThresholdMaxBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butClassThresholdMaxBar.setName("butClassThresholdMaxBar"); // NOI18N
        butClassThresholdMaxBar.addFocusListener(formListener);
        butClassThresholdMaxBar.addChangeListener(formListener);
        jPanel41.add(butClassThresholdMaxBar);

        butClassThresholdMaxText.setFont(fuente);
        butClassThresholdMaxText.setText(resourceMap.getString("butClassThresholdMaxText.text")); // NOI18N
        butClassThresholdMaxText.setMaximumSize(new java.awt.Dimension(50, 18));
        butClassThresholdMaxText.setMinimumSize(new java.awt.Dimension(50, 18));
        butClassThresholdMaxText.setName("butClassThresholdMaxText"); // NOI18N
        butClassThresholdMaxText.setPreferredSize(new java.awt.Dimension(50, 18));
        butClassThresholdMaxText.addFocusListener(formListener);
        butClassThresholdMaxText.addKeyListener(formListener);
        jPanel41.add(butClassThresholdMaxText);

        jPanel44.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel44.setName("jPanel44"); // NOI18N

        butClassThresholdMinLabel.setFont(fuente);
        butClassThresholdMinLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butClassThresholdMinLabel.setText(lang.getFrase(409));
        butClassThresholdMinLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butClassThresholdMinLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butClassThresholdMinLabel.setName("butClassThresholdMinLabel"); // NOI18N
        butClassThresholdMinLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butClassThresholdMinLabel.addFocusListener(formListener);
        butClassThresholdMinLabel.addMouseListener(formListener);
        jPanel44.add(butClassThresholdMinLabel);

        butClassThresholdMinBar.setFont(fuente);
        butClassThresholdMinBar.setMaximum(10000);
        butClassThresholdMinBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butClassThresholdMinBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butClassThresholdMinBar.setName("butClassThresholdMinBar"); // NOI18N
        butClassThresholdMinBar.addFocusListener(formListener);
        butClassThresholdMinBar.addChangeListener(formListener);
        jPanel44.add(butClassThresholdMinBar);

        butClassThresholdMinText.setFont(fuente);
        butClassThresholdMinText.setText(resourceMap.getString("butClassThresholdMinText.text")); // NOI18N
        butClassThresholdMinText.setMaximumSize(new java.awt.Dimension(50, 18));
        butClassThresholdMinText.setMinimumSize(new java.awt.Dimension(50, 18));
        butClassThresholdMinText.setName("butClassThresholdMinText"); // NOI18N
        butClassThresholdMinText.setPreferredSize(new java.awt.Dimension(50, 18));
        butClassThresholdMinText.addFocusListener(formListener);
        butClassThresholdMinText.addKeyListener(formListener);
        jPanel44.add(butClassThresholdMinText);

        butOptimism.setFont(fuente);
        butOptimism.setText(lang.getFrase(404));
        butOptimism.setName("butOptimism"); // NOI18N
        butOptimism.addFocusListener(formListener);
        butOptimism.addMouseListener(formListener);

        butClassThreshold.setFont(fuente);
        butClassThreshold.setText(lang.getFrase(408));
        butClassThreshold.setName("butClassThreshold"); // NOI18N
        butClassThreshold.addFocusListener(formListener);
        butClassThreshold.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel35Layout = new org.jdesktop.layout.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel35Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel35Layout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(jPanel35Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(124, 124, 124))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, butOptimism, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel35Layout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(jPanel35Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(122, 122, 122))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, butClassThreshold, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel35Layout.createSequentialGroup()
                .add(jPanel35Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel35Layout.createSequentialGroup()
                        .add(268, 268, 268)
                        .add(jPanel40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(butOptimism)
                        .add(10, 10, 10)
                        .add(jPanel39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(2, 2, 2)
                        .add(jPanel36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(2, 2, 2)
                        .add(jPanel38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(14, 14, 14)
                        .add(butClassThreshold)
                        .add(10, 10, 10)
                        .add(jPanel44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(2, 2, 2)
                        .add(jPanel41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(lang.getFrase(403), jPanel35);

        jPanel13.setMaximumSize(new java.awt.Dimension(569, 320));
        jPanel13.setMinimumSize(new java.awt.Dimension(569, 320));
        jPanel13.setName("jPanel13"); // NOI18N

        butClassSameNameMatch.setFont(fuente);
        butClassSameNameMatch.setText(lang.getFrase(372));
        butClassSameNameMatch.setName("butClassSameNameMatch"); // NOI18N
        butClassSameNameMatch.addFocusListener(formListener);
        butClassSameNameMatch.addChangeListener(formListener);

        butClassAllowMultiMatch.setFont(fuente);
        butClassAllowMultiMatch.setText(lang.getFrase(378));
        butClassAllowMultiMatch.setName("butClassAllowMultiMatch"); // NOI18N
        butClassAllowMultiMatch.addFocusListener(formListener);
        butClassAllowMultiMatch.addChangeListener(formListener);

        butMethodSameNameMatch.setFont(fuente);
        butMethodSameNameMatch.setText(lang.getFrase(376));
        butMethodSameNameMatch.setName("butMethodSameNameMatch"); // NOI18N
        butMethodSameNameMatch.addFocusListener(formListener);
        butMethodSameNameMatch.addChangeListener(formListener);

        butMethodOfClassSameNameMatch.setFont(fuente);
        butMethodOfClassSameNameMatch.setText(lang.getFrase(374));
        butMethodOfClassSameNameMatch.setName("butMethodOfClassSameNameMatch"); // NOI18N
        butMethodOfClassSameNameMatch.addFocusListener(formListener);
        butMethodOfClassSameNameMatch.addChangeListener(formListener);

        butMethodAllowMultiMatch.setFont(fuente);
        butMethodAllowMultiMatch.setText(lang.getFrase(380));
        butMethodAllowMultiMatch.setName("butMethodAllowMultiMatch"); // NOI18N
        butMethodAllowMultiMatch.addFocusListener(formListener);
        butMethodAllowMultiMatch.addChangeListener(formListener);

        butBlockAllowMultiMatch.setFont(fuente);
        butBlockAllowMultiMatch.setText(lang.getFrase(382));
        butBlockAllowMultiMatch.setName("butBlockAllowMultiMatch"); // NOI18N
        butBlockAllowMultiMatch.addFocusListener(formListener);
        butBlockAllowMultiMatch.addChangeListener(formListener);

        butMinimumInstructionLabel.setFont(fuente);
        butMinimumInstructionLabel.setText(lang.getFrase(398));
        butMinimumInstructionLabel.setName("butMinimumInstructionLabel"); // NOI18N
        butMinimumInstructionLabel.addFocusListener(formListener);
        butMinimumInstructionLabel.addMouseListener(formListener);

        jPanel15.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel15.setName("jPanel15"); // NOI18N

        butMinimumInstructionLabel1.setFont(fuente);
        butMinimumInstructionLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumInstructionLabel1.setText(lang.getFrase(399));
        butMinimumInstructionLabel1.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel1.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel1.setName("butMinimumInstructionLabel1"); // NOI18N
        butMinimumInstructionLabel1.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel1.addFocusListener(formListener);
        butMinimumInstructionLabel1.addMouseListener(formListener);
        jPanel15.add(butMinimumInstructionLabel1);

        butMinimumInstructionClass.setFont(fuente);
        butMinimumInstructionClass.setText(resourceMap.getString("butMinimumInstructionClass.text")); // NOI18N
        butMinimumInstructionClass.setMaximumSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionClass.setMinimumSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionClass.setName("butMinimumInstructionClass"); // NOI18N
        butMinimumInstructionClass.setPreferredSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionClass.addFocusListener(formListener);
        butMinimumInstructionClass.addKeyListener(formListener);
        jPanel15.add(butMinimumInstructionClass);

        jPanel16.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel16.setName("jPanel16"); // NOI18N

        butMinimumInstructionLabel2.setFont(fuente);
        butMinimumInstructionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumInstructionLabel2.setText(lang.getFrase(400));
        butMinimumInstructionLabel2.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel2.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel2.setName("butMinimumInstructionLabel2"); // NOI18N
        butMinimumInstructionLabel2.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel2.addFocusListener(formListener);
        butMinimumInstructionLabel2.addMouseListener(formListener);
        jPanel16.add(butMinimumInstructionLabel2);

        butMinimumInstructionMethod.setFont(fuente);
        butMinimumInstructionMethod.setText(resourceMap.getString("butMinimumInstructionMethod.text")); // NOI18N
        butMinimumInstructionMethod.setMaximumSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionMethod.setMinimumSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionMethod.setName("butMinimumInstructionMethod"); // NOI18N
        butMinimumInstructionMethod.setPreferredSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionMethod.addFocusListener(formListener);
        butMinimumInstructionMethod.addKeyListener(formListener);
        jPanel16.add(butMinimumInstructionMethod);

        jPanel17.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel17.setName("jPanel17"); // NOI18N

        butMinimumInstructionLabel3.setFont(fuente);
        butMinimumInstructionLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumInstructionLabel3.setText(lang.getFrase(401));
        butMinimumInstructionLabel3.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel3.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel3.setName("butMinimumInstructionLabel3"); // NOI18N
        butMinimumInstructionLabel3.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumInstructionLabel3.addFocusListener(formListener);
        butMinimumInstructionLabel3.addMouseListener(formListener);
        jPanel17.add(butMinimumInstructionLabel3);

        butMinimumInstructionBlock.setFont(fuente);
        butMinimumInstructionBlock.setText(resourceMap.getString("butMinimumInstructionBlock.text")); // NOI18N
        butMinimumInstructionBlock.setMaximumSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionBlock.setMinimumSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionBlock.setName("butMinimumInstructionBlock"); // NOI18N
        butMinimumInstructionBlock.setPreferredSize(new java.awt.Dimension(70, 18));
        butMinimumInstructionBlock.addFocusListener(formListener);
        butMinimumInstructionBlock.addKeyListener(formListener);
        jPanel17.add(butMinimumInstructionBlock);

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel13Layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(butMinimumInstructionLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butClassSameNameMatch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butMethodSameNameMatch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butMethodOfClassSameNameMatch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butMethodAllowMultiMatch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butBlockAllowMultiMatch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .add(38, 38, 38))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butClassAllowMultiMatch, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(326, Short.MAX_VALUE))
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(326, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(butClassSameNameMatch)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butClassAllowMultiMatch)
                .add(20, 20, 20)
                .add(butMethodSameNameMatch)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butMethodOfClassSameNameMatch)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butMethodAllowMultiMatch)
                .add(18, 18, 18)
                .add(butBlockAllowMultiMatch)
                .add(18, 18, 18)
                .add(butMinimumInstructionLabel)
                .add(10, 10, 10)
                .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .add(11, 11, 11))
        );

        jTabbedPane1.addTab(lang.getFrase(402), jPanel13);

        jPanel14.setMaximumSize(new java.awt.Dimension(569, 320));
        jPanel14.setMinimumSize(new java.awt.Dimension(569, 320));
        jPanel14.setName("jPanel14"); // NOI18N

        jPanel18.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel18.setName("jPanel18"); // NOI18N

        butDifferenceProgramLabel.setFont(fuente);
        butDifferenceProgramLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butDifferenceProgramLabel.setText(lang.getFrase(412));
        butDifferenceProgramLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butDifferenceProgramLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butDifferenceProgramLabel.setName("butDifferenceProgramLabel"); // NOI18N
        butDifferenceProgramLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butDifferenceProgramLabel.addFocusListener(formListener);
        butDifferenceProgramLabel.addMouseListener(formListener);
        jPanel18.add(butDifferenceProgramLabel);

        butDifferenceProgramBar.setFont(fuente);
        butDifferenceProgramBar.setMaximum(10000);
        butDifferenceProgramBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butDifferenceProgramBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butDifferenceProgramBar.setName("butDifferenceProgramBar"); // NOI18N
        butDifferenceProgramBar.addFocusListener(formListener);
        butDifferenceProgramBar.addChangeListener(formListener);
        jPanel18.add(butDifferenceProgramBar);

        butDifferenceProgramText.setFont(fuente);
        butDifferenceProgramText.setText(resourceMap.getString("butDifferenceProgramText.text")); // NOI18N
        butDifferenceProgramText.setMaximumSize(new java.awt.Dimension(50, 18));
        butDifferenceProgramText.setMinimumSize(new java.awt.Dimension(50, 18));
        butDifferenceProgramText.setName("butDifferenceProgramText"); // NOI18N
        butDifferenceProgramText.setPreferredSize(new java.awt.Dimension(50, 18));
        butDifferenceProgramText.addFocusListener(formListener);
        butDifferenceProgramText.addKeyListener(formListener);
        jPanel18.add(butDifferenceProgramText);

        butDifference.setFont(fuente);
        butDifference.setText(lang.getFrase(411));
        butDifference.setName("butDifference"); // NOI18N
        butDifference.addFocusListener(formListener);
        butDifference.addMouseListener(formListener);

        jPanel19.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel19.setName("jPanel19"); // NOI18N

        butDifferenceClassLabel.setFont(fuente);
        butDifferenceClassLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butDifferenceClassLabel.setText(lang.getFrase(413));
        butDifferenceClassLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butDifferenceClassLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butDifferenceClassLabel.setName("butDifferenceClassLabel"); // NOI18N
        butDifferenceClassLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butDifferenceClassLabel.addFocusListener(formListener);
        butDifferenceClassLabel.addMouseListener(formListener);
        jPanel19.add(butDifferenceClassLabel);

        butDifferenceClassBar.setFont(fuente);
        butDifferenceClassBar.setMaximum(10000);
        butDifferenceClassBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butDifferenceClassBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butDifferenceClassBar.setName("butDifferenceClassBar"); // NOI18N
        butDifferenceClassBar.addFocusListener(formListener);
        butDifferenceClassBar.addChangeListener(formListener);
        jPanel19.add(butDifferenceClassBar);

        butDifferenceClassText.setFont(fuente);
        butDifferenceClassText.setText(resourceMap.getString("butDifferenceClassText.text")); // NOI18N
        butDifferenceClassText.setMaximumSize(new java.awt.Dimension(50, 18));
        butDifferenceClassText.setMinimumSize(new java.awt.Dimension(50, 18));
        butDifferenceClassText.setName("butDifferenceClassText"); // NOI18N
        butDifferenceClassText.setPreferredSize(new java.awt.Dimension(50, 18));
        butDifferenceClassText.addFocusListener(formListener);
        butDifferenceClassText.addKeyListener(formListener);
        jPanel19.add(butDifferenceClassText);

        jPanel20.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel20.setName("jPanel20"); // NOI18N

        butDifferenceProgramMethod.setFont(fuente);
        butDifferenceProgramMethod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butDifferenceProgramMethod.setText(lang.getFrase(426));
        butDifferenceProgramMethod.setMaximumSize(new java.awt.Dimension(140, 14));
        butDifferenceProgramMethod.setMinimumSize(new java.awt.Dimension(140, 14));
        butDifferenceProgramMethod.setName("butDifferenceProgramMethod"); // NOI18N
        butDifferenceProgramMethod.setPreferredSize(new java.awt.Dimension(140, 14));
        butDifferenceProgramMethod.addFocusListener(formListener);
        butDifferenceProgramMethod.addMouseListener(formListener);
        jPanel20.add(butDifferenceProgramMethod);

        butDifferenceProgramMethodBar.setFont(fuente);
        butDifferenceProgramMethodBar.setMaximum(10000);
        butDifferenceProgramMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butDifferenceProgramMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butDifferenceProgramMethodBar.setName("butDifferenceProgramMethodBar"); // NOI18N
        butDifferenceProgramMethodBar.addFocusListener(formListener);
        butDifferenceProgramMethodBar.addChangeListener(formListener);
        jPanel20.add(butDifferenceProgramMethodBar);

        butDifferenceProgramMethodText.setFont(fuente);
        butDifferenceProgramMethodText.setText(resourceMap.getString("butDifferenceProgramMethodText.text")); // NOI18N
        butDifferenceProgramMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butDifferenceProgramMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butDifferenceProgramMethodText.setName("butDifferenceProgramMethodText"); // NOI18N
        butDifferenceProgramMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butDifferenceProgramMethodText.addFocusListener(formListener);
        butDifferenceProgramMethodText.addKeyListener(formListener);
        jPanel20.add(butDifferenceProgramMethodText);

        jPanel21.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel21.setName("jPanel21"); // NOI18N

        butDifferenceClassMethodLabel.setFont(fuente);
        butDifferenceClassMethodLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butDifferenceClassMethodLabel.setText(lang.getFrase(406));
        butDifferenceClassMethodLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butDifferenceClassMethodLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butDifferenceClassMethodLabel.setName("butDifferenceClassMethodLabel"); // NOI18N
        butDifferenceClassMethodLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butDifferenceClassMethodLabel.addFocusListener(formListener);
        butDifferenceClassMethodLabel.addMouseListener(formListener);
        jPanel21.add(butDifferenceClassMethodLabel);

        butDifferenceClassMethodBar.setFont(fuente);
        butDifferenceClassMethodBar.setMaximum(10000);
        butDifferenceClassMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butDifferenceClassMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butDifferenceClassMethodBar.setName("butDifferenceClassMethodBar"); // NOI18N
        butDifferenceClassMethodBar.addFocusListener(formListener);
        butDifferenceClassMethodBar.addChangeListener(formListener);
        jPanel21.add(butDifferenceClassMethodBar);

        butDifferenceClassMethodText.setFont(fuente);
        butDifferenceClassMethodText.setText(resourceMap.getString("butDifferenceClassMethodText.text")); // NOI18N
        butDifferenceClassMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butDifferenceClassMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butDifferenceClassMethodText.setName("butDifferenceClassMethodText"); // NOI18N
        butDifferenceClassMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butDifferenceClassMethodText.addFocusListener(formListener);
        butDifferenceClassMethodText.addKeyListener(formListener);
        jPanel21.add(butDifferenceClassMethodText);

        jPanel22.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel22.setName("jPanel22"); // NOI18N

        butLimitLabel2.setFont(fuente);
        butLimitLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butLimitLabel2.setText(lang.getFrase(413));
        butLimitLabel2.setMaximumSize(new java.awt.Dimension(140, 14));
        butLimitLabel2.setMinimumSize(new java.awt.Dimension(140, 14));
        butLimitLabel2.setName("butLimitLabel2"); // NOI18N
        butLimitLabel2.setPreferredSize(new java.awt.Dimension(140, 14));
        butLimitLabel2.addFocusListener(formListener);
        butLimitLabel2.addMouseListener(formListener);
        jPanel22.add(butLimitLabel2);

        butLimitClassBar.setFont(fuente);
        butLimitClassBar.setMaximum(10000);
        butLimitClassBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butLimitClassBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butLimitClassBar.setName("butLimitClassBar"); // NOI18N
        butLimitClassBar.addFocusListener(formListener);
        butLimitClassBar.addChangeListener(formListener);
        jPanel22.add(butLimitClassBar);

        butLimitClassText.setFont(fuente);
        butLimitClassText.setText(resourceMap.getString("butLimitClassText.text")); // NOI18N
        butLimitClassText.setMaximumSize(new java.awt.Dimension(50, 18));
        butLimitClassText.setMinimumSize(new java.awt.Dimension(50, 18));
        butLimitClassText.setName("butLimitClassText"); // NOI18N
        butLimitClassText.setPreferredSize(new java.awt.Dimension(50, 18));
        butLimitClassText.addFocusListener(formListener);
        butLimitClassText.addKeyListener(formListener);
        jPanel22.add(butLimitClassText);

        jPanel23.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel23.setName("jPanel23"); // NOI18N

        butDifferenceBlockLabel.setFont(fuente);
        butDifferenceBlockLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butDifferenceBlockLabel.setText(lang.getFrase(407));
        butDifferenceBlockLabel.setMaximumSize(new java.awt.Dimension(140, 14));
        butDifferenceBlockLabel.setMinimumSize(new java.awt.Dimension(140, 14));
        butDifferenceBlockLabel.setName("butDifferenceBlockLabel"); // NOI18N
        butDifferenceBlockLabel.setPreferredSize(new java.awt.Dimension(140, 14));
        butDifferenceBlockLabel.addFocusListener(formListener);
        butDifferenceBlockLabel.addMouseListener(formListener);
        jPanel23.add(butDifferenceBlockLabel);

        butDifferenceBlockBar.setFont(fuente);
        butDifferenceBlockBar.setMaximum(10000);
        butDifferenceBlockBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butDifferenceBlockBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butDifferenceBlockBar.setName("butDifferenceBlockBar"); // NOI18N
        butDifferenceBlockBar.addFocusListener(formListener);
        butDifferenceBlockBar.addChangeListener(formListener);
        jPanel23.add(butDifferenceBlockBar);

        butDifferenceBlockText.setFont(fuente);
        butDifferenceBlockText.setText(resourceMap.getString("butDifferenceBlockText.text")); // NOI18N
        butDifferenceBlockText.setMaximumSize(new java.awt.Dimension(50, 18));
        butDifferenceBlockText.setMinimumSize(new java.awt.Dimension(50, 18));
        butDifferenceBlockText.setName("butDifferenceBlockText"); // NOI18N
        butDifferenceBlockText.setPreferredSize(new java.awt.Dimension(50, 18));
        butDifferenceBlockText.addFocusListener(formListener);
        butDifferenceBlockText.addKeyListener(formListener);
        jPanel23.add(butDifferenceBlockText);

        jPanel24.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel24.setName("jPanel24"); // NOI18N

        butLimitLabel3.setFont(fuente);
        butLimitLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butLimitLabel3.setText(lang.getFrase(415));
        butLimitLabel3.setMaximumSize(new java.awt.Dimension(140, 14));
        butLimitLabel3.setMinimumSize(new java.awt.Dimension(140, 14));
        butLimitLabel3.setName("butLimitLabel3"); // NOI18N
        butLimitLabel3.setPreferredSize(new java.awt.Dimension(140, 14));
        butLimitLabel3.addFocusListener(formListener);
        butLimitLabel3.addMouseListener(formListener);
        jPanel24.add(butLimitLabel3);

        butLimitMethodBar.setFont(fuente);
        butLimitMethodBar.setMaximum(10000);
        butLimitMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butLimitMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butLimitMethodBar.setName("butLimitMethodBar"); // NOI18N
        butLimitMethodBar.addFocusListener(formListener);
        butLimitMethodBar.addChangeListener(formListener);
        jPanel24.add(butLimitMethodBar);

        butLimitMethodText.setFont(fuente);
        butLimitMethodText.setText(resourceMap.getString("butLimitMethodText.text")); // NOI18N
        butLimitMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butLimitMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butLimitMethodText.setName("butLimitMethodText"); // NOI18N
        butLimitMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butLimitMethodText.addFocusListener(formListener);
        butLimitMethodText.addKeyListener(formListener);
        jPanel24.add(butLimitMethodText);

        jPanel25.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel25.setName("jPanel25"); // NOI18N
        jPanel25.addFocusListener(formListener);

        butLimitLabel1.setFont(fuente);
        butLimitLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butLimitLabel1.setText(lang.getFrase(412));
        butLimitLabel1.setMaximumSize(new java.awt.Dimension(140, 14));
        butLimitLabel1.setMinimumSize(new java.awt.Dimension(140, 14));
        butLimitLabel1.setName("butLimitLabel1"); // NOI18N
        butLimitLabel1.setPreferredSize(new java.awt.Dimension(140, 14));
        butLimitLabel1.addFocusListener(formListener);
        butLimitLabel1.addMouseListener(formListener);
        jPanel25.add(butLimitLabel1);

        butLimitProgramBar.setFont(fuente);
        butLimitProgramBar.setMaximum(10000);
        butLimitProgramBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butLimitProgramBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butLimitProgramBar.setName("butLimitProgramBar"); // NOI18N
        butLimitProgramBar.addFocusListener(formListener);
        butLimitProgramBar.addChangeListener(formListener);
        jPanel25.add(butLimitProgramBar);

        butLimitProgramText.setFont(fuente);
        butLimitProgramText.setText(resourceMap.getString("butLimitProgramText.text")); // NOI18N
        butLimitProgramText.setMaximumSize(new java.awt.Dimension(50, 18));
        butLimitProgramText.setMinimumSize(new java.awt.Dimension(50, 18));
        butLimitProgramText.setName("butLimitProgramText"); // NOI18N
        butLimitProgramText.setPreferredSize(new java.awt.Dimension(50, 18));
        butLimitProgramText.addFocusListener(formListener);
        butLimitProgramText.addKeyListener(formListener);
        jPanel25.add(butLimitProgramText);

        butLimit.setFont(fuente);
        butLimit.setText(lang.getFrase(414));
        butLimit.setName("butLimit"); // NOI18N
        butLimit.addFocusListener(formListener);
        butLimit.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(butDifference, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .add(26, 26, 26))
            .add(jPanel14Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel14Layout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(116, 116, 116))
                    .add(butLimit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
                .add(10, 10, 10))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(butDifference)
                .add(10, 10, 10)
                .add(jPanel18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14)
                .add(butLimit)
                .add(10, 10, 10)
                .add(jPanel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(lang.getFrase(424), jPanel14);

        jPanel26.setMaximumSize(new java.awt.Dimension(569, 320));
        jPanel26.setMinimumSize(new java.awt.Dimension(569, 320));
        jPanel26.setName("jPanel26"); // NOI18N

        jPanel27.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel27.setName("jPanel27"); // NOI18N

        butAcceptableProgramMethod.setFont(fuente);
        butAcceptableProgramMethod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butAcceptableProgramMethod.setText(lang.getFrase(426));
        butAcceptableProgramMethod.setMaximumSize(new java.awt.Dimension(140, 14));
        butAcceptableProgramMethod.setMinimumSize(new java.awt.Dimension(140, 14));
        butAcceptableProgramMethod.setName("butAcceptableProgramMethod"); // NOI18N
        butAcceptableProgramMethod.setPreferredSize(new java.awt.Dimension(140, 14));
        butAcceptableProgramMethod.addFocusListener(formListener);
        butAcceptableProgramMethod.addMouseListener(formListener);
        jPanel27.add(butAcceptableProgramMethod);

        butAcceptableProgramMethodBar.setFont(fuente);
        butAcceptableProgramMethodBar.setMaximum(10000);
        butAcceptableProgramMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butAcceptableProgramMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butAcceptableProgramMethodBar.setName("butAcceptableProgramMethodBar"); // NOI18N
        butAcceptableProgramMethodBar.addFocusListener(formListener);
        butAcceptableProgramMethodBar.addChangeListener(formListener);
        jPanel27.add(butAcceptableProgramMethodBar);

        butAcceptableProgramMethodText.setFont(fuente);
        butAcceptableProgramMethodText.setText(resourceMap.getString("butAcceptableProgramMethodText.text")); // NOI18N
        butAcceptableProgramMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butAcceptableProgramMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butAcceptableProgramMethodText.setName("butAcceptableProgramMethodText"); // NOI18N
        butAcceptableProgramMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butAcceptableProgramMethodText.addFocusListener(formListener);
        butAcceptableProgramMethodText.addKeyListener(formListener);
        jPanel27.add(butAcceptableProgramMethodText);

        jPanel28.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel28.setName("jPanel28"); // NOI18N

        butAcceptableBlock.setFont(fuente);
        butAcceptableBlock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butAcceptableBlock.setText(lang.getFrase(407));
        butAcceptableBlock.setMaximumSize(new java.awt.Dimension(140, 14));
        butAcceptableBlock.setMinimumSize(new java.awt.Dimension(140, 14));
        butAcceptableBlock.setName("butAcceptableBlock"); // NOI18N
        butAcceptableBlock.setPreferredSize(new java.awt.Dimension(140, 14));
        butAcceptableBlock.addFocusListener(formListener);
        butAcceptableBlock.addMouseListener(formListener);
        jPanel28.add(butAcceptableBlock);

        butAcceptableBlockBar.setFont(fuente);
        butAcceptableBlockBar.setMaximum(10000);
        butAcceptableBlockBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butAcceptableBlockBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butAcceptableBlockBar.setName("butAcceptableBlockBar"); // NOI18N
        butAcceptableBlockBar.addFocusListener(formListener);
        butAcceptableBlockBar.addChangeListener(formListener);
        jPanel28.add(butAcceptableBlockBar);

        butAcceptableBlockText.setFont(fuente);
        butAcceptableBlockText.setText(resourceMap.getString("butAcceptableBlockText.text")); // NOI18N
        butAcceptableBlockText.setMaximumSize(new java.awt.Dimension(50, 18));
        butAcceptableBlockText.setMinimumSize(new java.awt.Dimension(50, 18));
        butAcceptableBlockText.setName("butAcceptableBlockText"); // NOI18N
        butAcceptableBlockText.setPreferredSize(new java.awt.Dimension(50, 18));
        butAcceptableBlockText.addFocusListener(formListener);
        butAcceptableBlockText.addKeyListener(formListener);
        jPanel28.add(butAcceptableBlockText);

        jPanel29.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel29.setName("jPanel29"); // NOI18N

        butAcceptableClassMethod.setFont(fuente);
        butAcceptableClassMethod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butAcceptableClassMethod.setText(lang.getFrase(406));
        butAcceptableClassMethod.setMaximumSize(new java.awt.Dimension(140, 14));
        butAcceptableClassMethod.setMinimumSize(new java.awt.Dimension(140, 14));
        butAcceptableClassMethod.setName("butAcceptableClassMethod"); // NOI18N
        butAcceptableClassMethod.setPreferredSize(new java.awt.Dimension(140, 14));
        butAcceptableClassMethod.addFocusListener(formListener);
        butAcceptableClassMethod.addMouseListener(formListener);
        jPanel29.add(butAcceptableClassMethod);

        butAcceptableClassMethodBar.setFont(fuente);
        butAcceptableClassMethodBar.setMaximum(10000);
        butAcceptableClassMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butAcceptableClassMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butAcceptableClassMethodBar.setName("butAcceptableClassMethodBar"); // NOI18N
        butAcceptableClassMethodBar.addFocusListener(formListener);
        butAcceptableClassMethodBar.addChangeListener(formListener);
        jPanel29.add(butAcceptableClassMethodBar);

        butAcceptableClassMethodText.setFont(fuente);
        butAcceptableClassMethodText.setText(resourceMap.getString("butAcceptableClassMethodText.text")); // NOI18N
        butAcceptableClassMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butAcceptableClassMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butAcceptableClassMethodText.setName("butAcceptableClassMethodText"); // NOI18N
        butAcceptableClassMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butAcceptableClassMethodText.addFocusListener(formListener);
        butAcceptableClassMethodText.addKeyListener(formListener);
        jPanel29.add(butAcceptableClassMethodText);

        jPanel30.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel30.setName("jPanel30"); // NOI18N

        butAcceptableClass.setFont(fuente);
        butAcceptableClass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butAcceptableClass.setText(lang.getFrase(413));
        butAcceptableClass.setMaximumSize(new java.awt.Dimension(140, 14));
        butAcceptableClass.setMinimumSize(new java.awt.Dimension(140, 14));
        butAcceptableClass.setName("butAcceptableClass"); // NOI18N
        butAcceptableClass.setPreferredSize(new java.awt.Dimension(140, 14));
        butAcceptableClass.addFocusListener(formListener);
        butAcceptableClass.addMouseListener(formListener);
        jPanel30.add(butAcceptableClass);

        butAcceptableClassBar.setFont(fuente);
        butAcceptableClassBar.setMaximum(10000);
        butAcceptableClassBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butAcceptableClassBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butAcceptableClassBar.setName("butAcceptableClassBar"); // NOI18N
        butAcceptableClassBar.addFocusListener(formListener);
        butAcceptableClassBar.addChangeListener(formListener);
        jPanel30.add(butAcceptableClassBar);

        butAcceptableClassText.setFont(fuente);
        butAcceptableClassText.setText(resourceMap.getString("butAcceptableClassText.text")); // NOI18N
        butAcceptableClassText.setMaximumSize(new java.awt.Dimension(50, 18));
        butAcceptableClassText.setMinimumSize(new java.awt.Dimension(50, 18));
        butAcceptableClassText.setName("butAcceptableClassText"); // NOI18N
        butAcceptableClassText.setPreferredSize(new java.awt.Dimension(50, 18));
        butAcceptableClassText.addFocusListener(formListener);
        butAcceptableClassText.addKeyListener(formListener);
        jPanel30.add(butAcceptableClassText);

        jPanel31.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel31.setName("jPanel31"); // NOI18N

        butMinimumProgramMethod.setFont(fuente);
        butMinimumProgramMethod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumProgramMethod.setText(lang.getFrase(426));
        butMinimumProgramMethod.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumProgramMethod.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumProgramMethod.setName("butMinimumProgramMethod"); // NOI18N
        butMinimumProgramMethod.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumProgramMethod.addFocusListener(formListener);
        butMinimumProgramMethod.addMouseListener(formListener);
        jPanel31.add(butMinimumProgramMethod);

        butMinimumProgramMethodBar.setFont(fuente);
        butMinimumProgramMethodBar.setMaximum(10000);
        butMinimumProgramMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butMinimumProgramMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butMinimumProgramMethodBar.setName("butMinimumProgramMethodBar"); // NOI18N
        butMinimumProgramMethodBar.addFocusListener(formListener);
        butMinimumProgramMethodBar.addChangeListener(formListener);
        jPanel31.add(butMinimumProgramMethodBar);

        butMinimumProgramMethodText.setFont(fuente);
        butMinimumProgramMethodText.setText(resourceMap.getString("butMinimumProgramMethodText.text")); // NOI18N
        butMinimumProgramMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butMinimumProgramMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butMinimumProgramMethodText.setName("butMinimumProgramMethodText"); // NOI18N
        butMinimumProgramMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butMinimumProgramMethodText.addFocusListener(formListener);
        butMinimumProgramMethodText.addKeyListener(formListener);
        jPanel31.add(butMinimumProgramMethodText);

        jPanel32.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel32.setName("jPanel32"); // NOI18N

        butMinimumBlock.setFont(fuente);
        butMinimumBlock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumBlock.setText(lang.getFrase(407));
        butMinimumBlock.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumBlock.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumBlock.setName("butMinimumBlock"); // NOI18N
        butMinimumBlock.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumBlock.addFocusListener(formListener);
        butMinimumBlock.addMouseListener(formListener);
        jPanel32.add(butMinimumBlock);

        butMinimumBlockBar.setFont(fuente);
        butMinimumBlockBar.setMaximum(10000);
        butMinimumBlockBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butMinimumBlockBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butMinimumBlockBar.setName("butMinimumBlockBar"); // NOI18N
        butMinimumBlockBar.addFocusListener(formListener);
        butMinimumBlockBar.addChangeListener(formListener);
        jPanel32.add(butMinimumBlockBar);

        butMinimumBlockText.setFont(fuente);
        butMinimumBlockText.setText(resourceMap.getString("butMinimumBlockText.text")); // NOI18N
        butMinimumBlockText.setMaximumSize(new java.awt.Dimension(50, 18));
        butMinimumBlockText.setMinimumSize(new java.awt.Dimension(50, 18));
        butMinimumBlockText.setName("butMinimumBlockText"); // NOI18N
        butMinimumBlockText.setPreferredSize(new java.awt.Dimension(50, 18));
        butMinimumBlockText.addFocusListener(formListener);
        butMinimumBlockText.addKeyListener(formListener);
        jPanel32.add(butMinimumBlockText);

        jPanel33.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel33.setName("jPanel33"); // NOI18N

        butMinimumClassMethod.setFont(fuente);
        butMinimumClassMethod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumClassMethod.setText(lang.getFrase(406));
        butMinimumClassMethod.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumClassMethod.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumClassMethod.setName("butMinimumClassMethod"); // NOI18N
        butMinimumClassMethod.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumClassMethod.addFocusListener(formListener);
        butMinimumClassMethod.addMouseListener(formListener);
        jPanel33.add(butMinimumClassMethod);

        butMinimumClassMethodBar.setFont(fuente);
        butMinimumClassMethodBar.setMaximum(10000);
        butMinimumClassMethodBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butMinimumClassMethodBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butMinimumClassMethodBar.setName("butMinimumClassMethodBar"); // NOI18N
        butMinimumClassMethodBar.addFocusListener(formListener);
        butMinimumClassMethodBar.addChangeListener(formListener);
        jPanel33.add(butMinimumClassMethodBar);

        butMinimumClassMethodText.setFont(fuente);
        butMinimumClassMethodText.setText(resourceMap.getString("butMinimumClassMethodText.text")); // NOI18N
        butMinimumClassMethodText.setMaximumSize(new java.awt.Dimension(50, 18));
        butMinimumClassMethodText.setMinimumSize(new java.awt.Dimension(50, 18));
        butMinimumClassMethodText.setName("butMinimumClassMethodText"); // NOI18N
        butMinimumClassMethodText.setPreferredSize(new java.awt.Dimension(50, 18));
        butMinimumClassMethodText.addFocusListener(formListener);
        butMinimumClassMethodText.addKeyListener(formListener);
        jPanel33.add(butMinimumClassMethodText);

        jPanel34.setMaximumSize(new java.awt.Dimension(410, 28));
        jPanel34.setName("jPanel34"); // NOI18N

        butMinimumClass.setFont(fuente);
        butMinimumClass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butMinimumClass.setText(lang.getFrase(413));
        butMinimumClass.setMaximumSize(new java.awt.Dimension(140, 14));
        butMinimumClass.setMinimumSize(new java.awt.Dimension(140, 14));
        butMinimumClass.setName("butMinimumClass"); // NOI18N
        butMinimumClass.setPreferredSize(new java.awt.Dimension(140, 14));
        butMinimumClass.addFocusListener(formListener);
        butMinimumClass.addMouseListener(formListener);
        jPanel34.add(butMinimumClass);

        butMinimumClassBar.setFont(fuente);
        butMinimumClassBar.setMaximum(10000);
        butMinimumClassBar.setMaximumSize(new java.awt.Dimension(200, 16));
        butMinimumClassBar.setMinimumSize(new java.awt.Dimension(200, 16));
        butMinimumClassBar.setName("butMinimumClassBar"); // NOI18N
        butMinimumClassBar.addFocusListener(formListener);
        butMinimumClassBar.addChangeListener(formListener);
        jPanel34.add(butMinimumClassBar);

        butMinimumClassText.setFont(fuente);
        butMinimumClassText.setText(resourceMap.getString("butMinimumClassText.text")); // NOI18N
        butMinimumClassText.setMaximumSize(new java.awt.Dimension(50, 18));
        butMinimumClassText.setMinimumSize(new java.awt.Dimension(50, 18));
        butMinimumClassText.setName("butMinimumClassText"); // NOI18N
        butMinimumClassText.setPreferredSize(new java.awt.Dimension(50, 18));
        butMinimumClassText.addFocusListener(formListener);
        butMinimumClassText.addKeyListener(formListener);
        jPanel34.add(butMinimumClassText);

        butAcceptable.setFont(fuente);
        butAcceptable.setText(lang.getFrase(416));
        butAcceptable.setName("butAcceptable"); // NOI18N
        butAcceptable.addFocusListener(formListener);
        butAcceptable.addMouseListener(formListener);

        butMinimum.setFont(fuente);
        butMinimum.setText(lang.getFrase(417));
        butMinimum.setName("butMinimum"); // NOI18N
        butMinimum.addFocusListener(formListener);
        butMinimum.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel26Layout = new org.jdesktop.layout.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel26Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
            .add(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel26Layout.createSequentialGroup()
                        .add(butAcceptable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(jPanel26Layout.createSequentialGroup()
                        .add(butMinimum, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                        .add(22, 22, 22))))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .add(butAcceptable)
                .add(10, 10, 10)
                .add(jPanel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14)
                .add(butMinimum)
                .add(10, 10, 10)
                .add(jPanel34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jPanel32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(lang.getFrase(425), jPanel26);

        jPanel4.setMaximumSize(new java.awt.Dimension(569, 320));
        jPanel4.setMinimumSize(new java.awt.Dimension(569, 320));
        jPanel4.setName("jPanel4"); // NOI18N

        butBlockWeightLabel0.setFont(fuente);
        butBlockWeightLabel0.setText(lang.getFrase(391));
        butBlockWeightLabel0.setName("butBlockWeightLabel0"); // NOI18N
        butBlockWeightLabel0.addFocusListener(formListener);
        butBlockWeightLabel0.addMouseListener(formListener);

        jPanel9.setName("jPanel9"); // NOI18N

        butBlockWeightLabel8.setFont(fuente);
        butBlockWeightLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel8.setText(resourceMap.getString("butBlockWeightLabel8.text")); // NOI18N
        butBlockWeightLabel8.setName("butBlockWeightLabel8"); // NOI18N
        butBlockWeightLabel8.addFocusListener(formListener);
        butBlockWeightLabel8.addMouseListener(formListener);

        butBlockWeightLabel7.setFont(fuente);
        butBlockWeightLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel7.setText(resourceMap.getString("butBlockWeightLabel7.text")); // NOI18N
        butBlockWeightLabel7.setName("butBlockWeightLabel7"); // NOI18N
        butBlockWeightLabel7.addFocusListener(formListener);
        butBlockWeightLabel7.addMouseListener(formListener);

        butBlockWeightLabel6.setFont(fuente);
        butBlockWeightLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel6.setText(resourceMap.getString("butBlockWeightLabel6.text")); // NOI18N
        butBlockWeightLabel6.setName("butBlockWeightLabel6"); // NOI18N
        butBlockWeightLabel6.addFocusListener(formListener);
        butBlockWeightLabel6.addMouseListener(formListener);

        butBlockWeightLabel5.setFont(fuente);
        butBlockWeightLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel5.setText(resourceMap.getString("butBlockWeightLabel5.text")); // NOI18N
        butBlockWeightLabel5.setName("butBlockWeightLabel5"); // NOI18N
        butBlockWeightLabel5.addFocusListener(formListener);
        butBlockWeightLabel5.addMouseListener(formListener);

        butBlockWeightLabel4.setFont(fuente);
        butBlockWeightLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel4.setText(resourceMap.getString("butBlockWeightLabel4.text")); // NOI18N
        butBlockWeightLabel4.setName("butBlockWeightLabel4"); // NOI18N
        butBlockWeightLabel4.addFocusListener(formListener);
        butBlockWeightLabel4.addMouseListener(formListener);

        butBlockWeightLabel3.setFont(fuente);
        butBlockWeightLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel3.setText(resourceMap.getString("butBlockWeightLabel3.text")); // NOI18N
        butBlockWeightLabel3.setName("butBlockWeightLabel3"); // NOI18N
        butBlockWeightLabel3.addFocusListener(formListener);
        butBlockWeightLabel3.addMouseListener(formListener);

        butBlockWeightLabel9.setFont(fuente);
        butBlockWeightLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel9.setText(resourceMap.getString("butBlockWeightLabel9.text")); // NOI18N
        butBlockWeightLabel9.setName("butBlockWeightLabel9"); // NOI18N
        butBlockWeightLabel9.addFocusListener(formListener);
        butBlockWeightLabel9.addMouseListener(formListener);

        butBlockWeightLabel10.setFont(fuente);
        butBlockWeightLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel10.setText(resourceMap.getString("butBlockWeightLabel10.text")); // NOI18N
        butBlockWeightLabel10.setName("butBlockWeightLabel10"); // NOI18N
        butBlockWeightLabel10.addFocusListener(formListener);
        butBlockWeightLabel10.addMouseListener(formListener);

        butBlockWeightInvokeStatic.setFont(fuente);
        butBlockWeightInvokeStatic.setText(resourceMap.getString("butBlockWeightInvokeStatic.text")); // NOI18N
        butBlockWeightInvokeStatic.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeStatic.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeStatic.setName("butBlockWeightInvokeStatic"); // NOI18N
        butBlockWeightInvokeStatic.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeStatic.addFocusListener(formListener);
        butBlockWeightInvokeStatic.addKeyListener(formListener);

        butBlockWeightInvokeVirtual.setFont(fuente);
        butBlockWeightInvokeVirtual.setText(resourceMap.getString("butBlockWeightInvokeVirtual.text")); // NOI18N
        butBlockWeightInvokeVirtual.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeVirtual.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeVirtual.setName("butBlockWeightInvokeVirtual"); // NOI18N
        butBlockWeightInvokeVirtual.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeVirtual.addFocusListener(formListener);
        butBlockWeightInvokeVirtual.addKeyListener(formListener);

        butBlockWeightInvokeSpecial.setFont(fuente);
        butBlockWeightInvokeSpecial.setText(resourceMap.getString("butBlockWeightInvokeSpecial.text")); // NOI18N
        butBlockWeightInvokeSpecial.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeSpecial.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeSpecial.setName("butBlockWeightInvokeSpecial"); // NOI18N
        butBlockWeightInvokeSpecial.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeSpecial.addFocusListener(formListener);
        butBlockWeightInvokeSpecial.addKeyListener(formListener);

        butBlockWeightMonitorEnter.setFont(fuente);
        butBlockWeightMonitorEnter.setText(resourceMap.getString("butBlockWeightMonitorEnter.text")); // NOI18N
        butBlockWeightMonitorEnter.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMonitorEnter.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMonitorEnter.setName("butBlockWeightMonitorEnter"); // NOI18N
        butBlockWeightMonitorEnter.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightMonitorEnter.addFocusListener(formListener);
        butBlockWeightMonitorEnter.addKeyListener(formListener);

        butBlockWeightMonitorExit.setFont(fuente);
        butBlockWeightMonitorExit.setText(resourceMap.getString("butBlockWeightMonitorExit.text")); // NOI18N
        butBlockWeightMonitorExit.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMonitorExit.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMonitorExit.setName("butBlockWeightMonitorExit"); // NOI18N
        butBlockWeightMonitorExit.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightMonitorExit.addFocusListener(formListener);
        butBlockWeightMonitorExit.addKeyListener(formListener);

        butBlockWeightTableSwitch.setFont(fuente);
        butBlockWeightTableSwitch.setText(resourceMap.getString("butBlockWeightTableSwitch.text")); // NOI18N
        butBlockWeightTableSwitch.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightTableSwitch.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightTableSwitch.setName("butBlockWeightTableSwitch"); // NOI18N
        butBlockWeightTableSwitch.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightTableSwitch.addFocusListener(formListener);
        butBlockWeightTableSwitch.addKeyListener(formListener);

        butBlockWeightInvokeInterface.setFont(fuente);
        butBlockWeightInvokeInterface.setText(resourceMap.getString("butBlockWeightInvokeInterface.text")); // NOI18N
        butBlockWeightInvokeInterface.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeInterface.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeInterface.setName("butBlockWeightInvokeInterface"); // NOI18N
        butBlockWeightInvokeInterface.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightInvokeInterface.addFocusListener(formListener);
        butBlockWeightInvokeInterface.addKeyListener(formListener);

        butBlockWeightLookupSwitch.setFont(fuente);
        butBlockWeightLookupSwitch.setText(resourceMap.getString("butBlockWeightLookupSwitch.text")); // NOI18N
        butBlockWeightLookupSwitch.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightLookupSwitch.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightLookupSwitch.setName("butBlockWeightLookupSwitch"); // NOI18N
        butBlockWeightLookupSwitch.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightLookupSwitch.addFocusListener(formListener);
        butBlockWeightLookupSwitch.addKeyListener(formListener);

        butBlockWeightLabel2.setFont(fuente);
        butBlockWeightLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel2.setText(lang.getFrase(394));
        butBlockWeightLabel2.setName("butBlockWeightLabel2"); // NOI18N
        butBlockWeightLabel2.addFocusListener(formListener);
        butBlockWeightLabel2.addMouseListener(formListener);

        butBlockWeightLabel1.setFont(fuente);
        butBlockWeightLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel1.setText(lang.getFrase(393));
        butBlockWeightLabel1.setName("butBlockWeightLabel1"); // NOI18N
        butBlockWeightLabel1.addFocusListener(formListener);
        butBlockWeightLabel1.addMouseListener(formListener);

        butBlockWeightInstruction.setFont(fuente);
        butBlockWeightInstruction.setText(resourceMap.getString("butBlockWeightInstruction.text")); // NOI18N
        butBlockWeightInstruction.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInstruction.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInstruction.setName("butBlockWeightInstruction"); // NOI18N
        butBlockWeightInstruction.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightInstruction.addFocusListener(formListener);
        butBlockWeightInstruction.addKeyListener(formListener);

        butBlockWeightMethodEnd.setFont(fuente);
        butBlockWeightMethodEnd.setText(resourceMap.getString("butBlockWeightMethodEnd.text")); // NOI18N
        butBlockWeightMethodEnd.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMethodEnd.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMethodEnd.setName("butBlockWeightMethodEnd"); // NOI18N
        butBlockWeightMethodEnd.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightMethodEnd.addFocusListener(formListener);
        butBlockWeightMethodEnd.addKeyListener(formListener);

        butBlockWeightLabel.setFont(fuente);
        butBlockWeightLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel.setText(lang.getFrase(392));
        butBlockWeightLabel.setName("butBlockWeightLabel"); // NOI18N
        butBlockWeightLabel.addFocusListener(formListener);
        butBlockWeightLabel.addMouseListener(formListener);

        butBlockWeightMethodStart.setFont(fuente);
        butBlockWeightMethodStart.setText(resourceMap.getString("butBlockWeightMethodStart.text")); // NOI18N
        butBlockWeightMethodStart.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMethodStart.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMethodStart.setName("butBlockWeightMethodStart"); // NOI18N
        butBlockWeightMethodStart.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightMethodStart.addFocusListener(formListener);
        butBlockWeightMethodStart.addKeyListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel9Layout.createSequentialGroup()
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .add(butBlockWeightLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel10))
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel9Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightInvokeStatic, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(butBlockWeightInvokeVirtual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightInvokeSpecial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightMonitorEnter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightMonitorExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightTableSwitch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(jPanel9Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightInvokeInterface, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel9Layout.createSequentialGroup()
                                .add(12, 12, 12)
                                .add(butBlockWeightLookupSwitch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, butBlockWeightLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(butBlockWeightInstruction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightMethodEnd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(butBlockWeightLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(butBlockWeightMethodStart, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightMethodStart, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightMethodEnd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightInstruction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel3)
                    .add(butBlockWeightInvokeInterface, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightInvokeStatic, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightInvokeSpecial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel6)
                    .add(butBlockWeightInvokeVirtual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightMonitorEnter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightMonitorExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel9)
                    .add(butBlockWeightTableSwitch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLookupSwitch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel10))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setName("jPanel10"); // NOI18N

        butBlockWeightLabel19.setFont(fuente);
        butBlockWeightLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel19.setText(resourceMap.getString("butBlockWeightLabel19.text")); // NOI18N
        butBlockWeightLabel19.setName("butBlockWeightLabel19"); // NOI18N
        butBlockWeightLabel19.addFocusListener(formListener);
        butBlockWeightLabel19.addMouseListener(formListener);

        butBlockWeightLabel18.setFont(fuente);
        butBlockWeightLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel18.setText(resourceMap.getString("butBlockWeightLabel18.text")); // NOI18N
        butBlockWeightLabel18.setName("butBlockWeightLabel18"); // NOI18N
        butBlockWeightLabel18.addFocusListener(formListener);
        butBlockWeightLabel18.addMouseListener(formListener);

        butBlockWeightLabel17.setFont(fuente);
        butBlockWeightLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel17.setText(resourceMap.getString("butBlockWeightLabel17.text")); // NOI18N
        butBlockWeightLabel17.setName("butBlockWeightLabel17"); // NOI18N
        butBlockWeightLabel17.addFocusListener(formListener);
        butBlockWeightLabel17.addMouseListener(formListener);

        butBlockWeightLabel16.setFont(fuente);
        butBlockWeightLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel16.setText(resourceMap.getString("butBlockWeightLabel16.text")); // NOI18N
        butBlockWeightLabel16.setName("butBlockWeightLabel16"); // NOI18N
        butBlockWeightLabel16.addFocusListener(formListener);
        butBlockWeightLabel16.addMouseListener(formListener);

        butBlockWeightLabel15.setFont(fuente);
        butBlockWeightLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel15.setText(resourceMap.getString("butBlockWeightLabel15.text")); // NOI18N
        butBlockWeightLabel15.setName("butBlockWeightLabel15"); // NOI18N
        butBlockWeightLabel15.addFocusListener(formListener);
        butBlockWeightLabel15.addMouseListener(formListener);

        butBlockWeightLabel20.setFont(fuente);
        butBlockWeightLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel20.setText(resourceMap.getString("butBlockWeightLabel20.text")); // NOI18N
        butBlockWeightLabel20.setName("butBlockWeightLabel20"); // NOI18N
        butBlockWeightLabel20.addFocusListener(formListener);
        butBlockWeightLabel20.addMouseListener(formListener);

        butBlockWeightLabel21.setFont(fuente);
        butBlockWeightLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel21.setText(resourceMap.getString("butBlockWeightLabel21.text")); // NOI18N
        butBlockWeightLabel21.setName("butBlockWeightLabel21"); // NOI18N
        butBlockWeightLabel21.addFocusListener(formListener);
        butBlockWeightLabel21.addMouseListener(formListener);

        butBlockWeightLabel14.setFont(fuente);
        butBlockWeightLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel14.setText(resourceMap.getString("butBlockWeightLabel14.text")); // NOI18N
        butBlockWeightLabel14.setName("butBlockWeightLabel14"); // NOI18N
        butBlockWeightLabel14.addFocusListener(formListener);
        butBlockWeightLabel14.addMouseListener(formListener);

        butBlockWeightNewArray.setFont(fuente);
        butBlockWeightNewArray.setText(resourceMap.getString("butBlockWeightNewArray.text")); // NOI18N
        butBlockWeightNewArray.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightNewArray.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightNewArray.setName("butBlockWeightNewArray"); // NOI18N
        butBlockWeightNewArray.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightNewArray.addFocusListener(formListener);
        butBlockWeightNewArray.addKeyListener(formListener);

        butBlockWeightReturn.setFont(fuente);
        butBlockWeightReturn.setText(resourceMap.getString("butBlockWeightReturn.text")); // NOI18N
        butBlockWeightReturn.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightReturn.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightReturn.setName("butBlockWeightReturn"); // NOI18N
        butBlockWeightReturn.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightReturn.addFocusListener(formListener);
        butBlockWeightReturn.addKeyListener(formListener);

        butBlockWeightNew.setFont(fuente);
        butBlockWeightNew.setText(resourceMap.getString("butBlockWeightNew.text")); // NOI18N
        butBlockWeightNew.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightNew.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightNew.setName("butBlockWeightNew"); // NOI18N
        butBlockWeightNew.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightNew.addFocusListener(formListener);
        butBlockWeightNew.addKeyListener(formListener);

        butBlockWeightConst.setFont(fuente);
        butBlockWeightConst.setText(resourceMap.getString("butBlockWeightConst.text")); // NOI18N
        butBlockWeightConst.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightConst.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightConst.setName("butBlockWeightConst"); // NOI18N
        butBlockWeightConst.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightConst.addFocusListener(formListener);
        butBlockWeightConst.addKeyListener(formListener);

        butBlockWeightThrow.setFont(fuente);
        butBlockWeightThrow.setText(resourceMap.getString("butBlockWeightThrow.text")); // NOI18N
        butBlockWeightThrow.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightThrow.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightThrow.setName("butBlockWeightThrow"); // NOI18N
        butBlockWeightThrow.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightThrow.addFocusListener(formListener);
        butBlockWeightThrow.addKeyListener(formListener);

        butBlockWeightShiftLeft.setFont(fuente);
        butBlockWeightShiftLeft.setText(resourceMap.getString("butBlockWeightShiftLeft.text")); // NOI18N
        butBlockWeightShiftLeft.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightShiftLeft.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightShiftLeft.setName("butBlockWeightShiftLeft"); // NOI18N
        butBlockWeightShiftLeft.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightShiftLeft.addFocusListener(formListener);
        butBlockWeightShiftLeft.addKeyListener(formListener);

        butBlockWeightPutField.setFont(fuente);
        butBlockWeightPutField.setText(resourceMap.getString("butBlockWeightPutField.text")); // NOI18N
        butBlockWeightPutField.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightPutField.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightPutField.setName("butBlockWeightPutField"); // NOI18N
        butBlockWeightPutField.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightPutField.addFocusListener(formListener);
        butBlockWeightPutField.addKeyListener(formListener);

        butBlockWeightShiftRight.setFont(fuente);
        butBlockWeightShiftRight.setText(resourceMap.getString("butBlockWeightShiftRight.text")); // NOI18N
        butBlockWeightShiftRight.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightShiftRight.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightShiftRight.setName("butBlockWeightShiftRight"); // NOI18N
        butBlockWeightShiftRight.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightShiftRight.addFocusListener(formListener);
        butBlockWeightShiftRight.addKeyListener(formListener);

        butBlockWeightLabel12.setFont(fuente);
        butBlockWeightLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel12.setText(resourceMap.getString("butBlockWeightLabel12.text")); // NOI18N
        butBlockWeightLabel12.setName("butBlockWeightLabel12"); // NOI18N
        butBlockWeightLabel12.addFocusListener(formListener);
        butBlockWeightLabel12.addMouseListener(formListener);

        butBlockWeightGetStatic.setFont(fuente);
        butBlockWeightGetStatic.setText(resourceMap.getString("butBlockWeightGetStatic.text")); // NOI18N
        butBlockWeightGetStatic.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightGetStatic.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightGetStatic.setName("butBlockWeightGetStatic"); // NOI18N
        butBlockWeightGetStatic.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightGetStatic.addFocusListener(formListener);
        butBlockWeightGetStatic.addKeyListener(formListener);

        butBlockWeightLabel13.setFont(fuente);
        butBlockWeightLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel13.setText(resourceMap.getString("butBlockWeightLabel13.text")); // NOI18N
        butBlockWeightLabel13.setName("butBlockWeightLabel13"); // NOI18N
        butBlockWeightLabel13.addFocusListener(formListener);
        butBlockWeightLabel13.addMouseListener(formListener);

        butBlockWeightGetField.setFont(fuente);
        butBlockWeightGetField.setText(resourceMap.getString("butBlockWeightGetField.text")); // NOI18N
        butBlockWeightGetField.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightGetField.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightGetField.setName("butBlockWeightGetField"); // NOI18N
        butBlockWeightGetField.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightGetField.addFocusListener(formListener);
        butBlockWeightGetField.addKeyListener(formListener);

        butBlockWeightLabel11.setFont(fuente);
        butBlockWeightLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel11.setText(resourceMap.getString("butBlockWeightLabel11.text")); // NOI18N
        butBlockWeightLabel11.setName("butBlockWeightLabel11"); // NOI18N
        butBlockWeightLabel11.addFocusListener(formListener);
        butBlockWeightLabel11.addMouseListener(formListener);

        butBlockWeightCheckCast.setFont(fuente);
        butBlockWeightCheckCast.setText(resourceMap.getString("butBlockWeightCheckCast.text")); // NOI18N
        butBlockWeightCheckCast.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightCheckCast.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightCheckCast.setName("butBlockWeightCheckCast"); // NOI18N
        butBlockWeightCheckCast.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightCheckCast.addFocusListener(formListener);
        butBlockWeightCheckCast.addKeyListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel10Layout.createSequentialGroup()
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel19, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel18, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel21)
                            .add(butBlockWeightLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel10Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightNewArray, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(butBlockWeightReturn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightConst, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightThrow, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightShiftLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(jPanel10Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightPutField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel10Layout.createSequentialGroup()
                                .add(12, 12, 12)
                                .add(butBlockWeightShiftRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(butBlockWeightLabel12)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(butBlockWeightGetStatic, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10Layout.createSequentialGroup()
                            .add(butBlockWeightLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(butBlockWeightGetField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10Layout.createSequentialGroup()
                            .add(butBlockWeightLabel11)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(butBlockWeightCheckCast, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightCheckCast, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel11))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightGetStatic, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightGetField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightPutField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightNewArray, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel15))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel16))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel17)
                    .add(butBlockWeightReturn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightConst, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel18))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightThrow, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel19))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel20)
                    .add(butBlockWeightShiftLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightShiftRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel21))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setName("jPanel11"); // NOI18N

        butBlockWeightLabel30.setFont(fuente);
        butBlockWeightLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel30.setText(resourceMap.getString("butBlockWeightLabel30.text")); // NOI18N
        butBlockWeightLabel30.setName("butBlockWeightLabel30"); // NOI18N
        butBlockWeightLabel30.addFocusListener(formListener);
        butBlockWeightLabel30.addMouseListener(formListener);

        butBlockWeightLabel29.setFont(fuente);
        butBlockWeightLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel29.setText(resourceMap.getString("butBlockWeightLabel29.text")); // NOI18N
        butBlockWeightLabel29.setName("butBlockWeightLabel29"); // NOI18N
        butBlockWeightLabel29.addFocusListener(formListener);
        butBlockWeightLabel29.addMouseListener(formListener);

        butBlockWeightLabel28.setFont(fuente);
        butBlockWeightLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel28.setText(resourceMap.getString("butBlockWeightLabel28.text")); // NOI18N
        butBlockWeightLabel28.setName("butBlockWeightLabel28"); // NOI18N
        butBlockWeightLabel28.addFocusListener(formListener);
        butBlockWeightLabel28.addMouseListener(formListener);

        butBlockWeightLabel27.setFont(fuente);
        butBlockWeightLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel27.setText(resourceMap.getString("butBlockWeightLabel27.text")); // NOI18N
        butBlockWeightLabel27.setName("butBlockWeightLabel27"); // NOI18N
        butBlockWeightLabel27.addFocusListener(formListener);
        butBlockWeightLabel27.addMouseListener(formListener);

        butBlockWeightLabel26.setFont(fuente);
        butBlockWeightLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel26.setText(resourceMap.getString("butBlockWeightLabel26.text")); // NOI18N
        butBlockWeightLabel26.setName("butBlockWeightLabel26"); // NOI18N
        butBlockWeightLabel26.addFocusListener(formListener);
        butBlockWeightLabel26.addMouseListener(formListener);

        butBlockWeightLabel31.setFont(fuente);
        butBlockWeightLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel31.setText(resourceMap.getString("butBlockWeightLabel31.text")); // NOI18N
        butBlockWeightLabel31.setName("butBlockWeightLabel31"); // NOI18N
        butBlockWeightLabel31.addFocusListener(formListener);
        butBlockWeightLabel31.addMouseListener(formListener);

        butBlockWeightLabel32.setFont(fuente);
        butBlockWeightLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel32.setText(resourceMap.getString("butBlockWeightLabel32.text")); // NOI18N
        butBlockWeightLabel32.setName("butBlockWeightLabel32"); // NOI18N
        butBlockWeightLabel32.addFocusListener(formListener);
        butBlockWeightLabel32.addMouseListener(formListener);

        butBlockWeightLabel25.setFont(fuente);
        butBlockWeightLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel25.setText(resourceMap.getString("butBlockWeightLabel25.text")); // NOI18N
        butBlockWeightLabel25.setName("butBlockWeightLabel25"); // NOI18N
        butBlockWeightLabel25.addFocusListener(formListener);
        butBlockWeightLabel25.addMouseListener(formListener);

        butBlockWeightGoto.setFont(fuente);
        butBlockWeightGoto.setText(resourceMap.getString("butBlockWeightGoto.text")); // NOI18N
        butBlockWeightGoto.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightGoto.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightGoto.setName("butBlockWeightGoto"); // NOI18N
        butBlockWeightGoto.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightGoto.addFocusListener(formListener);
        butBlockWeightGoto.addKeyListener(formListener);

        butBlockWeightJSR.setFont(fuente);
        butBlockWeightJSR.setText(resourceMap.getString("butBlockWeightJSR.text")); // NOI18N
        butBlockWeightJSR.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightJSR.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightJSR.setName("butBlockWeightJSR"); // NOI18N
        butBlockWeightJSR.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightJSR.addFocusListener(formListener);
        butBlockWeightJSR.addKeyListener(formListener);

        butBlockWeightIf.setFont(fuente);
        butBlockWeightIf.setText(resourceMap.getString("butBlockWeightIf.text")); // NOI18N
        butBlockWeightIf.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightIf.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightIf.setName("butBlockWeightIf"); // NOI18N
        butBlockWeightIf.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightIf.addFocusListener(formListener);
        butBlockWeightIf.addKeyListener(formListener);

        butBlockWeightPush.setFont(fuente);
        butBlockWeightPush.setText(resourceMap.getString("butBlockWeightPush.text")); // NOI18N
        butBlockWeightPush.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightPush.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightPush.setName("butBlockWeightPush"); // NOI18N
        butBlockWeightPush.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightPush.addFocusListener(formListener);
        butBlockWeightPush.addKeyListener(formListener);

        butBlockWeightPop.setFont(fuente);
        butBlockWeightPop.setText(resourceMap.getString("butBlockWeightPop.text")); // NOI18N
        butBlockWeightPop.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightPop.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightPop.setName("butBlockWeightPop"); // NOI18N
        butBlockWeightPop.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightPop.addFocusListener(formListener);
        butBlockWeightPop.addKeyListener(formListener);

        butBlockWeightSwap.setFont(fuente);
        butBlockWeightSwap.setText(resourceMap.getString("butBlockWeightSwap.text")); // NOI18N
        butBlockWeightSwap.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightSwap.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightSwap.setName("butBlockWeightSwap"); // NOI18N
        butBlockWeightSwap.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightSwap.addFocusListener(formListener);
        butBlockWeightSwap.addKeyListener(formListener);

        butBlockWeightLDC.setFont(fuente);
        butBlockWeightLDC.setText(resourceMap.getString("butBlockWeightLDC.text")); // NOI18N
        butBlockWeightLDC.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightLDC.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightLDC.setName("butBlockWeightLDC"); // NOI18N
        butBlockWeightLDC.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightLDC.addFocusListener(formListener);
        butBlockWeightLDC.addKeyListener(formListener);

        butBlockWeightDup.setFont(fuente);
        butBlockWeightDup.setText(resourceMap.getString("butBlockWeightDup.text")); // NOI18N
        butBlockWeightDup.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightDup.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightDup.setName("butBlockWeightDup"); // NOI18N
        butBlockWeightDup.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightDup.addFocusListener(formListener);
        butBlockWeightDup.addKeyListener(formListener);

        butBlockWeightStore.setFont(fuente);
        butBlockWeightStore.setText(resourceMap.getString("butBlockWeightStore.text")); // NOI18N
        butBlockWeightStore.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightStore.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightStore.setName("butBlockWeightStore"); // NOI18N
        butBlockWeightStore.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightStore.addFocusListener(formListener);
        butBlockWeightStore.addKeyListener(formListener);

        butBlockWeightLoad.setFont(fuente);
        butBlockWeightLoad.setText(resourceMap.getString("butBlockWeightLoad.text")); // NOI18N
        butBlockWeightLoad.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightLoad.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightLoad.setName("butBlockWeightLoad"); // NOI18N
        butBlockWeightLoad.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightLoad.addFocusListener(formListener);
        butBlockWeightLoad.addKeyListener(formListener);

        butBlockWeightInc.setFont(fuente);
        butBlockWeightInc.setText(resourceMap.getString("butBlockWeightInc.text")); // NOI18N
        butBlockWeightInc.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInc.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightInc.setName("butBlockWeightInc"); // NOI18N
        butBlockWeightInc.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightInc.addFocusListener(formListener);
        butBlockWeightInc.addKeyListener(formListener);

        butBlockWeightLabel23.setFont(fuente);
        butBlockWeightLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel23.setText(resourceMap.getString("butBlockWeightLabel23.text")); // NOI18N
        butBlockWeightLabel23.setName("butBlockWeightLabel23"); // NOI18N
        butBlockWeightLabel23.addFocusListener(formListener);
        butBlockWeightLabel23.addMouseListener(formListener);

        butBlockWeightLabel24.setFont(fuente);
        butBlockWeightLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel24.setText(resourceMap.getString("butBlockWeightLabel24.text")); // NOI18N
        butBlockWeightLabel24.setName("butBlockWeightLabel24"); // NOI18N
        butBlockWeightLabel24.addFocusListener(formListener);
        butBlockWeightLabel24.addMouseListener(formListener);

        butBlockWeightLabel22.setFont(fuente);
        butBlockWeightLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel22.setText(resourceMap.getString("butBlockWeightLabel22.text")); // NOI18N
        butBlockWeightLabel22.setName("butBlockWeightLabel22"); // NOI18N
        butBlockWeightLabel22.addFocusListener(formListener);
        butBlockWeightLabel22.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel11Layout.createSequentialGroup()
                        .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel30, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel29, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel28, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel27, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel26, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel31, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(butBlockWeightLabel32)
                            .add(butBlockWeightLabel25))
                        .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel11Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightGoto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(butBlockWeightJSR, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightIf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightPush, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightPop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightSwap, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(jPanel11Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(butBlockWeightLDC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightDup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightStore, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightLoad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(jPanel11Layout.createSequentialGroup()
                                .add(12, 12, 12)
                                .add(butBlockWeightInc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(butBlockWeightLabel23)
                        .add(60, 60, 60))
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, butBlockWeightLabel24, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, butBlockWeightLabel22))
                        .add(60, 60, 60)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel22)
                    .add(butBlockWeightLoad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel23)
                    .add(butBlockWeightStore, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel24)
                    .add(butBlockWeightLDC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightDup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel25))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightGoto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel26))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightIf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel27))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel28)
                    .add(butBlockWeightJSR, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightPush, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel29))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightPop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel30))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightLabel31)
                    .add(butBlockWeightSwap, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butBlockWeightInc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butBlockWeightLabel32))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setName("jPanel12"); // NOI18N

        butBlockWeightLabel41.setFont(fuente);
        butBlockWeightLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel41.setText(resourceMap.getString("butBlockWeightLabel41.text")); // NOI18N
        butBlockWeightLabel41.setName("butBlockWeightLabel41"); // NOI18N
        butBlockWeightLabel41.addFocusListener(formListener);
        butBlockWeightLabel41.addMouseListener(formListener);

        butBlockWeightLabel40.setFont(fuente);
        butBlockWeightLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel40.setText(resourceMap.getString("butBlockWeightLabel40.text")); // NOI18N
        butBlockWeightLabel40.setName("butBlockWeightLabel40"); // NOI18N
        butBlockWeightLabel40.addFocusListener(formListener);
        butBlockWeightLabel40.addMouseListener(formListener);

        butBlockWeightLabel39.setFont(fuente);
        butBlockWeightLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel39.setText(resourceMap.getString("butBlockWeightLabel39.text")); // NOI18N
        butBlockWeightLabel39.setName("butBlockWeightLabel39"); // NOI18N
        butBlockWeightLabel39.addFocusListener(formListener);
        butBlockWeightLabel39.addMouseListener(formListener);

        butBlockWeightLabel38.setFont(fuente);
        butBlockWeightLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel38.setText(resourceMap.getString("butBlockWeightLabel38.text")); // NOI18N
        butBlockWeightLabel38.setName("butBlockWeightLabel38"); // NOI18N
        butBlockWeightLabel38.addFocusListener(formListener);
        butBlockWeightLabel38.addMouseListener(formListener);

        butBlockWeightLabel37.setFont(fuente);
        butBlockWeightLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel37.setText(resourceMap.getString("butBlockWeightLabel37.text")); // NOI18N
        butBlockWeightLabel37.setName("butBlockWeightLabel37"); // NOI18N
        butBlockWeightLabel37.addFocusListener(formListener);
        butBlockWeightLabel37.addMouseListener(formListener);

        butBlockWeightLabel36.setFont(fuente);
        butBlockWeightLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel36.setText(resourceMap.getString("butBlockWeightLabel36.text")); // NOI18N
        butBlockWeightLabel36.setName("butBlockWeightLabel36"); // NOI18N
        butBlockWeightLabel36.addFocusListener(formListener);
        butBlockWeightLabel36.addMouseListener(formListener);

        butBlockWeightSub.setFont(fuente);
        butBlockWeightSub.setText(resourceMap.getString("butBlockWeightSub.text")); // NOI18N
        butBlockWeightSub.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightSub.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightSub.setName("butBlockWeightSub"); // NOI18N
        butBlockWeightSub.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightSub.addFocusListener(formListener);
        butBlockWeightSub.addKeyListener(formListener);

        butBlockWeightNeg.setFont(fuente);
        butBlockWeightNeg.setText(resourceMap.getString("butBlockWeightNeg.text")); // NOI18N
        butBlockWeightNeg.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightNeg.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightNeg.setName("butBlockWeightNeg"); // NOI18N
        butBlockWeightNeg.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightNeg.addFocusListener(formListener);
        butBlockWeightNeg.addKeyListener(formListener);

        butBlockWeightAnd.setFont(fuente);
        butBlockWeightAnd.setText(resourceMap.getString("butBlockWeightAnd.text")); // NOI18N
        butBlockWeightAnd.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightAnd.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightAnd.setName("butBlockWeightAnd"); // NOI18N
        butBlockWeightAnd.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightAnd.addFocusListener(formListener);
        butBlockWeightAnd.addKeyListener(formListener);

        butBlockWeightOr.setFont(fuente);
        butBlockWeightOr.setText(resourceMap.getString("butBlockWeightOr.text")); // NOI18N
        butBlockWeightOr.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightOr.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightOr.setName("butBlockWeightOr"); // NOI18N
        butBlockWeightOr.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightOr.addFocusListener(formListener);
        butBlockWeightOr.addKeyListener(formListener);

        butBlockWeightRet.setFont(fuente);
        butBlockWeightRet.setText(resourceMap.getString("butBlockWeightRet.text")); // NOI18N
        butBlockWeightRet.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightRet.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightRet.setName("butBlockWeightRet"); // NOI18N
        butBlockWeightRet.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightRet.addFocusListener(formListener);
        butBlockWeightRet.addKeyListener(formListener);

        butBlockWeightAdd.setFont(fuente);
        butBlockWeightAdd.setText(resourceMap.getString("butBlockWeightAdd.text")); // NOI18N
        butBlockWeightAdd.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightAdd.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightAdd.setName("butBlockWeightAdd"); // NOI18N
        butBlockWeightAdd.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightAdd.addFocusListener(formListener);
        butBlockWeightAdd.addKeyListener(formListener);

        butBlockWeightLabel34.setFont(fuente);
        butBlockWeightLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel34.setText(resourceMap.getString("butBlockWeightLabel34.text")); // NOI18N
        butBlockWeightLabel34.setName("butBlockWeightLabel34"); // NOI18N
        butBlockWeightLabel34.addFocusListener(formListener);
        butBlockWeightLabel34.addMouseListener(formListener);

        butBlockWeightRem.setFont(fuente);
        butBlockWeightRem.setText(resourceMap.getString("butBlockWeightRem.text")); // NOI18N
        butBlockWeightRem.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightRem.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightRem.setName("butBlockWeightRem"); // NOI18N
        butBlockWeightRem.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightRem.addFocusListener(formListener);
        butBlockWeightRem.addKeyListener(formListener);

        butBlockWeightLabel35.setFont(fuente);
        butBlockWeightLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel35.setText(resourceMap.getString("butBlockWeightLabel35.text")); // NOI18N
        butBlockWeightLabel35.setName("butBlockWeightLabel35"); // NOI18N
        butBlockWeightLabel35.addFocusListener(formListener);
        butBlockWeightLabel35.addMouseListener(formListener);

        butBlockWeightMul.setFont(fuente);
        butBlockWeightMul.setText(resourceMap.getString("butBlockWeightMul.text")); // NOI18N
        butBlockWeightMul.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMul.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightMul.setName("butBlockWeightMul"); // NOI18N
        butBlockWeightMul.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightMul.addFocusListener(formListener);
        butBlockWeightMul.addKeyListener(formListener);

        butBlockWeightLabel33.setFont(fuente);
        butBlockWeightLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel33.setText(resourceMap.getString("butBlockWeightLabel33.text")); // NOI18N
        butBlockWeightLabel33.setName("butBlockWeightLabel33"); // NOI18N
        butBlockWeightLabel33.addFocusListener(formListener);
        butBlockWeightLabel33.addMouseListener(formListener);

        butBlockWeightDiv.setFont(fuente);
        butBlockWeightDiv.setText(resourceMap.getString("butBlockWeightDiv.text")); // NOI18N
        butBlockWeightDiv.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightDiv.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightDiv.setName("butBlockWeightDiv"); // NOI18N
        butBlockWeightDiv.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightDiv.addFocusListener(formListener);
        butBlockWeightDiv.addKeyListener(formListener);

        butBlockWeightLabel42.setFont(fuente);
        butBlockWeightLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        butBlockWeightLabel42.setText(resourceMap.getString("butBlockWeightLabel42.text")); // NOI18N
        butBlockWeightLabel42.setName("butBlockWeightLabel42"); // NOI18N
        butBlockWeightLabel42.addFocusListener(formListener);
        butBlockWeightLabel42.addMouseListener(formListener);

        butBlockWeightXor.setFont(fuente);
        butBlockWeightXor.setText(resourceMap.getString("butBlockWeightXor.text")); // NOI18N
        butBlockWeightXor.setMaximumSize(new java.awt.Dimension(50, 18));
        butBlockWeightXor.setMinimumSize(new java.awt.Dimension(50, 18));
        butBlockWeightXor.setName("butBlockWeightXor"); // NOI18N
        butBlockWeightXor.setPreferredSize(new java.awt.Dimension(50, 18));
        butBlockWeightXor.addFocusListener(formListener);
        butBlockWeightXor.addKeyListener(formListener);
        butBlockWeightXor.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel12Layout.createSequentialGroup()
                            .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel41, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, butBlockWeightLabel40, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(butBlockWeightLabel39, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(butBlockWeightLabel38, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(butBlockWeightLabel37, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(butBlockWeightLabel36))
                            .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel12Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(butBlockWeightNeg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightAnd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightOr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(butBlockWeightRet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(jPanel12Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(butBlockWeightAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(jPanel12Layout.createSequentialGroup()
                            .add(butBlockWeightLabel34)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(butBlockWeightRem, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                                .add(butBlockWeightLabel35, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(butBlockWeightMul, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                                .add(butBlockWeightLabel33)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(butBlockWeightDiv, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                        .add(butBlockWeightLabel42)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(butBlockWeightXor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel12Layout.createSequentialGroup()
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightDiv, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel33))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightRem, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel34))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightMul, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel35))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel36))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightSub, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel37))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightAnd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel38))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightLabel39)
                            .add(butBlockWeightNeg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightOr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel40))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightRet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel41)))
                    .add(jPanel12Layout.createSequentialGroup()
                        .add(216, 216, 216)
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butBlockWeightXor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(butBlockWeightLabel42))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(butBlockWeightLabel0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                        .add(111, 111, 111))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(butBlockWeightLabel0)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(lang.getFrase(395), jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel5.setName("jPanel5"); // NOI18N

        atribVelocidadLabel.setFont(fuente2);
        atribVelocidadLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        atribVelocidadLabel.setText(lang.getFrase(347)+" (SP)");
        atribVelocidadLabel.setToolTipText(lang.getFrase(353));
        atribVelocidadLabel.setName("atribVelocidadLabel"); // NOI18N
        atribVelocidadLabel.addFocusListener(formListener);
        atribVelocidadLabel.addMouseListener(formListener);

        atribVelocidad.setEditable(false);
        atribVelocidad.setFont(fuente);
        atribVelocidad.setToolTipText(lang.getFrase(353));
        atribVelocidad.setName("atribVelocidad"); // NOI18N
        atribVelocidad.addFocusListener(formListener);
        atribVelocidad.addMouseListener(formListener);

        atribDetalleLabel.setFont(fuente2);
        atribDetalleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        atribDetalleLabel.setText(lang.getFrase(348)+" (DE)");
        atribDetalleLabel.setToolTipText(lang.getFrase(354));
        atribDetalleLabel.setName("atribDetalleLabel"); // NOI18N
        atribDetalleLabel.addFocusListener(formListener);
        atribDetalleLabel.addMouseListener(formListener);

        atribDetalle.setEditable(false);
        atribDetalle.setFont(fuente);
        atribDetalle.setToolTipText(lang.getFrase(354));
        atribDetalle.setName("atribDetalle"); // NOI18N
        atribDetalle.addFocusListener(formListener);
        atribDetalle.addMouseListener(formListener);

        atribPrecision.setEditable(false);
        atribPrecision.setFont(fuente);
        atribPrecision.setToolTipText(lang.getFrase(355));
        atribPrecision.setName("atribPrecision"); // NOI18N
        atribPrecision.addFocusListener(formListener);
        atribPrecision.addMouseListener(formListener);

        atribPrecisionLabel.setFont(fuente2);
        atribPrecisionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        atribPrecisionLabel.setText(lang.getFrase(349)+" (PR)");
        atribPrecisionLabel.setToolTipText(lang.getFrase(355));
        atribPrecisionLabel.setName("atribPrecisionLabel"); // NOI18N
        atribPrecisionLabel.addFocusListener(formListener);
        atribPrecisionLabel.addMouseListener(formListener);

        atribSensibilidad.setEditable(false);
        atribSensibilidad.setFont(fuente);
        atribSensibilidad.setToolTipText(lang.getFrase(356));
        atribSensibilidad.setName("atribSensibilidad"); // NOI18N
        atribSensibilidad.addFocusListener(formListener);
        atribSensibilidad.addMouseListener(formListener);

        atribSensibilidadLabel.setFont(fuente2);
        atribSensibilidadLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        atribSensibilidadLabel.setText(lang.getFrase(350)+" (SE)");
        atribSensibilidadLabel.setToolTipText(lang.getFrase(356));
        atribSensibilidadLabel.setName("atribSensibilidadLabel"); // NOI18N
        atribSensibilidadLabel.addFocusListener(formListener);
        atribSensibilidadLabel.addMouseListener(formListener);

        atribAsimilacionLabel.setFont(fuente2);
        atribAsimilacionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        atribAsimilacionLabel.setText(lang.getFrase(351)+" (AS)");
        atribAsimilacionLabel.setToolTipText(lang.getFrase(357));
        atribAsimilacionLabel.setName("atribAsimilacionLabel"); // NOI18N
        atribAsimilacionLabel.addFocusListener(formListener);
        atribAsimilacionLabel.addMouseListener(formListener);

        atribAsimilacion.setEditable(false);
        atribAsimilacion.setFont(fuente);
        atribAsimilacion.setToolTipText(lang.getFrase(357));
        atribAsimilacion.setName("atribAsimilacion"); // NOI18N
        atribAsimilacion.addFocusListener(formListener);
        atribAsimilacion.addMouseListener(formListener);

        atribEspecializacion.setEditable(false);
        atribEspecializacion.setFont(fuente);
        atribEspecializacion.setText(resourceMap.getString("atribEspecializacion.text")); // NOI18N
        atribEspecializacion.setToolTipText(lang.getFrase(358));
        atribEspecializacion.setName("atribEspecializacion"); // NOI18N
        atribEspecializacion.addFocusListener(formListener);
        atribEspecializacion.addMouseListener(formListener);

        atribsLabel.setFont(fuente);
        atribsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        atribsLabel.setText(lang.getFrase(333));
        atribsLabel.setName("atribsLabel"); // NOI18N
        atribsLabel.addFocusListener(formListener);
        atribsLabel.addMouseListener(formListener);

        atribEspecializacionLabel.setFont(fuente2);
        atribEspecializacionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        atribEspecializacionLabel.setText(lang.getFrase(352)+" (SC)");
        atribEspecializacionLabel.setToolTipText(lang.getFrase(358));
        atribEspecializacionLabel.setName("atribEspecializacionLabel"); // NOI18N
        atribEspecializacionLabel.addFocusListener(formListener);
        atribEspecializacionLabel.addMouseListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(atribsLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, atribEspecializacionLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, atribPrecisionLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, atribSensibilidadLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(atribAsimilacionLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(atribVelocidadLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .add(atribDetalleLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(atribEspecializacion)
                    .add(atribPrecision)
                    .add(atribSensibilidad)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, atribAsimilacion)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, atribDetalle)
                    .add(atribVelocidad, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(atribsLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(atribVelocidadLabel)
                    .add(atribVelocidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(atribDetalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(atribDetalleLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(atribSensibilidadLabel)
                    .add(atribSensibilidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(atribPrecisionLabel)
                    .add(atribPrecision, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(atribEspecializacionLabel)
                    .add(atribEspecializacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(atribAsimilacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(atribAsimilacionLabel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon")); // NOI18N
        imageLabel.setName("imageLabel"); // NOI18N

        perfilActual.setEditable(false);
        perfilActual.setFont(fuente);
        perfilActual.setText(resourceMap.getString("perfilActual.text")); // NOI18N
        perfilActual.setName("perfilActual"); // NOI18N

        jLabel9.setFont(fuente);
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText(lang.getFrase(340));
        jLabel9.setName("jLabel9"); // NOI18N

        iconoModificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jsimilprofile/resources/noeditado.png"))); // NOI18N
        iconoModificado.setName("iconoModificado"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        ayudaTextArea.setEditable(false);
        ayudaTextArea.setFont(fuente);
        ayudaTextArea.setText("");
        ayudaTextArea.setName("ayudaTextArea"); // NOI18N
        jScrollPane2.setViewportView(ayudaTextArea);

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPanelLayout.createSequentialGroup()
                        .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(perfilActual, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(iconoModificado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(6, 6, 6)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, imageLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel9)
                            .add(iconoModificado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(perfilActual, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 345, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(imageLabel)))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.jsimil.profileeditor.JSimilProfile.class).getContext().getActionMap(JSimilProfileView.class, this);
        fileMenu.setAction(actionMap.get("saveProfile")); // NOI18N
        fileMenu.setMnemonic('F');
        fileMenu.setText(lang.getFrase(300));
        fileMenu.setToolTipText(null);
        fileMenu.setFont(fuente);
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem1.setAction(actionMap.get("nuevoPerfil")); // NOI18N
        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setFont(fuente);
        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(lang.getFrase(324));
        jMenuItem1.setToolTipText(lang.getFrase(325));
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        fileMenu.add(jMenuItem1);

        jSeparator3.setName("jSeparator3"); // NOI18N
        fileMenu.add(jSeparator3);

        jMenuItem2.setAction(actionMap.get("defectoPerfil")); // NOI18N
        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setFont(fuente);
        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setText(lang.getFrase(326));
        jMenuItem2.setToolTipText(lang.getFrase(327));
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        fileMenu.add(jMenuItem2);

        jMenuItem3.setAction(actionMap.get("defectoReflexivoPerfil")); // NOI18N
        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setFont(fuente);
        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setText(lang.getFrase(328));
        jMenuItem3.setToolTipText(lang.getFrase(329));
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        fileMenu.add(jMenuItem3);

        jSeparator4.setName("jSeparator4"); // NOI18N
        fileMenu.add(jSeparator4);

        loadProfileMenuItem.setAction(actionMap.get("loadProfile")); // NOI18N
        loadProfileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        loadProfileMenuItem.setFont(fuente);
        loadProfileMenuItem.setIcon(resourceMap.getIcon("loadProfileMenuItem.icon")); // NOI18N
        loadProfileMenuItem.setText(lang.getFrase(305));
        loadProfileMenuItem.setToolTipText(lang.getFrase(306));
        loadProfileMenuItem.setName("loadProfileMenuItem"); // NOI18N
        loadProfileMenuItem.setOpaque(false);
        fileMenu.add(loadProfileMenuItem);

        jMenuItem4.setAction(actionMap.get("saveProfileDirecto")); // NOI18N
        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setFont(fuente);
        jMenuItem4.setIcon(resourceMap.getIcon("jMenuItem4.icon")); // NOI18N
        jMenuItem4.setText(lang.getFrase(334));
        jMenuItem4.setToolTipText(lang.getFrase(335));
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        fileMenu.add(jMenuItem4);

        saveProfileMenuItem.setAction(actionMap.get("saveProfile")); // NOI18N
        saveProfileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        saveProfileMenuItem.setFont(fuente);
        saveProfileMenuItem.setIcon(resourceMap.getIcon("saveProfileMenuItem.icon")); // NOI18N
        saveProfileMenuItem.setText(lang.getFrase(307));
        saveProfileMenuItem.setToolTipText(lang.getFrase(308));
        saveProfileMenuItem.setName("saveProfileMenuItem"); // NOI18N
        saveProfileMenuItem.setOpaque(false);
        fileMenu.add(saveProfileMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        fileMenu.add(jSeparator2);

        exportImageMenuItem.setAction(actionMap.get("exportarHuella")); // NOI18N
        exportImageMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        exportImageMenuItem.setFont(fuente);
        exportImageMenuItem.setIcon(resourceMap.getIcon("exportImageMenuItem.icon")); // NOI18N
        exportImageMenuItem.setText(lang.getFrase(309));
        exportImageMenuItem.setToolTipText(lang.getFrase(310));
        exportImageMenuItem.setName("exportImageMenuItem"); // NOI18N
        fileMenu.add(exportImageMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        exitMenuItem.setAction(actionMap.get("quitPropio")); // NOI18N
        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setFont(fuente);
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setMnemonic('\0');
        exitMenuItem.setText(lang.getFrase(301));
        exitMenuItem.setToolTipText(lang.getFrase(302));
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(lang.getFrase(299));
        helpMenu.setFont(fuente);
        helpMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setFont(fuente);
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setMnemonic('\0');
        aboutMenuItem.setText(lang.getFrase(298));
        aboutMenuItem.setToolTipText(lang.getFrase(303));
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jPanel2.setName("jPanel2"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.FocusListener, java.awt.event.KeyListener, java.awt.event.MouseListener, javax.swing.event.ChangeListener {
        FormListener() {}
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == huellaPanel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == huellaLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butReflexiveNo) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butReflexiveSi) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceSearchNo) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceSearchSi) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butReturnNull) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorLabel1) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMinLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMinBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMinText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorLabel2) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismClassLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismClassBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismClassText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismMethodLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismProgLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismProgBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimismProgText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMinLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMinBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMinText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butOptimism) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassThreshold) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassSameNameMatch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butClassAllowMultiMatch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMethodSameNameMatch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMethodOfClassSameNameMatch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMethodAllowMultiMatch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockAllowMultiMatch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel1) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionClass) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel2) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionMethod) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel3) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionBlock) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifference) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethod) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitLabel2) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitClassBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitClassText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceBlockLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceBlockBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butDifferenceBlockText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitLabel3) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == jPanel25) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitLabel1) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitProgramBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimitProgramText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butLimit) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethod) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableBlock) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableBlockBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableBlockText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethod) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableClass) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethod) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumBlock) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumBlockBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumBlockText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumClassMethod) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumClassMethodBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumClassMethodText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumClass) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumClassBar) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimumClassText) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butAcceptable) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butMinimum) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel0) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel8) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel7) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel6) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel5) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel4) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel3) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel9) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel10) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeStatic) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeVirtual) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeSpecial) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorEnter) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorExit) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightTableSwitch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeInterface) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLookupSwitch) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel2) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel1) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInstruction) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodEnd) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodStart) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel19) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel18) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel17) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel16) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel15) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel20) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel21) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel14) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNewArray) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightReturn) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNew) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightConst) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightThrow) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftLeft) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPutField) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftRight) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel12) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetStatic) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel13) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetField) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel11) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightCheckCast) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel30) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel29) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel28) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel27) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel26) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel31) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel32) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel25) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGoto) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightJSR) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightIf) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPush) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPop) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSwap) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLDC) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDup) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightStore) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLoad) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInc) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel23) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel24) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel22) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel41) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel40) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel39) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel38) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel37) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel36) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSub) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNeg) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAnd) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightOr) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRet) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAdd) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel34) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRem) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel35) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMul) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel33) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDiv) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel42) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == butBlockWeightXor) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribVelocidadLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribVelocidad) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribDetalleLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribDetalle) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribPrecision) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribPrecisionLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribSensibilidad) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribSensibilidadLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribAsimilacionLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribAsimilacion) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribEspecializacion) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribsLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
            else if (evt.getSource() == atribEspecializacionLabel) {
                JSimilProfileView.this.focusManager(evt);
            }
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == butProgMatchMinMaxMinText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butOptimismClassText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butOptimismMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butOptimismProgText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMinText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionClass) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionMethod) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionBlock) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butLimitClassText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butDifferenceBlockText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butLimitMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butLimitProgramText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butAcceptableBlockText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumBlockText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumClassMethodText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butMinimumClassText) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeStatic) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeVirtual) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeSpecial) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorEnter) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorExit) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightTableSwitch) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeInterface) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLookupSwitch) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInstruction) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodEnd) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodStart) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNewArray) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightReturn) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNew) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightConst) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightThrow) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftLeft) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPutField) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftRight) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetStatic) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetField) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightCheckCast) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGoto) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightJSR) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightIf) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPush) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPop) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSwap) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLDC) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDup) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightStore) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLoad) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInc) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSub) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNeg) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAnd) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightOr) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRet) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAdd) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRem) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMul) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDiv) {
                JSimilProfileView.this.focusLostManager(evt);
            }
            else if (evt.getSource() == butBlockWeightXor) {
                JSimilProfileView.this.focusLostManager(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == butOptimismClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butOptimismMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butOptimismProgText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMinText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == butProgMatchMinMaxMinText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butOptimismClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butOptimismMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butOptimismProgText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butClassThresholdMinText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionClass) {
                JSimilProfileView.this.keyTypedManager2(evt);
            }
            else if (evt.getSource() == butMinimumInstructionMethod) {
                JSimilProfileView.this.keyTypedManager2(evt);
            }
            else if (evt.getSource() == butMinimumInstructionBlock) {
                JSimilProfileView.this.keyTypedManager2(evt);
            }
            else if (evt.getSource() == butDifferenceProgramText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butLimitClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceBlockText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butLimitMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butLimitProgramText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableBlockText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumBlockText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumClassMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeStatic) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeVirtual) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeSpecial) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorEnter) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorExit) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightTableSwitch) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeInterface) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLookupSwitch) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInstruction) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodEnd) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodStart) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNewArray) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightReturn) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNew) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightConst) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightThrow) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftLeft) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPutField) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftRight) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetStatic) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetField) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightCheckCast) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGoto) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightJSR) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightIf) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPush) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPop) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSwap) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLDC) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDup) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightStore) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLoad) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInc) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSub) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNeg) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAnd) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightOr) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRet) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAdd) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRem) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMul) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDiv) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightXor) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == butProgMatchMinMaxMinText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butProgMatchErrorText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumInstructionClass) {
                JSimilProfileView.this.keyTypedManager2(evt);
            }
            else if (evt.getSource() == butMinimumInstructionMethod) {
                JSimilProfileView.this.keyTypedManager2(evt);
            }
            else if (evt.getSource() == butMinimumInstructionBlock) {
                JSimilProfileView.this.keyTypedManager2(evt);
            }
            else if (evt.getSource() == butDifferenceProgramText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butLimitClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butDifferenceBlockText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butLimitMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butLimitProgramText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableBlockText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butAcceptableClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumBlockText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumClassMethodText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butMinimumClassText) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeStatic) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeVirtual) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeSpecial) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorEnter) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMonitorExit) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightTableSwitch) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInvokeInterface) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLookupSwitch) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInstruction) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodEnd) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMethodStart) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNewArray) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightReturn) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNew) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightConst) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightThrow) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftLeft) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPutField) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightShiftRight) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetStatic) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGetField) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightCheckCast) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightGoto) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightJSR) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightIf) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPush) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightPop) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSwap) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLDC) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDup) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightStore) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightLoad) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightInc) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightSub) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightNeg) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAnd) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightOr) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRet) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightAdd) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightRem) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightMul) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightDiv) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
            else if (evt.getSource() == butBlockWeightXor) {
                JSimilProfileView.this.keyTypedManager(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == huellaPanel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == huellaLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butProgMatchErrorLabel1) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMinLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butProgMatchErrorLabel2) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butOptimismClassLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butOptimismMethodLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butOptimismProgLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butClassThresholdMinLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butOptimism) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butClassThreshold) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel1) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel2) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumInstructionLabel3) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butDifferenceProgramLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butDifference) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butDifferenceClassLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethod) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butLimitLabel2) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butDifferenceBlockLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butLimitLabel3) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butLimitLabel1) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butLimit) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethod) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butAcceptableBlock) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethod) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butAcceptableClass) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethod) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumBlock) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumClassMethod) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimumClass) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butAcceptable) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butMinimum) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel0) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel8) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel7) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel6) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel5) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel4) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel3) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel9) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel10) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel2) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel1) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel19) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel18) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel17) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel16) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel15) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel20) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel21) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel14) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel12) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel13) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel11) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel30) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel29) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel28) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel27) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel26) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel31) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel32) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel25) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel23) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel24) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel22) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel41) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel40) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel39) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel38) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel37) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel36) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel34) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel35) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel33) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightLabel42) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == butBlockWeightXor) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribVelocidadLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribVelocidad) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribDetalleLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribDetalle) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribPrecision) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribPrecisionLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribSensibilidad) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribSensibilidadLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribAsimilacionLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribAsimilacion) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribEspecializacion) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribsLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
            else if (evt.getSource() == atribEspecializacionLabel) {
                JSimilProfileView.this.mouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (evt.getSource() == butReflexiveNo) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butReflexiveSi) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butDifferenceSearchNo) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butDifferenceSearchSi) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butReturnNull) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMinBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butProgMatchMinMaxMaxBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butProgMatchErrorBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butOptimismClassBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butOptimismMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butOptimismProgBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butClassThresholdMaxBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butClassThresholdMinBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butClassSameNameMatch) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butClassAllowMultiMatch) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butMethodSameNameMatch) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butMethodOfClassSameNameMatch) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butMethodAllowMultiMatch) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butBlockAllowMultiMatch) {
                JSimilProfileView.this.changeManager(evt);
            }
            else if (evt.getSource() == butDifferenceProgramBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butDifferenceClassBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butDifferenceProgramMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butDifferenceClassMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butLimitClassBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butDifferenceBlockBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butLimitMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butLimitProgramBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butAcceptableProgramMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butAcceptableBlockBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butAcceptableClassMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butAcceptableClassBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butMinimumProgramMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butMinimumBlockBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butMinimumClassMethodBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
            else if (evt.getSource() == butMinimumClassBar) {
                JSimilProfileView.this.changeManagerBar(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gestor de evento de enfoque.
     * @param evt Evento.
     * @.post Evento gestionado.
     */
    private void focusManager(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusManager
        JTextField a;
        if (evt.getComponent().getClass() == JTextField.class) {
            a = (JTextField)evt.getComponent();
            a.setCaretPosition(0);
            a.moveCaretPosition(a.getText().length());
        }
        
        manager(evt.getComponent().getName());
}//GEN-LAST:event_focusManager

    /**
     * Gestor de evento de cambio.
     * @param evt Evento.
     * @.post Evento gestionado.
     */    
    private void changeManager(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeManager
        managerCambio();
    }//GEN-LAST:event_changeManager

    /**
     * Gestor de evento de enfoque perdido.
     * @param evt Evento.
     * @.post Evento gestionado.
     */
    private void focusLostManager(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusLostManager
        quitaLetras((JTextField)evt.getSource());
        managerCambio();
    }//GEN-LAST:event_focusLostManager

    /**
     * Gestor de evento de pulsación de tecla.
     * @param evt Evento.
     * @.post Evento gestionado.
     */    
    private void keyTypedManager(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTypedManager
        quitaLetras((JTextField)evt.getSource());
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
            managerCambio();
    }//GEN-LAST:event_keyTypedManager

    /**
     * Gestor de evento de cambio en barra.
     * @param evt Evento.
     * @.post Evento gestionado.
     */    
    private void changeManagerBar(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeManagerBar
        managerCambioBarra(((JSlider)evt.getSource()).getName());
    }//GEN-LAST:event_changeManagerBar

    /**
     * Gestor de evento de click de ratón.
     * @param evt Evento.
     * @.post Evento gestionado.
     */        
    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
        manager(evt.getComponent().getName());
    }//GEN-LAST:event_mouseClicked

    /**
     * Gestor de evento de pulsación de tecla.
     * @param evt Evento.
     * @.post Evento gestionado.
     */    
    private void keyTypedManager2(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTypedManager2
        quitaLetras2((JTextField)evt.getSource());
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
            managerCambio();
    }//GEN-LAST:event_keyTypedManager2

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField atribAsimilacion;
    private javax.swing.JLabel atribAsimilacionLabel;
    private javax.swing.JTextField atribDetalle;
    private javax.swing.JLabel atribDetalleLabel;
    private javax.swing.JTextField atribEspecializacion;
    private javax.swing.JLabel atribEspecializacionLabel;
    private javax.swing.JTextField atribPrecision;
    private javax.swing.JLabel atribPrecisionLabel;
    private javax.swing.JTextField atribSensibilidad;
    private javax.swing.JLabel atribSensibilidadLabel;
    private javax.swing.JTextField atribVelocidad;
    private javax.swing.JLabel atribVelocidadLabel;
    private javax.swing.JLabel atribsLabel;
    private javax.swing.JTextPane ayudaTextArea;
    private javax.swing.JLabel butAcceptable;
    private javax.swing.JLabel butAcceptableBlock;
    private javax.swing.JSlider butAcceptableBlockBar;
    private javax.swing.JTextField butAcceptableBlockText;
    private javax.swing.JLabel butAcceptableClass;
    private javax.swing.JSlider butAcceptableClassBar;
    private javax.swing.JLabel butAcceptableClassMethod;
    private javax.swing.JSlider butAcceptableClassMethodBar;
    private javax.swing.JTextField butAcceptableClassMethodText;
    private javax.swing.JTextField butAcceptableClassText;
    private javax.swing.JLabel butAcceptableProgramMethod;
    private javax.swing.JSlider butAcceptableProgramMethodBar;
    private javax.swing.JTextField butAcceptableProgramMethodText;
    private javax.swing.JCheckBox butBlockAllowMultiMatch;
    private javax.swing.JTextField butBlockWeightAdd;
    private javax.swing.JTextField butBlockWeightAnd;
    private javax.swing.JTextField butBlockWeightCheckCast;
    private javax.swing.JTextField butBlockWeightConst;
    private javax.swing.JTextField butBlockWeightDiv;
    private javax.swing.JTextField butBlockWeightDup;
    private javax.swing.JTextField butBlockWeightGetField;
    private javax.swing.JTextField butBlockWeightGetStatic;
    private javax.swing.JTextField butBlockWeightGoto;
    private javax.swing.JTextField butBlockWeightIf;
    private javax.swing.JTextField butBlockWeightInc;
    private javax.swing.JTextField butBlockWeightInstruction;
    private javax.swing.JTextField butBlockWeightInvokeInterface;
    private javax.swing.JTextField butBlockWeightInvokeSpecial;
    private javax.swing.JTextField butBlockWeightInvokeStatic;
    private javax.swing.JTextField butBlockWeightInvokeVirtual;
    private javax.swing.JTextField butBlockWeightJSR;
    private javax.swing.JTextField butBlockWeightLDC;
    private javax.swing.JLabel butBlockWeightLabel;
    private javax.swing.JLabel butBlockWeightLabel0;
    private javax.swing.JLabel butBlockWeightLabel1;
    private javax.swing.JLabel butBlockWeightLabel10;
    private javax.swing.JLabel butBlockWeightLabel11;
    private javax.swing.JLabel butBlockWeightLabel12;
    private javax.swing.JLabel butBlockWeightLabel13;
    private javax.swing.JLabel butBlockWeightLabel14;
    private javax.swing.JLabel butBlockWeightLabel15;
    private javax.swing.JLabel butBlockWeightLabel16;
    private javax.swing.JLabel butBlockWeightLabel17;
    private javax.swing.JLabel butBlockWeightLabel18;
    private javax.swing.JLabel butBlockWeightLabel19;
    private javax.swing.JLabel butBlockWeightLabel2;
    private javax.swing.JLabel butBlockWeightLabel20;
    private javax.swing.JLabel butBlockWeightLabel21;
    private javax.swing.JLabel butBlockWeightLabel22;
    private javax.swing.JLabel butBlockWeightLabel23;
    private javax.swing.JLabel butBlockWeightLabel24;
    private javax.swing.JLabel butBlockWeightLabel25;
    private javax.swing.JLabel butBlockWeightLabel26;
    private javax.swing.JLabel butBlockWeightLabel27;
    private javax.swing.JLabel butBlockWeightLabel28;
    private javax.swing.JLabel butBlockWeightLabel29;
    private javax.swing.JLabel butBlockWeightLabel3;
    private javax.swing.JLabel butBlockWeightLabel30;
    private javax.swing.JLabel butBlockWeightLabel31;
    private javax.swing.JLabel butBlockWeightLabel32;
    private javax.swing.JLabel butBlockWeightLabel33;
    private javax.swing.JLabel butBlockWeightLabel34;
    private javax.swing.JLabel butBlockWeightLabel35;
    private javax.swing.JLabel butBlockWeightLabel36;
    private javax.swing.JLabel butBlockWeightLabel37;
    private javax.swing.JLabel butBlockWeightLabel38;
    private javax.swing.JLabel butBlockWeightLabel39;
    private javax.swing.JLabel butBlockWeightLabel4;
    private javax.swing.JLabel butBlockWeightLabel40;
    private javax.swing.JLabel butBlockWeightLabel41;
    private javax.swing.JLabel butBlockWeightLabel42;
    private javax.swing.JLabel butBlockWeightLabel5;
    private javax.swing.JLabel butBlockWeightLabel6;
    private javax.swing.JLabel butBlockWeightLabel7;
    private javax.swing.JLabel butBlockWeightLabel8;
    private javax.swing.JLabel butBlockWeightLabel9;
    private javax.swing.JTextField butBlockWeightLoad;
    private javax.swing.JTextField butBlockWeightLookupSwitch;
    private javax.swing.JTextField butBlockWeightMethodEnd;
    private javax.swing.JTextField butBlockWeightMethodStart;
    private javax.swing.JTextField butBlockWeightMonitorEnter;
    private javax.swing.JTextField butBlockWeightMonitorExit;
    private javax.swing.JTextField butBlockWeightMul;
    private javax.swing.JTextField butBlockWeightNeg;
    private javax.swing.JTextField butBlockWeightNew;
    private javax.swing.JTextField butBlockWeightNewArray;
    private javax.swing.JTextField butBlockWeightOr;
    private javax.swing.JTextField butBlockWeightPop;
    private javax.swing.JTextField butBlockWeightPush;
    private javax.swing.JTextField butBlockWeightPutField;
    private javax.swing.JTextField butBlockWeightRem;
    private javax.swing.JTextField butBlockWeightRet;
    private javax.swing.JTextField butBlockWeightReturn;
    private javax.swing.JTextField butBlockWeightShiftLeft;
    private javax.swing.JTextField butBlockWeightShiftRight;
    private javax.swing.JTextField butBlockWeightStore;
    private javax.swing.JTextField butBlockWeightSub;
    private javax.swing.JTextField butBlockWeightSwap;
    private javax.swing.JTextField butBlockWeightTableSwitch;
    private javax.swing.JTextField butBlockWeightThrow;
    private javax.swing.JTextField butBlockWeightXor;
    private javax.swing.JCheckBox butClassAllowMultiMatch;
    private javax.swing.JCheckBox butClassSameNameMatch;
    private javax.swing.JLabel butClassThreshold;
    private javax.swing.JSlider butClassThresholdMaxBar;
    private javax.swing.JLabel butClassThresholdMaxLabel;
    private javax.swing.JTextField butClassThresholdMaxText;
    private javax.swing.JSlider butClassThresholdMinBar;
    private javax.swing.JLabel butClassThresholdMinLabel;
    private javax.swing.JTextField butClassThresholdMinText;
    private javax.swing.JLabel butDifference;
    private javax.swing.JSlider butDifferenceBlockBar;
    private javax.swing.JLabel butDifferenceBlockLabel;
    private javax.swing.JTextField butDifferenceBlockText;
    private javax.swing.JSlider butDifferenceClassBar;
    private javax.swing.JLabel butDifferenceClassLabel;
    private javax.swing.JSlider butDifferenceClassMethodBar;
    private javax.swing.JLabel butDifferenceClassMethodLabel;
    private javax.swing.JTextField butDifferenceClassMethodText;
    private javax.swing.JTextField butDifferenceClassText;
    private javax.swing.JSlider butDifferenceProgramBar;
    private javax.swing.JLabel butDifferenceProgramLabel;
    private javax.swing.JLabel butDifferenceProgramMethod;
    private javax.swing.JSlider butDifferenceProgramMethodBar;
    private javax.swing.JTextField butDifferenceProgramMethodText;
    private javax.swing.JTextField butDifferenceProgramText;
    private javax.swing.JRadioButton butDifferenceSearchNo;
    private javax.swing.JRadioButton butDifferenceSearchSi;
    private javax.swing.ButtonGroup butGDifference;
    private javax.swing.ButtonGroup butGReflexive;
    private javax.swing.JLabel butLimit;
    private javax.swing.JSlider butLimitClassBar;
    private javax.swing.JTextField butLimitClassText;
    private javax.swing.JLabel butLimitLabel1;
    private javax.swing.JLabel butLimitLabel2;
    private javax.swing.JLabel butLimitLabel3;
    private javax.swing.JSlider butLimitMethodBar;
    private javax.swing.JTextField butLimitMethodText;
    private javax.swing.JSlider butLimitProgramBar;
    private javax.swing.JTextField butLimitProgramText;
    private javax.swing.JCheckBox butMethodAllowMultiMatch;
    private javax.swing.JCheckBox butMethodOfClassSameNameMatch;
    private javax.swing.JCheckBox butMethodSameNameMatch;
    private javax.swing.JLabel butMinimum;
    private javax.swing.JLabel butMinimumBlock;
    private javax.swing.JSlider butMinimumBlockBar;
    private javax.swing.JTextField butMinimumBlockText;
    private javax.swing.JLabel butMinimumClass;
    private javax.swing.JSlider butMinimumClassBar;
    private javax.swing.JLabel butMinimumClassMethod;
    private javax.swing.JSlider butMinimumClassMethodBar;
    private javax.swing.JTextField butMinimumClassMethodText;
    private javax.swing.JTextField butMinimumClassText;
    private javax.swing.JTextField butMinimumInstructionBlock;
    private javax.swing.JTextField butMinimumInstructionClass;
    private javax.swing.JLabel butMinimumInstructionLabel;
    private javax.swing.JLabel butMinimumInstructionLabel1;
    private javax.swing.JLabel butMinimumInstructionLabel2;
    private javax.swing.JLabel butMinimumInstructionLabel3;
    private javax.swing.JTextField butMinimumInstructionMethod;
    private javax.swing.JLabel butMinimumProgramMethod;
    private javax.swing.JSlider butMinimumProgramMethodBar;
    private javax.swing.JTextField butMinimumProgramMethodText;
    private javax.swing.JLabel butOptimism;
    private javax.swing.JSlider butOptimismClassBar;
    private javax.swing.JLabel butOptimismClassLabel;
    private javax.swing.JTextField butOptimismClassText;
    private javax.swing.JSlider butOptimismMethodBar;
    private javax.swing.JLabel butOptimismMethodLabel;
    private javax.swing.JTextField butOptimismMethodText;
    private javax.swing.JSlider butOptimismProgBar;
    private javax.swing.JLabel butOptimismProgLabel;
    private javax.swing.JTextField butOptimismProgText;
    private javax.swing.JSlider butProgMatchErrorBar;
    private javax.swing.JLabel butProgMatchErrorLabel1;
    private javax.swing.JLabel butProgMatchErrorLabel2;
    private javax.swing.JTextField butProgMatchErrorText;
    private javax.swing.JLabel butProgMatchMinMaxLabel;
    private javax.swing.JSlider butProgMatchMinMaxMaxBar;
    private javax.swing.JLabel butProgMatchMinMaxMaxLabel;
    private javax.swing.JTextField butProgMatchMinMaxMaxText;
    private javax.swing.JSlider butProgMatchMinMaxMinBar;
    private javax.swing.JLabel butProgMatchMinMaxMinLabel;
    private javax.swing.JTextField butProgMatchMinMaxMinText;
    private javax.swing.JRadioButton butReflexiveNo;
    private javax.swing.JRadioButton butReflexiveSi;
    private javax.swing.JCheckBox butReturnNull;
    private javax.swing.JMenuItem exportImageMenuItem;
    private javax.swing.JLabel huellaLabel;
    private javax.swing.JPanel huellaPanel;
    private javax.swing.JLabel iconoModificado;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem loadProfileMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField perfilActual;
    private javax.swing.JMenuItem saveProfileMenuItem;
    // End of variables declaration//GEN-END:variables

    /**
     * Ventana acerca de.
     */
    private JDialog aboutBox;
    

    /**
     * Comprobador de campo.
     * @param campo Campo a comprobar.
     * @.post Se han eliminado todos los caracteres salvo puntos y dígitos.
     */
    void quitaLetras(JTextField campo) {
        String text = campo.getText();
        int i;
        boolean puntoencontrado = false;
        int aux;
        for (i = 0;i < text.length();i++)
            if (!Character.isDigit(text.charAt(i)) && (text.charAt(i) != '.')) {
                text = text.substring(0,i)+text.substring(i+1,text.length());
                aux = campo.getCaretPosition()-1;
                if (aux < 0)
                    aux = 0;
                campo.setText(text);
                campo.setCaretPosition(aux);
                i--;
            }
        for (i = 0;i < text.length();i++)
            if ((text.charAt(i) == '.')) {
                if (!puntoencontrado)
                    puntoencontrado = true;
                else {
                    text = text.substring(0,i)+text.substring(i+1,text.length());
                    aux = campo.getCaretPosition()-1;
                    if (aux < 0)
                        aux = 0;
                    campo.setText(text);
                    campo.setCaretPosition(aux);
                    i--;
                }
            }
        campo.repaint();
    }
    
    /**
     * Comprobador de campo.
     * @param campo Campo a comprobar.
     * @.post Se han eliminado todos los caracteres salvo dígitos.
     */
    void quitaLetras2(JTextField campo) {
        String text = campo.getText();
        int i;
        int aux;
        for (i = 0;i < text.length();i++)
            if (!Character.isDigit(text.charAt(i))) {
                text = text.substring(0,i)+text.substring(i+1,text.length());
                aux = campo.getCaretPosition()-1;
                if (aux < 0)
                    aux = 0;
                campo.setText(text);
                campo.setCaretPosition(aux);
                i--;
            }
        campo.repaint();
    }
    
    /**
     * Comprobar si hay modificaciones realizadas y se quieren ignorar.
     * @return true si se puede realizar la acción, false si no.
     */
    boolean comprobarModificado() {
        if (!modificado)
            return true;
        else {
            int opt = JOptionPane.showConfirmDialog(this.getFrame(),
                    lang.getFrase(331),lang.getFrase(330),
                    JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION)            
                return true;
            else
                return false;
        }
    }
    
    /**
     * Cargar perfil con diálogo.
     * @.post Carga tratada.
     */
    @Action
    public void loadProfile() {
        managerCambio();
        if (!comprobarModificado())
            return;
        JFileChooser fc = new JFileChooser(dir);
        ExtensionFileFilter ef =
                new ExtensionFileFilter(lang.getFrase(315),".jpf");
        fc.addChoosableFileFilter(ef);
        fc.setDialogTitle(lang.getFrase(312));
        fc.setApproveButtonText(lang.getFrase(313));
        fc.setApproveButtonToolTipText(lang.getFrase(314));
        fc.showOpenDialog(this.getFrame());
        if (fc.getSelectedFile() != null) {
            File diraux = fc.getSelectedFile().getParentFile();
            if (diraux != null)
                dir = diraux;
            String ruta = fc.getSelectedFile().getAbsolutePath();
            try {
                perfil.load(ruta);
                ajustarPerfil();
                perfilAVentana();
                dibujarPerfil();
                modificado = false;
                hashcode = perfil.hashCode();
                this.actual = ruta;
                actualizaActual();
                JOptionPane.showMessageDialog(this.getFrame(),lang.getFrase(316,
                    ruta),lang.getFrase(317),JOptionPane.INFORMATION_MESSAGE);
            } catch (JSimilException e) {
                if (e.getTipo()==ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                    JOptionPane.showMessageDialog(this.getFrame(),
                            lang.getFrase(75,ruta),
                            lang.getFrase(311),JOptionPane.ERROR_MESSAGE);
                else if (e.getTipo()==ExceptionType.FORMATO_INCORRECTO)
                    JOptionPane.showMessageDialog(this.getFrame(),
                            lang.getFrase(76,ruta),
                            lang.getFrase(311),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     
     /**
      * Umbralizar.
      * @param num Número a umbralizar.
      * @return Número umbralizado entre 0 y 1.
      */
     double regula(double num) {
         if (num < 0)
             return 0;
         else if (num > 1)
             return 1;
         return num;
     }

   /**
    * Guardar perfil con diálogo.
    * @.post Guardado tratado.
    */
    @Action
    public void saveProfile() {
        managerCambio();        
        JFileChooser fc = new JFileChooser(dir);
        ExtensionFileFilter ef =
                new ExtensionFileFilter(lang.getFrase(315),".jpf");
        fc.addChoosableFileFilter(ef);
        fc.setDialogTitle(lang.getFrase(318));
        fc.setApproveButtonText(lang.getFrase(319));
        fc.setApproveButtonToolTipText(lang.getFrase(320));
        fc.showSaveDialog(this.getFrame());
        if (fc.getSelectedFile() != null) {
            File diraux = fc.getSelectedFile().getParentFile();
            if (diraux != null)
                dir = diraux;
            String ruta = fc.getSelectedFile().getAbsolutePath();
            if (!ruta.toLowerCase().endsWith(".jpf") &&
                    fc.getFileFilter() == ef)
                ruta = ruta+".jpf";
            actual = ruta;
            if (fc.getSelectedFile().exists()) {
                int opt = JOptionPane.showConfirmDialog(this.getFrame(),
                        lang.getFrase(345),lang.getFrase(344),
                        JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    actual = ruta;
                    actualizaActual();
                    saveProfileDirecto();
                }
            }
            else
                saveProfileDirecto();
        }
    }

    /**
     * Nuevo perfil.
     * @.post Nuevo perfil iniciado.
     */
    @Action
    public void nuevoPerfil() {
        managerCambio();        
        if (!comprobarModificado())
            return;
        perfil = new MatchingProfile();
        modificado = false;
        hashcode = perfil.hashCode();
        this.actual = null;
        actualizaActual();
        ajustarPerfil();
        perfilAVentana();
        dibujarPerfil();
    }

    /**
     * Perfil por defecto.
     * @.post Perfil por defecto iniciado.
     */
    @Action
    public void defectoPerfil() {
        managerCambio();
        if (!comprobarModificado())
            return;
        perfil = DefaultJSimilProfileFactory.defecto();
        modificado = false;
        hashcode = perfil.hashCode();
        this.actual = null;
        actualizaActual();
        ajustarPerfil();
        
        perfilAVentana();
        dibujarPerfil();
    }

    /**
     * Perfil reflexivo por defecto.
     * @.post Perfil reflexivo por defecto iniciado.
     */
    @Action
    public void defectoReflexivoPerfil() {
        managerCambio();        
        if (!comprobarModificado())
            return;
        perfil = DefaultJSimilProfileFactory.defectoReflexivo();
        modificado = false;
        hashcode = perfil.hashCode();
        this.actual = null;
        actualizaActual();
        ajustarPerfil();
        perfilAVentana();
        dibujarPerfil();
    }

    /**
     * Salir con diálogo.
     * @.post Salir gestionado.
     */
    @Action
    public void quitPropio() {
      managerCambio();        
      if (!comprobarModificado())
        return;
      System.exit(0);
    }
    
    /**
     * Salir con diálogo 2.
     * @.post Salir gestionado.
     */
    @Action
    public void quitPropioInseguro() {
        if (modificado) {
            int opt = JOptionPane.showConfirmDialog(this.getFrame(),
                    lang.getFrase(336),lang.getFrase(330),
                    JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                saveProfileDirecto();
            }
        }
        System.exit(0);
    }

    /**
     * Actualizar el perfil actual.
     * @.post Información sobre modificación de perfil y ruta actualizadas.
     */
    void actualizaActual() {
        if (actual != null) {
            File f = new File(actual);
            perfilActual.setText(f.getName()+" ("+f.getAbsolutePath()+")");
            perfilActual.setCaretPosition(0);
            perfilActual.setFont(fuente);
        }
        else {
            perfilActual.setText(lang.getFrase(341));
            perfilActual.setFont(fuente3);
        }
        if (modificado) {
            iconoModificado.setIcon(icmodificado);
            iconoModificado.setToolTipText(lang.getFrase(343));
        }
        else {
            iconoModificado.setIcon(icnomodificado);
            iconoModificado.setToolTipText(lang.getFrase(342));
        }
    }
    
    /**
     * Guardar perfil al último fichero abierto. Preguntar si no lo hay.
     * @.post Perfil guardado.
     */
    @Action
    public void saveProfileDirecto() {
        managerCambio();
        if (actual == null)
            saveProfile();
        else {
            try {
                perfil.save(actual);
                modificado = false;
                hashcode = perfil.hashCode();
                actualizaActual();        
                JOptionPane.showMessageDialog(this.getFrame(),lang.getFrase(321,
                    actual),lang.getFrase(322),JOptionPane.INFORMATION_MESSAGE);
            } catch (JSimilException e) {
                if (e.getTipo()==ExceptionType.RUTA_NO_VALIDA_O_INACCESIBLE)
                    JOptionPane.showMessageDialog(this.getFrame(),
                            lang.getFrase(137,actual),
                            lang.getFrase(323),JOptionPane.ERROR_MESSAGE);
                else if (e.getTipo()==ExceptionType.ERROR_ESCRIBIENDO_FICHERO)
                    JOptionPane.showMessageDialog(this.getFrame(),
                            lang.getFrase(138,actual),
                            lang.getFrase(323),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Dibujar perfil.
     * @.post Atributos calculados, perfil dibujado.
     */
     public void dibujarPerfil() {
        double velocidad = 0;
        double detalle = 0;
        double precision = 0;
        double sensibilidad = 0;
        double asimilacion = 0;
        double especializacion = 0;
        double aux;
        double auxdifmaxmin;
        double auxdifctmaxmin;
        double auxprogmatchoptimismdist;
        double auxmethodmatchoptimismdist;
        double auxclassmatchoptimismdist;
        double auxclassconlymin;
        double auxmethodconlymin;
        double auxblockconlymin;
        double valReflexiveMATCH = 0;
        double valDifferenceSEARCH = 0;
        double valReturnNullMatchesIfNULL = 0;
        double valProgMatchMIN = 0;
        double valProgMatchMAX = 0;
        double valProgMatchERROR = 0;
        double valProgMatchLIMIT = 0;
        double valProgMatchOPTIMISM = 0;
        double valProgMatchClassACCEPTABLE = 0;
        double valProgMatchClassDIFFERENCE = 0;
        double valProgMatchClassMatchMINIMUM = 0;
        double valProgMatchClassThresholdMAXIMUM = 0;
        double valProgMatchClassThresholdMINIMUM = 0;
        double valProgMatchDIFFERENCE = 0;
        double valProgMatchMethodACCEPTABLE = 0;
        double valProgMatchMethodDIFFERENCE = 0;
        double valProgMatchMethodMatchMINIMUM = 0;
        double valClassAllowMULTIMATCH = 0;
        double valClassConsiderOnlyMINIMUMINSTRUCTION = 0;
        double valClassMatchACCEPTABLE = 0;
        double valClassMatchDIFFERENCE = 0;
        double valClassMatchLIMIT = 0;
        double valClassMatchMethodMatchMINIMUM = 0;
        double valClassMatchOPTIMISM = 0;
        double valClassSAMENAMEMATCH = 0;
        double valMethodAllowMULTIMATCH = 0;
        double valMethodConsiderOnlyMINIMUMINSTRUCTION = 0;
        double valMethodMatchACCEPTABLE = 0;
        double valMethodMatchBlockMatchMINIMUM = 0;
        double valMethodMatchDIFFERENCE = 0;
        double valMethodMatchLIMIT = 0;
        double valMethodMatchOPTIMISM = 0;
        double valMethodOfClassSAMENAMEMATCH = 0;
        double valMethodSAMENAMEMATCH = 0;
        double valBlockAllowMULTIMATCH = 0;
        double valBlockConsiderOnlyMINIMUMINSTRUCTION = 0;
        double sumweights = 0;
        boolean extrema;

         try {
            valReflexiveMATCH = 
                     perfil.getValor("ReflexiveMATCH");
            valDifferenceSEARCH = 
                     perfil.getValor("DifferenceSEARCH");
            valReturnNullMatchesIfNULL = 
                     perfil.getValor("ReturnNullMatchesIfNULL");
            valProgMatchMIN = 
                     perfil.getValor("ProgMatchMIN");
            valProgMatchMAX = 
                     perfil.getValor("ProgMatchMAX");
            valProgMatchERROR = 
                     perfil.getValor("ProgMatchERROR");
            valProgMatchLIMIT = 
                     perfil.getValor("ProgMatchLIMIT");
            valProgMatchOPTIMISM = 
                     perfil.getValor("ProgMatchOPTIMISM");
            valProgMatchClassACCEPTABLE = 
                     perfil.getValor("ProgMatchClassACCEPTABLE");
            valProgMatchClassDIFFERENCE = 
                     perfil.getValor("ProgMatchClassDIFFERENCE");
            valProgMatchClassMatchMINIMUM = 
                     perfil.getValor("ProgMatchClassMatchMINIMUM");
            valProgMatchClassThresholdMAXIMUM = 
                     perfil.getValor("ProgMatchClassThresholdMAXIMUM");
            valProgMatchClassThresholdMINIMUM = 
                     perfil.getValor("ProgMatchClassThresholdMINIMUM");
            valProgMatchDIFFERENCE = 
                     perfil.getValor("ProgMatchDIFFERENCE");
            valProgMatchMethodACCEPTABLE = 
                     perfil.getValor("ProgMatchMethodACCEPTABLE");
            valProgMatchMethodDIFFERENCE = 
                     perfil.getValor("ProgMatchMethodDIFFERENCE");
            valProgMatchMethodMatchMINIMUM = 
                     perfil.getValor("ProgMatchMethodMatchMINIMUM");
            valClassAllowMULTIMATCH = 
                     perfil.getValor("ClassAllowMULTIMATCH");
            valClassConsiderOnlyMINIMUMINSTRUCTION = 
                     perfil.getValor("ClassConsiderOnlyMINIMUMINSTRUCTION");
            valClassMatchACCEPTABLE = 
                     perfil.getValor("ClassMatchACCEPTABLE");
            valClassMatchDIFFERENCE = 
                     perfil.getValor("ClassMatchDIFFERENCE");
            valClassMatchLIMIT = 
                     perfil.getValor("ClassMatchLIMIT");
            valClassMatchMethodMatchMINIMUM = 
                     perfil.getValor("ClassMatchMethodMatchMINIMUM");
            valClassMatchOPTIMISM = 
                     perfil.getValor("ClassMatchOPTIMISM");
            valClassSAMENAMEMATCH = 
                     perfil.getValor("ClassSAMENAMEMATCH");
            valMethodAllowMULTIMATCH = 
                     perfil.getValor("MethodAllowMULTIMATCH");
            valMethodConsiderOnlyMINIMUMINSTRUCTION = 
                     perfil.getValor("MethodConsiderOnlyMINIMUMINSTRUCTION");
            valMethodMatchACCEPTABLE = 
                     perfil.getValor("MethodMatchACCEPTABLE");
            valMethodMatchBlockMatchMINIMUM = 
                     perfil.getValor("MethodMatchBlockMatchMINIMUM");
            valMethodMatchDIFFERENCE = 
                     perfil.getValor("MethodMatchDIFFERENCE");
            valMethodMatchLIMIT = 
                     perfil.getValor("MethodMatchLIMIT");
            valMethodMatchOPTIMISM = 
                     perfil.getValor("MethodMatchOPTIMISM");
            valMethodOfClassSAMENAMEMATCH = 
                     perfil.getValor("MethodOfClassSAMENAMEMATCH");
            valMethodSAMENAMEMATCH = 
                     perfil.getValor("MethodSAMENAMEMATCH");
            valBlockAllowMULTIMATCH = 
                     perfil.getValor("BlockAllowMULTIMATCH");
            valBlockConsiderOnlyMINIMUMINSTRUCTION = 
                     perfil.getValor("BlockConsiderOnlyMINIMUMINSTRUCTION");
            sumweights =
               perfil.getValor("BlockWeightADDCOUNT")+
               perfil.getValor("BlockWeightANDCOUNT")+
               perfil.getValor("BlockWeightCHECKCASTCOUNT")+
               perfil.getValor("BlockWeightCONSTCOUNT")+
               perfil.getValor("BlockWeightDIVCOUNT")+
               perfil.getValor("BlockWeightDUPCOUNT")+
               perfil.getValor("BlockWeightGETFIELDCOUNT")+
               perfil.getValor("BlockWeightGETSTATICCOUNT")+
               perfil.getValor("BlockWeightGOTOCOUNT")+
               perfil.getValor("BlockWeightIFCOUNT")+
               perfil.getValor("BlockWeightINCCOUNT")+
               perfil.getValor("BlockWeightINSTRUCTIONCOUNT")+
               perfil.getValor("BlockWeightINVOKEINTERFACECOUNT")+
               perfil.getValor("BlockWeightINVOKESPECIALCOUNT")+
               perfil.getValor("BlockWeightINVOKESTATICCOUNT")+
               perfil.getValor("BlockWeightINVOKEVIRTUALCOUNT")+
               perfil.getValor("BlockWeightJSRCOUNT")+
               perfil.getValor("BlockWeightLDCCOUNT")+
               perfil.getValor("BlockWeightLOADCOUNT")+
               perfil.getValor("BlockWeightLOOKUPSWITCHCOUNT")+
               perfil.getValor("BlockWeightMETHODEND")+
               perfil.getValor("BlockWeightMETHODSTART")+
               perfil.getValor("BlockWeightMONITORENTERCOUNT")+
               perfil.getValor("BlockWeightMONITOREXITCOUNT")+
               perfil.getValor("BlockWeightMULCOUNT")+
               perfil.getValor("BlockWeightNEGCOUNT")+
               perfil.getValor("BlockWeightNEWARRAYCOUNT")+
               perfil.getValor("BlockWeightNEWCOUNT")+
               perfil.getValor("BlockWeightORCOUNT")+
               perfil.getValor("BlockWeightPOPCOUNT")+
               perfil.getValor("BlockWeightPUSHCOUNT")+
               perfil.getValor("BlockWeightPUTFIELDCOUNT")+
               perfil.getValor("BlockWeightREMCOUNT")+
               perfil.getValor("BlockWeightRETCOUNT")+
               perfil.getValor("BlockWeightRETURNCOUNT")+
               perfil.getValor("BlockWeightSHIFTLEFTCOUNT")+
               perfil.getValor("BlockWeightSHIFTRIGHTCOUNT")+
               perfil.getValor("BlockWeightSTORECOUNT")+
               perfil.getValor("BlockWeightSUBCOUNT")+
               perfil.getValor("BlockWeightSWAPCOUNT")+
               perfil.getValor("BlockWeightTABLESWITCHCOUNT")+
               perfil.getValor("BlockWeightTHROWCOUNT")+
               perfil.getValor("BlockWeightXORCOUNT");
         } catch (JSimilException e) {
               System.err.println(e.getMessage());
               System.exit(0);
         }
         auxdifmaxmin = regula(valProgMatchMAX-valProgMatchMIN);
            auxdifctmaxmin =
                    regula(valProgMatchClassThresholdMAXIMUM-
                           valProgMatchClassThresholdMINIMUM);
            aux = valProgMatchOPTIMISM;
            if (aux <= 0.01 ||
                    aux >= 0.99)
                auxprogmatchoptimismdist = 0;
            else
                auxprogmatchoptimismdist = 1;
            aux = valClassConsiderOnlyMINIMUMINSTRUCTION;
            if (aux <= 10)
                auxclassconlymin = 0;
            else if (aux > 10 && aux <= 100)
                auxclassconlymin = 1;
            else if (aux > 100 && aux <= 1000)
                auxclassconlymin = 2;   
            else if (aux > 1000 && aux <= 10000)
                auxclassconlymin = 4;
            else
                auxclassconlymin = 8;
            aux = valBlockConsiderOnlyMINIMUMINSTRUCTION;
            if (aux <= 1)
                auxblockconlymin = 0;
            else if (aux > 1 && aux <= 5)
                auxblockconlymin = 1;
            else
                auxblockconlymin = 3;   
            aux = valMethodConsiderOnlyMINIMUMINSTRUCTION;
            if (aux <= 1)
                auxmethodconlymin = 0;
            else if (aux > 1 && aux <= 5)
                auxmethodconlymin = 1;
            else if (aux > 5 && aux <= 20)
                auxmethodconlymin = 2;   
            else if (aux > 20 && aux <= 100)
                auxmethodconlymin = 3;
            else
                auxmethodconlymin = 4;
            aux = valMethodMatchOPTIMISM;
            if (aux <= 0.01 ||
                    aux >= 0.99)
                auxmethodmatchoptimismdist = 0;
            else
                auxmethodmatchoptimismdist = 1;
            aux = valClassMatchOPTIMISM;
            if (aux <= 0.01 ||
                    aux >= 0.99)
                auxclassmatchoptimismdist = 0;
            else
                auxclassmatchoptimismdist = 1;
            if (valDifferenceSEARCH > 0.5) {
                valProgMatchClassACCEPTABLE = 1-valProgMatchClassACCEPTABLE;
                valProgMatchMethodACCEPTABLE = 1-valProgMatchMethodACCEPTABLE;
                valClassMatchACCEPTABLE = 1-valClassMatchACCEPTABLE;
                valMethodMatchACCEPTABLE = 1-valMethodMatchACCEPTABLE;
                valProgMatchClassMatchMINIMUM = 1-valProgMatchClassMatchMINIMUM;
                valProgMatchClassThresholdMINIMUM =
                        1-valProgMatchClassThresholdMINIMUM;
                valProgMatchMethodMatchMINIMUM =
                        1-valProgMatchMethodMatchMINIMUM;
                valClassMatchMethodMatchMINIMUM =
                        1-valClassMatchMethodMatchMINIMUM;
                valMethodMatchBlockMatchMINIMUM =
                        1-valMethodMatchBlockMatchMINIMUM;
            }
            velocidad =
                 valReflexiveMATCH*70+
                 valReturnNullMatchesIfNULL*-10+
                 auxdifmaxmin*-50+
                 valProgMatchERROR*30+
                 valProgMatchLIMIT*40+
                 auxprogmatchoptimismdist*-40+
                 valProgMatchClassACCEPTABLE*-20+
                 valProgMatchClassDIFFERENCE*60+
                 valProgMatchClassMatchMINIMUM*-30+
                 auxdifctmaxmin*20+
                 valProgMatchDIFFERENCE*70+
                 valProgMatchMethodACCEPTABLE*-20+
                 valProgMatchMethodDIFFERENCE*20+
                 valClassAllowMULTIMATCH*-30+
                 auxclassconlymin*10+
                 valClassMatchACCEPTABLE*-20+
                 valClassMatchDIFFERENCE*20+
                 valClassMatchLIMIT*10+
                 auxclassmatchoptimismdist*-40+
                 valClassSAMENAMEMATCH*80+
                 valMethodAllowMULTIMATCH*-20+
                 auxmethodconlymin*10+
                 valMethodMatchACCEPTABLE*-20+
                 valMethodMatchDIFFERENCE*20+
                 valMethodMatchLIMIT*10+
                 auxmethodmatchoptimismdist*-40+
                 valMethodOfClassSAMENAMEMATCH*60+
                 valMethodSAMENAMEMATCH*40+
                 valBlockAllowMULTIMATCH*-10+
                 auxblockconlymin*10;
            velocidad = (velocidad+550)/1050;
            detalle =
                 valReflexiveMATCH*-20+
                 valReturnNullMatchesIfNULL*30+
                 auxdifmaxmin*40+
                 valProgMatchERROR*-60+
                 valProgMatchLIMIT*20+
                 auxprogmatchoptimismdist*40+
                 valProgMatchClassDIFFERENCE*-20+
                 valProgMatchClassMatchMINIMUM*-10+
                 auxdifctmaxmin*10+
                 valProgMatchDIFFERENCE*-20+
                 valProgMatchMethodDIFFERENCE*-20+
                 valProgMatchMethodMatchMINIMUM*-10+
                 valClassAllowMULTIMATCH*-20+
                 auxclassconlymin*-15+
                 valClassMatchDIFFERENCE*-20+
                 valClassMatchLIMIT*20+
                 valClassMatchMethodMatchMINIMUM*-10+
                 auxclassmatchoptimismdist*40+
                 valClassSAMENAMEMATCH*-10+
                 valMethodAllowMULTIMATCH*-20+
                 auxmethodconlymin*-15+
                 valMethodMatchBlockMatchMINIMUM*-10+
                 valMethodMatchDIFFERENCE*-20+
                 valMethodMatchLIMIT*20+
                 auxmethodmatchoptimismdist*40+
                 valMethodOfClassSAMENAMEMATCH*-10+
                 valMethodSAMENAMEMATCH*-20+
                 valBlockAllowMULTIMATCH*-10+
                 auxblockconlymin*-7;
            detalle = (detalle+511)/771;
            precision =
                 valProgMatchERROR*-80+
                 valProgMatchLIMIT*100+
                 valProgMatchClassACCEPTABLE*20+
                 valProgMatchClassDIFFERENCE*-10+
                 valProgMatchClassMatchMINIMUM*40+
                 valProgMatchDIFFERENCE*-10+
                 valProgMatchMethodACCEPTABLE*20+
                 valProgMatchMethodDIFFERENCE*-10+
                 valProgMatchMethodMatchMINIMUM*-20+
                 valClassAllowMULTIMATCH*20+
                 auxclassconlymin*-10+
                 valClassMatchACCEPTABLE*20+
                 valClassMatchDIFFERENCE*-10+
                 valClassMatchLIMIT*30+
                 valClassMatchMethodMatchMINIMUM*-20+
                 valClassSAMENAMEMATCH*-20+
                 valMethodAllowMULTIMATCH*20+
                 auxmethodconlymin*-10+
                 valMethodMatchACCEPTABLE*20+
                 valMethodMatchBlockMatchMINIMUM*-20+
                 valMethodMatchDIFFERENCE*-10+
                 valMethodMatchLIMIT*30+
                 valMethodOfClassSAMENAMEMATCH*-20+
                 valMethodSAMENAMEMATCH*-20+
                 valBlockAllowMULTIMATCH*20+
                 auxblockconlymin*-10;
            precision = (precision+160)/740;
            sensibilidad =
                 valDifferenceSEARCH*40+
                 valProgMatchLIMIT*-10+
                 valProgMatchClassACCEPTABLE*-40+
                 valProgMatchClassDIFFERENCE*-20+
                 valProgMatchClassMatchMINIMUM*-100+
                 valProgMatchDIFFERENCE*-20+
                 valProgMatchMethodACCEPTABLE*-30+
                 valProgMatchMethodDIFFERENCE*-10+
                 valProgMatchMethodMatchMINIMUM*-50+
                 valClassAllowMULTIMATCH*30+
                 auxclassconlymin*10+
                 valClassMatchACCEPTABLE*-20+
                 valClassMatchDIFFERENCE*-10+
                 valClassMatchLIMIT*-10+
                 valClassMatchMethodMatchMINIMUM*-50+
                 valClassSAMENAMEMATCH*30+
                 valMethodAllowMULTIMATCH*30+
                 auxmethodconlymin*10+
                 valMethodMatchACCEPTABLE*-20+
                 valMethodMatchBlockMatchMINIMUM*-30+
                 valMethodMatchDIFFERENCE*-10+
                 valMethodMatchLIMIT*-10+
                 valMethodOfClassSAMENAMEMATCH*30+
                 valMethodSAMENAMEMATCH*30+
                 valBlockAllowMULTIMATCH*30+
                 auxblockconlymin*10;
            sensibilidad = (sensibilidad+580)/810;
            asimilacion =
                 auxdifmaxmin*-30+
                 valProgMatchLIMIT*-20+
                 auxprogmatchoptimismdist*-20+
                 valProgMatchClassACCEPTABLE*20+
                 valProgMatchClassDIFFERENCE*20+
                 auxdifctmaxmin*-20+
                 valProgMatchDIFFERENCE*20+
                 valProgMatchMethodACCEPTABLE*20+
                 valProgMatchMethodDIFFERENCE*20+
                 valClassAllowMULTIMATCH*-20+
                 auxclassconlymin*5+
                 valClassMatchACCEPTABLE*20+
                 valClassMatchDIFFERENCE*20+
                 valClassMatchLIMIT*-20+
                 auxclassmatchoptimismdist*-20+
                 valClassSAMENAMEMATCH*20+
                 valMethodAllowMULTIMATCH*-20+
                 auxmethodconlymin*5+
                 valMethodMatchACCEPTABLE*20+
                 valMethodMatchDIFFERENCE*20+
                 valMethodMatchLIMIT*-20+
                 auxmethodmatchoptimismdist*-20+
                 valMethodOfClassSAMENAMEMATCH*20+
                 valMethodSAMENAMEMATCH*20+
                 valBlockAllowMULTIMATCH*-20+
                 auxblockconlymin*5;
            asimilacion = (asimilacion+270)/545;
            especializacion =
                 valReflexiveMATCH*30+
                 valDifferenceSEARCH*80+
                 auxdifmaxmin*-60+
                 auxprogmatchoptimismdist*20+
                 valProgMatchClassMatchMINIMUM*-30+
                 auxdifctmaxmin*-30+
                 valProgMatchMethodMatchMINIMUM*-30+
                 auxclassconlymin*15+
                 valClassMatchMethodMatchMINIMUM*-30+
                 auxclassmatchoptimismdist*20+
                 valClassSAMENAMEMATCH*20+
                 valMethodAllowMULTIMATCH*-20+
                 auxmethodconlymin*15+
                 valMethodMatchBlockMatchMINIMUM*-30+
                 auxmethodmatchoptimismdist*20+
                 valMethodOfClassSAMENAMEMATCH*20+
                 valMethodSAMENAMEMATCH*20+
                 valBlockAllowMULTIMATCH*-20+
                 auxblockconlymin*10;
            especializacion = (especializacion+450)/690;
         extrema = false;
         if (valProgMatchMIN == 0 && valProgMatchMAX == 0)
             extrema = true;
         if (valProgMatchERROR == 1 && auxdifmaxmin == 1)
             extrema = true;
         if (sumweights == 0)
             extrema = true;
         
         velocidad =        velocidad      -0.06390476;
         detalle =          detalle        -0.03735409;
         precision =        precision      -0.01945946;
         sensibilidad =     sensibilidad   -0.00925926;
         asimilacion =      asimilacion    -0.00825688;
         especializacion =  especializacion-0.01086957;

         velocidad =        Math.pow((velocidad      +0.5),1.6)-0.5;
         detalle =          Math.pow((detalle        +0.5),1.6)-0.5;
         precision =        Math.pow((precision      +0.5),2.5)-0.5;
         sensibilidad =     Math.pow((sensibilidad   +0.5),1.6)-0.5;
         asimilacion =      Math.pow((asimilacion    +0.5),1.6)-0.5;
         especializacion =  Math.pow((especializacion+0.5),1.6)-0.5;
         

         if (extrema) {
             velocidad = 0;
             detalle = 0;
             precision = 0;
             sensibilidad = 0;
             asimilacion = 0;
             especializacion = 0;             
         }
         
         velocidad = regula(velocidad);
         detalle = regula(detalle);
         precision = regula(precision);
         sensibilidad = regula(sensibilidad);
         asimilacion = regula(asimilacion);
         especializacion = regula(especializacion);
         atribVelocidad.setText(df.format(velocidad));
         atribDetalle.setText(df.format(detalle));
         atribPrecision.setText(df.format(precision));
         atribSensibilidad.setText(df.format(sensibilidad));
         atribAsimilacion.setText(df.format(asimilacion));
         atribEspecializacion.setText(df.format(especializacion));
         ((FingerprintPanel)huellaPanel).setValores(velocidad,detalle,precision,
                 sensibilidad,asimilacion,especializacion);
     }

     /**
      * Exportar huella con diálogo.
      * @.post Huella exportada tratada.
      */
    @Action
    public void exportarHuella() {
        managerCambio();
        JFileChooser fc = new JFileChooser(dir);
        ExtensionFileFilter ef =
                new ExtensionFileFilter(lang.getFrase(359),".png");
        fc.addChoosableFileFilter(ef);
        fc.setDialogTitle(lang.getFrase(360));
        fc.setApproveButtonText(lang.getFrase(361));
        fc.setApproveButtonToolTipText(lang.getFrase(362));
        fc.showSaveDialog(this.getFrame());
        if (fc.getSelectedFile() != null) {
            File diraux = fc.getSelectedFile().getParentFile();
            if (diraux != null)
                dir = diraux;
            String ruta = fc.getSelectedFile().getAbsolutePath();
            if (!ruta.toLowerCase()
                    .endsWith(".png") && fc.getFileFilter() == ef)
                ruta = ruta+".png";
            int opt = JOptionPane.YES_OPTION;
            if (fc.getSelectedFile().exists()) {
                opt = JOptionPane.showConfirmDialog(this.getFrame(),
                        lang.getFrase(345),lang.getFrase(344),
                        JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            }
            if (opt == JOptionPane.YES_OPTION) {
                try {
                    boolean ret;
                    ret = ((FingerprintPanel)huellaPanel).exportarImagen(ruta);
                    if (ret)
                        JOptionPane.showMessageDialog(this.getFrame(),
                            lang.getFrase(363,ruta),lang.getFrase(364),
                            JOptionPane.INFORMATION_MESSAGE);
                    else
                        throw new IOException("a");
                } catch (IOException e) {
                        JOptionPane.showMessageDialog(this.getFrame(),
                                lang.getFrase(366,ruta),
                                lang.getFrase(365),
                                JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
     
    /**
     * Gestor de cambios en la interfaz.
     * @.post Cambios gestionados.
     */
    private void managerCambio() {
        if (!quieto) {
            actualizaActual();
            ventanaAPerfil();
            ajustarPerfil();
            perfilAVentana();
            dibujarPerfil();
        }
    }
 
    /**
     * Obtener valor numérico de campo.
     * @param tf Campo.
     * @return Valor obtenido.
     */
    double getVal(JTextField tf) {
        try {
            return Double.parseDouble(tf.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    

    /**
     * Cambiar valor numérico de campo.
     * @param tf Campo.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    void setVal(JTextField tf,double val) {
        tf.setText(""+df2.format(val));
    }
   
    /**
     * Cambiar valor numérico de campo.
     * @param tf Campo.
     * @param val Nuevo valor.
     * @.post Valor cambiado.
     */
    void setVal2(JTextField tf,double val) {
        tf.setText(""+df3.format(val));
    }    
    
    /**
     * Ajusta 0 o 1 un valor del perfil.
     * @param valor Valor a ajustar.
     * @.post Valor ajustado. 
     * @exception JSimilException Campo inexistente.
     */
    public void ajusta01(String valor) throws JSimilException {
        if (perfil.getValor(valor) > 0.5)
            perfil.setValor(valor,1);
        else
            perfil.setValor(valor,0);
    }
    
    /**
     * Ajusta >= 0 y <= 1 un valor del perfil.
     * @param valor Valor a ajustar.
     * @.post Valor ajustado. 
     * @exception JSimilException Campo inexistente.
     */
    public void ajusta1(String valor) throws JSimilException {
        double val;
        val = perfil.getValor(valor);
        if (val < 0) {
            val = 0;
            perfil.setValor(valor,val);
        }
        else if (val > 1) {
            val = 1;
            perfil.setValor(valor,val);
        }
    }

    /**
     * Ajusta >= 0 o <= 100 un valor del perfil.
     * @param valor Valor a ajustar.
     * @.post Valor ajustado. 
     * @exception JSimilException Campo inexistente.
     */
    public void ajusta1a(String valor) throws JSimilException {
        double val;
        val = perfil.getValor(valor);
        if (val < 0) {
            val = 0;
            perfil.setValor(valor,val);
        }
        else if (val > 100) {
            val = 100;
            perfil.setValor(valor,val);
        }
    }
    
    /**
     * Ajusta >= 0 y < 1000000000 un valor del perfil.
     * @param valor Valor a ajustar.
     * @.post Valor ajustado. 
     * @exception JSimilException Campo inexistente.
     */
    public void ajusta1b(String valor) throws JSimilException {
        double val;
        val = perfil.getValor(valor);
        if (val < 0) {
            val = 0;
            perfil.setValor(valor,val);
        }
        if (val >= 1000000000) {
            val = 999999999;
            perfil.setValor(valor,val);
        }
    }
    
    /**
     * Ajusta valor mínimo y máximo en el perfil.
     * @param valormin Valor mínimo.
     * @param valormax Valor máximo.
     * @.post Valores ajustados.
     * @exception JSimilException Campo inexistente.
     */
    public void ajusta2(String valormin,String valormax) throws JSimilException {
        if (perfil.getValor(valormax) < perfil.getValor(valormin))
            perfil.setValor(valormax,perfil.getValor(valormin));
    }
    
    /**
     * Ajustar perfil.
     * @.post Perfil completo ajustado.
     */
     public void ajustarPerfil() {
         try {
            ajusta01("ReflexiveMATCH");
            ajusta01("DifferenceSEARCH");
            ajusta01("ReturnNullMatchesIfNULL");
            ajusta01("ClassSAMENAMEMATCH");
            ajusta01("MethodSAMENAMEMATCH");
            ajusta01("MethodOfClassSAMENAMEMATCH");
            ajusta01("ClassAllowMULTIMATCH");
            ajusta01("MethodAllowMULTIMATCH");
            ajusta01("BlockAllowMULTIMATCH");
            ajusta1("ProgMatchClassACCEPTABLE");   
            ajusta1("ProgMatchMethodACCEPTABLE");            
            ajusta1("ClassMatchACCEPTABLE");            
            ajusta1("MethodMatchACCEPTABLE");            
            ajusta1("ProgMatchClassMatchMINIMUM");            
            ajusta1("ProgMatchMethodMatchMINIMUM");            
            ajusta1("ClassMatchMethodMatchMINIMUM");            
            ajusta1("MethodMatchBlockMatchMINIMUM");            
            ajusta1("ProgMatchDIFFERENCE");            
            ajusta1("ProgMatchClassDIFFERENCE");            
            ajusta1("ProgMatchMethodDIFFERENCE");            
            ajusta1("ClassMatchDIFFERENCE");            
            ajusta1("MethodMatchDIFFERENCE");            
            ajusta1("ProgMatchLIMIT");            
            ajusta1("ClassMatchLIMIT");            
            ajusta1("MethodMatchLIMIT");            
            ajusta1b("ClassConsiderOnlyMINIMUMINSTRUCTION");
            ajusta1b("MethodConsiderOnlyMINIMUMINSTRUCTION");
            ajusta1b("BlockConsiderOnlyMINIMUMINSTRUCTION");
            ajusta1("ProgMatchOPTIMISM");
            ajusta1("MethodMatchOPTIMISM");
            ajusta1("ClassMatchOPTIMISM");
            ajusta1("ProgMatchClassThresholdMAXIMUM");
            ajusta1("ProgMatchClassThresholdMINIMUM");
             ajusta1("ProgMatchMIN");
             ajusta1("ProgMatchMAX");
             ajusta1("ProgMatchERROR");
             ajusta2("ProgMatchMIN","ProgMatchMAX");
             ajusta2("ProgMatchClassThresholdMINIMUM",
                     "ProgMatchClassThresholdMAXIMUM");
            ajusta1a("BlockWeightCHECKCASTCOUNT");
            ajusta1a("BlockWeightGETFIELDCOUNT");
            ajusta1a("BlockWeightPUTFIELDCOUNT");
            ajusta1a("BlockWeightGETSTATICCOUNT");
            ajusta1a("BlockWeightNEWARRAYCOUNT");
            ajusta1a("BlockWeightNEWCOUNT");
            ajusta1a("BlockWeightSHIFTLEFTCOUNT");
            ajusta1a("BlockWeightSHIFTRIGHTCOUNT");
            ajusta1a("BlockWeightRETURNCOUNT");
            ajusta1a("BlockWeightCONSTCOUNT");
            ajusta1a("BlockWeightTHROWCOUNT");
            ajusta1a("BlockWeightLOADCOUNT");
            ajusta1a("BlockWeightSTORECOUNT");
            ajusta1a("BlockWeightLDCCOUNT");
            ajusta1a("BlockWeightDUPCOUNT");
            ajusta1a("BlockWeightGOTOCOUNT");
            ajusta1a("BlockWeightIFCOUNT");
            ajusta1a("BlockWeightJSRCOUNT");
            ajusta1a("BlockWeightPUSHCOUNT");
            ajusta1a("BlockWeightPOPCOUNT");
            ajusta1a("BlockWeightSWAPCOUNT");
            ajusta1a("BlockWeightINCCOUNT");
            ajusta1a("BlockWeightDIVCOUNT");
            ajusta1a("BlockWeightMULCOUNT");
            ajusta1a("BlockWeightADDCOUNT");
            ajusta1a("BlockWeightREMCOUNT");
            ajusta1a("BlockWeightSUBCOUNT");
            ajusta1a("BlockWeightANDCOUNT");
            ajusta1a("BlockWeightNEGCOUNT");
            ajusta1a("BlockWeightORCOUNT");
            ajusta1a("BlockWeightRETCOUNT");
            ajusta1a("BlockWeightXORCOUNT");
            ajusta1a("BlockWeightINVOKEINTERFACECOUNT");
            ajusta1a("BlockWeightINVOKESPECIALCOUNT");
            ajusta1a("BlockWeightINVOKESTATICCOUNT");
            ajusta1a("BlockWeightINVOKEVIRTUALCOUNT");
            ajusta1a("BlockWeightINSTRUCTIONCOUNT");
            ajusta1a("BlockWeightLOOKUPSWITCHCOUNT");
            ajusta1a("BlockWeightTABLESWITCHCOUNT");
            ajusta1a("BlockWeightMONITORENTERCOUNT");
            ajusta1a("BlockWeightMONITOREXITCOUNT");
            ajusta1a("BlockWeightMETHODEND");
            ajusta1a("BlockWeightMETHODSTART");             
         } catch (JSimilException e) {
             System.err.println(e.getMessage());
             System.exit(0);             
         }
         if (hashcode != perfil.hashCode())
             modificado = true;
         else
             modificado = false;
         actualizaActual(); 
     }

    /**
     * Pasar perfil a ventana.
     * @.post Perfil pasado a ventana.
     */
     public void perfilAVentana() {
         quieto = true;
         try {
             if (perfil.getValor("ReflexiveMATCH") > 0.5)
                 butGReflexive.setSelected(butReflexiveSi.getModel(),true);
             else
                 butGReflexive.setSelected(butReflexiveNo.getModel(),true);
             if (perfil.getValor("DifferenceSEARCH") > 0.5)
                 butGDifference.setSelected(butDifferenceSearchSi.getModel(),true);
             else
                 butGDifference.setSelected(butDifferenceSearchNo.getModel(),true);
             if (perfil.getValor("ReturnNullMatchesIfNULL") > 0.5)
                 butReturnNull.setSelected(true);
             else
                 butReturnNull.setSelected(false);
             if (perfil.getValor("ClassAllowMULTIMATCH") > 0.5)
                 butClassAllowMultiMatch.setSelected(true);
             else
                 butClassAllowMultiMatch.setSelected(false);
             if (perfil.getValor("MethodAllowMULTIMATCH") > 0.5)
                 butMethodAllowMultiMatch.setSelected(true);
             else
                 butMethodAllowMultiMatch.setSelected(false);
             if (perfil.getValor("BlockAllowMULTIMATCH") > 0.5)
                 butBlockAllowMultiMatch.setSelected(true);
             else
                 butBlockAllowMultiMatch.setSelected(false);
             if (perfil.getValor("ClassSAMENAMEMATCH") > 0.5)
                 butClassSameNameMatch.setSelected(true);
             else
                 butClassSameNameMatch.setSelected(false);             
             if (perfil.getValor("MethodOfClassSAMENAMEMATCH") > 0.5)
                 butMethodOfClassSameNameMatch.setSelected(true);
             else
                 butMethodOfClassSameNameMatch.setSelected(false);             
             if (perfil.getValor("MethodSAMENAMEMATCH") > 0.5)
                 butMethodSameNameMatch.setSelected(true);
             else
                 butMethodSameNameMatch.setSelected(false);             
             butAcceptableClassBar.setValue(
                 (int)(perfil.getValor("ProgMatchClassACCEPTABLE")*10000));
             butAcceptableProgramMethodBar.setValue(
                 (int)(perfil.getValor("ProgMatchMethodACCEPTABLE")*10000));
             butAcceptableClassMethodBar.setValue(
                 (int)(perfil.getValor("ClassMatchACCEPTABLE")*10000));
             butAcceptableBlockBar.setValue(
                 (int)(perfil.getValor("MethodMatchACCEPTABLE")*10000));
             butMinimumClassBar.setValue(
                 (int)(perfil.getValor("ProgMatchClassMatchMINIMUM")*10000));
             butMinimumProgramMethodBar.setValue(
                 (int)(perfil.getValor("ProgMatchMethodMatchMINIMUM")*10000));
             butMinimumClassMethodBar.setValue(
                 (int)(perfil.getValor("ClassMatchMethodMatchMINIMUM")*10000));
             butMinimumBlockBar.setValue(
                 (int)(perfil.getValor("MethodMatchBlockMatchMINIMUM")*10000));
             butDifferenceProgramBar.setValue(
                 (int)((1-perfil.getValor("ProgMatchDIFFERENCE"))*10000));
             butDifferenceClassBar.setValue(
                 (int)((1-perfil.getValor("ProgMatchClassDIFFERENCE"))*10000));
             butDifferenceProgramMethodBar.setValue(
                 (int)((1-perfil.getValor("ProgMatchMethodDIFFERENCE"))*10000));
             butDifferenceClassMethodBar.setValue(
                 (int)((1-perfil.getValor("ClassMatchDIFFERENCE"))*10000));
             butDifferenceBlockBar.setValue(
                 (int)((1-perfil.getValor("MethodMatchDIFFERENCE"))*10000));
             butLimitProgramBar.setValue(
                 (int)(perfil.getValor("ProgMatchLIMIT")*10000));
             butLimitClassBar.setValue(
                 (int)(perfil.getValor("ClassMatchLIMIT")*10000));
             butLimitMethodBar.setValue(
                 (int)(perfil.getValor("MethodMatchLIMIT")*10000));
             butOptimismProgBar.setValue(
                 (int)(perfil.getValor("ProgMatchOPTIMISM")*10000));
             butOptimismClassBar.setValue(
                 (int)(perfil.getValor("ClassMatchOPTIMISM")*10000));
             butOptimismMethodBar.setValue(
                 (int)(perfil.getValor("MethodMatchOPTIMISM")*10000));
             butProgMatchErrorBar.setValue(
                     (int)(perfil.getValor("ProgMatchERROR")*10000));
             butProgMatchMinMaxMinBar.setValue(
                     (int)(perfil.getValor("ProgMatchMIN")*10000));
             int val = (int)(perfil.getValor("ProgMatchMAX")*10000);
             if (val == 10000)
                 val = 10001;
             butProgMatchMinMaxMaxBar.setValue(val);
             butClassThresholdMinBar.setValue(
                (int)(perfil.getValor("ProgMatchClassThresholdMINIMUM")*10000));
             val = (int)(perfil.getValor("ProgMatchClassThresholdMAXIMUM")
                     *10000);
             if (val == 10000)
                 val = 10001;
             butClassThresholdMaxBar.setValue(val);
             butProgMatchMinMaxMaxBar.setMinimum(
                     (int)(perfil.getValor("ProgMatchMIN")*10000));
             butClassThresholdMaxBar.setMinimum(
                     (int)(perfil.getValor("ProgMatchClassThresholdMINIMUM")*10000));
            setVal2(butMinimumInstructionClass,
                perfil.getValor("ClassConsiderOnlyMINIMUMINSTRUCTION"));
            setVal2(butMinimumInstructionMethod,
                perfil.getValor("MethodConsiderOnlyMINIMUMINSTRUCTION"));
            setVal2(butMinimumInstructionBlock,
                perfil.getValor("BlockConsiderOnlyMINIMUMINSTRUCTION"));
             setVal(butOptimismProgText,
                     perfil.getValor("ProgMatchOPTIMISM")*100);
             setVal(butOptimismClassText,
                     perfil.getValor("ClassMatchOPTIMISM")*100);
             setVal(butOptimismMethodText,
                     perfil.getValor("MethodMatchOPTIMISM")*100);
             setVal(butClassThresholdMinText,
                     perfil.getValor("ProgMatchClassThresholdMINIMUM")*100);
             setVal(butClassThresholdMaxText,
                     perfil.getValor("ProgMatchClassThresholdMAXIMUM")*100);
             setVal(butProgMatchErrorText,
                     perfil.getValor("ProgMatchERROR")*100);
             setVal(butProgMatchMinMaxMinText,
                     perfil.getValor("ProgMatchMIN")*100);
             setVal(butProgMatchMinMaxMaxText,
                     perfil.getValor("ProgMatchMAX")*100);
             setVal(butAcceptableClassText,
                     perfil.getValor("ProgMatchClassACCEPTABLE")*100);
             setVal(butAcceptableProgramMethodText,
                     perfil.getValor("ProgMatchMethodACCEPTABLE")*100);
             setVal(butAcceptableClassMethodText,
                     perfil.getValor("ClassMatchACCEPTABLE")*100);
             setVal(butAcceptableBlockText,
                     perfil.getValor("MethodMatchACCEPTABLE")*100);
             setVal(butMinimumClassText,
                     perfil.getValor("ProgMatchClassMatchMINIMUM")*100);
             setVal(butMinimumProgramMethodText,
                     perfil.getValor("ProgMatchMethodMatchMINIMUM")*100);
             setVal(butMinimumClassMethodText,
                     perfil.getValor("ClassMatchMethodMatchMINIMUM")*100);
             setVal(butMinimumBlockText,
                     perfil.getValor("MethodMatchBlockMatchMINIMUM")*100);
             setVal(butDifferenceProgramText,
                     (1-perfil.getValor("ProgMatchDIFFERENCE"))*100);
             setVal(butDifferenceClassText,
                     (1-perfil.getValor("ProgMatchClassDIFFERENCE"))*100);
             setVal(butDifferenceProgramMethodText,
                     (1-perfil.getValor("ProgMatchMethodDIFFERENCE"))*100);
             setVal(butDifferenceClassMethodText,
                     (1-perfil.getValor("ClassMatchDIFFERENCE"))*100);
             setVal(butDifferenceBlockText,
                     (1-perfil.getValor("MethodMatchDIFFERENCE"))*100);
             setVal(butLimitProgramText,
                     perfil.getValor("ProgMatchLIMIT")*100);
             setVal(butLimitClassText,
                     perfil.getValor("ClassMatchLIMIT")*100);
             setVal(butLimitMethodText,
                     perfil.getValor("MethodMatchLIMIT")*100);             
            setVal(butBlockWeightCheckCast,
                perfil.getValor("BlockWeightCHECKCASTCOUNT"));
            setVal(butBlockWeightGetField,
                perfil.getValor("BlockWeightGETFIELDCOUNT"));
            setVal(butBlockWeightPutField,
                perfil.getValor("BlockWeightPUTFIELDCOUNT"));
            setVal(butBlockWeightGetStatic,
                perfil.getValor("BlockWeightGETSTATICCOUNT"));
            setVal(butBlockWeightNewArray,
                perfil.getValor("BlockWeightNEWARRAYCOUNT"));
            setVal(butBlockWeightNew,
                perfil.getValor("BlockWeightNEWCOUNT"));
            setVal(butBlockWeightShiftLeft,
                perfil.getValor("BlockWeightSHIFTLEFTCOUNT"));
            setVal(butBlockWeightShiftRight,
                perfil.getValor("BlockWeightSHIFTRIGHTCOUNT"));
            setVal(butBlockWeightReturn,
                perfil.getValor("BlockWeightRETURNCOUNT"));
            setVal(butBlockWeightConst,
                perfil.getValor("BlockWeightCONSTCOUNT"));
            setVal(butBlockWeightThrow,
                perfil.getValor("BlockWeightTHROWCOUNT"));
            setVal(butBlockWeightLoad,
                perfil.getValor("BlockWeightLOADCOUNT"));
            setVal(butBlockWeightStore,
                perfil.getValor("BlockWeightSTORECOUNT"));
            setVal(butBlockWeightLDC,
                perfil.getValor("BlockWeightLDCCOUNT"));
            setVal(butBlockWeightDup,
                perfil.getValor("BlockWeightDUPCOUNT"));
            setVal(butBlockWeightGoto,
                perfil.getValor("BlockWeightGOTOCOUNT"));
            setVal(butBlockWeightIf,
                perfil.getValor("BlockWeightIFCOUNT"));
            setVal(butBlockWeightJSR,
                perfil.getValor("BlockWeightJSRCOUNT"));
            setVal(butBlockWeightPush,
                perfil.getValor("BlockWeightPUSHCOUNT"));
            setVal(butBlockWeightPop,
                perfil.getValor("BlockWeightPOPCOUNT"));
            setVal(butBlockWeightSwap,
                perfil.getValor("BlockWeightSWAPCOUNT"));
            setVal(butBlockWeightInc,
                perfil.getValor("BlockWeightINCCOUNT"));
            setVal(butBlockWeightDiv,
                perfil.getValor("BlockWeightDIVCOUNT"));
            setVal(butBlockWeightMul,
                perfil.getValor("BlockWeightMULCOUNT"));
            setVal(butBlockWeightAdd,
                perfil.getValor("BlockWeightADDCOUNT"));
            setVal(butBlockWeightRem,
                perfil.getValor("BlockWeightREMCOUNT"));
            setVal(butBlockWeightSub,
                perfil.getValor("BlockWeightSUBCOUNT"));
            setVal(butBlockWeightAnd,
                perfil.getValor("BlockWeightANDCOUNT"));
            setVal(butBlockWeightNeg,
                perfil.getValor("BlockWeightNEGCOUNT"));
            setVal(butBlockWeightOr,
                perfil.getValor("BlockWeightORCOUNT"));
            setVal(butBlockWeightRet,
                perfil.getValor("BlockWeightRETCOUNT"));
            setVal(butBlockWeightXor,
                perfil.getValor("BlockWeightXORCOUNT"));
            setVal(butBlockWeightInvokeInterface,
                perfil.getValor("BlockWeightINVOKEINTERFACECOUNT"));
            setVal(butBlockWeightInvokeSpecial,
                perfil.getValor("BlockWeightINVOKESPECIALCOUNT"));
            setVal(butBlockWeightInvokeStatic,
                perfil.getValor("BlockWeightINVOKESTATICCOUNT"));
            setVal(butBlockWeightInvokeVirtual,
                perfil.getValor("BlockWeightINVOKEVIRTUALCOUNT"));
            setVal(butBlockWeightInstruction,
                perfil.getValor("BlockWeightINSTRUCTIONCOUNT"));
            setVal(butBlockWeightLookupSwitch,
                perfil.getValor("BlockWeightLOOKUPSWITCHCOUNT"));
            setVal(butBlockWeightTableSwitch,
                perfil.getValor("BlockWeightTABLESWITCHCOUNT"));
            setVal(butBlockWeightMonitorEnter,
                perfil.getValor("BlockWeightMONITORENTERCOUNT"));
            setVal(butBlockWeightMonitorExit,
                perfil.getValor("BlockWeightMONITOREXITCOUNT"));
            setVal(butBlockWeightMethodEnd,
                perfil.getValor("BlockWeightMETHODEND"));
            setVal(butBlockWeightMethodStart,
                perfil.getValor("BlockWeightMETHODSTART"));

         } catch (JSimilException e) {
             System.err.println(e.getMessage());
             System.exit(0);             
         }
         quieto = false;
     }     

    /**
     * Pasar ventana a perfil.
     * @.post Ventana pasada a perfil.
     */
     public void ventanaAPerfil() {
         try {
             if (butGReflexive.getSelection()==butReflexiveNo.getModel())
                 perfil.setValor("ReflexiveMATCH",0.0);
             else
                 perfil.setValor("ReflexiveMATCH",1.0);
             if (butGDifference.getSelection()==butDifferenceSearchNo.getModel())
                 perfil.setValor("DifferenceSEARCH",0.0);
             else
                 perfil.setValor("DifferenceSEARCH",1.0);
             if (butReturnNull.isSelected())
                 perfil.setValor("ReturnNullMatchesIfNULL",1.0);
             else
                 perfil.setValor("ReturnNullMatchesIfNULL",0.0);
             if (butClassSameNameMatch.isSelected())
                 perfil.setValor("ClassSAMENAMEMATCH",1.0);
             else
                 perfil.setValor("ClassSAMENAMEMATCH",0.0);
             if (butMethodOfClassSameNameMatch.isSelected())
                 perfil.setValor("MethodOfClassSAMENAMEMATCH",1.0);
             else
                 perfil.setValor("MethodOfClassSAMENAMEMATCH",0.0);
             if (butMethodSameNameMatch.isSelected())
                 perfil.setValor("MethodSAMENAMEMATCH",1.0);
             else
                 perfil.setValor("MethodSAMENAMEMATCH",0.0);
             if (butClassAllowMultiMatch.isSelected())
                 perfil.setValor("ClassAllowMULTIMATCH",1.0);
             else
                 perfil.setValor("ClassAllowMULTIMATCH",0.0);
             if (butMethodAllowMultiMatch.isSelected())
                 perfil.setValor("MethodAllowMULTIMATCH",1.0);
             else
                 perfil.setValor("MethodAllowMULTIMATCH",0.0);
             if (butBlockAllowMultiMatch.isSelected())
                 perfil.setValor("BlockAllowMULTIMATCH",1.0);
             else
                 perfil.setValor("BlockAllowMULTIMATCH",0.0);
            perfil.setValor("ClassConsiderOnlyMINIMUMINSTRUCTION",
                getVal(butMinimumInstructionClass));
            perfil.setValor("MethodConsiderOnlyMINIMUMINSTRUCTION",
                getVal(butMinimumInstructionMethod));
            perfil.setValor("BlockConsiderOnlyMINIMUMINSTRUCTION",
                getVal(butMinimumInstructionBlock));
             perfil.setValor("ProgMatchOPTIMISM",
                     getVal(butOptimismProgText)/100.0);
             perfil.setValor("ClassMatchOPTIMISM",
                     getVal(butOptimismClassText)/100.0);
             perfil.setValor("MethodMatchOPTIMISM",
                     getVal(butOptimismMethodText)/100.0);
             perfil.setValor("ProgMatchClassThresholdMINIMUM",
                     getVal(butClassThresholdMinText)/100.0);
             perfil.setValor("ProgMatchClassThresholdMAXIMUM",
                     getVal(butClassThresholdMaxText)/100.0);
             perfil.setValor("ProgMatchMIN",
                     getVal(butProgMatchMinMaxMinText)/100.0);
             perfil.setValor("ProgMatchMAX",
                     getVal(butProgMatchMinMaxMaxText)/100.0);
             perfil.setValor("ProgMatchERROR",
                     getVal(butProgMatchErrorText)/100.0);
             perfil.setValor("ProgMatchClassACCEPTABLE",
                     getVal(butAcceptableClassText)/100.0);
             perfil.setValor("ProgMatchMethodACCEPTABLE",
                     getVal(butAcceptableProgramMethodText)/100.0);
             perfil.setValor("ClassMatchACCEPTABLE",
                     getVal(butAcceptableClassMethodText)/100.0);
             perfil.setValor("MethodMatchACCEPTABLE",
                     getVal(butAcceptableBlockText)/100.0);
             perfil.setValor("ProgMatchClassMatchMINIMUM",
                     getVal(butMinimumClassText)/100.0);
             perfil.setValor("ProgMatchMethodMatchMINIMUM",
                     getVal(butMinimumProgramMethodText)/100.0);
             perfil.setValor("ClassMatchMethodMatchMINIMUM",
                     getVal(butMinimumClassMethodText)/100.0);
             perfil.setValor("MethodMatchBlockMatchMINIMUM",
                     getVal(butMinimumBlockText)/100.0);
             perfil.setValor("ProgMatchDIFFERENCE",
                     (1-getVal(butDifferenceProgramText)/100.0));
             perfil.setValor("ProgMatchClassDIFFERENCE",
                     (1-getVal(butDifferenceClassText)/100.0));
             perfil.setValor("ProgMatchMethodDIFFERENCE",
                     (1-getVal(butDifferenceProgramMethodText)/100.0));
             perfil.setValor("ClassMatchDIFFERENCE",
                     (1-getVal(butDifferenceClassMethodText)/100.0));
             perfil.setValor("MethodMatchDIFFERENCE",
                     (1-getVal(butDifferenceBlockText)/100.0));
             perfil.setValor("ProgMatchLIMIT",
                     getVal(butLimitProgramText)/100.0);
             perfil.setValor("ClassMatchLIMIT",
                     getVal(butLimitClassText)/100.0);
             perfil.setValor("MethodMatchLIMIT",
                     getVal(butLimitMethodText)/100.0);
            perfil.setValor("BlockWeightCHECKCASTCOUNT",
                getVal(butBlockWeightCheckCast));
            perfil.setValor("BlockWeightGETFIELDCOUNT",
                getVal(butBlockWeightGetField));
            perfil.setValor("BlockWeightPUTFIELDCOUNT",
                getVal(butBlockWeightPutField));
            perfil.setValor("BlockWeightGETSTATICCOUNT",
                getVal(butBlockWeightGetStatic));
            perfil.setValor("BlockWeightNEWARRAYCOUNT",
                getVal(butBlockWeightNewArray));
            perfil.setValor("BlockWeightNEWCOUNT",
                getVal(butBlockWeightNew));
            perfil.setValor("BlockWeightSHIFTLEFTCOUNT",
                getVal(butBlockWeightShiftLeft));
            perfil.setValor("BlockWeightSHIFTRIGHTCOUNT",
                getVal(butBlockWeightShiftRight));
            perfil.setValor("BlockWeightRETURNCOUNT",
                getVal(butBlockWeightReturn));
            perfil.setValor("BlockWeightCONSTCOUNT",
                getVal(butBlockWeightConst));
            perfil.setValor("BlockWeightTHROWCOUNT",
                getVal(butBlockWeightThrow));
            perfil.setValor("BlockWeightLOADCOUNT",
                getVal(butBlockWeightLoad));
            perfil.setValor("BlockWeightSTORECOUNT",
                getVal(butBlockWeightStore));
            perfil.setValor("BlockWeightLDCCOUNT",
                getVal(butBlockWeightLDC));
            perfil.setValor("BlockWeightDUPCOUNT",
                getVal(butBlockWeightDup));
            perfil.setValor("BlockWeightGOTOCOUNT",
                getVal(butBlockWeightGoto));
            perfil.setValor("BlockWeightIFCOUNT",
                getVal(butBlockWeightIf));
            perfil.setValor("BlockWeightJSRCOUNT",
                getVal(butBlockWeightJSR));
            perfil.setValor("BlockWeightPUSHCOUNT",
                getVal(butBlockWeightPush));
            perfil.setValor("BlockWeightPOPCOUNT",
                getVal(butBlockWeightPop));
            perfil.setValor("BlockWeightSWAPCOUNT",
                getVal(butBlockWeightSwap));
            perfil.setValor("BlockWeightINCCOUNT",
                getVal(butBlockWeightInc));
            perfil.setValor("BlockWeightDIVCOUNT",
                getVal(butBlockWeightDiv));
            perfil.setValor("BlockWeightMULCOUNT",
                getVal(butBlockWeightMul));
            perfil.setValor("BlockWeightADDCOUNT",
                getVal(butBlockWeightAdd));
            perfil.setValor("BlockWeightREMCOUNT",
                getVal(butBlockWeightRem));
            perfil.setValor("BlockWeightSUBCOUNT",
                getVal(butBlockWeightSub));
            perfil.setValor("BlockWeightANDCOUNT",
                getVal(butBlockWeightAnd));
            perfil.setValor("BlockWeightNEGCOUNT",
                getVal(butBlockWeightNeg));
            perfil.setValor("BlockWeightORCOUNT",
                getVal(butBlockWeightOr));
            perfil.setValor("BlockWeightRETCOUNT",
                getVal(butBlockWeightRet));
            perfil.setValor("BlockWeightXORCOUNT",
                getVal(butBlockWeightXor));
            perfil.setValor("BlockWeightINVOKEINTERFACECOUNT",
                getVal(butBlockWeightInvokeInterface));
            perfil.setValor("BlockWeightINVOKESPECIALCOUNT",
                getVal(butBlockWeightInvokeSpecial));
            perfil.setValor("BlockWeightINVOKESTATICCOUNT",
                getVal(butBlockWeightInvokeStatic));
            perfil.setValor("BlockWeightINVOKEVIRTUALCOUNT",
                getVal(butBlockWeightInvokeVirtual));
            perfil.setValor("BlockWeightINSTRUCTIONCOUNT",
                getVal(butBlockWeightInstruction));
            perfil.setValor("BlockWeightLOOKUPSWITCHCOUNT",
                getVal(butBlockWeightLookupSwitch));
            perfil.setValor("BlockWeightTABLESWITCHCOUNT",
                getVal(butBlockWeightTableSwitch));
            perfil.setValor("BlockWeightMONITORENTERCOUNT",
                getVal(butBlockWeightMonitorEnter));
            perfil.setValor("BlockWeightMONITOREXITCOUNT",
                getVal(butBlockWeightMonitorExit));
            perfil.setValor("BlockWeightMETHODEND",
                getVal(butBlockWeightMethodEnd));
            perfil.setValor("BlockWeightMETHODSTART",
                getVal(butBlockWeightMethodStart));

         } catch (JSimilException e) {
             System.err.println(e.getMessage());
             System.exit(0);
         }
     }

    /**
     * Gestor de la ayuda contextual.
     * @param nombre Nombre del elemento usado.
     * @.post Ayuda textual cambiada.
     */
    private void manager(String nombre) {
        String newtext = "no#$";
        if (nombre.startsWith("butReflexive"))
            newtext = lang.getFrase(339);
        else if (nombre.startsWith("butDifferenceSearch"))
            newtext = lang.getFrase(369);
        else if (nombre.equals("butReturnNull"))
            newtext = lang.getFrase(371);
        else if (nombre.startsWith("butProgMatchMinMax"))
            newtext = lang.getFrase(384);
        else if (nombre.startsWith("butProgMatchError"))
            newtext = lang.getFrase(385);
        else if (nombre.startsWith("butBlockWeight"))
            newtext = lang.getFrase(396);
        else if (nombre.startsWith("butMinimumInstruction"))
            newtext = lang.getFrase(397);
        else if (nombre.equals("butBlockAllowMultiMatch"))
            newtext = lang.getFrase(383);
        else if (nombre.equals("butMethodAllowMultiMatch"))
            newtext = lang.getFrase(381);
        else if (nombre.equals("butClassAllowMultiMatch"))
            newtext = lang.getFrase(379);
        else if (nombre.equals("butClassSameNameMatch"))
            newtext = lang.getFrase(373);
        else if (nombre.equals("butMethodSameNameMatch"))
            newtext = lang.getFrase(377);
        else if (nombre.equals("butMethodOfClassSameNameMatch"))
            newtext = lang.getFrase(375);
        else if (nombre.startsWith("butOptimism"))
            newtext = lang.getFrase(418);
        else if (nombre.startsWith("butClassThreshold"))
            newtext = lang.getFrase(419);
        else if (nombre.startsWith("butDifference"))
            newtext = lang.getFrase(420);
        else if (nombre.startsWith("butLimit"))
            newtext = lang.getFrase(421);
        else if (nombre.startsWith("butAcceptable"))
            newtext = lang.getFrase(422);
        else if (nombre.startsWith("butMinimum"))
            newtext = lang.getFrase(423);
        else if (nombre.startsWith("huella"))
            newtext = lang.getFrase(427);
        else if (nombre.startsWith("atrib"))
            newtext = lang.getFrase(428);
        else if (nombre.equals("quita"))
            newtext = "";
        else if (nombre.equals("entra"))
            newtext = lang.getFrase(429);
        
        if (!newtext.equals("no#$"))
            if (!newtext.equals(ayudaTextArea.getText())) {
                ayudaTextArea.setText(newtext);
                ayudaTextArea.setCaretPosition(0);
            }
    }

    /**
     * Gestor de cambios en barras.
     * @param nombre Nombre del elemento usado.
     * @.post Contenido de las entradas de texto actualizados..
     */
    private void managerCambioBarra(String nombre) {
        if (nombre.equals("butProgMatchErrorBar"))
            setVal(butProgMatchErrorText,
                    butProgMatchErrorBar.getValue()/100.0);
        if (nombre.equals("butProgMatchMinMaxMinBar"))
            setVal(butProgMatchMinMaxMinText,
                    butProgMatchMinMaxMinBar.getValue()/100.0);
        if (nombre.equals("butProgMatchMinMaxMaxBar")) {
            int val = butProgMatchMinMaxMaxBar.getValue();
            if (val == 10001)
                val = 10000;
            setVal(butProgMatchMinMaxMaxText,
                    val/100.0);
        }
        if (nombre.equals("butOptimismProgBar"))
            setVal(butOptimismProgText,
                    butOptimismProgBar.getValue()/100.0);        
        if (nombre.equals("butOptimismClassBar"))
            setVal(butOptimismClassText,
                    butOptimismClassBar.getValue()/100.0);        
        if (nombre.equals("butOptimismMethodBar"))
            setVal(butOptimismMethodText,
                    butOptimismMethodBar.getValue()/100.0);        
        if (nombre.equals("butClassThresholdMinBar"))
            setVal(butClassThresholdMinText,
                    butClassThresholdMinBar.getValue()/100.0);
        if (nombre.equals("butAcceptableClassBar"))
            setVal(butAcceptableClassText,
                    butAcceptableClassBar.getValue()/100.0);
        if (nombre.equals("butAcceptableProgramMethodBar"))
            setVal(butAcceptableProgramMethodText,
                    butAcceptableProgramMethodBar.getValue()/100.0);
        if (nombre.equals("butAcceptableClassMethodBar"))
            setVal(butAcceptableClassMethodText,
                    butAcceptableClassMethodBar.getValue()/100.0);
        if (nombre.equals("butAcceptableBlockBar"))
            setVal(butAcceptableBlockText,
                    butAcceptableBlockBar.getValue()/100.0);
        if (nombre.equals("butMinimumClassBar"))
            setVal(butMinimumClassText,
                    butMinimumClassBar.getValue()/100.0);
        if (nombre.equals("butMinimumProgramMethodBar"))
            setVal(butMinimumProgramMethodText,
                    butMinimumProgramMethodBar.getValue()/100.0);
        if (nombre.equals("butMinimumClassMethodBar"))
            setVal(butMinimumClassMethodText,
                    butMinimumClassMethodBar.getValue()/100.0);
        if (nombre.equals("butMinimumBlockBar"))
            setVal(butMinimumBlockText,
                    butMinimumBlockBar.getValue()/100.0);
        if (nombre.equals("butDifferenceProgramBar"))
            setVal(butDifferenceProgramText,
                    butDifferenceProgramBar.getValue()/100.0);
        if (nombre.equals("butDifferenceClassBar"))
            setVal(butDifferenceClassText,
                    butDifferenceClassBar.getValue()/100.0);
        if (nombre.equals("butDifferenceProgramMethodBar"))
            setVal(butDifferenceProgramMethodText,
                    butDifferenceProgramMethodBar.getValue()/100.0);
        if (nombre.equals("butDifferenceClassMethodBar"))
            setVal(butDifferenceClassMethodText,
                    butDifferenceClassMethodBar.getValue()/100.0);
        if (nombre.equals("butDifferenceBlockBar"))
            setVal(butDifferenceBlockText,
                    butDifferenceBlockBar.getValue()/100.0);
        if (nombre.equals("butLimitProgramBar"))
            setVal(butLimitProgramText,
                    butLimitProgramBar.getValue()/100.0);
        if (nombre.equals("butLimitClassBar"))
            setVal(butLimitClassText,
                    butLimitClassBar.getValue()/100.0);
        if (nombre.equals("butLimitMethodBar"))
            setVal(butLimitMethodText,
                    butLimitMethodBar.getValue()/100.0);        
        if (nombre.equals("butClassThresholdMaxBar")) {
            int val = butClassThresholdMaxBar.getValue();
            if (val == 10001)
                val = 10000;
            setVal(butClassThresholdMaxText,
                    val/100.0);
        }
        managerCambio();
    }

}
