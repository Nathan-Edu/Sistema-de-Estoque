package com.example.stockmaster.Controles.Materiais;

import Aplicacoes.Servicos.MaterialServ;
import Aplicacoes.Modelos.Material;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ExibirMaterialController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField descricaoCurtaField;

    @FXML
    private TextField unidadeMedidaField;

    @FXML
    private TextArea descricaoLongaField;

    @FXML
    private TextField origemMaterialField;

    @FXML
    private TextField statusField;

    @FXML
    private Button criarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label resultadoLabel;

    private MaterialServ materialServ = new MaterialServ();

    @FXML
    private void handleExibirButtonAction(ActionEvent event) {
        try {
            String input = codigoMaterialField.getText();
            Material material = null;

            // Tentar buscar por ID
            try {
                int id = Integer.parseInt(input);
                material = materialServ.buscarProdutoPorId(id);
            } catch (NumberFormatException e) {
                // Se não for um ID válido, buscar por nome
                material = materialServ.buscarProdutoPorNome(input);
            }

            if (material != null) {
                setMaterial(material);
                resultadoLabel.setText("Material carregado com sucesso!");
            } else {
                resultadoLabel.setText("Material não encontrado.");
            }
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Erro: entrada inválida.");
        }
        resultadoLabel.setVisible(true);
    }

    @FXML
    private void handleDeletarButtonAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(codigoMaterialField.getText());
            materialServ.deletarMaterial(id);
            resultadoLabel.setText("Material deletado com sucesso!");
            limparCampos();
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Erro: entrada inválida.");
        }
        resultadoLabel.setVisible(true);
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/CriarMaterial.fxml", "Criar Material");
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        String codigo = codigoMaterialField.getText();
        try {
            int id = Integer.parseInt(codigo);
            Material materialSelecionado = materialServ.buscarProdutoPorId(id);
            if (materialSelecionado != null) {
                carregarTelaComMaterialSelecionado("/com/example/stockmaster/Materiais/ModificarMaterial.fxml", "Modificar Material", materialSelecionado);
            } else {
                resultadoLabel.setText("Material não encontrado!");
            }
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Código inválido!");
        }
        resultadoLabel.setVisible(true);
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

    private void setMaterial(Material material) {
        codigoMaterialField.setText(String.valueOf(material.getId_material()));
        descricaoCurtaField.setText(material.getDescricao_curta());
        descricaoCurtaField.setEditable(false);
        descricaoLongaField.setText(material.getDescricao_longa());
        descricaoLongaField.setEditable(false);
        unidadeMedidaField.setText(material.getUnidade_medida());
        unidadeMedidaField.setEditable(false);
        origemMaterialField.setText(material.getOrigem_material());
        origemMaterialField.setEditable(false);
        statusField.setText(material.getStatus());
        statusField.setEditable(false);
    }

    private void limparCampos() {
        codigoMaterialField.clear();
        descricaoCurtaField.clear();
        descricaoLongaField.clear();
        unidadeMedidaField.clear();
        origemMaterialField.clear();
        statusField.clear();
    }
}
