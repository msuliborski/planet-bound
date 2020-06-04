package com.suliborski.planetbound.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class GraphicalInterface extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL location = getClass().getResource("/com/suliborski/planetbound/MainView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent/*, 600, 100*/);
        stage.setTitle("Planet Bound");
        stage.setScene(scene);
        com.suliborski.planetbound.ui.gui.Screen controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}