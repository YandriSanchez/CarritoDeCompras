package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.util.List;

/**
 * Clase ProductoEditarView.
 * <p>Vista gráfica para editar productos en la aplicación.
 * Permite buscar un producto, actualizar su información, mostrar mensajes y aplicar internacionalización.
 */
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

    /**
     * Constructor que inicializa la vista y aplica los textos según el idioma.
     *
     * @param i18n Manejador de internacionalización
     */
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
     * Limpia los campos de texto de la vista.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra los datos de un producto en los campos de texto.
     *
     * @param productos Lista de productos a mostrar (se muestra solo el primero)
     */
    public void mostrarProductos(List<Producto> productos) {
        txtNombre.setText(productos.get(0).getNombre());
        txtPrecio.setText(String.valueOf(productos.get(0).getPrecio()));
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
     * Aplica los textos de la vista según el idioma configurado.
     */
    public void aplicarIdiomas() {
        setTitle(i18n.get("producto.editar.titulo"));
        lblTitulo.setText(i18n.get("producto.editar.lbl.titulo"));
        lblCodigo.setText(i18n.get("producto.editar.lbl.codigo"));
        lblNombre.setText(i18n.get("producto.editar.lbl.nombre"));
        lblPrecio.setText(i18n.get("producto.editar.lbl.precio"));
        btnBuscar.setText(i18n.get("producto.editar.btn.buscar"));
        btnActualizar.setText(i18n.get("producto.editar.btn.actualizar"));
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
     * Obtiene el botón buscar.
     * @return botón buscar
     */
    public JButton getBtnBuscar() { return btnBuscar; }

    /**
     * Establece el botón buscar.
     * @param btnBuscar nuevo botón buscar
     */
    public void setBtnBuscar(JButton btnBuscar) { this.btnBuscar = btnBuscar; }

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
     * Obtiene el botón actualizar.
     * @return botón actualizar
     */
    public JButton getBtnActualizar() { return btnActualizar; }

    /**
     * Establece el botón actualizar.
     * @param btnActualizar nuevo botón actualizar
     */
    public void setBtnActualizar(JButton btnActualizar) { this.btnActualizar = btnActualizar; }

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
     * Obtiene el panel menor.
     * @return panel menor
     */
    public JPanel getPanelMenor() { return panelMenor; }

    /**
     * Establece el panel menor.
     * @param panelMenor nuevo panel menor
     */
    public void setPanelMenor(JPanel panelMenor) { this.panelMenor = panelMenor; }

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
     * Obtiene el scroll.
     * @return scroll
     */
    public JScrollPane getScroll() { return scroll; }

    /**
     * Establece el scroll.
     * @param scroll nuevo scroll
     */
    public void setScroll(JScrollPane scroll) { this.scroll = scroll; }

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