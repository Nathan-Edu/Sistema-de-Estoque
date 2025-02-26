package Aplicacoes.Servicos;

import Aplicacoes.Dao.LoteDAO;
import Aplicacoes.Modelos.Lote;

import java.util.List;
import java.util.logging.Logger;

public class LoteServ {
    private static final Logger LOGGER = Logger.getLogger(LoteServ.class.getName());
    private LoteDAO loteDAO = new LoteDAO();

    public LoteServ() {
    }

    public void cadastrarLote(Lote lote) {
        loteDAO.adicionaLote(lote);
        LOGGER.info("Lote cadastrado com sucesso: " + lote);
    }

    public Lote buscarLotePorId(int id) {
        return loteDAO.buscarLotePorId(id);
    }

    public List<Lote> listarLotes() {
        return loteDAO.listarLotes();
    }

    public void atualizarLote(Lote lote) {
        loteDAO.atualizarLote(lote);
        LOGGER.info("Lote atualizado: " + lote);
    }

    public void deletarLote(int id) {
        loteDAO.deletarLote(id);
        LOGGER.info("Lote deletado com sucesso, ID: " + id);
    }
}
