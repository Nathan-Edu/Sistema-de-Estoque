package com.example.stockmaster.Controles.Requisicoes;

import Aplicacoes.Modelos.Requisicao;
import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.RequisicaoServ;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListaRequisicaoController {

    @FXML
    private TableView<Requisicao> tabelaRequisicoes;

    @FXML
    private TableColumn<Requisicao, Integer> rcColumn;

    @FXML
    private TableColumn<Requisicao, String> solicitanteColumn;

    @FXML
    private TableColumn<Requisicao, String> materialColumn;

    @FXML
    private TableColumn<Requisicao, Double> quantidadeColumn;

    @FXML
    private TableColumn<Requisicao, String> dataColumn;

    @FXML
    private Button criarButton;

    @FXML
    private Button excluirButton;

    @FXML
    private Button voltaButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button exportarButton;

    @FXML
    private Label statusLabel;

    private RequisicaoServ requisicaoServ = new RequisicaoServ();
    private MaterialServ materialServ = new MaterialServ();
    private CSVExporter csvExporter = new CSVExporter();

    private ObservableList<Requisicao> requisicoesList;

    @FXML
    private void initialize() {
        rcColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        solicitanteColumn.setCellValueFactory(new PropertyValueFactory<>("solicitante"));
        materialColumn.setCellValueFactory(cellData -> {
            int codMaterial = cellData.getValue().getIdMaterial();
            Material material = materialServ.buscarProdutoPorId(codMaterial);
            if (material != null) {
                return new javafx.beans.property.SimpleStringProperty(material.getDescricaoCurta());
            } else {
                return new javafx.beans.property.SimpleStringProperty(String.valueOf(codMaterial));
            }
        });
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataColumn.setCellFactory(new Callback<TableColumn<Requisicao, String>, TableCell<Requisicao, String>>() {
            @Override
            public TableCell<Requisicao, String> call(TableColumn<Requisicao, String> param) {
                return new TableCell<Requisicao, String>() {
                    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            LocalDate localDate = LocalDate.parse(item);
                            setText(localDate.format(formatter));
                        }
                    }
                };
            }
        });

        requisicoesList = FXCollections.observableArrayList();
        tabelaRequisicoes.setItems(requisicoesList);

        carregarRequisicoes();
    }

    private void carregarRequisicoes() {
        requisicoesList.clear();
        requisicoesList.addAll(requisicaoServ.listarRequisicoes());
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Requisicoes/CriarRequisicoes.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Criar Requisição");
            stage.show();
        } catch (IOException e) {
            statusLabel.setText("Erro ao carregar a página de criação.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExcluirButtonAction(ActionEvent event) {
        Requisicao requisicaoSelecionada = tabelaRequisicoes.getSelectionModel().getSelectedItem();
        if (requisicaoSelecionada != null) {
            requisicaoServ.deletarRequisicao(requisicaoSelecionada.getId());
            carregarRequisicoes();
            statusLabel.setText("Requisição excluída com sucesso.");
        } else {
            statusLabel.setText("Nenhuma requisição selecionada.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltaButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        Requisicao requisicaoSelecionada = tabelaRequisicoes.getSelectionModel().getSelectedItem();
        if (requisicaoSelecionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Requisicoes/ModificarRequisicoes.fxml"));
                Parent root = loader.load();
                ModificarRequisicaoController modificarController = loader.getController();
                modificarController.setRequisicao(requisicaoSelecionada);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Modificar Requisição");
                stage.show();
            } catch (IOException e) {
                statusLabel.setText("Erro ao carregar a página de modificação.");
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Nenhuma requisição selecionada.");
        }
    }

    @FXML
    private void handleExportarButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo CSV");
        fileChooser.setInitialFileName("requisicoes.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(tabelaRequisicoes.getScene().getWindow());
        if (file != null) {
            String[] colunas = {"ID", "Solicitante", "Material", "Quantidade", "Data"};
            csvExporter.exportarParaCSV(
                    tabelaRequisicoes.getItems(),
                    file.getPath(),
                    colunas,
                    item -> String.format("%d,%s,%s,%.2f,%s",
                            item.getId(),
                            item.getSolicitante(),
                            materialServ.buscarProdutoPorId(item.getIdMaterial()).getDescricaoCurta(),
                            item.getQuantidade(),
                            item.getData())
            );
            statusLabel.setText("Requisições exportadas com sucesso.");
        }
    }
}
