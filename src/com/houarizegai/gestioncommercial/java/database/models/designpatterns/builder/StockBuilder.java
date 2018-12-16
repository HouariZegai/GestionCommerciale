package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Stock;

import java.util.Date;

public class StockBuilder {
    private String reference;
    private int qteEnStock;
    private int qteStockVirtuel;
    private String auteurModif;
    private Date dateModif;

    public StockBuilder setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public StockBuilder setQteEnStock(int qteEnStock) {
        this.qteEnStock = qteEnStock;
        return this;
    }

    public StockBuilder setQteStockVirtuel(int qteStockVirtuel) {
        this.qteStockVirtuel = qteStockVirtuel;
        return this;
    }

    public StockBuilder setAuteurModif(String auteurModif) {
        this.auteurModif = auteurModif;
        return this;
    }

    public StockBuilder setDateModif(Date dateModif) {
        this.dateModif = dateModif;
        return this;
    }

    public Stock build() {
        return new Stock(reference, qteEnStock, qteStockVirtuel, auteurModif, dateModif);
    }
}
