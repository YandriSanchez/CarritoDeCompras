package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoAnadirView extends JInternalFrame {

    private JPanel panelAll;
    private JTextField txtCodigo;
    private JButton btnGuardar;
    private JButton btnNuevo;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JPanel panelTop;
    private JPanel panelCenter;
    private JPanel panelBottom;
    private JLabel lblTitulo;
    private MensajeInternacionalizacionHandler i18n;

    public ProductoAnadirView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setSize(275, 235);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        aplicarIdiomas();
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    public void aplicarIdiomas() {
        setTitle(i18n.get("producto.anadir.titulo"));
        lblTitulo.setText(i18n.get("producto.anadir.lbl.titulo"));
        lblCodigo.setText(i18n.get("producto.anadir.lbl.codigo"));
        lblNombre.setText(i18n.get("producto.anadir.lbl.nombre"));
        lblPrecio.setText(i18n.get("producto.anadir.lbl.precio"));
        btnGuardar.setText(i18n.get("producto.anadir.btn.guardar"));
        btnNuevo.setText(i18n.get("producto.anadir.btn.nuevo"));
    }

    public JPanel getPanelAll() {
        return panelAll;
    }

    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public void setBtnNuevo(JButton btnNuevo) {
        this.btnNuevo = btnNuevo;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JPanel getPanelTop() {
        return panelTop;
    }

    public void setPanelTop(JPanel panelTop) {
        this.panelTop = panelTop;
    }

    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    public JPanel getPanelBottom() {
        return panelBottom;
    }

    public void setPanelBottom(JPanel panelBottom) {
        this.panelBottom = panelBottom;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}