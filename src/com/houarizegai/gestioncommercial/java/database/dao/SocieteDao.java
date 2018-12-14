package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Societe;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.SocieteBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SocieteDao {
    public static List<Societe> getSocietes() { // get all Societe
        String sql = "SELECT * FROM Societe;";

        List<Societe> societes = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Societe societe = new SocieteBuilder()
                        .setNomSociete(rs.getString("NomSociete"))
                        .setAdresse(rs.getString("Adresse"))
                        .setCodePostal(rs.getString("CodePostal"))
                        .setVille(rs.getString("Ville"))
                        .setTelephone(rs.getString("Telephone"))
                        .setFax(rs.getString("Fax"))
                        .setEmail(rs.getString("Email"))
                        .setLogo(rs.getBlob("Logo"))
                        .build();

                societes.add(societe);
            }

        } catch (SQLException se) {
            System.out.println("Get All Societe Error SQL");
            se.printStackTrace();
            return null;
        }

        return societes;
    }

    public static int setSociete(Societe societe) { // Edit Societe
        StringBuilder sql = new StringBuilder("UPDATE `societe` SET `Adresse`=?, `CodePostal`=?, `Ville`=?, `Telephone`=?, `Fax`=?, `Email`=?, `Logo`=? WHERE `NomSociete`=?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, societe.getAdresse());
            prest.setString(2, societe.getCodePostal());
            prest.setString(3, societe.getVille());
            prest.setString(4, societe.getTelephone());
            prest.setString(5, societe.getFax());
            prest.setString(6, societe.getEmail());
            prest.setBlob(7, societe.getLogo());
            prest.setString(8, societe.getNomSociete());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Societe Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteSociete(String  nomSociete) { // Remove Societe
        String sql = "DELETE FROM `Societe` WHERE `NomSociete` = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, nomSociete);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Societe Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addSociete(Societe societe) { // Add new Societe

        String sql = "INSERT INTO `Societe` (`NomSociete`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Fax`, `Email`, `Logo`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, societe.getNomSociete());
            prest.setString(2, societe.getAdresse());
            prest.setString(3, societe.getCodePostal());
            prest.setString(4, societe.getVille());
            prest.setString(5, societe.getTelephone());
            prest.setString(6, societe.getFax());
            prest.setString(7, societe.getEmail());
            prest.setBlob(8, societe.getLogo());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Societe Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
