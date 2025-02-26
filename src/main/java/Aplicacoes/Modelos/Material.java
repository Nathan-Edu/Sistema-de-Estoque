package Aplicacoes.Modelos;

import Aplicacoes.Decoradores.Produto;
import java.math.BigDecimal;

public class Material implements Produto {
    private int id_material;
    private String descricao_curta;
    private String descricao_Longa;
    private BigDecimal quantidade;
    private String unidade_Medida;
    private String deposito;

    public Material(int idMaterial, String descricao_curta, String descricao_Longa, BigDecimal quantidade, String unidadeMedida, String deposito) {
        this.id_material = idMaterial;
        this.descricao_curta = descricao_curta;
        this.descricao_Longa = descricao_Longa;
        this.quantidade = quantidade;
        this.unidade_Medida = unidadeMedida;
        this.deposito = deposito;
    }

    // Construtor alternativo que aceita 4 parâmetros
    public Material(int idMaterial, String descricao_curta, String descricao_Longa, BigDecimal quantidade) {
        this.id_material = idMaterial;
        this.descricao_curta = descricao_curta;
        this.descricao_Longa = descricao_Longa;
        this.quantidade = quantidade;
        // Valores padrão
        this.unidade_Medida = "";
        this.deposito = "";
    }

    public Material(int i, String descricaoCurta, String descricaoLonga, String unidadeMedida, String s) {
    }

    public int getId() {
        return id_material;
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

    public String getDescricao_Longa() {
        return descricao_Longa;
    }

    public void setDescricao_Longa(String descricao_Longa) {
        this.descricao_Longa = descricao_Longa;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    @Override
    public int getQuantidades() {
        return 0;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade_Medida() {
        return unidade_Medida;
    }

    public void setUnidade_Medida(String unidade_Medida) {
        this.unidade_Medida = unidade_Medida;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Descrição Curta: %s | Descrição Longa: %s | Quantidade: %s | Unidade de Medida: %s | Depósito: %s",
                id_material, descricao_curta, descricao_Longa, quantidade, unidade_Medida, deposito);
    }

    @Override
    public String getDescricao() {
        return descricao_curta;
    }
}
