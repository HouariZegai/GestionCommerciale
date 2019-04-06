package com.houarizegai.gestioncommerciale.java.controllers;

import com.houarizegai.gestioncommerciale.java.Launcher;
import com.houarizegai.gestioncommerciale.java.global.plugin.ViewManager;
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
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SystemController implements Initializable {

    @FXML // Root node (parent of all nodes)
    private StackPane root;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane paneHeader;

    @FXML // This label show date and time (dynamic clock)
    private Label lblDate;

    @FXML // menu icon
    private JFXHamburger hamburgerMenu;

    private HamburgerSlideCloseTransition burgerTask; // For make animation to menu icon

    @FXML
    private StackPane holderPane;

    @FXML // Drawer (Left Menu)
    private JFXDrawer drawerMenu;

    private VBox menuDrawerPane; // content of drawer (view)

    // Settings & About dialogs
    public static JFXDialog dialogSettings, dialogAbout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBinds();

        initMenu();
        initClock();
        initActionHomeBoxes();

        // Init about & settings dialog
        dialogAbout = new JFXDialog(root, (AnchorPane) ViewManager.getInstance().get("About"), JFXDialog.DialogTransition.TOP);
        dialogSettings = new JFXDialog(root, (VBox) ViewManager.getInstance().get("Settings"), JFXDialog.DialogTransition.BOTTOM);

        // Show home view
        setNode(ViewManager.getInstance().get("Home"));
    }

    private void initBinds() {
        // Container fill scroll
        paneHeader.prefWidthProperty().bind(scrollPane.widthProperty());
        paneHeader.prefHeightProperty().bind(scrollPane.heightProperty().subtract(20));

        holderPane.prefWidthProperty().bind(paneHeader.prefWidthProperty());
        holderPane.prefHeightProperty().bind(paneHeader.prefHeightProperty());

        // Make slider image fill parent
        ImageView imgSlider = (ImageView) ViewManager.getInstance().get("Home").lookup("#slider-img");
        imgSlider.fitWidthProperty().bind(holderPane.widthProperty());
        imgSlider.fitHeightProperty().bind(holderPane.heightProperty());
    }

    private void initMenu() { // initalize menu (show / hide)
        menuDrawerPane = (VBox) ViewManager.getInstance().get("Menu");
        drawerMenu.setSidePane(menuDrawerPane);

        burgerTask = new HamburgerSlideCloseTransition(hamburgerMenu);
        //burgerTask.setRate(burgerTask.getRate() * -1);
        hamburgerMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showHideMenu());

        // Add action to Menu Item
        for(Node node : menuDrawerPane.getChildren()) {
            if(node.getAccessibleText() != null) {
                if(node.getAccessibleText().equalsIgnoreCase("btnHome")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("Home"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnClient")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("Client"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnFicheClient")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("FicheClient"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnFournisseur")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("Fournisseur"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnProduit")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("Produit"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnFacture")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("Facture"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnReglement")) {
                    ((JFXButton) node).setOnAction(e -> {
                        setNode(ViewManager.getInstance().get("Reglement"));
                        showHideMenu(); // Hide menu
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("onLogout")) {
                    ((JFXButton) node).setOnAction(e -> { // switch to login form
                        ViewManager.getInstance().get("Login").getScene().setRoot(new Group()); // remove login from scene
                        ((Stage) root.getScene().getWindow())
                                .setScene(new Scene(ViewManager.getInstance().get("Login")));
                        Launcher.centerOnScreen(); // make stage in the center
                        showHideMenu(); // Hide menu
                        clearAll();
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
        ObservableList<Node> boxItems = ((HBox) ViewManager.getInstance().get("Home")
                .lookup("#cards-container")).getChildren();

        boxItems.get(0).setOnMouseClicked(e -> setNode(ViewManager.getInstance().get("Client")));
        boxItems.get(1).setOnMouseClicked(e -> setNode(ViewManager.getInstance().get("FicheClient")));
        boxItems.get(2).setOnMouseClicked(e -> setNode(ViewManager.getInstance().get("Fournisseur")));
        boxItems.get(3).setOnMouseClicked(e -> setNode(ViewManager.getInstance().get("Produit")));
        boxItems.get(4).setOnMouseClicked(e -> setNode(ViewManager.getInstance().get("Facture")));
        boxItems.get(5).setOnMouseClicked(e -> setNode(ViewManager.getInstance().get("Reglement")));
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);
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
        dialogSettings.show();
    }

    @FXML
    private void onShowAbout() {
        dialogAbout.show();
    }

    private void clearAll() {
        setNode(ViewManager.getInstance().get("Home"));
    }
}
