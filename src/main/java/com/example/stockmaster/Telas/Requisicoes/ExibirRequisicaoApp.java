package com.example.stockmaster.Telas.Requisicoes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExibirRequisicaoApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Requisicoes/ExibirRequisicoes.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Exibir Requisição");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
