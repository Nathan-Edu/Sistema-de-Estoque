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

public class CriarMaterialController {

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
    private Button listaButton;

    @FXML
    private Label statusLabel;

    private MaterialServ cadastroProdutoServ;

    public CriarMaterialController() {
        this.cadastroProdutoServ = new MaterialServ();
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String descricaoCurta = descricaoCurtaField.getText();
        String unidadeMedida = unidadeMedidaField.getText();
        String descricaoLonga = descricaoLongaField.getText();

        try {
            Material material = new Material(0, descricaoCurta, descricaoLonga, BigDecimal.ZERO, unidadeMedida, "");
            cadastroProdutoServ.cadastrarMaterial(material);
            statusLabel.setText("Material cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleListaButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/ListaMaterial.fxml", "Lista de Materiais");
    }

    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) listaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
