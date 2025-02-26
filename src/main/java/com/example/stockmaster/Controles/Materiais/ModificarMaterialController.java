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
import javafx.scene.control.TextArea;
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
    private TextField unidadeMedidaField;

    @FXML
    private TextArea descricaoLongaField;

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

    private MaterialServ materialServ = new MaterialServ();

    @FXML
    private void handleSalvarButtonAction() {
        String codigoMaterial = codigoMaterialField.getText();
        String descricaoCurta = descricaoCurtaField.getText();
        String unidadeMedida = unidadeMedidaField.getText();
        String descricaoLonga = descricaoLongaField.getText();

        try {
            int id = Integer.parseInt(codigoMaterial);
            Material material = new Material(id, descricaoCurta, descricaoLonga, BigDecimal.ZERO, unidadeMedida, "");
            materialServ.atualizarMaterial(material);
            statusLabel.setText("Material modificado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro ao modificar o material: código inválido.");
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
    private void handleExibirButtonAction() {
        String codigoMaterial = codigoMaterialField.getText();
        try {
            int id = Integer.parseInt(codigoMaterial);
            Material material = materialServ.buscarProdutoPorId(id);
            if (material != null) {
                descricaoCurtaField.setText(material.getDescricao_curta());
                unidadeMedidaField.setText(material.getUnidade_Medida());
                descricaoLongaField.setText(material.getDescricao_Longa());
                statusLabel.setText("Material encontrado!");
            } else {
                statusLabel.setText("Material não encontrado!");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: código do material inválido.");
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

    public void setMaterial(Material material) {
        codigoMaterialField.setText(String.valueOf(material.getId_material()));
        descricaoCurtaField.setText(material.getDescricao_curta());
        unidadeMedidaField.setText(material.getUnidade_Medida());
        descricaoLongaField.setText(material.getDescricao_Longa());
    }
}
