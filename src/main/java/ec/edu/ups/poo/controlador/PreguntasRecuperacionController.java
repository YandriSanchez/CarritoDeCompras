package ec.edu.ups.poo.controlador;

import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.PreguntaUsuario;
import ec.edu.ups.poo.vista.PreguntasValidacionView;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PreguntasRecuperacionController {

    private final Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final PreguntasValidacionView preguntasView;
    private final MensajeInternacionalizacionHandler tipoIdioma;
    private boolean estado;

    public PreguntasRecuperacionController(
            Usuario usuario,
            UsuarioDAO usuarioDAO,
            PreguntasValidacionView preguntasView,
            MensajeInternacionalizacionHandler tipoIdioma
    ) {
        this.usuario = usuario;
        this.usuarioDAO = usuarioDAO;
        this.preguntasView = preguntasView;
        this.tipoIdioma = tipoIdioma;
        this.estado = false;

        inicializarVista();
        configurarEventos();
    }

    private void inicializarVista() {
        List<PreguntaUsuario> preguntasGuardadas = usuario.getPreguntaValidacion();

        preguntasView.getLblPregunta1().setText(preguntasGuardadas.get(0).getPregunta().getTexto());
        preguntasView.getLblPregunta2().setText(preguntasGuardadas.get(1).getPregunta().getTexto());
        preguntasView.getLblPregunta3().setText(preguntasGuardadas.get(2).getPregunta().getTexto());

        preguntasView.getTxtPregunta1().setText("");
        preguntasView.getTxtPregunta2().setText("");
        preguntasView.getTxtPregunta3().setText("");

        preguntasView.getLblNuevaContra().setVisible(false);
        preguntasView.getTxtNuevaContra().setVisible(false);
        preguntasView.getTxtNuevaContra().setEnabled(false);
    }

    private void configurarEventos() {
        configurarEnvioPreguntas();
        configurarLimpiar();
    }

    private void configurarEnvioPreguntas() {
        preguntasView.getBtnEnviar().addActionListener(e -> procesarPreguntas());
    }

    private void configurarLimpiar() {
        preguntasView.getBtnClean().addActionListener(e -> limpiarCampos());
    }

    private void procesarPreguntas() {
        List<PreguntaUsuario> preguntasGuardadas = usuario.getPreguntaValidacion();

        String respuesta1 = preguntasView.getTxtPregunta1().getText().trim();
        String respuesta2 = preguntasView.getTxtPregunta2().getText().trim();
        String respuesta3 = preguntasView.getTxtPregunta3().getText().trim();

        if (respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            preguntasView.mostrarMensaje(
                    tipoIdioma.get("preguntas.recuperacion.error.responder_todas"),
                    tipoIdioma.get("global.error"),
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        boolean correcto =
                preguntasGuardadas.get(0).getRespuesta().equalsIgnoreCase(respuesta1) &&
                        preguntasGuardadas.get(1).getRespuesta().equalsIgnoreCase(respuesta2) &&
                        preguntasGuardadas.get(2).getRespuesta().equalsIgnoreCase(respuesta3);

        if (correcto) {
            preguntasView.mostrarMensaje(
                    tipoIdioma.get("preguntas.recuperacion.exito.respuestas_correctas"),
                    tipoIdioma.get("global.success"),
                    JOptionPane.INFORMATION_MESSAGE
            );
            mostrarCambioContrasena();
        } else {
            preguntasView.mostrarMensaje(
                    tipoIdioma.get("preguntas.recuperacion.error.respuesta_incorrecta"),
                    tipoIdioma.get("global.error"),
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarCambioContrasena() {
        estado = true;
        preguntasView.getLblNuevaContra().setVisible(true);
        preguntasView.getLblNuevaContra().setEnabled(true);
        preguntasView.getTxtNuevaContra().setVisible(true);
        preguntasView.getTxtNuevaContra().setEnabled(true);
        preguntasView.getTxtNuevaContra().setEditable(true);
        preguntasView.getTxtPregunta1().setEnabled(false);
        preguntasView.getTxtPregunta2().setEnabled(false);
        preguntasView.getTxtPregunta3().setEnabled(false);

        preguntasView.getBtnEnviar().setText(tipoIdioma.get("preguntas.recuperacion.btn.cambiar_contrasena"));

        // Limpiar y agregar solo el listener de cambio de contraseña
        for (ActionListener al : preguntasView.getBtnEnviar().getActionListeners()) {
            preguntasView.getBtnEnviar().removeActionListener(al);
        }
        preguntasView.getBtnEnviar().addActionListener(ev -> cambiarContrasena());
    }

    private void cambiarContrasena() {
        String nuevaContrasena = preguntasView.getTxtNuevaContra().getText().trim();
        if (nuevaContrasena.isEmpty()) {
            preguntasView.mostrarMensaje(
                    tipoIdioma.get("preguntas.recuperacion.error.nueva_contrasena_vacia"),
                    tipoIdioma.get("global.warning"),
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        usuario.setContrasena(nuevaContrasena);
        usuarioDAO.actualizar(usuario.getUserName(), usuario.getContrasena(), usuario.getRol());
        preguntasView.mostrarMensaje(
                tipoIdioma.get("preguntas.recuperacion.exito.cambio_contrasena"),
                tipoIdioma.get("global.success"),
                JOptionPane.INFORMATION_MESSAGE
        );
        preguntasView.dispose();
    }

    private void limpiarCampos() {
        if (!estado){
            preguntasView.getTxtPregunta1().setText("");
            preguntasView.getTxtPregunta2().setText("");
            preguntasView.getTxtPregunta3().setText("");
        } else {
            preguntasView.getTxtNuevaContra().setText("");
        }
    }
}