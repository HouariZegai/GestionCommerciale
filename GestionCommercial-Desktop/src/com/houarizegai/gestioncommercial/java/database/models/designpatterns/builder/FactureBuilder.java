package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Facture;
import com.houarizegai.gestioncommercial.java.database.models.LigneFacture;

import java.util.Date;
import java.util.List;

public class FactureBuilder {
    private int numFacture;
    private Date dateFacture;
    private int numClient;
    private int idAdresseFacturation;
    private int idModeReglement;
    private double totalHT, totalTVA, totalFraisPort, totalTTC, remise;
    private boolean acquittee;
    private String saisiPar;
    private Date saisiLe;
    private String observations;
    private int numCommande;

    private List<LigneFacture> ligneFactures;

    public FactureBuilder setNumFacture(int numFacture) {
        this.numFacture = numFacture;
        return this;
    }

    public FactureBuilder setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
        return this;
    }

    public FactureBuilder setNumClient(int numClient) {
        this.numClient = numClient;
        return this;
    }

    public FactureBuilder setIdAdresseFacturation(int idAdresseFacturation) {
        this.idAdresseFacturation = idAdresseFacturation;
        return this;
    }

    public FactureBuilder setIdModeReglement(int idModeReglement) {
        this.idModeReglement = idModeReglement;
        return this;
    }

    public FactureBuilder setTotalHT(double totalHT) {
        this.totalHT = totalHT;
        return this;
    }

    public FactureBuilder setTotalTVA(double totalTVA) {
        this.totalTVA = totalTVA;
        return this;
    }

    public FactureBuilder setTotalFraisPort(double totalFraisPort) {
        this.totalFraisPort = totalFraisPort;
        return this;
    }

    public FactureBuilder setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
        return this;
    }

    public FactureBuilder setRemise(double remise) {
        this.remise = remise;
        return this;
    }

    public FactureBuilder setAcquittee(boolean acquittee) {
        this.acquittee = acquittee;
        return this;
    }

    public FactureBuilder setSaisiPar(String saisiPar) {
        this.saisiPar = saisiPar;
        return this;
    }

    public FactureBuilder setSaisiLe(Date saisiLe) {
        this.saisiLe = saisiLe;
        return this;
    }

    public FactureBuilder setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public FactureBuilder setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
        return this;
    }

    public FactureBuilder setNumCommande(int numCommande) {
        this.numCommande = numCommande;
        return this;
    }

    public Facture build() {
        return new Facture(numFacture, dateFacture, numClient, idAdresseFacturation, idModeReglement, totalHT, totalTVA, totalFraisPort, totalTTC, remise, acquittee, saisiPar, saisiLe, observations, numCommande, ligneFactures);
    }
}
