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
 * Clase que representa una ventana interna (JInternalFrame) para añadir productos a un carrito de compras.
 * Incluye controles para buscar productos, seleccionar cantidad, mostrar la lista de items
 * y calcular subtotal, IVA y total.
 */
public class CarritoAnadirView extends JInternalFrame {
    // Componentes de la interfaz gráfica
    private JPanel panelAll;
    private JTextField lblCodeProductSearch;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTable tblProducts;
    private JButton btnAnadir;
    private JTextField lblSubTotal;
    private JComboBox cbxCantidad;
    private JButton btnSave;
    private JButton btnCancel;
    private JLabel txtTotal;
    private JLabel txtShoppingCart;
    private JLabel lblCodigoProducto;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JButton btnBuscar;
    private JLabel txtSubTotal;
    private JLabel lblIva;
    private JTextField lblTax;
    private JTextField lblTotal;
    private JPanel panelInferior;
    private JPanel panelItems;
    private JPanel panelProduct;
    private JPanel panelTitle;
    private JScrollPane scroll;
    private JButton btnEliminarItem;
    private JLabel lblTitulo;
    private JLabel lblItemsCarrito;
    private JTextField txtSubTot;
    private JTextField txtIva;
    private JTextField txtTot;
    private JLabel lblTot;
    private JLabel lblSubTot;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler i18n;

    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;

