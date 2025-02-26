package com.example.stockmaster.Controles.Requisicoes;

import Aplicacoes.Modelos.Requisicao;
import Aplicacoes.Servicos.RequisicaoServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CriarRequisicaoController {

    @FXML
    private TextField numeroRcField;

    @FXML
    private TextField solicitanteField;

    @FXML
    private TextField materialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField dataField;

    @FXML
    private Button listaButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label statusLabel;

    private RequisicaoServ requisicaoServ = new RequisicaoServ();

    @FXML
    private void handleCriarRequisicoesButtonAction() {
        carregarTela("/com/example/stockmaster/Requisicoes/CriarRequisicoes.fxml", "Criar Requisição");
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String numeroRc = numeroRcField.getText();
        String solicitante = solicitanteField.getText();
        String material = materialField.getText();
        String quantidade = quantidadeField.getText();
        String data = dataField.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate localDate = LocalDate.parse(data, formatter);
            Date sqlDate = Date.valueOf(localDate);

            int idUsuarioValido = 1; // Substitua por um id_usuario válido na sua tabela usuários

            Requisicao requisicao = new Requisicao(
                    Integer.parseInt(numeroRc),
                    idUsuarioValido, // ID do usuário válido
                    solicitante,
                    Integer.parseInt(material),
                    Double.parseDouble(quantidade),
                    sqlDate.toString()
            );
            requisicaoServ.adicionaRequisicao(requisicao);
            statusLabel.setText("Requisição salva com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro ao salvar a requisição: dados inválidos.");
        } catch (DateTimeParseException e) {
            statusLabel.setText("Erro ao salvar a requisição: formato de data inválido.");
        }
    }

    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListaButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/ListaRequisicoes.fxml", "Lista de Requisições");
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }
}
