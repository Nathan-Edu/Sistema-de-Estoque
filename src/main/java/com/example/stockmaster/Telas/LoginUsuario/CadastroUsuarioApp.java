package com.example.stockmaster.Telas.LoginUsuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroUsuarioApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/LoginUsuario/CadastroUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 575, 300);
        stage.setTitle("Cadastro de Usuário - Sistema de Estoque");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
