package com.example.stockmaster.Controles.LoginUsuario;

import Aplicacoes.Modelos.Usuario;
import Aplicacoes.Servicos.UsuarioServ;
import com.example.stockmaster.Controles.Principal.PagInicialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField nomeUsuario;

    @FXML
    private PasswordField senhaUsuario;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink cadastreSeLink;

    @FXML
    private Hyperlink esqueceuSenhaLink;

    @FXML
    private Label statusLabel;

    private UsuarioServ usuarioServ = new UsuarioServ();

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String nome = nomeUsuario.getText();
        String senha = senhaUsuario.getText();

        if (nome.isEmpty() || senha.isEmpty()) {
            statusLabel.setText("Por favor, preencha todos os campos.");
            return;
        }

        Usuario usuario = usuarioServ.buscarTodosUsuarios().stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst()
                .orElse(null);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            statusLabel.setText("Login bem-sucedido!");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Principal/PagInicial.fxml"));
                Parent root = loader.load();

                PagInicialController pagInicialController = loader.getController();
                pagInicialController.setUsuarioLogado(usuario);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Erro ao carregar a tela inicial.");
            }
        } else {
            statusLabel.setText("Credenciais inválidas. Tente novamente.");
        }
    }

    @FXML
    private void handleCadastreSeLinkAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/LoginUsuario/CadastroUsuario.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) cadastreSeLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Erro ao carregar a tela de cadastro.");
        }
    }

    @FXML
    private void handleEsqueceuSenhaLinkAction(ActionEvent event) {
        statusLabel.setText("Funcionalidade ainda não implementada.");
    }
}
