package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Servicos.MaterialServ;
import Aplicacoes.Modelos.Material;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class ExibirMaterialController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField descricaoCurtaField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private TextArea descricaoLongaField;

    @FXML
    private Button criarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label statusLabel;

    private MaterialServ cadastroProdutoServ = new MaterialServ();

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        String codigo = codigoMaterialField.getText();
        try {
            int id = Integer.parseInt(codigo);
            Material material = cadastroProdutoServ.buscarProdutoPorId(id);
            if (material != null) {
                descricaoCurtaField.setText(material.getDescricao_curta());
                descricaoCurtaField.setEditable(false);
                descricaoLongaField.setText(material.getDescricao_Longa());
                descricaoLongaField.setEditable(false);
                unidadeMedidaField.setText(material.getUnidade_Medida());
                unidadeMedidaField.setEditable(false);
                statusLabel.setText("Material encontrado!");
            } else {
                statusLabel.setText("Material não encontrado!");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Código inválido!");
        }
    }

    @FXML
    private void handleDeletarButtonAction(ActionEvent event) {
        String codigo = codigoMaterialField.getText();
        try {
            int id = Integer.parseInt(codigo);
            cadastroProdutoServ.deletarMaterial(id);
            statusLabel.setText("Material deletado com sucesso!");

            descricaoCurtaField.clear();
            descricaoLongaField.clear();
            unidadeMedidaField.clear();
        } catch (NumberFormatException e) {
            statusLabel.setText("Código inválido!");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/CriarMaterial.fxml", "Criar Material");
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/ModificarMaterial.fxml", "Modificar Material");
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
}
