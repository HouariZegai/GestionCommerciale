package com.houarizegai.gestioncommercial.java.database.models;

import java.util.Date;

public class Reglement {
    private int idReglement;
    private Date dateReglement;
    private int idModeReglement;
    private int numClient;
    private String saisiPar;
    private Date saisiLe;
    private String observations;

    private double montant;

    public Reglement() {
    }

    public Reglement(int idReglement, Date dateReglement, int idModeReglement, int numClient, String saisiPar, Date saisiLe, String observations, double montant) {
        this.idReglement = idReglement;
        this.dateReglement = dateReglement;
        this.idModeReglement = idModeReglement;
        this.numClient = numClient;
        this.saisiPar = saisiPar;
        this.saisiLe = saisiLe;
        this.observations = observations;
        this.montant = montant;
    }

    public int getIdReglement() {
        return idReglement;
    }

    public void setIdReglement(int idReglement) {
        this.idReglement = idReglement;
    }

    public Date getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
    }

    public int getIdModeReglement() {
        return idModeReglement;
    }

    public void setIdModeReglement(int idModeReglement) {
        this.idModeReglement = idModeReglement;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Reglement{" +
                "idReglement=" + idReglement +
                ", dateReglement=" + dateReglement +
                ", idModeReglement=" + idModeReglement +
                ", numClient=" + numClient +
                ", saisiPar='" + saisiPar + '\'' +
                ", saisiLe=" + saisiLe +
                ", observations='" + observations + '\'' +
                ", montant=" + montant +
                '}';
    }
}
