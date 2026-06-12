package esfe.presentacion;

import esfe.persistencia.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame {

    private String numeroCuenta;

    public MenuForm() {
        this("1002003001");
    }

    public MenuForm(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

        setTitle("Menu Principal - Cajero Automatico");
        setSize(430, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String nombreUsuario = usuarioDAO.obtenerNombreUsuario(numeroCuenta);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblUsuario = new JLabel(
                "<html><center>Usuario: " + nombreUsuario + "<br>Cuenta: " + numeroCuenta + "</center></html>",
                SwingConstants.CENTER
        );

        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.add(lblTitulo);
        panelSuperior.add(lblUsuario);

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton btnSaldo = new JButton("Consultar saldo");
        JButton btnDeposito = new JButton("Depositar dinero");
        JButton btnRetiro = new JButton("Retirar dinero");
        JButton btnHistorial = new JButton("Historial de transacciones");
        JButton btnCerrar = new JButton("Cerrar sesion");

        panelBotones.add(btnSaldo);
        panelBotones.add(btnDeposito);
        panelBotones.add(btnRetiro);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);

        btnSaldo.addActionListener(e -> {
            ConsultaSaldoForm form = new ConsultaSaldoForm(numeroCuenta);
            form.setVisible(true);
        });

        btnDeposito.addActionListener(e -> {
            DepositoForm form = new DepositoForm(numeroCuenta);
            form.setVisible(true);
        });

        btnRetiro.addActionListener(e -> {
            RetiroForm form = new RetiroForm(numeroCuenta);
            form.setVisible(true);
        });

        btnHistorial.addActionListener(e -> {
            HistorialForm form = new HistorialForm(numeroCuenta);
            form.setVisible(true);
        });

        btnCerrar.addActionListener(e -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            dispose();
        });
    }
}