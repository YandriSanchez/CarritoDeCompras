package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;

/**
 * Clase PreguntasValidacionView.
 * <p>Vista gráfica para validar la identidad del usuario mediante preguntas de seguridad.
 * Permite ingresar respuestas, mostrar nuevas contraseñas, cambiar de pregunta
 * y aplicar la internacionalización para varios idiomas.
 * Incluye paneles, campos de texto, etiquetas y botones para gestionar la recuperación de cuenta.
 */
public class PreguntasValidacionView extends JFrame{

    private JPanel panelAll;
    private JPanel panelSuperior;
    private JLabel lblPreguntaSeguridad;
    private JTextField txtPregunta;
    private JPanel panelForm;
    private JButton btnEnviar;
    private JButton btnClean;
    private JTextField txtNuevaContra;
    private JLabel lblNuevaContra;
    private JLabel lblTitulo;
    private JComboBox cbxPreguntas;
    private JTextField txtRespuestaSeguidad;
    private JLabel lblQuestion;
    private JButton btnsiguientePregunta;
    private JTextField txtRespuestComparar;
    private JLabel lblPregunta;
    private JComboBox cbxIdioma;
    private JLabel lblIdioma;
    private JButton btnExit;
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor de PreguntasValidacionView.
     *
     * @param usuario   Usuario que está realizando la validación
     * @param usuarioDAO DAO para acceder a datos de usuarios
     * @param i18n      Manejador de internacionalización
     */
    public PreguntasValidacionView(Usuario usuario, UsuarioDAO usuarioDAO, MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setTitle("YANDRI STORE");
        setContentPane(panelAll);
        setSize(410, 350);
        setLocationRelativeTo(null);
        lblNuevaContra.setVisible(false);
        txtNuevaContra.setVisible(false);
        aplicarIdiomas();
    }

    /**
     * Muestra un mensaje emergente.
     *
     * @param mensaje Mensaje a mostrar
     * @param titulo  Título de la ventana
     * @param tipo    Tipo de mensaje (informativo, advertencia, etc.)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un cuadro de diálogo de confirmación.
     *
     * @param mensaje Mensaje del cuadro de diálogo
     * @param titulo  Título del cuadro
     * @param tipo    Tipo de mensaje
     * @return opción seleccionada por el usuario
     */
    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Aplica la traducción de textos según el idioma actual.
     */
    public void aplicarIdiomas() {
        setTitle(i18n.get("producto.aplicar.idiomas"));
        btnExit.setText(i18n.get("register.btnSalir"));
        lblIdioma.setText(i18n.get("login.lblIdioma"));
        lblTitulo.setText(i18n.get("preguntas.validacion.lbl.titulo"));
        lblPreguntaSeguridad.setText(i18n.get("preguntas.validacion.lbll.pregunta"));
        btnsiguientePregunta.setText(i18n.get("preguntas.validacion.btn.siguiente"));
        btnEnviar.setText(i18n.get("preguntas.validacion.btn.enviar"));
        btnClean.setText(i18n.get("preguntas.validacion.btn.limpiar"));
        lblNuevaContra.setText(i18n.get("preguntas.validacion.lbl.nueva.contra"));
        actualizarOpcionesIdioma();
    }

