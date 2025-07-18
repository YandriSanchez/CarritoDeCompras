package ec.edu.ups.poo.controlador;

import ec.edu.ups.poo.dao.ProductoDAO;
import ec.edu.ups.poo.dao.CarritoDAO;
import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.modelo.Rol;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.poo.vista.MenuPrincipalView;
import ec.edu.ups.poo.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalController {

    private final Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private final ProductoDAO productoDAO;
    private final CarritoDAO carritoDAO;
    private final MensajeInternacionalizacionHandler i18n;
    private final MenuPrincipalView principalView;

    // Controladores/Vistas auxiliares
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final UsuarioController usuarioController;

    // Vistas de producto
    private final ProductoAnadirView productoAnadirView;
    private final ProductoListarView productoListaView;
    private final ProductoEditarView productoGestionView;
    private final ProductoEliminarView productoEliminarView;

    // Vistas de carrito
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoEditarView carritoEditarView;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoListarView carritoListarView;

    // Vistas de usuario
    private final UsuarioAnadirView usuarioAnadirView;
    private final UsuarioListarView usuarioListarView;
    private final UsuarioEditarView usuarioEditarView;
    private final UsuarioElimiarView usuarioElimiarView;

    private String rutaCarpetaDatos;
    private int tipoAlmacenamientoIndex;

    public MenuPrincipalController(
            Usuario usuario,
            UsuarioDAO usuarioDAO,
            PreguntaDAO preguntaDAO,
            ProductoDAO productoDAO,
            CarritoDAO carritoDAO,
            MensajeInternacionalizacionHandler i18n,
            String rutaCarpetaDatos,
            int tipoAlmacenamientoIndex
    ) {
        this.usuario = usuario;
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;
        this.productoDAO = productoDAO;
        this.carritoDAO = carritoDAO;
        this.i18n = i18n;
        this.rutaCarpetaDatos = rutaCarpetaDatos;
        this.tipoAlmacenamientoIndex = tipoAlmacenamientoIndex;

        // Instanciar vistas
        this.principalView = new MenuPrincipalView(usuario, i18n);

        this.productoAnadirView = new ProductoAnadirView(i18n);
        this.productoListaView = new ProductoListarView(i18n);
        this.productoGestionView = new ProductoEditarView(i18n);
        this.productoEliminarView = new ProductoEliminarView(i18n);

        this.carritoAnadirView = new CarritoAnadirView(i18n);
        this.carritoEditarView = new CarritoEditarView(i18n);
        this.carritoEliminarView = new CarritoEliminarView(i18n);
        this.carritoListarView = new CarritoListarView(i18n);

        this.usuarioAnadirView = new UsuarioAnadirView(i18n);
        this.usuarioListarView = new UsuarioListarView(i18n);
        this.usuarioEditarView = new UsuarioEditarView(i18n);
        this.usuarioElimiarView = new UsuarioElimiarView(i18n);

        this.usuarioController = new UsuarioController(usuarioDAO, preguntaDAO, i18n);
        this.productoController = new ProductoController(
                productoDAO,
                productoAnadirView,
                productoListaView,
                productoGestionView,
                productoEliminarView,
                carritoAnadirView,
                i18n
        );
        this.carritoController = new CarritoController(
                carritoDAO,
                productoDAO,
                carritoAnadirView,
                carritoEditarView,
                usuario,
                i18n,
                usuarioDAO
        );

        configurarPermisosPorRol();
        configurarEventos();

        principalView.setVisible(true);
    }

    private void configurarPermisosPorRol() {
        if (usuario.getRol() == Rol.USUARIO) {
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
        // Si tienes otros roles, aquí puedes agregar más lógica.
    }

    private void configurarEventos() {
        // PRODUCTOS
        principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInternalFrame(productoAnadirView);
            }
        });
        principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInternalFrame(productoListaView);
            }
        });
        principalView.getMenuItemEditarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInternalFrame(productoGestionView);
            }
        });
        principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInternalFrame(productoEliminarView);
            }
        });

        // CARRITOS
        principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInternalFrame(carritoAnadirView);
            }
        });
        principalView.getMenuItemEditarCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInternalFrame(carritoEditarView);
            }
        });
        principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoController.configurarEventosEliminar(carritoEliminarView);
                abrirInternalFrame(carritoEliminarView);
            }
        });
        principalView.getMenuItemListarCarritos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoController.configurarEventosListar(carritoListarView);
                abrirInternalFrame(carritoListarView);;
            }
        });

        // USUARIOS
        principalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioController.configurarUsuarioCrearView(usuarioAnadirView);
                abrirInternalFrame(usuarioAnadirView);
            }
        });
        principalView.getMenuItemEditarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioController.configurarUsuarioEditarView(usuarioEditarView, usuario);
                abrirInternalFrame(usuarioEditarView);
            }
        });
        principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioController.configurarUsuarioEliminarView(usuarioElimiarView);
                abrirInternalFrame(usuarioElimiarView);
            }
        });
        principalView.getMenuItemListarUsuarios().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioController.configurarUsuarioListarView(usuarioListarView);
                abrirInternalFrame(usuarioListarView);
            }
        });

        // LOGOUT
        principalView.getMenuItemLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principalView.dispose();
                SwingUtilities.invokeLater(() -> {
                    LogInView logInView = new LogInView(i18n);
                    logInView.getTxtRuta().setText(rutaCarpetaDatos);
                    logInView.getCbxUbicacionGuardar().setSelectedIndex(tipoAlmacenamientoIndex);
                    logInView.setVisible(true);
                    new LogInController(logInView, i18n);
                });
            }
        });

        // Cambios de idioma
        principalView.getMenuItemEspanol().addActionListener(this::cambiarIdiomaAEspanol);
        principalView.getMenuItemIngles().addActionListener(this::cambiarIdiomaAIngles);
        principalView.getMenuItemFrances().addActionListener(this::cambiarIdiomaAFrances);
    }

    private void abrirInternalFrame(JInternalFrame frame) {
        if (!frame.isVisible()) {
            frame.setVisible(true);
            principalView.getjDesktopPane().add(frame);
        }
    }

    private void cambiarIdiomaAEspanol(ActionEvent event) {
        i18n.setLenguaje("es", "EC");
        aplicarIdiomaATodo();
    }

    /**
     * Cambia el idioma de la aplicación a inglés y actualiza todas las vistas.
     *
     * @param event Evento de acción recibido.
     */
    private void cambiarIdiomaAIngles(ActionEvent event) {
        i18n.setLenguaje("en", "US");
        aplicarIdiomaATodo();
    }

    private void cambiarIdiomaAFrances(ActionEvent event) {
        i18n.setLenguaje("fr", "FR");
        aplicarIdiomaATodo();
    }

    private void aplicarIdiomaATodo() {
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

        carritoAnadirView.refrescarResumenValores(i18n.getLocale());
        carritoEditarView.refrescarResumenValores(i18n.getLocale());
        carritoEliminarView.refrescarResumenValores(i18n.getLocale());
        carritoListarView.refrescarResumenValores(i18n.getLocale());

        if (carritoAnadirView.isVisible()) {
            carritoAnadirView.refrescarResumenValores(i18n.getLocale());
            carritoAnadirView.refrescarTabla();
        }
        if (carritoEditarView.isVisible()) {
            carritoEditarView.refrescarResumenValores(i18n.getLocale());
            carritoEditarView.refrescarTabla();
        }
        if (carritoEliminarView.isVisible()) {
            carritoEliminarView.refrescarResumenValores(i18n.getLocale());
            carritoEliminarView.refrescarTabla();
        }
        if (carritoListarView.isVisible()) {
            carritoListarView.refrescarResumenValores(i18n.getLocale());
            carritoListarView.refrescarTabla();
        }
        if (carritoController.itemsView != null && carritoController.itemsView.isVisible()) {
            carritoController.itemsView.aplicarIdioma();
        }
    }
}