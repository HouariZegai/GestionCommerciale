package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClientDao {

    public static Client getClient(int numClient) {
        String sql = "SELECT * FROM Client WHERE NumClient=?;";
        Client client = null;
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, numClient);
            ResultSet rs = prest.executeQuery();
            if(rs.next()) {
                client = new ClientBuilder()
                        .setNumClient(rs.getInt(1))
                        .setSociete(rs.getString(2))
                        .setCivilite(rs.getString(3))
                        .setNomClient(rs.getString(4))
                        .setPrenom(rs.getString(5))
                        .setAdresse(rs.getString(6))
                        .setCodePostal(rs.getString(7))
                        .setVille(rs.getString(8))
                        .setPays(rs.getString(9))
                        .setTelephone(rs.getString(10))
                        .setMobile(rs.getString(11))
                        .setFax(rs.getString(12))
                        .setEmail(rs.getString(13))
                        .setType(rs.getInt(14))
                        .setLivreMemeAdresse(rs.getBoolean(15))
                        .setFactureMemeAdresse(rs.getBoolean(16))
                        .setExemptTva(rs.getBoolean(17))
                        .setSaisiPar(rs.getString(18))
                        .setSaisiLe(rs.getDate(19))
                        .setAuteurModif(rs.getString(20))
                        .setDateModif(rs.getDate(21))
                        .setObservations(rs.getString(22))
                        .build();
            }

        } catch (SQLException se) {
            System.out.println("SQL Error in getClient");
            se.printStackTrace();
        }

        return client;
    }

    public static List<Client> getClients() { // Get all client from database
        String sql = "SELECT * FROM Client;";

        List<Client> clients = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Client client = new ClientBuilder()
                        .setNumClient(rs.getInt(1))
                        .setSociete(rs.getString(2))
                        .setCivilite(rs.getString(3))
                        .setNomClient(rs.getString(4))
                        .setPrenom(rs.getString(5))
                        .setAdresse(rs.getString(6))
                        .setCodePostal(rs.getString(7))
                        .setVille(rs.getString(8))
                        .setPays(rs.getString(9))
                        .setTelephone(rs.getString(10))
                        .setMobile(rs.getString(11))
                        .setFax(rs.getString(12))
                        .setEmail(rs.getString(13))
                        .setType(rs.getInt(14))
                        .setLivreMemeAdresse(rs.getBoolean(15))
                        .setFactureMemeAdresse(rs.getBoolean(16))
                        .setExemptTva(rs.getBoolean(17))
                        .setSaisiPar(rs.getString(18))
                        .setSaisiLe(rs.getDate(19))
                        .setAuteurModif(rs.getString(20))
                        .setDateModif(rs.getDate(21))
                        .setObservations(rs.getString(22))
                        .build();

                clients.add(client);
            }

        } catch (SQLException se) {
            System.out.println("Get All Client Error SQL");
            se.printStackTrace();
            return null;
        }

        return clients;
    }

    public static int setClient(Client client) { // Edit Client
        StringBuilder sql = new StringBuilder("UPDATE Client SET");
        sql.append(" Societe = ?");
        sql.append(", Civilite = ?");
        sql.append(", nomClient = ?");
        sql.append(", prenom = ?");
        sql.append(", adresse = ?");
        sql.append(", codePostal = ?");
        sql.append(", ville = ?");
        sql.append(", Pays = ?");
        sql.append(", telephone = ?");
        sql.append(", mobile = ?");
        sql.append(", fax = ?");
        sql.append(", email = ?");
        sql.append(", type = ?");
        sql.append(", livreMemeAdresse = ?");
        sql.append(", factureMemeAdresse = ?");
        sql.append(", exemptTva = ?");
        sql.append(", saisiPar = ?");
        sql.append(", saisiLe = ?");
        sql.append(", auteurModif = ?");
        sql.append(", dateModif = ?");
        sql.append(", observations = ?");

        sql.append(" WHERE numClient = ?;");
        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, client.getSociete());
            prest.setString(2, client.getCivilite());
            prest.setString(3, client.getNomClient());
            prest.setString(4, client.getPrenom());
            prest.setString(5, client.getAdresse());
            prest.setString(6, client.getCodePostal());
            prest.setString(7, client.getVille());
            prest.setString(8, client.getPays());
            prest.setString(9, client.getTelephone());
            prest.setString(10, client.getMobile());
            prest.setString(11, client.getFax());
            prest.setString(12, client.getEmail());
            prest.setInt(13, client.getType());
            prest.setBoolean(14, client.isLivreMemeAdresse());
            prest.setBoolean(15, client.isFactureMemeAdresse());
            prest.setBoolean(16, client.isExemptTva());
            prest.setString(17, client.getSaisiPar());
            prest.setDate(18, UsefulMethods.getSQLDate(client.getSaisiLe()));
            prest.setString(19, client.getAuteurModif());
            prest.setDate(20, UsefulMethods.getSQLDate(client.getDateModif()));
            prest.setString(21, client.getObservations());
            prest.setInt(22, client.getNumClient());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Client Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteClient(int numClient) { // Delete Client
        String sql = "DELETE FROM Client WHERE numClient = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, numClient);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Client Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addclient(Client client) {

        String sql = "INSERT INTO Client (`Societe`, `Civilite`, `NomClient`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Pays`, `Telephone`, `Mobile`, `Fax`, `Email`, `Type`, `LivreMemeAdresse`, `FactureMemeAdresse`, `ExemptTva`, `SaisiPar`, `SaisiLe`, `AuteurModif`, `DateModif`, `Observations`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, client.getSociete());
            prest.setString(2, client.getCivilite());
            prest.setString(3, client.getNomClient());
            prest.setString(4, client.getPrenom());
            prest.setString(5, client.getAdresse());
            prest.setString(6, client.getCodePostal());
            prest.setString(7, client.getVille());
            prest.setString(8, client.getPays());
            prest.setString(9, client.getTelephone());
            prest.setString(10, client.getMobile());
            prest.setString(11, client.getFax());
            prest.setString(12, client.getEmail());
            prest.setInt(13, client.getType());
            prest.setBoolean(14, client.isLivreMemeAdresse());
            prest.setBoolean(15, client.isFactureMemeAdresse());
            prest.setBoolean(16, client.isExemptTva());
            prest.setString(17, client.getSaisiPar());
            prest.setDate(18, UsefulMethods.getSQLDate(client.getSaisiLe()));
            prest.setString(19, client.getAuteurModif());
            prest.setDate(20, UsefulMethods.getSQLDate(client.getDateModif()));
            prest.setString(21, client.getObservations());

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Client Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
