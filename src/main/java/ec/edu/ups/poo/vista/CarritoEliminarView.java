package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.FormateadorUtils;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Locale;

/**
 * Clase CarritoEliminarView.
 * <p>Vista interna Swing para eliminar productos de un carrito de compras,
 * muestra los productos actuales, permite buscar por código y eliminar ítems,
 * además de mostrar los totales con soporte de internacionalización.</p>
 */
public class CarritoEliminarView extends JInternalFrame {
    private JPanel panelTitle;
    private JLabel lblTitulo;
    private JPanel panelProduct;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JScrollPane scroll;
    private JTable tblProducts;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JTextField txtSubTotal;
    private JButton btnEliminar;
    private JPanel panelAll;
    private JLabel lblCodigo;
    private JLabel lblSubTotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JLabel lblItemsCarrito;
    private JPanel panelFinal;
    private MensajeInternacionalizacionHandler i18n;
    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;

    /**
     * Constructor que inicializa la vista con el manejador de mensajes internacionalizados,
     * configura la tabla, colores y aplica el idioma.
     *
     * @param i18n Manejador para la internacionalización de textos.
     */
    public CarritoEliminarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 420);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        Color fondoOscuro = new Color(132,148,229);

        panelAll.setBackground(fondoOscuro);

        tblProducts.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Código", "Nombre", "Precio", "Cantidad", "Total Item"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tblProducts.setBackground(fondoOscuro);
        tblProducts.setForeground(Color.BLACK);
        scroll.getViewport().setBackground(fondoOscuro);
        scroll.setBackground(fondoOscuro);

        JTableHeader header = tblProducts.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        aplicarIdioma();
    }

    /**
     * Muestra un cuadro de diálogo de confirmación con opciones Sí/No.
     *
     * @param mensaje Mensaje a mostrar.
     * @param titulo  Título de la ventana del diálogo.
     * @param tipo    Tipo de mensaje (información, advertencia, etc).
     * @return El índice de la opción seleccionada por el usuario.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Muestra un mensaje informativo simple en un cuadro de diálogo.
     *
     * @param mensaje Mensaje a mostrar.
     * @param titulo  Título de la ventana del mensaje.
     * @param tipo    Tipo de mensaje (información, error, etc).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Aplica los textos traducidos a los componentes de la interfaz según el idioma configurado.
     */
    public void aplicarIdioma(){
        setTitle(i18n.get("carrito.eliminar.title"));
        lblTitulo.setText(i18n.get("carrito.eliminar.lbl.Titulo"));
        lblCodigo.setText(i18n.get("carrito.eliminar.lbl.Codigo"));

        btnBuscar.setText(i18n.get("carrito.eliminar.btn.Buscar"));
        btnEliminar.setText(i18n.get("carrito.eliminar.btn.Eliminar"));

        tblProducts.getColumnModel().getColumn(0).setHeaderValue(i18n.get("carrito.eliminar.tbl.Codigo"));
        tblProducts.getColumnModel().getColumn(1).setHeaderValue(i18n.get("carrito.eliminar.tbl.Nombre"));
        tblProducts.getColumnModel().getColumn(2).setHeaderValue(i18n.get("carrito.eliminar.tbl.Precio"));
        tblProducts.getColumnModel().getColumn(3).setHeaderValue(i18n.get("carrito.eliminar.tbl.Cantidad"));
        tblProducts.getColumnModel().getColumn(4).setHeaderValue(i18n.get("carrito.eliminar.tbl.TotalItem"));
        tblProducts.getTableHeader().repaint();
    }

    /**
     * Actualiza los campos de subtotal, IVA y total con los valores formateados
     * según la configuración regional recibida.
     *
     * @param locale Configuración regional para el formato.
     */
    public void refrescarResumenValores(Locale locale) {
        txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        txtTotal.setText(FormateadorUtils.formatearMoneda(total, locale));
        txtSubTotal.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
    }

    /**
     * Refresca la tabla para que los datos se muestren actualizados visualmente.
     */
    public void refrescarTabla() {
        ((DefaultTableModel) tblProducts.getModel()).fireTableDataChanged();
        tblProducts.getTableHeader().repaint();
    }

    public JPanel getPanelTitle() {
        return panelTitle;
    }

    /**
     * @return El panel principal que contiene todos los componentes.
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * @param panelAll Establece el panel principal que contiene todos los componentes.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * @param panelTitle Establece el panel del título.
     */
    public void setPanelTitle(JPanel panelTitle) {
        this.panelTitle = panelTitle;
    }

    /**
     * @return El panel que contiene los componentes relacionados con el producto.
     */
    public JPanel getPanelProduct() {
        return panelProduct;
    }

    /**
     * @param panelProduct Establece el panel que contiene los componentes relacionados con el producto.
     */
    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    /**
     * @return El campo de texto donde se ingresa el código del producto.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * @param txtCodigo Establece el campo de texto para el código del producto.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * @return El botón para buscar productos.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * @param btnBuscar Establece el botón para buscar productos.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * @return El JScrollPane que contiene la tabla de productos.
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     * @param scroll Establece el JScrollPane que contiene la tabla de productos.
     */
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    /**
     * @return La tabla que muestra los productos del carrito.
     */
    public JTable getTblProducts() {
        return tblProducts;
    }

    /**
     * @param tblProducts Establece la tabla que muestra los productos del carrito.
     */
    public void setTblProducts(JTable tblProducts) {
        this.tblProducts = tblProducts;
    }

    /**
     * @return Campo de texto que muestra el valor del IVA.
     */
    public JTextField getTxtIva() {
        return txtIva;
    }

    /**
     * @param txtIva Establece el campo de texto para mostrar el IVA.
     */
    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    /**
     * @return Campo de texto que muestra el total a pagar.
     */
    public JTextField getTxtTotal() {
        return txtTotal;
    }

    /**
     * @param txtTotal Establece el campo de texto para mostrar el total a pagar.
     */
    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * @return Campo de texto que muestra el subtotal.
     */
    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    /**
     * @param txtSubTotal Establece el campo de texto para mostrar el subtotal.
     */
    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    /**
     * @return Botón para eliminar productos del carrito.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * @param btnEliminar Establece el botón para eliminar productos del carrito.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * @return La etiqueta que muestra el texto "Código".
     */
    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    /**
     * @param lblCodigo Establece la etiqueta que muestra el texto "Código".
     */
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    /**
     * @return La etiqueta que muestra el texto "Subtotal".
     */
    public JLabel getLblSubTotal() {
        return lblSubTotal;
    }

    /**
     * @param lblSubTotal Establece la etiqueta que muestra el texto "Subtotal".
     */
    public void setLblSubTotal(JLabel lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    /**
     * @return La etiqueta que muestra el texto "IVA".
     */
    public JLabel getLblIva() {
        return lblIva;
    }

    /**
     * @param lblIva Establece la etiqueta que muestra el texto "IVA".
     */
    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
    }

    /**
     * @return La etiqueta que muestra el texto "Total".
     */
    public JLabel getLblTotal() {
        return lblTotal;
    }

    /**
     * @param lblTotal Establece la etiqueta que muestra el texto "Total".
     */
    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    /**
     * @return La etiqueta que muestra el texto relacionado con los ítems del carrito.
     */
    public JLabel getLblItemsCarrito() {
        return lblItemsCarrito;
    }

    /**
     * @param lblItemsCarrito Establece la etiqueta que muestra el texto relacionado con los ítems del carrito.
     */
    public void setLblItemsCarrito(JLabel lblItemsCarrito) {
        this.lblItemsCarrito = lblItemsCarrito;
    }

    /**
     * @return Panel final que contiene componentes de resumen y totales.
     */
    public JPanel getPanelFinal() {
        return panelFinal;
    }

    /**
     * @param panelFinal Establece el panel final que contiene componentes de resumen y totales.
     */
    public void setPanelFinal(JPanel panelFinal) {
        this.panelFinal = panelFinal;
    }

    /**
     * @return El manejador de internacionalización.
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * @param i18n Establece el manejador de internacionalización.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }

    /**
     * @return Valor del subtotal.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal Establece el valor del subtotal.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return Valor del IVA.
     */
    public double getIva() {
        return iva;
    }

    /**
     * @param iva Establece el valor del IVA.
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * @return Valor total a pagar.
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total Establece el valor total a pagar.
     */
    public void setTotal(double total) {
        this.total = total;
    }
}