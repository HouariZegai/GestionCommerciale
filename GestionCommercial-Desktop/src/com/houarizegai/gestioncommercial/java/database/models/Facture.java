package com.houarizegai.gestioncommercial.java.database.models;

import java.util.Date;
import java.util.List;

public class Facture {
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

    public Facture() {
    }

    public Facture(int numFacture, Date dateFacture, int numClient, int idAdresseFacturation, int idModeReglement, double totalHT, double totalTVA, double totalFraisPort, double totalTTC, double remise, boolean acquittee, String saisiPar, Date saisiLe, String observations, int numCommande, List<LigneFacture> ligneFactures) {
        this.numFacture = numFacture;
        this.dateFacture = dateFacture;
        this.numClient = numClient;
        this.idAdresseFacturation = idAdresseFacturation;
        this.idModeReglement = idModeReglement;
        this.totalHT = totalHT;
        this.totalTVA = totalTVA;
        this.totalFraisPort = totalFraisPort;
        this.totalTTC = totalTTC;
        this.remise = remise;
        this.acquittee = acquittee;
        this.saisiPar = saisiPar;
        this.saisiLe = saisiLe;
        this.observations = observations;
        this.numCommande = numCommande;
        this.ligneFactures = ligneFactures;
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

    public int getIdAdresseFacturation() {
        return idAdresseFacturation;
    }

    public void setIdAdresseFacturation(int idAdresseFacturation) {
        this.idAdresseFacturation = idAdresseFacturation;
    }

    public int getIdModeReglement() {
        return idModeReglement;
    }

    public void setIdModeReglement(int idModeReglement) {
        this.idModeReglement = idModeReglement;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public double getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public double getTotalFraisPort() {
        return totalFraisPort;
    }

    public void setTotalFraisPort(double totalFraisPort) {
        this.totalFraisPort = totalFraisPort;
    }

    public double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public boolean isAcquittee() {
        return acquittee;
    }

    public void setAcquittee(boolean acquittee) {
        this.acquittee = acquittee;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public List<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "numFacture=" + numFacture +
                ", dateFacture=" + dateFacture +
                ", numClient=" + numClient +
                ", idAdresseFacturation=" + idAdresseFacturation +
                ", idModeReglement=" + idModeReglement +
                ", totalHT=" + totalHT +
                ", totalTVA=" + totalTVA +
                ", totalFraisPort=" + totalFraisPort +
                ", totalTTC=" + totalTTC +
                ", remise=" + remise +
                ", acquittee=" + acquittee +
                ", saisiPar='" + saisiPar + '\'' +
                ", saisiLe=" + saisiLe +
                ", observations='" + observations + '\'' +
                ", numCommande=" + numCommande +
                ", ligneFactures=" + ligneFactures +
                '}';
    }
}
