package com.houarizegai.gestioncommercial.java.controllers.forms.reglement;

import com.houarizegai.gestioncommercial.java.controllers.ReglementController;
import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.dao.*;
import com.houarizegai.gestioncommercial.java.database.models.Facture;
import com.houarizegai.gestioncommercial.java.database.models.ModeReglement;
import com.houarizegai.gestioncommercial.java.database.models.Reglement;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ReglementBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;
import com.houarizegai.gestioncommercial.java.utils.regex.ProduitRegex;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EditReglementController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    JFXTextField fieldIdReg;

    @FXML
    private JFXDatePicker pickerDateReg;

    @FXML
    private JFXComboBox<String> comboModeReg;

    @FXML
    private JFXTextField fieldMontant, fieldNumFac;

    @FXML
    private FontAwesomeIconView iconMontant;

    @FXML
    private JFXTextArea areaObs;

    @FXML
    private JFXTextField fieldSearchFacture;

    @FXML
    private JFXTreeTableView<TableFac> tableFac;

    private JFXTreeTableColumn<TableFac, String> colNumFac, colDate, colNumClient, colTotalHt, colTotalTtc;

    private JFXSnackbar toastMsg;

    // Reglement selected infos
    public static Reglement reglementInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);

        /* Get mode reglement from BDD and inserted in combo box */
        List<ModeReglement> modeReglements = ReglementDao.getModeReglements();
        if(modeReglements != null)
            for(ModeReglement modeReglement : modeReglements)
                comboModeReg.getItems().addAll(modeReglement.getLibModeReglement());

        // Init list listener
        fieldMontant.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldMontant, iconMontant, newValue, ProduitRegex.PRIX_HT));

        /* Table facture used to select N° Facture */
        initTableFac();
        loadDataToTableFac();
        initFilterTableInSearch();

        // on Change Select in facture table
        tableFac.setOnMouseClicked((e -> {
            if (tableFac.getSelectionModel().getSelectedItem() == null)
                return;

            fieldNumFac.setText(colNumFac.getCellData(tableFac.getSelectionModel().getSelectedIndex()));
        }));

        onReset();
    }

    private void setValidFont(JFXTextField field, FontAwesomeIconView errorIcon, String newValue, String regex) { // Change the font if not valid or reset color
        if(newValue != null && !newValue.trim().isEmpty() && !newValue.trim().matches(regex)) {
            field.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            errorIcon.setVisible(true);
        } else {
            field.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
            errorIcon.setVisible(false);
        }
    }

    /* Start Table */

    class TableFac extends RecursiveTreeObject<TableFac> {
        StringProperty numFac;
        StringProperty date;
        StringProperty numClient;
        StringProperty totalHt;
        StringProperty totalTtc;

        public TableFac() {

        }

        public TableFac(int numFac, Date date, int numClient, double totalHt, double totalTtc) {
            this.numFac = new SimpleStringProperty(String.valueOf(numFac));
            this.date = new SimpleStringProperty(date.toString());
            this.numClient = new SimpleStringProperty(String.valueOf(numClient));
            this.totalHt = new SimpleStringProperty(String.valueOf(totalHt));
            this.totalTtc = new SimpleStringProperty(String.valueOf(totalTtc));
        }
    }

    private void initTableFac() {
        colNumFac = new JFXTreeTableColumn<>("N° Facture");
        colNumFac.setPrefWidth(100);
        colNumFac.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFac, String> param) -> param.getValue().getValue().numFac);

        colDate = new JFXTreeTableColumn<>("Date");
        colDate.setPrefWidth(100);
        colDate.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFac, String> param) -> param.getValue().getValue().date);

        colNumClient = new JFXTreeTableColumn<>("N° Client");
        colNumClient.setPrefWidth(100);
        colNumClient.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFac, String> param) -> param.getValue().getValue().numClient);

        colTotalHt = new JFXTreeTableColumn<>("Total HT");
        colTotalHt.setPrefWidth(100);
        colTotalHt.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFac, String> param) -> param.getValue().getValue().totalHt);

        colTotalTtc = new JFXTreeTableColumn<>("Total TTC");
        colTotalTtc.setPrefWidth(100);
        colTotalTtc.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFac, String> param) -> param.getValue().getValue().totalTtc);


        tableFac.getColumns().addAll(colNumFac, colDate, colNumClient, colTotalHt, colTotalTtc);
        tableFac.setShowRoot(false);
    }

    private void loadDataToTableFac() {
        List<Facture> factures = FactureDao.getFactures();

        ObservableList<TableFac> listFactures = FXCollections.observableArrayList();
        if (factures != null) {
            for (Facture f : factures) {
                listFactures.add(new TableFac(f.getNumFacture(), f.getDateFacture(), f.getNumClient(), f.getTotalHT(), f.getTotalTTC()));
            }
        }

        final TreeItem<TableFac> treeItem = new RecursiveTreeItem<>(listFactures, RecursiveTreeObject::getChildren);

        try {
            tableFac.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    private void initFilterTableInSearch() {
        fieldSearchFacture.setOnKeyReleased(e -> {
            tableFac.setPredicate((TreeItem<TableFac> facture) -> {
                String numFac = facture.getValue().numFac.getValue().toLowerCase();
                String date = (facture.getValue().date.getValue() == null) ? "" : facture.getValue().date.getValue().toLowerCase();
                String totalHt = (facture.getValue().totalHt.getValue() == null) ? "" : facture.getValue().totalHt.getValue().toLowerCase();
                String totalTtc = (facture.getValue().totalTtc.getValue() == null) ? "" : facture.getValue().totalTtc.getValue().toLowerCase();

                return numFac.contains(fieldSearchFacture.getText().toLowerCase())
                        || date.contains(fieldSearchFacture.getText().toLowerCase())
                        || totalHt.contains(fieldSearchFacture.getText().toLowerCase())
                        || totalTtc.contains(fieldSearchFacture.getText().toLowerCase());

            });
        });
    }

    /* End Table */
    @FXML
    private void onSave() { // Add reglement to database
        if(iconMontant.isVisible() ) {
            toastMsg.show("Svp, il ya des champs n'est pas bien formé", 2000);
            return;
        }

        if(fieldNumFac.getText().isEmpty()) {
            toastMsg.show("Svp, il ya des champs n'est pas bien formé", 2000);
            return;
        }

        // Using builder design pattern to make reglement object
        Reglement reglement = new ReglementBuilder()
                .setIdReglement(Integer.parseInt(fieldIdReg.getText()))
                .setDateReglement(java.sql.Date.valueOf(pickerDateReg.getValue()))
                .setIdModeReglement(ReglementDao.getIdModeReglement(comboModeReg.getSelectionModel().getSelectedItem()))
                .setNumFacture(4)
                .setSaisiPar(DBConnection.user)
                .setSaisiLe(new Date())
                .setObservations(areaObs.getText())
                .setMontant(Double.parseDouble(fieldMontant.getText()))
                .build();

        int status = ReglementDao.setReglement(reglement);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 1500);
                break;
            case 0:
                toastMsg.show("Erreur dans la modification de Reglement !", 1500);
                break;
            default : {
                Notifications.create()
                        .title("Vous avez modifier le Reglement !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();
                onClose();
            }
        }
    }

    @FXML
    private void onReset() { // Add clear screen (remove the content of all nodes in this field)
        // Get auto increment
        fieldIdReg.setText(String.valueOf(reglementInfo.getIdReglement()));
        pickerDateReg.setValue(UsefulMethods.getSQLDate(reglementInfo.getDateReglement()).toLocalDate());
        comboModeReg.getSelectionModel().select(ReglementDao.getLibModeReglement(reglementInfo.getIdModeReglement()));
        fieldMontant.setText(String.valueOf(reglementInfo.getMontant()));
        fieldMontant.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
        iconMontant.setVisible(false);

        areaObs.setText(reglementInfo.getObservations());

        fieldNumFac.setText(String.valueOf(reglementInfo.getNumFacture()));
        fieldSearchFacture.setText(null);
        tableFac.getSelectionModel().clearSelection();
    }

    @FXML
    private void onClose() {
        ReglementController.dialogReglementEdit.close();
    }

}
