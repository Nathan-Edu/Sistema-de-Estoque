package Aplicacoes.Servicos;

import Aplicacoes.Dao.RequisicaoDAO;
import Aplicacoes.Modelos.Requisicao;

import java.util.List;
import java.util.logging.Logger;

public class RequisicaoServ {
    private static final Logger LOGGER = Logger.getLogger(RequisicaoServ.class.getName());
    private RequisicaoDAO requisicaoDAO = new RequisicaoDAO();

    public void adicionaRequisicao(Requisicao requisicao) {
        requisicaoDAO.adicionaRequisicao(requisicao);
        LOGGER.info("Requisição adicionada com sucesso: " + requisicao);
    }

    public Requisicao buscarRequisicaoPorId(int id) {
        return requisicaoDAO.buscarRequisicaoPorId(id);
    }

    public List<Requisicao> listarRequisicoes() {
        return requisicaoDAO.listarRequisicoes();
    }

    public void atualizarRequisicao(Requisicao requisicao) {
        requisicaoDAO.atualizarRequisicao(requisicao);
        LOGGER.info("Requisição atualizada: " + requisicao);
    }

    public void deletarRequisicao(int id) {
        requisicaoDAO.deletarRequisicao(id);
        LOGGER.info("Requisição deletada: " + id);
    }
}
