package com.houarizegai.gestioncommercial.java.utils.regex;

public class ClientRegex { // This class contains constant of regex validate for client information

    public final static String NOM;
    public final static String PRENOM;
    public final static String SOCIETE;
    public final static String CIVILITE;
    public final static String TELEPHONE;
    public final static String MOBILE;
    public final static String FAX;
    public final static String EMAIL;
    public final static String TYPE;
    public final static String ADRESSE;
    public final static String CODE_POSTAL;
    public final static String VILLE;
    public final static String PAYS;

    static {
        NOM = "[a-z]{3,}";
        PRENOM = "[a-z]{3,}";
        SOCIETE = "[a-z0-9]{4,}";
        CIVILITE = "[a-z0-9]{1,5}";
        TELEPHONE = "[0-9]{8,}";
        MOBILE = "[0-9]{8,}";
        FAX = "[0-9]{8,}";
        EMAIL = "[a-zA-Z_][\\w]*[-]{0,4}[\\w]+@[a-zA-Z0-9]+.[a-zA-Z]{2,6}";
        TYPE = "[0-9]{0,2}";
        ADRESSE = ".{3,}";
        CODE_POSTAL = "[0-9]{5}";
        VILLE = "[a-z]{3,}";
        PAYS = "[a-z]{3,}";
    }

}
