package com.houarizegai.gestioncommercial.java.database.models;

public class Cedex {
    private int idCedex;
    private String codePostal;
    private String ville;

    public Cedex() {
    }

    public Cedex(String codePostal, String ville) {
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Cedex(int idCedex, String codePostal, String ville) {
        this.idCedex = idCedex;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getIdCedex() {
        return idCedex;
    }

    public void setIdCedex(int idCedex) {
        this.idCedex = idCedex;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Cedex{idCedex=" + idCedex + ", codePostal='" + codePostal + "\', ville='" + ville + "\'}";
    }
}
