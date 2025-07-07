package ec.edu.ups.poo.controlador;

import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.vista.LogInView;
import ec.edu.ups.poo.vista.RegisterView;
import ec.edu.ups.poo.vista.PreguntasValidacionView;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class LogInController {

    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private final LogInView logInView;
    private final MensajeInternacionalizacionHandler tipoIdioma;
    private final MainAppCallback mainAppCallback;

    public interface MainAppCallback {
        void mostrarMenuPrincipal(Usuario usuarioAutenticado);
        void mostrarLogin();
    }

    public LogInController(UsuarioDAO usuarioDAO, PreguntaDAO preguntaDAO, LogInView logInView, MensajeInternacionalizacionHandler tipoIdioma, MainAppCallback callback) {
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;
        this.logInView = logInView;
        this.tipoIdioma = tipoIdioma;
        this.mainAppCallback = callback;
        configurarEventos();
    }

    private void configurarEventos() {
        configurarCambioIdioma();
        configurarLogin();
        configurarRegistro();
        configurarRecuperacion();
        configurarSalir();
    }

    private void configurarCambioIdioma() {
        logInView.actualizarOpcionesIdioma();
        logInView.getCbxIdioma().addActionListener(e -> {
            int selectedIndex = logInView.getCbxIdioma().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    tipoIdioma.setLenguaje("es", "EC");
                    break;
                case 1:
                    tipoIdioma.setLenguaje("en", "US");
                    break;
                case 2:
                    tipoIdioma.setLenguaje("fr", "FR");
                    break;
                default:
                    tipoIdioma.setLenguaje("en", "US");
            }
            logInView.aplicarIdioma();
            logInView.actualizarOpcionesIdioma();
        });
    }

    private void configurarLogin() {
        logInView.getBtnLogIn().addActionListener(e -> {
            String user = logInView.getTxtUserName().getText();
            String pass = logInView.getTxtContrasena().getText();
            Usuario usuario = usuarioDAO.autenticarUsuario(user, pass);
            if (usuario == null) {
                logInView.mostrarMensaje(
                        tipoIdioma.get("login.error.usuario_o_contrasena"),
                        tipoIdioma.get("global.error"),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (usuario.getPreguntaValidacion() == null || usuario.getPreguntaValidacion().isEmpty()) {
                int res = logInView.mostrarMensajeAlert(
                        tipoIdioma.get("login.warning.llene_preguntas_validacion"),
                        tipoIdioma.get("global.warning"),
                        JOptionPane.WARNING_MESSAGE
                );
                logInView.dispose();
                if (res == 0) {
                    PreguntasValidacionView preguntasView = new PreguntasValidacionView(usuario, usuarioDAO, tipoIdioma);
                    new PreguntasValidacionController(usuario, usuarioDAO, preguntaDAO, preguntasView, tipoIdioma);
                    preguntasView.setVisible(true);
                    preguntasView.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent evt) {
                            logInView.dispose();
                            mainAppCallback.mostrarLogin();
                        }
                    });
                }
                return;
            }

            mainAppCallback.mostrarMenuPrincipal(usuario);
            logInView.dispose();
        });
    }

    private void configurarRegistro() {
        logInView.getBtnRegister().addActionListener(e -> {
            RegisterView registerView = new RegisterView(tipoIdioma);
            new RegisterController(usuarioDAO, preguntaDAO, registerView, tipoIdioma);
            registerView.setVisible(true);
        });
    }

    private void configurarRecuperacion() {
        logInView.getBtnRecuContra().addActionListener(e -> {
            String username = logInView.getTxtUserName().getText().trim();
            if (username.isEmpty()) {
                logInView.mostrarMensaje(
                        tipoIdioma.get("login.warning.ingrese_usuario"),
                        tipoIdioma.get("global.warning"),
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            Usuario usuario = usuarioDAO.buscarUsuario(username);
            if (usuario == null) {
                logInView.mostrarMensaje(
                        tipoIdioma.get("login.error.usuario_no_encontrado"),
                        tipoIdioma.get("global.error"),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            if (usuario.getPreguntaValidacion() == null || usuario.getPreguntaValidacion().isEmpty()) {
                logInView.mostrarMensaje(
                        tipoIdioma.get("login.error.usuario_sin_preguntas"),
                        tipoIdioma.get("global.error"),
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            PreguntasValidacionView preguntasView = new PreguntasValidacionView(usuario, usuarioDAO, tipoIdioma);
            new PreguntasRecuperacionController(usuario, usuarioDAO, preguntasView, tipoIdioma);
            preguntasView.setVisible(true);
        });
    }

    private void configurarSalir() {
        logInView.getBtnExit().addActionListener(e -> System.exit(0));
    }
}