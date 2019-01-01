package com.houarizegai.gestioncommercial.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /* DB Information */
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "gestioncommercialdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection con;
    public static String user; // Username of user entered in system

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
