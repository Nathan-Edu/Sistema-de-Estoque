package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.MaterialServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class ModificarMaterialController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField descricaoCurtaField;

    @FXML
    private TextField descricaoLongaField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private TextField depositoField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label statusLabel;

    private MaterialServ materialServ = new MaterialServ();

    @FXML
    private void handleSalvarButtonAction() {
        try {
            int id = Integer.parseInt(codigoMaterialField.getText());
            String descricaoCurta = descricaoCurtaField.getText();
            String descricaoLonga = descricaoLongaField.getText();
            BigDecimal quantidade = new BigDecimal(quantidadeField.getText());
            String unidadeMedida = unidadeMedidaField.getText();
            String deposito = depositoField.getText();

            Material material = new Material(id, descricaoCurta, descricaoLonga, quantidade, unidadeMedida, deposito);
            materialServ.atualizarMaterial(material);
            statusLabel.setText("Material atualizado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: quantidade inválida.");
        }
    }

    public void setMaterial(Material material) {
        codigoMaterialField.setText(String.valueOf(material.getId_material()));
        descricaoCurtaField.setText(material.getDescricao_curta());
        descricaoLongaField.setText(material.getDescricao_longa());
        quantidadeField.setText(material.getQuantidade().toString());
        unidadeMedidaField.setText(material.getUnidade_medida());
        depositoField.setText(material.getDeposito());
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }
}
