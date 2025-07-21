package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Vista para el registro de usuarios en la aplicación YANDRI STORE.
 * Esta clase extiende JFrame y proporciona una interfaz gráfica
 * que permite al usuario ingresar sus datos personales, credenciales,
 * preguntas de seguridad y seleccionar opciones relacionadas.
 *
 * Incluye soporte para internacionalización a través de
 * MensajeInternacionalizacionHandler.
 *
 * @author Yandri Eduardo Sanchez YAnza
 * @version 2.0
 */
public class RegisterView extends JFrame {
    private JPanel panelAll;
    private JPanel panelSuperior;
    private JLabel lblTitulo;
    private JPanel panelCentral;
    private JTextField txtCedula;
    private JTextField txtContrasena;
    private JButton btnRegistro;
    private JButton btnClean;
    private JButton btnSalir;
    private JPanel panelInferior;
    private JLabel lblCedula;
    private JLabel lblContrasena;
    private JPanel panelForm;
    private JLabel lblPregunta1;
    private JTextField txtPregunta1;
    private JTextField txtPregunta2;
    private JTextField txtPregunta3;
    private JLabel lblPregunta2;
    private JLabel lblPregunta3;
    private JTextField txtNombreCompleto;
    private JLabel lblNombreCompleto;
    private JLabel lblFechaNacimiento;
    private JComboBox cbxDia;
    private JComboBox cbxMes;
    private JComboBox cbxAnio;
    private JTextField txtCorreo;
    private JLabel lblCorreo;
    private JTextField txtTelefono;
    private JLabel lblTelefono;
    private JLabel lblIdioma;
    private JComboBox cbxIdioma;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor que inicializa la vista de registro con soporte para internacionalización.
     * Configura título, tamaño, ubicación, aplica idioma y llena combos de fecha de nacimiento.
     *
     * @param i18n instancia de MensajeInternacionalizacionHandler para manejo multilenguaje.
     */
    public RegisterView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setTitle("YANDRI STORE");
        setContentPane(panelAll);
        setSize(710, 450);
        setLocationRelativeTo(null);
        aplicarIdioma();
        inicializarCombosFechaNacimiento();
    }

    /**
     * Actualiza las opciones del combo de idioma y selecciona el idioma activo.
     */
    public void actualizarOpcionesIdioma() {
        String code = i18n.getCodigoIdioma();
        cbxIdioma.removeAllItems();
        cbxIdioma.addItem("Español");
        cbxIdioma.addItem("Ingles");
        cbxIdioma.addItem("Frances");

        int idx = 0;
        if (code.equals("en")) idx = 1;
        else if (code.equals("fr")) idx = 2;
        cbxIdioma.setSelectedIndex(idx);
    }

    /**
     * Muestra un mensaje emergente informativo o de alerta.
     *
     * @param mensaje texto a mostrar.
     * @param titulo título del cuadro de mensaje.
     * @param tipo tipo de mensaje (JOptionPane constant).
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un diálogo de confirmación con opciones personalizadas.
     *
     * @param mensaje texto de confirmación.
     * @param titulo título del diálogo.
     * @param tipo tipo de mensaje (JOptionPane constant).
     * @return opción seleccionada por el usuario.
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Aplica el idioma actual a todos los textos y etiquetas visibles.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("register.title"));
        lblTitulo.setText(i18n.get("register.title"));
        lblCedula.setText(i18n.get("register.lblUsuario"));
        lblContrasena.setText(i18n.get("register.lblContrasena"));
        lblNombreCompleto.setText(i18n.get("register.lblNombreCompleto"));
        lblFechaNacimiento.setText(i18n.get("register.lblFechaNacimiento"));
        lblCorreo.setText(i18n.get("register.lblCorreo"));
        lblTelefono.setText(i18n.get("register.lblTelefono"));
        lblTelefono.setText(i18n.get("register.lblTelefono"));
        lblPregunta1.setText(i18n.get("register.lblPregunta1"));
        lblPregunta2.setText(i18n.get("register.lblPregunta2"));
        lblPregunta3.setText(i18n.get("register.lblPregunta3"));
        btnRegistro.setText(i18n.get("register.btnRegistro"));
        btnClean.setText(i18n.get("register.btnClean"));
        btnSalir.setText(i18n.get("register.btnSalir"));
        actualizarOpcionesIdioma();

    }

    /**
     * Obtiene la fecha de nacimiento seleccionada en los combos.
     *
     * @return objeto Date con la fecha o null si hay error.
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
     * Inicializa los combos de día, mes y año para la selección de fecha de nacimiento.
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
     * Obtiene el panel principal que contiene toda la vista.
     * @return panelAll
     */
    public JPanel getPanelAll() {
        return panelAll;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelAll panel a asignar
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
     * Establece el panel superior de la vista.
     * @param panelSuperior panel a asignar
     */
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Obtiene la etiqueta de título.
     * @return lblTitulo
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta de título.
     * @param lblTitulo etiqueta a asignar
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * Obtiene el panel central del formulario.
     * @return panelCentral
     */
    public JPanel getPanelCentral() {
        return panelCentral;
    }

    /**
     * Establece el panel central del formulario.
     * @param panelCentral panel a asignar
     */
    public void setPanelCentral(JPanel panelCentral) {
        this.panelCentral = panelCentral;
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
     * @param txtCedula campo a asignar
     */
    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
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
     * @param txtContrasena campo a asignar
     */
    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    /**
     * Obtiene el botón para registrar.
     * @return btnRegistro
     */
    public JButton getBtnRegistro() {
        return btnRegistro;
    }

    /**
     * Establece el botón para registrar.
     * @param btnRegistro botón a asignar
     */
    public void setBtnRegistro(JButton btnRegistro) {
        this.btnRegistro = btnRegistro;
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
     * @param btnClean botón a asignar
     */
    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
    }

    /**
     * Obtiene el botón para salir.
     * @return btnSalir
     */
    public JButton getBtnSalir() {
        return btnSalir;
    }

    /**
     * Establece el botón para salir.
     * @param btnSalir botón a asignar
     */
    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
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
     * @param panelInferior panel a asignar
     */
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Obtiene la etiqueta para cédula.
     * @return lblCedula
     */
    public JLabel getLblCedula() {
        return lblCedula;
    }

    /**
     * Establece la etiqueta para cédula.
     * @param lblCedula etiqueta a asignar
     */
    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    /**
     * Obtiene la etiqueta para contraseña.
     * @return lblContrasena
     */
    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    /**
     * Establece la etiqueta para contraseña.
     * @param lblContrasena etiqueta a asignar
     */
    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    /**
     * Obtiene el panel del formulario.
     * @return panelForm
     */
    public JPanel getPanelForm() {
        return panelForm;
    }

    /**
     * Establece el panel del formulario.
     * @param panelForm panel a asignar
     */
    public void setPanelForm(JPanel panelForm) {
        this.panelForm = panelForm;
    }

    /**
     * Obtiene la etiqueta para la primera pregunta de seguridad.
     * @return lblPregunta1
     */
    public JLabel getLblPregunta1() {
        return lblPregunta1;
    }

    /**
     * Establece la etiqueta para la primera pregunta de seguridad.
     * @param lblPregunta1 etiqueta a asignar
     */
    public void setLblPregunta1(JLabel lblPregunta1) {
        this.lblPregunta1 = lblPregunta1;
    }

    /**
     * Obtiene el campo de texto para la primera pregunta.
     * @return txtPregunta1
     */
    public JTextField getTxtPregunta1() {
        return txtPregunta1;
    }

    /**
     * Establece el campo de texto para la primera pregunta.
     * @param txtPregunta1 campo a asignar
     */
    public void setTxtPregunta1(JTextField txtPregunta1) {
        this.txtPregunta1 = txtPregunta1;
    }

    /**
     * Obtiene el campo de texto para la segunda pregunta.
     * @return txtPregunta2
     */
    public JTextField getTxtPregunta2() {
        return txtPregunta2;
    }

    /**
     * Establece el campo de texto para la segunda pregunta.
     * @param txtPregunta2 campo a asignar
     */
    public void setTxtPregunta2(JTextField txtPregunta2) {
        this.txtPregunta2 = txtPregunta2;
    }

    /**
     * Obtiene el campo de texto para la tercera pregunta.
     * @return txtPregunta3
     */
    public JTextField getTxtPregunta3() {
        return txtPregunta3;
    }

    /**
     * Establece el campo de texto para la tercera pregunta.
     * @param txtPregunta3 campo a asignar
     */
    public void setTxtPregunta3(JTextField txtPregunta3) {
        this.txtPregunta3 = txtPregunta3;
    }

    /**
     * Obtiene la etiqueta para la segunda pregunta.
     * @return lblPregunta2
     */
    public JLabel getLblPregunta2() {
        return lblPregunta2;
    }

    /**
     * Establece la etiqueta para la segunda pregunta.
     * @param lblPregunta2 etiqueta a asignar
     */
    public void setLblPregunta2(JLabel lblPregunta2) {
        this.lblPregunta2 = lblPregunta2;
    }

    /**
     * Obtiene la etiqueta para la tercera pregunta.
     * @return lblPregunta3
     */
    public JLabel getLblPregunta3() {
        return lblPregunta3;
    }

    /**
     * Establece la etiqueta para la tercera pregunta.
     * @param lblPregunta3 etiqueta a asignar
     */
    public void setLblPregunta3(JLabel lblPregunta3) {
        this.lblPregunta3 = lblPregunta3;
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
     * @param txtNombreCompleto campo a asignar
     */
    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    /**
     * Obtiene la etiqueta para el nombre completo.
     * @return lblNombreCompleto
     */
    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    /**
     * Establece la etiqueta para el nombre completo.
     * @param lblNombreCompleto etiqueta a asignar
     */
    public void setLblNombreCompleto(JLabel lblNombreCompleto) {
        this.lblNombreCompleto = lblNombreCompleto;
    }

    /**
     * Obtiene la etiqueta para la fecha de nacimiento.
     * @return lblFechaNacimiento
     */
    public JLabel getLblFechaNacimiento() {
        return lblFechaNacimiento;
    }

    /**
     * Establece la etiqueta para la fecha de nacimiento.
     * @param lblFechaNacimiento etiqueta a asignar
     */
    public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
        this.lblFechaNacimiento = lblFechaNacimiento;
    }

    /**
     * Obtiene el combo de días para la fecha de nacimiento.
     * @return cbxDia
     */
    public JComboBox getCbxDia() {
        return cbxDia;
    }

    /**
     * Establece el combo de días.
     * @param cbxDia combo a asignar
     */
    public void setCbxDia(JComboBox cbxDia) {
        this.cbxDia = cbxDia;
    }

    /**
     * Obtiene el combo de meses para la fecha de nacimiento.
     * @return cbxMes
     */
    public JComboBox getCbxMes() {
        return cbxMes;
    }

    /**
     * Establece el combo de meses.
     * @param cbxMes combo a asignar
     */
    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    /**
     * Obtiene el combo de años para la fecha de nacimiento.
     * @return cbxAnio
     */
    public JComboBox getCbxAnio() {
        return cbxAnio;
    }

    /**
     * Establece el combo de años.
     * @param cbxAnio combo a asignar
     */
    public void setCbxAnio(JComboBox cbxAnio) {
        this.cbxAnio = cbxAnio;
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
     * @param txtCorreo campo a asignar
     */
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    /**
     * Obtiene la etiqueta para el correo electrónico.
     * @return lblCorreo
     */
    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    /**
     * Establece la etiqueta para el correo electrónico.
     * @param lblCorreo etiqueta a asignar
     */
    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
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
     * @param txtTelefono campo a asignar
     */
    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    /**
     * Obtiene la etiqueta para el teléfono.
     * @return lblTelefono
     */
    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    /**
     * Establece la etiqueta para el teléfono.
     * @param lblTelefono etiqueta a asignar
     */
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    /**
     * Obtiene la etiqueta para el idioma.
     * @return lblIdioma
     */
    public JLabel getLblIdioma() {
        return lblIdioma;
    }

    /**
     * Establece la etiqueta para el idioma.
     * @param lblIdioma etiqueta a asignar
     */
    public void setLblIdioma(JLabel lblIdioma) {
        this.lblIdioma = lblIdioma;
    }

    /**
     * Obtiene el combo de selección de idioma.
     * @return cbxIdioma
     */
    public JComboBox getCbxIdioma() {
        return cbxIdioma;
    }

    /**
     * Establece el combo de selección de idioma.
     * @param cbxIdioma combo a asignar
     */
    public void setCbxIdioma(JComboBox cbxIdioma) {
        this.cbxIdioma = cbxIdioma;
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
     * @param i18n manejador a asignar
     */
    public void setI18n(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
    }
}
