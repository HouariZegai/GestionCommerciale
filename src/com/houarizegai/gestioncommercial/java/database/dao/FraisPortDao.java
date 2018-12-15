package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.FraisPort;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FraisPortDao {
    public static List<FraisPort> getFraisPorts() { // get all Frais Port
        String sql = "SELECT * FROM FraisPort;";

        List<FraisPort> fraisPorts = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                FraisPort fraisPort = new FraisPort(rs.getString("CodePort"),
                        rs.getString("LibFraisPort"),
                        rs.getDouble("Montant"));

                fraisPorts.add(fraisPort);
            }

        } catch (SQLException se) {
            System.out.println("Get All Frais Port Error SQL");
            se.printStackTrace();
            return null;
        }

        return fraisPorts;
    }

    public static String getCodePort(String libFraisPort) { // get Code via Libelle
        String sql = "SELECT CodePort FROM FraisPort WHERE LibFraisPort = '" + libFraisPort + "';";
        if(DBConnection.con == null)
            return null; // connection failed
        try {

            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return rs.getString("FraisPort");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public static String getLibFraisPort(String codePort) { // get Libelle via Code
        String sql = "SELECT LibFraisPort FROM FraisPort WHERE CodePort = '" + codePort + "';";
        if(DBConnection.con == null)
            return null; // connection failed
        try {

            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return rs.getString("LibFraisPort");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public static int setFraisPort(FraisPort fraisPort) { // Edit Frais Port
        StringBuilder sql = new StringBuilder("UPDATE `FraisPort` SET `LibFraisPort` = ?, `Montant` = ? WHERE `CodePort` = ?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, fraisPort.getLibFraisPort());
            prest.setDouble(2, fraisPort.getMontant());
            prest.setString(3, fraisPort.getCodePort());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Frais Port Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteFraisPort(String codePort) { // Remove FraisPort
        String sql = "DELETE FROM `FraisPort` WHERE `CodePort` = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, codePort);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Frais Port Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addFraisPort(FraisPort fraisPort) { // Add new Frais Port

        String sql = "INSERT INTO `FraisPort`(`CodePort`, `LibFraisPort`, `Montant`) VALUES (?, ?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, fraisPort.getCodePort());
            prest.setString(2, fraisPort.getLibFraisPort());
            prest.setDouble(3, fraisPort.getMontant());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Frais Port Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
