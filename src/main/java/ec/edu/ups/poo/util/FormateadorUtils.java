package ec.edu.ups.poo.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase utilitaria que proporciona métodos para formatear monedas y fechas
 * según la configuración regional especificada.
 */
public class FormateadorUtils {

    /**
     * Formatea una cantidad numérica como moneda de acuerdo al {@link Locale} dado.
     *
     * @param cantidad Cantidad numérica a formatear.
     * @param locale Configuración regional que define el formato de moneda.
     * @return Cadena con la cantidad formateada como moneda.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Formatea una fecha como texto según el {@link Locale} dado,
     * usando un formato de tipo medio (ejemplo: 21-jul-2025).
     *
     * @param fecha Fecha a formatear.
     * @param locale Configuración regional que define el formato de fecha.
     * @return Cadena con la fecha formateada.
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }
}
