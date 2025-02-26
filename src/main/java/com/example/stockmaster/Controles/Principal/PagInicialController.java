package com.example.stockmaster.Controles.Principal;

import Aplicacoes.Modelos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


import java.io.IOException;

public class PagInicialController {

    private Usuario usuarioLogado;

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    @FXML
    private void handleCriarMaterialButtonAction() {
        carregarTela("/com/example/stockmaster/Materiais/CriarMaterial.fxml", "Criar Material");
    }

    @FXML
    private void handleModificarMaterialButtonAction() {
        carregarTela("/com/example/stockmaster/Materiais/ModificarMaterial.fxml", "Modificar Material");
    }

    @FXML
    private void handleExibirMaterialButtonAction() {
        carregarTela("/com/example/stockmaster/Materiais/ExibirMaterial.fxml", "Exibir Material");
    }

    @FXML
    private void handleListaMaterialButtonAction() {
        carregarTela("/com/example/stockmaster/Materiais/ListaMaterial.fxml", "Lista dos Material");
    }

    @FXML
    private void handleCriarEstoqueButtonAction() {
        carregarTela("/com/example/stockmaster/Estoque/CriarEstoque.fxml", "Criar Estoque");
    }

    @FXML
    private void handleModificarEstoqueButtonAction() {
        carregarTela("/com/example/stockmaster/Estoque/ModificarEstoque.fxml", "Modificar Estoque");
    }

    @FXML
    private void handleExibirEstoqueButtonAction() {
        carregarTela("/com/example/stockmaster/Estoque/ExibirEstoque.fxml", "Exibir Estoque");
    }

    @FXML
    private void handleListaEstoqueButtonAction() {
        carregarTela("/com/example/stockmaster/Estoque/ListaEstoque.fxml", "Lista do Estoque");
    }

    @FXML
    private void handleCriarLoteButtonAction() {
        carregarTela("/com/example/stockmaster/Lotes/CriarLote.fxml", "Criar Lote");
    }

    @FXML
    private void handleModificarLoteButtonAction() {
        carregarTela("/com/example/stockmaster/Lotes/ModificarLote.fxml", "Modificar Lote");
    }

    @FXML
    private void handleExibirLoteButtonAction() {
        carregarTela("/com/example/stockmaster/Lotes/ExibirLote.fxml", "Exibir Lote");
    }

    @FXML
    private void handleListaLoteButtonAction() {
        carregarTela("/com/example/stockmaster/Lotes/ListaLote.fxml", "Lista dos Lote");
    }

    @FXML
    private void handleCriarRequisicoesButtonAction() {
        carregarTela("/com/example/stockmaster/Requisicoes/CriarRequisicoes.fxml", "Criar Requisições");
    }

    @FXML
    private void handleModificarRequisicoesButtonAction() {
        carregarTela("/com/example/stockmaster/Requisicoes/ModificarRequisicoes.fxml", "Modificar Requisições");
    }

    @FXML
    private void handleExibirRequisicoesButtonAction() {
        carregarTela("/com/example/stockmaster/Requisicoes/ExibirRequisicoes.fxml", "Exibir Requisições");
    }

    @FXML
    private void handleListaRequisicoesButtonAction() {
        carregarTela("/com/example/stockmaster/Requisicoes/ListaRequisicoes.fxml", "Lista de Requisições");
    }

    @FXML
    private void handleUserMenuItemAction(ActionEvent event) {
        carregarConfiguracoes();
    }

    @FXML
    private void handleLogoutMenuItemAction(ActionEvent event) {
        fecharJanelaAtual(event);
        carregarTela("/com/example/stockmaster/LoginUsuario/Login.fxml", "Login");
    }

    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarConfiguracoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/stockmaster/Principal/Configuracoes.fxml"));
            Parent root = loader.load();

            ConfiguracoesController configuracoesController = loader.getController();
            configuracoesController.setUsuarioLogado(usuarioLogado);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Configurações de Usuário");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fecharJanelaAtual(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.close();
    }

}
