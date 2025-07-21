package ec.edu.ups.poo;

import ec.edu.ups.poo.controlador.*;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.poo.vista.LogInView;

/**
 * Clase principal de la aplicación YANDRI STORE.
 *
 * Esta clase se encarga de iniciar la interfaz gráfica de usuario y configurar
 * el manejador de internacionalización con el idioma por defecto.
 *
 * @author Yandri Eduardo Sanchez Yanza
 */
public class Main {

    /**
     * Manejador de internacionalización que permite cargar los mensajes en diferentes idiomas.
     */
    public static MensajeInternacionalizacionHandler i18n;

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            i18n = new MensajeInternacionalizacionHandler("es", "EC");
            LogInView logInView = new LogInView(i18n);
            new LogInController(logInView, i18n);
            logInView.setVisible(true);
        });
    }
}