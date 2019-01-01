package com.houarizegai.gestioncommercial.java.database.models;

import java.util.Date;

public class Client {
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

    public Client() {

    }

    public Client(int numClient, String societe, String civilite, String nomClient, String prenom, String adresse, String codePostal, String ville, String pays, String telephone, String mobile, String fax, String email, int type, boolean livreMemeAdresse, boolean factureMemeAdresse, boolean exemptTva, String saisiPar, Date saisiLe, String auteurModif, Date dateModif, String observations) {
        this.numClient = numClient;
        this.societe = societe;
        this.civilite = civilite;
        this.nomClient = nomClient;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.telephone = telephone;
        this.mobile = mobile;
        this.fax = fax;
        this.email = email;
        this.type = type;
        this.livreMemeAdresse = livreMemeAdresse;
        this.factureMemeAdresse = factureMemeAdresse;
        this.exemptTva = exemptTva;
        this.saisiPar = saisiPar;
        this.saisiLe = saisiLe;
        this.auteurModif = auteurModif;
        this.dateModif = dateModif;
        this.observations = observations;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isLivreMemeAdresse() {
        return livreMemeAdresse;
    }

    public void setLivreMemeAdresse(boolean livreMemeAdresse) {
        this.livreMemeAdresse = livreMemeAdresse;
    }

    public boolean isFactureMemeAdresse() {
        return factureMemeAdresse;
    }

    public void setFactureMemeAdresse(boolean factureMemeAdresse) {
        this.factureMemeAdresse = factureMemeAdresse;
    }

    public boolean isExemptTva() {
        return exemptTva;
    }

    public void setExemptTva(boolean exemptTva) {
        this.exemptTva = exemptTva;
    }

    public String getSaisiPar() {
        return saisiPar;
    }

    public void setSaisiPar(String saisiPar) {
        this.saisiPar = saisiPar;
    }

    public Date getSaisiLe() {
        return saisiLe;
    }

    public void setSaisiLe(Date saisiLe) {
        this.saisiLe = saisiLe;
    }

    public String getAuteurModif() {
        return auteurModif;
    }

    public void setAuteurModif(String auteurModif) {
        this.auteurModif = auteurModif;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
