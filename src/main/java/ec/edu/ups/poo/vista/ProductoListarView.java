package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Producto;
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
 * Clase ProductoListarView.
 * <p>
 * Vista gráfica para listar productos en una tabla con opciones
 * para buscar y listar todos los productos.
 * Incluye configuración de internacionalización y estilos para la tabla.
 */
public class ProductoListarView extends JInternalFrame{

    private JPanel panelCenter;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JButton btnListar;
    private JPanel panelButtom;
    private JTable tableProdcuts;
    private JPanel panelTop;
    private JLabel lblTitulo;
    private JPanel panelAll;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor que inicializa la vista, configura la tabla,
     * la internacionalización y parámetros básicos de la ventana.
     *
     * @param i18n Manejador de internacionalización para la interfaz.
     */
    public ProductoListarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(405, 270);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        cargarTabla();
        aplicarIdioma();
    }

    /**
     * Muestra una lista de productos en la tabla.
     * Formatea el precio según la configuración regional.
     *
     * @param productos Lista de productos a mostrar.
     */
    public void mostrarProductos(List<Producto> productos) {
        modelo.setRowCount(0);
        Locale locale = i18n.getLocale();
        for (Producto producto : productos) {
            String precioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);
            modelo.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    precioFormateado
            });
        }
    }

    /**
     * Muestra un mensaje emergente en la interfaz.
     *
     * @param mensaje Texto del mensaje.
     * @param titulo  Título de la ventana del mensaje.
     * @param tipo    Tipo de mensaje (información, error, etc.).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Aplica los textos según el idioma configurado en los
     * componentes de la interfaz, incluyendo los encabezados de tabla.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("producto.listar.titulo"));
        lblTitulo.setText(i18n.get("producto.listar.lbl.titulo"));
        lblNombre.setText(i18n.get("producto.listar.lbl.nombre"));
        btnBuscar.setText(i18n.get("producto.listar.btn.buscar"));
        btnListar.setText(i18n.get("producto.listar.btn.listar"));
        tableProdcuts.getColumnModel().getColumn(0).setHeaderValue(i18n.get("producto.listar.columna.codigo"));
        tableProdcuts.getColumnModel().getColumn(1).setHeaderValue(i18n.get("producto.listar.columna.nombre"));
        tableProdcuts.getColumnModel().getColumn(2).setHeaderValue(i18n.get("producto.listar.columna.precio"));
        tableProdcuts.getTableHeader().repaint();
    }

    /**
     * Configura la tabla de productos, estableciendo modelo,
     * estilo, colores y alineación de texto.
     */
    public void cargarTabla(){
        modelo = new DefaultTableModel(new Object[]{"Codigo", "Nombre", "Precio"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableProdcuts.setModel(modelo);

        Color fondo = new Color(132, 148, 229);
        Color letras = Color.BLACK;

        if (scroll == null) {
            scroll = (JScrollPane) tableProdcuts.getParent().getParent();
        }
        scroll.getViewport().setBackground(fondo);
        scroll.setBackground(fondo);

        tableProdcuts.setBackground(fondo);
        tableProdcuts.setForeground(letras);
        tableProdcuts.setSelectionBackground(new Color(50, 50, 60));
        tableProdcuts.setSelectionForeground(Color.WHITE);
        tableProdcuts.setGridColor(fondo);

        JTableHeader header = tableProdcuts.getTableHeader();
        header.setBackground(fondo);
        header.setForeground(letras);
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setForeground(letras);
        centerRenderer.setBackground(fondo);
        for (int i = 0; i < tableProdcuts.getColumnCount(); i++) {
            tableProdcuts.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * @return el panel central donde están los controles principales de búsqueda y listado.
     */
    public JPanel getPanelCenter() {
        return panelCenter;
    }

    /**
     * Establece el panel central donde estarán los controles principales.
     *
     * @param panelCenter nuevo panel central a asignar.
     */
    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    /**
     * @return la etiqueta del campo de texto para ingresar el nombre.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Establece la etiqueta que indica el campo de texto para el nombre.
     *
     * @param lblNombre nueva etiqueta para el nombre.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * @return el campo de texto donde se escribe el nombre del producto a buscar.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Establece el campo de texto para el nombre del producto.
     *
     * @param txtNombre nuevo campo de texto para el nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * @return el botón que permite ejecutar la acción de búsqueda.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el botón para realizar la búsqueda.
     *
     * @param btnBuscar nuevo botón para búsqueda.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * @return el botón que permite listar todos los productos.
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * Establece el botón para listar todos los productos.
     *
     * @param btnListar nuevo botón para listar productos.
     */
    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    /**
     * @return el panel inferior donde pueden estar otros controles o botones.
     */
    public JPanel getPanelButtom() {
        return panelButtom;
    }

    /**
     * Establece el panel inferior que contiene controles o botones.
     *
     * @param panelButtom nuevo panel inferior.
     */
    public void setPanelButtom(JPanel panelButtom) {
        this.panelButtom = panelButtom;
    }

    /**
     * @return la tabla que muestra la lista de productos.
     */
    public JTable getTableProdcuts() {
        return tableProdcuts;
    }

    /**
     * Establece la tabla que muestra los productos.
     *
     * @param tableProdcuts nueva tabla de productos.
     */
    public void setTableProdcuts(JTable tableProdcuts) {
        this.tableProdcuts = tableProdcuts;
    }

    /**
     * @return el panel superior donde puede estar el título u otros controles.
     */
    public JPanel getPanelTop() {
        return panelTop;
    }

    /**
     * Establece el panel superior.
     *
     * @param panelTop nuevo panel superior.
     */
    public void setPanelTop(JPanel panelTop) {
        this.panelTop = panelTop;
    }

    /**
     * @return la etiqueta del título principal de la ventana.
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título principal.
     *
     * @param lblTitulo nueva etiqueta de título.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * @return el panel principal que contiene todos los componentes de la ventana.
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Establece el panel principal que contiene todos los componentes.
     *
     * @param panelAll nuevo panel principal.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * @return el JScrollPane que envuelve la tabla de productos.
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     * Establece el JScrollPane que envuelve la tabla.
     *
     * @param scroll nuevo JScrollPane.
     */
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    /**
     * @return el modelo de datos de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo de datos para la tabla.
     *
     * @param modelo nuevo modelo de tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * @return el manejador de internacionalización utilizado para los textos.
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * Establece el manejador de internacionalización para los textos.
     *
     * @param i18n nuevo manejador de internacionalización.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}