package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClientDao {

    public List<Client> getAllClients() { // Get all client from database
        String sql = "SELECT * FROM Client;";

        List<Client> clients = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Client client = new ClientBuilder()
                        .setNumClient(rs.getInt(0))
                        .setSociete(rs.getString(1))
                        .setCivilite(rs.getString(2))
                        .setNomClient(rs.getString(3))
                        .setPrenom(rs.getString(4))
                        .setAdresse(rs.getString(5))
                        .setCodePostal(rs.getString(6))
                        .setVille(rs.getString(7))
                        .setPays(rs.getString(8))
                        .setTelephone(rs.getString(9))
                        .setMobile(rs.getString(10))
                        .setFax(rs.getString(11))
                        .setEmail(rs.getString(12))
                        .setType(rs.getInt(13))
                        .setLivreMemeAdresse(rs.getBoolean(14))
                        .setFactureMemeAdresse(rs.getBoolean(15))
                        .setExemptTva(rs.getBoolean(16))
                        .setSaisiPar(rs.getString(17))
                        .setSaisiLe(rs.getDate(18))
                        .setAuteurModif(rs.getString(19))
                        .setDateModif(rs.getDate(20))
                        .setObservations(rs.getString(21))
                        .build();

                clients.add(client);
            }

        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }

        return clients;
    }

    public boolean setClient(int numClient, Client client) { // Edit Client
        StringBuilder sql = new StringBuilder("UPDATE Client SET");
        sql.append(" societe = ?");
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
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, client.getSociete());
            prest.setString(1, client.getSociete());
            prest.setString(2, client.getNomClient());
            prest.setString(3, client.getPrenom());
            prest.setString(4, client.getAdresse());
            prest.setString(5, client.getCodePostal());
            prest.setString(6, client.getVille());
            prest.setString(7, client.getPays());
            prest.setString(8, client.getTelephone());
            prest.setString(9, client.getMobile());
            prest.setString(10, client.getFax());
            prest.setString(11, client.getEmail());
            prest.setInt(12, client.getType());
            prest.setBoolean(13, client.isLivreMemeAdresse());
            prest.setBoolean(14, client.isFactureMemeAdresse());
            prest.setBoolean(15, client.isExemptTva());
            prest.setString(16, client.getSaisiPar());
            prest.setDate(17, UsefulMethods.getSQLDate(client.getSaisiLe()));
            prest.setString(18, client.getAuteurModif());
            prest.setDate(19, UsefulMethods.getSQLDate(client.getDateModif()));
            prest.setString(20, client.getObservations());
            prest.setInt(21, numClient);

            // fill all column
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    public int deleteClient(int numClient) { // Delete Client
        String sql = "DELETE FROM Client WHERE numClient = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, numClient);
            return prest.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        }
    }

    public int addclient(Client client) {

        String sql = "INSERT INTO Client VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, client.getSociete());
            prest.setString(1, client.getSociete());
            prest.setString(2, client.getNomClient());
            prest.setString(3, client.getPrenom());
            prest.setString(4, client.getAdresse());
            prest.setString(5, client.getCodePostal());
            prest.setString(6, client.getVille());
            prest.setString(7, client.getPays());
            prest.setString(8, client.getTelephone());
            prest.setString(9, client.getMobile());
            prest.setString(10, client.getFax());
            prest.setString(11, client.getEmail());
            prest.setInt(12, client.getType());
            prest.setBoolean(13, client.isLivreMemeAdresse());
            prest.setBoolean(14, client.isFactureMemeAdresse());
            prest.setBoolean(15, client.isExemptTva());
            prest.setString(16, client.getSaisiPar());
            prest.setDate(17, UsefulMethods.getSQLDate(client.getSaisiLe()));
            prest.setString(18, client.getAuteurModif());
            prest.setDate(19, UsefulMethods.getSQLDate(client.getDateModif()));
            prest.setString(20, client.getObservations());

            return prest.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        }
    }
}
