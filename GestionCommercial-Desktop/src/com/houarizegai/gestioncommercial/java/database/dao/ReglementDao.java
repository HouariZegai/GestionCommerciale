package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.ModeReglement;
import com.houarizegai.gestioncommercial.java.database.models.Reglement;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ReglementBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.prism.shader.DrawPgram_RadialGradient_PAD_AlphaTest_Loader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ReglementDao {

    public static String getLibModeReglement(int idModeReglement) {
        if (DBConnection.con == null)
            return null;

        String libModeReglement = null;
        String sql = "SELECT LibModeReglement FROM ModeReglement WHERE IDModeReglement = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, idModeReglement);
            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
                libModeReglement = rs.getString("LibModeReglement");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return libModeReglement;
    }

    public static int getIdModeReglement(String libModeReglement) {
        if (DBConnection.con == null)
            return -1;

        int idModeReglement = 0;
        String sql = "SELECT IDModeReglement FROM ModeReglement WHERE LibModeReglement = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, libModeReglement);
            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
                idModeReglement = rs.getInt("IDModeReglement");
                System.out.println("id Reg: " + idModeReglement);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return idModeReglement;
    }

    public static List<ModeReglement> getModeReglements() {

        String sql = "SELECT * FROM ModeReglement;";

        List<ModeReglement> modeReglements = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modeReglements.add(new ModeReglement(rs.getInt("IDModeReglement"), rs.getString("LibModeReglement")));
            }

        } catch (SQLException se) {
            System.out.println("Get Mode ReglementDao Error SQL");
            se.printStackTrace();
            return null;
        }
        return modeReglements;
    }

    public static List<Reglement> getReglementsByClient(int numClient) {
        if (DBConnection.con == null)
            return null;

        String sql = "SELECT * FROM Reglement WHERE NumClient = ?;";
        List<Reglement> reglements = new LinkedList<>();
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, numClient);
            ResultSet rs = prest.executeQuery();
            while (rs.next()) {
                Reglement reglement = new ReglementBuilder()
                        .setIdReglement(rs.getInt("IDReglement"))
                        .setDateReglement(rs.getDate("DateReglement"))
                        .setIdModeReglement(rs.getInt("IDModeReglement"))
                        .setNumClient(rs.getInt("NumClient"))
                        .setSaisiPar(rs.getString("SaisiPar"))
                        .setSaisiLe(rs.getDate("SaisiLe"))
                        .setObservations(rs.getString("Observations"))
                        .setMontant(rs.getDouble("Montant"))
                        .build();

                reglements.add(reglement);
            }
        } catch (SQLException se) {
            System.out.println("SQL Error in getReglements");
            se.printStackTrace();
        }

        return reglements;

    }

    public static List<Reglement> getReglements() {
        if (DBConnection.con == null)
            return null;

        String sql = "SELECT * FROM Reglement;";
        List<Reglement> reglements = new LinkedList<>();
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            while (rs.next()) {
                Reglement reglement = new ReglementBuilder()
                        .setIdReglement(rs.getInt("IDReglement"))
                        .setDateReglement(rs.getDate("DateReglement"))
                        .setIdModeReglement(rs.getInt("IDModeReglement"))
                        .setNumClient(rs.getInt("NumClient"))
                        .setSaisiPar(rs.getString("SaisiPar"))
                        .setSaisiLe(rs.getDate("SaisiLe"))
                        .setObservations(rs.getString("Observations"))
                        .setMontant(rs.getDouble("Montant"))
                        .build();

                reglements.add(reglement);
            }
        } catch (SQLException se) {
            System.out.println("SQL Error in getReglements");
            se.printStackTrace();
        }

        return reglements;

    }

    public static int addReglement(Reglement reglement) {
        if (DBConnection.con == null)
            return -1;

        String sql = "INSERT INTO `reglement`(`DateReglement`, `IDModeReglement`, `NumClient`, `SaisiPar`, `SaisiLe`, `Observations`, `Montant`) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setDate(1, UsefulMethods.getSQLDate(reglement.getDateReglement()));
            prest.setInt(2, reglement.getIdModeReglement());
            prest.setInt(3, reglement.getNumClient());
            prest.setString(4, reglement.getSaisiPar());
            prest.setDate(5, UsefulMethods.getSQLDate(reglement.getSaisiLe()));
            prest.setString(6, reglement.getObservations());
            prest.setDouble(7, reglement.getMontant());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Reglement Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int setReglement(Reglement reglement) {
        if (DBConnection.con == null)
            return -1;

        String sql = "UPDATE `reglement` SET `DateReglement` = ?, `IDModeReglement` = ?, `NumClient` = ?, " +
                "`SaisiPar` = ?, `SaisiLe` = ?, `Observations` = ?, `Montant` = ? WHERE `IDReglement` = ?;";

        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setDate(1, UsefulMethods.getSQLDate(reglement.getDateReglement()));
            prest.setInt(2, reglement.getIdModeReglement());
            prest.setInt(3, reglement.getNumClient());
            prest.setString(4, reglement.getSaisiPar());
            prest.setDate(5, UsefulMethods.getSQLDate(reglement.getSaisiLe()));
            prest.setString(6, reglement.getObservations());
            prest.setDouble(7, reglement.getMontant());
            prest.setInt(8, reglement.getIdReglement());

            System.out.println(reglement);

            int status = prest.executeUpdate();
            System.out.println(status);
            return status;
        } catch (SQLException se) {
            System.out.println("Edit Reglement Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteReglement(int idReglement) {
        if (DBConnection.con == null) // Connection failed !
            return -1;

        String sql = "DELETE FROM `Reglement` WHERE IDReglement = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, idReglement);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Produit Error SQL");
            se.printStackTrace();
            return -1;
        }
    }
}
