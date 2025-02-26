package com.example.stockmaster.Controles.LoginUsuario;

import Aplicacoes.Modelos.Usuario;
import Aplicacoes.Servicos.UsuarioServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class CadastroUsuarioController {

    @FXML
    private TextField nomeUsuario;

    @FXML
    private TextField emailUsuario;

    @FXML
    private PasswordField senhaUsuarioCads;

    @FXML
    private PasswordField confirmarSenhaField;

    @FXML
    private Button registrarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label statusLabel;

    private UsuarioServ usuarioServ = new UsuarioServ();

    @FXML
    private void handleRegistrarButtonAction(ActionEvent event) {
        String nome = nomeUsuario.getText();
        String email = emailUsuario.getText();
        String senha = senhaUsuarioCads.getText();
        String confirmarSenha = confirmarSenhaField.getText();

        if (!validarEmail(email)) {
            statusLabel.setText("Formato de email inválido.");
            return;
        }

        if (senha.equals(confirmarSenha)) {
            Usuario novoUsuario = new Usuario(nome, email, senha);
            boolean registrado = usuarioServ.registrarUsuario(novoUsuario);

            if (registrado) {
                statusLabel.setText("Cadastro bem-sucedido!");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/LoginUsuario/Login.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) registrarButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    statusLabel.setText("Erro ao carregar a tela de login.");
                }
            } else {
                statusLabel.setText("Erro ao registrar usuário. Tente novamente.");
            }
        } else {
            statusLabel.setText("Senhas não coincidem. Tente novamente.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/LoginUsuario/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Erro ao carregar a tela de login.");
        }
    }

    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