    /**
     * Constructor que inicializa la vista, configura los componentes,
     * carga la tabla, llena el combobox y aplica el idioma.
     *
     * @param i18n Manejador de internacionalización para los textos.
     */
    public CarritoAnadirView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("YANDRI STORE");
        setSize(410, 505);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        cargarTabla();
        cargarDatosCombobox();
        aplicarIdioma();
    }

    /**
     * Configura la tabla de productos, su modelo, diseño y estilo visual.
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

        if (scroll == null) {
            scroll = (JScrollPane) tblProducts.getParent().getParent();
        }
        scroll.getViewport().setBackground(fondo);
        scroll.setBackground(fondo);

        tblProducts.setBackground(fondo);
        tblProducts.setForeground(letras);
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

    /**
     * Muestra los datos de un producto buscado en los campos de nombre y precio.
     *
     * @param productos Lista de productos encontrados.
     */
    public void mostrarProductos(List<Producto> productos) {
        if (productos != null && !productos.isEmpty()) {
            txtNombre.setText(productos.get(0).getNombre());
            Locale locale = i18n.getLocale();
            String precioFormateado = FormateadorUtils.formatearMoneda(productos.get(0).getPrecio(), locale);
            txtPrecio.setText(precioFormateado);
        } else {
            txtNombre.setText("");
            txtPrecio.setText("");
        }
    }

    /**
     * Limpia los campos de búsqueda y datos del producto.
     */
    public void limpiarCampos() {
        cbxCantidad.setSelectedIndex(0);
        lblCodeProductSearch.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra los items del carrito en la tabla.
     *
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
     * Llena el combobox de cantidad con números del 1 al 20.
     */
    public void cargarDatosCombobox() {
        cbxCantidad.removeAllItems();
        for (int i = 1; i <= 20; i++) {
            cbxCantidad.addItem(i);
        }
    }

    /**
     * Muestra un mensaje informativo en la vista.
     *
     * @param mensaje Texto del mensaje.
     * @param titulo  Título de la ventana del mensaje.
     * @param tipo    Tipo de mensaje (INFO, ERROR, etc.).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un mensaje de confirmación con botones personalizados.
     *
     * @param mensaje Texto del mensaje.
     * @param titulo  Título de la ventana del mensaje.
     * @param tipo    Tipo de mensaje.
     * @return Índice del botón seleccionado.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Cambia los textos de la vista según el idioma configurado.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("carrito.anadir.tituloVentana"));
        lblTitulo.setText(i18n.get("carrito.anadir.lbl.titulo"));
        lblCodigoProducto.setText(i18n.get("carrito.anadir.lbl.codigoProducto"));
        lblCantidad.setText(i18n.get("carrito.anadir.lbl.cantidad"));
        lblNombre.setText(i18n.get("carrito.anadir.lbl.nombre"));
        lblPrecio.setText(i18n.get("carrito.anadir.lbl.precio"));
        btnAnadir.setText(i18n.get("carrito.anadir.btn.anadir"));
        btnBuscar.setText(i18n.get("carrito.anadir.btn.buscar"));
        btnSave.setText(i18n.get("carrito.anadir.btn.guardar"));
        btnCancel.setText(i18n.get("carrito.anadir.btn.cancelar"));
        btnEliminarItem.setText(i18n.get("carrito.anadir.btn.eliminar.item"));
        tblProducts.getColumnModel().getColumn(0).setHeaderValue(i18n.get("carrito.anadir.tbl.Codigo"));
        tblProducts.getColumnModel().getColumn(1).setHeaderValue(i18n.get("carrito.anadir.tbl.Nombre"));
        tblProducts.getColumnModel().getColumn(2).setHeaderValue(i18n.get("carrito.anadir.tbl.Precio"));
        tblProducts.getColumnModel().getColumn(3).setHeaderValue(i18n.get("carrito.anadir.tbl.Cantidad"));
        tblProducts.getColumnModel().getColumn(4).setHeaderValue(i18n.get("carrito.anadir.tbl.TotalItem"));
        tblProducts.getTableHeader().repaint();
    }

    /**
     * Actualiza los campos de subtotal, IVA y total en la vista con formato.
     *
     * @param locale Localización para el formato de moneda.
     */
    public void refrescarResumenValores(Locale locale) {
        txtSubTot.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        txtTot.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    /**
     * Refresca visualmente los datos de la tabla.
     */
    public void refrescarTabla() {
        ((DefaultTableModel) tblProducts.getModel()).fireTableDataChanged();
        tblProducts.getTableHeader().repaint();
    }

    /**
     * Obtiene el panel principal.
     * @return Panel principal.
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Obtiene el botón para eliminar un ítem del carrito.
     * @return btnEliminarItem botón de eliminación
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
     * Obtiene el campo de texto para mostrar el subtotal.
     * @return txtSubTot campo de texto del subtotal
     */
    public JTextField getTxtSubTot() {
        return txtSubTot;
    }

    /**
     * Establece el campo de texto para mostrar el subtotal.
     * @param txtSubTot campo de texto a asignar
     */
    public void setTxtSubTot(JTextField txtSubTot) {
        this.txtSubTot = txtSubTot;
    }

    /**
     * Obtiene el campo de texto para mostrar el IVA.
     * @return txtIva campo de texto del IVA
     */
    public JTextField getTxtIva() {
        return txtIva;
    }

    /**
     * Establece el campo de texto para mostrar el IVA.
     * @param txtIva campo de texto a asignar
     */
    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    /**
     * Obtiene el campo de texto para mostrar el total.
     * @return txtTot campo de texto del total
     */
    public JTextField getTxtTot() {
        return txtTot;
    }

    /**
     * Establece el campo de texto para mostrar el total.
     * @param txtTot campo de texto a asignar
     */
    public void setTxtTot(JTextField txtTot) {
        this.txtTot = txtTot;
    }



    /**
     * Establece el panel principal.
     * @param panelAll Panel principal.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * Obtiene el campo de texto para buscar código de producto.
     * @return Campo de texto para el código.
     */
    public JTextField getLblCodeProductSearch() {
        return lblCodeProductSearch;
    }

    /**
     * Establece el campo de texto para buscar código de producto.
     * @param lblCodeProductSearch Campo de texto.
     */
    public void setLblCodeProductSearch(JTextField lblCodeProductSearch) {
        this.lblCodeProductSearch = lblCodeProductSearch;
    }

    /**
     * Obtiene el campo de texto del nombre del producto.
     * @return Campo de texto del nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Establece el campo de texto del nombre del producto.
     * @param txtNombre Campo de texto del nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Obtiene el campo de texto del precio del producto.
     * @return Campo de texto del precio.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * Establece el campo de texto del precio del producto.
     * @param txtPrecio Campo de texto del precio.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * Obtiene la tabla de productos.
     * @return Tabla de productos.
     */
    public JTable getTblProducts() {
        return tblProducts;
    }

    /**
     * Establece la tabla de productos.
     * @param tblProducts Tabla de productos.
     */
    public void setTblProducts(JTable tblProducts) {
        this.tblProducts = tblProducts;
    }

    /**
     * Obtiene el botón para añadir producto.
     * @return Botón añadir.
     */
    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    /**
     * Establece el botón para añadir producto.
     * @param btnAnadir Botón añadir.
     */
    public void setBtnAnadir(JButton btnAnadir) {
        this.btnAnadir = btnAnadir;
    }

    /**
     * Obtiene el campo de texto del subtotal.
     * @return Campo de texto del subtotal.
     */
    public JTextField getLblSubTotal() {
        return lblSubTotal;
    }

    /**
     * Establece el campo de texto del subtotal.
     * @param lblSubTotal Campo de texto del subtotal.
     */
    public void setLblSubTotal(JTextField lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    /**
     * Obtiene el combobox de cantidad.
     * @return ComboBox de cantidad.
     */
    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    /**
     * Establece el combobox de cantidad.
     * @param cbxCantidad ComboBox de cantidad.
     */
    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    /**
     * Obtiene el botón de guardar.
     * @return Botón guardar.
     */
    public JButton getBtnSave() {
        return btnSave;
    }

    /**
     * Establece el botón de guardar.
     * @param btnSave Botón guardar.
     */
    public void setBtnSave(JButton btnSave) {
        this.btnSave = btnSave;
    }

    /**
     * Obtiene el botón de cancelar.
     * @return Botón cancelar.
     */
    public JButton getBtnCancel() {
        return btnCancel;
    }

    /**
     * Establece el botón de cancelar.
     * @param btnCancel Botón cancelar.
     */
    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    /**
     * Obtiene la etiqueta del total.
     * @return Etiqueta del total.
     */
    public JLabel getTxtTotal() {
        return txtTotal;
    }

    /**
     * Establece la etiqueta del total.
     * @param txtTotal Etiqueta del total.
     */
    public void setTxtTotal(JLabel txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * Obtiene la etiqueta del carrito de compras.
     * @return Etiqueta del carrito.
     */
    public JLabel getTxtShoppingCart() {
        return txtShoppingCart;
    }

    /**
     * Establece la etiqueta del carrito de compras.
     * @param txtShoppingCart Etiqueta del carrito.
     */
    public void setTxtShoppingCart(JLabel txtShoppingCart) {
        this.txtShoppingCart = txtShoppingCart;
    }

    /**
     * Obtiene la etiqueta del código del producto.
     * @return Etiqueta código producto.
     */
    public JLabel getLblCodigoProducto() {
        return lblCodigoProducto;
    }

    /**
     * Establece la etiqueta del código del producto.
     * @param lblCodigoProducto Etiqueta código producto.
     */
    public void setLblCodigoProducto(JLabel lblCodigoProducto) {
        this.lblCodigoProducto = lblCodigoProducto;
    }

    /**
     * Obtiene la etiqueta del nombre del producto.
     * @return Etiqueta nombre producto.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Establece la etiqueta del nombre del producto.
     * @param lblNombre Etiqueta nombre producto.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Obtiene la etiqueta del precio del producto.
     * @return Etiqueta precio.
     */
    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    /**
     * Establece la etiqueta del precio del producto.
     * @param lblPrecio Etiqueta precio.
     */
    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    /**
     * Obtiene la etiqueta de cantidad.
     * @return Etiqueta cantidad.
     */
    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    /**
     * Establece la etiqueta de cantidad.
     * @param lblCantidad Etiqueta cantidad.
     */
    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    /**
     * Obtiene el botón de buscar producto.
     * @return Botón buscar.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el botón de buscar producto.
     * @param btnBuscar Botón buscar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Obtiene la etiqueta del subtotal.
     * @return Etiqueta subtotal.
     */
    public JLabel getTxtSubTotal() {
        return txtSubTotal;
    }

    /**
     * Establece la etiqueta del subtotal.
     * @param txtSubTotal Etiqueta subtotal.
     */
    public void setTxtSubTotal(JLabel txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    /**
     * Obtiene la etiqueta del IVA.
     * @return Etiqueta IVA.
     */
    public JLabel getLblIva() {
        return lblIva;
    }

    /**
     * Establece la etiqueta del IVA.
     * @param lblIva Etiqueta IVA.
     */
    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
    }

    /**
     * Obtiene el campo de texto del impuesto.
     * @return Campo impuesto.
     */
    public JTextField getLblTax() {
        return lblTax;
    }

    /**
     * Establece el campo de texto del impuesto.
     * @param lblTax Campo impuesto.
     */
    public void setLblTax(JTextField lblTax) {
        this.lblTax = lblTax;
    }

    /**
     * Obtiene el campo de texto del total.
     * @return Campo total.
     */
    public JTextField getLblTotal() {
        return lblTotal;
    }

    /**
     * Establece el campo de texto del total.
     * @param lblTotal Campo total.
     */
    public void setLblTotal(JTextField lblTotal) {
        this.lblTotal = lblTotal;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return Modelo de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo de la tabla.
     * @param modelo Modelo de la tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el manejador de internacionalización.
     * @return Manejador internacionalización.
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * Establece el manejador de internacionalización.
     * @param i18n Manejador internacionalización.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }

    /**
     * Obtiene el subtotal actual.
     * @return Subtotal.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal actual.
     * @param subtotal Subtotal.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el IVA actual.
     * @return IVA.
     */
    public double getIva() {
        return iva;
    }

    /**
     * Establece el IVA actual.
     * @param iva IVA.
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Obtiene el total actual.
     * @return Total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total actual.
     * @param total Total.
     */
    public void setTotal(double total) {
        this.total = total;
    }
}

