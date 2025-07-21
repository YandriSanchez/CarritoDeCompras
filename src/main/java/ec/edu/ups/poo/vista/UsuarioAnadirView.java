package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Vista interna para añadir usuarios en la aplicación "YANDRI STORE".
 * Permite registrar nuevos usuarios, limpiar campos y gestionar la información
 * de registro incluyendo datos personales y fecha de nacimiento.
 * Utiliza internacionalización para soportar múltiples idiomas.
 */
public class UsuarioAnadirView extends JInternalFrame {
    private JPanel panelAll;
    private JPanel panelSuperior;
    private JLabel lblTitulo;
    private JPanel panelCenter;
    private JTextField txtCedula;
    private JLabel lblCedula;
    private JTextField txtContrasena;
    private JLabel lblContrasena;
    private JComboBox cbxRol;
    private JButton btnRegistrar;
    private JButton btnClean;
    private JPanel panelInferior;
    private JLabel lblRol;
    private JTextField txtNombreCompleto;
    private JLabel lblNombreCompleto;
    private JLabel lblFechaNacimiento;
    private JComboBox cbxDia;
    private JComboBox cbxMes;
    private JComboBox cbxAnio;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JLabel lblCorreo;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor que inicializa la vista para añadir usuarios.
     * Configura componentes gráficos, aplica idioma actual e inicializa las listas desplegables de fecha.
     * @param i18n Manejador de internacionalización para soporte multilenguaje.
     */
    public UsuarioAnadirView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(780, 310);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        aplicarIdioma();
        inicializarCombosFechaNacimiento();
    }

    /**
     * Muestra un cuadro de diálogo simple con un mensaje.
     * @param mensaje El mensaje a mostrar.
     * @param titulo El título del cuadro de diálogo.
     * @param tipo Tipo de mensaje (informativo, advertencia, error, etc.).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un cuadro de confirmación con botones personalizados.
     * @param mensaje El mensaje a mostrar.
     * @param titulo El título del cuadro de diálogo.
     * @param tipo Tipo de mensaje (informativo, advertencia, etc.).
     * @return Índice del botón seleccionado por el usuario.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Obtiene la fecha de nacimiento seleccionada por el usuario a partir de los combos.
     * @return Fecha construida o null si ocurre un error.
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
     * Aplica los textos traducidos según el idioma actual.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("usuario.anadir.titulo"));
        lblTitulo.setText(i18n.get("usuario.anadir.lbl.titulo"));
        lblCedula.setText(i18n.get("usuario.anadir.lbl.usuario"));
        lblContrasena.setText(i18n.get("usuario.anadir.lbl.contrasena"));
        lblRol.setText(i18n.get("usuario.anadir.lbl.rol"));
        btnRegistrar.setText(i18n.get("usuario.anadir.btn.registrar"));
        btnClean.setText(i18n.get("usuario.anadir.btn.limpiar"));
        lblNombreCompleto.setText(i18n.get("usuario.anadir.lbl.nombre.completo"));
        lblFechaNacimiento.setText(i18n.get("usuario.anadir.lbl.fecha.nacimiento"));
        lblTelefono.setText(i18n.get("usuario.anadir.lbl.telefono"));
        lblCorreo.setText(i18n.get("usuario.anadir.lbl.correo"));

    }

    /**
     * Inicializa los combos de día, mes y año con valores posibles para fecha de nacimiento.
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
     * Obtiene el panel principal que contiene todos los componentes.
     * @return panelAll
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Establece el panel principal.
     * @param panelAll Nuevo panel principal.
     */
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }

    /**
     * Obtiene el panel superior de la vista.
     * @return panelSuperior
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Establece el panel superior.
     * @param panelSuperior Nuevo panel superior.
     */
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Obtiene la etiqueta del título.
     * @return lblTitulo
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo Nueva etiqueta del título.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * Obtiene el panel central donde se encuentran los campos principales.
     * @return panelCenter
     */
    public JPanel getPanelCenter() {
        return panelCenter;
    }

    /**
     * Establece el panel central.
     * @param panelCenter Nuevo panel central.
     */
    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    /**
     * Obtiene el campo de texto para la cédula.
     * @return txtCedula
     */
    public JTextField getTxtCedula() {
        return txtCedula;
    }

    /**
     * Establece el campo de texto para la cédula.
     * @param txtCedula Nuevo campo de texto para la cédula.
     */
    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    /**
     * Obtiene la etiqueta de cédula.
     * @return lblCedula
     */
    public JLabel getLblCedula() {
        return lblCedula;
    }

    /**
     * Establece la etiqueta de cédula.
     * @param lblCedula Nueva etiqueta de cédula.
     */
    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    /**
     * Obtiene el campo de texto para la contraseña.
     * @return txtContrasena
     */
    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    /**
     * Establece el campo de texto para la contraseña.
     * @param txtContrasena Nuevo campo de texto para la contraseña.
     */
    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    /**
     * Obtiene la etiqueta de contraseña.
     * @return lblContrasena
     */
    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    /**
     * Establece la etiqueta de contraseña.
     * @param lblContrasena Nueva etiqueta de contraseña.
     */
    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    /**
     * Obtiene el combo box de rol.
     * @return cbxRol
     */
    public JComboBox getCbxRol() {
        return cbxRol;
    }

    /**
     * Establece el combo box de rol.
     * @param cbxRol Nuevo combo box de rol.
     */
    public void setCbxRol(JComboBox cbxRol) {
        this.cbxRol = cbxRol;
    }

    /**
     * Obtiene el botón para registrar.
     * @return btnRegistrar
     */
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    /**
     * Establece el botón para registrar.
     * @param btnRegistrar Nuevo botón de registrar.
     */
    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    /**
     * Obtiene el botón para limpiar campos.
     * @return btnClean
     */
    public JButton getBtnClean() {
        return btnClean;
    }

    /**
     * Establece el botón para limpiar campos.
     * @param btnClean Nuevo botón de limpiar.
     */
    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
    }

    /**
     * Obtiene el panel inferior.
     * @return panelInferior
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Establece el panel inferior.
     * @param panelInferior Nuevo panel inferior.
     */
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Obtiene la etiqueta del rol.
     * @return lblRol
     */
    public JLabel getLblRol() {
        return lblRol;
    }

    /**
     * Establece la etiqueta del rol.
     * @param lblRol Nueva etiqueta del rol.
     */
    public void setLblRol(JLabel lblRol) {
        this.lblRol = lblRol;
    }

    /**
     * Obtiene el campo de texto para el nombre completo.
     * @return txtNombreCompleto
     */
    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    /**
     * Establece el campo de texto para el nombre completo.
     * @param txtNombreCompleto Nuevo campo de texto del nombre completo.
     */
    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    /**
     * Obtiene la etiqueta de nombre completo.
     * @return lblNombreCompleto
     */
    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    /**
     * Establece la etiqueta de nombre completo.
     * @param lblNombreCompleto Nueva etiqueta de nombre completo.
     */
    public void setLblNombreCompleto(JLabel lblNombreCompleto) {
        this.lblNombreCompleto = lblNombreCompleto;
    }

    /**
     * Obtiene la etiqueta de fecha de nacimiento.
     * @return lblFechaNacimiento
     */
    public JLabel getLblFechaNacimiento() {
        return lblFechaNacimiento;
    }

    /**
     * Establece la etiqueta de fecha de nacimiento.
     * @param lblFechaNacimiento Nueva etiqueta de fecha de nacimiento.
     */
    public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
        this.lblFechaNacimiento = lblFechaNacimiento;
    }

    /**
     * Obtiene el combo box de día.
     * @return cbxDia
     */
    public JComboBox getCbxDia() {
        return cbxDia;
    }

    /**
     * Establece el combo box de día.
     * @param cbxDia Nuevo combo box de día.
     */
    public void setCbxDia(JComboBox cbxDia) {
        this.cbxDia = cbxDia;
    }

    /**
     * Obtiene el combo box de mes.
     * @return cbxMes
     */
    public JComboBox getCbxMes() {
        return cbxMes;
    }

    /**
     * Establece el combo box de mes.
     * @param cbxMes Nuevo combo box de mes.
     */
    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    /**
     * Obtiene el combo box de año.
     * @return cbxAnio
     */
    public JComboBox getCbxAnio() {
        return cbxAnio;
    }

    /**
     * Establece el combo box de año.
     * @param cbxAnio Nuevo combo box de año.
     */
    public void setCbxAnio(JComboBox cbxAnio) {
        this.cbxAnio = cbxAnio;
    }

    /**
     * Obtiene la etiqueta de teléfono.
     * @return lblTelefono
     */
    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    /**
     * Establece la etiqueta de teléfono.
     * @param lblTelefono Nueva etiqueta de teléfono.
     */
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    /**
     * Obtiene el campo de texto para el teléfono.
     * @return txtTelefono
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * Establece el campo de texto para el teléfono.
     * @param txtTelefono Nuevo campo de texto para el teléfono.
     */
    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    /**
     * Obtiene el campo de texto para el correo electrónico.
     * @return txtCorreo
     */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    /**
     * Establece el campo de texto para el correo electrónico.
     * @param txtCorreo Nuevo campo de texto para el correo.
     */
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    /**
     * Obtiene la etiqueta de correo electrónico.
     * @return lblCorreo
     */
    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    /**
     * Establece la etiqueta de correo electrónico.
     * @param lblCorreo Nueva etiqueta de correo.
     */
    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    /**
     * Obtiene el manejador de internacionalización.
     * @return i18n
     */
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }

    /**
     * Establece el manejador de internacionalización.
     * @param i18n Nuevo manejador de internacionalización.
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}
