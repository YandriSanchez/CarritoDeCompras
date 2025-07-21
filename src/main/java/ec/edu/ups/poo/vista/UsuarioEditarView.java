package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Vista interna para editar usuarios.
 * Permite modificar datos del usuario y actualizar la información.
 * Soporta selección de fecha de nacimiento mediante combos para día, mes y año.
 * Incluye internacionalización para adaptar textos a diferentes idiomas.
 */
public class UsuarioEditarView extends JInternalFrame {
    private JPanel panelAll;
    private JPanel panelSuperior;
    private JLabel lblTitulo;
    private JTextField txtCedula;
    private JTextField txtContrasena;
    private JComboBox cbxRol;
    private JButton btnActualizar;
    private JButton btnClean;
    private JButton btnBuscar;
    private JLabel lblCedula;
    private JLabel lblContrasena;
    private JLabel lblRol;
    private JComboBox cbxDia;
    private JComboBox cbxMes;
    private JComboBox cbxAnio;
    private JLabel lblNombreCompleto;
    private JTextField txtNombreCompleto;
    private JLabel lblFechaNacimiento;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor que inicializa la vista, configura la ventana,
     * aplica la internacionalización e inicializa los combos de fecha de nacimiento.
     *
     * @param i18n manejador de internacionalización para los textos.
     */
    public UsuarioEditarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(570, 350);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        aplicarIdioma();
        inicializarCombosFechaNacimiento();

    }

    /**
     * Obtiene la fecha de nacimiento seleccionada en los combos (día, mes, año).
     *
     * @return objeto Date con la fecha seleccionada o null si no es válida.
     */
    public Date getFechaNacimiento() {
        try {
            int dia = Integer.parseInt(cbxDia.getSelectedItem().toString());
            int mes = Integer.parseInt(cbxMes.getSelectedItem().toString()) - 1;
            int anio = Integer.parseInt(cbxAnio.getSelectedItem().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.set(anio, mes, dia, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
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
        setTitle(i18n.get("usuario.editar.titulo"));
        lblTitulo.setText(i18n.get("usuario.editar.lbl.titulo"));
        lblCedula.setText(i18n.get("usuario.editar.lbl.usuario"));
        lblContrasena.setText(i18n.get("usuario.editar.lblcontrasena"));
        lblRol.setText(i18n.get("usuario.editar.lbl.rol"));
        btnActualizar.setText(i18n.get("usuario.editar.btn.actualizar"));
        btnClean.setText(i18n.get("usuario.editar.btn.limpiar"));
        btnBuscar.setText(i18n.get("usuario.editar.btn.buscar"));
        lblNombreCompleto.setText(i18n.get("usuario.editar.lbl.nombrecompleto"));
        lblFechaNacimiento.setText(i18n.get("usuario.editar.lbl.fechanacimiento"));
        lblCorreo.setText(i18n.get("usuario.editar.lbl.correo"));
        lblTelefono.setText(i18n.get("usuario.editar.lbl.telefono"));

    }

    /**
     * Inicializa los combos para seleccionar el día, mes y año de la fecha de nacimiento.
     */
    private void inicializarCombosFechaNacimiento() {

        cbxDia.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            cbxDia.addItem(String.valueOf(i));
        }

        cbxMes.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            cbxMes.addItem(String.valueOf(i));
        }

        cbxAnio.removeAllItems();
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anioActual; i >= 1900; i--) {
            cbxAnio.addItem(String.valueOf(i));
        }
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
     * @return el panel superior.
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * @param panelSuperior nuevo panel superior.
     */
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
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
     * @return el campo de texto para cédula.
     */
    public JTextField getTxtCedula() {
        return txtCedula;
    }

    /**
     * @param txtCedula nuevo campo de texto para cédula.
     */
    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    /**
     * @return el campo de texto para contraseña.
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
     * @return el combo para seleccionar el rol.
     */
    public JComboBox getCbxRol() {
        return cbxRol;
    }

    /**
     * @param cbxRol nuevo combo para seleccionar el rol.
     */
    public void setCbxRol(JComboBox cbxRol) {
        this.cbxRol = cbxRol;
    }

    /**
     * @return el botón para actualizar usuario.
     */
    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    /**
     * @param btnActualizar nuevo botón actualizar.
     */
    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    /**
     * @return el botón para limpiar campos.
     */
    public JButton getBtnClean() {
        return btnClean;
    }

    /**
     * @param btnClean nuevo botón limpiar.
     */
    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
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
     * @return la etiqueta para cédula.
     */
    public JLabel getLblCedula() {
        return lblCedula;
    }

    /**
     * @param lblCedula nueva etiqueta para cédula.
     */
    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    /**
     * @return la etiqueta para contraseña.
     */
    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    /**
     * @param lblContrasena nueva etiqueta para contraseña.
     */
    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    /**
     * @return la etiqueta para rol.
     */
    public JLabel getLblRol() {
        return lblRol;
    }

    /**
     * @param lblRol nueva etiqueta para rol.
     */
    public void setLblRol(JLabel lblRol) {
        this.lblRol = lblRol;
    }

    /**
     * @return el combo para seleccionar día de nacimiento.
     */
    public JComboBox getCbxDia() {
        return cbxDia;
    }

    /**
     * @param cbxDia nuevo combo para día.
     */
    public void setCbxDia(JComboBox cbxDia) {
        this.cbxDia = cbxDia;
    }

    /**
     * @return el combo para seleccionar mes de nacimiento.
     */
    public JComboBox getCbxMes() {
        return cbxMes;
    }

    /**
     * @param cbxMes nuevo combo para mes.
     */
    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    /**
     * @return el combo para seleccionar año de nacimiento.
     */
    public JComboBox getCbxAnio() {
        return cbxAnio;
    }

    /**
     * @param cbxAnio nuevo combo para año.
     */
    public void setCbxAnio(JComboBox cbxAnio) {
        this.cbxAnio = cbxAnio;
    }

    /**
     * @return la etiqueta para nombre completo.
     */
    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    /**
     * @param lblNombreCompleto nueva etiqueta para nombre completo.
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
     * @param txtNombreCompleto nuevo campo para nombre completo.
     */
    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    /**
     * @return la etiqueta para fecha de nacimiento.
     */
    public JLabel getLblFechaNacimiento() {
        return lblFechaNacimiento;
    }

    /**
     * @param lblFechaNacimiento nueva etiqueta para fecha de nacimiento.
     */
    public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
        this.lblFechaNacimiento = lblFechaNacimiento;
    }

    /**
     * @return la etiqueta para correo.
     */
    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    /**
     * @param lblCorreo nueva etiqueta para correo.
     */
    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    /**
     * @return el campo de texto para correo.
     */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    /**
     * @param txtCorreo nuevo campo para correo.
     */
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    /**
     * @return la etiqueta para teléfono.
     */
    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    /**
     * @param lblTelefono nueva etiqueta para teléfono.
     */
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    /**
     * @return el campo de texto para teléfono.
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * @param txtTelefono nuevo campo para teléfono.
     */
    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
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
