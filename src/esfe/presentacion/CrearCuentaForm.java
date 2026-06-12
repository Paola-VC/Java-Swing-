package esfe.presentacion;

import esfe.persistencia.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class CrearCuentaForm extends JFrame {

    private JTextField txtNombre;
    private JPasswordField txtPin;
    private JPasswordField txtConfirmarPin;
    private JTextField txtSaldoInicial;
    private JButton btnCrear;
    private JButton btnVolver;

    public CrearCuentaForm() {
        setTitle("Crear Nueva Cuenta");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Crear Nueva Cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel panelCampos = new JPanel(new GridLayout(8, 1, 8, 8));

        txtNombre = new JTextField();
        txtPin = new JPasswordField();
        txtConfirmarPin = new JPasswordField();
        txtSaldoInicial = new JTextField("0");

        panelCampos.add(new JLabel("Nombre del usuario:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("PIN / Contrasena:"));
        panelCampos.add(txtPin);
        panelCampos.add(new JLabel("Confirmar PIN:"));
        panelCampos.add(txtConfirmarPin);
        panelCampos.add(new JLabel("Saldo inicial:"));
        panelCampos.add(txtSaldoInicial);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 8, 8));

        btnCrear = new JButton("Crear cuenta");
        btnVolver = new JButton("Volver al login");

        panelBotones.add(btnCrear);
        panelBotones.add(btnVolver);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnCrear.addActionListener(e -> crearCuenta());
        btnVolver.addActionListener(e -> volverLogin());
    }

    private void crearCuenta() {
        String nombre = txtNombre.getText().trim();
        String pin = new String(txtPin.getPassword()).trim();
        String confirmarPin = new String(txtConfirmarPin.getPassword()).trim();
        String saldoTexto = txtSaldoInicial.getText().trim();

        if (nombre.isEmpty() || pin.isEmpty() || confirmarPin.isEmpty() || saldoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        if (!pin.equals(confirmarPin)) {
            JOptionPane.showMessageDialog(this, "El PIN y la confirmacion no coinciden.");
            return;
        }

        if (pin.length() < 4) {
            JOptionPane.showMessageDialog(this, "El PIN debe tener al menos 4 caracteres.");
            return;
        }

        double saldoInicial;

        try {
            saldoInicial = Double.parseDouble(saldoTexto);

            if (saldoInicial < 0) {
                JOptionPane.showMessageDialog(this, "El saldo inicial no puede ser negativo.");
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un saldo valido.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String numeroCuenta = usuarioDAO.crearCuenta(nombre, pin, saldoInicial);

        if (numeroCuenta != null) {
            JOptionPane.showMessageDialog(this,
                    "Cuenta creada correctamente.\n\nSu numero de cuenta es:\n" + numeroCuenta +
                            "\n\nGuarde este numero para iniciar sesion.");

            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo crear la cuenta.");
        }
    }

    private void volverLogin() {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        dispose();
    }
}