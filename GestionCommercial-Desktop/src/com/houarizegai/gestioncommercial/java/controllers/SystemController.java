package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.Launcher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SystemController implements Initializable {

    // Root node (parent of all nodes)
    @FXML
    private StackPane root;

    @FXML // This label show date and time (dynamic clock)
    private Label lblDate;

    @FXML // icon show/hide menu
    private JFXHamburger hamburgerMenu;
    // For make animation to hamburgerMenu
    private HamburgerSlideCloseTransition burgerTask;

    @FXML
    private StackPane holderPane;
    // Drawer (Left Menu)
    @FXML
    private JFXDrawer drawerMenu;
    // content of drawer (view)
    private VBox menuDrawerPane;

    // [Client, Fournisseur, Produit] GUI (FXML)
    private StackPane clientView, fournisseurView, produitView, factureView;
    private VBox homeView;

    // For show Settings/About Dialog
    public static JFXDialog dialogSettings, dialogAbout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            homeView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Home.fxml"));
            clientView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Client.fxml"));
            fournisseurView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Fournisseur.fxml"));
            produitView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Produit.fxml"));
            factureView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/FactureFournisseur.fxml"));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        // Initialize the image (to fill parent)
        ImageView imgSlider = (ImageView) ((StackPane) homeView.getChildren().get(0)).getChildren().get(0);
        imgSlider.fitWidthProperty().bind(holderPane.widthProperty());
        imgSlider.fitHeightProperty().bind(holderPane.heightProperty());

        initMenu();
        initClock();
        initActionHomeBoxes();

        // Launch Home view
        setNode(homeView);

        // Init Dialog About
        try {
            AnchorPane aboutView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/About.fxml"));
            dialogAbout = new JFXDialog(root, aboutView, JFXDialog.DialogTransition.TOP);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initMenu() { // initalize menu (show / hide)
        try {
            menuDrawerPane = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Menu.fxml"));
            drawerMenu.setSidePane(menuDrawerPane);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        burgerTask = new HamburgerSlideCloseTransition(hamburgerMenu);
        //burgerTask.setRate(burgerTask.getRate() * -1);
        hamburgerMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showHideMenu());

        // Add action to Menu Item
        for(Node node : menuDrawerPane.getChildren()) {
            if(node.getAccessibleText() != null) {
                if(node.getAccessibleText().equalsIgnoreCase("btnHome")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(homeView);
                        showHideMenu();
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnClient")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(clientView);
                        showHideMenu();
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnFournisseur")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(fournisseurView);
                        showHideMenu();
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnProduit")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(produitView);
                        showHideMenu();
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnFacture")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(factureView);
                        showHideMenu();
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("onLogout")) {
                    ((JFXButton) node).setOnAction(e -> { // switch to login form
                        try {
                            Parent loginView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Login.fxml"));
                            ((Stage) holderPane.getScene().getWindow()).setScene(new Scene(loginView));
                            Launcher.centerOnScreen(); // make stage in the center
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("onExit")) {
                    // Exit application
                    ((JFXButton) node).setOnAction(e -> Platform.exit());
                }
                // close menu
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
                drawerMenu.close();
                drawerMenu.setStyle("-fx-pref-width: 0px");
            }
        }
    }

    private void showHideMenu() {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();

        if(drawerMenu.isShown()) {
            drawerMenu.close();
            drawerMenu.setStyle("-fx-pref-width: 0px");
        } else {
            drawerMenu.setStyle("-fx-pref-width: 270px");
            drawerMenu.open();
        }
    }

    private void initClock() {
        // initialize Clock Showing in home
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss    dd/MM/yyyy");
            Date date = new Date();
            lblDate.setText(dateFormat.format(date));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initActionHomeBoxes() { // Add action to boxes
        // Load views

        ObservableList<Node> boxItems = ((HBox) ((HBox) homeView.getChildren().get(1))).getChildren();

        VBox boxClient = (VBox) boxItems.get(0);
        VBox boxFournisseur = (VBox) boxItems.get(1);
        VBox boxProduit = (VBox) boxItems.get(2);
        VBox boxFacture = (VBox) boxItems.get(4);

        boxClient.setOnMouseClicked(e -> setNode(clientView));
        boxFournisseur.setOnMouseClicked(e -> setNode(fournisseurView));
        boxProduit.setOnMouseClicked(e -> setNode(produitView));
        boxFacture.setOnMouseClicked(e -> setNode(factureView));
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void onShowSettings() {
        try {
            VBox settingsView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Settings.fxml"));
            dialogSettings = new JFXDialog(root, settingsView, JFXDialog.DialogTransition.BOTTOM);
            dialogSettings.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private void onShowAbout() {
        dialogAbout.show();
    }
}
