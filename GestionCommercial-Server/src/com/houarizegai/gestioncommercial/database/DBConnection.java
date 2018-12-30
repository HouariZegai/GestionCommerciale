package com.houarizegai.gestioncommercial.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /* DB Information */
    private static String HOST = "127.0.0.1";
    private static int PORT = 3306;
    private static String DB_NAME = "gestioncommercialdb";
    private static String USERNAME = "root";
    private static String PASSWORD = "";

    public static Connection con;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        try {
            // Connect to Database
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USERNAME, PASSWORD);
            System.out.println("Connected !");
        } catch(SQLException se) {
            se.printStackTrace();
        }

    }

}
