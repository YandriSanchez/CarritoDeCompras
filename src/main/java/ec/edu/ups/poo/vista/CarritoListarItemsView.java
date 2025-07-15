package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.ItemCarrito;
import ec.edu.ups.poo.modelo.enums.Rol;
import ec.edu.ups.poo.util.FormateadorUtils;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

public class CarritoListarItemsView extends JInternalFrame {
    private JPanel panelTitle;
    private JLabel lblTitulo;
    private JPanel panelProduct;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblUsuario;
    private JTextField txtRolUser;
    private JTextField txtFecha;
    private JLabel lblFecha;
    private JPanel panelItems;
    private JScrollPane scroll;
    private JTable tblProducts;
    private JLabel lblSubtotal;
    private JLabel lblva;
    private JTextField txtIva;
    private JLabel lblTotal;
    private JTextField txtTotal;
    private JTextField txtSubTotal;
    private JPanel panelInferior;
    private JButton btnSalir;
    private JPanel panelAll;
    private JTextField txtUsuario;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler i18n;
    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;
    private List<ItemCarrito> currentItems;


    public CarritoListarItemsView(
            int carritoId,
            List<ItemCarrito> items,
            double subtotal,
            double iva,
            double total,
            String username,
            Rol rol,
            String fecha,
            MensajeInternacionalizacionHandler i18n){

        this.i18n = i18n;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.currentItems = items;

        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(465, 485);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mostrarDatos(carritoId, items, subtotal, iva, total, username, rol, fecha);
        cargarTabla();
        aplicarIdioma();
    }

    public void mostrarDatos(
            int carritoId,
            List<ItemCarrito> items,
            double subtotal,
            double iva,
            double total,
            String username,
            Rol rol,
            String fecha
    ) {
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.currentItems = items;
        Locale locale = i18n.getLocale();

        if (modelo != null) {
            modelo.setRowCount(0);
            if (items != null) {
                for (ItemCarrito item : items) {
                    modelo.addRow(new Object[]{
                            item.getProducto().getCodigo(),
                            item.getProducto().getNombre(),
                            FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                            item.getCantidad(),
                            FormateadorUtils.formatearMoneda(item.getTotalItem(), locale)
                    });
                }
            }
        }
        if (txtSubTotal != null) txtSubTotal.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        if (txtIva != null) txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        if (txtTotal != null) txtTotal.setText(FormateadorUtils.formatearMoneda(total, locale));
        if (txtCodigo != null) txtCodigo.setText(String.valueOf(carritoId));
        if (txtUsuario != null) txtUsuario.setText(username);
        if (txtRolUser != null && rol != null) txtRolUser.setText(rol.toString());
        if (txtFecha != null) txtFecha.setText(fecha);
    }

