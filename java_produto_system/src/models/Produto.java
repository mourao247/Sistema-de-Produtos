package models;

public class Produto {
    private int id;
    private String nome;
    private double valor;
    private int unidades;

    public int getId() {
        return this.id;
    }

    public void setId(int idProduto) {
        if (idProduto > 0) {
            this.id = idProduto;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nomeProduto) {
        if (nomeProduto != null && !nomeProduto.trim().isEmpty()) {
            this.nome = nomeProduto;
        }
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valorProduto) {
        if (valorProduto >= 0) {
            this.valor = valorProduto;
        }
    }

    public int getUnidades() {
        return this.unidades;
    }

    public void setUnidades(int qtdProduto) {
        if (qtdProduto >= 0) {
            this.unidades = qtdProduto;
        }
    }
}
