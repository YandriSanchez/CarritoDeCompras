package ec.edu.ups.poo;

import ec.edu.ups.poo.controlador.*;
import ec.edu.ups.poo.dao.CarritoDAO;
import ec.edu.ups.poo.dao.ProductoDAO;
import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.poo.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.poo.dao.impl.PreguntaDAOMemoria;
import ec.edu.ups.poo.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.poo.modelo.enums.Rol;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.poo.vista.*;
import ec.edu.ups.poo.vista.LogInView;
import ec.edu.ups.poo.vista.ProductoAnadirView;
import ec.edu.ups.poo.vista.ProductoEditarView;
import ec.edu.ups.poo.vista.ProductoEliminarView;
import ec.edu.ups.poo.vista.ProductoListarView;

public class Main {
    public static UsuarioDAO usuarioDAO;
    public static ProductoDAO productoDAO;
    public static CarritoDAO carritoDAO;
    public static PreguntaDAO preguntaDAO;
    private static UsuarioController usuarioController;
    private static MensajeInternacionalizacionHandler tipoIdioma;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            tipoIdioma = new MensajeInternacionalizacionHandler("es", "EC");
            preguntaDAO = new PreguntaDAOMemoria(tipoIdioma);
            usuarioDAO = new UsuarioDAOMemoria(preguntaDAO.listarTodas());
            productoDAO = new ProductoDAOMemoria();
            carritoDAO = new CarritoDAOMemoria();
            mostrarLogin();
        });
    }

    public static void mostrarLogin() {
        LogInView logInView = new LogInView(tipoIdioma);

        new LogInController(usuarioDAO, preguntaDAO, logInView, tipoIdioma, new LogInController.MainAppCallback() {
            @Override
            public void mostrarMenuPrincipal(Usuario usuarioAutenticado) {
                MenuPrincipalView principalView = new MenuPrincipalView(usuarioAutenticado, tipoIdioma);

                // PRODUCTO
                ProductoAnadirView productoAnadirView = new ProductoAnadirView(tipoIdioma);
                ProductoListarView productoListaView = new ProductoListarView(tipoIdioma);
                ProductoEditarView productoGestionView = new ProductoEditarView(tipoIdioma);
                ProductoEliminarView productoEliminarView = new ProductoEliminarView(tipoIdioma);

                // CARRITO
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView(tipoIdioma);
                CarritoEditarView carritoEditarView = new CarritoEditarView(tipoIdioma);
                CarritoEliminarView carritoEliminarView = new CarritoEliminarView(tipoIdioma);
                CarritoListarView carritoListarView = new CarritoListarView(tipoIdioma);

                // USUARIO
                UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView(tipoIdioma);
                UsuarioListarView usuarioListarView = new UsuarioListarView(tipoIdioma);
                UsuarioEditarView usuarioEditarView = new UsuarioEditarView(tipoIdioma);
                UsuarioElimiarView usuarioElimiarView = new UsuarioElimiarView(tipoIdioma);

                usuarioController = new UsuarioController(usuarioDAO, preguntaDAO, tipoIdioma);
                new ProductoController(productoDAO, productoAnadirView, productoListaView, productoGestionView, productoEliminarView, carritoAnadirView, tipoIdioma);
                CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, carritoAnadirView, carritoEditarView, usuarioAutenticado, tipoIdioma);

                if (usuarioAutenticado.getRol() == Rol.USUARIO) {
                    // PRODUCTOS
                    principalView.getMenuItemCrearProducto().setEnabled(false);
                    principalView.getMenuItemEditarProducto().setEnabled(false);
                    principalView.getMenuItemEliminarProducto().setEnabled(false);

                    // CARRITOS
                    principalView.getMenuItemCrearCarrito().setEnabled(true);
                    principalView.getMenuItemEditarCarrito().setEnabled(true);
                    principalView.getMenuItemEliminarCarrito().setEnabled(true);
                    principalView.getMenuItemListarCarritos().setEnabled(true);

                    // USUARIOS
                    principalView.getMenuItemCrearUsuario().setEnabled(false);
                    principalView.getMenuItemEditarUsuario().setEnabled(true);
                    principalView.getMenuItemEliminarUsuario().setEnabled(false);
                    principalView.getMenuItemListarUsuarios().setEnabled(false);

                    principalView.getMenuIdioma().setEnabled(true);
                }

                // PRODUCTOS
                principalView.getMenuItemCrearProducto().addActionListener(event -> {
                    if (!productoAnadirView.isVisible()) {
                        productoAnadirView.setVisible(true);
                        principalView.getjDesktopPane().add(productoAnadirView);
                    }
                });

                principalView.getMenuItemBuscarProducto().addActionListener(event -> {
                    if (!productoListaView.isVisible()) {
                        productoListaView.setVisible(true);
                        principalView.getjDesktopPane().add(productoListaView);
                    }
                });

                principalView.getMenuItemEditarProducto().addActionListener(event -> {
                    if (!productoGestionView.isVisible()) {
                        productoGestionView.setVisible(true);
                        principalView.getjDesktopPane().add(productoGestionView);
                    }
                });

                principalView.getMenuItemEliminarProducto().addActionListener(event -> {
                    if (!productoEliminarView.isVisible()) {
                        productoEliminarView.setVisible(true);
                        principalView.getjDesktopPane().add(productoEliminarView);
                    }
                });

                // CARRITO
                principalView.getMenuItemCrearCarrito().addActionListener(event -> {
                    if (!carritoAnadirView.isVisible()) {
                        carritoAnadirView.setVisible(true);
                        principalView.getjDesktopPane().add(carritoAnadirView);
                    }
                });

                principalView.getMenuItemEditarCarrito().addActionListener(event -> {
                    if (!carritoEditarView.isVisible()) {
                        principalView.getjDesktopPane().add(carritoEditarView);
                        carritoEditarView.setVisible(true);
                    }
                });

                principalView.getMenuItemEliminarCarrito().addActionListener(event -> {
                    if (!carritoEliminarView.isVisible()) {
                        carritoController.configurarEventosEliminar(carritoEliminarView);
                        carritoEliminarView.setVisible(true);
                        principalView.getjDesktopPane().add(carritoEliminarView);
                    }
                });

                principalView.getMenuItemListarCarritos().addActionListener(event -> {
                    if (!carritoListarView.isVisible()) {
                        principalView.getjDesktopPane().add(carritoListarView);
                        carritoListarView.setVisible(true);
                        carritoController.configurarEventosListar(carritoListarView);
                    }
                });

                // USUARIOS
                principalView.getMenuItemCrearUsuario().addActionListener(event -> {
                    if (!usuarioAnadirView.isVisible()) {
                        usuarioController.configurarUsuarioCrearView(usuarioAnadirView);
                        usuarioAnadirView.setVisible(true);
                        principalView.getjDesktopPane().add(usuarioAnadirView);
                    }
                });
                principalView.getMenuItemEditarUsuario().addActionListener(event -> {
                    if (!usuarioEditarView.isVisible()) {
                        usuarioController.configurarUsuarioEditarView(usuarioEditarView, usuarioAutenticado);
                        usuarioEditarView.setVisible(true);
                        principalView.getjDesktopPane().add(usuarioEditarView);
                    }
                });
                principalView.getMenuItemEliminarUsuario().addActionListener(event -> {
                    if (!usuarioElimiarView.isVisible()) {
                        usuarioController.configurarUsuarioEliminarView(usuarioElimiarView);
                        usuarioElimiarView.setVisible(true);
                        principalView.getjDesktopPane().add(usuarioElimiarView);
                    }
                });
                principalView.getMenuItemListarUsuarios().addActionListener(event -> {
                    if (!usuarioListarView.isVisible()) {
                        usuarioController.configurarUsuarioListarView(usuarioListarView);
                        usuarioListarView.setVisible(true);
                        principalView.getjDesktopPane().add(usuarioListarView);
                    }
                });

                principalView.getMenuItemLogout().addActionListener(ev -> {
                    principalView.dispose();
                    mostrarLogin();
                });

                principalView.getMenuItemEspanol().addActionListener(event -> {
                    tipoIdioma.setLenguaje("es", "EC");
                    principalView.aplicarIdioma();
                    productoAnadirView.aplicarIdiomas();
                    productoListaView.aplicarIdioma();
                    productoGestionView.aplicarIdiomas();
                    productoEliminarView.aplicarIdioma();
                    carritoAnadirView.aplicarIdioma();
                    carritoEditarView.aplicarIdioma();
                    carritoEliminarView.aplicarIdioma();
                    carritoListarView.aplicarIdioma();
                    usuarioAnadirView.aplicarIdioma();
                    usuarioListarView.aplicaraIdioma();
                    usuarioEditarView.aplicarIdioma();
                    usuarioElimiarView.aplicarIdioma();

                    carritoAnadirView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoEditarView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoEliminarView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoListarView.refrescarResumenValores(tipoIdioma.getLocale());

                    if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
                        carritoAnadirView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoAnadirView.refrescarTabla();
                    }
                    if (carritoEditarView != null && carritoEditarView.isVisible()) {
                        carritoEditarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoEditarView.refrescarTabla();
                    }
                    if (carritoEliminarView != null && carritoEliminarView.isVisible()) {
                        carritoEliminarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoEliminarView.refrescarTabla();
                    }
                    if (carritoListarView != null && carritoListarView.isVisible()) {
                        carritoListarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoListarView.refrescarTabla();
                    }
                    if (carritoController.itemsView != null && carritoController.itemsView.isVisible()) {
                        carritoController.itemsView.aplicarIdioma();
                    }
                });
                principalView.getMenuItemIngles().addActionListener(event -> {
                    tipoIdioma.setLenguaje("en", "US");
                    principalView.aplicarIdioma();
                    productoAnadirView.aplicarIdiomas();
                    productoListaView.aplicarIdioma();
                    productoGestionView.aplicarIdiomas();
                    productoEliminarView.aplicarIdioma();
                    carritoAnadirView.aplicarIdioma();
                    carritoEditarView.aplicarIdioma();
                    carritoEliminarView.aplicarIdioma();
                    carritoListarView.aplicarIdioma();
                    usuarioAnadirView.aplicarIdioma();
                    usuarioListarView.aplicaraIdioma();
                    usuarioEditarView.aplicarIdioma();
                    usuarioElimiarView.aplicarIdioma();

                    carritoAnadirView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoEditarView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoEliminarView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoListarView.refrescarResumenValores(tipoIdioma.getLocale());

                    if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
                        carritoAnadirView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoAnadirView.refrescarTabla();
                    }
                    if (carritoEditarView != null && carritoEditarView.isVisible()) {
                        carritoEditarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoEditarView.refrescarTabla();
                    }
                    if (carritoEliminarView != null && carritoEliminarView.isVisible()) {
                        carritoEliminarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoEliminarView.refrescarTabla();
                    }
                    if (carritoListarView != null && carritoListarView.isVisible()) {
                        carritoListarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoListarView.refrescarTabla();
                    }
                    if (carritoController.itemsView != null && carritoController.itemsView.isVisible()) {
                        carritoController.itemsView.aplicarIdioma();
                    }
                });
                principalView.getMenuItemFrances().addActionListener(event -> {
                    tipoIdioma.setLenguaje("fr", "FR");
                    principalView.aplicarIdioma();
                    productoAnadirView.aplicarIdiomas();
                    productoListaView.aplicarIdioma();
                    productoGestionView.aplicarIdiomas();
                    productoEliminarView.aplicarIdioma();
                    carritoAnadirView.aplicarIdioma();
                    carritoEditarView.aplicarIdioma();
                    carritoEliminarView.aplicarIdioma();
                    carritoListarView.aplicarIdioma();
                    usuarioAnadirView.aplicarIdioma();
                    usuarioListarView.aplicaraIdioma();
                    usuarioEditarView.aplicarIdioma();
                    usuarioElimiarView.aplicarIdioma();

                    carritoAnadirView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoEditarView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoEliminarView.refrescarResumenValores(tipoIdioma.getLocale());
                    carritoListarView.refrescarResumenValores(tipoIdioma.getLocale());

                    if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
                        carritoAnadirView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoAnadirView.refrescarTabla();
                    }
                    if (carritoEditarView != null && carritoEditarView.isVisible()) {
                        carritoEditarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoEditarView.refrescarTabla();
                    }
                    if (carritoEliminarView != null && carritoEliminarView.isVisible()) {
                        carritoEliminarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoEliminarView.refrescarTabla();
                    }
                    if (carritoListarView != null && carritoListarView.isVisible()) {
                        carritoListarView.refrescarResumenValores(tipoIdioma.getLocale());
                        carritoListarView.refrescarTabla();
                    }
                    if (carritoController.itemsView != null && carritoController.itemsView.isVisible()) {
                        carritoController.itemsView.aplicarIdioma();
                    }
                });

                principalView.setVisible(true);

            }

            @Override
            public void mostrarLogin() {
                Main.mostrarLogin();
            }
        });

        logInView.setVisible(true);
    }

}