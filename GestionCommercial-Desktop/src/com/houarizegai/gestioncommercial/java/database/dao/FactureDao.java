package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.models.Facture;
import com.houarizegai.gestioncommercial.java.database.models.LigneFacture;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.FactureBuilder;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.LigneFactureBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FactureDao {
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
}
