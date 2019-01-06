package com.houarizegai.gestioncommercial.java.database.models;

public class ModeReglement {
    private int idModeReglement;
    private String libModeReglement;

    public ModeReglement() {
    }

    public ModeReglement(int idModeReglement, String libModeReglement) {
        this.idModeReglement = idModeReglement;
        this.libModeReglement = libModeReglement;
    }

    public int getIdModeReglement() {
        return idModeReglement;
    }

    public void setIdModeReglement(int idModeReglement) {
        this.idModeReglement = idModeReglement;
    }

    public String getLibModeReglement() {
        return libModeReglement;
    }

    public void setLibModeReglement(String libModeReglement) {
        this.libModeReglement = libModeReglement;
    }
}
