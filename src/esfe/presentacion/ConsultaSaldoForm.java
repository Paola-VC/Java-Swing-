package esfe.presentacion;

import esfe.persistencia.CuentaDAO;

import javax.swing.*;
import java.awt.*;

public class ConsultaSaldoForm extends JFrame {

    private String numeroCuenta;

    public ConsultaSaldoForm() {
        this("1002003001");
    }

    public ConsultaSaldoForm(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

        setTitle("Consulta de Saldo");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        CuentaDAO cuentaDAO = new CuentaDAO();
        double saldo = cuentaDAO.obtenerSaldo(numeroCuenta);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Saldo Disponible", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblSaldo = new JLabel("$" + saldo, SwingConstants.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 28));

        JButton btnCerrar = new JButton("Cerrar");

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblSaldo, BorderLayout.CENTER);
        panel.add(btnCerrar, BorderLayout.SOUTH);

        add(panel);

        btnCerrar.addActionListener(e -> dispose());
    }
}