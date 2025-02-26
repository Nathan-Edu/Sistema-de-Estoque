package com.example.stockmaster.Telas.Materiais;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListaMaterialApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Materiais/ListaMaterial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 670, 450);
        stage.setTitle("Lista de Materiais");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
