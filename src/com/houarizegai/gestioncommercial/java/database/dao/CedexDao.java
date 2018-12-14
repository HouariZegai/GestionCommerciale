package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Cedex;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CedexDao {
    public static List<Cedex> getCedexs() { // get all Cedex
        String sql = "SELECT * FROM Cedex;";

        List<Cedex> cedexs = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Cedex cedex = new Cedex(rs.getInt("IDCedex"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"));

                cedexs.add(cedex);
            }

        } catch (SQLException se) {
            System.out.println("Get All Cedex Error SQL");
            se.printStackTrace();
            return null;
        }

        return cedexs;
    }

    public static int setCedex(Cedex cedex) { // Edit Cedex
        StringBuilder sql = new StringBuilder("UPDATE `Cedex` SET `CodePostal` = ?, `Ville` = ? WHERE `IDCedex` = ?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, cedex.getCodePostal());
            prest.setString(2, cedex.getVille());
            prest.setInt(3, cedex.getIdCedex());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Cedex Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteCedex(int idCedex) { // Remove Cedex
        String sql = "DELETE FROM `Cedex` WHERE `IDCedex` = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, idCedex);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Cedex Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addCedex(Cedex cedex) { // Add new Cedex

        String sql = "INSERT INTO `Cedex`(`CodePostal`, `Ville`) VALUES (?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, cedex.getCodePostal());
            prest.setString(2, cedex.getVille());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Cedex Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
