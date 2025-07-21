package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.ItemCarrito;
import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.util.FormateadorUtils;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import java.util.Locale;

/**
 * Vista para editar un carrito de compras.
 * Permite añadir, actualizar, eliminar productos, mostrar resumen de valores e internacionalización.
 */
public class CarritoEditarView extends JInternalFrame {

    // Atributos
    private JPanel panelAll;
    private JTextField txtCodigoProducto;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTable tblProducts;
    private JButton btnAnadir;
    private JComboBox cbxCantidad;
    private JButton btnActualizar;
    private JLabel txtTotal;
    private JLabel txtShoppingCart;
    private JLabel lblCodigoProducto;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JButton btnBuscarProducto;
    private JLabel txtTax;
    private JTextField txtIva;
    private JPanel panelInferior;
    private JPanel panelItems;
    private JPanel panelProduct;
    private JPanel panelTitle;
    private JScrollPane scroll;
    private JButton btnEliminarItem;
    private JLabel lblTitulo;
    private DefaultTableModel modelo;
    private JTextField txtCodigoCarrito;
    private JButton btnBuscarCarrito;
    private JButton btnClean;
    private JPanel panelFinal;
    private JLabel lblItemsCarrito;
    private JLabel lblCordigocCarrito;
    private JTextField txtSub;
    private JLabel lblTot;
    private JTextField txtTot;
    private JLabel lblIva;
    private JLabel lblSubTot;
    private JButton btnDelateItem;
    private MensajeInternacionalizacionHandler i18n;
    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;