    /**
     * Actualiza las opciones del combo box de idiomas.
     */
    public void actualizarOpcionesIdioma() {
        String code = i18n.getCodigoIdioma();
        cbxIdioma.removeAllItems();
        cbxIdioma.addItem(i18n.get("idioma.espanol"));
        cbxIdioma.addItem(i18n.get("idioma.ingles"));
        cbxIdioma.addItem(i18n.get("idioma.frances"));

        int idx = 0;
        if (code.equals("en")) idx = 1;
        else if (code.equals("fr")) idx = 2;
        cbxIdioma.setSelectedIndex(idx);
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
     * Obtiene el panel superior.
     * @return panel superior
     */
    public JPanel getPanelSuperior() { return panelSuperior; }

    /**
     * Establece el panel superior.
     * @param panelSuperior nuevo panel superior
     */
    public void setPanelSuperior(JPanel panelSuperior) { this.panelSuperior = panelSuperior; }

    /**
     * Obtiene la etiqueta de pregunta de seguridad.
     * @return etiqueta de pregunta de seguridad
     */
    public JLabel getLblPreguntaSeguridad() { return lblPreguntaSeguridad; }

    /**
     * Establece la etiqueta de pregunta de seguridad.
     * @param lblPreguntaSeguridad nueva etiqueta
     */
    public void setLblPreguntaSeguridad(JLabel lblPreguntaSeguridad) { this.lblPreguntaSeguridad = lblPreguntaSeguridad; }

    /**
     * Obtiene el campo de texto para la pregunta.
     * @return campo de texto de la pregunta
     */
    public JTextField getTxtPregunta() { return txtPregunta; }

    /**
     * Establece el campo de texto para la pregunta.
     * @param txtPregunta nuevo campo de texto
     */
    public void setTxtPregunta(JTextField txtPregunta) { this.txtPregunta = txtPregunta; }

    /**
     * Obtiene el panel del formulario.
     * @return panel del formulario
     */
    public JPanel getPanelForm() { return panelForm; }

    /**
     * Establece el panel del formulario.
     * @param panelForm nuevo panel del formulario
     */
    public void setPanelForm(JPanel panelForm) { this.panelForm = panelForm; }

    /**
     * Obtiene el botón enviar.
     * @return botón enviar
     */
    public JButton getBtnEnviar() { return btnEnviar; }

    /**
     * Establece el botón enviar.
     * @param btnEnviar nuevo botón enviar
     */
    public void setBtnEnviar(JButton btnEnviar) { this.btnEnviar = btnEnviar; }

    /**
     * Obtiene el botón limpiar.
     * @return botón limpiar
     */
    public JButton getBtnClean() { return btnClean; }

    /**
     * Establece el botón limpiar.
     * @param btnClean nuevo botón limpiar
     */
    public void setBtnClean(JButton btnClean) { this.btnClean = btnClean; }

    /**
     * Obtiene el campo de nueva contraseña.
     * @return campo de nueva contraseña
     */
    public JTextField getTxtNuevaContra() { return txtNuevaContra; }

    /**
     * Establece el campo de nueva contraseña.
     * @param txtNuevaContra nuevo campo
     */
    public void setTxtNuevaContra(JTextField txtNuevaContra) { this.txtNuevaContra = txtNuevaContra; }

    /**
     * Obtiene la etiqueta de nueva contraseña.
     * @return etiqueta de nueva contraseña
     */
    public JLabel getLblNuevaContra() { return lblNuevaContra; }

    /**
     * Establece la etiqueta de nueva contraseña.
     * @param lblNuevaContra nueva etiqueta
     */
    public void setLblNuevaContra(JLabel lblNuevaContra) { this.lblNuevaContra = lblNuevaContra; }

    /**
     * Obtiene la etiqueta del título.
     * @return etiqueta del título
     */
    public JLabel getLblTitulo() { return lblTitulo; }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo nueva etiqueta del título
     */
    public void setLblTitulo(JLabel lblTitulo) { this.lblTitulo = lblTitulo; }

    /**
     * Obtiene el combo box de preguntas.
     * @return combo box de preguntas
     */
    public JComboBox getCbxPreguntas() { return cbxPreguntas; }

    /**
     * Establece el combo box de preguntas.
     * @param cbxPreguntas nuevo combo box
     */
    public void setCbxPreguntas(JComboBox cbxPreguntas) { this.cbxPreguntas = cbxPreguntas; }

    /**
     * Obtiene el campo de respuesta de seguridad.
     * @return campo de respuesta de seguridad
     */
    public JTextField getTxtRespuestaSeguidad() { return txtRespuestaSeguidad; }

    /**
     * Establece el campo de respuesta de seguridad.
     * @param txtRespuestaSeguidad nuevo campo
     */
    public void setTxtRespuestaSeguidad(JTextField txtRespuestaSeguidad) { this.txtRespuestaSeguidad = txtRespuestaSeguidad; }

    /**
     * Obtiene la etiqueta de pregunta.
     * @return etiqueta de pregunta
     */
    public JLabel getLblQuestion() { return lblQuestion; }

    /**
     * Establece la etiqueta de pregunta.
     * @param lblQuestion nueva etiqueta
     */
    public void setLblQuestion(JLabel lblQuestion) { this.lblQuestion = lblQuestion; }

    /**
     * Obtiene el botón siguiente pregunta.
     * @return botón siguiente pregunta
     */
    public JButton getBtnsiguientePregunta() { return btnsiguientePregunta; }

    /**
     * Establece el botón siguiente pregunta.
     * @param btnsiguientePregunta nuevo botón
     */
    public void setBtnsiguientePregunta(JButton btnsiguientePregunta) { this.btnsiguientePregunta = btnsiguientePregunta; }

    /**
     * Obtiene el campo para comparar respuesta.
     * @return campo de comparación de respuesta
     */
    public JTextField getTxtRespuestComparar() { return txtRespuestComparar; }

    /**
     * Establece el campo para comparar respuesta.
     * @param txtRespuestComparar nuevo campo
     */
    public void setTxtRespuestComparar(JTextField txtRespuestComparar) { this.txtRespuestComparar = txtRespuestComparar; }

    /**
     * Obtiene la etiqueta pregunta.
     * @return etiqueta pregunta
     */
    public JLabel getLblPregunta() { return lblPregunta; }

    /**
     * Establece la etiqueta pregunta.
     * @param lblPregunta nueva etiqueta
     */
    public void setLblPregunta(JLabel lblPregunta) { this.lblPregunta = lblPregunta; }

    /**
     * Obtiene el combo box de idioma.
     * @return combo box de idioma
     */
    public JComboBox getCbxIdioma() { return cbxIdioma; }

    /**
     * Establece el combo box de idioma.
     * @param cbxIdioma nuevo combo box
     */
    public void setCbxIdioma(JComboBox cbxIdioma) { this.cbxIdioma = cbxIdioma; }

    /**
     * Obtiene la etiqueta de idioma.
     * @return etiqueta de idioma
     */
    public JLabel getLblIdioma() { return lblIdioma; }

    /**
     * Establece la etiqueta de idioma.
     * @param lblIdioma nueva etiqueta
     */
    public void setLblIdioma(JLabel lblIdioma) { this.lblIdioma = lblIdioma; }

    /**
     * Obtiene el botón salir.
     * @return botón salir
     */
    public JButton getBtnExit() { return btnExit; }

    /**
     * Establece el botón salir.
     * @param btnExit nuevo botón salir
     */
    public void setBtnExit(JButton btnExit) { this.btnExit = btnExit; }

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
