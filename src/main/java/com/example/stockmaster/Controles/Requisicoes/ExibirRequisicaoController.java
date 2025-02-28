package com.example.stockmaster.Controles.Requisicoes;

import Aplicacoes.Modelos.Requisicao;
import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.RequisicaoServ;
import Aplicacoes.Servicos.MaterialServ;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExibirRequisicaoController {

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
    private Button deletarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Button criarButton;

    @FXML
    private Label statusLabel;

    private RequisicaoServ requisicaoServ = new RequisicaoServ();
    private MaterialServ materialServ = new MaterialServ();

    @FXML
    private void handleDeletarButtonAction(ActionEvent event) {
        String numeroRc = numeroRcField.getText();
        try {
            int id = Integer.parseInt(numeroRc);
            requisicaoServ.deletarRequisicao(id);
            statusLabel.setText("Requisição deletada com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro ao deletar a requisição: código inválido.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        String numeroRc = numeroRcField.getText();
        try {
            int id = Integer.parseInt(numeroRc);
            Requisicao requisicao = requisicaoServ.buscarRequisicaoPorId(id);
            if (requisicao != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Requisicoes/ModificarRequisicoes.fxml"));
                loader.setLocation(getClass().getResource("/com/example/stockmaster/Requisicoes/ModificarRequisicoes.fxml"));
                Parent root = loader.load();

                ModificarRequisicaoController modificarController = loader.getController();
                modificarController.setRequisicao(requisicao);

                Stage stage = new Stage();
                stage.setTitle("Modificar Requisição");
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                statusLabel.setText("Requisição não encontrada!");
            }
        } catch (IOException e) {
            statusLabel.setText("Erro ao carregar a página de modificação.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: código da requisição inválido.");
        }
    }

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        String numeroRc = numeroRcField.getText();
        try {
            int id = Integer.parseInt(numeroRc);
            Requisicao requisicao = requisicaoServ.buscarRequisicaoPorId(id);
            if (requisicao != null) {
                solicitanteField.setText(requisicao.getSolicitante());
                Material material = materialServ.buscarProdutoPorId(requisicao.getIdMaterial());
                if (material != null) {
                    materialField.setText(material.getDescricaoCurta());
                } else {
                    materialField.setText(String.valueOf(requisicao.getIdMaterial()));
                }
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

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/CriarRequisicoes.fxml", "Criar Requisição");
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
            statusLabel.setText("Erro ao carregar a página de criação.");
            e.printStackTrace();
        }
    }
}
