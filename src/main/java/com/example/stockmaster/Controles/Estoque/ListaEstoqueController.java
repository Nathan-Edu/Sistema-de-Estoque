package com.example.stockmaster.Controles.Estoque;

import Aplicacoes.Modelos.Estoque;
import Aplicacoes.Servicos.EstoqueServ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListaEstoqueController {

    @FXML
    private TableView<Estoque> tabelaEstoque;

    @FXML
    private TableColumn<Estoque, Integer> codigoEstoqueColumn;

    @FXML
    private TableColumn<Estoque, Integer> materialColumn;

    @FXML
    private TableColumn<Estoque, Integer> quantidadeColumn;

    @FXML
    private TableColumn<Estoque, String> dataEntradaColumn;

    @FXML
    private Button adicionarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    private EstoqueServ estoqueServ = new EstoqueServ();

    @FXML
    private void initialize() {
        codigoEstoqueColumn.setCellValueFactory(new PropertyValueFactory<>("idEstoque"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("codMaterial"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dataEntradaColumn.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));

        carregarDados();
    }

    private void carregarDados() {
        List<Estoque> estoques = estoqueServ.listarEstoques();
        ObservableList<Estoque> listaEstoques = FXCollections.observableArrayList(estoques);
        tabelaEstoque.setItems(listaEstoques);
    }

    @FXML
    private void handleAdicionarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/CriarEstoque.fxml", "Adicionar Estoque");
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        Estoque estoqueSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (estoqueSelecionado != null) {
            estoqueServ.deletarEstoque(estoqueSelecionado.getIdEstoque());
            carregarDados();
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        Estoque estoqueSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (estoqueSelecionado != null) {
            carregarTelaComEstoqueSelecionado("/com/example/stockmaster/Estoque/ModificarEstoque.fxml", "Modificar Estoque", estoqueSelecionado);
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

    private void carregarTelaComEstoqueSelecionado(String caminhoFXML, String titulo, Estoque estoque) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            ModificarEstoqueController controlador = loader.getController();
            controlador.setEstoque(estoque);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
