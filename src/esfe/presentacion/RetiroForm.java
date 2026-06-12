package esfe.presentacion;

import esfe.persistencia.CuentaDAO;

import javax.swing.*;
import java.awt.*;

public class RetiroForm extends JFrame {

    private String numeroCuenta;
    private JTextField txtMonto;

    public RetiroForm() {
        this("1002003001");
    }

    public RetiroForm(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

        setTitle("Retiro de Dinero");
        setSize(370, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Retirar Dinero", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 8, 8));
        panelCentro.add(new JLabel("Monto a retirar:"));

        txtMonto = new JTextField();
        panelCentro.add(txtMonto);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 8, 8));
        JButton btnRetirar = new JButton("Retirar");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnRetirar);
        panelBotones.add(btnCerrar);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(panelCentro, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        btnRetirar.addActionListener(e -> retirar());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void retirar() {
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

        if (cuentaDAO.retirar(numeroCuenta, monto)) {
            JOptionPane.showMessageDialog(this, "Retiro realizado correctamente.");
            txtMonto.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo realizar el retiro. Verifique que tenga saldo suficiente.");
        }
    }
}