package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public static int checkLogin(Login login) { // This method check if the user exists in database
        String sql = "SELECT `NomUtilisateur` FROM Login WHERE NomUtilisateur = ? AND MotDePasse = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, login.getNomUtilisateur());
            prest.setString(2, login.getMotDePasse());

            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
                DBConnection.user = rs.getString("NomUtilisateur"); // save the current user
               return 1; // user found
            }

            return 0; // user not found
        } catch (SQLException se) {
            se.printStackTrace();
            return -1; // error
        }
    }

    public static int setUsername(String newUsername, String password) {
        if(newUsername.equalsIgnoreCase(DBConnection.user)) { // Check if it's the current username
            return -3;
        }

        if(DBConnection.con == null)
            return -1;
        try {
            String sql = "SELECT * FROM `Login` WHERE NomUtilisateur=?;";
            PreparedStatement ps = DBConnection.con.prepareStatement(sql);
            ps.setString(1, newUsername);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return -2; // Username already exists
            }

            sql = "UPDATE `Login` SET `NomUtilisateur`=? WHERE `NomUtilisateur`=? AND `MotDePasse`=?;";
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, newUsername);
            prest.setString(2, DBConnection.user);
            prest.setString(3, password);
            return prest.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error set Username : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        } // SELECT `NomUtilisateur` FROM Login WHERE NomUtilisateur = ? AND MotDePasse = ?;
    }

    public static int setPassword(String oldPassword, String newPassword) {
        if(DBConnection.con == null)
            return -1;
        try {
            String sql = "UPDATE `Login` SET `MotDePasse`=? WHERE `NomUtilisateur`=? AND `MotDePasse`=?;";
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, newPassword);
            prest.setString(2, DBConnection.user);
            prest.setString(3, oldPassword);
            return prest.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error set Password : " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
