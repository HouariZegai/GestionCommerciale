package com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder;

import com.houarizegai.gestioncommercial.java.database.models.Produit;
import javafx.scene.image.Image;

import java.util.Date;

public class ProduitBuilder {
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

    public ProduitBuilder setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public ProduitBuilder setGenCode(String genCode) {
        this.genCode = genCode;
        return this;
    }

    public ProduitBuilder setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
        return this;
    }

    public ProduitBuilder setLibProd(String libProd) {
        this.libProd = libProd;
        return this;
    }

    public ProduitBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProduitBuilder setPrixHt(double prixHt) {
        this.prixHt = prixHt;
        return this;
    }

    public ProduitBuilder setQteReappro(int qteReappro) {
        this.qteReappro = qteReappro;
        return this;
    }

    public ProduitBuilder setQteMini(int qteMini) {
        this.qteMini = qteMini;
        return this;
    }

    public ProduitBuilder setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
        return this;
    }

    public ProduitBuilder setPhoto(Image photo) {
        this.photo = photo;
        return this;
    }

    public ProduitBuilder setNumFournisseur(int numFournisseur) {
        this.numFournisseur = numFournisseur;
        return this;
    }

    public ProduitBuilder setPlusAuCatalogue(boolean plusAuCatalogue) {
        this.plusAuCatalogue = plusAuCatalogue;
        return this;
    }

    public ProduitBuilder setSaisiPar(String saisiPar) {
        this.saisiPar = saisiPar;
        return this;
    }

    public ProduitBuilder setSaisiLe(Date saisiLe) {
        this.saisiLe = saisiLe;
        return this;
    }

    public ProduitBuilder setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
        return this;
    }

    public ProduitBuilder setCodePort(String codePort) {
        this.codePort = codePort;
        return this;
    }

    public Produit build() {
        return new Produit(reference, genCode, codeBarre, libProd, description, prixHt, qteReappro, qteMini, tauxTva,
                photo, numFournisseur, plusAuCatalogue, saisiPar, saisiLe, codeFamille, codePort);
    }
}
