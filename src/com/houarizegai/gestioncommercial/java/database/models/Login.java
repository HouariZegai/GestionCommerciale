package com.houarizegai.gestioncommercial.java.database.models;

public class Login {
    private int idLogin;
    private String nomUtilisateur;
    private String motDePasse;

    public Login() {
    }

    public Login(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    public Login(int idLogin, String nomUtilisateur, String motDePasse) {
        this.idLogin = idLogin;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
