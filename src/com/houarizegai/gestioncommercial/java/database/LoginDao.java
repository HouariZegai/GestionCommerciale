package com.houarizegai.gestioncommercial.java.database;

import com.houarizegai.gestioncommercial.java.database.models.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public int checkLogin(Login login) { // This method check if the user exists in database
        String sql = "SELECT * FROM Login WHERE NomUtilisateur = ? AND MotDePasse = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, login.getNomUtilisateur());
            prest.setString(2, login.getMotDePasse());

            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
               return 1; // user found
            }

            return 0; // user not found
        } catch (SQLException se) {
            se.printStackTrace();
            return -1; // error
        }
    }
}
