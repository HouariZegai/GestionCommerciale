package com.houarizegai.gestioncommerciale.java.controllers;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML // image slider
    private ImageView imgSlider;

    private final int NUMBER_IMAGE_SLIDER = 3;
    private final Image[] sliderImages = new Image[NUMBER_IMAGE_SLIDER];
    private int sliderCounter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSlider();
    }

    private void initSlider() {
        // Load slider images
        for(int i = 0; i < NUMBER_IMAGE_SLIDER; i++) {
            sliderImages[i] = new Image("com/houarizegai/gestioncommerciale/resources/images/home/slider/" + i + ".png");
        }

        // Make auto change the slider in duration
        Timeline sliderTimer = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            FadeTransition ft = new FadeTransition();
            ft.setNode(imgSlider);
            ft.setDuration(new Duration(4000));
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(0);
            ft.setAutoReverse(true);
            ft.play();
            sliderCounter = ++sliderCounter % NUMBER_IMAGE_SLIDER;
            System.out.println(sliderCounter);
            imgSlider.setImage(sliderImages[sliderCounter]);
        }),
                new KeyFrame(Duration.seconds(4))
        );
        sliderTimer.setCycleCount(Animation.INDEFINITE);
        sliderTimer.play();
    }
}
