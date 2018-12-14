package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Societe;

import java.sql.Blob;

public class SocieteBuilder {
    private String nomSociete;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String fax;
    private String email;
    private Blob logo;

    public SocieteBuilder setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
        return this;
    }

    public SocieteBuilder setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public SocieteBuilder setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public SocieteBuilder setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public SocieteBuilder setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public SocieteBuilder setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public SocieteBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public SocieteBuilder setLogo(Blob logo) {
        this.logo = logo;
        return this;
    }

    public Societe build() {
        return new Societe(nomSociete, adresse, codePostal, ville, telephone, fax, email, logo);
    }
}
