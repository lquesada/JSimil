/*
 * JSimil. 2007-2010 Luis Quesada Torres.
 */

package com.jsimil.core;


/**
 * Generador de perfiles por defecto.
 * @author elezeta
 */
abstract public class DefaultJSimilProfileFactory {

    /**
     * Devuelve un perfil por defecto.
     * @return Perfil por defecto.
     */
    public static MatchingProfile defecto() {
        MatchingProfile n = new MatchingProfile();
        try {

            n.setValor("DifferenceSEARCH",0.0);
            n.setValor("ReflexiveMATCH",0.0);
            n.setValor("ReturnNullMatchesIfNULL",0.0);

            n.setValor("ProgMatchMIN",0.5);
            n.setValor("ProgMatchMAX",1.0);
            n.setValor("ProgMatchERROR",0.02);
            n.setValor("ProgMatchLIMIT",0.9);
            n.setValor("ProgMatchOPTIMISM",0.0);

            n.setValor("ProgMatchClassACCEPTABLE",0.95);
            n.setValor("ProgMatchClassDIFFERENCE",0.6);
            n.setValor("ProgMatchClassMatchMINIMUM",0.85);
            n.setValor("ProgMatchClassThresholdMAXIMUM",1.0);
            n.setValor("ProgMatchClassThresholdMINIMUM",0.0);
            n.setValor("ProgMatchDIFFERENCE",0.6);
            n.setValor("ProgMatchMethodACCEPTABLE",0.95);
            n.setValor("ProgMatchMethodDIFFERENCE",0.6);
            n.setValor("ProgMatchMethodMatchMINIMUM",0.3);

            n.setValor("ClassAllowMULTIMATCH",1.0);
            n.setValor("ClassConsiderOnlyMINIMUMINSTRUCTION",20.0);
            n.setValor("ClassMatchACCEPTABLE",0.95);
            n.setValor("ClassMatchDIFFERENCE",0.6);
            n.setValor("ClassMatchLIMIT",0.9);
            n.setValor("ClassMatchMethodMatchMINIMUM",0.3);
            n.setValor("ClassMatchOPTIMISM",0.0);
            n.setValor("ClassSAMENAMEMATCH",0.0);

            n.setValor("MethodAllowMULTIMATCH",1.0);
            n.setValor("MethodConsiderOnlyMINIMUMINSTRUCTION",10.0);
            n.setValor("MethodMatchACCEPTABLE",0.95);
            n.setValor("MethodMatchBlockMatchMINIMUM",0.3);
            n.setValor("MethodMatchDIFFERENCE",0.6);
            n.setValor("MethodMatchLIMIT",0.9);
            n.setValor("MethodMatchOPTIMISM",0.0);
            n.setValor("MethodSAMENAMEMATCH",0.0);
            n.setValor("MethodOfClassSAMENAMEMATCH",0.0);

            n.setValor("BlockAllowMULTIMATCH",1.0);
            n.setValor("BlockConsiderOnlyMINIMUMINSTRUCTION",2.0);
            n.setValor("BlockWeightADDCOUNT",1.0);
            n.setValor("BlockWeightANDCOUNT",1.0);
            n.setValor("BlockWeightCHECKCASTCOUNT",1.0);
            n.setValor("BlockWeightCONSTCOUNT",1.0);
            n.setValor("BlockWeightDIVCOUNT",1.0);
            n.setValor("BlockWeightDUPCOUNT",1.0);
            n.setValor("BlockWeightGETFIELDCOUNT",2.0);
            n.setValor("BlockWeightGETSTATICCOUNT",2.0);
            n.setValor("BlockWeightGOTOCOUNT",2.0);
            n.setValor("BlockWeightIFCOUNT",2.0);
            n.setValor("BlockWeightINCCOUNT",1.0);
            n.setValor("BlockWeightINSTRUCTIONCOUNT",0.2);
            n.setValor("BlockWeightINVOKEINTERFACECOUNT",5.0);
            n.setValor("BlockWeightINVOKESPECIALCOUNT",5.0);
            n.setValor("BlockWeightINVOKESTATICCOUNT",5.0);
            n.setValor("BlockWeightINVOKEVIRTUALCOUNT",5.0);
            n.setValor("BlockWeightJSRCOUNT",2.0);
            n.setValor("BlockWeightLDCCOUNT",1.0);
            n.setValor("BlockWeightLOADCOUNT",2.0);
            n.setValor("BlockWeightLOOKUPSWITCHCOUNT",1.0);
            n.setValor("BlockWeightMETHODEND",1.0);
            n.setValor("BlockWeightMETHODSTART",1.0);
            n.setValor("BlockWeightMONITORENTERCOUNT",5.0);
            n.setValor("BlockWeightMONITOREXITCOUNT",5.0);
            n.setValor("BlockWeightMULCOUNT",1.0);
            n.setValor("BlockWeightNEGCOUNT",1.0);
            n.setValor("BlockWeightNEWARRAYCOUNT",0.5);
            n.setValor("BlockWeightNEWCOUNT",0.5);
            n.setValor("BlockWeightORCOUNT",1.0);
            n.setValor("BlockWeightPOPCOUNT",2.0);
            n.setValor("BlockWeightPUSHCOUNT",2.0);
            n.setValor("BlockWeightPUTFIELDCOUNT",2.0);
            n.setValor("BlockWeightREMCOUNT",1.0);
            n.setValor("BlockWeightRETCOUNT",1.0);
            n.setValor("BlockWeightRETURNCOUNT",1.0);
            n.setValor("BlockWeightSHIFTLEFTCOUNT",1.0);
            n.setValor("BlockWeightSHIFTRIGHTCOUNT",1.0);
            n.setValor("BlockWeightSTORECOUNT",2.0);
            n.setValor("BlockWeightSUBCOUNT",1.0);
            n.setValor("BlockWeightSWAPCOUNT",1.0);
            n.setValor("BlockWeightTABLESWITCHCOUNT",4.0);
            n.setValor("BlockWeightTHROWCOUNT",1.0);
            n.setValor("BlockWeightXORCOUNT",1.0);
        } catch (JSimilException e) {
        }
        
        return n;
    }

