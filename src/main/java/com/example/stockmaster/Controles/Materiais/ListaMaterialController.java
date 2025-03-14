package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Modelos.Material;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ListaMaterialController {

    @FXML
    private TableView<Material> tabelaMateriais;

    @FXML
    private TableColumn<Material, Integer> codigoMaterialColumn;

    @FXML
    private TableColumn<Material, String> descricaoCurtaColumn;

    @FXML
    private TableColumn<Material, String> unidadeMedidaColumn;

    @FXML
    private TableColumn<Material, String> descricaoLongaColumn;

    @FXML
    private TableColumn<Material, String> origemMaterialColumn;

    @FXML
    private TableColumn<Material, String> statusColumn;

    @FXML
    private Button criarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button exportarButton;

    @FXML
    private Label resultadoLabel;

    private MaterialServ materialServ = new MaterialServ();
    private CSVExporter csvExporter = new CSVExporter();

    @FXML
    private void initialize() {
        codigoMaterialColumn.setCellValueFactory(new PropertyValueFactory<>("id_material"));
        descricaoCurtaColumn.setCellValueFactory(new PropertyValueFactory<>("descricao_curta"));
        descricaoLongaColumn.setCellValueFactory(new PropertyValueFactory<>("descricao_longa"));
        unidadeMedidaColumn.setCellValueFactory(new PropertyValueFactory<>("unidade_medida"));
        origemMaterialColumn.setCellValueFactory(new PropertyValueFactory<>("origem_material"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        carregarDados();
    }

    private void carregarDados() {
        List<Material> materiais = materialServ.listarProdutos();
        ObservableList<Material> listaMateriais = FXCollections.observableArrayList(materiais);
        tabelaMateriais.setItems(listaMateriais);
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/CriarMaterial.fxml", "Adicionar Material");
    }

    @FXML
    private void handleDeletarButtonAction(ActionEvent event) {
        Material materialSelecionado = tabelaMateriais.getSelectionModel().getSelectedItem();
        if (materialSelecionado != null) {
            materialServ.deletarMaterial(materialSelecionado.getId_material());
            carregarDados();
            resultadoLabel.setText("Material excluído com sucesso!");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        Material materialSelecionado = tabelaMateriais.getSelectionModel().getSelectedItem();
        if (materialSelecionado != null) {
            carregarTelaComMaterialSelecionado("/com/example/stockmaster/Materiais/ModificarMaterial.fxml", "Modificar Material", materialSelecionado);
        }
    }

    @FXML
    private void handleExportarButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo CSV");
        fileChooser.setInitialFileName("materiais.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(tabelaMateriais.getScene().getWindow());
        if (file != null) {
            String[] colunas = {"ID", "Descrição Curta", "Unidade", "Descrição Longa", "Origem", "Status"};
            csvExporter.exportarParaCSV(
                    tabelaMateriais.getItems(),
                    file.getPath(),
                    colunas,
                    item -> String.format("%d,%s,%s,%s,%s,%s",
                            item.getId_material(),
                            item.getDescricao_curta(),
                            item.getUnidade_medida(),
                            item.getDescricao_longa(),
                            item.getOrigem_material(),
                            item.getStatus())
            );
            resultadoLabel.setText("Dados exportados com sucesso!");
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

    private void carregarTelaComMaterialSelecionado(String caminhoFXML, String titulo, Material material) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            ModificarMaterialController controlador = loader.getController();
            controlador.setMaterial(material);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
