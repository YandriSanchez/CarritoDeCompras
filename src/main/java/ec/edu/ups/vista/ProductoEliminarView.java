package ec.edu.ups.vista;

import javax.swing.*;

public class ProductoEliminarView extends JInternalFrame{
    private JPanel panelSecundario;
    private JTextField txtCodigo;
    private JButton btnEliminar;
    private JButton btnCerrar;
    private JTextArea txtAreaImprimir;
    private JPanel panelPrincipal;
    private JComboBox CboxForma;
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JLabel codigo;
    private JLabel nombre;

    public ProductoEliminarView() {

        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(485, 390);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        codigo.setVisible(false);
        txtCodigo.setVisible(false);
        nombre.setVisible(false);
        txtNombre.setVisible(false);
        btnBuscar.setEnabled(false);
    }

    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public void setBtnCerrar(JButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }

    public JTextArea getTxtAreaImprimir() {
        return txtAreaImprimir;
    }

    public void setTxtAreaImprimir(JTextArea txtAreaImprimir) {
        this.txtAreaImprimir = txtAreaImprimir;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JComboBox getCboxForma() {
        return CboxForma;
    }

    public void setCboxForma(JComboBox cboxForma) {
        CboxForma = cboxForma;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JLabel getCodigo() {
        return codigo;
    }

    public void setCodigo(JLabel codigo) {
        this.codigo = codigo;
    }

    public JLabel getNombre() {
        return nombre;
    }

    public void setNombre(JLabel nombre) {
        this.nombre = nombre;
    }
}
