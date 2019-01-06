package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Reglement;

import java.util.Date;

public class ReglementBuilder {
    private int idReglement;
    private Date dateReglement;
    private int idModeReglement;
    private int numFacture;
    private String saisiPar;
    private Date saisiLe;
    private String observations;

    public ReglementBuilder setIdReglement(int idReglement) {
        this.idReglement = idReglement;
        return this;
    }

    public ReglementBuilder setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
        return this;
    }

    public ReglementBuilder setIdModeReglement(int idModeReglement) {
        this.idModeReglement = idModeReglement;
        return this;
    }

    public ReglementBuilder setNumFacture(int numFacture) {
        this.numFacture = numFacture;
        return this;
    }

    public ReglementBuilder setSaisiPar(String saisiPar) {
        this.saisiPar = saisiPar;
        return this;
    }

    public ReglementBuilder setSaisiLe(Date saisiLe) {
        this.saisiLe = saisiLe;
        return this;
    }

    public ReglementBuilder setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public Reglement build() {
        return new Reglement(idReglement, dateReglement, idModeReglement, numFacture, saisiPar, saisiLe, observations);
    }
}
