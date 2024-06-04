package com.aluracursos.currencyexchange.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioTasaDeCambio {
    private static final String API_KEY = "503f78c89375a5c1e827e806";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/"+baseCurrency+"/"+targetCurrency;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder() .uri(URI.create(url)) .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            return jsonResponse.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            System.err.println("Error al obtener la tasa de cambio: " + e.getMessage());
            throw new RuntimeException("Error al obtener la tasa de cambio.");
        }
    }
}
