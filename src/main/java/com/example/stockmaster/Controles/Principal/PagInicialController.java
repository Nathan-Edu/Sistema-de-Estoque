package com.example.stockmaster.Controles.Principal;

import Aplicacoes.Modelos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import java.io.IOException;

public class PagInicialController {

    @FXML
    private Label statusLabel;

    private Usuario usuarioLogado;

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    @FXML
    private void handleCriarMaterialButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/CriarMaterial.fxml", "Criar Material");
    }

    @FXML
    private void handleModificarMaterialButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/ModificarMaterial.fxml", "Modificar Material");
    }

    @FXML
    private void handleExibirMaterialButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/ExibirMaterial.fxml", "Exibir Material");
    }

    @FXML
    private void handleListaMaterialButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Materiais/ListaMaterial.fxml", "Lista de Materiais");
    }

    @FXML
    private void handleCriarEstoqueButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/CriarEstoque.fxml", "Criar Estoque");
    }

    @FXML
    private void handleModificarEstoqueButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/ModificarEstoque.fxml", "Modificar Estoque");
    }

    @FXML
    private void handleExibirEstoqueButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/ExibirEstoque.fxml", "Exibir Estoque");
    }

    @FXML
    private void handleListaEstoqueButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Estoque/ListaEstoque.fxml", "Lista de Estoques");
    }

    @FXML
    private void handleCriarLoteButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/CriarLote.fxml", "Criar Lote");
    }

    @FXML
    private void handleModificarLoteButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/ModificarLote.fxml", "Modificar Lote");
    }

    @FXML
    private void handleExibirLoteButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/ExibirLote.fxml", "Exibir Lote");
    }

    @FXML
    private void handleListaLoteButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Lotes/ListaLote.fxml", "Lista de Lotes");
    }

    @FXML
    private void handleCriarRequisicoesButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/CriarRequisicoes.fxml", "Criar Requisições");
    }

    @FXML
    private void handleModificarRequisicoesButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/ModificarRequisicoes.fxml", "Modificar Requisições");
    }

    @FXML
    private void handleExibirRequisicoesButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/ExibirRequisicoes.fxml", "Exibir Requisições");
    }

    @FXML
    private void handleListaRequisicoesButtonAction(ActionEvent event) {
        carregarTela("/com/example/stockmaster/Requisicoes/ListaRequisicoes.fxml", "Lista de Requisições");
    }

    @FXML
    private void handleUserMenuItemAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/LoginUsuario/CadastroUsuario.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Configurações");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogoutMenuItemAction(ActionEvent event) {
        Stage stage = (Stage) statusLabel.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/LoginUsuario/Login.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
}
