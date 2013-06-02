/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.languages;

import com.jsimil.languages.NoSuchLanguageException;

/**
 * Clase de idiomas.
 * @author elezeta
 */
final public class Language {
    
    /**
     * Lista de idiomas disponibles.
     */
    private static String[] langs = {"es","est","en"};

    /**
     * Lista de nombres de idiomas disponibles.
     */
    private static String[] langn = {"Espa~ol","Español con tildes","English"};
    
    /**
     * Códigos de frases del idioma.
     */
    private static String[] cod = {
        "_0000_CLI_WELCOME_1",
        "_0001_CLI_WELCOME_2",
        "_0002_CLI_WELCOME_3",
        "_0003_CLI_UNKNOWN_ARGUMENT",
        "_0004_CLI_HELP_1",
        "_0005_CLI_HELP_2",
        "_0006_CLI_HELP_3",
        "_0007_CLI_HELP_4",
        "_0008_CLI_HELP_5",
        "_0009_CLI_HELP_6",
        "_0010_CLI_HELP_7",
        "_0011_CLI_HELP_8",
        "_0012_CLI_HELP_9",
        "_0013_CLI_HELP_10",
        "_0014_CLI_HELP_11",
        "_0015_CLI_HELP_12",
        "_0016_CLI_HELP_13",
        "_0017_CLI_HELP_14",
        "_0018_CLI_HELP_15",
        "_0019_CLI_HELP_16",
        "_0020_CLI_HELP_17",
        "_0021_CLI_HELP_18",
        "_0022_CLI_HELP_19",
        "_0023_CLI_HELP_20",
        "_0024_CLI_HELP_21",
        "_0025_CLI_HELP_22",
        "_0026_CLI_HELP_23",
        "_0027_CLI_HELP_24",
        "_0028_CLI_HELP_25",
        "_0029_CLI_HELP_26",
        "_0030_CLI_HELP_27",
        "_0031_CLI_HELP_28",
        "_0032_CLI_HELP_29",
        "_0033_CLI_HELP_30",
        "_0034_CLI_HELP_31",
        "_0035_CLI_HELP_29",
        "_0036_CLI_HELP_30",
        "_0037_CLI_ERROR_REPEATED_OUTPUT",
        "_0038_CLI_ERROR_REPEATED_ARGUMENT",
        "_0039_CLI_ERROR_NO_SUCH_LANGUAGE",
        "_0040_CLI_ERROR_ARGUMENT_HYPHEN",
        "_0041_CLI_ERROR_UNKNOWN_ARGUMENT_WITHOUT_PARAMETER",
        "_0042_CLI_ERROR_MULTIPLE_CONFIG_INPUT",
        "_0043_CLI_ERROR_MULTIPLE_PROFILE_INPUT_1",
        "_0044_CLI_ERROR_MULTIPLE_BATTERY_INPUT",
        "_0045_CLI_HELP_34",
        "_0046_CLI_HELP_35",
        "_0047_CLI_ERROR_MULTIPLE_PROFILE_INPUT_2",
        "_0048_CLI_ERROR_MULTIPLE_PROFILE_INPUT_3",
        "_0049_CLI_ERROR_MULTIPLE_PROFILE_INPUT_4",
        "_0050_CLI_ERROR_WRITING_NEW_CONFIG",
        "_0051_CLI_ERROR_WRITING_NEW_PROFILE",
        "_0052_CLI_ERROR_WRITING_DEFAULT_PROFILE",
        "_0053_CLI_ERROR_WRITING_DEFAULT_REFLEXIVE_PROFILE",
        "_0054_CLI_ERROR_WRITING_NEW_BATTERY",       
        "_0055_CLI_WRITING_NEW_OR_DEFAULT_FILES",
        "_0056_CLI_WRITTEN_NEW_CONFIG",
        "_0057_CLI_WRITTEN_NEW_PROFILE",
        "_0058_CLI_WRITTEN_DEFAULT_PROFILE",
        "_0059_CLI_WRITTEN_DEFAULT_REFLEXIVE_PROFILE",
        "_0060_CLI_WRITTEN_NEW_BATTERY",       
        "_0061_CLI_HELP_36",
        "_0062_CLI_HELP_37",
        "_0063_CLI_LANGUAGE",
        "_0064_CLI_DEFAULT_LANGUAGE",
        "_0065_CLI_AVAILABLE_LANGUAGES",
        "_0066_CLI_CANT_AUTOCONFIG",
        "_0067_CLI_AUTOCONFIG_OK",
        "_0068_CLI_CONFIGLOAD_OK",
        "_0069_CLI_CONFIGLOAD_NOT_OK_PATH",
        "_0070_CLI_CONFIGLOAD_NOT_OK_FORMAT",
        "_0071_CLI_CONFIGLOAD_TRYING_AUTOCONFIG",
        "_0072_CLI_PROFILELOAD_DEFAULT",
        "_0073_CLI_PROFILELOAD_DEFAULTREFLEXIVE",
        "_0074_CLI_PROFILELOAD_OK",
        "_0075_CLI_PROFILELOAD_NOT_OK_PATH",
        "_0076_CLI_PROFILELOAD_NOT_OK_FORMAT",
        "_0077_CLI_BATTERYPATH",
        "_0078_CLI_BATTERYLOAD_OK",
        "_0079_CLI_BATTERYLOADLOAD_NOT_OK_PATH",
        "_0080_CLI_BATTERYLOAD_NOT_OK_FORMAT",
        "_0081_CLI_CONFIG_VALIDATES",
        "_0082_CLI_BATTERY_VALIDATES",
        "_0083_CLI_PROFILE_VALIDATES",
        "_0084_CLI_CONFIG_NOT_VALIDATES",
        "_0085_CLI_BATTERY_NOT_VALIDATES",
        "_0086_CLI_PROFILE_NOT_VALIDATES",
        "_0087_CLI_CONFIG_ERR_COMPILER_PATH",
        "_0088_CLI_CONFIG_ERR_COMPILER_EXE",
        "_0089_CLI_CONFIG_ERR_DISASSEMBLER_PATH",
        "_0090_CLI_CONFIG_ERR_DISASSEMBLER_EXE",
        "_0091_CLI_PROFILE_ERR_ZERO_OR_ONE",
        "_0092_CLI_PROFILE_ERR_BETWEEN_ZERO_AND_ONE",
        "_0093_CLI_PROFILE_ERR_NOT_VALID_MIN_MAX",
        "_0094_CLI_PROFILE_ERR_GREATER_ZERO",
        "_0095_CLI_BATTERY_ERR_NOT_ACCESIBLE_PATH",
        "_0096_CLI_BATTERY_ERR_NO_PROGRAMS_TO_COMPARE",
        "_0097_CLI_LOAD_BATTERY",
        "_0098_CLI_BATTERY_ERR_READING",
        "_0099_CLI_BATTERY_LOADED",
        "_0100_CLI_NOTHING_ELSE_TO_DO",        
        "_0101_CLI_PROCESS",        
        "_0102_CLI_PROCESS_DONE",        
        "_0103_CLI_PROCESS_ERROR",        
        "_0104_CLI_NO_PROFILE",        
        "_0105_CLI_HELP_38",
        "_0106_CLI_NOT_FORCED",
        "_0107_CLI_WELCOME_4",
        "_0108_CLI_EVENT_DEBUG",
        "_0109_CLI_EVENT_ERROR_ENTRADA_SALIDA_DESENSAMBLADOR",
        "_0110_CLI_EVENT_ERROR_CLASS_VACIO_O_NO_VALIDO",
        "_0111_CLI_EVENT_ERROR_LEYENDO_FICHERO_JAVA",
        "_0112_CLI_EVENT_ERROR_LEYENDO_FICHERO_JAVAP",
        "_0113_CLI_EVENT_ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA",
        "_0114_CLI_EVENT_ERROR_COPIANDO_FICHERO_CLASS",
        "_0115_CLI_EVENT_ERROR_COPIANDO_FICHERO_JAVA",
        "_0116_CLI_EVENT_INTERRUPCION_COMPILANDO",
        "_0117_CLI_EVENT_EXPLORADO_APARTADO_Y_COMPILADO",
        "_0118_CLI_EVENT_ERROR_COMPILANDO",
        "_0119_CLI_EVENT_ERROR_DESENSAMBLANDO",
        "_0120_CLI_EVENT_BATERIA_CARGADA",
        "_0121_CLI_EVENT_INTERRUPCION_DESENSAMBLANDO",
        "_0122_CLI_EVENT_FALTA_CODIGO_FUENTE",
        "_0123_CLI_EVENT_NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL",
        "_0124_CLI_EVENT_DESENSAMBLADO_Y_CARGADO",
        "_0125_CLI_EVENT_ENCONTRADA_CLASE_FUENTE",
        "_0126_CLI_EVENT_ENCONTRADA_CLASE_COMPILADA",
        "_0127_CLI_EVENT_DESENSAMBLADO",
        "_0128_CLI_EVENT_CARGADO",
        "_0129_CLI_EVENT_COMPILADO",
        "_0130_CLI_EVENT_EMPAREJADAS_CLASES",
        "_0131_CLI_EVENT_EMPAREJADOS_METODOS",
        "_0132_CLI_EVENT_EMPAREJADOS_PROGRAMAS",
        "_0133_CLI_CONFIGSAVE_OK",
        "_0134_CLI_CONFIGSAVE_NOT_OK_PATH",
        "_0135_CLI_CONFIGSAVE_NOT_OK_ERR",
        "_0136_CLI_PROFILESAVE_OK",
        "_0137_CLI_PROFILESAVE_NOT_OK_PATH",
        "_0138_CLI_PROFILESAVE_NOT_OK_ERR",
        "_0139_CLI_BATTERYSAVE_OK",
        "_0140_CLI_BATTERYSAVE_NOT_OK_PATH",
        "_0141_CLI_BATTERYSAVE_NOT_OK_ERR",
        "_0142_CLI_BATTERYDUMP_OK",
        "_0143_CLI_BATTERYDUMP_NOT_OK_PATH",
        "_0144_CLI_BATTERYDUMP_NOT_OK_ERR",
        "_0145_CLI_HELP_39",
        "_0146_CLI_HELP_40",
        "_0147_CLI_ERROR_MULTIPLE_ENABLED_1", 
        "_0148_CLI_ERROR_MULTIPLE_ENABLED_2",
        "_0149_CLI_ERROR_MULTIPLE_ENABLED_3",
        "_0150_CLI_ERROR_MULTIPLE_ENABLED_4",
        "_0151_CLI_ALL_PROGRAMS_ENABLED",
        "_0152_CLI_ALL_PROGRAMS_DISABLED",
        "_0153_CLI_PROGRAM_ENABLED",
        "_0154_CLI_PROGRAM_DISABLED",
        "_0155_CLI_PROGRAM_NOT_FOUND",
        "_0156_CLI_NO_PROGRAMS_ENABLED_NOR_DISABLED",
        "_0157_CLI_PROGRAM_LIST",
        "_0158_CLI_ENABLED_PROGRAM",
        "_0159_CLI_DISABLED_PROGRAM",
        "_0160_CLI_PROGRAM_REPEATED",
        "_0161_CLI_NO_RESULTS",
        "_0162_CLI_RESULTS",
        "_0163_CLI_EVENT_ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES",
        "_0164_CLI_EVENT_ERROR_COPIANDO_CONTENIDOS",
        "_0165_CLI_EXPORT_BHTML_OK",
        "_0166_CLI_EXPORT_BHTML_ERROR",
        "_0167_CLI_EXPORT_TXT_OK",
        "_0168_CLI_EXPORT_TXT_ERROR",
        "_0169_CLI_EXPORT_XML_OK",
        "_0170_CLI_EXPORT_XML_ERROR",
        "_0171_CLI_EXPORT_RESUME_OK",
        "_0172_CLI_EXPORT_RESUME_ERROR",
        "_0173_CLI_EXPORT_RESUME_EXISTS",
        "_0174_CLI_EXPORT_TXT_EXISTS",
        "_0175_CLI_EXPORT_BHTML_EXISTS",
        "_0176_CLI_EXPORT_XML_EXISTS",
        "_0177_CLI_EXPORT_RXML_OK",
        "_0178_CLI_EXPORT_RXML_ERROR",
        "_0179_CLI_EXPORT_RXML_EXISTS",
        "_0180_CLI_EXPORT_BHTML_START",
        "_0181_CLI_EXPORT_TXT_START",
        "_0182_CLI_EXPORT_XML_START",
        "_0183_CLI_EXPORT_RESUME_START",
        "_0184_CLI_EXPORT_RXML_START",
        "_0185_CLI_HELP_41",
        "_0186_CLI_HELP_42",
        "_0187_CLI_EXPORT_RHTML_START",
        "_0188_CLI_EXPORT_RHTML_OK",
        "_0189_CLI_EXPORT_RHTML_EXISTS",
        "_0190_CLI_EXPORT_RHTML_ERROR",
        "_0191_CLI_EXPORT_RRHTML_START",
        "_0192_CLI_EXPORT_RRHTML_OK",
        "_0193_CLI_EXPORT_RRHTML_EXISTS",
        "_0194_CLI_EXPORT_RRHTML_ERROR",
        "_0195_CLI_EXPORT_BHTML_EXISTS_2",
        "_0196_CLI_EXPORT_RHTML_EXISTS_2",
        "_0197_CLI_EXPORT_RRHTML_EXISTS_2",
        "_0198_HTML_TITLE",
        "_0199_HTML_BANNER",
        "_0200_HTML_BACK",
        "_0201_HTML_BACK_TO_MAIN",
        "_0202_HTML_BANNER_2",
        "_0203_HTML_BANNER_FOOTER",
        "_0204_HTML_PROFILE_MINMAX",
        "_0205_HTML_PROFILE_ERROR",
        "_0206_HTML_PROFILE_REFLEXIVE_YES",
        "_0207_HTML_PROFILE_REFLEXIVE_NO",
        "_0208_HTML_PROFILE_DIFFERENCE_YES",
        "_0209_HTML_PROFILE_DIFFERENCE_NO",
        "_0210_HTML_PROFILE_PROG_OPTIMISM",
        "_0211_HTML_PROFILE_CLASS_OPTIMISM",
        "_0212_HTML_PROFILE_METHOD_OPTIMISM",          
        "_0213_HTML_PROFILE_INFORMATION",          
        "_0214_HTML_PROFILE_LIMIT",          
        "_0215_HTML_MATCHED_PROGRAMS",          
        "_0216_HTML_SIMILARITY",          
        "_0217_HTML_PROFILE_MINMAX_BLUE",          
        "_0218_HTML_RESULTS",          
        "_0219_HTML_SIMILARITY_RANK",          
        "_0220_HTML_COMPARED_PROGRAMS",          
        "_0221_HTML_NAME",          
        "_0222_HTML_SIZE",          
        "_0223_HTML_MATCHED_SUBELEMENTS",   
        "_0224_HTML_FOUND_SIMILARITY",          
        "_0225_HTML_NAME_SIZE",          
        "_0226_HTML_TYPE",
        "_0227_HTML_TYPE_BLOCK",
        "_0228_HTML_TYPE_METHOD",
        "_0229_HTML_TYPE_CLASS",        
        "_0230_HTML_COMPARED_ELEMENTS",          
        "_0231_HTML_PARENT_PROGRAM",          
        "_0232_HTML_PARENT_PROGRAM",  
        "_0233_HTML_CODE",  
        "_0234_HTML_CODE2",  
        "_0235_HTML_SHOWN_ELEMENTS",          
        "_0236_HTML_SOURCE_CODE",
        "_0237_HTML_BYTECODE",
        "_0238_HTML_NOT_AVAILABLE",
        "_0239_CLI_HELP_43",
        "_0240_CLI_EXPORT_RRHTML_START",
        "_0241_CLI_EXPORT_RRHTML_OK",
        "_0242_CLI_EXPORT_RRHTML_EXISTS",
        "_0243_CLI_EXPORT_RRHTML_ERROR",
        "_0244_CLI_EXPORT_RRHTML_EXISTS_2",
        "_0245_CLI_HELP_44",
        "_0246_CLI_CANT_DUMP_BATTERY_IF_NOT_LOADED",
        "_0247_CLI_ONLY_A_PROGRAM",
        "_0248_CLI_EVENT_ERROR_ABORTANDO_POR_ERROR",
        "_0249_CLI_HELP_45",
        "_0250_CLI_LIMITE_NO_VALIDO",
        "_0251_HTML_PROFILE_METHOD_MULTIMATCH_YES",          
        "_0252_HTML_PROFILE_METHOD_MULTIMATCH_NO",          
        "_0253_HTML_PROFILE_CLASS_MULTIMATCH_YES",          
        "_0254_HTML_PROFILE_CLASS_MULTIMATCH_NO",          
        "_0255_HTML_PROFILE_CLASS_SAMEMATCH_YES",          
        "_0256_HTML_PROFILE_CLASS_SAMEMATCH_NO",          
        "_0257_HTML_PROFILE_METHOD_SAMEMATCH_YES",          
        "_0258_HTML_PROFILE_METHOD_SAMEMATCH_NO",          
        "_0259_HTML_PROFILE_METHOD_CLASS_SAMEMATCH_YES",          
        "_0260_HTML_PROFILE_METHOD_CLASS_SAMEMATCH_NO",          
        "_0261_HTML_PROFILE_PROG_DIF",          
        "_0262_HTML_ELEMENTO_NO_EMPAREJADO",
        "_0263_CLI_EVENT_ERROR_COMPILANDO_MSG",
        "_0264_CLI_HELP_46",
        "_0265_HTML_PAQUETES",
        "_0266_HTML_PAQUETES_PROGRAMA",                   
        "_0267_HTML_PAQUETES_NOMBRE",                   
        "_0268_HTML_PAQUETES_SIN_NOMBRE",                   
        "_0269_HTML_PACK",  
        "_0270_HTML_MATCHED_PROGRAMS_I",          
        "_0271_HTML_MATCHED_PROGRAMS_I_INSTRUCTIONS",          
        "_0272_HTML_PAQUETES_MOSTRANDO",        
        "_0273_HTML_INFLUENCIA",          
        "_0274_HTML_PAQUETES_TODOS",                  
        "_0275_HTML_PORCENTAJE_SUPERELEMENTO",                  
        "_0276_HTML_PORCENTAJE_PADRE",                  
        "_0277_HTML_INSTRUCTIONS_SHORT",                  
        "_0278_HTML_LEYENDA_COLOR",                  
        "_0279_CLI_HELP_47",
        "_0280_CLI_HELP_48",
        "_0281_CLI_EXPORT_DIFF_START",
        "_0282_CLI_EXPORT_DIFF_OK",
        "_0283_CLI_EXPORT_DIFF_EXISTS",
        "_0284_CLI_EXPORT_DIFF_EXISTS_2",
        "_0285_CLI_EXPORT_DIFF_ERROR",
        "_0286_CLI_SIMIL_NO_VALIDA",
        "_0287_HTML_PROFILE_BLOCK_MULTIMATCH_YES",          
        "_0288_HTML_PROFILE_BLOCK_MULTIMATCH_NO",          
        "_0289_CLIP_HELP_1",
        "_0290_CLIP_WELCOME_4",
        "_0291_CLIP_VERSION",
        "_0292_CLIP_AUTOR",
        "_0293_CLIP_WEB",
        "_0294_CLIP_DESARROLLADO",
        "_0295_CLIP_PROYECTO",
        "_0296_CLIP_UGR",
        "_0297_CLIP_CERRAR",
        "_0298_CLIP_ACERCA",
        "_0299_CLIP_AYUDA",
        "_0300_CLIP_FICHERO",
        "_0301_CLIP_SALIR",
        "_0302_CLIP_SALIR_DESC",
        "_0303_CLIP_ACERCA_DESC",
        "_0304_CLIP_CERRAR_DESC",
        "_0305_CLIP_CARGAR",
        "_0306_CLIP_CARGAR_DESC",
        "_0307_CLIP_GUARDAR_COMO",
        "_0308_CLIP_GUARDAR_COMO_DESC",
        "_0309_CLIP_EXPORTAR_HUELLA",
        "_0310_CLIP_GUARDAR_DESC",
        "_0311_CLIP_ERROR",
        "_0312_CLIP_PERFIL_CARGA_TITULO",
        "_0313_CLIP_PERFIL_CARGA_BOTON",
        "_0314_CLIP_PERFIL_CARGA_BOTON_DESC",
        "_0315_CLIP_PERFIL_EXTENSION",        
        "_0316_CLIP_PERFIL_CARGADO",        
        "_0317_CLIP_PERFIL_CARGADO_2",
        "_0318_CLIP_PERFIL_GUARDA_TITULO",
        "_0319_CLIP_PERFIL_GUARDA_BOTON",
        "_0320_CLIP_PERFIL_GUARDA_BOTON_DESC",
        "_0321_CLIP_PERFIL_GUARGADO",        
        "_0322_CLIP_PERFIL_GUARGADO_2",        
        "_0323_CLIP_ERROR_GUARDANDO",
        "_0324_CLIP_PERFIL_NUEVO",
        "_0325_CLIP_PERFIL_NUEVO_DESC",
        "_0326_CLIP_PERFIL_DEFECTO",
        "_0327_CLIP_PERFIL_DEFECTO_DESC",
        "_0328_CLIP_PERFIL_DEFECTOREFLEX",
        "_0329_CLIP_PERFIL_DEFECTOREFLEX_DESC",
        "_0330_CLIP_PERFIL_MODIFTITULO",
        "_0331_CLIP_PERFIL_MODIFTEXTO",
        "_0332_CLIP_PERFIL_HUELLA",
        "_0333_CLIP_PERFIL_PROPIEDADES",
        "_0334_CLIP_GUARDAR",
        "_0335_CLIP_GUARDAR_DESC",
        "_0336_CLIP_PERFIL_MODIFTEXTOSAL",          
        "_0337_CLIP_ATRIB_REFLEXIVO_SI",          
        "_0338_CLIP_ATRIB_REFLEXIVO_NO",              
        "_0339_CLIP_ATRIB_REFLEXIVO_DESCRIPCION",
        "_0340_CLIP_EDITANDO",              
        "_0341_CLIP_EDITANDO_MEMORIA",              
        "_0342_CLIP_NO_MODIFICADO",              
        "_0343_CLIP_MODIFICADO",              
        "_0344_CLIP_SOBREESCRIBIR_TITULO",              
        "_0345_CLIP_SOBREESCRIBIR",   
        "_0346_CLIP_TAB_GENERAL",           
        "_0347_CLIP_PROP_VELOCIDAD",
        "_0348_CLIP_PROP_DETALLE",
        "_0349_CLIP_PROP_PRECISION",
        "_0350_CLIP_PROP_SENSIBILIDAD",
        "_0351_CLIP_PROP_ASIMILACION",
        "_0352_CLIP_PROP_ESPECIALIZACION",
        "_0353_CLIP_PROP_VELOCIDAD_DESC",
        "_0354_CLIP_PROP_DETALLE_DESC",
        "_0355_CLIP_PROP_PRECISION_DESC",
        "_0356_CLIP_PROP_SENSIBILIDAD_DESC",
        "_0357_CLIP_PROP_ASIMILACION_DESC",
        "_0358_CLIP_PROP_ESPECIALIZACION_DESC",
        "_0359_CLIP_HUELLA_EXTENSION",        
        "_0360_CLIP_Huella_GUARDA_TITULO",
        "_0361_CLIP_HUELLA_GUARDA_BOTON",
        "_0362_CLIP_HUELLA_GUARDA_BOTON_DESC",
        "_0363_CLIP_HUELLA_GUARGADO",           
        "_0364_CLIP_HUELLA_GUARGADO_2",        
        "_0365_CLIP_ERROR_GUARDANDO_HUELLA",
        "_0366_CLI_HUELLASAVE_NOT_OK_ERR",
        "_0367_CLIP_ATRIB_DIFFERENCE_NO",          
        "_0368_CLIP_ATRIB_DIFFERENCE_SI",              
        "_0369_CLIP_ATRIB_DIFFERENCE_DESCRIPCION",
        "_0370_CLIP_ATRIB_RETURNNULL",          
        "_0371_CLIP_ATRIB_RETURNNULL_DESCRIPCION",
        "_0372_CLIP_ATRIB_CLASSSAMENAME",          
        "_0373_CLIP_ATRIB_CLASSSAMENAME_DESCRIPCION",
        "_0374_CLIP_ATRIB_METHODOFCLASSSAMENAME",          
        "_0375_CLIP_ATRIB_METHODOFCLASSSAMENAME_DESCRIPCION",
        "_0376_CLIP_ATRIB_METHODSAMENAME",          
        "_0377_CLIP_ATRIB_METHODSAMENAME_DESCRIPCION",
        "_0378_CLIP_ATRIB_CLASSALLOWMULTIMATCH",          
        "_0379_CLIP_ATRIB_CLASSALOWMULTIMATCH_DESCRIPCION",
        "_0380_CLIP_ATRIB_METHODALLOWMULTIMATCH",          
        "_0381_CLIP_ATRIB_METHODALOWMULTIMATCH_DESCRIPCION",
        "_0382_CLIP_ATRIB_BLOCKALLOWMULTIMATCH",          
        "_0383_CLIP_ATRIB_BLOCKALLOWMULTIMATCH_DESCRIPCION",
        "_0384_CLIP_ATRIB_PROGMATCHMINMAX_DESCRIPCION",
        "_0385_CLIP_ATRIB_PROGMATCHERROR_DESCRIPCION",
        "_0386_CLIP_ATRIB_PROGMATCHMINMAX",
        "_0387_CLIP_ATRIB_PROGMATCHMINMAX_MIN",
        "_0388_CLIP_ATRIB_PROGMATCHMINMAX_MAX",
        "_0389_CLIP_ATRIB_PROGMATCHERROR",
        "_0390_CLIP_ATRIB_PROGMATCHERROR_ERROR",
        "_0391_CLIP_ATRIB_BLOCKWEIGHT_DESC",
        "_0392_CLIP_ATRIB_BLOCKWEIGHT_METHODSTART",
        "_0393_CLIP_ATRIB_BLOCKWEIGHT_METHODEND",
        "_0394_CLIP_ATRIB_BLOCKWEIGHT_INSTRUCTIONCOUNT",
        "_0395_CLIP_ATRIB_BLOCKWEIGHT_TAB",
        "_0396_CLIP_ATRIB_BLOCKWEIGHT_AYUDA", 
        "_0397_CLIP_ATRIB_MINIMUMINSTRUCTION_AYUDA", 
        "_0398_CLIP_ATRIB_MINIMUMINSTRUCTION",
        "_0399_CLIP_ATRIB_MINIMUMINSTRUCTION_CLASS",
        "_0400_CLIP_ATRIB_MINIMUMINSTRUCTION_METHOD",
        "_0401_CLIP_ATRIB_MINIMUMINSTRUCTION_BLOCK",
        "_0402_CLIP_ATRIB_RESTRICCIONES",
        "_0403_CLIP_ORIENTACION_TAB",
        "_0404_CLIP_OPTIMISM",
        "_0405_CLIP_CLASEYMETODOSINCLASE",
        "_0406_CLIP_METODOENCLASE",
        "_0407_CLIP_BLOQUE",
        "_0408_CLIP_THRESHOLD",
        "_0409_CLIP_PMIN",
        "_0410_CLIP_PMAX",
        "_0411_CLIP_DIFFERENCE",
        "_0412_CLIP_PROGRAMA",
        "_0413_CLIP_CLASE",
        "_0414_CLIP_LIMIT",
        "_0415_CLIP_METODO",
        "_0416_CLIP_ACCEPTABLE",
        "_0417_CLIP_MINIMUM",
        "_0418_CLIP_OPTIMISM_HELP",
        "_0419_CLIP_THRESHOLD_HELP",
        "_0420_CLIP_DIFFERENCE_HELP",
        "_0421_CLIP_LIMIT_HELP",
        "_0422_CLIP_ACCEPTABLE_HELP",
        "_0423_CLIP_MINIMUM_HELP", 
        "_0424_CLIP_UMBRALES_TAB",
        "_0425_CLIP_EMPAREJAMIENTOS_TAB",
        "_0426_CLIP_METODOSINCLASE",
        "_0427_CLIP_HUELLA_HELP",
        "_0428_CLIP_ATRIBS_HELP",
        "_0429_CLIP_ENTRA",
        "_0430_CLI_HELP_49",
    };
            
