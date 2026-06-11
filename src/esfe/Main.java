package esfe;

import esfe.presentacion.LoginForm;

import javax.swing.SwingUtilities;

/**
 * Clase principal del sistema.
 * Desde aquí inicia la aplicación del Cajero Automático Swing.
 */
public class Main {

    /**
     * Método principal que ejecuta la aplicación.
     *
     * @param args argumentos de consola
     */
    public static void main(String[] args) {

        /*
         * SwingUtilities.invokeLater permite iniciar la interfaz gráfica
         * de forma segura dentro del hilo de eventos de Swing.
         */
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }
}