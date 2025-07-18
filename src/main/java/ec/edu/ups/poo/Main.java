package ec.edu.ups.poo;

import ec.edu.ups.poo.controlador.*;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.poo.vista.LogInView;

public class Main {

    public static MensajeInternacionalizacionHandler i18n;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            i18n = new MensajeInternacionalizacionHandler("es", "EC");
            LogInView logInView = new LogInView(i18n);
            new LogInController(logInView, i18n);
            logInView.setVisible(true);
        });
    }
}