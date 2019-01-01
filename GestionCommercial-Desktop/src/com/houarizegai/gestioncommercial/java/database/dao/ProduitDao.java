package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Produit;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ProduitBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProduitDao {

    public static List<Produit> getProduits() { // Get all Produit from database
        String sql = "SELECT * FROM Produit;";

        List<Produit> produits = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Produit produit = new ProduitBuilder()
                .setReference(rs.getString("Reference"))
                .setGenCode(rs.getString("GenCode"))
                .setCodeBarre(rs.getString("CodeBarre"))
                .setLibProd(rs.getString("LibProd"))
                .setDescription(rs.getString("description"))
                .setPrixHt(rs.getDouble("PrixHT"))
                .setQteReappro(rs.getInt("QteReappro"))
                .setQteMini(rs.getInt("QteMini"))
                .setTauxTva(rs.getDouble("TauxTva"))
                .setPhoto(UsefulMethods.blobToImage(rs.getBlob("Photo")))
                .setNumFournisseur(rs.getInt("NumFournisseur"))
                .setPlusAuCatalogue(rs.getBoolean("plusAuCatalogue"))
                .setSaisiPar(rs.getString("SaisiPar"))
                .setSaisiLe(rs.getDate("SaisiLe"))
                .setCodeFamille(rs.getString("CodeFamille"))
                .setCodePort(rs.getString("CodePort"))
                .build();

                produits.add(produit);
            }

        } catch (SQLException se) {
            System.out.println("Get All Produit Error SQL");
            se.printStackTrace();
            return null;
        }

        return produits;
    }

    public static int setProduit(Produit produit) { // Edit Produit
        StringBuilder sql = new StringBuilder("UPDATE `produit` SET `genCode` = ?, `codeBarre` = ?, `LibProd` = ?, `description` = ?, `PrixHt` = ?, `QteReappro` = ?, `QteMini` = ?, `TauxTva` = ?, `photo` = ?, `NumFournisseur` = ?, `plusAuCatalogue` = ?, `SaisiPar` = ?, `SaisiLe` = ?, `CodeFamille` = ?,`CodePort` = ? WHERE `Reference` = ?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, produit.getGenCode());
            prest.setString(2, produit.getCodeBarre());
            prest.setString(3, produit.getLibProd());
            prest.setString(4, produit.getDescription());
            prest.setDouble(5, produit.getPrixHt());
            prest.setInt(6, produit.getQteReappro());
            prest.setInt(7, produit.getQteMini());
            prest.setDouble(8, produit.getTauxTva());
            prest.setBlob(9, UsefulMethods.imageToBlob(produit.getPhoto()));
            prest.setInt(10, produit.getNumFournisseur());
            prest.setBoolean(11, produit.isPlusAuCatalogue());
            prest.setString(12, produit.getSaisiPar());
            prest.setDate(13, UsefulMethods.getSQLDate(produit.getSaisiLe()));
            prest.setString(14, produit.getCodeFamille());
            prest.setString(15, produit.getCodePort());
            prest.setString(16, produit.getReference());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Produit Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteProduit(String reference) { // Remove Produit
        String sql = "DELETE FROM `Produit` WHERE Reference = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, reference);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Produit Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addProduit(Produit produit) { // Add new product

        String sql = "INSERT INTO `produit`(`Reference`, `genCode`, `codeBarre`, `LibProd`, `description`, `PrixHt`, `QteReappro`, `QteMini`, `TauxTva`, `photo`, `NumFournisseur`, `plusAuCatalogue`, `SaisiPar`, `SaisiLe`, `CodeFamille`, `CodePort`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, produit.getReference());
            prest.setString(2, produit.getGenCode());
            prest.setString(3, produit.getCodeBarre());
            prest.setString(4, produit.getLibProd());
            prest.setString(5, produit.getDescription());
            prest.setDouble(6, produit.getPrixHt());
            prest.setInt(7, produit.getQteReappro());
            prest.setInt(8, produit.getQteMini());
            prest.setDouble(9, produit.getTauxTva());
            prest.setBlob(10, UsefulMethods.imageToBlob(produit.getPhoto()));
            prest.setInt(11, produit.getNumFournisseur());
            prest.setBoolean(12, produit.isPlusAuCatalogue());
            prest.setString(13, produit.getSaisiPar());
            prest.setDate(14, UsefulMethods.getSQLDate(produit.getSaisiLe()));
            prest.setString(15, produit.getCodeFamille());
            prest.setString(16, produit.getCodePort());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Produit Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
