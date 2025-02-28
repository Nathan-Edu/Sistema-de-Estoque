package Aplicacoes.Servicos;

import Aplicacoes.Dao.MaterialDAO;
import Aplicacoes.Modelos.Material;

import java.util.List;
import java.util.logging.Logger;

public class MaterialServ {
    private static final Logger LOGGER = Logger.getLogger(MaterialServ.class.getName());
    private MaterialDAO materialDAO = new MaterialDAO();

    public MaterialServ() {
    }

    public void adicionaProduto(Material material) {
        materialDAO.adicionaMaterial(material);
        LOGGER.info("Produto adicionado com sucesso: " + material);
    }

    public Material buscarProdutoPorId(int id) {
        return materialDAO.buscarMaterialPorId(id);
    }

    public List<Material> listarProdutos() {
        return materialDAO.listarMateriais();
    }

    public void atualizarProduto(Material material) {
        materialDAO.atualizarMaterial(material);
        LOGGER.info("Produto atualizado: " + material);
    }

    public void registrarMovimentacao(Material material, String tipo, int quantidade) {
        LOGGER.info("Movimentação registrada: " + tipo + " de " + quantidade + " unidades do produto: " + material.getDescricao_curta());
    }

    public void listarMovimentacao() {
        LOGGER.info("Listando movimentações");
    }

    public void pesquisarProduto(String termoBusca) {
        LOGGER.info("Pesquisando produto com termo: " + termoBusca);
    }

    public void gerarRelatorioEstoq() {
        LOGGER.info("Gerando relatório de estoque");
    }

    public void exibirProdutos() {
        for (Material m : listarProdutos()) {
            System.out.println(m);
        }
    }

    public void cadastrarMaterial(Material material) {
        try {
            materialDAO.adicionaMaterial(material);
            LOGGER.info("Material cadastrado com sucesso: " + material);
        } catch (IllegalArgumentException e) {
            LOGGER.warning("Falha no cadastro: " + e.getMessage());
        }
    }

    public void deletarMaterial(int id) {
        materialDAO.deletarMaterial(id);
        LOGGER.info("Material deletado com sucesso, ID: " + id);
    }

    public void atualizarMaterial(Material material) {
        materialDAO.atualizarMaterial(material);
        LOGGER.info("Material atualizado: " + material);
    }

    public int obterProximoId() {
        return materialDAO.obterProximoId();
    }

    public Material buscarProdutoPorNome(String nome) {
        return materialDAO.buscarMaterialPorNome(nome);
    }
}
