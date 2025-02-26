package com.example.stockmaster.Controles.Estoque;

import Aplicacoes.Modelos.Estoque;
import Aplicacoes.Servicos.EstoqueServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class ModificarEstoqueController {

    @FXML
    private TextField codigoLoteField;

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private TextField dataLote;

    @FXML
    private Button exibirButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button criarButton;

    @FXML
    private Label statusLabel;

    private EstoqueServ estoqueServ = new EstoqueServ();

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        try {
            int codigoLote = Integer.parseInt(codigoLoteField.getText());
            Estoque estoque = estoqueServ.buscarEstoquePorId(codigoLote);

            if (estoque != null) {
                codigoMaterialField.setText(String.valueOf(estoque.getCodMaterial()));
                quantidadeField.setText(String.valueOf(estoque.getQuantidade()));
                unidadeMedidaField.setText(estoque.getUnidadeMedida());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataLote.setText(sdf.format(estoque.getDataEntrada()));
                statusLabel.setText("Dados exibidos.");
            } else {
                statusLabel.setText("Estoque não encontrado.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Código do Estoque deve ser um número válido.");
        }
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        try {
            int codigoLote = Integer.parseInt(codigoLoteField.getText());
            int codMaterial = Integer.parseInt(codigoMaterialField.getText());
            BigDecimal quantidade = new BigDecimal(quantidadeField.getText());
            String unidadeMedida = unidadeMedidaField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = sdf.parse(dataLote.getText());
            Date dataEntrada = new Date(parsedDate.getTime());

            Estoque estoqueAtualizado = new Estoque(codigoLote, codMaterial, quantidade, unidadeMedida, dataEntrada);
            estoqueServ.atualizarEstoque(estoqueAtualizado);
            statusLabel.setText("Estoque atualizado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Código do Estoque e quantidade devem ser números válidos.");
        } catch (ParseException e) {
            statusLabel.setText("Data deve estar no formato dd/MM/yyyy.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {

    }

    public void setEstoque(Estoque estoque) {
    }
}
