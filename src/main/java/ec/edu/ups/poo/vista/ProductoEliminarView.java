package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoEliminarView extends JInternalFrame {
    private JPanel panelTop;
    private JLabel lblTitulo;
    private JPanel panelCenter;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JPanel panelMenor;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnEliminar;
    private JPanel panelAll;
    private MensajeInternacionalizacionHandler i18n;

    public ProductoEliminarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(320, 230);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        aplicarIdioma();
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

    public void aplicarIdioma() {

        setTitle(i18n.get("producto.eliminar.titulo"));
        lblTitulo.setText(i18n.get("producto.eliminar.lbltitulo"));
        lblCodigo.setText(i18n.get("producto.eliminar.lbl.codigo"));
        lblNombre.setText(i18n.get("producto.eliminar.lbl.nombre"));
        lblPrecio.setText(i18n.get("producto.eliminar.lbl.precio"));
        btnBuscar.setText(i18n.get("producto.eliminar.btn.buscar"));
        btnEliminar.setText(i18n.get("producto.eliminar.btn.eliminar"));
    }

    public JPanel getPanelTop() {
        return panelTop;
    }

    public void setPanelTop(JPanel panelTop) {
        this.panelTop = panelTop;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
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

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JPanel getPanelMenor() {
        return panelMenor;
    }

    public void setPanelMenor(JPanel panelMenor) {
        this.panelMenor = panelMenor;
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

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JPanel getPanelAll() {
        return panelAll;
    }

    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}