    /**
     * Código del idioma.
     */
    private String s;
    
    /**
     * Nombre del idioma.
     */
    private String n;
    
    /**
     * Contenido del idioma.
     */
    private String[] lc;
    
    /**
     * Constructor
     * @param lang Lenguaje a inicializar.
     * @.post Lenguaje inicializado.
     * @exception NoSuchLanguageException El lenguaje indicado no existe.
     */
    public Language(String lang) throws NoSuchLanguageException {
        int i;
        int size;
        s = lang;
        if (1 == 1);
        String rn = System.getProperty("line.separator");
        size = cod.length;
        lc = new String[size];
        for (i = 0;i < size;i++) {
            n = "@@##UNDEFINED##@@";
            lc[i] = "@@##UNDEFINED##@@";
        }
        if (lang.equals("es") || lang.equals("est")) {
            n = "Espa~ol";
            if (lang.equals("est")) {
                n = "Español con tildes";
            }            
            // "_0000_CLI_WELCOME_1"
            lc[0] = "JSimil $1 $2 ($3).";
            // "_0001_CLI_WELCOME_2"
            lc[1] = "Detector de Similitudes en Programas Escritos en Java.";
            // "_0002_CLI_WELCOME_3"
            lc[2] = "Autor: $1.";
            // "_0003_CLI_ERROR_UNKNOWN_ARGUMENT"
            lc[3] = "Argumento desconocido: $1.";
            //"_0004_CLI_HELP_1"
            lc[4] = "Uso:   java -Xms128M -Xmx1618M -jar $1 <argumentos>";
            //"_0005_CLI_HELP_2"
            lc[5] = "Listado de argumentos:";
            //"_0006_CLI_HELP_3"
            lc[6] = " -h/--help                Muestra esta ayuda.";
            //"_0007_CLI_HELP_4"
            lc[7] = " --langlist               Lista los códigos de idiomas disponibles.";
            //"_0008_CLI_HELP_5"
            lc[8] = " -l/--lang <codigo>       Activa el idioma indicado.";
            //"_0009_CLI_HELP_6"
            lc[9] = " -v/--verbose             Modo verboso.";
            //"_0010_CLI_HELP_7"
           lc[10] = " -n/--dont-process        No realizar procesamiento, sólo carga y guardado.";
            //"_0011_CLI_HELP_8"
           lc[11] = " --new-profile-to <fich>  Escribir un perfil vacío al fichero indicado.";
            //"_0012_CLI_HELP_9"
           lc[12] = " --new-config-to <fich>   Escribir una configuración vacía al fichero indicado.";
            //"_0013_CLI_HELP_10"
           lc[13] = " --new-battery-to <fich>  Escribir una batería vacía al fichero indicado.";
            //"_0014_CLI_HELP_11"
           lc[14] = " --def-profile-to <fich>  Escribir el perfil por defecto al fichero indicado.";
            //"_0015_CLI_HELP_12"
           lc[15] = " -b/--battery-load <fich> Cargar una batería desde el fichero indicado.";
            //"_0016_CLI_HELP_13"
           lc[16] = " --battery-save <fich>    Guardar la información de batería al fichero indicado.";
            //"_0017_CLI_HELP_14"
           lc[17] = " -g/--battery-dump <fich> Guardar la batería completa al fichero indicado.";
            //"_0018_CLI_HELP_15"
           lc[18] = " -t/--battery-path <ruta> Crea una batería a partir de la ruta indicada.";
            //"_0019_CLI_HELP_16"
           lc[19] = " --dont-autoconfig        No autoconfigurar si hay error.";
            //"_0020_CLI_HELP_17"
           lc[20] = " -p/--profile-load <fich> Cargar un perfil desde el fichero indicado.";
            //"_0021_CLI_HELP_18"
           lc[21] = " --profile-save <fich>    Guardar el perfil al fichero indicado.";
            //"_0022_CLI_HELP_19"
           lc[22] = " -d/--profile-def         Usar el perfil por defecto.";
            //"_0023_CLI_HELP_20"
           lc[23] = " -a/--autoconfig          Generar una configuración automática.";
            //"_0024_CLI_HELP_21"
           lc[24] = " -c/--config-load <fich>  Cargar una configuración desde el fichero indicado.";
            //"_0025_CLI_HELP_22"
           lc[25] = " --config-save <fich>     Guardar la configuración al fichero indicado.";
            //"_0026_CLI_HELP_23"
           lc[26] = " -e/--export-html <dir>   Exportar informe de resultados en HTML al directorio"+rn+
                    "                            indicado.";
            //"_0027_CLI_HELP_24"
           lc[27] = " --disable-only <lista>   Activar todos los programas de la batería salvo los"+rn+
                    "                            indicados para ser procesados (nombres separados por"+rn+
                    "                            comas).";
            //"_0028_CLI_HELP_25"
           lc[28] = " -s/--export-txt <fich>   Exportar informe de resultados en TXT al fichero"+rn+
                    "                            indicado.";
            //"_0029_CLI_HELP_26"
           lc[29] = " --enable-only <lista>    Activar los programas de la batería indicados para ser"+rn+
                    "                            procesados (nombres separados por comas).";
            //"_0030_CLI_HELP_27",
           lc[30] = " -o/--output-standard     Mostrar por la salida estandar los resultados"+rn+
                    "                            completos obtenidos.";
            //"_0031_CLI_HELP_28"
           lc[31] = " -q/--enable-all          Activar todos los programas de la batería indicados"+rn+
                    "                            para ser procesados.";
            //"_0032_CLI_HELP_29",
           lc[32] = " -r/--resume-standard     Mostrar por la salida estandar un resumen de los"+rn+
                    "                            resultados obtenidos.";
            //"_0033_CLI_HELP_30",
           lc[33] = " -f/--force-overwrite     Forzar la sobreescritura de los ficheros de salida.";
            //"_0034_CLI_HELP_31",
           lc[34] = "Ten en cuenta que las cargas se realizan antes del procesamiento (si lo hubiera)"+rn+
           "y los guardados se realizan después del procesamiento (si lo hubiera). En"+rn+
           "cualquier caso, cualquier error cancela todos los guardados.";
            //"_0035_CLI_HELP_32",
           lc[35] = " -j/--export-resume <fic> Exportar resumen de resultados en TXT al fichero"+rn+
                    "                            indicado.";
            //"_0036_CLI_HELP_33", //--output-profile
           lc[36] = " -i/--output-profile      Incluir información del perfil en las salidas.";
            //"_0037_CLI_ERROR_REPEATED_OUTPUT"
           lc[37] = "Objetivo de argumento de escritura repetido: $1";
            //"_0038_CLI_ERROR_REPEATED_ARGUMENT",
           lc[38] = "Argumento repetido: $1";
            //"_0039_CLI_ERROR_NO_SUCH_LANGUAGE",
           lc[39] = "No existe el idioma: $1";
            //"_0040_CLI_ERROR_ARGUMENT_HYPHEN",
           lc[40] = "No puede comenzar por guión parámetro de argumento: $1";
            // "_0041_CLI_ERROR_UNKNOWN_ARGUMENT_WITHOUT_PARAMETER"
           lc[41] = "Argumento desconocido: $1 (sin parámetro).";
            //"_0042_CLI_ERROR_MULTIPLE_CONFIG_INPUT",
           lc[42] = "Múltiples entradas para configuración: autoconfigurar y carga desde fichero.";
            //"_0043_CLI_ERROR_MULTIPLE_PROFILE_INPUT_1",
           lc[43] = "Múltiples entradas para perfil: por defecto y carga desde fichero.";
            //"_0044_CLI_ERROR_MULTIPLE_BATTERY_INPUT",
           lc[44] = "Múltiples entradas para batería: por ruta y carga desde fichero.";
            //"_0045_CLI_HELP_34"
           lc[45] = " --def-rprofile-to <fich> Escribir perfil reflex. por defecto al fichero dado.";
            //"_0046_CLI_HELP_35"
           lc[46] = " --rprofile-def           Usar el perfil reflexivo por defecto.";
            //"_0047_CLI_ERROR_MULTIPLE_PROFILE_INPUT_2",
           lc[47] = "Múltiples entradas para perfil: por defecto, reflexivo por defecto y fichero.";
            //"_0048_CLI_ERROR_MULTIPLE_PROFILE_INPUT_3",
           lc[48] = "Múltiples entradas para perfil: reflexivo por defecto y carga desde fichero.";
            //"_0049_CLI_ERROR_MULTIPLE_PROFILE_INPUT_4",
           lc[49] = "Múltiples entradas para perfil: reflexivo por defecto y por defecto.";
            //"_0050_CLI_ERROR_WRITING_NEW_CONFIG",
           lc[50] = "Error escribiendo nueva configuración vacía en: $1.";
            //"_0051_CLI_ERROR_WRITING_NEW_PROFILE",
           lc[51] = "Error escribiendo nuevo perfil vacío en: $1.";
            //"_0052_CLI_ERROR_WRITING_DEFAULT_PROFILE",
           lc[52] = "Error escribiendo perfil por defecto en: $1.";
            //"_0053_CLI_ERROR_WRITING_DEFAULT_REFLEXIVE_PROFILE",
           lc[53] = "Error escribiendo perfil reflexivo por defecto en: $1.";
            //"_0054_CLI_ERROR_WRITING_NEW_BATTERY",
           lc[54] = "Error escribiendo nueva batería en: $1.";
            //"_0055_CLI_WRITING_NEW_OR_DEFAULT_FILES",
           lc[55] = "Escribiendo los ficheros vacíos o por defecto solicitados.";
            //"_0056_CLI_WRITTEN_NEW_CONFIG",
           lc[56] = "Escrita configuración vacía en: $1.";
            //"_0057_CLI_WRITTEN_NEW_PROFILE",
           lc[57] = "Escrito perfil vacío en: $1.";
            //"_0058_CLI_WRITTEN_DEFAULT_PROFILE",
           lc[58] = "Escrito perfil por defecto en: $1.";
            //"_0059_CLI_WRITTEN_DEFAULT_REFLEXIVE_PROFILE",
           lc[59] = "Escrito perfil reflexivo por defecto en: $1.";
            //"_0060_CLI_WRITTEN_NEW_BATTERY",       
           lc[60] = "Escrita batería vacía en: $1.";
            //"_0061_CLI_HELP_36",
           lc[61] = "Ejemplo de ejecución:";
            //"_0062_CLI_HELP_37",
           lc[62] = "Procesa la batería alojada en dir/ con perfil por defecto y autoconfiguración.";
            //"_0063_CLI_LANGUAGE",
           lc[63] = "  Código: $1 - Idioma: $2.";
            //"_0064_CLI_DEFAULT_LANGUAGE",
           lc[64] = "  Código: $1 - Idioma: $2 (por defecto).";
            //"_0065_CLI_AVAILABLE_LANGUAGES",
           lc[65] = "Idiomas disponibles:";
            //"_0066_CLI_CANT_AUTOCONFIG",
           lc[66] = "No se puede autoconfigurar. Utilice una configuración manual.";
            //"_0067_CLI_AUTOCONFIG_OK",
           lc[67] = "Autoconfiguración realizada con éxito.";
            //"_0068_CLI_CONFIGLOAD_OK",
           lc[68] = "Configuración cargada con éxito desde el fichero: $1.";
            //"_0069_CLI_CONFIGLOAD_NOT_OK_PATH",
           lc[69] = "No se pudo cargar configuración desde el fichero: $1 (ruta no accesible).";
            //"_0070_CLI_CONFIGLOAD_NOT_OK_FORMAT",
           lc[70] = "No se pudo cargar configuración desde el fichero: $1 (formato no reconocido).";
            //"_0071_CLI_CONFIGLOAD_TRYING_AUTOCONFIG",
           lc[71] = "Intentando autoconfiguración.";
            //"_0072_CLI_PROFILELOAD_DEFAULT",
           lc[72] = "Perfil por defecto cargado.";
            //"_0073_CLI_PROFILELOAD_DEFAULTREFLEXIVE",
           lc[73] = "Perfil reflexivo por defecto cargado.";
            //"_0074_CLI_PROFILELOAD_OK",
           lc[74] = "Perfil cargado con éxito desde el fichero: $1.";
            //"_0075_CLI_PROFILELOAD_NOT_OK_PATH",
           lc[75] = "No se pudo cargar perfil desde el fichero: $1 (ruta no accesible).";
            //"_0076_CLI_PROFILELOAD_NOT_OK_FORMAT",
           lc[76] = "No se pudo cargar perfil desde el fichero: $1 (formato no reconocido).";
            //"_0077_CLI_BATTERYPATH",
           lc[77] = "Batería asignada por ruta: $1.";
            //"_0078_CLI_BATTERYLOAD_OK",
           lc[78] = "Batería cargada con éxito desde el fichero: $1.";
            //"_0079_CLI_BATTERYLOADLOAD_NOT_OK_PATH",
           lc[79] = "No se pudo cargar batería desde el fichero: $1 (ruta no accesible).";
            //"_0080_CLI_BATTERYLOAD_NOT_OK_FORMAT",
           lc[80] = "No se pudo cargar batería desde el fichero: $1 (formato no reconocido).";
            //"_0081_CLI_CONFIG_VALIDATES",
           lc[81] = "La configuración es VALIDA.";
            //"_0082_CLI_BATTERY_VALIDATES",
           lc[82] = "La batería es VALIDA.";
            //"_0083_CLI_PROFILE_VALIDATES",
           lc[83] = "El perfil es VALIDO.";
            //"_0084_CLI_CONFIG_NOT_VALIDATES",
           lc[84] = "La configuración NO es VALIDA.";
            //"_0085_CLI_BATTERY_NOT_VALIDATES",
           lc[85] = "La batería NO es VALIDA.";
            //"_0086_CLI_PROFILE_NOT_VALIDATES",
           lc[86] = "El perfil NO es VALIDO.";
            //"_0087_CLI_CONFIG_ERR_COMPILER_PATH",
           lc[87] = "La ruta del compilador no es accesible.";
            //"_0088_CLI_CONFIG_ERR_COMPILER_EXE",
           lc[88] = "El compilador no se puede ejecutar.";
            //"_0089_CLI_CONFIG_ERR_DISASSEMBLER_PATH",
           lc[89] = "La ruta del desensamblador no es accesible.";
            //"_0090_CLI_CONFIG_ERR_DISASSEMBLER_EXE",
           lc[90] = "El desensamblador no se puede ejecutar.";
            //"_0091_CLI_PROFILE_ERR_ZERO_OR_ONE",
           lc[91] = "Se esperaba valor 0 o 1 para el parámetro: $1.";
            //"_0092_CLI_PROFILE_ERR_BETWEEN_ZERO_AND_ONE",
           lc[92] = "Se esperaba valor entre 0 y 1 para el parámetro: $1.";
            //"_0093_CLI_PROFILE_ERR_NOT_VALID_MIN_MAX",
           lc[93] = "El valor del parámetro $1 debe ser mayor que el de $2.";
            //"_0094_CLI_PROFILE_ERR_GREATER_ZERO",
           lc[94] = "Se esperaba valor mayor que 0 para el parámetro: $1.";
            //"_0095_CLI_BATTERY_ERR_NOT_ACCESIBLE_PATH",
           lc[95] = "La ruta dada por la batería no es accesible.";
            //"_0096_CLI_BATTERY_ERR_NO_PROGRAMS_TO_COMPARE",
           lc[96] = "No hay programas a comparar en la batería.";
            //"_0097_CLI_LOAD_BATTERY",
           lc[97] = "Cargando batería.";
            //"_0098_CLI_BATTERY_ERR_READING",
           lc[98] = "Error leyendo los ficheros de los programas de la batería.";
            //"_0099_CLI_BATTERY_LOADED",
           lc[99] = "Batería cargada con éxito en $1s.";
            //"_0100_CLI_NOTHING_ELSE_TO_DO",
          lc[100] = "No hay nada más que hacer.";           
            //"_0101_CLI_PROCESS",        
          lc[101] = "Procesando.";           
            //"_0102_CLI_PROCESS_DONE",        
          lc[102] = "Procesamiento completo con éxito en $1s.";           
            //"_0103_CLI_PROCESS_ERROR",        
          lc[103] = "ERROR CRÍTICO DESCONOCIDO DURANTE EL PROCESO.";
            //"_0104_CLI_NO_PROFILE",        
          lc[104] = "No se ha indicado perfil.";
            //"_0105_CLI_HELP_38",
          lc[105] = " -w/--force-battery-load  Forzar carga de los programas de la batería.";
            //"_0106_CLI_NOT_FORCED",
          lc[106] = "La batería ya estaba cargada (usar -w para forzar carga).";
            //"_0107_CLI_WELCOME_4",
          lc[107] = "JSimilCLI - Interfaz de Linea de Comandos para JSimil.";
            //"_0108_CLI_EVENT_DEBUG",
          lc[108] = "[*] Debug: $1.";
            //"_0109_CLI_EVENT_ERROR_ENTRADA_SALIDA_DESENSAMBLADOR",
          lc[109] = "[*] Error entrada/salida desensamblador: $1.";
            //"_0110_CLI_EVENT_ERROR_CLASS_VACIO_O_NO_VALIDO",
          lc[110] = "[*] Class vacío o no válido: $1.";
            //"_0111_CLI_EVENT_ERROR_LEYENDO_FICHERO_JAVA",
          lc[111] = "[*] Error leyendo fichero java: $1.";
            //"_0112_CLI_EVENT_ERROR_LEYENDO_FICHERO_JAVAP",
          lc[112] = "[*] Error leyendo fichero javap: $1.";
            //"_0113_CLI_EVENT_ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA",
          lc[113] = "[*] Ningún fichero válido en programa: $1.";
            //"_0114_CLI_EVENT_ERROR_COPIANDO_FICHERO_CLASS",
          lc[114] = "[*] Error copiando fichero class: $1.";
            //"_0115_CLI_EVENT_ERROR_COPIANDO_FICHERO_JAVA",
          lc[115] = "[*] Error copiando fichero java: $1.";
            //"_0116_CLI_EVENT_INTERRUPCION_COMPILANDO",
          lc[116] = "[*] Interrupción compilando: $1.";
            //"_0117_CLI_EVENT_EXPLORADO_APARTADO_Y_COMPILADO",
          lc[117] = "[*] Explorado, apartado y compilado: $1.";
            //"_0118_CLI_EVENT_ERROR_COMPILANDO",
          lc[118] = "[*] Error compilando: $1.";
            //"_0119_CLI_EVENT_ERROR_DESENSAMBLANDO",
          lc[119] = "[*] Error desensamblando: $1.";
            //"_0120_CLI_EVENT_BATERIA_CARGADA",
          lc[120] = "[*] Batería cargada.";
            //"_0121_CLI_EVENT_INTERRUPCION_DESENSAMBLANDO",
          lc[121] = "[*] Interrupción desensamblando: $1.";
            //"_0122_CLI_EVENT_FALTA_CODIGO_FUENTE",
          lc[122] = "[*] Falta código fuente: $1.";
            //"_0123_CLI_EVENT_NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL",
          lc[123] = "[*] No se puede crear directorio temporal: $1.";
            //"_0124_CLI_EVENT_DESENSAMBLADO_Y_CARGADO",
          lc[124] = "[*] Desensamblado y cargado: $1.";
            //"_0125_CLI_EVENT_ENCONTRADA_CLASE_FUENTE",
          lc[125] = "[*] Encontrada clase fuente: $1.";
            //"_0126_CLI_EVENT_ENCONTRADA_CLASE_COMPILADA",
          lc[126] = "[*] Encontrada clase compilada: $1.";
            //"_0127_CLI_EVENT_DESENSAMBLADO",
          lc[127] = "[*] Desensamblado: $1.";
            //"_0128_CLI_EVENT_CARGADO",
          lc[128] = "[*] Cargado: $1.";
            //"_0129_CLI_EVENT_COMPILADO",
          lc[129] = "[*] Compilado: $1.";
            //"_0130_CLI_EVENT_EMPAREJADAS_CLASES",
          lc[130] = "[*] Emparejadas clases: $1.";
            //"_0131_CLI_EVENT_EMPAREJADOS_METODOS",
          lc[131] = "[*] Emparejados métodos: $1.";
            //"_0132_CLI_EVENT_EMPAREJADOS_PROGRAMAS",
          lc[132] = "[*] Emparejados programas: $1.";
            //"_0133_CLI_CONFIGSAVE_OK",
          lc[133] = "Configuración guardada con éxito al fichero: $1.";
            //"_0134_CLI_CONFIGSAVE_NOT_OK_PATH",
          lc[134] = "No se pudo guardar configuración al fichero: $1 (ruta no accesible).";
            //"_0135_CLI_CONFIGSAVE_NOT_OK_ERR",
          lc[135] = "No se pudo guardar configuración al fichero: $1 (error escribiendo).";
            //"_0136_CLI_PROFILESAVE_OK",
          lc[136] = "Perfil escrito con éxito al fichero: $1.";
            //"_0137_CLI_PROFILESAVE_NOT_OK_PATH",
          lc[137] = "No se pudo guardar perfil al fichero: $1 (ruta no accesible).";
            //"_0138_CLI_PROFILESAVE_NOT_OK_ERR",
          lc[138] = "No se pudo guardar perfil al fichero: $1 (error escribiendo).";
            //"_0139_CLI_BATTERYSAVE_OK",
          lc[139] = "Batería guardada con éxito al fichero: $1.";
            //"_0140_CLI_BATTERYSAVE_NOT_OK_PATH",
          lc[140] = "No se pudo guardar batería al fichero: $1 (ruta no accesible).";
            //"_0141_CLI_BATTERYSAVE_NOT_OK_ERR",
          lc[141] = "No se pudo guardar batería al fichero: $1 (error escribiendo).";
            //"_0142_CLI_BATTERYDUMP_OK",
          lc[142] = "Batería vaciada con éxito al fichero: $1.";
            //"_0143_CLI_BATTERYDUMP_NOT_OK_PATH",
          lc[143] = "No se pudo vaciar batería al fichero: $1 (ruta no accesible).";
            //"_0144_CLI_BATTERYDUMP_NOT_OK_ERR",
          lc[144] = "No se pudo vaciar batería al fichero: $1 (error escribiendo).";
            //"_0145_CLI_HELP_39"
          lc[145] = " -m/--export-rxml <fich>  Exportar resumen de resultados en XML al fichero"+rn+
                    "                            indicado.";
            //"_0146_CLI_HELP_40"
          lc[146] = " -x/--export-xml <fich>   Exportar informe de resultados en XML al fichero"+rn+
                    "                            indicado.";
            //"_0147_CLI_ERROR_MULTIPLE_ENABLED_1",
          lc[147] = "Múltiples activaciones de programa: todos, especificados, no especificados.";
            //"_0148_CLI_ERROR_MULTIPLE_ENABLED_2",
          lc[148] = "Múltiples activaciones de programa: todos, especificados.";
            //"_0149_CLI_ERROR_MULTIPLE_ENABLED_3",
          lc[149] = "Múltiples activaciones de programa: todos, no especificados.";
            //"_0150_CLI_ERROR_MULTIPLE_ENABLED_4",
          lc[150] = "Múltiples activaciones de programa: especificados, no especificados.";
            //"_0151_CLI_ALL_PROGRAMS_ENABLED",
          lc[151] = "Todos los programas han sido activados para el procesamiento.";
            //"_0152_CLI_ALL_PROGRAMS_DISABLED",
          lc[152] = "Todos los programas han sido desactivados para el procesamiento.";
            //"_0153_CLI_PROGRAM_ENABLED",
          lc[153] = "Programa activado para el procesamiento: $1.";
            //"_0154_CLI_PROGRAM_DISABLED",
          lc[154] = "Programa desactivado para el procesamiento: $1.";
            //"_0155_CLI_PROGRAM_NOT_FOUND",
          lc[155] = "Programa no encontrado: $1.";
            //"_0156_CLI_NO_PROGRAMS_ENABLED_NOR_DISABLED",
          lc[156] = "No se han activado ni desactivado programas para el procesamiento.";
            //"_0157_CLI_PROGRAM_LIST",
          lc[157] = "Lista de programas de la batería:";
            //"_0158_CLI_ENABLED_PROGRAM",
          lc[158] = "Programa: $1 - Estado: Activado para el procesamiento.";
            //"_0159_CLI_DISABLED_PROGRAM",
          lc[159] = "Programa: $1 - Estado: Desactivado para el procesamiento.";
            //"_0160_CLI_PROGRAM_REPEATED",
          lc[160] = "Programa repetido: $1.";
            //"_0161_CLI_NO_RESULTS",
          lc[161] = "No han sido obtenidos resultados. No se han realizado comparaciones.";
            //"_0162_CLI_RESULTS",
          lc[162] = "Resultados obtenidos:";
            //"_0163_CLI_EVENT_ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES",
          lc[163] = "[*] Error cargando programa: $1 (ficheros ilegibles).";
            //"_0164_CLI_EVENT_ERROR_COPIANDO_CONTENIDOS",
          lc[164] = "[*] Error cargando programa: $1 (no se pueden copiar contenidos).";
            //"_0165_CLI_EXPORT_BHTML_OK",
          lc[165] = "Resultados exportados como HTML en $2s al directorio: $1.";
            //"_0166_CLI_EXPORT_BHTML_ERROR",
          lc[166] = "Error exportando resultados como HTML al directorio: $1.";
            //"_0167_CLI_EXPORT_TXT_OK",
          lc[167] = "Resultados exportados como TXT en $2s al fichero: $1.";
            //"_0168_CLI_EXPORT_TXT_ERROR",
          lc[168] = "Error exportando resultados como TXT al fichero: $1.";
            //"_0169_CLI_EXPORT_XML_OK",
          lc[169] = "Resultados exportados como XML en $2s al fichero: $1.";
            //"_0170_CLI_EXPORT_XML_ERROR",
          lc[170] = "Error exportando resultados como XML al fichero: $1.";
            //"_0171_CLI_EXPORT_RESUME_OK",
          lc[171] = "Resumen exportado como TXT en $2s al fichero: $1.";
            //"_0172_CLI_EXPORT_RESUME_ERROR",
          lc[172] = "Error exportando resumen como TXT al fichero: $1.";
            //"_0173_CLI_EXPORT_RESUME_EXISTS",
          lc[173] = "Ya existe el fichero para el resumen TXT (usar -f para sobreescribir): $1.";
            //"_0174_CLI_EXPORT_TXT_EXISTS",
          lc[174] = "Ya existe el fichero para los resultados TXT (usar -f para sobreescribir): $1.";
            //"_0175_CLI_EXPORT_BHTML_EXISTS",
          lc[175] = "Ya existe el directorio para los resultados HTML (usar -f para sobreescribir): $1.";
            //"_0176_CLI_EXPORT_XML_EXISTS",
          lc[176] = "Ya existe el fichero para los resultados XML (usar -f para sobreescribir): $1.";
            //"_0177_CLI_EXPORT_RXML_OK",
          lc[177] = "Resumen de resultados exportado como XML en $2s al fichero: $1.";
            //"_0178_CLI_EXPORT_RXML_ERROR",
          lc[178] = "Error exportando resumen de resultados como XML al fichero: $1.";
            //"_0179_CLI_EXPORT_RXML_EXISTS",
          lc[179] = "Ya existe el fichero para el resumen de resultados XML (usar -f para sobreescribir): $1.";
            //"_0180_CLI_EXPORT_BHTML_START",
          lc[180] = "Exportando resultados como HTML al directorio: $1.";
            //"_0181_CLI_EXPORT_TXT_START",
          lc[181] = "Exportando resultados como TXT al fichero: $1.";
            //"_0182_CLI_EXPORT_XML_START",
          lc[182] = "Exportando resultados como XML al fichero: $1.";
            //"_0183_CLI_EXPORT_RESUME_START",
          lc[183] = "Exportando resumen de resultados como TXT al fichero: $1.";
            //"_0184_CLI_EXPORT_RXML_START",
          lc[184] = "Exportando resumen de resultados como XML al fichero: $1.";
            //"_0185_CLI_HELP_41",
          lc[185] = " --export-rhtml <dir>     Exportar resumen de resultados en HTML al directorio"+rn+
                    "                            indicado.";
            //"_0186_CLI_HELP_42",
          lc[186] = " --export-rrhtml <dir>    Exportar resumen corto de resultados en HTML al"+rn+
                    "                            directorio indicado.";
            //"_0187_CLI_EXPORT_RHTML_START",
          lc[187] = "Exportando resumen de resultados como HTML al directorio: $1.";
            //"_0188_CLI_EXPORT_RHTML_OK",
          lc[188] = "Resumen de resultados exportado como HTML en $2s al directorio: $1.";
            //"_0189_CLI_EXPORT_RHTML_EXISTS",
          lc[189] = "Ya existe el directorio para el resumen de resultados HTML (usar -f para sobreescribir): $1.";
            //"_0190_CLI_EXPORT_RHTML_ERROR",
          lc[190] = "Error exportando resumen de resultados como HTML al directorio: $1.";
            //"_0191_CLI_EXPORT_RRHTML_START",
          lc[191] = "Exportando resumen corto de resultados como HTML al directorio: $1.";
            //"_0192_CLI_EXPORT_RRHTML_OK",
          lc[192] = "Resumen corto de resultados exportado como HTML en $2s al directorio: $1.";
            //"_0193_CLI_EXPORT_RRHTML_EXISTS",
          lc[193] = "Ya existe el directorio para el resumen corto de resultados HTML (usar -f para sobreescribir): $1.";
            //"_0194_CLI_EXPORT_RRHTML_ERROR",
          lc[194] = "Error exportando resumen corto de resultados como HTML al directorio: $1.";
            //"_0195_CLI_EXPORT_BHTML_EXISTS_2",
          lc[195] = "Imposible sobreescribir el directorio para los resultados HTML (eliminar manualmente): $1.";
            //"_0196_CLI_EXPORT_RHTML_EXISTS_2",
          lc[196] = "Imposible sobreescribir el directorio para el resumen de resultados HTML (eliminar manualmente): $1.";
            //"_0197_CLI_EXPORT_RRHTML_EXISTS_2",
          lc[197] = "Imposible sobreescribir el directorio para el resumen corto de resultados HTML (eliminar manualmente): $1.";
            //"_0198_HTML_TITLE",
          lc[198] = "JSimil - Resultados.";
            //"_0199_HTML_BANNER_1",
          lc[199] = "JSimil $1 - Detector de Similitudes en Programas Escritos en Java.";
            //"_0200_HTML_BACK",
          lc[200] = "(volver atrás)";
            //"_0201_HTML_BACK_TO_MAIN",
          lc[201] = "(volver al índice)";
            //"_0202_HTML_BANNER_2",
          lc[202] = "Página de resultados.";
            //"_0203_HTML_BANNER_FOOTER",
          lc[203] = "JSimil $1 - $2 ($3).";
            //"_0204_HTML_PROFILE_MINMAX",
          lc[204] = "Búsqueda exhaustiva realizada sobre los emparejamientos con similitud: $1-$2";
            //"_0205_HTML_PROFILE_ERROR",
          lc[205] = "Error máximo admitido para dichos emparejamientos: $1.";
            //"_0206_HTML_PROFILE_REFLEXIVE_YES",
          lc[206] = "Emparejamiento reflexivo (elementos de un programa, entre sí).";
            //"_0207_HTML_PROFILE_REFLEXIVE_NO",
          lc[207] = "Emparejamiento no reflexivo (elementos de programas distintos entre sí).";
            //"_0208_HTML_PROFILE_DIFFERENCE_YES",
          lc[208] = "Búsqueda centrada en hallar diferencias.";
            //"_0209_HTML_PROFILE_DIFFERENCE_NO",
          lc[209] = "Búsqueda centrada en hallar similitudes.";
            //"_0210_HTML_PROFILE_PROG_OPTIMISM",
          lc[210] = "Optimismo para hallar similitudes en subelementos de programa: $1.";
            //"_0211_HTML_PROFILE_CLASS_OPTIMISM",
          lc[211] = "Optimismo para hallar similitudes en subelementos de clase: $1.";
            //"_0212_HTML_PROFILE_METHOD_OPTIMISM",          
          lc[212] = "Optimismo para hallar similitudes en subelementos de método: $1.";
            //"_0213_HTML_PROFILE_INFORMATION",          
          lc[213] = "Información de perfil:";
            //"_0214_HTML_PROFILE_LIMIT",          
          lc[214] = "Máximo del programa a explorar: $1.";
            //"_0215_HTML_MATCHED_PROGRAMS",          
          lc[215] = "------";
            //"_0216_HTML_SIMILARITY",          
          lc[216] = "Similitud";
            //"_0217_HTML_PROFILE_MINMAX_BLUE",          
          lc[217] = "(en azul)";
            //"_0218_HTML_RESULTS",          
          lc[218] = "Resultados:";
            //"_0219_HTML_SIMILARITY_RANK",          
          lc[219] = "Rango de similitud";
            //"_0220_HTML_COMPARED_PROGRAMS",          
          lc[220] = "Programas comparados:";
            //"_0221_HTML_NAME",          
          lc[221] = "Nombre";
            //"_0222_HTML_SIZE",          
          lc[222] = "Instrucciones";
            //"_0223_HTML_MATCHED_SUBELEMENTS",          
          lc[223] = "Subelementos emparejados:";
            //"_0224_HTML_FOUND_SIMILARITY",          
          lc[224] = "Similitud hallada:";
            //"_0225_HTML_NAME_SIZE",          
          lc[225] = "----";
            //"_0226_HTML_TYPE",
          lc[226] = "Tipo";
            //"_0227_HTML_TYPE_BLOCK",
          lc[227] = "Bloque";
            //"_0228_HTML_TYPE_METHOD",
          lc[228] = "Método";
            //"_0229_HTML_TYPE_CLASS",        
          lc[229] = "Clase";
            //"_0230_HTML_COMPARED_ELEMENTS",          
          lc[230] = "Elementos comparados:";
            //"_0231_HTML_PARENT_PROGRAM",  
          lc[231] = "% del superelemento";
            //"_0232_HTML_PARENT_PROGRAM",  
          lc[232] = "% del programa";
            //"_0233_HTML_CODE",  
          lc[233] = "---";
            //"_0234_HTML_CODE2",  
          lc[234] = "---";
            //"_0235_HTML_SHOWN_ELEMENTS",
          lc[235] = "Elementos mostrados:";          
            //"_0236_HTML_SOURCE_CODE",
          lc[236] = "Código fuente:"; 
            //"_0237_HTML_BYTECODE",
          lc[237] = "Bytecode:"; 
            //"_0238_HTML_NOT_AVAILABLE",
          lc[238] = "No disponible."; 
            //"_0239_CLI_HELP_43",
          lc[239] = "----";
            //"_0240_CLI_EXPORT_IHTML_START",
          lc[240] = "----";
            //"_0241_CLI_EXPORT_IHTML_OK",
          lc[241] = "----";
            //"_0242_CLI_EXPORT_IHTML_EXISTS",
          lc[242] = "----";
            //"_0243_CLI_EXPORT_IHTML_ERROR",
          lc[243] = "----";
            //"_0244_CLI_EXPORT_IHTML_EXISTS_2",
          lc[244] = "----";
            //"_0245_CLI_HELP_44",
          lc[245] = " -y/--mind-size           Tener en cuenta el tamaño de los programas al"+rn+
                    "                            ordenar los resultados.";
            //"_0246_CLI_CANT_DUMP_BATTERY_IF_NOT_LOADED",
          lc[246] = "No se puede exportar batería: $1 (no está cargada).";
            //"_0247_CLI_ONLY_A_PROGRAM",
          lc[247] = "No se puede comparar un sólo programa de forma no reflexiva.";
            //"_0248_CLI_EVENT_ERROR_ABORTANDO_POR_ERROR",
          lc[248] = "[*] Abortada carga de programa por error compilando o desensamblando: $1.";
            //"_0249_CLI_HELP_45",
          lc[249] = " -u/--results-limit <n>   Exportar información de los n resultados más"+rn+
                    "                            relevantes.";
            //"_0250_CLI_LIMITE_NO_VALIDO",
          lc[250] = "No es un límite válido: $1.";
            //"_0251_HTML_PROFILE_METHOD_MULTIMATCH_YES",      
          lc[251] = "Se permiten emparejamientos uno a muchos en los métodos.";
            //"_0252_HTML_PROFILE_METHOD_MULTIMATCH_NO",          
          lc[252] = "Se permiten emparejamientos uno a cero o uno en los métodos.";
            //"_0253_HTML_PROFILE_CLASS_MULTIMATCH_YES",          
          lc[253] = "Se permiten emparejamientos uno a muchos en las clases.";
            //"_0254_HTML_PROFILE_CLASS_MULTIMATCH_NO",          
          lc[254] = "Se permiten emparejamientos uno a cero o uno en las clases.";
            //"_0255_HTML_PROFILE_CLASS_SAMEMATCH_YES",          
          lc[255] = "Sólo se emparejan las clases de mismo nombre.";
            //"_0256_HTML_PROFILE_CLASS_SAMEMATCH_NO",          
          lc[256] = "Se pueden emparejar clases con nombre distinto.";
            //"_0257_HTML_PROFILE_METHOD_SAMEMATCH_YES",          
          lc[257] = "Sólo se emparejan los métodos de mismo nombre y clase.";
            //"_0258_HTML_PROFILE_METHOD_SAMEMATCH_NO",          
          lc[258] = "Se pueden emparejar métodos con nombre y clase distintos.";
            //"_0259_HTML_PROFILE_METHOD_CLASS_SAMEMATCH_YES",          
          lc[259] = "Sólo se emparejan los métodos contenidos en clases de mismo nombre.";
            //"_0260_HTML_PROFILE_METHOD_CLASS_SAMEMATCH_NO",          
          lc[260] = "No es necesario que dos métodos estén contenidos en una clase de mismo nombre para que se emparejen.";
            //"_0261_HTML_PROFILE_PROG_DIF",  
          lc[261] = "Ratio de diferencia máximo admitido para comparar programas: $1.";
            //"_0262_HTML_ELEMENTO_NO_EMPAREJADO",
          lc[262] = "Elemento no emparejado.";
            //"_0263_CLI_EVENT_ERROR_COMPILANDO_MSG",
          lc[263] = "[*] Traza: $1.";
            //"_0264_CLI_HELP_46",
          lc[264] = " --hide-errors            Ocultar errores de compilación.";
            //"_0265_HTML_PAQUETES",                   
          lc[265] = "Ver paquetes";
            //"_0266_HTML_PAQUETES_PROGRAMA",                   
          lc[266] = "Paquetes del programa $1:";
            //"_0267_HTML_PAQUETES_NOMBRE",                   
          lc[267] = "Nombre de paquete";
            //"_0268_HTML_PAQUETES_SIN_NOMBRE",                   
          lc[268] = "sin nombre";          
            //"_0269_HTML_PACK",  
          lc[269] = "($1)";
            //"_0270_HTML_MATCHED_PROGRAMS_I",          
          lc[270] = "Programas emparejados";
            //"_0271_HTML_MATCHED_PROGRAMS_I_INSTRUCTIONS",          
          lc[271] = "Instrucciones";
            //"_0272_HTML_PAQUETES_MOSTRANDO",          
          lc[272] = "Mostrando únicamente los emparejamientos del paquete $1 del programa $2.";
            //"_0273_HTML_INFLUENCIA",          
          lc[273] = "Influencia";
            //"_0274_HTML_PAQUETES_TODOS",                  
          lc[274] = "Ver los emparejamientos de todos los paquetes.";
            //"_0275_HTML_PORCENTAJE_SUPERELEMENTO",                  
          lc[275] = "% super.";
            //"_0276_HTML_PORCENTAJE_PADRE",                  
          lc[276] = "% prog.";
            //"_0277_HTML_INSTRUCTIONS_SHORT",                  
          lc[277] = "n. inst.";
            //"_0278_HTML_LEYENDA_COLOR",                  
          lc[278] = "Leyenda de color (rango de similitud)";
            //"_0279_CLI_HELP_47",
          lc[279] = " -k/--export-diff <dir>   Exportar resultados en formato diff al directorio"+rn+
                    "                            indicado.";
            //"_0280_CLI_HELP_48",
          lc[280] = " --diff-simil <real>      Usar la similitud mínima indicada como umbral de"+rn+
                    "                            diferencias en los resultados diff (0.0-100.0).";
            //"_0281_CLI_EXPORT_DIFFL_START",
          lc[281] = "Exportando resultados diff al directorio: $1.";
            //"_0282_CLI_EXPORT_DIFF_OK",
          lc[282] = "Resultados diff exportados en $2s al directorio: $1.";
            //"_0283_CLI_EXPORT_DIFF_EXISTS",
          lc[283] = "Ya existe el directorio para los resultados diff (usar -f para sobreescribir): $1.";
            //"_0284_CLI_EXPORT_DIFF_EXISTS_2",
          lc[284] = "Imposible sobreescribir el directorio para los resultados diff (eliminar manualmente): $1.";
            //"_0285_CLI_EXPORT_DIFF_ERROR",
          lc[285] = "Error exportando resultados diff al directorio: $1.";
            //"_0286_CLI_SIMIL_NO_VALIDA",
          lc[286] = "No es una similitud válida: $1.";
            //"_0287_HTML_PROFILE_BLOCK_MULTIMATCH_YES",      
          lc[287] = "Se permiten emparejamientos uno a muchos en los bloques.";
            //"_0288_HTML_PROFILE_BLOCK_MULTIMATCH_NO",          
          lc[288] = "Se permiten emparejamientos uno a cero o uno en los bloques.";
            //"_0289_CLIP_HELP_1"
          lc[289] = "Uso:   java -jar $1 <argumentos>";
            //"_0290_CLIP_WELCOME_4",
          lc[290] = "JSimilProfile - Gestor de Perfiles para JSimil.";
            //"_0291_CLIP_VERSION",
          lc[291] = "Versión:";
            //"_0292_CLIP_AUTOR",
          lc[292] = "Autor:";
            //"_0293_CLIP_WEB",
          lc[293] = "Página web:";
            //"_0294_CLIP_DESARROLLADO",
          lc[294] = "Desarrollado en:";
            //"_0295_CLIP_PROYECTO",
          lc[295] = "Proyecto fin de carrera 2007-2010";
            //"_0296_CLIP_UGR",
          lc[296] = "Universidad de Granada";
            //"_0297_CLIP_CERRAR",
          lc[297] = "Cerrar";
            //"_0298_CLIP_ACERCA",
          lc[298] = "Acerca de...";
            //"_0299_CLIP_AYUDA",
          lc[299] = "Ayuda";
            //"_0300_CLIP_FICHERO",
          lc[300] = "Fichero";
            //"_0301_CLIP_SALIR",
          lc[301] = "Salir";
            //"_0302_CLIP_SALIR_DESC",
          lc[302] = "Salir de la aplicación";
            //"_0303_CLIP_ACERCA_DESC",
          lc[303] = "Mostrar el diálogo de información de la aplicación";
            //"_0304_CLIP_CERRAR_DESC",
          lc[304] = "Cerrar el diálogo";
            //"_0305_CLIP_CARGAR",
          lc[305] = "Cargar perfil";
            //"_0306_CLIP_CARGAR_DESC",
          lc[306] = "Cargar un perfil desde un fichero";
            //"_0307_CLIP_GUARDAR_COMO",
          lc[307] = "Guardar perfil como";
            //"_0308_CLIP_GUARDAR_COMO_DESC",
          lc[308] = "Guardar perfil a un fichero indicado";
            //"_0309_CLIP_EXPORTAR_HUELLA",
          lc[309] = "Exportar huella del perfil";
            //"_0310_CLIP_GUARDAR_DESC",
          lc[310] = "Guardar imagen de la huella del perfil a un fichero";
            //"_0311_CLIP_ERROR",
          lc[311] = "Error cargando perfil";
            //"_0312_CLIP_PERFIL_CARGA_TITULO",
          lc[312] = "Escoge un fichero de perfil para cargar";
            //"_0313_CLIP_PERFIL_CARGA_BOTON",
          lc[313] = "Cargar";
            //"_0314_CLIP_PERFIL_CARGA_BOTON_DESC",
          lc[314] = "Cargar el perfil del fichero escogido";
            //"_0315_CLIP_PERFIL_EXTENSION",        
          lc[315] = "Fichero de Perfil de JSimil";
            //"_0316_CLIP_PERFIL_CARGADO",        
          lc[316] = "Perfil cargado.";
            //"_0317_CLIP_PERFIL_CARGADO_2",        
          lc[317] = "Perfil cargado";
            //"_0318_CLIP_PERFIL_GUARDA_TITULO",
          lc[318] = "Escoge un fichero para guardar el perfil";
            //"_0319_CLIP_PERFIL_GUARDA_BOTON",
          lc[319] = "Guardar";
            //"_0320_CLIP_PERFIL_GUARDA_BOTON_DESC",
          lc[320] = "Guardar el perfil al fichero indicado";
            //"_0321_CLIP_PERFIL_GUARGADO",        
          lc[321] = "Perfil guardado.";
            //"_0322_CLIP_PERFIL_GUARGADO_2",        
          lc[322] = "Perfil guardado";          
            //"_0323_CLIP_ERROR_GUARDANDO",
          lc[323] = "Error guardando perfil";
            //"_0324_CLIP_PERFIL_NUEVO",
          lc[324] = "Nuevo perfil";
            //"_0325_CLIP_PERFIL_NUEVO_DESC",
          lc[325] = "Eliminar la información del perfil actual y comenzar uno nuevo";
            //"_0326_CLIP_PERFIL_DEFECTO",
          lc[326] = "Perfil por defecto";
            //"_0327_CLIP_PERFIL_DEFECTO_DESC",
          lc[327] = "Eliminar la información del perfil actual y comenzar uno a partir del perfil por defecto";
            //"_0328_CLIP_PERFIL_DEFECTOREFLEX",
          lc[328] = "Perfil reflexivo por defecto";
            //"_0329_CLIP_PERFIL_DEFECTOREFLEX_DESC",
          lc[329] = "Eliminar la información del perfil actual y comenzar uno a partir del perfil reflexivo por defecto";
            //"_0330_CLIP_PERFIL_MODIFTITULO",
          lc[330] = "No se han guardado los cambios";
            //"_0331_CLIP_PERFIL_MODIFTEXTO",          
          lc[331] = "No se han guardado los cambios del perfil actual. Si realiza esta acción se perderán. ¿Está seguro?";
            //"_0332_CLIP_PERFIL_HUELLA",
          lc[332] = "Huella del perfil";
            //"_0333_CLIP_PERFIL_PROPIEDADES",
          lc[333] = "Propiedades del perfil";
            //"_0334_CLIP_GUARDAR",
          lc[334] = "Guardar perfil";
            //"_0335_CLIP_GUARDAR_DESC",
          lc[335] = "Guardar perfil al fichero actual";
            //"_0336_CLIP_PERFIL_MODIFTEXTOSAL",          
          lc[336] = "No se han guardado los cambios del perfil actual. ¿Quiere guardarlos antes de salir?";
            //"_0337_CLIP_ATRIB_REFLEXIVO_SI",          
          lc[337] = "Emparejar los distintos elementos de un programa entre sí";
            //"_0338_CLIP_ATRIB_REFLEXIVO_NO",              
          lc[338] = "Emparejar elementos de distintos programas entre sí";
            //"_0339_CLIP_ATRIB_REFLEXIVO_DESCRIPCION",              
          lc[339] = "La opción de emparejamiento reflexivo permite indicar si se realizan procesamientos entre distintos programas, emparejando los elementos de uno de ellos con los elementos del otro, o si por el contrario se realizan procesamientos de cada uno de los programas consigo mismo, en cuyo caso se intentan emparejar los elementos del programa con otros elementos del mismo programa.\n\n"+
                    "Se puede observar además que el emparejamiento reflexivo (cada programa consigo mismo) toma mucho menos tiempo, ya que se realiza un procesamiento por programa, mientras que para el emparejamiento no reflexivo se realizan (n^2-n)/2 procesamientos.\n\n"+
                    "Típicamente se quieren comparar programas distintos, por lo que la opción por defecto es \""+lc[338]+"\".";
            //"_0340_CLIP_EDITANDO",              
          lc[340] = "Editando";
            //"_0341_CLIP_EDITANDO_MEMORIA",              
          lc[341] = "[perfil no guardado]";
            //"_0342_CLIP_NO_MODIFICADO",              
          lc[342] = "El perfil coincide con la copia guardada";
            //"_0343_CLIP_MODIFICADO",              
          lc[343] = "El perfil no coincide con la copia guardada";
            //"_0344_CLIP_SOBREESCRIBIR_TITULO",              
          lc[344] = "Sobreescribir fichero";
            //"_0345_CLIP_SOBREESCRIBIR",              
          lc[345] = "El fichero escogido existe. ¿Desea sobreescribirlo?";
            //"_0346_CLIP_TAB_GENERAL",           
          lc[346] = "General";
            //"_0347_CLIP_PROP_VELOCIDAD",
          lc[347] = "Velocidad";
            //"_0348_CLIP_PROP_DETALLE",
          lc[348] = "Detalle";
            //"_0349_CLIP_PROP_PRECISION",
          lc[349] = "Precisión";
            //"_0350_CLIP_PROP_SENSIBILIDAD",
          lc[350] = "Sensibilidad";
            //"_0351_CLIP_PROP_ASIMILACION",
          lc[351] = "Asimilación";
            //"_0352_CLIP_PROP_ESPECIALIZACION",
          lc[352] = "Especialización";
            //"_0353_CLIP_PROP_VELOCIDAD_DESC",
          lc[353] = "La velocidad es indicador de la utilización de opciones para reducir el tiempo de procesamiento";
          //"_0354_CLIP_PROP_DETALLE_DESC",
          lc[354] = "El detalle es indicador de la cantidad de información que se obtendrá";
            //"_0355_CLIP_PROP_PRECISION_DESC",
          lc[355] = "La precisión es indicador de cómo de pequeño será el margen de error de los resultados";
            //"_0356_CLIP_PROP_SENSIBILIDAD_DESC",
          lc[356] = "La sensibilidad es indicador de la influencia de los resultados (positivos o negativos) en la similitud final";
            //"_0357_CLIP_PROP_ASIMILACION_DESC",
          lc[357] = "La asimilación es indicador del aprovechamiento del conocimiento de los datos para obtener mejores resultados";
            //"_0358_CLIP_PROP_ESPECIALIZACION_DESC",
          lc[358] = "La especialización es indicador de la cantidad de opciones llevadas al límite";
            //"_0359_CLIP_HUELLA_EXTENSION",        
          lc[359] = "Portable Network Graphics";
            //"_0360_CLIP_Huella_GUARDA_TITULO",
          lc[360] = "Escoge un fichero para guardar la huella del perfil";
            //"_0361_CLIP_HUELLA_GUARDA_BOTON",
          lc[361] = "Exportar";
            //"_0362_CLIP_HUELLA_GUARDA_BOTON_DESC",
          lc[362] = "Exportar huella del perfil al fichero escogido";
            //"_0363_CLIP_HUELLA_GUARGADO",           
          lc[363] = "Huella del perfil exportada.";
            //"_0364_CLIP_HUELLA_GUARGADO_2",        
          lc[364] = "Huella del perfil exportada";        
            //"_0365_CLIP_ERROR_GUARDANDO_HUELLA",
          lc[365] = "Error exportando huella del perfil";
            //"_0366_CLI_HUELLASAVE_NOT_OK_ERR",
          lc[366] = "No se pudo exportar huella al fichero: $1 (error escribiendo).";
            //"_0367_CLIP_ATRIB_DIFFERENCE_NO",
          lc[367] = "Buscar el mejor emparejamiento";
            //"_0368_CLIP_ATRIB_DIFFERENCE_SI",              
          lc[368] = "Buscar el peor emparejamiento";
            //"_0369_CLIP_ATRIB_DIFFERENCE_DESCRIPCION",
          lc[369] = "La opción de objetivo del emparejamiento permite indicar si se buscan los mejores emparejamientos posibles o los peores emparejamientos posibles.\n\n"+
                    "Obsérvese que si se escoge la opción \""+lc[368]+"\", el significado de las opciones de la pestaña emparejamientos se invierte (similitud máxima).\n\n"+
                    "Típicamente se quieren obtener los mejores emparejamientos posibles, por lo que el valor por defecto para esta opción es \""+lc[367]+"\".\n\n";
            //"_0370_CLIP_ATRIB_RETURNNULL",          
          lc[370] = "Generar también resultados para los elementos no emparejados";
            //"_0371_CLIP_ATRIB_RETURNNULL_DESCRIPCION",
          lc[371] = "La opción de generar resultados para los elementos no emparejados permite indicar si se quiere obtener más información en los resultados generados, correspondiente a los elementos que no han sido emparejados debido a las restricciones o condiciones del perfil.\n\n"+
                    "Típicamente se quieren observar únicamente los emparejamientos realizados, por lo que el valor por defecto para esta opción es no generarlos.";
            //"_0372_CLIP_ATRIB_CLASSSAMENAME",          
          lc[372] = "Emparejar clases entre sí sólo si tienen el mismo nombre";
            //"_0373_CLIP_ATRIB_CLASSSAMENAME_DESCRIPCION",
          lc[373] = "La opción de emparejar sólamente clases que se llamen igual permite indicar si se pueden emparejar dos clases cualesquiera o si sólo se pueden emparejar las clases de mismo nombre.\n\n"+
                    "En caso de saber que en una serie de programas las clases se van a llamar de la misma forma, es recomendable marcar esta opción, ya que se reducen los falsos positivos y aumenta la velocidad del procesamiento.\n\n"+
                    "Típicamente se desconoce si el nombre de las clases coincide, por lo que el valor por defecto para esta opción es no emparejar únicamente clases con el mismo nombre.";
            //"_0374_CLIP_ATRIB_METHODOFCLASSSAMENAME",          
          lc[374] = "Emparejar métodos entre sí sólo si están en una clase de mismo nombre";
            //"_0375_CLIP_ATRIB_METHODOFCLASSSAMENAME_DESCRIPCION",
          lc[375] = "La opción de emparejar sólamente métodos que estén contenidos en clases con el mismo nombre permite indicar si se pueden emparejar dos métodos cualesquiera o si sólo se pueden emparejar aquellos métodos contenidos en clases de mismo nombre.\n\n"+
                    "En caso de saber que en una serie de programas las clases se van a llamar de la misma forma, pero los nombres de los métodos de dichas clases pueden cambiar, es recomendable marcar esta opción, ya que se reducen los falsos positivos y aumenta la velocidad del procesamiento.\n\n"+
                    "También es útil en los emparejamientos reflexivos para limitar los emparejamientos de los métodos de un mismo programa a aquellos que se encuentren en las mismas clases.\n\n"+
                    "Típicamente se desconoce si el nombre de las clases coincide, por lo que el valor por defecto para esta opción es no emparejar únicamente métodos contenidos en clases de mismo nombre.";
            //"_0376_CLIP_ATRIB_METHODSAMENAME",          
          lc[376] = "Emparejar métodos entre sí sólo si tienen el mismo nombre";
            //"_0377_CLIP_ATRIB_METHODSAMENAME_DESCRIPCION",
          lc[377] = "La opción de emparejar sólamente métodos que se llamen igual permite indicar si se pueden emparejar dos métodos cualesquiera o si sólo se pueden emparejar los métodos de mismo nombre.\n\n"+
                    "En caso de saber que en una serie de programas los distintos métodos de las clases se van a llamar de la misma forma, es recomendable marcar esta opción, ya que se reducen los falsos positivos y aumenta la velocidad del procesamiento.\n\n"+
                    "Típicamente se desconoce si el nombre de los métodos coincide, por lo que el valor por defecto para esta opción es no emparejar únicamente métodos con el mismo nombre.";
            //"_0378_CLIP_ATRIB_CLASSALLOWMULTIMATCH",          
          lc[378] = "Permitir emparejamientos múltiples para clases";
            //"_0379_CLIP_ATRIB_CLASSALLOWMULTIMATCH_DESCRIPCION",
          lc[379] = "La opción de emparejamientos múltiples para clases permite indicar si una clase de un programa puede emparejarse como máximo con únicamente una o con varias clases.\n\n"+
                    "Típicamente se desconoce si una clase puede parecerse a varias otras clases, por lo que el valor por defecto para esta opción es permitir emparejamientos múltiples para clases.";
            //"_0380_CLIP_ATRIB_METHODALLOWMULTIMATCH",          
          lc[380] = "Permitir emparejamientos múltiples para métodos";
            //"_0381_CLIP_ATRIB_METHODALOWMULTIMATCH_DESCRIPCION",
          lc[381] = "La opción de emparejamientos múltiples para métodos permite indicar si un método puede emparejarse como máximo con únicamente uno o con varios métodos.\n\n"+
                    "Típicamente se desconoce si un método puede parecerse a varios otros métodos, y de hecho puede proporcionar una guía de cara a refactorizaciones, por lo que el valor por defecto para esta opción es permitir emparejamientos múltiples para métodos.";
            //"_0382_CLIP_ATRIB_BLOCKALLOWMULTIMATCH",          
          lc[382] = "Permitir emparejamientos múltiples para bloques";
            //"_0383_CLIP_ATRIB_BLOCKALOWMULTIMATCH_DESCRIPCION"
          lc[383] = "La opción de emparejamientos múltiples para bloques permite indicar si un bloque puede emparejarse como máximo con únicamente uno o con varios bloques.\n\n"+
                    "Típicamente se desconoce si un bloque puede parecerse a varios otros bloques, y de hecho puede proporcionar una guía de cara a refactorizaciones e información relevante sobre copy&paste, por lo que el valor por defecto para esta opción es permitir emparejamientos múltiples para bloques.";
            //"_0384_CLIP_ATRIB_PROGMATCHMINMAX_DESCRIPCION",
          lc[384] = "La opción de programas para los que obtener resultados en detalle permite indicar en qué programas se centrará el estudio más costoso.\n\n"+
                    "Es razonable dedicar más tiempo a obtener mejor información en programas que se encuentren en un rango de similitud determinado, generalmente superior a un umbral. De esta manera, se reduce el tiempo de procesamiento sin sacrificar resultados de interés.\n\n"+
                    "Cabe destacar que todos los programas se intentarán emparejar hasta estar seguro de que vaya a ocurrir una de las siguientes posibilidades:\n"+
                    "a) Se garantice que la similitud del emparejamiento va a estar por debajo de la similitud mínima.\n"+
                    "b) Se garantice que la similitud del emparejamiento va a estar por encima de la similitud máxima.\n"+
                    "c) Se garantice que la similitud del emparejamiento va a estar entre la similitud mínima y la máxima.\n"+
                    "Sólo en este último caso se continuará emparejando elementos de los programas.\n\n"+
                    "Típicamente sólo se consideran significativos emparejamientos con un amplio grado de similitud, por lo que el valor por defecto para esta opción es el rango 50%-100%.";
            //"_0385_CLIP_ATRIB_PROGMATCHERROR_DESCRIPCION",
          lc[385] = "La opción de error máximo de la similitud permite indicar la precisión de la similitud para los programas en el rango de similitudes indicado a generar resultados en detalle.\n\n"+
                    "Si el valor de esta opción es mayor que el rango de similitud, símplemente se diferencian los programas dentro del rango de los programas fuera del rango.\n\n"+
                    "Obsérvese que a menor sea el error máximo, más a fondo se realizarán los emparejamientos, y se producirán más resultados, no necesariamente más significativos.\n\n"+
                    "Típicamente se desea obtener información con una precisión razonable, que no sea demasiado pequeña para no generar demasiados resultados no significativos, pero que tampoco sea muy grande, para poder distinguir entre los distintos grados de similitud en los resultados obtenidos, por lo que el valor por defecto para esta opción es un 2%. Esto significa que si se obtiene una similitud final de 95%, el rango de similitud efectiva es 93%-97%.";
            //"_0386_CLIP_ATRIB_PROGMATCHMINMAX",
          lc[386] = "Obtener resultados en detalle para los programas con similitud entre";
            //"_0387_CLIP_ATRIB_PROGMATCHMINMAX_MIN",
          lc[387] = "Similitud mínima";
            //"_0388_CLIP_ATRIB_PROGMATCHMINMAX_MAX",
          lc[388] = "Similitud máxima";
            //"_0389_CLIP_ATRIB_PROGMATCHERROR",
          lc[389] = "Para estos programas, el error máximo de la similitud debe ser";
            //"_0390_CLIP_ATRIB_PROGMATCHERROR_ERROR",
          lc[390] = "Error máximo";
            //"_0391_CLIP_ATRIB_BLOCKWEIGHT_DESC",
          lc[391] = "Valores de ponderación de los atributos de los bloques basados en número de instrucciones";
            //"_0392_CLIP_ATRIB_BLOCKWEIGHT_METHODSTART",
          lc[392] = "¿Primer bloque?";
            //"_0393_CLIP_ATRIB_BLOCKWEIGHT_METHODEND",
          lc[393] = "¿Último bloque?";
            //"_0394_CLIP_ATRIB_BLOCKWEIGHT_INSTRUCTIONCOUNT",
          lc[394] = "Nº total inst.";
            //"_0395_CLIP_ATRIB_BLOCKWEIGHT_TAB",
          lc[395] = "Ponderación";
            //"_0396_CLIP_ATRIB_BLOCKWEIGHT_AYUDA",
          lc[396] = "Estas opciones permiten indicar los pesos en la ponderación de los atributos de los bloques en los emparejamientos.\n\n"+
                    "Si un valor de ponderación es 0, ese atributo no será considerado al emparejar dos bloques.\n\n"+
                    "Por lo demás, el valor es relativo a los demás, lo que quiere decir que si para un tipo de instrucción el valor es 1.00 y para otra es 2.00, se dará el doble de importancia al hecho de que aparezca una instrucción del segundo tipo en un bloque frente al hecho de que aparezca una instrucción del primer tipo en el mismo bloque.\n\n"+
                    "Típicamente se desea una distribución de ponderaciones inversamente proporcional a la posibilidad media de que una instrucción determinada aparezca en un bloque cualquiera. Los valores por defecto para estas opciones son una aproximación de esta distribución.\n\n"+
                    "Significado de los campos:\n"+
                    "- \""+lc[392]+"\" significa que el bloque es inicial de un método, es decir, el primer bloque del método. Valdrá 0 si el bloque no es inicial, y 1 si lo es.\n"+
                    "- \""+lc[393]+"\" significa que el bloque es final de un método, es decir, es un bloque que acaba en return.\n"+
                    "- \""+lc[394]+"\" significa el número total de instrucciones del bloque, que es una forma de cuantificar el tamaño del mismo.\n"+
                    "- El resto de campos significan el número de instrucciones de ese tipo determinado que hay en el bloque.";
            //"_0397_CLIP_ATRIB_MINIMUMINSTRUCTION_AYUDA",
          lc[397] = "Las opciones de ignorar elementos con menos instrucciones de las indicadas permite indicar un umbral en el número de instrucciones de los distintos tipos de elementos de manera que todos los elementos cuyo número de instrucciones no alcance dicho umbral serán obviados en el procesamiento.\n\n"+
                    "Elevar los valores de estas opciones supondrá un menor tiempo de procesamiento y una reducción de los falsos positivos, a costa de una pérdida progresiva y proporcional a estos valores de la precisión en los resultados.\n\n"+
                    "Unos valores muy elevados proporcionarán unos resultados con validez muy específica.\n\n"+
                    "Típicamente se desean ignorar clases, métodos y bloques que por su extensión no son significativos en los resultados, por lo que por defecto se proporcionan unos valores de 20, 10 y 2 respectivamente.";
            //"_0398_CLIP_ATRIB_MINIMUMINSTRUCTION",
          lc[398] = "Ignorar elementos de un tipo determinado con un número de instrucciones menor al indicado";
            //"_0399_CLIP_ATRIB_MINIMUMINSTRUCTION_CLASS",
          lc[399] = "Tipo clase";
            //"_0400_CLIP_ATRIB_MINIMUMINSTRUCTION_METHOD",
          lc[400] = "Tipo método";
            //"_0401_CLIP_ATRIB_MINIMUMINSTRUCTION_BLOCK",
          lc[401] = "Tipo bloque";
            //"_0402_CLIP_ATRIB_RESTRICCIONES",
          lc[402] = "Restricciones";          
            //"_0403_CLIP_ORIENTACION_TAB",
          lc[403] = "Orientación";
            //"_0404_CLIP_OPTIMISM",
          lc[404] = "Influencia en la similitud del encuadre del elemento complejo en el simple frente al enfoque opuesto";
            //"_0405_CLIP_CLASEYMETODOSINCLASE",
          lc[405] = "Clase y método (sin clase)";
            //"_0406_CLIP_METODOENCLASE",
          lc[406] = "Método (en clase)";
            //"_0407_CLIP_BLOQUE",
          lc[407] = "Bloque";
            //"_0408_CLIP_THRESHOLD",
          lc[408] = "Intentar emparejar las clases que representan porciones del programa entre";
            //"_0409_CLIP_PMIN",
          lc[409] = "Porcentaje mínimo";
            //"_0410_CLIP_PMAX",
          lc[410] = "Porcentaje máximo";
            //"_0411_CLIP_DIFFERENCE",
          lc[411] = "Máxima diferencia proporcional de tamaño para intentar emparejar dos elementos";
            //"_0412_CLIP_PROGRAMA",
          lc[412] = "Programa";
            //"_0413_CLIP_CLASE",
          lc[413] = "Clase";
            //"_0414_CLIP_LIMIT",
          lc[414] = "Dejar de buscar al haber emparejado al menos el porcentaje indicado de dos elementos";
            //"_0415_CLIP_METODO",
          lc[415] = "Método";
            //"_0416_CLIP_ACCEPTABLE",
          lc[416] = "Similitud mínima para emparejar inmediatamente dos elementos, ignorando el resto de posibilidades";
            //"_0417_CLIP_MINIMUM",
          lc[417] = "Similitud mínima necesaria para aceptar el emparejamiento de dos elementos";
            //"_0418_CLIP_OPTIMISM_HELP",
          lc[418] = "Las opciones de influencia de la similitud permiten definir si utilizar una aproximación de encuadrar los subelementos del elemento más simple (con menos subelementos) a comparar, en los del más complejo, o si usar una aproximación opuesta (subelementos del elemento complejo en subelementos del elemento simple), o en usar como aproximación ambas y ponderarlas con un peso determinado.\n\n"+
                    "Si se deja un valor 0, se realizará el emparejamiento más pesimista (subelementos del elemento complejo en subelementos del elemento simple). Es de esperar que con esta aproximación se obtengan similitudes más bajas y se reduzcan los falsos positivos.\n\n"+
                    "Si se deja un valor 1, se realizará el emparejamiento más optimista (subelementos del elemento simple en subelementos del elemento complejo). Con esta aproximación se obtendrán similitudes más altas, pero es más probable que se obtengan algunos tipos de falsos positivos, en particular si no se utilizan restricciones.\n\n"+
                    "Si se utiliza un valor entre 0 y 1, se realizan ambos emparejamientos, se ofrecen todos los resultados en ambos sentidos, y se considera similitud final (100%-influencia*pesimista)+(influencia*optimista).\n\n"+
                    "Obsérvese que al utilizar un emparejamiento intermedio se está duplicando el tiempo de procesamiento.\n\n"+
                    "Típicamente se desea realizar un emparejamiento veloz y obtener menos falsos positivos, por lo que el valor por defeto para esta opción es 0.";
            //"_0419_CLIP_THRESHOLD_HELP",
          lc[419] = "La opción de emparejar clases que representan una porción determinada del programa permite definir qué clases se intentarán emparejar como tales y cuales se descompondrán en subelementos.\n\n"+
                    "Obsérvese que en cualquier caso, una clase que no haya resultado efectivamente emparejada con otra, será descompuesta en subelementos.\n\n"+
                    "El uso de esta opción puede reducir el tiempo de procesamiento si se efectúan emparejamientos entre clases o puede incrementarlo si no se efectúan.\n\n"+
                    "Típicamente se desea claridad en los resultados, por lo que por defecto se intentan emparejar todas las clases del programa.";
            //"_0420_CLIP_DIFFERENCE_HELP",
          lc[420] = "Las opciones de máxima diferencia proporcional de tamaño permiten indicar umbrales de manera que dos elementos cuyos tamaños estén desproporcionados por encima de ese umbral no serán emparejados.\n\n"+
                    "Conforme este umbral crece, se permiten más emparejamientos.\n\n"+
                    "Es razonable dejar un margen para estos emparejamientos, pero si el umbral es muy grande se perderá mucha velocidad en intentar emparejar elementos con los que dificilmente se obtengan similitudes elevadas.\n\n"+
                    "Típicamente se desea un procesamiento veloz sin dejar de comprobar los elementos más significativos, así que por defecto se considera un valor de 40% para esta opción.";
            //"_0421_CLIP_LIMIT_HELP",
          lc[421] = "Las opciones de límite de emparejamiento permiten indicar un porcentaje máximo de similitud hallada tras el cual se dejan de explorar los elementos.\n\n"+
                    "Esto es, si mientras se está emparejando un elemento se observa que se ha obtenido una similitud mayor al umbral indicado, se cesa el emparejamiento.\n\n"+
                    "Como los elementos se emparejan por orden de tamaño, es una opción razonable que reducirá el tiempo de procesamiento sin sacrificar resultados.\n\n"+
                    "Típicamente se desea un compromiso entre velocidad y correctitud de los resultados, por ello por defecto se limita el emparejamiento hasta el 90% de similitud.";
            //"_0422_CLIP_ACCEPTABLE_HELP",
          lc[422] = "Las opciones de similitud mínima para emparejar inmediatamente dos elementos permiten indicar un umbral de similitud que, al ser superado en el emparejamiento de dos elementos, provoca que se dejen de intentar otros emparejamientos para los elementos en cuestión.\n\n"+
                    "Conforme este umbral aumenta se obtienen peores emparejamientos y el tiempo de procesamiento disminuye.\n\n"+
                    "No obstante, dejando de lado los resultados en sí, y considerando únicamente la similitud resultante de los emparejamientos de los programas, utilizar esta opción es razonable.\n\n"+
                    "Típicamente se desea un compromiso entre velocidad y correctitud de los resultados, por ello por defecto se aceptan los emparejamientos con similitud superior o igual a un 95%.";
            //"_0423_CLIP_MINIMUM_HELP",
          lc[423] = "Las opciones de similitud mínima necesaria para emparejar dos elementos permiten indicar un umbral de similitud que debe ser superado para que un emparejamiento se considere efectivo.\n\n"+
                    "Conforme este umbral aumenta se obtienen menos emparejamientos y el número de falsos positivos se reduce.\n\n"+
                    "Típicamente se desea obtener unos resultados con un emparejamiento mínimo razonable, aunque también se quieren observar los resultados con emparejamientos parciales. Por ello, el valor por defecto para esta opción es del 85% para las clases y del 30% para el resto de elementos.";
            //"_0424_CLIP_UMBRALES_TAB",
          lc[424] = "Umbrales";
            //"_0425_CLIP_EMPAREJAMIENTOS_TAB",
          lc[425] = "Emparejamientos";
            //"_0426_CLIP_METODOSINCLASE",
          lc[426] = "Método (sin clase)";
            //"_0427_CLIP_HUELLA_HELP",
          lc[427] = "La huella del perfil muestra una representación gráfica de las propiedades del perfil.\n\n"+
                    "Proporciona retroalimentación visual rápida y sirve de guía inmediata para detectar posibles errores que hayan podido cometerse durante la confección del perfil.\n\n";
            //"_0428_CLIP_ATRIBS_HELP",
          lc[428] = "Las propiedades del perfil proporcionan una síntesis de distintos aspectos del perfil.\n\n"+
                    "Estos son los significados de las distintas propiedades:\n"+
                    "- "+lc[353]+".\n"+
                    "- "+lc[354]+".\n"+
                    "- "+lc[356]+".\n"+
                    "- "+lc[355]+".\n"+
                    "- "+lc[358]+".\n"+
                    "- "+lc[357]+".";
            //"_0429_CLIP_ENTRA",
          lc[429] = "\n      ¡Bienvenido al Gestor de Perfiles de JSimil!\n\nSoy la ayuda contextual, te ofreceré explicaciones y ejemplos de las distintas opciones cuando las comiences a modificar.";
            //"_0430_CLI_HELP_49",
          lc[430] = " -z/--threads <num>       Número de hebras que se van a utilizar en carga y"+rn+
                    "                            procesamiento.";

          if (1 == 1);          
            if (lang.equals("es")) {
              for (i = 0;i < size;++i) {
                  if (cod[i].contains("_CLI_")) {
                      String sa = lc[i];
                      sa = sa.replace("á","a");
                      sa = sa.replace("é","e");
                      sa = sa.replace("í","i");
                      sa = sa.replace("ó","o");
                      sa = sa.replace("ú","u");
                      sa = sa.replace("Á","A");
                      sa = sa.replace("É","E");
                      sa = sa.replace("Í","I");
                      sa = sa.replace("Ó","O");
                      sa = sa.replace("Ú","U");
                      sa = sa.replace("ñ","~");
                      sa = sa.replace("Ñ","~");
                      sa = sa.replace("¿"," ");
                      sa = sa.replace("¡"," ");
                      lc[i] = sa;
                  }              
              }
          }
        }
        else if (lang.equals("en")) {
            n = "English";
            // "_0000_CLI_WELCOME_1"
            lc[0] = "JSimil $1 $2 ($3).";
            // "_0001_CLI_WELCOME_2"
            lc[1] = "Java-Written Programs Similarities Detector.";
            // "_0002_CLI_WELCOME_3"
            lc[2] = "Author: $1.";     
            // "_0003_CLI_ERROR_UNKNOWN_ARGUMENT"
            lc[3] = "Unknown argument: $1.";
            //"_0004_CLI_HELP_1"
            lc[4] = "Usage: java -Xms128M -Xmx1618M -jar $1 <arguments>";
            //"_0005_CLI_HELP_2"
            lc[5] = "Argument list:";
            //"_0006_CLI_HELP_3"
            lc[6] = " -h/--help                Shows this help.";
            //"_0007_CLI_HELP_4"
            lc[7] = " --langlist               Lists the available language codes.";
            //"_0008_CLI_HELP_5"
            lc[8] = " -l/--lang <code>         Switchs the language to the one specified.";
            //"_0009_CLI_HELP_6"
            lc[9] = " -v/--verbose             Verbose mode.";
            //"_0010_CLI_HELP_7"
           lc[10] = " -n/--dont-process        Don't process, just load and save.";
            //"_0011_CLI_HELP_8",
           lc[11] = " --new-profile-to <file>  Write a new profile to the specified file.";
            //"_0012_CLI_HELP_9",
           lc[12] = " --new-config-to <file>   Write a new configuration to the specified file.";
            //"_0013_CLI_HELP_10",
           lc[13] = " --new-battery-to <file>  Write a new battery to the specified file.";
            //"_0014_CLI_HELP_11",
           lc[14] = " --def-profile-to <file>  Write default profile to the specified file.";
            //"_0015_CLI_HELP_12"           
           lc[15] = " -b/--battery-load <file> Load battery from the specified file.";
            //"_0016_CLI_HELP_13"
           lc[16] = " --battery-save <file>    Save battery information to the specified file.";
            //"_0017_CLI_HELP_14"
           lc[17] = " -g/--battery-dump <file> Save complete battery to the specified file.";           
            //"_0018_CLI_HELP_15"
           lc[18] = " -t/--battery-path <path> Creates a battery contained in given path.";           
            //"_0019_CLI_HELP_16"
           lc[19] = " --dont-autoconfig        Don't autoconfigure if error.";
            //"_0020_CLI_HELP_17"
           lc[20] = " -p/--profile-load <file> Load profile from the specified file.";
            //"_0021_CLI_HELP_18"
           lc[21] = " --profile-save <file>    Save profile to the specified file.";
            //"_0022_CLI_HELP_19"
           lc[22] = " -d/--profile-def         Use default profile.";
            //"_0023_CLI_HELP_20"
           lc[23] = " -a/--autoconfig          Autoconfigure.";
            //"_0024_CLI_HELP_21"
           lc[24] = " -c/--config-load <file>  Load configuration from the specified file.";
            //"_0025_CLI_HELP_22"
           lc[25] = " --config-save <file>     Save configuration to the specified file.";
            //"_0026_CLI_HELP_23"
           lc[26] = " -e/--export-html <dir>   Make an HTML result report and save it to the"+rn+
                    "                            specified directory.";
            //"_0027_CLI_HELP_24"
           lc[27] = " --disable-only <list>    Enable all programs in the battery but the specified"+rn+
                    "                            ones to be processed (comma separated names).";
            //"_0028_CLI_HELP_25"
           lc[28] = " -s/--export-txt <file>   Make an TXT result report and save it to the specified"+rn+
                    "                            file.";
            //"_0029_CLI_HELP_26"
           lc[29] = " --enable-only <list>     Enable only specified programs in the battery to be"+rn+
                    "                            processed (comma separated names).";
            //"_0030_CLI_HELP_27",
           lc[30] = " -o/--output-standard     Write complete results to the standard command line"+rn+
                    "                            output.";
            //"_0031_CLI_HELP_28",
           lc[31] = " -q/--enable-all          Enable all programs in the battery to be processed.";
           //"_0032_CLI_HELP_29",
           lc[32] = " -r/--resume-standard     Write a results resume to the standard command line"+rn+
                    "                            output.";
            //"_0033_CLI_HELP_30",
           lc[33] = " -f/--force-overwrite     Force overwriting output files.";
            //"_0034_CLI_HELP_31",
           lc[34] = "Keep in mind all kind of loads get done before processing (if it were to be"+rn+
           "done) and all kind of saves get done after processing (if it were to be done)."+rn+
           "However, any error cancels all saves.";
            //"_0035_CLI_HELP_32",
           lc[35] = " -j/--export-resume <fil> Make an TXT result resume and save it to the specified"+rn+
                    "                            file.";
            //"_036_CLI_HELP_33", //--output-profile
           lc[36] = " -i/--output-profile      Write profile information on every output.";
            //"_0037_CLI_ERROR_REPEATED",
           lc[37] = "Repeated write-argument target: $1";
            //"_0038_CLI_ERROR_REPEATED_ARGUMENT",
           lc[38] = "Repeated argument: $1";
            //"_0039_CLI_ERROR_NO_SUCH_LANGUAGE",
           lc[39] = "No such language: $1";
            //"_0040_CLI_ERROR_ARGUMENT_HYPHEN",
           lc[40] = "Can't start with hyphen argument parameter: $1";
            // "_0041_CLI_ERROR_UNKNOWN_ARGUMENT_WITHOUT_PARAMETER"
           lc[41] = "Unknown argument: $1 (without parameter).";
            //"_0042_CLI_ERROR_MULTIPLE_CONFIG_INPUT",
           lc[42] = "Multiple configuration inputs: autoconfigure and load from file.";
            //"_0043_CLI_ERROR_MULTIPLE_PROFILE_INPUT_1",
           lc[43] = "Multiple profile inputs: default and load from file.";
            //"_0044_CLI_ERROR_MULTIPLE_BATTERY_INPUT",
           lc[44] = "Multiple battery inputs: path and load from file.";
            //"_0045_CLI_HELP_34"
           lc[45] = " --def-rprofile-to <file> Write default reflexive profile to the specified file.";
            //"_0046_CLI_HELP_35"
           lc[46] = " --rprofile-def           Use default reflexive profile.";
            //"_0047_CLI_ERROR_MULTIPLE_PROFILE_INPUT_2",
           lc[47] = "Multiple profile inputs: default, default reflexive and load from file.";
            //"_0048_CLI_ERROR_MULTIPLE_PROFILE_INPUT_3",
           lc[48] = "Multiple profile inputs: default reflexive and load from file.";
            //"_0049_CLI_ERROR_MULTIPLE_PROFILE_INPUT_4",
           lc[49] = "Multiple profile inputs: default reflexive and default.";
            //"_0050_CLI_ERROR_WRITING_NEW_CONFIG",
           lc[50] = "Error writing new configuration in: $1.";
            //"_0051_CLI_ERROR_WRITING_NEW_PROFILE",
           lc[51] = "Error writing new profile in: $1.";
            //"_0052_CLI_ERROR_WRITING_DEFAULT_PROFILE",
           lc[52] = "Error writing default profile in: $1.";
            //"_0053_CLI_ERROR_WRITING_DEFAULT_REFLEXIVE_PROFILE",
           lc[53] = "Error writing default reflexive profile in: $1.";
            //"_0054_CLI_ERROR_WRITING_NEW_BATTERY",
           lc[54] = "Error writing new battery in: $1.";
            //"_0055_CLI_WRITING_NEW_OR_DEFAULT_FILES",
           lc[55] = "Writing new or default files.";
            //"_0056_CLI_WRITTEN_NEW_CONFIG",
           lc[56] = "Written empty configuration in: $1.";
            //"_0057_CLI_WRITTEN_NEW_PROFILE",
           lc[57] = "Written empty profile in: $1.";
            //"_0058_CLI_WRITTEN_DEFAULT_PROFILE",
           lc[58] = "Written default profile in: $1.";
            //"_0059_CLI_WRITTEN_DEFAULT_REFLEXIVE_PROFILE",
           lc[59] = "Written default reflexive profile in: $1.";
            //"_0060_CLI_WRITTEN_NEW_BATTERY",       
           lc[60] = "Written empty battery in: $1.";
            //"_0061_CLI_HELP_36",
           lc[61] = "Example:";
            //"_0062_CLI_HELP_37",
           lc[62] = "Process battery at dir/ with default profile and autoconfiguration.";
            //"_0063_CLI_LANGUAGE",
           lc[63] = "  Code: $1 - Language: $2.";
            //"_0064_CLI_DEFAULT_LANGUAGE",
           lc[64] = "  Code: $1 - Language: $2 (default).";
            //"_0065_CLI_AVAILABLE_LANGUAGES",
           lc[65] = "Available languages:";
            //"_0066_CLI_CANT_AUTOCONFIG",
           lc[66] = "Can't autoconfigure. Use a manual configuration.";
            //"_0067_CLI_AUTOCONFIG_OK",
           lc[67] = "Successful autoconfiguration.";
            //"_0068_CLI_CONFIGLOAD_OK",
           lc[68] = "Configuration successfully loaded from file: $1.";
            //"_0069_CLI_CONFIGLOAD_NOT_OK_PATH",
           lc[69] = "Couldn't load configuration from file: $1 (not accesible path).";
            //"_0070_CLI_CONFIGLOAD_NOT_OK_FORMAT",
           lc[70] = "Couldn't load configuration from file: $1 (unrecognized format).";
            //"_0071_CLI_CONFIGLOAD_TRYING_AUTOCONFIG",
           lc[71] = "Trying to autoconfigure.";
            //"_0072_CLI_PROFILELOAD_DEFAULT",
           lc[72] = "Default profile loaded.";
            //"_0073_CLI_PROFILELOAD_DEFAULTREFLEXIVE",
           lc[73] = "Default reflexive profile loaded.";
            //"_0074_CLI_PROFILELOAD_OK",
           lc[74] = "Profile successfully loaded from file: $1.";
            //"_0075_CLI_PROFILELOAD_NOT_OK_PATH",
           lc[75] = "Couldn't load profile from file: $1 (not accesible path).";
            //"_0076_CLI_PROFILELOAD_NOT_OK_FORMAT",
           lc[76] = "Couldn't load profile from file: $1 (unrecognized format).";
            //"_0077_CLI_BATTERYPATH",
           lc[77] = "Battery set to path: $1.";
            //"_0078_CLI_BATTERYLOAD_OK",
           lc[78] = "Battery successfully loaded from file: $1.";
            //"_0079_CLI_BATTERYLOADLOAD_NOT_OK_PATH",
           lc[79] = "Couldn't load battery from file: $1 (not accesible path).";
            //"_0080_CLI_BATTERYLOAD_NOT_OK_FORMAT",
           lc[80] = "Couldn't load battery from file: $1 (unrecognized format).";
            //"_0081_CLI_CONFIG_VALIDATES",
           lc[81] = "Configuration is VALID.";
            //"_0082_CLI_BATTERY_VALIDATES",
           lc[82] = "Battery is VALID.";
            //"_0083_CLI_PROFILE_VALIDATES",
           lc[83] = "Profile is VALID.";
            //"_0084_CLI_CONFIG_NOT_VALIDATES",
           lc[84] = "Configuration is NOT VALID.";
            //"_0085_CLI_BATTERY_NOT_VALIDATES",
           lc[85] = "Battery is NOT VALID.";
            //"_0086_CLI_PROFILE_NOT_VALIDATES",
           lc[86] = "Profile is NOT VALID.";
            //"_0087_CLI_CONFIG_ERR_COMPILER_PATH",
           lc[87] = "Compiler path is not accesible.";
            //"_0088_CLI_CONFIG_ERR_COMPILER_EXE",
           lc[88] = "Compiler can't be executed.";
            //"_0089_CLI_CONFIG_ERR_DISASSEMBLER_PATH",
           lc[89] = "Dissasembler path is not accesible.";
            //"_0090_CLI_CONFIG_ERR_DISASSEMBLER_EXE",
           lc[90] = "Disassembler can't be executed.";
            //"_0091_CLI_PROFILE_ERR_ZERO_OR_ONE",
           lc[91] = "Expected value 0 or 1 for parameter: $1.";
            //"_0092_CLI_PROFILE_ERR_BETWEEN_ZERO_AND_ONE",
           lc[92] = "Expected value between 0 or 1 for parameter: $1.";
            //"_0093_CLI_PROFILE_ERR_NOT_VALID_MIN_MAX",
           lc[93] = "Value of parameter $1 should be greater than the one of $2.";
            //"_0094_CLI_PROFILE_ERR_GREATER_ZERO",
           lc[94] = "Expected value greater than 0 for parameter: $1.";
            //"_0095_CLI_BATTERY_ERR_NOT_ACCESIBLE_PATH",
           lc[95] = "Path in battery is not accesible.";
            //"_0096_CLI_BATTERY_ERR_NO_PROGRAMS_TO_COMPARE",
           lc[96] = "No programs to compare in battery.";
            //"_0097_CLI_LOAD_BATTERY",
           lc[97] = "Loading battery.";
            //"_0098_CLI_BATTERY_ERR_READING",
           lc[98] = "Error while reading battery programs files.";
            //"_0099_CLI_BATTERY_LOADED",
           lc[99] = "Battery loaded successfully in $1s.";
            //"_0100_CLI_NOTHING_ELSE_TO_DO",
          lc[100] = "Nothing else to do.";
            //"_0101_CLI_PROCESS",        
          lc[101] = "Processing.";
            //"_0102_CLI_PROCESS_DONE",        
          lc[102] = "Processing completed successfully in $1s.";
            //"_0103_CLI_PROCESS_ERROR",        
          lc[103] = "CRITICAL UNKNOWN ERROR WHILE PROCESSING.";
            //"_0104_CLI_NO_PROFILE",        
          lc[104] = "No profile has been specified.";
            //"_0105_CLI_HELP_38",
          lc[105] = " -w/--force-battery-load  Force loading of battery programs.";
            //"_0106_CLI_NOT_FORCED",
          lc[106] = "Battery was already loaded (use -w to force loading).";
            //"_0107_CLI_WELCOME_4",
          lc[107] = "JSimilCLI - JSimil Command Line Interface.";
            //"_0108_CLI_EVENT_DEBUG",
          lc[108] = "[*] Debug: $1";
            //"_0109_CLI_EVENT_ERROR_ENTRADA_SALIDA_DESENSAMBLADOR",
          lc[109] = "[*] Disassembler I/O error: $1";
            //"_0110_CLI_EVENT_ERROR_CLASS_VACIO_O_NO_VALIDO",
          lc[110] = "[*] Empty or not valid class: $1";
            //"_0111_CLI_EVENT_ERROR_LEYENDO_FICHERO_JAVA",
          lc[111] = "[*] Error reading java file: $1";
            //"_0112_CLI_EVENT_ERROR_LEYENDO_FICHERO_JAVAP",
          lc[112] = "[*] Error reading javap file: $1";
            //"_0113_CLI_EVENT_ERROR_NINGUN_FICHERO_VALIDO_EN_PROGRAMA",
          lc[113] = "[*] No valid files in program: $1";
            //"_0114_CLI_EVENT_ERROR_COPIANDO_FICHERO_CLASS",
          lc[114] = "[*] Error copying class file: $1";
            //"_0115_CLI_EVENT_ERROR_COPIANDO_FICHERO_JAVA",
          lc[115] = "[*] Error copying java file: $1";
            //"_0116_CLI_EVENT_INTERRUPCION_COMPILANDO",
          lc[116] = "[*] Compiling interrupted: $1";
            //"_0117_CLI_EVENT_EXPLORADO_APARTADO_Y_COMPILADO",
          lc[117] = "[*] Explored, moved and compiled: $1";
            //"_0118_CLI_EVENT_ERROR_COMPILANDO",
          lc[118] = "[*] Error compiling: $1";
            //"_0119_CLI_EVENT_ERROR_DESENSAMBLANDO",
          lc[119] = "[*] Error disassembling: $1";
            //"_0120_CLI_EVENT_BATERIA_CARGADA",
          lc[120] = "[*] Battery loaded.";
            //"_0121_CLI_EVENT_INTERRUPCION_DESENSAMBLANDO",
          lc[121] = "[*] Disassembling interrupted: $1";
            //"_0122_CLI_EVENT_FALTA_CODIGO_FUENTE",
          lc[122] = "[*] No source code found: $1";
            //"_0123_CLI_EVENT_NO_SE_PUEDE_CREAR_DIRECTORIO_TEMPORAL",
          lc[123] = "[*] Unable to create temporary directory: $1";
            //"_0124_CLI_EVENT_DESENSAMBLADO_Y_CARGADO",
          lc[124] = "[*] Disassembled and loaded: $1";
            //"_0125_CLI_EVENT_ENCONTRADA_CLASE_FUENTE",
          lc[125] = "[*] Found source class: $1";
            //"_0126_CLI_EVENT_ENCONTRADA_CLASE_COMPILADA",
          lc[126] = "[*] Found compiled class: $1";
            //"_0127_CLI_EVENT_DESENSAMBLADO",
          lc[127] = "[*] Disassembled: $1";
            //"_0128_CLI_EVENT_CARGADO",
          lc[128] = "[*] Loaded: $1";
            //"_0129_CLI_EVENT_COMPILADO",
          lc[129] = "[*] Compiled: $1";
            //"_0130_CLI_EVENT_EMPAREJADAS_CLASES",
          lc[130] = "[*] Matched classes: $1";
            //"_0131_CLI_EVENT_EMPAREJADOS_METODOS",
          lc[131] = "[*] Matched methods: $1";
            //"_0132_CLI_EVENT_EMPAREJADOS_PROGRAMAS",
          lc[132] = "[*] Matched programs: $1";
            //"_0133_CLI_CONFIGSAVE_OK",
          lc[133] = "Configuration successfully saved to file: $1.";
            //"_0134_CLI_CONFIGSAVE_NOT_OK_PATH",
          lc[134] = "Couldn't save configuration to file: $1 (not accesible path).";
            //"_0135_CLI_CONFIGSAVE_NOT_OK_ERR",
          lc[135] = "Couldn't save configuration to file: $1 (error writing).";
            //"_0136_CLI_PROFILESAVE_OK",
          lc[136] = "Profile successfully written to file: $1.";
            //"_0137_CLI_PROFILESAVE_NOT_OK_PATH",
          lc[137] = "Couldn't save profile to file: $1 (not accesible path).";
            //"_0138_CLI_PROFILESAVE_NOT_OK_ERR",
          lc[138] = "Couldn't save profile to file: $1 (error writing).";
            //"_0139_CLI_BATTERYSAVE_OK",
          lc[139] = "Battery successfully saved to file: $1.";
            //"_0140_CLI_BATTERYSAVE_NOT_OK_PATH",
          lc[140] = "Couldn't save battery to file: $1 (not accesible path).";
            //"_0141_CLI_BATTERYSAVE_NOT_OK_ERR",
          lc[141] = "Couldn't save battery to file: $1 (error writing).";
            //"_0142_CLI_BATTERYDUMP_OK",
          lc[142] = "Battery successfully dumped to file: $1.";
            //"_0143_CLI_BATTERYDUMP_NOT_OK_PATH",
          lc[143] = "Couldn't dump battery to file: $1 (not accesible path).";
            //"_0144_CLI_BATTERYDUMP_NOT_OK_ERR",
          lc[144] = "Couldn't dump battery to file: $1 (error writing).";
            //"_0145_CLI_HELP_39"
          lc[145] = " -m/--export-rxml <file>  Make an XML result resume and save it to the specified"+rn+
                    "                            file.";
            //"_0146_CLI_HELP_40"
          lc[146] = " -x/--export-xml <file>   Make an XML result report and save it to the specified"+rn+
                    "                            file.";
            //"_0147_CLI_ERROR_MULTIPLE_ENABLED_1",
          lc[147] = "Multiple program activation: all, specified, not specified.";
            //"_0148_CLI_ERROR_MULTIPLE_ENABLED_2",
          lc[148] = "Multiple program activation: all, specified.";
            //"_0149_CLI_ERROR_MULTIPLE_ENABLED_3",
          lc[149] = "Multiple program activation: all, not specified.";
            //"_0150_CLI_ERROR_MULTIPLE_ENABLED_4",
          lc[150] = "Multiple program activation: specified, not specified.";
            //"_0151_CLI_ALL_PROGRAMS_ENABLED",
          lc[151] = "All programs have been enabled for processing.";
            //"_0152_CLI_ALL_PROGRAMS_DISABLED",
          lc[152] = "All programs have been disabled for processing.";
            //"_0153_CLI_PROGRAM_ENABLED",
          lc[153] = "Program enabled for processing: $1.";
            //"_0154_CLI_PROGRAM_DISABLED",
          lc[154] = "Program disabled for processing: $1.";
            //"_0155_CLI_PROGRAM_NOT_FOUND",
          lc[155] = "Program not found: $1.";
            //"_0156_CLI_NO_PROGRAMS_ENABLED_NOR_DISABLED",
          lc[156] = "No programs have been enabled nor disabled for processing.";
            //"_0157_CLI_PROGRAM_LIST",
          lc[157] = "Battery programs list:";
            //"_0158_CLI_ENABLED_PROGRAM",
          lc[158] = "Program: $1 - Status: Enabled for processing.";
            //"_0159_CLI_DISABLED_PROGRAM",
          lc[159] = "Program: $1 - Status: Disabled for processing.";
            //"_0160_CLI_PROGRAM_REPEATED",
          lc[160] = "Program repeated: $1.";
            //"_0161_CLI_NO_RESULTS",
          lc[161] = "No results generated. No matches have been done.";
            //"_0162_CLI_RESULTS",
          lc[162] = "Results:";
            //"_0163_CLI_EVENT_ERROR_FICHEROS_DE_PROGRAMA_ILEGIBLES",
          lc[163] = "[*] Error loading program: $1 (can't understand files).";
            //"_0164_CLI_EVENT_ERROR_COPIANDO_CONTENIDOS",
          lc[164] = "[*] Error cargando programa: $1 (can't copy contents).";
            //"_0165_CLI_EXPORT_IHTML_OK",
          lc[165] = "HTML results exported successfully in $2s to directory: $1.";
            //"_0166_CLI_EXPORT_IHTML_ERROR",
          lc[166] = "Error exporting HTML results to directory: $1.";
            //"_0167_CLI_EXPORT_TXT_OK",
          lc[167] = "TXT results exported successfully in $2s to file: $1.";
            //"_0168_CLI_EXPORT_TXT_ERROR",
          lc[168] = "Error exporting TXT results to file: $1.";
            //"_0169_CLI_EXPORT_XML_OK",
          lc[169] = "XML results exported successfully in $2s to file: $1.";
            //"_0170_CLI_EXPORT_XML_ERROR",
          lc[170] = "Error exporting XML results to file: $1.";
            //"_0171_CLI_EXPORT_RESUME_OK",
          lc[171] = "TXT resume exported successfully in $2s to file: $1.";
            //"_0172_CLI_EXPORT_RESUME_ERROR",
          lc[172] = "Error exporting TXT resume to file: $1.";
            //"_0173_CLI_EXPORT_RESUME_EXISTS",
          lc[173] = "Already exists TXT resume target (-f to overwrite): $1.";
            //"_0174_CLI_EXPORT_TXT_EXISTS",
          lc[174] = "Already exists TXT results target (-f to overwrite): $1.";
            //"_0175_CLI_EXPORT_IHTML_EXISTS",
          lc[175] = "Already exists HTML results target (-f to overwrite): $1.";
            //"_0176_CLI_EXPORT_XML_EXISTS",
          lc[176] = "Already exists XML results target (-f to overwrite): $1.";
            //"_0177_CLI_EXPORT_RXML_OK",
          lc[177] = "XML results resume exported successfully in $2s to file: $1.";
            //"_0178_CLI_EXPORT_RXML_ERROR",
          lc[178] = "Error exporting XML results resume to file: $1.";
            //"_0179_CLI_EXPORT_RXML_EXISTS",
          lc[179] = "Already exists XML results resume target (-f to overwrite): $1.";
            //"_0180_CLI_EXPORT_IHTML_START",
          lc[180] = "Exporting HTML results to directory: $1.";
            //"_0181_CLI_EXPORT_TXT_START",
          lc[181] = "Exporting TXT results to file: $1.";
            //"_0182_CLI_EXPORT_XML_START",
          lc[182] = "Exporting XML results to file: $1.";
            //"_0183_CLI_EXPORT_RESUME_START",
          lc[183] = "Exporting TXT results resume to file: $1.";
            //"_0184_CLI_EXPORT_RXML_START",
          lc[184] = "Exporting XML results resume to file: $1.";
            //"_0185_CLI_HELP_41",
          lc[185] = " --export-rhtml <dir>     Make an HTML result resume and save it to the"+rn+
                    "                            specified directory.";
            //"_0186_CLI_HELP_42",
          lc[186] = " --export-rrhtml <dir>    Make a short HTML result resume and save it to the"+rn+
                    "                            specified directory.";
            //"_0187_CLI_EXPORT_RHTML_START",
          lc[187] = "Exporting HTML results resume to directory: $1.";
            //"_0188_CLI_EXPORT_RHTML_OK",
          lc[188] = "HTML results resume exported successfully in $2s to directory: $1.";
            //"_0189_CLI_EXPORT_RHTML_EXISTS",
          lc[189] = "Already exists HTML results resume target (-f to overwrite): $1.";
            //"_0190_CLI_EXPORT_RHTML_ERROR",
          lc[190] = "Error exporting HTML results resume to directory: $1.";
            //"_0191_CLI_EXPORT_RRHTML_START",
          lc[191] = "Exporting HTML short results resume to directory: $1.";
            //"_0192_CLI_EXPORT_RRHTML_OK",
          lc[192] = "HTML short results resume exported successfully in $2s to directory: $1.";
            //"_0193_CLI_EXPORT_RRHTML_EXISTS",
          lc[193] = "Already exists HTML short results resume target (-f to overwrite): $1.";
            //"_0194_CLI_EXPORT_RRHTML_ERROR",
          lc[194] = "Error exporting HTML short results resume to directory: $1.";
            //"_0195_CLI_EXPORT_IHTML_EXISTS_2",
          lc[195] = "Can't overwrite directory with HTML results (delete manually): $1.";
            //"_0196_CLI_EXPORT_RHTML_EXISTS_2",
          lc[196] = "Can't overwrite directory with HTML results resume (delete manually): $1.";
            //"_0197_CLI_EXPORT_RRHTML_EXISTS_2",
          lc[197] = "Can't overwrite directory with HTML shorts results resume (delete manually): $1.";
            //"_0198_HTML_TITLE",
          lc[198] = "JSimil - Results.";
            //"_0199_HTML_BANNER_1",
          lc[199] = "JSimil $1 - Java-Written Programs Similarities Detector.";
            //"_0200_HTML_BACK",
          lc[200] = "(back)";
            //"_0201_HTML_BACK_TO_MAIN",
          lc[201] = "(back to index)";
            //"_0202_HTML_BANNER_2",
          lc[202] = "Results page.";
            //"_0203_HTML_BANNER_FOOTER",
          lc[203] = "JSimil $1 - $2 ($3).";
            //"_0204_HTML_PROFILE_MINMAX",
          lc[204] = "Exhaustive search applied to matched with similarities: $1-$2";
            //"_0205_HTML_PROFILE_ERROR",
          lc[205] = "Maximum error admited for those matched: $1.";
            //"_0206_HTML_PROFILE_REFLEXIVE_YES",
          lc[206] = "Reflexive matching (program elements matched with themselves).";
            //"_0207_HTML_PROFILE_REFLEXIVE_NO",
          lc[207] = "Non-reflexive matching (disctint programs elements matched).";
            //"_0208_HTML_PROFILE_DIFFERENCE_YES",
          lc[208] = "Looking for differences.";
            //"_0209_HTML_PROFILE_DIFFERENCE_NO",
          lc[209] = "Looking for similarities.";
            //"_0210_HTML_PROFILE_PROG_OPTIMISM",
          lc[210] = "Similarity detection optimism on program subelements: $1.";
            //"_0211_HTML_PROFILE_CLASS_OPTIMISM",
          lc[211] = "Similarity detection optimism on class subelements: $1.";
            //"_0212_HTML_PROFILE_METHOD_OPTIMISM",          
          lc[212] = "Similarity detection optimism on method subelements: $1.";
            //"_0213_HTML_PROFILE_INFORMATION",          
          lc[213] = "Profile information:";
            //"_0214_HTML_PROFILE_LIMIT",          
          lc[214] = "Maximum to explore out of a program: $1.";
            //"_0215_HTML_MATCHED_PROGRAMS",          
          lc[215] = "----";
            //"_0216_HTML_SIMILARITY",          
          lc[216] = "Similarity";
            //"_0217_HTML_PROFILE_MINMAX_BLUE",          
          lc[217] = "(blue)";
            //"_0218_HTML_RESULTS",          
          lc[218] = "Results:";
            //"_0219_HTML_SIMILARITY_RANK",          
          lc[219] = "Similarity rank";
            //"_0220_HTML_COMPARED_PROGRAMS",          
          lc[220] = "Matched programs:";
            //"_0221_HTML_NAME",          
          lc[221] = "Name";
            //"_0222_HTML_SIZE",          
          lc[222] = "Instructions";
            //"_0223_HTML_MATCHED_SUBELEMENTS",          
          lc[223] = "Subelements matches:";
            //"_0224_HTML_FOUND_SIMILARITY",          
          lc[224] = "Found similaritiy:";
            //"_0225_HTML_NAME_SIZE",          
          lc[225] = "----";
            //"_0226_HTML_TYPE",
          lc[226] = "Type";
            //"_0227_HTML_TYPE_BLOCK",
          lc[227] = "Block";
            //"_0228_HTML_TYPE_METHOD",
          lc[228] = "Method";
            //"_0229_HTML_TYPE_CLASS",        
          lc[229] = "Class";
            //"_0230_HTML_COMPARED_ELEMENTS",          
          lc[230] = "Matched elements:";
            //"_0231_HTML_PARENT_PROGRAM",  
          lc[231] = "% of superelement";
            //"_0232_HTML_PARENT_PROGRAM",  
          lc[232] = "% of program";
            //"_0233_HTML_CODE",  
          lc[233] = "----";
            //"_0234_HTML_CODE2",  
          lc[234] = "----";
            //"_0235_HTML_SHOWN_ELEMENTS",
          lc[235] = "Shown elements:";          
            //"_0236_HTML_SOURCE_CODE",
          lc[236] = "Source code:"; 
            //"_0237_HTML_BYTECODE",
          lc[237] = "Bytecode:"; 
            //"_0238_HTML_NOT_AVAILABLE",
          lc[238] = "Not available."; 
            //"_0239_CLI_HELP_43",
          lc[239] = "----";
            //"_0240_CLI_EXPORT_RRHTML_START",
          lc[240] = "----";
            //"_0241_CLI_EXPORT_RRHTML_OK",
          lc[241] = "----";
            //"_0242_CLI_EXPORT_RRHTML_EXISTS",
          lc[242] = "----";
            //"_0243_CLI_EXPORT_RRHTML_ERROR",
          lc[243] = "----";
            //"_0244_CLI_EXPORT_RRHTML_EXISTS_2",
          lc[244] = "----";
            //"_0245_CLI_HELP_44",
          lc[245] = " -y/--mind-size           Mind programs sizes for results ordering.";
            //"_0246_CLI_CANT_DUMP_BATTERY_IF_NOT_LOADED",
          lc[246] = "Can't dump battery to: $1 (battery not loaded).";
            //"_0247_CLI_ONLY_A_PROGRAM",
          lc[247] = "Can't compare only a program in a non-reflexive way.";
            //"_0248_CLI_EVENT_ERROR_ABORTANDO_POR_ERROR",
          lc[248] = "[*] Program load aborted due to compiling/disassembling error: $1.";
            //"_0249_CLI_HELP_45",
          lc[249] = " -u/--results-limit <n>   Export information only about the most relevant n"+rn+
                    "                            results.";
            //"_0250_CLI_LIMITE_NO_VALIDO",
          lc[250] = "Not a valid limit: $1.";
            //"_0251_HTML_PROFILE_METHOD_MULTIMATCH_YES",      
          lc[251] = "One-to-many matchings allowed for methods.";
            //"_0252_HTML_PROFILE_METHOD_MULTIMATCH_NO",          
          lc[252] = "One-to-one matchings allowed for methods.";
            //"_0253_HTML_PROFILE_CLASS_MULTIMATCH_YES",          
          lc[253] = "One-to-many matchings allowed for classes.";
            //"_0254_HTML_PROFILE_CLASS_MULTIMATCH_NO",          
          lc[254] = "One-to-one matchings allowed for clases.";
            //"_0255_HTML_PROFILE_CLASS_SAMEMATCH_YES",          
          lc[255] = "Only classes with the same name can be matched.";
            //"_0256_HTML_PROFILE_CLASS_SAMEMATCH_NO",          
          lc[256] = "Classes with different names can be matched.";
            //"_0257_HTML_PROFILE_METHOD_SAMEMATCH_YES",          
          lc[257] = "Only methods with the same name and class can be matched.";
            //"_0258_HTML_PROFILE_METHOD_SAMEMATCH_NO",          
          lc[258] = "Methods with different name or class can be matched.";
            //"_0259_HTML_PROFILE_METHOD_CLASS_SAMEMATCH_YES",          
          lc[259] = "Only methods contained on classes with the same name can be matched.";
            //"_0260_HTML_PROFILE_METHOD_CLASS_SAMEMATCH_NO",          
          lc[260] = "It isn't neccesary for two methods to be contained on classes with the same name for them to be matched.";
            //"_0261_HTML_PROFILE_PROG_DIF",  
          lc[261] = "Maximum difference ratio admitted for comparing programs: $1.";
            //"_0262_HTML_ELEMENTO_NO_EMPAREJADO",
          lc[262] = "Element not matched.";
            //"_0263_CLI_EVENT_ERROR_COMPILANDO_MSG",
          lc[263] = "[*] Trace: $1.";
            //"_0264_CLI_HELP_46",
          lc[264] = " --hide-errors            Hide compile errors.";
            //"_0265_HTML_PAQUETES",                   
          lc[265] = "View packages";          
            //"_0266_HTML_PAQUETES_PROGRAMA",                   
          lc[266] = "Packages of program $1:";
            //"_0267_HTML_PAQUETES_NOMBRE",                   
          lc[267] = "Package name";
            //"_0268_HTML_PAQUETES_SIN_NOMBRE",                   
          lc[268] = "no name";
            //"_0269_HTML_PACK",  
          lc[269] = "($1)";
            //"_0270_HTML_MATCHED_PROGRAMS_I",          
          lc[270] = "Matched programs";
            //"_0271_HTML_MATCHED_PROGRAMS_I_INSTRUCTIONS",          
          lc[271] = "Instructions";
            //"_0272_HTML_PAQUETES_MOSTRANDO",          
          lc[272] = "Showing only matches related to package $1 of program $2.";
            //"_0273_HTML_INFLUENCIA",          
          lc[273] = "Influence";
            //"_0274_HTML_PAQUETES_TODOS",                  
          lc[274] = "View all matches of all packages.";
            //"_0275_HTML_PORCENTAJE_SUPERELEMENTO",                  
          lc[275] = "% super.";
            //"_0276_HTML_PORCENTAJE_PADRE",                  
          lc[276] = "% prog.";
            //"_0277_HTML_INSTRUCTIONS_SHORT",                  
          lc[277] = "n. inst.";
            //"_0278_HTML_LEYENDA_COLOR",                  
          lc[278] = "Color meaning (similarity rank)";
            //"_0279_CLI_HELP_47",
          lc[279] = " -k/--export-diff <dir>   Make diff format results and save them to the"+rn+
                    "                            specified directory.";
            //"_0280_CLI_HELP_48",
          lc[280] = " --diff-simil <real>      Use given minimum similarity as difference threshold"+rn+
                    "                            in diff format results (0.0-100.0).";
            //"_0281_CLI_EXPORT_DIFF_START",
          lc[281] = "Exporting diff format results to directory: $1.";
            //"_0282_CLI_EXPORT_DIFF_OK",
          lc[282] = "diff format results exported successfully in $2s to directory: $1.";
            //"_0283_CLI_EXPORT_DIFF_EXISTS",
          lc[283] = "Already exists diff format results target (-f to overwrite): $1.";
            //"_0284_CLI_EXPORT_DIFF_EXISTS_2",
          lc[284] = "Can't overwrite directory with diff format results (delete manually): $1.";
            //"_0285_CLI_EXPORT_DIFF_ERROR",
          lc[285] = "Error exporting diff format results to directory: $1.";
            //"_0286_CLI_SIMIL_NO_VALIDA",
          lc[286] = "Not a valid similarity: $1.";
            //"_0287_HTML_PROFILE_BLOCK_MULTIMATCH_YES",      
          lc[287] = "One-to-many matchings allowed for blocks.";
            //"_0288_HTML_PROFILE_BLOCK_MULTIMATCH_NO",          
          lc[288] = "One-to-one matchings allowed for blocks.";
            //"_0289_CLIP_HELP_1"
          lc[289] = "Usage: java -jar $1 <arguments>";
            //"_0290_CLIP_WELCOME_4",
          lc[290] = "JSimilProfile - JSimil Profile Manager.";
            //"_0291_CLIP_VERSION",
          lc[291] = "Version:";
            //"_0292_CLIP_AUTOR",
          lc[292] = "Author:";
            //"_0293_CLIP_WEB",
          lc[293] = "Homepage:";
            //"_0294_CLIP_DESARROLLADO",
          lc[294] = "Developed at:";
            //"_0295_CLIP_PROYECTO",
          lc[295] = "End of career project 2007-2010";
            //"_0296_CLIP_UGR",
          lc[296] = "Universidad de Granada (Spain)";
            //"_0297_CLIP_CERRAR",
          lc[297] = "Close";
            //"_0298_CLIP_ACERCA",
          lc[298] = "About...";
            //"_0299_CLIP_AYUDA",
          lc[299] = "Help";
            //"_0300_CLIP_FICHERO",
          lc[300] = "File";
            //"_0301_CLIP_SALIR",
          lc[301] = "Exit";
            //"_0302_CLIP_SALIR_DESC",
          lc[302] = "Exit the application";
            //"_0303_CLIP_ACERCA_DESC",
          lc[303] = "Show the application information dialog";
            //"_0304_CLIP_CERRAR_DESC",
          lc[304] = "Close the dialog";
            //"_0305_CLIP_CARGAR",
          lc[305] = "Load profile";
            //"_0306_CLIP_CARGAR_DESC",
          lc[306] = "Load a profile from a file";
            //"_0307_CLIP_GUARDAR_COMO",
          lc[307] = "Save profile as";
            //"_0308_CLIP_GUARDAR_COMO_DESC",
          lc[308] = "Save profile to chosen file";
            //"_0309_CLIP_EXPORTAR_HUELLA",
          lc[309] = "Export profile fingerprint";
            //"_0310_CLIP_GUARDAR_DESC",
          lc[310] = "Save profile fingerprint image to a file";
            //"_0311_CLIP_ERROR",
          lc[311] = "Error loading profile";
            //"_0312_CLIP_PERFIL_CARGA_TITULO",
          lc[312] = "Choose a profile file to be loaded";
            //"_0313_CLIP_PERFIL_CARGA_BOTON",
          lc[313] = "Load";
            //"_0314_CLIP_PERFIL_CARGA_BOTON_DESC",
          lc[314] = "Load chosen file profile";
            //"_0315_CLIP_PERFIL_EXTENSION",        
          lc[315] = "JSimil Profile File";
            //"_0316_CLIP_PERFIL_CARGADO",        
          lc[316] = "Profile loaded.";
            //"_0317_CLIP_PERFIL_CARGADO_2",        
          lc[317] = "Profile loaded";
            //"_0318_CLIP_PERFIL_GUARDA_TITULO",
          lc[318] = "Choose a file for the profile to be saved";
            //"_0319_CLIP_PERFIL_GUARDA_BOTON",
          lc[319] = "Save";
            //"_0320_CLIP_PERFIL_GUARDA_BOTON_DESC",
          lc[320] = "Save profile to chosen file";
            //"_0321_CLIP_PERFIL_GUARGADO",        
          lc[321] = "Profile saved.";
            //"_0322_CLIP_PERFIL_GUARGADO_2",        
          lc[322] = "Profile saved";          
            //"_0323_CLIP_ERROR_GUARDANDO",
          lc[323] = "Error saving profile";    
            //"_0324_CLIP_PERFIL_NUEVO",
          lc[324] = "New profile";
            //"_0325_CLIP_PERFIL_NUEVO_DESC",
          lc[325] = "Start creating a new profile from scratch";
            //"_0326_CLIP_PERFIL_DEFECTO",
          lc[326] = "Default profile";
            //"_0327_CLIP_PERFIL_DEFECTO_DESC",
          lc[327] = "Start creating a new profile from the default one";
            //"_0328_CLIP_PERFIL_DEFECTOREFLEX",
          lc[328] = "Default reflexive profile";
            //"_0329_CLIP_PERFIL_DEFECTOREFLEX_DESC",
          lc[329] = "Start creating a new profile from the default reflexive one";
            //"_0330_CLIP_PERFIL_MODIFTITULO",
          lc[330] = "Changes haven't been saved";
            //"_0331_CLIP_PERFIL_MODIFTEXTO",          
          lc[331] = "Unsaved changes will be lost. Are you sure?";
            //"_0332_CLIP_PERFIL_HUELLA",
          lc[332] = "Profile fingerprint";
            //"_0333_CLIP_PERFIL_PROPIEDADES",
          lc[333] = "Profile properties";
            //"_0334_CLIP_GUARDAR",
          lc[334] = "Save profile ";
            //"_0335_CLIP_GUARDAR_DESC",
          lc[335] = "Save profile to last file";
            //"_0336_CLIP_PERFIL_MODIFTEXTOSAL",          
          lc[336] = "Unsaved changes will be lost. Do you want to save them before exiting?";
            //"_0337_CLIP_ATRIB_REFLEXIVO_SI",          
          lc[337] = "Match elements of a program with themselves";
            //"_0338_CLIP_ATRIB_REFLEXIVO_NO",              
          lc[338] = "Match elements of differents programs";
            //"_0339_CLIP_ATRIB_REFLEXIVO_DESCRIPCION",              
          lc[339] = "The reflexive matching option lets you choose whether to match different programs by matching the elements of one of them with the elements of the other of them, or whether to match individual programs with themselves, in which case the elements of a program are matched with other elements of the same program.\n\n"+
                    "You can see that the reflexive matching (each program with itself) is a lot faster, as it is just a processing task per program, while for the not reflexive matching (n^2-n)/2 matches are to be done.\n\n"+
                    "Typically you may want to compare different programs, so the default value for this option is \""+lc[338]+"\".";
          //"_0340_CLIP_EDITANDO",              
          lc[340] = "Editing";
            //"_0341_CLIP_EDITANDO_MEMORIA",              
          lc[341] = "[not saved profile]";
            //"_0342_CLIP_NO_MODIFICADO",              
          lc[342] = "Profile matches the saved one";
            //"_0343_CLIP_MODIFICADO",              
          lc[343] = "Profile doesn't match the saved one";
            //"_0344_CLIP_SOBREESCRIBIR_TITULO",              
          lc[344] = "Overwrite file";
            //"_0345_CLIP_SOBREESCRIBIR",              
          lc[345] = "Chosen file exists. ¿Do you want to overwrite it?";
            //"_0346_CLIP_TAB_GENERAL",           
          lc[346] = "General";
            //"_0347_CLIP_PROP_VELOCIDAD",
          lc[347] = "Speed";
            //"_0348_CLIP_PROP_DETALLE",
          lc[348] = "Detail";
            //"_0349_CLIP_PROP_PRECISION",
          lc[349] = "Precision";
            //"_0350_CLIP_PROP_SENSIBILIDAD",
          lc[350] = "Sensibility";
            //"_0351_CLIP_PROP_ASIMILACION",
          lc[351] = "Assimilation";
            //"_0352_CLIP_PROP_ESPECIALIZACION",
          lc[352] = "Specialization";
            //"_0353_CLIP_PROP_VELOCIDAD_DESC",
          lc[353] = "Speed measures the usage of processing time reducing options";
          //"_0354_CLIP_PROP_DETALLE_DESC",
          lc[354] = "Detail measures the quantity of information that will be shown";
            //"_0355_CLIP_PROP_PRECISION_DESC",
          lc[355] = "Precision measures how small will be the error margin of the results";
            //"_0356_CLIP_PROP_SENSIBILIDAD_DESC",
          lc[356] = "Sensibility measures how much partial results (positive or negative) influence global results";
            //"_0357_CLIP_PROP_ASIMILACION_DESC",
          lc[357] = "Assimilation measures the use of knowledge of the data for better results";
            //"_0358_CLIP_PROP_ESPECIALIZACION_DESC",
          lc[358] = "Specialization measures the amount of options taken to the limit";
            //"_0359_CLIP_HUELLA_EXTENSION",        
          lc[359] = "Portable Network Graphics";
            //"_0360_CLIP_Huella_GUARDA_TITULO",
          lc[360] = "Choose a file for the profile fingerprint to be saved.";
            //"_0361_CLIP_HUELLA_GUARDA_BOTON",
          lc[361] = "Export";
            //"_0362_CLIP_HUELLA_GUARDA_BOTON_DESC",
          lc[362] = "Export profile fingerprint to chosen file";
            //"_0363_CLIP_HUELLA_GUARGADO",           
          lc[363] = "Profile fingerprint exported.";
            //"_0364_CLIP_HUELLA_GUARGADO_2",        
          lc[364] = "Profile fingerprint exported";        
            //"_0365_CLIP_ERROR_GUARDANDO_HUELLA",
          lc[365] = "Error exporting profile fingerprint";
            //"_0366_CLI_HUELLASAVE_NOT_OK_ERR",
          lc[366] = "Couldn't export profile fingerprint to file: $1 (error writing).";
            //"_0367_CLIP_ATRIB_DIFFERENCE_NO",
          lc[367] = "Always match the best matching elements";
            //"_0368_CLIP_ATRIB_DIFFERENCE_SI",              
          lc[368] = "Always match the worst matching elements";
            //"_0369_CLIP_ATRIB_DIFFERENCE_DESCRIPCION",
          lc[369] = "The matching objective option lets you specify either if best possible matches or worst possible matches must be found.\n\n"+
                    "Note that if the option \""+lc[368]+"\" is chosen, meaning of options of the matchings tab es inverted (maximum similarity).\n\n"+
                    "Typically you may want to obtain the best possible matches, so the default value for this option is \""+lc[367]+"\".\n\n";
            //"_0370_CLIP_ATRIB_RETURNNULL",          
          lc[370] = "Generate also results for not matched elements";
            //"_0371_CLIP_ATRIB_RETURNNULL_DESCRIPCION",
          lc[371] = "The generate results for not matched elements option lets you specify if you want information about which elements were not matched due to the restrictions or conditions of the profile.\n\n"+
                    "Typically you may want to obtain information only about the matched elements, so the default value for this option is not generating results for not matched elements.";
            //"_0372_CLIP_ATRIB_CLASSSAMENAME",          
          lc[372] = "Match classes only if they have the same name";
            //"_0373_CLIP_ATRIB_CLASSSAMENAME_DESCRIPCION",
          lc[373] = "The matching only classes that have the same name option lets you specify if any two classes can be matched or if only classes that have the same name can be matched.\n\n"+
                    "If you know that in a set of programs classes have the same name, it is advisable to check this option as it reduces false positives and increases the speed of processing.\n\n"+
                    "Typically it is unknown if classes on different programs have the same name, so the default value for this option is match any classes.";
            //"_0374_CLIP_ATRIB_METHODOFCLASSSAMENAME",          
          lc[374] = "Match methods only if they're in classes with the same name";
            //"_0375_CLIP_ATRIB_METHODOFCLASSSAMENAME_DESCRIPCION",
          lc[375] = "The matching only methods that are contained into classes that have the same name option lets you specify if any two methods can be matched or if only methods that are contained in classes that have the same name can be matched.\n\n"+
                    "If you know that in a set of programs classes have the same name, it is advisable to check this option as it reduces false positives and increases the speed of processing.\n\n"+
                    "This option is also interesting in reflexive matching to limit the matches of methods of a program to those that can found in the same classes.\n\n"+
                    "Typically it is unknown if classes on different programs have the same name, so the default value for this option is match any methods.";
            //"_0376_CLIP_ATRIB_METHODSAMENAME",          
          lc[376] = "Match methods only if they have the same name";
            //"_0377_CLIP_ATRIB_METHODSAMENAME_DESCRIPCION",
          lc[377] = "The matching only methods that have the same name option lets you specify if any two methods can be matched or if only methods that have the same name can be matched.\n\n"+
                    "If you know that in a set of programs classes have the same name, it is advisable to check this option as it reduces false positives and increases the speed of processing.\n\n"+
                    "Typically it is unknown if methods on different programs have the same name, so the default value for this option is match any methods.";
            //"_0378_CLIP_ATRIB_CLASSALLOWMULTIMATCH",          
          lc[378] = "Allow multiple matchings for classes";
            //"_0379_CLIP_ATRIB_CLASSALOWMULTIMATCH_DESCRIPCION",
          lc[379] = "The multiple classes matchings options let you specify whether a class can be matched with at most only one or with at most several other classes.\n\n"+
                    "Typically it is unknown if a class might be similar to several other classes, so the default value for this option is to allow multiple matchings for classes.";
            //"_0380_CLIP_ATRIB_METHODALLOWMULTIMATCH",          
          lc[380] = "Allow multiple matchings for methods";
            //"_0381_CLIP_ATRIB_METHODALOWMULTIMATCH_DESCRIPCION",
          lc[381] = "The multiple methods matchings options let you specify whether a method can be matched with at most only one or with at most several other methods.\n\n"+
                    "Typically it is unknown if a method might be similar to several other methods, and in fact it can give relevant information about possible refactorizations, so the default value for this option is to allow multiple matchings for methods.";
            //"_0382_CLIP_ATRIB_BLOCKALLOWMULTIMATCH",          
          lc[382] = "Allow multiple matchings for blocks";
            //"_0383_CLIP_ATRIB_BLOCKALOWMULTIMATCH_DESCRIPCION",
          lc[383] = "The multiple block matchings options let you specify whether a block can be matched with at most only one or with at most several other blocks.\n\n"+
                    "Typically it is unknown if a block might be similar to several other methods, and in fact it can give relevant information about possible refactorizations or copy&paste detection, so the default value for this option is to allow multiple matchings for blocks.";
            //"_0384_CLIP_ATRIB_PROGMATCHMINMAX_DESCRIPCION",
          lc[384] = "The programs for which to generate detailed results option lets you specify in which programs will be focused the processing.\n\n"+
                    "It is reasonable to spend more time obtaining better information on pairs of programs that are within a certain range of similarity, usually greater than a threshold. This will reduce processing time without sacrificing interesting results.\n\n"+
                    "It should be noted that all programs will be matched until any of these three conditions satisfies:\n"+                                                            "a) It is guaranteed that the matching similarity is going to be under the minimum similarity.\n"+                                                                  "b) It is guaranteed that the matching similarity is going to be over the maximum similarity.\n"+                                                                   "c) It is guaranteed that the matching similarity is going to be between the minimum and maximum similarities.\n"+                                                  "Furthermore, only in the latest case, the matching will keep being done.\n\n"+                                                                                     "Typically the interest focus on significant matchings with a wide rank of similarity, so the default value for this option is the rank 50%-100%.";
            //"_0385_CLIP_ATRIB_PROGMATCHERROR_DESCRIPCION",
          lc[385] = "The maximum error of similarity lets you specify the precision of the similarity for program matches that are within the range of similarity for which to obtain detailed results.\n\n"+
                    "If the value of this option is greater than the similarity rank, the results obtained will only difference programs inside the rank and programs outside the rank.\n\n"+
                    "Note that the lower the maximum error is, the more thoroughly matching will be made, and more results, not neccesarily more significant, will be obtained.\n\n"+
                    "Typically you want to obtain information with reasonable precision, which is not too small so as not to generate too many non significant results, but neither too large, so you're able to distinguish between different degrees of similarity in the results, so the default value for this option is 2%. This means that if you get a final similarity of 95%, the effective range of similarity is 93%-97%.";
            //"_0386_CLIP_ATRIB_PROGMATCHMINMAX",
          lc[386] = "Generate detailed results for programs with similitarities between";
            //"_0387_CLIP_ATRIB_PROGMATCHMINMAX_MIN",
          lc[387] = "Minimum similarity";
            //"_0388_CLIP_ATRIB_PROGMATCHMINMAX_MAX",
          lc[388] = "Maximum similarity";
            //"_0389_CLIP_ATRIB_PROGMATCHERROR",
          lc[389] = "For these programs, the maximum error of the similarity must be";
            //"_0390_CLIP_ATRIB_PROGMATCHERROR_ERROR",
          lc[390] = "Maximum error";
            //"_0391_CLIP_ATRIB_BLOCKWEIGHT_DESC",
          lc[391] = "Weights values of block attributes based on instruction counts";
            //"_0392_CLIP_ATRIB_BLOCKWEIGHT_METHODSTART",
          lc[392] = "First block?";
            //"_0393_CLIP_ATRIB_BLOCKWEIGHT_METHODEND",
          lc[393] = "Last block?";
            //"_0394_CLIP_ATRIB_BLOCKWEIGHT_INSTRUCTIONCOUNT",
          lc[394] = "Total # inst.";
            //"_0395_CLIP_ATRIB_BLOCKWEIGHT_TAB",
          lc[395] = "Weighting";
            //"_0396_CLIP_ATRIB_BLOCKWEIGHT_AYUDA",
          lc[396] = "These options allow you to specify the weights for the attributes of the blocks while matching.\n\n"+
                    "If a weight is 0, the correspondent attribute will not be considered while matching two blocks.\n\n"+
                    "Moreover, every value is relative to the others, which means that if for a kind of instruction the value is 1.00 and for another kind of instruction the value is 2.00, the latest one will be twice as important as the first one in a block.\n\n"+
                    "Typically you want a distribution of weights inversely proportional to the average possibility of a kind of instruction to appear in any block, so the default values for these options are an approximation of this distribution.\n\n"+
                    "Meaning of fields:\n"+
                    "- \""+lc[392]+"\" means that the block is the first of a method, in other words, the only entrance to a method. This value will count as 1.00 if the block is first and will count as 0.00 if the block is not first.\n"+
                    "- \""+lc[393]+"\" means that the block is last of a method, that means it ends in a return instruction.\n"+
                    "- \""+lc[394]+"\" means the total number of instructions of the block, which is a way to quantify its size\n"+
                    "- All other fields mean the number of those specific instructions that are on the block.";
            //"_0397_CLIP_ATRIB_MINIMUMINSTRUCTION_AYUDA",
          lc[397] = "The ignore elements with less instructions than given options let you specify thresholds on the numbers of instructions an element should have for it to be considered in the processing.\n\n"+
                    "Raising the values of these options will mean less processing time and a reduction in false positives at the expense of a progressive and proportional to these values loss of the results precision.\n\n"+
                    "Very high values will provide a very specific validity results.\n\n"+
                    "Typically you may wish to ignore classes, methods and blocks not significant in the results due to they extension, so the default values for this option are 20, 10 and 2 respectively.";
            //"_0398_CLIP_ATRIB_MINIMUMINSTRUCTION",
          lc[398] = "Ignore elements of a certain type with less instructions than specified";
            //"_0399_CLIP_ATRIB_MINIMUMINSTRUCTION_CLASS",
          lc[399] = "Type class";
            //"_0400_CLIP_ATRIB_MINIMUMINSTRUCTION_METHOD",
          lc[400] = "Type method";
            //"_0401_CLIP_ATRIB_MINIMUMINSTRUCTION_BLOCK",
          lc[401] = "Type block";
            //"_0402_CLIP_ATRIB_RESTRICCIONES",
          lc[402] = "Restrictions";          
            //"_0403_CLIP_ORIENTACION_TAB",
          lc[403] = "Orientation";
            //"_0404_CLIP_OPTIMISM",
          lc[404] = "Influence of similarity of the match of complex into simple element against opposite approach";
            //"_0405_CLIP_CLASEYMETODOSINCLASE",
          lc[405] = "Program method or class";
            //"_0406_CLIP_METODOENCLASE",
          lc[406] = "From class method";
            //"_0407_CLIP_BLOQUE",
          lc[407] = "Block";
            //"_0408_CLIP_THRESHOLD",
          lc[408] = "Try to match classes that represent portions of the program between";
            //"_0409_CLIP_PMIN",
          lc[409] = "Minimum percentage";
            //"_0410_CLIP_PMAX",
          lc[410] = "Maximum percentage";
            //"_0411_CLIP_DIFFERENCE",
          lc[411] = "Maximum size difference proportion for trying to match two elements";
            //"_0412_CLIP_PROGRAMA",
          lc[412] = "Program";
            //"_0413_CLIP_CLASE",
          lc[413] = "Class";
            //"_0414_CLIP_LIMIT",
          lc[414] = "Stop searching after matching at least given percentage of two elements";
            //"_0415_CLIP_METODO",
          lc[415] = "Method";
            //"_0416_CLIP_ACCEPTABLE",
          lc[416] = "Minimum similarity for matching inmediately two elements, ignoring the rest of possibilities";
            //"_0417_CLIP_MINIMUM",
          lc[417] = "Minimum similarity neccesary to accept the match of two elements";
            //"_0418_CLIP_OPTIMISM_HELP",
          lc[418] = "The influence of similarity options let you specify whether to use an approximation of framing of the simple element (fewer subelements) in the most complex, or the opposite one, or both approximations averaged with weight values.\n\n"+
                    "If you use a 0 value, the most pessimistic match will be made (subelements of the complex elements matched into subelements of the simple elements). This approach will give lower similarities and reduced false positives.\n\n"+
                    "If you use a 1 value, the most optimistic match will be made (subelements of the simple elements matched into subelements of the complex elements). This approach will give higher similarities and more false positives.\n\n"+
                    "If you use a value between 0 and 1, both matchings will be made, and the final similarity will be (100%-influence*pessimistic)+(influence*optimistic).\n\n"+
                    "Note that when using these last values, processing time will be double than with any of the two first approximations.\n\n"+
                    "Typically you want the matches to be quick and with not many false positives, so the default value for this option is 0.";
            //"_0419_CLIP_THRESHOLD_HELP",
          lc[419] = "The matching classes that represent a certain portion of the program lets you define which classes will be tried to match as classes and which ones will be broken down into subelements.\n\n"+
                    "Note that in any case, a class that has not be efectively matched with another will be decomposed into subelements.\n\n"+
                    "The use of this option can reduce processing time if matches are made between classes. On the other hand it can increase it if not many classes matches are made.\n\n"+
                    "Typically you want clarity in the results, so the default value for this option is to try to match all classes of the program.";
            //"_0420_CLIP_DIFFERENCE_HELP",
          lc[420] = "The maximum proportional size difference options let you specify thresholds in such a way that two elements will be matched only if their size difference doesn't surpass that threshold.\n\n"+
                    "As this threshold increases, more matches are allowed.\n\n"+
                    "It is reasonable to leave a margin for the matchings, but if the threshold is too big you will lose time while trying to match elements that hardly will get high similarities.\n\n"+
                    "Typically you may wish a fast processing while checking most significant elements, so by default this option has a value of 40%.";
            //"_0421_CLIP_LIMIT_HELP",
          lc[421] = "The limit matching options let you decide a maximum percentage of similarity found after which element matching stops.\n\n"+
                    "That is, if while an element is being matched, its similarity surpasses the threshold, matching stops.\n\n"+
                    "As elements are matched ordered by size, this is a reasonable option that will decrease processing time without sacrificing results.\n\n"+
                    "Typically you may wish a compromise between speed and correctness of the results, thus the default value for this option is 90% similarity.";
            //"_0422_CLIP_ACCEPTABLE_HELP",
          lc[422] = "The minimum similarity for inmediate matching options let you specify a similarity threshold that, when passed while trying to match two elements will lead to stop trying any other matchings for those elements.\n\n"+
                    "As this threshold increases you will get worse matchings and a decreased processing time.\n\n"+
                    "However, ignoring the results themselves and considering only the similarity resulting from the programs matchings, it is reasonable to use this option.\n\n"+
                    "Typically you want a compromise between speed and correctness of the results, so the default value of this option is to automatically accept matches with similarity greater than or equal to 95%.";
            //"_0423_CLIP_MINIMUM_HELP",
          lc[423] = "The minimum similarity for matching two elements options let you specify a threshold of similarity that has to be surpassed for a matching to be considered effective.\n\n"+
                    "As this threshold increases, less matchings are generated and the false positive number is decreased.\n\n"+
                    "Typically you may wish to obtain results with a minimum reasonable similarity, while also being able to see some most significating partial matches. That's why the default value for this option is 85% for classes and 30% for any other element.";
            //"_0424_CLIP_UMBRALES_TAB",
          lc[424] = "Thresholds";
            //"_0425_CLIP_EMPAREJAMIENTOS_TAB",
          lc[425] = "Matchings";
            //"_0426_CLIP_METODOSINCLASE",
          lc[426] = "Program method";
            //"_0427_CLIP_HUELLA_HELP",
          lc[427] = "Profile fingerprint is a graphical representation of the profile properties.\n\n"+
                    "It gives rapid visual feedback and will help you detect possible mistakes you made during profile creation.";
            //"_0428_CLIP_ATRIBS_HELP",
          lc[428] = "Profile properties provide a summary of various aspects of the profile\n\n"+                                                                                       "These are the meanings of the properties:\n"+
                    "- "+lc[353]+".\n"+
                    "- "+lc[354]+".\n"+
                    "- "+lc[356]+".\n"+
                    "- "+lc[355]+".\n"+
                    "- "+lc[358]+".\n"+
                    "- "+lc[357]+".";
            //"_0429_CLIP_ENTRA",
          lc[429] = "\n      Welcome to JSimil Profile Manager!\n\nI'm the contextual help, i'll explain and give examples about the available profile options when you start modifying them.";
            //"_0430_CLI_HELP_49",
          lc[430] = " -z/--threads <num>       Number of threads to use in load and processing.";

        }
        else
            throw new NoSuchLanguageException(lang);
      for (i = 0;i < size;++i) {
          if (cod[i].contains("_HTML_")) {
              String sa = lc[i];
              sa = sa.replace("&","&amp;");
              sa = sa.replace("<","&lt;");
              sa = sa.replace(">","&gt;");
              sa = sa.replace("\"","&quot;");
              sa = sa.replace("'","&#039;");
              sa = sa.replace("(","&#040;");
              sa = sa.replace(")","&#041;");
              sa = sa.replace("/","&#047;");
              sa = sa.replace("\\","&#092;"); 
              lc[i] = sa;
          }              
      }        
        if (lang.equals("es") || lang.equals("est")) {
              for (i = 0;i < size;++i) {
                  if (cod[i].contains("_HTML_")) {
                      String sa = lc[i];
                      sa = sa.replace("á","&aacute;");
                      sa = sa.replace("é","&eacute;");
                      sa = sa.replace("í","&iacute;");
                      sa = sa.replace("ó","&oacute;");
                      sa = sa.replace("ú","&uacute;");
                      sa = sa.replace("Á","&Aacute;");
                      sa = sa.replace("É","&Eacute;");
                      sa = sa.replace("Í","&Iacute;");
                      sa = sa.replace("Ó","&Oacute;");
                      sa = sa.replace("Ú","&Uacute;");
                      sa = sa.replace("ñ","&ntilde;");
                      sa = sa.replace("Ñ","&Ntilde;");
                      lc[i] = sa;
                  }              
              }
      }

    }
    
