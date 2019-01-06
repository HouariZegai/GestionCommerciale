package com.houarizegai.gestioncommercial.java.database.models;

import java.util.Date;

public class Reglement {
    private int idReglement;
    private Date dateReglement;
    private int idModeReglement;
    private int numFacture;
    private String saisiPar;
    private Date saisiLe;
    private String observations;

    public Reglement() {
    }

    public Reglement(int idReglement, Date dateReglement, int idModeReglement, int numFacture, String saisiPar, Date saisiLe, String observations) {
        this.idReglement = idReglement;
        this.dateReglement = dateReglement;
        this.idModeReglement = idModeReglement;
        this.numFacture = numFacture;
        this.saisiPar = saisiPar;
        this.saisiLe = saisiLe;
        this.observations = observations;
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

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
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

    @Override
    public String toString() {
        return "Reglement{" +
                "idReglement=" + idReglement +
                ", dateReglement=" + dateReglement +
                ", idModeReglement=" + idModeReglement +
                ", numFacture=" + numFacture +
                ", saisiPar='" + saisiPar + '\'' +
                ", saisiLe=" + saisiLe +
                ", observations='" + observations + '\'' +
                '}';
    }
}
