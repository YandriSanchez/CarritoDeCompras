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
    private MensajeInternacionalizacionHandler tipoIdioma;
    private double subtotal = 0.0;
    private double iva = 0.0;
    private double total = 0.0;
    private List<Carrito> carritosMostrados;

    public CarritoListarView(MensajeInternacionalizacionHandler tipoIdioma) {
        this.tipoIdioma = tipoIdioma;
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

    public void mostrarCarritos(List<Carrito> carritos, Locale locale) {
        modelo.setRowCount(0);
        subtotal = 0;
        iva = 0;
        total = 0;
        if (carritos != null) {
            for (Carrito carrito : carritos) {
                modelo.addRow(new Object[]{
                        carrito.getId(),
                        carrito.getUsuario() != null ? carrito.getUsuario().getUserName() : "N/A",
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

    public void refrescarTabla() {
        if (carritosMostrados != null) {
            mostrarCarritos(carritosMostrados, tipoIdioma.getLocale());
        }
    }

    public void refrescarResumenValores(Locale locale) {
        if (lblSubtotalResumen != null) lblSubtotalResumen.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        if (lblIvaResumen != null) lblIvaResumen.setText(FormateadorUtils.formatearMoneda(iva, locale));
        if (lblTotalResumen != null) lblTotalResumen.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {tipoIdioma.get("mensaje.confirmacion"), tipoIdioma.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public void aplicarIdioma() {
        setTitle(tipoIdioma.get("carrito.listar.title"));
        lblTitulo.setText(tipoIdioma.get("carrito.listar.lbl.title"));
        lblCodigo.setText(tipoIdioma.get("carrito.listar.lbl.codigo"));
        btnBuscar.setText(tipoIdioma.get("carrito.listar.btn.buscar"));
        btnListar.setText(tipoIdioma.get("carrito.listar.btn.listar"));
        btnVerCarrito.setText(tipoIdioma.get("carrito.listarbtn..ver"));
        tblCarritos.getColumnModel().getColumn(0).setHeaderValue(tipoIdioma.get("carrito.listar.tbl.codigo"));
        tblCarritos.getColumnModel().getColumn(1).setHeaderValue(tipoIdioma.get("carrito.listar.tbl.usuario"));
        tblCarritos.getColumnModel().getColumn(2).setHeaderValue(tipoIdioma.get("carrito.listar.tbl.fecha"));
        tblCarritos.getColumnModel().getColumn(3).setHeaderValue(tipoIdioma.get("carrito.listar.tbl.subtotal"));
        tblCarritos.getColumnModel().getColumn(4).setHeaderValue(tipoIdioma.get("carrito.listar.tbl.iva"));
        tblCarritos.getColumnModel().getColumn(5).setHeaderValue(tipoIdioma.get("carrito.listar.tbl.total"));
        tblCarritos.getTableHeader().repaint();
        refrescarResumenValores(tipoIdioma.getLocale());
    }

    public JPanel getPanelAll() { return panelAll; }
    public void setPanelAll(JPanel panelAll) { this.panelAll = panelAll; }
    public JPanel getPanelSuperior() { return panelSuperior; }
    public void setPanelSuperior(JPanel panelSuperior) { this.panelSuperior = panelSuperior; }
    public JLabel getLblTitulo() { return lblTitulo; }
    public void setLblTitulo(JLabel lblTitulo) { this.lblTitulo = lblTitulo; }
    public JPanel getPanelCenter() { return panelCenter; }
    public void setPanelCenter(JPanel panelCenter) { this.panelCenter = panelCenter; }
    public JLabel getLblCodigo() { return lblCodigo; }
    public void setLblCodigo(JLabel lblCodigo) { this.lblCodigo = lblCodigo; }
    public JTextField getTxtCodigo() { return txtCodigo; }
    public void setTxtCodigo(JTextField txtCodigo) { this.txtCodigo = txtCodigo; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public void setBtnBuscar(JButton btnBuscar) { this.btnBuscar = btnBuscar; }
    public JButton getBtnListar() { return btnListar; }
    public void setBtnListar(JButton btnListar) { this.btnListar = btnListar; }
    public JTable getTblCarritos() { return tblCarritos; }
    public void setTblCarritos(JTable tblCarritos) { this.tblCarritos = tblCarritos; }
    public JButton getBtnVerCarrito() { return btnVerCarrito; }
    public void setBtnVerCarrito(JButton btnVerCarrito) { this.btnVerCarrito = btnVerCarrito; }
    public JPanel getPanelTabla() { return panelTabla; }
    public void setPanelTabla(JPanel panelTabla) { this.panelTabla = panelTabla; }
    public JScrollPane getScroll() { return scroll; }
    public void setScroll(JScrollPane scroll) { this.scroll = scroll; }
    public JPanel getPanelInferior() { return panelInferior; }
    public void setPanelInferior(JPanel panelInferior) { this.panelInferior = panelInferior; }
    public DefaultTableModel getModelo() { return modelo; }
    public void setModelo(DefaultTableModel modelo) { this.modelo = modelo; }
    public JLabel getLblSubtotalResumen() { return lblSubtotalResumen; }
    public void setLblSubtotalResumen(JLabel lblSubtotalResumen) { this.lblSubtotalResumen = lblSubtotalResumen; }
    public JLabel getLblIvaResumen() { return lblIvaResumen; }
    public void setLblIvaResumen(JLabel lblIvaResumen) { this.lblIvaResumen = lblIvaResumen; }
    public JLabel getLblTotalResumen() { return lblTotalResumen; }
    public void setLblTotalResumen(JLabel lblTotalResumen) { this.lblTotalResumen = lblTotalResumen; }

}