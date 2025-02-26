package Aplicacoes.Modelos;

public class Requisicao {
    private int id_requisicao;
    private int id_usuario;
    private String solicitante;
    private int id_material;
    private double quantidade;
    private String data;

    public Requisicao(int id_requisicao, int id_usuario, String solicitante, int id_material, double quantidade, String data) {
        this.id_requisicao = id_requisicao;
        this.id_usuario = id_usuario;
        this.solicitante = solicitante;
        this.id_material = id_material;
        this.quantidade = quantidade;
        this.data = data;
    }

    public int getId() {
        return id_requisicao;
    }

    public void setId(int id_requisicao) {
        this.id_requisicao = id_requisicao;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public int getIdMaterial() {
        return id_material;
    }

    public void setIdMaterial(int id_material) {
        this.id_material = id_material;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, ID Usuário: %d, Solicitante: %s, ID Material: %d, Quantidade: %.2f, Data: %s", id_requisicao, id_usuario, solicitante, id_material, quantidade, data);
    }
}
