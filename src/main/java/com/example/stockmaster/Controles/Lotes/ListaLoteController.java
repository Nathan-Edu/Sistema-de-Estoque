package com.example.stockmaster.Controles.Lotes;

import Aplicacoes.Modelos.Lote;
import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.LoteServ;
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
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaLoteController {

    @FXML
    private TableView<Lote> tabelaEstoque;

    @FXML
    private TableColumn<Lote, Integer> codigoLoteColumn;

    @FXML
    private TableColumn<Lote, String> materialColumn;

    @FXML
    private TableColumn<Lote, BigDecimal> quantidadeColumn;

    @FXML
    private TableColumn<Lote, Date> dataEntradaColumn;

    @FXML
    private TableColumn<Lote, String> statusColumn;

    @FXML
    private Button adicionarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Button criarButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button exportarButton;

    private LoteServ loteServ = new LoteServ();
    private MaterialServ materialServ = new MaterialServ();
    private CSVExporter csvExporter = new CSVExporter();

    @FXML
    private void initialize() {
        codigoLoteColumn.setCellValueFactory(new PropertyValueFactory<>("idLote"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("descricaoCurta"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dataEntradaColumn.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        dataEntradaColumn.setCellFactory(new Callback<TableColumn<Lote, Date>, TableCell<Lote, Date>>() {
            @Override
            public TableCell<Lote, Date> call(TableColumn<Lote, Date> param) {
                return new TableCell<Lote, Date>() {
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

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        carregarDados();
    }

    private void carregarDados() {
        List<Lote> lotes = loteServ.listarLotes();
        ObservableList<Lote> listaLotes = FXCollections.observableArrayList();

        for (Lote lote : lotes) {
            Material material = materialServ.buscarProdutoPorId(lote.getCodMaterial());
            if (material != null) {
                lote.setDescricaoCurta(material.getDescricao_curta());
                lote.setStatus(material.getStatus());
            }
            listaLotes.add(lote);
        }

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
            atualizarStatusLabel("Lote excluído com sucesso!");
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

    @FXML
    private void handleDeletarButtonAction(ActionEvent event) {
        Lote loteSelecionado = tabelaEstoque.getSelectionModel().getSelectedItem();
        if (loteSelecionado != null) {
            loteServ.deletarLote(loteSelecionado.getIdLote());
            carregarDados();
            atualizarStatusLabel("Lote excluído com sucesso!");
        }
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/CriarLote.fxml", "Criar Lote");
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

    @FXML
    private void handleExportarButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo CSV");
        fileChooser.setInitialFileName("lotes.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(tabelaEstoque.getScene().getWindow());
        if (file != null) {
            String[] colunas = {"ID", "Material", "Quantidade", "Data de Entrada", "Status"};
            csvExporter.exportarParaCSV(
                    tabelaEstoque.getItems(),
                    file.getPath(),
                    colunas,
                    item -> String.format("%d,%s,%s,%s,%s",
                            item.getIdLote(),
                            item.getDescricaoCurta(),
                            item.getQuantidade(),
                            new SimpleDateFormat("dd/MM/yyyy").format(item.getDataEntrada()),
                            item.getStatus())
            );
            statusLabel.setText("Lotes exportados com sucesso.");
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

    private void atualizarStatusLabel(String mensagem) {
        statusLabel.setText(mensagem);
    }
}
