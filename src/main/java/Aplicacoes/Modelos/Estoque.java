package Aplicacoes.Modelos;

import java.math.BigDecimal;
import java.util.Date;

public class Estoque {
    private int idEstoque;
    private int codMaterial;
    private BigDecimal quantidade;
    private String unidadeMedida;
    private Date dataEntrada;

    public Estoque(int idEstoque, int codMaterial, BigDecimal quantidade, String unidadeMedida, Date dataEntrada) {
        this.idEstoque = idEstoque;
        this.codMaterial = codMaterial;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.dataEntrada = dataEntrada;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(int codMaterial) {
        this.codMaterial = codMaterial;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Código Material: %d, Quantidade: %s, Unidade de Medida: %s, Data de Entrada: %s",
                idEstoque, codMaterial, quantidade, unidadeMedida, dataEntrada);
    }
}
