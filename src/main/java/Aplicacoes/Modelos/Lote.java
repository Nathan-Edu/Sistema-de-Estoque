package Aplicacoes.Modelos;

import java.math.BigDecimal;
import java.util.Date;

public class Lote {
    private int idLote;
    private int codMaterial;
    private String descricaoCurta;
    private BigDecimal quantidade;
    private String tipoAcao;
    private Date dataEntrada;
    private String status;

    public Lote(int idLote, int codMaterial, BigDecimal quantidade, String tipoAcao, Date dataEntrada) {
        this.idLote = idLote;
        this.codMaterial = codMaterial;
        this.quantidade = quantidade;
        this.tipoAcao = tipoAcao;
        this.dataEntrada = dataEntrada;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(int codMaterial) {
        this.codMaterial = codMaterial;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Código Material: %d, Descrição Curta: %s, Quantidade: %s, Tipo de Ação: %s, Data: %s, Status: %s",
                idLote, codMaterial, descricaoCurta, quantidade, tipoAcao, dataEntrada, status);
    }
}
