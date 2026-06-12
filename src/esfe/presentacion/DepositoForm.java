package esfe.presentacion;

import esfe.persistencia.CuentaDAO;

import javax.swing.*;
import java.awt.*;

public class DepositoForm extends JFrame {

    private String numeroCuenta;
    private JTextField txtMonto;

    public DepositoForm() {
        this("1002003001");
    }

    public DepositoForm(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

        setTitle("Deposito de Dinero");
        setSize(370, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Depositar Dinero", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 8, 8));
        panelCentro.add(new JLabel("Monto a depositar:"));

        txtMonto = new JTextField();
        panelCentro.add(txtMonto);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 8, 8));
        JButton btnDepositar = new JButton("Depositar");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnDepositar);
        panelBotones.add(btnCerrar);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(panelCentro, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        btnDepositar.addActionListener(e -> depositar());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void depositar() {
        String montoTexto = txtMonto.getText().trim();

        if (montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto.");
            return;
        }

        double monto;

        try {
            monto = Double.parseDouble(montoTexto);

            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.");
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto valido.");
            return;
        }

        CuentaDAO cuentaDAO = new CuentaDAO();

        if (cuentaDAO.depositar(numeroCuenta, monto)) {
            JOptionPane.showMessageDialog(this, "Deposito realizado correctamente.");
            txtMonto.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo realizar el deposito.");
        }
    }
}