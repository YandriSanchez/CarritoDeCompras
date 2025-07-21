package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Carrito;
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
 * Clase CarritoListarView.
 * <p>Vista interna para listar los carritos de compras existentes.
 * Permite visualizar un resumen con subtotal, IVA y total,
 * así como buscar, listar y ver los detalles de carritos seleccionados.
 * Incluye funcionalidades para mostrar mensajes, aplicar internacionalización
 * y actualizar los datos de la tabla.
 */
public class CarritoListarView extends JInternalFrame {
    private JPanel panelAll;
    private JPanel panelSuperior;
    private JLabel lblTitulo;
    private JPanel panelCenter;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblCarritos;
    private JButton btnVerCarrito;
    private JPanel panelTabla;
    private JScrollPane scroll;
    private JPanel panelInferior;
    private JLabel lblSubtotalResumen;
    private JLabel lblIvaResumen;
    private JLabel lblTotalResumen;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler i18n;
    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;
    private List<Carrito> carritosMostrados;

    /**
     * Constructor de la clase.
     *
     * @param i18n manejador de internacionalización para los textos de la vista.
     */
    public CarritoListarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setSize(475, 290);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new Object[]{"Code", "User", "Fecha", "SubTotal", "Iva", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCarritos.setModel(modelo);

        Color fondo = new Color(132, 148, 229);
        Color letras = Color.BLACK;

        if (scroll == null && tblCarritos != null) {
            scroll = (JScrollPane) tblCarritos.getParent().getParent();
        }
        if (scroll != null) {
            scroll.getViewport().setBackground(fondo);
            scroll.setBackground(fondo);
        }

        if (tblCarritos != null) {
            tblCarritos.setBackground(fondo);
            tblCarritos.setForeground(letras);
            tblCarritos.setSelectionBackground(new Color(50, 50, 60));
            tblCarritos.setSelectionForeground(Color.WHITE);
            tblCarritos.setGridColor(fondo);

            JTableHeader header = tblCarritos.getTableHeader();
            header.setBackground(fondo);
            header.setForeground(letras);
            header.setFont(header.getFont().deriveFont(Font.BOLD));
            ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setForeground(letras);
            centerRenderer.setBackground(fondo);
            for (int i = 0; i < tblCarritos.getColumnCount(); i++) {
                tblCarritos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        aplicarIdioma();
    }

    /**
     * Muestra los carritos en la tabla y actualiza el resumen de valores.
     *
     * @param carritos lista de carritos a mostrar.
     * @param locale configuración regional para formatear fechas y montos.
     */
    public void mostrarCarritos(List<Carrito> carritos, Locale locale) {
        modelo.setRowCount(0);
        subtotal = 0;
        iva = 0;
        total = 0;
        if (carritos != null) {
            for (Carrito carrito : carritos) {
                modelo.addRow(new Object[]{
                        carrito.getId(),
                        carrito.getUsuario() != null ? carrito.getUsuario().getCedula() : "N/A",
                        FormateadorUtils.formatearFecha(carrito.getFecha(), locale),
                        FormateadorUtils.formatearMoneda(carrito.getSubtotal(), locale),
                        FormateadorUtils.formatearMoneda(carrito.getIva(), locale),
                        FormateadorUtils.formatearMoneda(carrito.getTotal(), locale)
                });
                subtotal += carrito.getSubtotal();
                iva += carrito.getIva();
                total += carrito.getTotal();
            }
        }
        this.carritosMostrados = carritos;
        refrescarResumenValores(locale);
    }

    /**
     * Refresca la tabla de carritos mostrados con los datos actuales.
     */
    public void refrescarTabla() {
        if (carritosMostrados != null) {
            mostrarCarritos(carritosMostrados, i18n.getLocale());
        }
    }

    /**
     * Actualiza las etiquetas del resumen (subtotal, IVA, total) con el formato de moneda.
     *
     * @param locale configuración regional para el formato de moneda.
     */
    public void refrescarResumenValores(Locale locale) {
        if (lblSubtotalResumen != null) lblSubtotalResumen.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        if (lblIvaResumen != null) lblIvaResumen.setText(FormateadorUtils.formatearMoneda(iva, locale));
        if (lblTotalResumen != null) lblTotalResumen.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    /**
     * Muestra un cuadro de diálogo de confirmación.
     *
     * @param mensaje el mensaje a mostrar.
     * @param titulo el título del cuadro de diálogo.
     * @param tipo tipo de mensaje (por ejemplo, JOptionPane.QUESTION_MESSAGE).
     * @return la opción seleccionada por el usuario.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Muestra un cuadro de mensaje informativo, de advertencia o de error.
     *
     * @param mensaje el mensaje a mostrar.
     * @param titulo el título del cuadro de diálogo.
     * @param tipo tipo de mensaje (por ejemplo, JOptionPane.INFORMATION_MESSAGE).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Aplica los textos traducidos según el idioma seleccionado.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("carrito.listar.title"));
        lblTitulo.setText(i18n.get("carrito.listar.lbl.title"));
        lblCodigo.setText(i18n.get("carrito.listar.lbl.codigo"));
        btnBuscar.setText(i18n.get("carrito.listar.btn.buscar"));
        btnListar.setText(i18n.get("carrito.listar.btn.listar"));
        btnVerCarrito.setText(i18n.get("carrito.listarbtn..ver"));
        tblCarritos.getColumnModel().getColumn(0).setHeaderValue(i18n.get("carrito.listar.tbl.codigo"));
        tblCarritos.getColumnModel().getColumn(1).setHeaderValue(i18n.get("carrito.listar.tbl.usuario"));
        tblCarritos.getColumnModel().getColumn(2).setHeaderValue(i18n.get("carrito.listar.tbl.fecha"));
        tblCarritos.getColumnModel().getColumn(3).setHeaderValue(i18n.get("carrito.listar.tbl.subtotal"));
        tblCarritos.getColumnModel().getColumn(4).setHeaderValue(i18n.get("carrito.listar.tbl.iva"));
        tblCarritos.getColumnModel().getColumn(5).setHeaderValue(i18n.get("carrito.listar.tbl.total"));
        tblCarritos.getTableHeader().repaint();
        refrescarResumenValores(i18n.getLocale());
    }

    /**
     * Obtiene el panel principal de la vista.
     *
     * @return panel principal
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Establece el panel principal de la vista.
     *
     * @param panelAll el panel principal a establecer
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * Obtiene el panel superior.
     *
     * @return panel superior
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Establece el panel superior.
     *
     * @param panelSuperior el panel superior a establecer
     */
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Obtiene la etiqueta del título.
     *
     * @return etiqueta del título
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
     * Obtiene el panel central.
     *
     * @return panel central
     */
    public JPanel getPanelCenter() {
        return panelCenter;
    }

    /**
     * Establece el panel central.
     *
     * @param panelCenter el panel central a establecer
     */
    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    /**
     * Obtiene la etiqueta del código.
     *
     * @return etiqueta del código
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
     * @return campo de texto del código
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
     * Obtiene el botón de buscar.
     *
     * @return botón de buscar
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el botón de buscar.
     *
     * @param btnBuscar el botón de buscar a establecer
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Obtiene el botón de listar.
     *
     * @return botón de listar
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * Establece el botón de listar.
     *
     * @param btnListar el botón de listar a establecer
     */
    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    /**
     * Obtiene la tabla de carritos.
     *
     * @return tabla de carritos
     */
    public JTable getTblCarritos() {
        return tblCarritos;
    }

    /**
     * Establece la tabla de carritos.
     *
     * @param tblCarritos la tabla de carritos a establecer
     */
    public void setTblCarritos(JTable tblCarritos) {
        this.tblCarritos = tblCarritos;
    }

    /**
     * Obtiene el botón para ver carrito.
     *
     * @return botón para ver carrito
     */
    public JButton getBtnVerCarrito() {
        return btnVerCarrito;
    }

    /**
     * Establece el botón para ver carrito.
     *
     * @param btnVerCarrito el botón para ver carrito a establecer
     */
    public void setBtnVerCarrito(JButton btnVerCarrito) {
        this.btnVerCarrito = btnVerCarrito;
    }

    /**
     * Obtiene el panel de la tabla.
     *
     * @return panel de la tabla
     */
    public JPanel getPanelTabla() {
        return panelTabla;
    }

    /**
     * Establece el panel de la tabla.
     *
     * @param panelTabla el panel de la tabla a establecer
     */
    public void setPanelTabla(JPanel panelTabla) {
        this.panelTabla = panelTabla;
    }

    /**
     * Obtiene el scroll de la tabla.
     *
     * @return scroll de la tabla
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     * Establece el scroll de la tabla.
     *
     * @param scroll el scroll de la tabla a establecer
     */
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    /**
     * Obtiene el panel inferior.
     *
     * @return panel inferior
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
     * Obtiene la etiqueta del subtotal del resumen.
     *
     * @return etiqueta del subtotal
     */
    public JLabel getLblSubtotalResumen() {
        return lblSubtotalResumen;
    }

    /**
     * Establece la etiqueta del subtotal del resumen.
     *
     * @param lblSubtotalResumen la etiqueta del subtotal a establecer
     */
    public void setLblSubtotalResumen(JLabel lblSubtotalResumen) {
        this.lblSubtotalResumen = lblSubtotalResumen;
    }

    /**
     * Obtiene la etiqueta del IVA del resumen.
     *
     * @return etiqueta del IVA
     */
    public JLabel getLblIvaResumen() {
        return lblIvaResumen;
    }

    /**
     * Establece la etiqueta del IVA del resumen.
     *
     * @param lblIvaResumen la etiqueta del IVA a establecer
     */
    public void setLblIvaResumen(JLabel lblIvaResumen) {
        this.lblIvaResumen = lblIvaResumen;
    }

    /**
     * Obtiene la etiqueta del total del resumen.
     *
     * @return etiqueta del total
     */
    public JLabel getLblTotalResumen() {
        return lblTotalResumen;
    }

    /**
     * Establece la etiqueta del total del resumen.
     *
     * @param lblTotalResumen la etiqueta del total a establecer
     */
    public void setLblTotalResumen(JLabel lblTotalResumen) {
        this.lblTotalResumen = lblTotalResumen;
    }

    /**
     * Obtiene el modelo de la tabla.
     *
     * @return modelo de la tabla
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
     * @return manejador de internacionalización
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
     * Obtiene el subtotal acumulado.
     *
     * @return subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal acumulado.
     *
     * @param subtotal el subtotal a establecer
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el IVA acumulado.
     *
     * @return IVA
     */
    public double getIva() {
        return iva;
    }

    /**
     * Establece el IVA acumulado.
     *
     * @param iva el IVA a establecer
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Obtiene el total acumulado.
     *
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total acumulado.
     *
     * @param total el total a establecer
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene la lista de carritos mostrados actualmente.
     *
     * @return lista de carritos mostrados
     */
    public List<Carrito> getCarritosMostrados() {
        return carritosMostrados;
    }

    /**
     * Establece la lista de carritos a mostrar.
     *
     * @param carritosMostrados la lista de carritos a establecer
     */
    public void setCarritosMostrados(List<Carrito> carritosMostrados) {
        this.carritosMostrados = carritosMostrados;
    }
}