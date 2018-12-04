package com.houarizegai.gestioncommercial.java.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FournisseurController implements Initializable
{
    @FXML // Parent of all nodes (root node)
    private StackPane root;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    @FXML // Table fournisseur
    private JFXTreeTableView<?> tableFournisseur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onDetail() {

    }

    @FXML
    private void onAdd() {

    }

    @FXML
    private void onDelete() {

    }


    @FXML
    private void onEdit() {

    }
}
