package com.houarizegai.gestioncommerciale.java.controllers;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AboutController {

    @FXML
    private void btnClose() {
        SystemController.dialogAbout.close();
    }
    
    @FXML
    void goGithub() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.github.com/HouariZegai"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goFacebook() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com/HZegai"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goYoutube() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/HouariZegai"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goLinkedIn() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/HouariZegai"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goTwitter() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.twitter.com/HouariZegai"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goInstagram() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/HouariZegai"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
