package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Client;

import java.util.Date;

public class ClientBuilder {
    private int numClient;
    private String societe;
    private String civilite;
    private String nomClient;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;
    private String telephone;
    private String mobile;
    private String fax;
    private String email;
    private int type;
    private boolean livreMemeAdresse;
    private boolean factureMemeAdresse;
    private boolean exemptTva;
    private String saisiPar;
    private Date saisiLe;
    private String auteurModif;
    private Date dateModif;
    private String observations;

    public ClientBuilder setNumClient(int numClient) {
        this.numClient = numClient;
        return this;
    }

    public ClientBuilder setSociete(String societe) {
        this.societe = societe;
        return this;
    }

    public ClientBuilder setCivilite(String civilite) {
        this.civilite = civilite;
        return this;
    }

    public ClientBuilder setNomClient(String nomClient) {
        this.nomClient = nomClient;
        return this;
    }

    public ClientBuilder setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public ClientBuilder setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public ClientBuilder setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public ClientBuilder setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public ClientBuilder setPays(String pays) {
        this.pays = pays;
        return this;
    }

    public ClientBuilder setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ClientBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ClientBuilder setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setType(int type) {
        this.type = type;
        return this;
    }

    public ClientBuilder setLivreMemeAdresse(boolean livreMemeAdresse) {
        this.livreMemeAdresse = livreMemeAdresse;
        return this;
    }

    public ClientBuilder setFactureMemeAdresse(boolean factureMemeAdresse) {
        this.factureMemeAdresse = factureMemeAdresse;
        return this;
    }

    public ClientBuilder setExemptTva(boolean exemptTva) {
        this.exemptTva = exemptTva;
        return this;
    }

    public ClientBuilder setSaisiPar(String saisiPar) {
        this.saisiPar = saisiPar;
        return this;
    }

    public ClientBuilder setSaisiLe(Date saisiLe) {
        this.saisiLe = saisiLe;
        return this;
    }

    public ClientBuilder setAuteurModif(String auteurModif) {
        this.auteurModif = auteurModif;
        return this;
    }

    public ClientBuilder setDateModif(Date dateModif) {
        this.dateModif = dateModif;
        return this;
    }

    public ClientBuilder setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public Client build() {
        return new Client(numClient, societe, civilite, nomClient, prenom, adresse, codePostal, ville,
                pays, telephone, mobile, fax, email, type, livreMemeAdresse, factureMemeAdresse, exemptTva,
                saisiPar, saisiLe, auteurModif, dateModif, observations);
    }
}
