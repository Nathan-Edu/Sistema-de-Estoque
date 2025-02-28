package com.example.stockmaster.Controles.Lotes;

import Aplicacoes.Modelos.Lote;
import Aplicacoes.Modelos.Material;
import Aplicacoes.Servicos.LoteServ;
import Aplicacoes.Servicos.MaterialServ;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriarLoteController {

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField dataField;

    @FXML
    private TextField tipoAcaoField;

    @FXML
    private Button listaButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Label statusLabel;

    private LoteServ loteServ = new LoteServ();
    private MaterialServ materialServ = new MaterialServ();

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

    @FXML
    private void handleSalvarButtonAction() {
        String codigoOuNomeMaterial = codigoMaterialField.getText();
        String quantidade = quantidadeField.getText();
        String data = dataField.getText();
        String tipoAcao = tipoAcaoField.getText();

        Matcher matcher = DATE_PATTERN.matcher(data);
        if (!matcher.matches()) {
            statusLabel.setText("Erro: formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        try {
            Material material = null;

            try {
                int codMaterial = Integer.parseInt(codigoOuNomeMaterial);
                material = materialServ.buscarProdutoPorId(codMaterial);
            } catch (NumberFormatException e) {
                material = materialServ.buscarProdutoPorNome(codigoOuNomeMaterial);
            }

            if (material == null) {
                statusLabel.setText("Erro: material não encontrado.");
                return;
            }

            Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            BigDecimal quantidadeBigDecimal = new BigDecimal(quantidade);
            Lote lote = new Lote(0, material.getId_material(), quantidadeBigDecimal, tipoAcao, dataEntrada);
            loteServ.cadastrarLote(lote);
            statusLabel.setText("Lote salvo com sucesso!");
        } catch (ParseException e) {
            statusLabel.setText("Erro ao salvar o lote: formato de data inválido.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro ao salvar o lote: quantidade inválida.");
        }
    }

    @FXML
    private void handleListaButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/ListaLote.fxml", "Lista de Lotes");
    }

    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage stage = (Stage) listaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }
}
