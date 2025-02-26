package com.example.stockmaster.Controles.Principal;

import Aplicacoes.Modelos.Usuario;
import Aplicacoes.Servicos.UsuarioServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfiguracoesController {

    @FXML
    private TextField nomeUsuarioField;

    @FXML
    private TextField emailUsuarioField;

    @FXML
    private TextField senhaUsuarioField;

    @FXML
    private TextField confirmarSenhaField;

    @FXML
    private Button voltarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Label statusLabel;

    private UsuarioServ usuarioServ = new UsuarioServ();
    private Usuario usuarioLogado;

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
        nomeUsuarioField.setText(usuario.getNome());
        emailUsuarioField.setText(usuario.getEmail());
        senhaUsuarioField.setText(usuario.getSenha());
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String nome = nomeUsuarioField.getText();
        String email = emailUsuarioField.getText();
        String senha = senhaUsuarioField.getText();
        String confirmarSenha = confirmarSenhaField.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            statusLabel.setText("Por favor, preencha todos os campos.");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            statusLabel.setText("As senhas não coincidem.");
            return;
        }

        Usuario usuarioExistente = usuarioServ.buscarUsuarioPorEmail(email);
        if (usuarioExistente != null && usuarioExistente.getId_usuario() != usuarioLogado.getId_usuario()) {
            statusLabel.setText("Este email já está sendo usado por outro usuário.");
            return;
        }

        usuarioLogado.setNome(nome);
        usuarioLogado.setEmail(email);
        usuarioLogado.setSenha(senha);

        usuarioServ.atualizarUsuario(usuarioLogado);
        statusLabel.setText("Informações atualizadas com sucesso!");
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }
}
