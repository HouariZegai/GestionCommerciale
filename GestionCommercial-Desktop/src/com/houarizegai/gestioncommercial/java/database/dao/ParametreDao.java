package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Parametre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ParametreDao {
    public static List<Parametre> getParametres() { // get all Parametre
        String sql = "SELECT * FROM Parametre;";

        List<Parametre> parametres = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Parametre parametre = new Parametre(rs.getInt("IDParametre"),
                        rs.getString("NomParametre"),
                        rs.getString("Valuer"));

                parametres.add(parametre);
            }

        } catch (SQLException se) {
            System.out.println("Get All Parametre Error SQL");
            se.printStackTrace();
            return null;
        }

        return parametres;
    }

    public static int setParametre(Parametre parametre) { // Edit Parametre
        StringBuilder sql = new StringBuilder("UPDATE `Parametre` SET `NomParametre` = ?, `Valuer` = ? WHERE `IDParametre` = ?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, parametre.getNomParametre());
            prest.setString(2, parametre.getValuer());
            prest.setInt(3, parametre.getIdParametre());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Parametre Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteParametre(int idParametre) { // Remove Parametre
        String sql = "DELETE FROM `Parametre` WHERE `IDParametre` = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, idParametre);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Parametre Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addParametre(Parametre parametre) { // Add new Parametre

        String sql = "INSERT INTO `Parametre`(`NomParametre`, `Valuer`) VALUES (?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, parametre.getNomParametre());
            prest.setString(2, parametre.getValuer());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Parametre Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
