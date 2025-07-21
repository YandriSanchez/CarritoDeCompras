package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;

/**
 * Vista interna para eliminar usuarios.
 * Contiene campos para mostrar datos del usuario seleccionado y botones para buscar y eliminar.
 * Soporta internacionalización para adaptar los textos según el idioma.
 */
public class UsuarioEliminarView extends JInternalFrame {
    private JPanel panelAll;
    private JLabel lblTitulo;
    private JPanel panelSuperioir;
    private JPanel panelCentral;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btnEliminar;
    private JPanel panelInferioir;
    private JButton btnBuscar;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblRol;
    private JTextField txtRol;
    private JLabel lblNombreCompleto;
    private JTextField txtNombreCompleto;
    private JLabel lblFechaNacimiento;
    private JTextField txtDia;
    private JTextField txtMes;
    private JTextField txtAnio;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor que inicializa la vista, configura la ventana y aplica la internacionalización.
     *
     * @param i18n manejador de internacionalización para los textos.
     */
    public UsuarioEliminarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(610, 390);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        aplicarIdioma();
    }

    /**
     * Muestra un mensaje emergente tipo JOptionPane con un mensaje, título y tipo.
     *
     * @param mensaje el texto a mostrar.
     * @param titulo el título del diálogo.
     * @param tipo el tipo de mensaje (información, error, etc.).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un cuadro de diálogo de confirmación con opciones de confirmar o cancelar.
     *
     * @param mensaje el texto del mensaje de confirmación.
     * @param titulo el título del diálogo.
     * @param tipo el tipo de mensaje.
     * @return entero indicando la opción seleccionada por el usuario.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Aplica los textos traducidos a los componentes gráficos de la interfaz según el idioma actual.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("usuario.listar.titulo"));
        lblTitulo.setText(i18n.get("usuario.eliminar.titulo"));
        lblUsuario.setText(i18n.get("usuario.eliminar.lbl.usuario"));
        lblContrasena.setText(i18n.get("usuario.eliminar.lbl.contrasena"));
        lblRol.setText(i18n.get("usuario.eliminar.lbl.rol"));
        btnBuscar.setText(i18n.get("usuario.eliminar.btn.buscar"));
        btnEliminar.setText(i18n.get("usuario.eliminar.btn.eliminar"));
        lblNombreCompleto.setText(i18n.get("usuario.eliminar.lbl.nombreCompleto"));
        lblFechaNacimiento.setText(i18n.get("usuario.eliminar.lbl.fechaNacimiento"));
        lblTelefono.setText(i18n.get("usuario.eliminar.lbl.telefono"));
        lblCorreo.setText(i18n.get("usuario.eliminar.lbl.correo"));

    }

    /**
     * @return el panel principal que contiene todos los componentes.
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * @param panelAll nuevo panel principal.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * @return la etiqueta del título.
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * @param lblTitulo nueva etiqueta del título.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * @return el panel superior.
     */
    public JPanel getPanelSuperioir() {
        return panelSuperioir;
    }

    /**
     * @param panelSuperioir nuevo panel superior.
     */
    public void setPanelSuperioir(JPanel panelSuperioir) {
        this.panelSuperioir = panelSuperioir;
    }

    /**
     * @return el panel central.
     */
    public JPanel getPanelCentral() {
        return panelCentral;
    }

    /**
     * @param panelCentral nuevo panel central.
     */
    public void setPanelCentral(JPanel panelCentral) {
        this.panelCentral = panelCentral;
    }

    /**
     * @return el campo de texto para el usuario.
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * @param txtUsuario nuevo campo de texto para usuario.
     */
    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    /**
     * @return el campo de texto para la contraseña.
     */
    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    /**
     * @param txtContrasena nuevo campo de texto para contraseña.
     */
    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    /**
     * @return el botón para eliminar usuario.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * @param btnEliminar nuevo botón eliminar.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * @return el panel inferior.
     */
    public JPanel getPanelInferioir() {
        return panelInferioir;
    }

    /**
     * @param panelInferioir nuevo panel inferior.
     */
    public void setPanelInferioir(JPanel panelInferioir) {
        this.panelInferioir = panelInferioir;
    }

    /**
     * @return el botón para buscar usuario.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * @param btnBuscar nuevo botón buscar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * @return la etiqueta usuario.
     */
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    /**
     * @param lblUsuario nueva etiqueta usuario.
     */
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    /**
     * @return la etiqueta contraseña.
     */
    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    /**
     * @param lblContrasena nueva etiqueta contraseña.
     */
    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    /**
     * @return la etiqueta rol.
     */
    public JLabel getLblRol() {
        return lblRol;
    }

    /**
     * @param lblRol nueva etiqueta rol.
     */
    public void setLblRol(JLabel lblRol) {
        this.lblRol = lblRol;
    }

    /**
     * @return el campo de texto para rol.
     */
    public JTextField getTxtRol() {
        return txtRol;
    }

    /**
     * @param txtRol nuevo campo de texto para rol.
     */
    public void setTxtRol(JTextField txtRol) {
        this.txtRol = txtRol;
    }

    /**
     * @return la etiqueta nombre completo.
     */
    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    /**
     * @param lblNombreCompleto nueva etiqueta nombre completo.
     */
    public void setLblNombreCompleto(JLabel lblNombreCompleto) {
        this.lblNombreCompleto = lblNombreCompleto;
    }

    /**
     * @return el campo de texto para nombre completo.
     */
    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    /**
     * @param txtNombreCompleto nuevo campo de texto para nombre completo.
     */
    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    /**
     * @return la etiqueta fecha de nacimiento.
     */
    public JLabel getLblFechaNacimiento() {
        return lblFechaNacimiento;
    }

    /**
     * @param lblFechaNacimiento nueva etiqueta fecha de nacimiento.
     */
    public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
        this.lblFechaNacimiento = lblFechaNacimiento;
    }

    /**
     * @return el campo de texto para día de nacimiento.
     */
    public JTextField getTxtDia() {
        return txtDia;
    }

    /**
     * @param txtDia nuevo campo de texto para día.
     */
    public void setTxtDia(JTextField txtDia) {
        this.txtDia = txtDia;
    }

    /**
     * @return el campo de texto para mes de nacimiento.
     */
    public JTextField getTxtMes() {
        return txtMes;
    }

    /**
     * @param txtMes nuevo campo de texto para mes.
     */
    public void setTxtMes(JTextField txtMes) {
        this.txtMes = txtMes;
    }

    /**
     * @return el campo de texto para año de nacimiento.
     */
    public JTextField getTxtAnio() {
        return txtAnio;
    }

    /**
     * @param txtAnio nuevo campo de texto para año.
     */
    public void setTxtAnio(JTextField txtAnio) {
        this.txtAnio = txtAnio;
    }

    /**
     * @return la etiqueta teléfono.
     */
    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    /**
     * @param lblTelefono nueva etiqueta teléfono.
     */
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    /**
     * @return la etiqueta correo.
     */
    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    /**
     * @param lblCorreo nueva etiqueta correo.
     */
    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    /**
     * @return el campo de texto para teléfono.
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * @param txtTelefono nuevo campo de texto para teléfono.
     */
    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    /**
     * @return el campo de texto para correo.
     */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    /**
     * @param txtCorreo nuevo campo de texto para correo.
     */
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    /**
     * @return el manejador de internacionalización.
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * @param i18n nuevo manejador de internacionalización.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}
