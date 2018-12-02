package com.houarizegai.gestioncommercial.java.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SystemController implements Initializable {
    @FXML // This label show date and time (dynamic clock)
    private Label lblDate;

    @FXML
    private JFXHamburger hamburgerMenu;

    @FXML
    private StackPane holderPane;
    // Drawer (Left Menu)
    @FXML
    private JFXDrawer drawerMenu;
    // content of drawer (view)
    private VBox menuDrawerPane;
    // CLient GUI (FXML)
    private StackPane clientView;
    private VBox homeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Client.fxml"));
            homeView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Home.fxml"));
        } catch(IOException ioe) {
           ioe.printStackTrace();
        }

        initMenu();
        initClock();

        // Launch Home view
        setNode(homeView);
    }

    private void initMenu() { // initalize menu (show / hide)
        try {
            menuDrawerPane = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Menu.fxml"));
            drawerMenu.setSidePane(menuDrawerPane);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(hamburgerMenu);
        burgerTask.setRate(-1);
        hamburgerMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();

            if(drawerMenu.isShown()) {
                drawerMenu.close();
                drawerMenu.setStyle("-fx-pref-width: 0px");
            } else {
                drawerMenu.setStyle("-fx-pref-width: 300px");
                drawerMenu.open();
            }
        });
        // Add action to Menu Item
        for(Node node : menuDrawerPane.getChildren()) {
            if(node.getAccessibleText() != null) {
                if(node.getAccessibleText().equalsIgnoreCase("btnHome")) {
                    ((JFXButton) node).setOnAction(e -> setNode(homeView));
                } else if(node.getAccessibleText().equalsIgnoreCase("btnClient")) {
                    ((JFXButton) node).setOnAction(e -> setNode(clientView));
                }
            }
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
}
