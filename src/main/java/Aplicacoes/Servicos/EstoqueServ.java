package Aplicacoes.Servicos;

import Aplicacoes.Dao.EstoqueDAO;
import Aplicacoes.Modelos.Estoque;

import java.util.List;

public class EstoqueServ {
    private EstoqueDAO estoqueDAO = new EstoqueDAO();

    public void adicionaEstoque(Estoque estoque) {
        estoqueDAO.adicionaEstoque(estoque);
    }

    public Estoque buscarEstoquePorId(int idEstoque) {
        return estoqueDAO.buscarEstoquePorId(idEstoque);
    }

    public List<Estoque> listarEstoques() {
        return estoqueDAO.listarEstoques();
    }

    public void atualizarEstoque(Estoque estoque) {
        estoqueDAO.atualizarEstoque(estoque);
    }

    public void deletarEstoque(int idEstoque) {
        estoqueDAO.deletarEstoque(idEstoque);
    }

    public List<Estoque> buscarEstoquePorNome(String nomeMaterial) {
        return estoqueDAO.buscarEstoquePorNome(nomeMaterial);
    }
}
