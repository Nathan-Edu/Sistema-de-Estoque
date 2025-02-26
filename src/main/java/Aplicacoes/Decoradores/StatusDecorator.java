package Aplicacoes.Decoradores;

import java.math.BigDecimal;

public class StatusDecorator extends MaterialDecorator {
    public StatusDecorator(Produto produto) {
        super(produto);
    }

    @Override
    public String getDescricao() {
        String status = produto.getQuantidade().compareTo(BigDecimal.ZERO) > 0 ? "Disponível" : "Indisponível";
        return produto.getDescricao() + " \nStatus: " + status;
    }

    @Override
    public int getQuantidades() {
        return 0;
    }
}
