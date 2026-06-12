package esfe.presentacion;

import esfe.persistencia.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtNumeroCuenta;
    private JPasswordField txtPin;
    private JButton btnIngresar;
    private JButton btnCrearCuenta;

    public LoginForm() {
        setTitle("Cajero Automatico - Inicio de Sesion");
        setSize(420, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Cajero Automatico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(4, 1, 8, 8));

        JLabel lblCuenta = new JLabel("Numero de cuenta:");
        txtNumeroCuenta = new JTextField();

        JLabel lblPin = new JLabel("PIN / Contrasena:");
        txtPin = new JPasswordField();

        panelCampos.add(lblCuenta);
        panelCampos.add(txtNumeroCuenta);
        panelCampos.add(lblPin);
        panelCampos.add(txtPin);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1, 8, 8));

        btnIngresar = new JButton("Iniciar sesion");
        btnCrearCuenta = new JButton("Crear nueva cuenta");

        panelBotones.add(btnIngresar);
        panelBotones.add(btnCrearCuenta);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnIngresar.addActionListener(e -> iniciarSesion());
        btnCrearCuenta.addActionListener(e -> abrirCrearCuenta());
    }

    private void iniciarSesion() {
        String numeroCuenta = txtNumeroCuenta.getText().trim();
        String pin = new String(txtPin.getPassword()).trim();

        if (numeroCuenta.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese numero de cuenta y PIN.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarLogin(numeroCuenta, pin)) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + usuarioDAO.obtenerNombreUsuario(numeroCuenta));

            MenuForm menuForm = new MenuForm(numeroCuenta);
            menuForm.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Numero de cuenta o PIN incorrecto.");
        }
    }

    private void abrirCrearCuenta() {
        CrearCuentaForm crearCuentaForm = new CrearCuentaForm();
        crearCuentaForm.setVisible(true);
        dispose();
    }
}