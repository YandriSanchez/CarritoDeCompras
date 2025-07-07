package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.List;

public class ProductoEliminarView extends JInternalFrame {

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
    private JPanel JSuperior;
    private JLabel lblTitle;
    private final MensajeInternacionalizacionHandler tipoIdioma;

    public ProductoEliminarView(MensajeInternacionalizacionHandler tipoIdioma) {
        this.tipoIdioma = tipoIdioma;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(320, 230);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        aplicarIdioma();
    }

    public void mostrarProductos(List<Producto> productos) {
        txtNombre.setText(productos.get(0).getNombre());
        txtPrecio.setText(String.valueOf(productos.get(0).getPrecio()));
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {tipoIdioma.get("mensaje.confirmacion"), tipoIdioma.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void aplicarIdioma() {

        setTitle(tipoIdioma.get("producto.eliminar.titulo"));
        lblTitle.setText(tipoIdioma.get("producto.eliminar.lbltitulo"));
        lblCodigo.setText(tipoIdioma.get("producto.eliminar.lbl.codigo"));
        lblNombre.setText(tipoIdioma.get("producto.eliminar.lbl.nombre"));
        lblPrecio.setText(tipoIdioma.get("producto.eliminar.lbl.precio"));
        btnBuscar.setText(tipoIdioma.get("producto.eliminar.btn.buscar"));
        btnEliminar.setText(tipoIdioma.get("producto.eliminar.btn.eliminar"));
    }

    public JPanel getJSuperior() {
        return JSuperior;
    }
    public void setJSuperior(JPanel JSuperior) {
        this.JSuperior = JSuperior;
    }
    public JLabel getLblTitle() {
        return lblTitle;
    }
    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
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

}
