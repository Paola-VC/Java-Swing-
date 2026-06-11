package esfe.presentacion;

import esfe.servicio.CajeroService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para realizar depósitos de dinero.
 */
public class DepositoForm extends JFrame {

    private JTextField txtMonto;
    private CajeroService cajeroService;

    /**
     * Constructor del formulario DepositoForm.
     *
     * @param cajeroService servicio compartido del cajero
     */
    public DepositoForm(CajeroService cajeroService) {
        this.cajeroService = cajeroService;

        setTitle("Depositar Dinero");
        setSize(380, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    /**
     * Inicializa los componentes gráficos para el depósito.
     */
    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Depósito de Dinero", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblMonto = new JLabel("Monto:");
        txtMonto = new JTextField();

        JButton btnDepositar = new JButton("Depositar");
        JButton btnCancelar = new JButton("Cancelar");

        btnDepositar.addActionListener(e -> realizarDeposito());
        btnCancelar.addActionListener(e -> dispose());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblMonto, gbc);

        gbc.gridx = 1;
        panel.add(txtMonto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btnDepositar, gbc);

        gbc.gridx = 1;
        panel.add(btnCancelar, gbc);

        add(panel);
    }

    /**
     * Valida el monto ingresado y realiza el depósito.
     */
    private void realizarDeposito() {

        try {
            double monto = Double.parseDouble(txtMonto.getText());

            // Se envía el monto al servicio para validar y depositar.
            boolean resultado = cajeroService.depositar(monto);

            if (resultado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Depósito realizado correctamente.\nNuevo saldo: $ "
                                + String.format("%.2f", cajeroService.consultarSaldo()),
                        "Depósito exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "El monto debe ser mayor que cero.",
                        "Monto inválido",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}