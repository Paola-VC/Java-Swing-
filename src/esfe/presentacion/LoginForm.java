package esfe.presentacion;

import esfe.servicio.CajeroService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario de inicio de sesión.
 * Permite al usuario ingresar su PIN para acceder al cajero automático.
 */
public class LoginForm extends JFrame {

    private JPasswordField txtPin;
    private JButton btnIngresar;
    private CajeroService cajeroService;

    /**
     * Constructor del formulario LoginForm.
     */
    public LoginForm() {

        // Se crea el servicio que manejará la lógica del cajero.
        cajeroService = new CajeroService();

        // Configuración principal de la ventana.
        setTitle("Cajero Automático - Iniciar Sesión");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    /**
     * Inicializa y organiza los componentes gráficos del formulario.
     */
    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Cajero Automático", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblPin = new JLabel("Ingrese su PIN:");
        txtPin = new JPasswordField();

        btnIngresar = new JButton("Ingresar");

        // Evento del botón ingresar.
        btnIngresar.addActionListener(e -> iniciarSesion());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblPin, gbc);

        gbc.gridx = 1;
        panel.add(txtPin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnIngresar, gbc);

        add(panel);
    }

    /**
     * Valida el PIN ingresado por el usuario.
     */
    private void iniciarSesion() {

        String pin = new String(txtPin.getPassword());

        // Se valida que el campo no esté vacío.
        if (pin.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un PIN.",
                    "Campo obligatorio",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Se valida el PIN usando el servicio del cajero.
        if (cajeroService.validarPin(pin)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido al sistema.",
                    "Acceso correcto",
                    JOptionPane.INFORMATION_MESSAGE
            );

            MenuForm menuForm = new MenuForm(cajeroService);
            menuForm.setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "PIN incorrecto.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPin.setText("");
        }
    }
}