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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModificarLoteController {

    @FXML
    private TextField codigoLoteField;

    @FXML
    private TextField codigoMaterialField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField dataLote;

    @FXML
    private TextField tipoAcao;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button criarButton;

    @FXML
    private Button exibirButton;

    @FXML
    private Label statusLabel;

    private LoteServ loteServ = new LoteServ();

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

    @FXML
    private void handleSalvarButtonAction() {
        String codigoLote = codigoLoteField.getText();
        String codigoMaterial = codigoMaterialField.getText();
        String quantidade = quantidadeField.getText();
        String data = dataLote.getText();
        String tipoAcaoValue = tipoAcao.getText();

        Matcher matcher = DATE_PATTERN.matcher(data);
        if (!matcher.matches()) {
            statusLabel.setText("Erro: formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        try {
            Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            BigDecimal quantidadeBigDecimal = new BigDecimal(quantidade);
            Lote lote = new Lote(Integer.parseInt(codigoLote), Integer.parseInt(codigoMaterial), quantidadeBigDecimal, tipoAcaoValue, dataEntrada);
            loteServ.atualizarLote(lote);
            statusLabel.setText("Lote modificado com sucesso!");
        } catch (ParseException e) {
            statusLabel.setText("Erro ao modificar o lote: formato de data inválido.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro ao modificar o lote: código de material, quantidade ou ID de lote inválidos.");
        }
    }

    @FXML
    private void handleExibirButtonAction() {
        String codigoLote = codigoLoteField.getText();
        try {
            int id = Integer.parseInt(codigoLote);
            Lote lote = loteServ.buscarLotePorId(id);
            if (lote != null) {
                codigoMaterialField.setText(String.valueOf(lote.getCodMaterial()));
                quantidadeField.setText(String.valueOf(lote.getQuantidade()));
                dataLote.setText(new SimpleDateFormat("dd/MM/yyyy").format(lote.getDataEntrada()));
                tipoAcao.setText(lote.getTipoAcao());
                statusLabel.setText("Lote encontrado!");
            } else {
                statusLabel.setText("Lote não encontrado!");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: código do lote inválido.");
        }
    }

    @FXML
    private void handleVoltarButtonAction(ActionEvent event) {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
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

    public void setLote(Lote lote) {
        codigoLoteField.setText(String.valueOf(lote.getIdLote()));
        codigoMaterialField.setText(String.valueOf(lote.getCodMaterial()));
        quantidadeField.setText(String.valueOf(lote.getQuantidade()));
        dataLote.setText(new SimpleDateFormat("dd/MM/yyyy").format(lote.getDataEntrada()));
        tipoAcao.setText(lote.getTipoAcao());
    }
}
