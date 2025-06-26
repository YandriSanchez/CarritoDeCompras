package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            LoginView loginView = new LoginView();
            RegisterView registerView = new RegisterView();
            loginView.setVisible(true);

            UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, registerView);

            loginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowDeactivated(WindowEvent e) {
                    Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                    if (usuarioAutenticado != null && !loginView.isVisible()) {
                        lanzarMenu(usuarioAutenticado, usuarioDAO, loginView);
                        loginView.dispose();
                    }
                }
            });
        });
    }

    private static void lanzarMenu(Usuario usuario, UsuarioDAO usuarioDAO, LoginView loginView) {
        ProductoDAO productoDAO = new ProductoDAOMemoria();
        CarritoDAO carritoDAO = new CarritoDAOMemoria();

        MenuPrincipalView principalView = new MenuPrincipalView();
        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
        ProductoListaView productoListaView = new ProductoListaView();
        CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
        ProductoEliminarView productoEliminarView = new ProductoEliminarView();

        new ProductoController(productoDAO, productoAnadirView, productoListaView, carritoAnadirView,
                productoEliminarView);
        new CarritoController(carritoDAO, productoDAO, carritoAnadirView);

        principalView.mostrarMensaje("Bienvenido: " + usuario.getUsername());
        if (usuario.getRol().equals(Rol.USUARIO)) {
            principalView.deshabilitarMenusAdministrador();
        }

        principalView.getMenuItemCrearProducto().addActionListener(e -> {
            if (!productoAnadirView.isVisible()) {
                productoAnadirView.setVisible(true);
                principalView.getjDesktopPane().add(productoAnadirView);
            }
        });

        principalView.getMenuItemEliminarProducto().addActionListener(e -> {
            if (!productoEliminarView.isVisible()) {
                productoEliminarView.setVisible(true);
                principalView.getjDesktopPane().add(productoEliminarView);
            }
        });

        principalView.getMenuItemBuscarProducto().addActionListener(e -> {
            if (!productoListaView.isVisible()) {
                productoListaView.setVisible(true);
                principalView.getjDesktopPane().add(productoListaView);
            }
        });

        principalView.getMenuItemCrearCarrito().addActionListener(e -> {
            if (!carritoAnadirView.isVisible()) {
                carritoAnadirView.setVisible(true);
                principalView.getjDesktopPane().add(carritoAnadirView);
            }
        });

        principalView.getMenuItemCerrarSesion().addActionListener(ev -> {
            principalView.dispose();

            LoginView nuevoLoginView = new LoginView();
            RegisterView nuevoRegisterView = new RegisterView();
            UsuarioController nuevoController = new UsuarioController(usuarioDAO, nuevoLoginView, nuevoRegisterView);

            nuevoLoginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowDeactivated(WindowEvent e) {
                    Usuario nuevoUsuario = nuevoController.getUsuarioAutenticado();
                    if (nuevoUsuario != null && !nuevoLoginView.isVisible()) {
                        lanzarMenu(nuevoUsuario, usuarioDAO, nuevoLoginView);
                        nuevoLoginView.dispose();
                    }
                }
            });

            nuevoLoginView.setVisible(true);
        });
    }
}
