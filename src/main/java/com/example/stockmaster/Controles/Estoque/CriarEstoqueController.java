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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class CriarEstoqueController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private TextField dataField;

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
        String dataStr = dataField.getText();

        if (materialStr.isEmpty() || quantidadeStr.isEmpty() || unidadeMedida.isEmpty() || dataStr.isEmpty()) {
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

            BigDecimal quantidade = new BigDecimal(quantidadeStr);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = sdf.parse(dataStr);
            Date dataEntrada = new Date(parsedDate.getTime());

            Estoque novoEstoque = new Estoque(0, material.getId_material(), quantidade, unidadeMedida, dataEntrada);
            estoqueServ.adicionaEstoque(novoEstoque);
            statusLabel.setText("Estoque adicionado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Quantidade deve ser um número válido.");
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
