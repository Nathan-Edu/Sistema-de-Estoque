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
import java.text.SimpleDateFormat;

public class ExibirEstoqueController {

    @FXML
    private TextField codigoEstoqueField;

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private TextField dataField;

    @FXML
    private Button modificarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Button excluirButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button criarButton;

    @FXML
    private Label statusLabel;

    private EstoqueServ estoqueServ = new EstoqueServ();
    private MaterialServ materialServ = new MaterialServ();

    private Estoque estoqueAtual;

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        try {
            String codigoEstoqueStr = codigoEstoqueField.getText().trim();

            if (codigoEstoqueStr.isEmpty()) {
                statusLabel.setText("Informe um código de estoque válido!");
                return;
            }

            int codigoEstoque = Integer.parseInt(codigoEstoqueStr);
            Estoque estoque = estoqueServ.buscarEstoquePorId(codigoEstoque);

            if (estoque != null) {
                estoqueAtual = estoque;
                Material material = materialServ.buscarProdutoPorId(estoque.getCodMaterial());
                if (material != null) {
                    codigoMaterialField.setText(material.getDescricaoCurta());
                } else {
                    codigoMaterialField.setText(String.valueOf(estoque.getCodMaterial()));
                }
                quantidadeField.setText(String.valueOf(estoque.getQuantidade()));
                unidadeMedidaField.setText(estoque.getUnidadeMedida());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataField.setText(sdf.format(estoque.getDataEntrada()));
                statusLabel.setText("Dados exibidos.");
            } else {
                statusLabel.setText("Estoque não encontrado.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Código do Estoque deve ser numérico.");
        } catch (Exception e) {
            statusLabel.setText("Erro ao exibir estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExcluirButtonAction(ActionEvent event) {
        try {
            String codigoEstoqueStr = codigoEstoqueField.getText().trim();

            if (codigoEstoqueStr.isEmpty()) {
                statusLabel.setText("Informe um código de estoque válido!");
                return;
            }

            int codigoEstoque = Integer.parseInt(codigoEstoqueStr);
            estoqueServ.deletarEstoque(codigoEstoque);
            statusLabel.setText("Estoque excluído com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Código do Estoque deve ser numérico.");
        } catch (Exception e) {
            statusLabel.setText("Erro ao excluir estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/CriarEstoque.fxml", "Criar Estoque");
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Estoque/ModificarEstoque.fxml"));
            Parent root = loader.load();

            ModificarEstoqueController modificarController = loader.getController();
            modificarController.setEstoque(estoqueAtual);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar Estoque");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Erro ao abrir a tela de modificação: " + e.getMessage());
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
            statusLabel.setText("Erro ao abrir a tela: " + e.getMessage());
        }
    }


}
