package com.houarizegai.gestioncommercial.java.utils;

public class UsefulMethods { // This class contains useful methods are used in many place

    public static java.sql.Date getSQLDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
