package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.util.List;

/**
 * Clase ProductoEliminarView.
 * <p>Vista gráfica para eliminar productos en la aplicación.
 * Permite buscar un producto por código, mostrar sus datos, eliminarlo,
 * y aplicar textos según el idioma configurado.
 */
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

    /**
     * Constructor que inicializa la vista, configura el diseño
     * y aplica la internacionalización.
     *
     * @param i18n Manejador de internacionalización
     */
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

    /**
     * Muestra un mensaje emergente al usuario.
     *
     * @param mensaje Mensaje que se mostrará
     * @param titulo  Título de la ventana del mensaje
     * @param tipo    Tipo de mensaje (información, error, advertencia, etc.)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un cuadro de confirmación y devuelve la opción elegida.
     *
     * @param mensaje Mensaje a mostrar
     * @param titulo  Título del cuadro de confirmación
     * @param tipo    Tipo de mensaje
     * @return Entero con la opción seleccionada
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Aplica los textos a los componentes de la vista según el idioma configurado.
     */
    public void aplicarIdioma() {

        setTitle(i18n.get("producto.eliminar.titulo"));
        lblTitulo.setText(i18n.get("producto.eliminar.lbltitulo"));
        lblCodigo.setText(i18n.get("producto.eliminar.lbl.codigo"));
        lblNombre.setText(i18n.get("producto.eliminar.lbl.nombre"));
        lblPrecio.setText(i18n.get("producto.eliminar.lbl.precio"));
        btnBuscar.setText(i18n.get("producto.eliminar.btn.buscar"));
        btnEliminar.setText(i18n.get("producto.eliminar.btn.eliminar"));
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
     * Muestra la información de un producto en los campos de texto.
     *
     * @param productos Lista de productos a mostrar (se usa el primero)
     */
    public void mostrarProductos(List<Producto> productos) {
        txtNombre.setText(productos.get(0).getNombre());
        txtPrecio.setText(String.valueOf(productos.get(0).getPrecio()));
    }

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
     * Obtiene el botón para buscar productos.
     * @return botón buscar
     */
    public JButton getBtnBuscar() { return btnBuscar; }

    /**
     * Establece el botón para buscar productos.
     * @param btnBuscar nuevo botón buscar
     */
    public void setBtnBuscar(JButton btnBuscar) { this.btnBuscar = btnBuscar; }

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
     * Obtiene el botón para eliminar productos.
     * @return botón eliminar
     */
    public JButton getBtnEliminar() { return btnEliminar; }

    /**
     * Establece el botón para eliminar productos.
     * @param btnEliminar nuevo botón eliminar
     */
    public void setBtnEliminar(JButton btnEliminar) { this.btnEliminar = btnEliminar; }

    /**
     * Obtiene el panel principal que contiene todos los elementos.
     * @return panel principal
     */
    public JPanel getPanelAll() { return panelAll; }

    /**
     * Establece el panel principal que contiene todos los elementos.
     * @param panelAll nuevo panel principal
     */
    public void setPanelAll(JPanel panelAll) { this.panelAll = panelAll; }

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
