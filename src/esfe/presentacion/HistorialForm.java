package esfe.presentacion;

import esfe.persistencia.MovimientoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistorialForm extends JFrame {

    private String numeroCuenta;
    private JTable tablaMovimientos;

    public HistorialForm() {
        this("1002003001");
    }

    public HistorialForm(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

        setTitle("Historial de Transacciones");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblTitulo = new JLabel("Historial de Transacciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        MovimientoDAO movimientoDAO = new MovimientoDAO();
        DefaultTableModel modelo = movimientoDAO.obtenerHistorial(numeroCuenta);

        tablaMovimientos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaMovimientos);

        JButton btnCerrar = new JButton("Cerrar");

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnCerrar, BorderLayout.SOUTH);

        add(panel);

        btnCerrar.addActionListener(e -> dispose());
    }
}