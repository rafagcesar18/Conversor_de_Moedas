import java.util.Map;

/**
 * Record que representa o retorno da API ExchangeRate.
 * Contém a moeda base e o mapa de conversões disponíveis.
 */
public record ExchangeRateApi(String baseCode, Map<String, Double> conversionRates) {

    @Override
    public String toString() {
        return  "Código da Moeda Base: " + baseCode +
                "\nTaxas de Conversão: " + conversionRates;
    }
}