    /**
     * Constructor principal.
     * Inicializa la vista, aplica idioma, configura tabla y combo.
     * @param i18n Manejador de internacionalización.
     */
    public CarritoEditarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(410, 505);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        cargarTabla();
        cargarDatosCombobox();
        aplicarIdioma();
    }

    /**
     * Actualiza los campos de subtotal, IVA y total según el locale.
     * @param locale Idioma y formato regional.
     */
    public void refrescarResumenValores(Locale locale) {
        txtSub.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        txtTot.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    /**
     * Carga los números del 1 al 20 en el combobox de cantidad.
     */
    public void cargarDatosCombobox() {
        cbxCantidad.removeAllItems();
        for (int i = 1; i <= 20; i++) {
            cbxCantidad.addItem(i);
        }
    }

    /**
     * Muestra un cuadro de mensaje informativo, de advertencia o de error.
     * @param mensaje Mensaje a mostrar.
     * @param titulo Título de la ventana.
     * @param tipo Tipo de mensaje (JOptionPane).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un cuadro de confirmación con opciones.
     * @param mensaje Mensaje a mostrar.
     * @param titulo Título de la ventana.
     * @param tipo Tipo de mensaje.
     * @return Índice de la opción seleccionada.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Configura el modelo de la tabla, aplica estilos y alineación.
     */
    public void cargarTabla() {
        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Total Item"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProducts.setModel(modelo);

        Color fondo = new Color(29, 30, 32);
        Color letras = Color.WHITE;

        if (panelAll != null) panelAll.setBackground(fondo);
        if (panelInferior != null) panelInferior.setBackground(fondo);
        if (panelItems != null) panelItems.setBackground(fondo);
        if (panelProduct != null) panelProduct.setBackground(fondo);
        if (panelTitle != null) panelTitle.setBackground(fondo);

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

    /**
     * Limpia los campos del producto actual.
     */
    public void limpiarCampos() {
        cbxCantidad.setSelectedIndex(0);
        txtCodigoProducto.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra en la tabla los items actuales del carrito.
     * @param items Lista de items del carrito.
     */
    public void mostrarItemsCarrito(List<ItemCarrito> items) {
        modelo.setRowCount(0);
        Locale locale = i18n.getLocale();
        for (ItemCarrito item : items) {
            Producto p = item.getProducto();
            String precioFormateado = FormateadorUtils.formatearMoneda(p.getPrecio(), locale);
            String totalItemFormateado = FormateadorUtils.formatearMoneda(item.getTotalItem(), locale);
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    precioFormateado,
                    item.getCantidad(),
                    totalItemFormateado
            });
        }
    }

    /**
     * Aplica los textos traducidos según el idioma actual.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("carrito.editar.tituloVentana"));
        lblTitulo.setText(i18n.get("carrito.editar.lbl.titulo"));
        lblCodigoProducto.setText(i18n.get("carrito.editar.lbl.codigoProducto"));
        lblNombre.setText(i18n.get("carrito.editar.lbl.nombre"));
        lblPrecio.setText(i18n.get("carrito.editar.precio"));
        lblCantidad.setText(i18n.get("carrito.editar.cantidad"));
        btnAnadir.setText(i18n.get("carrito.editar.btn.anadir"));
        btnActualizar.setText(i18n.get("carrito.editar.btn.actualizar"));
        btnBuscarProducto.setText(i18n.get("producto.buscar.btn.buscar.producto"));
        btnBuscarCarrito.setText(i18n.get("carrito.buscar.buscar.btn.buscar.carrito"));
        btnClean.setText(i18n.get("carrito.limpiar.btn.limpiar"));
        btnEliminarItem.setText(i18n.get("carrito.editar.btn.eliminar.item"));
        lblCordigocCarrito.setText(i18n.get("carrito.items.lbl.codigoCarrito"));
        tblProducts.getColumnModel().getColumn(0).setHeaderValue(i18n.get("carrito.listar.item..tbl.codigo"));
        tblProducts.getColumnModel().getColumn(1).setHeaderValue(i18n.get("carrito.listar.item..tbl.nombre"));
        tblProducts.getColumnModel().getColumn(2).setHeaderValue(i18n.get("carrito.listar.item.tbl.precio"));
        tblProducts.getColumnModel().getColumn(3).setHeaderValue(i18n.get("carrito.listar.item.tbl.cantidad"));
        tblProducts.getColumnModel().getColumn(4).setHeaderValue(i18n.get("carrito.listar.item.tbl.totalItem"));
        tblProducts.getTableHeader().repaint();
    }

    /**
     * Actualiza y repinta la tabla de productos.
     */
    public void refrescarTabla() {
        ((DefaultTableModel) tblProducts.getModel()).fireTableDataChanged();
        tblProducts.getTableHeader().repaint();
    }

    /**
     * @return Panel principal de la vista.
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * @param panelAll Panel principal de la vista.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * Obtiene el botón para buscar un carrito.
     * @return btnBuscarCarrito botón para buscar
     */
    public JButton getBtnBuscarCarrito() {
        return btnBuscarCarrito;
    }

    /**
     * Establece el botón para buscar un carrito.
     * @param btnBuscarCarrito botón a asignar
     */
    public void setBtnBuscarCarrito(JButton btnBuscarCarrito) {
        this.btnBuscarCarrito = btnBuscarCarrito;
    }

    /**
     * Obtiene el botón para limpiar los campos del formulario.
     * @return btnClean botón para limpiar
     */
    public JButton getBtnClean() {
        return btnClean;
    }

    /**
     * Establece el botón para limpiar los campos del formulario.
     * @param btnClean botón a asignar
     */
    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
    }

    /**
     * Obtiene el botón para eliminar un ítem del carrito.
     * @return btnEliminarItem botón para eliminar ítem
     */
    public JButton getBtnEliminarItem() {
        return btnEliminarItem;
    }

    /**
     * Establece el botón para eliminar un ítem del carrito.
     * @param btnEliminarItem botón a asignar
     */
    public void setBtnEliminarItem(JButton btnEliminarItem) {
        this.btnEliminarItem = btnEliminarItem;
    }

    /**
     * Obtiene el campo de texto que contiene el código del carrito.
     * @return txtCodigoCarrito campo de texto del código del carrito
     */
    public JTextField getTxtCodigoCarrito() {
        return txtCodigoCarrito;
    }

    /**
     * Establece el campo de texto que contiene el código del carrito.
     * @param txtCodigoCarrito campo de texto a asignar
     */
    public void setTxtCodigoCarrito(JTextField txtCodigoCarrito) {
        this.txtCodigoCarrito = txtCodigoCarrito;
    }

    /**
     * @return Campo de texto para el código del producto.
     */
    public JTextField getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    /**
     * Obtiene el campo de texto que muestra el subtotal de la compra.
     * @return txtSub campo de texto del subtotal
     */
    public JTextField getTxtSub() {
        return txtSub;
    }

    /**
     * Establece el campo de texto que muestra el subtotal de la compra.
     * @param txtSub campo de texto a asignar
     */
    public void setTxtSub(JTextField txtSub) {
        this.txtSub = txtSub;
    }

    /**
     * Obtiene el campo de texto que muestra el total de la compra.
     * @return txtTot campo de texto del total
     */
    public JTextField getTxtTot() {
        return txtTot;
    }

    /**
     * Establece el campo de texto que muestra el total de la compra.
     * @param txtTot campo de texto a asignar
     */
    public void setTxtTot(JTextField txtTot) {
        this.txtTot = txtTot;
    }

    /**
     * @param txtCodigoProducto Campo de texto para el código del producto.
     */
    public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
        this.txtCodigoProducto = txtCodigoProducto;
    }

    /**
     * @return Campo de texto para el nombre del producto.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @param txtNombre Campo de texto para el nombre del producto.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * @return Campo de texto para el precio del producto.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * @param txtPrecio Campo de texto para el precio del producto.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * @return Tabla de productos del carrito.
     */
    public JTable getTblProducts() {
        return tblProducts;
    }

    /**
     * @param tblProducts Tabla de productos del carrito.
     */
    public void setTblProducts(JTable tblProducts) {
        this.tblProducts = tblProducts;
    }

    /**
     * @return Botón para añadir productos.
     */
    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    /**
     * @param btnAnadir Botón para añadir productos.
     */
    public void setBtnAnadir(JButton btnAnadir) {
        this.btnAnadir = btnAnadir;
    }

    /**
     * @return ComboBox para seleccionar cantidad.
     */
    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    /**
     * @param cbxCantidad ComboBox para seleccionar cantidad.
     */
    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    /**
     * @return Botón para actualizar el carrito.
     */
    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    /**
     * @param btnActualizar Botón para actualizar el carrito.
     */
    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    /**
     * @return Etiqueta para mostrar el total.
     */
    public JLabel getTxtTotal() {
        return txtTotal;
    }

    /**
     * @param txtTotal Etiqueta para mostrar el total.
     */
    public void setTxtTotal(JLabel txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * @return Etiqueta del carrito de compras.
     */
    public JLabel getTxtShoppingCart() {
        return txtShoppingCart;
    }

    /**
     * @param txtShoppingCart Etiqueta del carrito de compras.
     */
    public void setTxtShoppingCart(JLabel txtShoppingCart) {
        this.txtShoppingCart = txtShoppingCart;
    }

    /**
     * @return Etiqueta del código de producto.
     */
    public JLabel getLblCodigoProducto() {
        return lblCodigoProducto;
    }

    /**
     * @param lblCodigoProducto Etiqueta del código de producto.
     */
    public void setLblCodigoProducto(JLabel lblCodigoProducto) {
        this.lblCodigoProducto = lblCodigoProducto;
    }

    /**
     * @return Etiqueta del nombre del producto.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * @param lblNombre Etiqueta del nombre del producto.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * @return Etiqueta del precio del producto.
     */
    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    /**
     * @param lblPrecio Etiqueta del precio del producto.
     */
    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    /**
     * @return Etiqueta de cantidad.
     */
    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    /**
     * @param lblCantidad Etiqueta de cantidad.
     */
    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    /**
     * @return Botón para buscar un producto.
     */
    public JButton getBtnBuscarProducto() {
        return btnBuscarProducto;
    }

    /**
     * @param btnBuscarProducto Botón para buscar un producto.
     */
    public void setBtnBuscarProducto(JButton btnBuscarProducto) {
        this.btnBuscarProducto = btnBuscarProducto;
    }

    /**
     * @return Etiqueta para mostrar el impuesto.
     */
    public JLabel getTxtTax() {
        return txtTax;
    }

    /**
     * @param txtTax Etiqueta para mostrar el impuesto.
     */
    public void setTxtTax(JLabel txtTax) {
        this.txtTax = txtTax;
    }

    /**
     * @return Campo de texto para mostrar IVA.
     */
    public JTextField getTxtIva() {
        return txtIva;
    }

    /**
     * @param txtIva Campo de texto para mostrar IVA.
     */
    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    /**
     * @return Modelo de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * @param modelo Modelo de la tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * @return Subtotal del carrito.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal Subtotal del carrito.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return IVA del carrito.
     */
    public double getIva() {
        return iva;
    }

    /**
     * @param iva IVA del carrito.
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * @return Total del carrito.
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total Total del carrito.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return Manejador de internacionalización.
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * @param i18n Manejador de internacionalización.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}

