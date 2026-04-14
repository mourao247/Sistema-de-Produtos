package services;

import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class ProdutoService {

    public int contarProdutos(List<Produto> armazem) {
        return armazem.size();
    }

    public void exibirTodos(List<Produto> armazem) {
        if (armazem.isEmpty()) {
            System.out.println("Armazem vazio.");
            return;
        }

        for (int i = 0; i < armazem.size(); i++) {
            Produto prodAtual = armazem.get(i);
            System.out.printf("[%d] %s | R$ %.2f | %d unid.%n",
                    i + 1,
                    prodAtual.getNome(),
                    prodAtual.getValor(),
                    prodAtual.getUnidades());
        }
    }

    public double calcularTotalArmazem(List<Produto> armazem) {
        double total = 0;

        for (int i = 0; i < armazem.size(); i++) {
            Produto prodAtual = armazem.get(i);
            total += prodAtual.getValor() * prodAtual.getUnidades();
        }

        return total;
    }

    public Produto buscarPorNome(List<Produto> armazem, String nomeBusca) {
        int i = 0;

        while (i < armazem.size()) {
            if (armazem.get(i).getNome().equalsIgnoreCase(nomeBusca)) {
                return armazem.get(i);
            }
            i++;
        }

        return null;
    }

    public List<Produto> filtrarAcimaDe(List<Produto> armazem, double limiteValor) {
        List<Produto> filtrados = new ArrayList<>();

        for (int i = 0; i < armazem.size(); i++) {
            if (armazem.get(i).getValor() > limiteValor) {
                filtrados.add(armazem.get(i));
            }
        }

        return filtrados;
    }

    public String classificarProduto(Produto prod) {
        double valorProduto = prod.getValor();
        int faixa = (valorProduto < 20) ? 1 : (valorProduto < 100) ? 2 : (valorProduto < 500) ? 3 : 4;

        switch (faixa) {
            case 1:
                return "Economico";
            case 2:
                return "Intermediario";
            case 3:
                return "Premium";
            case 4:
                return "Luxo";
            default:
                return "Sem classificacao";
        }
    }

    public Produto maiorValor(List<Produto> armazem) {
        if (armazem.isEmpty()) {
            return null;
        }

        Produto maior = armazem.get(0);

        for (int i = 1; i < armazem.size(); i++) {
            if (armazem.get(i).getValor() > maior.getValor()) {
                maior = armazem.get(i);
            }
        }

        return maior;
    }

    public double calcularMediaValor(List<Produto> armazem) {
        if (armazem.isEmpty()) {
            return 0;
        }

        double soma = 0;

        for (int i = 0; i < armazem.size(); i++) {
            soma += armazem.get(i).getValor();
        }

        return soma / armazem.size();
    }
}
