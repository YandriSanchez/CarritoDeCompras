package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.ItemCarrito;
import ec.edu.ups.poo.modelo.Rol;
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

/**
 * Clase CarritoEliminarView.
 * <p>Vista interna para listar los ítems de un carrito de compras.
 * Proporciona una interfaz gráfica para mostrar detalles del carrito,
 * como los ítems, subtotal, IVA, total, usuario, rol y fecha.
 * Incluye funcionalidades para mostrar datos, cargar la tabla de productos,
 * aplicar internacionalización y mostrar mensajes.
 */
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
    private JLabel lblItemsCarrito;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler i18n;
    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;
    private List<ItemCarrito> currentItems;

    /**
     * Construye la vista para listar los ítems del carrito.
     *
     * @param carritoId  Identificador del carrito
     * @param items      Lista de ítems contenidos en el carrito
     * @param subtotal   Valor subtotal del carrito
     * @param iva        Valor del IVA aplicado
     * @param total      Valor total del carrito (subtotal + IVA)
     * @param username   Nombre de usuario asociado al carrito
     * @param rol        Rol del usuario
     * @param fecha      Fecha asociada al carrito
     * @param i18n       Manejador de mensajes para internacionalización
     */
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

    /**
     * Muestra los datos del carrito y sus ítems en la vista.
     *
     * @param carritoId Identificador del carrito
     * @param items     Lista de ítems en el carrito
     * @param subtotal  Valor subtotal del carrito
     * @param iva       Valor del IVA aplicado
     * @param total     Valor total del carrito
     * @param username  Nombre de usuario asociado
     * @param rol       Rol del usuario
     * @param fecha     Fecha del carrito
     */
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

    /**
     * Carga y configura la tabla para mostrar los ítems del carrito.
     * Establece colores, formato de texto y encabezados.
     */
    public void cargarTabla() {
        modelo = new DefaultTableModel(new Object[]{"Item", "Nombre", "Precio", "Cantidad", "Total Item"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProducts.setModel(modelo);

        Color fondo = new Color(29, 30, 32);
        Color letras = Color.WHITE;

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
     * Muestra un mensaje emergente en la interfaz gráfica.
     *
     * @param mensaje Texto del mensaje a mostrar
     * @param titulo  Título del cuadro de mensaje
     * @param tipo    Tipo de mensaje (información, error, advertencia, etc.)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Aplica la internacionalización a los componentes de la interfaz,
     * traduciendo textos y encabezados según el idioma configurado.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("carrito.listar.item.titulo"));
        lblTitulo.setText(i18n.get("carrito.listar.item.lbl.titulo"));
        lblCodigo.setText(i18n.get("carrito.listar.item.lbl.codigo"));
        lblUsuario.setText(i18n.get("carrito.listar.item.lbl.usuario"));
        btnSalir.setText(i18n.get("carrito.listar.item.btn.salir"));
        lblItemsCarrito.setText(i18n.get("carrito.listar.items"));
        lblFecha.setText(i18n.get("carrito.listar.item.lbl.fecha"));

        tblProducts.getColumnModel().getColumn(0).setHeaderValue(i18n.get("carrito.listar.item..tbl.codigo"));
        tblProducts.getColumnModel().getColumn(1).setHeaderValue(i18n.get("carrito.listar.item..tbl.nombre"));
        tblProducts.getColumnModel().getColumn(2).setHeaderValue(i18n.get("carrito.listar.item.tbl.precio"));
        tblProducts.getColumnModel().getColumn(3).setHeaderValue(i18n.get("carrito.listar.item.tbl.cantidad"));
        tblProducts.getColumnModel().getColumn(4).setHeaderValue(i18n.get("carrito.listar.item.tbl.totalItem"));
        tblProducts.getTableHeader().repaint();

        refrescarFormatoMoneda();
    }

    /**
     * Refresca el formato de moneda en la tabla y campos numéricos
     * para que coincidan con la configuración regional actual.
     */
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

    /**
     * Obtiene el panel del título.
     *
     * @return el panel del título
     */
    public JPanel getPanelTitle() {
        return panelTitle;
    }

    /**
     * Establece el panel del título.
     *
     * @param panelTitle el panel del título a establecer
     */
    public void setPanelTitle(JPanel panelTitle) {
        this.panelTitle = panelTitle;
    }

    /**
     * Obtiene la etiqueta del título.
     *
     * @return la etiqueta del título
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título.
     *
     * @param lblTitulo la etiqueta del título a establecer
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * Obtiene el panel de productos.
     *
     * @return el panel de productos
     */
    public JPanel getPanelProduct() {
        return panelProduct;
    }

    /**
     * Establece el panel de productos.
     *
     * @param panelProduct el panel de productos a establecer
     */
    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    /**
     * Obtiene la etiqueta del código.
     *
     * @return la etiqueta del código
     */
    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    /**
     * Establece la etiqueta del código.
     *
     * @param lblCodigo la etiqueta del código a establecer
     */
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    /**
     * Obtiene el campo de texto del código.
     *
     * @return el campo de texto del código
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Establece el campo de texto del código.
     *
     * @param txtCodigo el campo de texto del código a establecer
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Obtiene la etiqueta del usuario.
     *
     * @return la etiqueta del usuario
     */
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    /**
     * Establece la etiqueta del usuario.
     *
     * @param lblUsuario la etiqueta del usuario a establecer
     */
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    /**
     * Obtiene el campo de texto para el rol del usuario.
     *
     * @return el campo de texto del rol del usuario
     */
    public JTextField getTxtRolUser() {
        return txtRolUser;
    }

    /**
     * Establece el campo de texto para el rol del usuario.
     *
     * @param txtRolUser el campo de texto del rol del usuario a establecer
     */
    public void setTxtRolUser(JTextField txtRolUser) {
        this.txtRolUser = txtRolUser;
    }

    /**
     * Obtiene el campo de texto de la fecha.
     *
     * @return el campo de texto de la fecha
     */
    public JTextField getTxtFecha() {
        return txtFecha;
    }

    /**
     * Establece el campo de texto de la fecha.
     *
     * @param txtFecha el campo de texto de la fecha a establecer
     */
    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    /**
     * Obtiene la etiqueta de la fecha.
     *
     * @return la etiqueta de la fecha
     */
    public JLabel getLblFecha() {
        return lblFecha;
    }

    /**
     * Establece la etiqueta de la fecha.
     *
     * @param lblFecha la etiqueta de la fecha a establecer
     */
    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    /**
     * Obtiene el panel de ítems.
     *
     * @return el panel de ítems
     */
    public JPanel getPanelItems() {
        return panelItems;
    }

    /**
     * Establece el panel de ítems.
     *
     * @param panelItems el panel de ítems a establecer
     */
    public void setPanelItems(JPanel panelItems) {
        this.panelItems = panelItems;
    }

    /**
     * Obtiene el scroll de la tabla.
     *
     * @return el JScrollPane que contiene la tabla
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     * Establece el scroll de la tabla.
     *
     * @param scroll el JScrollPane a establecer
     */
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    /**
     * Obtiene la tabla de productos.
     *
     * @return la JTable de productos
     */
    public JTable getTblProducts() {
        return tblProducts;
    }

    /**
     * Establece la tabla de productos.
     *
     * @param tblProducts la JTable de productos a establecer
     */
    public void setTblProducts(JTable tblProducts) {
        this.tblProducts = tblProducts;
    }

    /**
     * Obtiene la etiqueta del subtotal.
     *
     * @return la etiqueta del subtotal
     */
    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }

    /**
     * Establece la etiqueta del subtotal.
     *
     * @param lblSubtotal la etiqueta del subtotal a establecer
     */
    public void setLblSubtotal(JLabel lblSubtotal) {
        this.lblSubtotal = lblSubtotal;
    }

    /**
     * Obtiene la etiqueta del IVA.
     *
     * @return la etiqueta del IVA
     */
    public JLabel getLblva() {
        return lblva;
    }

    /**
     * Establece la etiqueta del IVA.
     *
     * @param lblva la etiqueta del IVA a establecer
     */
    public void setLblva(JLabel lblva) {
        this.lblva = lblva;
    }

    /**
     * Obtiene el campo de texto del IVA.
     *
     * @return el campo de texto del IVA
     */
    public JTextField getTxtIva() {
        return txtIva;
    }

    /**
     * Establece el campo de texto del IVA.
     *
     * @param txtIva el campo de texto del IVA a establecer
     */
    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    /**
     * Obtiene la etiqueta del total.
     *
     * @return la etiqueta del total
     */
    public JLabel getLblTotal() {
        return lblTotal;
    }

    /**
     * Establece la etiqueta del total.
     *
     * @param lblTotal la etiqueta del total a establecer
     */
    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    /**
     * Obtiene el campo de texto del total.
     *
     * @return el campo de texto del total
     */
    public JTextField getTxtTotal() {
        return txtTotal;
    }

    /**
     * Establece el campo de texto del total.
     *
     * @param txtTotal el campo de texto del total a establecer
     */
    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * Obtiene el campo de texto del subtotal.
     *
     * @return el campo de texto del subtotal
     */
    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    /**
     * Establece el campo de texto del subtotal.
     *
     * @param txtSubTotal el campo de texto del subtotal a establecer
     */
    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    /**
     * Obtiene el panel inferior.
     *
     * @return el panel inferior
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Establece el panel inferior.
     *
     * @param panelInferior el panel inferior a establecer
     */
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Obtiene el botón de salir.
     *
     * @return el botón de salir
     */
    public JButton getBtnSalir() {
        return btnSalir;
    }

    /**
     * Establece el botón de salir.
     *
     * @param btnSalir el botón de salir a establecer
     */
    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    /**
     * Obtiene el panel principal.
     *
     * @return el panel principal
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Establece el panel principal.
     *
     * @param panelAll el panel principal a establecer
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * Obtiene el campo de texto del usuario.
     *
     * @return el campo de texto del usuario
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * Establece el campo de texto del usuario.
     *
     * @param txtUsuario el campo de texto del usuario a establecer
     */
    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    /**
     * Obtiene la etiqueta que muestra la cantidad de ítems del carrito.
     *
     * @return la etiqueta de ítems del carrito
     */
    public JLabel getLblItemsCarrito() {
        return lblItemsCarrito;
    }

    /**
     * Establece la etiqueta que muestra la cantidad de ítems del carrito.
     *
     * @param lblItemsCarrito la etiqueta de ítems del carrito a establecer
     */
    public void setLblItemsCarrito(JLabel lblItemsCarrito) {
        this.lblItemsCarrito = lblItemsCarrito;
    }

    /**
     * Obtiene el modelo de la tabla.
     *
     * @return el modelo de la tabla
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo de la tabla.
     *
     * @param modelo el modelo de la tabla a establecer
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el manejador de internacionalización.
     *
     * @return el manejador de internacionalización
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * Establece el manejador de internacionalización.
     *
     * @param i18n el manejador de internacionalización a establecer
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }

    /**
     * Obtiene el subtotal actual.
     *
     * @return el subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal.
     *
     * @param subtotal el subtotal a establecer
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el valor del IVA actual.
     *
     * @return el IVA
     */
    public double getIva() {
        return iva;
    }

    /**
     * Establece el valor del IVA.
     *
     * @param iva el IVA a establecer
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Obtiene el valor total actual.
     *
     * @return el total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el valor total.
     *
     * @param total el total a establecer
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene la lista actual de ítems del carrito.
     *
     * @return la lista de ítems del carrito
     */
    public List<ItemCarrito> getCurrentItems() {
        return currentItems;
    }

    /**
     * Establece la lista actual de ítems del carrito.
     *
     * @param currentItems la lista de ítems a establecer
     */
    public void setCurrentItems(List<ItemCarrito> currentItems) {
        this.currentItems = currentItems;
    }
}

