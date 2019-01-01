package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;

public class FournisseurBuilder {
    private int numFournisseur;
    private String societe;
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;
    private String telephone;
    private String mobile;
    private String fax;
    private String email;
    private String observations;

    public FournisseurBuilder setNumFournisseur(int numFournisseur) {
        this.numFournisseur = numFournisseur;
        return this;
    }

    public FournisseurBuilder setSociete(String societe) {
        this.societe = societe;
        return this;
    }

    public FournisseurBuilder setCivilite(String civilite) {
        this.civilite = civilite;
        return this;
    }

    public FournisseurBuilder setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public FournisseurBuilder setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public FournisseurBuilder setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public FournisseurBuilder setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public FournisseurBuilder setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public FournisseurBuilder setPays(String pays) {
        this.pays = pays;
        return this;
    }

    public FournisseurBuilder setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public FournisseurBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public FournisseurBuilder setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public FournisseurBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public FournisseurBuilder setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public Fournisseur build() {
        return new Fournisseur(numFournisseur, societe, civilite, nom, prenom, adresse, codePostal, ville, pays, telephone,
                mobile, fax, email, observations);
    }
}
