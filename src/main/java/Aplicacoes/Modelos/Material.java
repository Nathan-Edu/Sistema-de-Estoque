package Aplicacoes.Modelos;

import java.math.BigDecimal;

public class Material {
    private int id_material;
    private String descricao_curta;
    private String descricao_longa;
    private BigDecimal quantidade;
    private String unidade_medida;
    private String deposito;
    private String status;

    public Material(int id_material, String descricao_curta, String descricao_longa, BigDecimal quantidade, String unidade_medida, String deposito) {
        this.id_material = id_material;
        this.descricao_curta = descricao_curta;
        this.descricao_longa = descricao_longa;
        this.quantidade = quantidade;
        this.unidade_medida = unidade_medida;
        this.deposito = deposito;
        this.status = "";
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getDescricao_curta() {
        return descricao_curta;
    }

    public void setDescricao_curta(String descricao_curta) {
        this.descricao_curta = descricao_curta;
    }

    public String getDescricao_longa() {
        return descricao_longa;
    }

    public void setDescricao_longa(String descricao_longa) {
        this.descricao_longa = descricao_longa;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = unidade_medida;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Descrição Curta: %s, Descrição Longa: %s, Quantidade: %s, Unidade Medida: %s, Depósito: %s, Status: %s",
                id_material, descricao_curta, descricao_longa, quantidade, unidade_medida, deposito, status);
    }

    public void setDescricao_Longa(String novaDescricaoLonga) {
        this.descricao_longa = novaDescricaoLonga;
    }

    public void setUnidade_Medida(String novaUnidadeMedida) {
        this.unidade_medida = novaUnidadeMedida;
    }

    public int getId() {
        return id_material;
    }

    public String getDescricao_Longa() {
        return descricao_longa;
    }

    public String getUnidade_Medida() {
        return unidade_medida;
    }

    public String getDescricaoCurta() {
        return descricao_curta;
    }
}
