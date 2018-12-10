package com.houarizegai.gestioncommercial.java.database.models;

public class Fournisseur {
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

    public Fournisseur() {

    }

    public Fournisseur(int numFournisseur, String societe, String civilite, String nom, String prenom, String adresse, String codePostal, String ville, String pays, String telephone, String mobile, String fax, String email, String observations) {
        this.numFournisseur = numFournisseur;
        this.societe = societe;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.telephone = telephone;
        this.mobile = mobile;
        this.fax = fax;
        this.email = email;
        this.observations = observations;
    }

    public int getNumFournisseur() {
        return numFournisseur;
    }

    public void setNumFournisseur(int numFournisseur) {
        this.numFournisseur = numFournisseur;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "numFournisseur=" + numFournisseur +
                ", societe='" + societe + '\'' +
                ", civilite='" + civilite + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", observations='" + observations + '\'' +
                '}';
    }
}
