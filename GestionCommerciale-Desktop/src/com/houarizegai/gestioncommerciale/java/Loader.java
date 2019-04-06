package com.houarizegai.gestioncommerciale.java;

import com.houarizegai.gestioncommerciale.java.controllers.SplashScreenController;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Loader extends Preloader {

    private Stage splashScreenStage;
    private Scene scene;

    @Override
    public void init() throws Exception {
        Parent splashView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommerciale/resources/views/SplashScreen.fxml"));
        scene = new Scene(splashView);
    }

    @Override
    public void start(Stage stage) {
        splashScreenStage = stage;
        splashScreenStage.setScene(scene);
        splashScreenStage.initStyle(StageStyle.UNDECORATED);
        splashScreenStage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification notification) {
        if(notification instanceof ProgressNotification) {
            double progressValue = ((ProgressNotification) notification).getProgress();
            SplashScreenController.staticProgress.setProgress(progressValue);
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_START:
                splashScreenStage.hide();
                break;
        }
     }
}
