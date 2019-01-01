package com.houarizegai.gestioncommercial.java.database.models;

import javafx.scene.image.Image;

import java.util.Date;

public class Produit {
    private String reference;
    private String genCode;
    private String codeBarre;
    private String libProd;
    private String description;
    private double prixHt;
    private int qteReappro;
    private int qteMini;
    private double tauxTva;
    private Image photo;
    private int numFournisseur;
    private boolean plusAuCatalogue;
    private String saisiPar;
    private Date saisiLe;
    private String codeFamille;
    private String codePort;

    public Produit(String reference, String genCode, String codeBarre, String libProd, String description, double prixHt, int qteReappro, int qteMini, double tauxTva, Image photo, int numFournisseur, boolean plusAuCatalogue, String saisiPar, Date saisiLe, String codeFamille, String codePort) {
        this.reference = reference;
        this.genCode = genCode;
        this.codeBarre = codeBarre;
        this.libProd = libProd;
        this.description = description;
        this.prixHt = prixHt;
        this.qteReappro = qteReappro;
        this.qteMini = qteMini;
        this.tauxTva = tauxTva;
        this.photo = photo;
        this.numFournisseur = numFournisseur;
        this.plusAuCatalogue = plusAuCatalogue;
        this.saisiPar = saisiPar;
        this.saisiLe = saisiLe;
        this.codeFamille = codeFamille;
        this.codePort = codePort;
    }

    public Produit() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getGenCode() {
        return genCode;
    }

    public void setGenCode(String genCode) {
        this.genCode = genCode;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getLibProd() {
        return libProd;
    }

    public void setLibProd(String libProd) {
        this.libProd = libProd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(double prixHt) {
        this.prixHt = prixHt;
    }

    public int getQteReappro() {
        return qteReappro;
    }

    public void setQteReappro(int qteReappro) {
        this.qteReappro = qteReappro;
    }

    public int getQteMini() {
        return qteMini;
    }

    public void setQteMini(int qteMini) {
        this.qteMini = qteMini;
    }

    public double getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public int getNumFournisseur() {
        return numFournisseur;
    }

    public void setNumFournisseur(int numFournisseur) {
        this.numFournisseur = numFournisseur;
    }

    public boolean isPlusAuCatalogue() {
        return plusAuCatalogue;
    }

    public void setPlusAuCatalogue(boolean plusAuCatalogue) {
        this.plusAuCatalogue = plusAuCatalogue;
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

    public String getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getCodePort() {
        return codePort;
    }

    public void setCodePort(String codePort) {
        this.codePort = codePort;
    }
}
