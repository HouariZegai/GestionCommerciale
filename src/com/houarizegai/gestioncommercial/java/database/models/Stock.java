package com.houarizegai.gestioncommercial.java.database.models;

import java.util.Date;

public class Stock {
    private String reference;
    private int qteEnStock;
    private int qteStockVirtuel;
    private String auteurModif;
    private Date dateModif;

    public Stock() {
    }

    public Stock(String reference, int qteEnStock, int qteStockVirtuel, String auteurModif, Date dateModif) {
        this.reference = reference;
        this.qteEnStock = qteEnStock;
        this.qteStockVirtuel = qteStockVirtuel;
        this.auteurModif = auteurModif;
        this.dateModif = dateModif;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQteEnStock() {
        return qteEnStock;
    }

    public void setQteEnStock(int qteEnStock) {
        this.qteEnStock = qteEnStock;
    }

    public int getQteStockVirtuel() {
        return qteStockVirtuel;
    }

    public void setQteStockVirtuel(int qteStockVirtuel) {
        this.qteStockVirtuel = qteStockVirtuel;
    }

    public String getAuteurModif() {
        return auteurModif;
    }

    public void setAuteurModif(String auteurModif) {
        this.auteurModif = auteurModif;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    @Override
    public String toString() {
        return "Stock{reference='" + reference + '\'' +
                ", qteEnStock=" + qteEnStock +
                ", qteStockVirtuel=" + qteStockVirtuel +
                ", auteurModif='" + auteurModif + '\'' +
                ", dateModif=" + dateModif +
                '}';
    }
}