    /**
     * Devuelve un perfil por defecto.
     * @return Perfil por defecto.
     */
    public static MatchingProfile defectoReflexivo() {
        MatchingProfile n = new MatchingProfile();
        try {
            n.setValor("DifferenceSEARCH",0.0);
            n.setValor("ReflexiveMATCH",1.0);
            n.setValor("ReturnNullMatchesIfNULL",0.0);

            n.setValor("ProgMatchMIN",0.5);
            n.setValor("ProgMatchMAX",1.0);
            n.setValor("ProgMatchERROR",0.02);
            n.setValor("ProgMatchLIMIT",0.9);
            n.setValor("ProgMatchOPTIMISM",0.0);

            n.setValor("ProgMatchClassACCEPTABLE",0.95);
            n.setValor("ProgMatchClassDIFFERENCE",0.6);
            n.setValor("ProgMatchClassMatchMINIMUM",0.85);
            n.setValor("ProgMatchClassThresholdMAXIMUM",1.0);
            n.setValor("ProgMatchClassThresholdMINIMUM",0.3);
            n.setValor("ProgMatchDIFFERENCE",0.6);
            n.setValor("ProgMatchMethodACCEPTABLE",0.95);
            n.setValor("ProgMatchMethodDIFFERENCE",0.6);
            n.setValor("ProgMatchMethodMatchMINIMUM",0.3);

            n.setValor("ClassAllowMULTIMATCH",1.0);
            n.setValor("ClassConsiderOnlyMINIMUMINSTRUCTION",20.0);
            n.setValor("ClassMatchACCEPTABLE",0.95);
            n.setValor("ClassMatchDIFFERENCE",0.6);
            n.setValor("ClassMatchLIMIT",0.9);
            n.setValor("ClassMatchMethodMatchMINIMUM",0.3);
            n.setValor("ClassMatchOPTIMISM",0.0);
            n.setValor("ClassSAMENAMEMATCH",0.0);

            n.setValor("MethodAllowMULTIMATCH",1.0);
            n.setValor("MethodConsiderOnlyMINIMUMINSTRUCTION",10.0);
            n.setValor("MethodMatchACCEPTABLE",0.95);
            n.setValor("MethodMatchBlockMatchMINIMUM",0.3);
            n.setValor("MethodMatchDIFFERENCE",0.6);
            n.setValor("MethodMatchLIMIT",0.9);
            n.setValor("MethodMatchOPTIMISM",0.0);
            n.setValor("MethodSAMENAMEMATCH",0.0);
            n.setValor("MethodOfClassSAMENAMEMATCH",0.0);

            n.setValor("BlockAllowMULTIMATCH",1.0);
            n.setValor("BlockConsiderOnlyMINIMUMINSTRUCTION",2.0);
            n.setValor("BlockWeightADDCOUNT",1.0);
            n.setValor("BlockWeightANDCOUNT",1.0);
            n.setValor("BlockWeightCHECKCASTCOUNT",1.0);
            n.setValor("BlockWeightCONSTCOUNT",1.0);
            n.setValor("BlockWeightDIVCOUNT",1.0);
            n.setValor("BlockWeightDUPCOUNT",1.0);
            n.setValor("BlockWeightGETFIELDCOUNT",2.0);
            n.setValor("BlockWeightGETSTATICCOUNT",2.0);
            n.setValor("BlockWeightGOTOCOUNT",2.0);
            n.setValor("BlockWeightIFCOUNT",2.0);
            n.setValor("BlockWeightINCCOUNT",1.0);
            n.setValor("BlockWeightINSTRUCTIONCOUNT",0.2);
            n.setValor("BlockWeightINVOKEINTERFACECOUNT",5.0);
            n.setValor("BlockWeightINVOKESPECIALCOUNT",5.0);
            n.setValor("BlockWeightINVOKESTATICCOUNT",5.0);
            n.setValor("BlockWeightINVOKEVIRTUALCOUNT",5.0);
            n.setValor("BlockWeightJSRCOUNT",2.0);
            n.setValor("BlockWeightLDCCOUNT",1.0);
            n.setValor("BlockWeightLOADCOUNT",2.0);
            n.setValor("BlockWeightLOOKUPSWITCHCOUNT",1.0);
            n.setValor("BlockWeightMETHODEND",1.0);
            n.setValor("BlockWeightMETHODSTART",1.0);
            n.setValor("BlockWeightMONITORENTERCOUNT",5.0);
            n.setValor("BlockWeightMONITOREXITCOUNT",5.0);
            n.setValor("BlockWeightMULCOUNT",1.0);
            n.setValor("BlockWeightNEGCOUNT",1.0);
            n.setValor("BlockWeightNEWARRAYCOUNT",0.5);
            n.setValor("BlockWeightNEWCOUNT",0.5);
            n.setValor("BlockWeightORCOUNT",1.0);
            n.setValor("BlockWeightPOPCOUNT",2.0);
            n.setValor("BlockWeightPUSHCOUNT",2.0);
            n.setValor("BlockWeightPUTFIELDCOUNT",2.0);
            n.setValor("BlockWeightREMCOUNT",1.0);
            n.setValor("BlockWeightRETCOUNT",1.0);
            n.setValor("BlockWeightRETURNCOUNT",1.0);
            n.setValor("BlockWeightSHIFTLEFTCOUNT",1.0);
            n.setValor("BlockWeightSHIFTRIGHTCOUNT",1.0);
            n.setValor("BlockWeightSTORECOUNT",2.0);
            n.setValor("BlockWeightSUBCOUNT",1.0);
            n.setValor("BlockWeightSWAPCOUNT",1.0);
            n.setValor("BlockWeightTABLESWITCHCOUNT",4.0);
            n.setValor("BlockWeightTHROWCOUNT",1.0);
            n.setValor("BlockWeightXORCOUNT",1.0);
        } catch (JSimilException e) {
        }
        
        return n;
    }

}
