package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.MaterialServ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class CriarMaterialController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField descricaoCurtaField;

    @FXML
    private TextArea descricaoLongaField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private ComboBox<String> origemMaterialComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button listaButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Label resultadoLabel;

    private MaterialServ materialServ;

    public CriarMaterialController() {
        this.materialServ = new MaterialServ();
    }

    @FXML
    public void initialize() {
        ObservableList<String> origemOptions = FXCollections.observableArrayList("Produzido Internamente", "Comprado Externamente");
        origemMaterialComboBox.setItems(origemOptions);

        ObservableList<String> statusOptions = FXCollections.observableArrayList("Disponível", "Indisponível", "Em Produção");
        statusComboBox.setItems(statusOptions);

        resultadoLabel.setVisible(false); // Tornar o resultadoLabel invisível por padrão
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        try {
            String descricaoCurta = descricaoCurtaField.getText();
            String descricaoLonga = descricaoLongaField.getText();
            BigDecimal quantidade = BigDecimal.ZERO;
            String unidadeMedida = unidadeMedidaField.getText();
            String deposito = "N/A";
            String origemMaterial = origemMaterialComboBox.getValue();
            String status = statusComboBox.getValue();

            Material material = new Material(descricaoCurta, descricaoLonga, quantidade, unidadeMedida, deposito, origemMaterial, status);
            materialServ.cadastrarMaterial(material);

            resultadoLabel.setText("Material cadastrado com sucesso!");
            resultadoLabel.setVisible(true);
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Erro: código inválido.");
            resultadoLabel.setVisible(true);
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
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            resultadoLabel.setText("Erro ao carregar a tela: " + titulo);
            resultadoLabel.setVisible(true);
        }
    }
}
