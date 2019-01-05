package com.houarizegai.gestioncommercial.java.database.models;

import java.util.Date;
import java.util.List;

public class Facture {
    private int numFacture;
    private String dateFacture;
    private int numClient;
    private int idAdresseFacturation;
    private int idModeReglement;
    private double totalHT, totalTVA, totalFraisPort, totalTTC;
    private boolean acquittee;
    private String saisiPar;
    private Date saisiLe;
    private String observations;

    private List<LigneFacture> ligneFactures;

    public Facture() {
    }

    public Facture(int numFacture, String dateFacture, int numClient, int idAdresseFacturation, int idModeReglement, double totalHT, double totalTVA, double totalFraisPort, double totalTTC, boolean acquittee, String saisiPar, Date saisiLe, String observations, List<LigneFacture> ligneFactures) {
        this.numFacture = numFacture;
        this.dateFacture = dateFacture;
        this.numClient = numClient;
        this.idAdresseFacturation = idAdresseFacturation;
        this.idModeReglement = idModeReglement;
        this.totalHT = totalHT;
        this.totalTVA = totalTVA;
        this.totalFraisPort = totalFraisPort;
        this.totalTTC = totalTTC;
        this.acquittee = acquittee;
        this.saisiPar = saisiPar;
        this.saisiLe = saisiLe;
        this.observations = observations;
        this.ligneFactures = ligneFactures;
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(String dateFacture) {
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
                ", dateFacture='" + dateFacture + '\'' +
                ", numClient=" + numClient +
                ", idAdresseFacturation=" + idAdresseFacturation +
                ", idModeReglement=" + idModeReglement +
                ", totalHT=" + totalHT +
                ", totalTVA=" + totalTVA +
                ", totalFraisPort=" + totalFraisPort +
                ", totalTTC=" + totalTTC +
                ", acquittee=" + acquittee +
                ", saisiPar='" + saisiPar + '\'' +
                ", saisiLe=" + saisiLe +
                ", observations='" + observations + '\'' +
                ", ligneFactures=" + ligneFactures +
                '}';
    }
}
