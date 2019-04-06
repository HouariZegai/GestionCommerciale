package com.houarizegai.gestioncommerciale.java.global.plugin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.HashMap;
import java.util.Map;

public class ViewManager {
    private static ViewManager instance;
    private static final Map<String, Parent> SCREENS = new HashMap<>();
    private static String nameView; /* Current View */

    private ViewManager() {
    }

    public static ViewManager getInstance() {
        if (instance == null)
            instance = new ViewManager();
        return instance;
    }

    public void put(String name, Parent node) {
        nameView = name;
        SCREENS.put(name, node);
    }

    public Parent get(String view) {
        return SCREENS.get(view);
    }

    public int size() {
        return SCREENS.size();
    }

    public Parent getCurrentView() {
        return SCREENS.get(nameView);
    }

    public ObservableList<Parent> getAll() {
        return FXCollections.observableArrayList(SCREENS.values());
    }
}
