package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Cedex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ReglementDao {
    public static List<String> getModeReglements() {

        String sql = "SELECT * FROM ModeReglement;";

        List<String> modeReglements = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                modeReglements.add(rs.getString("LibModeReglement"));
            }

        } catch (SQLException se) {
            System.out.println("Get Mode ReglementDao Error SQL");
            se.printStackTrace();
            return null;
        }
        return modeReglements;
    }
}
