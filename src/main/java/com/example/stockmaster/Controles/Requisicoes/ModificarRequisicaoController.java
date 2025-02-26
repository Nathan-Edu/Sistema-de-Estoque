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

public class ModificarRequisicaoController {

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
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button criarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Label statusLabel;

    private RequisicaoServ requisicaoServ = new RequisicaoServ();

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

            int idUsuarioValido = 1;
            Requisicao requisicao = new Requisicao(
                    Integer.parseInt(numeroRc),
                    idUsuarioValido,
                    solicitante,
                    Integer.parseInt(material),
                    Double.parseDouble(quantidade),
                    sqlDate.toString()
            );
            requisicaoServ.atualizarRequisicao(requisicao);
            statusLabel.setText("Requisição modificada com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro ao modificar a requisição: dados inválidos.");
        } catch (DateTimeParseException e) {
            statusLabel.setText("Erro ao modificar a requisição: formato de data inválido.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/CriarRequisicoes.fxml", "Criar Requisição");
    }

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        String numeroRc = numeroRcField.getText();
        try {
            int id = Integer.parseInt(numeroRc);
            Requisicao requisicao = requisicaoServ.buscarRequisicaoPorId(id);
            if (requisicao != null) {
                solicitanteField.setText(requisicao.getSolicitante());
                materialField.setText(String.valueOf(requisicao.getIdMaterial()));
                quantidadeField.setText(String.valueOf(requisicao.getQuantidade()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(requisicao.getData());
                String formattedDate = localDate.format(formatter);
                dataField.setText(formattedDate);
                statusLabel.setText("Requisição encontrada!");
            } else {
                statusLabel.setText("Requisição não encontrada!");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: código da requisição inválido.");
        }
    }

    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRequisicao(Requisicao requisicao) {
        numeroRcField.setText(String.valueOf(requisicao.getId()));
        solicitanteField.setText(requisicao.getSolicitante());
        materialField.setText(String.valueOf(requisicao.getIdMaterial()));
        quantidadeField.setText(String.valueOf(requisicao.getQuantidade()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(requisicao.getData());
        String formattedDate = localDate.format(formatter);
        dataField.setText(formattedDate);
    }
}
