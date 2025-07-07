package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class RegisterView extends JFrame {
    private JPanel panelAll;
    private JPanel panelSuperior;
    private JLabel lblTitulo;
    private JPanel panelCentral;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btnRegistro;
    private JButton btnClean;
    private JButton btnSalir;
    private JPanel panelInferior;
    private JLabel lblUsuario;
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
    private MensajeInternacionalizacionHandler tipoIdioma;

    public RegisterView(MensajeInternacionalizacionHandler tipoIdioma) {
        this.tipoIdioma = tipoIdioma;
        setTitle("YANDRI STORE");
        setContentPane(panelAll);
        setSize(710, 450);
        setLocationRelativeTo(null);

        aplicarIdioma();
        inicializarCombosFechaNacimiento();
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public int mostrarMensajeConfirmacion(String mensaje, String titulo, int tipo) {
        Object[] botones = {tipoIdioma.get("mensaje.confirmacion"), tipoIdioma.get("mensaje.cancelacion")};
        return JOptionPane.showOptionDialog(
                this, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, tipo,
                null, botones, botones[0]);
    }

    public void aplicarIdioma() {
        setTitle(tipoIdioma.get("register.title"));
        lblTitulo.setText(tipoIdioma.get("register.lbltitulo"));
        lblUsuario.setText(tipoIdioma.get("register.lblUsuario"));
        lblContrasena.setText(tipoIdioma.get("register.lblContrasena"));
        lblNombreCompleto.setText(tipoIdioma.get("register.lblNombreCompleto"));
        lblFechaNacimiento.setText(tipoIdioma.get("register.lblFechaNacimiento"));
        lblCorreo.setText(tipoIdioma.get("register.lblCorreo"));
        lblTelefono.setText(tipoIdioma.get("register.lblTelefono"));
        lblTelefono.setText(tipoIdioma.get("register.lblTelefono"));
        lblPregunta1.setText(tipoIdioma.get("register.lblPregunta1"));
        lblPregunta2.setText(tipoIdioma.get("register.lblPregunta2"));
        lblPregunta3.setText(tipoIdioma.get("register.lblPregunta3"));
        btnRegistro.setText(tipoIdioma.get("register.btnRegistro"));
        btnClean.setText(tipoIdioma.get("register.btnClean"));
        btnSalir.setText(tipoIdioma.get("register.btnSalir"));

    }

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

    public JPanel getPanelAll() {
        return panelAll;
    }
    public void setPanelAll(JPanel panelAll) {
        this.panelAll = panelAll;
    }
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }
    public JLabel getLblTitulo() {
        return lblTitulo;
    }
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
    public JPanel getPanelCentral() {
        return panelCentral;
    }
    public void setPanelCentral(JPanel panelCentral) {
        this.panelCentral = panelCentral;
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
    public JButton getBtnRegistro() {
        return btnRegistro;
    }
    public void setBtnRegistro(JButton btnRegistro) {
        this.btnRegistro = btnRegistro;
    }
    public JButton getBtnClean() {
        return btnClean;
    }
    public void setBtnClean(JButton btnClean) {
        this.btnClean = btnClean;
    }
    public JButton getBtnSalir() {
        return btnSalir;
    }
    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }
    public JPanel getPanelInferior() {
        return panelInferior;
    }
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
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
    public JPanel getPanelForm() {
        return panelForm;
    }
    public void setPanelForm(JPanel panelForm) {
        this.panelForm = panelForm;
    }
    public JLabel getLblPregunta1() {
        return lblPregunta1;
    }
    public void setLblPregunta1(JLabel lblPregunta1) {
        this.lblPregunta1 = lblPregunta1;
    }
    public JTextField getTxtPregunta1() {
        return txtPregunta1;
    }
    public void setTxtPregunta1(JTextField txtPregunta1) {
        this.txtPregunta1 = txtPregunta1;
    }
    public JTextField getTxtPregunta2() {
        return txtPregunta2;
    }
    public void setTxtPregunta2(JTextField txtPregunta2) {
        this.txtPregunta2 = txtPregunta2;
    }
    public JTextField getTxtPregunta3() {
        return txtPregunta3;
    }
    public void setTxtPregunta3(JTextField txtPregunta3) {
        this.txtPregunta3 = txtPregunta3;
    }
    public JLabel getLblPregunta2() {
        return lblPregunta2;
    }
    public void setLblPregunta2(JLabel lblPregunta2) {
        this.lblPregunta2 = lblPregunta2;
    }
    public JLabel getLblPregunta3() {
        return lblPregunta3;
    }
    public void setLblPregunta3(JLabel lblPregunta3) {
        this.lblPregunta3 = lblPregunta3;
    }
    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }
    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }
    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }
    public void setLblNombreCompleto(JLabel lblNombreCompleto) {
        this.lblNombreCompleto = lblNombreCompleto;
    }
    public JLabel getLblFechaNacimiento() {
        return lblFechaNacimiento;
    }
    public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
        this.lblFechaNacimiento = lblFechaNacimiento;
    }
    public JComboBox getCbxDia() {
        return cbxDia;
    }
    public void setCbxDia(JComboBox cbxDia) {
        this.cbxDia = cbxDia;
    }
    public JComboBox getCbxMes() {
        return cbxMes;
    }
    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }
    public JComboBox getCbxAnio() {
        return cbxAnio;
    }
    public void setCbxAnio(JComboBox cbxAnio) {
        this.cbxAnio = cbxAnio;
    }
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
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
    public JLabel getLblTelefono() {
        return lblTelefono;
    }
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }
    public MensajeInternacionalizacionHandler gettipoIdioma() {
        return tipoIdioma;
    }
    public void settipoIdioma(MensajeInternacionalizacionHandler tipoIdioma) {
        this.tipoIdioma = tipoIdioma;
    }
}
