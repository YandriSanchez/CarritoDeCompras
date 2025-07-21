package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.io.File;

/**
 * Clase LogInView.
 * <p>Vista principal de la aplicación para el inicio de sesión.
 * Incluye campos para usuario, contraseña, selección de idioma y ubicación de guardado,
 * además de botones para iniciar sesión, registrarse, salir y recuperar contraseña.
 * Proporciona funcionalidades para seleccionar directorio, mostrar mensajes,
 * y actualizar opciones de idioma e internacionalización.
 */
public class LogInView extends JFrame {
    private JPanel panelAll;
    private JTextField txtUserName;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JButton btnInicioSesion;
    private JButton btnSalir;
    private JPasswordField txtContrasena;
    private JButton btnRegistro;
    private JPanel panelFinal;
    private JPanel panelCentro;
    private JPanel panelArriba;
    private JLabel lblTitulo;
    private JComboBox cbxIdioma;
    private JLabel lblIdioma;
    private JButton btnRecuContra;
    private JLabel lblUbicacionGuardar;
    private JComboBox cbxUbicacionGuardar;
    private JButton btnUbicacion;
    private JLabel lblRuta;
    private JTextField txtRuta;
    private MensajeInternacionalizacionHandler i18n;
    private String look;

    /**
     * Constructor de LogInView.
     *
     * @param i18n Manejador de internacionalización para la vista
     */
    public LogInView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(485, 395);
        setLocationRelativeTo(null);