    public void cargarTabla() {
        modelo = new DefaultTableModel(new Object[]{"Item", "Nombre", "Precio", "Cantidad", "Total Item"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProducts.setModel(modelo);

        Color fondo = new Color(132, 148, 229);
        Color letras = Color.BLACK;

        if (scroll == null && tblProducts != null) {
            scroll = (JScrollPane) tblProducts.getParent().getParent();
        }
        if (scroll != null) {
            scroll.getViewport().setBackground(fondo);
            scroll.setBackground(fondo);
        }

        if (tblProducts != null) {
            tblProducts.setBackground(fondo);
            tblProducts.setForeground(letras);
            tblProducts.setSelectionBackground(new Color(50, 50, 60));
            tblProducts.setSelectionForeground(Color.WHITE);
            tblProducts.setGridColor(fondo);

            JTableHeader header = tblProducts.getTableHeader();
            header.setBackground(fondo);
            header.setForeground(letras);
            header.setFont(header.getFont().deriveFont(Font.BOLD));
            ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setForeground(letras);
            centerRenderer.setBackground(fondo);
            for (int i = 0; i < tblProducts.getColumnCount(); i++) {
                tblProducts.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public void aplicarIdioma() {
        setTitle(i18n.get("carrito.listar.item.titulo"));
        lblTitulo.setText(i18n.get("carrito.listar.item.lbl.titulo"));
        lblCodigo.setText(i18n.get("carrito.listar.item.lbl.codigo"));
        lblUsuario.setText(i18n.get("carrito.listar.item.lbl.usuario"));
        btnSalir.setText(i18n.get("carrito.listar.item.btn.salir"));
        lblFecha.setText(i18n.get("carrito.listar.item.lbl.fecha"));

        tblProducts.getColumnModel().getColumn(0).setHeaderValue(i18n.get("carrito.listar.item..tbl.codigo"));
        tblProducts.getColumnModel().getColumn(1).setHeaderValue(i18n.get("carrito.listar.item..tbl.nombre"));
        tblProducts.getColumnModel().getColumn(2).setHeaderValue(i18n.get("carrito.listar.item.tbl.precio"));
        tblProducts.getColumnModel().getColumn(3).setHeaderValue(i18n.get("carrito.listar.item.tbl.cantidad"));
        tblProducts.getColumnModel().getColumn(4).setHeaderValue(i18n.get("carrito.listar.item.tbl.totalItem"));
        tblProducts.getTableHeader().repaint();

        refrescarFormatoMoneda();
    }

    public void refrescarFormatoMoneda() {
        Locale locale = i18n.getLocale();
        if (modelo != null) {
            modelo.setRowCount(0);
            if (currentItems != null) {
                for (ItemCarrito item : currentItems) {
                    modelo.addRow(new Object[]{
                            item.getProducto().getCodigo(),
                            item.getProducto().getNombre(),
                            FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                            item.getCantidad(),
                            FormateadorUtils.formatearMoneda(item.getTotalItem(), locale)
                    });
                }
            }
        }
        if (txtSubTotal != null) txtSubTotal.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        if (txtIva != null) txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        if (txtTotal != null) txtTotal.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    public JPanel getPanelTitle() {
        return panelTitle;
    }
    public void setPanelTitle(JPanel panelTitle) {
        this.panelTitle = panelTitle;
    }
    public JLabel getLblTitulo() {
        return lblTitulo;
    }
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
    public JPanel getPanelProduct() {
        return panelProduct;
    }
    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }
    public JLabel getLblCodigo() {
        return lblCodigo;
    }
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }
    public JLabel getLblUsuario() {
        return lblUsuario;
    }
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }
    public JTextField getTxtRolUser() {
        return txtRolUser;
    }
    public void setTxtRolUser(JTextField txtRolUser) {
        this.txtRolUser = txtRolUser;
    }
    public JTextField getTxtFecha() {
        return txtFecha;
    }
    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }
    public JLabel getLblFecha() {
        return lblFecha;
    }
    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }
    public JPanel getPanelItems() {
        return panelItems;
    }
    public void setPanelItems(JPanel panelItems) {
        this.panelItems = panelItems;
    }
    public JScrollPane getScroll() {
        return scroll;
    }
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }
    public JTable getTblProducts() {
        return tblProducts;
    }
    public void setTblProducts(JTable tblProducts) {
        this.tblProducts = tblProducts;
    }
    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }
    public void setLblSubtotal(JLabel lblSubtotal) {
        this.lblSubtotal = lblSubtotal;
    }
    public JLabel getLblva() {
        return lblva;
    }
    public void setLblva(JLabel lblva) {
        this.lblva = lblva;
    }
    public JTextField getTxtIva() {
        return txtIva;
    }
    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }
    public JLabel getLblTotal() {
        return lblTotal;
    }
    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }
    public JTextField getTxtTotal() {
        return txtTotal;
    }
    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }
    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }
    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }
    public JPanel getPanelInferior() {
        return panelInferior;
    }
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }
    public JButton getBtnSalir() {
        return btnSalir;
    }
    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }
    public JPanel getPanelAll() {
        return panelAll;
    }
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }
    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }
    public DefaultTableModel getModelo() {
        return modelo;
    }
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

}