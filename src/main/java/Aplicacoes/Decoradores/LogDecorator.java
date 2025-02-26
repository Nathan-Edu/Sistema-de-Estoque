package Aplicacoes.Decoradores;

public class LogDecorator extends MaterialDecorator {
    public LogDecorator(Produto produto) {
        super(produto);
    }

    @Override
    public String getDescricao() {
        return produto.getDescricao() + " \n[Logado]";
    }

    @Override
    public int getQuantidades() {
        return produto.getQuantidades();
    }
}
