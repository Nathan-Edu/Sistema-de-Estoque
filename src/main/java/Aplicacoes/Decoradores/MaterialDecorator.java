package Aplicacoes.Decoradores;

import java.math.BigDecimal;

public abstract class MaterialDecorator implements Produto {
    protected Produto produto;

    public MaterialDecorator(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String getDescricao() {
        return produto.getDescricao();
    }

    @Override
    public BigDecimal getQuantidade() {
        return produto.getQuantidade();
    }
}
