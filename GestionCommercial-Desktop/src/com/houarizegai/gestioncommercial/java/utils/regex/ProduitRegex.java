package com.houarizegai.gestioncommercial.java.utils.regex;

public class ProduitRegex { // This class contains constant of regex validate for Produit information

    public final static String REFERENCE;
    public final static String GEN_CODE;
    public final static String CODE_BARRE;
    public final static String LIB_PROD;
    public final static String PRIX_HT;
    public final static String QTE_REAPPRO;
    public final static String QTE_MIN;

    static {
        REFERENCE = "[a-zA-Z0-9 ]{1,20}";
        GEN_CODE = "[a-zA-Z0-9]{1,40}";
        CODE_BARRE = "[a-zA-Z0-9]{1,40}";
        LIB_PROD = "[a-zA-Z0-9 -_]{1,40}";
        PRIX_HT = "[0-9]*\\.?[0-9]{1,6}";
        QTE_REAPPRO = "[0-9]{1,4}";
        QTE_MIN = "[0-9]{1,4}";
    }

}
