package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.util.List;

/**
 * Clase ProductoAnadirView.
 * <p>Vista gráfica para añadir productos en la aplicación.
 * Permite ingresar código, nombre y precio de un producto, gestionar campos,
 * mostrar mensajes de confirmación y aplicar internacionalización.
 */
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

    /**
     * Constructor que inicializa la vista y aplica los textos según el idioma.
     *
     * @param i18n Manejador de internacionalización
     */
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

    /**
     * Muestra un mensaje emergente.
     *
     * @param mensaje Mensaje a mostrar
     * @param titulo  Título del cuadro
     * @param tipo    Tipo de mensaje
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un cuadro de confirmación.
     *
     * @param mensaje Mensaje a mostrar
     * @param titulo  Título del cuadro
     * @param tipo    Tipo de mensaje
     * @return Opción seleccionada por el usuario
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Muestra por consola la lista de productos.
     *
     * @param productos Lista de productos a mostrar
     */
    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    /**
     * Limpia los campos de texto de la vista.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Inhabilita los campos de texto y el botón guardar.
     */
    public void inhabilitarCampos() {
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
        btnGuardar.setEnabled(false);
    }

    /**
     * Habilita los campos de texto y el botón guardar.
     */
    public void habilitarCampos() {
        txtNombre.setEnabled(true);
        txtPrecio.setEnabled(true);
        btnGuardar.setEnabled(true);
    }

    /**
     * Aplica los textos según el idioma configurado.
     */
    public void aplicarIdiomas() {
        setTitle(i18n.get("producto.anadir.titulo"));
        lblTitulo.setText(i18n.get("producto.anadir.lbl.titulo"));
        lblCodigo.setText(i18n.get("producto.anadir.lbl.codigo"));
        lblNombre.setText(i18n.get("producto.anadir.lbl.nombre"));
        lblPrecio.setText(i18n.get("producto.anadir.lbl.precio"));
        btnGuardar.setText(i18n.get("producto.anadir.btn.guardar"));
        btnNuevo.setText(i18n.get("producto.anadir.btn.nuevo"));
    }

    /**
     * Obtiene el panel principal.
     * @return panel principal
     */
    public JPanel getPanelAll() { return panelAll; }

    /**
     * Establece el panel principal.
     * @param panelAll nuevo panel principal
     */
    public void setPanelAll(JPanel panelAll) { this.panelAll = panelAll; }

    /**
     * Obtiene el campo de texto del código.
     * @return campo de texto del código
     */
    public JTextField getTxtCodigo() { return txtCodigo; }

    /**
     * Establece el campo de texto del código.
     * @param txtCodigo nuevo campo de texto
     */
    public void setTxtCodigo(JTextField txtCodigo) { this.txtCodigo = txtCodigo; }

    /**
     * Obtiene el botón guardar.
     * @return botón guardar
     */
    public JButton getBtnGuardar() { return btnGuardar; }

    /**
     * Establece el botón guardar.
     * @param btnGuardar nuevo botón guardar
     */
    public void setBtnGuardar(JButton btnGuardar) { this.btnGuardar = btnGuardar; }

    /**
     * Obtiene el botón nuevo.
     * @return botón nuevo
     */
    public JButton getBtnNuevo() { return btnNuevo; }

    /**
     * Establece el botón nuevo.
     * @param btnNuevo nuevo botón nuevo
     */
    public void setBtnNuevo(JButton btnNuevo) { this.btnNuevo = btnNuevo; }

    /**
     * Obtiene la etiqueta del código.
     * @return etiqueta del código
     */
    public JLabel getLblCodigo() { return lblCodigo; }

    /**
     * Establece la etiqueta del código.
     * @param lblCodigo nueva etiqueta
     */
    public void setLblCodigo(JLabel lblCodigo) { this.lblCodigo = lblCodigo; }

    /**
     * Obtiene la etiqueta del nombre.
     * @return etiqueta del nombre
     */
    public JLabel getLblNombre() { return lblNombre; }

    /**
     * Establece la etiqueta del nombre.
     * @param lblNombre nueva etiqueta
     */
    public void setLblNombre(JLabel lblNombre) { this.lblNombre = lblNombre; }

    /**
     * Obtiene la etiqueta del precio.
     * @return etiqueta del precio
     */
    public JLabel getLblPrecio() { return lblPrecio; }

    /**
     * Establece la etiqueta del precio.
     * @param lblPrecio nueva etiqueta
     */
    public void setLblPrecio(JLabel lblPrecio) { this.lblPrecio = lblPrecio; }

    /**
     * Obtiene el campo de texto del nombre.
     * @return campo de texto del nombre
     */
    public JTextField getTxtNombre() { return txtNombre; }

    /**
     * Establece el campo de texto del nombre.
     * @param txtNombre nuevo campo de texto
     */
    public void setTxtNombre(JTextField txtNombre) { this.txtNombre = txtNombre; }

    /**
     * Obtiene el campo de texto del precio.
     * @return campo de texto del precio
     */
    public JTextField getTxtPrecio() { return txtPrecio; }

    /**
     * Establece el campo de texto del precio.
     * @param txtPrecio nuevo campo de texto
     */
    public void setTxtPrecio(JTextField txtPrecio) { this.txtPrecio = txtPrecio; }

    /**
     * Obtiene el panel superior.
     * @return panel superior
     */
    public JPanel getPanelTop() { return panelTop; }

    /**
     * Establece el panel superior.
     * @param panelTop nuevo panel superior
     */
    public void setPanelTop(JPanel panelTop) { this.panelTop = panelTop; }

    /**
     * Obtiene el panel central.
     * @return panel central
     */
    public JPanel getPanelCenter() { return panelCenter; }

    /**
     * Establece el panel central.
     * @param panelCenter nuevo panel central
     */
    public void setPanelCenter(JPanel panelCenter) { this.panelCenter = panelCenter; }

    /**
     * Obtiene el panel inferior.
     * @return panel inferior
     */
    public JPanel getPanelBottom() { return panelBottom; }

    /**
     * Establece el panel inferior.
     * @param panelBottom nuevo panel inferior
     */
    public void setPanelBottom(JPanel panelBottom) { this.panelBottom = panelBottom; }

    /**
     * Obtiene la etiqueta del título.
     * @return etiqueta del título
     */
    public JLabel getLblTitulo() { return lblTitulo; }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo nueva etiqueta
     */
    public void setLblTitulo(JLabel lblTitulo) { this.lblTitulo = lblTitulo; }

    /**
     * Obtiene el manejador de internacionalización.
     * @return manejador de internacionalización
     */
    public MensajeInternacionalizacionHandler getI18n() { return i18n; }

    /**
     * Establece el manejador de internacionalización.
     * @param i18n nuevo manejador
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) { this.i18n = i18n; }
}