    /**
     * Devuelve el número de lenguajes disponibles.
     * @return Número de lenguajes disponibles.
     */
    static public int getLangsNum() {
        return langs.length;
    }
    
    /**
     * Devuelve el nombre del lenguaje solicitado.
     * @param i Número de lenguaje preguntado.
     * @return Nombre del lenguaje solicitado.
     */
    static public String getLangsCod(int i) throws IndexOutOfBoundsException {
        return langs[i];
    }
    
    /**
     * Devuelve el nombre del lenguaje solicitado.
     * @param i Número de lenguaje preguntado.
     * @return Nombre del lenguaje solicitado.
     */
    static public String getLangsName(int i) throws IndexOutOfBoundsException {
        return langn[i];
    }
    
    /**
     * Devuelve la frase del lenguaje con 0 parÃ¡metros solicitada.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i) {
        return lc[i];
    }

    /**
     * Devuelve la frase del lenguaje con 1 parÃ¡metros solicitada.
     * @param a1 Primer parÃ¡metro.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i,String a1) {
        return lc[i].replace("$1", a1);
    }

    /**
     * Devuelve la frase del lenguaje con 2 parÃ¡metros solicitada.
     * @param a1 Primer parÃ¡metro.
     * @param a2 Segundo parÃ¡metro.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i,String a1,String a2) {
        return lc[i].replace("$1", a1).replace("$2",a2);
    }

    /**
     * Devuelve la frase del lenguaje con 3 parÃ¡metros solicitada.
     * @param a1 Primer parÃ¡metro.
     * @param a2 Segundo parÃ¡metro.
     * @param a3 Tercer parÃ¡metro.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i,String a1,String a2,String a3) {
        return lc[i].replace("$1", a1).replace("$2",a2).replace("$3",a3);
    }

    /**
     * Devuelve la frase del lenguaje con 4 parÃ¡metros solicitada.
     * @param a1 Primer parÃ¡metro.
     * @param a2 Segundo parÃ¡metro.
     * @param a3 Tercer parÃ¡metro.
     * @param a4 Cuarto parÃ¡metro.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i,String a1,String a2,String a3,String a4) {
        return lc[i].replace("$1", a1).replace("$2",a2).replace("$3",a3)
                .replace("$4",a4);
    }

    /**
     * Devuelve la frase del lenguaje con 5 parÃ¡metros solicitada.
     * @param a1 Primer parÃ¡metro.
     * @param a2 Segundo parÃ¡metro.
     * @param a3 Tercer parÃ¡metro.
     * @param a4 Cuarto parÃ¡metro.
     * @param a5 Quinto parÃ¡metro.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i,String a1,String a2,String a3,String a4,
            String a5) {
        return lc[i].replace("$1", a1).replace("$2",a2).replace("$3",a3)
                .replace("$4",a4).replace("$5",a5);
    }

    /**
     * Devuelve la frase del lenguaje con 6 parÃ¡metros solicitada.
     * @param a1 Primer parÃ¡metro.
     * @param a2 Segundo parÃ¡metro.
     * @param a3 Tercer parÃ¡metro.
     * @param a4 Cuarto parÃ¡metro.
     * @param a5 Quinto parÃ¡metro.
     * @param a6 Sexto parÃ¡metro.
     * @return Frase del lenguaje solicitada.
     */
    public String getFrase(int i,String a1,String a2,String a3,String a4,
            String a5,String a6) {
        return lc[i].replace("$1", a1).replace("$2",a2).replace("$3",a3)
                .replace("$4",a4).replace("$5",a5).replace("$6",a6);
    }

    /**
     * Devuelve el código del idioma.
     * @return Código del idioma.
     */
    public String getCodigo() {
        return s;
    }

    /**
     * Devuelve el nombre del idioma.
     * @return Nombre del idioma.
     */
    public String getNombre() {
        return n;
    }
}
