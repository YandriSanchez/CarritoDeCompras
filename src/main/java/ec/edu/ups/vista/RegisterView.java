package ec.edu.ups.vista;

import javax.swing.*;

public class RegisterView extends  JFrame{
    private JPanel panelPrincipal;
    private JPanel panelSecundario;
    private JTextField txtContrasena;
    private JButton btnRegistrarse;
    private JButton btnRegresar;
    private JComboBox CBoxRol;
    private JTextField txtUsuario;

    public RegisterView() {
        setContentPane(panelPrincipal);
        setTitle("YANDRI STORE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(JButton btnRegresar) {
        this.btnRegresar = btnRegresar;
    }

    public JComboBox getCBoxRol() {
        return CBoxRol;
    }

    public void setCBoxRol(JComboBox CBoxRol) {
        this.CBoxRol = CBoxRol;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }
}
