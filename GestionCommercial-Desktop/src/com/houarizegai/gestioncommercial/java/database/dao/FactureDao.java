package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Facture;
import com.houarizegai.gestioncommercial.java.database.models.LigneFacture;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.FactureBuilder;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.LigneFactureBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FactureDao {

    public static List<Facture> getFacturesByClient(int numClient) { // Get all facture with ligne facture from database
        if (DBConnection.con == null) // Check connection
            return null;

        List<Facture> factureList = new LinkedList<>();

        String sql = "SELECT * FROM Facture WHERE NumClient = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, numClient);
            ResultSet rsFac = prest.executeQuery();
            while (rsFac.next()) {
                Facture facture = new FactureBuilder()
                        .setNumFacture(rsFac.getInt("NumFacture"))
                        .setDateFacture(rsFac.getDate("DateFacture"))
                        .setNumClient(rsFac.getInt("NumClient"))
                        .setIdAdresseFacturation(rsFac.getInt("IDAdresseFacturation"))
                        .setIdModeReglement(rsFac.getInt("IDModeReglement"))
                        .setTotalHT(rsFac.getDouble("TotalHt"))
                        .setTotalTVA(rsFac.getDouble("TotalTva"))
                        .setTotalFraisPort(rsFac.getDouble("TotalFraisPort"))
                        .setTotalTTC(rsFac.getDouble("TotalTtc"))
                        .setRemise(rsFac.getDouble("Remise"))
                        .setAcquittee(rsFac.getBoolean("Acquittee"))
                        .setSaisiPar(rsFac.getString("SaisiPar"))
                        .setSaisiLe(rsFac.getDate("SaisiLe"))
                        .setObservations(rsFac.getString("Observations"))
                        .setNumCommande(rsFac.getInt("NumCommande"))
                        .setLigneFactures(null)
                        .build();

                factureList.add(facture);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }


        return factureList;
    }

    public static List<Facture> getFactures() { // Get all facture with ligne facture from database
        if (DBConnection.con == null) // Check connection
            return null;

        List<Facture> factureList = new LinkedList<>();

        String sql = "SELECT * FROM Facture;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            ResultSet rsFac = prest.executeQuery();
            String sqlLigneFac = "SELECT * FROM LigneFac WHERE NumFacture = ?;";
            ResultSet rsLigneFac;
            while (rsFac.next()) {
                // get ligne facture
                List<LigneFacture> ligneFactures = getLigneFactures(rsFac.getInt("NumFacture"));

                Facture facture = new FactureBuilder()
                        .setNumFacture(rsFac.getInt("NumFacture"))
                        .setDateFacture(rsFac.getDate("DateFacture"))
                        .setNumClient(rsFac.getInt("NumClient"))
                        .setIdAdresseFacturation(rsFac.getInt("IDAdresseFacturation"))
                        .setIdModeReglement(rsFac.getInt("IDModeReglement"))
                        .setTotalHT(rsFac.getDouble("TotalHt"))
                        .setTotalTVA(rsFac.getDouble("TotalTva"))
                        .setTotalFraisPort(rsFac.getDouble("TotalFraisPort"))
                        .setTotalTTC(rsFac.getDouble("TotalTtc"))
                        .setRemise(rsFac.getDouble("Remise"))
                        .setAcquittee(rsFac.getBoolean("Acquittee"))
                        .setSaisiPar(rsFac.getString("SaisiPar"))
                        .setSaisiLe(rsFac.getDate("SaisiLe"))
                        .setObservations(rsFac.getString("Observations"))
                        .setNumCommande(rsFac.getInt("NumCommande"))
                        .setLigneFactures(ligneFactures)
                        .build();

                factureList.add(facture);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }


        return factureList;
    }

    public static int addFacture(Facture facture) { // Add facture with Ligne facture
        if (DBConnection.con == null) // Connection failed !
            return -1;

        // SQL query for insert in facture table
        String sql = "INSERT INTO `facture`(`NumFacture`, `DateFacture`, `NumClient`, `IDAdresseFacturation`, `IDModeReglement`, `TotalHt`, `TotalTva`, " +
                "`TotalFraisPort`, `TotalTtc`, `Remise`, `Acquittee`, `SaisiPar`, `SaisiLe`, `Observations`, `NumCommande`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        int status = 0;
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setInt(1, facture.getNumFacture());
            prest.setDate(2, UsefulMethods.getSQLDate(facture.getDateFacture()));
            prest.setInt(3, facture.getNumClient());
            //prest.setInt(4, facture.getIdAdresseFacturation());
            prest.setString(4, null);
            prest.setInt(5, facture.getIdModeReglement());
            prest.setDouble(6, facture.getTotalHT());
            prest.setDouble(7, facture.getTotalTVA());
            prest.setDouble(8, facture.getTotalFraisPort());
            prest.setDouble(9, facture.getTotalHT());
            prest.setDouble(10, facture.getRemise());
            prest.setBoolean(11, facture.isAcquittee());
            prest.setString(12, facture.getSaisiPar());
            prest.setDate(13, UsefulMethods.getSQLDate(facture.getSaisiLe()));
            prest.setString(14, facture.getObservations());
            prest.setInt(15, facture.getNumCommande());

            status = prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("SQL Error: Add Facture");
            se.printStackTrace();
            return 0;
        }

        // SQL query for insert in LigneFacture table
        sql = "INSERT INTO `lignefac`(`NumFacture`, `Reference`, `LibProd`, `Quantite`, `PrixVente`, `Remise`, `TauxTva`, `IDLigneCde`, `OrdreAffichage`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement prest = null;
        for (LigneFacture ligneFacture : facture.getLigneFactures()) {
            try {
                prest = DBConnection.con.prepareStatement(sql);
                prest.setInt(1, ligneFacture.getNumFacture());
                prest.setString(2, ligneFacture.getReference());
                prest.setString(3, ligneFacture.getLibProd());
                prest.setInt(4, ligneFacture.getQuantite());
                prest.setDouble(5, ligneFacture.getPrixVente());
                prest.setDouble(6, ligneFacture.getRemise());
                prest.setDouble(7, ligneFacture.getTauxTva());
                //prest.setInt(8, ligneFacture.getIdLigneCde());
                prest.setString(8, null);
                prest.setInt(9, ligneFacture.getOrdreAffichage());

                status = prest.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return status;
    }

    public static Facture getNextFacture(int currentNumFact) {
        String sql = "SELECT* FROM Facture WHERE NumFacture = (SELECT MIN(NumFacture) FROM Facture WHERE NumFacture > " + currentNumFact + ");";

        return getFactureFromQuery(sql);
    }

    public static Facture getPreviousFacture(int currentNumFact) {
        String sql = "SELECT* FROM Facture WHERE NumFacture = (SELECT MAX(NumFacture) FROM Facture WHERE NumFacture < " + currentNumFact + ");";

        return getFactureFromQuery(sql);
    }

    public static Facture getFirstFacture() {
        String sql = "SELECT * FROM Facture LIMIT 1;";

        return getFactureFromQuery(sql);
    }

    public static Facture getLastFacture() {
        String sql = "SELECT * FROM Facture ORDER BY NumFacture DESC LIMIT 1;";

        return getFactureFromQuery(sql);
    }

    private static Facture getFactureFromQuery(String sql) {
        if(DBConnection.con == null)
            return null;
        Facture facture = null;
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int numFact = rs.getInt("NumFacture");
                List<LigneFacture> ligneFactures = getLigneFactures(numFact);
                facture = new FactureBuilder()
                        .setNumFacture(numFact)
                        .setDateFacture(rs.getDate("DateFacture"))
                        .setNumClient(rs.getInt("NumClient"))
                        .setIdAdresseFacturation(rs.getInt("IDAdresseFacturation"))
                        .setIdModeReglement(rs.getInt("IDModeReglement"))
                        .setTotalHT(rs.getDouble("TotalHt"))
                        .setTotalTVA(rs.getDouble("TotalTva"))
                        .setTotalFraisPort(rs.getDouble("TotalFraisPort"))
                        .setTotalTTC(rs.getDouble("TotalTtc"))
                        .setRemise(rs.getDouble("Remise"))
                        .setAcquittee(rs.getBoolean("Acquittee"))
                        .setSaisiPar(rs.getString("SaisiPar"))
                        .setSaisiLe(rs.getDate("SaisiLe"))
                        .setObservations(rs.getString("Observations"))
                        .setNumCommande(rs.getInt("NumCommande"))
                        .setLigneFactures(ligneFactures)
                        .build();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return facture;
    }

    public static List<LigneFacture> getLigneFactures(int numFacture) {
        if (DBConnection.con == null)
            return null;

        List<LigneFacture> ligneFactures = null;
        try {
            String sql = "SELECT * FROM LigneFac WHERE NumFacture = ?;";
            PreparedStatement ps = DBConnection.con.prepareStatement(sql);
            ps.setInt(1, numFacture);
            ResultSet rs = ps.executeQuery();

            ligneFactures = new LinkedList<>();
            while (rs.next()) {
                LigneFacture ligneFacture = new LigneFactureBuilder()
                        .setNumFacture(rs.getInt("NumFacture"))
                        .setReference(rs.getString("Reference"))
                        .setLibProd(rs.getString("LibProd"))
                        .setQuantite(rs.getInt("Quantite"))
                        .setPrixVente(rs.getDouble("PrixVente"))
                        .setRemise(rs.getDouble("Remise"))
                        .setTauxTva(rs.getInt("TauxTva"))
                        .setIdLigneCde(rs.getInt("IdLigneCde"))
                        .setOrdreAffichage(rs.getInt("OrdreAffichage"))
                        .build();
                ligneFactures.add(ligneFacture);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return ligneFactures;
    }

    public static void main(String[] args) {
        System.out.println(getLastFacture());
//        for(Facture facture : getFactures())
//            System.out.println(facture);
    }
}
