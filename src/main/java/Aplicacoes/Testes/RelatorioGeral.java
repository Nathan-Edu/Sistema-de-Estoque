package Aplicacoes.Testes;

import Aplicacoes.Dao.*;
import Aplicacoes.Modelos.*;

import java.util.List;

public class RelatorioGeral {
    public static void main(String[] args) {
        MaterialDAO materialDAO = new MaterialDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LoteDAO loteDAO = new LoteDAO();
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        RequisicaoDAO requisicaoDAO = new RequisicaoDAO();

        System.out.println("\n--- Lista de Materiais ---");
        List<Material> materiais = materialDAO.listarMateriais();
        for (Material material : materiais) {
            System.out.println("ID: " + material.getId() + ", Descrição Curta: " + material.getDescricao_curta() +
                    ", Descrição Longa: " + material.getDescricao_Longa() + ", Quantidade: " + material.getQuantidade() +
                    ", Unidade de Medida: " + material.getUnidade_Medida() + ", Depósito: " + material.getDeposito());
        }

        System.out.println("\n--- Lista de Usuários ---");
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId_usuario() + ", Nome: " + usuario.getNome() +
                    ", Email: " + usuario.getEmail());
        }

        System.out.println("\n--- Lista de Lotes ---");
        List<Lote> lotes = loteDAO.listarLotes();
        for (Lote lote : lotes) {
            System.out.println("ID: " + lote.getIdLote() + ", Código Material: " + lote.getCodMaterial() + ", Quantidade: " + lote.getQuantidade() +
                    ", Tipo de Ação: " + lote.getTipoAcao() + ", Data: " + lote.getDataEntrada());
        }

        System.out.println("\n--- Lista de Estoques ---");
        List<Estoque> estoques = estoqueDAO.listarEstoques();
        for (Estoque estoque : estoques) {
            System.out.println("ID: " + estoque.getIdEstoque() + ", Código Material: " + estoque.getCodMaterial() +
                    ", Quantidade: " + estoque.getQuantidade() + ", Unidade de Medida: " + estoque.getUnidadeMedida() +
                    ", Data de Entrada: " + estoque.getDataEntrada());
        }

        System.out.println("\n--- Lista de Requisições ---");
        List<Requisicao> requisicoes = requisicaoDAO.listarRequisicoes();
        for (Requisicao requisicao : requisicoes) {
            System.out.println("ID: " + requisicao.getId() + ", Solicitante: " + requisicao.getSolicitante() +
                    ", ID Material: " + requisicao.getIdMaterial() + ", Quantidade: " + requisicao.getQuantidade() +
                    ", Data: " + requisicao.getData());
        }
    }
}
