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
import java.time.ZoneId;

public class ModificarEstoqueController {

    @FXML
    private TextField codigoEstoqueField;

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private Button exibirButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button criarButton;

    @FXML
    private Button listaButton;

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
                preencherCampos(estoque);
                statusLabel.setText("Dados exibidos com sucesso!");
            } else {
                statusLabel.setText("Estoque não encontrado.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Código de estoque deve ser numérico.");
        } catch (Exception e) {
            statusLabel.setText("Erro ao exibir estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        try {
            String codigoEstoqueStr = codigoEstoqueField.getText().trim();
            if (codigoEstoqueStr.isEmpty()) {
                throw new NumberFormatException("Código de estoque está vazio");
            }
            int codigoEstoque = Integer.parseInt(codigoEstoqueStr);

            String codigoMaterialStr = codigoMaterialField.getText().trim();
            Material material;
            int codMaterial;

            try {
                codMaterial = Integer.parseInt(codigoMaterialStr);
                material = materialServ.buscarProdutoPorId(codMaterial);
            } catch (NumberFormatException e) {
                material = materialServ.buscarProdutoPorNome(codigoMaterialStr);
                if (material != null) {
                    codMaterial = material.getId();
                } else {
                    throw new NumberFormatException("Código de material inválido");
                }
            }

            String quantidadeStr = quantidadeField.getText().trim();
            if (quantidadeStr.isEmpty()) {
                throw new NumberFormatException("Quantidade está vazia");
            }
            BigDecimal quantidade = new BigDecimal(quantidadeStr);

            String unidadeMedida = unidadeMedidaField.getText().trim();
            LocalDate localDate = dataPicker.getValue();

            if (unidadeMedida.isEmpty() || localDate == null) {
                statusLabel.setText("Preencha todos os campos corretamente!");
                return;
            }

            Date dataEntrada = Date.valueOf(localDate);

            Estoque estoqueAtualizado = new Estoque(codigoEstoque, codMaterial, quantidade, unidadeMedida, dataEntrada);
            estoqueServ.atualizarEstoque(estoqueAtualizado);

            statusLabel.setText("Estoque atualizado com sucesso!");

        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Verifique os campos numéricos e assegure-se de que todos os valores são válidos.");
        } catch (Exception e) {
            statusLabel.setText("Erro ao atualizar estoque: " + e.getMessage());
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
    private void handleListaButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/ListaEstoque.fxml", "Lista de Estoques");
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

    public void setEstoque(Estoque estoque) {
        estoqueAtual = estoque;
        preencherCampos(estoque);
    }

    private void preencherCampos(Estoque estoque) {
        codigoEstoqueField.setText(String.valueOf(estoque.getIdEstoque()));

        Material material = materialServ.buscarProdutoPorId(estoque.getCodMaterial());
        if (material != null) {
            codigoMaterialField.setText(material.getDescricaoCurta());
        } else {
            codigoMaterialField.setText(String.valueOf(estoque.getCodMaterial()));
        }

        quantidadeField.setText(estoque.getQuantidade().toString());
        unidadeMedidaField.setText(estoque.getUnidadeMedida());

        Date dataEntrada = (Date) estoque.getDataEntrada();
        LocalDate localDate = dataEntrada.toLocalDate();
        dataPicker.setValue(localDate);
    }
}
