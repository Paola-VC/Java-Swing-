package esfe.presentacion;

import esfe.servicio.CajeroService;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario principal del cajero automático.
 * Desde esta ventana el usuario puede acceder a las operaciones disponibles.
 */
public class MenuForm extends JFrame {

    private CajeroService cajeroService;

    /**
     * Constructor del formulario MenuForm.
     *
     * @param cajeroService servicio compartido del cajero
     */
    public MenuForm(CajeroService cajeroService) {
        this.cajeroService = cajeroService;

        setTitle("Cajero Automático - Menú Principal");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    /**
     * Inicializa los botones y componentes del menú principal.
     */
    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel lblBienvenida = new JLabel(
                "Bienvenido, " + cajeroService.obtenerNombreUsuario(),
                SwingConstants.CENTER
        );
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnConsultarSaldo = new JButton("Consultar Saldo");
        JButton btnDepositar = new JButton("Depositar Dinero");
        JButton btnRetirar = new JButton("Retirar Dinero");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        // Eventos de navegación hacia cada formulario.
        btnConsultarSaldo.addActionListener(e -> {
            ConsultaSaldoForm consultaSaldoForm = new ConsultaSaldoForm(cajeroService);
            consultaSaldoForm.setVisible(true);
        });

        btnDepositar.addActionListener(e -> {
            DepositoForm depositoForm = new DepositoForm(cajeroService);
            depositoForm.setVisible(true);
        });

        btnRetirar.addActionListener(e -> {
            RetiroForm retiroForm = new RetiroForm(cajeroService);
            retiroForm.setVisible(true);
        });

        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        panel.add(lblBienvenida);
        panel.add(btnConsultarSaldo);
        panel.add(btnDepositar);
        panel.add(btnRetirar);
        panel.add(btnCerrarSesion);

        add(panel);
    }

    /**
     * Cierra la sesión actual y regresa al formulario de login.
     */
    private void cerrarSesion() {

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cerrar sesión?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            dispose();
        }
    }
}