package com.example.stockmaster.Controles.Estoque;

import Aplicacoes.Modelos.Estoque;
import Aplicacoes.Servicos.EstoqueServ;
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
import java.text.SimpleDateFormat;

public class ExibirEstoqueController {

    @FXML
    private TextField codigoLoteField;

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
    private Label resultadoLabel;

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
                dataField.setText(sdf.format(estoque.getDataEntrada()));
                resultadoLabel.setText("Dados exibidos.");
            } else {
                resultadoLabel.setText("Estoque não encontrado.");
            }
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Código do Estoque deve ser um número válido.");
        }
    }

    @FXML
    private void handleExcluirButtonAction(ActionEvent event) {
        try {
            int codigoLote = Integer.parseInt(codigoLoteField.getText());
            estoqueServ.deletarEstoque(codigoLote);
            resultadoLabel.setText("Estoque excluído com sucesso!");
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Código do Estoque deve ser um número válido.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/CriarEstoque.fxml"," Criar Estoque");
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
            carregarTela("/com/example/stockmaster/Estoque/ModificarEstoque.fxml"," Modificar Estoque");
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
