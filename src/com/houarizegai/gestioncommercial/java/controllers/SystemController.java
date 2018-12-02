package com.houarizegai.gestioncommercial.java.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SystemController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/Client.fxml"));
        } catch(IOException ioe) {
           ioe.printStackTrace();
        }

        initMenu();

        // Launch Client view
        setNode(clientView);
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
            } else {
                drawerMenu.open();
            }
        });
        // Add action to Menu Item
        for(Node node : menuDrawerPane.getChildren()) {
            if(node.getAccessibleText() != null) {
                if(node.getAccessibleText().equalsIgnoreCase("btnHome")) {
                    ((JFXButton) node).setOnAction(e ->  {
                        //setNode(null)
                        System.out.println("Show Home View");
                    });
                } else if(node.getAccessibleText().equalsIgnoreCase("btnClient")) {
                    ((JFXButton) node).setOnAction(e -> setNode(clientView));
                }
            }
        }
    }

    private void onMenuDrawer() {
        if(drawerMenu.isShown()) {
            drawerMenu.close();
        }
        else {
            drawerMenu.setSidePane(menuDrawerPane);
            drawerMenu.open();
        }
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
