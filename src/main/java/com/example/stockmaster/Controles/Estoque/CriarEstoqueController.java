package com.example.stockmaster.Controles.Estoque;

import Aplicacoes.Modelos.Estoque;
import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.EstoqueServ;
import Aplicacoes.Servicos.MaterialServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class CriarEstoqueController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextField estoqueField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button listaButton;

    @FXML
    private Label statusLabel;

    private EstoqueServ estoqueServ = new EstoqueServ();
    private MaterialServ materialServ = new MaterialServ();

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String materialStr = codigoMaterialField.getText();
        String quantidadeStr = quantidadeField.getText();
        String unidadeMedida = unidadeMedidaField.getText();
        LocalDate data = dataPicker.getValue();
        String idEstoqueStr = estoqueField.getText();

        if (materialStr.isEmpty() || quantidadeStr.isEmpty() || unidadeMedida.isEmpty() || data == null || idEstoqueStr.isEmpty()) {
            statusLabel.setText("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Material material = null;
            try {
                int codMaterial = Integer.parseInt(materialStr);
                material = materialServ.buscarProdutoPorId(codMaterial);
            } catch (NumberFormatException e) {
                material = materialServ.buscarProdutoPorNome(materialStr);
            }

            if (material == null) {
                statusLabel.setText("Material não encontrado.");
                return;
            }

            int idEstoque = Integer.parseInt(idEstoqueStr);
            BigDecimal quantidade = new BigDecimal(quantidadeStr);
            Date dataEntrada = Date.valueOf(data);

            Estoque novoEstoque = new Estoque(idEstoque, material.getId_material(), quantidade, unidadeMedida, dataEntrada);
            estoqueServ.adicionaEstoque(novoEstoque);
            statusLabel.setText("Estoque adicionado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Quantidade e ID do Estoque devem ser números válidos.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleListaButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Estoque/ListaEstoque.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) listaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Erro ao carregar a lista de estoques.");
        }
    }
}
