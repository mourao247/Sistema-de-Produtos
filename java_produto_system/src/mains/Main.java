package mains;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Produto;
import services.ProdutoService;

public class Main {
    public static void main(String[] args) {
        List<Produto> armazem = new ArrayList<>();
        ProdutoService funcsProd = new ProdutoService();
        Scanner scan = new Scanner(System.in);

        Produto prodMesaEscritorio = new Produto();
        prodMesaEscritorio.setNome("Mesa de Escritorio");
        prodMesaEscritorio.setValor(250.50);
        prodMesaEscritorio.setUnidades(50);

        Produto prodCadeiraEscritorio = new Produto();
        prodCadeiraEscritorio.setNome("Cadeira de Escritorio");
        prodCadeiraEscritorio.setValor(298.80);
        prodCadeiraEscritorio.setUnidades(120);

        Produto prodNotebook = new Produto();
        prodNotebook.setNome("Notebook");
        prodNotebook.setValor(2500.00);
        prodNotebook.setUnidades(30);

        Produto prodMousepad = new Produto();
        prodMousepad.setNome("Mousepad");
        prodMousepad.setValor(19.99);
        prodMousepad.setUnidades(80);

        Produto prodMonitor = new Produto();
        prodMonitor.setNome("Monitor");
        prodMonitor.setValor(899.00);
        prodMonitor.setUnidades(8);

        armazem.add(prodMesaEscritorio);
        armazem.add(prodCadeiraEscritorio);
        armazem.add(prodNotebook);
        armazem.add(prodMousepad);
        armazem.add(prodMonitor);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n====================================");
            System.out.println("      SISTEMA DE ARMAZEM");
            System.out.println("====================================");
            System.out.println("1 - Exibir produtos");
            System.out.println("2 - Contar produtos");
            System.out.println("3 - Valor total do armazem");
            System.out.println("4 - Buscar por nome");
            System.out.println("5 - Filtrar por valor");
            System.out.println("6 - Classificar produto");
            System.out.println("7 - Produto mais caro");
            System.out.println("8 - Media dos valores");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            if (!scan.hasNextInt()) {
                System.out.println("Opcao invalida.");
                scan.nextLine();
                continue;
            }

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        funcsProd.exibirTodos(armazem);
                    }
                    break;

                case 2:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        System.out.println("Total de produtos no armazem: " + funcsProd.contarProdutos(armazem));
                    }
                    break;

                case 3:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        double totalArmazem = funcsProd.calcularTotalArmazem(armazem);
                        System.out.printf("VALOR TOTAL DO ARMAZEM: R$ %.2f%n", totalArmazem);
                    }
                    break;

                case 4:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        System.out.print("Digite o nome do produto: ");
                        String nomeBusca = scan.nextLine();

                        Produto resultadoBusca = funcsProd.buscarPorNome(armazem, nomeBusca);

                        if (resultadoBusca != null) {
                            System.out.printf("Encontrado: %s | R$ %.2f | %d unid.%n",
                                    resultadoBusca.getNome(),
                                    resultadoBusca.getValor(),
                                    resultadoBusca.getUnidades());
                        } else {
                            System.out.println("Produto '" + nomeBusca + "' nao encontrado no armazem.");
                        }
                    }
                    break;

                case 5:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        System.out.print("Filtrar produtos acima de: R$ ");

                        if (!scan.hasNextDouble()) {
                            System.out.println("Valor invalido.");
                            scan.nextLine();
                            break;
                        }

                        double limiteValor = scan.nextDouble();
                        scan.nextLine();

                        List<Produto> produtosFiltrados = funcsProd.filtrarAcimaDe(armazem, limiteValor);

                        if (produtosFiltrados.isEmpty()) {
                            System.out.printf("Nenhum produto acima de R$ %.2f%n", limiteValor);
                        } else {
                            System.out.println("Produtos encontrados: " + produtosFiltrados.size());
                            for (int i = 0; i < produtosFiltrados.size(); i++) {
                                Produto prodFiltrado = produtosFiltrados.get(i);
                                System.out.printf("%s | R$ %.2f | %d unidades%n",
                                        prodFiltrado.getNome(),
                                        prodFiltrado.getValor(),
                                        prodFiltrado.getUnidades());
                            }
                        }
                    }
                    break;

                case 6:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        System.out.print("Digite o nome do produto para classificar: ");
                        String nomeClassificacao = scan.nextLine();

                        Produto prodClassificado = funcsProd.buscarPorNome(armazem, nomeClassificacao);

                        if (prodClassificado != null) {
                            String categoria = funcsProd.classificarProduto(prodClassificado);
                            System.out.printf("%s (R$ %.2f) -> %s%n",
                                    prodClassificado.getNome(),
                                    prodClassificado.getValor(),
                                    categoria);
                        } else {
                            System.out.println("Produto '" + nomeClassificacao + "' nao encontrado no armazem.");
                        }
                    }
                    break;

                case 7:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        Produto prodMaiorValor = funcsProd.maiorValor(armazem);
                        System.out.printf("Produto mais caro: %s | R$ %.2f | Categoria: %s%n",
                                prodMaiorValor.getNome(),
                                prodMaiorValor.getValor(),
                                funcsProd.classificarProduto(prodMaiorValor));
                    }
                    break;

                case 8:
                    if (armazem.isEmpty()) {
                        System.out.println("Armazem vazio.");
                    } else {
                        double mediaValores = funcsProd.calcularMediaValor(armazem);
                        System.out.printf("Media dos valores: R$ %.2f%n", mediaValores);
                    }
                    break;

                case 0:
                    System.out.println("Encerrando sistema. Ate logo!");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }

        scan.close();
    }
}
