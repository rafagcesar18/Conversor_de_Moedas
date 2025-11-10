import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por realizar consultas à API ExchangeRate
 * e retornar as taxas de câmbio para determinadas moedas.
 */
public class Conversor {

    private static final String API_KEY = "433121069fb277a91a33391e";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Consulta as taxas de câmbio com base na moeda informada.
     *
     * @param codigoMoeda Código da moeda base (ex: "USD")
     * @return objeto ExchangeRateApi contendo as taxas filtradas
     */
    public ExchangeRateApi consultaValor(String codigoMoeda) {
        URI url = URI.create(BASE_URL + API_KEY + "/latest/" + codigoMoeda.toUpperCase());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro ao consultar API: HTTP " + response.statusCode());
            }

            return parseExchangeRate(response.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro de comunicação com a API: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar dados da moeda: " + codigoMoeda);
        }
    }

    /**
     * Converte o JSON retornado pela API em um objeto ExchangeRateApi.
     */
    private ExchangeRateApi parseExchangeRate(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String baseCode = jsonObject.get("base_code").getAsString();
        Map<String, Double> filteredRates = filterRates(jsonObject.getAsJsonObject("conversion_rates"));
        return new ExchangeRateApi(baseCode, filteredRates);
    }

    /**
     * Filtra apenas as moedas de interesse para simplificar a aplicação.
     */
    private Map<String, Double> filterRates(JsonObject conversionRatesObject) {
        Map<String, Double> filteredRates = new HashMap<>();
        for (String currency : new String[]{"USD", "COP", "ARS", "BRL"}) {
            if (conversionRatesObject.has(currency)) {
                filteredRates.put(currency, conversionRatesObject.get(currency).getAsDouble());
            }
        }
        return filteredRates;
    }
}
