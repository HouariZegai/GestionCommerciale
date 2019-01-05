package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.LigneFacture;

public class LigneFactureBuilder {
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

    public LigneFactureBuilder setIdLigneFac(int idLigneFac) {
        this.idLigneFac = idLigneFac;
        return this;
    }

    public LigneFactureBuilder setNumFacture(int numFacture) {
        this.numFacture = numFacture;
        return this;
    }

    public LigneFactureBuilder setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public LigneFactureBuilder setLibProd(String libProd) {
        this.libProd = libProd;
        return this;
    }

    public LigneFactureBuilder setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public LigneFactureBuilder setPrixVente(double prixVente) {
        this.prixVente = prixVente;
        return this;
    }

    public LigneFactureBuilder setRemise(double remise) {
        this.remise = remise;
        return this;
    }

    public LigneFactureBuilder setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
        return this;
    }

    public LigneFactureBuilder setIdLigneCde(int idLigneCde) {
        this.idLigneCde = idLigneCde;
        return this;
    }

    public LigneFactureBuilder setOrdreAffichage(int ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
        return this;
    }

    public LigneFacture build() {
        return new LigneFacture(idLigneFac, numFacture, reference, libProd, quantite, prixVente, remise, tauxTva, idLigneCde, ordreAffichage);
    }
}
