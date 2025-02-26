package com.example.stockmaster.Telas.Requisicoes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListaRequisicaoApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Requisicoes/ListaRequisicoes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 675, 450);
        stage.setTitle("Lista de Requisições");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
