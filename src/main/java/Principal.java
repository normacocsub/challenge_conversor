import com.aluracursos.currencyexchange.modelos.Divisas;
import com.aluracursos.currencyexchange.servicios.ConversorDivisas;
import com.aluracursos.currencyexchange.modelos.HistorialConversiones;
import com.aluracursos.currencyexchange.ui.SelectorDivisas;
import com.aluracursos.currencyexchange.modelos.Conversion;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Principal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SelectorDivisas selectorDivisas = new SelectorDivisas();
    private static final ConversorDivisas conversorDivisas = new ConversorDivisas();
    private static final HistorialConversiones historialConversiones = new HistorialConversiones();

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            ejecutarConversion();
            continuar = preguntarContinuar();
        }

        System.out.println("\n-------------------------- Historial de las conversiones realizadas: --------------------------");
        historialConversiones.mostrarHistorial();

        despedida();
    }


    private static void ejecutarConversion() {
        String[] divisas = {Divisas.USD, Divisas.ARS, Divisas.EUR, Divisas.BRL, Divisas.MXN,
                Divisas.CNY, Divisas.CHF, Divisas.GBP, Divisas.JPY, Divisas.COP};

        String divisaBase = seleccionarDivisa("Seleccione qué divisa desea convertir:", divisas);
        String divisaObjetivo = seleccionarDivisa("Ahora, seleccione a qué divisa desea convertir los " + divisaBase + " seleccionados:", divisas);
        double cantidad = leerCantidad("¿Qué monto desea convertir de " + divisaBase + " a " +
                divisaObjetivo + "? (No utilice ni puntos ni comas)");
        double montoConvertido = conversorDivisas.convertir(divisaBase, divisaObjetivo, cantidad);
        mostrarResultado(divisaBase, divisaObjetivo, cantidad, montoConvertido);

        Conversion conversion = new Conversion(divisaBase, divisaObjetivo, cantidad, conversorDivisas.getExchangeRate(divisaBase, divisaObjetivo),
                LocalDateTime.now());
        historialConversiones.agregarConversion(conversion);
    }


    //Método seleccionarDivisa, muestra al usuario un menú para seleccionar una divisa
    private static String seleccionarDivisa(String mensaje, String[] divisas) {
        System.out.println("\n────────────────────────────────────────────────────────────");
        System.out.println(mensaje);
        return selectorDivisas.seleccionarDivisa(divisas);
    }


    //Método leerCantidad, solicita al usuario que ingrese la cantidad a convertir
    private static double leerCantidad(String mensaje) {
        System.out.println("\n────────────────────────────────────────────────────────────");
        System.out.println(mensaje);
        String input = scanner.nextLine();

        while (!input.matches("\\d+")) {
            System.out.println("Por favor, ingrese un valor válido sin puntos ni comas.");
            input = scanner.nextLine();
        }

        return Double.parseDouble(input);
    }


    private static void mostrarResultado(String divisaBase, String divisaObjetivo, double cantidad, double montoConvertido) {
        DecimalFormat formato = new DecimalFormat("#.##");
        String cantidadFormateada = formato.format(cantidad);
        String montoFormateado = formato.format(montoConvertido);

        System.out.println("\n────────────────────────────────────────────────────────────");
        System.out.println("El resultado de convertir " + cantidadFormateada + " " +
                divisaBase + " a " + divisaObjetivo + " es: " + montoFormateado);
        System.out.println("────────────────────────────────────────────────────────────\n");
    }


    //Método preguntarContinuar, solicita al usuario que decida si desea realizar otra conversión
    private static boolean preguntarContinuar() {
        System.out.println("¿Desea realizar otra conversión? (s/n): ");
        String respuesta = scanner.nextLine();
        return respuesta.equalsIgnoreCase("s");
    }


    //Método despedida, se ejecuta al finalizar el programa y muestra un mensaje de despedida
    private static void despedida() {
        System.out.println("\nGracias por usar nuestro servicio de conversión de divisas. ¡Hasta luego!");
    }
}

