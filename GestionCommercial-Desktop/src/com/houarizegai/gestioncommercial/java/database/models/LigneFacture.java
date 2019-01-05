package com.houarizegai.gestioncommercial.java.database.models;

public class LigneFacture {
    private int idLigneFac;
    private int numFacture;
    private String reference;
    private String libProd;
    private int quantite;
    private double prixVente;
    private double remise;
    private double tauxTva;
    private int idLigneCde;
    private int ordreAffichage;

    public LigneFacture() {

    }

    public LigneFacture(int idLigneFac, int numFacture, String reference, String libProd, int quantite, double prixVente, double remise, double tauxTva, int idLigneCde, int ordreAffichage) {
        this.idLigneFac = idLigneFac;
        this.numFacture = numFacture;
        this.reference = reference;
        this.libProd = libProd;
        this.quantite = quantite;
        this.prixVente = prixVente;
        this.remise = remise;
        this.tauxTva = tauxTva;
        this.idLigneCde = idLigneCde;
        this.ordreAffichage = ordreAffichage;
    }

    public int getIdLigneFac() {
        return idLigneFac;
    }

    public void setIdLigneFac(int idLigneFac) {
        this.idLigneFac = idLigneFac;
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibProd() {
        return libProd;
    }

    public void setLibProd(String libProd) {
        this.libProd = libProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
    }

    public int getIdLigneCde() {
        return idLigneCde;
    }

    public void setIdLigneCde(int idLigneCde) {
        this.idLigneCde = idLigneCde;
    }

    public int getOrdreAffichage() {
        return ordreAffichage;
    }

    public void setOrdreAffichage(int ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
    }

    @Override
    public String toString() {
        return "LigneFacture{" +
                "idLigneFac=" + idLigneFac +
                ", numFacture=" + numFacture +
                ", reference='" + reference + '\'' +
                ", libProd='" + libProd + '\'' +
                ", quantite=" + quantite +
                ", prixVente=" + prixVente +
                ", remise=" + remise +
                ", tauxTva=" + tauxTva +
                ", idLigneCde=" + idLigneCde +
                ", ordreAffichage=" + ordreAffichage +
                '}';
    }
}
