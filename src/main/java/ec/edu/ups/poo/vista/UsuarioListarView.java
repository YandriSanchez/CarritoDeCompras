package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.Rol;
import ec.edu.ups.poo.modelo.Usuario;
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
 * Clase que representa la vista para listar usuarios en una ventana interna.
 * Permite mostrar una tabla con los usuarios, filtrar por rol y nombre de usuario,
 * y aplicar internacionalización a la interfaz.
 */
public class UsuarioListarView extends JInternalFrame {

    private JPanel panelSuperio;
    private JButton btnListar;
    private JPanel panelCentral;
    private JTable tableUsers;
    private JPanel panelAll;
    private JScrollPane scroll;
    private JPanel panelItulo;
    private JLabel lblTitulo;
    private JButton btnBuscar;
    private JTextField txtUsuario;
    private JComboBox<Object> cbxRol;
    private JLabel lblUsuario;
    private JLabel lblRol;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor que inicializa la vista con los componentes principales,
     * carga la tabla, carga los roles disponibles y aplica el idioma seleccionado.
     *
     * @param i18n el manejador de internacionalización para traducir los textos.
     */
    public UsuarioListarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(595, 295);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        cargarTabla();
        cargarRol();
        aplicaraIdioma();
    }

    /**
     * Actualiza la tabla de usuarios con una lista de objetos Usuario.
     * Formatea la fecha de nacimiento según el locale de la internacionalización.
     *
     * @param usuarios lista de usuarios a mostrar en la tabla.
     */
    public void mostrarUsuarios(List<Usuario> usuarios) {
        modelo.setRowCount(0);
        Locale locale = i18n.getLocale();
        for (Usuario usuario : usuarios) {
            String fechaFormateada = "";
            if (usuario.getFechaNacimiento() != null) {
                fechaFormateada = FormateadorUtils.formatearFecha(usuario.getFechaNacimiento(), locale);
            }
            modelo.addRow(new Object[]{
                    usuario.getCedula(),
                    usuario.getContrasena(),
                    usuario.getNombreCompleto(),
                    fechaFormateada,
                    usuario.getCorreo(),
                    usuario.getTelefono(),
                    usuario.getRol().name()
            });
        }
    }

    /**
     * Muestra un mensaje emergente tipo JOptionPane con un mensaje, título y tipo.
     *
     * @param mensaje el texto del mensaje a mostrar.
     * @param titulo el título de la ventana del mensaje.
     * @param tipo el tipo de mensaje (información, error, etc.).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Aplica los textos traducidos a los componentes de la interfaz según el idioma actual.
     * Actualiza etiquetas, botones y encabezados de tabla.
     */
    public void aplicaraIdioma() {
        setTitle(i18n.get("usuario.listar.title"));
        lblTitulo.setText(i18n.get("usuario.listar.titulo"));
        lblUsuario.setText(i18n.get("usuario.listar.lbl.usuario"));
        lblRol.setText(i18n.get("usuario.listar.lbl.rol"));
        btnBuscar.setText(i18n.get("usuario.listar.btn.buscar"));
        btnListar.setText(i18n.get("usuario.listar.btn.listar"));
        tableUsers.getColumnModel().getColumn(0).setHeaderValue(i18n.get("usuario.listar.table.usuario"));
        tableUsers.getColumnModel().getColumn(1).setHeaderValue(i18n.get("usuario.listar.table.contrasena"));
        tableUsers.getColumnModel().getColumn(2).setHeaderValue(i18n.get("usuario.listar.table.nombreCompleto"));
        tableUsers.getColumnModel().getColumn(3).setHeaderValue(i18n.get("usuario.listar.table.fechaNacimiento"));
        tableUsers.getColumnModel().getColumn(4).setHeaderValue(i18n.get("usuario.listar.table.correo"));
        tableUsers.getColumnModel().getColumn(5).setHeaderValue(i18n.get("usuario.listar.table.telefono"));
        tableUsers.getColumnModel().getColumn(6).setHeaderValue(i18n.get("usuario.listar.table.rol"));
        tableUsers.getTableHeader().repaint();
    }

    /**
     * Carga las opciones del combo box de roles con todos los valores del enum Rol,
     * agregando una opción "Todos" al inicio para permitir mostrar todos los roles.
     */
    public void cargarRol(){
        if (cbxRol != null) {
            cbxRol.removeAllItems();
            cbxRol.addItem("Todos");
            for (Rol rol : Rol.values()) {
                cbxRol.addItem(rol);
            }
        }
    }

    /**
     * Configura y carga el modelo de la tabla de usuarios, estableciendo columnas,
     * deshabilitando edición de celdas y aplicando estilos visuales personalizados.
     */
    public void cargarTabla() {
        modelo = new DefaultTableModel(
                new Object[]{
                        "Usuario",
                        "Contrasena",
                        "Nombre Completo",
                        "Fecha Nacimiento",
                        "Correo",
                        "Telefono",
                        "Rol"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableUsers.setModel(modelo);

        tableUsers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        Color fondo = new Color(132, 148, 229);
        Color letras = Color.WHITE;

        if (scroll == null && tableUsers != null) {
            scroll = (JScrollPane) tableUsers.getParent().getParent();
        }
        if (scroll != null) {
            scroll.getViewport().setBackground(fondo);
            scroll.setBackground(fondo);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }

        if (tableUsers != null) {
            tableUsers.setBackground(fondo);
            tableUsers.setForeground(letras);
            tableUsers.setSelectionBackground(new Color(50, 50, 60));
            tableUsers.setSelectionForeground(Color.WHITE);
            tableUsers.setGridColor(fondo);

            JTableHeader header = tableUsers.getTableHeader();
            header.setBackground(fondo);
            header.setForeground(letras);
            header.setFont(header.getFont().deriveFont(Font.BOLD));
            ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setForeground(letras);
            centerRenderer.setBackground(fondo);
            for (int i = 0; i < tableUsers.getColumnCount(); i++) {
                tableUsers.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

    }

    /**
     * Obtiene el panel superior de la ventana.
     * @return el panel superior (panelSuperio).
     */
    public JPanel getPanelSuperio() {
        return panelSuperio;
    }

    /**
     * Establece el panel superior de la ventana.
     * @param panelSuperio el nuevo panel superior.
     */
    public void setPanelSuperio(JPanel panelSuperio) {
        this.panelSuperio = panelSuperio;
    }

    /**
     * Obtiene el botón para listar usuarios.
     * @return el botón btnListar.
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * Establece el botón para listar usuarios.
     * @param btnListar el nuevo botón btnListar.
     */
    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    /**
     * Obtiene el panel central de la ventana.
     * @return el panel central (panelCentral).
     */
    public JPanel getPanelCentral() {
        return panelCentral;
    }

    /**
     * Establece el panel central de la ventana.
     * @param panelCentral el nuevo panel central.
     */
    public void setPanelCentral(JPanel panelCentral) {
        this.panelCentral = panelCentral;
    }

    /**
     * Obtiene la tabla que muestra los usuarios.
     * @return la tabla tableUsers.
     */
    public JTable getTableUsers() {
        return tableUsers;
    }

    /**
     * Establece la tabla que muestra los usuarios.
     * @param tableUsers la nueva tabla tableUsers.
     */
    public void setTableUsers(JTable tableUsers) {
        this.tableUsers = tableUsers;
    }

    /**
     * Obtiene el panel que contiene todos los componentes.
     * @return el panelAll.
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Establece el panel que contiene todos los componentes.
     * @param panelAll el nuevo panelAll.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * Obtiene el JScrollPane que contiene la tabla.
     * @return el JScrollPane scroll.
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     * Establece el JScrollPane que contiene la tabla.
     * @param scroll el nuevo JScrollPane scroll.
     */
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    /**
     * Obtiene el panel del título.
     * @return el panelItulo.
     */
    public JPanel getPanelItulo() {
        return panelItulo;
    }

    /**
     * Establece el panel del título.
     * @param panelItulo el nuevo panelItulo.
     */
    public void setPanelItulo(JPanel panelItulo) {
        this.panelItulo = panelItulo;
    }

    /**
     * Obtiene la etiqueta del título.
     * @return el JLabel lblTitulo.
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo el nuevo JLabel lblTitulo.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * Obtiene el botón para buscar usuarios.
     * @return el botón btnBuscar.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el botón para buscar usuarios.
     * @param btnBuscar el nuevo botón btnBuscar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Obtiene el campo de texto para ingresar el nombre de usuario a buscar.
     * @return el JTextField txtUsuario.
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * Establece el campo de texto para ingresar el nombre de usuario a buscar.
     * @param txtUsuario el nuevo JTextField txtUsuario.
     */
    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    /**
     * Obtiene el combo box para seleccionar el rol del usuario.
     * @return el JComboBox<Object> cbxRol.
     */
    public JComboBox<Object> getCbxRol() {
        return cbxRol;
    }

    /**
     * Establece el combo box para seleccionar el rol del usuario.
     * @param cbxRol el nuevo JComboBox<Object> cbxRol.
     */
    public void setCbxRol(JComboBox<Object> cbxRol) {
        this.cbxRol = cbxRol;
    }

    /**
     * Obtiene la etiqueta del campo usuario.
     * @return el JLabel lblUsuario.
     */
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    /**
     * Establece la etiqueta del campo usuario.
     * @param lblUsuario el nuevo JLabel lblUsuario.
     */
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    /**
     * Obtiene la etiqueta del campo rol.
     * @return el JLabel lblRol.
     */
    public JLabel getLblRol() {
        return lblRol;
    }

    /**
     * Establece la etiqueta del campo rol.
     * @param lblRol el nuevo JLabel lblRol.
     */
    public void setLblRol(JLabel lblRol) {
        this.lblRol = lblRol;
    }

    /**
     * Obtiene el modelo de la tabla de usuarios.
     * @return el DefaultTableModel modelo.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo de la tabla de usuarios.
     * @param modelo el nuevo DefaultTableModel modelo.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el manejador de internacionalización.
     * @return el MensajeInternacionalizacionHandler i18n.
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * Establece el manejador de internacionalización.
     * @param i18n el nuevo MensajeInternacionalizacionHandler i18n.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}

