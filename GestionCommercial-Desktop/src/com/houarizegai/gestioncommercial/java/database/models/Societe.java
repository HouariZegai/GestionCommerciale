package com.houarizegai.gestioncommercial.java.database.models;

import java.sql.Blob;

public class Societe {
    private String nomSociete;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String fax;
    private String email;
    private Blob logo;

    public Societe() {
    }

    public Societe(String nomSociete, String adresse, String codePostal, String ville, String telephone, String fax, String email, Blob logo) {
        this.nomSociete = nomSociete;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.logo = logo;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Societe{" +
                "nomSociete='" + nomSociete + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
