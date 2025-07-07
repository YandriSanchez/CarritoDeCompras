package ec.edu.ups.poo.controlador;

import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Pregunta;
import ec.edu.ups.poo.modelo.PreguntaUsuario;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.poo.vista.PreguntasValidacionView;

import javax.swing.*;
import java.util.*;

public class PreguntasValidacionController {

    private final Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private final PreguntasValidacionView preguntasView;
    private final List<Pregunta> preguntasRandom;
    private final MensajeInternacionalizacionHandler tipoIdioma;

    public PreguntasValidacionController(
            Usuario usuario,
            UsuarioDAO usuarioDAO,
            PreguntaDAO preguntaDAO,
            PreguntasValidacionView preguntasView,
            MensajeInternacionalizacionHandler tipoIdioma
    ) {
        this.usuario = usuario;
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;
        this.preguntasView = preguntasView;
        this.tipoIdioma = tipoIdioma;

        preguntasRandom = getPreguntasRandom();
        mostrarPreguntasEnVista();
        limpiarCampos();

        configurarEventos();
    }

    private void configurarEventos() {
        configurarEnvio();
        configurarLimpiar();
    }

    private void configurarEnvio() {
        preguntasView.getBtnEnviar().addActionListener(e -> guardarPreguntasValidacion());
    }

    private void configurarLimpiar() {
        preguntasView.getBtnClean().addActionListener(e -> limpiarCampos());
    }

    private void guardarPreguntasValidacion() {
        String respuesta1 = preguntasView.getTxtPregunta1().getText().trim();
        String respuesta2 = preguntasView.getTxtPregunta2().getText().trim();
        String respuesta3 = preguntasView.getTxtPregunta3().getText().trim();

        if (respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            preguntasView.mostrarMensaje(
                    tipoIdioma.get("preguntas.validacion.error.responder_todas"),
                    tipoIdioma.get("global.error"),
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        List<PreguntaUsuario> preguntasUsuario = new ArrayList<>();
        preguntasUsuario.add(new PreguntaUsuario(preguntasRandom.get(0), respuesta1));
        preguntasUsuario.add(new PreguntaUsuario(preguntasRandom.get(1), respuesta2));
        preguntasUsuario.add(new PreguntaUsuario(preguntasRandom.get(2), respuesta3));
        usuario.setPreguntaValidacion(preguntasUsuario);

        usuarioDAO.actualizar(usuario.getUserName(), usuario.getContrasena(), usuario.getRol());

        preguntasView.mostrarMensaje(
                tipoIdioma.get("preguntas.validacion.exito.guardado"),
                tipoIdioma.get("global.success"),
                JOptionPane.INFORMATION_MESSAGE
        );
        preguntasView.dispose();
    }

    private void mostrarPreguntasEnVista() {
        preguntasView.getLblPregunta1().setText(preguntasRandom.get(0).getTexto());
        preguntasView.getLblPregunta2().setText(preguntasRandom.get(1).getTexto());
        preguntasView.getLblPregunta3().setText(preguntasRandom.get(2).getTexto());
    }

    private void limpiarCampos() {
        preguntasView.getTxtPregunta1().setText("");
        preguntasView.getTxtPregunta2().setText("");
        preguntasView.getTxtPregunta3().setText("");
    }

    private List<Pregunta> getPreguntasRandom() {
        List<Pregunta> lista = new ArrayList<>(preguntaDAO.listarTodas());
        Collections.shuffle(lista);
        return lista.subList(0, 3);
    }
}