package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.FournisseurBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FournisseurDao {

    public static List<Fournisseur> getFournisseur() { // Get all client from database
        String sql = "SELECT * FROM Fournisseur;";

        List<Fournisseur> fournisseurs = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Fournisseur fournisseur = new FournisseurBuilder()
                        .setNumFournisseur(rs.getInt(1))
                        .setSociete(rs.getString(2))
                        .setCivilite(rs.getString(3))
                        .setNom(rs.getString(4))
                        .setPrenom(rs.getString(5))
                        .setAdresse(rs.getString(6))
                        .setCodePostal(rs.getString(7))
                        .setVille(rs.getString(8))
                        .setPays(rs.getString(9))
                        .setTelephone(rs.getString(10))
                        .setMobile(rs.getString(11))
                        .setFax(rs.getString(12))
                        .setEmail(rs.getString(13))
                        .setObservations(rs.getString(14))
                        .build();

                fournisseurs.add(fournisseur);
            }

        } catch (SQLException se) {
            System.out.println("Get All Fournisseur Error SQL");
            se.printStackTrace();
            return null;
        }

        return fournisseurs;
    }

    public static int setFournisseur(Fournisseur fournisseur) { // Edit Client
        StringBuilder sql = new StringBuilder("UPDATE Fournisseur SET Societe = ?, Civilite = ?, Nom = ?, prenom = ?");
        sql.append(", adresse = ?, codePostal = ?, ville = ?, Pays = ?, telephone = ?, mobile = ?, fax = ?");
        sql.append(", email = ?, observations = ? WHERE numFournisseur = ?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, fournisseur.getSociete());
            prest.setString(2, fournisseur.getCivilite());
            prest.setString(3, fournisseur.getNom());
            prest.setString(4, fournisseur.getPrenom());
            prest.setString(5, fournisseur.getAdresse());
            prest.setString(6, fournisseur.getCodePostal());
            prest.setString(7, fournisseur.getVille());
            prest.setString(8, fournisseur.getPays());
            prest.setString(9, fournisseur.getTelephone());
            prest.setString(10, fournisseur.getMobile());
            prest.setString(11, fournisseur.getFax());
            prest.setString(12, fournisseur.getEmail());
            prest.setString(13, fournisseur.getObservations());
            prest.setInt(14, fournisseur.getNumFournisseur());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Fournisseur Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteFournisseur(int numFournisseur) { // Delete Fournisseur
        String sql = "DELETE FROM Fournisseur WHERE numFournisseur = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, numFournisseur);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Fournisseur Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addFournisseur(Fournisseur fournisseur) {

        String sql = "INSERT INTO Fournisseur (`Societe`, `Civilite`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Pays`, `Telephone`, `Mobile`, `Fax`, `Email`, `Observations`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, fournisseur.getSociete());
            prest.setString(2, fournisseur.getCivilite());
            prest.setString(3, fournisseur.getNom());
            prest.setString(4, fournisseur.getPrenom());
            prest.setString(5, fournisseur.getAdresse());
            prest.setString(6, fournisseur.getCodePostal());
            prest.setString(7, fournisseur.getVille());
            prest.setString(8, fournisseur.getPays());
            prest.setString(9, fournisseur.getTelephone());
            prest.setString(10, fournisseur.getMobile());
            prest.setString(11, fournisseur.getFax());
            prest.setString(12, fournisseur.getEmail());
            prest.setString(13, fournisseur.getObservations());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Fournisseur Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
