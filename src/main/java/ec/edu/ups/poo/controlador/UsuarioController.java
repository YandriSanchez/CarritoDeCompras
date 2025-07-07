package ec.edu.ups.poo.controlador;

import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.modelo.enums.Rol;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Pregunta;
import ec.edu.ups.poo.modelo.PreguntaUsuario;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.poo.vista.UsuarioAnadirView;
import ec.edu.ups.poo.vista.UsuarioEditarView;
import ec.edu.ups.poo.vista.UsuarioElimiarView;
import ec.edu.ups.poo.vista.UsuarioListarView;

import javax.swing.*;
import java.util.*;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private final MensajeInternacionalizacionHandler i18n;

    public UsuarioController(UsuarioDAO usuarioDAO, PreguntaDAO preguntaDAO, MensajeInternacionalizacionHandler i18n) {
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;
        this.i18n = i18n;
    }

    public Usuario autenticarUsuario(String username, String contrasena) {
        return usuarioDAO.autenticarUsuario(username, contrasena);
    }

    public boolean registrarUsuario(
            String username,
            String password,
            Rol rol,
            String nombreCompleto,
            Date fechaNacimiento,
            String correo,
            String telefono,
            List<String> respuestas
    ) {
        if (usuarioDAO.buscarUsuario(username) != null)
            return false;
        List<Pregunta> preguntas = obtenerPreguntasRandom();
        List<PreguntaUsuario> preguntasUsuario = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            preguntasUsuario.add(new PreguntaUsuario(preguntas.get(i), respuestas.get(i)));
        }
        Usuario usuario = new Usuario(username, password, rol, nombreCompleto, fechaNacimiento, correo, telefono);
        usuario.setPreguntaValidacion(preguntasUsuario);
        usuarioDAO.crearUsuario(usuario);
        return true;
    }

    public List<Pregunta> obtenerPreguntasRandom() {
        List<Pregunta> preguntas = preguntaDAO.listarTodas();
        Collections.shuffle(preguntas);
        return preguntas.subList(0, 3);
    }

    public void configurarUsuarioCrearView(UsuarioAnadirView view) {
        JComboBox cbxRol = view.getCbxRol();
        llenarComboRoles(cbxRol);

        view.getBtnRegistrar().addActionListener(e -> registrarUsuarioDesdeView(view, cbxRol));
        view.getBtnClean().addActionListener(e -> limpiarCamposCrear(view, cbxRol));
    }

    private void registrarUsuarioDesdeView(UsuarioAnadirView view, JComboBox cbxRol) {
        String username = view.getTxtUsuario().getText().trim();
        String password = view.getTxtContrasena().getText().trim();
        Rol rol = (Rol) cbxRol.getSelectedItem();
        String nombreCompleto = view.getTxtNombreCompleto().getText().trim();
        Date fechaNacimiento = view.getFechaNacimiento();
        String correo = view.getTxtCorreo().getText().trim();
        String telefono = view.getTxtTelefono().getText().trim();

        if (camposVacios(username, password, nombreCompleto, fechaNacimiento, correo, telefono, rol)) {
            view.mostrarMensaje(i18n.get("usuario.error.campos_obligatorios"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!correo.contains("@") || !correo.contains(".")) {
            view.mostrarMensaje(i18n.get("register.error.correo"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!telefono.matches("\\d{10,}")) {
            view.mostrarMensaje(i18n.get("register.error.telefono"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (usuarioDAO.buscarUsuario(username) != null) {
            view.mostrarMensaje(i18n.get("usuario.error.existe"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        Usuario nuevoUsuario = new Usuario(username, password, rol, nombreCompleto, fechaNacimiento, correo, telefono);
        usuarioDAO.crearUsuario(nuevoUsuario);
        view.mostrarMensaje(i18n.get("usuario.exito.creado"), i18n.get("global.success"), JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposCrear(view, cbxRol);
    }

    public void configurarUsuarioEditarView(UsuarioEditarView view, Usuario usuarioAutenticado) {
        JComboBox cbxRol = view.getCbxRol();
        llenarComboRoles(cbxRol);

        if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
            setCamposUsuarioAutenticado(view, usuarioAutenticado, cbxRol);
        } else {
            view.getTxtUsuario().setEditable(true);
            view.getBtnBuscar().setEnabled(true);
            view.getBtnBuscar().addActionListener(e -> buscarUsuarioParaEditar(view, cbxRol));
        }

        view.getBtnActualizar().addActionListener(e -> actualizarUsuarioDesdeView(view, cbxRol));
        view.getBtnClean().addActionListener(e -> limpiarCamposEditar(view, cbxRol));
    }

    private void setCamposUsuarioAutenticado(UsuarioEditarView view, Usuario usuarioAutenticado, JComboBox cbxRol) {
        view.getTxtUsuario().setText(usuarioAutenticado.getUserName());
        view.getTxtUsuario().setEditable(false);
        view.getTxtContrasena().setText(usuarioAutenticado.getContrasena());
        view.getTxtNombreCompleto().setText(usuarioAutenticado.getNombreCompleto());

        Date fecha = usuarioAutenticado.getFechaNacimiento();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);

        view.getCbxDia().setSelectedItem(String.valueOf(dia));
        view.getCbxMes().setSelectedItem(String.valueOf(mes));
        view.getCbxAnio().setSelectedItem(String.valueOf(anio));

        view.getTxtCorreo().setText(usuarioAutenticado.getCorreo());
        view.getTxtTelefono().setText(usuarioAutenticado.getTelefono());
        cbxRol.setSelectedItem(usuarioAutenticado.getRol());
        cbxRol.setEnabled(false);

        view.getBtnBuscar().setEnabled(false);
    }

    private void buscarUsuarioParaEditar(UsuarioEditarView view, JComboBox cbxRol) {
        String usernameBuscar = view.getTxtUsuario().getText().trim();
        if (usernameBuscar.isEmpty()) {
            view.mostrarMensaje(i18n.get("usuario.error.ingrese_usuario_buscar"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        Usuario usuarioEncontrado = usuarioDAO.buscarUsuario(usernameBuscar);
        if (usuarioEncontrado == null) {
            view.mostrarMensaje(i18n.get("usuario.error.no_encontrado"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            limpiarCamposEditar(view, cbxRol);
        } else {
            setCamposUsuarioAutenticado(view, usuarioEncontrado, cbxRol);
            view.getTxtUsuario().setEditable(false);
            cbxRol.setEnabled(true);
        }
    }

    private void actualizarUsuarioDesdeView(UsuarioEditarView view, JComboBox cbxRol) {
        String username = view.getTxtUsuario().getText().trim();
        String password = view.getTxtContrasena().getText().trim();
        String correo = view.getTxtCorreo().getText().trim();
        String telefono = view.getTxtTelefono().getText().trim();
        String nombreCompleto = view.getTxtNombreCompleto().getText().trim();
        Date fechaNacimiento = view.getFechaNacimiento();
        Rol rol = (Rol) cbxRol.getSelectedItem();

        if (camposVacios(username, password, nombreCompleto, fechaNacimiento, correo, telefono, rol)) {
            view.mostrarMensaje(i18n.get("usuario.error.campos_obligatorios"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        Usuario usuarioExistente = usuarioDAO.buscarUsuario(username);
        if (usuarioExistente == null) {
            view.mostrarMensaje(i18n.get("usuario.error.no_existe"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        usuarioExistente.setContrasena(password);
        usuarioExistente.setCorreo(correo);
        usuarioExistente.setTelefono(telefono);
        usuarioExistente.setRol(rol);
        usuarioDAO.actualizar(username, password, rol);
        view.mostrarMensaje(i18n.get("usuario.exito.actualizado"), i18n.get("global.success"), JOptionPane.INFORMATION_MESSAGE);
    }

    public void configurarUsuarioEliminarView(UsuarioElimiarView view) {
        view.getBtnBuscar().addActionListener(e -> buscarUsuarioParaEliminar(view));
        view.getBtnEliminar().addActionListener(e -> eliminarUsuarioDesdeView(view));
    }

    private void buscarUsuarioParaEliminar(UsuarioElimiarView view) {
        String username = view.getTxtUsuario().getText().trim();
        if (username.isEmpty()) {
            view.mostrarMensaje(i18n.get("usuario.error.ingrese_usuario_eliminar"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        Usuario usuarioEncontrado = usuarioDAO.buscarUsuario(username);
        if (usuarioEncontrado == null) {
            view.mostrarMensaje(i18n.get("usuario.error.no_encontrado"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            limpiarCamposEliminar(view);
            view.getBtnEliminar().setEnabled(false);
        } else {
            setCamposUsuarioEliminar(view, usuarioEncontrado);
            view.getTxtUsuario().setEditable(false);
            view.getBtnEliminar().setEnabled(true);
        }
    }

    private void setCamposUsuarioEliminar(UsuarioElimiarView view, Usuario usuario) {
        view.getTxtContrasena().setText(usuario.getContrasena());
        view.getTxtNombreCompleto().setText(usuario.getNombreCompleto());
        Date fecha = usuario.getFechaNacimiento();
        view.getTxtDia().setText(String.valueOf(fecha.getDate()));
        view.getTxtMes().setText(String.valueOf(fecha.getMonth() + 1));
        view.getTxtAnio().setText(String.valueOf(fecha.getYear() + 1900));
        view.getTxtCorreo().setText(usuario.getCorreo());
        view.getTxtTelefono().setText(usuario.getTelefono());
        view.getTxtRol().setText(usuario.getRol().toString());
    }

    private void eliminarUsuarioDesdeView(UsuarioElimiarView view) {
        String username = view.getTxtUsuario().getText().trim();
        if (username.isEmpty()) {
            view.mostrarMensaje(i18n.get("usuario.error.no_usuario_eliminar"), i18n.get("global.error"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirmacion = view.mostrarMensajeConfirmacion(i18n.get("usuario.confirmacion.eliminar"), i18n.get("global.confirm"), JOptionPane.WARNING_MESSAGE);
        if (confirmacion == 0) {
            usuarioDAO.eliminarUsuario(username);
            view.mostrarMensaje(i18n.get("usuario.exito.eliminado"), i18n.get("global.success"), JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposEliminar(view);
            view.getTxtUsuario().setEditable(true);
            view.getBtnEliminar().setEnabled(false);
        }
    }

    public void configurarUsuarioListarView(UsuarioListarView view) {
        view.getBtnListar().addActionListener(e -> listarUsuarios(view));
        view.getBtnBuscar().addActionListener(e -> buscarUsuarios(view));
    }

    private void listarUsuarios(UsuarioListarView view) {
        List<Usuario> usuarios = usuarioDAO.listarUsuariosTodos();
        view.mostrarUsuarios(usuarios);
    }

    private void buscarUsuarios(UsuarioListarView view) {
        String username = view.getTxtUsuario().getText().trim();
        Object selectedRol = view.getCbxRol().getSelectedItem();

        boolean isTodos = selectedRol instanceof String && "Todos".equals(selectedRol);

        if (!username.isEmpty()) {
            Usuario usuario = usuarioDAO.buscarUsuario(username);
            if (usuario != null) {
                view.mostrarUsuarios(List.of(usuario));
            } else {
                view.mostrarUsuarios(List.of());
            }
        } else if (!isTodos) {
            Rol rol = (Rol) selectedRol;
            view.mostrarUsuarios(usuarioDAO.buscarUsuariosPorRol(rol));
        } else {
            view.mostrarUsuarios(usuarioDAO.listarUsuariosTodos());
        }
    }

    // UTILS

    private void llenarComboRoles(JComboBox cbxRol) {
        cbxRol.removeAllItems();
        for (Rol rol : Rol.values()) {
            cbxRol.addItem(rol);
        }
    }

    private void limpiarCamposCrear(UsuarioAnadirView view, JComboBox cbxRol) {
        view.getTxtUsuario().setText("");
        view.getTxtContrasena().setText("");
        view.getTxtCorreo().setText("");
        view.getTxtTelefono().setText("");
        view.getTxtNombreCompleto().setText("");
        view.getCbxDia().setSelectedIndex(0);
        view.getCbxMes().setSelectedIndex(0);
        view.getCbxAnio().setSelectedIndex(0);
        if (cbxRol.getItemCount() > 0) cbxRol.setSelectedIndex(0);
    }

    private void limpiarCamposEditar(UsuarioEditarView view, JComboBox cbxRol) {
        view.getTxtUsuario().setText("");
        view.getTxtContrasena().setText("");
        view.getTxtCorreo().setText("");
        view.getTxtTelefono().setText("");
        view.getTxtNombreCompleto().setText("");
        view.getCbxDia().setSelectedIndex(0);
        view.getCbxMes().setSelectedIndex(0);
        view.getCbxAnio().setSelectedIndex(0);
        if (cbxRol.getItemCount() > 0) cbxRol.setSelectedIndex(0);
        view.getTxtUsuario().setEditable(true);
    }

    private void limpiarCamposEliminar(UsuarioElimiarView view) {
        view.getTxtUsuario().setText("");
        view.getTxtContrasena().setText("");
        view.getTxtCorreo().setText("");
        view.getTxtTelefono().setText("");
        view.getTxtRol().setText("");
        view.getTxtDia().setText("");
        view.getTxtMes().setText("");
        view.getTxtAnio().setText("");
        view.getTxtNombreCompleto().setText("");
    }

    private boolean camposVacios(String username, String pass, String nombreCompleto, Date fechaNacimiento, String correo, String telefono, Rol rol) {
        return username.isEmpty()
                || pass.isEmpty()
                || nombreCompleto.isEmpty()
                || fechaNacimiento == null
                || correo.isEmpty()
                || telefono.isEmpty()
                || rol == null;
    }
}