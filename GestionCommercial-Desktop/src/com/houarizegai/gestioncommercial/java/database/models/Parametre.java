package com.houarizegai.gestioncommercial.java.database.models;

public class Parametre {
    private int idParametre;
    private String nomParametre;
    private String valuer;

    public Parametre() {
    }

    public Parametre(String nomParametre, String valuer) {
        this.nomParametre = nomParametre;
        this.valuer = valuer;
    }

    public Parametre(int idParametre, String nomParametre, String valuer) {
        this.idParametre = idParametre;
        this.nomParametre = nomParametre;
        this.valuer = valuer;
    }

    public int getIdParametre() {
        return idParametre;
    }

    public void setIdParametre(int idParametre) {
        this.idParametre = idParametre;
    }

    public String getNomParametre() {
        return nomParametre;
    }

    public void setNomParametre(String nomParametre) {
        this.nomParametre = nomParametre;
    }

    public String getValuer() {
        return valuer;
    }

    public void setValuer(String valuer) {
        this.valuer = valuer;
    }

    @Override
    public String toString() {
        return "Parametre{idParametre=" + idParametre + ", nomParametre='" + nomParametre + "\', valuer='" + valuer + "\'}";
    }
}
