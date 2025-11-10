import java.util.Scanner;

/**
 * Classe principal do Conversor de Moedas.
 * Exibe o menu interativo no console e permite ao usuário
 * converter valores entre USD, BRL, ARS e COP.
 */
public class ConversorApp {

    public static void main(String[] args) {

        Conversor conversor = new Conversor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n########## CONVERSOR DE MOEDAS ##########");
            System.out.println("1 - Dólar (USD) --> Peso Argentino (ARS)");
            System.out.println("2 - Peso Argentino (ARS) --> Dólar (USD)");
            System.out.println("3 - Dólar (USD) --> Real Brasileiro (BRL)");
            System.out.println("4 - Real Brasileiro (BRL) --> Dólar (USD)");
            System.out.println("5 - Dólar (USD) --> Peso Colombiano (COP)");
            System.out.println("6 - Peso Colombiano (COP) --> Dólar (USD)");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (escolha) {
                case 1 -> realizarConversao(conversor, scanner, "USD", "ARS");
                case 2 -> realizarConversao(conversor, scanner, "ARS", "USD");
                case 3 -> realizarConversao(conversor, scanner, "USD", "BRL");
                case 4 -> realizarConversao(conversor, scanner, "BRL", "USD");
                case 5 -> realizarConversao(conversor, scanner, "USD", "COP");
                case 6 -> realizarConversao(conversor, scanner, "COP", "USD");
                case 7 -> {
                    System.out.println("Encerrando a aplicação...");
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    /**
     * Método responsável por realizar a conversão de moeda.
     *
     * @param conversor     Instância do objeto Conversor que faz a requisição à API
     * @param scanner       Scanner para entrada de dados do usuário
     * @param moedaOrigem   Código da moeda base (ex: "USD")
     * @param moedaDestino  Código da moeda destino (ex: "BRL")
     */
    private static void realizarConversao(Conversor conversor, Scanner scanner,
                                          String moedaOrigem, String moedaDestino) {
        try {
            ExchangeRateApi taxas = conversor.consultaValor(moedaOrigem);

            if (taxas != null && taxas.conversionRates() != null) {
                Double taxaDeCambio = taxas.conversionRates().get(moedaDestino);

                if (taxaDeCambio != null) {
                    System.out.print("Digite o valor em " + moedaOrigem + ": ");
                    double valorOriginal = scanner.nextDouble();

                    double valorConvertido = valorOriginal * taxaDeCambio;

                    System.out.printf("O valor de %.2f %s convertido para %s é: %.2f%n",
                            valorOriginal, moedaOrigem, moedaDestino, valorConvertido);
                    System.out.println("********************************\n");
                } else {
                    System.out.println("Taxa de câmbio para " + moedaDestino + " não disponível.");
                }
            } else {
                System.out.println("Erro ao obter as taxas de câmbio.");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
