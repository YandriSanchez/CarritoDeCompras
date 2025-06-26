package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final RegisterView registerView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, RegisterView registerView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.registerView = registerView;
        this.usuario = null;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas(){
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.setVisible(true);
                loginView.setVisible(false);
            }
        });

        registerView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarNuevoUsuario();
            }
        });

        registerView.getBtnRegresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.dispose();
                loginView.setVisible(true);
            }
        });

    }

    private void registrarNuevoUsuario() {
        String username = registerView.getTxtUsuario().getText().trim();
        String contrasenia = registerView.getTxtContrasena().getText().trim();
        String rolSeleccionado = (String) registerView.getCBoxRol().getSelectedItem();

        if (username.isEmpty() || contrasenia.isEmpty() || rolSeleccionado == null) {
            JOptionPane.showMessageDialog(registerView, "Todos los campos son obligatorios.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Rol rol;
        try {
            rol = Rol.valueOf(rolSeleccionado.toUpperCase());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(registerView, "Rol inválido seleccionado.", "Error de rol", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            JOptionPane.showMessageDialog(registerView, "El usuario ya existe.", "Duplicado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(username, contrasenia, rol);
        usuarioDAO.crear(nuevoUsuario);

        JOptionPane.showMessageDialog(registerView, "Usuario registrado exitosamente.", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
        registerView.dispose();
        loginView.setVisible(true);
    }



    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }else{
            loginView.dispose();
        }
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}
