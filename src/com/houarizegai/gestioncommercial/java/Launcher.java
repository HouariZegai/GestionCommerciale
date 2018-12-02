package com.houarizegai.gestioncommercial.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        Parent root = null;
        try { // load the FXML file
            root = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/System.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        stage.setScene(new Scene(root)); // Add the GUI to scene and the scene to stage (GUI > Scene > Stage)

        // Adding icon of App
        stage.getIcons().add(new Image("/com/houarizegai/gestioncommercial/resources/images/gc-logo-48px.png"));
        stage.setTitle("Gestion Commercial"); // Change the title of app
        stage.show(); // make stage visible
    }

    public static void main(String[] args) {
        launch(args);
    }
}
