package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Modelos.Material;
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
import java.math.BigDecimal;

public class ModificarMaterialController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField descricaoCurtaField;

    @FXML
    private TextField descricaoLongaField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label statusLabel;

    private MaterialServ materialServ;

    public ModificarMaterialController() {
        this.materialServ = new MaterialServ();
    }

    @FXML
    private void handleSalvarButtonAction() {
        try {
            int id = Integer.parseInt(codigoMaterialField.getText());
            String descricaoCurta = descricaoCurtaField.getText();
            String descricaoLonga = descricaoLongaField.getText();
            BigDecimal quantidade = BigDecimal.ZERO; // Definindo uma quantidade padrão, se necessário
            String unidadeMedida = unidadeMedidaField.getText();
            String deposito = "N/A"; // Definindo um valor padrão para depósito

            Material material = new Material(id, descricaoCurta, descricaoLonga, quantidade, unidadeMedida, deposito);
            materialServ.atualizarMaterial(material);
            statusLabel.setText("Material atualizado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: código inválido.");
        }
    }

    public void setMaterial(Material material) {
        codigoMaterialField.setText(String.valueOf(material.getId_material()));
        descricaoCurtaField.setText(material.getDescricao_curta());
        descricaoLongaField.setText(material.getDescricao_longa());
        unidadeMedidaField.setText(material.getUnidade_medida());
        // Não é necessário configurar o campo depósito
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/CriarMaterial.fxml", "Criar Material");
    }

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        Material material = materialServ.buscarProdutoPorId(Integer.parseInt(codigoMaterialField.getText()));
        if (material != null) {
            setMaterial(material);
        } else {
            statusLabel.setText("Material não encontrado.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
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
