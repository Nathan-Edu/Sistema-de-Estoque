package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.MaterialServ;
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
    private Button criarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button modificarButton;

    private MaterialServ materialServ = new MaterialServ();

    @FXML
    private void initialize() {
        codigoMaterialColumn.setCellValueFactory(new PropertyValueFactory<>("id_material"));
        descricaoCurtaColumn.setCellValueFactory(new PropertyValueFactory<>("descricao_curta"));
        descricaoLongaColumn.setCellValueFactory(new PropertyValueFactory<>("descricao_Longa"));
        unidadeMedidaColumn.setCellValueFactory(new PropertyValueFactory<>("unidade_Medida"));

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
