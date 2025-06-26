package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuCuenta;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;
    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemCerrarSesion;
    private JDesktopPane jDesktopPane;

    public MenuPrincipalView() {
        jDesktopPane = new JDesktopPane();
        jDesktopPane.setBackground(Color.BLUE);

        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");
        menuCuenta = new JMenu("Cuenta");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");
        menuItemCrearCarrito = new JMenuItem("Crear Carrito");
        menuItemCerrarSesion = new JMenuItem("Cerrar Sesion");

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);

        menuCuenta.add(menuItemCerrarSesion);

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuCuenta);
        setJMenuBar(menuBar);

        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("YANDRI STORE");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }


    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public void setMenuItemActualizarProducto(JMenuItem menuItemActualizarProducto) {
        this.menuItemActualizarProducto = menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public void setMenuItemCrearCarrito(JMenuItem menuItemCrearCarrito) {
        this.menuItemCrearCarrito = menuItemCrearCarrito;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemActualizarProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
    }
}
