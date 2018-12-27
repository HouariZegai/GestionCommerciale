package com.houarizegai.gestioncommercial.java.database.models;

public class FraisPort {
    private String codePort;
    private String libFraisPort;
    private double montant;

    public FraisPort() {
    }

    public FraisPort(String codePort, String libFraisPort, double montant) {
        this.codePort = codePort;
        this.libFraisPort = libFraisPort;
        this.montant = montant;
    }

    public String getCodePort() {
        return codePort;
    }

    public void setCodePort(String codePort) {
        this.codePort = codePort;
    }

    public String getLibFraisPort() {
        return libFraisPort;
    }

    public void setLibFraisPort(String libFraisPort) {
        this.libFraisPort = libFraisPort;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "FraisPort{codePort='" + codePort + "\', libFraisPort='" + libFraisPort + "\', montant=" + montant + '}';
    }
}
