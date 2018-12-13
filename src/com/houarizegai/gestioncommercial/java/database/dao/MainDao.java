package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDao {

    public static int getCurrentAutoIncrement(String tableName) { // Get number of current auto increment of table
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "';";
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt("AUTO_INCREMENT");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return 0;
    }
}
