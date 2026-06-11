package esfe.presentacion;

import esfe.servicio.CajeroService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para consultar el saldo actual de la cuenta.
 */
public class ConsultaSaldoForm extends JFrame {

    private CajeroService cajeroService;

    /**
     * Constructor del formulario ConsultaSaldoForm.
     *
     * @param cajeroService servicio compartido del cajero
     */
    public ConsultaSaldoForm(CajeroService cajeroService) {
        this.cajeroService = cajeroService;

        setTitle("Consultar Saldo");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    /**
     * Inicializa los componentes gráficos para mostrar el saldo.
     */
    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel lblTitulo = new JLabel("Saldo Disponible", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblSaldo = new JLabel(
                "$ " + String.format("%.2f", cajeroService.consultarSaldo()),
                SwingConstants.CENTER
        );
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton btnCerrar = new JButton("Cerrar");

        // Cierra únicamente esta ventana.
        btnCerrar.addActionListener(e -> dispose());

        panel.add(lblTitulo);
        panel.add(lblSaldo);
        panel.add(btnCerrar);

        add(panel);
    }
}