package com.example.stockmaster.Controles.Lotes;

import Aplicacoes.Modelos.Lote;
import Aplicacoes.Servicos.LoteServ;
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
import java.math.BigDecimal;
import java.util.List;

public class ListaLoteController {

    @FXML
    private TableView<Lote> tabelaEstoque;

    @FXML
    private TableColumn<Lote, Integer> codigoLoteColumn;

    @FXML
    private TableColumn<Lote, Integer> materialColumn;

    @FXML
    private TableColumn<Lote, BigDecimal> quantidadeColumn;

    @FXML
    private TableColumn<Lote, String> dataEntradaColumn;

    @FXML
    private Button adicionarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    private LoteServ loteServ = new LoteServ();

    @FXML
    private void initialize() {
        codigoLoteColumn.setCellValueFactory(new PropertyValueFactory<>("idLote"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("codMaterial"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dataEntradaColumn.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));

        carregarDados();
    }

    private void carregarDados() {
        List<Lote> lotes = loteServ.listarLotes();
        ObservableList<Lote> listaLotes = FXCollections.observableArrayList(lotes);
        tabelaEstoque.setItems(listaLotes);
    }

    @FXML
    private void handleAdicionarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/CriarLote.fxml", "Adicionar Lote");
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        Lote loteSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (loteSelecionado != null) {
            loteServ.deletarLote(loteSelecionado.getIdLote());
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
        Lote loteSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (loteSelecionado != null) {
            carregarTelaComLoteSelecionado("/com/example/stockmaster/Lotes/ModificarLote.fxml", "Modificar Lote", loteSelecionado);
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

    private void carregarTelaComLoteSelecionado(String caminhoFXML, String titulo, Lote lote) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            ModificarLoteController controlador = loader.getController();
            controlador.setLote(lote);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
