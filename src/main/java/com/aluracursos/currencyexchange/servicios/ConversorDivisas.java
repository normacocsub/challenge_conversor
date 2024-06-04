package com.aluracursos.currencyexchange.servicios;

import com.aluracursos.currencyexchange.api.ServicioTasaDeCambio;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ConversorDivisas {

    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        return ServicioTasaDeCambio.getExchangeRate(baseCurrency, targetCurrency);
    }

    public double convertir(String divisaBase, String divisaObjetivo, double cantidad) {
        double tasaConversion = getExchangeRate(divisaBase, divisaObjetivo);
        return cantidad * tasaConversion;
    }

    public String formatearCantidad(double cantidad) {
        NumberFormat formato = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formatoDecimal = (DecimalFormat) formato;
        formatoDecimal.applyPattern("#,##0.00");
        return formatoDecimal.format(cantidad);
    }
}

