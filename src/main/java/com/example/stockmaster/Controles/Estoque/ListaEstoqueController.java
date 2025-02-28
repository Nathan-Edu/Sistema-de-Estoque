package com.example.stockmaster.Controles.Estoque;

import Aplicacoes.Modelos.Estoque;
import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.EstoqueServ;
import Aplicacoes.Servicos.MaterialServ;
import Aplicacoes.Exportacao.CSVExporter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaEstoqueController {

    @FXML
    private TableView<Estoque> tabelaEstoque;

    @FXML
    private TableColumn<Estoque, Integer> codigoEstoqueColumn;

    @FXML
    private TableColumn<Estoque, String> materialColumn;

    @FXML
    private TableColumn<Estoque, Integer> quantidadeColumn;

    @FXML
    private TableColumn<Estoque, Date> dataEntradaColumn;

    @FXML
    private Button adicionarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button exportarButton;

    private EstoqueServ estoqueServ = new EstoqueServ();
    private MaterialServ materialServ = new MaterialServ();
    private CSVExporter csvExporter = new CSVExporter();

    @FXML
    private void initialize() {
        codigoEstoqueColumn.setCellValueFactory(new PropertyValueFactory<>("idEstoque"));
        materialColumn.setCellValueFactory(cellData -> {
            int codMaterial = cellData.getValue().getCodMaterial();
            Material material = materialServ.buscarProdutoPorId(codMaterial);
            if (material != null) {
                return new javafx.beans.property.SimpleStringProperty(material.getDescricaoCurta());
            } else {
                return new javafx.beans.property.SimpleStringProperty(String.valueOf(codMaterial));
            }
        });
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dataEntradaColumn.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        dataEntradaColumn.setCellFactory(new Callback<TableColumn<Estoque, Date>, TableCell<Estoque, Date>>() {
            @Override
            public TableCell<Estoque, Date> call(TableColumn<Estoque, Date> param) {
                return new TableCell<Estoque, Date>() {
                    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(sdf.format(item));
                        }
                    }
                };
            }
        });

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
        atualizarStatusLabel("Adicionar Estoque selecionado.");
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        Estoque estoqueSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (estoqueSelecionado != null) {
            estoqueServ.deletarEstoque(estoqueSelecionado.getIdEstoque());
            carregarDados();
            atualizarStatusLabel("Estoque excluído com sucesso!");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
        atualizarStatusLabel("Voltando para a tela anterior.");
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        Estoque estoqueSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (estoqueSelecionado != null) {
            carregarTelaComEstoqueSelecionado("/com/example/stockmaster/Estoque/ModificarEstoque.fxml", "Modificar Estoque", estoqueSelecionado);
            atualizarStatusLabel("Modificar Estoque selecionado.");
        }
    }

    @FXML
    private void handleExportarButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo CSV");
        fileChooser.setInitialFileName("estoque.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(tabelaEstoque.getScene().getWindow());
        if (file != null) {
            String[] colunas = {"ID", "Material", "Quantidade", "Data de Entrada"};
            csvExporter.exportarParaCSV(
                    tabelaEstoque.getItems(),
                    file.getPath(),
                    colunas,
                    item -> {
                        String materialDescricao = materialServ.buscarProdutoPorId(item.getCodMaterial()).getDescricaoCurta();
                        String quantidade = String.format("%.2f", item.getQuantidade().doubleValue());
                        String dataEntrada = new SimpleDateFormat("dd/MM/yyyy").format(item.getDataEntrada());
                        return String.format("%d,%s,%s,%s",
                                item.getIdEstoque(),
                                materialDescricao,
                                quantidade,
                                dataEntrada);
                    }
            );
            statusLabel.setText("Estoque exportado com sucesso.");
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
            atualizarStatusLabel("Erro ao carregar a tela: " + titulo);
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
            atualizarStatusLabel("Erro ao carregar a tela de modificação de estoque.");
        }
    }

    private void atualizarStatusLabel(String mensagem) {
        statusLabel.setText(mensagem);
    }
}