        aplicarIdioma();
        actualizarOpcionesGuardado();
    }

    /**
     * Permite al usuario seleccionar un directorio desde un cuadro de diálogo.
     */
    public void seleccionarDirectorio() {
        JFileChooser fileChooser = new JFileChooser();

        String userHome = System.getProperty("user.home");
        File desktopDir = new File(userHome, "Desktop");
        fileChooser.setCurrentDirectory(desktopDir);

        fileChooser.setDialogTitle(i18n.get("select.directory"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File directorioSeleccionado = fileChooser.getSelectedFile();
            String ruta = directorioSeleccionado.getAbsolutePath();
            txtRuta.setText(ruta);
        }
    }

    /**
     * Muestra un mensaje emergente.
     *
     * @param mensaje Texto del mensaje
     * @param titulo  Título de la ventana
     * @param tipo    Tipo de mensaje (informativo, error, etc.)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    /**
     * Muestra un mensaje de confirmación con botones personalizados.
     *
     * @param mensaje Texto del mensaje
     * @param titulo  Título de la ventana
     * @param tipo    Tipo de mensaje
     * @return opción seleccionada por el usuario
     */
    public int mostrarMensajeAlert(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    /**
     * Actualiza las opciones del combo de idiomas según el idioma actual.
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
     * Actualiza las opciones del combo de ubicación de guardado.
     */
    public void actualizarOpcionesGuardado() {
        int selected = cbxUbicacionGuardar.getSelectedIndex();

        cbxUbicacionGuardar.removeAllItems();
        cbxUbicacionGuardar.addItem(i18n.get("dao.memoria"));
        cbxUbicacionGuardar.addItem(i18n.get("archivo.texto"));
        cbxUbicacionGuardar.addItem(i18n.get("archivo.binario"));

        if (selected >= 0 && selected < cbxUbicacionGuardar.getItemCount()) {
            cbxUbicacionGuardar.setSelectedIndex(selected);
        } else {
            cbxUbicacionGuardar.setSelectedIndex(0);
        }
    }

    /**
     * Aplica el idioma a todos los componentes de la vista.
     */
    public void aplicarIdioma() {
        setTitle(i18n.get("login.title"));
        lblTitulo.setText(i18n.get("login.lblTitulo"));
        lblIdioma.setText(i18n.get("login.lblIdioma"));
        lblUsuario.setText(i18n.get("login.lblUsuario"));
        lblContrasena.setText(i18n.get("login.lblContrasena"));
        lblUbicacionGuardar.setText(i18n.get("login.lblUbicacionGuardar"));
        lblRuta.setText(i18n.get("archivo.ruta"));
        btnUbicacion.setText(i18n.get("login.btnUbicacion"));
        btnInicioSesion.setText(i18n.get("login.btnInicioSesion"));
        btnSalir.setText(i18n.get("login.btnSalir"));
        btnRegistro.setText(i18n.get("login.btnRegistro"));
        btnRecuContra.setText(i18n.get("login.btnRecuContra"));
        actualizarOpcionesIdioma();
        actualizarOpcionesGuardado();
    }

    /**
     * Obtiene el campo de texto para la ruta.
     * @return campo de texto de la ruta
     */
    public JTextField getTxtRuta() { return txtRuta; }

    /**
     * Establece el campo de texto para la ruta.
     * @param txtRuta nuevo campo de texto de la ruta
     */
    public void setTxtRuta(JTextField txtRuta) { this.txtRuta = txtRuta; }

    /**
     * Obtiene la etiqueta de ruta.
     * @return etiqueta de ruta
     */
    public JLabel getLblRuta() { return lblRuta; }

    /**
     * Establece la etiqueta de ruta.
     * @param lblRuta nueva etiqueta de ruta
     */
    public void setLblRuta(JLabel lblRuta) { this.lblRuta = lblRuta; }

    /**
     * Obtiene el botón para seleccionar ubicación.
     * @return botón de ubicación
     */
    public JButton getBtnUbicacion() { return btnUbicacion; }

    /**
     * Establece el botón para seleccionar ubicación.
     * @param btnUbicacion nuevo botón de ubicación
     */
    public void setBtnUbicacion(JButton btnUbicacion) { this.btnUbicacion = btnUbicacion; }

    /**
     * Obtiene la etiqueta de ubicación de guardado.
     * @return etiqueta de ubicación de guardado
     */
    public JLabel getLblUbicacionGuardar() { return lblUbicacionGuardar; }

    /**
     * Establece la etiqueta de ubicación de guardado.
     * @param lblUbicacionGuardar nueva etiqueta
     */
    public void setLblUbicacionGuardar(JLabel lblUbicacionGuardar) { this.lblUbicacionGuardar = lblUbicacionGuardar; }

    /**
     * Obtiene el combo box de ubicación de guardado.
     * @return combo box de ubicación de guardado
     */
    public JComboBox getCbxUbicacionGuardar() { return cbxUbicacionGuardar; }

    /**
     * Establece el combo box de ubicación de guardado.
     * @param cbxUbicacionGuardar nuevo combo box
     */
    public void setCbxUbicacionGuardar(JComboBox cbxUbicacionGuardar) { this.cbxUbicacionGuardar = cbxUbicacionGuardar; }

    /**
     * Obtiene el botón para recuperar contraseña.
     * @return botón de recuperar contraseña
     */
    public JButton getBtnRecuContra() { return btnRecuContra; }

    /**
     * Establece el botón para recuperar contraseña.
     * @param btnRecuContra nuevo botón
     */
    public void setBtnRecuContra(JButton btnRecuContra) { this.btnRecuContra = btnRecuContra; }

    /**
     * Obtiene el campo de texto de usuario.
     * @return campo de texto de usuario
     */
    public JTextField getTxtUserName() { return txtUserName; }

    /**
     * Establece el campo de texto de usuario.
     * @param txtUserName nuevo campo de texto
     */
    public void setTxtUserName(JTextField txtUserName) { this.txtUserName = txtUserName; }

    /**
     * Obtiene el campo de contraseña.
     * @return campo de contraseña
     */
    public JPasswordField getTxtContrasena() { return txtContrasena; }

    /**
     * Establece el campo de contraseña.
     * @param txtContrasena nuevo campo de contraseña
     */
    public void setTxtContrasena(JPasswordField txtContrasena) { this.txtContrasena = txtContrasena; }

    /**
     * Obtiene el botón para iniciar sesión.
     * @return botón de inicio de sesión
     */
    public JButton getBtnLogIn() { return btnInicioSesion; }

    /**
     * Establece el botón para iniciar sesión.
     * @param btnLogIn nuevo botón de inicio de sesión
     */
    public void setBtnLogIn(JButton btnLogIn) { this.btnInicioSesion = btnLogIn; }

    /**
     * Obtiene el botón para salir.
     * @return botón de salir
     */
    public JButton getBtnExit() { return btnSalir; }

    /**
     * Establece el botón para salir.
     * @param btnExit nuevo botón de salir
     */
    public void setBtnExit(JButton btnExit) { this.btnSalir = btnExit; }

    /**
     * Obtiene el botón para registrarse.
     * @return botón de registro
     */
    public JButton getBtnRegister() { return btnRegistro; }

    /**
     * Establece el botón para registrarse.
     * @param btnRegister nuevo botón de registro
     */
    public void setBtnRegister(JButton btnRegister) { this.btnRegistro = btnRegister; }

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
     * Obtiene el panel final.
     * @return panel final
     */
    public JPanel getPanelFinal() { return panelFinal; }

    /**
     * Establece el panel final.
     * @param panelFinal nuevo panel final
     */
    public void setPanelFinal(JPanel panelFinal) { this.panelFinal = panelFinal; }

    /**
     * Obtiene el panel central.
     * @return panel central
     */
    public JPanel getPanelCentro() { return panelCentro; }

    /**
     * Establece el panel central.
     * @param panelCentro nuevo panel central
     */
    public void setPanelCentro(JPanel panelCentro) { this.panelCentro = panelCentro; }

    /**
     * Obtiene el panel superior.
     * @return panel superior
     */
    public JPanel getPanelArriba() { return panelArriba; }

    /**
     * Establece el panel superior.
     * @param panelArriba nuevo panel superior
     */
    public void setPanelArriba(JPanel panelArriba) { this.panelArriba = panelArriba; }

    /**
     * Obtiene la etiqueta de usuario.
     * @return etiqueta de usuario
     */
    public JLabel getLblUsername() { return lblUsuario; }

    /**
     * Establece la etiqueta de usuario.
     * @param lblUsername nueva etiqueta
     */
    public void setLblUsername(JLabel lblUsername) { this.lblUsuario = lblUsername; }

    /**
     * Obtiene la etiqueta de contraseña.
     * @return etiqueta de contraseña
     */
    public JLabel getLblContrasena() { return lblContrasena; }

    /**
     * Establece la etiqueta de contraseña.
     * @param lblContrasena nueva etiqueta
     */
    public void setLblContrasena(JLabel lblContrasena) { this.lblContrasena = lblContrasena; }

    /**
     * Obtiene la etiqueta del título.
     * @return etiqueta del título
     */
    public JLabel getTxtSettingsProduc() { return lblTitulo; }

    /**
     * Establece la etiqueta del título.
     * @param txtSettingsProduc nueva etiqueta del título
     */
    public void setTxtSettingsProduc(JLabel txtSettingsProduc) { this.lblTitulo = txtSettingsProduc; }

    /**
     * Obtiene el combo box de idiomas.
     * @return combo box de idiomas
     */
    public JComboBox getCbxIdioma() { return cbxIdioma; }

    /**
     * Establece el combo box de idiomas.
     * @param cbxIdioma nuevo combo box de idiomas
     */
    public void setCbxIdioma(JComboBox cbxIdioma) { this.cbxIdioma = cbxIdioma; }

    /**
     * Obtiene la etiqueta de idioma.
     * @return etiqueta de idioma
     */
    public JLabel getLblIdioma() { return lblIdioma; }

    /**
     * Establece la etiqueta de idioma.
     * @param lblIdioma nueva etiqueta de idioma
     */
    public void setLblIdioma(JLabel lblIdioma) { this.lblIdioma = lblIdioma; }
}