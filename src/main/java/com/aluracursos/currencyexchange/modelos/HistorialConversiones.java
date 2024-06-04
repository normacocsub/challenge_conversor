package com.aluracursos.currencyexchange.modelos;

import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {
    private List<Conversion> conversiones;

    public HistorialConversiones() {
        conversiones = new ArrayList<>();
    }

    public void agregarConversion(Conversion conversion) {
        conversiones.add(conversion);
    }

    public List<Conversion> obtenerHistorial() {
        return conversiones;
    }

    public void mostrarHistorial() {
        List<Conversion> historial = obtenerHistorial();
        for (Conversion conversion : historial) {
            System.out.println(conversion);
        }
    }
}
