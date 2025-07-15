package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class UsuarioElimiarView extends JInternalFrame {
    private JPanel panelAll;
    private JLabel lblTitulo;
    private JPanel panelSuperioir;
    private JPanel panelCentro;
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

    public UsuarioElimiarView(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
        setContentPane(panelAll);
        setTitle("YANDRO STORE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(610, 390);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        aplicarIdioma();
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {i18n.get("mensaje.confirmacion"), i18n.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

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

    public JPanel getPanelAll() {
        return panelAll;
    }
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }
    public JLabel getLblTitulo() {
        return lblTitulo;
    }
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
    public JPanel getPanelSuperioir() {
        return panelSuperioir;
    }
    public void setPanelSuperioir(JPanel panelSuperioir) {
        this.panelSuperioir = panelSuperioir;
    }
    public JPanel getPanelCentral() {
        return panelCentro;
    }
    public void setPanelCentral(JPanel panelCentral) {
        this.panelCentro = panelCentral;
    }
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }
    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }
    public JTextField getTxtContrasena() {
        return txtContrasena;
    }
    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }
    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }
    public JPanel getPanelInferioir() {
        return panelInferioir;
    }
    public void setPanelInferioir(JPanel panelInferioir) {
        this.panelInferioir = panelInferioir;
    }
    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }
    public JLabel getLblUsuario() {
        return lblUsuario;
    }
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }
    public JLabel getLblContrasena() {
        return lblContrasena;
    }
    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }
    public JLabel getLblRol() {
        return lblRol;
    }
    public void setLblRol(JLabel lblRol) {
        this.lblRol = lblRol;
    }
    public JTextField getTxtRol() {
        return txtRol;
    }
    public void setTxtRol(JTextField txtRol) {
        this.txtRol = txtRol;
    }
    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }
    public void setLblNombreCompleto(JLabel lblNombreCompleto) {
        this.lblNombreCompleto = lblNombreCompleto;
    }
    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }
    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }
    public JLabel getLblFechaNacimiento() {
        return lblFechaNacimiento;
    }
    public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
        this.lblFechaNacimiento = lblFechaNacimiento;
    }
    public JTextField getTxtDia() {
        return txtDia;
    }
    public void setTxtDia(JTextField txtDia) {
        this.txtDia = txtDia;
    }
    public JTextField getTxtMes() {
        return txtMes;
    }
    public void setTxtMes(JTextField txtMes) {
        this.txtMes = txtMes;
    }
    public JTextField getTxtAnio() {
        return txtAnio;
    }
    public void setTxtAnio(JTextField txtAnio) {
        this.txtAnio = txtAnio;
    }
    public JLabel getLblTelefono() {
        return lblTelefono;
    }
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }
    public JLabel getLblCorreo() {
        return lblCorreo;
    }
    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }
    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }
    public MensajeInternacionalizacionHandler getI18n() {
        return i18n;
    }
    public void setI18n(MensajeInternacionalizacionHandler tipoIdioma) {
        this.i18n = tipoIdioma;
    }
}
