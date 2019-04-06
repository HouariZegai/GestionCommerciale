package com.houarizegai.gestioncommerciale.java;

import com.houarizegai.gestioncommerciale.java.global.plugin.ViewManager;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {
    public static Stage stage;

    private static final int TOTAL_VIEW = 12;
    private float incrementValue;
    private double progressValue = 0;

    @Override
    public void init() {
        // load all (database start, check update for application, ...and more)

        incrementValue = 1f / TOTAL_VIEW;

        initDBConnection();

        load("About");
        load("Client");
        load("Facture");
        load("FicheClient");
        load("Fournisseur");
        load("Home");
        load("Login");
        load("Menu");
        load("Produit");
        load("Reglement");
        load("Settings");
        load("System");
    }

    private void initDBConnection() {

    }

    private void load(String name) {
        try {
            Parent view = FXMLLoader.load(getClass()
                    .getResource("/com/houarizegai/gestioncommerciale/resources/views/" + name + ".fxml"));
            ViewManager.getInstance().put(name, view);
            preloaderNotify();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private synchronized void preloaderNotify() {
        progressValue += incrementValue;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progressValue));
    }

    @Override
    public void start(Stage stage) {

        Parent loginView = ViewManager.getInstance().get("Login");
        stage.setScene(new Scene(loginView)); // Add the GUI to scene and the scene to stage (GUI > Scene > Stage)

        // Adding icon of App
        stage.getIcons().add(new Image("/com/houarizegai/gestioncommerciale/resources/images/gc-logo-48px.png"));
        stage.setTitle("Gestion Commerciale"); // Change the title of app
        Launcher.stage = stage;
        stage.show();
    }

    public static void centerOnScreen() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Launcher.stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        Launcher.stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Launcher.class, Loader.class, args);
    }
}
