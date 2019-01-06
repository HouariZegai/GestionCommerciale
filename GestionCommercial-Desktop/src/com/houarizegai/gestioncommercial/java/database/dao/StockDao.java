package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Stock;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.StockBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class StockDao {
    public static List<Stock> getStocks() { // get all Stock
        String sql = "SELECT * FROM Stock;";

        List<Stock> stocks = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Stock stock = new StockBuilder()
                        .setReference(rs.getString("Reference"))
                        .setQteEnStock(rs.getInt("QteEnStock"))
                        .setQteStockVirtuel(rs.getInt("QteStockVirtuel"))
                        .setAuteurModif(rs.getString("AuteurModif"))
                        .setDateModif(rs.getDate("DateModif"))
                        .build();

                stocks.add(stock);
            }

        } catch (SQLException se) {
            System.out.println("Get All Stock Error SQL");
            se.printStackTrace();
            return null;
        }

        return stocks;
    }

    public static int setStock(Stock stock) { // Edit Stock
        StringBuilder sql = new StringBuilder("UPDATE `stock` SET `QteEnStock`=?,`QteStockVirtuel`=?,`AuteurModif`=?,`DateModif`=? WHERE `Reference`=?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setInt(1, stock.getQteEnStock());
            prest.setInt(2, stock.getQteStockVirtuel());
            prest.setString(3, stock.getAuteurModif());
            prest.setDate(4, UsefulMethods.getSQLDate(stock.getDateModif()));
            prest.setString(5, stock.getReference());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Stock Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteStock(String reference) { // Remove Stock
        String sql = "DELETE FROM `Stock` WHERE `Reference` = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, reference);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Stock Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addStock(Stock stock) { // Add new Stock

        String sql = "INSERT INTO `stock`(`Reference`, `QteEnStock`, `QteStockVirtuel`, `AuteurModif`, `DateModif`) VALUES (?, ?, ?, ?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, stock.getReference());
            prest.setInt(2, stock.getQteEnStock());
            prest.setInt(3, stock.getQteStockVirtuel());
            prest.setString(4, stock.getAuteurModif());
            prest.setDate(5, UsefulMethods.getSQLDate(stock.getDateModif()));

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Stock Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int setStockQte(Stock stock) { // Edit Stock
        StringBuilder sql = new StringBuilder("UPDATE `stock` SET QteEnStock = QteEnStock - ?,`AuteurModif`=?,`DateModif`=? WHERE `Reference`=?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setInt(1, stock.getQteEnStock());
            prest.setString(2, stock.getAuteurModif());
            prest.setDate(3, UsefulMethods.getSQLDate(stock.getDateModif()));
            prest.setString(4, stock.getReference());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Stock Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
