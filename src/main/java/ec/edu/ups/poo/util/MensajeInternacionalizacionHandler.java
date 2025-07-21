package ec.edu.ups.poo.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase encargada de gestionar la internacionalización de mensajes
 * mediante el uso de {@link ResourceBundle} y {@link Locale}.
 * Permite obtener textos traducidos y cambiar el idioma en tiempo de ejecución.
 */
public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;

    /**
     * Constructor que inicializa el manejador con un idioma y país específicos.
     *
     * @param lenguaje Código de idioma (por ejemplo, "es", "en", "fr").
     * @param pais Código de país (por ejemplo, "EC", "US", "FR").
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el mensaje traducido correspondiente a la clave especificada.
     *
     * @param key Clave del mensaje en el archivo de propiedades.
     * @return Mensaje traducido.
     */
    public String get(String key) {
        return bundle.getString(key);
    }

    /**
     * Cambia el idioma y país del {@link Locale} y actualiza el {@link ResourceBundle}.
     *
     * @param lenguaje Nuevo código de idioma.
     * @param pais Nuevo código de país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el {@link Locale} actual.
     *
     * @return Objeto {@link Locale} usado actualmente.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Obtiene el código del idioma actual (por ejemplo, "es", "en", "fr").
     *
     * @return Código del idioma como cadena.
     */
    public String getCodigoIdioma() {
        return locale.getLanguage();
    }

}