package com.example.stockmaster.Controles.Lotes;

import Aplicacoes.Modelos.Lote;
import Aplicacoes.Servicos.LoteServ;
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

public class ExibirLoteController {

    @FXML
    private TextField codigoLoteField;

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField dataField;

    @FXML
    private TextField tipoAcaoField;

    @FXML
    private Button criarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Button excluirButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label resultadoLabel;

    private LoteServ loteServ = new LoteServ();

    @FXML
    private void handleExibirButtonAction() {
        String codigoLote = codigoLoteField.getText();
        try {
            int id = Integer.parseInt(codigoLote);
            Lote lote = loteServ.buscarLotePorId(id);
            if (lote != null) {
                codigoMaterialField.setText(String.valueOf(lote.getCodMaterial()));
                quantidadeField.setText(String.valueOf(lote.getQuantidade()));
                dataField.setText(new SimpleDateFormat("dd/MM/yyyy").format(lote.getDataEntrada()));
                tipoAcaoField.setText(lote.getTipoAcao());

                // Desativa a edição dos campos
                codigoMaterialField.setEditable(false);
                quantidadeField.setEditable(false);
                dataField.setEditable(false);
                tipoAcaoField.setEditable(false);

                resultadoLabel.setText("Lote encontrado!");
            } else {
                resultadoLabel.setText("Lote não encontrado!");
            }
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Erro: código do lote inválido.");
        }
    }

    @FXML
    private void handleExcluirButtonAction() {
        String codigoLote = codigoLoteField.getText();
        try {
            int id = Integer.parseInt(codigoLote);
            loteServ.deletarLote(id);
            resultadoLabel.setText("Lote excluído com sucesso!");


            codigoMaterialField.clear();
            quantidadeField.clear();
            dataField.clear();
            tipoAcaoField.clear();
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Erro: código do lote inválido.");
        }
    }

    @FXML
    private void handleCriarButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/CriarLote.fxml", "Criar Lote");
    }

    @FXML
    public void handleModificarButtonAction(ActionEvent actionEvent) {
        carregarTela("/com/example/stockmaster/Lotes/ModificarLote.fxml", "Criar Lote");
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
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
