package ec.edu.ups.poo.vista;


import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoEditarView extends JInternalFrame {
    private JPanel panelAll;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTextField txtNombre;
    private JLabel lblCodigo;
    private JPanel panelCenter;
    private JPanel panelTop;
    private JTextField txtPrecio;
    private JButton btnActualizar;
    private JLabel lblPrecio;
    private JLabel lblNombre;
    private JPanel panelMenor;
    private JLabel lblTitulo;
    private JScrollPane scroll;
    private MensajeInternacionalizacionHandler i18n;

    public ProductoEditarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(315, 220);
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
        setTitle(i18n.get("producto.editar.titulo"));
        lblTitulo.setText(i18n.get("producto.editar.lbl.titulo"));
        lblCodigo.setText(i18n.get("producto.editar.lbl.codigo"));
        lblNombre.setText(i18n.get("producto.editar.lbl.nombre"));
        lblPrecio.setText(i18n.get("producto.editar.lbl.precio"));
        btnBuscar.setText(i18n.get("producto.editar.btn.buscar"));
        btnActualizar.setText(i18n.get("producto.editar.btn.actualizar"));
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

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    public JPanel getPanelTop() {
        return panelTop;
    }

    public void setPanelTop(JPanel panelTop) {
        this.panelTop = panelTop;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JPanel getPanelMenor() {
        return panelMenor;
    }

    public void setPanelMenor(JPanel panelMenor) {
        this.panelMenor = panelMenor;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}